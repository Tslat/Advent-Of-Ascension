package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityHellquin;

public class HellquinSlab extends BaseSlab {
	public HellquinSlab() {
		super("HellquinSlab", "hellquin_slab", 34, 140, 45, 500);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityHellquin hellquin = new EntityHellquin(pl.world);

		hellquin.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		hellquin.setTamedBy(pl);
		pl.world.spawnEntity(hellquin);

		return hellquin;
	}
}
