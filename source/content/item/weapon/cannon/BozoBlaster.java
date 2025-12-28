package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.cannon.BozoBallEntity;

public class BozoBlaster extends AoACannon {
	public BozoBlaster(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new BozoBallEntity(level, context);
	}
}
