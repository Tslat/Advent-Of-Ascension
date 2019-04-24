package net.tslat.aoa3.entity.base.ai.npc;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.entity.base.AoATrader;

public class EntityAITradeWithPlayer extends EntityAIBase {
	private final AoATrader trader;

	public EntityAITradeWithPlayer(AoATrader trader) {
		this.trader = trader;
		this.setMutexBits(5);
	}

	@Override
	public boolean shouldExecute() {
		if (!trader.isEntityAlive())
			return false;

		if (trader.isInWater() || trader.isInLava())
			return false;

		if (!trader.onGround)
			return false;

		if (trader.velocityChanged)
			return false;

		EntityPlayer customer = trader.getCustomer();

		if (customer == null || trader.getDistance(customer) > 16)
			return false;

		return customer.openContainer != null;
	}

	@Override
	public void startExecuting() {
		trader.getNavigator().clearPath();
	}

	@Override
	public void resetTask() {
		trader.setCustomer(null);
	}
}
