package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.BlissardEntity;

public class BlissardSlab extends BaseSlab {
	public BlissardSlab() {
		super(70, 180, 70, 2500);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		BlissardEntity blissard = new BlissardEntity(AoAEntities.Minions.BLISSARD.get(), pl.world);

		blissard.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		blissard.setTamedBy(pl);
		pl.world.addEntity(blissard);

		return blissard;
	}
}
