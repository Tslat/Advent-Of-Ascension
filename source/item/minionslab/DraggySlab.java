package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityDraggy;

public class DraggySlab extends BaseSlab {
	public DraggySlab() {
		super("DraggySlab", "draggy_slab", 1, 100, 1, 20);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityDraggy draggy = new EntityDraggy(pl.world);

		draggy.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		draggy.setTamedBy(pl);
		pl.world.spawnEntity(draggy);

		return draggy;
	}
}
