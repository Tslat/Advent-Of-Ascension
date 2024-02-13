package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;
import org.jetbrains.annotations.Nullable;


public class NaturalistEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 18).itemCost(Blocks.MELON, 8).xp(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 15).itemCost(Blocks.PUMPKIN, 8).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 10).itemCost(Items.BEETROOT, 8).xp(6),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 8).itemCost(Items.CARROT, 8).xp(4),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 2).itemCost(Items.MELON_SLICE, 8),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(Items.POTATO, 8).xp(7),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 9).itemCost(Items.WHEAT, 8).xp(6))
			.trades(2,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.BUBBLE_BERRIES, 8).xp(9),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 10).itemCost(AoAItems.CHILLI, 8).xp(7),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 11).itemCost(AoAItems.EYE_BULB, 8).xp(7),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 16).itemCost(AoAItems.FLORACLE_STICKS, 8).xp(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 14).itemCost(AoAItems.GOLDICAP_PETALS, 8).xp(10),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.LUNACRIKE, 8).xp(9),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.LUNA_GLOBE, 8).xp(9),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 12).itemCost(AoAItems.LUNALONS, 8).xp(9),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 15).itemCost(AoAItems.MYSTIC_SHROOMS, 8).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 16).itemCost(AoAItems.ROSIDONS, 8).xp(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 14).itemCost(AoAItems.TEA_SHREDDINGS, 8).xp(10),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 13).itemCost(AoAItems.TRILLIAD_LEAVES, 8).xp(10))
			.trades(3,
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 19).itemCost(AoAItems.HEART_FRUIT, 8).xp(14),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 13).itemCost(AoAItems.MAGIC_MARANG, 8).xp(11),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 15).itemCost(Items.CHORUS_FRUIT, 8).xp(13)).build();

	public NaturalistEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
