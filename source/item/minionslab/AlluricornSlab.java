package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AlluricornEntity;
import net.tslat.aoa3.entity.minion.AoAMinion;

public class AlluricornSlab extends BaseSlab {
	public AlluricornSlab() {
		super(80, 180, 80, 4000);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		AlluricornEntity alluricorn = new AlluricornEntity(AoAEntities.Minions.ALLURICORN.get(), pl.level);

		alluricorn.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		alluricorn.tame(pl);
		pl.level.addFreshEntity(alluricorn);

		return alluricorn;
	}
}
