package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class LelyetianTraderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 4).cost(AoAItems.ZHINX_DUST).xp(3),
					BuildableTrade.trade(AoABlocks.LELYETIAN_GLASS, 14).cost(AoAItems.COPPER_COIN, 10).xp(7),
					BuildableTrade.trade(AoABlocks.LELYETIAN_GLASS, 64).cost(AoAItems.SILVER_COIN, 2).xp(35))
			.trades(4,
					BuildableTrade.trade(AoAWeapons.GAUGE_RIFLE).cost(AoAItems.SILVER_COIN, 10).cost(AoAItems.YELLOW_SPORES, 5).xp(40).stock(5),
					BuildableTrade.trade(AoAWeapons.GAUGE_RIFLE).cost(AoAItems.SILVER_COIN, 10).cost(AoAItems.ORANGE_SPORES, 5).xp(40).stock(5)).build();

	public LelyetianTraderEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LELYETIA.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
