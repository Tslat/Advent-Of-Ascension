package net.tslat.aoa3.common.registration;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.registration.item.AoAWeapons;

import java.util.ArrayList;
import java.util.function.Supplier;

public final class AoACreativeModeTabs {
	public static final ArrayList<AoAItemGroup> REGISTERED_ITEM_GROUPS = new ArrayList<>();

	public static final CreativeModeTab MISC_ITEMS = new AoAItemGroup("AoA Miscellaneous Items", "miscellaneous", () -> new ItemStack(AoAItems.LIMONITE_INGOT.get()));
	public static final CreativeModeTab FOOD = new AoAItemGroup("AoA Food", "food", () -> new ItemStack(AoAItems.COOKED_RAINBOWFISH.get()));
	public static final CreativeModeTab TOOLS = new AoAItemGroup("AoA Tools", "tools", () -> new ItemStack(AoATools.LIMONITE_PICKAXE.get()));
	public static final CreativeModeTab ARMOUR = new AoAItemGroup("AoA Armour", "armour", () -> new ItemStack(AoAArmour.ALACRITY_ARMOUR.chestplate.get()));
	public static final CreativeModeTab SWORDS = new AoAItemGroup("AoA Swords", "swords", () -> new ItemStack(AoAWeapons.LIMONITE_SWORD.get()));
	public static final CreativeModeTab GREATBLADES = new AoAItemGroup("AoA Greatblades", "greatblades", () -> new ItemStack(AoAWeapons.BLOODLURKER.get()));
	public static final CreativeModeTab MAULS = new AoAItemGroup("AoA Mauls", "mauls", () -> new ItemStack(AoAWeapons.HORIZON_MAUL.get()));
	public static final CreativeModeTab GUNS = new AoAItemGroup("AoA Guns", "guns", () -> new ItemStack(AoAWeapons.SQUAD_GUN.get()));
	public static final CreativeModeTab CANNONS = new AoAItemGroup("AoA Cannons", "cannons", () -> new ItemStack(AoAWeapons.BLAST_CANNON.get()));
	public static final CreativeModeTab SHOTGUNS = new AoAItemGroup("AoA Shotguns", "shotguns", () -> new ItemStack(AoAWeapons.BLAST_BARREL.get()));
	public static final CreativeModeTab SNIPERS = new AoAItemGroup("AoA Snipers", "snipers", () -> new ItemStack(AoAWeapons.BOLT_RIFLE.get()));
	public static final CreativeModeTab BLASTERS = new AoAItemGroup("AoA Blasters", "blasters", () -> new ItemStack(AoAWeapons.LASER_BLASTER.get()));
	public static final CreativeModeTab CROSSBOWS = new AoAItemGroup("AoA Crossbows", "crossbows", () -> new ItemStack(AoAWeapons.LUNAR_CROSSBOW.get()));
	public static final CreativeModeTab BOWS = new AoAItemGroup("AoA Bows", "bows", () -> new ItemStack(AoAWeapons.SPEED_BOW.get()));
	public static final CreativeModeTab THROWN_WEAPONS = new AoAItemGroup("AoA Thrown Weapons", "throwables", () -> new ItemStack(AoAWeapons.GRENADE.get()));
	public static final CreativeModeTab STAVES = new AoAItemGroup("AoA Staves", "staves", () -> new ItemStack(AoAWeapons.CELESTIAL_STAFF.get()));
	public static final CreativeModeTab VULCANES = new AoAItemGroup("AoA Vulcanes", "vulcanes", () -> new ItemStack(AoAWeapons.VULCANE.get()));
	public static final CreativeModeTab AMMUNITION = new AoAItemGroup("AoA Ammunition", "ammunition", () -> new ItemStack(AoAItems.LIMONITE_BULLET.get()));
	public static final CreativeModeTab TABLETS = new AoAItemGroup("AoA Tablets", "tablets", () -> new ItemStack(AoAItems.AGILITY_TABLET.get()));
	public static final CreativeModeTab GENERATION_BLOCKS = new AoAItemGroup("AoA Worldgen Blocks", "generation", () -> new ItemStack(AoABlocks.LELYETIAN_GRASS.get()));
	public static final CreativeModeTab DECORATION_BLOCKS = new AoAItemGroup("AoA Decorative Blocks", "decoration", () -> new ItemStack(AoABlocks.BARON_STONE_BRICKS.get()));
	public static final CreativeModeTab FUNCTIONAL_BLOCKS = new AoAItemGroup("AoA Functional Blocks", "functional", () -> new ItemStack(AoABlocks.SHADOW_ALTAR.get()));
	public static final CreativeModeTab BANNERS = new AoAItemGroup("AoA Banners", "banners", () -> new ItemStack(AoABlocks.ANCIENT_BANNER.get()));

	public static class AoAItemGroup extends CreativeModeTab {
		private final String localeName;
		private final Supplier<ItemStack> displayStack;

		public AoAItemGroup(String name, String id, Supplier<ItemStack> iconStack) {
			super(AdventOfAscension.MOD_ID + "." + id);

			this.localeName = name;
			this.displayStack = iconStack;

			REGISTERED_ITEM_GROUPS.add(this);
		}

		@Override
		public ItemStack makeIcon() {
			return displayStack.get();
		}

		public String getLocaleName() {
			return this.localeName;
		}
	}
}
