package net.tslat.aoa3.common.registration;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.ArrayList;
import java.util.function.Supplier;

public final class AoAItemGroups {
	public static final ArrayList<AoAItemGroup> REGISTERED_ITEM_GROUPS = new ArrayList<AoAItemGroup>();

	public static final ItemGroup MISC_ITEMS = new AoAItemGroup("AoA Miscellaneous Items", "miscellaneous", () -> new ItemStack(AoAItems.ROSITE_INGOT.get()));
	public static final ItemGroup FOOD = new AoAItemGroup("AoA Food", "food", () -> new ItemStack(AoAItems.COOKED_RAINBOWFISH.get()));
	public static final ItemGroup TOOLS = new AoAItemGroup("AoA Tools", "tools", () -> new ItemStack(AoATools.LIMONITE_PICKAXE.get()));
	public static final ItemGroup ARMOUR = new AoAItemGroup("AoA Armour", "armour", () -> new ItemStack(AoAArmour.AMETHIND_ARMOUR.chestplate.get()));
	public static final ItemGroup SWORDS = new AoAItemGroup("AoA Swords", "swords", () -> new ItemStack(AoAWeapons.AMETHYST_SWORD.get()));
	public static final ItemGroup GREATBLADES = new AoAItemGroup("AoA Greatblades", "greatblades", () -> new ItemStack(AoAWeapons.BLOODLURKER.get()));
	public static final ItemGroup MAULS = new AoAItemGroup("AoA Mauls", "mauls", () -> new ItemStack(AoAWeapons.HORIZON_MAUL.get()));
	public static final ItemGroup GUNS = new AoAItemGroup("AoA Guns", "guns", () -> new ItemStack(AoAWeapons.SQUAD_GUN.get()));
	public static final ItemGroup CANNONS = new AoAItemGroup("AoA Cannons", "cannons", () -> new ItemStack(AoAWeapons.BLAST_CANNON.get()));
	public static final ItemGroup SHOTGUNS = new AoAItemGroup("AoA Shotguns", "shotguns", () -> new ItemStack(AoAWeapons.BLAST_BARREL.get()));
	public static final ItemGroup SNIPERS = new AoAItemGroup("AoA Snipers", "snipers", () -> new ItemStack(AoAWeapons.BOLT_RIFLE.get()));
	public static final ItemGroup BLASTERS = new AoAItemGroup("AoA Blasters", "blasters", () -> new ItemStack(AoAWeapons.LASER_BLASTER.get()));
	public static final ItemGroup CROSSBOWS = new AoAItemGroup("AoA Crossbows", "crossbows", () -> new ItemStack(AoAWeapons.LUNAR_CROSSBOW.get()));
	public static final ItemGroup BOWS = new AoAItemGroup("AoA Bows", "bows", () -> new ItemStack(AoAWeapons.SPEED_BOW.get()));
	public static final ItemGroup THROWN_WEAPONS = new AoAItemGroup("AoA Thrown Weapons", "throwables", () -> new ItemStack(AoAWeapons.GRENADE.get()));
	public static final ItemGroup STAVES = new AoAItemGroup("AoA Staves", "staves", () -> new ItemStack(AoAWeapons.CELESTIAL_STAFF.get()));
	public static final ItemGroup VULCANES = new AoAItemGroup("AoA Vulcanes", "vulcanes", () -> new ItemStack(AoAWeapons.VULCANE.get()));
	public static final ItemGroup AMMUNITION = new AoAItemGroup("AoA Ammunition", "ammunition", () -> new ItemStack(AoAItems.LIMONITE_BULLET.get()));
	public static final ItemGroup MINION_SLABS = new AoAItemGroup("AoA Minion Slabs", "minions", () -> new ItemStack(AoAItems.PENGUIN_SLAB.get()));
	public static final ItemGroup TABLETS = new AoAItemGroup("AoA Tablets", "tablets", () -> new ItemStack(AoAItems.AGILITY_TABLET.get()));
	public static final ItemGroup GENERATION_BLOCKS = new AoAItemGroup("AoA Worldgen Blocks", "generation", () -> new ItemStack(AoABlocks.LELYETIAN_GRASS.get()));
	public static final ItemGroup DECORATION_BLOCKS = new AoAItemGroup("AoA Decorative Blocks", "decoration", () -> new ItemStack(AoABlocks.BARON_BRICKS.get()));
	public static final ItemGroup FUNCTIONAL_BLOCKS = new AoAItemGroup("AoA Functional Blocks", "functional", () -> new ItemStack(AoABlocks.SHADOW_ALTAR.get()));
	public static final ItemGroup BANNERS = new AoAItemGroup("AoA Banners", "banners", () -> new ItemStack(AoABlocks.ANCIENT_BANNER.get()));

	public static class AoAItemGroup extends ItemGroup {
		private final String displayName;
		private final Supplier<ItemStack> displayStack;

		public AoAItemGroup(String name, String id, Supplier<ItemStack> iconStack) {
			super(AdventOfAscension.MOD_ID + "." + id);

			this.displayName = name;
			this.displayStack = iconStack;

			REGISTERED_ITEM_GROUPS.add(this);
		}

		@Override
		public ItemStack createIcon() {
			return displayStack.get();
		}

		public String getDisplayName() {
			return this.displayName;
		}
	}
}
