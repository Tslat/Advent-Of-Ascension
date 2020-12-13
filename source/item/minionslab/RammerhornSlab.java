package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.RammerhornEntity;

public class RammerhornSlab extends BaseSlab {
	public RammerhornSlab() {
		super(27, 140, 27, 200);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		RammerhornEntity rammerhorn = new RammerhornEntity(AoAEntities.Minions.RAMMERHORN.get(), pl.world);

		rammerhorn.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		rammerhorn.setTamedBy(pl);
		pl.world.addEntity(rammerhorn);

		return rammerhorn;
	}
}
