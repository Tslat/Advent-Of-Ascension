package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityBlissard;

public class BlissardSlab extends BaseSlab {
	public BlissardSlab() {
		super("BlissardSlab", "blissard_slab", 70, 180, 70, 2500);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityBlissard blissard = new EntityBlissard(pl.world);

		blissard.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		blissard.setTamedBy(pl);
		pl.world.spawnEntity(blissard);

		return blissard;
	}
}
