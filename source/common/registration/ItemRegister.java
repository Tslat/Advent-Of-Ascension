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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.food.*;
import net.tslat.aoa3.item.lootbox.*;
import net.tslat.aoa3.item.minionslab.*;
import net.tslat.aoa3.item.misc.*;
import net.tslat.aoa3.item.misc.summon.*;
import net.tslat.aoa3.item.tablet.*;
import net.tslat.aoa3.item.tool.misc.ExpFlask;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;

@SuppressWarnings({"ConstantConditions", "unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class ItemRegister {
	private static ArrayList<ItemRegistryWrapper> itemRegistryList = new ArrayList<ItemRegistryWrapper>(750);

	@GameRegistry.ObjectHolder("balloon")
	public static final AmmoItem balloon = null;
	@GameRegistry.ObjectHolder("limonite_bullet")
	public static final ItemTieredBullet bulletLimonite = null;
	@GameRegistry.ObjectHolder("cannonball")
	public static final AmmoItem cannonball = null;
	@GameRegistry.ObjectHolder("chilli")
	public static final Chilli chilli = null;
	@GameRegistry.ObjectHolder("discharge_capsule")
	public static final AmmoItem dischargeCapsule = null;
	@GameRegistry.ObjectHolder("holly_arrow")
	public static final HollyArrow hollyArrow = null;
	@GameRegistry.ObjectHolder("spectral_holly_arrow")
	public static final SpectralHollyArrow hollyArrowSpectral = null;
	@GameRegistry.ObjectHolder("tipped_holly_arrow")
	public static final TippedHollyArrow hollyArrowTipped = null;
	@GameRegistry.ObjectHolder("metal_slug")
	public static final AmmoItem metalSlug = null;
	@GameRegistry.ObjectHolder("pop_shot")
	public static final PopShot popShot = null;
	@GameRegistry.ObjectHolder("spreadshot")
	public static final AmmoItem spreadshot = null;

	@GameRegistry.ObjectHolder("amethyst")
	public static final SimpleItem gemAmethyst = null;
	@GameRegistry.ObjectHolder("bloodstone")
	public static final SimpleItem gemBloodstone = null;
	@GameRegistry.ObjectHolder("crystallite")
	public static final SimpleItem gemCrystallite = null;
	@GameRegistry.ObjectHolder("gemenyte")
	public static final SimpleItem gemGemenyte = null;
	@GameRegistry.ObjectHolder("jade")
	public static final SimpleItem gemJade = null;
	@GameRegistry.ObjectHolder("jewelyte")
	public static final SimpleItem gemJewelyte = null;
	@GameRegistry.ObjectHolder("ornamyte")
	public static final SimpleItem gemOrnamyte = null;
	@GameRegistry.ObjectHolder("sapphire")
	public static final SimpleItem gemSapphire = null;
	@GameRegistry.ObjectHolder("shyregem")
	public static final SimpleItem gemShyregem = null;
	@GameRegistry.ObjectHolder("baronyte_ingot")
	public static final SimpleItem ingotBaronyte = null;
	@GameRegistry.ObjectHolder("blazium_ingot")
	public static final SimpleItem ingotBlazium = null;
	@GameRegistry.ObjectHolder("elecanium_ingot")
	public static final SimpleItem ingotElecanium = null;
	@GameRegistry.ObjectHolder("emberstone_ingot")
	public static final SimpleItem ingotEmberstone = null;
	@GameRegistry.ObjectHolder("ghastly_ingot")
	public static final SimpleItem ingotGhastly = null;
	@GameRegistry.ObjectHolder("ghoulish_ingot")
	public static final SimpleItem ingotGhoulish = null;
	@GameRegistry.ObjectHolder("limonite_ingot")
	public static final SimpleItem ingotLimonite = null;
	@GameRegistry.ObjectHolder("lunar_ingot")
	public static final SimpleItem ingotLunar = null;
	@GameRegistry.ObjectHolder("lyon_ingot")
	public static final SimpleItem ingotLyon = null;
	@GameRegistry.ObjectHolder("mystite_ingot")
	public static final SimpleItem ingotMystite = null;
	@GameRegistry.ObjectHolder("rosite_ingot")
	public static final SimpleItem ingotRosite = null;
	@GameRegistry.ObjectHolder("rusted_iron_ingot")
	public static final SimpleItem ingotRustedIron = null;
	@GameRegistry.ObjectHolder("shyrestone_ingot")
	public static final SimpleItem ingotShyrestone = null;
	@GameRegistry.ObjectHolder("skeletal_ingot")
	public static final SimpleItem ingotSkeletal = null;
	@GameRegistry.ObjectHolder("varsium_ingot")
	public static final SimpleItem ingotVarsium = null;
	@GameRegistry.ObjectHolder("runium_chunk")
	public static final SimpleItem runiumChunk = null;

	@GameRegistry.ObjectHolder("chestbone_fragment")
	public static final SimpleItem boneFragmentChestbone = null;
	@GameRegistry.ObjectHolder("footbone_fragment")
	public static final SimpleItem boneFragmentFootbone = null;
	@GameRegistry.ObjectHolder("legbone_fragment")
	public static final SimpleItem boneFragmentLegbone = null;
	@GameRegistry.ObjectHolder("skullbone_fragment")
	public static final SimpleItem boneFragmentSkullbone = null;
	@GameRegistry.ObjectHolder("charged_runium_chunk")
	public static final SimpleItem chargedRuniumChunk = null;
	@GameRegistry.ObjectHolder("blue_crystal")
	public static final SimpleItem crystalBlue = null;
	@GameRegistry.ObjectHolder("green_crystal")
	public static final SimpleItem crystalGreen = null;
	@GameRegistry.ObjectHolder("purple_crystal")
	public static final SimpleItem crystalPurple = null;
	@GameRegistry.ObjectHolder("red_crystal")
	public static final SimpleItem crystalRed = null;
	@GameRegistry.ObjectHolder("white_crystal")
	public static final SimpleItem crystalWhite = null;
	@GameRegistry.ObjectHolder("yellow_crystal")
	public static final SimpleItem crystalYellow = null;

	@GameRegistry.ObjectHolder("charged_rune")
	public static final RuneItem runeCharged = null;
	@GameRegistry.ObjectHolder("compass_rune")
	public static final RuneItem runeCompass = null;
	@GameRegistry.ObjectHolder("distortion_rune")
	public static final RuneItem runeDistortion = null;
	@GameRegistry.ObjectHolder("energy_rune")
	public static final RuneItem runeEnergy = null;
	@GameRegistry.ObjectHolder("fire_rune")
	public static final RuneItem runeFire = null;
	@GameRegistry.ObjectHolder("kinetic_rune")
	public static final RuneItem runeKinetic = null;
	@GameRegistry.ObjectHolder("life_rune")
	public static final RuneItem runeLife = null;
	@GameRegistry.ObjectHolder("lunar_rune")
	public static final RuneItem runeLunar = null;
	@GameRegistry.ObjectHolder("poison_rune")
	public static final RuneItem runePoison = null;
	@GameRegistry.ObjectHolder("power_rune")
	public static final RuneItem runePower = null;
	@GameRegistry.ObjectHolder("storm_rune")
	public static final RuneItem runeStorm = null;
	@GameRegistry.ObjectHolder("strike_rune")
	public static final RuneItem runeStrike = null;
	@GameRegistry.ObjectHolder("unpowered_rune")
	public static final RuneItem runeUnpowered = null;
	@GameRegistry.ObjectHolder("water_rune")
	public static final RuneItem runeWater = null;
	@GameRegistry.ObjectHolder("wind_rune")
	public static final RuneItem runeWind = null;
	@GameRegistry.ObjectHolder("wither_rune")
	public static final RuneItem runeWither = null;

	@GameRegistry.ObjectHolder("active_rune_stone")
	public static final SimpleItem activeRuneStone = null;
	@GameRegistry.ObjectHolder("alien_orb")
	public static final ReservedItem alienOrb = null;
	@GameRegistry.ObjectHolder("amphibiyte_lung")
	public static final AmphibiyteLung amphibiyteLung = null;
	@GameRegistry.ObjectHolder("apoco_stone")
	public static final SimpleItem apocoStone = null;
	@GameRegistry.ObjectHolder("armour_plating")
	public static final SimpleItem armourPlating = null;
	@GameRegistry.ObjectHolder("blank_slab")
	public static final SimpleItem blankSlab = null;
	@GameRegistry.ObjectHolder("chitin")
	public static final SimpleItem chitin = null;
	@GameRegistry.ObjectHolder("circus_coin")
	public static final SimpleItem circusCoin = null;
	@GameRegistry.ObjectHolder("copper_coin")
	public static final CoinItem coinCopper = null;
	@GameRegistry.ObjectHolder("gold_coin")
	public static final CoinItem coinGold = null;
	@GameRegistry.ObjectHolder("lunaver_coin")
	public static final CoinItem coinLunaver = null;
	@GameRegistry.ObjectHolder("silver_coin")
	public static final CoinItem coinSilver = null;
	@GameRegistry.ObjectHolder("confetti_pile")
	public static final SimpleItem confettiPile = null;
	@GameRegistry.ObjectHolder("coral_stone")
	public static final SimpleItem coralStone = null;
	@GameRegistry.ObjectHolder("cosmic_dust")
	public static final SimpleItem cosmicDust = null;
	@GameRegistry.ObjectHolder("cup")
	public static final SimpleItem cup = null;
	@GameRegistry.ObjectHolder("dark_bones")
	public static final ReservedItem darkBones = null;
	@GameRegistry.ObjectHolder("darkly_powder")
	public static final SimpleItem darklyPowder = null;
	@GameRegistry.ObjectHolder("dense_rock")
	public static final SimpleItem denseRock = null;
	@GameRegistry.ObjectHolder("desert_shell")
	public static final SimpleItem desertShell = null;
	@GameRegistry.ObjectHolder("distorting_artifact")
	public static final DistortingArtifact distortingArtifact = null;
	@GameRegistry.ObjectHolder("doom_stone")
	public static final SimpleItem doomStone = null;
	@GameRegistry.ObjectHolder("blue_druse")
	public static final SimpleItem druseBlue = null;
	@GameRegistry.ObjectHolder("green_druse")
	public static final SimpleItem druseGreen = null;
	@GameRegistry.ObjectHolder("purple_druse")
	public static final SimpleItem drusePurple = null;
	@GameRegistry.ObjectHolder("rainbow_druse")
	public static final SimpleItem druseRainbow = null;
	@GameRegistry.ObjectHolder("red_druse")
	public static final SimpleItem druseRed = null;
	@GameRegistry.ObjectHolder("white_druse")
	public static final SimpleItem druseWhite = null;
	@GameRegistry.ObjectHolder("yellow_druse")
	public static final SimpleItem druseYellow = null;
	@GameRegistry.ObjectHolder("enchanted_gunpowder")
	public static final SimpleItem enchantedGunpowder = null;
	@GameRegistry.ObjectHolder("eye_bulb")
	public static final EyeBulb eyeBulb = null;
	@GameRegistry.ObjectHolder("flammable_dust")
	public static final SimpleItem flammableDust = null;
	@GameRegistry.ObjectHolder("fleshy_bones")
	public static final ReservedItem fleshyBones = null;
	@GameRegistry.ObjectHolder("floating_stone")
	public static final FloatingStone floatingStone = null;
	@GameRegistry.ObjectHolder("fragmented_anima_stone")
	public static final FragmentedAnimaStone fragmentedAnimaStone = null;
	@GameRegistry.ObjectHolder("blue_gemstones")
	public static final SimpleItem gemstonesBlue = null;
	@GameRegistry.ObjectHolder("green_gemstones")
	public static final SimpleItem gemstonesGreen = null;
	@GameRegistry.ObjectHolder("purple_gemstones")
	public static final SimpleItem gemstonesPurple = null;
	@GameRegistry.ObjectHolder("red_gemstones")
	public static final SimpleItem gemstonesRed = null;
	@GameRegistry.ObjectHolder("white_gemstones")
	public static final SimpleItem gemstonesWhite = null;
	@GameRegistry.ObjectHolder("yellow_gemstones")
	public static final SimpleItem gemstonesYellow = null;
	@GameRegistry.ObjectHolder("ghostly_powder")
	public static final SimpleItem ghostlyPowder = null;
	@GameRegistry.ObjectHolder("ghostly_stone")
	public static final SimpleItem ghostlyStone = null;
	@GameRegistry.ObjectHolder("ghoulasm")
	public static final SimpleItem ghoulasm = null;
	@GameRegistry.ObjectHolder("gravitator")
	public static final Gravitator gravitator = null;
	@GameRegistry.ObjectHolder("hardened_confetti_ball")
	public static final SimpleItem hardenedConfettiBall = null;
	@GameRegistry.ObjectHolder("hive_chunk")
	public static final HiveChunk hiveChunk = null;
	@GameRegistry.ObjectHolder("holly_top_petals")
	public static final HollyTopPetals hollyTopPetals = null;
	@GameRegistry.ObjectHolder("hydro_stone")
	public static final SimpleItem hydroStone = null;
	@GameRegistry.ObjectHolder("ice_crystal")
	public static final SimpleItem iceCrystal = null;
	@GameRegistry.ObjectHolder("impure_gold")
	public static final SimpleItem impureGold = null;
	@GameRegistry.ObjectHolder("ivory")
	public static final SimpleItem ivory = null;
	@GameRegistry.ObjectHolder("jungle_thorns")
	public static final SimpleItem jungleThorns = null;
	@GameRegistry.ObjectHolder("limonite_rod")
	public static final SimpleItem limoniteRod = null;
	@GameRegistry.ObjectHolder("lotto_totem")
	public static final ItemLottoTotem lottoTotem = null;
	@GameRegistry.ObjectHolder("lunarade_mug")
	public static final SimpleItem lunaradeMug = null;
	@GameRegistry.ObjectHolder("metal_tub")
	public static final SimpleItem metalTub = null;
	@GameRegistry.ObjectHolder("magic_mending_compound")
	public static final SimpleItem magicMendingCompound = null;
	@GameRegistry.ObjectHolder("magic_mending_solution")
	public static final MagicMendingSolution magicMendingSolution = null;
	@GameRegistry.ObjectHolder("magic_repair_dust")
	public static final SimpleItem magicRepairDust = null;
	@GameRegistry.ObjectHolder("magnet_shard")
	public static final SimpleItem magnetShard = null;
	@GameRegistry.ObjectHolder("mecha_gear")
	public static final SimpleItem mechaGear = null;
	@GameRegistry.ObjectHolder("mega_rune_fragment_blue")
	public static final SimpleItem megaRuneFragmentBlue = null;
	@GameRegistry.ObjectHolder("mega_rune_fragment_green")
	public static final SimpleItem megaRuneFragmentGreen = null;
	@GameRegistry.ObjectHolder("mega_rune_fragment_red")
	public static final SimpleItem megaRuneFragmentRed = null;
	@GameRegistry.ObjectHolder("mega_rune_fragment_yellow")
	public static final SimpleItem megaRuneFragmentYellow = null;
	@GameRegistry.ObjectHolder("millenium_upgrader")
	public static final ReservedItem milleniumUpgrader = null;
	@GameRegistry.ObjectHolder("molten_upgrader")
	public static final ReservedItem moltenUpgrader = null;
	@GameRegistry.ObjectHolder("moonstone")
	public static final ReservedItem moonstone = null;
	@GameRegistry.ObjectHolder("mud_ball")
	public static final SimpleItem mudBall = null;
	@GameRegistry.ObjectHolder("mushroom_spores")
	public static final SimpleItem mushroomSpores = null;
	@GameRegistry.ObjectHolder("nightmare_flakes")
	public static final SimpleItem nightmareFlakes = null;
	@GameRegistry.ObjectHolder("old_boot")
	public static final SimpleItem oldBoot = null;
	@GameRegistry.ObjectHolder("opteryx_feather")
	public static final SimpleItem opteryxFeather = null;
	@GameRegistry.ObjectHolder("orange_spores")
	public static final SimpleItem orangeSpores = null;
	@GameRegistry.ObjectHolder("orbulon")
	public static final SimpleItem orbulon = null;
	@GameRegistry.ObjectHolder("padded_cloth")
	public static final SimpleItem paddedCloth = null;
	@GameRegistry.ObjectHolder("phantasm")
	public static final SimpleItem phantasm = null;
	@GameRegistry.ObjectHolder("power_core")
	public static final SimpleItem powerCore = null;
	@GameRegistry.ObjectHolder("primed_ghoulasm")
	public static final SimpleItem primedGhoulasm = null;
	@GameRegistry.ObjectHolder("primordial_skull")
	public static final SimpleItem primordialSkull = null;
	@GameRegistry.ObjectHolder("pure_coral_stone")
	public static final SimpleItem pureCoralStone = null;
	@GameRegistry.ObjectHolder("pure_gold")
	public static final SimpleItem pureGold = null;
	@GameRegistry.ObjectHolder("pure_rain_stone")
	public static final SimpleItem pureRainStone = null;
	@GameRegistry.ObjectHolder("rammerhead_hide")
	public static final SimpleItem rammerheadHide = null;
	@GameRegistry.ObjectHolder("realm_travel_ticket")
	public static final SimpleItem realmTravelTicket = null;
	@GameRegistry.ObjectHolder("reinforced_cloth")
	public static final SimpleItem reinforcedCloth = null;
	@GameRegistry.ObjectHolder("return_crystal")
	public static final ReturnCrystal returnCrystal = null;
	@GameRegistry.ObjectHolder("rock_bones")
	public static final ReservedItem rockBones = null;
	@GameRegistry.ObjectHolder("rosid_root")
	public static final SimpleItem rosidRoot = null;
	@GameRegistry.ObjectHolder("rune_stone")
	public static final SimpleItem runeStone = null;
	@GameRegistry.ObjectHolder("runic_energy")
	public static final SimpleItem runicEnergy = null;
	@GameRegistry.ObjectHolder("scrap_metal")
	public static final SimpleItem scrapMetal = null;
	@GameRegistry.ObjectHolder("scream_shield")
	public static final SimpleItem screamShield = null;
	@GameRegistry.ObjectHolder("sharp_claw")
	public static final SimpleItem sharpClaw = null;
	@GameRegistry.ObjectHolder("sludge_parasite")
	public static final SimpleItem sludgeParasite = null;
	@GameRegistry.ObjectHolder("small_blue_petal")
	public static final SimpleItem smallPetalBlue = null;
	@GameRegistry.ObjectHolder("small_green_petal")
	public static final SimpleItem smallPetalGreen = null;
	@GameRegistry.ObjectHolder("small_orange_petal")
	public static final SimpleItem smallPetalOrange = null;
	@GameRegistry.ObjectHolder("small_purple_petal")
	public static final SimpleItem smallPetalPurple = null;
	@GameRegistry.ObjectHolder("small_red_petal")
	public static final SimpleItem smallPetalRed = null;
	@GameRegistry.ObjectHolder("soulbone")
	public static final ReservedItem soulbone = null;
	@GameRegistry.ObjectHolder("sticky_slime")
	public static final SimpleItem stickySlime = null;
	@GameRegistry.ObjectHolder("blue_strange_stone")
	public static final SimpleItem strangeStoneBlue = null;
	@GameRegistry.ObjectHolder("white_strange_stone")
	public static final SimpleItem strangeStoneWhite = null;
	@GameRegistry.ObjectHolder("yellow_strange_stone")
	public static final SimpleItem strangeStoneYellow = null;
	@GameRegistry.ObjectHolder("tea_shreddings")
	public static final TeaShreddings teaShreddings = null;
	@GameRegistry.ObjectHolder("thorny_petals")
	public static final ThornyPetals thornyPetals = null;
	@GameRegistry.ObjectHolder("torn_cloth")
	public static final SimpleItem tornCloth = null;
	@GameRegistry.ObjectHolder("toxic_lump")
	public static final SimpleItem toxicLump = null;
	@GameRegistry.ObjectHolder("troll_skull")
	public static final SimpleItem trollSkull = null;
	@GameRegistry.ObjectHolder("uncharged_orb")
	public static final SimpleItem unchargedOrb = null;
	@GameRegistry.ObjectHolder("uncharged_stone")
	public static final SimpleItem unchargedStone = null;
	@GameRegistry.ObjectHolder("unstable_gunpowder")
	public static final SimpleItem unstableGunpowder = null;
	@GameRegistry.ObjectHolder("urka_hide")
	public static final SimpleItem urkaHide = null;
	@GameRegistry.ObjectHolder("void_scales")
	public static final SimpleItem voidScales = null;
	@GameRegistry.ObjectHolder("vulcane_augment_battle")
	public static final SimpleItem vulcaneAugmentBattle = null;
	@GameRegistry.ObjectHolder("vulcane_augment_equality")
	public static final SimpleItem vulcaneAugmentEquality = null;
	@GameRegistry.ObjectHolder("vulcane_augment_fire")
	public static final SimpleItem vulcaneAugmentFire = null;
	@GameRegistry.ObjectHolder("vulcane_augment_impairment")
	public static final SimpleItem vulcaneAugmentImpairment = null;
	@GameRegistry.ObjectHolder("vulcane_augment_poison")
	public static final SimpleItem vulcaneAugmentPoison = null;
	@GameRegistry.ObjectHolder("vulcane_augment_power")
	public static final SimpleItem vulcaneAugmentPower = null;
	@GameRegistry.ObjectHolder("vulcane_augment_wither")
	public static final SimpleItem vulcaneAugmentWither = null;
	@GameRegistry.ObjectHolder("weapon_parts")
	public static final SimpleItem weaponParts = null;
	@GameRegistry.ObjectHolder("whitewashing_solution")
	public static final SimpleItem whitewashingSolution = null;
	@GameRegistry.ObjectHolder("worn_book")
	public static final WornBook wornBook = null;
	@GameRegistry.ObjectHolder("yellow_spores")
	public static final SimpleItem yellowSpores = null;
	@GameRegistry.ObjectHolder("zhinx_dust")
	public static final SimpleItem zhinxDust = null;

	@GameRegistry.ObjectHolder("archergun_frame")
	public static final FrameItem frameArchergun = null;
	@GameRegistry.ObjectHolder("blaster_frame")
	public static final FrameItem frameBlaster = null;
	@GameRegistry.ObjectHolder("boots_frame")
	public static final FrameItem frameBoots = null;
	@GameRegistry.ObjectHolder("leggings_frame")
	public static final FrameItem frameLeggings = null;
	@GameRegistry.ObjectHolder("chestplate_frame")
	public static final FrameItem frameChestplate = null;
	@GameRegistry.ObjectHolder("helmet_frame")
	public static final FrameItem frameHelmet = null;
	@GameRegistry.ObjectHolder("cannon_frame")
	public static final FrameItem frameCannon = null;
	@GameRegistry.ObjectHolder("gun_frame")
	public static final FrameItem frameGun = null;
	@GameRegistry.ObjectHolder("shotgun_frame")
	public static final FrameItem frameShotgun = null;
	@GameRegistry.ObjectHolder("sniper_frame")
	public static final FrameItem frameSniper = null;

	@GameRegistry.ObjectHolder("ancient_orb")
	public static final SimpleItem ancientOrb = null;
	@GameRegistry.ObjectHolder("ancient_ring")
	public static final SimpleItem ancientRing = null;
	@GameRegistry.ObjectHolder("bone_horn")
	public static final BoneHorn boneHorn = null;
	@GameRegistry.ObjectHolder("book_of_shadows")
	public static final SimpleItem bookOfShadows = null;
	@GameRegistry.ObjectHolder("boulder_dash")
	public static final SimpleItem boulderDash = null;
	@GameRegistry.ObjectHolder("call_of_the_drake")
	public static final SimpleItem callOfTheDrake = null;
	@GameRegistry.ObjectHolder("explosive_gems")
	public static final SimpleItem explosiveGems = null;
	@GameRegistry.ObjectHolder("explosive_idol")
	public static final BossSpawningItem explosiveIdol = null;
	@GameRegistry.ObjectHolder("giant_crystal")
	public static final SimpleItem giantCrystal = null;
	@GameRegistry.ObjectHolder("gold_spring")
	public static final SimpleItem goldSpring = null;
	@GameRegistry.ObjectHolder("guardians_eye")
	public static final SimpleItem guardiansEye = null;
	@GameRegistry.ObjectHolder("haunted_idol")
	public static final BossSpawningItem hauntedIdol = null;
	@GameRegistry.ObjectHolder("heavy_boulder")
	public static final SimpleItem heavyBoulder = null;
	@GameRegistry.ObjectHolder("hive_egg")
	public static final SimpleItem hiveEgg = null;
	@GameRegistry.ObjectHolder("mega_rune_stone")
	public static final SimpleItem megaRuneStone = null;
	@GameRegistry.ObjectHolder("nethengeic_callstone")
	public static final BossSpawningItem nethengeicCallstone = null;
	@GameRegistry.ObjectHolder("observing_eye")
	public static final SimpleItem observingEye = null;
	@GameRegistry.ObjectHolder("petals")
	public static final SimpleItem petals = null;
	@GameRegistry.ObjectHolder("primordial_dust")
	public static final SimpleItem primordialDust = null;
	@GameRegistry.ObjectHolder("pure_water_stone")
	public static final SimpleItem pureWaterStone = null;
	@GameRegistry.ObjectHolder("shroom_stone")
	public static final BossSpawningItem shroomStone = null;
	@GameRegistry.ObjectHolder("silvro_coin")
	public static final SimpleItem silvroCoin = null;
	@GameRegistry.ObjectHolder("staring_eye")
	public static final SimpleItem staringEye = null;
	@GameRegistry.ObjectHolder("toy_gyrocopter")
	public static final ToyGyrocopter toyGyrocopter = null;
	@GameRegistry.ObjectHolder("treat_bag")
	public static final TreatBag treatBag = null;
	@GameRegistry.ObjectHolder("troll_idol")
	public static final BossSpawningItem trollIdol = null;
	@GameRegistry.ObjectHolder("vile_stone")
	public static final SimpleItem vileStone = null;
	@GameRegistry.ObjectHolder("voliant_heart")
	public static final SimpleItem voliantHeart = null;
	@GameRegistry.ObjectHolder("warlock_gem")
	public static final SimpleItem warlockGem = null;

	@GameRegistry.ObjectHolder("abyss_tokens")
	public static final DimensionalTokensItem tokensAbyss = null;
	@GameRegistry.ObjectHolder("baron_tokens")
	public static final DimensionalTokensItem tokensBaron = null;
	@GameRegistry.ObjectHolder("borean_tokens")
	public static final DimensionalTokensItem tokensBorean = null;
	@GameRegistry.ObjectHolder("candyland_tokens")
	public static final DimensionalTokensItem tokensCandyland = null;
	@GameRegistry.ObjectHolder("celeve_tokens")
	public static final DimensionalTokensItem tokensCeleve = null;
	@GameRegistry.ObjectHolder("creeponia_tokens")
	public static final DimensionalTokensItem tokensCreeponia = null;
	@GameRegistry.ObjectHolder("crystevia_tokens")
	public static final DimensionalTokensItem tokensCrystevia = null;
	@GameRegistry.ObjectHolder("deeplands_tokens")
	public static final DimensionalTokensItem tokensDeeplands = null;
	@GameRegistry.ObjectHolder("dungeon_tokens")
	public static final DimensionalTokensItem tokensDungeon = null;
	@GameRegistry.ObjectHolder("dustopia_tokens")
	public static final DimensionalTokensItem tokensDustopia = null;
	@GameRegistry.ObjectHolder("gardencia_tokens")
	public static final DimensionalTokensItem tokensGardencia = null;
	@GameRegistry.ObjectHolder("greckon_tokens")
	public static final DimensionalTokensItem tokensGreckon = null;
	@GameRegistry.ObjectHolder("haven_tokens")
	public static final DimensionalTokensItem tokensHaven = null;
	@GameRegistry.ObjectHolder("iromine_tokens")
	public static final DimensionalTokensItem tokensIromine = null;
	@GameRegistry.ObjectHolder("lelyetia_tokens")
	public static final DimensionalTokensItem tokensLelyetia = null;
	@GameRegistry.ObjectHolder("lunar_tokens")
	public static final DimensionalTokensItem tokensLunar = null;
	@GameRegistry.ObjectHolder("mysterium_tokens")
	public static final DimensionalTokensItem tokensMysterium = null;
	@GameRegistry.ObjectHolder("nether_tokens")
	public static final DimensionalTokensItem tokensNether = null;
	@GameRegistry.ObjectHolder("precasian_tokens")
	public static final DimensionalTokensItem tokensPrecasian = null;
	@GameRegistry.ObjectHolder("runandor_tokens")
	public static final DimensionalTokensItem tokensRunandor = null;
	@GameRegistry.ObjectHolder("shyrelands_tokens")
	public static final DimensionalTokensItem tokensShyrelands = null;
	@GameRegistry.ObjectHolder("vox_ponds_tokens")
	public static final DimensionalTokensItem tokensVoxPonds = null;

	@GameRegistry.ObjectHolder("abyssal_upgrade_kit")
	public static final SimpleItem upgradeKitAbyssal = null;
	@GameRegistry.ObjectHolder("ancient_upgrade_kit")
	public static final SimpleItem upgradeKitAncient = null;
	@GameRegistry.ObjectHolder("apoco_upgrade_kit")
	public static final SimpleItem upgradeKitApoco = null;
	@GameRegistry.ObjectHolder("clown_upgrade_kit")
	public static final SimpleItem upgradeKitClown = null;
	@GameRegistry.ObjectHolder("darkly_upgrade_kit")
	public static final SimpleItem upgradeKitDarkly = null;
	@GameRegistry.ObjectHolder("floro_upgrade_kit")
	public static final SimpleItem upgradeKitFloro = null;
	@GameRegistry.ObjectHolder("golden_upgrade_kit")
	public static final SimpleItem upgradeKitGolden = null;
	@GameRegistry.ObjectHolder("haunted_upgrade_kit")
	public static final SimpleItem upgradeKitHaunted = null;
	@GameRegistry.ObjectHolder("lelyetian_upgrade_kit")
	public static final SimpleItem upgradeKitLelyetian = null;
	@GameRegistry.ObjectHolder("light_upgrade_kit")
	public static final SimpleItem upgradeKitLight = null;
	@GameRegistry.ObjectHolder("lunar_upgrade_kit")
	public static final SimpleItem upgradeKitLunar = null;
	@GameRegistry.ObjectHolder("nether_upgrade_kit")
	public static final SimpleItem upgradeKitNether = null;
	@GameRegistry.ObjectHolder("precasian_upgrade_kit")
	public static final SimpleItem upgradeKitPrecasian = null;
	@GameRegistry.ObjectHolder("predator_upgrade_kit")
	public static final SimpleItem upgradeKitPredator = null;
	@GameRegistry.ObjectHolder("rocky_upgrade_kit")
	public static final SimpleItem upgradeKitRocky = null;
	@GameRegistry.ObjectHolder("runic_upgrade_kit")
	public static final SimpleItem upgradeKitRunic = null;
	@GameRegistry.ObjectHolder("seaside_upgrade_kit")
	public static final SimpleItem upgradeKitSeaside = null;
	@GameRegistry.ObjectHolder("smiley_upgrade_kit")
	public static final SimpleItem upgradeKitSmiley = null;

	@GameRegistry.ObjectHolder("waterlogged_aqua_cannon")
	public static final WaterloggedItem waterloggedAquaCannon = null;
	@GameRegistry.ObjectHolder("waterlogged_coral_archergun")
	public static final WaterloggedItem waterloggedCoralArchergun = null;
	@GameRegistry.ObjectHolder("waterlogged_coral_cannon")
	public static final WaterloggedItem waterloggedCoralCannon = null;
	@GameRegistry.ObjectHolder("waterlogged_coral_clogger")
	public static final WaterloggedItem waterloggedCoralClogger = null;
	@GameRegistry.ObjectHolder("waterlogged_reefer")
	public static final WaterloggedItem waterloggedReefer = null;

	@GameRegistry.ObjectHolder("incomplete_mecha_archergun")
	public static final IncompleteMechaItem incompleteMechaArchergun = null;
	@GameRegistry.ObjectHolder("incomplete_mecha_bow")
	public static final IncompleteMechaItem incompleteMechaBow = null;
	@GameRegistry.ObjectHolder("incomplete_mecha_cannon")
	public static final IncompleteMechaItem incompleteMechaCannon = null;
	@GameRegistry.ObjectHolder("incomplete_mechanical_assault_rifle")
	public static final IncompleteMechaItem incompleteMechanicalAssaultRifle = null;
	@GameRegistry.ObjectHolder("incomplete_mecha_staff")
	public static final IncompleteMechaItem incompleteMechaStaff = null;
	@GameRegistry.ObjectHolder("incomplete_mechyro")
	public static final IncompleteMechaItem incompleteMechyro = null;

	@GameRegistry.ObjectHolder("blank_realmstone")
	public static final BlankRealmstone realmstoneBlank = null;
	@GameRegistry.ObjectHolder("abyss_realmstone")
	public static final Realmstone realmstoneAbyss = null;
	@GameRegistry.ObjectHolder("nether_realmstone")
	public static final Realmstone realmstoneNether = null;
	@GameRegistry.ObjectHolder("ancient_cavern_realmstone")
	public static final Realmstone realmstoneAncientCavern = null;
	@GameRegistry.ObjectHolder("barathos_realmstone")
	public static final Realmstone realmstoneBarathos = null;
	@GameRegistry.ObjectHolder("borean_realmstone")
	public static final Realmstone realmstoneBorean = null;
	@GameRegistry.ObjectHolder("candyland_realmstone")
	public static final Realmstone realmstoneCandyland = null;
	@GameRegistry.ObjectHolder("celeve_realmstone")
	public static final Realmstone realmstoneCeleve = null;
	@GameRegistry.ObjectHolder("creeponia_realmstone")
	public static final Realmstone realmstoneCreeponia = null;
	@GameRegistry.ObjectHolder("crystevia_realmstone")
	public static final Realmstone realmstoneCrystevia = null;
	@GameRegistry.ObjectHolder("deeplands_realmstone")
	public static final Realmstone realmstoneDeeplands = null;
	@GameRegistry.ObjectHolder("dustopia_realmstone")
	public static final Realmstone realmstoneDustopia = null;
	@GameRegistry.ObjectHolder("gardencia_realmstone")
	public static final Realmstone realmstoneGardencia = null;
	@GameRegistry.ObjectHolder("greckon_realmstone")
	public static final Realmstone realmstoneGreckon = null;
	@GameRegistry.ObjectHolder("haven_realmstone")
	public static final Realmstone realmstoneHaven = null;
	@GameRegistry.ObjectHolder("immortallis_realmstone")
	public static final Realmstone realmstoneImmortallis = null;
	@GameRegistry.ObjectHolder("iromine_realmstone")
	public static final Realmstone realmstoneIromine = null;
	@GameRegistry.ObjectHolder("lelyetia_realmstone")
	public static final Realmstone realmstoneLelyetia = null;
	@GameRegistry.ObjectHolder("lunalus_realmstone")
	public static final Realmstone realmstoneLunalus = null;
	@GameRegistry.ObjectHolder("mysterium_realmstone")
	public static final Realmstone realmstoneMysterium = null;
	@GameRegistry.ObjectHolder("precasia_realmstone")
	public static final Realmstone realmstonePrecasia = null;
	@GameRegistry.ObjectHolder("runandor_realmstone")
	public static final Realmstone realmstoneRunandor = null;
	@GameRegistry.ObjectHolder("shyrelands_realmstone")
	public static final Realmstone realmstoneShyrelands = null;
	@GameRegistry.ObjectHolder("vox_ponds_realmstone")
	public static final Realmstone realmstoneVoxPonds = null;

	@GameRegistry.ObjectHolder("ancient_essence")
	public static final AuguryEssence essenceAncient = null;
	@GameRegistry.ObjectHolder("charged_essence")
	public static final AuguryEssence essenceCharged = null;
	@GameRegistry.ObjectHolder("dark_essence")
	public static final AuguryEssence essenceDark = null;
	@GameRegistry.ObjectHolder("divine_essence")
	public static final AuguryEssence essenceDivine = null;
	@GameRegistry.ObjectHolder("empowered_essence")
	public static final AuguryEssence essenceEmpowered = null;
	@GameRegistry.ObjectHolder("ethereal_essence")
	public static final AuguryEssence essenceEthereal = null;
	@GameRegistry.ObjectHolder("luminate_essence")
	public static final AuguryEssence essenceLuminate = null;
	@GameRegistry.ObjectHolder("molten_essence")
	public static final AuguryEssence essenceMolten = null;
	@GameRegistry.ObjectHolder("ominous_essence")
	public static final AuguryEssence essenceOminous = null;
	@GameRegistry.ObjectHolder("weak_essence")
	public static final AuguryEssence essenceWeak = null;

	@GameRegistry.ObjectHolder("agility_tablet")
	public static final TabletItem tabletAgility = null;
	@GameRegistry.ObjectHolder("awareness_tablet")
	public static final TabletItem tabletAwareness = null;
	@GameRegistry.ObjectHolder("breeding_tablet")
	public static final TabletItem tabletBreeding = null;
	@GameRegistry.ObjectHolder("cleansing_tablet")
	public static final TabletItem tabletCleansing = null;
	@GameRegistry.ObjectHolder("distortion_tablet")
	public static final TabletItem tabletDistortion = null;
	@GameRegistry.ObjectHolder("energy_tablet")
	public static final TabletItem tabletEnergy = null;
	@GameRegistry.ObjectHolder("gravity_tablet")
	public static final TabletItem tabletGravity = null;
	@GameRegistry.ObjectHolder("oxygen_tablet")
	public static final TabletItem tabletOxygen = null;
	@GameRegistry.ObjectHolder("pressure_tablet")
	public static final TabletItem tabletPressure = null;
	@GameRegistry.ObjectHolder("proficiency_tablet")
	public static final TabletItem tabletProficiency = null;
	@GameRegistry.ObjectHolder("resistance_tablet")
	public static final TabletItem tabletResistance = null;
	@GameRegistry.ObjectHolder("sanctity_tablet")
	public static final TabletItem tabletSanctity = null;
	@GameRegistry.ObjectHolder("satiation_tablet")
	public static final TabletItem tabletSatiation = null;
	@GameRegistry.ObjectHolder("sight_tablet")
	public static final TabletItem tabletSight = null;
	@GameRegistry.ObjectHolder("strength_tablet")
	public static final TabletItem tabletStrength = null;
	@GameRegistry.ObjectHolder("untiring_tablet")
	public static final TabletItem tabletUntiring = null;
	@GameRegistry.ObjectHolder("vitality_tablet")
	public static final TabletItem tabletVitality = null;

	@GameRegistry.ObjectHolder("progress_coin0")
	public static final SimpleItem progressCoin0 = null;
	@GameRegistry.ObjectHolder("progress_coin1")
	public static final SimpleItem progressCoin1 = null;
	@GameRegistry.ObjectHolder("progress_coin2")
	public static final SimpleItem progressCoin2 = null;
	@GameRegistry.ObjectHolder("progress_coin3")
	public static final SimpleItem progressCoin3 = null;
	@GameRegistry.ObjectHolder("progress_coin4")
	public static final SimpleItem progressCoin4 = null;

	@GameRegistry.ObjectHolder("ambient_power_stone")
	public static final SimpleItem powerStoneAmbient = null;
	@GameRegistry.ObjectHolder("blooming_power_stone")
	public static final SimpleItem powerStoneBlooming = null;
	@GameRegistry.ObjectHolder("glaring_power_stone")
	public static final SimpleItem powerStoneGlaring = null;
	@GameRegistry.ObjectHolder("gleaming_power_stone")
	public static final SimpleItem powerStoneGleaming = null;
	@GameRegistry.ObjectHolder("glistening_power_stone")
	public static final SimpleItem powerStoneGlistening = null;
	@GameRegistry.ObjectHolder("glowing_power_stone")
	public static final SimpleItem powerStoneGlowing = null;
	@GameRegistry.ObjectHolder("radiant_power_stone")
	public static final SimpleItem powerStoneRadiant = null;
	@GameRegistry.ObjectHolder("shining_power_stone")
	public static final SimpleItem powerStoneShining = null;

	@GameRegistry.ObjectHolder("ambient_infusion_stone")
	public static final InfusionStone infusionStoneAmbient = null;
	@GameRegistry.ObjectHolder("blooming_infusion_stone")
	public static final InfusionStone infusionStoneBlooming = null;
	@GameRegistry.ObjectHolder("glaring_infusion_stone")
	public static final InfusionStone infusionStoneGlaring = null;
	@GameRegistry.ObjectHolder("gleaming_infusion_stone")
	public static final InfusionStone infusionStoneGleaming = null;
	@GameRegistry.ObjectHolder("glistening_infusion_stone")
	public static final InfusionStone infusionStoneGlistening = null;
	@GameRegistry.ObjectHolder("glowing_infusion_stone")
	public static final InfusionStone infusionStoneGlowing = null;
	@GameRegistry.ObjectHolder("radiant_infusion_stone")
	public static final InfusionStone infusionStoneRadiant = null;
	@GameRegistry.ObjectHolder("shining_infusion_stone")
	public static final InfusionStone infusionStoneShining = null;

	@GameRegistry.ObjectHolder("giant_skill_crystal")
	public static final SkillCrystal skillCrystalGiant = null;
	@GameRegistry.ObjectHolder("large_skill_crystal")
	public static final SkillCrystal skillCrystalLarge = null;
	@GameRegistry.ObjectHolder("medium_skill_crystal")
	public static final SkillCrystal skillCrystalMedium = null;
	@GameRegistry.ObjectHolder("small_skill_crystal")
	public static final SkillCrystal skillCrystalSmall = null;

	@GameRegistry.ObjectHolder("crystal_box")
	public static final CrystalBox crystalBox = null;
	@GameRegistry.ObjectHolder("fish_case")
	public static final FishCase fishCase = null;
	@GameRegistry.ObjectHolder("gem_bag")
	public static final GemBag gemBag = null;
	@GameRegistry.ObjectHolder("rune_box")
	public static final RuneBox runeBox = null;
	@GameRegistry.ObjectHolder("shiny_box")
	public static final ShinyBox shinyBox = null;
	@GameRegistry.ObjectHolder("treasure_box")
	public static final TreasureBox treasureBox = null;
	@GameRegistry.ObjectHolder("weapons_case")
	public static final WeaponsCase weaponsCase = null;

	@GameRegistry.ObjectHolder("raw_candlefish")
	public static final BasicFood candlefishRaw = null;
	@GameRegistry.ObjectHolder("raw_crimson_skipper")
	public static final BasicFood crimsonSkipperRaw = null;
	@GameRegistry.ObjectHolder("raw_crimson_stripefish")
	public static final BasicFood crimsonStripefishRaw = null;
	@GameRegistry.ObjectHolder("raw_dark_hatchetfish")
	public static final BasicFood darkHatchetfishRaw = null;
	@GameRegistry.ObjectHolder("raw_fingerfish")
	public static final BasicFood fingerfishRaw = null;
	@GameRegistry.ObjectHolder("raw_golden_gullfish")
	public static final BasicFood goldenGullfishRaw = null;
	@GameRegistry.ObjectHolder("raw_ironback")
	public static final BasicFood ironbackRaw = null;
	@GameRegistry.ObjectHolder("raw_limefish")
	public static final BasicFood limefishRaw = null;
	@GameRegistry.ObjectHolder("raw_pearl_stripefish")
	public static final BasicFood pearlStripefishRaw = null;
	@GameRegistry.ObjectHolder("raw_rainbowfish")
	public static final BasicFood rainbowfishRaw = null;
	@GameRegistry.ObjectHolder("raw_razorfish")
	public static final BasicFood razorfishRaw = null;
	@GameRegistry.ObjectHolder("raw_rocketfish")
	public static final BasicFood rocketfishRaw = null;
	@GameRegistry.ObjectHolder("raw_sailback")
	public static final BasicFood sailbackRaw = null;
	@GameRegistry.ObjectHolder("raw_sapphire_strider")
	public static final BasicFood sapphireStriderRaw = null;
	@GameRegistry.ObjectHolder("raw_turquoise_stripefish")
	public static final BasicFood turquoiseStripefishRaw = null;
	@GameRegistry.ObjectHolder("raw_violet_skipper")
	public static final BasicFood violetSkipperRaw = null;
	@GameRegistry.ObjectHolder("candlefish")
	public static final HealingFishFood candlefish = null;
	@GameRegistry.ObjectHolder("crimson_skipper")
	public static final HealingFishFood crimsonSkipper = null;
	@GameRegistry.ObjectHolder("crimson_stripefish")
	public static final HealingFishFood crimsonStripefish = null;
	@GameRegistry.ObjectHolder("dark_hatchetfish")
	public static final HealingFishFood darkHatchetfish = null;
	@GameRegistry.ObjectHolder("fingerfish")
	public static final HealingFishFood fingerfish = null;
	@GameRegistry.ObjectHolder("golden_gullfish")
	public static final HealingFishFood goldenGullfish = null;
	@GameRegistry.ObjectHolder("ironback")
	public static final HealingFishFood ironback = null;
	@GameRegistry.ObjectHolder("limefish")
	public static final HealingFishFood limefish = null;
	@GameRegistry.ObjectHolder("pearl_stripefish")
	public static final HealingFishFood pearlStripefish = null;
	@GameRegistry.ObjectHolder("rainbowfish")
	public static final HealingFishFood rainbowfish = null;
	@GameRegistry.ObjectHolder("razorfish")
	public static final HealingFishFood razorfish = null;
	@GameRegistry.ObjectHolder("rocketfish")
	public static final HealingFishFood rocketfish = null;
	@GameRegistry.ObjectHolder("sailback")
	public static final HealingFishFood sailback = null;
	@GameRegistry.ObjectHolder("sapphire_strider")
	public static final HealingFishFood sapphireStrider = null;
	@GameRegistry.ObjectHolder("turquoise_stripefish")
	public static final HealingFishFood turquoiseStripefish = null;
	@GameRegistry.ObjectHolder("violet_skipper")
	public static final HealingFishFood violetSkipper = null;

	@GameRegistry.ObjectHolder("bubble_berries")
	public static final BubbleBerries bubbleBerries = null;
	@GameRegistry.ObjectHolder("candy_cane")
	public static final BasicFood candyCane = null;
	@GameRegistry.ObjectHolder("candy_corn")
	public static final BasicFood candyCorn = null;
	@GameRegistry.ObjectHolder("raw_charger_shank")
	public static final BasicFood chargerShankRaw = null;
	@GameRegistry.ObjectHolder("charger_shank")
	public static final BasicFood chargerShank = null;
	@GameRegistry.ObjectHolder("raw_chimera_chop")
	public static final BasicFood chimeraChopRaw = null;
	@GameRegistry.ObjectHolder("chimera_chop")
	public static final BasicFood chimeraChop = null;
	@GameRegistry.ObjectHolder("eye_candy")
	public static final EyeCandy eyeCandy = null;
	@GameRegistry.ObjectHolder("fiery_chops")
	public static final FieryChops fieryChops = null;
	@GameRegistry.ObjectHolder("floracle_sticks")
	public static final FloracleSticks floracleSticks = null;
	@GameRegistry.ObjectHolder("fungal_tea")
	public static final FungalTea fungalTea = null;
	@GameRegistry.ObjectHolder("raw_furlion_chop")
	public static final BasicFood furlionChopRaw = null;
	@GameRegistry.ObjectHolder("furlion_chop")
	public static final BasicFood furlionChop = null;
	@GameRegistry.ObjectHolder("gingerbread_cookie")
	public static final BasicFood gingerbreadCookie = null;
	@GameRegistry.ObjectHolder("gingerbread_wing")
	public static final BasicFood gingerbreadWing = null;
	@GameRegistry.ObjectHolder("goldicap_petals")
	public static final GoldicapPetals goldicapPetals = null;
	@GameRegistry.ObjectHolder("raw_halycon_beef")
	public static final RawHalyconBeef halyconBeefRaw = null;
	@GameRegistry.ObjectHolder("halycon_beef")
	public static final HalyconBeef halyconBeef = null;
	@GameRegistry.ObjectHolder("halycon_milk")
	public static final HalyconMilk halyconMilk = null;
	@GameRegistry.ObjectHolder("heart_fruit")
	public static final HeartFruit heartFruit = null;
	@GameRegistry.ObjectHolder("hot_rod")
	public static final HotRod hotRod = null;
	@GameRegistry.ObjectHolder("lunacrike")
	public static final Lunacrike lunacrike = null;
	@GameRegistry.ObjectHolder("luna_globe")
	public static final LunaGlobe lunaGlobe = null;
	@GameRegistry.ObjectHolder("lunalons")
	public static final Lunalons lunalons = null;
	@GameRegistry.ObjectHolder("lunarade")
	public static final Lunarade lunarade = null;
	@GameRegistry.ObjectHolder("magic_marang")
	public static final MagicMarang magicMarang = null;
	@GameRegistry.ObjectHolder("mystic_shrooms")
	public static final MysticShrooms mysticShrooms = null;
	@GameRegistry.ObjectHolder("natural_tea")
	public static final NaturalTea naturalTea = null;
	@GameRegistry.ObjectHolder("nature_melon_slice")
	public static final BasicFood natureMelonSlice = null;
	@GameRegistry.ObjectHolder("peppermint_candy")
	public static final BasicFood peppermintCandy = null;
	@GameRegistry.ObjectHolder("rosidons")
	public static final Rosidons rosidons = null;
	@GameRegistry.ObjectHolder("sour_candy")
	public static final BasicFood sourCandy = null;
	@GameRegistry.ObjectHolder("sour_gummy")
	public static final BasicFood sourGummy = null;
	@GameRegistry.ObjectHolder("sour_pop")
	public static final BasicFood sourPop = null;
	@GameRegistry.ObjectHolder("spearmint_candy")
	public static final BasicFood spearmintCandy = null;
	@GameRegistry.ObjectHolder("tea")
	public static final Tea tea = null;
	@GameRegistry.ObjectHolder("trilliad_leaves")
	public static final TrilliadLeaves trilliadLeaves = null;
	@GameRegistry.ObjectHolder("raw_ursa_meat")
	public static final BasicFood ursaMeatRaw = null;
	@GameRegistry.ObjectHolder("ursa_meat")
	public static final BasicFood ursaMeat = null;
	@GameRegistry.ObjectHolder("yeti_fingernails")
	public static final YetiFingernails yetiFingernails = null;

	@GameRegistry.ObjectHolder("diamond_bowl")
	public static final InfusionBowl diamondBowl = null;
	@GameRegistry.ObjectHolder("stone_bowl")
	public static final InfusionBowl stoneBowl = null;
	@GameRegistry.ObjectHolder("exp_flask")
	public static final ExpFlask expFlask = null;

	@GameRegistry.ObjectHolder("alluricorn_slab")
	public static final BaseSlab slabAlluricorn = null;
	@GameRegistry.ObjectHolder("blissard_slab")
	public static final BaseSlab slabBlissard = null;
	@GameRegistry.ObjectHolder("compeer_slab")
	public static final BaseSlab slabCompeer = null;
	@GameRegistry.ObjectHolder("construct_of_servility_slab")
	public static final BaseSlab slabConstructOfServility = null;
	@GameRegistry.ObjectHolder("corby_slab")
	public static final BaseSlab slabCorby = null;
	@GameRegistry.ObjectHolder("craggy_slab")
	public static final BaseSlab slabCraggy = null;
	@GameRegistry.ObjectHolder("draggy_slab")
	public static final BaseSlab slabDraggy = null;
	@GameRegistry.ObjectHolder("ender_carrier_slab")
	public static final BaseSlab slabEnderCarrier = null;
	@GameRegistry.ObjectHolder("gnawer_slab")
	public static final BaseSlab slabGnawer = null;
	@GameRegistry.ObjectHolder("goofer_slab")
	public static final BaseSlab slabGoofer = null;
	@GameRegistry.ObjectHolder("healing_golem_slab")
	public static final BaseSlab slabHealingGolem = null;
	@GameRegistry.ObjectHolder("hellquin_slab")
	public static final BaseSlab slabHellquin = null;
	@GameRegistry.ObjectHolder("horntial_slab")
	public static final BaseSlab slabHorntail = null;
	@GameRegistry.ObjectHolder("mecha_cyclops_slab")
	public static final BaseSlab slabMechaCyclops = null;
	@GameRegistry.ObjectHolder("mecha_skellox_slab")
	public static final BaseSlab slabMechaSkellox = null;
	@GameRegistry.ObjectHolder("penguin_slab")
	public static final BaseSlab slabPenguin = null;
	@GameRegistry.ObjectHolder("plateosaur_slab")
	public static final BaseSlab slabPlateosaur = null;
	@GameRegistry.ObjectHolder("rammerhorn_slab")
	public static final BaseSlab slabRammerhorn = null;
	@GameRegistry.ObjectHolder("shaddy_slab")
	public static final BaseSlab slabShaddy = null;
	@GameRegistry.ObjectHolder("spikeback_slab")
	public static final BaseSlab slabSpikeback = null;
	@GameRegistry.ObjectHolder("spraggy_slab")
	public static final BaseSlab slabSpraggy = null;
	@GameRegistry.ObjectHolder("waggy_slab")
	public static final BaseSlab slabWaggy = null;

	@GameRegistry.ObjectHolder("bubble_berry_seeds")
	public static final SeedsItem seedsBubbleBerry = null;
	@GameRegistry.ObjectHolder("chilli_seeds")
	public static final SeedsItem seedsChilli = null;
	@GameRegistry.ObjectHolder("floracle_seeds")
	public static final SeedsItem seedsFloracle = null;
	@GameRegistry.ObjectHolder("goldicap_seeds")
	public static final SeedsItem seedsGoldicap = null;
	@GameRegistry.ObjectHolder("heart_fruit_seeds")
	public static final SeedsItem seedsHeartFruit = null;
	@GameRegistry.ObjectHolder("holly_top_seeds")
	public static final SeedsItem seedsHollyTop = null;
	@GameRegistry.ObjectHolder("lunacrike_seeds")
	public static final SeedsItem seedsLunacrike = null;
	@GameRegistry.ObjectHolder("luna_globe_seeds")
	public static final SeedsItem seedsLunaGlobe = null;
	@GameRegistry.ObjectHolder("lunalon_seeds")
	public static final SeedsItem seedsLunalon = null;
	@GameRegistry.ObjectHolder("magic_marang_seeds")
	public static final SeedsItem seedsMagicMarang = null;
	@GameRegistry.ObjectHolder("rosidon_seeds")
	public static final SeedsItem seedsRosidon = null;
	@GameRegistry.ObjectHolder("tea_seeds")
	public static final SeedsItem seedsTea = null;
	@GameRegistry.ObjectHolder("thorny_plant_seeds")
	public static final SeedsItem seedsThornyPlant = null;
	@GameRegistry.ObjectHolder("trilliad_seeds")
	public static final SeedsItem seedsTrilliad = null;

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> ev) {
		AdventOfAscension.logMessage(Level.INFO, "Beginning item registration");

		IForgeRegistry<Item> registry = ev.getRegistry();

		registerItem(registry, new AmmoItem("Balloon", "balloon"), "misc/misc/");
		registerItem(registry, new ItemTieredBullet("LimoniteBullet", "limonite_bullet"), "misc/ammo/");
		registerItem(registry, new AmmoItem("Cannonball", "cannonball"), "misc/ammo/");
		registerItem(registry, new Chilli(), "food/plants/");
		registerItem(registry, new AmmoItem("DischargeCapsule", "discharge_capsule"), "misc/ammo/");
		registerItem(registry, new HollyArrow("HollyArrow", "holly_arrow"), "misc/ammo/");
		registerItem(registry, new SpectralHollyArrow("SpectralHollyArrow", "spectral_holly_arrow"), "misc/ammo/");
		registerItem(registry, new TippedHollyArrow("TippedHollyArrow", "tipped_holly_arrow"), "misc/ammo/");
		registerItem(registry, new AmmoItem("MetalSlug", "metal_slug"), "misc/ammo/");
		registerItem(registry, new PopShot("PopShot", "pop_shot"), "misc/ammo/");
		registerItem(registry, new AmmoItem("Spreadshot", "spreadshot"), "misc/ammo/");

		registerItem(registry, new SimpleItem("Amethyst", "amethyst"), "misc/mineral/", "gemAmethyst");
		registerItem(registry, new SimpleItem("Bloodstone", "bloodstone"), "misc/mineral/", "gemBloodstone");
		registerItem(registry, new SimpleItem("Crystallite", "crystallite"), "misc/mineral/", "gemCrystallite");
		registerItem(registry, new SimpleItem("Gemenyte", "gemenyte"), "misc/mineral/", "gemGemenyte");
		registerItem(registry, new SimpleItem("Jade", "jade"), "misc/mineral/", "gemJade");
		registerItem(registry, new SimpleItem("Jewelyte", "jewelyte"), "misc/mineral/", "gemJewelyte");
		registerItem(registry, new SimpleItem("Ornamyte", "ornamyte"), "misc/mineral/", "gemOrnamyte");
		registerItem(registry, new SimpleItem("Sapphire", "sapphire"), "misc/mineral/", "gemSapphire");
		registerItem(registry, new SimpleItem("Shyregem", "shyregem"), "misc/mineral/", "gemShyregem");
		registerItem(registry, new SimpleItem("BaronyteIngot", "baronyte_ingot"), "misc/mineral/", "ingotBaronyte");
		registerItem(registry, new SimpleItem("BlaziumIngot", "blazium_ingot"), "misc/mineral/", "ingotBlazium");
		registerItem(registry, new SimpleItem("ElecaniumIngot", "elecanium_ingot"), "misc/mineral/", "ingotElecanium");
		registerItem(registry, new SimpleItem("EmberstoneIngot", "emberstone_ingot"), "misc/mineral/", "ingotEmberstone");
		registerItem(registry, new SimpleItem("GhastlyIngot", "ghastly_ingot"), "misc/mineral/", "ingotGhastly");
		registerItem(registry, new SimpleItem("GhoulishIngot", "ghoulish_ingot"), "misc/mineral/", "ingotGhoulish");
		registerItem(registry, new SimpleItem("LimoniteIngot", "limonite_ingot"), "misc/mineral/", "ingotLimonite");
		registerItem(registry, new SimpleItem("LunarIngot", "lunar_ingot"), "misc/mineral/", "ingotLunar");
		registerItem(registry, new SimpleItem("LyonIngot", "lyon_ingot"), "misc/mineral/", "ingotLyon");
		registerItem(registry, new SimpleItem("MystiteIngot", "mystite_ingot"), "misc/mineral/", "ingotMystite");
		registerItem(registry, new SimpleItem("RositeIngot", "rosite_ingot"), "misc/mineral/", "ingotRosite");
		registerItem(registry, new SimpleItem("RustedIronIngot", "rusted_iron_ingot"), "misc/mineral/");
		registerItem(registry, new SimpleItem("ShyrestoneIngot", "shyrestone_ingot"), "misc/mineral/", "ingotShyrestone");
		registerItem(registry, new SimpleItem("SkeletalIngot", "skeletal_ingot"), "misc/mineral/", "ingotSkeletal");
		registerItem(registry, new SimpleItem("VarsiumIngot", "varsium_ingot"), "misc/mineral/", "ingotVarsium");
		registerItem(registry, new SimpleItem("RuniumChunk", "runium_chunk"), "misc/misc/");
		registerItem(registry, new SimpleItem("ChestboneFragment", "chestbone_fragment"), "misc/misc/", "bone", "precasianBone");
		registerItem(registry, new SimpleItem("FootboneFragment", "footbone_fragment"), "misc/misc/", "bone", "precasianBone");
		registerItem(registry, new SimpleItem("LegboneFragment", "legbone_fragment"), "misc/misc/", "bone", "precasianBone");
		registerItem(registry, new SimpleItem("SkullboneFragment", "skullbone_fragment"), "misc/misc/", "bone", "precasianBone");
		registerItem(registry, new SimpleItem("ChargedRuniumChunk", "charged_runium_chunk"), "misc/misc/");
		registerItem(registry, new SimpleItem("BlueCrystal", "blue_crystal"), "misc/misc/");
		registerItem(registry, new SimpleItem("GreenCrystal", "green_crystal"), "misc/misc/");
		registerItem(registry, new SimpleItem("PurpleCrystal", "purple_crystal"), "misc/misc/");
		registerItem(registry, new SimpleItem("RedCrystal", "red_crystal"), "misc/misc/");
		registerItem(registry, new SimpleItem("WhiteCrystal", "white_crystal"), "misc/misc/");
		registerItem(registry, new SimpleItem("YellowCrystal", "yellow_crystal"), "misc/misc/");

		registerItem(registry, new RuneItem("ChargedRune", "charged_rune", false), "misc/ammo/");
		registerItem(registry, new RuneItem("CompassRune", "compass_rune", true, BlockRegister.runePostCompass), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("DistortionRune", "distortion_rune", true, BlockRegister.runePostDistortion), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("EnergyRune", "energy_rune", false, BlockRegister.runePostEnergy), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("FireRune", "fire_rune", false, BlockRegister.runePostFire), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("KineticRune", "kinetic_rune", true, BlockRegister.runePostKinetic), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("LifeRune", "life_rune", true, BlockRegister.runePostLife), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("LunarRune", "lunar_rune", true, BlockRegister.runePostLunar), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("PoisonRune", "poison_rune", false, BlockRegister.runePostPoison), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("PowerRune", "power_rune", false, BlockRegister.runePostPower), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("StormRune", "storm_rune", true, BlockRegister.runePostStorm), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("StrikeRune", "strike_rune", false, BlockRegister.runePostStrike), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("UnpoweredRune", "unpowered_rune", false), "misc/ammo/");
		registerItem(registry, new RuneItem("WaterRune", "water_rune", false, BlockRegister.runePostWater), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("WindRune", "wind_rune", false, BlockRegister.runePostWind), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("WitherRune", "wither_rune", false, BlockRegister.runePostWither), "misc/ammo/", "aoaRune");
		
		registerItem(registry, new SimpleItem("ActiveRuneStone", "active_rune_stone"), "misc/misc/");
		registerItem(registry, new ReservedItem("AlienOrb", "alien_orb", "alien_orb").withTooltip("item.AlienOrb.desc.1"), "misc/misc/");
		registerItem(registry, new AmphibiyteLung(), "misc/misc/");
		registerItem(registry, new SimpleItem("ApocoStone", "apoco_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("ArmourPlating", "armour_plating"), "misc/misc/");
		registerItem(registry, new SimpleItem("BlankSlab", "blank_slab"), "misc/misc/");
		registerItem(registry, new SimpleItem("Chitin", "chitin"), "misc/misc/");
		registerItem(registry, new SimpleItem("CircusCoin", "circus_coin"), "misc/misc/", "allCurrency");
		registerItem(registry, new CoinItem("CopperCoin", "copper_coin", 1), "misc/misc/", "allCurrency");
		registerItem(registry, new CoinItem("GoldCoin", "gold_coin", 400), "misc/misc/", "allCurrency");
		registerItem(registry, new CoinItem("LunaverCoin", "lunaver_coin", 8000), "misc/misc/", "allCurrency");
		registerItem(registry, new CoinItem("SilverCoin", "silver_coin", 20), "misc/misc/", "allCurrency");
		registerItem(registry, new SimpleItem("ConfettiPile", "confetti_pile"), "misc/misc/");
		registerItem(registry, new SimpleItem("CoralStone", "coral_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("CosmicDust", "cosmic_dust"), "misc/misc/");
		registerItem(registry, new SimpleItem("Cup", "cup"), "misc/misc/");
		registerItem(registry, new ReservedItem("DarkBones", "dark_bones", "alien_orb"), "misc/misc/");
		registerItem(registry, new SimpleItem("DarklyPowder", "darkly_powder"), "misc/misc/");
		registerItem(registry, new SimpleItem("DenseRock", "dense_rock"), "misc/misc/");
		registerItem(registry, new SimpleItem("DesertShell", "desert_shell"), "misc/misc/");
		registerItem(registry, new DistortingArtifact(), "misc/misc/");
		registerItem(registry, new SimpleItem("DoomStone", "doom_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("BlueDruse", "blue_druse"), "misc/misc/");
		registerItem(registry, new SimpleItem("GreenDruse", "green_druse"), "misc/misc/");
		registerItem(registry, new SimpleItem("PurpleDruse", "purple_druse"), "misc/misc/");
		registerItem(registry, new SimpleItem("RainbowDruse", "rainbow_druse"), "misc/misc/");
		registerItem(registry, new SimpleItem("RedDruse", "red_druse"), "misc/misc/");
		registerItem(registry, new SimpleItem("WhiteDruse", "white_druse"), "misc/misc/");
		registerItem(registry, new SimpleItem("YellowDruse", "yellow_druse"), "misc/misc/");
		registerItem(registry, new SimpleItem("EnchantedGunpowder", "enchanted_gunpowder"), "misc/misc/");
		registerItem(registry, new EyeBulb(), "misc/misc/");
		registerItem(registry, new SimpleItem("FlammableDust", "flammable_dust"), "misc/misc/");
		registerItem(registry, new ReservedItem("FleshyBones", "fleshy_bones", "alien_orb"), "misc/misc/");
		registerItem(registry, new FloatingStone(), "misc/misc/");
		registerItem(registry, new FragmentedAnimaStone(), "misc/misc/");
		registerItem(registry, new SimpleItem("BlueGemstones", "blue_gemstones"), "misc/misc/");
		registerItem(registry, new SimpleItem("GreenGemstones", "green_gemstones"), "misc/misc/");
		registerItem(registry, new SimpleItem("PurpleGemstones", "purple_gemstones"), "misc/misc/");
		registerItem(registry, new SimpleItem("RedGemstones", "red_gemstones"), "misc/misc/");
		registerItem(registry, new SimpleItem("WhiteGemstones", "white_gemstones"), "misc/misc/");
		registerItem(registry, new SimpleItem("YellowGemstones", "yellow_gemstones"), "misc/misc/");
		registerItem(registry, new SimpleItem("GhostlyPowder", "ghostly_powder"), "misc/misc/");
		registerItem(registry, new SimpleItem("GhostlyStone", "ghostly_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("Ghoulasm", "ghoulasm"), "misc/misc/");
		registerItem(registry, new Gravitator(), "misc/misc/");
		registerItem(registry, new SimpleItem("HardenedConfettiBall", "hardened_confetti_ball"), "misc/misc/");
		registerItem(registry, new HiveChunk(), "misc/misc/");
		registerItem(registry, new HollyTopPetals(), "misc/misc/");
		registerItem(registry, new SimpleItem("HydroStone", "hydro_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("IceCrystal", "ice_crystal"), "misc/misc/");
		registerItem(registry, new SimpleItem("ImpureGold", "impure_gold"), "misc/misc/");
		registerItem(registry, new SimpleItem("Ivory", "ivory"), "misc/misc/");
		registerItem(registry, new SimpleItem("JungleThorns", "jungle_thorns"), "misc/misc/");
		registerItem(registry, new SimpleItem("LimoniteRod", "limonite_rod"), "misc/misc/");
		registerItem(registry, new ItemLottoTotem(), "misc/misc/");
		registerItem(registry, new SimpleItem("LunaradeMug", "lunarade_mug"), "misc/misc/");
		registerItem(registry, new SimpleItem("MetalTub", "metal_tub"), "misc/misc/");
		registerItem(registry, new SimpleItem("MagicMendingCompound", "magic_mending_compound").withTooltip("item.MagicMendingCompound.desc.1"), "misc/misc/");
		registerItem(registry, new MagicMendingSolution(), "misc/misc/");
		registerItem(registry, new SimpleItem("MagicRepairDust", "magic_repair_dust").withTooltip("item.MagicRepairDust.desc.1"), "misc/misc/");
		registerItem(registry, new SimpleItem("MagnetShard", "magnet_shard"), "misc/misc/");
		registerItem(registry, new SimpleItem("MechaGear", "mecha_gear"), "misc/misc/");
		registerItem(registry, new SimpleItem("BlueMegaRuneFragment", "mega_rune_fragment_blue"), "misc/misc/");
		registerItem(registry, new SimpleItem("GreenMegaRuneFragment", "mega_rune_fragment_green"), "misc/misc/");
		registerItem(registry, new SimpleItem("RedMegaRuneFragment", "mega_rune_fragment_red"), "misc/misc/");
		registerItem(registry, new SimpleItem("YellowMegaRuneFragment", "mega_rune_fragment_yellow"), "misc/misc/");
		registerItem(registry, new ReservedItem("MilleniumUpgrader", "millenium_upgrader", "alien_orb"), "misc/misc/");
		registerItem(registry, new ReservedItem("MoltenUpgrader", "molten_upgrader", "alien_orb"), "misc/misc/");
		registerItem(registry, new ReservedItem("Moonstone", "moonstone", "alien_orb"), "misc/misc/");
		registerItem(registry, new SimpleItem("MudBall", "mud_ball"), "misc/misc/");
		registerItem(registry, new SimpleItem("MushroomSpores", "mushroom_spores"), "misc/misc/");
		registerItem(registry, new NightmareFlakes(), "misc/misc/");
		registerItem(registry, new SimpleItem("OldBoot", "old_boot").hidden(), "misc/misc/");
		registerItem(registry, new SimpleItem("OpteryxFeather", "opteryx_feather"), "misc/misc/");
		registerItem(registry, new SimpleItem("OrangeSpores", "orange_spores"), "misc/misc/");
		registerItem(registry, new SimpleItem("Orbulon", "orbulon"), "misc/misc/");
		registerItem(registry, new SimpleItem("PaddedCloth", "padded_cloth"), "misc/misc/");
		registerItem(registry, new SimpleItem("Phantasm", "phantasm"), "misc/misc/");
		registerItem(registry, new SimpleItem("PowerCore", "power_core"), "misc/misc/");
		registerItem(registry, new SimpleItem("PrimedGhoulasm", "primed_ghoulasm"), "misc/misc/");
		registerItem(registry, new SimpleItem("PrimordialSkull", "primordial_skull"), "misc/misc/");
		registerItem(registry, new SimpleItem("PureCoralStone", "pure_coral_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("PureGold", "pure_gold"), "misc/misc/");
		registerItem(registry, new SimpleItem("PureRainStone", "pure_rain_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("RammerheadHide", "rammerhead_hide"), "misc/misc/");
		registerItem(registry, new SimpleItem("RealmTravelTicket", "realm_travel_ticket").unstackable(), "misc/misc/");
		registerItem(registry, new SimpleItem("ReinforcedCloth", "reinforced_cloth"), "misc/misc/");
		registerItem(registry, new ReturnCrystal(), "misc/misc/");
		registerItem(registry, new ReservedItem("RockBones", "rock_bones", "alien_orb"), "misc/misc/");
		registerItem(registry, new SimpleItem("RosidRoot", "rosid_root"), "misc/misc/");
		registerItem(registry, new SimpleItem("RuneStone", "rune_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("RunicEnergy", "runic_energy"), "misc/misc/");
		registerItem(registry, new SimpleItem("ScrapMetal", "scrap_metal"), "misc/misc/");
		registerItem(registry, new SimpleItem("ScreamShield", "scream_shield"), "misc/misc/");
		registerItem(registry, new SimpleItem("SharpClaw", "sharp_claw"), "misc/misc/");
		registerItem(registry, new SimpleItem("SludgeParasite", "sludge_parasite"), "misc/misc/");
		registerItem(registry, new SimpleItem("SmallBluePetal", "small_blue_petal"), "misc/misc/");
		registerItem(registry, new SimpleItem("SmallGreenPetal", "small_green_petal"), "misc/misc/");
		registerItem(registry, new SimpleItem("SmallOrangePetal", "small_orange_petal"), "misc/misc/");
		registerItem(registry, new SimpleItem("SmallPurplePetal", "small_purple_petal"), "misc/misc/");
		registerItem(registry, new SimpleItem("SmallRedPetal", "small_red_petal"), "misc/misc/");
		registerItem(registry, new ReservedItem("Soulbone", "soulbone", "alien_orb"), "misc/misc/");
		registerItem(registry, new SimpleItem("StickySlime", "sticky_slime"), "misc/misc/");
		registerItem(registry, new SimpleItem("BlueStrangeStone", "blue_strange_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("WhiteStrangeStone", "white_strange_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("YellowStrangeStone", "yellow_strange_stone"), "misc/misc/");
		registerItem(registry, new TeaShreddings(), "misc/misc/");
		registerItem(registry, new ThornyPetals(), "misc/misc/");
		registerItem(registry, new SimpleItem("TornCloth", "torn_cloth"), "misc/misc/");
		registerItem(registry, new SimpleItem("ToxicLump", "toxic_lump"), "misc/misc/");
		registerItem(registry, new SimpleItem("TrollSkull", "troll_skull"), "misc/misc/");
		registerItem(registry, new SimpleItem("UnchargedOrb", "uncharged_orb"), "misc/misc/");
		registerItem(registry, new SimpleItem("UnchargedStone", "uncharged_stone"), "misc/misc/");
		registerItem(registry, new SimpleItem("UnstableGunpowder", "unstable_gunpowder"), "misc/misc/");
		registerItem(registry, new SimpleItem("UrkaHide", "urka_hide"), "misc/misc/");
		registerItem(registry, new SimpleItem("VoidScales", "void_scales"), "misc/misc/");
		registerItem(registry, new SimpleItem("VulcaneAugmentBattle", "vulcane_augment_battle"), "misc/misc/");
		registerItem(registry, new SimpleItem("VulcaneAugmentEquality", "vulcane_augment_equality"), "misc/misc/");
		registerItem(registry, new SimpleItem("VulcaneAugmentFire", "vulcane_augment_fire"), "misc/misc/");
		registerItem(registry, new SimpleItem("VulcaneAugmentImpairment", "vulcane_augment_impairment"), "misc/misc/");
		registerItem(registry, new SimpleItem("VulcaneAugmentPoison", "vulcane_augment_poison"), "misc/misc/");
		registerItem(registry, new SimpleItem("VulcaneAugmentPower", "vulcane_augment_power"), "misc/misc/");
		registerItem(registry, new SimpleItem("VulcaneAugmentWither", "vulcane_augment_wither"), "misc/misc/");
		registerItem(registry, new SimpleItem("WeaponParts", "weapon_parts"), "misc/misc/");
		registerItem(registry, new SimpleItem("WhitewashingSolution", "whitewashing_solution"), "misc/misc/");
		registerItem(registry, new WornBook(), "misc/misc/");
		registerItem(registry, new SimpleItem("YellowSpores", "yellow_spores"), "misc/misc/");
		registerItem(registry, new SimpleItem("ZhinxDust", "zhinx_dust"), "misc/misc/");

		registerItem(registry, new FrameItem("ArchergunFrame", "archergun_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("BlasterFrame", "blaster_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("BootsFrame", "boots_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("LeggingsFrame", "leggings_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("ChestplateFrame", "chestplate_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("HelmetFrame", "helmet_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("CannonFrame", "cannon_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("GunFrame", "gun_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("ShotgunFrame", "shotgun_frame"), "misc/frames/");
		registerItem(registry, new FrameItem("SniperFrame", "sniper_frame"), "misc/frames/");

		registerItem(registry, new SimpleItem("AncientOrb", "ancient_orb"), "misc/boss/");
		registerItem(registry, new SimpleItem("AncientRing", "ancient_ring"), "misc/boss/");
		registerItem(registry, new BoneHorn(), "misc/boss/");
		registerItem(registry, new SimpleItem("BookOfShadows", "book_of_shadows"), "misc/boss/");
		registerItem(registry, new SimpleItem("BoulderDash", "boulder_dash"), "misc/boss/");
		registerItem(registry, new SimpleItem("CallOfTheDrake", "call_of_the_drake"), "misc/boss/");
		registerItem(registry, new SimpleItem("ExplosiveGems", "explosive_gems"), "misc/boss/");
		registerItem(registry, new ExplosiveIdol(), "misc/boss/");
		registerItem(registry, new SimpleItem("GiantCrystal", "giant_crystal"), "misc/boss/");
		registerItem(registry, new SimpleItem("GoldSpring", "gold_spring"), "misc/boss/");
		registerItem(registry, new SimpleItem("GuardiansEye", "guardians_eye"), "misc/boss/");
		registerItem(registry, new HauntedIdol(), "misc/boss/");
		registerItem(registry, new HeavyBoulder(), "misc/boss/");
		registerItem(registry, new SimpleItem("HiveEgg", "hive_egg"), "misc/boss/");
		registerItem(registry, new SimpleItem("MegaRuneStone", "mega_rune_stone").damageable(5), "misc/boss/");
		registerItem(registry, new NethengeicCallstone(), "misc/boss/");
		registerItem(registry, new SimpleItem("ObservingEye", "observing_eye"), "misc/boss/");
		registerItem(registry, new SimpleItem("Petals", "petals"), "misc/boss/");
		registerItem(registry, new SimpleItem("PrimordialDust", "primordial_dust"), "misc/boss/");
		registerItem(registry, new SimpleItem("PureWaterStone", "pure_water_stone"), "misc/boss/");
		registerItem(registry, new ShroomStone(), "misc/boss/");
		registerItem(registry, new SimpleItem("SilvroCoin", "silvro_coin"), "misc/boss/");
		registerItem(registry, new SimpleItem("StaringEye", "staring_eye"), "misc/boss/");
		registerItem(registry, new ToyGyrocopter(), "misc/boss/");
		registerItem(registry, new TreatBag(), "misc/boss/");
		registerItem(registry, new TrollIdol(), "misc/boss/");
		registerItem(registry, new SimpleItem("VileStone", "vile_stone"), "misc/boss/");
		registerItem(registry, new SimpleItem("VoliantHeart", "voliant_heart"), "misc/boss/");
		registerItem(registry, new SimpleItem("WarlockGem", "warlock_gem"), "misc/boss/");
		
		registerItem(registry, new DimensionalTokensItem("AbyssTokens", "abyss_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("BaronTokens", "baron_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("BoreanTokens", "borean_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("CandylandTokens", "candyland_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("CeleveTokens", "celeve_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("CreeponiaTokens", "creeponia_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("CrysteviaTokens", "crystevia_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("DeeplandsTokens", "deeplands_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("DungeonTokens", "dungeon_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("DustopiaTokens", "dustopia_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("GardenciaTokens", "gardencia_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("GreckonTokens", "greckon_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("HavenTokens", "haven_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("IromineTokens", "iromine_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("LelyetiaTokens", "lelyetia_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("LunarTokens", "lunar_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("MysteriumTokens", "mysterium_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("NetherTokens", "nether_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("PrecasianTokens", "precasian_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("RunandorTokens", "runandor_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("ShyrelandsTokens", "shyrelands_tokens"), "misc/tokens/", "allCurrency");
		registerItem(registry, new DimensionalTokensItem("VoxPondsTokens", "vox_ponds_tokens"), "misc/tokens/", "allCurrency");

		registerItem(registry, new SimpleItem("AbyssalUpgradeKit", "abyssal_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("AncientUpgradeKit", "ancient_upgrade_kit"), "misc/upgradekit/"); // TODO Obtain Method
		registerItem(registry, new SimpleItem("ApocoUpgradeKit", "apoco_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("ClownUpgradeKit", "clown_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("DarklyUpgradeKit", "darkly_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("FloroUpgradeKit", "floro_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("GoldenUpgradeKit", "golden_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("HauntedUpgradeKit", "haunted_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("LelyetianUpgradeKit", "lelyetian_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("LightUpgradeKit", "light_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("LunarUpgradeKit", "lunar_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("NetherUpgradeKit", "nether_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("PrecasianUpgradeKit", "precasian_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("PredatorUpgradeKit", "predator_upgrade_kit"), "misc/upgradekit/"); // TODO Precasia event placement
		registerItem(registry, new SimpleItem("RockyUpgradeKit", "rocky_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("RunicUpgradeKit", "runic_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("SeasideUpgradeKit", "seaside_upgrade_kit"), "misc/upgradekit/");
		registerItem(registry, new SimpleItem("SmileyUpgradeKit", "smiley_upgrade_kit"), "misc/upgradekit/"); // TODO Obtain method
		
		registerItem(registry, new WaterloggedItem("WaterloggedAquaCannon", "waterlogged_aqua_cannon", 0), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedCoralArchergun", "waterlogged_coral_archergun", 1), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedCoralCannon", "waterlogged_coral_cannon", 2), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedCoralClogger", "waterlogged_coral_clogger", 3), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedReefer", "waterlogged_reefer", 4), "misc/misc/");

		registerItem(registry, new IncompleteMechaItem("IncompleteMechaArchergun", "incomplete_mecha_archergun", WeaponRegister.archergunMecha), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechaBow", "incomplete_mecha_bow", WeaponRegister.bowMecha), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechaCannon", "incomplete_mecha_cannon", WeaponRegister.cannonMechaCannon), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechanicalAssaultRifle", "incomplete_mechanical_assault_rifle", WeaponRegister.gunMechanicalAssaultRifle), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechaStaff", "incomplete_mecha_staff", WeaponRegister.staffMecha), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechyro", "incomplete_mechyro", WeaponRegister.shotgunMechyro), "misc/misc/");

		registerItem(registry, new BlankRealmstone(), "misc/realmstone/");
		registerItem(registry, new Realmstone("AbyssRealmstone", "abyss_realmstone", BlockRegister.portalAbyss, new ResourceLocation("aoa3", "abyss_portal_activate"), "abyss"), "misc/realmstone/");
		registerItem(registry, new Realmstone("NetherRealmstone", "nether_realmstone", BlockRegister.portalNether, new ResourceLocation("aoa3", "abyss_portal_activate"), "nether"), "misc/realmstone/");
		registerItem(registry, new Realmstone("AncientCavernRealmstone", "ancient_cavern_realmstone", BlockRegister.portalAncientCavern, new ResourceLocation("aoa3", "ancient_cavern_portal_activate"), "ancientCavern"), "misc/realmstone/");
		registerItem(registry, new Realmstone("BarathosRealmstone", "barathos_realmstone", BlockRegister.portalBarathos, new ResourceLocation("aoa3", "barren_portal_activate"), "barathos"), "misc/realmstone/");
		registerItem(registry, new Realmstone("BoreanRealmstone", "borean_realmstone", BlockRegister.portalBorean, new ResourceLocation("aoa3", "natural_portal_activate"), "lborean"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CandylandRealmstone", "candyland_realmstone", BlockRegister.portalCandyland, new ResourceLocation("aoa3", "candyland_portal_activate"), "candyland"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CeleveRealmstone", "celeve_realmstone", BlockRegister.portalCeleve, new ResourceLocation("aoa3", "celeve_portal_activate"), "celeve"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CreeponiaRealmstone", "creeponia_realmstone", BlockRegister.portalCreeponia, new ResourceLocation("aoa3", "creeponia_portal_activate"), "creeponia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CrysteviaRealmstone", "crystevia_realmstone", BlockRegister.portalCrystevia, new ResourceLocation("aoa3", "crystevia_portal_activate"), "crystevia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("DeeplandsRealmstone", "deeplands_realmstone", BlockRegister.portalDeeplands, new ResourceLocation("aoa3", "barren_portal_activate"), "deeplands"), "misc/realmstone/");
		registerItem(registry, new Realmstone("DustopiaRealmstone", "dustopia_realmstone", BlockRegister.portalDustopia, new ResourceLocation("aoa3", "dark_portal_activate"), "dustopia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("GardenciaRealmstone", "gardencia_realmstone", BlockRegister.portalGardencia, new ResourceLocation("aoa3", "natural_portal_activate"), "gardencia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("GreckonRealmstone", "greckon_realmstone", BlockRegister.portalGreckon, new ResourceLocation("aoa3", "dark_portal_activate"), "greckon"), "misc/realmstone/");
		registerItem(registry, new Realmstone("HavenRealmstone", "haven_realmstone", BlockRegister.portalHaven, new ResourceLocation("aoa3", "light_portal_activate"), "haven"), "misc/realmstone/");
		registerItem(registry, new Realmstone("ImmortallisRealmstone", "immortallis_realmstone", BlockRegister.portalImmortallis, new ResourceLocation("aoa3", "immortallis_portal_activate"), "immortallis"), "misc/realmstone/");
		registerItem(registry, new Realmstone("IromineRealmstone", "iromine_realmstone", BlockRegister.portalIromine, new ResourceLocation("aoa3", "iromine_portal_activate"), "iromine"), "misc/realmstone/");
		registerItem(registry, new Realmstone("LelyetiaRealmstone", "lelyetia_realmstone", BlockRegister.portalLelyetia, new ResourceLocation("aoa3", "natural_portal_activate"), "lelyetia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("LunalusRealmstone", "lunalus_realmstone", BlockRegister.portalLunalus, new ResourceLocation("aoa3", "natural_portal_activate"), "lunalus"), "misc/realmstone/");
		registerItem(registry, new Realmstone("MysteriumRealmstone", "mysterium_realmstone", BlockRegister.portalMysterium, new ResourceLocation("aoa3", "natural_portal_activate"), "mysterium"), "misc/realmstone/");
		registerItem(registry, new Realmstone("PrecasiaRealmstone", "precasia_realmstone", BlockRegister.portalPrecasia, new ResourceLocation("aoa3", "natural_portal_activate"), "precasia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("RunandorRealmstone", "runandor_realmstone", BlockRegister.portalRunandor, new ResourceLocation("aoa3", "light_portal_activate"), "runandor"), "misc/realmstone/");
		registerItem(registry, new Realmstone("ShyrelandsRealmstone", "shyrelands_realmstone", BlockRegister.portalShyrelands, new ResourceLocation("aoa3", "shyrelands_portal_activate"), "shyrelands"), "misc/realmstone/");
		registerItem(registry, new Realmstone("VoxPondsRealmstone", "vox_ponds_realmstone", BlockRegister.portalVoxPonds, new ResourceLocation("aoa3", "dark_portal_activate"), "voxPonds"), "misc/realmstone/");

		registerItem(registry, new AuguryEssence("AncientEssence", "ancient_essence", 58, 120f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("ChargedEssence", "charged_essence", 18, 10f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("DarkEssence", "dark_essence", 66, 250f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("DivineEssence", "divine_essence", 84, 600f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("EmpoweredEssence", "empowered_essence", 36, 15f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("EtherealEssence", "ethereal_essence", 75, 400f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("LuminateEssence", "luminate_essence", 45, 20f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("MoltenEssence", "molten_essence", 10, 7f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("OminousEssence", "ominous_essence", 27, 12f), "misc/essence/", "magicEssence");
		registerItem(registry, new AuguryEssence("WeakEssence", "weak_essence", 1, 4.0f), "misc/essence/", "magicEssence");

		registerItem(registry, new AgilityTablet("AgilityTablet", "agility_tablet", 15f, 0.168f, 17, 15), "misc/tablets/");
		registerItem(registry, new AwarenessTablet("AwarenessTablet", "awareness_tablet", 7.5f, 0.091f, 23, 20), "misc/tablets/");
		registerItem(registry, new BreedingTablet("BreedingTablet", "breeding_tablet", 75f, 0.51f, 29, 15), "misc/tablets/");
		registerItem(registry, new CleansingTablet("CleansingTablet", "cleansing_tablet", 30f, 0.16f, 16, 7), "misc/tablets/");
		registerItem(registry, new DistortionTablet("DistortionTablet", "distortion_tablet", 45f, 0.293f, 64, 10), "misc/tablets/");
		registerItem(registry, new EnergyTablet("EnergyTablet", "energy_tablet", 19.5f, 0.3216f, 37, 12), "misc/tablets/");
		registerItem(registry, new GravityTablet("GravityTablet", "gravity_tablet", 27f, 0.3217f, 11, 14), "misc/tablets/");
		registerItem(registry, new OxygenTablet("OxygenTablet", "oxygen_tablet", 7.5f, 0.090f, 6, 11), "misc/tablets/");
		registerItem(registry, new PressureTablet("PressureTablet", "pressure_tablet", 37.5f, 0.302f, 71, 10), "misc/tablets/");
		registerItem(registry, new ProficiencyTablet("ProficiencyTablet", "proficiency_tablet", 60f, 0.143f, 56, 14), "misc/tablets/");
		registerItem(registry, new ResistanceTablet("ResistanceTablet", "resistance_tablet", 33f, 0.307f, 45, 16), "misc/tablets/");
		registerItem(registry, new SanctityTablet("SanctityTablet", "sanctity_tablet", 67.5f, 0.268f, 77, 9), "misc/tablets/");
		registerItem(registry, new SatiationTablet("SatiationTablet", "satiation_tablet", 43.5f, 0.1525f, 3, 25), "misc/tablets/");
		registerItem(registry, new SightTablet("SightTablet", "sight_tablet", 52.5f, 0.079f, 1, 23), "misc/tablets/");
		registerItem(registry, new StrengthTablet("StrengthTablet", "strength_tablet", 47f, 0.297f, 85, 10), "misc/tablets/");
		registerItem(registry, new UntiringTablet("UntiringTablet", "untiring_tablet", 90f, 0.127f, 97, 20), "misc/tablets/");
		registerItem(registry, new VitalityTablet("VitalityTablet", "vitality_tablet", 54f, 0.283f, 91, 13), "misc/tablets/");
	
		registerItem(registry, new SimpleItem("ProgressCoin0", "progress_coin0"), "misc/misc/");
		registerItem(registry, new SimpleItem("ProgressCoin1", "progress_coin1"), "misc/misc/");
		registerItem(registry, new SimpleItem("ProgressCoin2", "progress_coin2"), "misc/misc/");
		registerItem(registry, new SimpleItem("ProgressCoin3", "progress_coin3"), "misc/misc/");
		registerItem(registry, new SimpleItem("ProgressCoin4", "progress_coin4"), "misc/misc/");

		registerItem(registry, new SimpleItem("AmbientPowerStone", "ambient_power_stone"), "misc/powerstone/");
		registerItem(registry, new SimpleItem("BloomingPowerStone", "blooming_power_stone"), "misc/powerstone/");
		registerItem(registry, new SimpleItem("GlaringPowerStone", "glaring_power_stone"), "misc/powerstone/");
		registerItem(registry, new SimpleItem("GleamingPowerStone", "gleaming_power_stone"), "misc/powerstone/");
		registerItem(registry, new SimpleItem("GlisteningPowerStone", "glistening_power_stone"), "misc/powerstone/");
		registerItem(registry, new SimpleItem("GlowingPowerStone", "glowing_power_stone"), "misc/powerstone/");
		registerItem(registry, new SimpleItem("RadiantPowerStone", "radiant_power_stone"), "misc/powerstone/");
		registerItem(registry, new SimpleItem("ShiningPowerStone", "shining_power_stone"), "misc/powerstone/");

		registerItem(registry, new InfusionStone("AmbientInfusionStone", "ambient_infusion_stone", 20, 20.0f, getUnmappedItem("ambient_power_stone")), "misc/infusionstone/", "infusionStone");
		registerItem(registry, new InfusionStone("BloomingInfusionStone", "blooming_infusion_stone", 80, 300.0f, getUnmappedItem("blooming_power_stone")), "misc/infusionstone/", "infusionStone");
		registerItem(registry, new InfusionStone("GlaringInfusionStone", "glaring_infusion_stone", 30, 40.0f, getUnmappedItem("glaring_power_stone")), "misc/infusionstone/", "infusionStone");
		registerItem(registry, new InfusionStone("GleamingInfusionStone", "gleaming_infusion_stone", 15, 16.0f, getUnmappedItem("gleaming_power_stone")), "misc/infusionstone/", "infusionStone");
		registerItem(registry, new InfusionStone("GlisteningInfusionStone", "glistening_infusion_stone", 5, 8.0f, getUnmappedItem("glistening_power_stone")), "misc/infusionstone/", "infusionStone");
		registerItem(registry, new InfusionStone("GlowingInfusionStone", "glowing_infusion_stone", 45, 85.0f, getUnmappedItem("glowing_power_stone")), "misc/infusionstone/", "infusionStone");
		registerItem(registry, new InfusionStone("RadiantInfusionStone", "radiant_infusion_stone", 70, 22.0f, getUnmappedItem("radiant_power_stone")), "misc/infusionstone/", "infusionStone");
		registerItem(registry, new InfusionStone("ShiningInfusionStone", "shining_infusion_stone", 60, 150.0f, getUnmappedItem("shining_power_stone")), "misc/infusionstone/", "infusionStone");

		registerItem(registry, new SkillCrystal("GiantSkillCrystal", "giant_skill_crystal", 4), "misc/misc/", "skillCrystal");
		registerItem(registry, new SkillCrystal("LargeSkillCrystal", "large_skill_crystal", 8), "misc/misc/", "skillCrystal");
		registerItem(registry, new SkillCrystal("MediumSkillCrystal", "medium_skill_crystal", 12), "misc/misc/", "skillCrystal");
		registerItem(registry, new SkillCrystal("SmallSkillCrystal", "small_skill_crystal", 15), "misc/misc/", "skillCrystal");

		registerItem(registry, new CrystalBox(), "misc/misc/");
		registerItem(registry, new FishCase(), "misc/misc/");
		registerItem(registry, new GemBag(), "misc/misc/");
		registerItem(registry, new RuneBox(), "misc/misc/");
		registerItem(registry, new ShinyBox(), "misc/misc/");
		registerItem(registry, new TreasureBox(), "misc/misc/");
		registerItem(registry, new WeaponsCase(), "misc/misc/");

		registerItem(registry, new BasicFood("RawCandlefish", "raw_candlefish", 1, 0.2f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawCrimsonSkipper", "raw_crimson_skipper", 1, 0.15f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawCrimsonStripefish", "raw_crimson_stripefish", 1, 0.1f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawDarkHatchetfish", "raw_dark_hatchetfish", 2, 0.2f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawFingerfish", "raw_fingerfish", 1, 0f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawGoldenGullfish", "raw_golden_gullfish", 2, 0.1f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawIronback", "raw_ironback", 3, 0.3f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawLimefish", "raw_limefish", 1, 0.15f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawPearlStripefish", "raw_pearl_stripefish", 1, 0.05f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawRainbowfish", "raw_rainbowfish", 3, 0.85f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawRazorfish", "raw_razorfish", 3, 0.3f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawRocketfish", "raw_rocketfish", 1, 0.15f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawSailback", "raw_sailback", 1, 0.1f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawSapphireStrider", "raw_sapphire_strider", 2, 0.2f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawTurquoiseStripefish", "raw_turquoise_stripefish", 1, 0.15f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new BasicFood("RawVioletSkipper", "raw_violet_skipper", 1, 0.2f), "food/fish/raw/", "listAllfishfresh", "listAllfishraw");
		registerItem(registry, new HealingFishFood("Candlefish", "candlefish", 6, 0.7f, 7.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("CrimsonSkipper", "crimson_skipper", 5, 0.6f, 6.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("CrimsonStripefish", "crimson_stripefish", 4, 0.5f, 5.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("DarkHatchetfish", "dark_hatchetfish", 7, 0.75f, 8.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("Fingerfish", "fingerfish", 1, 0.2f, 1.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("GoldenGullfish", "golden_gullfish", 2, 0.4f, 3.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("Ironback", "ironback", 8, 0.8f, 9.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("Limefish", "limefish", 1, 0.35f, 2.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("PearlStripefish", "pearl_stripefish", 1, 0.35f, 1.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("Rainbowfish", "rainbowfish", 9, 0.85f, 10.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("Razorfish", "razorfish", 9, 0.85f, 10.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("Rocketfish", "rocketfish", 4, 0.5f, 5.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("Sailback", "sailback", 2, 0.4f, 2.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("SapphireStrider", "sapphire_strider", 6, 0.65f, 6.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("TurquoiseStripefish", "turquoise_stripefish", 2, 0.4f, 4.0f), "food/fish/", "listAllfishcooked");
		registerItem(registry, new HealingFishFood("VioletSkipper", "violet_skipper", 3, 0.5f, 4.0f), "food/fish/", "listAllfishcooked");

		registerItem(registry, new BubbleBerries(), "food/plants/", "listAllfruit");
		registerItem(registry, new BasicFood("CandyCane", "candy_cane", 1, 0.1f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("CandyCorn", "candy_corn", 1, 0.22f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("RawChargerShank", "raw_charger_shank", 2, 0.2f, true), "food/meat/raw/", "listAllmeatraw");
		registerItem(registry, new BasicFood("ChargerShank", "charger_shank", 7, 0.3f, true), "food/meat/", "listAllmeatcooked");
		registerItem(registry, new BasicFood("RawChimeraChop", "raw_chimera_chop", 2, 0.2f, true), "food/meat/raw/", "listAllmeatraw");
		registerItem(registry, new BasicFood("ChimeraChop", "chimera_chop", 6, 0.6f, true), "food/meat/", "listAllmeatcooked");
		registerItem(registry, new EyeCandy(), "food/misc/", "foodCandy");
		registerItem(registry, new FieryChops(), "food/meat/", "listAllmeatcooked");
		registerItem(registry, new FloracleSticks(), "food/plants/");
		registerItem(registry, new FungalTea(), "food/drinks/");
		registerItem(registry, new BasicFood("RawFurlionChop", "raw_furlion_chop", 2, 0.2f, true), "food/meat/raw/", "listAllmeatraw");
		registerItem(registry, new BasicFood("FurlionChop", "furlion_chop", 6, 0.6f, true), "food/meat/", "listAllmeatcooked");
		registerItem(registry, new BasicFood("GingerbreadCookie", "gingerbread_cookie", 2, 0.25f), "food/misc/", "listAllcookie", "foodGingerbread");
		registerItem(registry, new BasicFood("GingerbreadWing", "gingerbread_wing", 2, 0.2f), "food/misc/", "listAllcookie", "foodGingerbread");
		registerItem(registry, new GoldicapPetals(), "food/plants/");
		registerItem(registry, new RawHalyconBeef(), "food/meat/raw/", "listAllbeefraw", "listAllmeatraw");
		registerItem(registry, new HalyconBeef(), "food/meat/", "listAllbeef", "listAllmeatcooked");
		registerItem(registry, new HalyconMilk(), "food/drinks/", "listAllmilk");
		registerItem(registry, new HeartFruit(), "food/plants/", "listAllfruit");
		registerItem(registry, new HotRod(), "food/meat/", "listAllmeatcooked");
		registerItem(registry, new Lunacrike(), "food/plants/");
		registerItem(registry, new LunaGlobe(), "food/plants/");
		registerItem(registry, new Lunalons(), "food/plants/");
		registerItem(registry, new Lunarade(), "food/drinks/", "listAllsoda");
		registerItem(registry, new MagicMarang(), "food/plants/");
		registerItem(registry, new MysticShrooms(), "food/plants/", "listAllmushroom");
		registerItem(registry, new NaturalTea(), "food/drinks/");
		registerItem(registry, new BasicFood("NatureMelonSlice", "nature_melon_slice", 1, 0.4f), "food/plants/", "listAllfruit");
		registerItem(registry, new BasicFood("PeppermintCandy", "peppermint_candy", 1, 0.2f), "food/misc/", "foodMints", "foodCandy");
		registerItem(registry, new Rosidons(), "food/plants/");
		registerItem(registry, new BasicFood("SourCandy", "sour_candy", 1, 0.15f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("SourGummy", "sour_gummy", 1, 0.3f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("SourPop", "sour_pop", 1, 0.18f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("SpearmintCandy", "spearmint_candy", 1, 0.25f), "food/misc/", "foodMints", "foodCandy");
		registerItem(registry, new Tea(), "food/drinks/");
		registerItem(registry, new TrilliadLeaves(), "food/plants/");
		registerItem(registry, new BasicFood("RawUrsaMeat", "raw_ursa_meat", 3, 0.3f, true), "food/meat/raw/", "listAllmeatraw");
		registerItem(registry, new BasicFood("UrsaMeat", "ursa_meat", 8, 0.8f, true), "food/meat/", "listAllmeatcooked");
		registerItem(registry, new YetiFingernails(), "food/misc/");

		registerItem(registry, new InfusionBowl("DiamondBowl", "diamond_bowl", 750, 5, 10), "tools/misc/");
		registerItem(registry, new InfusionBowl("StoneBowl", "stone_bowl", 100, 1, 0), "tools/misc/");
		registerItem(registry, new ExpFlask(), "tools/misc/");

		registerItem(registry, new AlluricornSlab(), "minionslabs/"); // TODO Slab recipes
		registerItem(registry, new BlissardSlab(), "minionslabs/");
		registerItem(registry, new CompeerSlab(), "minionslabs/");
		registerItem(registry, new ConstructOfServilitySlab(), "minionslabs/");
		registerItem(registry, new CorbySlab(), "minionslabs/");
		registerItem(registry, new CraggySlab(), "minionslabs/");
		registerItem(registry, new DraggySlab(), "minionslabs/");
		registerItem(registry, new EnderCarrierSlab(), "minionslabs/");
		registerItem(registry, new GnawerSlab(), "minionslabs/");
		registerItem(registry, new GooferSlab(), "minionslabs/");
		registerItem(registry, new HealingGolemSlab(), "minionslabs/");
		registerItem(registry, new HellquinSlab(), "minionslabs/");
		registerItem(registry, new HorntailSlab(), "minionslabs/");
		registerItem(registry, new MechaCyclopsSlab(), "minionslabs/");
		registerItem(registry, new MechaSkelloxSlab(), "minionslabs/");
		registerItem(registry, new PenguinSlab(), "minionslabs/");
		registerItem(registry, new PlateosaurSlab(), "minionslabs/");
		registerItem(registry, new RammerhornSlab(), "minionslabs/");
		registerItem(registry, new ShaddySlab(), "minionslabs/");
		registerItem(registry, new SpikebackSlab(), "minionslabs/");
		registerItem(registry, new SpraggySlab(), "minionslabs/");
		registerItem(registry, new WaggySlab(), "minionslabs/");

		registerItem(registry, new SeedsItem("BubbleBerrySeeds", "bubble_berry_seeds", BlockRegister.cropBubbleBerries, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("ChilliSeeds", "chilli_seeds", BlockRegister.cropChilli, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("FloracleSeeds", "floracle_seeds", BlockRegister.cropFloracles, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("GoldicapSeeds", "goldicap_seeds", BlockRegister.cropGoldicaps, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("HeartFruitSeeds", "heart_fruit_seeds", BlockRegister.cropHeartFruit, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("HollyTopSeeds", "holly_top_seeds", BlockRegister.cropHollyTops, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("LunacrikeSeeds", "lunacrike_seeds", BlockRegister.cropLunacrike, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("LunaGlobeSeeds", "luna_globe_seeds", BlockRegister.cropLunaGlobes, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("LunalonSeeds", "lunalon_seeds", BlockRegister.cropLunalons, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("MagicMarangSeeds", "magic_marang_seeds", BlockRegister.cropMagicMarang, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("RosidonSeeds", "rosidon_seeds", BlockRegister.cropRosidons, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("TeaSeeds", "tea_seeds", BlockRegister.cropTea, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("ThornyPlantSeeds", "thorny_plant_seeds", BlockRegister.cropThornyPlant, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("TrilliadSeeds", "trilliad_seeds", BlockRegister.cropTrilliads, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
	}

	@SubscribeEvent
	public static void remapMissing(final RegistryEvent.MissingMappings<Item> ev) {
		for (RegistryEvent.MissingMappings.Mapping<Item> map : ev.getAllMappings()) {
			if (map.key.equals(new ResourceLocation("aoa3:amethyst_ingot"))) {
				map.remap(gemAmethyst);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:jade_ingot"))) {
				map.remap(gemJade);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:shyregem_ingot"))) {
				map.remap(gemShyregem);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:sapphire_ingot"))) {
				map.remap(gemSapphire);
			}
		}
	}

	@SubscribeEvent
	public static void registerItemRenders(final ModelRegistryEvent ev) {
		itemRegistryList.forEach(wrapper -> registerRender(wrapper.item, wrapper.modelSubfolder));
	}

	public static void doInitTasks() {
		AdventOfAscension.logOptionalMessage("Performing miscellaneous post-init items tasks");

		itemRegistryList.forEach(wrapper -> {
			if (wrapper.oreDictEntries != null) {
				for (String entry : wrapper.oreDictEntries) {
					OreDictionary.registerOre(entry, wrapper.item);
				}
			}
		});

		itemRegistryList = null;

		WeaponRegister.registerDispensables();
	}

	@Nullable
	public static Item getUnmappedItem(String registryName) {
		if (itemRegistryList != null) {
			for (ItemRegistryWrapper wrapper : itemRegistryList) {
				if (wrapper.item.getRegistryName().getPath().equals(registryName))
					return wrapper.item;
			}
		}

		return null;
	}

	protected static void registerRender(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation("aoa3:" + location + item.getRegistryName().getPath()), "inventory"));
	}

	protected static void registerItem(IForgeRegistry<Item> registry, Item item, String modelSubfolder, String... oreDictEntries) {
		registry.register(item);

		itemRegistryList.add(new ItemRegistryWrapper(item, modelSubfolder, oreDictEntries));
	}

	protected static class ItemRegistryWrapper {
		private final String[] oreDictEntries;
		private final String modelSubfolder;
		private final Item item;

		protected ItemRegistryWrapper(Item item, String modelSubfolder, String... oreDictEntries) {
			this.item = item;
			this.modelSubfolder = modelSubfolder;
			this.oreDictEntries = oreDictEntries == null || oreDictEntries.length == 0 ? null : oreDictEntries;
		}
	}
}
