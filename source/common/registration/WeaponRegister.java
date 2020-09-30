package net.tslat.aoa3.common.registration;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntitySpectralHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntityTippedHollyArrow;
import net.tslat.aoa3.entity.projectiles.thrown.*;
import net.tslat.aoa3.item.weapon.archergun.*;
import net.tslat.aoa3.item.weapon.blaster.*;
import net.tslat.aoa3.item.weapon.bow.*;
import net.tslat.aoa3.item.weapon.cannon.*;
import net.tslat.aoa3.item.weapon.greatblade.*;
import net.tslat.aoa3.item.weapon.gun.*;
import net.tslat.aoa3.item.weapon.maul.*;
import net.tslat.aoa3.item.weapon.shotgun.*;
import net.tslat.aoa3.item.weapon.sniper.Terminator;
import net.tslat.aoa3.item.weapon.sniper.*;
import net.tslat.aoa3.item.weapon.staff.*;
import net.tslat.aoa3.item.weapon.sword.*;
import net.tslat.aoa3.item.weapon.thrown.*;
import net.tslat.aoa3.item.weapon.vulcane.*;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;

import static net.tslat.aoa3.common.registration.MaterialsRegister.*;

@SuppressWarnings({"ConstantConditions", "unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class WeaponRegister {
	public static final BaseSword AMETHYST_SWORD = ObjectHolder();
	public static final BaseSword BARON_SWORD = ObjectHolder();
	public static final BaseSword BLOODFURY = ObjectHolder();
	public static final BaseSword BLOODSTONE_SWORD = ObjectHolder();
	public static final BaseSword CANDLEFIRE_SWORD = ObjectHolder();
	public static final BaseSword CARAMEL_CARVER = ObjectHolder();
	public static final BaseSword CORALSTORM_SWORD = ObjectHolder();
	public static final BaseSword CREEPIFIED_SWORD = ObjectHolder();
	public static final BaseSword CRYSTALLITE_SWORD = ObjectHolder();
	public static final BaseSword EMBERSTONE_SWORD = ObjectHolder();
	public static final BaseSword EXPLOCHRON_SWORD = ObjectHolder();
	public static final BaseSword FIREBORNE_SWORD = ObjectHolder();
	public static final BaseSword GUARDIANS_SWORD = ObjectHolder();
	public static final BaseSword HARVESTER_SWORD = ObjectHolder();
	public static final BaseSword HOLY_SWORD = ObjectHolder();
	public static final BaseSword ILLUSION_SWORD = ObjectHolder();
	public static final BaseSword JADE_SWORD = ObjectHolder();
	public static final BaseSword LEGBONE_SWORD = ObjectHolder();
	public static final BaseSword LIGHTS_WAY = ObjectHolder();
	public static final BaseSword LIMONITE_SWORD = ObjectHolder();
	public static final BaseSword NETHENGEIC_SWORD = ObjectHolder();
	public static final BaseSword PRIMAL_SWORD = ObjectHolder();
	public static final BaseSword ROCKBASHER_SWORD = ObjectHolder();
	public static final BaseSword ROCK_PICK_SWORD = ObjectHolder();
	public static final BaseSword ROSIDIAN_SWORD = ObjectHolder();
	public static final BaseSword ROSITE_SWORD = ObjectHolder();
	public static final BaseSword RUNIC_SWORD = ObjectHolder();
	public static final BaseSword SAPPHIRE_SWORD = ObjectHolder();
	public static final BaseSword SHADOW_SWORD = ObjectHolder();
	public static final BaseSword SHROOMUS_SWORD = ObjectHolder();
	public static final BaseSword SKELETAL_SWORD = ObjectHolder();
	public static final BaseSword SUPREMACY_SWORD = ObjectHolder();
	public static final BaseSword SWEET_SWORD = ObjectHolder();
	public static final BaseSword TROLL_BASHER_AXE = ObjectHolder();
	public static final BaseSword ULTRAFLAME = ObjectHolder();
	public static final BaseSword VOID_SWORD = ObjectHolder();

	public static final BaseGreatblade BARON_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade BLOODLURKER = ObjectHolder();
	public static final BaseGreatblade CANDY_BLADE = ObjectHolder();
	public static final BaseGreatblade CORAL_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade COTTON_CRUSHER = ObjectHolder();
	public static final BaseGreatblade CREEPOID_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade CRYSTAL_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade EREBON_SCYTHE = ObjectHolder();
	public static final BaseGreatblade GODS_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade GOOFY_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade HAUNTED_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade KNIGHTS_GUARD = ObjectHolder();
	public static final BaseGreatblade LELYETIAN_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade LUNAR_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade LUXON_SCYTHE = ObjectHolder();
	public static final BaseGreatblade LYONIC_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade MILLENNIUM_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade NOXIOUS_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade PLUTON_SCYTHE = ObjectHolder();
	public static final BaseGreatblade PRIMORDIAL_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade ROSIDIAN_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade ROYAL_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade RUNIC_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade SELYAN_SCYTHE = ObjectHolder();
	public static final BaseGreatblade SHROOMIC_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade SHYRE_SWORD = ObjectHolder();
	public static final BaseGreatblade SUBTERRANEAN_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade TIDAL_GREATBLADE = ObjectHolder();
	public static final BaseGreatblade UNDERWORLD_GREATBLADE = ObjectHolder();

	public static final BaseMaul CORALSTONE_MAUL = ObjectHolder();
	public static final BaseMaul CRYSTAL_MAUL = ObjectHolder();
	public static final BaseMaul ELECTRON_MAUL = ObjectHolder();
	public static final BaseMaul HORIZON_MAUL = ObjectHolder();
	public static final BaseMaul VULCAMMER_MAUL = ObjectHolder();

	public static final BaseGun ABOMINATOR = ObjectHolder();
	public static final BaseGun APOCO_ASSAULT_RIFLE = ObjectHolder();
	public static final BaseGun APOCO_RIFLE = ObjectHolder();
	public static final BaseGun AQUA_MAGNUM = ObjectHolder();
	public static final BaseGun ARTIFACT = ObjectHolder();
	public static final BaseGun BARONATOR = ObjectHolder();
	public static final BaseGun BAYONETTE_RIFLE = ObjectHolder();
	public static final BaseGun BIG_TOP = ObjectHolder();
	public static final BaseGun BLOOD_IRON = ObjectHolder();
	public static final BaseGun CHAIN_WRECKER = ObjectHolder();
	public static final BaseGun CHILLI_CHUGGER = ObjectHolder();
	public static final BaseGun CLOWNERSHOT = ObjectHolder();
	public static final BaseGun CONSTRUCT = ObjectHolder();
	public static final BaseGun CORAL_CLOGGER = ObjectHolder();
	public static final BaseGun CORE_RIFLE = ObjectHolder();
	public static final BaseGun CRYSTAL_CARVER = ObjectHolder();
	public static final BaseGun CYCLONE = ObjectHolder();
	public static final BaseGun DARKENER = ObjectHolder();
	public static final BaseGun DART_GUN = ObjectHolder();
	public static final BaseGun DESTRUCTION_RIFLE = ObjectHolder();
	public static final BaseGun DISCHARGE_RIFLE = ObjectHolder();
	public static final BaseGun DRACO = ObjectHolder();
	public static final BaseGun DRAGILATOR = ObjectHolder();
	public static final BaseGun DUSTOMETER = ObjectHolder();
	public static final BaseGun ECHO_GULL = ObjectHolder();
	public static final BaseGun ELECTINATOR = ObjectHolder();
	public static final BaseGun FLAME_WRECKER = ObjectHolder();
	public static final BaseGun FLAMING_FURY = ObjectHolder();
	public static final BaseGun FLORO_RIFLE = ObjectHolder();
	public static final BaseGun FLOWERS_FURY = ObjectHolder();
	public static final BaseGun FROSTICATOR = ObjectHolder();
	public static final BaseGun GARDENER = ObjectHolder();
	public static final BaseGun GAUGE_RIFLE = ObjectHolder();
	public static final BaseGun GERMINATOR = ObjectHolder();
	public static final BaseGun GOLDEN_FURY = ObjectHolder();
	public static final BaseGun HAPPY_HAUNTER = ObjectHolder();
	public static final BaseGun HAUNTER_RIFLE = ObjectHolder();
	public static final BaseGun HEAT_WAVE = ObjectHolder();
	public static final BaseGun HIVER = ObjectHolder();
	public static final BaseGun HOT_SHOT = ObjectHolder();
	public static final BaseGun HUNTERS_RIFLE = ObjectHolder();
	public static final BaseGun IOMINATOR = ObjectHolder();
	public static final BaseGun ION_REVOLVER = ObjectHolder();
	public static final BaseGun IRO_RIFLE = ObjectHolder();
	public static final BaseGun KRILINATOR = ObjectHolder();
	public static final BaseGun LIGHT_IRON = ObjectHolder();
	public static final BaseGun LUNAR_ASSAULT_RIFLE = ObjectHolder();
	public static final BaseGun MECHANICAL_ASSAULT_RIFLE = ObjectHolder();
	public static final BaseGun MEGAGUN = ObjectHolder();
	public static final BaseGun MIASMA = ObjectHolder();
	public static final BaseGun MINIGUN = ObjectHolder();
	public static final BaseGun MINT_MAGNUM = ObjectHolder();
	public static final BaseGun MK = ObjectHolder();
	public static final BaseGun MK_FUNG = ObjectHolder();
	public static final BaseGun NETHENETTE_RIFLE = ObjectHolder();
	public static final BaseGun NETHENGEIC_SLUGGER = ObjectHolder();
	public static final BaseGun OVERSHOT = ObjectHolder();
	public static final BaseGun PRECASIAN_SLUGGER = ObjectHolder();
	public static final BaseGun PREDATOR = ObjectHolder();
	public static final BaseGun PREDIGUN = ObjectHolder();
	public static final BaseGun PULSATOR = ObjectHolder();
	public static final BaseGun PURITY_RIFLE = ObjectHolder();
	public static final BaseGun ROCKER_RIFLE = ObjectHolder();
	public static final BaseGun ROULETTE = ObjectHolder();
	public static final BaseGun SHOE_FLINGER = ObjectHolder();
	public static final BaseGun SKULLETTE = ObjectHolder();
	public static final BaseGun SKULLIFACT = ObjectHolder();
	public static final BaseGun SPECTACLE = ObjectHolder();
	public static final BaseGun SPINE_GUN = ObjectHolder();
	public static final BaseGun SQUAD_GUN = ObjectHolder();
	public static final BaseGun STAMPEDE = ObjectHolder();
	public static final BaseGun STORMER = ObjectHolder();
	public static final BaseGun SUBLIMUS = ObjectHolder();
	public static final BaseGun TIGER_TOMMY = ObjectHolder();
	public static final BaseGun TOMMY = ObjectHolder();
	public static final BaseGun VILE_VANQUISHER = ObjectHolder();
	public static final BaseGun WART_GUN = ObjectHolder();
	public static final BaseGun WRECKER = ObjectHolder();

	public static final BaseShotgun ABYSSRO = ObjectHolder();
	public static final BaseShotgun AMPLIFIER = ObjectHolder();
	public static final BaseShotgun BLAST_BARREL = ObjectHolder();
	public static final BaseShotgun BLUE_BARREL = ObjectHolder();
	public static final BaseShotgun BOULDER = ObjectHolder();
	public static final BaseShotgun BROWN_BLASTER = ObjectHolder();
	public static final BaseShotgun DEMOLISHER = ObjectHolder();
	public static final BaseShotgun DESTRUCTION_SHOTGUN = ObjectHolder();
	public static final BaseShotgun DISCHARGE_SHOTGUN = ObjectHolder();
	public static final BaseShotgun GIMMICK = ObjectHolder();
	public static final BaseShotgun GINGER_BLASTER = ObjectHolder();
	public static final BaseShotgun LONG_SHOT = ObjectHolder();
	public static final BaseShotgun MECHYRO = ObjectHolder();
	public static final BaseShotgun PURITY_SHOTGUN = ObjectHolder();
	public static final BaseShotgun PURPLE_PUNISHER = ObjectHolder();
	public static final BaseShotgun RED_ROCKET = ObjectHolder();
	public static final BaseShotgun VIVO = ObjectHolder();

	public static final BaseSniper BARON_SSR = ObjectHolder();
	public static final BaseSniper BAYONETTE_SR = ObjectHolder();
	public static final BaseSniper BOLT_RIFLE = ObjectHolder();
	public static final BaseSniper CAMO_RIFLE = ObjectHolder();
	public static final BaseSniper CLOWN_CRACKER = ObjectHolder();
	public static final BaseSniper CLOWNIMATOR = ObjectHolder();
	public static final BaseSniper CRYSTANEER = ObjectHolder();
	public static final BaseSniper DARK_BEAST = ObjectHolder();
	public static final BaseSniper DEADLOCK = ObjectHolder();
	public static final BaseSniper DECIMATOR = ObjectHolder();
	public static final BaseSniper DISCHARGE_SNIPER = ObjectHolder();
	public static final BaseSniper DUAL_SIGHT = ObjectHolder();
	public static final BaseSniper DUSTER = ObjectHolder();
	public static final BaseSniper FLORO500 = ObjectHolder();
	public static final BaseSniper HEAD_HUNTER = ObjectHolder();
	public static final BaseSniper HIVE_CRACKER = ObjectHolder();
	public static final BaseSniper KA500 = ObjectHolder();
	public static final BaseSniper MARK_MAKER = ObjectHolder();
	public static final BaseSniper MINERAL = ObjectHolder();
	public static final BaseSniper MONSTER = ObjectHolder();
	public static final BaseSniper MOON_MAKER = ObjectHolder();
	public static final BaseSniper ROSID_RIFLE = ObjectHolder();
	public static final BaseSniper SABBATH = ObjectHolder();
	public static final BaseSniper SLUDGE_SNIPER = ObjectHolder();
	public static final BaseSniper SWEET_TOOTH = ObjectHolder();
	public static final BaseSniper TERMINATOR = ObjectHolder();
	public static final BaseSniper VIPER1 = ObjectHolder();

	public static final BaseCannon ANCIENT_BOMBER = ObjectHolder();
	public static final BaseCannon ANCIENT_DISCHARGER = ObjectHolder();
	public static final BaseCannon AQUA_CANNON = ObjectHolder();
	public static final BaseCannon BALLOON_BOMBER = ObjectHolder();
	public static final BaseCannon BIG_BLAST = ObjectHolder();
	public static final BaseCannon BLAST_CANNON = ObjectHolder();
	public static final BaseCannon BLISSFUL_BLAST = ObjectHolder();
	public static final BaseCannon BOMB_LAUNCHER = ObjectHolder();
	public static final BaseCannon BOOM_BOOM = ObjectHolder();
	public static final BaseCannon BOOM_CANNON = ObjectHolder();
	public static final BaseCannon BOULDER_BOMBER = ObjectHolder();
	public static final BaseCannon BOZO_BLASTER = ObjectHolder();
	public static final BaseCannon BULB_CANNON = ObjectHolder();
	public static final BaseCannon CARROT_CANNON = ObjectHolder();
	public static final BaseCannon CLOWN_CANNON = ObjectHolder();
	public static final BaseCannon CLOWNO_PULSE = ObjectHolder();
	public static final BaseCannon CORAL_CANNON = ObjectHolder();
	public static final BaseCannon DISCHARGE_CANNON = ObjectHolder();
	public static final BaseCannon ENERGY_CANNON = ObjectHolder();
	public static final BaseCannon EREBON_STICKLER = ObjectHolder();
	public static final BaseCannon FLORO_RPG = ObjectHolder();
	public static final BaseCannon FLOWER_CANNON = ObjectHolder();
	public static final BaseCannon FUNGAL_CANNON = ObjectHolder();
	public static final BaseCannon GHAST_BLASTER = ObjectHolder();
	public static final BaseCannon GHOUL_CANNON = ObjectHolder();
	public static final BaseCannon GIGA_CANNON = ObjectHolder();
	public static final BaseCannon GOLDER_BOMBER = ObjectHolder();
	public static final BaseCannon HIVE_BLASTER = ObjectHolder();
	public static final BaseCannon HIVE_HOWITZER = ObjectHolder();
	public static final BaseCannon IRO_CANNON = ObjectHolder();
	public static final BaseCannon JACK_FUNGER = ObjectHolder();
	public static final BaseCannon JACK_ROCKER = ObjectHolder();
	public static final BaseCannon LUXON_STICKLER = ObjectHolder();
	public static final BaseCannon MECHA_CANNON = ObjectHolder();
	public static final BaseCannon MINI_CANNON = ObjectHolder();
	public static final BaseCannon MISSILE_MAKER = ObjectHolder();
	public static final BaseCannon MOON_CANNON = ObjectHolder();
	public static final BaseCannon PLUTON_STICKLER = ObjectHolder();
	public static final BaseCannon PREDATORIAN_BLASTER = ObjectHolder();
	public static final BaseCannon PULSE_CANNON = ObjectHolder();
	public static final BaseCannon RPG = ObjectHolder();
	public static final BaseCannon SELYAN_STICKLER = ObjectHolder();
	public static final BaseCannon SHADOW_BLASTER = ObjectHolder();
	public static final BaseCannon SHYRE_BLASTER = ObjectHolder();
	public static final BaseCannon SMILE_BLASTER = ObjectHolder();
	public static final BaseCannon SUPER_CANNON = ObjectHolder();
	public static final BaseCannon ULTRA_CANNON = ObjectHolder();
	public static final BaseCannon VOX_CANNON = ObjectHolder();
	public static final BaseCannon WATER_BALLOON_BOMBER = ObjectHolder();
	public static final BaseCannon WITHER_CANNON = ObjectHolder();

	public static final BaseThrownWeapon GRENADE = ObjectHolder();
	public static final BaseThrownWeapon SLICE_STAR = ObjectHolder();
	public static final BaseThrownWeapon CHAKRAM = ObjectHolder();
	public static final BaseThrownWeapon GOO_BALL = ObjectHolder();
	public static final BaseThrownWeapon VULKRAM = ObjectHolder();
	public static final BaseThrownWeapon HELLFIRE = ObjectHolder();
	public static final BaseThrownWeapon RUNIC_BOMB = ObjectHolder();

	public static final BaseVulcane VULCANE = ObjectHolder();
	public static final BaseVulcane BATTLE_VULCANE = ObjectHolder();
	public static final BaseVulcane EQUALITY_VULCANE = ObjectHolder();
	public static final BaseVulcane FIRE_VULCANE = ObjectHolder();
	public static final BaseVulcane IMPAIRMENT_VULCANE = ObjectHolder();
	public static final BaseVulcane POISON_VULCANE = ObjectHolder();
	public static final BaseVulcane POWER_VULCANE = ObjectHolder();
	public static final BaseVulcane WITHER_VULCANE = ObjectHolder();

	public static final BaseBow ALACRITY_BOW = ObjectHolder();
	public static final BaseBow ANCIENT_BOW = ObjectHolder();
	public static final BaseBow ATLANTIC_BOW = ObjectHolder();
	public static final BaseBow BARON_BOW = ObjectHolder();
	public static final BaseBow BOREIC_BOW = ObjectHolder();
	public static final BaseBow DAYBREAKER_BOW = ObjectHolder();
	public static final BaseBow DEEP_BOW = ObjectHolder();
	public static final BaseBow EXPLOSIVE_BOW = ObjectHolder();
	public static final BaseBow HAUNTED_BOW = ObjectHolder();
	public static final BaseBow ICE_BOW = ObjectHolder();
	public static final BaseBow INFERNAL_BOW = ObjectHolder();
	public static final BaseBow JUSTICE_BOW = ObjectHolder();
	public static final BaseBow LUNAR_BOW = ObjectHolder();
	public static final BaseBow MECHA_BOW = ObjectHolder();
	public static final BaseBow NIGHTMARE_BOW = ObjectHolder();
	public static final BaseBow POISON_BOW = ObjectHolder();
	public static final BaseBow PREDATIOUS_BOW = ObjectHolder();
	public static final BaseBow PRIMORDIAL_BOW = ObjectHolder();
	public static final BaseBow ROSIDIAN_BOW = ObjectHolder();
	public static final BaseBow RUNIC_BOW = ObjectHolder();
	public static final BaseBow SCREAMER_BOW = ObjectHolder();
	public static final BaseBow SHYREGEM_BOW = ObjectHolder();
	public static final BaseBow SKELETAL_BOW = ObjectHolder();
	public static final BaseBow SKYDRIVER_BOW = ObjectHolder();
	public static final BaseBow SLINGSHOT = ObjectHolder();
	public static final BaseBow SOULFIRE_BOW = ObjectHolder();
	public static final BaseBow SPECTRAL_BOW = ObjectHolder();
	public static final BaseBow SPEED_BOW = ObjectHolder();
	public static final BaseBow SUNSHINE_BOW = ObjectHolder();
	public static final BaseBow TOXIN_BOW = ObjectHolder();
	public static final BaseBow VOID_BOW = ObjectHolder();
	public static final BaseBow WEAKEN_BOW = ObjectHolder();
	public static final BaseBow WITHER_BOW = ObjectHolder();

	public static final BaseArchergun CORAL_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun LUNAR_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun MECHA_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun PYRO_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun ROSIDIAN_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun SKELETAL_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun SPECTRAL_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun TROLLS_ARCHERGUN = ObjectHolder();
	public static final BaseArchergun VIRAL_ARCHERGUN = ObjectHolder();

	public static final BaseStaff AQUATIC_STAFF = ObjectHolder();
	public static final BaseStaff ATLANTIC_STAFF = ObjectHolder();
	public static final BaseStaff BARON_STAFF = ObjectHolder();
	public static final BaseStaff CANDY_STAFF = ObjectHolder();
	public static final BaseStaff CELESTIAL_STAFF = ObjectHolder();
	public static final BaseStaff CONCUSSION_STAFF = ObjectHolder();
	public static final BaseStaff CORAL_STAFF = ObjectHolder();
	public static final BaseStaff CRYSTAL_STAFF = ObjectHolder();
	public static final BaseStaff CRYSTIK_STAFF = ObjectHolder();
	public static final BaseStaff CRYSTON_STAFF = ObjectHolder();
	public static final BaseStaff DESTRUCTION_STAFF = ObjectHolder();
	public static final BaseStaff EMBER_STAFF = ObjectHolder();
	public static final BaseStaff EVERFIGHT_STAFF = ObjectHolder();
	public static final BaseStaff EVERMIGHT_STAFF = ObjectHolder();
	public static final BaseStaff FIRE_STAFF = ObjectHolder();
	public static final BaseStaff FIREFLY_STAFF = ObjectHolder();
	public static final BaseStaff FIRESTORM_STAFF = ObjectHolder();
	public static final BaseStaff FUNGAL_STAFF = ObjectHolder();
	public static final BaseStaff GHOUL_STAFF = ObjectHolder();
	public static final BaseStaff HAUNTERS_STAFF = ObjectHolder();
	public static final BaseStaff HIVE_STAFF = ObjectHolder();
	public static final BaseStaff JOKER_STAFF = ObjectHolder();
	public static final BaseStaff KAIYU_STAFF = ObjectHolder();
	public static final BaseStaff LIGHTNING_STAFF = ObjectHolder();
	public static final BaseStaff LIGHTSHINE = ObjectHolder();
	public static final BaseStaff LUNAR_STAFF = ObjectHolder();
	public static final BaseStaff LYONIC_STAFF = ObjectHolder();
	public static final BaseStaff MECHA_STAFF = ObjectHolder();
	public static final BaseStaff METEOR_STAFF = ObjectHolder();
	public static final BaseStaff MOONLIGHT_STAFF = ObjectHolder();
	public static final BaseStaff NATURE_STAFF = ObjectHolder();
	public static final BaseStaff NIGHTMARE_STAFF = ObjectHolder();
	public static final BaseStaff NOXIOUS_STAFF = ObjectHolder();
	public static final BaseStaff PHANTOM_STAFF = ObjectHolder();
	public static final BaseStaff POISON_STAFF = ObjectHolder();
	public static final BaseStaff POWER_STAFF = ObjectHolder();
	public static final BaseStaff PRIMORDIAL_STAFF = ObjectHolder();
	public static final BaseStaff REEF_STAFF = ObjectHolder();
	public static final BaseStaff REJUVENATION_STAFF = ObjectHolder();
	public static final BaseStaff ROSIDIAN_STAFF = ObjectHolder();
	public static final BaseStaff RUNIC_STAFF = ObjectHolder();
	public static final BaseStaff SHADOWLORD_STAFF = ObjectHolder();
	public static final BaseStaff SHOW_STAFF = ObjectHolder();
	public static final BaseStaff SHYRE_STAFF = ObjectHolder();
	public static final BaseStaff SKY_STAFF = ObjectHolder();
	public static final BaseStaff STRIKER_STAFF = ObjectHolder();
	public static final BaseStaff SUN_STAFF = ObjectHolder();
	public static final BaseStaff SURGE_STAFF = ObjectHolder();
	public static final BaseStaff TANGLE_STAFF = ObjectHolder();
	public static final BaseStaff ULTIMATUM_STAFF = ObjectHolder();
	public static final BaseStaff UNDERWORLD_STAFF = ObjectHolder();
	public static final BaseStaff WARLOCK_STAFF = ObjectHolder();
	public static final BaseStaff WATER_STAFF = ObjectHolder();
	public static final BaseStaff WEB_STAFF = ObjectHolder();
	public static final BaseStaff WIND_STAFF = ObjectHolder();
	public static final BaseStaff WITHER_STAFF = ObjectHolder();
	public static final BaseStaff WIZARDS_STAFF = ObjectHolder();

	public static final BaseBlaster APOCO_SHOWER = ObjectHolder();
	public static final BaseBlaster ATOMIZER = ObjectHolder();
	public static final BaseBlaster BEAMER = ObjectHolder();
	public static final BaseBlaster BLAST_CHILLER = ObjectHolder();
	public static final BaseBlaster BLOOD_DRAINER = ObjectHolder();
	public static final BaseBlaster BONE_BLASTER = ObjectHolder();
	public static final BaseBlaster BUBBLE_HORN = ObjectHolder();
	public static final BaseBlaster COLOUR_CANNON = ObjectHolder();
	public static final BaseBlaster CONFETTI_CANNON = ObjectHolder();
	public static final BaseBlaster CONFETTI_CLUSTER = ObjectHolder();
	public static final BaseBlaster DARK_DESTROYER = ObjectHolder();
	public static final BaseBlaster DARKLY_GUSTER = ObjectHolder();
	public static final BaseBlaster DEATH_RAY = ObjectHolder();
	public static final BaseBlaster DOOM_BRINGER = ObjectHolder();
	public static final BaseBlaster ERADICATOR = ObjectHolder();
	public static final BaseBlaster EXPERIMENT_W801 = ObjectHolder();
	public static final BaseBlaster FLOWERCORNE = ObjectHolder();
	public static final BaseBlaster FRAGMENT = ObjectHolder();
	public static final BaseBlaster FROSTER = ObjectHolder();
	public static final BaseBlaster GAS_BLASTER = ObjectHolder();
	public static final BaseBlaster GHOUL_GASSER = ObjectHolder();
	public static final BaseBlaster GOLD_BRINGER = ObjectHolder();
	public static final BaseBlaster GRAVITY_BLASTER = ObjectHolder();
	public static final BaseBlaster HELL_HORN = ObjectHolder();
	public static final BaseBlaster ILLUSION_REVOLVER = ObjectHolder();
	public static final BaseBlaster ILLUSION_SMG = ObjectHolder();
	public static final BaseBlaster ION_BLASTER = ObjectHolder();
	public static final BaseBlaster IRO_MINER = ObjectHolder();
	public static final BaseBlaster LASER_BLASTER = ObjectHolder();
	public static final BaseBlaster LIGHT_BLASTER = ObjectHolder();
	public static final BaseBlaster LIGHT_SPARK = ObjectHolder();
	public static final BaseBlaster LUNA_BLASTER = ObjectHolder();
	public static final BaseBlaster MECHA_BLASTER = ObjectHolder();
	public static final BaseBlaster MIND_BLASTER = ObjectHolder();
	public static final BaseBlaster MOON_DESTROYER = ObjectHolder();
	public static final BaseBlaster MOON_SHINER = ObjectHolder();
	public static final BaseBlaster ODIOUS = ObjectHolder();
	public static final BaseBlaster ORBOCRON = ObjectHolder();
	public static final BaseBlaster PARALYZER = ObjectHolder();
	public static final BaseBlaster PARTY_POPPER = ObjectHolder();
	public static final BaseBlaster POISON_PLUNGER = ObjectHolder();
	public static final BaseBlaster POWER_RAY = ObjectHolder();
	public static final BaseBlaster PROTON = ObjectHolder();
	public static final BaseBlaster REEFER = ObjectHolder();
	public static final BaseBlaster REVOLUTION = ObjectHolder();
	public static final BaseBlaster SEAOCRON = ObjectHolder();
	public static final BaseBlaster SKULLO_BLASTER = ObjectHolder();
	public static final BaseBlaster SOUL_DRAINER = ObjectHolder();
	public static final BaseBlaster SOUL_SPARK = ObjectHolder();
	public static final BaseBlaster SOUL_STORM = ObjectHolder();
	public static final BaseBlaster SPIRIT_SHOWER = ObjectHolder();
	public static final BaseBlaster SWARMOTRON = ObjectHolder();
	public static final BaseBlaster TOXIC_TERRORIZER = ObjectHolder();
	public static final BaseBlaster VORTEX_BLASTER = ObjectHolder();
	public static final BaseBlaster WHIMSY_WINDER = ObjectHolder();
	public static final BaseBlaster WITHERS_WRATH = ObjectHolder();

	@SubscribeEvent
	public static void registerWeapon(final RegistryEvent.Register<Item> ev) {
		final IForgeRegistry<Item> registry = ev.getRegistry();

		registerSwords(registry,
				new AmethystSword(		SWORD_AMETHYST, 		0), // Overworld | Crafting
				new BaronSword(			SWORD_BARON, 			0), // Barathos | Crafting
				new BloodfurySword(		SWORD_BLOODFURY, 		-2.2D), // Abyss | Crafting
				new BloodstoneSword(	SWORD_BLOODSTONE, 		0), // Abyss | Crafting
				new CandlefireSword(	SWORD_CANDLEFIRE, 		0), // UPGRADE KIT Nether
				new CaramelCarver(		SWORD_CARAMEL_CARVER, 	0), // Candyland | Gingerbread Man
				new CoralstormSword(	SWORD_CORALSTORM, 		0), // Overworld | Troll Trader
				new CreepifiedSword(	SWORD_CREEPIFIED, 		0), // Creeponia | CREEP
				new CrystalliteSword(	SWORD_CRYSTALLITE, 		-2.3D), // Haven | Crafting
				new EmberstoneSword(	SWORD_EMBERSTONE, 		0), // Nether | Crafting
				new ExplochronSword(	SWORD_EXPLOCHRON, 		0), // Iromine | Crafting
				new FireborneSword(		SWORD_FIREBORNE, 		0), // Nether | Crafting
				new GuardiansSword(		SWORD_GUARDIAN, 		0), // Haven | Four Guardians
				new HarvesterSword(		SWORD_HARVESTER, 		0), // Dustopia | Crusilisk
				new HolySword(			SWORD_HOLY, 			Enums.WeaponSpeed.TRIPLE.value), // Tribute
				new IllusionSword(		SWORD_ILLUSION, 		0), // Abyss | Elusive
				new JadeSword(			SWORD_JADE, 			0), // Overworld | Crafting
				new LegboneSword(		SWORD_LEGBONE, 			0), // Precasia | Iosaur
				new LightsWay(			SWORD_LIGHTS_WAY, 		Enums.WeaponSpeed.DOUBLE.value), // Shyrelands | Crafting
				new LimoniteSword(		SWORD_LIMONITE, 		0), // Overworld | Crafting
				new NethengeicSword(	SWORD_NETHENGEIC, 		0), // Nether | Nethengeic Wither
				new PrimalSword(		SWORD_PRIMAL, 			0), // Precasia | Tyrosaur
				new RockbasherSword(	SWORD_ROCKBASHER, 		0), // Haven | Rock Rider
				new RockPickSword(		SWORD_ROCKPICK, 		0), // Deeplands | Arocknid
				new RosidianSword(		SWORD_ROSIDIAN, 		0), // Gardencia | Vinocorne
				new RositeSword(		SWORD_ROSITE, 			0), // Overworld | Crafting
				new RunicSword(			SWORD_RUNIC, 			0), // Runandor | Clunkhead
				new SapphireSword(		SWORD_SAPPHIRE, 		0), // Overworld | Crafting
				new ShadowSword(		SWORD_SHADOW, 			0), // Dustopia | Crafting
				new ShroomusSword(		SWORD_SHROOMUS, 		0), // Mysterium | King Shroomus
				new SkeletalSword(		SWORD_SKELETAL, 		-2D), // Precasia | Skeletron
				new SupremacySword(		SWORD_SUPREMACY, 		0), // Celeve | Crafting
				new SweetSword(			SWORD_SWEET, 			0), // Candyland | Crafting
				new TrollBasherAxe(MaterialsRegister.TROLL_BASHER_AXE, 		0), // Overworld | Smash
				new Ultraflame(			SWORD_ULTRAFLAME, 		0), // Lunalus | Gorb Engineer
				new VoidSword(			SWORD_VOID, 			0) // Overworld | Mother Void Walker
		);

		registerGreatblades(registry,
				new BaronGreatblade(		19.5f, 	0, 		1200), // Barathos | Baroness
				new Bloodlurker(			21.0f, 	0, 		1350), // Abyss | Crafting
				new CandyBlade(				23.0f, 	0, 		1450), // Candyland | Gingerbread House Loot
				new CoralGreatblade(		24.5f, 	0, 		1800), // LBorean | Crafting
				new CottonCrusher(			24.0f,	0, 		1600), // Candyland | Cotton Candor
				new CreepoidGreatblade(		19.0f, 	0, 		1080), // Creeponia | Crafting
				new CrystalGreatblade(		22.0f, 	0, 		1480), // Crystevia | Crystal Trader
				new ErebonScythe(			19.0f, 	-3.0D, 	1750), // Ancient Cavern | Penumbra
				new GodsGreatblade(			29.5f, 	0, 		2000), // Shyrelands | Xxeus
				new GoofyGreatblade(		22.0f, 	0, 		1300), // Celeve | Crafting
				new HauntedGreatblade(		22.0f, 	0, 		1370), // Mysterium | Haunted Castle
				new KnightsGuard(			26.5, 	0, 		2050), // Shyrelands | Crafting
				new LelyetianGreatblade(	18.5f, 	0, 		1100), // Lelyetia | Crafting
				new LunarGreatblade(		25.0f, 	0, 		1850), // Lunalus | Lunar Village Loot
				new LuxonScythe(			17.5f, 	-2.8D, 	1750), // Ancient Cavern | Horon
				new LyonicGreatblade(		20.0f, 	-3.1D, 	1500), // Iromine | Silverfoot
				new MillenniumGreatblade(	26.5f, 	0, 		2050), // UPGRADE KIT
				new NoxiousGreatblade(		23.0f, 	0, 		1580), // Vox Ponds | Voxxulon
				new PlutonScythe(			19.0f, 	-3.0D, 	1750), // Ancient Cavern | Goldorth
				new PrimordialGreatblade(	25.5f, 	0, 		1900), // Dustopiaend | Primordial Five
				new RosidianGreatblade(		22.5f, 	0, 		1470), // Gardencia | Floro Castle Loot
				new RoyalGreatblade(		19.0f, 	0, 		1130), // Barathos | Crafting
				new RunicGreatblade(		24.5f, 	0, 		1800), // Runandor | Crafting
				new SelyanScythe(			19.0f, 	-3.0D, 	1750), // Ancient Cavern | Coniferon
				new ShroomicGreatblade(		21.5f, 	0, 		1300), // Mysterium | Crafting
				new ShyreSword(				26.0f, 	0, 		2000), // Shyrelands | Crafting
				new SubterraneanGreatblade(	21.5f, 	0, 		1160), // Deeplands | Kror
				new TidalGreatblade(		24.0f, 	0, 		1750), // Hauling
				new UnderworldGreatblade(	18.5f, 	0, 		1050) // Deeplands | Crafting
		);

		registerMauls(registry,
				new CoralstoneMaul(	26.5f, 	Enums.WeaponSpeed.THIRD.value, 	3.6D, 	1600), // LBorean | Crafting
				new CrystalMaul(	23.5f, 	Enums.WeaponSpeed.THIRD.value, 	3.1D, 	1400), // Crystevia | Crystal Trader
				new ElectronMaul(	25.0f, 	Enums.WeaponSpeed.THIRD.value, 	3.5D, 	1500), // Iromine | Mechbot
				new HorizonMaul(	23.0f, 	Enums.WeaponSpeed.THIRD.value, 	3.0D, 	1300), // Haven | Crafting
				new VulcammerMaul(	28.0f, 	Enums.WeaponSpeed.THIRD.value, 	3.5D, 	1750) // Immortallis | Token Collector
		);

		registerGuns(registry,
				new Abominator(				10.5f,	1180, 12, 	3.0f), // Greckon | Crafting
				new ApocoAssaultRifle(		22.5f, 	1480, 24, 	6.0f), // UPGRADE KIT
				new ApocoRifle(				10.0f, 	1490, 24, 	6.0f), // UPGRADE KIT
				new AquaMagnum(				7.5f, 	1520, 8, 	4.0f), // UPGRADE KIT
				new Artifact(				19.0f, 	1500, 20, 	3.0f), // UPGRADE KIT
				new Baronator(				12.0f, 	620, 	20, 	3.0f), // Barathos | Crafting
				new BayonetteRifle(			12.5f, 	630, 	20, 	4.0f), // Precasia | Crafting
				new BigTop(					12.5f, 	920, 	16, 	4.0f), // Celeve | Crafting
				new BloodIron(				24.5f, 	870, 	32, 	14.0f), // Abyss | Crafting
				new ChainWrecker(			6.0f, 	960, 	8, 	2.0f), // Iromine | Crafting
				new ChilliChugger(			12.0f, 	890, 	16, 	2.0f), // Gardencia | Crafting
				new Clownershot(			8.0f, 	1470, 16, 	7.0f), // UPGRADE KIT
				new Construct(				9.5f, 	910, 	12, 	4.0f), // Crystevia | Crafting
				new CoralClogger(			33.0f, 	500, 	40, 	15.0f), // TODO LBorean Minigame
				new CoreRifle(				7.5f, 	630, 	12, 	3.0f), // Lelyetia | Crafting
				new CrystalCarver(			12.0f, 	870, 	16, 	5.0f), // Crystevia | Crafting
				new Cyclone(				9.5f, 	1100, 12, 	3.0f), // Celeve | Gyro
				new Darkener(				11.5f, 	1510, 12, 	7.0f), // UPGRADE KIT
				new DartGun(				10.0f, 	360, 	20, 	0.2f), // Overworld | Amphibiyte
				new DestructionRifle(		12.5f, 	610, 	20, 	5.0f), // Deeplands | Crafting
				new DischargeRifle(			5.0f, 	570, 	24, 	6.0f), // Creeponia | Crafting
				new Draco(					15.0f, 	1390, 16, 	2.0f), // LBorean | Dracyon
				new Dragilator(				6.5f, 	1120, 8, 	4.0f), // Haven | Red Guardian
				new Dustometer(				21.0f, 	1180, 24, 	7.0f), // Dustopia | Crafting
				new EchoGull(				17.5f, 	580, 	28, 	6.0f), // Barathos | Crafting
				new Electinator(			6.5f, 	1120, 8, 	4.0f), // Haven | Yellow Guardian
				new FlameWrecker(			7.5f, 	1530, 8, 	2.0f), // UPGRADE KIT
				new FlamingFury(			20.5f, 	860, 	28, 	4.0f), // The End | Ender Dragon
				new FloroRifle(				15.0f, 	1500, 16, 	4.0f), // UPGRADE KIT
				new FlowersFury(			13.0f, 	1100,	16, 	5.0f), // Gardencia | Vinocorne
				new Frosticator(			6.5f, 	1120, 8, 	4.0f), // Haven | Blue Guardian
				new Gardener(				7.0f, 	630, 	12, 	2.0f), // Overworld | Farming
				new GaugeRifle(				22.5f, 	590, 	36, 	8.0f), // Lelyetia | Lelyetian Trader
				new Germinator(				6.5f, 	1120, 8, 	4.0f), // Haven | Green Guardian
				new GoldenFury(				26.0f, 	1520, 28, 	4.0f), // UPGRADE KIT
				new HappyHaunter(			11.5f, 	1510, 12, 	3.0f), // UPGRADE KIT
				new HaunterRifle(			11.0f, 	1420, 12, 	3.0f), // Greckon | Bane
				new HeatWave(				23.5f, 	1470, 24, 	5.0f), // UPGRADE KIT
				new Hiver(					11.5f, 	1490, 12, 	4.0f), // UPGRADE KIT
				new HotShot(				14.5f, 	580, 	24, 	5.0f), // Nether | Crafting
				new HuntersRifle(			21.5f, 	900, 	28, 	4.0f), // Vox Ponds | Crafting
				new Iominator(				11.5f, 	1500, 12, 	3.0f), // UPGRADE KIT
				new IonRevolver(			9.5f, 	930, 	12, 	3.0f), // Crystevia | Crafting
				new IroRifle(				12.5f, 	910, 	16, 	4.0f), // Iromine | Crafting
				new Krilinator(				15.0f, 	900, 	20, 	4.0f), // Mysterium | Crafting
				new LightIron(				30.5f, 	1470, 32, 	14.0f), // UPGRADE KIT
				new LunarAssaultRifle(		11.5f, 	1430, 12, 	5.0f), // Lunalus | Visualent
				new MechanicalAssaultRifle(	18.5f, 	940, 	24, 	6.0f), // Iromine | Repair
				new Megagun(				4.0f, 	1530, 4, 	3.0f), // UPGRADE KIT
				new Miasma(					9.0f, 	940, 	12, 	3.0f), // Gardencia | Crafting
				new Minigun(				2.5f, 	670, 	4, 	3.0f), // Lelyetia | Lelyetian Tower
				new MintMagnum(				6.0f, 	900, 	8, 	4.0f), // Candyland | Crafting
				new MK(						14.5f, 	1240, 16, 	3.0f), // LBorean | Aquatic Castle
				new MKFung(					15.0f, 	1490, 16, 	3.0f), // UPGRADE KIT
				new NethenetteRifle(		18.5f, 	1500, 20, 	4.0f), // UPGRADE KIT
				new NethengeicSlugger(		22.5f, 	780, 	32, 	9.0f), // Nether | Nethengeic Wither
				new Overshot(				6.5f, 	990, 	16, 	7.0f), // Iromine | Enforcer
				new PrecasianSlugger(		29.5f, 	1490, 32, 	9.0f), // UPGRADE KIT
				new Predator(				25.5f, 	1480, 28, 	6.0f), // UPGRADE KIT
				new Predigun(				4.0f, 	1530, 4, 	3.0f), // UPGRADE KIT
				new Pulsator(				25.0f, 	1190, 28, 	6.0f), // Lunalus | Crafting
				new PurityRifle(			18.0f, 	1200, 20, 	3.0f), // Runandor | Crafting
				new RockerRifle(			10.0f, 	630, 	16, 	6.0f), // Deeplands | Deep Case
				new Roulette(				22.5f, 	1470, 24, 	8.0f), // UPGRADE KIT
				new ShoeFlinger(			24.0f, 	400, 	24, 	7.0f), // SPECIAL | Crafting
				new Skullette(				23.5f, 	1580, 24, 	8.0f), // UPGRADE KIT
				new Skullifact(				19.5f, 	1570, 20, 	3.0f), // UPGRADE KIT
				new Spectacle(				11.0f, 	880, 	16, 	3.0f), // Celeve | Jumbo
				new SpineGun(				11.5f, 	1510, 12, 	7.0f), // UPGRADE KIT
				new SquadGun(				10.5f, 	1210, 12, 	7.0f), // Dustopia | Crafting
				new Stampede(				12.0f, 	300, 	24, 	8.0f), // Overworld | Crafting
				new Stormer(				15.0f, 	900, 	20, 	3.0f), // Candyland | Crafting
				new Sublimus(				11.0f, 	1530,	12, 	5.0f), // Shyrelands | Crafting
				new TigerTommy(				11.5f, 	1490, 12, 	7.0f), // UPGRADE KIT
				new Tommy(					6.0f, 	330, 	12, 	7.0f), // Overworld | Crafting
				new VileVanquisher(			11.0f, 	930, 	16, 	3.0f), // Vox Ponds | Crafting
				new WartGun(				16.5f, 	580, 	28, 	1.0f), // Nether | Crafting
				new Wrecker(				9.5f, 	920, 	12, 	7.0f) // Candyland | Crafting
		);

		registerShotguns(registry,
				new Abyssro(			18.5f, 	2, 	1100, 46, 	0.3f, 	18.0f), // UPGRADE KIT
				new Amplifier(			9.0f, 	5, 	1100, 56, 	0.2f, 	25.0f), // Shyrelands | Crafting
				new BlastBarrel(		13.0f, 	3, 	740, 	62, 	0.4f, 	23.0f), // Haven | Dawnlight Dungeon
				new BlueBarrel(			14.5f, 	3, 	1120, 54, 	0.4f, 	16.0f), // UPGRADE KIT Seaside
				new Boulder(			11.5f, 	4,	1080, 58, 	0.4f, 	30.0f), // UPGRADE KIT
				new BrownBlaster(		6.0f, 	3, 	300, 	54, 	0.4f, 	16.0f), // Overworld | Crafting
				new Demolisher(			8.5f, 	4, 	730, 	58, 	0.4f, 	30.0f), // Iromine | Professor
				new DestructionShotgun(	10.5f, 	3, 	690, 	56, 	0.4f, 	14.0f), // Celeve | Crafting
				new DischargeShotgun(	0.0f, 	4, 	160, 	55, 	0f, 		15.0f), // TODO Creeponia Minigame
				new Gimmick(			4.5f, 	10, 	800, 	68, 	0.2f, 	28.0f), // Celeve | Gyro
				new GingerBlaster(		7.0f, 	5, 	720, 	60, 	0.4f, 	19.0f), // Candyland | Crafting
				new LongShot(			15.5f, 	2, 	730, 	54, 	0.2f, 	12.0f), // Gardencia | Crafting
				new Mechyro(			13.0f, 	2, 	720, 	46, 	0.3f, 	18.0f), // Iromine | Professor Trade
				new PurityShotgun(		15.0f, 	3, 	1090, 56, 	0.4f, 	14.0f), // UPGRADE KIT Runic
				new PurplePunisher(		20.5f, 	2, 	1120, 52, 	0.785f, 	23.0f), // UPGRADE KIT Haunted
				new RedRocket(			11.5f, 	2, 	500, 	52, 	0.785f, 	23.0f), // Barathos | Crafting
				new Vivo(				7.0f, 	3, 	710, 	36, 	0.3f, 	8.0f) // Iromine | Crafting
		);

		registerSnipers(registry,
				new BaronSSR(		35.0f, 		240, 	88, 	20.0f), // Barathos | Baroness
				new BayonetteSR(	33.0f, 		300, 	86, 	18.0f), // Mysterium | Crafting
				new BoltRifle(		30.0f, 		100, 	100, 	26.0f), // Overworld | Crafting
				new CamoRifle(		31.5f, 		150, 	84, 	19.0f), // Precasia | Crafting
				new ClownCracker(	33.5f, 		305, 	84, 	17.0f), // Celeve | Crafting
				new Clownimator(	49.5f, 		545, 	95, 	18.0f), // UPGRADE KIT
				new Crystaneer(		37.5f, 		345, 	88, 	21.0f), // Crystevia | Crafting
				new DarkBeast(		50.5f, 		560, 	96, 	25.0f), // UPGRADE KIT
				new Deadlock(		25.5f, 		120, 	90, 	25.0f), // Overworld | Crafting
				new Decimator(		33.0f, 		180, 	95, 	18.0f), // Lelyetia | Crafting
				new DischargeSniper(10.0f, 		540, 	24, 	11.0f), // UPGRADE KIT
				new DualSight(		38.5f, 		350, 	91, 	30.0f), // Crystevia | Crafting
				new Duster(			21.0f, 		170, 	60, 	18.0f), // Barathos | Crafting
				new Floro500(		37.0f, 		550, 	70, 	13.0f), // UPGRADE KIT
				new HeadHunter(		41.5f, 		380, 	92, 	23.0f), // Haven | Rock Rider
				new HiveCracker(	44.0f, 		555, 	84, 	17.0f), // UPGRADE KIT
				new Ka500(			30.0f, 		360, 	70, 	13.0f), // Gardencia | Crafting
				new MarkMaker(		36.5f, 		530, 	70, 	13.0f), // UPGRADE KIT
				new Mineral(		58.5f, 		560, 	112, 	22.0f), // UPGRADE KIT
				new Monster(		38.0f, 		250, 	96, 	25.0f), // Lelyetia | Graw
				new MoonMaker(		38.5f, 		570, 	70, 	13.0f), // UPGRADE KIT
				new RosidRifle(		45.5f, 		405, 	102, 	19.0f), // Gardencia | Vinocorne
				new Sabbath(		44.0f, 		450, 	97, 	22.0f), // Greckon | Crafting
				new SludgeSniper(	37.0f, 		350, 	87, 	18.0f), // Vox Ponds | Crafting
				new SweetTooth(		32.5f, 		460, 	77, 	17.0f), // Candyland | Crafting
				new Terminator(		53.0f, 		470, 	112, 	22.0f), // Dustopia | Crafting
				new Viper1(			30.0f, 		185, 	89, 	17.0f) // Deeplands | Crafting
		);

		registerCannons(registry,
				new AncientBomber(		23.5f, 		855, 	28, 	14.0f), // UPGRADE KIT
				new AncientDischarger(	0.0f, 		850, 	20, 	6.0f), // UPGRADE KIT
				new AquaCannon(			14.0f, 		300, 	35, 	5.0f), // TODO LBorean Minigame
				new BalloonBomber(		12.5f, 		505, 	24, 	7.0f), // Celeve | Toy Merchant
				new BigBlast(			28.0f, 		550, 	60, 	19.0f), // Abyss | Shadowlord
				new BlastCannon(		17.0f, 		510, 	27, 	17.0f), // Crystevia | Crafting
				new BlissfulBlast(		33.5f, 		835, 	60, 	19.0f), // UPGRADE KIT
				new BombLauncher(		23.0f, 		840, 	27, 	17.0f), // UPGRADE KIT
				new BoomBoom(			18.5f, 		390, 	36, 	11.0f), // Creeponia | Crafting
				new BoomCannon(			17.5f, 		510, 	28, 	14.0f), // Iromine | Crafting
				new BoulderBomber(		18.5f,		475, 	62, 	20.0f), // Deeplands | Kror
				new BozoBlaster(		12.5f, 		505, 	20, 	11.0f), // Celeve | Crafting
				new BulbCannon(			25.0f, 		860, 	30, 	8.0f), // UPGRADE KIT
				new CarrotCannon(		10.0f, 		520, 	16, 	8.0f), // Gardencia | Crafting
				new ClownCannon(		22.5f, 		570, 	32, 	10.0f), // Celeve | Gyro
				new ClownoPulse(		35.5f, 		845, 	42, 	11.0f), // UPGRADE KIT
				new CoralCannon(		12.0f, 		300, 	15, 	5.0f), // TODO LBorean Minigame
				new DischargeCannon(	0.0f, 		400, 	20, 	6.0f), // TODO Creeponia Minigame
				new EnergyCannon(		18.5f, 		610, 	25, 	3.0f), // Runandor | Crafting
				new ErebonStickler(		35.0f, 		750, 	48, 	11.0f), // Ancient Cavern | Penumbra
				new FloroRPG(			14.0f, 		830, 	14, 	7.0f), // UPGRADE KIT
				new FlowerCannon(		14.5f, 		510, 	23, 	17.0f), // Gardencia | Crafting
				new FungalCannon(		19.5f, 		850, 	23, 	17.0f), // UPGRADE KIT
				new GhastBlaster(		20.0f, 		600, 	28, 	16.0f), // Greckon | Crafting
				new GhoulCannon(		25.5f, 		590, 	34, 	18.0f), // Greckon | Crafting
				new GigaCannon(			25.5f, 		700, 	30, 	6.0f), // Shyrelands | Shyre Archer
				new GolderBomber(		26.0f, 		840, 	62, 	20.0f), // UPGRADE KIT
				new HiveBlaster(		20.5f, 		850, 	24, 	13.0f), // UPGRADE KIT
				new HiveHowitzer(		13.0f, 		375, 	24, 	6.5f), // Barathos | Crafting
				new IroCannon(			25.5f, 		580, 	36, 	10.0f), // Iromine | Silverfoot
				new JackFunger(			17.0f, 		860, 	20, 	6.0f), // UPGRADE KIT
				new JackRocker(			10.5f, 		400, 	20, 	6.0f), // Deeplands | Crafting
				new LuxonStickler(		35.0f, 		750, 	48, 	11.0f), // Ancient Cavern | Horon
				new MechaCannon(		26.5f, 		525, 	42, 	11.0f), // Iromine | Repairing
				new MiniCannon(			15.0f, 		415, 	30, 	3.0f), // Precasia | Diocus
				new MissileMaker(		0.0f, 		495, 	60, 	10.0f), // Iromine | Crafting
				new MoonCannon(			21.0f, 		855, 	25, 	3.0f), // UPGRADE KIT
				new PlutonStickler(		35.0f, 		750, 	48, 	11.0f), // Ancient Cavern | Goldorth
				new PredatorianBlaster(	23.5f, 		845, 	28, 	16.0f), // UPGRADE KIT
				new PulseCannon(		26.0f, 		510, 	42, 	11.0f), // Crystevia | Crafting
				new RPG(				14.0f, 		320, 	50, 	24.0f), // Overworld | Crafting
				new SelyanStickler(		35.0f, 		750, 	48, 	11.0f), // Ancient Cavern | Coniferon
				new ShadowBlaster(		15.0f, 		515, 	24, 	13.0f), // Abyss | Crafting
				new ShyreBlaster(		25.5f, 		850, 	30, 	12.0f), // Shyrelands | Crafting
				new SmileBlaster(		20.5f, 		840, 	24, 	13.0f), // UPGRADE KIT
				new SuperCannon(		18.5f, 		510, 	30, 	4.0f), // Iromine | Professor
				new UltraCannon(		22.5f, 		605, 	30, 	5.0f), // Lunalus | Zal Spellbinder
				new VoxCannon(			15.0f, 		440, 	26, 	13.0f), // Vox Ponds | Nightwing
				new WaterBalloonBomber(	20.5f, 		855, 	24, 	7.0f), // UPGRADE KIT
				new WitherCannon(		17.0f, 		460, 	30, 	8.0f) // Nether | Wither
		);

		registerThrownWeapons(registry,
				new Grenade(),
				new SliceStar(),
				new Chakram(),
				new GooBall(),
				new Vulkram(),
				new Hellfire(),
				new RunicBomb()
		);

		registerVulcanes(registry,
				new Vulcane(			15, 	50), // Overworld | Crafting
				new BattleVulcane(		20, 	75), // Immortallis | Token Collector
				new EqualityVulcane(	20, 	75), // Immortallis | Token Collector
				new FireVulcane(		20, 	75), // Immortallis | Token Collector
				new ImpairmentVulcane(	20, 	75), // Immortallis | Token Collector
				new PoisonVulcane(		20, 	75), // Immortallis | Token Collector
				new PowerVulcane(		20, 	75), // Immortallis | Token Collector
				new WitherVulcane(		20, 	75) // Immortallis | Token Collector
		);

		registerBows(registry,
				new AlacrityBow(	9.5f, 		1f, 		600), // Overworld | Crafting
				new AncientBow(		22.0f, 	1f, 		1510), // UPGRADE KIT Ancient
				new AtlanticBow(	24.5f, 	0.9f, 	1480), // UPGRADE KIT Seaside
				new BaronBow(		12.0f, 	1.1f, 	3000), // Barathos | Crafting
				new BoreicBow(		14.0f, 	1.3f, 	1190), // LBorean | Amphibior
				new DaybreakerBow(	17.5f, 	1f, 		1180), // Dustopia | Primordial Merchant
				new DeepBow(		15.5f, 	0.85f, 	700), // Deeplands | Deep Case
				new ExplosiveBow(	17.0f, 	0.8f, 	900), // Nether | King BamBamBam
				new HauntedBow(		17.0f,		1f, 		920),	// Greckon | Crafting
				new IceBow(			8.5f, 		1f, 		580), // Overworld | Crafting
				new InfernalBow(	11.5f, 	1f, 		710), // Barathos | Crafting
				new JusticeBow(		14.5f,		1f, 		920), // Haven | Crafting
				new LunarBow(		17.5f, 	1f, 		900), // Lunalus | Crafting
				new MechaBow(		20.5f,		0.75f, 	930), // Iromine | Repairing
				new NightmareBow(	13.5f,		1f, 		890), // Abyss | Crafting
				new PoisonBow(		15.0f, 	1f, 		950), // Mysterium | Mushroom Spider
				new PredatiousBow(	14.0f, 	0.9f, 	690), // Precasia | Crafting
				new PrimordialBow(	18.5f,		1f, 		1350), // Dustopia | Primordial Five
				new RosidianBow(	15.5f, 	1f, 		900), // Gardencia | Crafting
				new RunicBow(		20.5f,		1f, 		1320), // Runandor | Clunkhead
				new ScreamerBow(	12.0f, 	1f, 		665), // Deeplands | Crafting
				new ShyregemBow(	7.5f, 		3.0f, 	1500), // Shyrelands | Crafting
				new SkeletalBow(	8.5f, 		1.5f, 	720), // Precasia | Crafting
				new SkydriverBow(	14.5f, 	1f, 		850), // Lelyetia | Graw
				new Slingshot(		17.0f, 	1f, 		1200), // Lunalus | Lunarade Vendor
				new SoulfireBow(	23.5f, 	0.75f, 	1100), // Runandor | Crafting
				new SpectralBow(	12.0f, 	1.3f, 	900), // Crystevia | Crafting
				new SpeedBow(		8.0f, 		2, 		930), // Haven | Crafting
				new SunshineBow(	27.0f, 	0.8f, 	1530), // Shyrelands | Shyre Archer
				new ToxinBow(		14.0f, 	0.85f, 	900), // Vox Ponds | Crafting
				new VoidBow(		9.0f, 		1.2f,	 	600), // Overworld | Crafting
				new WeakenBow(		12.5f, 	1f, 		700), // Lelyetia | Crafting
				new WitherBow(		13.5f, 	1f, 		835) // Nether | Wither
		);

		registerArcherguns(registry,
				new CoralArchergun(		13.0f, 	600, 	12, 	3.0f), // TODO LBorean Minigame
				new LunarArchergun(		10.5f, 	1530, 12, 	3.0f), // Lunalus | Crafting
				new MechaArchergun(		11.5f, 	1300, 16, 	3.0f), // Iromine | Repairing Incomplete
				new PyroArchergun(		7.5f, 	950, 	12, 	3.0f), // Nether | Skeletal Cowman
				new RosidianArchergun(	9.0f, 	1280, 12, 	3.0f), // Gardencia | Garden Castle
				new SkeletalArchergun(	8.5f, 	1100, 12, 	3.0f), // Precasia | Skeletron
				new SpectralArchergun(	6.5f, 	1600, 8, 		3.0f), // Runandor | Crafting
				new TrollsArchergun(	9.5f,		800, 	16, 	3.0f), // Overworld | Smash
				new ViralArchergun(		9.0f, 	1250, 12, 	3.0f)  // Vox Ponds | Crafting
		);

		registerStaves(registry,
				new AquaticStaff(		1220), // LBorean | Crafting
				new AtlanticStaff(		1250), // LBorean | Mermage
				new BaronStaff(			1200), // Barathos | Crafting
				new CandyStaff(			950), // Candyland | Cane Bug
				new CelestialStaff(		1150), // Haven | Crafting
				new ConcussionStaff(	1150), // Creeponia | CREEP
				new CoralStaff(			950), // Overworld | Corallus
				new CrystalStaff(		1230), // Crystevia | Crystocore
				new CrystikStaff(		1140), // Crystevia | Crafting
				new CrystonStaff(		1240), // Crystevia | Crystocore
				new DestructionStaff(	1170), // Iromine | Crafting
				new EmberStaff(			1400), // Nether | Crafting
				new EverfightStaff(		1050), // TODO Random Event Minigame
				new EvermightStaff(		1050), // TODO Random Event Minigame
				new FireStaff(			850), // Overworld | Crafting
				new FireflyStaff(		1230), // Barathos | Baron Castle
				new FirestormStaff(		990), // Abyss | Crafting
				new FungalStaff(		1130), // Mysterium | King Shroomus
				new GhoulStaff(			400), // Greckon | Bane
				new HauntersStaff(		1225), // Greckon |  Bane
				new HiveStaff(			1130), // Barathos | Hive King
				new JokerStaff(			300), // Celeve | Gyro
				new KaiyuStaff(			900), // Precasia | Kaiyu
				new LightningStaff(		1070), // UPGRADE KIT Predator
				new Lightshine(			700), // Shyrelands | Lightwalker
				new LunarStaff(			1250), // Lunalus | Visualent
				new LyonicStaff(		1100), // Iromine | Crafting
				new MechaStaff(			1110), // Iromine | Repairing
				new MeteorStaff(		1190), // Immortallis | Token Collector
				new MoonlightStaff(		1130), // Greckon | Crafting
				new NatureStaff(		1450), // Precasia | Diocus
				new NightmareStaff(		1200), // End | Ender Dragon
				new NoxiousStaff(		1210), // Vox Ponds | Voxxulon
				new PhantomStaff(		1060), // Mysterium | Phantom
				new PoisonStaff(		850), // Overworld | Crafting
				new PowerStaff(			1010), // Deeplands | Crafting
				new PrimordialStaff(	1230), // Dustopia | Primordial Five
				new ReefStaff(			1230), // LBorean | Hydrolisk
				new RejuvenationStaff(	870), // Gardencia | Crafting
				new RosidianStaff(		1180), // Gardencia | Crafting
				new RunicStaff(			1000), // Runandor | Runic Guardian
				new ShadowlordStaff(	1200), // Abyss | Shadowlord
				new ShowStaff(			900), // Celeve | Crafting
				new ShyreStaff(			1380), // Shyrelands | Crafting
				new SkyStaff(			1480), // Haven | Crafting
				new StrikerStaff(		850), // Overworld | Crafting
				new SunStaff(			960), // Lunalus | Inmate-X
				new SurgeStaff(			1270), // Celeve | Crafting
				new TangleStaff(		1140), // Gardencia | Vine Wizard
				new UltimatumStaff(		750), // Shyrelands | Xxeus
				new UnderworldStaff(	1140), // Abyss | Crafting
				new WarlockStaff(		1050), // Barathos | Baroness
				new WaterStaff(			850), // Overworld | Crafting
				new WebStaff(			1420), // Lelyetia | Paravite
				new WindStaff(			850), /// Overworld | Crafting
				new WitherStaff(		850), // Overworld | Crafting
				new WizardsStaff(		800) // Runandor | Spectral Wizard
		);

		registerBlasters(registry,
				new ApocoShower(		0.0f, 	3760, 10, 	80f), // UPGRADE KIT
				new Atomizer(			11.0f, 	3150, 8, 		15f), // Crafting | Runandor
				new Beamer(				1.0f, 	3240, 1, 		3f), // LBorean | Aquatic Castle
				new BlastChiller(		4.0f, 	1750, 6, 		20f), // Overworld | Crafting
				new BloodDrainer(		0.1f, 	2750, 1, 		2.5f), // Abyss | Crafting
				new BoneBlaster(		6.0f, 	2430, 6, 		20f), // Precasia | Crafting
				new BubbleHorn(			11.0f, 	3200, 8, 		15.5f), // LBorean | Crafting
				new ColourCannon(		45.0f, 	1000, 40, 	110f), // Rare Table
				new ConfettiCannon(		0.0f, 	1000, 10, 	0f), // Overworld | Clown
				new ConfettiCluster(	0.0f, 	1500, 5, 		0f), // Celeve | Toy Merchant
				new DarkDestroyer(		0.0f, 	3180, 100, 	80f), // Dustopia | Merkyre
				new DarklyGuster(		8.0f, 	3790,	5, 	7f), // UPGRADE KIT
				new DeathRay(			19f, 		3840, 12, 	17f), // UPGRADE KIT
				new DoomBringer(		0.0f, 	2820, 3, 		15.5f), // Crystevia | Crafting
				new Eradicator(			1.0f, 	2790, 1, 		2f), // Vox Ponds | Crafting
				new ExperimentW801(		37.0f, 	5000, 50, 	90.0f), // Alien Orb
				new Flowercorne(		2.5f, 	2910, 4, 		20f), // Gardencia | Pod Plant
				new Fragment(			3.0f, 	3830,	2, 	2.5f), // UPGRADE KIT
				new Froster(			3.5f, 	2800, 3, 		7.5f), // Candyland | Crafting
				new GasBlaster(			0.0f, 	2860, 1, 		0.5f), // Gardencia | Crafting
				new GhoulGasser(		0.0f, 	3210, 1, 		1f), // Greckon | Crafting
				new GoldBringer(		0.0f, 	3830, 3, 		12.5f), // UPGRADE KIT
				new GravityBlaster(		4.0f, 	600, 	1, 	120f), // Haven | Crafting
				new HellHorn(			12.5f, 	3800, 8, 		11.5f), // UPGRADE KIT
				new IllusionRevolver(	14.0f, 	3200, 10, 	20f), // Runandor | Crafting
				new IllusionSMG(		4.0f, 	3240, 3, 		7f), // Abyss | Elusive
				new IonBlaster(			13.0f, 	2810, 11, 	28.5f),  // Crystevia | Crafting
				new IroMiner(			4.0f, 	3060, 4, 		7f), // Iromine | Mechbot
				new LaserBlaster(		1.2f, 	2840, 1, 		2.5f), // Mysterium | Gorb Arms Dealer
				new LightBlaster(		22.5f, 	3810, 14, 	20.5f), // UPGRADE KIT
				new LightSpark(			0.0f, 	7, 	1, 	0f), // UPGRADE KIT
				new LunaBlaster(		3.0f, 	3200, 2, 		4f), // Lunalus | Crafting
				new MechaBlaster(		36.0f, 	2770, 30, 	80f), // Iromine | Crafting
				new MindBlaster(		19.5f, 	3180, 14, 	27.5f), // Runandor | Crafting
				new MoonDestroyer(		0.0f, 	3750, 100, 	60f), // UPGRADE KIT
				new MoonShiner(			3.5f, 	1810, 5, 		17.5f), // Overworld | Crafting
				new Odious(				1.0f, 	3190, 1, 		1.5f), // Dustopia | Crafting
				new Orbocron(			18.5f,  	3230, 13, 	26.5f), // Lunalus |  Crafting
				new Paralyzer(			1.5f, 	3900, 1, 		1.5f), // Shyrelands | Shyre Troll
				new PartyPopper(		1.5f, 	2810, 1, 		3f), // Celeve | Crafting
				new PoisonPlunger(		0.0f, 	2600, 20, 	50f), // Mysterium | Crafting
				new PowerRay(			12.5f, 	2490, 12, 	38.5f), // Lelyetia | Zhinx
				new Proton(				2.5f, 	3000, 2, 		4.5f), // Crystevia | Crystocore
				new Reefer(				5.0f, 	3600, 4, 		7f), // TODO LBorean Minigame
				new Revolution(			0.0f, 	2800, 20, 	66f), // Iromine | Crafting
				new Seaocron(			20.5f, 	3800, 13, 	18.5f), // UPGRADE KIT
				new SkulloBlaster(		3.5f, 	3780, 2, 		3f), // UPGRADE KIT
				new SoulDrainer(		0.2f, 	3770, 1, 		2f), // UPGRADE KIT Runic
				new SoulSpark(			0.0f, 	5, 	1, 	0f), // Shyrelands | Luxocron
				new SoulStorm(			1.5f, 	3190, 1, 		2f), // Greckon | Crafting
				new SpiritShower(		0.0f, 	2870, 10, 	100f), // Abyss | Jawe
				new Swarmotron(			2.0f, 	2400, 12, 	33f),  // Barathos | Crafting
				new ToxicTerrorizer(	0.0f, 	2760, 7, 		22f), // Vox Ponds | Crafting
				new VortexBlaster(		0.0f, 	2870, 1, 		130f), // Haven | Dawnlight
				new WhimsyWinder(		6.0f, 	2830,	5, 	13f), // Celeve | Crafting
				new WithersWrath(		7.5f, 	800, 	7, 	21f) // Nether | Nethengeic Wither
		);
	}

	private static void registerSwords(IForgeRegistry<Item> registry, BaseSword... swords) {
		for (BaseSword sword : swords) {
			ItemRegister.registerItem(registry, sword, "weapons/swords/");
		}
	}

	private static void registerGreatblades(IForgeRegistry<Item> registry, BaseGreatblade... greatblades) {
		for (BaseGreatblade greatblade : greatblades) {
			ItemRegister.registerItem(registry, greatblade, "weapons/greatblades/");
		}
	}

	private static void registerMauls(IForgeRegistry<Item> registry, BaseMaul... mauls) {
		for (BaseMaul maul : mauls) {
			ItemRegister.registerItem(registry, maul, "weapons/mauls/");
		}
	}
	
	private static void registerGuns(IForgeRegistry<Item> registry, BaseGun... guns) {
		for (BaseGun gun : guns) {
			ItemRegister.registerItem(registry, gun, "weapons/guns/");
		}
	}

	private static void registerShotguns(IForgeRegistry<Item> registry, BaseShotgun... shotguns) {
		for (BaseShotgun shotgun : shotguns) {
			ItemRegister.registerItem(registry, shotgun, "weapons/shotguns/");
		}
	}

	private static void registerSnipers(IForgeRegistry<Item> registry, BaseSniper... snipers) {
		for (BaseSniper sniper : snipers) {
			ItemRegister.registerItem(registry, sniper, "weapons/snipers/");
		}
	}

	private static void registerCannons(IForgeRegistry<Item> registry, BaseCannon... cannons) {
		for (BaseCannon cannon : cannons) {
			ItemRegister.registerItem(registry, cannon, "weapons/cannons/");
		}
	}

	private static void registerThrownWeapons(IForgeRegistry<Item> registry, BaseThrownWeapon... thrownWeapons) {
		for (BaseThrownWeapon thrownWeapon : thrownWeapons) {
			ItemRegister.registerItem(registry, thrownWeapon, "weapons/thrown/");
		}
	}

	private static void registerVulcanes(IForgeRegistry<Item> registry, BaseVulcane... vulcanes) {
		for (BaseVulcane vulcane : vulcanes) {
			ItemRegister.registerItem(registry, vulcane, "weapons/vulcanes/");
		}
	}

	private static void registerBows(IForgeRegistry<Item> registry, BaseBow... bows) {
		for (BaseBow bow : bows) {
			ItemRegister.registerItem(registry, bow, "weapons/bows/");
		}
	}

	private static void registerArcherguns(IForgeRegistry<Item> registry, BaseArchergun... archerguns) {
		for (BaseArchergun archergun : archerguns) {
			ItemRegister.registerItem(registry, archergun, "weapons/archerguns/");
		}
	}

	private static void registerStaves(IForgeRegistry<Item> registry, BaseStaff... staffs) {
		for (BaseStaff staff : staffs) {
			ItemRegister.registerItem(registry, staff, "weapons/staves/");
		}
	}

	private static void registerBlasters(IForgeRegistry<Item> registry, BaseBlaster... blasters) {
		for (BaseBlaster blaster : blasters) {
			ItemRegister.registerItem(registry, blaster, "weapons/blasters/");
		}
	}

	@SubscribeEvent
	public static void remapMissing(final RegistryEvent.MissingMappings<Item> ev) {
		for (RegistryEvent.MissingMappings.Mapping<Item> map : ev.getMappings()) {
			if (map.key.toString().equals("aoa3:millenium_greatblade")) {
				map.remap(MILLENNIUM_GREATBLADE);
			}
			else if (map.key.toString().equals("aoa3:grandsword")) {
				map.remap(BLOODLURKER);
			}
		}
	}

	public static void registerDispensables() {
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.HELLFIRE, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityHellfire(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.GRENADE, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityGrenade(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.CHAKRAM, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityChakram(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.GOO_BALL, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityGooBall(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.RUNIC_BOMB, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityRunicBomb(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.VULKRAM, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityVulkram(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.SLICE_STAR, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntitySliceStar(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.HOLLY_ARROW, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				EntityHollyArrow arrow = new EntityHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());

				arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				return arrow;
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.TIPPED_HOLLY_ARROW, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				EntityTippedHollyArrow arrow = new EntityTippedHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());

				arrow.setPotionEffect(stack);
				arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				return arrow;
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.SPECTRAL_HOLLY_ARROW, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				EntitySpectralHollyArrow arrow = new EntitySpectralHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());

				arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				return arrow;
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.FRAGMENTED_ANIMA_STONE, new Bootstrap.BehaviorDispenseOptional() {
			@Override
			protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				successful = true;

				World world = source.getWorld();
				BlockPos pos = source.getBlockPos().offset(source.getBlockState().getValue(BlockDispenser.FACING));

				if (ItemDye.applyBonemeal(stack, world, pos)) {
					if (!world.isRemote)
						world.playEvent(2005, pos, 0);
				}
				else {
					successful = false;
				}

				return stack;
			}
		});
	}

	@SuppressWarnings("ConstantConditions")
	@Nonnull
	private static <T> T ObjectHolder() {
		return null;
	}
}
