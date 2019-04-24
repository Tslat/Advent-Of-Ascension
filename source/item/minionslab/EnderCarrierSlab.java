package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityEnderCarrier;

public class EnderCarrierSlab extends BaseSlab {
	public EnderCarrierSlab() {
		super("EnderCarrierSlab", "ender_carrier_slab", 70, 130, 70, 1000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityEnderCarrier enderCarrier = new EntityEnderCarrier(pl.world);

		enderCarrier.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		enderCarrier.setTamedBy(pl);
		pl.world.spawnEntity(enderCarrier);

		return enderCarrier;
	}
}
