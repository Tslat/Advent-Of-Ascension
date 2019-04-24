package net.tslat.aoa3.entity.base.ai.npc;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.entity.base.AoATrader;

public class EntityAIFaceCustomer extends EntityAIWatchClosest {
	private final AoATrader trader;

	public EntityAIFaceCustomer(AoATrader trader) {
		super(trader, EntityPlayer.class, 8.0f);
		this.trader = trader;
	}

	@Override
	public boolean shouldExecute() {
		if (trader.isTrading()) {
			this.closestEntity = trader.getCustomer();

			return true;
		}

		return false;
	}
}
