package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityGnawer;

public class GnawerSlab extends BaseSlab {
	public GnawerSlab() {
		super("GnawerSlab", "gnawer_slab", 68, 180, 60, 2000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityGnawer gnawer = new EntityGnawer(pl.world);

		gnawer.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		gnawer.setTamedBy(pl);
		pl.world.spawnEntity(gnawer);

		return gnawer;
	}
}
