package net.tslat.aoa3.utils;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationUtil {
	private static Configuration cfg;
	private static Configuration oreCfg;
	private static Configuration structureCfg;
	private static File guidesFolder;

	public static int dimAbyss, dimAncientCavern, dimBarathos, dimCandyland, dimCeleve, dimCreeponia, dimCrystevia, dimDeeplands, dimDustopia, dimGardencia, dimGreckon, dimHaven, dimImmortallis,
					dimIromine, dimLborean, dimLelyetia, dimLunalus, dimMysterium, dimPrecasia, dimRunandor, dimShyrelands, dimVoxPonds;

	public static int guiXResourceCreation, guiYResourceCreation, guiXResourceEnergy, guiYResourceEnergy, guiXResourceRage, guiYResourceRage, guiXResourceRevenge, guiYResourceRevenge, guiXResourceSoul,
					guiYResourceSoul, guiXTribute, guiYTribute;

	public static int amethystVeinCount, amethystMinOres, amethystMaxOres;
	public static int baronyteVeinCount, baronyteMinOres, baronyteMaxOres;
	public static int blaziumVeinCount, blaziumMinOres, blaziumMaxOres;
	public static int bloodstoneVeinCount, bloodstoneMinOres, bloodstoneMaxOres;
	public static int blueCrystalVeinCount, blueCrystalMinOres, blueCrystalMaxOres;
	public static int chargedRuniumVeinCount, chargedRuniumMinOres, chargedRuniumMaxOres;
	public static int chestboneFragmentsVeinCount, chestboneFragmentsMinOres, chestboneFragmentsMaxOres;
	public static int crystalliteVeinCount, crystalliteMinOres, crystalliteMaxOres;
	public static int elecaniumVeinCount, elecaniumMinOres, elecaniumMaxOres;
	public static int emberstoneVeinCount, emberstoneMinOres, emberstoneMaxOres;
	public static int footboneFragmentsVeinCount, footboneFragmentsMinOres, footboneFragmentsMaxOres;
	public static int gemenyteVeinCount, gemenyteMinOres, gemenyteMaxOres;
	public static int ghastlyVeinCount, ghastlyMinOres, ghastlyMaxOres;
	public static int ghoulishVeinCount, ghoulishMinOres, ghoulishMaxOres;
	public static int greenCrystalVeinCount, greenCrystalMinOres, greenCrystalMaxOres;
	public static int jadeVeinCount, jadeMinOres, jadeMaxOres;
	public static int jewelyteVeinCount, jewelyteMinOres, jewelyteMaxOres;
	public static int legboneFragmentsVeinCount, legboneFragmentsMinOres, legboneFragmentsMaxOres;
	public static int limoniteVeinCount, limoniteMinOres, limoniteMaxOres;
	public static int lyonVeinCount, lyonMinOres, lyonMaxOres;
	public static int mystiteVeinCount, mystiteMinOres, mystiteMaxOres;
	public static int ornamyteVeinCount, ornamyteMinOres, ornamyteMaxOres;
	public static int purpleCrystalVeinCount, purpleCrystalMinOres, purpleCrystalMaxOres;
	public static int redCrystalVeinCount, redCrystalMinOres, redCrystalMaxOres;
	public static int rositeVeinCount, rositeMinOres, rositeMaxOres;
	public static int runiumVeinCount, runiumMinOres, runiumMaxOres;
	public static int sapphireVeinCount, sapphireMinOres, sapphireMaxOres;
	public static int shyreOreMinOres, shyreOreMaxOres;
	public static int skullboneFragmentsVeinCount, skullboneFragmentsMinOres, skullboneFragmentsMaxOres;
	public static int varsiumVeinCount, varsiumMinOres, varsiumMaxOres;
	public static int whiteCrystalVeinCount, whiteCrystalMinOres, whiteCrystalMaxOres;
	public static int yellowCrystalVeinCount, yellowCrystalMinOres, yellowCrystalMaxOres;

	public static int spawnChanceIllusionTree, spawnChanceJaweHut, spawnChanceFleshTemple, spawnChanceAbyssalLottoHut, spawnChanceShadowlordPlatform, spawnChanceWitherRuneShrine;
	public static int spawnChanceBaronessArena, spawnChanceHiveNest, spawnChanceBaronCastle, spawnChanceBaronessHouse, spawnChancePowerRuneShrine;
	public static int spawnChanceCottonCandyTower, spawnChanceGingerbreadHouse, spawnChanceGingerbirdAviary, spawnChanceCandyLottoPlatform, spawnChanceInfestedCandyCane;
	public static int spawnChanceGyroPlatform, spawnChanceCelevianLottoBalloon, spawnChanceToyTower, spawnChanceCompassRuneShrine;
	public static int spawnChanceCreeperHQ, spawnChanceCreeponianLottoStand, spawnChanceExplosivesTower, spawnChanceCreeponiaBank;
	public static int spawnChanceCrystalBuilding, spawnChanceCrystalTransferHut;
	public static int spawnChanceArocknidCave, spawnChanceChargingStation, spawnChanceKrorPillars, spawnChanceDeepLottoShelter;
	public static int spawnChancePrimordialShrine, spawnChanceCrusiliskCave, spawnChanceMerkyreTower, spawnChanceArkzyneOutpost, spawnChanceLottoCage, spawnChanceDustopianVillage;
	public static int spawnChanceFloroCastle, spawnChanceGardenCastle, spawnChanceLottoSkyFlower, spawnChanceWizardFlower, spawnChanceDayseeFlower, spawnChanceKineticRuneShrine;
	public static int spawnChanceHauntedMaze, spawnChanceHauntedLottoRock, spawnChanceFacelessTree, spawnChanceStormRuneShrine;
	public static int spawnChanceDawnlightDungeon, spawnChanceFloatingLottoFountain, spawnChanceGuardianTower, spawnChanceRockriderBoulder, spawnChanceStrikeRuneShrine;
	public static int spawnChanceIroMaze, spawnChanceChargingPads, spawnChanceProfessorsLab, spawnChanceIroPillar, spawnChanceIroPassage, spawnChanceEnergyRuneShrine;
	public static int spawnChanceAquaticCastle, spawnChanceDrownedLottoStand, spawnChanceDracyonFountain, spawnChanceHydroPlatform, spawnChanceWaterRuneShrine;
	public static int spawnChanceLelyetianTower, spawnChanceZhinxEnclave, spawnChanceParaviteHive, spawnChanceBoneyDungeon, spawnChanceGrawPillar;
	public static int spawnChanceLunarVillage, spawnChanceSpaceArena, spawnChanceLunarCreationPlatform, spawnChanceZargPlanetoid, spawnChanceLunarMaze, spawnChanceLunarPrison, spawnChanceLunarGarden, spawnChanceObserversEye, spawnChanceLunarRuneShrine;
	public static int spawnChanceHauntedCastle, spawnChanceFungshroom, spawnChanceGorbVillage, spawnChanceMysticLottoShroom, spawnChanceMushroomSpiderCave, spawnChanceMysticPortalPlatform, spawnChanceRunicArena, spawnChanceDistortionRuneShrine;
	public static int spawnChanceNethengeicPit, spawnChanceFireRuneShrine;
	public static int spawnChanceAmphibiyteCove, spawnChanceRuinedTeleporterFrame, spawnChanceWindRuneShrine;
	public static int spawnChanceKaiyuTemple, spawnChanceSkeletalArmyArena, spawnChanceTyrosaurStompingGround, spawnChanceJungleLottoHut, spawnChanceOpteryxNest, spawnChancePrecasianDen, spawnChanceLifeRuneShrine;
	public static int spawnChanceClunkheadArena, spawnChanceRunicTower, spawnChanceSpectralCage, spawnChanceRuneRandomisationStation, spawnChanceRuneTemplarBunker;
	public static int spawnChanceShyreDecoration, spawnChanceCraexxeusTower, spawnChanceShyreDungeon;
	public static int spawnChanceEnigmaStation, spawnChanceControlTower, spawnChanceCellTower, spawnChanceObservationTower, spawnChanceVoxBuilding, spawnChanceNightwingIsland, spawnChanceVoxLottoOutpost, spawnChancePoisonRuneShrine;

	public static boolean vanityLevels;
	public static boolean showXpParticles;
	public static String mainWindowTheme;
	public static boolean mainWindowPausesGame;

	public static boolean doVerboseDebugging;
	public static boolean showDailyMessages;
	public static boolean disableOverworldMobs;

	public static boolean leaderboardEnabled;
	public static int leaderboardSize;

	public static void init(final File configDir) {
		doLegacyCheck(configDir);

		(cfg = new Configuration(new File(configDir, "aoa3/main.cfg"))).load();
		(oreCfg = new Configuration(new File(configDir, "aoa3/ores.cfg"))).load();
		(structureCfg = new Configuration(new File(configDir, "aoa3/structures.cfg"))).load();

		generalConfigInit();
		oreConfigInit();
		structureConfigInit();
		cfg.save();
	}

	private static void generalConfigInit() {
		if (cfg.hasCategory("Dimension")) {
			handleLegacyDimensionIds(cfg.getCategory("Dimension"));
		}
		else {
			cfg.setCategoryRequiresMcRestart("DimensionIDs", true);
			cfg.setCategoryComment("DimensionIDs", "Registered dimension IDs. Change this if you get a dimension ID conflict crash. Changing this on an existing world can cause data loss or corruption");
			dimAbyss = cfg.get("DimensionIDs", "Abyss", 800).getInt();
			dimAncientCavern = cfg.get("DimensionIDs", "AncientCavern", 801).getInt();
			dimBarathos = cfg.get("DimensionIDs", "Barathos", 802).getInt();
			dimCandyland = cfg.get("DimensionIDs", "Candyland", 803).getInt();
			dimCeleve = cfg.get("DimensionIDs", "Celeve", 804).getInt();
			dimCreeponia = cfg.get("DimensionIDs", "Creeponia", 805).getInt();
			dimCrystevia = cfg.get("DimensionIDs", "Crystevia", 806).getInt();
			dimDeeplands = cfg.get("DimensionIDs", "Deeplands", 807).getInt();
			dimDustopia = cfg.get("DimensionIDs", "Dustopia", 808).getInt();
			dimGardencia = cfg.get("DimensionIDs", "Gardencia", 809).getInt();
			dimGreckon = cfg.get("DimensionIDs", "Greckon", 810).getInt();
			dimHaven = cfg.get("DimensionIDs", "Haven", 811).getInt();
			dimImmortallis = cfg.get("DimensionIDs", "Immortallis", 812).getInt();
			dimIromine = cfg.get("DimensionIDs", "Iromine", 813).getInt();
			dimLborean = cfg.get("DimensionIDs", "LBorean", 814).getInt();
			dimLelyetia = cfg.get("DimensionIDs", "Lelyetia", 815).getInt();
			dimLunalus = cfg.get("DimensionIDs", "Lunalus", 816).getInt();
			dimMysterium = cfg.get("DimensionIDs", "Mysterium", 817).getInt();
			dimPrecasia = cfg.get("DimensionIDs", "Precasia", 818).getInt();
			dimRunandor = cfg.get("DimensionIDs", "Runandor", 819).getInt();
			dimShyrelands = cfg.get("DimensionIDs", "Shyrelands", 820).getInt();
			dimVoxPonds = cfg.get("DimensionIDs", "VoxPonds", 821).getInt();
		}

		cfg.setCategoryComment("GuiPositioning", "X and Y pixel coordinates for resource hud elements. It is strongly recommended to leave as default");
		guiXResourceCreation = cfg.get("GuiPositioning", "CreationBarX", 50).getInt();
		guiYResourceCreation = cfg.get("GuiPositioning", "CreationBarY", 2).getInt();
		guiXResourceEnergy = cfg.get("GuiPositioning", "EnergyBarX", 75).getInt();
		guiYResourceEnergy = cfg.get("GuiPositioning", "EnergyBarY", 2).getInt();
		guiXResourceRage = cfg.get("GuiPositioning", "RageBarX", 125).getInt();
		guiYResourceRage = cfg.get("GuiPositioning", "RageBarY", 2).getInt();
		guiXResourceRevenge = cfg.get("GuiPositioning", "RevengeIconX", 150).getInt();
		guiYResourceRevenge = cfg.get("GuiPositioning", "RevengeIconY", 2).getInt();
		guiXResourceSoul = cfg.get("GuiPositioning", "SoulBarX", 25).getInt();
		guiYResourceSoul = cfg.get("GuiPositioning", "SoulBarY", 2).getInt();
		guiXTribute = cfg.get("GuiPositioning", "TributeBarX", 100).getInt();
		guiYTribute = cfg.get("GuiPositioning", "TributeBarY", 2).getInt();

		vanityLevels = cfg.getBoolean("ShowVanityLevels", "General", true, "Set this to false to limit your levels display to a max of level 100. Levels above this are for prestige only and do not affect your gameplay");
		showXpParticles = cfg.getBoolean("ShowXpParticles", "General", true, "Set this to false to disable the small scrolling popups that appear when you gain xp in a skill");
		doVerboseDebugging = cfg.getBoolean("DetailedDebugging", "General", false, "Set this to true to enable more detailed debugging. If you don't mind your logs having a bit more info, or you are trying to figure out a bug or crash, this can be very helpful to have enabled");
		showDailyMessages = cfg.getBoolean("ShowDailyMessages", "General", true, "Set this to false to disable the messages that appear at sunrise and sunset. This does not affect tribute or event messages");
		disableOverworldMobs = cfg.getBoolean("DisableOverworldMobSpawns", "General", false, "Set this to true to disable all overworld mob natural spawns. WARNING: This will make a lot of content inaccessible without further modifications. Use at your own risk!");
		mainWindowTheme = cfg.getString("MainWindowTheme", "General", "default", "The current theme for the main Advent of Ascension window.\nValid Options: Default, Jungle, Ancient_Ruins");
		mainWindowPausesGame = cfg.getBoolean("MainWindowPausesGame", "General", true, "Setting this to false allows the game to continue running as normal while the Advent of Ascension GUI is open.");

		cfg.setCategoryComment("Leaderboard", "Set config options for the AoA leaderboard feature");
		leaderboardEnabled = cfg.getBoolean("Enabled", "Leaderboard", true, "Set this to false to disable the leaderboard functionality. Doing so on an existing leaderboard may cause issues with data accuracy on re-enabling until the mod catches back up");
		leaderboardSize = cfg.getInt("Maximum Size", "Leaderboard", 100, 5, 9999, "The amount of players to store as ranked on the leaderboard. Lower amounts generally have slightly better performance");

		cfg.save();
	}

	private static void oreConfigInit() {
		oreCfg.setCategoryComment("Amethyst", "Set spawn rates for Amethyst ore");
		amethystMinOres = oreCfg.getInt("MinOresPerVein", "Amethyst", 5, 1, 20, "Minimum ore blocks per Amethyst vein");
		amethystMaxOres = oreCfg.getInt("MaxOresPerVein", "Amethyst", 9, amethystMinOres, 30, "Maximum ore blocks per Amethyst vein");
		amethystVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Amethyst", 5, 0, 20, "Maximum number of Amethyst veins per chunk");

		oreCfg.setCategoryComment("Baronyte", "Set spawn rates for Baronyte ore");
		baronyteMinOres = oreCfg.getInt("MinOresPerVein", "Baronyte", 4, 1, 20, "Minimum ore blocks per Baronyte vein");
		baronyteMaxOres = oreCfg.getInt("MaxOresPerVein", "Baronyte", 8, baronyteMinOres, 30, "Maximum ore blocks per Baronyte vein");
		baronyteVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Baronyte", 2, 0, 20, "Maximum number of Baronyte veins per chunk");

		oreCfg.setCategoryComment("Blazium", "Set spawn rates for Blazium ore");
		blaziumMinOres = oreCfg.getInt("MinOresPerVein", "Blazium", 4, 1, 20, "Minimum ore blocks per Blazium vein");
		blaziumMaxOres = oreCfg.getInt("MaxOresPerVein", "Blazium", 8, blaziumMinOres, 30, "Maximum ore blocks per Blazium vein");
		blaziumVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Blazium", 2, 0, 20, "Maximum number of Blazium veins per chunk");

		oreCfg.setCategoryComment("Bloodstone", "Set spawn rates for Bloodstone ore");
		bloodstoneMinOres = oreCfg.getInt("MinOresPerVein", "Bloodstone", 6, 1, 20, "Minimum ore blocks per Bloodstone vein");
		bloodstoneMaxOres = oreCfg.getInt("MaxOresPerVein", "Bloodstone", 12, bloodstoneMinOres, 30, "Maximum ore blocks per Bloodstone vein");
		bloodstoneVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Bloodstone", 4, 0, 20, "Maximum number of Bloodstone veins per chunk");

		oreCfg.setCategoryComment("BlueCrystal", "Set spawn rates for Blue Crystal ore");
		blueCrystalMinOres = oreCfg.getInt("MinOresPerVein", "BlueCrystal", 9, 1, 20, "Minimum ore blocks per Blue Crystal vein");
		blueCrystalMaxOres = oreCfg.getInt("MaxOresPerVein", "BlueCrystal", 15, blueCrystalMinOres, 30, "Maximum ore blocks per Blue Crystal vein");
		blueCrystalVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "BlueCrystal", 4, 0, 20, "Maximum number of Blue Crystal veins per chunk");

		oreCfg.setCategoryComment("ChargedRunium", "Set spawn rates for Charged Runium ore");
		chargedRuniumMinOres = oreCfg.getInt("MinOresPerVein", "ChargedRunium", 12, 1, 20, "Minimum ore blocks per Charged Runium vein");
		chargedRuniumMaxOres = oreCfg.getInt("MaxOresPerVein", "ChargedRunium", 18, chargedRuniumMinOres, 30, "Maximum ore blocks per Charged Runium vein");
		chargedRuniumVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "ChargedRunium", 5, 0, 20, "Maximum number of Charged Runium veins per chunk");

		oreCfg.setCategoryComment("ChestboneFragments", "Set spawn rates for Chestbone Fragments ore");
		chestboneFragmentsMinOres = oreCfg.getInt("MinOresPerVein", "ChestboneFragments", 2, 1, 20, "Minimum ore blocks per Chestbone Fragments vein");
		chestboneFragmentsMaxOres = oreCfg.getInt("MaxOresPerVein", "ChestboneFragments", 6, chestboneFragmentsMinOres, 30, "Maximum ore blocks per Chestbone Fragments vein");
		chestboneFragmentsVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "ChestboneFragments", 2, 0, 20, "Maximum number of Chestbone Fragments veins per chunk");

		oreCfg.setCategoryComment("Crystallite", "Set spawn rates for Crystallite ore");
		crystalliteMinOres = oreCfg.getInt("MinOresPerVein", "Crystallite", 8, 1, 20, "Minimum ore blocks per Crystallite vein");
		crystalliteMaxOres = oreCfg.getInt("MaxOresPerVein", "Crystallite", 12, crystalliteMinOres, 30, "Maximum ore blocks per Crystallite vein");
		crystalliteVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Crystallite", 8, 0, 20, "Maximum number of Crystallite veins per chunk");

		oreCfg.setCategoryComment("Elecanium", "Set spawn rates for Elecanium ore");
		elecaniumMinOres = oreCfg.getInt("MinOresPerVein", "Elecanium", 4, 1, 20, "Minimum ore blocks per Elecanium vein");
		elecaniumMaxOres = oreCfg.getInt("MaxOresPerVein", "Elecanium", 10, elecaniumMinOres, 30, "Maximum ore blocks per Elecanium vein");
		elecaniumVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Elecanium", 2, 0, 20, "Maximum number of Elecanium veins per chunk");

		oreCfg.setCategoryComment("Emberstone", "Set spawn rates for Emberstone ore");
		emberstoneMinOres = oreCfg.getInt("MinOresPerVein", "Emberstone", 5, 1, 20, "Minimum ore blocks per Emberstone vein");
		emberstoneMaxOres = oreCfg.getInt("MaxOresPerVein", "Emberstone", 8, emberstoneMinOres, 30, "Maximum ore blocks per Emberstone vein");
		emberstoneVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Emberstone", 4, 0, 20, "Maximum number of Emberstone veins per chunk");

		oreCfg.setCategoryComment("FootboneFragments", "Set spawn rates for Footbone Fragments ore");
		footboneFragmentsMinOres = oreCfg.getInt("MinOresPerVein", "FootboneFragments", 2, 1, 20, "Minimum ore blocks per Footbone Fragments vein");
		footboneFragmentsMaxOres = oreCfg.getInt("MaxOresPerVein", "FootboneFragments", 6, footboneFragmentsMinOres, 30, "Maximum ore blocks per Footbone Fragments vein");
		footboneFragmentsVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "FootboneFragments", 2, 0, 20, "Maximum number of Footbone Fragments veins per chunk");

		oreCfg.setCategoryComment("Gemenyte", "Set spawn rates for Gemenyte ore");
		gemenyteMinOres = oreCfg.getInt("MinOresPerVein", "Gemenyte", 2, 1, 20, "Minimum ore blocks per Gemenyte vein");
		gemenyteMaxOres = oreCfg.getInt("MaxOresPerVein", "Gemenyte", 6, gemenyteMinOres, 30, "Maximum ore blocks per Gemenyte vein");
		gemenyteVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Gemenyte", 4, 0, 20, "Maximum number of Gemenyte veins per chunk");

		oreCfg.setCategoryComment("Ghastly", "Set spawn rates for Ghastly ore");
		ghastlyMinOres = oreCfg.getInt("MinOresPerVein", "Ghastly", 3, 1, 20, "Minimum ore blocks per Ghastly ore vein");
		ghastlyMaxOres = oreCfg.getInt("MaxOresPerVein", "Ghastly", 7, ghastlyMinOres, 30, "Maximum ore blocks per Ghastly ore vein");
		ghastlyVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Ghastly", 2, 0, 20, "Maximum number of Ghastly ore veins per chunk");

		oreCfg.setCategoryComment("Ghoulish", "Set spawn rates for Ghoulish ore");
		ghoulishMinOres = oreCfg.getInt("MinOresPerVein", "Ghoulish", 3, 1, 20, "Minimum ore blocks per Ghoulish ore vein");
		ghoulishMaxOres = oreCfg.getInt("MaxOresPerVein", "Ghoulish", 7, ghoulishMinOres, 30, "Maximum ore blocks per Ghoulish ore vein");
		ghoulishVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Ghoulish", 2, 0, 20, "Maximum number of Ghoulish ore veins per chunk");

		oreCfg.setCategoryComment("GreenCrystal", "Set spawn rates for Green Crystal ore");
		greenCrystalMinOres = oreCfg.getInt("MinOresPerVein", "GreenCrystal", 9, 1, 20, "Minimum ore blocks per Green Crystal vein");
		greenCrystalMaxOres = oreCfg.getInt("MaxOresPerVein", "GreenCrystal", 15, greenCrystalMinOres, 30, "Maximum ore blocks per Green Crystal vein");
		greenCrystalVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "GreenCrystal", 4, 0, 20, "Maximum number of Green Crystal veins per chunk");

		oreCfg.setCategoryComment("Jade", "Set spawn rates for Jade ore");
		jadeMinOres = oreCfg.getInt("MinOresPerVein", "Jade", 2, 1, 20, "Minimum ore blocks per Jade vein");
		jadeMaxOres = oreCfg.getInt("MaxOresPerVein", "Jade", 6, jadeMinOres, 30, "Maximum ore blocks per Jade vein");
		jadeVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Jade", 3, 0, 20, "Maximum number of Jade veins per chunk");

		oreCfg.setCategoryComment("Jewelyte", "Set spawn rates for Jewelyte ore");
		jewelyteMinOres = oreCfg.getInt("MinOresPerVein", "Jewelyte", 2, 1, 20, "Minimum ore blocks per Jewelyte vein");
		jewelyteMaxOres = oreCfg.getInt("MaxOresPerVein", "Jewelyte", 6, jewelyteMinOres, 30, "Maximum ore blocks per Jewelyte vein");
		jewelyteVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Jewelyte", 4, 0, 20, "Maximum number of Jewelyte veins per chunk");

		oreCfg.setCategoryComment("LegboneFragments", "Set spawn rates for Legbone Fragments ore");
		legboneFragmentsMinOres = oreCfg.getInt("MinOresPerVein", "LegboneFragments", 2, 1, 20, "Minimum ore blocks per Legbone Fragments vein");
		legboneFragmentsMaxOres = oreCfg.getInt("MaxOresPerVein", "LegboneFragments", 6, legboneFragmentsMinOres, 30, "Maximum ore blocks per Legbone Fragments vein");
		legboneFragmentsVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "LegboneFragments", 2, 0, 20, "Maximum number of Legbone Fragments veins per chunk");

		oreCfg.setCategoryComment("Limonite", "Set spawn rates for Limonite ore");
		limoniteMinOres = oreCfg.getInt("MinOresPerVein", "Limonite", 10, 1, 20, "Minimum ore blocks per Limonite vein");
		limoniteMaxOres = oreCfg.getInt("MaxOresPerVein", "Limonite", 19, limoniteMinOres, 30, "Maximum ore blocks per Limonite vein");
		limoniteVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Limonite", 6, 0, 20, "Maximum number of Limonite veins per chunk");

		oreCfg.setCategoryComment("Lyon", "Set spawn rates for Lyon ore");
		lyonMinOres = oreCfg.getInt("MinOresPerVein", "Lyon", 3, 1, 20, "Minimum ore blocks per Lyon vein");
		lyonMaxOres = oreCfg.getInt("MaxOresPerVein", "Lyon", 7, lyonMinOres, 30, "Maximum ore blocks per Lyon vein");
		lyonVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Lyon", 2, 0, 20, "Maximum number of Lyon veins per chunk");

		oreCfg.setCategoryComment("Mystite", "Set spawn rates for Mystite ore");
		mystiteMinOres = oreCfg.getInt("MinOresPerVein", "Mystite", 5, 1, 20, "Minimum ore blocks per Mystite vein");
		mystiteMaxOres = oreCfg.getInt("MaxOresPerVein", "Mystite", 9, mystiteMinOres, 30, "Maximum ore blocks per Mystite vein");
		mystiteVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Mystite", 2, 0, 20, "Maximum number of Mystite veins per chunk");

		oreCfg.setCategoryComment("Ornamyte", "Set spawn rates for Ornamyte ore");
		ornamyteMinOres = oreCfg.getInt("MinOresPerVein", "Ornamyte", 2, 1, 20, "Minimum ore blocks per Ornamyte vein");
		ornamyteMaxOres = oreCfg.getInt("MaxOresPerVein", "Ornamyte", 6, ornamyteMinOres, 30, "Maximum ore blocks per Ornamyte vein");
		ornamyteVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Ornamyte", 4, 0, 20, "Maximum number of Ornamyte veins per chunk");

		oreCfg.setCategoryComment("PurpleCrystal", "Set spawn rates for Purple Crystal ore");
		purpleCrystalMinOres = oreCfg.getInt("MinOresPerVein", "PurpleCrystal", 9, 1, 20, "Minimum ore blocks per Purple Crystal vein");
		purpleCrystalMaxOres = oreCfg.getInt("MaxOresPerVein", "PurpleCrystal", 15, purpleCrystalMinOres, 30, "Maximum ore blocks per Purple Crystal vein");
		purpleCrystalVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "PurpleCrystal", 4, 0, 20, "Maximum number of Purple Crystal veins per chunk");

		oreCfg.setCategoryComment("RedCrystal", "Set spawn rates for Red Crystal ore");
		redCrystalMinOres = oreCfg.getInt("MinOresPerVein", "RedCrystal", 9, 1, 20, "Minimum ore blocks per Red Crystal vein");
		redCrystalMaxOres = oreCfg.getInt("MaxOresPerVein", "RedCrystal", 15, redCrystalMinOres, 30, "Maximum ore blocks per Red Crystal vein");
		redCrystalVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "RedCrystal", 4, 0, 20, "Maximum number of Red Crystal veins per chunk");

		oreCfg.setCategoryComment("Rosite", "Set spawn rates for Rosite ore");
		rositeMinOres = oreCfg.getInt("MinOresPerVein", "Rosite", 2, 1, 20, "Minimum ore blocks per Rosite vein");
		rositeMaxOres = oreCfg.getInt("MaxOresPerVein", "Rosite", 6, rositeMinOres, 30, "Maximum ore blocks per Rosite vein");
		rositeVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Rosite", 3, 0, 20, "Maximum number of Rosite veins per chunk");

		oreCfg.setCategoryComment("Runium", "Set spawn rates for Runium ore");
		runiumMinOres = oreCfg.getInt("MinOresPerVein", "Runium", 6, 1, 20, "Minimum ore blocks per Runium vein");
		runiumMaxOres = oreCfg.getInt("MaxOresPerVein", "Runium", 15, runiumMinOres, 30, "Maximum ore blocks per Runium vein");
		runiumVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Runium", 6, 0, 20, "Maximum number of Runium veins per chunk");

		oreCfg.setCategoryComment("Sapphire", "Set spawn rates for Sapphire ore");
		sapphireMinOres = oreCfg.getInt("MinOresPerVein", "Sapphire", 2, 1, 20, "Minimum ore blocks per Sapphire vein");
		sapphireMaxOres = oreCfg.getInt("MaxOresPerVein", "Sapphire", 6, sapphireMinOres, 30, "Maximum ore blocks per Sapphire vein");
		sapphireVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Sapphire", 3, 0, 20, "Maximum number of Sapphire veins per chunk");

		oreCfg.setCategoryComment("ShyreOre", "Set spawn rates for Shyre ores");
		shyreOreMinOres = oreCfg.getInt("MinOresPerChunk", "ShyreOre", 10, 0, 20, "Minimum shyre ore blocks per chunk");
		shyreOreMaxOres = oreCfg.getInt("MaxOresPerChunk", "ShyreOre", 19, shyreOreMinOres, 30, "Maximum shyre ore blocks per chunk");

		oreCfg.setCategoryComment("SkullboneFragments", "Set spawn rates for Skullbone Fragments ore");
		skullboneFragmentsMinOres = oreCfg.getInt("MinOresPerVein", "SkullboneFragments", 2, 1, 20, "Minimum ore blocks per Skullbone Fragments vein");
		skullboneFragmentsMaxOres = oreCfg.getInt("MaxOresPerVein", "SkullboneFragments", 6, skullboneFragmentsMinOres, 30, "Maximum ore blocks per Skullbone Fragments vein");
		skullboneFragmentsVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "SkullboneFragments", 2, 0, 20, "Maximum number of Skullbone Fragments veins per chunk");

		oreCfg.setCategoryComment("Varsium", "Set spawn rates for Varsium ore");
		varsiumMinOres = oreCfg.getInt("MinOresPerVein", "Varsium", 7, 1, 20, "Minimum ore blocks per Varsium vein");
		varsiumMaxOres = oreCfg.getInt("MaxOresPerVein", "Varsium", 13, varsiumMinOres, 30, "Maximum ore blocks per Varsium vein");
		varsiumVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "Varsium", 2, 0, 20, "Maximum number of Varsium veins per chunk");

		oreCfg.setCategoryComment("WhiteCrystal", "Set spawn rates for White Crystal ore");
		whiteCrystalMinOres = oreCfg.getInt("MinOresPerVein", "WhiteCrystal", 9, 1, 20, "Minimum ore blocks per White Crystal vein");
		whiteCrystalMaxOres = oreCfg.getInt("MaxOresPerVein", "WhiteCrystal", 15, whiteCrystalMinOres, 30, "Maximum ore blocks per White Crystal vein");
		whiteCrystalVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "WhiteCrystal", 4, 0, 20, "Maximum number of White Crystal veins per chunk");

		oreCfg.setCategoryComment("YellowCrystal", "Set spawn rates for YellowCrystal ore");
		yellowCrystalMinOres = oreCfg.getInt("MinOresPerVein", "YellowCrystal", 9, 1, 20, "Minimum ore blocks per Yellow Crystal vein");
		yellowCrystalMaxOres = oreCfg.getInt("MaxOresPerVein", "YellowCrystal", 15, yellowCrystalMinOres, 30, "Maximum ore blocks per Yellow Crystal vein");
		yellowCrystalVeinCount = oreCfg.getInt("MaxVeinsPerChunk", "YellowCrystal", 4, 0, 20, "Maximum number of Yellow Crystal veins per chunk");
		
		oreCfg.save();
	}

	private static void structureConfigInit() {
		structureCfg.setCategoryComment("Abyss", "Set custom spawn chances for Abyss structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceIllusionTree = structureCfg.getInt("IllusionTreeSpawnChance", "Abyss", 600, 0, 1000000, "Chance to try to spawn an Illusion Tree per chunk");
		spawnChanceJaweHut = structureCfg.getInt("JaweHutSpawnChance", "Abyss", 520, 0, 1000000, "Chance to try to spawn a Jawe Hut per chunk");
		spawnChanceFleshTemple = structureCfg.getInt("FleshTempleSpawnChance", "Abyss", 520, 0, 1000000, "Chance to try to spawn a Flesh Temple per chunk");
		spawnChanceAbyssalLottoHut = structureCfg.getInt("AbyssalLottoHutSpawnChance", "Abyss", 440, 0, 1000000, "Chance to try to spawn an Abyssal Lotto Hut per chunk");
		spawnChanceShadowlordPlatform = structureCfg.getInt("ShadowlordPlatformSpawnChance", "Abyss", 420, 0, 1000000, "Chance to try to spawn a Shadowlord Platform per chunk");
		spawnChanceWitherRuneShrine = structureCfg.getInt("WitherRuneShrineSpawnChance", "Abyss", 650, 0, 1000000, "Chance to try to spawn a Wither Rune Shrine per chunk");

		structureCfg.setCategoryComment("Barathos", "Set custom spawn chances for Barathos structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceBaronessArena = structureCfg.getInt("BaronessArenaSpawnChance", "Barathos", 1000, 0, 1000000, "Chance to try to spawn a Baroness Arena per chunk");
		spawnChanceHiveNest = structureCfg.getInt("HiveNestSpawnChance", "Barathos", 650, 0, 1000000, "Chance to try to spawn a Hive Nest per chunk");
		spawnChanceBaronCastle = structureCfg.getInt("BaronCastleSpawnChance", "Barathos", 650, 0, 1000000, "Chance to try to spawn a Baron Castle per chunk");
		spawnChanceBaronessHouse = structureCfg.getInt("BaronessHouseSpawnChance", "Barathos", 600, 0, 1000000, "Chance to try to spawn a Baroness House per chunk");
		spawnChancePowerRuneShrine = structureCfg.getInt("PowerRuneShrineSpawnChance", "Barathos", 650, 0, 1000000, "Chance to try to spawn a Power Rune Shrine per chunk");

		structureCfg.setCategoryComment("Candyland", "Set custom spawn chances for Candyland structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk");
		spawnChanceCottonCandyTower = structureCfg.getInt("CottonCandyTowerSpawnChance", "Candyland", 850, 0, 1000000, "Chance to try to spawn a Cotton Candy Tower per chunk");
		spawnChanceGingerbreadHouse = structureCfg.getInt("GingerbreadHouseSpawnChance", "Candyland", 740, 0, 1000000, "Chance to try to spawn a Gingerbread House per chunk");
		spawnChanceGingerbirdAviary = structureCfg.getInt("GingerbirdAviarySpawnChance", "Candyland", 700, 0, 1000000, "Chance to try to spawn a Gingerbird Aviary per chunk");
		spawnChanceCandyLottoPlatform = structureCfg.getInt("CandyLottoPlatformSpawnChance", "Candyland", 600, 0, 1000000, "Chance to try to spawn a Candy Lotto Platform per chunk");
		spawnChanceInfestedCandyCane = structureCfg.getInt("InfestedCandyCaneSpawnChance", "Candyland", 420, 0, 1000000, "Chance to try to spawn an Infested Candy Cane per chunk");

		structureCfg.setCategoryComment("Celeve", "Set custom spawn chances for Celeve structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceGyroPlatform = structureCfg.getInt("GyroPlatformSpawnChance", "Celeve", 980, 0, 1000000, "Chance to try to spawn a Gyro Platform per chunk");
		spawnChanceCelevianLottoBalloon = structureCfg.getInt("CelevianLotto BalloonSpawnChance", "Celeve", 650, 0, 1000000, "Chance to try to spawn a Celevian Lotto Balloon per chunk");
		spawnChanceToyTower = structureCfg.getInt("ToyTowerSpawnChance", "Celeve", 600, 0, 1000000, "Chance to try to spawn a Toy Tower per chunk");
		spawnChanceCompassRuneShrine = structureCfg.getInt("CompassRuneShrineSpawnChance", "Celeve", 450, 0, 1000000, "Chance to try to spawn a Compass Rune Shrine per chunk");

		structureCfg.setCategoryComment("Creeponia", "Set custom spawn chances for Creeponia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk");
		spawnChanceCreeperHQ = structureCfg.getInt("CreeperHQSpawnChance", "Creeponia", 700, 0, 1000000, "Chance to try to spawn a Creeper HQ per chunk");
		spawnChanceCreeponianLottoStand = structureCfg.getInt("CreeponianLottoStandSpawnChance", "Creeponia", 300, 0, 1000000, "Chance to try to spawn a Creeponian Lotto Stand per chunk");
		spawnChanceExplosivesTower = structureCfg.getInt("ExplosivesTowerSpawnChance", "Creeponia", 300, 0, 1000000, "Chance to try to spawn an Explosives Tower per chunk");
		spawnChanceCreeponiaBank = structureCfg.getInt("CreeponiaBankSpawnChance", "Creeponia", 300, 0, 1000000, "Chance to try to spawn a Creeponia Bank per chunk");

		structureCfg.setCategoryComment("Crystevia", "Set custom spawn chances for Crystevia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely");
		spawnChanceCrystalBuilding = structureCfg.getInt("CrystalBuildingSpawnChance", "Crystevia", 400, 0, 1000000, "Chance to try to spawn a Crystal Building per chunk");
		spawnChanceCrystalTransferHut = structureCfg.getInt("CrystalTransferHutSpawnChance", "Crystevia", 45, 0, 1000000, "Chance to try to spawn a Crystal Transfer Hut per chunk");

		structureCfg.setCategoryComment("Deeplands", "Set custom spawn chances for Deeplands structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk");
		spawnChanceArocknidCave = structureCfg.getInt("ArocknidCaveSpawnChance", "Deeplands", 700, 0, 1000000, "Chance to try to spawn an Arocknid Cave per chunk");
		spawnChanceChargingStation = structureCfg.getInt("ChargingStationSpawnChance", "Deeplands", 635, 0, 1000000, "Chance to try to spawn a Charging Station per chunk");
		spawnChanceKrorPillars = structureCfg.getInt("KrorPillarsSpawnChance", "Deeplands", 635, 0, 1000000, "Chance to try to spawn Kror Pillars per chunk");
		spawnChanceDeepLottoShelter = structureCfg.getInt("DeepLottoShelterSpawnChance", "Deeplands", 635, 0, 1000000, "Chance to try to spawn a Deep Lotto Shelter per chunk");

		structureCfg.setCategoryComment("Dustopia", "Set custom spawn chances for Dustopia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk");
		spawnChancePrimordialShrine = structureCfg.getInt("PrimordialShrineSpawnChance", "Dustopia", 350, 0, 1000000, "Chance to try to spawn a Primordial Shrine per chunk");
		spawnChanceCrusiliskCave = structureCfg.getInt("CrusiliskCaveSpawnChance", "Dustopia", 305, 0, 1000000, "Chance to try to spawn a Crusilisk Cave per chunk");
		spawnChanceMerkyreTower = structureCfg.getInt("MerkyreTowerSpawnChance", "Dustopia", 300, 0, 1000000, "Chance to try to spawn a Merkyre Tower per chunk");
		spawnChanceArkzyneOutpost = structureCfg.getInt("ArkzyneOutpostSpawnChance", "Dustopia", 295, 0, 1000000, "Chance to try to spawn an Arkzyne Outpost per chunk");
		spawnChanceLottoCage = structureCfg.getInt("LottoCageSpawnChance", "Dustopia", 200, 0, 1000000, "Chance to try to spawn a Lotto Cage per chunk");
		spawnChanceDustopianVillage = structureCfg.getInt("DustopianVillageSpawnChance", "Dustopia", 200, 0, 1000000, "Chance to try to spawn a Dustopian Village per chunk");

		structureCfg.setCategoryComment("Gardencia", "Set custom spawn chances for Gardencia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceFloroCastle = structureCfg.getInt("FloroCastleSpawnChance", "Gardencia", 1700, 0, 1000000, "Chance to try to spawn a Floro Castle per chunk");
		spawnChanceGardenCastle = structureCfg.getInt("GardenCastleSpawnChance", "Gardencia", 1650, 0, 1000000, "Chance to try to spawn a Garden Castle per chunk");
		spawnChanceLottoSkyFlower = structureCfg.getInt("LottoSkyFlowerSpawnChance", "Gardencia", 500, 0, 1000000, "Chance to try to spawn a Lotto Sky Flower per chunk");
		spawnChanceWizardFlower = structureCfg.getInt("WizardFlowerSpawnChance", "Gardencia", 300, 0, 1000000, "Chance to try to spawn a Wizard Flower per chunk");
		spawnChanceDayseeFlower = structureCfg.getInt("DayseeFlowerSpawnChance", "Gardencia", 300, 0, 1000000, "Chance to try to spawn a Daysee Flower per chunk");
		spawnChanceKineticRuneShrine = structureCfg.getInt("KineticRuneShrineSpawnChance", "Gardencia", 650, 0, 1000000, "Chance to try and spawn a Kinetic Rune Shrine per chunk");

		structureCfg.setCategoryComment("Greckon", "Set custom spawn chances for Greckon structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceHauntedMaze = structureCfg.getInt("HauntedMazeSpawnChance", "Greckon", 700, 0, 1000000, "Chance to try to spawn a Haunted Maze per chunk");
		spawnChanceHauntedLottoRock = structureCfg.getInt("HauntedLottoRockSpawnChance", "Greckon", 500, 0, 1000000, "Chance to try to spawn a Haunted Lotto Rock per chunk");
		spawnChanceFacelessTree = structureCfg.getInt("FacelessTreeSpawnChance", "Greckon", 180, 0, 1000000, "Chance to try to spawn a Faceless Tree per chunk");
		spawnChanceStormRuneShrine = structureCfg.getInt("StormRuneShrineSpawnChance", "Greckon", 650, 0, 1000000, "Chance to try to spawn a Storm Rune Shrine per chunk");

		structureCfg.setCategoryComment("Haven", "Set custom spawn chances for Haven structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceDawnlightDungeon = structureCfg.getInt("DawnlightDungeonSpawnChance", "Haven", 650, 0, 1000000, "Chance to try to spawn a Dawnlight Dungeon per chunk");
		spawnChanceFloatingLottoFountain = structureCfg.getInt("FloatingLottoFountainSpawnChance", "Haven", 650, 0, 1000000, "Chance to try to spawn a Floating Lotto Fountain per chunk");
		spawnChanceGuardianTower = structureCfg.getInt("GuardianTowerSpawnChance", "Haven", 35, 0, 1000000, "Chance to try to spawn a Guardian Tower per chunk");
		spawnChanceRockriderBoulder = structureCfg.getInt("RockriderBoulderSpawnChance", "Haven", 17, 0, 1000000, "Chance to try to spawn a Rockrider Boulder per chunk");
		spawnChanceStrikeRuneShrine = structureCfg.getInt("StrikeRuneShrineSpawnChance", "Haven", 650, 0, 1000000, "Chance to try to spawn a Strike Rune Shrine per chunk");

		structureCfg.setCategoryComment("Iromine", "Set custom spawn chances for Iromine structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceIroMaze = structureCfg.getInt("IroMazeSpawnChance", "Iromine", 1300, 0, 1000000, "Chance to try to spawn an Iro Maze per chunk");
		spawnChanceChargingPads = structureCfg.getInt("ChargingPadsSpawnChance", "Iromine", 1300, 0, 1000000, "Chance to try to spawn Charging Pads per chunk");
		spawnChanceProfessorsLab = structureCfg.getInt("ProfessorsLabSpawnChance", "Iromine", 600, 0, 1000000, "Chance to try to spawn a Professors Lab per chunk");
		spawnChanceIroPillar = structureCfg.getInt("IroPillarSpawnChance", "Iromine", 600, 0, 1000000, "Chance to try to spawn an Iro Pillar per chunk");
		spawnChanceIroPassage = structureCfg.getInt("IroPassageSpawnChance", "Iromine", 25, 0, 1000000, "Chance to try to spawn an Iro Passage per chunk");
		spawnChanceEnergyRuneShrine = structureCfg.getInt("EnergyRuneShrineSpawnChance", "Iromine", 650, 0, 1000000, "Chance to try to spawn an Energy Rune Shrine per chunk");

		structureCfg.setCategoryComment("L'Borean", "Set custom spawn chances for L'Borean structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceAquaticCastle = structureCfg.getInt("AquaticCastleSpawnChance", "L'Borean", 1700, 0, 1000000, "Chance to try to spawn an Aquatic Castle per chunk");
		spawnChanceDrownedLottoStand = structureCfg.getInt("DrownedLottoStandSpawnChance", "L'Borean", 550, 0, 1000000, "Chance to try to spawn a Drowned Lotto Stand per chunk");
		spawnChanceDracyonFountain = structureCfg.getInt("DracyonFountainSpawnChance", "L'Borean", 450, 0, 1000000, "Chance to try to spawn a Dracyon Fountain per chunk");
		spawnChanceHydroPlatform = structureCfg.getInt("HydroPlatformSpawnChance", "L'Borean", 300, 0, 1000000, "Chance to try to spawn a Hydro Platform per chunk");
		spawnChanceWaterRuneShrine = structureCfg.getInt("WaterRuneShrineSpawnChance", "L'Borean", 650, 0, 1000000, "Chance to try to spawn a Water Rune Shrine per chunk");

		structureCfg.setCategoryComment("Lelyetia", "Set custom spawn chances for Lelyetia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk");
		spawnChanceLelyetianTower = structureCfg.getInt("LelyetianTowerSpawnChance", "Lelyetia", 760, 0, 1000000, "Chance to try to spawn a Lelyetian Tower per chunk");
		spawnChanceZhinxEnclave = structureCfg.getInt("ZhinxEnclaveSpawnChance", "Lelyetia", 380, 0, 1000000, "Chance to try to spawn a Zhinx Enclave per chunk");
		spawnChanceParaviteHive = structureCfg.getInt("ParaviteHiveSpawnChance", "Lelyetia", 380, 0, 1000000, "Chance to try to spawn a Paravite Hive per chunk");
		spawnChanceBoneyDungeon = structureCfg.getInt("BoneyDungeonSpawnChance", "Lelyetia", 380, 0, 1000000, "Chance to try to spawn a Boney Dungeon per chunk");
		spawnChanceGrawPillar = structureCfg.getInt("GrawPillarSpawnChance", "Lelyetia", 166, 0, 1000000, "Chance to try to spawn a Graw Pillar per chunk");

		structureCfg.setCategoryComment("Lunalus", "Set custom spawn chances for Lunalus structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceLunarVillage = structureCfg.getInt("LunarVillageSpawnChance", "Lunalus", 1500, 0, 1000000, "Chance to try to spawn a Lunar Village per chunk");
		spawnChanceSpaceArena = structureCfg.getInt("SpaceArenaSpawnChance", "Lunalus", 905, 0, 1000000, "Chance to try to spawn a Space Arena per chunk");
		spawnChanceLunarCreationPlatform = structureCfg.getInt("LunarCreationPlatformSpawnChance", "Lunalus", 905, 0, 1000000, "Chance to try to spawn a Lunar Creation Platform per chunk");
		spawnChanceZargPlanetoid = structureCfg.getInt("ZargPlanetoidSpawnChance", "Lunalus", 900, 0, 1000000, "Chance to try to spawn a Zarg Planetoid per chunk");
		spawnChanceLunarMaze = structureCfg.getInt("LunarMazeSpawnChance", "Lunalus", 900, 0, 1000000, "Chance to try to spawn a Lunar Maze per chunk");
		spawnChanceLunarPrison = structureCfg.getInt("LunarPrisonSpawnChance", "Lunalus", 850, 0, 1000000, "Chance to try to spawn a Lunar Prison per chunk");
		spawnChanceLunarGarden = structureCfg.getInt("LunarGardenSpawnChance", "Lunalus", 804, 0, 1000000, "Chance to try to spawn a Lunar Garden per chunk");
		spawnChanceObserversEye = structureCfg.getInt("ObserversEyeSpawnChance", "Lunalus", 375, 0, 1000000, "Chance to try to spawn an Observers Eye per chunk");
		spawnChanceLunarRuneShrine = structureCfg.getInt("LunarRuneShrineSpawnChance", "Lunalus", 600, 0, 1000000, "Chance to try to spawn a Lunar Rune Shrine per chunk");

		structureCfg.setCategoryComment("Mysterium", "Set custom spawn chances for Mysterium structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceHauntedCastle = structureCfg.getInt("HauntedCastleSpawnChance", "Mysterium", 850, 0, 1000000, "Chance to try to spawn a Haunted Castle per chunk");
		spawnChanceFungshroom = structureCfg.getInt("FungshroomSpawnChance", "Mysterium", 800, 0, 1000000, "Chance to try to spawn a Fungshroom per chunk");
		spawnChanceGorbVillage = structureCfg.getInt("GorbVillageSpawnChance", "Mysterium", 650, 0, 1000000, "Chance to try to spawn a Gorb Village per chunk");
		spawnChanceMysticLottoShroom = structureCfg.getInt("MysticLottoShroomSpawnChance", "Mysterium", 500, 0, 1000000, "Chance to try to spawn a Mystic Lotto Shroom per chunk");
		spawnChanceMushroomSpiderCave = structureCfg.getInt("MushroomSpiderCaveSpawnChance", "Mysterium", 450, 0, 1000000, "Chance to try to spawn a Mushroom Spider Cave per chunk");
		spawnChanceMysticPortalPlatform = structureCfg.getInt("MysticPortalPlatformSpawnChance", "Mysterium", 416, 0, 1000000, "Chance to try to spawn a Mystic Portal Platform per chunk");
		spawnChanceRunicArena = structureCfg.getInt("RunicArenaSpawnChance", "Mysterium", 315, 0, 1000000, "Chance to try to spawn a Runic Arena per chunk");
		spawnChanceDistortionRuneShrine = structureCfg.getInt("DistortionRuneShrineSpawnChance", "Mysterium", 500, 0, 1000000, "Chance to try to spawn a Distortion Rune Shrine per chunk");

		structureCfg.setCategoryComment("Nether", "Set custom spawn chances for Nether structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely");
		spawnChanceNethengeicPit = structureCfg.getInt("NethengeicPitSpawnChance", "Nether", 200, 0, 1000000, "Chance to try to spawn a Nethengeic Pit per chunk");
		spawnChanceFireRuneShrine = structureCfg.getInt("FireRuneShrineSpawnChance", "Nether", 100, 0, 1000000, "Chance to try to spawn a Fire Rune Shrine per chunk");

		structureCfg.setCategoryComment("Overworld", "Set custom spawn chances for Overworld structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely");
		spawnChanceAmphibiyteCove = structureCfg.getInt("AmphibiyteCoveSpawnChance", "Overworld", 20, 0, 1000000, "Chance to try to spawn an Amphibiyte Cove per chunk");
		spawnChanceRuinedTeleporterFrame = structureCfg.getInt("RuinedTeleporterFrameSpawnChance", "Overworld", 120, 0, 1000000, "Chance to try to spawn a Ruined Teleporter Frame per chunk");
		spawnChanceWindRuneShrine = structureCfg.getInt("WindRuneShrineSpawnChance", "Overworld", 150, 0, 1000000, "Chance to try to spawn a Wind Rune Shrine per chunk");

		structureCfg.setCategoryComment("Precasia", "Set custom spawn chances for Precasia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines and precasian dens");
		spawnChanceKaiyuTemple = structureCfg.getInt("KaiyuTempleSpawnChance", "Precasia", 1500, 0, 1000000, "Chance to try to spawn a Kaiyu Temple per chunk");
		spawnChanceSkeletalArmyArena = structureCfg.getInt("SkeletalArmyArenaSpawnChance", "Precasia", 1000, 0, 1000000, "Chance to try to spawn a Skeletal Army Arena per chunk");
		spawnChanceTyrosaurStompingGround = structureCfg.getInt("TyrosaurStompingGroundSpawnChance", "Precasia", 800, 0, 1000000, "Chance to try to spawn a Tyrosaur Stomping Ground per chunk");
		spawnChanceJungleLottoHut = structureCfg.getInt("JungleLottoHutSpawnChance", "Precasia", 420, 0, 1000000, "Chance to try to spawn a Jungle Lotto Hut per chunk");
		spawnChanceOpteryxNest = structureCfg.getInt("OpteryxNestSpawnChance", "Precasia", 300, 0, 1000000, "Chance to try to spawn an Opteryx Nest per chunk");
		spawnChancePrecasianDen = structureCfg.getInt("PrecasianDenSpawnChance", "Precasia", 600, 0, 1000000, "Chance to try to spawn a Precasian Den per chunk");
		spawnChanceLifeRuneShrine = structureCfg.getInt("LifeRuneShrineSpawnChance", "Precasia", 650, 0, 1000000, "Chance to try to spawn a Life Rune Shrine per chunk");

		structureCfg.setCategoryComment("Runandor", "Set custom spawn chances for Runandor structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk");
		spawnChanceClunkheadArena = structureCfg.getInt("ClunkheadArenaSpawnChance", "Runandor", 800, 0, 1000000, "Chance to try to spawn a Clunkhead Arena per chunk");
		spawnChanceRunicTower = structureCfg.getInt("RunicTowerSpawnChance", "Runandor", 650, 0, 1000000, "Chance to try to spawn a Runic Tower per chunk");
		spawnChanceSpectralCage = structureCfg.getInt("SpectralCageSpawnChance", "Runandor", 500, 0, 1000000, "Chance to try to spawn a Spectral Cage per chunk");
		spawnChanceRuneRandomisationStation = structureCfg.getInt("RuneRandomisation StationSpawnChance", "Runandor", 315, 0, 1000000, "Chance to try to spawn a Rune Randomisation Station per chunk");
		spawnChanceRuneTemplarBunker = structureCfg.getInt("RuneTemplarBunkerSpawnChance", "Runandor", 300, 0, 1000000, "Chance to try to spawn a Rune Templar Bunker per chunk");

		structureCfg.setCategoryComment("Shyrelands", "Set custom spawn chances for Shyrelands structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely");
		spawnChanceShyreDecoration = structureCfg.getInt("ShyreDecorationSpawnChance", "Shyrelands", 25, 0, 1000000, "Chance to try to spawn a Shyre Decoration per chunk");
		spawnChanceCraexxeusTower = structureCfg.getInt("CraexxeusTowerSpawnChance", "Shyrelands", 600, 0, 1000000, "Chance to try to spawn a Craexxeus Tower per chunk");
		spawnChanceShyreDungeon = structureCfg.getInt("ShyreDungeonSpawnChance", "Shyrelands", 250, 0, 1000000, "Chance to try to spawn a Shyre Dungeon per chunk");

		structureCfg.setCategoryComment("VoxPonds", "Set custom spawn chances for VoxPonds structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines");
		spawnChanceEnigmaStation = structureCfg.getInt("EnigmaStationSpawnChance", "VoxPonds", 866, 0, 1000000, "Chance to try to spawn an Enigma Station per chunk");
		spawnChanceControlTower = structureCfg.getInt("ControlTowerSpawnChance", "VoxPonds", 506, 0, 1000000, "Chance to try to spawn a Control Tower per chunk");
		spawnChanceCellTower = structureCfg.getInt("CellTowerSpawnChance", "VoxPonds", 506, 0, 1000000, "Chance to try to spawn a Cell Tower per chunk");
		spawnChanceObservationTower = structureCfg.getInt("ObservationTowerSpawnChance", "VoxPonds", 506, 0, 1000000, "Chance to try to spawn an Observation Tower per chunk");
		spawnChanceVoxBuilding = structureCfg.getInt("VoxBuildingSpawnChance", "VoxPonds", 130, 0, 1000000, "Chance to try to spawn a Vox Building per chunk");
		spawnChanceNightwingIsland = structureCfg.getInt("NightwingIslandSpawnChance", "VoxPonds", 97, 0, 1000000, "Chance to try to spawn a Nightwing Island per chunk");
		spawnChanceVoxLottoOutpost = structureCfg.getInt("VoxLottoOutpostSpawnChance", "VoxPonds", 83, 0, 1000000, "Chance to try to spawn a Vox Lotto Outpost per chunk");
		spawnChancePoisonRuneShrine = structureCfg.getInt("PoisonRuneShrineSpawnChance", "VoxPonds", 600, 0, 1000000, "Chance to try to spawn a Poison Rune Shrine per chunk");

		structureCfg.save();
	}

	private static void doLegacyCheck(File configDir) {
		File oldFile = new File(configDir, "aoa3.cfg");

		if (oldFile.exists())
			oldFile.renameTo(new File(configDir, "aoa3/main.cfg"));
	}

	private static void handleLegacyDimensionIds(ConfigCategory category) {
		cfg.setCategoryRequiresMcRestart("DimensionIDs", true);
		cfg.setCategoryComment("DimensionIDs", "Registered dimension IDs. Change this if you get a dimension ID conflict crash. Changing this on an existing world can cause data loss or corruption");
		dimAbyss = cfg.get("DimensionIDs", "Abyss", cfg.get("Dimension", "Abyss ID", 800).getInt()).getInt();
		dimAncientCavern = cfg.get("DimensionIDs", "AncientCavern", cfg.get("Dimension", "Ancient Cavern ID", 801).getInt()).getInt();
		dimBarathos = cfg.get("DimensionIDs", "Barathos", cfg.get("Dimension", "Barathos ID", 802).getInt()).getInt();
		dimCandyland = cfg.get("DimensionIDs", "Candyland", cfg.get("Dimension", "Candyland ID", 803).getInt()).getInt();
		dimCeleve = cfg.get("DimensionIDs", "Celeve", cfg.get("Dimension", "Celeve ID", 804).getInt()).getInt();
		dimCreeponia = cfg.get("DimensionIDs", "Creeponia", cfg.get("Dimension", "Creeponia ID", 805).getInt()).getInt();
		dimCrystevia = cfg.get("DimensionIDs", "Crystevia", cfg.get("Dimension", "Crystevia ID", 806).getInt()).getInt();
		dimDeeplands = cfg.get("DimensionIDs", "Deeplands", cfg.get("Dimension", "Deeplands ID", 807).getInt()).getInt();
		dimDustopia = cfg.get("DimensionIDs", "Dustopia", cfg.get("Dimension", "Dustopia ID", 808).getInt()).getInt();
		dimGardencia = cfg.get("DimensionIDs", "Gardencia", cfg.get("Dimension", "Gardencia ID", 809).getInt()).getInt();
		dimGreckon = cfg.get("DimensionIDs", "Greckon", cfg.get("Dimension", "Greckon ID", 810).getInt()).getInt();
		dimHaven = cfg.get("DimensionIDs", "Haven", cfg.get("Dimension", "Haven ID", 811).getInt()).getInt();
		dimImmortallis = cfg.get("DimensionIDs", "Immortallis", cfg.get("Dimension", "Immortallis ID", 812).getInt()).getInt();
		dimIromine = cfg.get("DimensionIDs", "Iromine", cfg.get("Dimension", "Iromine ID", 813).getInt()).getInt();
		dimLborean = cfg.get("DimensionIDs", "L'Borean", cfg.get("Dimension", "Lborean ID", 814).getInt()).getInt();
		dimLelyetia = cfg.get("DimensionIDs", "Lelyetia", cfg.get("Dimension", "Lelyetia ID", 815).getInt()).getInt();
		dimLunalus = cfg.get("DimensionIDs", "Lunalus", cfg.get("Dimension", "Lunalus ID", 816).getInt()).getInt();
		dimMysterium = cfg.get("DimensionIDs", "Mysterium", cfg.get("Dimension", "Mysterium ID", 817).getInt()).getInt();
		dimPrecasia = cfg.get("DimensionIDs", "Precasia", cfg.get("Dimension", "PRecasia ID", 818).getInt()).getInt();
		dimRunandor = cfg.get("DimensionIDs", "Runandor", cfg.get("Dimension", "Runandor ID", 819).getInt()).getInt();
		dimShyrelands = cfg.get("DimensionIDs", "Shyrelands", cfg.get("Dimension", "Shyrelands ID", 820).getInt()).getInt();
		dimVoxPonds = cfg.get("DimensionIDs", "VoxPonds", cfg.get("Dimension", "Vox Ponds ID", 821).getInt()).getInt();

		cfg.removeCategory(category);
	}

	public static void changeMainWindowTheme(String newTheme) {
		mainWindowTheme = newTheme;

		cfg.getCategory("General").getValues().get("MainWindowTheme").setValue(newTheme);
		cfg.save();
	}
}
