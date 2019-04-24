package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityPenguin;

public class PenguinSlab extends BaseSlab {
	public PenguinSlab() {
		super("PenguinSlab", "penguin_slab", 1, 120, 1, 9);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityPenguin penguin = new EntityPenguin(pl.world);

		penguin.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		penguin.setTamedBy(pl);
		pl.world.spawnEntity(penguin);

		return penguin;
	}
}
