package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityHeavyTriDischargeShot extends BaseBullet implements HardProjectile {
	public EntityHeavyTriDischargeShot(World world) {
		super(world);
	}

	public EntityHeavyTriDischargeShot(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public EntityHeavyTriDischargeShot(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityHeavyTriDischargeShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
