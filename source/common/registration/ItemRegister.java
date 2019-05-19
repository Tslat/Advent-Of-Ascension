package net.tslat.aoa3.common.registration;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.item.food.*;
import net.tslat.aoa3.item.lootbox.*;
import net.tslat.aoa3.item.minionslab.*;
import net.tslat.aoa3.item.misc.*;
import net.tslat.aoa3.item.misc.summon.*;
import net.tslat.aoa3.item.special.AnimaStone;
import net.tslat.aoa3.item.special.HeartStone;
import net.tslat.aoa3.item.tool.misc.BloodAccumulator;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.item.totem.*;

@Mod.EventBusSubscriber
public class ItemRegister {
	public static final AmmoItem balloon = new AmmoItem("Balloon", "balloon");
	public static final ItemTieredBullet bulletLimonite = new ItemTieredBullet("LimoniteBullet", "limonite_bullet");
	public static final AmmoItem cannonball = new AmmoItem("Cannonball", "cannonball");
	public static final Chilli chilli = new Chilli();
	public static final AmmoItem dischargeCapsule = new AmmoItem("DischargeCapsule", "discharge_capsule");
	public static final HollyArrow hollyArrow = new HollyArrow("HollyArrow", "holly_arrow");
	public static final SpectralHollyArrow hollyArrowSpectral = new SpectralHollyArrow("SpectralHollyArrow", "spectral_holly_arrow");
	public static final TippedHollyArrow hollyArrowTipped = new TippedHollyArrow("TippedHollyArrow", "tipped_holly_arrow");
	public static final AmmoItem metalSlug = new AmmoItem("MetalSlug", "metal_slug");
	public static final PopShot popShot = new PopShot("PopShot", "pop_shot");
	public static final AmmoItem spreadshot = new AmmoItem("Spreadshot", "spreadshot");

	public static final SimpleItem amethyst = new SimpleItem("Amethyst", "amethyst");
	public static final SimpleItem ingotBaronyte = new SimpleItem("BaronyteIngot", "baronyte_ingot");
	public static final SimpleItem ingotBlazium = new SimpleItem("BlaziumIngot", "blazium_ingot");
	public static final SimpleItem ingotElecanium = new SimpleItem("ElecaniumIngot", "elecanium_ingot");
	public static final SimpleItem ingotEmberstone = new SimpleItem("EmberstoneIngot", "emberstone_ingot");
	public static final SimpleItem ingotGhastly = new SimpleItem("GhastlyIngot", "ghastly_ingot");
	public static final SimpleItem ingotGhoulish = new SimpleItem("GhoulishIngot", "ghoulish_ingot");
	public static final SimpleItem jade = new SimpleItem("Jade", "jade");
	public static final SimpleItem ingotLimonite = new SimpleItem("LimoniteIngot", "limonite_ingot");
	public static final SimpleItem ingotLunar = new SimpleItem("LunarIngot", "lunar_ingot");
	public static final SimpleItem ingotLyon = new SimpleItem("LyonIngot", "lyon_ingot");
	public static final SimpleItem ingotMystite = new SimpleItem("MystiteIngot", "mystite_ingot");
	public static final SimpleItem ingotRosite = new SimpleItem("RositeIngot", "rosite_ingot");
	public static final SimpleItem ingotRustedIron = new SimpleItem("RustedIronIngot", "rusted_iron_ingot");
	public static final SimpleItem sapphire = new SimpleItem("Sapphire", "sapphire");
	public static final SimpleItem shyregem = new SimpleItem("Shyregem", "shyregem");
	public static final SimpleItem ingotShyrestone = new SimpleItem("ShyrestoneIngot", "shyrestone_ingot");
	public static final SimpleItem ingotSkeletal = new SimpleItem("SkeletalIngot", "skeletal_ingot");
	public static final SimpleItem ingotVarsium = new SimpleItem("VarsiumIngot", "varsium_ingot");

	public static final SimpleItem boneFragmentChestbone = new SimpleItem("ChestboneFragment", "chestbone_fragment");
	public static final SimpleItem boneFragmentFootbone = new SimpleItem("FootboneFragment", "footbone_fragment");
	public static final SimpleItem boneFragmentLegbone = new SimpleItem("LegboneFragment", "legbone_fragment");
	public static final SimpleItem boneFragmentSkullbone = new SimpleItem("SkullboneFragment", "skullbone_fragment");
	public static final SimpleItem chargedRuniumChunk = new SimpleItem("ChargedRuniumChunk", "charged_runium_chunk");
	public static final SimpleItem crystalBlue = new SimpleItem("BlueCrystal", "blue_crystal");
	public static final SimpleItem crystalGreen = new SimpleItem("GreenCrystal", "green_crystal");
	public static final SimpleItem crystalPurple = new SimpleItem("PurpleCrystal", "purple_crystal");
	public static final SimpleItem crystalRed = new SimpleItem("RedCrystal", "red_crystal");
	public static final SimpleItem crystalWhite = new SimpleItem("WhiteCrystal", "white_crystal");
	public static final SimpleItem crystalYellow = new SimpleItem("YellowCrystal", "yellow_crystal");
	public static final SimpleItem gemBloodstone = new SimpleItem("Bloodstone", "bloodstone");
	public static final SimpleItem gemCrystallite = new SimpleItem("Crystallite", "crystallite");
	public static final SimpleItem gemGemenyte = new SimpleItem("Gemenyte", "gemenyte");
	public static final SimpleItem gemJewelyte = new SimpleItem("Jewelyte", "jewelyte");
	public static final SimpleItem gemOrnamyte = new SimpleItem("Ornamyte", "ornamyte");
	public static final SimpleItem runiumChunk = new SimpleItem("RuniumChunk", "runium_chunk");

	public static final RuneItem runeCharged = new RuneItem("ChargedRune", "charged_rune", false);
	public static final RuneItem runeCompass = new RuneItem("CompassRune", "compass_rune", true, BlockRegister.runePostCompass);
	public static final RuneItem runeDistortion = new RuneItem("DistortionRune", "distortion_rune", true, BlockRegister.runePostDistortion);
	public static final RuneItem runeEnergy = new RuneItem("EnergyRune", "energy_rune", false, BlockRegister.runePostEnergy);
	public static final RuneItem runeFire = new RuneItem("FireRune", "fire_rune", false, BlockRegister.runePostFire);
	public static final RuneItem runeKinetic = new RuneItem("KineticRune", "kinetic_rune", true, BlockRegister.runePostKinetic);
	public static final RuneItem runeLife = new RuneItem("LifeRune", "life_rune", true, BlockRegister.runePostLife);
	public static final RuneItem runeLunar = new RuneItem("LunarRune", "lunar_rune", true, BlockRegister.runePostLunar);
	public static final RuneItem runePoison = new RuneItem("PoisonRune", "poison_rune", false, BlockRegister.runePostPoison);
	public static final RuneItem runePower = new RuneItem("PowerRune", "power_rune", false, BlockRegister.runePostPower);
	public static final RuneItem runeStorm = new RuneItem("StormRune", "storm_rune", true, BlockRegister.runePostStorm);
	public static final RuneItem runeStrike = new RuneItem("StrikeRune", "strike_rune", false, BlockRegister.runePostStrike);
	public static final RuneItem runeUnpowered = new RuneItem("UnpoweredRune", "unpowered_rune", false);
	public static final RuneItem runeWater = new RuneItem("WaterRune", "water_rune", false, BlockRegister.runePostWater);
	public static final RuneItem runeWind = new RuneItem("WindRune", "wind_rune", false, BlockRegister.runePostWind);
	public static final RuneItem runeWither = new RuneItem("WitherRune", "wither_rune", false, BlockRegister.runePostWither);

	public static final EyeBulb eyeBulb = new EyeBulb();
	public static final FragmentedAnimaStone fragmentedAnimaStone = new FragmentedAnimaStone();
	public static final Gravitator gravitator = new Gravitator();
	public static final HollyTopPetals hollyTopPetals = new HollyTopPetals();
	public static final ReturnCrystal returnCrystal = new ReturnCrystal();
	public static final SimpleItem activeRuneStone = new SimpleItem("ActiveRuneStone", "active_rune_stone");
	public static final SimpleItem amphibiyteLung = new SimpleItem("AmphibiyteLung", "amphibiyte_lung");
	public static final SimpleItem apocoStone = new SimpleItem("ApocoStone", "apoco_stone");
	public static final SimpleItem blankSlab = new SimpleItem("BlankSlab", "blank_slab");
	public static final SimpleItem circusCoin = new SimpleItem("CircusCoin", "circus_coin");
	public static final SimpleItem coinCopper = new SimpleItem("CopperCoin", "copper_coin");
	public static final SimpleItem coinGold = new SimpleItem("GoldCoin", "gold_coin");
	public static final SimpleItem coinLunaver = new SimpleItem("LunaverCoin", "lunaver_coin");
	public static final SimpleItem coinSilver = new SimpleItem("SilverCoin", "silver_coin");
	public static final SimpleItem coralStone = new SimpleItem("CoralStone", "coral_stone");
	public static final SimpleItem cup = new SimpleItem("Cup", "cup");
	public static final SimpleItem darkBones = new SimpleItem("DarkBones", "dark_bones");
	public static final SimpleItem darklyPowder = new SimpleItem("DarklyPowder", "darkly_powder");
	public static final SimpleItem desertShell = new SimpleItem("DesertShell", "desert_shell");
	public static final SimpleItem doomStone = new SimpleItem("DoomStone", "doom_stone");
	public static final SimpleItem fleshyBones = new SimpleItem("FleshyBones", "fleshy_bones");
	public static final SimpleItem gemstonesBlue = new SimpleItem("BlueGemstones", "blue_gemstones");
	public static final SimpleItem gemstonesGreen = new SimpleItem("GreenGemstones", "green_gemstones");
	public static final SimpleItem gemstonesPurple = new SimpleItem("PurpleGemstones", "purple_gemstones");
	public static final SimpleItem gemstonesRed = new SimpleItem("RedGemstones", "red_gemstones");
	public static final SimpleItem gemstonesWhite = new SimpleItem("WhiteGemstones", "white_gemstones");
	public static final SimpleItem gemstonesYellow = new SimpleItem("YellowGemstones", "yellow_gemstones");
	public static final SimpleItem ghostlyPowder = new SimpleItem("GhostlyPowder", "ghostly_powder");
	public static final SimpleItem ghoulasm = new SimpleItem("Ghoulasm", "ghoulasm");
	public static final SimpleItem hydroStone = new SimpleItem("HydroStone", "hydro_stone");
	public static final SimpleItem iceCrystal = new SimpleItem("IceCrystal", "ice_crystal");
	public static final SimpleItem impureGold = new SimpleItem("ImpureGold", "impure_gold");
	public static final SimpleItem ivory = new SimpleItem("Ivory", "ivory");
	public static final SimpleItem jungleThorns = new SimpleItem("JungleThorns", "jungle_thorns");
	public static final SimpleItem lunaradeMug = new SimpleItem("LunaradeMug", "lunarade_mug");
	public static final SimpleItem magicMendingCompound = new SimpleItem("MagicMendingCompound", "magic_mending_compound");
	public static final SimpleItem magicMendingSolution = new SimpleItem("MagicMendingSolution", "magic_mending_solution");
	public static final SimpleItem magicRepairDust = new SimpleItem("MagicRepairDust", "magic_repair_dust");
	public static final SimpleItem mechaGear = new SimpleItem("MechaGear", "mecha_gear");
	public static final SimpleItem megaRuneFragmentBlue = new SimpleItem("BlueMegaRuneFragment", "mega_rune_fragment_blue");
	public static final SimpleItem megaRuneFragmentGreen = new SimpleItem("GreenMegaRuneFragment", "mega_rune_fragment_green");
	public static final SimpleItem megaRuneFragmentRed = new SimpleItem("RedMegaRuneFragment", "mega_rune_fragment_red");
	public static final SimpleItem megaRuneFragmentYellow = new SimpleItem("YellowMegaRuneFragment", "mega_rune_fragment_yellow");
	public static final SimpleItem metalTub = new SimpleItem("MetalTub", "metal_tub");
	public static final SimpleItem milleniumUpgrader = new SimpleItem("MilleniumUpgrader", "millenium_upgrader");
	public static final SimpleItem moltenUpgrader = new SimpleItem("MoltenUpgrader", "molten_upgrader");
	public static final SimpleItem moonstone = new SimpleItem("Moonstone", "moonstone");
	public static final SimpleItem mudBall = new SimpleItem("MudBall", "mud_ball");
	public static final SimpleItem nightmareFlakes = new SimpleItem("NightmareFlakes", "nightmare_flakes");
	public static final SimpleItem oldBoot = new SimpleItem("OldBoot", "old_boot");
	public static final SimpleItem opteryxFeather = new SimpleItem("OpteryxFeather", "opteryx_feather");
	public static final SimpleItem orbulon = new SimpleItem("Orbulon", "orbulon");
	public static final SimpleItem phantasm = new SimpleItem("Phantasm", "phantasm");
	public static final SimpleItem primedGhoulasm = new SimpleItem("PrimedGhoulasm", "primed_ghoulasm");
	public static final SimpleItem primordialSkull = new SimpleItem("PrimordialSkull", "primordial_skull");
	public static final SimpleItem pureCoralStone = new SimpleItem("PureCoralStone", "pure_coral_stone");
	public static final SimpleItem pureGold = new SimpleItem("PureGold", "pure_gold");
	public static final SimpleItem pureRainStone = new SimpleItem("PureRainStone", "pure_rain_stone");
	public static final SimpleItem rammerheadHide = new SimpleItem("RammerheadHide", "rammerhead_hide");
	public static final SimpleItem realmTravelTicket = new RealmTravelTicket();
	public static final SimpleItem rockBones = new SimpleItem("RockBones", "rock_bones");
	public static final SimpleItem runeStone = new SimpleItem("RuneStone", "rune_stone");
	public static final SimpleItem runicEnergy = new SimpleItem("RunicEnergy", "runic_energy");
	public static final SimpleItem screamShield = new SimpleItem("ScreamShield", "scream_shield");
	public static final SimpleItem sludgeParasite = new SimpleItem("SludgeParasite", "sludge_parasite");
	public static final SimpleItem smallPetalBlue = new SimpleItem("SmallBluePetal", "small_blue_petal");
	public static final SimpleItem smallPetalGreen = new SimpleItem("SmallGreenPetal", "small_green_petal");
	public static final SimpleItem smallPetalOrange = new SimpleItem("SmallOrangePetal", "small_orange_petal");
	public static final SimpleItem smallPetalPurple = new SimpleItem("SmallPurplePetal", "small_purple_petal");
	public static final SimpleItem smallPetalRed = new SimpleItem("SmallRedPetal", "small_red_petal");
	public static final SimpleItem soulbone = new SimpleItem("Soulbone", "soulbone");
	public static final SimpleItem strangeStoneBlue = new SimpleItem("BlueStrangeStone", "blue_strange_stone");
	public static final SimpleItem strangeStoneWhite = new SimpleItem("WhiteStrangeStone", "white_strange_stone");
	public static final SimpleItem strangeStoneYellow = new SimpleItem("YellowStrangeStone", "yellow_strange_stone");
	public static final SimpleItem toxicLump = new SimpleItem("ToxicLump", "toxic_lump");
	public static final SimpleItem trollSkull = new SimpleItem("TrollSkull", "troll_skull");
	public static final SimpleItem unchargedOrb = new SimpleItem("UnchargedOrb", "uncharged_orb");
	public static final SimpleItem unchargedStone = new SimpleItem("UnchargedStone", "uncharged_stone");
	public static final SimpleItem urkaHide = new SimpleItem("UrkaHide", "urka_hide");
	public static final SimpleItem vulcaneAugmentBattle = new SimpleItem("VulcaneAugmentBattle", "vulcane_augment_battle");
	public static final SimpleItem vulcaneAugmentEquality = new SimpleItem("VulcaneAugmentEquality", "vulcane_augment_equality");
	public static final SimpleItem vulcaneAugmentFire = new SimpleItem("VulcaneAugmentFire", "vulcane_augment_fire");
	public static final SimpleItem vulcaneAugmentImpairment = new SimpleItem("VulcaneAugmentImpairment", "vulcane_augment_impairment");
	public static final SimpleItem vulcaneAugmentPoison = new SimpleItem("VulcaneAugmentPoison", "vulcane_augment_poison");
	public static final SimpleItem vulcaneAugmentPower = new SimpleItem("VulcaneAugmentPower", "vulcane_augment_power");
	public static final SimpleItem vulcaneAugmentWither = new SimpleItem("VulcaneAugmentWither", "vulcane_augment_wither");
	public static final SimpleItem weaponParts = new SimpleItem("WeaponParts", "weapon_parts");
	public static final SimpleItem whitewashingSolution = new SimpleItem("WhitewashingSolution", "whitewashing_solution");
	public static final SimpleItem zhinxDust = new SimpleItem("ZhinxDust", "zhinx_dust");
	public static final TeaShreddings teaShreddings = new TeaShreddings();
	public static final ThornyPetals thornyPetals = new ThornyPetals();
	public static final WornBook wornBook = new WornBook();

	public static final ExplosiveIdol explosiveIdol = new ExplosiveIdol();
	public static final HauntedIdol hauntedIdol = new HauntedIdol();
	public static final NethengeicCallstone nethengeicCallstone = new NethengeicCallstone();
	public static final ShroomStone shroomStone = new ShroomStone();
	public static final SimpleItem ancientOrb = new SimpleItem("AncientOrb", "ancient_orb");
	public static final SimpleItem ancientRing = new SimpleItem("AncientRing", "ancient_ring");
	public static final SimpleItem boneHorn = new SimpleItem("BoneHorn", "bone_horn");
	public static final SimpleItem bookOfShadows = new SimpleItem("BookOfShadows", "book_of_shadows");
	public static final SimpleItem boulderDash = new SimpleItem("BoulderDash", "boulder_dash");
	public static final SimpleItem callOfTheDrake = new SimpleItem("CallOfTheDrake", "call_of_the_drake");
	public static final SimpleItem explosiveGems = new SimpleItem("ExplosiveGems", "explosive_gems");
	public static final SimpleItem giantCrystal = new SimpleItem("GiantCrystal", "giant_crystal");
	public static final SimpleItem goldSpring = new SimpleItem("GoldSpring", "gold_spring");
	public static final SimpleItem guardiansEye = new SimpleItem("GuardiansEye", "guardians_eye");
	public static final SimpleItem heavyBoulder = new SimpleItem("HeavyBoulder", "heavy_boulder");
	public static final SimpleItem hiveChunk = new SimpleItem("HiveChunk", "hive_chunk");
	public static final SimpleItem megaRuneStone = new SimpleItem("MegaRuneStone", "mega_rune_stone");
	public static final SimpleItem observingEye = new SimpleItem("ObservingEye", "observing_eye");
	public static final SimpleItem petals = new SimpleItem("Petals", "petals");
	public static final SimpleItem primordialDust = new SimpleItem("PrimordialDust", "primordial_dust");
	public static final SimpleItem pureWaterStone = new SimpleItem("PureWaterStone", "pure_water_stone");
	public static final SimpleItem silvroCoin = new SimpleItem("SilvroCoin", "silvro_coin");
	public static final SimpleItem staringEye = new SimpleItem("StaringEye", "staring_eye");
	public static final SimpleItem toyGyrocopter = new SimpleItem("ToyGyrocopter", "toy_gyrocopter");
	public static final SimpleItem treatBag = new SimpleItem("TreatBag", "treat_bag");
	public static final SimpleItem vileStone = new SimpleItem("VileStone", "vile_stone");
	public static final SimpleItem voliantHeart = new SimpleItem("VoliantHeart", "voliant_heart");
	public static final SimpleItem warlockGem = new SimpleItem("WarlockGem", "warlock_gem");
	public static final TrollIdol trollIdol = new TrollIdol();

	public static final SimpleItem tokensAbyss = new SimpleItem("AbyssTokens", "abyss_tokens");
	public static final SimpleItem tokensBaron = new SimpleItem("BaronTokens", "baron_tokens");
	public static final SimpleItem tokensBorean = new SimpleItem("BoreanTokens", "borean_tokens");
	public static final SimpleItem tokensCandyland = new SimpleItem("CandylandTokens", "candyland_tokens");
	public static final SimpleItem tokensCeleve = new SimpleItem("CeleveTokens", "celeve_tokens");
	public static final SimpleItem tokensCreeponia = new SimpleItem("CreeponiaTokens", "creeponia_tokens");
	public static final SimpleItem tokensCrystevia = new SimpleItem("CrysteviaTokens", "crystevia_tokens");
	public static final SimpleItem tokensDeeplands = new SimpleItem("DeeplandsTokens", "deeplands_tokens");
	public static final SimpleItem tokensDungeon = new SimpleItem("DungeonTokens", "dungeon_tokens");
	public static final SimpleItem tokensDustopia = new SimpleItem("DustopiaTokens", "dustopia_tokens");
	public static final SimpleItem tokensGardencia = new SimpleItem("GardenciaTokens", "gardencia_tokens");
	public static final SimpleItem tokensGreckon = new SimpleItem("GreckonTokens", "greckon_tokens");
	public static final SimpleItem tokensHaven = new SimpleItem("HavenTokens", "haven_tokens");
	public static final SimpleItem tokensIromine = new SimpleItem("IromineTokens", "iromine_tokens");
	public static final SimpleItem tokensLelyetia = new SimpleItem("LelyetiaTokens", "lelyetia_tokens");
	public static final SimpleItem tokensLunar = new SimpleItem("LunarTokens", "lunar_tokens");
	public static final SimpleItem tokensMysterium = new SimpleItem("MysteriumTokens", "mysterium_tokens");
	public static final SimpleItem tokensNether = new SimpleItem("NetherTokens", "nether_tokens");
	public static final SimpleItem tokensPrecasian = new SimpleItem("PrecasianTokens", "precasian_tokens");
	public static final SimpleItem tokensRunandor = new SimpleItem("RunandorTokens", "runandor_tokens");
	public static final SimpleItem tokensShyrelands = new SimpleItem("ShyrelandsTokens", "shyrelands_tokens");
	public static final SimpleItem tokensVoxPonds = new SimpleItem("VoxPondsTokens", "vox_ponds_tokens");

	public static final SimpleItem upgradeKitAbyssal = new SimpleItem("AbyssalUpgradeKit", "abyssal_upgrade_kit");
	public static final SimpleItem upgradeKitAncient = new SimpleItem("AncientUpgradeKit", "ancient_upgrade_kit");
	public static final SimpleItem upgradeKitApoco = new SimpleItem("ApocoUpgradeKit", "apoco_upgrade_kit");
	public static final SimpleItem upgradeKitClown = new SimpleItem("ClownUpgradeKit", "clown_upgrade_kit");
	public static final SimpleItem upgradeKitDarkly = new SimpleItem("DarklyUpgradeKit", "darkly_upgrade_kit");
	public static final SimpleItem upgradeKitFloro = new SimpleItem("FloroUpgradeKit", "floro_upgrade_kit");
	public static final SimpleItem upgradeKitGolden = new SimpleItem("GoldenUpgradeKit", "golden_upgrade_kit");
	public static final SimpleItem upgradeKitHaunted = new SimpleItem("HauntedUpgradeKit", "haunted_upgrade_kit");
	public static final SimpleItem upgradeKitLelyetian = new SimpleItem("LelyetianUpgradeKit", "lelyetian_upgrade_kit");
	public static final SimpleItem upgradeKitLight = new SimpleItem("LightUpgradeKit", "light_upgrade_kit");
	public static final SimpleItem upgradeKitLunar = new SimpleItem("LunarUpgradeKit", "lunar_upgrade_kit");
	public static final SimpleItem upgradeKitNether = new SimpleItem("NetherUpgradeKit", "nether_upgrade_kit");
	public static final SimpleItem upgradeKitPrecasian = new SimpleItem("PrecasianUpgradeKit", "precasian_upgrade_kit");
	public static final SimpleItem upgradeKitPredator = new SimpleItem("PredatorUpgradeKit", "predator_upgrade_kit");
	public static final SimpleItem upgradeKitRocky = new SimpleItem("RockyUpgradeKit", "rocky_upgrade_kit");
	public static final SimpleItem upgradeKitRunic = new SimpleItem("RunicUpgradeKit", "runic_upgrade_kit");
	public static final SimpleItem upgradeKitSeaside = new SimpleItem("SeasideUpgradeKit", "seaside_upgrade_kit");
	public static final SimpleItem upgradeKitSmiley = new SimpleItem("SmileyUpgradeKit", "smiley_upgrade_kit");

	public static final WaterloggedItem waterloggedAquaCannon = new WaterloggedItem("WaterloggedAquaCannon", "waterlogged_aqua_cannon", 0);
	public static final WaterloggedItem waterloggedCoralArchergun = new WaterloggedItem("WaterloggedCoralArchergun", "waterlogged_coral_archergun", 1);
	public static final WaterloggedItem waterloggedCoralCannon = new WaterloggedItem("WaterloggedCoralCannon", "waterlogged_coral_cannon", 2);
	public static final WaterloggedItem waterloggedCoralClogger = new WaterloggedItem("WaterloggedCoralClogger", "waterlogged_coral_clogger", 3);
	public static final WaterloggedItem waterloggedReefer = new WaterloggedItem("WaterloggedReefer", "waterlogged_reefer", 4);

	public static final IncompleteMechaItem incompleteMechaArchergun = new IncompleteMechaItem("IncompleteMechaArchergun", "incomplete_mecha_archergun", WeaponRegister.archergunMecha);
	public static final IncompleteMechaItem incompleteMechaBow = new IncompleteMechaItem("IncompleteMechaBow", "incomplete_mecha_bow", WeaponRegister.bowMecha);
	public static final IncompleteMechaItem incompleteMechaCannon = new IncompleteMechaItem("IncompleteMechaCannon", "incomplete_mecha_cannon", WeaponRegister.cannonMechaCannon);
	public static final IncompleteMechaItem incompleteMechanicalAssaultRifle = new IncompleteMechaItem("IncompleteMechanicalAssaultRifle", "incomplete_mechanical_assault_rifle", WeaponRegister.gunMechanicalAssaultRifle);
	public static final IncompleteMechaItem incompleteMechaStaff = new IncompleteMechaItem("IncompleteMechaStaff", "incomplete_mecha_staff", WeaponRegister.staffMecha);
	public static final IncompleteMechaItem incompleteMechyro = new IncompleteMechaItem("IncompleteMechyro", "incomplete_mechyro", WeaponRegister.shotgunMechyro);

	public static final Realmstone realmstoneAbyss = new Realmstone("AbyssRealmstone", "abyss_realmstone", BlockRegister.portalAbyss, SoundsRegister.portalAbyss, "abyss");
	public static final Realmstone realmstoneAncientCavern = new Realmstone("AncientCavernRealmstone", "ancient_cavern_realmstone", BlockRegister.portalAncientCavern, SoundsRegister.portalAncientCavern, "ancientCavern");
	public static final Realmstone realmstoneBarathos = new Realmstone("BarathosRealmstone", "barathos_realmstone", BlockRegister.portalBarathos, SoundsRegister.portalBarren, "barathos");
	public static final Realmstone realmstoneBorean = new Realmstone("BoreanRealmstone", "borean_realmstone", BlockRegister.portalBorean, SoundsRegister.portalNatural, "lborean");
	public static final Realmstone realmstoneCandyland = new Realmstone("CandylandRealmstone", "candyland_realmstone", BlockRegister.portalCandyland, SoundsRegister.portalCandyland, "candyland");
	public static final Realmstone realmstoneCeleve = new Realmstone("CeleveRealmstone", "celeve_realmstone", BlockRegister.portalCeleve, SoundsRegister.portalCeleve, "celeve");
	public static final Realmstone realmstoneCreeponia = new Realmstone("CreeponiaRealmstone", "creeponia_realmstone", BlockRegister.portalCreeponia, SoundsRegister.portalCreeponia, "creeponia");
	public static final Realmstone realmstoneCrystevia = new Realmstone("CrysteviaRealmstone", "crystevia_realmstone", BlockRegister.portalCrystevia, SoundsRegister.portalCrystevia, "crystevia");
	public static final Realmstone realmstoneDeeplands = new Realmstone("DeeplandsRealmstone", "deeplands_realmstone", BlockRegister.portalDeeplands, SoundsRegister.portalBarren, "deeplands");
	public static final Realmstone realmstoneDustopia = new Realmstone("DustopiaRealmstone", "dustopia_realmstone", BlockRegister.portalDustopia, SoundsRegister.portalDark, "dustopia");
	public static final Realmstone realmstoneGardencia = new Realmstone("GardenciaRealmstone", "gardencia_realmstone", BlockRegister.portalGardencia, SoundsRegister.portalNatural, "gardencia");
	public static final Realmstone realmstoneHaven = new Realmstone("HavenRealmstone", "haven_realmstone", BlockRegister.portalHaven, SoundsRegister.portalLight, "haven");
	public static final Realmstone realmstoneImmortallis = new Realmstone("ImmortallisRealmstone", "immortallis_realmstone", BlockRegister.portalImmortallis, SoundsRegister.portalImmortallis, "immortallis");
	public static final Realmstone realmstoneIromine = new Realmstone("IromineRealmstone", "iromine_realmstone", BlockRegister.portalIromine, SoundsRegister.portalIromine, "iromine");
	public static final Realmstone realmstoneLelyetia = new Realmstone("LelyetiaRealmstone", "lelyetia_realmstone", BlockRegister.portalLelyetia, SoundsRegister.portalNatural, "lelyetia");
	public static final Realmstone realmstoneLunalus = new Realmstone("LunalusRealmstone", "lunalus_realmstone", BlockRegister.portalLunalus, null, "lunalus");
	public static final Realmstone realmstoneMysterium = new Realmstone("MysteriumRealmstone", "mysterium_realmstone", BlockRegister.portalMysterium, SoundsRegister.portalNatural, "mysterium");
	public static final Realmstone realmstonePrecasia = new Realmstone("PrecasiaRealmstone", "precasia_realmstone", BlockRegister.portalPrecasia, SoundsRegister.portalNatural, "precasia");
	public static final Realmstone realmstoneShyrelands = new Realmstone("ShyrelandsRealmstone", "shyrelands_realmstone", BlockRegister.portalShyrelands, SoundsRegister.portalShyrelands, "shyrelands");
	public static final Realmstone realmstoneVoxPonds = new Realmstone("VoxPondsRealmstone", "vox_ponds_realmstone", BlockRegister.portalVoxPonds, SoundsRegister.portalDark, "voxPonds");

	public static final AuguryEssence essenceAncient = new AuguryEssence("AncientEssence", "ancient_essence", 58, 120f);
	public static final AuguryEssence essenceCharged = new AuguryEssence("ChargedEssence", "charged_essence", 18, 10f);
	public static final AuguryEssence essenceDark = new AuguryEssence("DarkEssence", "dark_essence", 66, 250f);
	public static final AuguryEssence essenceDivine = new AuguryEssence("DivineEssence", "divine_essence", 84, 600f);
	public static final AuguryEssence essenceEmpowered = new AuguryEssence("EmpoweredEssence", "empowered_essence", 36, 15f);
	public static final AuguryEssence essenceEthereal = new AuguryEssence("EtherealEssence", "ethereal_essence", 75, 400f);
	public static final AuguryEssence essenceLuminate = new AuguryEssence("LuminateEssence", "luminate_essence", 45, 20f);
	public static final AuguryEssence essenceMolten = new AuguryEssence("MoltenEssence", "molten_essence", 10, 7f);
	public static final AuguryEssence essenceOminous = new AuguryEssence("OminousEssence", "ominous_essence", 27, 12f);
	public static final AuguryEssence essenceWeak = new AuguryEssence("WeakEssence", "weak_essence", 1, 4.0f);
	public static final AuguryEssence ghostlyStone = new AuguryEssence("GhostlyStone", "ghostly_stone", 50, 40f);

	public static final SimpleItem progressCoin0 = new SimpleItem("ProgressCoin0", "progress_coin0");
	public static final SimpleItem progressCoin1 = new SimpleItem("ProgressCoin1", "progress_coin1");
	public static final SimpleItem progressCoin2 = new SimpleItem("ProgressCoin2", "progress_coin2");
	public static final SimpleItem progressCoin3 = new SimpleItem("ProgressCoin3", "progress_coin3");
	public static final SimpleItem progressCoin4 = new SimpleItem("ProgressCoin4", "progress_coin4");

	public static final SimpleItem powerStoneAmbient = new SimpleItem("AmbientPowerStone", "ambient_power_stone");
	public static final SimpleItem powerStoneBlooming = new SimpleItem("BloomingPowerStone", "blooming_power_stone");
	public static final SimpleItem powerStoneGlaring = new SimpleItem("GlaringPowerStone", "glaring_power_stone");
	public static final SimpleItem powerStoneGleaming = new SimpleItem("GleamingPowerStone", "gleaming_power_stone");
	public static final SimpleItem powerStoneGlistening = new SimpleItem("GlisteningPowerStone", "glistening_power_stone");
	public static final SimpleItem powerStoneGlowing = new SimpleItem("GlowingPowerStone", "glowing_power_stone");
	public static final SimpleItem powerStoneRadiant = new SimpleItem("RadiantPowerStone", "radiant_power_stone");
	public static final SimpleItem powerStoneShining = new SimpleItem("ShiningPowerStone", "shining_power_stone");

	public static final InfusionStone infusionStoneAmbient = new InfusionStone("AmbientInfusionStone", "ambient_infusion_stone", 20, 20.0f, ItemRegister.powerStoneAmbient);
	public static final InfusionStone infusionStoneBlooming = new InfusionStone("BloomingInfusionStone", "blooming_infusion_stone", 80, 300.0f, ItemRegister.powerStoneBlooming);
	public static final InfusionStone infusionStoneGlaring = new InfusionStone("GlaringInfusionStone", "glaring_infusion_stone", 30, 40.0f, ItemRegister.powerStoneGlaring);
	public static final InfusionStone infusionStoneGleaming = new InfusionStone("GleamingInfusionStone", "gleaming_infusion_stone", 15, 16.0f, ItemRegister.powerStoneGleaming);
	public static final InfusionStone infusionStoneGlistening = new InfusionStone("GlisteningInfusionStone", "glistening_infusion_stone", 5, 8.0f, ItemRegister.powerStoneGlistening);
	public static final InfusionStone infusionStoneGlowing = new InfusionStone("GlowingInfusionStone", "glowing_infusion_stone", 45, 85.0f, ItemRegister.powerStoneGlowing);
	public static final InfusionStone infusionStoneRadiant = new InfusionStone("RadiantInfusionStone", "radiant_infusion_stone", 70, 22.0f, ItemRegister.powerStoneRadiant);
	public static final InfusionStone infusionStoneShining = new InfusionStone("ShiningInfusionStone", "shining_infusion_stone", 60, 150.0f, ItemRegister.powerStoneShining);

	public static final SkillCrystal skillCrystalGiant = new SkillCrystal("GiantSkillCrystal", "giant_skill_crystal", 4);
	public static final SkillCrystal skillCrystalLarge = new SkillCrystal("LargeSkillCrystal", "large_skill_crystal", 8);
	public static final SkillCrystal skillCrystalMedium = new SkillCrystal("MediumSkillCrystal", "medium_skill_crystal", 12);
	public static final SkillCrystal skillCrystalSmall = new SkillCrystal("SmallSkillCrystal", "small_skill_crystal", 15);

	public static final CrystalBox crystalBox = new CrystalBox();
	public static final FishCase fishCase = new FishCase();
	public static final GemBag gemBag = new GemBag();
	public static final RuneBox runeBox = new RuneBox();
	public static final ShinyBox shinyBox = new ShinyBox();
	public static final TreasureBox treasureBox = new TreasureBox();
	public static final WeaponsCase weaponsCase = new WeaponsCase();

	public static final HealingFishFood candlefish = new HealingFishFood("Candlefish", "candlefish", 6, 0.7f, 7.0f);
	public static final HealingFishFood crimsonSkipper = new HealingFishFood("CrimsonSkipper", "crimson_skipper", 5, 0.6f, 6.0f);
	public static final HealingFishFood crimsonStripefish = new HealingFishFood("CrimsonStripefish", "crimson_stripefish", 4, 0.5f, 5.0f);
	public static final HealingFishFood darkHatchetfish = new HealingFishFood("DarkHatchetfish", "dark_hatchetfish", 7, 0.75f, 8.0f);
	public static final HealingFishFood fingerfish = new HealingFishFood("Fingerfish", "fingerfish", 1, 0.2f, 1.0f);
	public static final HealingFishFood goldenGullfish = new HealingFishFood("GoldenGullfish", "golden_gullfish", 2, 0.4f, 3.0f);
	public static final HealingFishFood ironback = new HealingFishFood("Ironback", "ironback", 8, 0.8f, 9.0f);
	public static final HealingFishFood limefish = new HealingFishFood("Limefish", "limefish", 1, 0.35f, 2.0f);
	public static final HealingFishFood pearlStripefish = new HealingFishFood("PearlStripefish", "pearl_stripefish", 1, 0.35f, 1.0f);
	public static final HealingFishFood rainbowfish = new HealingFishFood("Rainbowfish", "rainbowfish", 9, 0.85f, 10.0f);
	public static final HealingFishFood razorfish = new HealingFishFood("Razorfish", "razorfish", 9, 0.85f, 10.0f);
	public static final HealingFishFood rocketfish = new HealingFishFood("Rocketfish", "rocketfish", 4, 0.5f, 5.0f);
	public static final HealingFishFood sailback = new HealingFishFood("Sailback", "sailback", 2, 0.4f, 2.0f);
	public static final HealingFishFood sapphireStrider = new HealingFishFood("SapphireStrider", "sapphire_strider", 6, 0.65f, 6.0f);
	public static final HealingFishFood turquoiseStripefish = new HealingFishFood("TurquoiseStripefish", "turquoise_stripefish", 2, 0.4f, 4.0f);
	public static final HealingFishFood violetSkipper = new HealingFishFood("VioletSkipper", "violet_skipper", 3, 0.5f, 4.0f);

	public static final BasicFood candyCane = new BasicFood("CandyCane", "candy_cane", 1, 0.1f);
	public static final BasicFood candyCorn = new BasicFood("CandyCorn", "candy_corn", 1, 0.22f);
	public static final BasicFood chimeraChop = new BasicFood("ChimeraChop", "chimera_chop", 6, 0.6f, true);
	public static final BasicFood chimeraChopRaw = new BasicFood("RawChimeraChop", "raw_chimera_chop", 2, 0.2f, true);
	public static final BasicFood furlionChop = new BasicFood("FurlionChop", "furlion_chop", 6, 0.6f, true);
	public static final BasicFood furlionChopRaw = new BasicFood("RawFurlionChop", "raw_furlion_chop", 2, 0.2f, true);
	public static final BasicFood gingerbreadCookie = new BasicFood("GingerbreadCookie", "gingerbread_cookie", 2, 0.25f);
	public static final BasicFood gingerbreadWing = new BasicFood("GingerbreadWing", "gingerbread_wing", 2, 0.2f);
	public static final BasicFood natureMelonSlice = new BasicFood("NatureMelonSlice", "nature_melon_slice", 1, 0.4f);
	public static final BasicFood peppermintCandy = new BasicFood("PeppermintCandy", "peppermint_candy", 1, 0.2f);
	public static final BasicFood sourCandy = new BasicFood("SourCandy", "sour_candy", 1, 0.15f);
	public static final BasicFood sourGummy = new BasicFood("SourGummy", "sour_gummy", 1, 0.3f);
	public static final BasicFood sourPop = new BasicFood("SourPop", "sour_pop", 1, 0.18f);
	public static final BasicFood spearmintCandy = new BasicFood("SpearmintCandy", "spearmint_candy", 1, 0.25f);
	public static final BasicFood ursaMeat = new BasicFood("UrsaMeat", "ursa_meat", 8, 0.8f, true);
	public static final BasicFood ursaMeatRaw = new BasicFood("RawUrsaMeat", "raw_ursa_meat", 3, 0.3f, true);
	public static final BasicFood chargerShank = new BasicFood("ChargerShank", "charger_shank", 7, 0.3f, true);
	public static final BasicFood chargerShankRaw = new BasicFood("RawChargerShank", "raw_charger_shank", 2, 0.2f, true);
	public static final BubbleBerries bubbleBerries = new BubbleBerries();
	public static final EyeCandy eyeCandy = new EyeCandy();
	public static final FieryChops fieryChops = new FieryChops();
	public static final FloracleSticks floracleSticks = new FloracleSticks();
	public static final FungalTea fungalTea = new FungalTea();
	public static final GoldicapPetals goldicapPetals = new GoldicapPetals();
	public static final HalyconBeef halyconBeef = new HalyconBeef();
	public static final HalyconMilk halyconMilk = new HalyconMilk();
	public static final HeartFruit heartFruit = new HeartFruit();
	public static final HotRod hotRod = new HotRod();
	public static final Lunacrike lunacrike = new Lunacrike();
	public static final LunaGlobe lunaGlobe = new LunaGlobe();
	public static final Lunalons lunalons = new Lunalons();
	public static final Lunarade lunarade = new Lunarade();
	public static final MagicMarang magicMarang = new MagicMarang();
	public static final MysticShrooms mysticShrooms = new MysticShrooms();
	public static final NaturalTea naturalTea = new NaturalTea();
	public static final RawHalyconBeef halyconBeefRaw = new RawHalyconBeef();
	public static final Rosidons rosidons = new Rosidons();
	public static final Tea tea = new Tea();
	public static final TrilliadLeaves trilliadLeaves = new TrilliadLeaves();
	public static final YetiFingernails yetiFingernails = new YetiFingernails();

	public static final InfusionBowl diamondBowl = new InfusionBowl("DiamondBowl", "diamond_bowl", 50);
	public static final InfusionBowl stoneBowl = new InfusionBowl("StoneBowl", "stone_bowl", 100);

	public static final BloodAccumulator bloodAccumulator = new BloodAccumulator();

	public static final AnimaStone animaStone = new AnimaStone();
	public static final HeartStone heartStone = new HeartStone();

	public static final BaseSlab slabAlluricorn = new AlluricornSlab();
	public static final BaseSlab slabBlissard = new BlissardSlab();
	public static final BaseSlab slabCompeer = new CompeerSlab();
	public static final BaseSlab slabConstructOfServility = new ConstructOfServilitySlab();
	public static final BaseSlab slabCorby = new CorbySlab();
	public static final BaseSlab slabCraggy = new CraggySlab();
	public static final BaseSlab slabDraggy = new DraggySlab();
	public static final BaseSlab slabEnderCarrier = new EnderCarrierSlab();
	public static final BaseSlab slabGnawer = new GnawerSlab();
	public static final BaseSlab slabGoofer = new GooferSlab();
	public static final BaseSlab slabHealingGolem = new HealingGolemSlab();
	public static final BaseSlab slabHellquin = new HellquinSlab();
	public static final BaseSlab slabHorntail = new HorntailSlab();
	public static final BaseSlab slabMechaCyclops = new MechaCyclopsSlab();
	public static final BaseSlab slabMechaSkellox = new MechaSkelloxSlab();
	public static final BaseSlab slabPenguin = new PenguinSlab();
	public static final BaseSlab slabPlateosaur = new PlateosaurSlab();
	public static final BaseSlab slabRammerhorn = new RammerhornSlab();
	public static final BaseSlab slabShaddy = new ShaddySlab();
	public static final BaseSlab slabSpikeback = new SpikebackSlab();
	public static final BaseSlab slabSpraggy = new SpraggySlab();
	public static final BaseSlab slabWaggy = new WaggySlab();

	public static final TotemItem totemAbominator = new AbominatorTotem();
	public static final TotemItem totemAncientBow = new AncientBowTotem();
	public static final TotemItem totemAnimalBlaster = new AnimalBlasterTotem();
	public static final TotemItem totemAquaticStaff = new AquaticStaffTotem();
	public static final TotemItem totemBaronSSR = new BaronSSRTotem();
	public static final TotemItem totemBaronStaff = new BaronStaffTotem();
	public static final TotemItem totemBayonetteSR = new BayonetteSRTotem();
	public static final TotemItem totemBloodfury = new BloodfuryTotem();
	public static final TotemItem totemBoomBoom = new BoomBoomTotem();
	public static final TotemItem totemCreepoidGreatblade = new CreepoidGreatbladeTotem();
	public static final TotemItem totemCrystalMaul = new CrystalMaulTotem();
	public static final TotemItem totemCrystaneer = new CrystaneerTotem();
	public static final TotemItem totemDaybreaker = new DaybreakerTotem();
	public static final TotemItem totemExplochronSword = new ExplochronSwordTotem();
	public static final TotemItem totemFlamingFury = new FlamingFuryTotem();
	public static final TotemItem totemFlowercorne = new FlowercorneTotem();
	public static final TotemItem totemFroster = new FrosterTotem();
	public static final TotemItem totemGigaCannon = new GigaCannonTotem();
	public static final TotemItem totemGoofyGreatblade = new GoofyGreatbladeTotem();
	public static final TotemItem totemHaunterRifle = new HaunterRifleTotem();
	public static final TotemItem totemLunaBlaster = new LunaBlasterTotem();
	public static final TotemItem totemMechaBlaster = new MechaBlasterTotem();
	public static final TotemItem totemMintMagnum = new MintMagnumTotem();
	public static final TotemItem totemNoxiousGreatblade = new NoxiousGreatbladeTotem();
	public static final TotemItem totemPredatiousBow = new PredatiousBowTotem();
	public static final TotemItem totemPulsator = new PulsatorTotem();
	public static final TotemItem totemPurplePunisher = new PurplePunisherTotem();
	public static final TotemItem totemRosidianArchergun = new RosidianArchergunTotem();
	public static final TotemItem totemRoyalGreatblade = new RoyalGreatbladeTotem();
	public static final TotemItem totemSkyStaff = new SkyStaffTotem();
	public static final TotemItem totemSoulBone = new SoulBoneTotem();
	public static final TotemItem totemSoulfireBow = new SoulfireBowTotem();
	public static final TotemItem totemSoundCannon = new SoundCannonTotem();
	public static final TotemItem totemSwarmotron = new SwarmotronTotem();
	public static final TotemItem totemTerminator = new TerminatorTotem();
	public static final TotemItem totemTidalGreatblade = new TidalGreatbladeTotem();
	public static final TotemItem totemViper1 = new Viper1Totem();
	public static final TotemItem totemViralArchergun = new ViralArchergunTotem();
	public static final TotemItem totemWartGun = new WartGunTotem();
	public static final TotemItem totemWhimsyWinder = new WhimsyWinderTotem();
	public static final TotemItem totemWizardStaff = new WizardStaffTotem();

	public static final SeedsItem seedsBubbleBerry = new SeedsItem("BubbleBerrySeeds", "bubble_berry_seeds", BlockRegister.cropBubbleBerries, Blocks.FARMLAND);
	public static final SeedsItem seedsChilli = new SeedsItem("ChilliSeeds", "chilli_seeds", BlockRegister.cropChilli, Blocks.FARMLAND);
	public static final SeedsItem seedsEyeBulb = new SeedsItem("EyeBulbSeeds", "eye_bulb_seeds", BlockRegister.cropEyeBulbs, Blocks.FARMLAND);
	public static final SeedsItem seedsFloracle = new SeedsItem("FloracleSeeds", "floracle_seeds", BlockRegister.cropFloracles, Blocks.FARMLAND);
	public static final SeedsItem seedsGoldicap = new SeedsItem("GoldicapSeeds", "goldicap_seeds", BlockRegister.cropGoldicaps, Blocks.FARMLAND);
	public static final SeedsItem seedsHeartFruit = new SeedsItem("HeartFruitSeeds", "heart_fruit_seeds", BlockRegister.cropHeartFruit, Blocks.FARMLAND);
	public static final SeedsItem seedsHollyTop = new SeedsItem("HollyTopSeeds", "holly_top_seeds", BlockRegister.cropHollyTops, Blocks.FARMLAND);
	public static final SeedsItem seedsLunacrike = new SeedsItem("LunacrikeSeeds", "lunacrike_seeds", BlockRegister.cropLunacrike, Blocks.FARMLAND);
	public static final SeedsItem seedsLunaGlobe = new SeedsItem("LunaGlobeSeeds", "luna_globe_seeds", BlockRegister.cropLunaGlobes, Blocks.FARMLAND);
	public static final SeedsItem seedsLunalon = new SeedsItem("LunalonSeeds", "lunalon_seeds", BlockRegister.cropLunalons, Blocks.FARMLAND);
	public static final SeedsItem seedsMagicMarang = new SeedsItem("MagicMarangSeeds", "magic_marang_seeds", BlockRegister.cropMagicMarang, Blocks.FARMLAND);
	public static final SeedsItem seedsMysticShroom = new SeedsItem("MysticShroomSeeds", "mystic_shroom_seeds", BlockRegister.cropMysticShrooms, Blocks.FARMLAND);
	public static final SeedsItem seedsRosidon = new SeedsItem("RosidonSeeds", "rosidon_seeds", BlockRegister.cropRosidons, Blocks.FARMLAND);
	public static final SeedsItem seedsTea = new SeedsItem("TeaSeeds", "tea_seeds", BlockRegister.cropTea, Blocks.FARMLAND);
	public static final SeedsItem seedsThornyPlant = new SeedsItem("ThornyPlantSeeds", "thorny_plant_seeds", BlockRegister.cropThornyPlant, Blocks.FARMLAND);
	public static final SeedsItem seedsTrilliad = new SeedsItem("TrilliadSeeds", "trilliad_seeds", BlockRegister.cropTrilliads, Blocks.FARMLAND);

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> ev) {
		ev.getRegistry().registerAll(
				activeRuneStone,
				amphibiyteLung,
				ancientOrb,
				ancientRing,
				animaStone,
				apocoStone,
				balloon,
				blankSlab,
				bloodAccumulator,
				boneFragmentChestbone,
				boneFragmentFootbone,
				boneFragmentLegbone,
				boneFragmentSkullbone,
				boneHorn,
				bookOfShadows,
				boulderDash,
				bubbleBerries,
				bulletLimonite,
				callOfTheDrake,
				candlefish,
				candyCane,
				candyCorn,
				cannonball,
				chargedRuniumChunk,
				chilli,
				chimeraChop,
				chimeraChopRaw,
				circusCoin,
				coinCopper,
				coinGold,
				coinLunaver,
				coinSilver,
				coralStone,
				crimsonSkipper,
				crimsonStripefish,
				crystalBlue,
				crystalBox,
				crystalGreen,
				crystalPurple,
				crystalRed,
				crystalWhite,
				crystalYellow,
				cup,
				darkBones,
				darkHatchetfish,
				darklyPowder,
				desertShell,
				diamondBowl,
				dischargeCapsule,
				doomStone,
				essenceAncient,
				essenceCharged,
				essenceDark,
				essenceDivine,
				essenceEmpowered,
				essenceEthereal,
				essenceLuminate,
				essenceMolten,
				essenceOminous,
				essenceWeak,
				explosiveGems,
				explosiveIdol,
				eyeBulb,
				eyeCandy,
				fieryChops,
				fingerfish,
				fishCase,
				fleshyBones,
				floracleSticks,
				fragmentedAnimaStone,
				fungalTea,
				furlionChop,
				furlionChopRaw,
				gemBag,
				gemBloodstone,
				gemCrystallite,
				gemGemenyte,
				gemJewelyte,
				gemOrnamyte,
				gemstonesBlue,
				gemstonesGreen,
				gemstonesPurple,
				gemstonesRed,
				gemstonesWhite,
				gemstonesYellow,
				ghostlyPowder,
				ghostlyStone,
				ghoulasm,
				giantCrystal,
				gingerbreadCookie,
				gingerbreadWing,
				goldenGullfish,
				goldicapPetals,
				goldSpring,
				gravitator,
				guardiansEye,
				halyconBeef,
				halyconBeefRaw,
				halyconMilk,
				hauntedIdol, mysticShrooms,
				heartFruit,
				heartStone,
				heavyBoulder,
				hiveChunk,
				hollyArrow,
				hollyArrowSpectral,
				hollyArrowTipped,
				hollyTopPetals,
				hotRod,
				hydroStone,
				iceCrystal,
				impureGold,
				incompleteMechaArchergun,
				incompleteMechaBow,
				incompleteMechaCannon,
				incompleteMechanicalAssaultRifle,
				incompleteMechaStaff,
				incompleteMechyro,
				infusionStoneAmbient,
				infusionStoneBlooming,
				infusionStoneGlaring,
				infusionStoneGleaming,
				infusionStoneGlistening,
				infusionStoneGlowing,
				infusionStoneRadiant,
				infusionStoneShining, amethyst,
				ingotBaronyte,
				ingotBlazium,
				ingotElecanium,
				ingotEmberstone,
				ingotGhastly,
				ingotGhoulish, jade,
				ingotLimonite,
				ingotLunar,
				ingotLyon,
				ingotMystite,
				ingotRosite,
				ingotRustedIron, sapphire, shyregem,
				ingotShyrestone,
				ingotSkeletal,
				ingotVarsium,
				ironback,
				ivory,
				jungleThorns,
				limefish,
				lunacrike,
				lunaGlobe,
				lunalons,
				lunarade,
				lunaradeMug,
				magicMarang,
				magicMendingCompound,
				magicMendingSolution,
				magicRepairDust,
				mechaGear,
				megaRuneFragmentBlue,
				megaRuneFragmentGreen,
				megaRuneFragmentRed,
				megaRuneFragmentYellow,
				megaRuneStone,
				metalSlug,
				metalTub,
				milleniumUpgrader,
				moltenUpgrader,
				moonstone,
				mudBall,
				naturalTea,
				natureMelonSlice,
				nethengeicCallstone,
				nightmareFlakes,
				observingEye,
				oldBoot,
				opteryxFeather,
				orbulon,
				pearlStripefish,
				peppermintCandy,
				petals,
				phantasm,
				popShot,
				powerStoneAmbient,
				powerStoneBlooming,
				powerStoneGlaring,
				powerStoneGleaming,
				powerStoneGlistening,
				powerStoneGlowing,
				powerStoneRadiant,
				powerStoneShining,
				primedGhoulasm,
				primordialDust,
				primordialSkull,
				progressCoin0,
				progressCoin1,
				progressCoin2,
				progressCoin3,
				progressCoin4,
				pureCoralStone,
				pureGold,
				pureRainStone,
				pureWaterStone,
				rainbowfish,
				rammerheadHide,
				razorfish,
				realmstoneAbyss,
				realmstoneAncientCavern,
				realmstoneBarathos,
				realmstoneBorean,
				realmstoneCandyland,
				realmstoneCeleve,
				realmstoneCreeponia,
				realmstoneCrystevia,
				realmstoneDeeplands,
				realmstoneDustopia,
				realmstoneGardencia,
				realmstoneHaven,
				realmstoneImmortallis,
				realmstoneIromine,
				realmstoneLelyetia,
				realmstoneLunalus,
				realmstoneMysterium,
				realmstonePrecasia,
				realmstoneShyrelands,
				realmstoneVoxPonds,
				realmTravelTicket,
				returnCrystal,
				rockBones,
				rocketfish,
				rosidons,
				runeBox,
				runeCharged,
				runeCompass,
				runeDistortion,
				runeEnergy,
				runeFire,
				runeKinetic,
				runeLife,
				runeLunar,
				runePoison,
				runePower,
				runeStone,
				runeStorm,
				runeStrike,
				runeUnpowered,
				runeWater,
				runeWind,
				runeWither,
				runicEnergy,
				runiumChunk,
				sailback,
				sapphireStrider,
				screamShield,
				seedsBubbleBerry,
				seedsChilli,
				seedsEyeBulb,
				seedsFloracle,
				seedsGoldicap, seedsMysticShroom,
				seedsHeartFruit,
				seedsHollyTop,
				seedsLunacrike,
				seedsLunaGlobe,
				seedsLunalon,
				seedsMagicMarang,
				seedsRosidon,
				seedsTea,
				seedsThornyPlant,
				seedsTrilliad,
				shinyBox,
				shroomStone,
				silvroCoin,
				skillCrystalGiant,
				skillCrystalLarge,
				skillCrystalMedium,
				skillCrystalSmall,
				slabAlluricorn,
				slabBlissard,
				slabCompeer,
				slabConstructOfServility,
				slabCorby,
				slabCraggy,
				slabDraggy,
				slabEnderCarrier,
				slabGnawer,
				slabGoofer,
				slabHealingGolem,
				slabHellquin,
				slabHorntail,
				slabMechaCyclops,
				slabMechaSkellox,
				slabPenguin,
				slabPlateosaur,
				slabRammerhorn,
				slabShaddy,
				slabSpikeback,
				slabSpraggy,
				slabWaggy,
				sludgeParasite,
				smallPetalBlue,
				smallPetalGreen,
				smallPetalOrange,
				smallPetalPurple,
				smallPetalRed,
				soulbone,
				sourCandy,
				sourGummy,
				sourPop,
				spearmintCandy,
				spreadshot,
				staringEye,
				stoneBowl,
				strangeStoneBlue,
				strangeStoneWhite,
				strangeStoneYellow,
				tea,
				teaShreddings,
				thornyPetals,
				tokensAbyss,
				tokensBaron,
				tokensBorean,
				tokensCandyland,
				tokensCeleve,
				tokensCreeponia,
				tokensCrystevia,
				tokensDeeplands,
				tokensDungeon,
				tokensDustopia,
				tokensGardencia,
				tokensGreckon,
				tokensHaven,
				tokensIromine,
				tokensLelyetia,
				tokensLunar,
				tokensMysterium,
				tokensNether,
				tokensPrecasian,
				tokensRunandor,
				tokensShyrelands,
				tokensVoxPonds,
				totemAbominator,
				totemAncientBow,
				totemAnimalBlaster,
				totemAquaticStaff,
				totemBaronSSR,
				totemBaronStaff,
				totemBayonetteSR,
				totemBloodfury,
				totemBoomBoom,
				totemCreepoidGreatblade,
				totemCrystalMaul,
				totemCrystaneer,
				totemDaybreaker,
				totemExplochronSword,
				totemFlamingFury,
				totemFlowercorne,
				totemFroster,
				totemGigaCannon,
				totemGoofyGreatblade, totemHaunterRifle,
				totemLunaBlaster,
				totemMechaBlaster,
				totemMintMagnum,
				totemNoxiousGreatblade,
				totemPredatiousBow,
				totemPulsator,
				totemPurplePunisher,
				totemRosidianArchergun,
				totemRoyalGreatblade,
				totemSkyStaff,
				totemSoulBone,
				totemSoulfireBow,
				totemSoundCannon,
				totemSwarmotron,
				totemTerminator,
				totemTidalGreatblade,
				totemViper1,
				totemViralArchergun,
				totemWartGun,
				totemWhimsyWinder,
				totemWizardStaff,
				toxicLump,
				toyGyrocopter,
				treasureBox,
				treatBag,
				trilliadLeaves,
				yetiFingernails,
				trollIdol,
				trollSkull,
				turquoiseStripefish,
				unchargedOrb,
				unchargedStone,
				upgradeKitAbyssal,
				upgradeKitAncient,
				upgradeKitApoco,
				upgradeKitClown,
				upgradeKitDarkly,
				upgradeKitFloro,
				upgradeKitGolden,
				upgradeKitHaunted,
				upgradeKitLelyetian,
				upgradeKitLight,
				upgradeKitLunar,
				upgradeKitNether,
				upgradeKitPrecasian,
				upgradeKitPredator,
				upgradeKitRocky,
				upgradeKitRunic,
				upgradeKitSeaside,
				upgradeKitSmiley,
				urkaHide,
				ursaMeat,
				ursaMeatRaw,
				chargerShank, chargerShankRaw,
				vileStone,
				violetSkipper,
				voliantHeart,
				vulcaneAugmentBattle,
				vulcaneAugmentEquality,
				vulcaneAugmentFire,
				vulcaneAugmentImpairment,
				vulcaneAugmentPoison,
				vulcaneAugmentPower,
				vulcaneAugmentWither,
				warlockGem,
				waterloggedAquaCannon,
				waterloggedCoralArchergun,
				waterloggedCoralCannon,
				waterloggedCoralClogger,
				waterloggedReefer,
				weaponParts,
				weaponsCase,
				whitewashingSolution,
				wornBook,
				zhinxDust
		);
	}

	@SubscribeEvent
	public static void remapMissing(final RegistryEvent.MissingMappings<Item> ev) {
		for (RegistryEvent.MissingMappings.Mapping<Item> map : ev.getAllMappings()) {
			if (map.key.equals(new ResourceLocation("aoa3:amethyst_ingot"))) {
				map.remap(amethyst);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:jade_ingot"))) {
				map.remap(jade);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:shyregem_ingot"))) {
				map.remap(shyregem);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:sapphire_ingot"))) {
				map.remap(sapphire);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:haunters_rifle_totem"))) {
				map.remap(totemHaunterRifle);
			}
		}
	}

	@SubscribeEvent
	public static void registerItemRenders(final ModelRegistryEvent ev) {
		registerRender(activeRuneStone, "misc/misc/");
		registerRender(amphibiyteLung, "misc/misc/");
		registerRender(ancientOrb, "misc/boss/");
		registerRender(ancientRing, "misc/boss/");
		registerRender(animaStone, "special/");
		registerRender(apocoStone, "misc/misc/");
		registerRender(balloon, "misc/misc/");
		registerRender(blankSlab, "misc/misc/");
		registerRender(bloodAccumulator, "tools/misc/");
		registerRender(boneFragmentChestbone, "misc/misc/");
		registerRender(boneFragmentFootbone, "misc/misc/");
		registerRender(boneFragmentLegbone, "misc/misc/");
		registerRender(boneFragmentSkullbone, "misc/misc/");
		registerRender(boneHorn, "misc/boss/");
		registerRender(bookOfShadows, "misc/boss/");
		registerRender(boulderDash, "misc/boss/");
		registerRender(bubbleBerries, "food/plants/");
		registerRender(bulletLimonite, "misc/ammo/");
		registerRender(callOfTheDrake, "misc/boss/");
		registerRender(candlefish, "food/fish/");
		registerRender(candyCane, "food/misc/");
		registerRender(candyCorn, "food/misc/");
		registerRender(cannonball, "misc/ammo/");
		registerRender(chargedRuniumChunk, "misc/misc/");
		registerRender(chilli, "food/plants/");
		registerRender(chimeraChop, "food/meat/");
		registerRender(chimeraChopRaw, "food/meat/");
		registerRender(circusCoin, "misc/misc/");
		registerRender(coinCopper, "misc/misc/");
		registerRender(coinGold, "misc/misc/");
		registerRender(coinLunaver, "misc/misc/");
		registerRender(coinSilver, "misc/misc/");
		registerRender(coralStone, "misc/misc/");
		registerRender(crimsonSkipper, "food/fish/");
		registerRender(crimsonStripefish, "food/fish/");
		registerRender(crystalBlue, "misc/misc/");
		registerRender(crystalBox, "misc/misc/");
		registerRender(crystalGreen, "misc/misc/");
		registerRender(crystalPurple, "misc/misc/");
		registerRender(crystalRed, "misc/misc/");
		registerRender(crystalWhite, "misc/misc/");
		registerRender(crystalYellow, "misc/misc/");
		registerRender(cup, "misc/misc/");
		registerRender(darkBones, "misc/misc/");
		registerRender(darkHatchetfish, "food/fish/");
		registerRender(darklyPowder, "misc/misc/");
		registerRender(desertShell, "misc/misc/");
		registerRender(diamondBowl, "tools/misc/");
		registerRender(dischargeCapsule, "misc/ammo/");
		registerRender(doomStone, "misc/misc/");
		registerRender(essenceAncient, "misc/essence/");
		registerRender(essenceCharged, "misc/essence/");
		registerRender(essenceDark, "misc/essence/");
		registerRender(essenceDivine, "misc/essence/");
		registerRender(essenceEmpowered, "misc/essence/");
		registerRender(essenceEthereal, "misc/essence/");
		registerRender(essenceLuminate, "misc/essence/");
		registerRender(essenceMolten, "misc/essence/");
		registerRender(essenceOminous, "misc/essence/");
		registerRender(essenceWeak, "misc/essence/");
		registerRender(explosiveGems, "misc/boss/");
		registerRender(explosiveIdol, "misc/boss/");
		registerRender(eyeBulb, "misc/misc/");
		registerRender(eyeCandy, "food/misc/");
		registerRender(fieryChops, "food/meat/");
		registerRender(fingerfish, "food/fish/");
		registerRender(fishCase, "misc/misc/");
		registerRender(fishCase, "misc/misc/");
		registerRender(fleshyBones, "misc/misc/");
		registerRender(floracleSticks, "food/plants/");
		registerRender(fragmentedAnimaStone, "misc/misc/");
		registerRender(fungalTea, "food/drinks/");
		registerRender(furlionChop, "food/meat/");
		registerRender(furlionChopRaw, "food/meat/");
		registerRender(gemBag, "misc/misc/");
		registerRender(gemBloodstone, "misc/mineral/");
		registerRender(gemCrystallite, "misc/mineral/");
		registerRender(gemGemenyte, "misc/mineral/");
		registerRender(gemJewelyte, "misc/mineral/");
		registerRender(gemOrnamyte, "misc/mineral/");
		registerRender(gemstonesBlue, "misc/misc/");
		registerRender(gemstonesGreen, "misc/misc/");
		registerRender(gemstonesPurple, "misc/misc/");
		registerRender(gemstonesRed, "misc/misc/");
		registerRender(gemstonesWhite, "misc/misc/");
		registerRender(gemstonesYellow, "misc/misc/");
		registerRender(ghostlyPowder, "misc/misc/");
		registerRender(ghostlyStone, "misc/misc/");
		registerRender(ghoulasm, "misc/misc/");
		registerRender(giantCrystal, "misc/boss/");
		registerRender(gingerbreadCookie, "food/misc/");
		registerRender(gingerbreadWing, "food/misc/");
		registerRender(goldenGullfish, "food/fish/");
		registerRender(goldicapPetals, "food/plants/");
		registerRender(goldSpring, "misc/boss/");
		registerRender(gravitator, "misc/misc/");
		registerRender(guardiansEye, "misc/boss/");
		registerRender(halyconBeef, "food/meat/");
		registerRender(halyconBeefRaw, "food/meat/");
		registerRender(halyconMilk, "food/drinks/");
		registerRender(hauntedIdol, "misc/boss/");
		registerRender(heartFruit, "food/plants/");
		registerRender(heartStone, "special/");
		registerRender(heavyBoulder, "misc/boss/");
		registerRender(hiveChunk, "misc/boss/");
		registerRender(hollyArrow, "misc/ammo/");
		registerRender(hollyArrowSpectral, "misc/ammo/");
		registerRender(hollyArrowTipped, "misc/ammo/");
		registerRender(hollyTopPetals, "misc/misc/");
		registerRender(hotRod, "food/meat/");
		registerRender(hydroStone, "misc/misc/");
		registerRender(iceCrystal, "misc/misc/");
		registerRender(impureGold, "misc/misc/");
		registerRender(incompleteMechaArchergun, "misc/misc/");
		registerRender(incompleteMechaBow, "misc/misc/");
		registerRender(incompleteMechaCannon, "misc/misc/");
		registerRender(incompleteMechanicalAssaultRifle, "misc/misc/");
		registerRender(incompleteMechaStaff, "misc/misc/");
		registerRender(incompleteMechyro, "misc/misc/");
		registerRender(infusionStoneAmbient, "misc/infusionstone/");
		registerRender(infusionStoneBlooming, "misc/infusionstone/");
		registerRender(infusionStoneGlaring, "misc/infusionstone/");
		registerRender(infusionStoneGleaming, "misc/infusionstone/");
		registerRender(infusionStoneGlistening, "misc/infusionstone/");
		registerRender(infusionStoneGlowing, "misc/infusionstone/");
		registerRender(infusionStoneRadiant, "misc/infusionstone/");
		registerRender(infusionStoneShining, "misc/infusionstone/");
		registerRender(amethyst, "misc/mineral/");
		registerRender(ingotBaronyte, "misc/mineral/");
		registerRender(ingotBlazium, "misc/mineral/");
		registerRender(ingotElecanium, "misc/mineral/");
		registerRender(ingotEmberstone, "misc/mineral/");
		registerRender(ingotGhastly, "misc/mineral/");
		registerRender(ingotGhoulish, "misc/mineral/");
		registerRender(jade, "misc/mineral/");
		registerRender(ingotLimonite, "misc/mineral/");
		registerRender(ingotLunar, "misc/mineral/");
		registerRender(ingotLyon, "misc/mineral/");
		registerRender(ingotMystite, "misc/mineral/");
		registerRender(ingotRosite, "misc/mineral/");
		registerRender(ingotRustedIron, "misc/mineral/");
		registerRender(sapphire, "misc/mineral/");
		registerRender(shyregem, "misc/mineral/");
		registerRender(ingotShyrestone, "misc/mineral/");
		registerRender(ingotSkeletal, "misc/mineral/");
		registerRender(ingotVarsium, "misc/mineral/");
		registerRender(ironback, "food/fish/");
		registerRender(ivory, "misc/misc/");
		registerRender(jungleThorns, "misc/misc/");
		registerRender(limefish, "food/fish/");
		registerRender(lunacrike, "food/plants/");
		registerRender(lunaGlobe, "food/plants/");
		registerRender(lunalons, "food/plants/");
		registerRender(lunarade, "food/drinks/");
		registerRender(lunaradeMug, "misc/misc/");
		registerRender(magicMarang, "food/plants/");
		registerRender(magicMendingCompound, "misc/misc/");
		registerRender(magicMendingSolution, "misc/misc/");
		registerRender(magicRepairDust, "misc/misc/");
		registerRender(mechaGear, "misc/misc/");
		registerRender(megaRuneFragmentBlue, "misc/misc/");
		registerRender(megaRuneFragmentGreen, "misc/misc/");
		registerRender(megaRuneFragmentRed, "misc/misc/");
		registerRender(megaRuneFragmentYellow, "misc/misc/");
		registerRender(megaRuneStone, "misc/boss/");
		registerRender(metalSlug, "misc/ammo/");
		registerRender(metalTub, "misc/misc/");
		registerRender(milleniumUpgrader, "misc/misc/");
		registerRender(moltenUpgrader, "misc/misc/");
		registerRender(moonstone, "misc/misc/");
		registerRender(mudBall, "misc/misc/");
		registerRender(mysticShrooms, "food/misc/");
		registerRender(naturalTea, "food/drinks/");
		registerRender(natureMelonSlice, "food/plants/");
		registerRender(nethengeicCallstone, "misc/boss/");
		registerRender(nightmareFlakes, "misc/misc/");
		registerRender(observingEye, "misc/boss/");
		registerRender(oldBoot, "misc/misc/");
		registerRender(opteryxFeather, "misc/misc/");
		registerRender(orbulon, "misc/misc/");
		registerRender(pearlStripefish, "food/fish/");
		registerRender(peppermintCandy, "food/misc/");
		registerRender(petals, "misc/boss/");
		registerRender(phantasm, "misc/misc/");
		registerRender(popShot, "misc/ammo/");
		registerRender(powerStoneAmbient, "misc/powerstone/");
		registerRender(powerStoneBlooming, "misc/powerstone/");
		registerRender(powerStoneGlaring, "misc/powerstone/");
		registerRender(powerStoneGleaming, "misc/powerstone/");
		registerRender(powerStoneGlistening, "misc/powerstone/");
		registerRender(powerStoneGlowing, "misc/powerstone/");
		registerRender(powerStoneRadiant, "misc/powerstone/");
		registerRender(powerStoneShining, "misc/powerstone/");
		registerRender(primedGhoulasm, "misc/misc/");
		registerRender(primordialDust, "misc/boss/");
		registerRender(primordialSkull, "misc/misc/");
		registerRender(progressCoin0, "misc/misc/");
		registerRender(progressCoin1, "misc/misc/");
		registerRender(progressCoin2, "misc/misc/");
		registerRender(progressCoin3, "misc/misc/");
		registerRender(progressCoin4, "misc/misc/");
		registerRender(pureCoralStone, "misc/misc/");
		registerRender(pureGold, "misc/misc/");
		registerRender(pureRainStone, "misc/misc/");
		registerRender(pureWaterStone, "misc/boss/");
		registerRender(rainbowfish, "food/fish/");
		registerRender(rammerheadHide, "misc/misc/");
		registerRender(razorfish, "food/fish/");
		registerRender(realmstoneAbyss, "misc/realmstone/");
		registerRender(realmstoneAncientCavern, "misc/realmstone/");
		registerRender(realmstoneBarathos, "misc/realmstone/");
		registerRender(realmstoneBorean, "misc/realmstone/");
		registerRender(realmstoneCandyland, "misc/realmstone/");
		registerRender(realmstoneCeleve, "misc/realmstone/");
		registerRender(realmstoneCreeponia, "misc/realmstone/");
		registerRender(realmstoneCrystevia, "misc/realmstone/");
		registerRender(realmstoneDeeplands, "misc/realmstone/");
		registerRender(realmstoneDustopia, "misc/realmstone/");
		registerRender(realmstoneGardencia, "misc/realmstone/");
		registerRender(realmstoneHaven, "misc/realmstone/");
		registerRender(realmstoneImmortallis, "misc/realmstone/");
		registerRender(realmstoneIromine, "misc/realmstone/");
		registerRender(realmstoneLelyetia, "misc/realmstone/");
		registerRender(realmstoneLunalus, "misc/realmstone/");
		registerRender(realmstoneMysterium, "misc/realmstone/");
		registerRender(realmstonePrecasia, "misc/realmstone/");
		registerRender(realmstoneShyrelands, "misc/realmstone/");
		registerRender(realmstoneVoxPonds, "misc/realmstone/");
		registerRender(realmTravelTicket, "misc/misc/");
		registerRender(returnCrystal, "misc/misc/");
		registerRender(rockBones, "misc/misc/");
		registerRender(rocketfish, "food/fish/");
		registerRender(rosidons, "food/plants/");
		registerRender(runeBox, "misc/misc/");
		registerRender(runeCharged, "misc/ammo/");
		registerRender(runeCompass, "misc/ammo/");
		registerRender(runeDistortion, "misc/ammo/");
		registerRender(runeEnergy, "misc/ammo/");
		registerRender(runeFire, "misc/ammo/");
		registerRender(runeKinetic, "misc/ammo/");
		registerRender(runeLife, "misc/ammo/");
		registerRender(runeLunar, "misc/ammo/");
		registerRender(runePoison, "misc/ammo/");
		registerRender(runePower, "misc/ammo/");
		registerRender(runeStone, "misc/misc/");
		registerRender(runeStorm, "misc/ammo/");
		registerRender(runeStrike, "misc/ammo/");
		registerRender(runeUnpowered, "misc/ammo/");
		registerRender(runeWater, "misc/ammo/");
		registerRender(runeWind, "misc/ammo/");
		registerRender(runeWither, "misc/ammo/");
		registerRender(runicEnergy, "misc/misc/");
		registerRender(runiumChunk, "misc/misc/");
		registerRender(sailback, "food/fish/");
		registerRender(sapphireStrider, "food/fish/");
		registerRender(screamShield, "misc/misc/");
		registerRender(seedsBubbleBerry, "misc/seeds/");
		registerRender(seedsChilli, "misc/seeds/");
		registerRender(seedsEyeBulb, "misc/seeds/");
		registerRender(seedsFloracle, "misc/seeds/");
		registerRender(seedsGoldicap, "misc/seeds/");
		registerRender(seedsHeartFruit, "misc/seeds/");
		registerRender(seedsHollyTop, "misc/seeds/");
		registerRender(seedsLunacrike, "misc/seeds/");
		registerRender(seedsLunaGlobe, "misc/seeds/");
		registerRender(seedsLunalon, "misc/seeds/");
		registerRender(seedsMagicMarang, "misc/seeds/");
		registerRender(seedsMysticShroom, "misc/seeds/");
		registerRender(seedsRosidon, "misc/seeds/");
		registerRender(seedsTea, "misc/seeds/");
		registerRender(seedsThornyPlant, "misc/seeds/");
		registerRender(seedsTrilliad, "misc/seeds/");
		registerRender(shinyBox, "misc/misc/");
		registerRender(shroomStone, "misc/boss/");
		registerRender(silvroCoin, "misc/boss/");
		registerRender(skillCrystalGiant, "misc/misc/");
		registerRender(skillCrystalLarge, "misc/misc/");
		registerRender(skillCrystalMedium, "misc/misc/");
		registerRender(skillCrystalSmall, "misc/misc/");
		registerRender(slabAlluricorn, "minionslabs/");
		registerRender(slabBlissard, "minionslabs/");
		registerRender(slabCompeer, "minionslabs/");
		registerRender(slabConstructOfServility, "minionslabs/");
		registerRender(slabCorby, "minionslabs/");
		registerRender(slabCraggy, "minionslabs/");
		registerRender(slabDraggy, "minionslabs/");
		registerRender(slabEnderCarrier, "minionslabs/");
		registerRender(slabGnawer, "minionslabs/");
		registerRender(slabGoofer, "minionslabs/");
		registerRender(slabHealingGolem, "minionslabs/");
		registerRender(slabHellquin, "minionslabs/");
		registerRender(slabHorntail, "minionslabs/");
		registerRender(slabMechaCyclops, "minionslabs/");
		registerRender(slabMechaSkellox, "minionslabs/");
		registerRender(slabPenguin, "minionslabs/");
		registerRender(slabPlateosaur, "minionslabs/");
		registerRender(slabRammerhorn, "minionslabs/");
		registerRender(slabShaddy, "minionslabs/");
		registerRender(slabSpikeback, "minionslabs/");
		registerRender(slabSpraggy, "minionslabs/");
		registerRender(slabWaggy, "minionslabs/");
		registerRender(sludgeParasite, "misc/misc/");
		registerRender(smallPetalBlue, "misc/misc/");
		registerRender(smallPetalGreen, "misc/misc/");
		registerRender(smallPetalOrange, "misc/misc/");
		registerRender(smallPetalPurple, "misc/misc/");
		registerRender(smallPetalRed, "misc/misc/");
		registerRender(soulbone, "misc/misc/");
		registerRender(sourCandy, "food/misc/");
		registerRender(sourGummy, "food/misc/");
		registerRender(sourPop, "food/misc/");
		registerRender(spearmintCandy, "food/misc/");
		registerRender(spreadshot, "misc/ammo/");
		registerRender(staringEye, "misc/boss/");
		registerRender(stoneBowl, "tools/misc/");
		registerRender(strangeStoneBlue, "misc/misc/");
		registerRender(strangeStoneWhite, "misc/misc/");
		registerRender(strangeStoneYellow, "misc/misc/");
		registerRender(tea, "food/drinks/");
		registerRender(teaShreddings, "misc/misc/");
		registerRender(thornyPetals, "misc/misc/");
		registerRender(tokensAbyss, "misc/tokens/");
		registerRender(tokensBaron, "misc/tokens/");
		registerRender(tokensBorean, "misc/tokens/");
		registerRender(tokensCandyland, "misc/tokens/");
		registerRender(tokensCeleve, "misc/tokens/");
		registerRender(tokensCreeponia, "misc/tokens/");
		registerRender(tokensCrystevia, "misc/tokens/");
		registerRender(tokensDeeplands, "misc/tokens/");
		registerRender(tokensDungeon, "misc/tokens/");
		registerRender(tokensDustopia, "misc/tokens/");
		registerRender(tokensGardencia, "misc/tokens/");
		registerRender(tokensGreckon, "misc/tokens/");
		registerRender(tokensHaven, "misc/tokens/");
		registerRender(tokensIromine, "misc/tokens/");
		registerRender(tokensLelyetia, "misc/tokens/");
		registerRender(tokensLunar, "misc/tokens/");
		registerRender(tokensMysterium, "misc/tokens/");
		registerRender(tokensNether, "misc/tokens/");
		registerRender(tokensPrecasian, "misc/tokens/");
		registerRender(tokensRunandor, "misc/tokens/");
		registerRender(tokensShyrelands, "misc/tokens/");
		registerRender(tokensVoxPonds, "misc/tokens/");
		registerRender(totemAbominator, "totems/");
		registerRender(totemAncientBow, "totems/");
		registerRender(totemAnimalBlaster, "totems/");
		registerRender(totemAquaticStaff, "totems/");
		registerRender(totemBaronSSR, "totems/");
		registerRender(totemBaronStaff, "totems/");
		registerRender(totemBayonetteSR, "totems/");
		registerRender(totemBloodfury, "totems/");
		registerRender(totemBoomBoom, "totems/");
		registerRender(totemCreepoidGreatblade, "totems/");
		registerRender(totemCrystalMaul, "totems/");
		registerRender(totemCrystaneer, "totems/");
		registerRender(totemDaybreaker, "totems/");
		registerRender(totemExplochronSword, "totems/");
		registerRender(totemFlamingFury, "totems/");
		registerRender(totemFlowercorne, "totems/");
		registerRender(totemFroster, "totems/");
		registerRender(totemGigaCannon, "totems/");
		registerRender(totemGoofyGreatblade, "totems/");
		registerRender(totemHaunterRifle, "totems/");
		registerRender(totemLunaBlaster, "totems/");
		registerRender(totemMechaBlaster, "totems/");
		registerRender(totemMintMagnum, "totems/");
		registerRender(totemNoxiousGreatblade, "totems/");
		registerRender(totemPredatiousBow, "totems/");
		registerRender(totemPulsator, "totems/");
		registerRender(totemPurplePunisher, "totems/");
		registerRender(totemRosidianArchergun, "totems/");
		registerRender(totemRoyalGreatblade, "totems/");
		registerRender(totemSkyStaff, "totems/");
		registerRender(totemSoulBone, "totems/");
		registerRender(totemSoulfireBow, "totems/");
		registerRender(totemSoundCannon, "totems/");
		registerRender(totemSwarmotron, "totems/");
		registerRender(totemTerminator, "totems/");
		registerRender(totemTidalGreatblade, "totems/");
		registerRender(totemViper1, "totems/");
		registerRender(totemViralArchergun, "totems/");
		registerRender(totemWartGun, "totems/");
		registerRender(totemWhimsyWinder, "totems/");
		registerRender(totemWizardStaff, "totems/");
		registerRender(toxicLump, "misc/misc/");
		registerRender(toyGyrocopter, "misc/boss/");
		registerRender(treasureBox, "misc/misc/");
		registerRender(treatBag, "misc/boss/");
		registerRender(trilliadLeaves, "food/plants/");
		registerRender(yetiFingernails, "food/misc/");
		registerRender(trollIdol, "misc/boss/");
		registerRender(trollSkull, "misc/misc/");
		registerRender(turquoiseStripefish, "food/fish/");
		registerRender(unchargedOrb, "misc/misc/");
		registerRender(unchargedStone, "misc/misc/");
		registerRender(upgradeKitAbyssal, "misc/upgradekit/");
		registerRender(upgradeKitAncient, "misc/upgradekit/");
		registerRender(upgradeKitApoco, "misc/upgradekit/");
		registerRender(upgradeKitClown, "misc/upgradekit/");
		registerRender(upgradeKitDarkly, "misc/upgradekit/");
		registerRender(upgradeKitFloro, "misc/upgradekit/");
		registerRender(upgradeKitGolden, "misc/upgradekit/");
		registerRender(upgradeKitHaunted, "misc/upgradekit/");
		registerRender(upgradeKitLelyetian, "misc/upgradekit/");
		registerRender(upgradeKitLight, "misc/upgradekit/");
		registerRender(upgradeKitLunar, "misc/upgradekit/");
		registerRender(upgradeKitNether, "misc/upgradekit/");
		registerRender(upgradeKitPrecasian, "misc/upgradekit/");
		registerRender(upgradeKitPredator, "misc/upgradekit/");
		registerRender(upgradeKitRocky, "misc/upgradekit/");
		registerRender(upgradeKitRunic, "misc/upgradekit/");
		registerRender(upgradeKitSeaside, "misc/upgradekit/");
		registerRender(upgradeKitSmiley, "misc/upgradekit/");
		registerRender(urkaHide, "misc/misc/");
		registerRender(ursaMeat, "food/meat/");
		registerRender(ursaMeatRaw, "food/meat/");
		registerRender(chargerShank, "food/meat/");
		registerRender(chargerShankRaw, "food/meat/");
		registerRender(vileStone, "misc/boss/");
		registerRender(violetSkipper, "food/fish/");
		registerRender(voliantHeart, "misc/boss/");
		registerRender(vulcaneAugmentBattle, "misc/misc/");
		registerRender(vulcaneAugmentEquality, "misc/misc/");
		registerRender(vulcaneAugmentFire, "misc/misc/");
		registerRender(vulcaneAugmentImpairment, "misc/misc/");
		registerRender(vulcaneAugmentPoison, "misc/misc/");
		registerRender(vulcaneAugmentPower, "misc/misc/");
		registerRender(vulcaneAugmentWither, "misc/misc/");
		registerRender(warlockGem, "misc/boss/");
		registerRender(waterloggedAquaCannon, "misc/misc/");
		registerRender(waterloggedCoralArchergun, "misc/misc/");
		registerRender(waterloggedCoralCannon, "misc/misc/");
		registerRender(waterloggedCoralClogger, "misc/misc/");
		registerRender(waterloggedReefer, "misc/misc/");
		registerRender(weaponParts, "misc/misc/");
		registerRender(weaponsCase, "misc/misc/");
		registerRender(whitewashingSolution, "misc/misc/");
		registerRender(wornBook, "misc/misc/");
		registerRender(zhinxDust, "misc/misc/");
	}

	public static void doInitTasks() {
		OreDictionary.registerOre("ingotBaronyte", ingotBaronyte);
		OreDictionary.registerOre("ingotBlazium", ingotBlazium);
		OreDictionary.registerOre("ingotElecanium", ingotElecanium);
		OreDictionary.registerOre("ingotEmberstone", ingotEmberstone);
		OreDictionary.registerOre("ingotGhastly", ingotGhastly);
		OreDictionary.registerOre("ingotGhoulish", ingotGhoulish);
		OreDictionary.registerOre("ingotLimonite", ingotLimonite);
		OreDictionary.registerOre("ingotLunar", ingotLunar);
		OreDictionary.registerOre("ingotLyon", ingotLyon);
		OreDictionary.registerOre("ingotMystite", ingotMystite);
		OreDictionary.registerOre("ingotRosite", ingotRosite);
		OreDictionary.registerOre("ingotShyrestone", ingotShyrestone);
		OreDictionary.registerOre("ingotSkeletal", ingotSkeletal);
		OreDictionary.registerOre("ingotVarsium", ingotVarsium);

		OreDictionary.registerOre("gemAmethyst", amethyst);
		OreDictionary.registerOre("gemBloodstone", gemBloodstone);
		OreDictionary.registerOre("gemCrystallite", gemCrystallite);
		OreDictionary.registerOre("gemGemenyte", gemGemenyte);
		OreDictionary.registerOre("gemJade", jade);
		OreDictionary.registerOre("gemJewelyte", gemJewelyte);
		OreDictionary.registerOre("gemOrnamyte", gemOrnamyte);
		OreDictionary.registerOre("gemSapphire", sapphire);
		OreDictionary.registerOre("gemShyregem", shyregem);
	}

	private static void registerRender(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation("aoa3:" + location + item.getRegistryName().getResourcePath()), "inventory"));
	}
}
