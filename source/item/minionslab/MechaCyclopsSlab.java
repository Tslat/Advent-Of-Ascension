package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityMechaCyclops;

public class MechaCyclopsSlab extends BaseSlab {
	public MechaCyclopsSlab() {
		super("MechaCyclopsSlab", "mecha_cyclops_slab", 78, 160, 78, 4500);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityMechaCyclops mechaCyclops = new EntityMechaCyclops(pl.world);

		mechaCyclops.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		mechaCyclops.setTamedBy(pl);
		pl.world.spawnEntity(mechaCyclops);

		return mechaCyclops;
	}
}
