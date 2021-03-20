package net.tslat.aoa3.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;

public interface EnergyProjectileWeapon {
	Hand getWeaponHand(LivingEntity holder);

	void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter);

	boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter);
}
