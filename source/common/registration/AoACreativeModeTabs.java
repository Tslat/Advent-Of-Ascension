package net.tslat.aoa3.common.registration;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.AoAStartupCache;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;
import java.util.function.Supplier;

public final class AoACreativeModeTabs {
	public static void init() {
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, CreativeModeTabEvent.Register.class, AoACreativeModeTabs::registerTabs);
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, CreativeModeTabEvent.BuildContents.class, AoACreativeModeTabs::fillVanillaTabs);
	}

	public static CreativeModeTab MISC_ITEMS;
	public static CreativeModeTab FOOD;
	public static CreativeModeTab TOOLS;
	public static CreativeModeTab ARMOUR;
	public static CreativeModeTab SWORDS;
	public static CreativeModeTab GREATBLADES;
	public static CreativeModeTab MAULS;
	public static CreativeModeTab GUNS;
	public static CreativeModeTab CANNONS;
	public static CreativeModeTab SHOTGUNS;
	public static CreativeModeTab SNIPERS;
	public static CreativeModeTab BLASTERS;
	public static CreativeModeTab CROSSBOWS;
	public static CreativeModeTab BOWS;
	public static CreativeModeTab THROWN_WEAPONS;
	public static CreativeModeTab STAVES;
	public static CreativeModeTab VULCANES;
	public static CreativeModeTab AMMUNITION;
	public static CreativeModeTab TABLETS;
	public static CreativeModeTab GENERATION_BLOCKS;
	public static CreativeModeTab DECORATION_BLOCKS;
	public static CreativeModeTab FUNCTIONAL_BLOCKS;
	public static CreativeModeTab BANNERS;

	private static void registerTabs(final CreativeModeTabEvent.Register ev) {
		MISC_ITEMS = register(ev, "miscellaneous", () -> new ItemStack(AoAItems.LIMONITE_INGOT.get()), () -> MISC_ITEMS, CreativeModeTabs.SPAWN_EGGS);
		FOOD = register(ev, "food", () -> new ItemStack(AoAItems.COOKED_RAINBOWFISH.get()), () -> FOOD, MISC_ITEMS);
		TOOLS = register(ev, "tools", () -> new ItemStack(AoATools.LIMONITE_PICKAXE.get()), () -> TOOLS, FOOD);
		ARMOUR = register(ev, "armour", () -> new ItemStack(AoAArmour.ALACRITY_ARMOUR.chestplate.get()), () -> ARMOUR, TOOLS);
		SWORDS = register(ev, "swords", () -> new ItemStack(AoAWeapons.LIMONITE_SWORD.get()), () -> SWORDS, ARMOUR);
		GREATBLADES = register(ev, "greatblades", () -> new ItemStack(AoAWeapons.BLOODLURKER.get()), () -> GREATBLADES, SWORDS);
		MAULS = register(ev, "mauls", () -> new ItemStack(AoAWeapons.HORIZON_MAUL.get()), () -> MAULS, GREATBLADES);
		GUNS = register(ev, "guns", () -> new ItemStack(AoAWeapons.SQUAD_GUN.get()), () -> GUNS, MAULS);
		CANNONS = register(ev, "cannons", () -> new ItemStack(AoAWeapons.BLAST_CANNON.get()), () -> CANNONS, GUNS);
		SHOTGUNS = register(ev, "shotguns", () -> new ItemStack(AoAWeapons.BLAST_BARREL.get()), () -> SHOTGUNS, CANNONS);
		SNIPERS = register(ev, "snipers", () -> new ItemStack(AoAWeapons.BOLT_RIFLE.get()), () -> SNIPERS, SHOTGUNS);
		BLASTERS = register(ev, "blasters", () -> new ItemStack(AoAWeapons.LASER_BLASTER.get()), () -> BLASTERS, SNIPERS);
		CROSSBOWS = register(ev, "crossbows", () -> new ItemStack(AoAWeapons.LUNAR_CROSSBOW.get()), () -> CROSSBOWS, BLASTERS);
		BOWS = register(ev, "bows", () -> new ItemStack(AoAWeapons.SPEED_BOW.get()), () -> BOWS, CROSSBOWS);
		THROWN_WEAPONS = register(ev, "thrown_weapons", () -> new ItemStack(AoAWeapons.GRENADE.get()), () -> THROWN_WEAPONS, BOWS);
		STAVES = register(ev, "staves", () -> new ItemStack(AoAWeapons.CELESTIAL_STAFF.get()), () -> STAVES, THROWN_WEAPONS);
		VULCANES = register(ev, "vulcanes", () -> new ItemStack(AoAWeapons.VULCANE.get()), () -> VULCANES, STAVES);
		AMMUNITION = register(ev, "ammunition", () -> new ItemStack(AoAItems.LIMONITE_BULLET.get()), () -> AMMUNITION, VULCANES);
		TABLETS = register(ev, "tablets", () -> new ItemStack(AoAItems.AGILITY_TABLET.get()), () -> TABLETS, AMMUNITION);
		GENERATION_BLOCKS = register(ev, "worldgen_blocks", () -> new ItemStack(AoABlocks.LELYETIAN_GRASS.get()), () -> GENERATION_BLOCKS, TABLETS);
		DECORATION_BLOCKS = register(ev, "decoration_blocks", () -> new ItemStack(AoABlocks.BARON_STONE_BRICKS.get()), () -> DECORATION_BLOCKS, GENERATION_BLOCKS);
		FUNCTIONAL_BLOCKS = register(ev, "functional_blocks", () -> new ItemStack(AoABlocks.SHADOW_ALTAR.get()), () -> FUNCTIONAL_BLOCKS, DECORATION_BLOCKS);
		BANNERS = register(ev, "banners", () -> new ItemStack(AoABlocks.ANCIENT_BANNER.get()), () -> BANNERS, FUNCTIONAL_BLOCKS);
	}

	private static CreativeModeTab register(CreativeModeTabEvent.Register ev, String id, Supplier<ItemStack> icon, Supplier<CreativeModeTab> tab, CreativeModeTab afterTab) {
		return ev.registerCreativeModeTab(AdventOfAscension.id(id), List.of(), List.of(afterTab), builder -> {
			builder.title(LocaleUtil.getLocaleMessage("itemGroup." + AdventOfAscension.MOD_ID + "." + id))
					.icon(icon)
					.displayItems((displayParams, output) -> output.acceptAll(AoAStartupCache.getItemsForTab(tab.get())));
		});
	}

	private static void fillVanillaTabs(final CreativeModeTabEvent.BuildContents ev) {
		ev.acceptAll(AoAStartupCache.getItemsForTab(ev.getTab()));
	}
}
