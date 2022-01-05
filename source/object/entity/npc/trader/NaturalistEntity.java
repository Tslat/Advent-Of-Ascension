package net.tslat.aoa3.object.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.entity.base.AoATrader;

import javax.annotation.Nullable;

public class NaturalistEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 18).cost(Blocks.MELON, 8).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(Blocks.PUMPKIN, 8).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(Items.BEETROOT, 8).xp(6),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(Items.CARROT, 8).xp(4),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 2).cost(Items.MELON_SLICE, 8),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(Items.POTATO, 8).xp(7),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 9).cost(Items.WHEAT, 8).xp(6))
			.trades(2,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.BUBBLE_BERRIES, 8).xp(9),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(AoAItems.CHILLI, 8).xp(7),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 11).cost(AoAItems.EYE_BULB, 8).xp(7),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 16).cost(AoAItems.FLORACLE_STICKS, 8).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 14).cost(AoAItems.GOLDICAP_PETALS, 8).xp(10),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.LUNACRIKE, 8).xp(9),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.LUNA_GLOBE, 8).xp(9),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 12).cost(AoAItems.LUNALONS, 8).xp(9),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(AoAItems.MYSTIC_SHROOMS, 8).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 16).cost(AoAItems.ROSIDONS, 8).xp(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 14).cost(AoAItems.TEA_SHREDDINGS, 8).xp(10),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 13).cost(AoAItems.TRILLIAD_LEAVES, 8).xp(10))
			.trades(3,
					BuildableTrade.trade(AoAItems.COPPER_COIN, 19).cost(AoAItems.HEART_FRUIT, 8).xp(14),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 13).cost(AoAItems.MAGIC_MARANG, 8).xp(11),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 15).cost(Items.CHORUS_FRUIT, 8).xp(13)).build();

	public NaturalistEntity(EntityType<? extends AoATrader> entityType, World world) {
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
