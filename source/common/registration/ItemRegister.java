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
import net.tslat.aoa3.item.record.RecordItem;
import net.tslat.aoa3.item.tablet.*;
import net.tslat.aoa3.item.tool.misc.ExpFlask;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

@SuppressWarnings({"ConstantConditions", "unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class ItemRegister {
	private static ArrayList<ItemRegistryWrapper> itemRegistryList = new ArrayList<ItemRegistryWrapper>(750);

	public static final AmmoItem BALLOON = ObjectHolder();
	public static final ItemTieredBullet LIMONITE_BULLET = ObjectHolder();
	public static final AmmoItem CANNONBALL = ObjectHolder();
	public static final Chilli CHILLI = ObjectHolder();
	public static final AmmoItem DISCHARGE_CAPSULE = ObjectHolder();
	public static final HollyArrow HOLLY_ARROW = ObjectHolder();
	public static final SpectralHollyArrow SPECTRAL_HOLLY_ARROW = ObjectHolder();
	public static final TippedHollyArrow TIPPED_HOLLY_ARROW = ObjectHolder();
	public static final AmmoItem METAL_SLUG = ObjectHolder();
	public static final PopShot POP_SHOT = ObjectHolder();
	public static final AmmoItem SPREADSHOT = ObjectHolder();

	public static final SimpleItem AMETHYST = ObjectHolder();
	public static final SimpleItem BLOODSTONE = ObjectHolder();
	public static final SimpleItem CRYSTALLITE = ObjectHolder();
	public static final SimpleItem GEMENYTE = ObjectHolder();
	public static final SimpleItem JADE = ObjectHolder();
	public static final SimpleItem JEWELYTE = ObjectHolder();
	public static final SimpleItem ORNAMYTE = ObjectHolder();
	public static final SimpleItem SAPPHIRE = ObjectHolder();
	public static final SimpleItem SHYREGEM = ObjectHolder();
	public static final SimpleItem BARONYTE_INGOT = ObjectHolder();
	public static final SimpleItem BLAZIUM_INGOT = ObjectHolder();
	public static final SimpleItem ELECANIUM_INGOT = ObjectHolder();
	public static final SimpleItem EMBERSTONE_INGOT = ObjectHolder();
	public static final SimpleItem GHASTLY_INGOT = ObjectHolder();
	public static final SimpleItem GHOULISH_INGOT = ObjectHolder();
	public static final SimpleItem LIMONITE_INGOT = ObjectHolder();
	public static final SimpleItem LUNAR_INGOT = ObjectHolder();
	public static final SimpleItem LYON_INGOT = ObjectHolder();
	public static final SimpleItem MYSTITE_INGOT = ObjectHolder();
	public static final SimpleItem ROSITE_INGOT = ObjectHolder();
	public static final SimpleItem RUSTED_IRON_INGOT = ObjectHolder();
	public static final SimpleItem SHYRESTONE_INGOT = ObjectHolder();
	public static final SimpleItem SKELETAL_INGOT = ObjectHolder();
	public static final SimpleItem VARSIUM_INGOT = ObjectHolder();
	public static final SimpleItem RUNIUM_CHUNK = ObjectHolder();

	public static final SimpleItem CHESTBONE_FRAGMENT = ObjectHolder();
	public static final SimpleItem FOOTBONE_FRAGMENT = ObjectHolder();
	public static final SimpleItem LEGBONE_FRAGMENT = ObjectHolder();
	public static final SimpleItem SKULLBONE_FRAGMENT = ObjectHolder();
	public static final SimpleItem CHARGED_RUNIUM_CHUNK = ObjectHolder();
	public static final SimpleItem BLUE_CRYSTAL = ObjectHolder();
	public static final SimpleItem GREEN_CRYSTAL = ObjectHolder();
	public static final SimpleItem PURPLE_CRYSTAL = ObjectHolder();
	public static final SimpleItem RED_CRYSTAL = ObjectHolder();
	public static final SimpleItem WHITE_CRYSTAL = ObjectHolder();
	public static final SimpleItem YELLOW_CRYSTAL = ObjectHolder();

	public static final RuneItem CHARGED_RUNE = ObjectHolder();
	public static final RuneItem COMPASS_RUNE = ObjectHolder();
	public static final RuneItem DISTORTION_RUNE = ObjectHolder();
	public static final RuneItem ENERGY_RUNE = ObjectHolder();
	public static final RuneItem FIRE_RUNE = ObjectHolder();
	public static final RuneItem KINETIC_RUNE = ObjectHolder();
	public static final RuneItem LIFE_RUNE = ObjectHolder();
	public static final RuneItem LUNAR_RUNE = ObjectHolder();
	public static final RuneItem POISON_RUNE = ObjectHolder();
	public static final RuneItem POWER_RUNE = ObjectHolder();
	public static final RuneItem STORM_RUNE = ObjectHolder();
	public static final RuneItem STRIKE_RUNE = ObjectHolder();
	public static final RuneItem UNPOWERED_RUNE = ObjectHolder();
	public static final RuneItem WATER_RUNE = ObjectHolder();
	public static final RuneItem WIND_RUNE = ObjectHolder();
	public static final RuneItem WITHER_RUNE = ObjectHolder();

	public static final SimpleItem ACTIVE_RUNE_STONE = ObjectHolder();
	public static final ReservedItem ALIEN_ORB = ObjectHolder();
	public static final AmphibiyteLung AMPHIBIYTE_LUNG = ObjectHolder();
	public static final SimpleItem APOCO_STONE = ObjectHolder();
	public static final SimpleItem ARMOUR_PLATING = ObjectHolder();
	public static final SimpleItem BLANK_SLAB = ObjectHolder();
	public static final SimpleItem CHITIN = ObjectHolder();
	public static final SimpleItem CIRCUS_COIN = ObjectHolder();
	public static final CoinItem COPPER_COIN = ObjectHolder();
	public static final CoinItem GOLD_COIN = ObjectHolder();
	public static final CoinItem LUNAVER_COIN = ObjectHolder();
	public static final CoinItem SILVER_COIN = ObjectHolder();
	public static final SimpleItem CONFETTI_PILE = ObjectHolder();
	public static final SimpleItem CORAL_STONE = ObjectHolder();
	public static final SimpleItem COSMIC_DUST = ObjectHolder();
	public static final SimpleItem CUP = ObjectHolder();
	public static final ReservedItem DARK_BONES = ObjectHolder();
	public static final SimpleItem DARKLY_POWDER = ObjectHolder();
	public static final SimpleItem DENSE_ROCK = ObjectHolder();
	public static final SimpleItem DESERT_SHELL = ObjectHolder();
	public static final DistortingArtifact DISTORTING_ARTIFACT = ObjectHolder();
	public static final SimpleItem DOOM_STONE = ObjectHolder();
	public static final SimpleItem BLUE_DRUSE = ObjectHolder();
	public static final SimpleItem GREEN_DRUSE = ObjectHolder();
	public static final SimpleItem PURPLE_DRUSE = ObjectHolder();
	public static final SimpleItem RAINBOW_DRUSE = ObjectHolder();
	public static final SimpleItem RED_DRUSE = ObjectHolder();
	public static final SimpleItem WHITE_DRUSE = ObjectHolder();
	public static final SimpleItem YELLOW_DRUSE = ObjectHolder();
	public static final SimpleItem ENCHANTED_GUNPOWDER = ObjectHolder();
	public static final EyeBulb EYE_BULB = ObjectHolder();
	public static final SimpleItem FLAMMABLE_DUST = ObjectHolder();
	public static final ReservedItem FLESHY_BONES = ObjectHolder();
	public static final FloatingStone FLOATING_STONE = ObjectHolder();
	public static final FragmentedAnimaStone FRAGMENTED_ANIMA_STONE = ObjectHolder();
	public static final SimpleItem BLUE_GEMSTONES = ObjectHolder();
	public static final SimpleItem GREEN_GEMSTONES = ObjectHolder();
	public static final SimpleItem PURPLE_GEMSTONES = ObjectHolder();
	public static final SimpleItem RED_GEMSTONES = ObjectHolder();
	public static final SimpleItem WHITE_GEMSTONES = ObjectHolder();
	public static final SimpleItem YELLOW_GEMSTONES = ObjectHolder();
	public static final SimpleItem GHOSTLY_POWDER = ObjectHolder();
	public static final SimpleItem GHOSTLY_STONE = ObjectHolder();
	public static final SimpleItem GHOULASM = ObjectHolder();
	public static final Gravitator GRAVITATOR = ObjectHolder();
	public static final SimpleItem HARDENED_CONFETTI_BALL = ObjectHolder();
	public static final HiveChunk HIVE_CHUNK = ObjectHolder();
	public static final HollyTopPetals HOLLY_TOP_PETALS = ObjectHolder();
	public static final SimpleItem HYDRO_STONE = ObjectHolder();
	public static final SimpleItem ICE_CRYSTAL = ObjectHolder();
	public static final SimpleItem IMPURE_GOLD = ObjectHolder();
	public static final SimpleItem IVORY = ObjectHolder();
	public static final SimpleItem JUNGLE_THORNS = ObjectHolder();
	public static final SimpleItem LIMONITE_ROD = ObjectHolder();
	public static final ItemLottoTotem LOTTO_TOTEM = ObjectHolder();
	public static final SimpleItem LUNARADE_MUG = ObjectHolder();
	public static final SimpleItem METAL_TUB = ObjectHolder();
	public static final SimpleItem MAGIC_MENDING_COMPOUND = ObjectHolder();
	public static final MagicMendingSolution MAGIC_MENDING_SOLUTION = ObjectHolder();
	public static final SimpleItem MAGIC_REPAIR_DUST = ObjectHolder();
	public static final SimpleItem MAGNET_SHARD = ObjectHolder();
	public static final SimpleItem MECHA_GEAR = ObjectHolder();
	public static final SimpleItem MEGA_RUNE_FRAGMENT_BLUE = ObjectHolder();
	public static final SimpleItem MEGA_RUNE_FRAGMENT_GREEN = ObjectHolder();
	public static final SimpleItem MEGA_RUNE_FRAGMENT_RED = ObjectHolder();
	public static final SimpleItem MEGA_RUNE_FRAGMENT_YELLOW = ObjectHolder();
	public static final ReservedItem MILLENNIUM_UPGRADER = ObjectHolder();
	public static final ReservedItem MOLTEN_UPGRADER = ObjectHolder();
	public static final ReservedItem MOONSTONE = ObjectHolder();
	public static final SimpleItem MUD_BALL = ObjectHolder();
	public static final SimpleItem MUSHROOM_SPORES = ObjectHolder();
	public static final SimpleItem NIGHTMARE_FLAKES = ObjectHolder();
	public static final SimpleItem OLD_BOOT = ObjectHolder();
	public static final SimpleItem OPTERYX_FEATHER = ObjectHolder();
	public static final SimpleItem ORANGE_SPORES = ObjectHolder();
	public static final SimpleItem ORBULON = ObjectHolder();
	public static final SimpleItem PADDED_CLOTH = ObjectHolder();
	public static final SimpleItem PHANTASM = ObjectHolder();
	public static final SimpleItem POWER_CORE = ObjectHolder();
	public static final SimpleItem PRIMED_GHOULASM = ObjectHolder();
	public static final SimpleItem PRIMORDIAL_SKULL = ObjectHolder();
	public static final SimpleItem PURE_CORAL_STONE = ObjectHolder();
	public static final SimpleItem PURE_GOLD = ObjectHolder();
	public static final SimpleItem PURE_RAIN_STONE = ObjectHolder();
	public static final SimpleItem RAMMERHEAD_HIDE = ObjectHolder();
	public static final SimpleItem REALM_TRAVEL_TICKET = ObjectHolder();
	public static final SimpleItem REINFORCED_CLOTH = ObjectHolder();
	public static final ReturnCrystal RETURN_CRYSTAL = ObjectHolder();
	public static final ReservedItem ROCK_BONES = ObjectHolder();
	public static final SimpleItem ROSID_ROOT = ObjectHolder();
	public static final SimpleItem RUNE_STONE = ObjectHolder();
	public static final SimpleItem RUNIC_ENERGY = ObjectHolder();
	public static final SimpleItem SCRAP_METAL = ObjectHolder();
	public static final SimpleItem SCREAM_SHIELD = ObjectHolder();
	public static final SimpleItem SHARP_CLAW = ObjectHolder();
	public static final SimpleItem SLUDGE_PARASITE = ObjectHolder();
	public static final SimpleItem SMALL_BLUE_PETAL = ObjectHolder();
	public static final SimpleItem SMALL_GREEN_PETAL = ObjectHolder();
	public static final SimpleItem SMALL_ORANGE_PETAL = ObjectHolder();
	public static final SimpleItem SMALL_PURPLE_PETAL = ObjectHolder();
	public static final SimpleItem SMALL_RED_PETAL = ObjectHolder();
	public static final ReservedItem SOULBONE = ObjectHolder();
	public static final SimpleItem STICKY_SLIME = ObjectHolder();
	public static final SimpleItem BLUE_STRANGE_STONE = ObjectHolder();
	public static final SimpleItem WHITE_STRANGE_STONE = ObjectHolder();
	public static final SimpleItem YELLOW_STRANGE_STONE = ObjectHolder();
	public static final TeaShreddings TEA_SHREDDINGS = ObjectHolder();
	public static final ThornyPetals THORNY_PETALS = ObjectHolder();
	public static final SimpleItem TORN_CLOTH = ObjectHolder();
	public static final SimpleItem TOXIC_LUMP = ObjectHolder();
	public static final SimpleItem TROLL_SKULL = ObjectHolder();
	public static final SimpleItem UNCHARGED_ORB = ObjectHolder();
	public static final SimpleItem UNCHARGED_STONE = ObjectHolder();
	public static final SimpleItem UNSTABLE_GUNPOWDER = ObjectHolder();
	public static final SimpleItem URKA_HIDE = ObjectHolder();
	public static final SimpleItem VOID_SCALES = ObjectHolder();
	public static final SimpleItem VULCANE_AUGMENT_BATTLE = ObjectHolder();
	public static final SimpleItem VULCANE_AUGMENT_EQUALITY = ObjectHolder();
	public static final SimpleItem VULCANE_AUGMENT_FIRE = ObjectHolder();
	public static final SimpleItem VULCANE_AUGMENT_IMPAIRMENT = ObjectHolder();
	public static final SimpleItem VULCANE_AUGMENT_POISON = ObjectHolder();
	public static final SimpleItem VULCANE_AUGMENT_POWER = ObjectHolder();
	public static final SimpleItem VULCANE_AUGMENT_WITHER = ObjectHolder();
	public static final SimpleItem WEAPON_PARTS = ObjectHolder();
	public static final SimpleItem WHITEWASHING_SOLUTION = ObjectHolder();
	public static final WornBook WORN_BOOK = ObjectHolder();
	public static final SimpleItem YELLOW_SPORES = ObjectHolder();
	public static final SimpleItem ZHINX_DUST = ObjectHolder();

	public static final FrameItem ARCHERGUN_FRAME = ObjectHolder();
	public static final FrameItem BLASTER_FRAME = ObjectHolder();
	public static final FrameItem BOOTS_FRAME = ObjectHolder();
	public static final FrameItem LEGGINGS_FRAME = ObjectHolder();
	public static final FrameItem CHESTPLATE_FRAME = ObjectHolder();
	public static final FrameItem HELMET_FRAME = ObjectHolder();
	public static final FrameItem CANNON_FRAME = ObjectHolder();
	public static final FrameItem GUN_FRAME = ObjectHolder();
	public static final FrameItem SHOTGUN_FRAME = ObjectHolder();
	public static final FrameItem SNIPER_FRAME = ObjectHolder();

	public static final SimpleItem ANCIENT_ORB = ObjectHolder();
	public static final SimpleItem ANCIENT_RING = ObjectHolder();
	public static final BoneHorn BONE_HORN = ObjectHolder();
	public static final SimpleItem BOOK_OF_SHADOWS = ObjectHolder();
	public static final SimpleItem BOULDER_DASH = ObjectHolder();
	public static final SimpleItem CALL_OF_THE_DRAKE = ObjectHolder();
	public static final SimpleItem EXPLOSIVE_GEMS = ObjectHolder();
	public static final BossSpawningItem EXPLOSIVE_IDOL = ObjectHolder();
	public static final SimpleItem GIANT_CRYSTAL = ObjectHolder();
	public static final SimpleItem GOLD_SPRING = ObjectHolder();
	public static final SimpleItem GUARDIANS_EYE = ObjectHolder();
	public static final BossSpawningItem HAUNTED_IDOL = ObjectHolder();
	public static final SimpleItem HEAVY_BOULDER = ObjectHolder();
	public static final SimpleItem HIVE_EGG = ObjectHolder();
	public static final SimpleItem MEGA_RUNE_STONE = ObjectHolder();
	public static final BossSpawningItem NETHENGEIC_CALLSTONE = ObjectHolder();
	public static final SimpleItem OBSERVING_EYE = ObjectHolder();
	public static final SimpleItem PETALS = ObjectHolder();
	public static final SimpleItem PRIMORDIAL_DUST = ObjectHolder();
	public static final SimpleItem PURE_WATER_STONE = ObjectHolder();
	public static final BossSpawningItem SHROOM_STONE = ObjectHolder();
	public static final SimpleItem SILVRO_COIN = ObjectHolder();
	public static final SimpleItem STARING_EYE = ObjectHolder();
	public static final ToyGyrocopter TOY_GYROCOPTER = ObjectHolder();
	public static final TreatBag TREAT_BAG = ObjectHolder();
	public static final BossSpawningItem TROLL_IDOL = ObjectHolder();
	public static final SimpleItem VILE_STONE = ObjectHolder();
	public static final SimpleItem VOLIANT_HEART = ObjectHolder();
	public static final SimpleItem WARLOCK_GEM = ObjectHolder();

	public static final DimensionalTokensItem ABYSS_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem BARON_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem BOREAN_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem CANDYLAND_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem CELEVE_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem CREEPONIA_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem CRYSTEVIA_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem DEEPLANDS_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem DUNGEON_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem DUSTOPIA_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem GARDENCIA_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem GRECKON_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem HAVEN_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem IROMINE_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem LELYETIA_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem LUNAR_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem MYSTERIUM_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem NETHER_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem PRECASIAN_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem RUNANDOR_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem SHYRELANDS_TOKENS = ObjectHolder();
	public static final DimensionalTokensItem VOX_PONDS_TOKENS = ObjectHolder();

	public static final SimpleItem ABYSSAL_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem ANCIENT_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem APOCO_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem CLOWN_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem DARKLY_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem FLORO_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem GOLDEN_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem HAUNTED_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem LELYETIAN_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem LIGHT_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem LUNAR_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem NETHER_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem PRECASIAN_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem PREDATOR_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem ROCKY_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem RUNIC_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem SEASIDE_UPGRADE_KIT = ObjectHolder();
	public static final SimpleItem SMILEY_UPGRADE_KIT = ObjectHolder();

	public static final WaterloggedItem WATERLOGGED_AQUA_CANNON = ObjectHolder();
	public static final WaterloggedItem WATERLOGGED_CORAL_ARCHERGUN = ObjectHolder();
	public static final WaterloggedItem WATERLOGGED_CORAL_CANNON = ObjectHolder();
	public static final WaterloggedItem WATERLOGGED_CORAL_CLOGGER = ObjectHolder();
	public static final WaterloggedItem WATERLOGGED_REEFER = ObjectHolder();

	public static final IncompleteMechaItem INCOMPLETE_MECHA_ARCHERGUN = ObjectHolder();
	public static final IncompleteMechaItem INCOMPLETE_MECHA_BOW = ObjectHolder();
	public static final IncompleteMechaItem INCOMPLETE_MECHA_CANNON = ObjectHolder();
	public static final IncompleteMechaItem INCOMPLETE_MECHANICAL_ASSAULT_RIFLE = ObjectHolder();
	public static final IncompleteMechaItem INCOMPLETE_MECHA_STAFF = ObjectHolder();
	public static final IncompleteMechaItem INCOMPLETE_MECHYRO = ObjectHolder();

	public static final BlankRealmstone BLANK_REALMSTONE = ObjectHolder();
	public static final Realmstone ABYSS_REALMSTONE = ObjectHolder();
	public static final Realmstone NETHER_REALMSTONE = ObjectHolder();
	public static final Realmstone ANCIENT_CAVERN_REALMSTONE = ObjectHolder();
	public static final Realmstone BARATHOS_REALMSTONE = ObjectHolder();
	public static final Realmstone BOREAN_REALMSTONE = ObjectHolder();
	public static final Realmstone CANDYLAND_REALMSTONE = ObjectHolder();
	public static final Realmstone CELEVE_REALMSTONE = ObjectHolder();
	public static final Realmstone CREEPONIA_REALMSTONE = ObjectHolder();
	public static final Realmstone CRYSTEVIA_REALMSTONE = ObjectHolder();
	public static final Realmstone DEEPLANDS_REALMSTONE = ObjectHolder();
	public static final Realmstone DUSTOPIA_REALMSTONE = ObjectHolder();
	public static final Realmstone GARDENCIA_REALMSTONE = ObjectHolder();
	public static final Realmstone GRECKON_REALMSTONE = ObjectHolder();
	public static final Realmstone HAVEN_REALMSTONE = ObjectHolder();
	public static final Realmstone IMMORTALLIS_REALMSTONE = ObjectHolder();
	public static final Realmstone IROMINE_REALMSTONE = ObjectHolder();
	public static final Realmstone LELYETIA_REALMSTONE = ObjectHolder();
	public static final Realmstone LUNALUS_REALMSTONE = ObjectHolder();
	public static final Realmstone MYSTERIUM_REALMSTONE = ObjectHolder();
	public static final Realmstone PRECASIA_REALMSTONE = ObjectHolder();
	public static final Realmstone RUNANDOR_REALMSTONE = ObjectHolder();
	public static final Realmstone SHYRELANDS_REALMSTONE = ObjectHolder();
	public static final Realmstone VOX_PONDS_REALMSTONE = ObjectHolder();

	public static final AuguryEssence ANCIENT_ESSENCE = ObjectHolder();
	public static final AuguryEssence CHARGED_ESSENCE = ObjectHolder();
	public static final AuguryEssence DARK_ESSENCE = ObjectHolder();
	public static final AuguryEssence DIVINE_ESSENCE = ObjectHolder();
	public static final AuguryEssence EMPOWERED_ESSENCE = ObjectHolder();
	public static final AuguryEssence ETHEREAL_ESSENCE = ObjectHolder();
	public static final AuguryEssence LUMINATE_ESSENCE = ObjectHolder();
	public static final AuguryEssence MOLTEN_ESSENCE = ObjectHolder();
	public static final AuguryEssence OMINOUS_ESSENCE = ObjectHolder();
	public static final AuguryEssence WEAK_ESSENCE = ObjectHolder();

	public static final TabletItem AGILITY_TABLET = ObjectHolder();
	public static final TabletItem AWARENESS_TABLET = ObjectHolder();
	public static final TabletItem BREEDING_TABLET = ObjectHolder();
	public static final TabletItem CLEANSING_TABLET = ObjectHolder();
	public static final TabletItem DISTORTION_TABLET = ObjectHolder();
	public static final TabletItem ENERGY_TABLET = ObjectHolder();
	public static final TabletItem GRAVITY_TABLET = ObjectHolder();
	public static final TabletItem OXYGEN_TABLET = ObjectHolder();
	public static final TabletItem PRESSURE_TABLET = ObjectHolder();
	public static final TabletItem PROFICIENCY_TABLET = ObjectHolder();
	public static final TabletItem RESISTANCE_TABLET = ObjectHolder();
	public static final TabletItem SANCTITY_TABLET = ObjectHolder();
	public static final TabletItem SATIATION_TABLET = ObjectHolder();
	public static final TabletItem SIGHT_TABLET = ObjectHolder();
	public static final TabletItem STRENGTH_TABLET = ObjectHolder();
	public static final TabletItem UNTIRING_TABLET = ObjectHolder();
	public static final TabletItem VITALITY_TABLET = ObjectHolder();

	public static final SimpleItem PROGRESS_COIN0 = ObjectHolder();
	public static final SimpleItem PROGRESS_COIN1 = ObjectHolder();
	public static final SimpleItem PROGRESS_COIN2 = ObjectHolder();
	public static final SimpleItem PROGRESS_COIN3 = ObjectHolder();
	public static final SimpleItem PROGRESS_COIN4 = ObjectHolder();

	public static final SimpleItem AMBIENT_POWER_STONE = ObjectHolder();
	public static final SimpleItem BLOOMING_POWER_STONE = ObjectHolder();
	public static final SimpleItem GLARING_POWER_STONE = ObjectHolder();
	public static final SimpleItem GLEAMING_POWER_STONE = ObjectHolder();
	public static final SimpleItem GLISTENING_POWER_STONE = ObjectHolder();
	public static final SimpleItem GLOWING_POWER_STONE = ObjectHolder();
	public static final SimpleItem RADIANT_POWER_STONE = ObjectHolder();
	public static final SimpleItem SHINING_POWER_STONE = ObjectHolder();

	public static final InfusionStone AMBIENT_INFUSION_STONE = ObjectHolder();
	public static final InfusionStone BLOOMING_INFUSION_STONE = ObjectHolder();
	public static final InfusionStone GLARING_INFUSION_STONE = ObjectHolder();
	public static final InfusionStone GLEAMING_INFUSION_STONE = ObjectHolder();
	public static final InfusionStone GLISTENING_INFUSION_STONE = ObjectHolder();
	public static final InfusionStone GLOWING_INFUSION_STONE = ObjectHolder();
	public static final InfusionStone RADIANT_INFUSION_STONE = ObjectHolder();
	public static final InfusionStone SHINING_INFUSION_STONE = ObjectHolder();

	public static final SkillCrystal GIANT_SKILL_CRYSTAL = ObjectHolder();
	public static final SkillCrystal LARGE_SKILL_CRYSTAL = ObjectHolder();
	public static final SkillCrystal MEDIUM_SKILL_CRYSTAL = ObjectHolder();
	public static final SkillCrystal SMALL_SKILL_CRYSTAL = ObjectHolder();

	public static final CrystalBox CRYSTAL_BOX = ObjectHolder();
	public static final FishCase FISH_CASE = ObjectHolder();
	public static final GemBag GEM_BAG = ObjectHolder();
	public static final RuneBox RUNE_BOX = ObjectHolder();
	public static final ShinyBox SHINY_BOX = ObjectHolder();
	public static final TreasureBox TREASURE_BOX = ObjectHolder();
	public static final WeaponsCase WEAPONS_CASE = ObjectHolder();

	public static final BasicFood RAW_CANDLEFISH = ObjectHolder();
	public static final BasicFood RAW_CRIMSON_SKIPPER = ObjectHolder();
	public static final BasicFood RAW_CRIMSON_STRIPEFISH = ObjectHolder();
	public static final BasicFood RAW_DARK_HATCHETFISH = ObjectHolder();
	public static final BasicFood RAW_FINGERFISH = ObjectHolder();
	public static final BasicFood RAW_GOLDEN_GULLFISH = ObjectHolder();
	public static final BasicFood RAW_IRONBACK = ObjectHolder();
	public static final BasicFood RAW_LIMEFISH = ObjectHolder();
	public static final BasicFood RAW_PEARL_STRIPEFISH = ObjectHolder();
	public static final BasicFood RAW_RAINBOWFISH = ObjectHolder();
	public static final BasicFood RAW_RAZORFISH = ObjectHolder();
	public static final BasicFood RAW_ROCKETFISH = ObjectHolder();
	public static final BasicFood RAW_SAILBACK = ObjectHolder();
	public static final BasicFood RAW_SAPPHIRE_STRIDER = ObjectHolder();
	public static final BasicFood RAW_TURQUOISE_STRIPEFISH = ObjectHolder();
	public static final BasicFood RAW_VIOLET_SKIPPER = ObjectHolder();
	public static final HealingFishFood CANDLEFISH = ObjectHolder();
	public static final HealingFishFood CRIMSON_SKIPPER = ObjectHolder();
	public static final HealingFishFood CRIMSON_STRIPEFISH = ObjectHolder();
	public static final HealingFishFood DARK_HATCHETFISH = ObjectHolder();
	public static final HealingFishFood FINGERFISH = ObjectHolder();
	public static final HealingFishFood GOLDEN_GULLFISH = ObjectHolder();
	public static final HealingFishFood IRONBACK = ObjectHolder();
	public static final HealingFishFood LIMEFISH = ObjectHolder();
	public static final HealingFishFood PEARL_STRIPEFISH = ObjectHolder();
	public static final HealingFishFood RAINBOWFISH = ObjectHolder();
	public static final HealingFishFood RAZORFISH = ObjectHolder();
	public static final HealingFishFood ROCKETFISH = ObjectHolder();
	public static final HealingFishFood SAILBACK = ObjectHolder();
	public static final HealingFishFood SAPPHIRE_STRIDER = ObjectHolder();
	public static final HealingFishFood TURQUOISE_STRIPEFISH = ObjectHolder();
	public static final HealingFishFood VIOLET_SKIPPER = ObjectHolder();

	public static final BubbleBerries BUBBLE_BERRIES = ObjectHolder();
	public static final BasicFood CANDY_CANE = ObjectHolder();
	public static final BasicFood CANDY_CORN = ObjectHolder();
	public static final BasicFood RAW_CHARGER_SHANK = ObjectHolder();
	public static final BasicFood CHARGER_SHANK = ObjectHolder();
	public static final BasicFood RAW_CHIMERA_CHOP = ObjectHolder();
	public static final BasicFood CHIMERA_CHOP = ObjectHolder();
	public static final EyeCandy EYE_CANDY = ObjectHolder();
	public static final FieryChops FIERY_CHOPS = ObjectHolder();
	public static final FloracleSticks FLORACLE_STICKS = ObjectHolder();
	public static final FungalTea FUNGAL_TEA = ObjectHolder();
	public static final BasicFood RAW_FURLION_CHOP = ObjectHolder();
	public static final BasicFood FURLION_CHOP = ObjectHolder();
	public static final BasicFood GINGERBREAD_COOKIE = ObjectHolder();
	public static final BasicFood GINGERBREAD_WING = ObjectHolder();
	public static final GoldicapPetals GOLDICAP_PETALS = ObjectHolder();
	public static final RawHalyconBeef RAW_HALYCON_BEEF = ObjectHolder();
	public static final HalyconBeef HALYCON_BEEF = ObjectHolder();
	public static final HalyconMilk HALYCON_MILK = ObjectHolder();
	public static final HeartFruit HEART_FRUIT = ObjectHolder();
	public static final HotRod HOT_ROD = ObjectHolder();
	public static final Lunacrike LUNACRIKE = ObjectHolder();
	public static final LunaGlobe LUNA_GLOBE = ObjectHolder();
	public static final Lunalons LUNALONS = ObjectHolder();
	public static final Lunarade LUNARADE = ObjectHolder();
	public static final MagicMarang MAGIC_MARANG = ObjectHolder();
	public static final MysticShrooms MYSTIC_SHROOMS = ObjectHolder();
	public static final NaturalTea NATURAL_TEA = ObjectHolder();
	public static final BasicFood NATURE_MELON_SLICE = ObjectHolder();
	public static final BasicFood PEPPERMINT_CANDY = ObjectHolder();
	public static final Rosidons ROSIDONS = ObjectHolder();
	public static final BasicFood SOUR_CANDY = ObjectHolder();
	public static final BasicFood SOUR_GUMMY = ObjectHolder();
	public static final BasicFood SOUR_POP = ObjectHolder();
	public static final BasicFood SPEARMINT_CANDY = ObjectHolder();
	public static final Tea TEA = ObjectHolder();
	public static final TrilliadLeaves TRILLIAD_LEAVES = ObjectHolder();
	public static final BasicFood RAW_URSA_MEAT = ObjectHolder();
	public static final BasicFood URSA_MEAT = ObjectHolder();
	public static final YetiFingernails YETI_FINGERNAILS = ObjectHolder();

	public static final InfusionBowl DIAMOND_BOWL = ObjectHolder();
	public static final InfusionBowl STONE_BOWL = ObjectHolder();
	public static final ExpFlask EXP_FLASK = ObjectHolder();

	public static final BaseSlab ALLURICORN_SLAB = ObjectHolder();
	public static final BaseSlab BLISSARD_SLAB = ObjectHolder();
	public static final BaseSlab COMPEER_SLAB = ObjectHolder();
	public static final BaseSlab CONSTRUCT_OF_SERVILITY_SLAB = ObjectHolder();
	public static final BaseSlab CORBY_SLAB = ObjectHolder();
	public static final BaseSlab CRAGGY_SLAB = ObjectHolder();
	public static final BaseSlab DRAGGY_SLAB = ObjectHolder();
	public static final BaseSlab ENDER_CARRIER_SLAB = ObjectHolder();
	public static final BaseSlab GNAWER_SLAB = ObjectHolder();
	public static final BaseSlab GOOFER_SLAB = ObjectHolder();
	public static final BaseSlab HEALING_GOLEM_SLAB = ObjectHolder();
	public static final BaseSlab HELLQUIN_SLAB = ObjectHolder();
	public static final BaseSlab HORNTAIL_SLAB = ObjectHolder();
	public static final BaseSlab MECHA_CYCLOPS_SLAB = ObjectHolder();
	public static final BaseSlab MECHA_SKELLOX_SLAB = ObjectHolder();
	public static final BaseSlab PENGUIN_SLAB = ObjectHolder();
	public static final BaseSlab PLATEOSAUR_SLAB = ObjectHolder();
	public static final BaseSlab RAMMERHORN_SLAB = ObjectHolder();
	public static final BaseSlab SHADDY_SLAB = ObjectHolder();
	public static final BaseSlab SPIKEBACK_SLAB = ObjectHolder();
	public static final BaseSlab SPRAGGY_SLAB = ObjectHolder();
	public static final BaseSlab WAGGY_SLAB = ObjectHolder();

	public static final SeedsItem BUBBLE_BERRY_SEEDS = ObjectHolder();
	public static final SeedsItem CHILLI_SEEDS = ObjectHolder();
	public static final SeedsItem FLORACLE_SEEDS = ObjectHolder();
	public static final SeedsItem GOLDICAP_SEEDS = ObjectHolder();
	public static final SeedsItem HEART_FRUIT_SEEDS = ObjectHolder();
	public static final SeedsItem HOLLY_TOP_SEEDS = ObjectHolder();
	public static final SeedsItem LUNACRIKE_SEEDS = ObjectHolder();
	public static final SeedsItem LUNA_GLOBE_SEEDS = ObjectHolder();
	public static final SeedsItem LUNALON_SEEDS = ObjectHolder();
	public static final SeedsItem MAGIC_MARANG_SEEDS = ObjectHolder();
	public static final SeedsItem ROSIDON_SEEDS = ObjectHolder();
	public static final SeedsItem TEA_SEEDS = ObjectHolder();
	public static final SeedsItem THORNY_PLANT_SEEDS = ObjectHolder();
	public static final SeedsItem TRILLIAD_SEEDS = ObjectHolder();

	public static final RecordItem OUTLAW_DISC = ObjectHolder();

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

		registerItem(registry, new SimpleItem("BaronyteNugget", "baronyte_nugget"), "misc/mineral/nugget/", "nuggetBaronyte");
		registerItem(registry, new SimpleItem("BlaziumNugget", "blazium_nugget"), "misc/mineral/nugget/", "nuggetBlazium");
		registerItem(registry, new SimpleItem("ElecaniumNugget", "elecanium_nugget"), "misc/mineral/nugget/", "nuggetElecanium");
		registerItem(registry, new SimpleItem("EmberstoneNugget", "emberstone_nugget"), "misc/mineral/nugget/", "nuggetEmberstone");
		registerItem(registry, new SimpleItem("GhastlyNugget", "ghastly_nugget"), "misc/mineral/nugget/", "nuggetGhastly");
		registerItem(registry, new SimpleItem("GhoulishNugget", "ghoulish_nugget"), "misc/mineral/nugget/", "nuggetGhoulish");
		registerItem(registry, new SimpleItem("LimoniteNugget", "limonite_nugget"), "misc/mineral/nugget/", "nuggetLimonite");
		registerItem(registry, new SimpleItem("LunarNugget", "lunar_nugget"), "misc/mineral/nugget/", "nuggetLunar");
		registerItem(registry, new SimpleItem("LyonNugget", "lyon_nugget"), "misc/mineral/nugget/", "nuggetLyon");
		registerItem(registry, new SimpleItem("MystiteNugget", "mystite_nugget"), "misc/mineral/nugget/", "nuggetMystite");
		registerItem(registry, new SimpleItem("RositeNugget", "rosite_nugget"), "misc/mineral/nugget/", "nuggetRosite");
		registerItem(registry, new SimpleItem("ShyrestoneNugget", "shyrestone_nugget"), "misc/mineral/nugget/", "nuggetShyrestone");
		registerItem(registry, new SimpleItem("VarsiumNugget", "varsium_nugget"), "misc/mineral/nugget/", "nuggetVarsium");

		registerItem(registry, new RuneItem("ChargedRune", "charged_rune", true), "misc/ammo/");
		registerItem(registry, new RuneItem("CompassRune", "compass_rune", true, BlockRegister.RUNE_POST_COMPASS), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("DistortionRune", "distortion_rune", true, BlockRegister.RUNE_POST_DISTORTION), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("EnergyRune", "energy_rune", false, BlockRegister.RUNE_POST_ENERGY), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("FireRune", "fire_rune", false, BlockRegister.RUNE_POST_FIRE), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("KineticRune", "kinetic_rune", true, BlockRegister.RUNE_POST_KINETIC), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("LifeRune", "life_rune", true, BlockRegister.RUNE_POST_LIFE), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("LunarRune", "lunar_rune", true, BlockRegister.RUNE_POST_LUNAR), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("PoisonRune", "poison_rune", false, BlockRegister.RUNE_POST_POISON), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("PowerRune", "power_rune", false, BlockRegister.RUNE_POST_POWER), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("StormRune", "storm_rune", true, BlockRegister.RUNE_POST_STORM), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("StrikeRune", "strike_rune", false, BlockRegister.RUNE_POST_STRIKE), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("UnpoweredRune", "unpowered_rune", false), "misc/ammo/");
		registerItem(registry, new RuneItem("WaterRune", "water_rune", false, BlockRegister.RUNE_POST_WATER), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("WindRune", "wind_rune", false, BlockRegister.RUNE_POST_WIND), "misc/ammo/", "aoaRune");
		registerItem(registry, new RuneItem("WitherRune", "wither_rune", false, BlockRegister.RUNE_POST_WITHER), "misc/ammo/", "aoaRune");
		
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
		registerItem(registry, new ReservedItem("MillenniumUpgrader", "millennium_upgrader", "alien_orb"), "misc/misc/");
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
		registerItem(registry, new SimpleItem("StickySlime", "sticky_slime"), "misc/misc/", "slimeball");
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
		registerItem(registry, new SimpleItem("SmileyUpgradeKit", "smiley_upgrade_kit"), "misc/upgradekit/");
		
		registerItem(registry, new WaterloggedItem("WaterloggedAquaCannon", "waterlogged_aqua_cannon", 0), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedCoralArchergun", "waterlogged_coral_archergun", 1), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedCoralCannon", "waterlogged_coral_cannon", 2), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedCoralClogger", "waterlogged_coral_clogger", 3), "misc/misc/");
		registerItem(registry, new WaterloggedItem("WaterloggedReefer", "waterlogged_reefer", 4), "misc/misc/");

		registerItem(registry, new IncompleteMechaItem("IncompleteMechaArchergun", "incomplete_mecha_archergun", WeaponRegister.MECHA_ARCHERGUN), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechaBow", "incomplete_mecha_bow", WeaponRegister.MECHA_BOW), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechaCannon", "incomplete_mecha_cannon", WeaponRegister.MECHA_CANNON), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechanicalAssaultRifle", "incomplete_mechanical_assault_rifle", WeaponRegister.MECHANICAL_ASSAULT_RIFLE), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechaStaff", "incomplete_mecha_staff", WeaponRegister.MECHA_STAFF), "misc/misc/");
		registerItem(registry, new IncompleteMechaItem("IncompleteMechyro", "incomplete_mechyro", WeaponRegister.MECHYRO), "misc/misc/");

		registerItem(registry, new BlankRealmstone(), "misc/realmstone/");
		registerItem(registry, new Realmstone("AbyssRealmstone", "abyss_realmstone", BlockRegister.ABYSS_PORTAL, () -> SoundsRegister.ABYSS_PORTAL_ACTIVATE, "abyss"), "misc/realmstone/");
		registerItem(registry, new Realmstone("NetherRealmstone", "nether_realmstone", BlockRegister.NETHER_PORTAL, () -> SoundsRegister.ABYSS_PORTAL_ACTIVATE, "nether"), "misc/realmstone/");
		registerItem(registry, new Realmstone("AncientCavernRealmstone", "ancient_cavern_realmstone", BlockRegister.ANCIENT_CAVERN_PORTAL, () -> SoundsRegister.ANCIENT_CAVERN_PORTAL_ACTIVATE, "ancientCavern"), "misc/realmstone/");
		registerItem(registry, new Realmstone("BarathosRealmstone", "barathos_realmstone", BlockRegister.BARATHOS_PORTAL, () -> SoundsRegister.BARREN_PORTAL_ACTIVATE, "barathos"), "misc/realmstone/");
		registerItem(registry, new Realmstone("BoreanRealmstone", "borean_realmstone", BlockRegister.BOREAN_PORTAL, () -> SoundsRegister.NATURAL_PORTAL_ACTIVATE, "lborean"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CandylandRealmstone", "candyland_realmstone", BlockRegister.CANDYLAND_PORTAL, () -> SoundsRegister.CANDYLAND_PORTAL_ACTIVATE, "candyland"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CeleveRealmstone", "celeve_realmstone", BlockRegister.CELEVE_PORTAL, () -> SoundsRegister.CELEVE_PORTAL_ACTIVATE, "celeve"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CreeponiaRealmstone", "creeponia_realmstone", BlockRegister.CREEPONIA_PORTAL, () -> SoundsRegister.CREEPONIA_PORTAL_ACTIVATE, "creeponia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("CrysteviaRealmstone", "crystevia_realmstone", BlockRegister.CRYSTEVIA_PORTAL, () -> SoundsRegister.CRYSTEVIA_PORTAL_ACTIVATE, "crystevia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("DeeplandsRealmstone", "deeplands_realmstone", BlockRegister.DEEPLANDS_PORTAL, () -> SoundsRegister.BARREN_PORTAL_ACTIVATE, "deeplands"), "misc/realmstone/");
		registerItem(registry, new Realmstone("DustopiaRealmstone", "dustopia_realmstone", BlockRegister.DUSTOPIA_PORTAL, () -> SoundsRegister.DARK_PORTAL_ACTIVATE, "dustopia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("GardenciaRealmstone", "gardencia_realmstone", BlockRegister.GARDENCIA_PORTAL, () -> SoundsRegister.NATURAL_PORTAL_ACTIVATE, "gardencia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("GreckonRealmstone", "greckon_realmstone", BlockRegister.GRECKON_PORTAL, () -> SoundsRegister.DARK_PORTAL_ACTIVATE, "greckon"), "misc/realmstone/");
		registerItem(registry, new Realmstone("HavenRealmstone", "haven_realmstone", BlockRegister.HAVEN_PORTAL, () -> SoundsRegister.LIGHT_PORTAL_ACTIVATE, "haven"), "misc/realmstone/");
		registerItem(registry, new Realmstone("ImmortallisRealmstone", "immortallis_realmstone", BlockRegister.IMMORTALLIS_PORTAL, () -> SoundsRegister.IMMORTALLIS_PORTAL_ACTIVATE, "immortallis"), "misc/realmstone/");
		registerItem(registry, new Realmstone("IromineRealmstone", "iromine_realmstone", BlockRegister.IROMINE_PORTAL, () -> SoundsRegister.IROMINE_PORTAL_ACTIVATE, "iromine"), "misc/realmstone/");
		registerItem(registry, new Realmstone("LelyetiaRealmstone", "lelyetia_realmstone", BlockRegister.LELYETIA_PORTAL, () -> SoundsRegister.NATURAL_PORTAL_ACTIVATE, "lelyetia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("LunalusRealmstone", "lunalus_realmstone", BlockRegister.LUNALUS_PORTAL, () -> SoundsRegister.MUSIC_NULL, "lunalus"), "misc/realmstone/");
		registerItem(registry, new Realmstone("MysteriumRealmstone", "mysterium_realmstone", BlockRegister.MYSTERIUM_PORTAL, () -> SoundsRegister.NATURAL_PORTAL_ACTIVATE, "mysterium"), "misc/realmstone/");
		registerItem(registry, new Realmstone("PrecasiaRealmstone", "precasia_realmstone", BlockRegister.PRECASIA_PORTAL, () -> SoundsRegister.NATURAL_PORTAL_ACTIVATE, "precasia"), "misc/realmstone/");
		registerItem(registry, new Realmstone("RunandorRealmstone", "runandor_realmstone", BlockRegister.RUNANDOR_PORTAL, () -> SoundsRegister.LIGHT_PORTAL_ACTIVATE, "runandor"), "misc/realmstone/");
		registerItem(registry, new Realmstone("ShyrelandsRealmstone", "shyrelands_realmstone", BlockRegister.SHYRELANDS_PORTAL, () -> SoundsRegister.SHYRELANDS_PORTAL_ACTIVATE, "shyrelands"), "misc/realmstone/");
		registerItem(registry, new Realmstone("VoxPondsRealmstone", "vox_ponds_realmstone", BlockRegister.VOX_PONDS_PORTAL, () -> SoundsRegister.DARK_PORTAL_ACTIVATE, "voxPonds"), "misc/realmstone/");

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
		registerItem(registry, new InfusionStone("RadiantInfusionStone", "radiant_infusion_stone", 70, 220.0f, getUnmappedItem("radiant_power_stone")), "misc/infusionstone/", "infusionStone");
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
		registerItem(registry, new FungalTea().setContainerItem(ItemRegister.CUP), "food/drinks/");
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
		registerItem(registry, new NaturalTea().setContainerItem(ItemRegister.CUP), "food/drinks/");
		registerItem(registry, new BasicFood("NatureMelonSlice", "nature_melon_slice", 1, 0.4f), "food/plants/", "listAllfruit");
		registerItem(registry, new BasicFood("PeppermintCandy", "peppermint_candy", 1, 0.2f), "food/misc/", "foodMints", "foodCandy");
		registerItem(registry, new Rosidons(), "food/plants/");
		registerItem(registry, new BasicFood("SourCandy", "sour_candy", 1, 0.15f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("SourGummy", "sour_gummy", 1, 0.3f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("SourPop", "sour_pop", 1, 0.18f), "food/misc/", "foodCandy");
		registerItem(registry, new BasicFood("SpearmintCandy", "spearmint_candy", 1, 0.25f), "food/misc/", "foodMints", "foodCandy");
		registerItem(registry, new Tea().setContainerItem(ItemRegister.CUP), "food/drinks/");
		registerItem(registry, new TrilliadLeaves(), "food/plants/");
		registerItem(registry, new BasicFood("RawUrsaMeat", "raw_ursa_meat", 3, 0.3f, true), "food/meat/raw/", "listAllmeatraw");
		registerItem(registry, new BasicFood("UrsaMeat", "ursa_meat", 8, 0.8f, true), "food/meat/", "listAllmeatcooked");
		registerItem(registry, new YetiFingernails(), "food/misc/");

		registerItem(registry, new InfusionBowl("DiamondBowl", "diamond_bowl", 750, 5, 10), "tools/misc/");
		registerItem(registry, new InfusionBowl("StoneBowl", "stone_bowl", 100, 1, 0), "tools/misc/");
		registerItem(registry, new ExpFlask(), "tools/misc/");

		registerItem(registry, new AlluricornSlab(), "minionslabs/");
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

		registerItem(registry, new SeedsItem("BubbleBerrySeeds", "bubble_berry_seeds", BlockRegister.BUBBLE_BERRY_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("ChilliSeeds", "chilli_seeds", BlockRegister.CHILLI_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("FloracleSeeds", "floracle_seeds", BlockRegister.FLORACLES_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("GoldicapSeeds", "goldicap_seeds", BlockRegister.GOLDICAPS_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("HeartFruitSeeds", "heart_fruit_seeds", BlockRegister.HEART_FRUIT_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("HollyTopSeeds", "holly_top_seeds", BlockRegister.HOLLY_TOPS_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("LunacrikeSeeds", "lunacrike_seeds", BlockRegister.LUNACRIKE_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("LunaGlobeSeeds", "luna_globe_seeds", BlockRegister.LUNA_GLOBE_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("LunalonSeeds", "lunalon_seeds", BlockRegister.LUNALON_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("MagicMarangSeeds", "magic_marang_seeds", BlockRegister.MAGIC_MARANG_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("RosidonSeeds", "rosidon_seeds", BlockRegister.ROSIDON_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("TeaSeeds", "tea_seeds", BlockRegister.TEA_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("ThornyPlantSeeds", "thorny_plant_seeds", BlockRegister.THORNY_PLANT_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");
		registerItem(registry, new SeedsItem("TrilliadSeeds", "trilliad_seeds", BlockRegister.TRILLIAD_CROP, Blocks.FARMLAND), "misc/seeds/", "listAllseed");

		registerItem(registry, new RecordItem("Outlaw", "outlaw_disc", () -> SoundsRegister.OUTLAW_DISC), "records/", "record");
	}

	@SubscribeEvent
	public static void remapMissing(final RegistryEvent.MissingMappings<Item> ev) {
		for (RegistryEvent.MissingMappings.Mapping<Item> map : ev.getAllMappings()) {
			switch (map.key.toString()) {
				case "aoa3:amethyst_ingot":
					map.remap(AMETHYST);
					break;
				case "aoa3:jade_ingot":
					map.remap(JADE);
					break;
				case "aoa3:shyregem_ingot":
					map.remap(SHYREGEM);
					break;
				case "aoa3:sapphire_ingot":
					map.remap(SAPPHIRE);
					break;
				case "aoa3:millenium_upgrader":
					map.remap(MILLENNIUM_UPGRADER);
					break;
				default:
					break;
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

			if (wrapper.item instanceof RecordItem)
				((RecordItem)wrapper.item).applySound();
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

	@SuppressWarnings("ConstantConditions")
	@Nonnull
	private static <T> T ObjectHolder() {
		return null;
	}
}
