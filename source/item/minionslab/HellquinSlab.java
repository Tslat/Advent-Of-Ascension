package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.HellquinEntity;

public class HellquinSlab extends BaseSlab {
	public HellquinSlab() {
		super(45, 140, 45, 500);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		HellquinEntity hellquin = new HellquinEntity(AoAEntities.Minions.HELLQUIN.get(), pl.level);

		hellquin.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		hellquin.tame(pl);
		pl.level.addFreshEntity(hellquin);

		return hellquin;
	}
}