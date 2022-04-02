package net.tslat.aoa3.content.entity.ai.trader;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.content.entity.base.AoATrader;

public class TraderFaceCustomerGoal extends LookAtPlayerGoal {
	private final AoATrader trader;

	public TraderFaceCustomerGoal(AoATrader trader) {
		super(trader, Player.class, 8.0F);

		this.trader = trader;
	}

	public boolean canUse() {
		if (!trader.isTrading())
			return false;

		lookAt = trader.getTradingPlayer();

		return true;
	}
}
