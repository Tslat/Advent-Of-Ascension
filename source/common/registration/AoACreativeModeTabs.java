package net.tslat.aoa3.common.registration;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.AoAStartupCache;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.function.Supplier;

public final class AoACreativeModeTabs {

	public static void init() {
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, BuildCreativeModeTabContentsEvent.class, AoACreativeModeTabs::fillVanillaTabs);
	}

	public static final RegistryObject<CreativeModeTab> MISC_ITEMS = register("miscellaneous", () -> new ItemStack(AoAItems.LIMONITE_INGOT.get()), CreativeModeTabs.SPAWN_EGGS);
	public static final RegistryObject<CreativeModeTab> FOOD = register("food", () -> new ItemStack(AoAItems.COOKED_RAINBOWFISH.get()), MISC_ITEMS);
	public static final RegistryObject<CreativeModeTab> TOOLS = register("tools", () -> new ItemStack(AoATools.LIMONITE_PICKAXE.get()), FOOD);
	public static final RegistryObject<CreativeModeTab> ARMOUR = register("armour", () -> new ItemStack(AoAArmour.ALACRITY_ARMOUR.chestplate.get()), TOOLS);
	public static final RegistryObject<CreativeModeTab> SWORDS = register("swords", () -> new ItemStack(AoAWeapons.LIMONITE_SWORD.get()), ARMOUR);
	public static final RegistryObject<CreativeModeTab> GREATBLADES = register("greatblades", () -> new ItemStack(AoAWeapons.BLOODLURKER.get()), SWORDS);
	public static final RegistryObject<CreativeModeTab> MAULS = register("mauls", () -> new ItemStack(AoAWeapons.HORIZON_MAUL.get()), GREATBLADES);
	public static final RegistryObject<CreativeModeTab> GUNS = register("guns", () -> new ItemStack(AoAWeapons.SQUAD_GUN.get()), MAULS);
	public static final RegistryObject<CreativeModeTab> CANNONS = register("cannons", () -> new ItemStack(AoAWeapons.BLAST_CANNON.get()), GUNS);
	public static final RegistryObject<CreativeModeTab> SHOTGUNS = register("shotguns", () -> new ItemStack(AoAWeapons.BLAST_BARREL.get()), CANNONS);
	public static final RegistryObject<CreativeModeTab> SNIPERS = register("snipers", () -> new ItemStack(AoAWeapons.BOLT_RIFLE.get()), SHOTGUNS);
	public static final RegistryObject<CreativeModeTab> BLASTERS = register("blasters", () -> new ItemStack(AoAWeapons.LASER_BLASTER.get()), SNIPERS);
	public static final RegistryObject<CreativeModeTab> CROSSBOWS = register("crossbows", () -> new ItemStack(AoAWeapons.LUNAR_CROSSBOW.get()), BLASTERS);
	public static final RegistryObject<CreativeModeTab> BOWS = register("bows", () -> new ItemStack(AoAWeapons.SPEED_BOW.get()), CROSSBOWS);
	public static final RegistryObject<CreativeModeTab> THROWN_WEAPONS = register("thrown_weapons", () -> new ItemStack(AoAWeapons.GRENADE.get()), BOWS);
	public static final RegistryObject<CreativeModeTab> STAVES = register("staves", () -> new ItemStack(AoAWeapons.CELESTIAL_STAFF.get()), THROWN_WEAPONS);
	public static final RegistryObject<CreativeModeTab> VULCANES = register("vulcanes", () -> new ItemStack(AoAWeapons.VULCANE.get()), STAVES);
	public static final RegistryObject<CreativeModeTab> AMMUNITION = register("ammunition", () -> new ItemStack(AoAItems.LIMONITE_BULLET.get()), VULCANES);
	public static final RegistryObject<CreativeModeTab> TABLETS = register("tablets", () -> new ItemStack(AoAItems.AGILITY_TABLET.get()), AMMUNITION);
	public static final RegistryObject<CreativeModeTab> GENERATION_BLOCKS = register("worldgen_blocks", () -> new ItemStack(AoABlocks.LELYETIAN_GRASS.get()), TABLETS);
	public static final RegistryObject<CreativeModeTab> DECORATION_BLOCKS = register("decoration_blocks", () -> new ItemStack(AoABlocks.BARON_STONE_BRICKS.stone()), GENERATION_BLOCKS);
	public static final RegistryObject<CreativeModeTab> FUNCTIONAL_BLOCKS = register("functional_blocks", () -> new ItemStack(AoABlocks.SHADOW_ALTAR.get()), DECORATION_BLOCKS);
	public static final RegistryObject<CreativeModeTab> BANNERS = register("banners", () -> new ItemStack(AoABlocks.ANCIENT_BANNER.base()), FUNCTIONAL_BLOCKS);

	private static RegistryObject<CreativeModeTab> register(String id, Supplier<ItemStack> icon, RegistryObject<CreativeModeTab> afterTab) {
		return register(id, icon, afterTab.getKey());
	}

	private static RegistryObject<CreativeModeTab> register(String id, Supplier<ItemStack> icon, ResourceKey<CreativeModeTab>... afterTabs) {
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
