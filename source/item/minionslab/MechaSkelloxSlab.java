package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.MechaSkelloxEntity;

public class MechaSkelloxSlab extends BaseSlab {
	public MechaSkelloxSlab() {
		super(92, 200, 92, 8000);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		MechaSkelloxEntity mechaSkellox = new MechaSkelloxEntity(AoAEntities.Minions.MECHA_SKELLOX.get(), pl.world);

		mechaSkellox.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		mechaSkellox.setTamedBy(pl);
		pl.world.addEntity(mechaSkellox);

		return mechaSkellox;
	}
}
