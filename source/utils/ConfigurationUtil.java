package net.tslat.aoa3.utils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.tslat.aoa3.client.gui.render.ResourcesRenderer;
import net.tslat.aoa3.library.Enums;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class ConfigurationUtil {
	@Config(modid = "aoa3", type = Config.Type.INSTANCE, name = "aoa3/main_config")
	@Config.LangKey("gui.aoaconfig.title")
	public static class MainConfig {
		@Config.Comment("Set this to false to limit your levels display to a max of level 100. Levels above this are for prestige only and do not affect your gameplay")
		@Config.LangKey("gui.aoaconfig.showVanityLevels")
		public static boolean showVanityLevels = true;

		@Config.Comment("Set this to true to enable more detailed debugging. If you don't mind your logs having a bit more info, or you are trying to figure out a bug or crash, this can be very helpful to have")
		@Config.LangKey("gui.aoaconfig.doVerboseDebugging")
		public static boolean doVerboseDebugging = false;

		@Config.Comment("Set this to false to disable the small scrolling popups that appear when you gain xp in a skill")
		@Config.LangKey("gui.aoaconfig.showXpParticles")
		public static boolean showXpParticles = true;

		@Config.Comment("Set this to false to disable the messages that appear at sunrise and sunset. This does not affect tribute or event messages")
		@Config.LangKey("gui.aoaconfig.showDailyMessages")
		public static boolean showDailyMessages = true;

		@Config.Comment("Set this to false to disable the message that welcomes you to Advent of Ascension when logging in")
		@Config.LangKey("gui.aoaconfig.showWelcomeMessage")
		public static boolean showWelcomeMessage = true;

		@Config.Comment("Set this to false to hide player halos for those that have them")
		@Config.LangKey("gui.aoaconfig.showPlayerHalos")
		public static boolean showPlayerHalos = true;

		@Config.Comment("If multiple halos are available, which one would you prefer to have on?")
		@Config.LangKey("gui.aoaconfig.personalHaloPreference")
		public static Enums.PlayerHaloTypes.ChoosableHaloTypes personalHaloPreference = Enums.PlayerHaloTypes.ChoosableHaloTypes.Super_Donator;

		@Config.Comment("Set this to true to disable all overworld natural spawns. WARNING: This will make a lot of content inaccessible without further modifications. Use at your own risk!")
		@Config.LangKey("gui.aoaconfig.disableOverworldMobs")
		@Config.RequiresMcRestart
		public static boolean disableOverworldMobs = false;

		@Config.Comment("Set this to false to disable AoA's skills functionality.")
		@Config.LangKey("gui.aoaconfig.enableSkills")
		@Config.RequiresMcRestart
		public static boolean skillsEnabled = true;

		@Config.Comment("Set this to false to disable AoA's resources functionality.")
		@Config.LangKey("gui.aoaconfig.enableResources")
		@Config.RequiresMcRestart
		public static boolean resourcesEnabled = true;

		@Config.Comment("The current theme for the main Advent of Ascension window.")
		@Config.LangKey("gui.aoaconfig.mainWindowTheme")
		public static String mainWindowTheme = "Default";

		@Config.Comment("Setting this to false allows the game to continue running as normal while the Advent of Ascension GUI is open.")
		@Config.LangKey("gui.aoaconfig.mainWindowPausesGame")
		public static boolean mainWindowPausesGame = true;

		@Config.Comment("Set this to false to disable third party and vanilla entities from showing in the bestiary")
		@Config.LangKey("gui.aoaconfig.thirdPartyBestiary")
		public static boolean thirdPartyBestiary = true;

		@Config.Comment("Choose a corner to have your resources indicators render in.")
		@Config.LangKey("gui.aoaconfig.hudResourcesPosition")
		public static ResourcesRenderer.HudResourcesPosition hudResourcesPosition = ResourcesRenderer.HudResourcesPosition.Top_Right;

		@Config.Comment("Set this to false to switch to a vertical layout for the HUD resources")
		@Config.LangKey("gui.aoaconfig.hudResourcesHorizontal")
		public static boolean hudResourcesHorizontal = true;

		@Config.Comment("Set this to false to use chat messages for mod feedback instead of toasts")
		@Config.LangKey("gui.aoaconfig.useToasts")
		public static boolean useToasts = true;

		@Config.Comment("Set this to false to disable enchantments that breach level caps")
		@Config.LangKey("gui.aoaconfig.allowUnsafeInfusion")
		public static boolean allowUnsafeInfusion = true;

		@Config.Comment("Adjust this value to modify how far to look for safe/existing portal locations. The lower the value, the faster it runs")
		@Config.LangKey("gui.aoaconfig.portalSearchRadius")
		public static int portalSearchRadius = 24;

		@Config.RequiresMcRestart
		@Config.Comment("Set this to true to enable the leaderboard functionality. Doing so on an existing leaderboard may cause issues with data accuracy on re-enabling until the mod catches back up")
		@Config.LangKey("gui.aoaconfig.leaderboardEnabled")
		public static boolean leaderboardEnabled = false;

		@Config.RequiresWorldRestart
		@Config.Comment("Set this to true to make Corrupted Travellers easier to find.")
		@Config.LangKey("gui.aoaconfig.easyCorruptedTravellers")
		public static boolean easyCorruptedTravellers = false;

		@Config.RequiresMcRestart
		@Config.Comment("The amount of players to store as ranked on the leaderboard. Lower amounts generally have slightly better performance")
		@Config.LangKey("gui.aoaconfig.leaderboardCapacity")
		@Config.RangeInt(min = 5, max = 9999)
		public static int leaderboardCapacity = 100;

		@Config.Comment("Set to false to stop non-player entities from using the AoA portal system")
		@Config.LangKey("gui.aoaconfig.nonPlayerPortalUsage")
		public static boolean allowNonPlayerPortalTravel = true;

		@Config.Comment("Modifier for global xp gain for all players for AoA skills. Higher numbers means more xp gained")
		@Config.LangKey("gui.aoaconfig.globalXpModifier")
		@Config.RangeDouble(min = 0d, max = 10d)
		public static double globalXpModifier = 1d;

		@Config.Comment("Configure registered dimension IDs for all AoA dimensions. Don't change this unless you know what you are doing")
		@Config.LangKey("gui.aoaconfig.dimensionIds")
		@Config.RequiresMcRestart
		public static final SubCategoryDimensionIds dimensionIds = new SubCategoryDimensionIds();

		@Config.Comment("Configure random events that happen in the overworld each day or night")
		@Config.LangKey("gui.aoaconfig.overworldEvents")
		public static final SubCategoryOverworldEvents overworldEvents = new SubCategoryOverworldEvents();

		@Config.Comment("Configure the maximum amount of minions a player can have at a given time")
		@Config.LangKey("gui.aoaconfig.maxMinions")
		@Config.RangeInt(min = 1, max = 200)
		public static int maxMinions = 10;

		@Config.Comment("Toggle random fun options on and off")
		@Config.LangKey("gui.aoaconfig.funOptions")
		public static final SubCategoryFunOptions funOptions = new SubCategoryFunOptions();

		public static class SubCategoryDimensionIds {
			@Config.LangKey("dimension.aoa.abyss")
			public int abyss = 800;

			@Config.LangKey("dimension.aoa.ancientCavern")
			public int ancientCavern = 801;

			@Config.LangKey("dimension.aoa.barathos")
			public int barathos = 802;

			@Config.LangKey("dimension.aoa.candyland")
			public int candyland = 803;

			@Config.LangKey("dimension.aoa.celeve")
			public int celeve = 804;

			@Config.LangKey("dimension.aoa.creeponia")
			public int creeponia = 805;

			@Config.LangKey("dimension.aoa.crystevia")
			public int crystevia = 806;

			@Config.LangKey("dimension.aoa.deeplands")
			public int deeplands = 807;

			@Config.LangKey("dimension.aoa.dustopia")
			public int dustopia = 808;

			@Config.LangKey("dimension.aoa.gardencia")
			public int gardencia = 809;

			@Config.LangKey("dimension.aoa.greckon")
			public int greckon = 810;

			@Config.LangKey("dimension.aoa.haven")
			public int haven = 811;

			@Config.LangKey("dimension.aoa.immortallis")
			public int immortallis = 812;

			@Config.LangKey("dimension.aoa.iromine")
			public int iromine = 813;

			@Config.LangKey("dimension.aoa.lborean")
			public int lborean = 814;

			@Config.LangKey("dimension.aoa.lelyetia")
			public int lelyetia = 815;

			@Config.LangKey("dimension.aoa.lunalus")
			public int lunalus = 816;

			@Config.LangKey("dimension.aoa.mysterium")
			public int mysterium = 817;

			@Config.LangKey("dimension.aoa.precasia")
			public int precasia = 818;

			@Config.LangKey("dimension.aoa.runandor")
			public int runandor = 819;

			@Config.LangKey("dimension.aoa.shyrelands")
			public int shyrelands = 820;

			@Config.LangKey("dimension.aoa.voxPonds")
			public int voxPonds = 821;
		}

		public static class SubCategoryOverworldEvents {
			@Config.Comment("Set to false to disable all overworld events entirely.")
			@Config.LangKey("gui.aoaconfig.overworldEvents.enabled")
			public boolean enabled = true;

			@Config.Comment("Chance per day for the Big Day event to occur. Value is represented as a chance of 1/n. Set to 0 to disable entirely.")
			@Config.LangKey("gui.aoaconfig.overworldEvents.bigDayChance")
			@Config.RangeInt(min = 0, max = 1000000)
			public int bigDayChance = 50;

			@Config.Comment("Chance per day for the Creep Day event to occur. Value is represented as a chance of 1/n. Set to 0 to disable entirely.")
			@Config.LangKey("gui.aoaconfig.overworldEvents.creepDayChance")
			@Config.RangeInt(min = 0, max = 1000000)
			public int creepDayChance = 45;

			@Config.Comment("Chance per day for the Death Day event to occur. Value is represented as a chance of 1/n. Set to 0 to disable entirely.")
			@Config.LangKey("gui.aoaconfig.overworldEvents.deathDayChance")
			@Config.RangeInt(min = 0, max = 1000000)
			public int deathDayChance = 37;

			@Config.Comment("Chance per day for the Soul Scurry event to occur. Value is represented as a chance of 1/n. Set to 0 to disable entirely.")
			@Config.LangKey("gui.aoaconfig.overworldEvents.soulScurryChance")
			@Config.RangeInt(min = 0, max = 1000000)
			public int soulScurryChance = 59;

			@Config.Comment("Chance per day for the Blood Hunt event to occur. Value is represented as a chance of 1/n. Set to 0 to disable entirely.")
			@Config.LangKey("gui.aoaconfig.overworldEvents.bloodHuntChance")
			@Config.RangeInt(min = 0, max = 1000000)
			public int bloodHuntChance = 31;

			@Config.Comment("Chance per day for the Lunar Invasion event to occur. Value is represented as a chance of 1/n. Set to 0 to disable entirely.")
			@Config.LangKey("gui.aoaconfig.overworldEvents.lunarInvasionChance")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lunarInvasionChance = 26;

			@Config.Comment("Set to false to disable the Full Moon event on full moon nights in Minecraft")
			@Config.LangKey("gui.aoaconfig.overworldEvents.fullMoonEnabled")
			public boolean fullMoonEnabled = true;
		}

		public static class SubCategoryFunOptions {
			@Config.Comment("Set this to true to enable AoA hardcore mode.")
			@Config.LangKey("gui.aoaconfig.funOptions.hardcoreMode")
			@Config.RequiresMcRestart
			public boolean hardcoreMode = false;

			@Config.Comment("Set this to true to enable party deaths")
			@Config.LangKey("gui.aoaconfig.funOptions.partyDeaths")
			public boolean partyDeaths = false;

			@Config.Comment("Set this to true for Chargers. All the chargers")
			@Config.LangKey("gui.aoaconfig.funOptions.chargers")
			public boolean alwaysChargers = false;
		}
	}

	@Config(modid = "aoa3", type = Config.Type.INSTANCE, name = "aoa3/ore_config")
	@Config.LangKey("gui.aoaoreconfig.title")
	public static class OreConfig {
		@Config.LangKey("tile.AmethystOre.name")
		public static final SubCategoryAmethyst amethyst = new SubCategoryAmethyst();

		@Config.LangKey("tile.BaronyteOre.name")
		public static final SubCategoryBaronyte baronyte = new SubCategoryBaronyte();

		@Config.LangKey("tile.BlaziumOre.name")
		public static final SubCategoryBlazium blazium = new SubCategoryBlazium();

		@Config.LangKey("tile.BloodstoneOre.name")
		public static final SubCategoryBloodstone bloodstone = new SubCategoryBloodstone();

		@Config.LangKey("tile.BlueGemstoneOre.name")
		public static final SubCategoryBlueCrystal blueCrystal = new SubCategoryBlueCrystal();

		@Config.LangKey("tile.ChargedRuniumOre.name")
		public static final SubCategoryChargedRunium chargedRunium = new SubCategoryChargedRunium();

		@Config.LangKey("tile.ChestboneFragmentsOre.name")
		public static final SubCategoryChestboneFragments chestboneFragments = new SubCategoryChestboneFragments();

		@Config.LangKey("tile.CrystalliteOre.name")
		public static final SubCategoryCrystallite crystallite = new SubCategoryCrystallite();

		@Config.LangKey("tile.ElecaniumOre.name")
		public static final SubCategoryElecanium elecanium = new SubCategoryElecanium();

		@Config.LangKey("tile.EmberstoneOre.name")
		public static final SubCategoryEmberstone emberstone = new SubCategoryEmberstone();

		@Config.LangKey("tile.FootboneFragmentsOre.name")
		public static final SubCategoryFootboneFragments footboneFragments = new SubCategoryFootboneFragments();

		@Config.LangKey("tile.GemenyteOre.name")
		public static final SubCategoryGemenyte gemenyte = new SubCategoryGemenyte();

		@Config.LangKey("tile.GhastlyOre.name")
		public static final SubCategoryGhastly ghastly = new SubCategoryGhastly();

		@Config.LangKey("tile.GhoulishOre.name")
		public static final SubCategoryGhoulish ghoulish = new SubCategoryGhoulish();

		@Config.LangKey("tile.GreenGemstoneOre.name")
		public static final SubCategoryGreenCrystal greenCrystal = new SubCategoryGreenCrystal();

		@Config.LangKey("tile.JadeOre.name")
		public static final SubCategoryJade jade = new SubCategoryJade();

		@Config.LangKey("tile.JewelyteOre.name")
		public static final SubCategoryJewelyte jewelyte = new SubCategoryJewelyte();

		@Config.LangKey("tile.LegboneFragmentsOre.name")
		public static final SubCategoryLegboneFragments legboneFragments = new SubCategoryLegboneFragments();

		@Config.LangKey("tile.LimoniteOre.name")
		public static final SubCategoryLimonite limonite = new SubCategoryLimonite();

		@Config.LangKey("tile.LyonOre.name")
		public static final SubCategoryLyon lyon = new SubCategoryLyon();

		@Config.LangKey("tile.MystiteOre.name")
		public static final SubCategoryMystite mystite = new SubCategoryMystite();

		@Config.LangKey("tile.OrnamyteOre.name")
		public static final SubCategoryOrnamyte ornamyte = new SubCategoryOrnamyte();

		@Config.LangKey("tile.PurpleGemstoneOre.name")
		public static final SubCategoryPurpleCrystal purpleCrystal = new SubCategoryPurpleCrystal();

		@Config.LangKey("tile.RedGemstoneOre.name")
		public static final SubCategoryRedCrystal redCrystal = new SubCategoryRedCrystal();

		@Config.LangKey("tile.RositeOre.name")
		public static final SubCategoryRosite rosite = new SubCategoryRosite();

		@Config.LangKey("tile.RuniumOre.name")
		public static final SubCategoryRunium runium = new SubCategoryRunium();

		@Config.LangKey("tile.SapphireOre.name")
		public static final SubCategorySapphire sapphire = new SubCategorySapphire();

		@Config.LangKey("gui.aoaconfig.ores.shyreOres")
		public static final SubCategoryShyre shyre = new SubCategoryShyre();

		@Config.LangKey("tile.SkullboneFragmentsOre.name")
		public static final SubCategorySkullboneFragments skullboneFragments = new SubCategorySkullboneFragments();

		@Config.LangKey("tile.VarsiumOre.name")
		public static final SubCategoryVarsium varsium = new SubCategoryVarsium();

		@Config.LangKey("tile.WhiteGemstoneOre.name")
		public static final SubCategoryWhiteCrystal whiteCrystal = new SubCategoryWhiteCrystal();

		@Config.LangKey("tile.YellowGemstoneOre.name")
		public static final SubCategoryYellowCrystal yellowCrystal = new SubCategoryYellowCrystal();
		
		public static class SubCategoryAmethyst {
			@Config.Comment("Minimum ore blocks per Amethyst vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 3;
			
			@Config.Comment("Maximum ore blocks per Amethyst vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 7;

			@Config.Comment("Maximum number of Amethyst veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.maxVeinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int maxVeinsPerChunk = 4;
		}

		public static class SubCategoryBaronyte {
			@Config.Comment("Minimum ore blocks per Baronyte vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 4;

			@Config.Comment("Maximum ore blocks per Baronyte vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 8;

			@Config.Comment("Number of Baronyte veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 3;
		}

		public static class SubCategoryBlazium {
			@Config.Comment("Minimum ore blocks per Blazium vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 4;

			@Config.Comment("Maximum ore blocks per Blazium vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 8;

			@Config.Comment("Number of Blazium veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryBloodstone {
			@Config.Comment("Minimum ore blocks per Bloodstone vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 6;

			@Config.Comment("Maximum ore blocks per Bloodstone vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 12;

			@Config.Comment("Number of Bloodstone veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryBlueCrystal {
			@Config.Comment("Minimum ore blocks per Blue Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 9;

			@Config.Comment("Maximum ore blocks per Blue Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 15;

			@Config.Comment("Number of Blue Crystal veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryChargedRunium {
			@Config.Comment("Minimum ore blocks per Charged Runium vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 12;

			@Config.Comment("Maximum ore blocks per Charged Runium vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 18;

			@Config.Comment("Number of Charged Runium veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 5;
		}

		public static class SubCategoryChestboneFragments {
			@Config.Comment("Minimum ore blocks per Chestbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Chestbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 6;

			@Config.Comment("Number of Chestbone Fragments veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryCrystallite {
			@Config.Comment("Minimum ore blocks per Crystallite vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 8;

			@Config.Comment("Maximum ore blocks per Crystallite vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 12;

			@Config.Comment("Number of Crystallite veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 8;
		}

		public static class SubCategoryElecanium {
			@Config.Comment("Minimum ore blocks per Elecanium vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Elecanium vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 5;

			@Config.Comment("Number of Elecanium veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 3;
		}

		public static class SubCategoryEmberstone {
			@Config.Comment("Minimum ore blocks per Emberstone vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 5;

			@Config.Comment("Maximum ore blocks per Emberstone vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 8;

			@Config.Comment("Number of Emberstone veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 6;
		}

		public static class SubCategoryFootboneFragments {
			@Config.Comment("Minimum ore blocks per Footbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Footbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 6;

			@Config.Comment("Number of Footbone Fragments veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryGemenyte {
			@Config.Comment("Minimum ore blocks per Gemenyte vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Gemenyte vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 6;

			@Config.Comment("Number of Gemenyte veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryGhastly {
			@Config.Comment("Minimum ore blocks per Ghastly Ore vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 3;

			@Config.Comment("Maximum ore blocks per Ghastly Ore vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 7;

			@Config.Comment("Number of Ghastly Ore veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryGhoulish {
			@Config.Comment("Minimum ore blocks per Ghoulish Ore vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 3;

			@Config.Comment("Maximum ore blocks per Ghoulish Ore vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 7;

			@Config.Comment("Number of Ghoulish Ore veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryGreenCrystal {
			@Config.Comment("Minimum ore blocks per Green Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 9;

			@Config.Comment("Maximum ore blocks per Green Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 15;

			@Config.Comment("Number of Green Crystal veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryJade {
			@Config.Comment("Minimum ore blocks per Jade vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 3;

			@Config.Comment("Maximum ore blocks per Jade vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 9;

			@Config.Comment("Maximum number of Jade veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.maxVeinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int maxVeinsPerChunk = 5;
		}

		public static class SubCategoryJewelyte {
			@Config.Comment("Minimum ore blocks per Jewelyte vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Jewelyte vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 6;

			@Config.Comment("Number of Jewelyte veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryLegboneFragments {
			@Config.Comment("Minimum ore blocks per Legbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Legbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 6;

			@Config.Comment("Number of Legbone Fragments veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryLimonite {
			@Config.Comment("Minimum ore blocks per Limonite vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 4;

			@Config.Comment("Maximum ore blocks per Limonite vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 12;

			@Config.Comment("Maximum number of Limonite veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.maxVeinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int maxVeinsPerChunk = 8;
		}

		public static class SubCategoryLyon {
			@Config.Comment("Minimum ore blocks per Lyon vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 3;

			@Config.Comment("Maximum ore blocks per Lyon vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 7;

			@Config.Comment("Number of Lyon veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryMystite {
			@Config.Comment("Minimum ore blocks per Mystite vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 5;

			@Config.Comment("Maximum ore blocks per Mystite vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 9;

			@Config.Comment("Number of Mystite veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryOrnamyte {
			@Config.Comment("Minimum ore blocks per Ornamyte vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Ornamyte vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 6;

			@Config.Comment("Number of Ornamyte veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryPurpleCrystal {
			@Config.Comment("Minimum ore blocks per Purple Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 9;

			@Config.Comment("Maximum ore blocks per Purple Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 15;

			@Config.Comment("Number of Purple Crystal veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryRedCrystal {
			@Config.Comment("Minimum ore blocks per Red Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 9;

			@Config.Comment("Maximum ore blocks per Red Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 15;

			@Config.Comment("Number of Red Crystal veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryRosite {
			@Config.Comment("Minimum ore blocks per Rosite vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 3;

			@Config.Comment("Maximum ore blocks per Rosite vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 10;

			@Config.Comment("Maximum number of Rosite veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.maxVeinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int maxVeinsPerChunk = 5;
		}

		public static class SubCategoryRunium {
			@Config.Comment("Minimum ore blocks per Runium vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 8;

			@Config.Comment("Maximum ore blocks per Runium vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 17;

			@Config.Comment("Maximum number of Runium veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.maxVeinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int maxVeinsPerChunk = 7;
		}

		public static class SubCategorySapphire {
			@Config.Comment("Minimum ore blocks per Sapphire vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Sapphire vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 4;

			@Config.Comment("Maximum number of Sapphire veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.maxVeinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int maxVeinsPerChunk = 3;
		}

		public static class SubCategoryShyre {
			@Config.Comment("Minimum Shyre ore blocks per chunk")
			@Config.LangKey("gui.aoaconfig.ores.minBlocksPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int minBlocksPerChunk = 10;

			@Config.Comment("Maximum Shyre ore blocks per chunk")
			@Config.LangKey("gui.aoaconfig.ores.maxBlocksPerChunk")
			@Config.RangeInt(min = 0, max = 30)
			public int maxBlocksPerChunk = 19;
		}

		public static class SubCategorySkullboneFragments {
			@Config.Comment("Minimum ore blocks per Skullbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 2;

			@Config.Comment("Maximum ore blocks per Skullbone Fragments vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 6;

			@Config.Comment("Number of Skullbone Fragments veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryVarsium {
			@Config.Comment("Minimum ore blocks per Varsium vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 4;

			@Config.Comment("Maximum ore blocks per Varsium vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 8;

			@Config.Comment("Number of Varsium veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 2;
		}

		public static class SubCategoryWhiteCrystal {
			@Config.Comment("Minimum ore blocks per White Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 9;

			@Config.Comment("Maximum ore blocks per White Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 15;

			@Config.Comment("Number of White Crystal veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}

		public static class SubCategoryYellowCrystal {
			@Config.Comment("Minimum ore blocks per Yellow Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.minOresPerVein")
			@Config.RangeInt(min = 1, max = 20)
			public int minOresPerVein = 9;

			@Config.Comment("Maximum ore blocks per Yellow Crystal vein")
			@Config.LangKey("gui.aoaconfig.ores.maxOresPerVein")
			@Config.RangeInt(min = 1, max = 30)
			public int maxOresPerVein = 15;

			@Config.Comment("Number of Yellow Crystal veins per chunk")
			@Config.LangKey("gui.aoaconfig.ores.veinsPerChunk")
			@Config.RangeInt(min = 0, max = 20)
			public int veinsPerChunk = 4;
		}
	}

	@Config(modid = "aoa3", type = Config.Type.INSTANCE, name = "aoa3/structure_config")
	@Config.LangKey("gui.aoastructureconfig.title")
	public static class StructureConfig {
		@Config.Comment("Set custom spawn chances for Abyss structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.abyss")
		public static final SubCategoryAbyss abyss = new SubCategoryAbyss();

		@Config.Comment("Set custom spawn chances for Barathos structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.barathos")
		public static final SubCategoryBarathos barathos = new SubCategoryBarathos();

		@Config.Comment("Set custom spawn chances for Candyland structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk")
		@Config.LangKey("dimension.aoa.candyland")
		public static final SubCategoryCandyland candyland = new SubCategoryCandyland();

		@Config.Comment("Set custom spawn chances for Celeve structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.celeve")
		public static final SubCategoryCeleve celeve = new SubCategoryCeleve();

		@Config.Comment("Set custom spawn chances for Creeponia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk")
		@Config.LangKey("dimension.aoa.creeponia")
		public static final SubCategoryCreeponia creeponia = new SubCategoryCreeponia();

		@Config.Comment("Set custom spawn chances for Crystevia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely")
		@Config.LangKey("dimension.aoa.crystevia")
		public static final SubCategoryCrystevia crystevia = new SubCategoryCrystevia();

		@Config.Comment("Set custom spawn chances for Deeplands structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk")
		@Config.LangKey("dimension.aoa.deeplands")
		public static final SubCategoryDeeplands deeplands = new SubCategoryDeeplands();

		@Config.Comment("Set custom spawn chances for Dustopia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk")
		@Config.LangKey("dimension.aoa.dustopia")
		public static final SubCategoryDustopia dustopia = new SubCategoryDustopia();

		@Config.Comment("Set custom spawn chances for Gardencia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.gardencia")
		public static final SubCategoryGardencia gardencia = new SubCategoryGardencia();

		@Config.Comment("Set custom spawn chances for Greckon structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.greckon")
		public static final SubCategoryGreckon greckon = new SubCategoryGreckon();

		@Config.Comment("Set custom spawn chances for Haven structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.haven")
		public static final SubCategoryHaven haven = new SubCategoryHaven();

		@Config.Comment("Set custom spawn chances for Iromine structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.iromine")
		public static final SubCategoryIromine iromine = new SubCategoryIromine();

		@Config.Comment("Set custom spawn chances for L'Borean structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.lborean")
		public static final SubCategoryLborean lborean = new SubCategoryLborean();

		@Config.Comment("Set custom spawn chances for Lelyetia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk")
		@Config.LangKey("dimension.aoa.lelyetia")
		public static final SubCategoryLelyetia lelyetia = new SubCategoryLelyetia();

		@Config.Comment("Set custom spawn chances for Lunalus structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.lunalus")
		public static final SubCategoryLunalus lunalus = new SubCategoryLunalus();

		@Config.Comment("Set custom spawn chances for Mysterium structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.mysterium")
		public static final SubCategoryMysterium mysterium = new SubCategoryMysterium();

		@Config.Comment("Set custom spawn chances for Nether structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely")
		@Config.LangKey("dimension.aoa.nether")
		public static final SubCategoryNether nether = new SubCategoryNether();

		@Config.Comment("Set custom spawn chances for Overworld structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely")
		@Config.LangKey("dimension.aoa.overworld")
		public static final SubCategoryOverworld overworld = new SubCategoryOverworld();

		@Config.Comment("Set custom spawn chances for Precasia structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines and precasian dens")
		@Config.LangKey("dimension.aoa.precasia")
		public static final SubCategoryPrecasia precasia = new SubCategoryPrecasia();

		@Config.Comment("Set custom spawn chances for Runandor structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk")
		@Config.LangKey("dimension.aoa.runandor")
		public static final SubCategoryRunandor runandor = new SubCategoryRunandor();

		@Config.Comment("Set custom spawn chances for Shyrelands structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely")
		@Config.LangKey("dimension.aoa.shyrelands")
		public static final SubCategoryShyrelands shyrelands = new SubCategoryShyrelands();

		@Config.Comment("Set custom spawn chances for VoxPonds structures. Value is represented as a chance of 1/n. Enter 0 to disable spawning the structure entirely. One structure per chunk, with the exception of rune shrines")
		@Config.LangKey("dimension.aoa.voxPonds")
		public static final SubCategoryVoxPonds voxPonds = new SubCategoryVoxPonds();
		
		public static class SubCategoryAbyss {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.illusionTree")
			@Config.RangeInt(min = 0, max = 1000000)
			public int illusionTreeSpawnChance = 600;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.jaweHut")
			@Config.RangeInt(min = 0, max = 1000000)
			public int jaweHutSpawnChance = 520;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.fleshTemple")
			@Config.RangeInt(min = 0, max = 1000000)
			public int fleshTempleSpawnChance = 520;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.abyssalLottoHut")
			@Config.RangeInt(min = 0, max = 1000000)
			public int abyssalLottoHutSpawnChance = 440;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.shadowlordPlatform")
			@Config.RangeInt(min = 0, max = 1000000)
			public int shadowlordPlatformSpawnChance = 420;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.witherRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int witherRuneShrineSpawnChance = 650;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.eyeBulbGrotto")
			@Config.RangeInt(min = 0, max = 1000000)
			public int eyeBulbGrottoSpawnChance = 140;
		}

		public static class SubCategoryBarathos {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.baronessArena")
			@Config.RangeInt(min = 0, max = 1000000)
			public int baronessArenaSpawnChance = 1400;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.hiveNest")
			@Config.RangeInt(min = 0, max = 1000000)
			public int hiveNestSpawnChance = 900;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.baronCastle")
			@Config.RangeInt(min = 0, max = 1000000)
			public int baronCastleSpawnChance = 950;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.baronessHouse")
			@Config.RangeInt(min = 0, max = 1000000)
			public int baronessHouseSpawnChance = 975;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.powerRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int powerRuneShrineSpawnChance = 650;
		}

		public static class SubCategoryCandyland {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.cottonCandyTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int cottonCandyTowerSpawnChance = 850;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.gingerbreadHouse")
			@Config.RangeInt(min = 0, max = 1000000)
			public int gingerbreadHouseSpawnChance = 740;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.gingerbirdAviary")
			@Config.RangeInt(min = 0, max = 1000000)
			public int gingerbirdAviarySpawnChance = 700;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.candyLottoPlatform")
			@Config.RangeInt(min = 0, max = 1000000)
			public int candyLottoPlatformSpawnChance = 600;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.infestedCandyCane")
			@Config.RangeInt(min = 0, max = 1000000)
			public int infestedCandyCaneSpawnChance = 420;
		}

		public static class SubCategoryCeleve {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.gyroPlatform")
			@Config.RangeInt(min = 0, max = 1000000)
			public int gyroPlatformSpawnChance = 980;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.celevianLottoBalloon")
			@Config.RangeInt(min = 0, max = 1000000)
			public int celevianLottoBalloonSpawnChance = 650;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.toyTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int toyTowerSpawnChance = 600;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.compassRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int compassRuneShrineSpawnChance = 450;
		}

		public static class SubCategoryCreeponia {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.creeperHQ")
			@Config.RangeInt(min = 0, max = 1000000)
			public int creeperHQSpawnChance = 700;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.creeponianLottoStand")
			@Config.RangeInt(min = 0, max = 1000000)
			public int creeponianLottoStandSpawnChance = 300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.explosivesTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int explosivesTowerSpawnChance = 300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.creeponiaBank")
			@Config.RangeInt(min = 0, max = 1000000)
			public int creeponiaBankSpawnChance = 300;
		}

		public static class SubCategoryCrystevia {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.crystalBuilding")
			@Config.RangeInt(min = 0, max = 1000000)
			public int crystalBuildingSpawnChance = 400;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.crystalTransferHut")
			@Config.RangeInt(min = 0, max = 1000000)
			public int crystalTransferHutSpawnChance = 45;
		}

		public static class SubCategoryDeeplands {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.arocknidCave")
			@Config.RangeInt(min = 0, max = 1000000)
			public int arocknidCaveSpawnChance = 700;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.chargingStation")
			@Config.RangeInt(min = 0, max = 1000000)
			public int chargingStationSpawnChance = 635;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.krorPillars")
			@Config.RangeInt(min = 0, max = 1000000)
			public int krorPillarsSpawnChance = 635;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.deepLottoShelter")
			@Config.RangeInt(min = 0, max = 1000000)
			public int deepLottoShelterSpawnChance = 635;
		}

		public static class SubCategoryDustopia {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.primordialShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int primordialShrineSpawnChance = 350;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.crusiliskCave")
			@Config.RangeInt(min = 0, max = 1000000)
			public int crusiliskCaveSpawnChance = 305;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.merkyreTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int merkyreTowerSpawnChance = 300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.arkzyneOutpost")
			@Config.RangeInt(min = 0, max = 1000000)
			public int arkzyneOutpostSpawnChance = 295;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lottoCage")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lottoCageSpawnChance = 200;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.dustopianVillage")
			@Config.RangeInt(min = 0, max = 1000000)
			public int dustopianVillageSpawnChance = 200;
		}

		public static class SubCategoryGardencia {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.floroCastle")
			@Config.RangeInt(min = 0, max = 1000000)
			public int floroCastleSpawnChance = 1700;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.gardenCastle")
			@Config.RangeInt(min = 0, max = 1000000)
			public int gardenCastleSpawnChance = 1650;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lottoSkyFlower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lottoSkyFlowerSpawnChance = 500;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.wizardFlower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int wizardFlowerSpawnChance = 300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.dayseeFlower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int dayseeFlowerSpawnChance = 300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.kineticRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int kineticRuneShrineSpawnChance = 650;
		}

		public static class SubCategoryGreckon {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.hauntedMaze")
			@Config.RangeInt(min = 0, max = 1000000)
			public int hauntedMazeSpawnChance = 700;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.hauntedLottoRock")
			@Config.RangeInt(min = 0, max = 1000000)
			public int hauntedLottoRockSpawnChance = 500;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.facelessTree")
			@Config.RangeInt(min = 0, max = 1000000)
			public int facelessTreeSpawnChance = 180;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.stormRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int stormRuneShrineSpawnChance = 650;
		}

		public static class SubCategoryHaven {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.dawnlightDungeon")
			@Config.RangeInt(min = 0, max = 1000000)
			public int dawnlightDungeonSpawnChance = 650;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.floatingLottoFountain")
			@Config.RangeInt(min = 0, max = 1000000)
			public int floatingLottoFountainSpawnChance = 650;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.guardianTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int guardianTowerSpawnChance = 35;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.rockriderBoulder")
			@Config.RangeInt(min = 0, max = 1000000)
			public int rockriderBoulderSpawnChance = 17;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.strikeRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int strikeRuneShrineSpawnChance = 650;
		}

		public static class SubCategoryIromine {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.iroMaze")
			@Config.RangeInt(min = 0, max = 1000000)
			public int iroMazeSpawnChance = 1300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.chargingPads")
			@Config.RangeInt(min = 0, max = 1000000)
			public int chargingPadsSpawnChance = 1300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.professorsLab")
			@Config.RangeInt(min = 0, max = 1000000)
			public int professorsLabSpawnChance = 600;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.iroPillar")
			@Config.RangeInt(min = 0, max = 1000000)
			public int iroPillarSpawnChance = 600;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.iroPassage")
			@Config.RangeInt(min = 0, max = 1000000)
			public int iroPassageSpawnChance = 150;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.energyRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int energyRuneShrineSpawnChance = 650;
		}

		public static class SubCategoryLborean {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.aquaticCastle")
			@Config.RangeInt(min = 0, max = 1000000)
			public int aquaticCastleSpawnChance = 1700;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.drownedLottoStand")
			@Config.RangeInt(min = 0, max = 1000000)
			public int drownedLottoStandSpawnChance = 550;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.dracyonFountain")
			@Config.RangeInt(min = 0, max = 1000000)
			public int dracyonFountainSpawnChance = 450;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.hydroPlatform")
			@Config.RangeInt(min = 0, max = 1000000)
			public int hydroPlatformSpawnChance = 300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.waterRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int waterRuneShrineSpawnChance = 650;
		}

		public static class SubCategoryLelyetia {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lelyetianTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lelyetianTowerSpawnChance = 760;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.zhinxEnclave")
			@Config.RangeInt(min = 0, max = 1000000)
			public int zhinxEnclaveSpawnChance = 380;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.paraviteHive")
			@Config.RangeInt(min = 0, max = 1000000)
			public int paraviteHiveSpawnChance = 380;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.boneyDungeon")
			@Config.RangeInt(min = 0, max = 1000000)
			public int boneyDungeonSpawnChance = 380;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.grawPillar")
			@Config.RangeInt(min = 0, max = 1000000)
			public int grawPillarSpawnChance = 166;
		}

		public static class SubCategoryLunalus {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lunarVillage")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lunarVillageSpawnChance = 1500;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.spaceArena")
			@Config.RangeInt(min = 0, max = 1000000)
			public int spaceArenaSpawnChance = 905;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lunarCreationPlatform")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lunarCreationPlatformSpawnChance = 905;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.zargPlanetoid")
			@Config.RangeInt(min = 0, max = 1000000)
			public int zargPlanetoidSpawnChance = 900;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lunarMaze")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lunarMazeSpawnChance = 900;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lunarPrison")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lunarPrisonSpawnChance = 850;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lunarGarden")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lunarGardenSpawnChance = 804;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.observersEye")
			@Config.RangeInt(min = 0, max = 1000000)
			public int observersEyeSpawnChance = 375;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lunarRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lunarRuneShrineSpawnChance = 600;
		}

		public static class SubCategoryMysterium {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.hauntedCastle")
			@Config.RangeInt(min = 0, max = 1000000)
			public int hauntedCastleSpawnChance = 850;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.fungshroom")
			@Config.RangeInt(min = 0, max = 1000000)
			public int fungshroomSpawnChance = 800;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.gorbVillage")
			@Config.RangeInt(min = 0, max = 1000000)
			public int gorbVillageSpawnChance = 650;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.mysticLottoShroom")
			@Config.RangeInt(min = 0, max = 1000000)
			public int mysticLottoShroomSpawnChance = 500;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.mushroomSpiderCave")
			@Config.RangeInt(min = 0, max = 1000000)
			public int mushroomSpiderCaveSpawnChance = 450;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.mysticPortalPlatform")
			@Config.RangeInt(min = 0, max = 1000000)
			public int mysticPortalPlatformSpawnChance = 416;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.runicArena")
			@Config.RangeInt(min = 0, max = 1000000)
			public int runicArenaSpawnChance = 315;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.distortionRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int distortionRuneShrineSpawnChance = 500;
		}

		public static class SubCategoryNether {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.nethengeicPit")
			@Config.RangeInt(min = 0, max = 1000000)
			public int nethengeicPitSpawnChance = 200;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.fireRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int fireRuneShrineSpawnChance = 100;
		}

		public static class SubCategoryOverworld {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.amphibiyteCove")
			@Config.RangeInt(min = 0, max = 1000000)
			public int amphibiyteCoveSpawnChance = 20;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.ruinedTeleporterFrame")
			@Config.RangeInt(min = 0, max = 1000000)
			public int ruinedTeleporterFrameSpawnChance = 130;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.windRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int windRuneShrineSpawnChance = 150;
		}

		public static class SubCategoryPrecasia {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.kaiyuTemple")
			@Config.RangeInt(min = 0, max = 1000000)
			public int kaiyuTempleSpawnChance = 1500;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.skeletalArmyArena")
			@Config.RangeInt(min = 0, max = 1000000)
			public int skeletalArmyArenaSpawnChance = 1000;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.jungleLottoHut")
			@Config.RangeInt(min = 0, max = 1000000)
			public int jungleLottoHutSpawnChance = 420;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.opteryxNest")
			@Config.RangeInt(min = 0, max = 1000000)
			public int opteryxNestSpawnChance = 300;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.precasianDen")
			@Config.RangeInt(min = 0, max = 1000000)
			public int precasianDenSpawnChance = 600;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.lifeRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int lifeRuneShrineSpawnChance = 650;
		}

		public static class SubCategoryRunandor {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.clunkheadArena")
			@Config.RangeInt(min = 0, max = 1000000)
			public int clunkheadArenaSpawnChance = 800;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.runicTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int runicTowerSpawnChance = 650;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.spectralCage")
			@Config.RangeInt(min = 0, max = 1000000)
			public int spectralCageSpawnChance = 500;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.runeRandomisationStation")
			@Config.RangeInt(min = 0, max = 1000000)
			public int runeRandomisationStationSpawnChance = 315;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.runeTemplarBunker")
			@Config.RangeInt(min = 0, max = 1000000)
			public int runeTemplarBunkerSpawnChance = 300;
		}

		public static class SubCategoryShyrelands {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.shyreDecoration")
			@Config.RangeInt(min = 0, max = 1000000)
			public int shyreDecorationSpawnChance = 25;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.craexxeusTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int craexxeusTowerSpawnChance = 600;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.shyreDungeon")
			@Config.RangeInt(min = 0, max = 1000000)
			public int shyreDungeonSpawnChance = 250;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.whitewashingStation")
			@Config.RangeInt(min = 0, max = 1000000)
			public int whitewashingStationSpawnChance = 275;
		}

		public static class SubCategoryVoxPonds {
			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.enigmaStation")
			@Config.RangeInt(min = 0, max = 1000000)
			public int enigmaStationSpawnChance = 866;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.controlTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int controlTowerSpawnChance = 506;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.cellTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int cellTowerSpawnChance = 506;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.observationTower")
			@Config.RangeInt(min = 0, max = 1000000)
			public int observationTowerSpawnChance = 506;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.voxBuilding")
			@Config.RangeInt(min = 0, max = 1000000)
			public int voxBuildingSpawnChance = 130;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.nightwingIsland")
			@Config.RangeInt(min = 0, max = 1000000)
			public int nightwingIslandSpawnChance = 97;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.voxLottoOutpost")
			@Config.RangeInt(min = 0, max = 1000000)
			public int voxLottoOutpostSpawnChance = 83;

			@Config.Comment("Value is represented as a chance to spawn of 1/n per chunk")
			@Config.LangKey("gui.aoaconfig.structures.poisonRuneShrine")
			@Config.RangeInt(min = 0, max = 1000000)
			public int poisonRuneShrineSpawnChance = 600;
		}
	}

	@Config(modid = "aoa3", type = Config.Type.INSTANCE, name = "aoa3/entities_config")
	@Config.LangKey("gui.aoaentityconfig.title")
	public static class EntityConfig {
		@Config.Comment("Value represents a modifier to general spawn rates. Higher = more spawns. Value between 0 and 1")
		@Config.LangKey("gui.aoaconfig.entitySpawnModifier")
		@Config.RangeDouble(min = 0, max = 1)
		public static double mobSpawnFrequencyModifier = 0.3d;

		@Config.Comment("Configure entity IDs for mobs that shouldn't spawn bloodlusts when hit")
		@Config.LangKey("gui.aoaentityconfig.bloodlustBlacklist")
		public static String[] bloodlustBlacklist = new String[] {};

		@Config.Comment("Configure entity IDs for mobs that shouldn't spawn heartstones when hit")
		@Config.LangKey("gui.aoaentityconfig.heartstoneBlacklist")
		public static String[] heartstoneBlacklist = new String[] {};

		@Config.Comment("Add or remove Hunter entity attributes from creatures")
		@Config.LangKey("gui.aoaconfig.hunterEntities")
		@Config.RequiresMcRestart
		public static final SubCategoryHunterEntities hunterEntities = new SubCategoryHunterEntities();

		public static class SubCategoryHunterEntities {
			@Config.Comment("A list of entities with their associated hunter level and xp reward. Add to the list to include a new entity, or remove one to withdraw its hunter status")
			@Config.LangKey("gui.aoaconfig.hunterEntities")
			@Config.RequiresMcRestart
			public String[] hunterEntities = new String[] {"aoa3:anemia lvl:1 xp:23.1", "aoa3:bloodmist lvl:1 xp:9", "aoa3:dark_beast lvl:1 xp:11.2", "aoa3:host lvl:1 xp:19", "aoa3:irkling lvl:1 xp:9.9", "aoa3:linger lvl:1 xp:10.8", "aoa3:modulo lvl:1 xp:11.9", "aoa3:night_watcher lvl:1 xp:9.5", "aoa3:rammerhead lvl:1 xp:10.8", "aoa3:roloscope lvl:1 xp:9", "aoa3:scrubby lvl:1 xp:7.6", "aoa3:skellox lvl:1 xp:7.6", "aoa3:terrestrial lvl:1 xp:13.4", "aoa3:urka lvl:1 xp:8.8", "aoa3:vertebron lvl:1 xp:9.5", "aoa3:void_walker lvl:1 xp:6.4", "aoa3:walker lvl:1 xp:6.6", "aoa3:death_hunter lvl:3 xp:11.4", "aoa3:headless_destroyer lvl:3 xp:12.1", "aoa3:reaper_twins lvl:3 xp:9", "aoa3:triclops lvl:3 xp:7.6", "aoa3:mother_void_walker lvl:5 xp:9.9", "aoa3:ice_giant lvl:9 xp:8.6", "aoa3:leafy_giant lvl:9 xp:7.4", "aoa3:sand_giant lvl:9 xp:10.2", "aoa3:stone_giant lvl:9 xp:15.5", "aoa3:wood_giant lvl:9 xp:12.4", "aoa3:amphibiyte lvl:12 xp:8.1", "aoa3:skeletal_cowman lvl:13 xp:12.8", "aoa3:kaiyu lvl:17 xp:10.5", "aoa3:zhinx lvl:19 xp:17.8", "aoa3:exohead lvl:23 xp:15.2", "aoa3:nethengeic_beast lvl:24 xp:16.3", "aoa3:diocus lvl:27 xp:29.9", "aoa3:paravite lvl:28 xp:21.4", "aoa3:spinoledon lvl:28 xp:21.4", "aoa3:iosaur lvl:29 xp:24.2", "aoa3:mushroom_spider lvl:29 xp:21.6", "aoa3:rawbone lvl:30 xp:24.6", "aoa3:enforcer lvl:31 xp:26.4", "aoa3:undead_troll lvl:32 xp:28.4", "aoa3:jawe lvl:33 xp:30.5", "aoa3:banshee lvl:34 xp:39.1", "aoa3:nightmare_spider lvl:34 xp:26.6", "aoa3:dawnlight lvl:35 xp:35.4", "aoa3:nightwing lvl:39 xp:47.8", "aoa3:flesh_eater lvl:40 xp:51.6", "aoa3:jumbo lvl:42 xp:90.2", "aoa3:phantom lvl:44 xp:70.3", "aoa3:cane_bug lvl:47 xp:88.9", "aoa3:gingerbird lvl:50 xp:112.8", "aoa3:gingerbread_man lvl:53 xp:143.6", "aoa3:pod_plant lvl:56 xp:183.1", "aoa3:vine_wizard lvl:59 xp:233.9", "aoa3:spectral_wizard lvl:64 xp:353.3", "aoa3:runic_guardian lvl:66 xp:417.2", "aoa3:mermage lvl:67 xp:453.5", "aoa3:amphibior lvl:69 xp:536", "aoa3:faceless_floater lvl:71 xp:634", "aoa3:zorp lvl:75 xp:888.4", "aoa3:zarg lvl:76 xp:966.9", "aoa3:baumba lvl:78 xp:1145.8", "aoa3:refluct lvl:79 xp:1247.6", "aoa3:inmate_x lvl:81 xp:1443.4", "aoa3:inmate_y lvl:81 xp:1640.8", "aoa3:merkyre lvl:83 xp:1755.3", "aoa3:crusilisk lvl:84 xp:1912.2", "aoa3:arkzyne lvl:86 xp:2270.2", "aoa3:shyre_troll lvl:90 xp:3203.6", "aoa3:lightwalker lvl:94 xp:4527.5", "aoa3:luxocron lvl:97 xp:5873.8"};
		}
	}

	@Config(modid = "aoa3", type = Config.Type.INSTANCE, name = "aoa3/integrations_config")
	@Config.LangKey("gui.aoaintegrationsconfig.title")
	public static class IntegrationsConfig {
		@Config.Comment("Configure Tinkers Construct integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.tinkersConstruct")
		public static final SubCategoryTinkersConstruct tinkersConstruct = new SubCategoryTinkersConstruct();

		public static class SubCategoryTinkersConstruct {
			@Config.Comment("Set this to false to disable all integrations with Tinkers Construct when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;

			@Config.Comment("List of AoA Dimensions to prevent slime island generation in.")
			@Config.LangKey("gui.aoaintegrationsconfig.tinkersConstruct.slimeIslandBlacklist")
			@Config.RequiresMcRestart
			public String[] slimeIslandBlacklist = new String[] {"Abyss", "Ancient_Cavern", "Barathos", "Candyland", "Celeve", "Creeponia", "Crystevia", "Deeplands", "Dustopia", "Gardencia", "Greckon", "Haven", "Immortallis", "Iromine", "LBorean", "Lelyetia", "Lunalus", "Mysterium", "Precasia", "Runandor", "Shyrelands", "Vox_Ponds"};

			@Config.Comment("Set this to false to disable AoA's additional modifiers for Tinkers Construct")
			@Config.LangKey("gui.aoaintegrationsconfig.tinkersConstruct.modifiers")
			@Config.RequiresMcRestart
			public boolean modifiers = true;

			@Config.Comment("Set this to false to disable AoA's custom materials for Tinkers Construct")
			@Config.LangKey("gui.aoaintegrationsconfig.tinkersConstruct.materials")
			@Config.RequiresMcRestart
			public boolean materials = true;
		}

		@Config.Comment("Configure JustEnoughResources integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.jer")
		public static final SubCategoryJustEnoughResources jer = new SubCategoryJustEnoughResources();

		public static class SubCategoryJustEnoughResources {
			@Config.Comment("Set this to false to disable all integrations with JustEnoughResources when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;
		}

		@Config.Comment("Configure IndustrialCraft2 integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.ic2")
		public static final SubCategoryIndustrialCraft ic2 = new SubCategoryIndustrialCraft();

		public static class SubCategoryIndustrialCraft {
			@Config.Comment("Set this to false to disable all integrations with IndustrialCraft2 when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;

			@Config.Comment("Set this to false to disable Hazmat Armor & Qantum suit integration with AoA's Hazmat Suit")
			@Config.LangKey("gui.aoaintegrationsconfig.ic2.hazmatArmour")
			public boolean hazmatArmour = true;

			@Config.Comment("Set this to false to disable additional compressor recipes from AoA")
			@Config.LangKey("gui.aoaintegrationsconfig.ic2.compressorRecipes")
			public boolean compressorRecipes = true;

			@Config.Comment("Set this to false to disable additional extractor recipes from AoA")
			@Config.LangKey("gui.aoaintegrationsconfig.ic2.extractorRecipes")
			public boolean extractorRecipes = true;

			@Config.Comment("Set this to false to disable custom materials from AoA")
			@Config.LangKey("gui.aoaintegrationsconfig.ic2.materials")
			public boolean materials = true;
		}

		@Config.Comment("Configure CraftTweaker integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.craftTweaker")
		public static final SubCategoryCraftTweaker craftTweaker = new SubCategoryCraftTweaker();

		public static class SubCategoryCraftTweaker {
			@Config.Comment("Set this to false to disable all integrations with CraftTweaker when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;
		}

		@Config.Comment("Configure JEI integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.jei")
		public static final SubCategoryJEI jei = new SubCategoryJEI();

		public static class SubCategoryJEI {
			@Config.Comment("Set this to false to disable all integrations with JEI when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;
		}

		@Config.Comment("Configure Immersive Engineering integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.immersiveEngineering")
		public static final SubCategoryImmersiveEngineering immersiveEngineering = new SubCategoryImmersiveEngineering();

		public static class SubCategoryImmersiveEngineering {
			@Config.Comment("Set this to false to disable all integrations with Immersive Engineering when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;
		}

		@Config.Comment("Configure Thaumcraft integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.thaumcraft")
		public static final SubCategoryThaumcraft thaumcraft = new SubCategoryThaumcraft();

		public static class SubCategoryThaumcraft {
			@Config.Comment("Set this to false to disable all integrations with Thaumcraft when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;

			@Config.Comment("List of AoA raw items and their default aspect values. Thaumcraft automatically assigns aspects to non-raw items.")
			@Config.LangKey("gui.aoaintegrationsconfig.thaumcraft.aspects")
			@Config.RequiresMcRestart
			public String[] defaultAspects = new String[] {"aoa3:copper_coin; permutatio:1", "aoa3:silver_coin; desiderium:1 permutatio:1", "aoa3:gold_coin; desiderium:1 permutatio:1", "aoa3:lunaver_coin; desiderium:1 permutatio:1", "aoa3:eye_bulb; herba:5 victus:5 vitium:1", "aoa3:holly_top_petals; herba:5 victus:5 praecantatio:1", "aoa3:floracle_sticks; herba:5 victus:5 aqua:5", "aoa3:trilliad_leaves; herba:5 victus:5 alkimia:1", "aoa3:goldicap_petals; herba:5 victus:5 motus:1", "aoa3:luna_globe; herba:5 victus:5 alienis:1", "aoa3:lunacrike; herba:5 victus:5 alienis:1", "aoa3:lunalons; herba:5 victus:5 alienis:1", "aoa3:rosidons; herba:5 victus:5 motus:1", "aoa3:mystic_shrooms; herba:5 victus:5 sensus:1", "aoa3:tea_shreddings; herba:5 victus:5 terra:5", "aoa3:chilli; herba:5 victus:5 ignis:5", "aoa3:thorny_petals; herba:5 victus:5 aversio:1", "aoa3:heart_fruit; herba:5 victus:5 aversio:1", "aoa3:magic_marang; herba:5 victus:5 potentia:1", "aoa3:bubble_berries; herba:5 victus:5 aqua:5", "aoa3:archergun_frame; metallum:5 fabrico:5", "aoa3:blaster_frame; metallum:5 fabrico:5", "aoa3:boots_frame; metallum:5 fabrico:5", "aoa3:leggings_frame; metallum:5 fabrico:5", "aoa3:chestplate_frame; metallum:5 fabrico:5", "aoa3:helmet_frame; metallum:5 fabrico:5", "aoa3:cannon_frame; metallum:5 fabrico:5", "aoa3:gun_frame; metallum:5 fabrico:5", "aoa3:shotgun_frame; metallum:5 fabrico:5", "aoa3:sniper_frame; metallum:5 fabrico:5", "aoa3:ancient_upgrade_kit; fabrico:35 sensus:15", "aoa3:abyssal_upgrade_kit; fabrico:35 vitium:15", "aoa3:predator_upgrade_kit; fabrico:35 bestia:15", "aoa3:smiley_upgrade_kit; fabrico:35 humanus:15", "aoa3:haunted_upgrade_kit; fabrico:35 spiritus:15", "aoa3:precasian_upgrade_kit; fabrico:35 victus:15", "aoa3:apoco_upgrade_kit; fabrico:35 mortuus:15", "aoa3:lunar_upgrade_kit; fabrico:35 alienis:15", "aoa3:seaside_upgrade_kit; fabrico:35 aqua:15", "aoa3:rocky_upgrade_kit; fabrico:35 terra:15", "aoa3:nether_upgrade_kit; fabrico:35 ignis:15", "aoa3:floro_upgrade_kit; fabrico:35 herba:15", "aoa3:golden_upgrade_kit; fabrico:35 desiderium:15", "aoa3:clown_upgrade_kit; fabrico:35 vinculum:15", "aoa3:darkly_upgrade_kit; fabrico:35 tenebrae:15", "aoa3:runic_upgrade_kit; fabrico:35 potentia:15", "aoa3:light_upgrade_kit; fabrico:35 lux:15", "aoa3:weapon_parts; fabrico:35 instrumentum:15", "aoa3:lelyetian_upgrade_kit; fabrico:35 motus:15", "aoa3:abyss_tokens; permutatio:1 vitium:1", "aoa3:baron_tokens; permutatio:1 alienis:1", "aoa3:borean_tokens; permutatio:1 aqua:1", "aoa3:candyland_tokens; permutatio:1 vitreus:1", "aoa3:celeve_tokens; permutatio:1 humanus:1", "aoa3:creeponia_tokens; permutatio:1 perditio:1", "aoa3:crystevia_tokens; permutatio:1 vitreus:1", "aoa3:deeplands_tokens; permutatio:1 perditio:1", "aoa3:dungeon_tokens; permutatio:1 sensus:1", "aoa3:dustopia_tokens; permutatio:1 tenebrae:1", "aoa3:gardencia_tokens; permutatio:1 herba:1", "aoa3:greckon_tokens; permutatio:1 spiritus:1", "aoa3:haven_tokens; permutatio:1 ordo:1", "aoa3:iromine_tokens; permutatio:1 metallum:1", "aoa3:lelyetia_tokens; permutatio:1 motus:1", "aoa3:lunar_tokens; permutatio:1 alienis:1", "aoa3:mysterium_tokens; permutatio:1 victus:1", "aoa3:nether_tokens; permutatio:1 ignis:1", "aoa3:precasian_tokens; permutatio:1 bestia:1", "aoa3:runandor_tokens; permutatio:1 praecantatio:1", "aoa3:shyrelands_tokens; permutatio:1 aer:1", "aoa3:vox_ponds_tokens; permutatio:1 mortuus:1", "aoa3:blank_slab; potentia:2 vacuos:2 terra:2", "aoa3:penguin_slab; bestia:1 gelum:1", "aoa3:draggy_slab; bestia:1 praecantatio:1", "aoa3:compeer_slab; bestia:1 terra:1", "aoa3:waggy_slab; bestia:1 praecantatio:1", "aoa3:plateosaur_slab; bestia:1 praemunio:1", "aoa3:spraggy_slab; bestia:1 praecantatio:1", "aoa3:spikeback_slab; bestia:1 aversio:1", "aoa3:craggy_slab; bestia:1 praecantatio:1", "aoa3:hellquin_slab; bestia:1 ignis:1", "aoa3:horntail_slab; bestia:1 motus:1", "aoa3:rammerhorn_slab; bestia:1 aversio:1", "aoa3:shaddy_slab; bestia:1 praecantatio:1", "aoa3:corby_slab; bestia:1 aqua:1", "aoa3:gnawer_slab; bestia:1 herba:1", "aoa3:blissard_slab; bestia:1 gelum:1", "aoa3:ender_carrier_slab; bestia:1 alienis:1", "aoa3:goofer_slab; bestia:1 vinculum:1", "aoa3:mecha_cyclops_slab; bestia:1 machina:1", "aoa3:alluricorn_slab; bestia:1 potentia:1", "aoa3:construct_of_servility_slab; bestia:1 machina:1", "aoa3:healing_golem_slab; bestia:1 victus:1", "aoa3:mecha_skellox_slab; bestia:1 machina:1", "aoa3:bubble_berry_seeds; herba:5 victus:1", "aoa3:chilli_seeds; herba:5 victus:1", "aoa3:floracle_seeds; herba:5 victus:1", "aoa3:goldicap_seeds; herba:5 victus:1", "aoa3:heart_fruit_seeds; herba:5 victus:1", "aoa3:holly_top_seeds; herba:5 victus:1", "aoa3:lunacrike_seeds; herba:5 victus:1", "aoa3:luna_globe_seeds; herba:5 victus:1", "aoa3:lunalon_seeds; herba:5 victus:1", "aoa3:magic_marang_seeds; herba:5 victus:1", "aoa3:rosidon_seeds; herba:5 victus:1", "aoa3:tea_seeds; herba:5 victus:1", "aoa3:thorny_plant_seeds; herba:5 victus:1", "aoa3:trilliad_seeds; herba:5 victus:1", "aoa3:wind_rune; praecantatio:1 aer:1", "aoa3:fire_rune; praecantatio:1 ignis:1", "aoa3:water_rune; praecantatio:1 aqua:1", "aoa3:poison_rune; praecantatio:1 mortuus:1", "aoa3:wither_rune; praecantatio:1 perditio:1", "aoa3:lunar_rune; praecantatio:1 alienis:1", "aoa3:energy_rune; praecantatio:1 potentia:1", "aoa3:strike_rune; praecantatio:1 aversio:1", "aoa3:storm_rune; praecantatio:1 ordo:1", "aoa3:kinetic_rune; praecantatio:1 cognitio:1", "aoa3:power_rune; praecantatio:1 machina:1", "aoa3:compass_rune; praecantatio:1 motus:1", "aoa3:distortion_rune; praecantatio:1 vitium:1", "aoa3:life_rune; praecantatio:1 victus:1", "aoa3:lunarade_mug; vacuos:5 vitreus:3", "aoa3:cup; vacuos:5 victus:3", "aoa3:gravitator; volatus:20 motus:15 potentia:15", "aoa3:scream_shield; praemunio:20 spiritus:15 tenebrae:15", "aoa3:holly_arrow; aversio:5 terra:1 instrumentum:1 volatus:1 aer:1 praecantatio:1", "aoa3:spectral_holly_arrow; sensus:17 lux:15 praecantatio:1 aversio:1", "aoa3:pop_shot; metallum:1 alienis:1", "aoa3:balloon; aer:1 fabrico:1", "aoa3:discharge_capsule; vitreus:1 perditio:1", "aoa3:mecha_gear; machina:25 fabrico:25", "aoa3:magic_repair_dust; fabrico:10", "aoa3:whitewashing_solution; victus:10 perditio:10", "aoa3:archaic_dirt_breakable; terra:5 sensus:2", "aoa3:baron_ground; terra:5 alienis:2", "aoa3:abyss_stone; terra:5 vitium:2", "aoa3:barathos_hellstone; terra:5 ignis:2", "aoa3:baron_stone; terra:5 alienis:2", "aoa3:borean_dirt; terra:5 alienis:2", "aoa3:borean_stone; terra:5 alienis:2", "aoa3:candyland_dirt; terra:5 vitreus:2", "aoa3:celeve_dirt; terra:5 humanus:2", "aoa3:creeponia_dirt; terra:5 perditio:2", "aoa3:creep_stone; terra:5 perditio:2", "aoa3:primed_stone; terra:5 perditio:2", "aoa3:unstable_stone; terra:5 perditio:2", "aoa3:crystevia_stone; terra:5 vitreus:2", "aoa3:deeplands_stone; terra:5 perditio:2", "aoa3:dustopia_dirt; terra:5 tenebrae:2", "aoa3:dustopia_stone; terra:5 tenebrae:2", "aoa3:gardencia_dirt; terra:5 herba:2", "aoa3:gardencia_stone; terra:5 spiritus:2", "aoa3:greckon_dirt; terra:5 spiritus:2", "aoa3:greckon_stone; terra:5 spiritus:2", "aoa3:high_precasia_stone; terra:5 bestia:2", "aoa3:low_precasia_stone; terra:5 bestia:2", "aoa3:haven_dirt; terra:5 ordo:2", "aoa3:haven_stone; terra:5 ordo:2", "aoa3:iromine_stone; terra:5 metallum:2", "aoa3:lelyetia_stone; terra:5 motus:2", "aoa3:lunalyte_dirt; terra:5 alienis:2", "aoa3:lunasole_dirt; terra:5 alienis:2", "aoa3:mysterium_dirt; terra:5 victus:2", "aoa3:mysterium_stone; terra:5 victus:2", "aoa3:shyrelands_stone; terra:5 aer:2", "aoa3:toxic_dirt; terra:5 mortuus:2", "aoa3:toxic_stone; terra:5 mortuus:2", "aoa3:runic_stone; terra:5 potentia:2", "aoa3:baron_carpet; humanus:3 fabrico:2", "aoa3:borean_carpet; humanus:3 fabrico:2", "aoa3:gardencian_carpet; humanus:3 fabrico:2", "aoa3:iro_carpet; humanus:3 fabrico:2", "aoa3:lunar_carpet; humanus:3 fabrico:2", "aoa3:nature_melon_slice; victus:1", "aoa3:hot_rod; bestia:5 victus:5 ignis:5 fabrico:1", "aoa3:fiery_chops; bestia:5 victus:5 ignis:5 fabrico:1", "aoa3:yeti_fingernails; bestia:5 victus:5 terra:5", "aoa3:candy_cane; desiderium:1 potentia:1 aqua:1", "aoa3:candy_corn; desiderium:1 potentia:1 aqua:1", "aoa3:eye_candy; desiderium:1 potentia:1 aqua:1", "aoa3:peppermint_candy; desiderium:1 potentia:1 aqua:1", "aoa3:sour_candy; desiderium:1 potentia:1 aqua:1", "aoa3:sour_gummy; desiderium:1 potentia:1 aqua:1", "aoa3:sour_pop; desiderium:1 potentia:1 aqua:1", "aoa3:spearmint_candy; desiderium:1 potentia:1 aqua:1", "aoa3:gingerbread_cookie; desiderium:1 herba:1 victus:1", "aoa3:gingerbread_wing; desiderium:1 herba:1 victus:1", "aoa3:halycon_milk; metallum:33 victus:10 vacuos:5 bestia:5 aqua:5", "aoa3:lunarade; aqua:5 motus:5", "aoa3:tea; aqua:5 herba:5", "aoa3:fungal_tea; aqua:5 herba:5 sensus:5", "aoa3:natural_tea; aqua:5 herba:5 victus:5", "aoa3:rammerhead_hide; bestia:5 praemunio:5", "aoa3:urka_hide; bestia:5 praemunio:5", "aoa3:scrap_metal; metallum:1", "aoa3:white_druse; vitreus:5 praemunio:5", "aoa3:blue_druse; vitreus:5 volatus:5", "aoa3:red_druse; vitreus:5 aversio:5", "aoa3:green_druse; vitreus:5 spiritus:5", "aoa3:purple_druse; vitreus:5 cognitio:5", "aoa3:yellow_druse; vitreus:5 motus:5", "aoa3:small_purple_petal; herba:5 praemunio:5", "aoa3:small_blue_petal; herba:5 aversio:5", "aoa3:small_green_petal; herba:5 cognitio:5", "aoa3:small_red_petal; herba:5 victus:5", "aoa3:small_orange_petal; herba:5 potentia:5", "aoa3:yellow_spores; victus:5 ordo:5 sensus:5", "aoa3:orange_spores; victus:5 perditio:5 sensus:5", "aoa3:ghostly_powder; spiritus:5 exanimis:5 alkimia:5", "aoa3:phantasm; spiritus:5 mortuus:5 sensus:5", "aoa3:ghoulasm; exanimis:5 mortuus:5 sensus:5", "aoa3:nightmare_flakes; vitium:5 tenebrae:5 alkimia:5", "aoa3:ice_crystal; vitreus:5 gelum:5", "aoa3:orbulon; alienis:5 potentia:5 praecantatio:5", "aoa3:coral_stone; terra:10 aqua:10 perditio:5", "aoa3:void_scales; alienis:5 tenebrae:5 vacuos:5", "aoa3:rosid_root; herba:5 alkimia:5 victus:5", "aoa3:floating_stone; terra:5 volatus:5 aer:5", "aoa3:ivory; mortuus:5 victus:5 bestia:5", "aoa3:flammable_dust; ignis:15 perditio:10 alkimia:5", "aoa3:amphibiyte_lung; sensus:5 aqua:5 bestia:5", "aoa3:hive_chunk; alienis:5 bestia:5 terra:5", "aoa3:chitin; bestia:5 praemunio:5", "aoa3:enchanted_gunpowder; perditio:10 ignis:10 praecantatio:5 alkimia:5", "aoa3:unstable_gunpowder; perditio:15 ignis:10 alkimia:5", "aoa3:confetti_pile; humanus:5 fabrico:5 aer:5", "aoa3:darkly_powder; tenebrae:5 mortuus:5 alienis:5", "aoa3:runic_energy; potentia:5 praecantatio:5 auram:5", "aoa3:power_core; potentia:5 praecantatio:5 machina:5", "aoa3:mushroom_spores; victus:5 herba:5 sensus:5", "aoa3:magnet_shard; metallum:5 motus:5 terra:5", "aoa3:torn_cloth; fabrico:5 praemunio:5 humanus:5", "aoa3:sharp_claw; bestia:5 aversio:5 victus:5", "aoa3:dense_rock; terra:15 perditio:10", "aoa3:jungle_thorns; terra:10 herba:5 victus:5", "aoa3:primordial_skull; spiritus:10 exanimis:10 tenebrae:10", "aoa3:zhinx_dust; motus:5 lux:5 ordo:5", "aoa3:toxic_lump; mortuus:5 vitium:5 perditio:5", "aoa3:runium_chunk; potentia:5 vacuos:5", "aoa3:chestbone_fragment; mortuus:10 victus:10", "aoa3:skullbone_fragment; mortuus:10 victus:10", "aoa3:legbone_fragment; mortuus:10 victus:10", "aoa3:footbone_fragment; mortuus:10 victus:10", "aoa3:charged_runium_chunk; potentia:10 vacuos:10", "aoa3:jade; vitreus:10 desiderium:10", "aoa3:amethyst; vitreus:15 desiderium:15", "aoa3:sapphire; vitreus:15 desiderium:15", "aoa3:gemenyte; vitreus:10 desiderium:10 perditio:5", "aoa3:ornamyte; vitreus:10 desiderium:10 perditio:5", "aoa3:jewelyte; vitreus:10 desiderium:10 perditio:5", "aoa3:bloodstone; vitreus:15 desiderium:15 vitium:5", "aoa3:crystallite; vitreus:15 desiderium:15 ordo:5", "aoa3:blue_gemstones; vitreus:15 desiderium:15 permutatio:5", "aoa3:green_gemstones; vitreus:15 desiderium:15 permutatio:5", "aoa3:purple_gemstones; vitreus:15 desiderium:15 permutatio:5", "aoa3:red_gemstones; vitreus:15 desiderium:15 permutatio:5", "aoa3:white_gemstones; vitreus:15 desiderium:15 permutatio:5", "aoa3:yellow_gemstones; vitreus:15 desiderium:15 permutatio:5", "aoa3:blue_crystal; vitreus:15 desiderium:15 praecantatio:5", "aoa3:green_crystal; vitreus:15 desiderium:15 praecantatio:5", "aoa3:purple_crystal; vitreus:15 desiderium:15 praecantatio:5", "aoa3:red_crystal; vitreus:15 desiderium:15 praecantatio:5", "aoa3:white_crystal; vitreus:15 desiderium:15 praecantatio:5", "aoa3:yellow_crystal; vitreus:15 desiderium:15 praecantatio:5", "aoa3:shyregem; vitreus:18 desiderium:18 potentia:10 auram:5", "aoa3:limonite_ingot; metallum:10 perditio:5", "aoa3:rosite_ingot; metallum:10 fabrico:5", "aoa3:skeletal_ingot; mortuus:10 victus:10 metallum:5", "aoa3:emberstone_ingot; metallum:10 ignis:5", "aoa3:mystite_ingot; metallum:15 victus:5", "aoa3:lyon_ingot; metallum:15 instrumentum:5", "aoa3:varsium_ingot; metallum:10 alienis:5", "aoa3:elecanium_ingot; metallum:15 potentia:10", "aoa3:blazium_ingot; metallum:10 ignis:10", "aoa3:ghastly_ingot; metallum:15 spiritus:10", "aoa3:ghoulish_ingot; metallum:15 exanimis:10", "aoa3:lunar_ingot; metallum:15 alienis:10", "aoa3:baronyte_ingot; metallum:10 alienis:5", "aoa3:rusted_iron_ingot; metallum:2 perditio:2", "aoa3:shyrestone_ingot; metallum:18 potentia:10 praecantatio:10 auram:5", "aoa3:raw_candlefish; bestia:5 victus:5 aqua:5", "aoa3:raw_crimson_skipper; bestia:5 victus:5 aqua:5", "aoa3:raw_crimson_stripefish; bestia:5 victus:5 aqua:5", "aoa3:raw_dark_hatchetfish; bestia:5 victus:5 aqua:5", "aoa3:raw_fingerfish; bestia:5 victus:5 aqua:5", "aoa3:raw_golden_gullfish; bestia:5 victus:5 aqua:5", "aoa3:raw_ironback; bestia:5 victus:5 aqua:5", "aoa3:raw_limefish; bestia:5 victus:5 aqua:5", "aoa3:raw_pearl_stripefish; bestia:5 victus:5 aqua:5", "aoa3:raw_rainbowfish; bestia:5 victus:5 aqua:5", "aoa3:raw_razorfish; bestia:5 victus:5 aqua:5", "aoa3:raw_rocketfish; bestia:5 victus:5 aqua:5", "aoa3:raw_sailback; bestia:5 victus:5 aqua:5", "aoa3:raw_sapphire_strider; bestia:5 victus:5 aqua:5", "aoa3:raw_turquoise_stripefish; bestia:5 victus:5 aqua:5", "aoa3:raw_violet_skipper; bestia:5 victus:5 aqua:5", "aoa3:candlefish; bestia:5 victus:5 fabrico:1", "aoa3:crimson_skipper; bestia:5 victus:5 fabrico:1", "aoa3:crimson_stripefish; bestia:5 victus:5 fabrico:1", "aoa3:dark_hatchetfish; bestia:5 victus:5 fabrico:1", "aoa3:fingerfish; bestia:5 victus:5 fabrico:1", "aoa3:golden_gullfish; bestia:5 victus:5 fabrico:1", "aoa3:ironback; bestia:5 victus:5 fabrico:1", "aoa3:limefish; bestia:5 victus:5 fabrico:1", "aoa3:pearl_stripefish; bestia:5 victus:5 fabrico:1", "aoa3:rainbowfish; bestia:5 victus:5 fabrico:1", "aoa3:razorfish; bestia:5 victus:5 fabrico:1", "aoa3:rocketfish; bestia:5 victus:5 fabrico:1", "aoa3:sailback; bestia:5 victus:5 fabrico:1", "aoa3:sapphire_strider; bestia:5 victus:5 fabrico:1", "aoa3:turquoise_stripefish; bestia:5 victus:5 fabrico:1", "aoa3:violet_skipper; bestia:5 victus:5 fabrico:1", "aoa3:raw_ursa_meat; bestia:5 victus:5 terra:5", "aoa3:raw_halycon_beef; bestia:5 victus:5 terra:5", "aoa3:raw_furlion_chop; bestia:5 victus:5 terra:5", "aoa3:raw_chimera_chop; bestia:5 victus:5 terra:5", "aoa3:raw_charger_shank; bestia:5 victus:5 terra:5", "aoa3:charger_shank; bestia:5 victus:5 fabrico:1", "aoa3:chimera_chop; bestia:5 victus:5 fabrico:1", "aoa3:furlion_chop; bestia:5 victus:5 fabrico:1", "aoa3:halycon_beef; bestia:5 victus:5 fabrico:1", "aoa3:ursa_meat; bestia:5 victus:5 fabrico:1", "aoa3:runium_ore; potentia:5 vacuos:5 terra:5", "aoa3:chestbone_fragments_ore; mortuus:10 victus:10 terra:5", "aoa3:skullbone_fragments_ore; mortuus:10 victus:10 terra:5", "aoa3:legbone_fragments_ore; mortuus:10 victus:10 terra:5", "aoa3:footbone_fragments_ore; mortuus:10 victus:10 terra:5", "aoa3:charged_runium_ore; potentia:10 vacuos:10 terra:5", "aoa3:jade_ore; vitreus:10 desiderium:10 terra:5", "aoa3:amethyst_ore; vitreus:15 desiderium:15 terra:5", "aoa3:sapphire_ore; vitreus:15 desiderium:15 terra:5", "aoa3:gemenyte_ore; vitreus:10 desiderium:10 perditio:5 terra:5", "aoa3:ornamyte_ore; vitreus:10 desiderium:10 perditio:5 terra:5", "aoa3:jewelyte_ore; vitreus:10 desiderium:10 perditio:5 terra:5", "aoa3:bloodstone_ore; vitreus:15 desiderium:15 vitium:5 terra:5", "aoa3:crystallite_ore; vitreus:15 desiderium:15 ordo:5 terra:5", "aoa3:blue_crystal_ore; vitreus:15 desiderium:15 praecantatio:5 terra:5", "aoa3:green_crystal_ore; vitreus:15 desiderium:15 praecantatio:5 terra:5", "aoa3:purple_crystal_ore; vitreus:15 desiderium:15 praecantatio:5 terra:5", "aoa3:red_crystal_ore; vitreus:15 desiderium:15 praecantatio:5 terra:5", "aoa3:white_crystal_ore; vitreus:15 desiderium:15 praecantatio:5 terra:5", "aoa3:yellow_crystal_ore; vitreus:15 desiderium:15 praecantatio:5 terra:5", "aoa3:shyregem_ore; vitreus:18 desiderium:18 potentia:10 auram:5 terra:5", "aoa3:limonite_ore; metallum:10 perditio:5 terra:5", "aoa3:rosite_ore; metallum:10 fabrico:5 terra:5", "aoa3:emberstone_ore; metallum:10 ignis:5 terra:5", "aoa3:mystite_ore; metallum:15 victus:5 terra:5", "aoa3:lyon_ore; metallum:15 instrumentum:5 terra:5", "aoa3:varsium_ore; metallum:10 alienis:5 terra:5", "aoa3:elecanium_ore; metallum:15 potentia:10 terra:5", "aoa3:blazium_ore; metallum:10 ignis:10 terra:5", "aoa3:ghastly_ore; metallum:15 spiritus:10 terra:5", "aoa3:ghoulish_ore; metallum:15 exanimis:10 terra:5", "aoa3:baronyte_ore; metallum:10 alienis:5 terra:5", "aoa3:shyrestone_ore; metallum:18 potentia:10 praecantatio:10 auram:5 terra:5", "aoa3:arcbulb; herba:5 sensus:5 victus:1", "aoa3:arcflower; herba:5 sensus:5 victus:1", "aoa3:celevians_blue; herba:5 sensus:5 victus:1", "aoa3:celevians_purple; herba:5 sensus:5 victus:1", "aoa3:celevians_red; herba:5 sensus:5 victus:1", "aoa3:celevians_white; herba:5 sensus:5 victus:1", "aoa3:creep_flowers; herba:5 sensus:5 victus:1", "aoa3:daileers; herba:5 sensus:5 victus:1", "aoa3:dawn_bush; herba:5 sensus:5 victus:1", "aoa3:dawn_flower; herba:5 sensus:5 victus:1", "aoa3:daylooms_yellow; herba:5 sensus:5 victus:1", "aoa3:daylooms_pink; herba:5 sensus:5 victus:1", "aoa3:daylooms_blue; herba:5 sensus:5 victus:1", "aoa3:haunted_flower; herba:5 sensus:5 victus:1", "aoa3:horizon_daisies; herba:5 sensus:5 victus:1", "aoa3:lylips; herba:5 sensus:5 victus:1", "aoa3:magias; herba:5 sensus:5 victus:1", "aoa3:ocealites_blue; herba:5 sensus:5 victus:1", "aoa3:runic_bush; herba:5 sensus:5 victus:1", "aoa3:shyre_weed; herba:5 sensus:5 victus:1", "aoa3:bureal_stocks; herba:5 aer:1", "aoa3:candycane; herba:5 aer:1", "aoa3:candy_grass; herba:5 aer:1", "aoa3:blue_candy_grass; herba:5 aer:1", "aoa3:creep_grass; herba:5 aer:1", "aoa3:blue_crystal_plant; herba:5 aer:1", "aoa3:green_crystal_plant; herba:5 aer:1", "aoa3:purple_crystal_plant; herba:5 aer:1", "aoa3:red_crystal_plant; herba:5 aer:1", "aoa3:white_crystal_plant; herba:5 aer:1", "aoa3:yellow_crystal_plant; herba:5 aer:1", "aoa3:dawn_grass; herba:5 aer:1", "aoa3:dead_grass; herba:5 aer:1", "aoa3:deep_blooms; herba:5 aer:1", "aoa3:deep_grass; herba:5 aer:1", "aoa3:haven_grass_plant; herba:5 aer:1", "aoa3:iro_grass; herba:5 aer:1", "aoa3:irotops; herba:5 aer:1", "aoa3:lelyetian_grass; herba:5 aer:1", "aoa3:lelyetian_grass_down; herba:5 aer:1", "aoa3:lucon_grass; herba:5 aer:1", "aoa3:lunalip; herba:5 aer:1", "aoa3:luntar; herba:5 aer:1", "aoa3:lurchians; herba:5 aer:1", "aoa3:mystic_bush; herba:5 aer:1", "aoa3:mystic_ferns; herba:5 aer:1", "aoa3:rainbow_grass; herba:5 aer:1", "aoa3:rainbow_grass2; herba:5 aer:1", "aoa3:rainbow_grass3; herba:5 aer:1", "aoa3:rune_bulbs; herba:5 aer:1", "aoa3:tangle_thorns; herba:5 aer:1", "aoa3:trilliad_bloom; herba:5 aer:1", "aoa3:tubeicles; herba:5 aer:1", "aoa3:green_waterweeds; herba:5 aer:1", "aoa3:red_waterweeds; herba:5 aer:1", "aoa3:white_waterweeds; herba:5 aer:1", "aoa3:yellow_waterweeds; herba:5 aer:1", "aoa3:blue_lollypop; herba:5 aer:1", "aoa3:red_lollypop; herba:5 aer:1", "aoa3:yellow_lollypop; herba:5 aer:1", "aoa3:ancient_vines; herba:5 aer:1", "aoa3:ancient_vines_cap; herba:5 aer:1", "aoa3:blood_pine_stem; herba:5 aer:1", "aoa3:blood_pine; herba:5 aer:1", "aoa3:blood_spikes; herba:5 aer:1", "aoa3:blood_strands; herba:5 aer:1", "aoa3:bulb_stock; herba:5 aer:1", "aoa3:bulb_stock_cap; herba:5 aer:1", "aoa3:celebulbs_stem; herba:5 aer:1", "aoa3:celebulbs_green; herba:5 aer:1", "aoa3:celebulbs_yellow; herba:5 aer:1", "aoa3:coral_cage; herba:5 aer:1", "aoa3:dawnwood_bars; herba:5 aer:1", "aoa3:eye_shrub_stem; herba:5 aer:1", "aoa3:eye_shrub; herba:5 aer:1", "aoa3:garden_grass; herba:5 aer:1", "aoa3:havendales_blue_stem; herba:5 aer:1", "aoa3:havendales_pink_stem; herba:5 aer:1", "aoa3:havendales_yellow_stem; herba:5 aer:1", "aoa3:havendales_blue; herba:5 aer:1", "aoa3:havendales_pink; herba:5 aer:1", "aoa3:havendales_yellow; herba:5 aer:1", "aoa3:lelyetian_stem; herba:5 aer:1", "aoa3:lelyetian_stem_cap; herba:5 aer:1", "aoa3:lelyetian_stem_cap_down; herba:5 aer:1", "aoa3:lelyetian_wiggler; herba:5 aer:1", "aoa3:lelyetian_wiggler_bottom; herba:5 aer:1", "aoa3:lelyetian_wiggler_top; herba:5 aer:1", "aoa3:green_peppermint; herba:5 aer:1", "aoa3:red_peppermint; herba:5 aer:1", "aoa3:plastic_stick; herba:5 aer:1", "aoa3:candy_tube; herba:5 aer:1", "aoa3:shyre_stock; herba:5 aer:1", "aoa3:shyre_cap; herba:5 aer:1", "aoa3:shyre_cap_down; herba:5 aer:1", "aoa3:vox_fungi_stem; herba:5 aer:1", "aoa3:vox_fungi; herba:5 aer:1", "aoa3:vox_tentacles_stem; herba:5 aer:1", "aoa3:vox_tentacles; herba:5 aer:1", "aoa3:creep_vines; herba:5 aer:1", "aoa3:abyss_grass; terra:5 vitium:2 herba:2", "aoa3:borean_grass; terra:5 alienis:2 herba:2", "aoa3:candyland_grass; terra:5 vitreus:2 herba:2", "aoa3:celeve_grass; terra:5 humanus:2 herba:2", "aoa3:creeponia_grass; terra:5 perditio:2 herba:2", "aoa3:dustopia_grass; terra:5 tenebrae:2 herba:2", "aoa3:gardencia_grass; terra:5 herba:2 herba:2", "aoa3:greckon_grass; terra:5 spiritus:2 herba:2", "aoa3:precasia_grass; terra:5 bestia:2 herba:2", "aoa3:haven_grass; terra:5 ordo:2 herba:2", "aoa3:iromine_grass; terra:5 metallum:2 herba:2", "aoa3:lelyetia_grass; terra:5 motus:2 herba:2", "aoa3:lelyetia_down_grass; terra:5 motus:2 herba:2", "aoa3:lunalyte_grass; terra:5 alienis:2 herba:2", "aoa3:lunasole_grass; terra:5 alienis:2 herba:2", "aoa3:mysterium_grass; terra:5 victus:2 herba:2", "aoa3:shyrelands_grass; terra:5 aer:2 herba:2", "aoa3:toxic_grass; terra:5 mortuus:2 herba:2", "aoa3:runic_grass; terra:5 potentia:2 herba:2", "aoa3:baron_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:black_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:bloodstone_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:blue_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:brown_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:coral_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:creeponia_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:crystallite_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:crystevia_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:cyan_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:dark_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:dark_blue_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:dark_grey_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:darkwash_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:gardencia_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:greckon_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:green_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:grey_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:haunted_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:iro_dotted_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:iro_striped_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:lelyetia_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:lime_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:lunar_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:magenta_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:black_mysterium_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:green_mysterium_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:orange_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:pink_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:purple_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:red_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:rosidian_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:runic_construct_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:white_shyre_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:yellow_shyre_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:skeletal_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:white_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:whitewash_bricks; terra:3 fabrico:2 praemunio:1", "aoa3:yellow_bricks; terra:3 fabrico:2 praemunio:1"};
		}

		@Config.Comment("Configure Thermal Expansion integration options")
		@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion")
		public static final SubCategoryThermalExpansion thermalExpansion = new SubCategoryThermalExpansion();

		public static class SubCategoryThermalExpansion {
			@Config.Comment("Set this to false to disable all integrations with Thermal Expansion when the mod is present.")
			@Config.LangKey("gui.aoaintegrationsconfig.enabled")
			@Config.RequiresMcRestart
			public boolean enabled = true;

			@Config.Comment("Set this to false to disable AoA integration pulverizer recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.pulverizer")
			public boolean pulverizerRecipes = true;

			@Config.Comment("Set this to false to disable AoA integration sawmill recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.sawmill")
			public boolean sawmillRecipes = true;

			@Config.Comment("Set this to false to disable AoA integration Phytogenic Insolator recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.phytogenicInsolator")
			public boolean phytogenicInsolatorRecipes = true;

			@Config.Comment("Set this to false to disable AoA integration Fractionating Still recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.fractionatingStill")
			public boolean fractionatingStillRecipes = true;

			@Config.Comment("Set this to false to disable AoA integration Glacial Precipitator recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.glacialPrecipitator")
			public boolean glacialPrecipitatorRecipes = true;

			@Config.Comment("Set this to false to disable AoA integration Arboreal Extractor recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.arborealExtractor")
			public boolean arborealExtractorRecipes = true;

			@Config.Comment("Set this to false to disable AoA integration Enervation Dynamo recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.enervationDynamo")
			public boolean enervationDynamoRecipes = true;

			@Config.Comment("Set this to false to disable AoA integration Numismatic Dynamo recipes")
			@Config.LangKey("gui.aoaintegrationsconfig.thermalExpansion.numismaticDynamo")
			public boolean numismaticDynamoRecipes = true;
		}
	}

	public static void changeMainWindowTheme(String themeKey) {
		MainConfig.mainWindowTheme = themeKey;
		postConfigChange("aoa/main_config", false);
	}

	private static void postConfigChange(String configPath, boolean requiresRestart) {
		ConfigChangedEvent ev = new ConfigChangedEvent.OnConfigChangedEvent("aoa3", configPath, Minecraft.getMinecraft().world != null, requiresRestart);

		MinecraftForge.EVENT_BUS.post(ev);

		if (!ev.getResult().equals(Event.Result.DENY))
			MinecraftForge.EVENT_BUS.post(new ConfigChangedEvent.PostConfigChangedEvent("aoa3", configPath, Minecraft.getMinecraft() != null, requiresRestart));
	}

	public static int getDimensionId(Enums.Dimensions dim) {
		switch (dim) {
			case ABYSS:
				return MainConfig.dimensionIds.abyss;
			case ANCIENT_CAVERN:
				return MainConfig.dimensionIds.ancientCavern;
			case BARATHOS:
				return MainConfig.dimensionIds.barathos;
			case CANDYLAND:
				return MainConfig.dimensionIds.candyland;
			case CELEVE:
				return MainConfig.dimensionIds.celeve;
			case CREEPONIA:
				return MainConfig.dimensionIds.creeponia;
			case CRYSTEVIA:
				return MainConfig.dimensionIds.crystevia;
			case DEEPLANDS:
				return MainConfig.dimensionIds.deeplands;
			case DUSTOPIA:
				return MainConfig.dimensionIds.dustopia;
			case GARDENCIA:
				return MainConfig.dimensionIds.gardencia;
			case GRECKON:
				return MainConfig.dimensionIds.greckon;
			case HAVEN:
				return MainConfig.dimensionIds.haven;
			case IMMORTALLIS:
				return MainConfig.dimensionIds.immortallis;
			case IROMINE:
				return MainConfig.dimensionIds.iromine;
			case LBOREAN:
				return MainConfig.dimensionIds.lborean;
			case LELYETIA:
				return MainConfig.dimensionIds.lelyetia;
			case LUNALUS:
				return MainConfig.dimensionIds.lunalus;
			case MYSTERIUM:
				return MainConfig.dimensionIds.mysterium;
			case NETHER:
				return -1;
			case PRECASIA:
				return MainConfig.dimensionIds.precasia;
			case RUNANDOR:
				return MainConfig.dimensionIds.runandor;
			case SHYRELANDS:
				return MainConfig.dimensionIds.shyrelands;
			case THE_END:
				return 1;
			case VOX_PONDS:
				return MainConfig.dimensionIds.voxPonds;
			case OVERWORLD:
			default:
				return 0;
		}
	}

	public static void doDebuggingPrintout(Logger logger) {
		logger.log(Level.INFO, "Printing out current configuration settings...");

		logger.log(Level.INFO, "AllowUnsafeInfusion: " + MainConfig.allowUnsafeInfusion);
		logger.log(Level.INFO, "DisableOverworldMobs: " + MainConfig.disableOverworldMobs);
		logger.log(Level.INFO, "DoVerboseDebugging: " + MainConfig.doVerboseDebugging);
		logger.log(Level.INFO, "EasyCorruptedTravellers: " + MainConfig.easyCorruptedTravellers);
		logger.log(Level.INFO, "GlobalXpModifier: " + MainConfig.globalXpModifier);
		logger.log(Level.INFO, "HudResourcesHorizonal: " + MainConfig.hudResourcesHorizontal);
		logger.log(Level.INFO, "HudResourcesPosition: " + MainConfig.hudResourcesPosition.toString());
		logger.log(Level.INFO, "LeaderboardCapacity: " + MainConfig.leaderboardCapacity);
		logger.log(Level.INFO, "LeaderboardEnabled: " + MainConfig.leaderboardEnabled);
		logger.log(Level.INFO, "MainWindowPausesGame: " + MainConfig.mainWindowPausesGame);
		logger.log(Level.INFO, "MainWindowTheme: " + MainConfig.mainWindowTheme);
		logger.log(Level.INFO, "PersonalHaloPreference: " + MainConfig.personalHaloPreference.toString());
		logger.log(Level.INFO, "PortalSearchRadius: " + MainConfig.portalSearchRadius);
		logger.log(Level.INFO, "ResourcesEnabled: " + MainConfig.resourcesEnabled);
		logger.log(Level.INFO, "ShowDailyMessages: " + MainConfig.showDailyMessages);
		logger.log(Level.INFO, "ShowPlayerHalos: " + MainConfig.showPlayerHalos);
		logger.log(Level.INFO, "ShowVanityLevels: " + MainConfig.showVanityLevels);
		logger.log(Level.INFO, "ShowXpParticles: " + MainConfig.showXpParticles);
		logger.log(Level.INFO, "SkillsEnabled: " + MainConfig.skillsEnabled);
		logger.log(Level.INFO, "UseToasts: " + MainConfig.useToasts);
		logger.log(Level.INFO, "AlwaysChargers: " + MainConfig.funOptions.alwaysChargers);
		logger.log(Level.INFO, "HardcoreMode: " + MainConfig.funOptions.hardcoreMode);
		logger.log(Level.INFO, "PartyDeaths: " + MainConfig.funOptions.partyDeaths);
		logger.log(Level.INFO, "JustEnoughResources Enabled: " + IntegrationsConfig.jer.enabled);
		logger.log(Level.INFO, "Tinkers Construct Enabled: " + IntegrationsConfig.tinkersConstruct.enabled);
		logger.log(Level.INFO, "IndustrialCraft2 Enabled: " + IntegrationsConfig.ic2.enabled);
	}
}
