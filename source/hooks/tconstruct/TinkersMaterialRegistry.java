package net.tslat.aoa3.hooks.tconstruct;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.entity.mobs.overworld.*;
import net.tslat.aoa3.entity.mobs.overworld.soulscurry.EntityGhostlyCharger;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ModUtil;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.tools.TinkerTraits;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class TinkersMaterialRegistry {
	private static final Material BARONYTE = compileMaterial("baronyte", 0xCE0000, TinkersFluidRegistry.BARONYTE, "ingotBaronyte");
	private static final Material BLAZIUM = compileMaterial("blazium", 0xFF5D00, TinkersFluidRegistry.BLAZIUM, "ingotBlazium");
	private static final Material ELECANIUM = compileMaterial("elecanium", 0x00F9F9, TinkersFluidRegistry.ELECANIUM, "ingotElecanium");
	private static final Material EMBERSTONE = compileMaterial("emberstone", 0x6B1919, TinkersFluidRegistry.EMBERSTONE, "ingotEmberstone");
	private static final Material GHASTLY = compileMaterial("ghastly", 0xCFF99F, TinkersFluidRegistry.GHASTLY, "ingotGhastly");
	private static final Material GHOULISH = compileMaterial("ghoulish", 0x99A8FC, TinkersFluidRegistry.GHOULISH, "ingotGhoulish");
	private static final Material LIMONITE = compileMaterial("limonite", 0xFFB200, TinkersFluidRegistry.LIMONITE, "ingotLimonite");
	private static final Material LUNAR = compileMaterial("lunar", 0xFF8CBF, TinkersFluidRegistry.LUNAR, "ingotLunar");
	private static final Material LYON = compileMaterial("lyon", 0xA88100, TinkersFluidRegistry.LYON, "ingotLyon");
	private static final Material MYSTITE = compileMaterial("mystite", 0x6BFFBE, TinkersFluidRegistry.MYSTITE, "ingotMystite");
	private static final Material ROSITE = compileMaterial("rosite", 0xD31336, TinkersFluidRegistry.ROSITE, "ingotRosite");
	private static final Material SHYRESTONE = compileMaterial("shyrestone", 0x009BFC, TinkersFluidRegistry.SHYRESTONE, "ingotShyrestone");
	private static final Material SKELETAL = compileMaterial("skeletal", 0xFFE8A0, TinkersFluidRegistry.SKELETAL, "ingotSkeletal");
	private static final Material VARSIUM = compileMaterial("varsium", 0xC68A2F, TinkersFluidRegistry.SKELETAL, "ingotVarsium");

	private static final Material CHARGER = compileMaterial("charger", 0xFFE500);

	private static final Material LIMONITE_ROD = compileMaterial("limonite_rod", 0xFFB200);
	private static final Material TORN_CLOTH = compileMaterial("torn_cloth", 0x6D6958);

	public static void preInit() {
		setMaterialStats();
		integrateMaterials();

		if (ModUtil.isClient())
			MinecraftForge.EVENT_BUS.register(new TinkerClientEventHandler());
	}

	public static void init() {
		CHARGER.addItem(ItemRegister.RAW_CHARGER_SHANK, 6, 1);
		CHARGER.addItem(ItemRegister.CHARGER_SHANK, 5, 1);
		CHARGER.setRepresentativeItem(new ItemStack(ItemRegister.RAW_CHARGER_SHANK, 6));
		LIMONITE_ROD.addItem(ItemRegister.LIMONITE_ROD);
		LIMONITE_ROD.setRepresentativeItem(ItemRegister.LIMONITE_ROD);
		TORN_CLOTH.addItem(ItemRegister.TORN_CLOTH);
		TORN_CLOTH.setRepresentativeItem(ItemRegister.TORN_CLOTH);

		ArrayList<Integer> aoaSlimeIslandBlacklist = new ArrayList<Integer>();

		for (String st : ConfigurationUtil.IntegrationsConfig.tinkersConstruct.slimeIslandBlacklist) {
			Enums.Dimensions dim;

			try {
				dim = Enums.Dimensions.valueOf(st.toUpperCase().replace(" ", "_"));

				if (dim != Enums.Dimensions.OVERWORLD && dim != Enums.Dimensions.THE_END && dim != Enums.Dimensions.NETHER)
					aoaSlimeIslandBlacklist.add(ConfigurationUtil.getDimensionId(dim));
			}
			catch (IllegalArgumentException ex) {
				AdventOfAscension.logOptionalMessage("Invalid dimension name \"" + st + "\" found in AoA Slime Island Blacklist. Skipping.");
			}
		}

		int[] newSlimeIslandBlacklist = new int[Config.slimeIslandBlacklist.length + aoaSlimeIslandBlacklist.size()];

		System.arraycopy(Config.slimeIslandBlacklist, 0, newSlimeIslandBlacklist, 0, Config.slimeIslandBlacklist.length);

		for (int i = 0; i < aoaSlimeIslandBlacklist.size(); i++) {
			newSlimeIslandBlacklist[i + Config.slimeIslandBlacklist.length] = aoaSlimeIslandBlacklist.get(i);
		}

		Config.slimeIslandBlacklist = newSlimeIslandBlacklist;

		registerCustomMelting();
		applyTraits();
	}

	private static void setMaterialStats() {
		TinkerRegistry.addMaterialStats(BARONYTE,
				new HeadMaterialStats(900, 5.3f, 3.75f, 4),
				new HandleMaterialStats(1.15f, 130),
				new ExtraMaterialStats(220),
				new BowMaterialStats(0.5f, 1.6f, 4.0f));

		TinkerRegistry.addMaterialStats(BLAZIUM,
				new HeadMaterialStats(800, 4.5f, 3.6f, 4),
				new HandleMaterialStats(0.7f, 60),
				new ExtraMaterialStats(70),
				new BowMaterialStats(0.6f, 1.2f, 3.5f));

		TinkerRegistry.addMaterialStats(ELECANIUM,
				new HeadMaterialStats(600, 8.3f, 6.1f, 6),
				new HandleMaterialStats(2, -400),
				new ExtraMaterialStats(30),
				new BowMaterialStats(0.82f, 1.8f, 7.7f));

		TinkerRegistry.addMaterialStats(EMBERSTONE,
				new HeadMaterialStats(640, 4.0f, 3.8f, MaterialsRegister.TOOL_EMBERSTONE.getHarvestLevel()),
				new HandleMaterialStats(0.65f, 100),
				new ExtraMaterialStats(200),
				new BowMaterialStats(0.65f, 1.3f, 4.2f));

		TinkerRegistry.addMaterialStats(GHASTLY,
				new HeadMaterialStats(730, 4.7f, 4.5f, 6),
				new HandleMaterialStats(0.9f, 30),
				new ExtraMaterialStats(50),
				new BowMaterialStats(0.73f, 1.55f, 4.6f));

		TinkerRegistry.addMaterialStats(GHOULISH,
				new HeadMaterialStats(1000, 3.9f, 3.2f, 6),
				new HandleMaterialStats(1.3f, 45),
				new ExtraMaterialStats(420),
				new BowMaterialStats(0.74f, 0.6f, 3.2f));

		TinkerRegistry.addMaterialStats(LIMONITE,
				new HeadMaterialStats(250, 3f, 2.8f, MaterialsRegister.TOOL_LIMONITE.getHarvestLevel()),
				new HandleMaterialStats(0.6f, 70),
				new ExtraMaterialStats(75),
				new BowMaterialStats(0.4f, 0.6f, 0f));

		TinkerRegistry.addMaterialStats(LUNAR,
				new HeadMaterialStats(660, 3.8f, 2f, 6),
				new HandleMaterialStats(0.9f, 90),
				new ExtraMaterialStats(130),
				new BowMaterialStats(0.6f, 1.15f, 3.4f));

		TinkerRegistry.addMaterialStats(LYON,
				new HeadMaterialStats(840, 6.2f, 4f, 6),
				new HandleMaterialStats(1.05f, 55),
				new ExtraMaterialStats(380),
				new BowMaterialStats(0.5f, 1.5f, 3.75f));

		TinkerRegistry.addMaterialStats(MYSTITE,
				new HeadMaterialStats(760, 3.65f, 4.3f, 6),
				new HandleMaterialStats(1.05f, 105),
				new ExtraMaterialStats(80),
				new BowMaterialStats(0.65f, 1.4f, 3f));

		TinkerRegistry.addMaterialStats(ROSITE,
				new HeadMaterialStats(400, 3.4f, 3.4f, MaterialsRegister.TOOL_ROSITE.getHarvestLevel()),
				new HandleMaterialStats(0.95f, 20),
				new ExtraMaterialStats(55),
				new BowMaterialStats(0.54f, 0.8f, 1.0f));

		TinkerRegistry.addMaterialStats(SHYRESTONE,
				new HeadMaterialStats(640, 13f, 3.6f, 6),
				new HandleMaterialStats(1.0f, 0),
				new ExtraMaterialStats(315),
				new BowMaterialStats(0.65f, 1.3f, 3.2f));

		TinkerRegistry.addMaterialStats(SKELETAL,
				new HeadMaterialStats(610, 10f, 4.3f, MaterialsRegister.TOOL_SKELETAL.getHarvestLevel()),
				new HandleMaterialStats(0.75f, 140),
				new ExtraMaterialStats(290),
				new BowMaterialStats(1.15f, 1.4f, 2.6f));

		TinkerRegistry.addMaterialStats(VARSIUM,
				new HeadMaterialStats(870, 5.2f, 3.9f, 4),
				new HandleMaterialStats(1f, 300),
				new ExtraMaterialStats(250),
				new BowMaterialStats(0.55f, 1.5f, 3.8f));

		TinkerRegistry.addMaterialStats(CHARGER,
				new HeadMaterialStats(110, 2.4f, 2.5f, 0),
				new HandleMaterialStats(0.5f, 0),
				new ExtraMaterialStats(18));

		TinkerRegistry.addMaterialStats(LIMONITE_ROD, new ArrowShaftMaterialStats(2f, -20));
		TinkerRegistry.addMaterialStats(TORN_CLOTH, new FletchingMaterialStats(0.35f, 2.25f));
	}

	private static void applyTraits() {
		BARONYTE.addTrait(Traits.BARON);
		BLAZIUM.addTrait(Traits.FLAMING_FURY);
		BLAZIUM.addTrait(Traits.INFERNAL_ENERGY, MaterialTypes.HEAD);
		CHARGER.addTrait(Traits.TWEETING);
		ELECANIUM.addTrait(Traits.RUNIC_1);
		ELECANIUM.addTrait(Traits.RUNIC_2, MaterialTypes.HEAD);
		EMBERSTONE.addTrait(TinkerTraits.autosmelt);
		EMBERSTONE.addTrait(Traits.BLAZING);
		GHASTLY.addTrait(Traits.ANTI_AIR_1);
		GHASTLY.addTrait(Traits.ANTI_AIR_2, MaterialTypes.HEAD);
		GHASTLY.addTrait(Traits.ETHEREAL_MINER);
		GHOULISH.addTrait(Traits.SOUL_HARVEST);
		GHOULISH.addTrait(Traits.SOUL_SAP);
		LIMONITE.addTrait(Traits.DISCOUNTED);
		LUNAR.addTrait(Traits.COUNTERWEIGHT_1);
		LUNAR.addTrait(Traits.COUNTERWEIGHT_2, MaterialTypes.HEAD);
		LYON.addTrait(TinkerTraits.magnetic);
		LYON.addTrait(TinkerTraits.magnetic2, MaterialTypes.HEAD);
		MYSTITE.addTrait(Traits.LACED);
		MYSTITE.addTrait(Traits.TOXIC, MaterialTypes.HEAD);
		ROSITE.addTrait(TinkerTraits.writable2, MaterialTypes.HEAD);
		SHYRESTONE.addTrait(Traits.SHYRE_SYNTHESIS);
		SKELETAL.addTrait(Traits.BONE_SHOCK);
		SKELETAL.addTrait(Traits.HIGH_IN_CALCIUM);
		VARSIUM.addTrait(Traits.EVIL_PRESSURE);

		LIMONITE_ROD.addTrait(Traits.DISCOUNTED);
	}

	private static void integrateMaterials() {
		integrate(BARONYTE, TinkersFluidRegistry.BARONYTE, "Baronyte", true);
		integrate(BLAZIUM, TinkersFluidRegistry.BLAZIUM, "Blazium", true);
		integrate(ELECANIUM, TinkersFluidRegistry.ELECANIUM, "Elecanium", true);
		integrate(EMBERSTONE, TinkersFluidRegistry.EMBERSTONE, "Emberstone", true);
		integrate(GHASTLY, TinkersFluidRegistry.GHASTLY, "Ghastly", true);
		integrate(GHOULISH, TinkersFluidRegistry.GHOULISH, "Ghoulish", true);
		integrate(LIMONITE, TinkersFluidRegistry.LIMONITE, "Limonite", true);
		integrate(LUNAR, TinkersFluidRegistry.LUNAR, "Lunar", true);
		integrate(LYON, TinkersFluidRegistry.LYON, "Lyon", true);
		integrate(MYSTITE, TinkersFluidRegistry.MYSTITE, "Mystite", true);
		integrate(ROSITE, TinkersFluidRegistry.ROSITE, "Rosite", true);
		integrate(SHYRESTONE, TinkersFluidRegistry.SHYRESTONE, "Shyrestone", true);
		integrate(SKELETAL, TinkersFluidRegistry.SKELETAL, "Skeletal", true);
		integrate(VARSIUM, TinkersFluidRegistry.VARSIUM, "Varsium", true);

		integrate(CHARGER, TinkersFluidRegistry.CHARGER);
		integrate(LIMONITE_ROD);
		integrate(TORN_CLOTH);
	}

	public static void registerSmelting(Item smeltItem, Fluid fluid, int amount) {
		TinkerRegistry.registerMelting(smeltItem, fluid, amount);
	}

	private static void registerCustomMelting() {
		registerSmelting(ItemRegister.CHARGER_SHANK, TinkersFluidRegistry.CHARGER, 20);
		registerSmelting(ItemRegister.RAW_CHARGER_SHANK, TinkersFluidRegistry.CHARGER, 17);

		TinkerRegistry.registerEntityMelting(EntityCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
		TinkerRegistry.registerEntityMelting(EntityKingCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 8));
		TinkerRegistry.registerEntityMelting(EntityDesertCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
		TinkerRegistry.registerEntityMelting(EntityHillCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
		TinkerRegistry.registerEntityMelting(EntityGhostlyCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
		TinkerRegistry.registerEntityMelting(EntitySeaCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
		TinkerRegistry.registerEntityMelting(EntitySwampCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
		TinkerRegistry.registerEntityMelting(EntityVoidCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
		TinkerRegistry.registerEntityMelting(EntitySnowCharger.class, new FluidStack(TinkersFluidRegistry.CHARGER, 3));
	}

	private static void integrate(Material material) {
		TinkerRegistry.integrate(material);
	}

	private static void integrate(Material material, Fluid fluid) {
		TinkerRegistry.integrate(material, fluid);
	}

	private static void integrate(Material material, Fluid fluid, String oreSuffix, boolean toolForgeMaterial) {
		MaterialIntegration integration = new MaterialIntegration(material, fluid, oreSuffix);

		if (toolForgeMaterial)
			integration.toolforge();

		TinkerRegistry.integrate(integration).preInit();
	}

	private static Material compileMaterial(String name, int colour) {
		return new Material(name, colour);
	}

	private static Material compileMaterial(String name, int colour, @Nullable Fluid fluid, @Nullable String ingotOreDict) {
		Material mat = new Material(name, colour);

		if (fluid != null)
			mat.setFluid(fluid);

		if (ingotOreDict != null)
			mat.addItemIngot(ingotOreDict);

		return mat;
	}
}
