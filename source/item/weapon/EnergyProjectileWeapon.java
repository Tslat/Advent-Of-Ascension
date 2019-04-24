package net.tslat.aoa3.item.weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;

public interface EnergyProjectileWeapon {
	EnumHand getWeaponHand();

	void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter);

	void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter);
}
