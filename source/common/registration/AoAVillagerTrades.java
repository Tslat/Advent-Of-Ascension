package net.tslat.aoa3.common.registration;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAProfessions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoATrader;

import java.util.List;

public final class AoAVillagerTrades {
	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, VillagerTradesEvent.class, AoAVillagerTrades::onTraderGenTrades);
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
		tradesMap.get(1).add(AoATrader.BuildableTrade.trade(AoABlocks.ANCIENT_ROCK, 3).cost(AoAItems.COPPER_COIN).xp(1).stock(16));
		tradesMap.get(2).addAll(List.of(
				AoATrader.BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_TRAVEL).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(4),
				AoATrader.BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_SPACE).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(4),
				AoATrader.BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_REALITY).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(4),
				AoATrader.BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_DIRECTION).cost(AoAItems.COPPER_COIN, 20).xp(20).stock(4)));
		tradesMap.get(4).add(AoATrader.BuildableTrade.trade(AoAItems.RUNIUM_CHUNK).cost(AoAItems.COPPER_COIN, 5).xp(9).stock(8));
		tradesMap.get(5).add(AoATrader.BuildableTrade.trade(AoABlocks.CARVED_RUNE_OF_POWER).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(1));
	}

	private static void doAssassinTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> tradesMap) {
		tradesMap.get(1).addAll(List.of(
				AoATrader.BuildableTrade.trade(AoAWeapons.GOO_BALL, 3).cost(AoAItems.COPPER_COIN, 2),
				AoATrader.BuildableTrade.trade(AoAWeapons.SLICE_STAR, 2).cost(AoAItems.COPPER_COIN, 2)
		));
		tradesMap.get(2).addAll(List.of(
				AoATrader.BuildableTrade.trade(AoAWeapons.CHAKRAM, 2).cost(AoAItems.COPPER_COIN, 3).xp(3),
				AoATrader.BuildableTrade.trade(AoAWeapons.VULKRAM, 2).cost(AoAItems.COPPER_COIN, 3).xp(3)
		));
		tradesMap.get(3).addAll(List.of(
				AoATrader.BuildableTrade.trade(AoAItems.METAL_SLUG, 2).cost(AoAItems.COPPER_COIN, 2),
				AoATrader.BuildableTrade.trade(AoAItems.LIMONITE_BULLET, 5).cost(AoAItems.COPPER_COIN, 2)
		));
		tradesMap.get(4).add(AoATrader.BuildableTrade.trade(AoAWeapons.HELLFIRE).cost(AoAItems.COPPER_COIN, 2));
	}
}
