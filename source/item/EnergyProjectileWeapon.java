package net.tslat.aoa3.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;

public interface EnergyProjectileWeapon {
	Hand getWeaponHand(LivingEntity holder);

	void doBlockImpact(BaseEnergyShot shot, Vec3d hitPos, LivingEntity shooter);

	boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter);
}
