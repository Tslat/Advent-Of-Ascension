package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.CraggyEntity;

public class CraggySlab extends BaseSlab {
	public CraggySlab() {
		super( 45, 160, 45, 400);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		CraggyEntity craggy = new CraggyEntity(AoAEntities.Minions.CRAGGY.get(), pl.world);

		craggy.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		craggy.setTamedBy(pl);
		pl.world.addEntity(craggy);

		return craggy;
	}
}
