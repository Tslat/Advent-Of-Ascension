package net.tslat.aoa3.entity.ai.trader;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.tslat.aoa3.entity.base.AoATrader;

public class TraderFaceCustomerGoal extends LookAtGoal {
	private final AoATrader trader;

	public TraderFaceCustomerGoal(AoATrader trader) {
		super(trader, PlayerEntity.class, 8.0F);

		this.trader = trader;
	}

	public boolean shouldExecute() {
		if (!trader.isTrading())
			return false;

		closestEntity = trader.getCustomer();

		return true;
	}
}
