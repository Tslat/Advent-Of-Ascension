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
					BuildableTrade.trade(Items.IRON_INGOT).cost(AoAItems.COPPER_COIN, 17).xp(10),
					BuildableTrade.trade(AoAItems.LIMONITE_INGOT).cost(AoAItems.COPPER_COIN, 15).xp(8),
					BuildableTrade.trade(AoAItems.JADE).cost(AoAItems.COPPER_COIN, 35).xp(18),
					BuildableTrade.trade(Items.EMERALD).cost(AoAItems.COPPER_COIN, 45).xp(23),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 5).cost(Items.IRON_INGOT).xp(5),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 9).cost(AoAItems.LIMONITE_INGOT).xp(4),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(AoAItems.JADE).xp(9),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(Items.EMERALD).xp(10))
			.trades(2,
					BuildableTrade.trade(Items.GOLD_INGOT).cost(AoAItems.COPPER_COIN, 25).xp(15),
					BuildableTrade.trade(Items.DIAMOND).cost(AoAItems.SILVER_COIN, 2).xp(20),
					BuildableTrade.trade(Items.NETHERITE_INGOT).cost(AoAItems.COPPER_COIN, 60).xp(26),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(Items.NETHERITE_INGOT).xp(13),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(Items.DIAMOND).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(Items.GOLD_INGOT).xp(8))
			.trades(3,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.BARONYTE_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.BLAZIUM_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.VARSIUM_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.EMBERSTONE_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.SKELETAL_INGOT).xp(11))
			.trades(4,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 16).cost(AoAItems.GHASTLY_INGOT).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 16).cost(AoAItems.GHOULISH_INGOT).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(AoAItems.LYON_INGOT).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(AoAItems.CRYSTALLITE).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(AoAItems.MYSTITE_INGOT).xp(12))
			.trades(5,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 16).cost(AoAItems.ELECANIUM_INGOT).xp(15),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 16).cost(AoAItems.LUNAR_INGOT).xp(15),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 18).cost(AoAItems.SHYRESTONE_INGOT).xp(15),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 18).cost(AoAItems.SHYREGEM).xp(15)).build();

	public MetalloidEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
