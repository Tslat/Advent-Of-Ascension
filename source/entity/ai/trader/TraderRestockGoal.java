package net.tslat.aoa3.entity.ai.trader;

import net.minecraft.entity.ai.goal.Goal;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.mixin.common.invoker.AccessibleVillagerEntity;

import java.util.EnumSet;

public class TraderRestockGoal extends Goal {
	private final AoATrader trader;

	public TraderRestockGoal(AoATrader trader) {
		this.trader = trader;

		setFlags(EnumSet.of(Flag.MOVE));
	}

	public boolean canUse() {
		return this.trader.isAlive() && !this.trader.isTrading() && this.trader.canRestock() && ((AccessibleVillagerEntity)this.trader).hasSpareRestocks() && !this.trader.level.hasNearbyAlivePlayer(this.trader.position().x(), this.trader.position().y(), this.trader.position().z(), 24);
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
