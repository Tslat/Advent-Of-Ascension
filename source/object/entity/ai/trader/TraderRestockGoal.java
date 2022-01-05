package net.tslat.aoa3.object.entity.ai.trader;

import net.minecraft.entity.ai.goal.Goal;
import net.tslat.aoa3.object.entity.base.AoATrader;

import java.util.EnumSet;

public class TraderRestockGoal extends Goal {
	private final AoATrader trader;

	public TraderRestockGoal(AoATrader trader) {
		this.trader = trader;

		setFlags(EnumSet.of(Flag.MOVE));
	}

	public boolean canUse() {
		return this.trader.isAlive() && !this.trader.isTrading() && this.trader.canRestock() && this.trader.allowedToRestock() && !this.trader.level.hasNearbyAlivePlayer(this.trader.position().x(), this.trader.position().y(), this.trader.position().z(), 24);
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
