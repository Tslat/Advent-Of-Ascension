package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityRPG extends BaseBullet implements HardProjectile {
	private EntityLivingBase shooter;

	public EntityRPG(World world) {
		super(world);
	}

	public EntityRPG(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public EntityRPG(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		if (!world.isRemote)
			WorldUtil.createExplosion(shooter, world, this, 2.7f);
	}
}
