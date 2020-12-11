package net.tslat.aoa3.entity.ai.trader;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.tslat.aoa3.entity.base.AoATrader;

import java.util.EnumSet;

public class TraderPlayerTradeGoal extends Goal {
	private final AoATrader trader;

	public TraderPlayerTradeGoal(AoATrader trader) {
		this.trader = trader;

		setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	public boolean shouldExecute() {
		if (!trader.isAlive() || trader.isInWater() || !trader.onGround || trader.velocityChanged)
			return false;

		PlayerEntity customer = trader.getCustomer();

		if (customer == null || trader.getDistanceSq(customer) > 16.0D)
			return false;

		return customer.openContainer != null;
	}

	public void startExecuting() {
		trader.getNavigator().clearPath();
	}

	public void resetTask() {
		trader.setCustomer(null);
	}
}
