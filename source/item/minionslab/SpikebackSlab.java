package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.SpikebackEntity;

public class SpikebackSlab extends BaseSlab {
	public SpikebackSlab() {
		super(16, 140, 16, 220);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		SpikebackEntity spikeback = new SpikebackEntity(AoAEntities.Minions.SPIKEBACK.get(), pl.level);

		spikeback.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		spikeback.tame(pl);
		pl.level.addFreshEntity(spikeback);

		return spikeback;
	}
}
