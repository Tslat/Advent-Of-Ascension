package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntitySeedDart extends BaseBullet implements HardProjectile {
	public EntitySeedDart(World world) {
		super(world);
	}

	public EntitySeedDart(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntitySeedDart(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
