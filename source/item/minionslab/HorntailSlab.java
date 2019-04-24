package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityHorntail;

public class HorntailSlab extends BaseSlab {
	public HorntailSlab() {
		super("HorntailSlab", "horntail_slab", 54, 160, 54, 2000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityHorntail horntail = new EntityHorntail(pl.world);

		horntail.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		horntail.setTamedBy(pl);
		pl.world.spawnEntity(horntail);

		return horntail;
	}
}
