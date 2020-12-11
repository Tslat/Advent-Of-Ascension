package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.HorntailEntity;

public class HorntailSlab extends BaseSlab {
	public HorntailSlab() {
		super(54, 160, 54, 2000);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		HorntailEntity horntail = new HorntailEntity(AoAEntities.Minions.HORNTAIL.get(), pl.world);

		horntail.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		horntail.setTamedBy(pl);
		pl.world.addEntity(horntail);

		return horntail;
	}
}
