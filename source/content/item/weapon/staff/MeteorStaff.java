package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MeteorStaff extends AoAStaff<Vec3> {
	public MeteorStaff(Item.Properties properties) {
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
								   new NonPhysicalWeaponProjectile(AoAProjectiles.METEOR_FALL.get(), serverLevel, context),
						   (context, projectile) -> projectile
								   .fromPos(args.add(RandomUtil.valueBetween(-4, 4), 30, RandomUtil.valueBetween(-4, 4)))
								   .shootingTowards(new Vec3(0, -1, 0), 3, 0.1f));
		}
	}

	@Override
	protected void onHitBlock(Level level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<Void> rayTrace, BlockState hitBlock) {
		explode(level, rayTrace.hitPos(), projectile.asEntity(), projectile.getShooter());
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		explode(level, rayTrace.hitPos(), projectile.asEntity(), projectile.getShooter());
	}

	protected void explode(Level level, Vec3 position, @Nullable Entity projectile, @Nullable Entity shooter) {
		if (level instanceof ServerLevel serverLevel)
			AoAExplosionBuilder.at(serverLevel, position, AoAExplosions.METEOR_STAFF, StandardExplosion::new).explodingEntity(projectile).explode();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);
		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.METEOR_STAFF, false, flag.isAdvanced(), false)) {
			tooltip.add(3, component);
		}
	}
}
