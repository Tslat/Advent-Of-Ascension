package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

import java.util.UUID;

public class EntityFireflyShot extends BaseEnergyShot {
	public final UUID lastTargetUUID;

	public EntityFireflyShot(World world) {
		super(world);

		this.lastTargetUUID = null;
	}

	public EntityFireflyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, EntityFireflyShot shot, UUID lastTargetUUID, double motionX, double motionY, double motionZ) {
		super(shooter, weapon, shot.posX, shot.posY, shot.posZ, motionX, motionY, motionZ);

		this.lastTargetUUID = lastTargetUUID;
	}

	public EntityFireflyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, EntityFireflyShot shot, double motionX, double motionY, double motionZ) {
		super(shooter, weapon, shot.posX, shot.posY, shot.posZ, motionX, motionY, motionZ);

		this.lastTargetUUID = null;
	}

	public EntityFireflyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);

		this.lastTargetUUID = null;
	}

	public EntityFireflyShot(World world, double x, double y, double z) {
		super(world, x, y, z);

		this.lastTargetUUID = null;
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}
}
