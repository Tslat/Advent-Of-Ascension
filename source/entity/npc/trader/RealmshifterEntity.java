package net.tslat.aoa3.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;

import javax.annotation.Nullable;

public class RealmshifterEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoABlocks.ANCIENT_ROCK, 3).cost(AoAItems.COPPER_COIN).xp(1).stock(32),
					BuildableTrade.trade(AoABlocks.ANCIENT_ROCK, 30).cost(AoAItems.COPPER_COIN, 10).xp(10),
					BuildableTrade.trade(AoABlocks.ANCIENT_ROCK, 64).cost(AoAItems.SILVER_COIN, 1).xp(15))
			.trades(2,
					BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_TRAVEL).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(12),
					BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_SPACE).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(12),
					BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_REALITY).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(12),
					BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_DIRECTION).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(12)).build();

	public RealmshifterEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
