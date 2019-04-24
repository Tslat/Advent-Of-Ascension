package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityAlluricorn;

public class AlluricornSlab extends BaseSlab {
	public AlluricornSlab() {
		super("AlluricornSlab", "alluricorn_slab", 81, 180, 80, 4000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityAlluricorn alluricorn = new EntityAlluricorn(pl.world);

		alluricorn.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		alluricorn.setTamedBy(pl);
		pl.world.spawnEntity(alluricorn);

		return alluricorn;
	}
}
