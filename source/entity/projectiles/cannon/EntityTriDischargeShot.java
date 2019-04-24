package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityTriDischargeShot extends BaseBullet implements HardProjectile {
	public EntityTriDischargeShot(World world) {
		super(world);
	}

	public EntityTriDischargeShot(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public EntityTriDischargeShot(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityTriDischargeShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		world.createExplosion(getThrower(), posX, posY, posZ, 2.5f, false);
	}
}
