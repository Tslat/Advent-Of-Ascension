package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityRammerhorn;

public class RammerhornSlab extends BaseSlab {
	public RammerhornSlab() {
		super("RammerhornSlab", "rammerhorn_slab", 59, 140, 27, 200);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityRammerhorn rammerhorn = new EntityRammerhorn(pl.world);

		rammerhorn.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		rammerhorn.setTamedBy(pl);
		pl.world.spawnEntity(rammerhorn);

		return rammerhorn;
	}
}
