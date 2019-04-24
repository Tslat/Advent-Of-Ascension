package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityCraggy;

public class CraggySlab extends BaseSlab {
	public CraggySlab() {
		super("CraggySlab", "craggy_slab", 45, 160, 45, 400);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityCraggy craggy = new EntityCraggy(pl.world);

		craggy.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		craggy.setTamedBy(pl);
		pl.world.spawnEntity(craggy);

		return craggy;
	}
}
