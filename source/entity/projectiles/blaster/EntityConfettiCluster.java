package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

public class EntityConfettiCluster extends BaseEnergyShot {
	public EntityConfettiCluster(World world) {
		super(world);
	}

	public EntityConfettiCluster(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityConfettiCluster(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote)
			setDead();
	}
}
