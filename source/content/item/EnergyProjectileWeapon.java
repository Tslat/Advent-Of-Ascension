package net.tslat.aoa3.content.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;

public interface EnergyProjectileWeapon {
	InteractionHand getWeaponHand(LivingEntity holder);

	void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter);

	boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter);
}
