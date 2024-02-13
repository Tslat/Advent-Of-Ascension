package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class LelyetianTraderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 4).itemCost(AoAItems.ZHINX_DUST).xp(3),
					BuildableTrade.forItem(AoABlocks.LELYETIAN_GLASS, 14).itemCost(AoAItems.COPPER_COIN, 10).xp(7),
					BuildableTrade.forItem(AoABlocks.LELYETIAN_GLASS, 64).itemCost(AoAItems.SILVER_COIN, 2).xp(35))
			.trades(4,
					BuildableTrade.forItem(AoAWeapons.GAUGE_RIFLE).itemCost(AoAItems.SILVER_COIN, 10).itemCost(AoAItems.YELLOW_SPORES, 5).xp(40).stock(5),
					BuildableTrade.forItem(AoAWeapons.GAUGE_RIFLE).itemCost(AoAItems.SILVER_COIN, 10).itemCost(AoAItems.ORANGE_SPORES, 5).xp(40).stock(5)).build();

	public LelyetianTraderEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.LELYETIA);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
