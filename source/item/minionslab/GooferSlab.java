package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.GooferEntity;

public class GooferSlab extends BaseSlab {
	public GooferSlab() {
		super(74, 180, 74, 500);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		GooferEntity goofer = new GooferEntity(AoAEntities.Minions.GOOFER.get(), pl.world);

		goofer.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		goofer.setTamedBy(pl);
		pl.world.addEntity(goofer);

		return goofer;
	}
}
