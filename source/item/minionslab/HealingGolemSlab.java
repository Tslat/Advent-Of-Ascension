package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.HealingGolemEntity;

public class HealingGolemSlab extends BaseSlab {
	public HealingGolemSlab() {
		super(90, 200, 90, 4000);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		HealingGolemEntity healingGolem = new HealingGolemEntity(AoAEntities.Minions.HEALING_GOLEM.get(), pl.world);

		healingGolem.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		healingGolem.setTamedBy(pl);
		pl.world.addEntity(healingGolem);

		return healingGolem;
	}
}
