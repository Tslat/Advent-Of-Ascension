package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityConstructOfServility;

public class ConstructOfServilitySlab extends BaseSlab {
	public ConstructOfServilitySlab() {
		super("ConstructOfServilitySlab", "construct_of_servility_slab", 83, 180, 83, 3000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityConstructOfServility constructOfServility = new EntityConstructOfServility(pl.world);

		constructOfServility.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		constructOfServility.setTamedBy(pl);
		pl.world.spawnEntity(constructOfServility);

		return constructOfServility;
	}
}
