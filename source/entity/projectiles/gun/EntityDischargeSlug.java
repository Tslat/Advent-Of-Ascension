package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityDischargeSlug extends BaseBullet implements HardProjectile {
	public EntityDischargeSlug(World world) {
		super(world);
	}

	public EntityDischargeSlug(EntityLivingBase shooter, BaseGun gun, int piercingValue) {
		super(shooter, gun, 1.0f, piercingValue, 20.0f);
	}

	public EntityDischargeSlug(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityDischargeSlug(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getThrower(), world, this, 1.8f);
	}
}
