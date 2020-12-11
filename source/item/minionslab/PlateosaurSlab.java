package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.PlateosaurEntity;

public class PlateosaurSlab extends BaseSlab {
	public PlateosaurSlab() {
		super(33, 140, 33, 250);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		PlateosaurEntity plateosaur = new PlateosaurEntity(AoAEntities.Minions.PLATEOSAUR.get(), pl.world);

		plateosaur.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		plateosaur.setTamedBy(pl);
		pl.world.addEntity(plateosaur);

		return plateosaur;
	}
}
