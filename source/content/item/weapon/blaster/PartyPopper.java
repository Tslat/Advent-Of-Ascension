package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class PartyPopper extends AoABlaster {
	public PartyPopper(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireBasicBlasterProjectile(level, context, AoAProjectiles.PARTY_POPPER_SHOT);
	}
}
