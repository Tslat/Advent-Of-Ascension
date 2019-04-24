package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityWaggy;

public class WaggySlab extends BaseSlab {
	public WaggySlab() {
		super("WaggySlab", "waggy_slab", 15, 120, 15, 50);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityWaggy waggy = new EntityWaggy(pl.world);

		waggy.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		waggy.setTamedBy(pl);
		pl.world.spawnEntity(waggy);

		return waggy;
	}
}
