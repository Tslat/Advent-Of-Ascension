package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.CorbyEntity;

public class CorbySlab extends BaseSlab {
	public CorbySlab() {
		super(63, 120, 63, 3000);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		CorbyEntity corby = new CorbyEntity(AoAEntities.Minions.CORBY.get(), pl.level);

		corby.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		corby.tame(pl);
		pl.level.addFreshEntity(corby);

		return corby;
	}
}
