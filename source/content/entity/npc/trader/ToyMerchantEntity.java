package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ToyMerchantEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.BALLOON, 10).cost(AoAItems.CIRCUS_COIN, 2).xp(5).stock(32))
			.trades(3,
					BuildableTrade.trade(AoAItems.GRAVITATOR).cost(AoAItems.CIRCUS_COIN, 30).xp(40).stock(5),
					BuildableTrade.trade(AoAItems.TOY_GYROCOPTER).cost(AoAItems.CIRCUS_COIN, 10).xp(25))
			.trades(4,
					BuildableTrade.trade(AoAWeapons.CONFETTI_CLUSTER).cost(AoAItems.CIRCUS_COIN, 12).xp(40).stock(5),
					BuildableTrade.trade(AoAWeapons.BALLOON_BOMBER).cost(AoAItems.CIRCUS_COIN, 18).xp(40).stock(5),
					BuildableTrade.trade(AoAItems.SMILEY_UPGRADE_KIT).cost(AoAItems.CIRCUS_COIN, 64).xp(100).stock(1)).build();

	public ToyMerchantEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.CELEVE.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
