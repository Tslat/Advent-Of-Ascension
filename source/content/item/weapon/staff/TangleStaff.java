package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TangleStaff extends AoAStaff<Vec3> {
	public TangleStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<Vec3> checkPreconditions(LivingEntity caster, ItemStack staff) {
		RayTrace<LivingEntity> rayTrace = RayTrace.createForEyeline(caster, 70).forEntities(LivingEntity.class).run();

		return rayTrace.missed() ? Optional.empty() : Optional.of(rayTrace.hitPos());
	}

	@Override
	public WeaponFiringContext.Builder createProjectileContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return super.createProjectileContext(stack, shooter, hand).lifespan(120);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Vec3 args) {
		WeaponFiringContext firingContext = createProjectileContext(staff, caster, hand).build();

		for (int i = 0; i < 8; i++) {
			fireProjectile(level, firingContext, (serverLevel, context) ->
								   new NonPhysicalWeaponProjectile(AoAProjectiles.TANGLE_FALL.get(), serverLevel, context),
						   (context, projectile) -> projectile
								   .fromPos(args.add(RandomUtil.valueBetween(-4, 4), 30, RandomUtil.valueBetween(-4, 4)))
								   .shootingTowards(new Vec3(0, -1, 0), 3, 0.1f));
		}
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		EntityUtil.applyPotions(hitEntity, projectile == null ? null : projectile.getShooter(), new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 60).level(50));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
