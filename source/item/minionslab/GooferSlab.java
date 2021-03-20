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
		GooferEntity goofer = new GooferEntity(AoAEntities.Minions.GOOFER.get(), pl.level);

		goofer.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		goofer.tame(pl);
		pl.level.addFreshEntity(goofer);

		return goofer;
	}
}
