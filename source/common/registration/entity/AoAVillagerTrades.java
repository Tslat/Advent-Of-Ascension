package net.tslat.aoa3.common.registration.entity;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoATrader;

import java.util.List;

public final class AoAVillagerTrades {
	public static void init() {
		NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, VillagerTradesEvent.class, AoAVillagerTrades::onTraderGenTrades);
	}

	private static void onTraderGenTrades(final VillagerTradesEvent ev) {
		if (ev.getType() == VillagerProfession.CARTOGRAPHER) {
			doCartographerTrades(ev.getTrades());
		}
		else if (ev.getType() == VillagerProfession.CLERIC) {
			doClericTraces(ev.getTrades());
		}
		else if (ev.getType() == AoAProfessions.ASSASSIN.get()) {
			doAssassinTrades(ev.getTrades());
		}
	}

	private static void doCartographerTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> tradesMap) {
		tradesMap.get(1).add(
				new VillagerTrades.TreasureMapForEmeralds(
						4,
						AoATags.Structures.ON_RUINED_TELEPORTER_FRAME_MAPS,
						"filled_map." + AdventOfAscension.MOD_ID + ".ruined_teleporter_frame",
						MapDecoration.Type.TARGET_POINT,
						3,
						7));
	}

	private static void doClericTraces(Int2ObjectMap<List<VillagerTrades.ItemListing>> tradesMap) {
		tradesMap.get(1).add(AoATrader.BuildableTrade.forItem(AoABlocks.ANCIENT_ROCK, 3).itemCost(AoAItems.COPPER_COIN).xp(1).stock(16));
		tradesMap.get(2).add(AoATrader.BuildableTrade.forItem(AoAItems.RUNIUM_CHUNK).itemCost(AoAItems.COPPER_COIN, 5).xp(9).stock(8));
		tradesMap.get(3).addAll(List.of(
				AoATrader.BuildableTrade.forItem(AoABlocks.CARVED_RUNE_OF_TRAVEL).itemCost(AoAItems.COPPER_COIN, 20).xp(20).stock(4),
				AoATrader.BuildableTrade.forItem(AoABlocks.CARVED_RUNE_OF_SPACE).itemCost(AoAItems.COPPER_COIN, 20).xp(20).stock(4),
				AoATrader.BuildableTrade.forItem(AoABlocks.CARVED_RUNE_OF_REALITY).itemCost(AoAItems.COPPER_COIN, 20).xp(20).stock(4),
				AoATrader.BuildableTrade.forItem(AoABlocks.CARVED_RUNE_OF_DIRECTION).itemCost(AoAItems.COPPER_COIN, 20).xp(20).stock(4)));
		tradesMap.get(5).add(AoATrader.BuildableTrade.forItem(AoABlocks.CARVED_RUNE_OF_POWER).itemCost(AoAItems.SILVER_COIN, 2).xp(50).stock(1));
	}

	private static void doAssassinTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> tradesMap) {
		tradesMap.get(1).addAll(List.of(
				AoATrader.BuildableTrade.forItem(AoAWeapons.GOO_BALL, 3).itemCost(AoAItems.COPPER_COIN, 2),
				AoATrader.BuildableTrade.forItem(AoAWeapons.SLICE_STAR, 2).itemCost(AoAItems.COPPER_COIN, 2)
		));
		tradesMap.get(2).addAll(List.of(
				AoATrader.BuildableTrade.forItem(AoAWeapons.CHAKRAM, 2).itemCost(AoAItems.COPPER_COIN, 3).xp(3),
				AoATrader.BuildableTrade.forItem(AoAWeapons.VULKRAM, 2).itemCost(AoAItems.COPPER_COIN, 3).xp(3)
		));
		tradesMap.get(3).addAll(List.of(
				AoATrader.BuildableTrade.forItem(AoAItems.METAL_SLUG, 2).itemCost(AoAItems.COPPER_COIN, 2),
				AoATrader.BuildableTrade.forItem(AoAItems.LIMONITE_BULLET, 5).itemCost(AoAItems.COPPER_COIN, 2)
		));
		tradesMap.get(4).add(AoATrader.BuildableTrade.forItem(AoAWeapons.HELLFIRE).itemCost(AoAItems.COPPER_COIN, 2));
	}
}
