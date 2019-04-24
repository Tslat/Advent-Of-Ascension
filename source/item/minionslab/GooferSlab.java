package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityGoofer;

public class GooferSlab extends BaseSlab {
	public GooferSlab() {
		super("GooferSlab", "goofer_slab", 74, 180, 74, 500);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityGoofer goofer = new EntityGoofer(pl.world);

		goofer.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		goofer.setTamedBy(pl);
		pl.world.spawnEntity(goofer);

		return goofer;
	}
}
