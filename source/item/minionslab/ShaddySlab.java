package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.ShaddyEntity;

public class ShaddySlab extends BaseSlab {
	public ShaddySlab() {
		super(60, 160, 60, 850);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		ShaddyEntity shaddy = new ShaddyEntity(AoAEntities.Minions.SHADDY.get(), pl.world);

		shaddy.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		shaddy.setTamedBy(pl);
		pl.world.addEntity(shaddy);

		return shaddy;
	}
}
