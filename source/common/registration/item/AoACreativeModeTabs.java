package net.tslat.aoa3.common.registration.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.AoAStartupCache;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.function.Supplier;

public final class AoACreativeModeTabs {

	public static void init() {
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, BuildCreativeModeTabContentsEvent.class, AoACreativeModeTabs::fillVanillaTabs);
	}

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MISC_ITEMS = register("miscellaneous", () -> AoAItems.LIMONITE_INGOT.get().getDefaultInstance(), CreativeModeTabs.SPAWN_EGGS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD = register("food", () -> AoAItems.COOKED_RAINBOWFISH.get().getDefaultInstance(), MISC_ITEMS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS = register("tools", () -> AoATools.LIMONITE_PICKAXE.get().getDefaultInstance(), FOOD);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ARMOUR = register("armour", () -> AoAArmour.ALACRITY_ARMOUR.chestplate.get().getDefaultInstance(), TOOLS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SWORDS = register("swords", () -> AoAWeapons.LIMONITE_SWORD.get().getDefaultInstance(), ARMOUR);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GREATBLADES = register("greatblades", () -> AoAWeapons.BLOODLURKER.get().getDefaultInstance(), SWORDS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAULS = register("mauls", () -> AoAWeapons.HORIZON_MAUL.get().getDefaultInstance(), GREATBLADES);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GUNS = register("guns", () -> AoAWeapons.SQUAD_GUN.get().getDefaultInstance(), MAULS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CANNONS = register("cannons", () -> AoAWeapons.BLAST_CANNON.get().getDefaultInstance(), GUNS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SHOTGUNS = register("shotguns", () -> AoAWeapons.BLAST_BARREL.get().getDefaultInstance(), CANNONS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SNIPERS = register("snipers", () -> AoAWeapons.BOLT_RIFLE.get().getDefaultInstance(), SHOTGUNS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLASTERS = register("blasters", () -> AoAWeapons.LASER_BLASTER.get().getDefaultInstance(), SNIPERS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CROSSBOWS = register("crossbows", () -> AoAWeapons.LUNAR_CROSSBOW.get().getDefaultInstance(), BLASTERS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BOWS = register("bows", () -> AoAWeapons.SPEED_BOW.get().getDefaultInstance(), CROSSBOWS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> THROWN_WEAPONS = register("thrown_weapons", () -> AoAWeapons.GRENADE.get().getDefaultInstance(), BOWS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STAVES = register("staves", () -> AoAWeapons.CELESTIAL_STAFF.get().getDefaultInstance(), THROWN_WEAPONS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VULCANES = register("vulcanes", () -> AoAWeapons.VULCANE.get().getDefaultInstance(), STAVES);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AMMUNITION = register("ammunition", () -> AoAItems.LIMONITE_BULLET.get().getDefaultInstance(), VULCANES);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GENERATION_BLOCKS = register("worldgen_blocks", () -> AoABlocks.LELYETIAN_GRASS.get().asItem().getDefaultInstance(), AMMUNITION);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_BLOCKS = register("decoration_blocks", () -> AoABlocks.BARON_STONE_BRICKS.stone().asItem().getDefaultInstance(), GENERATION_BLOCKS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FUNCTIONAL_BLOCKS = register("functional_blocks", () -> AoABlocks.SHADOW_ALTAR.get().asItem().getDefaultInstance(), DECORATION_BLOCKS);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BANNERS = register("banners", () -> AoABlocks.ANCIENT_BANNER.base().asItem().getDefaultInstance(), FUNCTIONAL_BLOCKS);

	private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String id, Supplier<ItemStack> icon, DeferredHolder<CreativeModeTab, CreativeModeTab> afterTab) {
		return register(id, icon, afterTab.getKey());
	}

	private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String id, Supplier<ItemStack> icon, ResourceKey<CreativeModeTab>... afterTabs) {
		return AoARegistries.CREATIVE_MODE_TABS.register(id, () -> CreativeModeTab.builder()
				.title(LocaleUtil.getLocaleMessage("itemGroup." + AdventOfAscension.MOD_ID + "." + id))
				.withTabsBefore(afterTabs)
				.icon(icon)
				.displayItems((displayParams, output) -> output.acceptAll(AoAStartupCache.getItemsForTab(AoARegistries.CREATIVE_MODE_TABS.getEntry(AdventOfAscension.id(id)))))
				.build());
	}

	private static void fillVanillaTabs(final BuildCreativeModeTabContentsEvent ev) {
		ev.acceptAll(AoAStartupCache.getItemsForTab(ev.getTab()));
	}
}
