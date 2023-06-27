package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.mob.mysterium.RunicGolemEntity;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

public class RunicBombEntity extends BaseBullet implements HardProjectile, ItemSupplier {
	private float explosionStrength = 1.5f;
	private LivingEntity shooter;

	public RunicBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public RunicBombEntity(Level world) {
		super(AoAProjectiles.RUNIC_BOMB.get(), world);
	}

	public RunicBombEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAProjectiles.RUNIC_BOMB.get(), shooter, gun, 1.0f, 0, 1.5f);
		this.shooter = shooter;
	}

	public RunicBombEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.RUNIC_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public RunicBombEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.RUNIC_BOMB.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public void doBlockImpact(Vec3 impactLocation, Direction face, BlockPos blockPos) {
		explode();
	}

	@Override
	public void doEntityImpact(Entity target, Vec3 impactLocation) {
		explode();
	}

	private void explode() {
		WorldUtil.createExplosion(shooter, level(), this, explosionStrength);

		for (LivingEntity e : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(3.0D), EntityUtil.Predicates.HOSTILE_MOB)) {
			EntityUtil.applyPotions(e, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 30).level(100));

			if (e instanceof RunicGolemEntity && ((RunicGolemEntity)e).isShielded())
				((RunicGolemEntity)e).deactivateShield();
		}
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.RUNIC_BOMB.get());
	}
}
