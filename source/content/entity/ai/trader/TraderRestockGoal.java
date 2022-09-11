package net.tslat.aoa3.content.entity.ai.trader;

import net.minecraft.entity.ai.goal.Goal;
import net.tslat.aoa3.content.entity.base.AoATrader;

public class TraderRestockGoal extends Goal {
	private final AoATrader trader;

	public TraderRestockGoal(AoATrader trader) {
		this.trader = trader;
	}

	public boolean canUse() {
		return this.trader.isAlive() && !this.trader.isTrading() && this.trader.canRestock() && this.trader.allowedToRestock();
	}

	@Override
	public boolean canContinueToUse() {
		return false;
	}

	@Override
	public void start() {
		if (trader.shouldRestock())
			trader.restock();

		stop();
	}
}
