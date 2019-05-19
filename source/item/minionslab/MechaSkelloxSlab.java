package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityMechaSkellox;

public class MechaSkelloxSlab extends BaseSlab {
	public MechaSkelloxSlab() {
		super("MechaSkelloxSlab", "mecha_skellox_slab", 92, 200, 92, 8000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityMechaSkellox mechaSkellox = new EntityMechaSkellox(pl.world);

		mechaSkellox.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		mechaSkellox.setTamedBy(pl);
		pl.world.spawnEntity(mechaSkellox);

		return mechaSkellox;
	}
}
