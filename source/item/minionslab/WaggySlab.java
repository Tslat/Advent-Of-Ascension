package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.WaggyEntity;

public class WaggySlab extends BaseSlab {
	public WaggySlab() {
		super(15, 120, 15, 50);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		WaggyEntity waggy = new WaggyEntity(AoAEntities.Minions.WAGGY.get(), pl.world);

		waggy.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		waggy.setTamedBy(pl);
		pl.world.addEntity(waggy);

		return waggy;
	}
}
