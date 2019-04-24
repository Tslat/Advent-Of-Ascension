package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityCompeer;

public class CompeerSlab extends BaseSlab {
	public CompeerSlab() {
		super("CompeerSlab", "compeer_slab", 10, 120, 10, 40);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityCompeer compeer = new EntityCompeer(pl.world);

		compeer.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		compeer.setTamedBy(pl);
		pl.world.spawnEntity(compeer);

		return compeer;
	}
}
