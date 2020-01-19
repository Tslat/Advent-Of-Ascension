package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntitySpikeback;

public class SpikebackSlab extends BaseSlab {
	public SpikebackSlab() {
		super("SpikebackSlab", "spikeback_slab", 16, 140, 16, 220);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntitySpikeback spikeback = new EntitySpikeback(pl.world);

		spikeback.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		spikeback.setTamedBy(pl);
		pl.world.spawnEntity(spikeback);

		return spikeback;
	}
}
