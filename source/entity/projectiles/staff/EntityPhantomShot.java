package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

import java.util.UUID;

public class EntityPhantomShot extends BaseEnergyShot {
	private UUID lastHit = null;

	public EntityPhantomShot(World world) {
		super(world);
	}

	public EntityPhantomShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityPhantomShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (weapon != null) {
				if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
					weapon.doBlockImpact(this, result.getBlockPos(), this.thrower);
					setDead();
				}
				else if (result.entityHit != null && !result.entityHit.getUniqueID().equals(lastHit)) {
					weapon.doEntityImpact(this, result.entityHit, thrower);
				}
			}
		}
	}
}
