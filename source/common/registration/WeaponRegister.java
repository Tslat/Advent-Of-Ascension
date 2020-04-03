package net.tslat.aoa3.common.registration;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
	@GameRegistry.ObjectHolder("amethyst_sword")
	public static final BaseSword swordAmethyst = null;
	@GameRegistry.ObjectHolder("baron_sword")
	public static final BaseSword swordBaron = null;
	@GameRegistry.ObjectHolder("bloodfury")
	public static final BaseSword swordBloodfury = null;
	@GameRegistry.ObjectHolder("bloodstone_sword")
	public static final BaseSword swordBloodstone = null;
	@GameRegistry.ObjectHolder("candlefire_sword")
	public static final BaseSword swordCandlefire = null;
	@GameRegistry.ObjectHolder("caramel_carver")
	public static final BaseSword swordCaramelCarver = null;
	@GameRegistry.ObjectHolder("coralstorm_sword")
	public static final BaseSword swordCoralstorm = null;
	@GameRegistry.ObjectHolder("creepified_sword")
	public static final BaseSword swordCreepified = null;
	@GameRegistry.ObjectHolder("crystallite_sword")
	public static final BaseSword swordCrystallite = null;
	@GameRegistry.ObjectHolder("emberstone_sword")
	public static final BaseSword swordEmberstone = null;
	@GameRegistry.ObjectHolder("explochron_sword")
	public static final BaseSword swordExplochron = null;
	@GameRegistry.ObjectHolder("fireborne_sword")
	public static final BaseSword swordFireborne = null;
	@GameRegistry.ObjectHolder("guardian_sword")
	public static final BaseSword swordGuardians = null;
	@GameRegistry.ObjectHolder("harvester_sword")
	public static final BaseSword swordHarvester = null;
	@GameRegistry.ObjectHolder("holy_sword")
	public static final BaseSword swordHoly = null;
	@GameRegistry.ObjectHolder("illusion_sword")
	public static final BaseSword swordIllusion = null;
	@GameRegistry.ObjectHolder("jade_sword")
	public static final BaseSword swordJade = null;
	@GameRegistry.ObjectHolder("legbone_sword")
	public static final BaseSword swordLegbone = null;
	@GameRegistry.ObjectHolder("lights_way")
	public static final BaseSword swordLightsWay = null;
	@GameRegistry.ObjectHolder("limonite_sword")
	public static final BaseSword swordLimonite = null;
	@GameRegistry.ObjectHolder("nethengeic_sword")
	public static final BaseSword swordNethengeic = null;
	@GameRegistry.ObjectHolder("primal_sword")
	public static final BaseSword swordPrimal = null;
	@GameRegistry.ObjectHolder("rockbasher_sword")
	public static final BaseSword swordRockbasher = null;
	@GameRegistry.ObjectHolder("rock_pick_sword")
	public static final BaseSword swordRockPick = null;
	@GameRegistry.ObjectHolder("rosidian_sword")
	public static final BaseSword swordRosidian = null;
	@GameRegistry.ObjectHolder("rosite_sword")
	public static final BaseSword swordRosite = null;
	@GameRegistry.ObjectHolder("runic_sword")
	public static final BaseSword swordRunic = null;
	@GameRegistry.ObjectHolder("sapphire_sword")
	public static final BaseSword swordSapphire = null;
	@GameRegistry.ObjectHolder("shadow_sword")
	public static final BaseSword swordShadow = null;
	@GameRegistry.ObjectHolder("shroomus_sword")
	public static final BaseSword swordShroomus = null;
	@GameRegistry.ObjectHolder("skeletal_sword")
	public static final BaseSword swordSkeletal = null;
	@GameRegistry.ObjectHolder("supremacy_sword")
	public static final BaseSword swordSupremacy = null;
	@GameRegistry.ObjectHolder("sweet_sword")
	public static final BaseSword swordSweet = null;
	@GameRegistry.ObjectHolder("troll_basher_axe")
	public static final BaseSword swordTrollBasherAxe = null;
	@GameRegistry.ObjectHolder("ultraflame")
	public static final BaseSword swordUltraflame = null;
	@GameRegistry.ObjectHolder("void_sword")
	public static final BaseSword swordVoid = null;

	@GameRegistry.ObjectHolder("baron_greatblade")
	public static final BaseGreatblade greatbladeBaron = null;
	@GameRegistry.ObjectHolder("bloodlurker")
	public static final BaseGreatblade greatbladeBloodlurker = null;
	@GameRegistry.ObjectHolder("candy_blade")
	public static final BaseGreatblade greatbladeCandyBlade = null;
	@GameRegistry.ObjectHolder("coral_greatblade")
	public static final BaseGreatblade greatbladeCoral = null;
	@GameRegistry.ObjectHolder("cotton_crusher")
	public static final BaseGreatblade greatbladeCottonCrusher = null;
	@GameRegistry.ObjectHolder("creepoid_greatblade")
	public static final BaseGreatblade greatbladeCreepoid = null;
	@GameRegistry.ObjectHolder("crystal_greatblade")
	public static final BaseGreatblade greatbladeCrystal = null;
	@GameRegistry.ObjectHolder("erebon_scythe")
	public static final BaseGreatblade greatbladeErebonScythe = null;
	@GameRegistry.ObjectHolder("gods_greatblade")
	public static final BaseGreatblade greatbladeGodsGreatblade = null;
	@GameRegistry.ObjectHolder("goofy_greatblade")
	public static final BaseGreatblade greatbladeGoofy = null;
	@GameRegistry.ObjectHolder("haunted_greatblade")
	public static final BaseGreatblade greatbladeHaunted = null;
	@GameRegistry.ObjectHolder("knights_guard")
	public static final BaseGreatblade greatbladeKnightsGuard = null;
	@GameRegistry.ObjectHolder("lelyetian_greatblade")
	public static final BaseGreatblade greatbladeLelyetian = null;
	@GameRegistry.ObjectHolder("lunar_greatblade")
	public static final BaseGreatblade greatbladeLunar = null;
	@GameRegistry.ObjectHolder("luxon_scythe")
	public static final BaseGreatblade greatbladeLuxonScythe = null;
	@GameRegistry.ObjectHolder("lyonic_greatblade")
	public static final BaseGreatblade greatbladeLyonic = null;
	@GameRegistry.ObjectHolder("millennium_greatblade")
	public static final BaseGreatblade greatbladeMillennium = null;
	@GameRegistry.ObjectHolder("noxious_greatblade")
	public static final BaseGreatblade greatbladeNoxious = null;
	@GameRegistry.ObjectHolder("pluton_scythe")
	public static final BaseGreatblade greatbladePlutonScythe = null;
	@GameRegistry.ObjectHolder("primordial_greatblade")
	public static final BaseGreatblade greatbladePrimordial = null;
	@GameRegistry.ObjectHolder("rosidian_greatblade")
	public static final BaseGreatblade greatbladeRosidian = null;
	@GameRegistry.ObjectHolder("royal_greatblade")
	public static final BaseGreatblade greatbladeRoyal = null;
	@GameRegistry.ObjectHolder("runic_greatblade")
	public static final BaseGreatblade greatbladeRunic = null;
	@GameRegistry.ObjectHolder("selyan_scythe")
	public static final BaseGreatblade greatbladeSelyanScythe = null;
	@GameRegistry.ObjectHolder("shroomic_greatblade")
	public static final BaseGreatblade greatbladeShroomic = null;
	@GameRegistry.ObjectHolder("shyre_sword")
	public static final BaseGreatblade greatbladeShyreSword = null;
	@GameRegistry.ObjectHolder("subterranean_greatblade")
	public static final BaseGreatblade greatbladeSubterranean = null;
	@GameRegistry.ObjectHolder("tidal_greatblade")
	public static final BaseGreatblade greatbladeTidal = null;
	@GameRegistry.ObjectHolder("underworld_greatblade")
	public static final BaseGreatblade greatbladeUnderworld = null;

	@GameRegistry.ObjectHolder("coralstone_maul")
	public static final BaseMaul maulCoralstone = null;
	@GameRegistry.ObjectHolder("crystal_maul")
	public static final BaseMaul maulCrystal = null;
	@GameRegistry.ObjectHolder("electron_maul")
	public static final BaseMaul maulElectron = null;
	@GameRegistry.ObjectHolder("horizon_maul")
	public static final BaseMaul maulHorizon = null;
	@GameRegistry.ObjectHolder("vulcammer_maul")
	public static final BaseMaul maulVulcammer = null;

	@GameRegistry.ObjectHolder("abominator")
	public static final BaseGun gunAbominator = null;
	@GameRegistry.ObjectHolder("apoco_assault_rifle")
	public static final BaseGun gunApocoAssaultRifle = null;
	@GameRegistry.ObjectHolder("apoco_rifle")
	public static final BaseGun gunApocoRifle = null;
	@GameRegistry.ObjectHolder("aqua_magnum")
	public static final BaseGun gunAquaMagnum = null;
	@GameRegistry.ObjectHolder("artifact")
	public static final BaseGun gunArtifact = null;
	@GameRegistry.ObjectHolder("baronator")
	public static final BaseGun gunBaronator = null;
	@GameRegistry.ObjectHolder("bayonette_rifle")
	public static final BaseGun gunBayonetteRifle = null;
	@GameRegistry.ObjectHolder("big_top")
	public static final BaseGun gunBigTop = null;
	@GameRegistry.ObjectHolder("blood_iron")
	public static final BaseGun gunBloodIron = null;
	@GameRegistry.ObjectHolder("chain_wrecker")
	public static final BaseGun gunChainWrecker = null;
	@GameRegistry.ObjectHolder("chilli_chugger")
	public static final BaseGun gunChilliChugger = null;
	@GameRegistry.ObjectHolder("clownershot")
	public static final BaseGun gunClownershot = null;
	@GameRegistry.ObjectHolder("construct")
	public static final BaseGun gunConstruct = null;
	@GameRegistry.ObjectHolder("coral_clogger")
	public static final BaseGun gunCoralClogger = null;
	@GameRegistry.ObjectHolder("core_rifle")
	public static final BaseGun gunCoreRifle = null;
	@GameRegistry.ObjectHolder("crystal_carver")
	public static final BaseGun gunCrystalCarver = null;
	@GameRegistry.ObjectHolder("cyclone")
	public static final BaseGun gunCyclone = null;
	@GameRegistry.ObjectHolder("darkener")
	public static final BaseGun gunDarkener = null;
	@GameRegistry.ObjectHolder("dart_gun")
	public static final BaseGun gunDartGun = null;
	@GameRegistry.ObjectHolder("destruction_rifle")
	public static final BaseGun gunDestructionRifle = null;
	@GameRegistry.ObjectHolder("discharge_rifle")
	public static final BaseGun gunDischargeRifle = null;
	@GameRegistry.ObjectHolder("draco")
	public static final BaseGun gunDraco = null;
	@GameRegistry.ObjectHolder("dragilator")
	public static final BaseGun gunDragilator = null;
	@GameRegistry.ObjectHolder("dustometer")
	public static final BaseGun gunDustometer = null;
	@GameRegistry.ObjectHolder("echo_gull")
	public static final BaseGun gunEchoGull = null;
	@GameRegistry.ObjectHolder("electinator")
	public static final BaseGun gunElectinator = null;
	@GameRegistry.ObjectHolder("flame_wrecker")
	public static final BaseGun gunFlameWrecker = null;
	@GameRegistry.ObjectHolder("flaming_fury")
	public static final BaseGun gunFlamingFury = null;
	@GameRegistry.ObjectHolder("floro_rifle")
	public static final BaseGun gunFloroRifle = null;
	@GameRegistry.ObjectHolder("flowers_fury")
	public static final BaseGun gunFlowersFury = null;
	@GameRegistry.ObjectHolder("frosticator")
	public static final BaseGun gunFrosticator = null;
	@GameRegistry.ObjectHolder("gardener")
	public static final BaseGun gunGardener = null;
	@GameRegistry.ObjectHolder("gauge_rifle")
	public static final BaseGun gunGaugeRifle = null;
	@GameRegistry.ObjectHolder("germinator")
	public static final BaseGun gunGerminator = null;
	@GameRegistry.ObjectHolder("golden_fury")
	public static final BaseGun gunGoldenFury = null;
	@GameRegistry.ObjectHolder("happy_haunter")
	public static final BaseGun gunHappyHaunter = null;
	@GameRegistry.ObjectHolder("haunter_rifle")
	public static final BaseGun gunHaunterRifle = null;
	@GameRegistry.ObjectHolder("heat_wave")
	public static final BaseGun gunHeatWave = null;
	@GameRegistry.ObjectHolder("hiver")
	public static final BaseGun gunHiver = null;
	@GameRegistry.ObjectHolder("hot_shot")
	public static final BaseGun gunHotShot = null;
	@GameRegistry.ObjectHolder("hunters_rifle")
	public static final BaseGun gunHuntersRifle = null;
	@GameRegistry.ObjectHolder("iominator")
	public static final BaseGun gunIominator = null;
	@GameRegistry.ObjectHolder("ion_revolver")
	public static final BaseGun gunIonRevolver = null;
	@GameRegistry.ObjectHolder("iro_rifle")
	public static final BaseGun gunIroRifle = null;
	@GameRegistry.ObjectHolder("krilinator")
	public static final BaseGun gunKrilinator = null;
	@GameRegistry.ObjectHolder("light_iron")
	public static final BaseGun gunLightIron = null;
	@GameRegistry.ObjectHolder("lunar_assault_rifle")
	public static final BaseGun gunLunarAssaultRifle = null;
	@GameRegistry.ObjectHolder("mechanical_assault_rifle")
	public static final BaseGun gunMechanicalAssaultRifle = null;
	@GameRegistry.ObjectHolder("megagun")
	public static final BaseGun gunMegagun = null;
	@GameRegistry.ObjectHolder("miasma")
	public static final BaseGun gunMiasma = null;
	@GameRegistry.ObjectHolder("minigun")
	public static final BaseGun gunMinigun = null;
	@GameRegistry.ObjectHolder("mint_magnum")
	public static final BaseGun gunMintMagnum = null;
	@GameRegistry.ObjectHolder("mk")
	public static final BaseGun gunMK = null;
	@GameRegistry.ObjectHolder("mk_fung")
	public static final BaseGun gunMKFung = null;
	@GameRegistry.ObjectHolder("nethenette_rifle")
	public static final BaseGun gunNethenetteRifle = null;
	@GameRegistry.ObjectHolder("nethengeic_slugger")
	public static final BaseGun gunNethengeicSlugger = null;
	@GameRegistry.ObjectHolder("overshot")
	public static final BaseGun gunOvershot = null;
	@GameRegistry.ObjectHolder("precasian_slugger")
	public static final BaseGun gunPrecasianSlugger = null;
	@GameRegistry.ObjectHolder("predator")
	public static final BaseGun gunPredator = null;
	@GameRegistry.ObjectHolder("predigun")
	public static final BaseGun gunPredigun = null;
	@GameRegistry.ObjectHolder("pulsator")
	public static final BaseGun gunPulsator = null;
	@GameRegistry.ObjectHolder("purity_rifle")
	public static final BaseGun gunPurityRifle = null;
	@GameRegistry.ObjectHolder("rocker_rifle")
	public static final BaseGun gunRockerRifle = null;
	@GameRegistry.ObjectHolder("roulette")
	public static final BaseGun gunRoulette = null;
	@GameRegistry.ObjectHolder("shoe_flinger")
	public static final BaseGun gunShoeFlinger = null;
	@GameRegistry.ObjectHolder("skullette")
	public static final BaseGun gunSkullette = null;
	@GameRegistry.ObjectHolder("skullifact")
	public static final BaseGun gunSkullifact = null;
	@GameRegistry.ObjectHolder("spectacle")
	public static final BaseGun gunSpectacle = null;
	@GameRegistry.ObjectHolder("spine_gun")
	public static final BaseGun gunSpineGun = null;
	@GameRegistry.ObjectHolder("squad_gun")
	public static final BaseGun gunSquadGun = null;
	@GameRegistry.ObjectHolder("stampede")
	public static final BaseGun gunStampede = null;
	@GameRegistry.ObjectHolder("stormer")
	public static final BaseGun gunStormer = null;
	@GameRegistry.ObjectHolder("sublimus")
	public static final BaseGun gunSublimus = null;
	@GameRegistry.ObjectHolder("tiger_tommy")
	public static final BaseGun gunTigerTommy = null;
	@GameRegistry.ObjectHolder("tommy")
	public static final BaseGun gunTommy = null;
	@GameRegistry.ObjectHolder("vile_vanquisher")
	public static final BaseGun gunVileVanquisher = null;
	@GameRegistry.ObjectHolder("wart_gun")
	public static final BaseGun gunWartGun = null;
	@GameRegistry.ObjectHolder("wrecker")
	public static final BaseGun gunWrecker = null;

	@GameRegistry.ObjectHolder("abyssro")
	public static final BaseShotgun shotgunAbyssro = null;
	@GameRegistry.ObjectHolder("amplifier")
	public static final BaseShotgun shotgunAmplifier = null;
	@GameRegistry.ObjectHolder("blast_barrel")
	public static final BaseShotgun shotgunBlastBarrel = null;
	@GameRegistry.ObjectHolder("blue_barrel")
	public static final BaseShotgun shotgunBlueBarrel = null;
	@GameRegistry.ObjectHolder("boulder")
	public static final BaseShotgun shotgunBoulder = null;
	@GameRegistry.ObjectHolder("brown_blaster")
	public static final BaseShotgun shotgunBrownBlaster = null;
	@GameRegistry.ObjectHolder("demolisher")
	public static final BaseShotgun shotgunDemolisher = null;
	@GameRegistry.ObjectHolder("destruction_shotgun")
	public static final BaseShotgun shotgunDestructionShotgun = null;
	@GameRegistry.ObjectHolder("discharge_shotgun")
	public static final BaseShotgun shotgunDischargeShotgun = null;
	@GameRegistry.ObjectHolder("gimmick")
	public static final BaseShotgun shotgunGimmick = null;
	@GameRegistry.ObjectHolder("ginger_blaster")
	public static final BaseShotgun shotgunGingerBlaster = null;
	@GameRegistry.ObjectHolder("longshot")
	public static final BaseShotgun shotgunLongshot = null;
	@GameRegistry.ObjectHolder("mechyro")
	public static final BaseShotgun shotgunMechyro = null;
	@GameRegistry.ObjectHolder("purity_shotgun")
	public static final BaseShotgun shotgunPurityShotgun = null;
	@GameRegistry.ObjectHolder("purple_punisher")
	public static final BaseShotgun shotgunPurplePunisher = null;
	@GameRegistry.ObjectHolder("red_rocket")
	public static final BaseShotgun shotgunRedRocket = null;
	@GameRegistry.ObjectHolder("vivo")
	public static final BaseShotgun shotgunVivo = null;

	@GameRegistry.ObjectHolder("baron_ssr")
	public static final BaseSniper sniperBaronSSR = null;
	@GameRegistry.ObjectHolder("bayonette_sr")
	public static final BaseSniper sniperBayonetteSR = null;
	@GameRegistry.ObjectHolder("bolt_rifle")
	public static final BaseSniper sniperBoltRifle = null;
	@GameRegistry.ObjectHolder("camo_rifle")
	public static final BaseSniper sniperCamoRifle = null;
	@GameRegistry.ObjectHolder("clown_cracker")
	public static final BaseSniper sniperClownCracker = null;
	@GameRegistry.ObjectHolder("clownimator")
	public static final BaseSniper sniperClownimator = null;
	@GameRegistry.ObjectHolder("crystaneer")
	public static final BaseSniper sniperCrystaneer = null;
	@GameRegistry.ObjectHolder("dark_beast")
	public static final BaseSniper sniperDarkBeast = null;
	@GameRegistry.ObjectHolder("deadlock")
	public static final BaseSniper sniperDeadlock = null;
	@GameRegistry.ObjectHolder("decimator")
	public static final BaseSniper sniperDecimator = null;
	@GameRegistry.ObjectHolder("discharge_sniper")
	public static final BaseSniper sniperDischargeSniper = null;
	@GameRegistry.ObjectHolder("dual_sight")
	public static final BaseSniper sniperDualSight = null;
	@GameRegistry.ObjectHolder("duster")
	public static final BaseSniper sniperDuster = null;
	@GameRegistry.ObjectHolder("floro_500")
	public static final BaseSniper sniperFloro500 = null;
	@GameRegistry.ObjectHolder("head_hunter")
	public static final BaseSniper sniperHeadHunter = null;
	@GameRegistry.ObjectHolder("hive_cracker")
	public static final BaseSniper sniperHiveCracker = null;
	@GameRegistry.ObjectHolder("ka_500")
	public static final BaseSniper sniperKa500 = null;
	@GameRegistry.ObjectHolder("mark_maker")
	public static final BaseSniper sniperMarkMaker = null;
	@GameRegistry.ObjectHolder("mineral")
	public static final BaseSniper sniperMineral = null;
	@GameRegistry.ObjectHolder("monster")
	public static final BaseSniper sniperMonster = null;
	@GameRegistry.ObjectHolder("moon_maker")
	public static final BaseSniper sniperMoonMaker = null;
	@GameRegistry.ObjectHolder("rosid_rifle")
	public static final BaseSniper sniperRosidRifle = null;
	@GameRegistry.ObjectHolder("sabbath")
	public static final BaseSniper sniperSabbath = null;
	@GameRegistry.ObjectHolder("sludge_sniper")
	public static final BaseSniper sniperSludgeSniper = null;
	@GameRegistry.ObjectHolder("sweet_tooth")
	public static final BaseSniper sniperSweetTooth = null;
	@GameRegistry.ObjectHolder("terminator")
	public static final BaseSniper sniperTerminator = null;
	@GameRegistry.ObjectHolder("viper1")
	public static final BaseSniper sniperViper1 = null;

	@GameRegistry.ObjectHolder("ancient_bomber")
	public static final BaseCannon cannonAncientBomber = null;
	@GameRegistry.ObjectHolder("ancient_discharger")
	public static final BaseCannon cannonAncientDischarger = null;
	@GameRegistry.ObjectHolder("aqua_cannon")
	public static final BaseCannon cannonAquaCannon = null;
	@GameRegistry.ObjectHolder("balloon_bomber")
	public static final BaseCannon cannonBalloonBomber = null;
	@GameRegistry.ObjectHolder("big_blast")
	public static final BaseCannon cannonBigBlast = null;
	@GameRegistry.ObjectHolder("blast_cannon")
	public static final BaseCannon cannonBlastCannon = null;
	@GameRegistry.ObjectHolder("blissful_blast")
	public static final BaseCannon cannonBlissfulBlast = null;
	@GameRegistry.ObjectHolder("bomb_launcher")
	public static final BaseCannon cannonBombLauncher = null;
	@GameRegistry.ObjectHolder("boom_boom")
	public static final BaseCannon cannonBoomBoom = null;
	@GameRegistry.ObjectHolder("boom_cannon")
	public static final BaseCannon cannonBoomCannon = null;
	@GameRegistry.ObjectHolder("boulder_bomber")
	public static final BaseCannon cannonBoulderBomber = null;
	@GameRegistry.ObjectHolder("bozo_blaster")
	public static final BaseCannon cannonBozoBlaster = null;
	@GameRegistry.ObjectHolder("bulb_cannon")
	public static final BaseCannon cannonBulbCannon = null;
	@GameRegistry.ObjectHolder("carrot_cannon")
	public static final BaseCannon cannonCarrotCannon = null;
	@GameRegistry.ObjectHolder("clown_cannon")
	public static final BaseCannon cannonClownCannon = null;
	@GameRegistry.ObjectHolder("clowno_pulse")
	public static final BaseCannon cannonClownoPulse = null;
	@GameRegistry.ObjectHolder("coral_cannon")
	public static final BaseCannon cannonCoralCannon = null;
	@GameRegistry.ObjectHolder("discharge_cannon")
	public static final BaseCannon cannonDischargeCannon = null;
	@GameRegistry.ObjectHolder("energy_cannon")
	public static final BaseCannon cannonEnergyCannon = null;
	@GameRegistry.ObjectHolder("erebon_stickler")
	public static final BaseCannon cannonErebonStickler = null;
	@GameRegistry.ObjectHolder("floro_rpg")
	public static final BaseCannon cannonFloroRPG = null;
	@GameRegistry.ObjectHolder("flower_cannon")
	public static final BaseCannon cannonFlowerCannon = null;
	@GameRegistry.ObjectHolder("fungal_cannon")
	public static final BaseCannon cannonFungalCannon = null;
	@GameRegistry.ObjectHolder("ghast_blaster")
	public static final BaseCannon cannonGhastBlaster = null;
	@GameRegistry.ObjectHolder("ghoul_cannon")
	public static final BaseCannon cannonGhoulCannon = null;
	@GameRegistry.ObjectHolder("giga_cannon")
	public static final BaseCannon cannonGigaCannon = null;
	@GameRegistry.ObjectHolder("golder_bomber")
	public static final BaseCannon cannonGolderBomber = null;
	@GameRegistry.ObjectHolder("hive_blaster")
	public static final BaseCannon cannonHiveBlaster = null;
	@GameRegistry.ObjectHolder("hive_howitzer")
	public static final BaseCannon cannonHiveHowitzer = null;
	@GameRegistry.ObjectHolder("iro_cannon")
	public static final BaseCannon cannonIroCannon = null;
	@GameRegistry.ObjectHolder("jack_funger")
	public static final BaseCannon cannonJackFunger = null;
	@GameRegistry.ObjectHolder("jack_rocker")
	public static final BaseCannon cannonJackRocker = null;
	@GameRegistry.ObjectHolder("luxon_sticker")
	public static final BaseCannon cannonLuxonStickler = null;
	@GameRegistry.ObjectHolder("mecha_cannon")
	public static final BaseCannon cannonMechaCannon = null;
	@GameRegistry.ObjectHolder("mini_cannon")
	public static final BaseCannon cannonMiniCannon = null;
	@GameRegistry.ObjectHolder("missile_maker")
	public static final BaseCannon cannonMissileMaker = null;
	@GameRegistry.ObjectHolder("moon_cannon")
	public static final BaseCannon cannonMoonCannon = null;
	@GameRegistry.ObjectHolder("pluton_stickler")
	public static final BaseCannon cannonPlutonStickler = null;
	@GameRegistry.ObjectHolder("predatorian_blaster")
	public static final BaseCannon cannonPredatorianBlaster = null;
	@GameRegistry.ObjectHolder("pulse_cannon")
	public static final BaseCannon cannonPulseCannon = null;
	@GameRegistry.ObjectHolder("rpg")
	public static final BaseCannon cannonRPG = null;
	@GameRegistry.ObjectHolder("selyan_stickler")
	public static final BaseCannon cannonSelyanStickler = null;
	@GameRegistry.ObjectHolder("shadow_blaster")
	public static final BaseCannon cannonShadowBlaster = null;
	@GameRegistry.ObjectHolder("shyre_blaster")
	public static final BaseCannon cannonShyreBlaster = null;
	@GameRegistry.ObjectHolder("smile_blaster")
	public static final BaseCannon cannonSmileBlaster = null;
	@GameRegistry.ObjectHolder("super_cannon")
	public static final BaseCannon cannonSuperCannon = null;
	@GameRegistry.ObjectHolder("ultra_cannon")
	public static final BaseCannon cannonUltraCannon = null;
	@GameRegistry.ObjectHolder("vox_cannon")
	public static final BaseCannon cannonVoxCannon = null;
	@GameRegistry.ObjectHolder("water_balloon_bomber")
	public static final BaseCannon cannonWaterBalloonBomber = null;
	@GameRegistry.ObjectHolder("wither_cannon")
	public static final BaseCannon cannonWitherCannon = null;

	@GameRegistry.ObjectHolder("grenade")
	public static final BaseThrownWeapon throwableGrenade = null;
	@GameRegistry.ObjectHolder("slice_star")
	public static final BaseThrownWeapon throwableSliceStar = null;
	@GameRegistry.ObjectHolder("chakram")
	public static final BaseThrownWeapon throwableChakram = null;
	@GameRegistry.ObjectHolder("goo_ball")
	public static final BaseThrownWeapon throwableGooBall = null;
	@GameRegistry.ObjectHolder("vulkram")
	public static final BaseThrownWeapon throwableVulkram = null;
	@GameRegistry.ObjectHolder("hellfire")
	public static final BaseThrownWeapon throwableHellfire = null;
	@GameRegistry.ObjectHolder("runic_bomb")
	public static final BaseThrownWeapon throwableRunicBomb = null;

	@GameRegistry.ObjectHolder("vulcane")
	public static final BaseVulcane vulcane = null;
	@GameRegistry.ObjectHolder("battle_vulcane")
	public static final BaseVulcane vulcaneBattle = null;
	@GameRegistry.ObjectHolder("equality_vulcane")
	public static final BaseVulcane vulcaneEquality = null;
	@GameRegistry.ObjectHolder("fire_vulcane")
	public static final BaseVulcane vulcaneFire = null;
	@GameRegistry.ObjectHolder("impairment_vulcane")
	public static final BaseVulcane vulcaneImpairment = null;
	@GameRegistry.ObjectHolder("poison_vulcane")
	public static final BaseVulcane vulcanePoison = null;
	@GameRegistry.ObjectHolder("power_vulcane")
	public static final BaseVulcane vulcanePower = null;
	@GameRegistry.ObjectHolder("wither_vulcane")
	public static final BaseVulcane vulcaneWither = null;

	@GameRegistry.ObjectHolder("alacrity_bow")
	public static final BaseBow bowAlacrity = null;
	@GameRegistry.ObjectHolder("ancient_bow")
	public static final BaseBow bowAncient = null;
	@GameRegistry.ObjectHolder("atlantic_bow")
	public static final BaseBow bowAtlantic = null;
	@GameRegistry.ObjectHolder("baron_bow")
	public static final BaseBow bowBaron = null;
	@GameRegistry.ObjectHolder("boreic_bow")
	public static final BaseBow bowBoreic = null;
	@GameRegistry.ObjectHolder("daybreaker_bow")
	public static final BaseBow bowDaybreaker = null;
	@GameRegistry.ObjectHolder("deep_bow")
	public static final BaseBow bowDeep = null;
	@GameRegistry.ObjectHolder("explosive_bow")
	public static final BaseBow bowExplosive = null;
	@GameRegistry.ObjectHolder("haunted_bow")
	public static final BaseBow bowHaunted = null;
	@GameRegistry.ObjectHolder("ice_bow")
	public static final BaseBow bowIce = null;
	@GameRegistry.ObjectHolder("infernal_bow")
	public static final BaseBow bowInfernal = null;
	@GameRegistry.ObjectHolder("justice_bow")
	public static final BaseBow bowJustice = null;
	@GameRegistry.ObjectHolder("lunar_bow")
	public static final BaseBow bowLunar = null;
	@GameRegistry.ObjectHolder("mecha_bow")
	public static final BaseBow bowMecha = null;
	@GameRegistry.ObjectHolder("nightmare_bow")
	public static final BaseBow bowNightmare = null;
	@GameRegistry.ObjectHolder("poison_bow")
	public static final BaseBow bowPoison = null;
	@GameRegistry.ObjectHolder("predatious_bow")
	public static final BaseBow bowPredatious = null;
	@GameRegistry.ObjectHolder("primordial_bow")
	public static final BaseBow bowPrimordial = null;
	@GameRegistry.ObjectHolder("rosidian_bow")
	public static final BaseBow bowRosidian = null;
	@GameRegistry.ObjectHolder("runic_bow")
	public static final BaseBow bowRunic = null;
	@GameRegistry.ObjectHolder("screamer_bow")
	public static final BaseBow bowScreamer = null;
	@GameRegistry.ObjectHolder("shyregem_bow")
	public static final BaseBow bowShyregem = null;
	@GameRegistry.ObjectHolder("skeletal_bow")
	public static final BaseBow bowSkeletal = null;
	@GameRegistry.ObjectHolder("skydriver_bow")
	public static final BaseBow bowSkydriver = null;
	@GameRegistry.ObjectHolder("slingshot")
	public static final BaseBow bowSlingshot = null;
	@GameRegistry.ObjectHolder("soulfire_bow")
	public static final BaseBow bowSoulfire = null;
	@GameRegistry.ObjectHolder("spectral_bow")
	public static final BaseBow bowSpectral = null;
	@GameRegistry.ObjectHolder("speed_bow")
	public static final BaseBow bowSpeed = null;
	@GameRegistry.ObjectHolder("sunshine_bow")
	public static final BaseBow bowSunshine = null;
	@GameRegistry.ObjectHolder("toxin_bow")
	public static final BaseBow bowToxin = null;
	@GameRegistry.ObjectHolder("void_bow")
	public static final BaseBow bowVoid = null;
	@GameRegistry.ObjectHolder("weaken_bow")
	public static final BaseBow bowWeaken = null;
	@GameRegistry.ObjectHolder("wither_bow")
	public static final BaseBow bowWither = null;

	@GameRegistry.ObjectHolder("coral_archergun")
	public static final BaseArchergun archergunCoral = null;
	@GameRegistry.ObjectHolder("lunar_archergun")
	public static final BaseArchergun archergunLunar = null;
	@GameRegistry.ObjectHolder("mecha_archergun")
	public static final BaseArchergun archergunMecha = null;
	@GameRegistry.ObjectHolder("pyro_archergun")
	public static final BaseArchergun archergunPyro = null;
	@GameRegistry.ObjectHolder("rosidian_archergun")
	public static final BaseArchergun archergunRosidian = null;
	@GameRegistry.ObjectHolder("skeletal_archergun")
	public static final BaseArchergun archergunSkeletal = null;
	@GameRegistry.ObjectHolder("spectral_archergun")
	public static final BaseArchergun archergunSpectral = null;
	@GameRegistry.ObjectHolder("trolls_archergun")
	public static final BaseArchergun archergunTrolls = null;
	@GameRegistry.ObjectHolder("viral_archergun")
	public static final BaseArchergun archergunViral = null;

	@GameRegistry.ObjectHolder("aquatic_staff")
	public static final BaseStaff staffAquatic = null;
	@GameRegistry.ObjectHolder("atlantic_staff")
	public static final BaseStaff staffAtlantic = null;
	@GameRegistry.ObjectHolder("baron_staff")
	public static final BaseStaff staffBaron = null;
	@GameRegistry.ObjectHolder("candy_staff")
	public static final BaseStaff staffCandy = null;
	@GameRegistry.ObjectHolder("celestial_staff")
	public static final BaseStaff staffCelestial = null;
	@GameRegistry.ObjectHolder("concussion_staff")
	public static final BaseStaff staffConcussion = null;
	@GameRegistry.ObjectHolder("coral_staff")
	public static final BaseStaff staffCoral = null;
	@GameRegistry.ObjectHolder("crystal_staff")
	public static final BaseStaff staffCrystal = null;
	@GameRegistry.ObjectHolder("crystik_staff")
	public static final BaseStaff staffCrystik = null;
	@GameRegistry.ObjectHolder("cryston_staff")
	public static final BaseStaff staffCryston = null;
	@GameRegistry.ObjectHolder("destruction_staff")
	public static final BaseStaff staffDestruction = null;
	@GameRegistry.ObjectHolder("ember_staff")
	public static final BaseStaff staffEmber = null;
	@GameRegistry.ObjectHolder("everfight_staff")
	public static final BaseStaff staffEverfight = null;
	@GameRegistry.ObjectHolder("evermight_staff")
	public static final BaseStaff staffEvermight = null;
	@GameRegistry.ObjectHolder("fire_staff")
	public static final BaseStaff staffFire = null;
	@GameRegistry.ObjectHolder("firefly_staff")
	public static final BaseStaff staffFirefly = null;
	@GameRegistry.ObjectHolder("firestorm_staff")
	public static final BaseStaff staffFirestorm = null;
	@GameRegistry.ObjectHolder("fungal")
	public static final BaseStaff staffFungal = null;
	@GameRegistry.ObjectHolder("ghoul_staff")
	public static final BaseStaff staffGhoul = null;
	@GameRegistry.ObjectHolder("haunters_staff")
	public static final BaseStaff staffHaunters = null;
	@GameRegistry.ObjectHolder("hive_staff")
	public static final BaseStaff staffHive = null;
	@GameRegistry.ObjectHolder("joker_staff")
	public static final BaseStaff staffJoker = null;
	@GameRegistry.ObjectHolder("kaiyu_staff")
	public static final BaseStaff staffKaiyu = null;
	@GameRegistry.ObjectHolder("lightning_staff")
	public static final BaseStaff staffLightning = null;
	@GameRegistry.ObjectHolder("lightshine")
	public static final BaseStaff staffLightshine = null;
	@GameRegistry.ObjectHolder("lunar_staff")
	public static final BaseStaff staffLunar = null;
	@GameRegistry.ObjectHolder("lyonic_staff")
	public static final BaseStaff staffLyonic = null;
	@GameRegistry.ObjectHolder("mecha_staff")
	public static final BaseStaff staffMecha = null;
	@GameRegistry.ObjectHolder("meteor_staff")
	public static final BaseStaff staffMeteor = null;
	@GameRegistry.ObjectHolder("moonlight_staff")
	public static final BaseStaff staffMoonlight = null;
	@GameRegistry.ObjectHolder("nature_staff")
	public static final BaseStaff staffNature = null;
	@GameRegistry.ObjectHolder("nightmare_staff")
	public static final BaseStaff staffNightmare = null;
	@GameRegistry.ObjectHolder("noxious_staff")
	public static final BaseStaff staffNoxious = null;
	@GameRegistry.ObjectHolder("phantom_staff")
	public static final BaseStaff staffPhantom = null;
	@GameRegistry.ObjectHolder("poison_staff")
	public static final BaseStaff staffPoison = null;
	@GameRegistry.ObjectHolder("power_staff")
	public static final BaseStaff staffPower = null;
	@GameRegistry.ObjectHolder("primordial_staff")
	public static final BaseStaff staffPrimordial = null;
	@GameRegistry.ObjectHolder("reef_staff")
	public static final BaseStaff staffReef = null;
	@GameRegistry.ObjectHolder("rejuvenation_staff")
	public static final BaseStaff staffRejuvenation = null;
	@GameRegistry.ObjectHolder("rosidian_staff")
	public static final BaseStaff staffRosidian = null;
	@GameRegistry.ObjectHolder("runic_staff")
	public static final BaseStaff staffRunic = null;
	@GameRegistry.ObjectHolder("shadowlord_staff")
	public static final BaseStaff staffShadowlord = null;
	@GameRegistry.ObjectHolder("show_staff")
	public static final BaseStaff staffShow = null;
	@GameRegistry.ObjectHolder("shyre_staff")
	public static final BaseStaff staffShyre = null;
	@GameRegistry.ObjectHolder("sky_staff")
	public static final BaseStaff staffSky = null;
	@GameRegistry.ObjectHolder("striker_staff")
	public static final BaseStaff staffStriker = null;
	@GameRegistry.ObjectHolder("sun_staff")
	public static final BaseStaff staffSun = null;
	@GameRegistry.ObjectHolder("surge_staff")
	public static final BaseStaff staffSurge = null;
	@GameRegistry.ObjectHolder("tangle_staff")
	public static final BaseStaff staffTangle = null;
	@GameRegistry.ObjectHolder("ultimatum_staff")
	public static final BaseStaff staffUltimatum = null;
	@GameRegistry.ObjectHolder("underworld_staff")
	public static final BaseStaff staffUnderworld = null;
	@GameRegistry.ObjectHolder("warlock_staff")
	public static final BaseStaff staffWarlock = null;
	@GameRegistry.ObjectHolder("water_staff")
	public static final BaseStaff staffWater = null;
	@GameRegistry.ObjectHolder("web_staff")
	public static final BaseStaff staffWeb = null;
	@GameRegistry.ObjectHolder("wind_staff")
	public static final BaseStaff staffWind = null;
	@GameRegistry.ObjectHolder("wither_staff")
	public static final BaseStaff staffWither = null;
	@GameRegistry.ObjectHolder("wizards_staff")
	public static final BaseStaff staffWizards = null;

	@GameRegistry.ObjectHolder("apoco_shower")
	public static final BaseBlaster blasterApocoShower = null;
	@GameRegistry.ObjectHolder("atomizer")
	public static final BaseBlaster blasterAtomizer = null;
	@GameRegistry.ObjectHolder("beamer")
	public static final BaseBlaster blasterBeamer = null;
	@GameRegistry.ObjectHolder("blast_chiller")
	public static final BaseBlaster blasterBlastChiller = null;
	@GameRegistry.ObjectHolder("blood_drainer")
	public static final BaseBlaster blasterBloodDrainer = null;
	@GameRegistry.ObjectHolder("bone_blaster")
	public static final BaseBlaster blasterBoneBlaster = null;
	@GameRegistry.ObjectHolder("bubble_horn")
	public static final BaseBlaster blasterBubbleHorn = null;
	@GameRegistry.ObjectHolder("colour_cannon")
	public static final BaseBlaster blasterColourCannon = null;
	@GameRegistry.ObjectHolder("confetti_cannon")
	public static final BaseBlaster blasterConfettiCannon = null;
	@GameRegistry.ObjectHolder("confetti_cluster")
	public static final BaseBlaster blasterConfettiCluster = null;
	@GameRegistry.ObjectHolder("dark_destroyer")
	public static final BaseBlaster blasterDarkDestroyer = null;
	@GameRegistry.ObjectHolder("darkly_guster")
	public static final BaseBlaster blasterDarklyGuster = null;
	@GameRegistry.ObjectHolder("death_ray")
	public static final BaseBlaster blasterDeathRay = null;
	@GameRegistry.ObjectHolder("doom_bringer")
	public static final BaseBlaster blasterDoomBringer = null;
	@GameRegistry.ObjectHolder("eradicator")
	public static final BaseBlaster blasterEradicator = null;
	@GameRegistry.ObjectHolder("experiment_w801")
	public static final BaseBlaster blasterExperimentW801 = null;
	@GameRegistry.ObjectHolder("flowercorne")
	public static final BaseBlaster blasterFlowercorne = null;
	@GameRegistry.ObjectHolder("fragment")
	public static final BaseBlaster blasterFragment = null;
	@GameRegistry.ObjectHolder("froster")
	public static final BaseBlaster blasterFroster = null;
	@GameRegistry.ObjectHolder("gas_blaster")
	public static final BaseBlaster blasterGasBlaster = null;
	@GameRegistry.ObjectHolder("ghoul_gasser")
	public static final BaseBlaster blasterGhoulGasser = null;
	@GameRegistry.ObjectHolder("gold_bringer")
	public static final BaseBlaster blasterGoldBringer = null;
	@GameRegistry.ObjectHolder("gravity_blaster")
	public static final BaseBlaster blasterGravityBlaster = null;
	@GameRegistry.ObjectHolder("hell_horn")
	public static final BaseBlaster blasterHellHorn = null;
	@GameRegistry.ObjectHolder("illusion_revolver")
	public static final BaseBlaster blasterIllusionRevolver = null;
	@GameRegistry.ObjectHolder("illusion_smg")
	public static final BaseBlaster blasterIllusionSMG = null;
	@GameRegistry.ObjectHolder("ion_blaster")
	public static final BaseBlaster blasterIonBlaster = null;
	@GameRegistry.ObjectHolder("iro_miner")
	public static final BaseBlaster blasterIroMiner = null;
	@GameRegistry.ObjectHolder("laser_blaster")
	public static final BaseBlaster blasterLaserBlaster = null;
	@GameRegistry.ObjectHolder("light_blaster")
	public static final BaseBlaster blasterLightBlaster = null;
	@GameRegistry.ObjectHolder("light_spark")
	public static final BaseBlaster blasterLightSpark = null;
	@GameRegistry.ObjectHolder("luna_blaster")
	public static final BaseBlaster blasterLunaBlaster = null;
	@GameRegistry.ObjectHolder("mecha_blaster")
	public static final BaseBlaster blasterMechaBlaster = null;
	@GameRegistry.ObjectHolder("mind_blaster")
	public static final BaseBlaster blasterMindBlaster = null;
	@GameRegistry.ObjectHolder("moon_destroyer")
	public static final BaseBlaster blasterMoonDestroyer = null;
	@GameRegistry.ObjectHolder("moon_shiner")
	public static final BaseBlaster blasterMoonShiner = null;
	@GameRegistry.ObjectHolder("odious")
	public static final BaseBlaster blasterOdious = null;
	@GameRegistry.ObjectHolder("orbocron")
	public static final BaseBlaster blasterOrbocron = null;
	@GameRegistry.ObjectHolder("paralyzer")
	public static final BaseBlaster blasterParalyzer = null;
	@GameRegistry.ObjectHolder("party_popper")
	public static final BaseBlaster blasterPartyPopper = null;
	@GameRegistry.ObjectHolder("poison_plunger")
	public static final BaseBlaster blasterPoisonPlunger = null;
	@GameRegistry.ObjectHolder("power_ray")
	public static final BaseBlaster blasterPowerRay = null;
	@GameRegistry.ObjectHolder("proton")
	public static final BaseBlaster blasterProton = null;
	@GameRegistry.ObjectHolder("reefer")
	public static final BaseBlaster blasterReefer = null;
	@GameRegistry.ObjectHolder("revolution")
	public static final BaseBlaster blasterRevolution = null;
	@GameRegistry.ObjectHolder("seaocron")
	public static final BaseBlaster blasterSeaocron = null;
	@GameRegistry.ObjectHolder("skullo_blaster")
	public static final BaseBlaster blasterSkulloBlaster = null;
	@GameRegistry.ObjectHolder("soul_drainer")
	public static final BaseBlaster blasterSoulDrainer = null;
	@GameRegistry.ObjectHolder("soul_spark")
	public static final BaseBlaster blasterSoulSpark = null;
	@GameRegistry.ObjectHolder("soul_storm")
	public static final BaseBlaster blasterSoulStorm = null;
	@GameRegistry.ObjectHolder("spirit_shower")
	public static final BaseBlaster blasterSpiritShower = null;
	@GameRegistry.ObjectHolder("swarmotron")
	public static final BaseBlaster blasterSwarmotron = null;
	@GameRegistry.ObjectHolder("toxic_terrorizer")
	public static final BaseBlaster blasterToxicTerrorizer = null;
	@GameRegistry.ObjectHolder("vortex_blaster")
	public static final BaseBlaster blasterVortexBlaster = null;
	@GameRegistry.ObjectHolder("whimsy_winder")
	public static final BaseBlaster blasterWhimsyWinder = null;
	@GameRegistry.ObjectHolder("withers_wrath")
	public static final BaseBlaster blasterWithersWrath = null;

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
				new CrystalliteSword(	SWORD_CRYSTALLITE, 		0), // Haven | Crafting
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
				new TrollBasherAxe(		TROLL_BASHER_AXE, 		0), // Overworld | Smash
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
				new PrimordialGreatblade(	25.5f, 	0, 		1900), // Dustopia | Primordial Five
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
				new Megagun(				7.5f, 	1530, 8, 	3.0f), // UPGRADE KIT
				new Miasma(					9.0f, 	940, 	12, 	3.0f), // Gardencia | Crafting
				new Minigun(				5.0f, 	670, 	8, 	3.0f), // Lelyetia | Lelyetian Tower
				new MintMagnum(				6.0f, 	900, 	8, 	4.0f), // Candyland | Crafting
				new MK(						14.5f, 	1240, 16, 	3.0f), // LBorean | Aquatic Castle
				new MKFung(					15.0f, 	1490, 16, 	3.0f), // UPGRADE KIT
				new NethenetteRifle(		18.5f, 	1500, 20, 	4.0f), // UPGRADE KIT
				new NethengeicSlugger(		22.5f, 	780, 	32, 	9.0f), // Nether | Nethengeic Wither
				new Overshot(				6.5f, 	990, 	16, 	7.0f), // Iromine | Enforcer
				new PrecasianSlugger(		29.5f, 	1490, 32, 	9.0f), // UPGRADE KIT
				new Predator(				25.5f, 	1480, 28, 	6.0f), // UPGRADE KIT
				new Predigun(				7.5f, 	1530, 8, 	3.0f), // UPGRADE KIT
				new Pulsator(				25.0f, 	1190, 28, 	6.0f), // Lunalus | Crafting
				new PurityRifle(			18.0f, 	1200, 20, 	3.0f), // Runandor | Crafting
				new RockerRifle(			10.0f, 	630, 	16, 	0.0f), // Deeplands | Deep Case
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
				new BaronBow(		12.5f, 	1f, 		650), // Barathos | Crafting
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
		for (RegistryEvent.MissingMappings.Mapping<Item> map : ev.getAllMappings()) {
			if (map.key.toString().equals("aoa3:millenium_greatblade")) {
				map.remap(greatbladeMillennium);
			}
			else if (map.key.toString().equals("aoa3:grandsword")) {
				map.remap(greatbladeBloodlurker);
			}
		}
	}

	public static void registerDispensables() {
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableHellfire, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityHellfire(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableGrenade, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityGrenade(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableChakram, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityChakram(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableGooBall, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityGooBall(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableRunicBomb, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityRunicBomb(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableVulkram, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntityVulkram(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableSliceStar, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				return new EntitySliceStar(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.hollyArrow, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				EntityHollyArrow arrow = new EntityHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());

				arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				return arrow;
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.hollyArrowTipped, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				EntityTippedHollyArrow arrow = new EntityTippedHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());

				arrow.setPotionEffect(stack);
				arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				return arrow;
			}
		});

		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.hollyArrowSpectral, new BehaviorProjectileDispense() {
			@Nonnull
			@Override
			protected IProjectile getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, @Nonnull ItemStack stack) {
				EntitySpectralHollyArrow arrow = new EntitySpectralHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());

				arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				return arrow;
			}
		});
	}
}
