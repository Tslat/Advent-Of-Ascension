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
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), shooter.world);
		this.target = target;
		this.shooter = shooter;

		setLocationAndAngles(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public ErebonSticklerStuckEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}

	@Override
	protected void onImpact(RayTraceResult result) {}

	@Override
	public void tick() {
		super.tick();

		if (world.isRemote)
			return;

		age++;

		if (target != null && target.isAlive()) {
			setLocationAndAngles(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ(), 0, 360);
		}
		else {
			WorldUtil.createExplosion(shooter, world, this, 2.0f);
			remove();

			if (cloud != null)
				cloud.remove();

			return;
		}

		if (age >= 100) {
			WorldUtil.createExplosion(owner, world, getPosX(), getPosY() + 1, getPosZ(), 2.0f);
			remove();

			if (cloud != null)
				cloud.remove();

			return;
		}

		if (cloud == null) {
			cloud = new AreaEffectCloudEntity(world, target.getPosX(), target.getPosY(), target.getPosZ());

			cloud.setDuration(100 - age);
			cloud.setRadius(2);
			cloud.addEffect(new EffectInstance(Effects.WITHER, 40, 0, false, true));
			world.addEntity(cloud);
		}
		else {
			cloud.setPositionAndUpdate(target.getPosX(), target.getPosY(), target.getPosZ());
		}
	}

	@Override
	protected void registerData() {}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
