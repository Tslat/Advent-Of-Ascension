package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityHollyArrowShot extends BaseBullet implements HardProjectile {
	public EntityHollyArrowShot(World world) {
		super(world);
	}

	public EntityHollyArrowShot(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityHollyArrowShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05000000074505806F;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);

		if (!world.isRemote && isDead && result.typeOfHit == RayTraceResult.Type.BLOCK) {
			EntityHollyArrow hollyArrow = ItemRegister.hollyArrow.createArrow(world, this);

			hollyArrow.inGround = true;
			hollyArrow.motionX = motionX;
			hollyArrow.motionY = motionY;
			hollyArrow.motionZ = motionZ;
			hollyArrow.velocityChanged = true;

			if ((getThrower() instanceof EntityPlayer && ((EntityPlayer)getThrower()).capabilities.isCreativeMode) || getWeapon() == WeaponRegister.archergunSpectral)
				hollyArrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;

			world.spawnEntity(hollyArrow);
		}
	}
}
