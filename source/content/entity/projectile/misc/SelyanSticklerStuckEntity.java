package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;

public class SelyanSticklerStuckEntity extends ThrowableProjectile {
	private LivingEntity target;
	private LivingEntity shooter;
	private int age;

	public SelyanSticklerStuckEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public SelyanSticklerStuckEntity(Level world) {
		super(AoAProjectiles.SELYAN_STICKLER_STUCK.get(), world);
	}

	public SelyanSticklerStuckEntity(LivingEntity shooter, BaseGun gun, LivingEntity target, float bulletDmgMultiplier) {
		super(AoAProjectiles.SELYAN_STICKLER_STUCK.get(), shooter.level);
		this.target = target;
		this.shooter = shooter;
		moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public SelyanSticklerStuckEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.SELYAN_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}

	@Override
	protected void onHit(HitResult result) {}

	@Override
	public void tick() {
		super.tick();

		age++;

		if (level.isClientSide)
			return;

		if (target != null && target.isAlive()) {
			moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 360);
			EntityUtil.healEntity(shooter, 0.03f);
		}
		else {
			WorldUtil.createExplosion(getOwner(), level, this, 2.0f);

			if (!level.isClientSide)
				discard();
		}

		if (age >= 100) {
			WorldUtil.createExplosion(getOwner(), level, getX(), getY() + 1, getZ(), 2.0f);

			if (!level.isClientSide)
				discard();
		}
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
