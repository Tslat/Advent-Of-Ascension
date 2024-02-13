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
import org.jetbrains.annotations.Nullable;


public class CrystalTraderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.COPPER_COIN).itemCost(AoAItems.BLUE_CRYSTAL).stock(9999),
					BuildableTrade.forItem(AoAItems.COPPER_COIN).itemCost(AoAItems.GREEN_CRYSTAL).stock(9999),
					BuildableTrade.forItem(AoAItems.COPPER_COIN).itemCost(AoAItems.PURPLE_CRYSTAL).stock(9999),
					BuildableTrade.forItem(AoAItems.COPPER_COIN).itemCost(AoAItems.RED_CRYSTAL).stock(9999),
					BuildableTrade.forItem(AoAItems.COPPER_COIN).itemCost(AoAItems.WHITE_CRYSTAL).stock(9999),
					BuildableTrade.forItem(AoAItems.COPPER_COIN).itemCost(AoAItems.YELLOW_CRYSTAL).stock(9999))
			.trades(2,
					BuildableTrade.forItem(AoAItems.BLUE_DRUSE).itemCost(AoAItems.BLUE_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.forItem(AoAItems.GREEN_DRUSE).itemCost(AoAItems.GREEN_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.forItem(AoAItems.PURPLE_DRUSE).itemCost(AoAItems.PURPLE_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.forItem(AoAItems.RED_DRUSE).itemCost(AoAItems.RED_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.forItem(AoAItems.WHITE_DRUSE).itemCost(AoAItems.WHITE_CRYSTAL, 16).xp(15).stock(9999),
					BuildableTrade.forItem(AoAItems.YELLOW_DRUSE).itemCost(AoAItems.YELLOW_CRYSTAL, 16).xp(15).stock(9999))
			.trades(3,
					BuildableTrade.forItem(AoAWeapons.CRYSTAL_MAUL).itemCost(AoAItems.RAINBOW_DRUSE, 12).xp(50).stock(5),
					BuildableTrade.forItem(AoAWeapons.CRYSTAL_GREATBLADE).itemCost(AoAItems.RAINBOW_DRUSE, 12).xp(50).stock(5)).build();

	public CrystalTraderEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.CRYSTEVIA);
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
