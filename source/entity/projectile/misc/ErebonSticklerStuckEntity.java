package net.tslat.aoa3.entity.projectile.misc;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class ErebonSticklerStuckEntity extends ThrowableEntity {
	private LivingEntity target;
	private LivingEntity shooter;
	private int age;
	private AreaEffectCloudEntity cloud = null;

	public ErebonSticklerStuckEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ErebonSticklerStuckEntity(World world) {
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), world);
	}

	public ErebonSticklerStuckEntity(LivingEntity shooter, BaseGun gun, LivingEntity target, float bulletDmgMultiplier) {
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), shooter.level);
		this.target = target;
		this.shooter = shooter;

		moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public ErebonSticklerStuckEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}

	@Override
	protected void onHit(RayTraceResult result) {}

	@Override
	public void tick() {
		super.tick();

		if (level.isClientSide)
			return;

		age++;

		if (target != null && target.isAlive()) {
			moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 360);
		}
		else {
			WorldUtil.createExplosion(shooter, level, this, 2.0f);
			remove();

			if (cloud != null)
				cloud.remove();

			return;
		}

		if (age >= 100) {
			WorldUtil.createExplosion(getOwner(), level, getX(), getY() + 1, getZ(), 2.0f);
			remove();

			if (cloud != null)
				cloud.remove();

			return;
		}

		if (cloud == null) {
			cloud = new AreaEffectCloudEntity(level, target.getX(), target.getY(), target.getZ());

			cloud.setDuration(100 - age);
			cloud.setRadius(2);
			cloud.addEffect(new EffectInstance(Effects.WITHER, 40, 0, false, true));
			level.addFreshEntity(cloud);
		}
		else {
			cloud.teleportTo(target.getX(), target.getY(), target.getZ());
		}
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
