package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.EnderCarrierEntity;

public class EnderCarrierSlab extends BaseSlab {
	public EnderCarrierSlab() {
		super(70, 130, 70, 1000);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		EnderCarrierEntity enderCarrier = new EnderCarrierEntity(AoAEntities.Minions.ENDER_CARRIER.get(), pl.world);

		enderCarrier.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		enderCarrier.setTamedBy(pl);
		pl.world.addEntity(enderCarrier);

		return enderCarrier;
	}
}
