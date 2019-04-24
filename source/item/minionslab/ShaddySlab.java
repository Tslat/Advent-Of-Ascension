package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityShaddy;

public class ShaddySlab extends BaseSlab {
	public ShaddySlab() {
		super("ShaddySlab", "shaddy_slab", 60, 160, 60, 850);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityShaddy shaddy = new EntityShaddy(pl.world);

		shaddy.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		shaddy.setTamedBy(pl);
		pl.world.spawnEntity(shaddy);

		return shaddy;
	}
}
