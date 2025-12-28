package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.staff.NoxiousShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class NoxiousStaff extends AoAStaff<Object> {
	public NoxiousStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponFiringContext.Builder createProjectileContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return super.createProjectileContext(stack, shooter, hand).degreesInaccuracy(13.33f);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		WeaponFiringContext firingContext = createProjectileContext(staff, caster, hand).build();

		for (int i = 0; i < 6; i++) {
			fireProjectile(level, firingContext, NoxiousShotEntity::new, (context, projectile) ->
					projectile.fromArmPos().shootingAtTarget(context.velocity(), context.inaccuracy()));
		}
	}

	@Override
	protected void onHitBlock(Level level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<Void> rayTrace, BlockState hitBlock) {
		spawnCloud(level, rayTrace.hitPos(), projectile.getShooter());
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		EntityUtil.applyPotions(hitEntity, projectile == null ? null : projectile.getShooter(), new EffectBuilder(MobEffects.POISON, 100).level(3));
		spawnCloud(level, rayTrace.hitPos(), projectile.getShooter());
	}

	protected void spawnCloud(Level level, Vec3 location, @Nullable Entity caster) {
		AreaEffectCloud cloud = new AreaEffectCloud(level, location.x, location.y, location.z);

		cloud.setRadius(3);
		cloud.setPotionContents(new PotionContents(Optional.of(Potions.STRONG_POISON), Optional.of(ColourUtil.RGB(51, 102, 0)), List.of(new MobEffectInstance(MobEffects.POISON, 100, 2, true, true))));
		cloud.setDuration(3);

		if (caster instanceof LivingEntity livingCaster)
			cloud.setOwner(livingCaster);

		level.addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
