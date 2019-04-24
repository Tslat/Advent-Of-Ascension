package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntitySmileyCannonball extends BaseBullet implements HardProjectile {
	public EntitySmileyCannonball(World world) {
		super(world);
	}

	public EntitySmileyCannonball(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntitySmileyCannonball(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

}
