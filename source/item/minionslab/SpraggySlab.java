package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntitySpraggy;

public class SpraggySlab extends BaseSlab {
	public SpraggySlab() {
		super("SpraggySlab", "spraggy_slab", 30, 140, 30, 200);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntitySpraggy spraggy = new EntitySpraggy(pl.world);

		spraggy.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		spraggy.setTamedBy(pl);
		pl.world.spawnEntity(spraggy);

		return spraggy;
	}
}
