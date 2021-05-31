package net.tslat.aoa3.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ToyMerchantEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.BALLOON, 10).cost(AoAItems.CIRCUS_COIN, 2).xp(5).stock(32))
			.trades(3,
					BuildableTrade.trade(AoAItems.GRAVITATOR).cost(AoAItems.CIRCUS_COIN, 30).xp(40).stock(5),
					BuildableTrade.trade(AoAItems.TOY_GYROCOPTER).cost(AoAItems.CIRCUS_COIN, 10).xp(25))
			.trades(4,
					BuildableTrade.trade(AoAWeapons.CONFETTI_CLUSTER).cost(AoAItems.CIRCUS_COIN, 12).xp(40).stock(5),
					BuildableTrade.trade(AoAWeapons.BALLOON_BOMBER).cost(AoAItems.CIRCUS_COIN, 18).xp(40).stock(5)).build();

	public ToyMerchantEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.CELEVE.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
