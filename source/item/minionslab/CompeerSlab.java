package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.CompeerEntity;

public class CompeerSlab extends BaseSlab {
	public CompeerSlab() {
		super(10, 120, 10, 40);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		CompeerEntity compeer = new CompeerEntity(AoAEntities.Minions.COMPEER.get(), pl.level);

		compeer.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		compeer.tame(pl);
		pl.level.addFreshEntity(compeer);

		return compeer;
	}
}
