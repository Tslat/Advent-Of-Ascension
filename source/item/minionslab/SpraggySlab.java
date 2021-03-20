package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.SpraggyEntity;

public class SpraggySlab extends BaseSlab {
	public SpraggySlab() {
		super(30, 140, 30, 200);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		SpraggyEntity spraggy = new SpraggyEntity(AoAEntities.Minions.SPRAGGY.get(), pl.level);

		spraggy.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		spraggy.tame(pl);
		pl.level.addFreshEntity(spraggy);

		return spraggy;
	}
}
