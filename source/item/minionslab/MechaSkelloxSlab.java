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
		MechaSkelloxEntity mechaSkellox = new MechaSkelloxEntity(AoAEntities.Minions.MECHA_SKELLOX.get(), pl.level);

		mechaSkellox.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		mechaSkellox.tame(pl);
		pl.level.addFreshEntity(mechaSkellox);

		return mechaSkellox;
	}
}
