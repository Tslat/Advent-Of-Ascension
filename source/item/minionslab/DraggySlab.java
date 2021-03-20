package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.DraggyEntity;

public class DraggySlab extends BaseSlab {
	public DraggySlab() {
		super(1, 100, 1, 20);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		DraggyEntity draggy = new DraggyEntity(AoAEntities.Minions.DRAGGY.get(), pl.level);

		draggy.teleportTo(pl.getX(), pl.getY(), pl.getZ());
		draggy.tame(pl);
		pl.level.addFreshEntity(draggy);

		return draggy;
	}
}
