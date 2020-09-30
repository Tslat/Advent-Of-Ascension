package net.tslat.aoa3.common.registration;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

@SuppressWarnings("ConstantConditions")
public class CreativeTabsRegister {
	public static final CreativeTabs MISC = new CreativeTabs("AdventMiscTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.ROSITE_INGOT);
		}
	};

	public static final CreativeTabs FOOD = new CreativeTabs("AdventFoodTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.RAINBOWFISH, 1, 0);
		}
	};

	public static final CreativeTabs TOOLS = new CreativeTabs("AdventToolsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ToolRegister.LIMONITE_PICKAXE, 1, 0);
		}
	};

	public static final CreativeTabs ARMOUR = new CreativeTabs("AdventArmourTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ArmourRegister.AMETHIND_CHESTPLATE, 1, 0);
		}
	};

	public static final CreativeTabs SWORDS = new CreativeTabs("AdventSwordsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.AMETHYST_SWORD, 1, 0);
		}
	};

	public static final CreativeTabs GREATBLADES = new CreativeTabs("AdventGreatbladesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.BLOODLURKER, 1, 0);
		}
	};

	public static final CreativeTabs MAULS = new CreativeTabs("AdventMaulsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.HORIZON_MAUL, 1, 0);
		}
	};

	public static final CreativeTabs GUNS = new CreativeTabs("AdventGunsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.SQUAD_GUN, 1, 0);
		}
	};

	public static final CreativeTabs CANNONS = new CreativeTabs("AdventCannonsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.BLAST_CANNON, 1, 0);
		}
	};

	public static final CreativeTabs SHOTGUNS = new CreativeTabs("AdventShotgunsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.BLAST_BARREL, 1, 0);
		}
	};

	public static final CreativeTabs SNIPERS = new CreativeTabs("AdventSnipersTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.BOLT_RIFLE, 1, 0);
		}
	};

	public static final CreativeTabs BLASTERS = new CreativeTabs("AdventBlastersTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.COLOUR_CANNON, 1, 0);
		}
	};

	public static final CreativeTabs ARCHERGUNS = new CreativeTabs("AdventArchergunsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.TROLLS_ARCHERGUN, 1, 0);
		}
	};

	public static final CreativeTabs BOWS = new CreativeTabs("AdventBowsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.SPEED_BOW, 1, 0);
		}
	};

	public static final CreativeTabs THROWN_WEAPONS = new CreativeTabs("AdventThrownWeaponsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.GRENADE, 1, 0);
		}
	};

	public static final CreativeTabs STAVES = new CreativeTabs("AdventStavesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.CELESTIAL_STAFF, 1, 0);
		}
	};

	public static final CreativeTabs VULCANES = new CreativeTabs("AdventVulcanesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.VULCANE, 1, 0);
		}
	};

	public static final CreativeTabs AMMUNITION = new CreativeTabs("AdventAmmoTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.HOLLY_ARROW, 1, 0);
		}
	};

	public static final CreativeTabs MINION_SLABS = new CreativeTabs("AdventMinionSlabsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.PENGUIN_SLAB, 1, 0);
		}
	};

	public static final CreativeTabs TABLETS = new CreativeTabs("AdventTabletsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.AGILITY_TABLET, 1, 0);
		}
	};

	public static final CreativeTabs GENERATION_BLOCKS = new CreativeTabs("AdventGenerationBlocksTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.LELYETIA_GRASS, 1, 0);
		}
	};

	public static final CreativeTabs DECORATION_BLOCKS = new CreativeTabs("AdventDecorationBlocksTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.BARON_BRICKS, 1, 0);
		}
	};

	public static final CreativeTabs FUNCTIONAL_BLOCKS = new CreativeTabs("AdventFunctionalBlocksTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.SHADOW_ALTAR, 1, 0);
		}
	};

	public static final CreativeTabs BANNERS = new CreativeTabs("AdventBannersTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.ANCIENT_BANNER, 1, 0);
		}
	};

	public static final CreativeTabs STATUES = new CreativeTabs("AdventStatuesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.SMASH_STATUE, 1, 0);
		}
	};
}
