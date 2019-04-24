package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityPlateosaur;

public class PlateosaurSlab extends BaseSlab {
	public PlateosaurSlab() {
		super("PlateosaurSlab", "plateosaur_slab", 25, 140, 33, 250);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityPlateosaur plateosaur = new EntityPlateosaur(pl.world);

		plateosaur.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		plateosaur.setTamedBy(pl);
		pl.world.spawnEntity(plateosaur);

		return plateosaur;
	}
}
