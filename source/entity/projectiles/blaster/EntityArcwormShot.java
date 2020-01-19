package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.utils.StringUtil;

public class EntityArcwormShot extends BaseEnergyShot {
	public EntityArcwormShot(World world) {
		super(world);
	}

	public EntityArcwormShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityArcwormShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		float motionVector = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)(MathHelper.atan2(motionX, motionZ) * (180D / Math.PI));
		rotationPitch = (float)(MathHelper.atan2(motionY, motionVector) * (180D / Math.PI));
		prevRotationYaw = rotationYaw;
		prevRotationPitch = rotationPitch;
	}

	@Override
	public String getName() {
		if (hasCustomName()) {
			return getCustomNameTag();
		}
		else {
			return StringUtil.getLocaleString("entity.aoa3.arcworm.name");
		}
	}
}
