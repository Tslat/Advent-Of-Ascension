package net.tslat.aoa3.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;

import javax.annotation.Nullable;

public class MetalloidEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(Items.IRON_INGOT).cost(AoAItems.COPPER_COIN, 17).xp(10),
					BuildableTrade.trade(Items.GOLD_INGOT).cost(AoAItems.COPPER_COIN, 25).xp(15),
					BuildableTrade.trade(Items.DIAMOND).cost(AoAItems.SILVER_COIN, 2).xp(20),
					BuildableTrade.trade(Items.EMERALD).cost(AoAItems.COPPER_COIN, 45).xp(23),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 3).cost(Items.IRON_INGOT).xp(5),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 5).cost(Items.GOLD_INGOT).xp(8),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(Items.DIAMOND).xp(11))
			.trades(2,
					BuildableTrade.trade(AoAItems.LIMONITE_INGOT).cost(AoAItems.COPPER_COIN, 15).xp(8),
					BuildableTrade.trade(AoAItems.ROSITE_INGOT).cost(AoAItems.COPPER_COIN, 20).xp(11),
					BuildableTrade.trade(AoAItems.JADE).cost(AoAItems.COPPER_COIN, 30).xp(17),
					BuildableTrade.trade(AoAItems.AMETHYST).cost(AoAItems.COPPER_COIN, 40).xp(19),
					BuildableTrade.trade(AoAItems.SAPPHIRE).cost(AoAItems.COPPER_COIN, 60).xp(26),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 6).cost(AoAItems.LIMONITE_INGOT).xp(4),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 7).cost(AoAItems.ROSITE_INGOT).xp(7),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 7).cost(AoAItems.JADE).xp(9),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 7).cost(AoAItems.AMETHYST).xp(10),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.SAPPHIRE).xp(13))
			.trades(3,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.BARONYTE_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.BLAZIUM_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.VARSIUM_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.EMBERSTONE_INGOT).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.SKELETAL_INGOT).xp(11))
			.trades(4,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 11).cost(AoAItems.GHASTLY_INGOT).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 11).cost(AoAItems.GHOULISH_INGOT).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(AoAItems.LYON_INGOT).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(AoAItems.CRYSTALLITE).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(AoAItems.MYSTITE_INGOT).xp(12))
			.trades(5,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 11).cost(AoAItems.ELECANIUM_INGOT).xp(15),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 11).cost(AoAItems.LUNAR_INGOT).xp(15),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.SHYRESTONE_INGOT).xp(15),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.SHYREGEM).xp(15)).build();

	public MetalloidEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
