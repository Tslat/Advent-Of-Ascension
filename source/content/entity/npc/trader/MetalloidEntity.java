package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;
import org.jetbrains.annotations.Nullable;


public class MetalloidEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(Items.IRON_INGOT).itemCost(AoAItems.COPPER_COIN, 17).xp(10),
					BuildableTrade.forItem(AoAItems.LIMONITE_INGOT).itemCost(AoAItems.COPPER_COIN, 15).xp(8),
					BuildableTrade.forItem(AoAItems.JADE).itemCost(AoAItems.COPPER_COIN, 35).xp(18),
					BuildableTrade.forItem(Items.EMERALD).itemCost(AoAItems.COPPER_COIN, 45).xp(23),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 5).itemCost(Items.IRON_INGOT).xp(5),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 9).itemCost(AoAItems.LIMONITE_INGOT).xp(4),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 10).itemCost(AoAItems.JADE).xp(9),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 10).itemCost(Items.EMERALD).xp(10))
			.trades(2,
					BuildableTrade.forItem(Items.GOLD_INGOT).itemCost(AoAItems.COPPER_COIN, 25).xp(15),
					BuildableTrade.forItem(Items.DIAMOND).itemCost(AoAItems.SILVER_COIN, 2).xp(20),
					BuildableTrade.forItem(Items.NETHERITE_INGOT).itemCost(AoAItems.COPPER_COIN, 60).xp(26),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(Items.NETHERITE_INGOT).xp(13),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(Items.DIAMOND).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 8).itemCost(Items.GOLD_INGOT).xp(8))
			.trades(3,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.BARONYTE_INGOT).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.BLAZIUM_INGOT).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.VARSIUM_INGOT).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.EMBERSTONE_INGOT).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.SKELETAL_INGOT).xp(11))
			.trades(4,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 16).itemCost(AoAItems.GHASTLY_INGOT).xp(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 16).itemCost(AoAItems.GHOULISH_INGOT).xp(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 15).itemCost(AoAItems.LYON_INGOT).xp(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 15).itemCost(AoAItems.CRYSTALLITE).xp(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 15).itemCost(AoAItems.MYSTITE_INGOT).xp(12))
			.trades(5,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 16).itemCost(AoAItems.ELECANIUM_INGOT).xp(15),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 16).itemCost(AoAItems.LUNAR_INGOT).xp(15),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 18).itemCost(AoAItems.SHYRESTONE_INGOT).xp(15),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 18).itemCost(AoAItems.SHYREGEM).xp(15)).build();

	public MetalloidEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
