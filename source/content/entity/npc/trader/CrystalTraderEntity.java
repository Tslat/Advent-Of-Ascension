package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CrystalTraderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN).cost(AoAItems.BLUE_CRYSTAL).stock(9999),
					BuildableTrade.trade(AoAItems.COPPER_COIN).cost(AoAItems.GREEN_CRYSTAL).stock(9999),
					BuildableTrade.trade(AoAItems.COPPER_COIN).cost(AoAItems.PURPLE_CRYSTAL).stock(9999),
					BuildableTrade.trade(AoAItems.COPPER_COIN).cost(AoAItems.RED_CRYSTAL).stock(9999),
					BuildableTrade.trade(AoAItems.COPPER_COIN).cost(AoAItems.WHITE_CRYSTAL).stock(9999),
					BuildableTrade.trade(AoAItems.COPPER_COIN).cost(AoAItems.YELLOW_CRYSTAL).stock(9999))
			.trades(2,
					BuildableTrade.trade(AoAItems.BLUE_DRUSE).cost(AoAItems.BLUE_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.trade(AoAItems.GREEN_DRUSE).cost(AoAItems.GREEN_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.trade(AoAItems.PURPLE_DRUSE).cost(AoAItems.PURPLE_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.trade(AoAItems.RED_DRUSE).cost(AoAItems.RED_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.trade(AoAItems.WHITE_DRUSE).cost(AoAItems.WHITE_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.trade(AoAItems.YELLOW_DRUSE).cost(AoAItems.YELLOW_CRYSTAL, 16).xp(15).stock(9999))
			.trades(3,
					BuildableTrade.trade(AoAWeapons.CRYSTAL_MAUL).cost(AoAItems.RAINBOW_DRUSE, 12).xp(50).stock(5),
					BuildableTrade.trade(AoAWeapons.CRYSTAL_GREATBLADE).cost(AoAItems.RAINBOW_DRUSE, 12).xp(50).stock(5)).build();

	public CrystalTraderEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.CRYSTEVIA.key);
	}

	@Override
	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return newProfessionLevel < 3 ? 6 : 2;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
