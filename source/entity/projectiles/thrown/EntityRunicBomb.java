package net.tslat.aoa3.entity.projectiles.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.mobs.mysterium.EntityRunicGolem;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityRunicBomb extends BaseBullet implements HardProjectile {
	private float explosionStrength = 1.5f;
	private EntityLivingBase shooter;

	public EntityRunicBomb(World world) {
		super(world);
	}

	public EntityRunicBomb(EntityLivingBase shooter, BaseGun gun) {
		super(shooter, gun, 1.0f, 0, 1.5f);
		this.shooter = shooter;
	}

	public EntityRunicBomb(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityRunicBomb(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	public void doImpactEffect() {
		world.createExplosion(shooter, posX, posY, posZ, explosionStrength, false);

		for (EntityMob e : world.getEntitiesWithinAABB(EntityMob.class, getEntityBoundingBox().grow(3.0D))) {
			e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 100, true, false));

			if (e instanceof EntityRunicGolem && ((EntityRunicGolem)e).isShielded())
				((EntityRunicGolem)e).deactivateShield();
		}
	}

	@Override
	public void doEntityImpact(Entity target) {
		world.createExplosion(shooter, posX, posY, posZ, explosionStrength, false);

		for (EntityMob e : world.getEntitiesWithinAABB(EntityMob.class, getEntityBoundingBox().grow(3.0D))) {
			e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 100, true, false));
		}

		if (target instanceof EntityRunicGolem && ((EntityRunicGolem)target).isShielded())
			((EntityRunicGolem)target).deactivateShield();
	}
}
