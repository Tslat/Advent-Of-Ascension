package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityCorby;

public class CorbySlab extends BaseSlab {
	public CorbySlab() {
		super("CorbySlab", "corby_slab", 63, 120, 63, 3000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityCorby corby = new EntityCorby(pl.world);

		corby.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		corby.setTamedBy(pl);
		pl.world.spawnEntity(corby);

		return corby;
	}
}
