package net.tslat.aoa3.entity.ai.trader;

import net.minecraft.entity.ai.goal.Goal;
import net.tslat.aoa3.entity.base.AoATrader;

import java.util.EnumSet;

public class TraderRestockGoal extends Goal {
	private final AoATrader trader;

	public TraderRestockGoal(AoATrader trader) {
		this.trader = trader;

		setFlags(EnumSet.of(Flag.MOVE));
	}

	public boolean canUse() {
		return canContinueToUse();
	}

	@Override
	public boolean canContinueToUse() {
		return trader.isAlive() && trader.isTrading() && trader.canRestock() && trader.shouldRestock();
	}

	@Override
	public void start() {
		if (!this.trader.level.hasNearbyAlivePlayer(this.trader.position().x(), this.trader.position().y(), this.trader.position().z(), 24))
			trader.restock();

		stop();
	}
}
