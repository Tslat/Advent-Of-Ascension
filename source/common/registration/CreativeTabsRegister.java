package net.tslat.aoa3.common.registration;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

@SuppressWarnings("ConstantConditions")
public class CreativeTabsRegister {
	public static final CreativeTabs miscTab = new CreativeTabs("AdventMiscTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.ingotRosite);
		}
	};

	public static final CreativeTabs foodTab = new CreativeTabs("AdventFoodTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.rainbowfish, 1, 0);
		}
	};

	public static final CreativeTabs toolsTab = new CreativeTabs("AdventToolsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ToolRegister.pickaxeLimonite, 1, 0);
		}
	};

	public static final CreativeTabs armourTab = new CreativeTabs("AdventArmourTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ArmourRegister.amethindBody, 1, 0);
		}
	};

	public static final CreativeTabs swordsTab = new CreativeTabs("AdventSwordsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.swordAmethyst, 1, 0);
		}
	};

	public static final CreativeTabs greatbladesTab = new CreativeTabs("AdventGreatbladesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.greatbladeGrandsword, 1, 0);
		}
	};

	public static final CreativeTabs maulsTab = new CreativeTabs("AdventMaulsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.maulHorizon, 1, 0);
		}
	};

	public static final CreativeTabs gunsTab = new CreativeTabs("AdventGunsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.gunSquadGun, 1, 0);
		}
	};

	public static final CreativeTabs cannonsTab = new CreativeTabs("AdventCannonsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.cannonBlastCannon, 1, 0);
		}
	};

	public static final CreativeTabs shotgunsTab = new CreativeTabs("AdventShotgunsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.shotgunBlastBarrel, 1, 0);
		}
	};

	public static final CreativeTabs snipersTab = new CreativeTabs("AdventSnipersTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.sniperBoltRifle, 1, 0);
		}
	};

	public static final CreativeTabs blastersTab = new CreativeTabs("AdventBlastersTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.blasterColourCannon, 1, 0);
		}
	};

	public static final CreativeTabs archergunsTab = new CreativeTabs("AdventArchergunsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.archergunTrolls, 1, 0);
		}
	};

	public static final CreativeTabs bowsTab = new CreativeTabs("AdventBowsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.bowSpeed, 1, 0);
		}
	};

	public static final CreativeTabs thrownWeaponsTab = new CreativeTabs("AdventThrownWeaponsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.throwableGrenade, 1, 0);
		}
	};

	public static final CreativeTabs stavesTab = new CreativeTabs("AdventStavesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.staffCelestial, 1, 0);
		}
	};

	public static final CreativeTabs vulcanesTab = new CreativeTabs("AdventVulcanesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WeaponRegister.vulcane, 1, 0);
		}
	};

	public static final CreativeTabs ammoTab = new CreativeTabs("AdventAmmoTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.hollyArrow, 1, 0);
		}
	};

	public static final CreativeTabs minionSlabsTab = new CreativeTabs("AdventMinionSlabsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.slabPenguin, 1, 0);
		}
	};

	public static final CreativeTabs tabletsTab = new CreativeTabs("AdventTabletsTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.tabletAgility, 1, 0);
		}
	};

	public static final CreativeTabs generationBlocksTab = new CreativeTabs("AdventGenerationBlocksTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.grassLelyetia, 1, 0);
		}
	};

	public static final CreativeTabs decorationBlocksTab = new CreativeTabs("AdventDecorationBlocksTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.bricksBaron, 1, 0);
		}
	};

	public static final CreativeTabs functionalBlocksTab = new CreativeTabs("AdventFunctionalBlocksTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.shadowAltar, 1, 0);
		}
	};

	public static final CreativeTabs bannersTab = new CreativeTabs("AdventBannersTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.bannerAncient, 1, 0);
		}
	};

	public static final CreativeTabs statuesTab = new CreativeTabs("AdventStatuesTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockRegister.statueSmash, 1, 0);
		}
	};
}
