package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityRockFragmentFungal extends BaseBullet implements HardProjectile {
	public EntityRockFragmentFungal(World world) {
		super(world);
	}

	public EntityRockFragmentFungal(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityRockFragmentFungal(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
}
