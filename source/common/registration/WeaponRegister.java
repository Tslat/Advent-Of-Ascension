package net.tslat.aoa3.common.registration;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
import net.tslat.aoa3.item.weapon.maul.CoralstoneMaul;
import net.tslat.aoa3.item.weapon.maul.CrystalMaul;
import net.tslat.aoa3.item.weapon.maul.HorizonMaul;
import net.tslat.aoa3.item.weapon.shotgun.*;
import net.tslat.aoa3.item.weapon.sniper.*;
import net.tslat.aoa3.item.weapon.sniper.Terminator;
import net.tslat.aoa3.item.weapon.staff.*;
import net.tslat.aoa3.item.weapon.sword.*;
import net.tslat.aoa3.item.weapon.thrown.*;
import net.tslat.aoa3.item.weapon.vulcane.*;
import net.tslat.aoa3.library.Enums;

import static net.tslat.aoa3.common.registration.MaterialsRegister.*;

@Mod.EventBusSubscriber
public class WeaponRegister {
	public static final Item swordAmethyst = new AmethystSword(AMETHYST, null, null);
	public static final Item swordBaron = new BaronSword(BARON, null, null);
	public static final Item swordBloodfury = new BloodfurySword(BLOODFURY, null, null);
	public static final Item swordBloodstone = new BloodstoneSword(BLOODSTONE, null, null);
	public static final Item swordCandlefire = new CandlefireSword(CANDLEFIRE, null, null);
	public static final Item swordCaramelCarver = new CaramelCarver(CARAMELCARVER, null, null);
	public static final Item swordCoralstorm = new CoralstormSword(CORALSTORM, null, null);
	public static final Item swordCreepified = new CreepifiedSword(CREEPIFIED, null, null);
	public static final Item swordCrystallite = new CrystalliteSword(CRYSTALLITE, null, null);
	public static final Item swordEmberstone = new EmberstoneSword(EMBERSTONE, null, null);
	public static final Item swordExplochron = new ExplochronSword(EXPLOCHRON, null, null);
	public static final Item swordFireborne = new FireborneSword(FIREBORNE, null, null);
	public static final Item swordGuardians = new GuardiansSword(GUARDIAN, null, null);
	public static final Item swordHarvester = new HarvesterSword(HARVESTER, null, null);
	public static final Item swordHoly = new HolySword(HOLY, null, null);
	public static final Item swordIllusion = new IllusionSword(ILLUSION, null, null);
	public static final Item swordJade = new JadeSword(JADE, null, null);
	public static final Item swordLegbone = new LegboneSword(LEGBONE, null, null);
	public static final Item swordLightsWay = new LightsWay(LIGHTSWAY, null, null);
	public static final Item swordLimonite = new LimoniteSword(LIMONITE, null, null);
	public static final Item swordNethengeic = new NethengeicSword(NETHENGEIC, null, null);
	public static final Item swordRockbasher = new RockbasherSword(ROCKBASHER, null, null);
	public static final Item swordRockPick = new RockPickSword(ROCKPICK, null, null);
	public static final Item swordRosidian = new RosidianSword(ROSIDIAN, null, null);
	public static final Item swordRosite = new RositeSword(ROSITE, null, null);
	public static final Item swordRunic = new RunicSword(RUNIC, null, null);
	public static final Item swordSapphire = new SapphireSword(SAPPHIRE, null, null);
	public static final Item swordShadow = new ShadowSword(SHADOW, null, null);
	public static final Item swordShroomus = new ShroomusSword(SHROOMUS, null, null);
	public static final Item swordSkeletal = new SkeletalSword(SKELETAL, null, null);
	public static final Item swordSupremacy = new SupremacySword(SUPREMACY, null, null);
	public static final Item swordSweet = new SweetSword(SWEET, null, null);
	public static final Item swordTrollBasherAxe = new TrollBasherAxe(TROLLBASHERAXE, null, null);
	public static final Item swordUltraflame = new Ultraflame(ULTRAFLAME, null, null);
	public static final Item swordVoid = new VoidSword(VOID, null, null);
	public static final Item swordVulcammer = new VulcammerSword(VULCAMMER, null, null);

	public static final Item greatbladeBaron = new BaronGreatblade(23.0f, 0, 600);
	public static final Item greatbladeCandyBlade = new CandyBlade(39.0f, 0, 500);
	public static final Item greatbladeCoral = new CoralGreatblade(21.0f, 0, 450);
	public static final Item greatbladeCottonCrusher = new CottonCrusher(24.0f, 0, 800);
	public static final Item greatbladeCreepoid = new CreepoidGreatblade(25.0f, 0, 550);
	public static final Item greatbladeCrystal = new CrystalGreatblade(22.0f, 0, 500);
	public static final Item greatbladeErebonScythe = new ErebonScythe(22.0f, -2.6D, 1000);
	public static final Item greatbladeGodsGreatblade = new GodsGreatblade(36.0f, 0, 800);
	public static final Item greatbladeGoofy = new GoofyGreatblade(35.0f, 0, 600);
	public static final Item greatbladeGrandsword = new Grandsword(16.0f, 0, 350);
	public static final Item greatbladeHaunted = new HauntedGreatblade(37.0f, 0, 800);
	public static final Item greatbladeKnightsGuard = new KnightsGuard(31.0f, 0, 600);
	public static final Item greatbladeLelyetian = new LelyetianGreatblade(26.0f, 0, 450);
	public static final Item greatbladeLunar = new LunarGreatblade(10.0f, 0, 600);
	public static final Item greatbladeLuxonScythe = new LuxonScythe(22.0f, -2.6D, 1000);
	public static final Item greatbladeLyonic = new LyonicGreatblade(22.0f, 0, 700);
	public static final Item greatbladeMillenium = new MilleniumGreatblade(-1f, 0, 700);
	public static final Item greatbladeNoxious = new NoxiousGreatblade(27.0f, 0, 600);
	public static final Item greatbladePlutonScythe = new PlutonScythe(15, -2.6D, 1000);
	public static final Item greatbladePrimordial = new PrimordialGreatblade(28.0f, 0, 800);
	public static final Item greatbladeRosidian = new RosidianGreatblade(27.0f, 0, 700);
	public static final Item greatbladeRoyal = new RoyalGreatblade(26.0f, 0, 500);
	public static final Item greatbladeRunic = new RunicGreatblade(25.0f, 0, 600);
	public static final Item greatbladeSelyanScythe = new SelyanScythe(22.0f, -2.6D, 1000);
	public static final Item greatbladeShroomic = new ShroomicGreatblade(27.0f, 0, 500);
	public static final Item greatbladeShyreSword = new ShyreSword(29.0f, 0, 500);
	public static final Item greatbladeSubterranean = new SubterraneanGreatblade(28.0f, 0, 800);
	public static final Item greatbladeTidal = new TidalGreatblade(23.0f, 0, 400);
	public static final Item greatbladeUnderworld = new UnderworldGreatblade(27.0f, 0, 500);

	public static final Item maulCoralstone = new CoralstoneMaul(15.0f, Enums.WeaponSpeed.HALF.value, 1.2D, 600);
	public static final Item maulCrystal = new CrystalMaul(19.0f, Enums.WeaponSpeed.HALF.value, 1.6D, 800);
	public static final Item maulHorizon = new HorizonMaul(12.0f, Enums.WeaponSpeed.HALF.value, 0.6D, 400);

	public static final Item gunAbominator = new Abominator(20, SoundsRegister.gunAbominator, 400, 12, 3.0f);
	public static final Item gunApocoAssaultRifle = new ApocoAssaultRifle(12, SoundsRegister.gunRevolver, 450, 8, 4.0f);
	public static final Item gunApocoRifle = new ApocoRifle(0, SoundsRegister.gunDischargeGun, 500, 8, 2.0f);
	public static final Item gunAquaMagnum = new AquaMagnum(39, SoundsRegister.gunFastRifle, 450, 40, 35.0f);
	public static final Item gunArtifact = new Artifact(17, SoundsRegister.gunArtifact, 450, 12, 4.0f);
	public static final Item gunBaronator = new Baronator(21, SoundsRegister.gunRevolver, 450, 20, 3.0f);
	public static final Item gunBayonetteRifle = new BayonetteRifle(13, SoundsRegister.gunSniper, 600, 36, 4.0f);
	public static final Item gunBigTop = new BigTop(7, SoundsRegister.gunRevolver, 600, 4, 3.0f);
	public static final Item gunBloodIron = new BloodIron(20, SoundsRegister.gunSlugger, 450, 16, 14.0f);
	public static final Item gunChainWrecker = new ChainWrecker(7, SoundsRegister.gunChaingun, 600, 4, 4.0f);
	public static final Item gunChilliChugger = new ChilliChugger(16, SoundsRegister.gunChugger, 600, 8, 5.0f);
	public static final Item gunClownershot = new Clownershot(8, SoundsRegister.gunWoodRifle, 450, 4, 7.0f);
	public static final Item gunConstruct = new Construct(7, SoundsRegister.gunFastRifle, 400, 4, 14.0f);
	public static final Item gunCoralClogger = new CoralClogger(33, SoundsRegister.gunSlugger, 500, 40, 15.0f);
	public static final Item gunCoreRifle = new CoreRifle(9.5, SoundsRegister.gunFastRifle, 450, 8, 3.0f);
	public static final Item gunCrystalCarver = new CrystalCarver(15, SoundsRegister.gunSniper, 400, 8, 23.0f);
	public static final Item gunCyclone = new Cyclone(18, SoundsRegister.gunArtifact, 500, 16, 6.0f);
	public static final Item gunDarkener = new Darkener(8, SoundsRegister.gunFastRifle, 450, 4, 13.0f);
	public static final Item gunDartGun = new DartGun(7, SoundsRegister.gunBlowpipe, 450, 40, 0.2f);
	public static final Item gunDestructionRifle = new DestructionRifle(16, SoundsRegister.gunRevolver, 450, 16, 3.0f);
	public static final Item gunDischargeRifle = new DischargeRifle(0, SoundsRegister.gunDischargeGun, 450, 8, 4.0f);
	public static final Item gunDraco = new Draco(18, SoundsRegister.gunRevolver, 600, 16, 2.0f);
	public static final Item gunDragilator = new Dragilator(8.5, SoundsRegister.gunGolemGun, 450, 8, 4.0f);
	public static final Item gunDustometer = new Dustometer(14, SoundsRegister.gunFastRifle, 450, 16, 7.0f);
	public static final Item gunEchoGull = new EchoGull(16, SoundsRegister.gunFastRifle, 450, 12, 2.0f);
	public static final Item gunElectinator = new Electinator(10, SoundsRegister.gunGolemGun, 450, 8, 4.0f);
	public static final Item gunFlameWrecker = new FlameWrecker(8, SoundsRegister.gunChaingun, 450, 4, 4.0f);
	public static final Item gunFlamingFury = new FlamingFury(40, SoundsRegister.gunFastRifle, 400, 24, 2.0f);
	public static final Item gunFloroRifle = new FloroRifle(15, SoundsRegister.gunFastRifle, 450, 20, 4.0f);
	public static final Item gunFlowersFury = new FlowersFury(16, SoundsRegister.gunFastRifle, 450, 20, 7.0f);
	public static final Item gunFrosticator = new Frosticator(8.5, SoundsRegister.gunGolemGun, 450, 8, 4.0f);
	public static final Item gunGardener = new Gardener(5, SoundsRegister.gunBlowpipe, 450, 4, 3.0f);
	public static final Item gunGaugeRifle = new GaugeRifle(20, SoundsRegister.gunGaugeRifle, 450, 16, 6.0f);
	public static final Item gunGerminator = new Germinator(8.5, SoundsRegister.gunGolemGun, 450, 8, 4.0f);
	public static final Item gunGoldenFury = new GoldenFury(50, SoundsRegister.gunFastRifle, 450, 24, 2.0f);
	public static final Item gunHappyHaunter = new HappyHaunter(45, SoundsRegister.gunRevolver, 450, 32, 2.0f);
	public static final Item gunHaunterRifle = new HaunterRifle(40, SoundsRegister.gunRevolver, 400, 32, 2.0f);
	public static final Item gunHeatWave = new HeatWave(11, SoundsRegister.gunHeatWave, 450, 12, 10.0f);
	public static final Item gunHiver = new Hiver(8, SoundsRegister.gunFastRifle, 450, 4, 14.0f);
	public static final Item gunHotShot = new HotShot(9, SoundsRegister.gunMiniPistol, 450, 24, 2.0f);
	public static final Item gunHuntersRifle = new HuntersRifle(10, SoundsRegister.gunSniper, 450, 56, 10.0f);
	public static final Item gunIominator = new Iominator(24, SoundsRegister.gunAbominator, 450, 12, 3.0f);
	public static final Item gunIonRevolver = new IonRevolver(14, SoundsRegister.gunSpaceRevolver, 400, 8, 3.0f);
	public static final Item gunIroRifle = new IroRifle(5, SoundsRegister.gunFastRifle, 600, 20, 2.0f);
	public static final Item gunKrilinator = new Krilinator(8.5, SoundsRegister.gunGolemGun, 450, 8, 4.0f);
	public static final Item gunLightIron = new LightIron(24, SoundsRegister.gunSlugger, 450, 16, 14.0f);
	public static final Item gunLunarAssaultRifle = new LunarAssaultRifle(20, SoundsRegister.gunSpaceGun, 600, 20, 5.0f);
	public static final Item gunMechanicalAssaultRifle = new MechanicalAssaultRifle(11, SoundsRegister.gunRevolver, 500, 8, 4.0f);
	public static final Item gunMegagun = new Megagun(8, SoundsRegister.gunMinigun, 400, 4, 4.0f);
	public static final Item gunMiasma = new Miasma(8, SoundsRegister.gunFastRifle, 450, 4, 3.0f);
	public static final Item gunMinigun = new Minigun(6, SoundsRegister.gunMinigun, 600, 4, 8.0f);
	public static final Item gunMintMagnum = new MintMagnum(35, SoundsRegister.gunFastRifle, 450, 40, 35.0f);
	public static final Item gunMK = new MK(6, SoundsRegister.gunRevolver, 400, 4, 3.0f);
	public static final Item gunMKFung = new MKFung(7, SoundsRegister.gunRevolver, 450, 4, 3.0f);
	public static final Item gunNethenetteRifle = new NethenetteRifle(18, SoundsRegister.gunSniper, 450, 36, 4.0f);
	public static final Item gunNethengeicSlugger = new NethengeicSlugger(25, SoundsRegister.gunSlugger, 500, 44, 15.0f);
	public static final Item gunOvershot = new Overshot(7, SoundsRegister.gunWoodRifle, 500, 4, 7.0f);
	public static final Item gunPrecasianSlugger = new PrecasianSlugger(29, SoundsRegister.gunSlugger, 450, 44, 15.0f);
	public static final Item gunPredator = new Predator(29, SoundsRegister.gunSpaceGun, 450, 16, 4.0f);
	public static final Item gunPredigun = new Predigun(7, SoundsRegister.gunMinigun, 450, 4, 8.0f);
	public static final Item gunPulsator = new Pulsator(16, SoundsRegister.gunSpaceGun, 400, 12, 4.0f);
	public static final Item gunPurityRifle = new PurityRifle(16, SoundsRegister.gunRevolver, 450, 16, 3.0f);
	public static final Item gunRockerRifle = new RockerRifle(24, SoundsRegister.gunRevolver, 600, 32, 5.0f);
	public static final Item gunRoulette = new Roulette(16, SoundsRegister.gunRoulette, 450, 8, 2.0f);
	public static final Item gunShoeFlinger = new ShoeFlinger(24, SoundsRegister.gunFlinger, 400, 24, 7.0f);
	public static final Item gunSkullette = new Skullette(17, SoundsRegister.gunRoulette, 450, 8, 2.0f);
	public static final Item gunSkullifact = new Skullifact(22, SoundsRegister.gunArtifact, 450, 12, 4.0f);
	public static final Item gunSpectacle = new Spectacle(6, SoundsRegister.gunFastRifle, 600, 4, 3.0f);
	public static final Item gunSpineGun = new SpineGun(6.5, SoundsRegister.gunSquadGun, 450, 4, 8.0f);
	public static final Item gunSquadGun = new SquadGun(6, SoundsRegister.gunSquadGun, 500, 4, 8.0f);
	public static final Item gunStampede = new Stampede(18, SoundsRegister.gunStampede, 450, 36, 4.0f);
	public static final Item gunStormer = new Stormer(17, SoundsRegister.gunFastRifle, 450, 24, 2.0f);
	public static final Item gunSublimus = new Sublimus(7, SoundsRegister.gunGolemGun, 500, 4, 12.0f);
	public static final Item gunTigerTommy = new TigerTommy(6, SoundsRegister.gunSquadGun, 450, 4, 5.0f);
	public static final Item gunTommy = new Tommy(5.5, SoundsRegister.gunSquadGun, 450, 4, 5.0f);
	public static final Item gunVileVanquisher = new VileVanquisher(14, SoundsRegister.gunFastRifle, 400, 16, 2.0f);
	public static final Item gunWartGun = new WartGun(25, SoundsRegister.gunBlowpipe, 400, 32, 0.2f);
	public static final Item gunWrecker = new Wrecker(7, SoundsRegister.gunFastRifle, 600, 4, 13.0f);

	public static final Item shotgunAbyssro = new Abyssro(20, 3, SoundsRegister.gunRevolver, 140, 21, 7.0f);
	public static final Item shotgunAmplifier = new Amplifier(10, 5, SoundsRegister.gunSlugger, 140, 20, 25.0f);
	public static final Item shotgunBlastBarrel = new BlastBarrel(14, 5, SoundsRegister.gunSlugger, 100, 22, 15.0f);
	public static final Item shotgunBlueBarrel = new BlueBarrel(15, 2, SoundsRegister.gunShotgun, 160, 37, 14.0f);
	public static final Item shotgunBoulder = new Boulder(24, 5, SoundsRegister.gunSlugger, 140, 60, 30.0f);
	public static final Item shotgunBrownBlaster = new BrownBlaster(6, 3, SoundsRegister.gunShotgun, 160, 55, 14.0f);
	public static final Item shotgunDemolisher = new Demolisher(20, 5, SoundsRegister.gunSlugger, 160, 60, 30.0f);
	public static final Item shotgunDestructionShotgun = new DestructionShotgun(24, 2, SoundsRegister.gunShotgun, 140, 37, 14.0f);
	public static final Item shotgunDischargeShotgun = new DischargeShotgun(0, 4, SoundsRegister.gunDischargeGun, 160, 55, 15.0f);
	public static final Item shotgunGimmick = new Gimmick(7, 3, SoundsRegister.gunShotgun, 160, 17, 9.0f);
	public static final Item shotgunGingerBlaster = new GingerBlaster(8, 5, SoundsRegister.gunSlugger, 140, 20, 16.0f);
	public static final Item shotgunLongshot = new LongShot(10, 2, SoundsRegister.gunRevolver, 160, 30, 4.0f);
	public static final Item shotgunMechyro = new Mechyro(16, 3, SoundsRegister.gunRevolver, 100, 21, 6.0f);
	public static final Item shotgunPurityShotgun = new PurityShotgun(24, 4, SoundsRegister.gunShotgun, 140, 47, 8.0f);
	public static final Item shotgunPurplePunisher = new PurplePunisher(20, 2, SoundsRegister.gunShotgun, 120, 22, 16.0f);
	public static final Item shotgunRedRocket = new RedRocket(19, 2, SoundsRegister.gunShotgun, 160, 27, 18.0f);
	public static final Item shotgunVivo = new Vivo(12, 5, SoundsRegister.gunShotgun, 140, 27, 15.0f);

	public static final Item sniperBaronSSR = new BaronSSR(40, SoundsRegister.gunSniper, 60, 35, 13.0f);
	public static final Item sniperBayonetteSR = new BayonetteSR(55, SoundsRegister.gunSniper, 60, 90, 14.0f);
	public static final Item sniperBoltRifle = new BoltRifle(50, SoundsRegister.gunSniper, 80, 100, 13.0f);
	public static final Item sniperCamoRifle = new CamoRifle(30, SoundsRegister.gunSniper, 80, 70, 13.0f);
	public static final Item sniperClownCracker = new ClownCracker(20, SoundsRegister.gunSniper, 80, 15, 19.0f);
	public static final Item sniperClownimator = new Clownimator(45, SoundsRegister.gunSniper, 80, 30, 13.0f);
	public static final Item sniperCrystaneer = new Crystaneer(32, SoundsRegister.gunSniper, 60, 16, 25.0f);
	public static final Item sniperDarkBeast = new DarkBeast(82, SoundsRegister.gunMonster, 80, 75, 13.0f);
	public static final Item sniperDeadlock = new Deadlock(40, SoundsRegister.gunSniper, 80, 75, 25.0f);
	public static final Item sniperDecimator = new Decimator(40, SoundsRegister.gunSniper, 90, 30, 13.0f);
	public static final Item sniperDischargeSniper = new DischargeSniper(50, SoundsRegister.gunDischargeGun, 80, 70, 11.0f);
	public static final Item sniperDualSight = new DualSight(49, SoundsRegister.gunSniper, 90, 24, 30.0f);
	public static final Item sniperDuster = new Duster(40, SoundsRegister.gunSniper, 80, 100, 13.0f);
	public static final Item sniperFloro500 = new Floro500(70, SoundsRegister.gunSniper, 80, 70, 13.0f);
	public static final Item sniperHeadHunter = new HeadHunter(45, SoundsRegister.gunSniper, 80, 50, 13.0f);
	public static final Item sniperHiveCracker = new HiveCracker(25, SoundsRegister.gunSniper, 80, 15, 19.0f);
	public static final Item sniperKa500 = new Ka500(60, SoundsRegister.gunSniper, 100, 70, 13.0f);
	public static final Item sniperMarkMaker = new MarkMaker(60, SoundsRegister.gunSniper, 90, 75, 8.0f);
	public static final Item sniperMineral = new Mineral(77, SoundsRegister.gunSniper, 80, 140, 22.0f);
	public static final Item sniperMonster = new Monster(75, SoundsRegister.gunMonster, 80, 75, 13.0f);
	public static final Item sniperMoonMaker = new MoonMaker(75, SoundsRegister.gunSniper, 80, 75, 8.0f);
	public static final Item sniperRosidRifle = new RosidRifle(35, SoundsRegister.gunSniper, 100, 100, 13.0f);
	public static final Item sniperSabbath = new Sabbath(48, SoundsRegister.gunSniper, 90, 95, 25.0f);
	public static final Item sniperSludgeSniper = new SludgeSniper(50, SoundsRegister.gunSniper, 100, 100, 13.0f);
	public static final Item sniperSweetTooth = new SweetTooth(40, SoundsRegister.gunSniper, 80, 20, 25.0f);
	public static final Item sniperTerminator = new Terminator(70, SoundsRegister.gunSniper, 60, 140, 22.0f);
	public static final Item sniperViper1 = new Viper1(50, SoundsRegister.gunSniper, 60, 70, 13.0f);

	public static final Item cannonAncientBomber = new AncientBomber(10, SoundsRegister.gunBoomCannon, 300, 8, 4.0f);
	public static final Item cannonAncientDischarger = new AncientDischarger(0, SoundsRegister.gunDischargeGun, 300, 20, 6.0f);
	public static final Item cannonAquaCannon = new AquaCannon(14, SoundsRegister.gunBallCannon, 300, 35, 5.0f);
	public static final Item cannonBalloonBomber = new BalloonBomber(14, SoundsRegister.gunBallCannon, 250, 12, 7.0f);
	public static final Item cannonBigBlast = new BigBlast(35, SoundsRegister.gunBigBlast, 400, 60, 11.0f);
	public static final Item cannonBlastCannon = new BlastCannon(20, SoundsRegister.gunBoomCannon, 350, 20, 15.0f);
	public static final Item cannonBlissfulBlast = new BlissfulBlast(40, SoundsRegister.gunBigBlast, 300, 60, 11.0f);
	public static final Item cannonBombLauncher = new BombLauncher(15, SoundsRegister.gunBoomCannon, 300, 45, 15.0f);
	public static final Item cannonBoomBoom = new BoomBoom(15, SoundsRegister.gunShadowBlaster, 300, 40, 10.0f);
	public static final Item cannonBoomCannon = new BoomCannon(8, SoundsRegister.gunBoomCannon, 400, 8, 4.0f);
	public static final Item cannonBoulderBomber = new BoulderBomber(35, SoundsRegister.gunBigBlast, 400, 85, 15.0f);
	public static final Item cannonBozoBlaster = new BozoBlaster(20, SoundsRegister.gunClowner, 250, 20, 11.0f);
	public static final Item cannonBulbCannon = new BulbCannon(28, SoundsRegister.gunWitherCannon, 300, 25, 8.0f);
	public static final Item cannonCarrotCannon = new CarrotCannon(8, SoundsRegister.gunCarrotCannon, 300, 8, 8.0f);
	public static final Item cannonClownCannon = new ClownCannon(15, SoundsRegister.gunClowner, 400, 35, 11.0f);
	public static final Item cannonClownoPulse = new ClownoPulse(41, SoundsRegister.gunShadowBlaster, 300, 35, 11.0f);
	public static final Item cannonCoralCannon = new CoralCannon(12, SoundsRegister.gunHighCannon, 300, 15, 5.0f);
	public static final Item cannonDischargeCannon = new DischargeCannon(0, SoundsRegister.gunDischargeGun, 400, 20, 6.0f);
	public static final Item cannonEnergyCannon = new EnergyCannon(17, SoundsRegister.gunEnergyCannon, 250, 15, 3.0f);
	public static final Item cannonErebonStickler = new ErebonStickler(22, SoundsRegister.gunCarrotCannon, 400, 45, 11.0f);
	public static final Item cannonFloroRPG = new FloroRPG(14, SoundsRegister.gunRPG, 300, 14, 7.0f);
	public static final Item cannonFlowerCannon = new FlowerCannon(14, SoundsRegister.gunBallCannon, 300, 25, 5.0f);
	public static final Item cannonFungalCannon = new FungalCannon(18, SoundsRegister.gunBallCannon, 300, 25, 5.0f);
	public static final Item cannonGhastBlaster = new GhastBlaster(10, SoundsRegister.gunLightCannon, 300, 10, 5.0f);
	public static final Item cannonGhoulCannon = new GhoulCannon(14, SoundsRegister.gunBallCannon, 300, 22, 5.0f);
	public static final Item cannonGigaCannon = new GigaCannon(6, SoundsRegister.gunUpperCannon, 250, 4, 3.0f);
	public static final Item cannonGolderBomber = new GolderBomber(40, SoundsRegister.gunBigBlast, 300, 85, 15.0f);
	public static final Item cannonHiveBlaster = new HiveBlaster(17, SoundsRegister.gunShadowBlaster, 300, 16, 7.0f);
	public static final Item cannonHiveHowitzer = new HiveHowitzer(16, SoundsRegister.gunBallCannon, 400, 15, 5.0f);
	public static final Item cannonIroCannon = new IroCannon(18, SoundsRegister.gunBallCannon, 400, 25, 5.0f);
	public static final Item cannonJackFunger = new JackFunger(8, SoundsRegister.gunJackRocker, 300, 12, 2.0f);
	public static final Item cannonJackRocker = new JackRocker(6, SoundsRegister.gunJackRocker, 400, 12, 2.0f);
	public static final Item cannonLuxonStickler = new LuxonStickler(22, SoundsRegister.gunCarrotCannon, 400, 45, 11.0f);
	public static final Item cannonMechaCannon = new MechaCannon(36, SoundsRegister.gunMechCannon, 250, 35, 5.0f);
	public static final Item cannonMiniCannon = new MiniCannon(7, SoundsRegister.gunLowerCannon, 300, 20, 2.0f);
	public static final Item cannonMissileMaker = new MissileMaker(12, SoundsRegister.gunMissileMaker, 400, 60, 35.0f);
	public static final Item cannonMoonCannon = new MoonCannon(18, SoundsRegister.gunEnergyCannon, 300, 15, 3.0f);
	public static final Item cannonPlutonStickler = new PlutonStickler(25, SoundsRegister.gunCarrotCannon, 400, 45, 11.0f);
	public static final Item cannonPredatorianBlaster = new PredatorianBlaster(11, SoundsRegister.gunLightCannon, 300, 10, 5.0f);
	public static final Item cannonPulseCannon = new PulseCannon(39, SoundsRegister.gunShadowBlaster, 400, 35, 11.0f);
	public static final Item cannonRPG = new RPG(10, SoundsRegister.gunRPG, 300, 14, 6.0f);
	public static final Item cannonSelyanStickler = new SelyanStickler(20, SoundsRegister.gunCarrotCannon, 400, 45, 11.0f);
	public static final Item cannonShadowBlaster = new ShadowBlaster(16, SoundsRegister.gunShadowBlaster, 400, 16, 7.0f);
	public static final Item cannonShyreBlaster = new ShyreBlaster(30, SoundsRegister.gunBigBlast, 300, 30, 12.0f);
	public static final Item cannonSmileBlaster = new SmileBlaster(17, SoundsRegister.gunShadowBlaster, 300, 16, 7.0f);
	public static final Item cannonSuperCannon = new SuperCannon(10, SoundsRegister.gunLowerCannon, 300, 13, 3.0f);
	public static final Item cannonUltraCannon = new UltraCannon(6, SoundsRegister.gunUpperCannon, 300, 6, 3.0f);
	public static final Item cannonVoxCannon = new VoxCannon(6, SoundsRegister.gunBallCannon, 300, 40, 5.0f);
	public static final Item cannonWaterBalloonBomber = new WaterBalloonBomber(15, SoundsRegister.gunBallCannon, 300, 12, 7.0f);
	public static final Item cannonWitherCannon = new WitherCannon(25, SoundsRegister.gunWitherCannon, 400, 25, 8.0f);

	public static final Item throwableGrenade = new Grenade();
	public static final Item throwableSliceStar = new SliceStar();
	public static final Item throwableChakram = new Chakram();
	public static final Item throwableGooBall = new GooBall();
	public static final Item throwableVulkram = new Vulkram();
	public static final Item throwableHellfire = new Hellfire();
	public static final Item throwableRunicBomb = new RunicBomb();

	public static final Item vulcane = new Vulcane(10, 300);
	public static final Item vulcaneBattle = new BattleVulcane(25, 150);
	public static final Item vulcaneEquality = new EqualityVulcane(15, 200);
	public static final Item vulcaneFire = new FireVulcane(15, 200);
	public static final Item vulcaneImpairment = new ImpairmentVulcane(15, 200);
	public static final Item vulcanePoison = new PoisonVulcane(15, 200);
	public static final Item vulcanePower = new PowerVulcane(15, 200);
	public static final Item vulcaneWither = new WitherVulcane(15, 200);

	public static final Item bowAlacrity = new AlacrityBow(20, 1.33f, 600);
	public static final Item bowAncient = new AncientBow(23, 1.33f, 700);
	public static final Item bowAtlantic = new AtlanticBow(24, 1.33f, 800);
	public static final Item bowBaron = new BaronBow(25, 1.33f, 800);
	public static final Item bowBoreic = new BoreicBow(20, 1f, 900);
	public static final Item bowDaybreaker = new DaybreakerBow(19, 1f, 700);
	public static final Item bowDeep = new DeepBow(28, 0.55f, 700);
	public static final Item bowExplosive = new ExplosiveBow(23, 1f, 900);
	public static final Item bowHaunted = new HauntedBow(29, 1f, 900);
	public static final Item bowIce = new IceBow(19, 1f, 600);
	public static final Item bowInfernal = new InfernalBow(20, 1f, 600);
	public static final Item bowJustice = new JusticeBow(24, 1f, 700);
	public static final Item bowLunar = new LunarBow(14, 1f, 900);
	public static final Item bowMecha = new MechaBow(21, 1f, 600);
	public static final Item bowNightmare = new NightmareBow(18, 1f, 800);
	public static final Item bowPoison = new PoisonBow(19, 1f, 600);
	public static final Item bowPredatious = new PredatiousBow(20, 1f, 700);
	public static final Item bowPrimordial = new PrimordialBow(25, 1f, 900);
	public static final Item bowRosidian = new RosidianBow(23, 1f, 900);
	public static final Item bowRunic = new RunicBow(22, 1f, 700);
	public static final Item bowScreamer = new ScreamerBow(22, 1f, 800);
	public static final Item bowShyregem = new ShyregemBow(13, 3f, 700);
	public static final Item bowSkeletal = new SkeletalBow(12, 3f, 900);
	public static final Item bowSkydriver = new SkydriverBow(26, 1f, 800);
	public static final Item bowSlingshot = new Slingshot(24, 1f, 700);
	public static final Item bowSoulfire = new SoulfireBow(24, 1.5f, 700);
	public static final Item bowSpectral = new SpectralBow(23, 0.8f, 800);
	public static final Item bowSpeed = new SpeedBow(15, 2, 600);
	public static final Item bowSunshine = new SunshineBow(25, 1f, 700);
	public static final Item bowToxin = new ToxinBow(26, 0.85f, 900);
	public static final Item bowVoid = new VoidBow(15, 1f, 800);
	public static final Item bowWeaken = new WeakenBow(19, 1f, 600);
	public static final Item bowWither = new WitherBow(21, 1f, 600);

	public static final Item archergunCoral = new CoralArchergun(13, SoundsRegister.gunArchergun, 600, 12, 3.0f);
	public static final Item archergunLunar = new LunarArchergun(8, SoundsRegister.gunArchergun, 800, 12, 3.0f);
	public static final Item archergunMecha = new MechaArchergun(16, SoundsRegister.gunArchergun, 600, 17, 3.0f);
	public static final Item archergunPyro = new PyroArchergun(14, SoundsRegister.gunArchergun, 700, 12, 3.0f);
	public static final Item archergunRosidian = new RosidianArchergun(14, SoundsRegister.gunArchergun, 650, 12, 3.0f);
	public static final Item archergunSkeletal = new SkeletalArchergun(13, SoundsRegister.gunArchergun, 1000, 10, 3.0f);
	public static final Item archergunSpectral = new SpectralArchergun(17, SoundsRegister.gunArchergun, 700, 16, 3.0f);
	public static final Item archergunTrolls = new TrollsArchergun(12, SoundsRegister.gunArchergun, 800, 12, 3.0f);
	public static final Item archergunViral = new ViralArchergun(14, SoundsRegister.gunArchergun, 650, 12, 3.0f);

	public static final Item staffAquatic = new AquaticStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffAtlantic = new AtlanticStaff(SoundsRegister.staffAtlantic, 300);
	public static final Item staffBaron = new BaronStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffCandy = new CandyStaff(SoundsRegister.staffCandy, 300);
	public static final Item staffCelestial = new CelestialStaff(SoundsRegister.staffCelestial, 350);
	public static final Item staffConcussion = new ConcussionStaff(SoundsRegister.staffConcussion, 300);
	public static final Item staffCoral = new CoralStaff(SoundsRegister.staffCoral, 150);
	public static final Item staffCrystal = new CrystalStaff(SoundsRegister.staffCrystevia, 300);
	public static final Item staffCrystik = new CrystikStaff(SoundsRegister.staffCrystevia, 300);
	public static final Item staffCryston = new CrystonStaff(SoundsRegister.staffCrystevia, 300);
	public static final Item staffDestruction = new DestructionStaff(SoundsRegister.staffBasic, 350);
	public static final Item staffEmber = new EmberStaff(SoundsRegister.staffEmber, 200);
	public static final Item staffEverfight = new EverfightStaff(SoundsRegister.staffEver, 300);
	public static final Item staffEvermight = new EvermightStaff(SoundsRegister.staffEmber, 300);
	public static final Item staffFire = new FireStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffFirefly = new FireflyStaff(SoundsRegister.staffFirefly, 400);
	public static final Item staffFirestorm = new FirestormStaff(SoundsRegister.staffNightmare, 400);
	public static final Item staffFungal = new FungalStaff(SoundsRegister.staffFungal, 300);
	public static final Item staffGhoul = new GhoulStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffHaunters = new HauntersStaff(SoundsRegister.staffBasic, 350);
	public static final Item staffHive = new HiveStaff(SoundsRegister.staffShadow, 200);
	public static final Item staffJoker = new JokerStaff(SoundsRegister.staffJoker, 300);
	public static final Item staffKaiyu = new KaiyuStaff(SoundsRegister.staffKaiyu, 100);
	public static final Item staffLightning = new LightningStaff(SoundsRegister.staffBasic, 350);
	public static final Item staffLightshine = new Lightshine(SoundsRegister.staffLightshine, 300);
	public static final Item staffLunar = new LunarStaff(SoundsRegister.staffLunar, 300);
	public static final Item staffLyonic = new LyonicStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffMecha = new MechaStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffMeteor = new MeteorStaff(SoundsRegister.staffMeteor, 400);
	public static final Item staffMoonlight = new MoonlightStaff(SoundsRegister.staffMoonlight, 70);
	public static final Item staffNature = new NatureStaff(SoundsRegister.staffNature, 150);
	public static final Item staffNightmare = new NightmareStaff(SoundsRegister.staffNightmare, 400);
	public static final Item staffNoxious = new NoxiousStaff(SoundsRegister.staffNoxious, 400);
	public static final Item staffPhantom = new PhantomStaff(SoundsRegister.staffPhantom, 400);
	public static final Item staffPoison = new PoisonStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffPower = new PowerStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffPrimordial = new PrimordialStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffReef = new ReefStaff(SoundsRegister.staffReef, 70);
	public static final Item staffRejuvenation = new RejuvenationStaff(SoundsRegister.staffRejuvenation, 50);
	public static final Item staffRosidian = new RosidianStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffRunic = new RunicStaff(SoundsRegister.staffRunic, 200);
	public static final Item staffShadowlord = new ShadowlordStaff(SoundsRegister.staffShadow, 200);
	public static final Item staffShow = new ShowStaff(SoundsRegister.staffShow, 300);
	public static final Item staffShyre = new ShyreStaff(SoundsRegister.staffShyre, 400);
	public static final Item staffSky = new SkyStaff(SoundsRegister.staffSky, 400);
	public static final Item staffStriker = new StrikerStaff(SoundsRegister.staffBasic, 350);
	public static final Item staffSun = new SunStaff(SoundsRegister.staffSun, 400);
	public static final Item staffSurge = new SurgeStaff(SoundsRegister.staffSurge, 100);
	public static final Item staffTangle = new TangleStaff(SoundsRegister.staffTangle, 400);
	public static final Item staffUltimatum = new UltimatumStaff(SoundsRegister.staffUltimatum, 300);
	public static final Item staffUnderworld = new UnderworldStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffWater = new WaterStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffWeb = new WebStaff(SoundsRegister.staffWeb, 70);
	public static final Item staffWind = new WindStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffWither = new WitherStaff(SoundsRegister.staffBasic, 400);
	public static final Item staffWizards = new WizardsStaff(SoundsRegister.staffBasic, 400);

	public static final Item blasterApocoShower = new ApocoShower(0, SoundsRegister.gunSpiritShower, 600, 25, 50);
	public static final Item blasterAtomizer = new Atomizer(12, SoundsRegister.gunAtomizer, 500, 20, 35);
	public static final Item blasterBeamer = new Beamer(1, SoundsRegister.gunSprayer, 700, 1, 3);
	public static final Item blasterBearBlaster = new BearBlaster(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterBeatSoundCannon = new BeatSoundCannon(12, null, 500, 10, 8);
	public static final Item blasterBeeBlaster = new BeeBlaster(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterBlastChiller = new BlastChiller(8, SoundsRegister.gunMagicGun, 600, 15, 15);
	public static final Item blasterBloodDrainer = new BloodDrainer(2, SoundsRegister.gunDrainGun, 600, 10, 40);
	public static final Item blasterBoneBlaster = new BoneBlaster(15, SoundsRegister.gunMiniPistol, 800, 11, 20);
	public static final Item blasterBubbleHorn = new BubbleHorn(13, SoundsRegister.gunBubbleGun, 700, 4, 10);
	public static final Item blasterCamelCannon = new CamelCannon(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterColourCannon = new ColourCannon(40, SoundsRegister.gunColourCannon, 600, 40, 50);
	public static final Item blasterConfettiCannon = new ConfettiCannon(0, SoundsRegister.gunConfettiCannon, 600, 40, 50);
	public static final Item blasterConfettiCluster = new ConfettiCluster(0, SoundsRegister.gunConfettiCannon, 500, 20, 20);
	public static final Item blasterDarkDestroyer = new DarkDestroyer(0, SoundsRegister.gunDoomGun, 600, 35, 40);
	public static final Item blasterDarklyGuster = new DarklyGuster(8, SoundsRegister.gunDarkGun, 600, 5, 10);
	public static final Item blasterDeathRay = new DeathRay(14, SoundsRegister.gunRayGun, 600, 20, 40);
	public static final Item blasterDeerDetonator = new DeerDetonator(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterDoomBringer = new DoomBringer(0, SoundsRegister.gunDoomGun, 600, 100, 40);
	public static final Item blasterDragonDestroyer = new DragonDestroyer(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterElectroSoundCannon = new ElectroSoundCannon(12, null, 500, 10, 8);
	public static final Item blasterEradicator = new Eradicator(2, SoundsRegister.gunSprayer, 800, 1, 2);
	public static final Item blasterFishFryer = new FishFryer(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterFlowercorne = new Flowercorne(3, SoundsRegister.gunSprayer, 500, 4, 10);
	public static final Item blasterFragment = new Fragment(4, SoundsRegister.gunIllusionSMG, 600, 2, 3);
	public static final Item blasterFroster = new Froster(2, SoundsRegister.gunSprayer, 500, 1, 2);
	public static final Item blasterGasBlaster = new GasBlaster(16, SoundsRegister.gunGasGun, 600, 10, 25);
	public static final Item blasterGhoulGasser = new GhoulGasser(25, SoundsRegister.gunGasGun, 800, 15, 25);
	public static final Item blasterGoldBringer = new GoldBringer(0, SoundsRegister.gunDoomGun, 600, 100, 25);
	public static final Item blasterGravityBlaster = new GravityBlaster(0, SoundsRegister.gunGravityBlaster, 600, 1, 1.5f);
	public static final Item blasterHellHorn = new HellHorn(14, SoundsRegister.gunBubbleGun, 600, 4, 10);
	public static final Item blasterHoundHoncho = new HoundHoncho(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterIllusionRevolver = new IllusionRevolver(16, SoundsRegister.gunIllusionRevolver, 800, 10, 25);
	public static final Item blasterIllusionSMG = new IllusionSMG(7, SoundsRegister.gunIllusionSMG, 800, 4, 8);
	public static final Item blasterIonBlaster = new IonBlaster(8, SoundsRegister.gunIonBlaster, 500, 10, 20);
	public static final Item blasterIroMiner = new IroMiner(22, SoundsRegister.gunMoonShiner, 600, 18, 30);
	public static final Item blasterLaserBlaster = new LaserBlaster(1, SoundsRegister.gunIllusionSMG, 600, 1, 3);
	public static final Item blasterLightBlaster = new LightBlaster(63, SoundsRegister.gunMindBlaster, 500, 40, 50);
	public static final Item blasterLightSpark = new LightSpark(0, SoundsRegister.gunSoulSpark, 2, 1, 200);
	public static final Item blasterLunaBlaster = new LunaBlaster(4, SoundsRegister.gunSpaceRevolver, 500, 3, 4);
	public static final Item blasterMechaBlaster = new MechaBlaster(30, SoundsRegister.gunMechCannon, 500, 30, 40);
	public static final Item blasterMindBlaster = new MindBlaster(55, SoundsRegister.gunMindBlaster, 800, 40, 50);
	public static final Item blasterMoonDestroyer = new MoonDestroyer(0, SoundsRegister.gunDoomGun, 600, 35, 20);
	public static final Item blasterMoonShiner = new MoonShiner(20, SoundsRegister.gunMoonShiner, 600, 70, 60);
	public static final Item blasterOdious = new Odious(1.3d, SoundsRegister.gunSprayer, 500, 1, 2);
	public static final Item blasterOrbocron = new Orbocron(16, SoundsRegister.gunShadowBlaster, 600, 30, 100);
	public static final Item blasterParalyzer = new Paralyzer(17, SoundsRegister.gunParalyzer, 800, 25, 25);
	public static final Item blasterPartyPopper = new PartyPopper(17, SoundsRegister.gunPartyPopper, 500, 25, 20);
	public static final Item blasterPenguinBlaster = new PenguinBlaster(6, SoundsRegister.gunAnimalBlaster, 500, 5, 16);
	public static final Item blasterPoisonPlunger = new PoisonPlunger(1, SoundsRegister.gunGasGun, 600, 8, 40);
	public static final Item blasterPowerRay = new PowerRay(8, SoundsRegister.gunRayGun, 700, 20, 40);
	public static final Item blasterProton = new Proton(3, SoundsRegister.gunIllusionSMG, 600, 2, 3);
	public static final Item blasterReefer = new Reefer(55, SoundsRegister.gunReefer, 600, 35, 100);
	public static final Item blasterRevolution = new Revolution(10, SoundsRegister.gunRevolution, 600, 35, 50);
	public static final Item blasterSeaocron = new Seaocron(20, SoundsRegister.gunShadowBlaster, 600, 30, 100);
	public static final Item blasterSkulloBlaster = new SkulloBlaster(6, SoundsRegister.gunSpaceRevolver, 600, 3, 4);
	public static final Item blasterSoulDrainer = new SoulDrainer(3, SoundsRegister.gunDrainGun, 550, 10, 30);
	public static final Item blasterSoulSpark = new SoulSpark(0, SoundsRegister.gunSoulSpark, 1, 30, 200);
	public static final Item blasterSoulStorm = new SoulStorm(1, SoundsRegister.gunSprayer, 600, 1, 2);
	public static final Item blasterSpiritShower = new SpiritShower(0, SoundsRegister.gunSpiritShower, 600, 30, 50);
	public static final Item blasterStepSoundCannon = new StepSoundCannon(12, null, 500, 10, 8);
	public static final Item blasterSwarmotron = new Swarmotron(20, SoundsRegister.gunSwarmotron, 500, 35, 50);
	public static final Item blasterSynthSoundCannon = new SynthSoundCannon(12, null, 500, 10, 8);
	public static final Item blasterToxicTerrorizer = new ToxicTerrorizer(9, SoundsRegister.gunMagicGun, 600, 12, 8);
	public static final Item blasterVibeSoundCannon = new VibeSoundCannon(12, null, 500, 10, 8);
	public static final Item blasterVortexBlaster = new VortexBlaster(0, SoundsRegister.gunGravityBlaster, 600, 60, 30);
	public static final Item blasterWhimsyWinder = new WhimsyWinder(5, SoundsRegister.gunWhimsyWinder, 500, 5, 10);
	public static final Item blasterWithersWrath = new WithersWrath(11, SoundsRegister.gunWithersWrath, 800, 6, 20);

	@SubscribeEvent
	public static void registerWeapon(final RegistryEvent.Register<Item> ev) {
		final IForgeRegistry<Item> registry = ev.getRegistry();

		registry.registerAll(
				swordAmethyst,
				swordBaron,
				swordBloodfury,
				swordBloodstone,
				swordCandlefire,
				swordCaramelCarver,
				swordCoralstorm,
				swordCreepified,
				swordCrystallite,
				swordEmberstone,
				swordExplochron,
				swordFireborne,
				swordGuardians,
				swordHarvester,
				swordHoly,
				swordIllusion,
				swordJade,
				swordLegbone,
				swordLightsWay,
				swordLimonite,
				swordNethengeic,
				swordRockbasher,
				swordRockPick,
				swordRosidian,
				swordRosite,
				swordRunic,
				swordSapphire,
				swordShadow,
				swordShroomus,
				swordSkeletal,
				swordSupremacy,
				swordSweet,
				swordTrollBasherAxe,
				swordUltraflame,
				swordVoid,
				swordVulcammer,
				greatbladeBaron,
				greatbladeCandyBlade,
				greatbladeCoral,
				greatbladeCottonCrusher,
				greatbladeCreepoid,
				greatbladeCrystal,
				greatbladeErebonScythe,
				greatbladeGodsGreatblade,
				greatbladeGoofy,
				greatbladeGrandsword,
				greatbladeHaunted,
				greatbladeKnightsGuard,
				greatbladeLelyetian,
				greatbladeLunar,
				greatbladeLuxonScythe,
				greatbladeLyonic,
				greatbladeMillenium,
				greatbladeNoxious,
				greatbladePlutonScythe,
				greatbladePrimordial,
				greatbladeRosidian,
				greatbladeRoyal,
				greatbladeRunic,
				greatbladeSelyanScythe,
				greatbladeShroomic,
				greatbladeShyreSword,
				greatbladeSubterranean,
				greatbladeTidal,
				greatbladeUnderworld,
				maulCrystal,
				maulHorizon,
				maulCoralstone,
				gunAbominator,
				gunApocoAssaultRifle,
				gunApocoRifle,
				gunAquaMagnum,
				gunArtifact,
				gunBaronator,
				gunBayonetteRifle,
				gunBigTop,
				gunBloodIron,
				gunChainWrecker,
				gunChilliChugger,
				gunClownershot,
				gunConstruct,
				gunCoralClogger,
				gunCoreRifle,
				gunCrystalCarver,
				gunCyclone,
				gunDarkener,
				gunDartGun,
				gunDestructionRifle,
				gunDischargeRifle,
				gunDraco,
				gunDragilator,
				gunDustometer,
				gunEchoGull,
				gunElectinator,
				gunFlameWrecker,
				gunFlamingFury,
				gunFloroRifle,
				gunFlowersFury,
				gunFrosticator,
				gunGardener,
				gunGaugeRifle,
				gunGerminator,
				gunGoldenFury,
				gunHappyHaunter,
				gunHaunterRifle,
				gunHeatWave,
				gunHiver,
				gunHotShot,
				gunHuntersRifle,
				gunIominator,
				gunIonRevolver,
				gunIroRifle,
				gunKrilinator,
				gunLightIron,
				gunLunarAssaultRifle,
				gunMechanicalAssaultRifle,
				gunMegagun,
				gunMiasma,
				gunMinigun,
				gunMintMagnum,
				gunMK,
				gunMKFung,
				gunNethenetteRifle,
				gunNethengeicSlugger,
				gunOvershot,
				gunPrecasianSlugger,
				gunPredator,
				gunPredigun,
				gunPulsator,
				gunPurityRifle,
				gunRockerRifle,
				gunRoulette,
				gunShoeFlinger,
				gunSkullette,
				gunSkullifact,
				gunSpectacle,
				gunSpineGun,
				gunSquadGun,
				gunStampede,
				gunStormer,
				gunSublimus,
				gunTigerTommy,
				gunTommy,
				gunVileVanquisher,
				gunWartGun,
				gunWrecker,
				shotgunAbyssro,
				shotgunAmplifier,
				shotgunBlastBarrel,
				shotgunBlueBarrel,
				shotgunBoulder,
				shotgunBrownBlaster,
				shotgunDemolisher,
				shotgunDestructionShotgun,
				shotgunDischargeShotgun,
				shotgunGimmick,
				shotgunGingerBlaster,
				shotgunLongshot,
				shotgunMechyro,
				shotgunPurityShotgun,
				shotgunPurplePunisher,
				shotgunRedRocket,
				shotgunVivo,
				sniperBaronSSR,
				sniperBayonetteSR,
				sniperBoltRifle,
				sniperCamoRifle,
				sniperClownCracker,
				sniperClownimator,
				sniperCrystaneer,
				sniperDarkBeast,
				sniperDeadlock,
				sniperDecimator,
				sniperDischargeSniper,
				sniperDualSight,
				sniperDuster,
				sniperFloro500,
				sniperHeadHunter,
				sniperHiveCracker,
				sniperKa500,
				sniperMarkMaker,
				sniperMineral,
				sniperMonster,
				sniperMoonMaker,
				sniperRosidRifle,
				sniperSabbath,
				sniperSludgeSniper,
				sniperSweetTooth,
				sniperTerminator,
				sniperViper1,
				cannonAncientBomber,
				cannonAncientDischarger,
				cannonAquaCannon,
				cannonBalloonBomber,
				cannonBigBlast,
				cannonBlastCannon,
				cannonBlissfulBlast,
				cannonBombLauncher,
				cannonBoomBoom,
				cannonBoomCannon,
				cannonBoulderBomber,
				cannonBozoBlaster,
				cannonBulbCannon,
				cannonCarrotCannon,
				cannonClownCannon,
				cannonClownoPulse,
				cannonCoralCannon,
				cannonDischargeCannon,
				cannonEnergyCannon,
				cannonErebonStickler,
				cannonFloroRPG,
				cannonFlowerCannon,
				cannonFungalCannon,
				cannonGhastBlaster,
				cannonGhoulCannon,
				cannonGigaCannon,
				cannonGolderBomber,
				cannonHiveBlaster,
				cannonHiveHowitzer,
				cannonIroCannon,
				cannonJackFunger,
				cannonJackRocker,
				cannonLuxonStickler,
				cannonMechaCannon,
				cannonMiniCannon,
				cannonMissileMaker,
				cannonMoonCannon,
				cannonPlutonStickler,
				cannonPredatorianBlaster,
				cannonPulseCannon,
				cannonRPG,
				cannonSelyanStickler,
				cannonShadowBlaster,
				cannonShyreBlaster,
				cannonSmileBlaster,
				cannonSuperCannon,
				cannonUltraCannon,
				cannonVoxCannon,
				cannonWaterBalloonBomber,
				cannonWitherCannon,
				throwableGrenade,
				throwableSliceStar,
				throwableVulkram,
				throwableChakram,
				throwableRunicBomb,
				throwableGooBall,
				throwableHellfire,
				vulcane,
				vulcaneBattle,
				vulcaneEquality,
				vulcaneFire,
				vulcaneImpairment,
				vulcanePoison,
				vulcanePower,
				vulcaneWither,
				bowAlacrity,
				bowAncient,
				bowAtlantic,
				bowBaron,
				bowBoreic,
				bowDaybreaker,
				bowDeep,
				bowExplosive,
				bowHaunted,
				bowIce,
				bowInfernal,
				bowJustice,
				bowLunar,
				bowMecha,
				bowNightmare,
				bowPoison,
				bowPredatious,
				bowPrimordial,
				bowRosidian,
				bowRunic,
				bowScreamer,
				bowShyregem,
				bowSkeletal,
				bowSkydriver,
				bowSlingshot,
				bowSoulfire,
				bowSpectral,
				bowSpeed,
				bowSunshine,
				bowToxin,
				bowVoid,
				bowWeaken,
				bowWither,
				archergunCoral,
				archergunLunar,
				archergunMecha,
				archergunPyro,
				archergunRosidian,
				archergunSkeletal,
				archergunSpectral,
				archergunTrolls,
				archergunViral,
				staffAquatic,
				staffAtlantic,
				staffBaron,
				staffCandy,
				staffCelestial,
				staffConcussion,
				staffCoral,
				staffCrystal,
				staffCrystik,
				staffCryston,
				staffDestruction,
				staffEmber,
				staffEverfight,
				staffEvermight,
				staffFire,
				staffFirefly,
				staffFirestorm,
				staffFungal,
				staffGhoul,
				staffHaunters,
				staffHive,
				staffJoker,
				staffKaiyu,
				staffLightning,
				staffLightshine,
				staffLunar,
				staffLyonic,
				staffMecha,
				staffMeteor,
				staffMoonlight,
				staffNature,
				staffNightmare,
				staffNoxious,
				staffPhantom,
				staffPoison,
				staffPower,
				staffPrimordial,
				staffReef,
				staffRejuvenation,
				staffRosidian,
				staffRunic,
				staffShadowlord,
				staffShow,
				staffShyre,
				staffSky,
				staffStriker,
				staffSun,
				staffSurge,
				staffTangle,
				staffUltimatum,
				staffUnderworld,
				staffWater,
				staffWeb,
				staffWind,
				staffWither,
				staffWizards,
				blasterApocoShower,
				blasterAtomizer,
				blasterBeamer,
				blasterBearBlaster,
				blasterBeatSoundCannon,
				blasterBeeBlaster,
				blasterBlastChiller,
				blasterBloodDrainer,
				blasterBoneBlaster,
				blasterBubbleHorn,
				blasterCamelCannon,
				blasterColourCannon,
				blasterConfettiCannon,
				blasterConfettiCluster,
				blasterDarkDestroyer,
				blasterDarklyGuster,
				blasterDeathRay,
				blasterDeerDetonator,
				blasterDoomBringer,
				blasterDragonDestroyer,
				blasterElectroSoundCannon,
				blasterEradicator,
				blasterFishFryer,
				blasterFlowercorne,
				blasterFragment,
				blasterFroster,
				blasterGasBlaster,
				blasterGhoulGasser,
				blasterGoldBringer,
				blasterGravityBlaster,
				blasterHellHorn,
				blasterHoundHoncho,
				blasterIllusionRevolver,
				blasterIllusionSMG,
				blasterIonBlaster,
				blasterIroMiner,
				blasterLaserBlaster,
				blasterLightBlaster,
				blasterLightSpark,
				blasterLunaBlaster,
				blasterMechaBlaster,
				blasterMindBlaster,
				blasterMoonDestroyer,
				blasterMoonShiner,
				blasterOdious,
				blasterOrbocron,
				blasterParalyzer,
				blasterPartyPopper,
				blasterPenguinBlaster,
				blasterPoisonPlunger,
				blasterPowerRay,
				blasterProton,
				blasterReefer,
				blasterRevolution,
				blasterSeaocron,
				blasterSkulloBlaster,
				blasterSoulDrainer,
				blasterSoulSpark,
				blasterSoulStorm,
				blasterSpiritShower,
				blasterStepSoundCannon,
				blasterSwarmotron,
				blasterSynthSoundCannon,
				blasterToxicTerrorizer,
				blasterVibeSoundCannon,
				blasterVortexBlaster,
				blasterWhimsyWinder,
				blasterWithersWrath
		);
	}

	@SubscribeEvent
	public static void registerWeaponRender(final ModelRegistryEvent ev) {
		registerRender(swordAmethyst, "swords/amethyst");
		registerRender(swordBaron, "swords/baron");
		registerRender(swordBloodfury, "swords/bloodfury");
		registerRender(swordBloodstone, "swords/bloodstone");
		registerRender(swordCandlefire, "swords/candlefire");
		registerRender(swordCaramelCarver, "swords/caramel_carver");
		registerRender(swordCoralstorm, "swords/coralstorm");
		registerRender(swordCreepified, "swords/creepified");
		registerRender(swordCrystallite, "swords/crystallite");
		registerRender(swordEmberstone, "swords/emberstone");
		registerRender(swordExplochron, "swords/explochron");
		registerRender(swordFireborne, "swords/fireborne");
		registerRender(swordGuardians, "swords/guardians");
		registerRender(swordHarvester, "swords/harvester");
		registerRender(swordHoly, "swords/holy");
		registerRender(swordIllusion, "swords/illusion");
		registerRender(swordJade, "swords/jade");
		registerRender(swordLegbone, "swords/legbone");
		registerRender(swordLightsWay, "swords/lights_way");
		registerRender(swordLimonite, "swords/limonite");
		registerRender(swordNethengeic, "swords/nethengeic");
		registerRender(swordRockbasher, "swords/rockbasher");
		registerRender(swordRockPick, "swords/rock_pick");
		registerRender(swordRosidian, "swords/rosidian");
		registerRender(swordRosite, "swords/rosite");
		registerRender(swordRunic, "swords/runic");
		registerRender(swordSapphire, "swords/sapphire");
		registerRender(swordShadow, "swords/shadow");
		registerRender(swordShroomus, "swords/shroomus");
		registerRender(swordSkeletal, "swords/skeletal");
		registerRender(swordSupremacy, "swords/supremacy");
		registerRender(swordSweet, "swords/sweet");
		registerRender(swordTrollBasherAxe, "swords/troll_basher_axe");
		registerRender(swordUltraflame, "swords/ultraflame");
		registerRender(swordVoid, "swords/void");
		registerRender(swordVulcammer, "swords/vulcammer");
		registerRender(greatbladeBaron, "greatblades/baron");
		registerRender(greatbladeCandyBlade, "greatblades/candy_blade");
		registerRender(greatbladeCoral, "greatblades/coral");
		registerRender(greatbladeCottonCrusher, "greatblades/cotton_crusher");
		registerRender(greatbladeCreepoid, "greatblades/creepoid");
		registerRender(greatbladeCrystal, "greatblades/crystal");
		registerRender(greatbladeErebonScythe, "greatblades/erebon_scythe");
		registerRender(greatbladeGodsGreatblade, "greatblades/gods");
		registerRender(greatbladeGoofy, "greatblades/goofy");
		registerRender(greatbladeGrandsword, "greatblades/grandsword");
		registerRender(greatbladeHaunted, "greatblades/haunted");
		registerRender(greatbladeKnightsGuard, "greatblades/knights_guard");
		registerRender(greatbladeLelyetian, "greatblades/lelyetian");
		registerRender(greatbladeLunar, "greatblades/lunar");
		registerRender(greatbladeLuxonScythe, "greatblades/luxon_scythe");
		registerRender(greatbladeLyonic, "greatblades/lyonic");
		registerRender(greatbladeMillenium, "greatblades/millenium");
		registerRender(greatbladeNoxious, "greatblades/noxious");
		registerRender(greatbladePlutonScythe, "greatblades/pluton_scythe");
		registerRender(greatbladePrimordial, "greatblades/primordial");
		registerRender(greatbladeRosidian, "greatblades/rosidian");
		registerRender(greatbladeRoyal, "greatblades/royal");
		registerRender(greatbladeRunic, "greatblades/runic");
		registerRender(greatbladeSelyanScythe, "greatblades/selyan_scythe");
		registerRender(greatbladeShroomic, "greatblades/shroomic");
		registerRender(greatbladeShyreSword, "greatblades/shyre_sword");
		registerRender(greatbladeSubterranean, "greatblades/subterranean");
		registerRender(greatbladeTidal, "greatblades/tidal");
		registerRender(greatbladeUnderworld, "greatblades/underworld");
		registerRender(maulCrystal, "mauls/crystal");
		registerRender(maulHorizon, "mauls/horizon");
		registerRender(maulCoralstone, "mauls/coralstone");
		registerRender(gunAbominator, "guns/abominator");
		registerRender(gunApocoAssaultRifle, "guns/apoco_assault_rifle");
		registerRender(gunApocoRifle, "guns/apoco_rifle");
		registerRender(gunAquaMagnum, "guns/aqua_magnum");
		registerRender(gunArtifact, "guns/artifact");
		registerRender(gunBaronator, "guns/baronator");
		registerRender(gunBayonetteRifle, "guns/bayonette_rifle");
		registerRender(gunBigTop, "guns/big_top");
		registerRender(gunBloodIron, "guns/blood_iron");
		registerRender(gunChainWrecker, "guns/chain_wrecker");
		registerRender(gunChilliChugger, "guns/chilli_chugger");
		registerRender(gunClownershot, "guns/clownershot");
		registerRender(gunConstruct, "guns/construct");
		registerRender(gunCoralClogger, "guns/coral_clogger");
		registerRender(gunCoreRifle, "guns/core_rifle");
		registerRender(gunCrystalCarver, "guns/crystal_carver");
		registerRender(gunCyclone, "guns/cyclone");
		registerRender(gunDarkener, "guns/darkener");
		registerRender(gunDartGun, "guns/dart_gun");
		registerRender(gunDestructionRifle, "guns/destruction_rifle");
		registerRender(gunDischargeRifle, "guns/discharge_rifle");
		registerRender(gunDraco, "guns/draco");
		registerRender(gunDragilator, "guns/dragilator");
		registerRender(gunDustometer, "guns/dustometer");
		registerRender(gunEchoGull, "guns/echo_gull");
		registerRender(gunElectinator, "guns/electinator");
		registerRender(gunFlameWrecker, "guns/flame_wrecker");
		registerRender(gunFlamingFury, "guns/flaming_fury");
		registerRender(gunFloroRifle, "guns/floro_rifle");
		registerRender(gunFlowersFury, "guns/flowers_fury");
		registerRender(gunFrosticator, "guns/frosticator");
		registerRender(gunGardener, "guns/gardener");
		registerRender(gunGaugeRifle, "guns/gauge_rifle");
		registerRender(gunGerminator, "guns/germinator");
		registerRender(gunGoldenFury, "guns/golden_fury");
		registerRender(gunHappyHaunter, "guns/happy_haunter");
		registerRender(gunHaunterRifle, "guns/haunter_rifle");
		registerRender(gunHeatWave, "guns/heat_wave");
		registerRender(gunHiver, "guns/hiver");
		registerRender(gunHotShot, "guns/hot_shot");
		registerRender(gunHuntersRifle, "guns/hunters_rifle");
		registerRender(gunIominator, "guns/iominator");
		registerRender(gunIonRevolver, "guns/ion_revolver");
		registerRender(gunIroRifle, "guns/iro_rifle");
		registerRender(gunKrilinator, "guns/krilinator");
		registerRender(gunLightIron, "guns/light_iron");
		registerRender(gunLunarAssaultRifle, "guns/lunar_assault_rifle");
		registerRender(gunMechanicalAssaultRifle, "guns/mechanical_assault_rifle");
		registerRender(gunMegagun, "guns/megagun");
		registerRender(gunMiasma, "guns/miasma");
		registerRender(gunMinigun, "guns/minigun");
		registerRender(gunMintMagnum, "guns/mint_magnum");
		registerRender(gunMK, "guns/mk");
		registerRender(gunMKFung, "guns/mk_fung");
		registerRender(gunNethenetteRifle, "guns/nethenette_rifle");
		registerRender(gunNethengeicSlugger, "guns/nethengeic_slugger");
		registerRender(gunOvershot, "guns/overshot");
		registerRender(gunPrecasianSlugger, "guns/precasian_slugger");
		registerRender(gunPredator, "guns/predator");
		registerRender(gunPredigun, "guns/predigun");
		registerRender(gunPulsator, "guns/pulsator");
		registerRender(gunPurityRifle, "guns/purity_rifle");
		registerRender(gunRockerRifle, "guns/rocker_rifle");
		registerRender(gunRoulette, "guns/roulette");
		registerRender(gunShoeFlinger, "guns/shoe_flinger");
		registerRender(gunSkullette, "guns/skullette");
		registerRender(gunSkullifact, "guns/skullifact");
		registerRender(gunSpectacle, "guns/spectacle");
		registerRender(gunSpineGun, "guns/spine_gun");
		registerRender(gunSquadGun, "guns/squad_gun");
		registerRender(gunStampede, "guns/stampede");
		registerRender(gunStormer, "guns/stormer");
		registerRender(gunSublimus, "guns/sublimus");
		registerRender(gunTigerTommy, "guns/tiger_tommy");
		registerRender(gunTommy, "guns/tommy");
		registerRender(gunVileVanquisher, "guns/vile_vanquisher");
		registerRender(gunWartGun, "guns/wart_gun");
		registerRender(gunWrecker, "guns/wrecker");
		registerRender(shotgunAbyssro, "shotguns/abyssro");
		registerRender(shotgunAmplifier, "shotguns/amplifier");
		registerRender(shotgunBlastBarrel, "shotguns/blast_barrel");
		registerRender(shotgunBlueBarrel, "shotguns/blue_barrel");
		registerRender(shotgunBoulder, "shotguns/boulder");
		registerRender(shotgunBrownBlaster, "shotguns/brown_blaster");
		registerRender(shotgunDemolisher, "shotguns/demolisher");
		registerRender(shotgunDestructionShotgun, "shotguns/destruction_shotgun");
		registerRender(shotgunDischargeShotgun, "shotguns/discharge_shotgun");
		registerRender(shotgunGimmick, "shotguns/gimmick");
		registerRender(shotgunGingerBlaster, "shotguns/ginger_blaster");
		registerRender(shotgunLongshot, "shotguns/long_shot");
		registerRender(shotgunMechyro, "shotguns/mechyro");
		registerRender(shotgunPurityShotgun, "shotguns/purity_shotgun");
		registerRender(shotgunPurplePunisher, "shotguns/purple_punisher");
		registerRender(shotgunRedRocket, "shotguns/red_rocket");
		registerRender(shotgunVivo, "shotguns/vivo");
		registerRender(sniperBaronSSR, "snipers/baron_ssr");
		registerRender(sniperBayonetteSR, "snipers/bayonette_sr");
		registerRender(sniperBoltRifle, "snipers/bolt_rifle");
		registerRender(sniperCamoRifle, "snipers/camo_rifle");
		registerRender(sniperClownCracker, "snipers/clown_cracker");
		registerRender(sniperClownimator, "snipers/clownimator");
		registerRender(sniperCrystaneer, "snipers/crystaneer");
		registerRender(sniperDarkBeast, "snipers/dark_beast");
		registerRender(sniperDeadlock, "snipers/deadlock");
		registerRender(sniperDecimator, "snipers/decimator");
		registerRender(sniperDischargeSniper, "snipers/discharge_sniper");
		registerRender(sniperDualSight, "snipers/dual_sight");
		registerRender(sniperDuster, "snipers/duster");
		registerRender(sniperFloro500, "snipers/floro500");
		registerRender(sniperHeadHunter, "snipers/head_hunter");
		registerRender(sniperHiveCracker, "snipers/hive_cracker");
		registerRender(sniperKa500, "snipers/ka500");
		registerRender(sniperMarkMaker, "snipers/mark_maker");
		registerRender(sniperMineral, "snipers/mineral");
		registerRender(sniperMonster, "snipers/monster");
		registerRender(sniperMoonMaker, "snipers/moon_maker");
		registerRender(sniperRosidRifle, "snipers/rosid_rifle");
		registerRender(sniperSabbath, "snipers/sabbath");
		registerRender(sniperSludgeSniper, "snipers/sludge_sniper");
		registerRender(sniperSweetTooth, "snipers/sweet_tooth");
		registerRender(sniperTerminator, "snipers/terminator");
		registerRender(sniperViper1, "snipers/viper1");
		registerRender(cannonAncientBomber, "cannons/ancient_bomber");
		registerRender(cannonAncientDischarger, "cannons/ancient_discharger");
		registerRender(cannonAquaCannon, "cannons/aqua_cannon");
		registerRender(cannonBalloonBomber, "cannons/balloon_bomber");
		registerRender(cannonBigBlast, "cannons/big_blast");
		registerRender(cannonBlastCannon, "cannons/blast_cannon");
		registerRender(cannonBlissfulBlast, "cannons/blissful_blast");
		registerRender(cannonBombLauncher, "cannons/bomb_launcher");
		registerRender(cannonBoomBoom, "cannons/boom_boom");
		registerRender(cannonBoomCannon, "cannons/boom_cannon");
		registerRender(cannonBoulderBomber, "cannons/boulder_bomber");
		registerRender(cannonBozoBlaster, "cannons/bozo_blaster");
		registerRender(cannonBulbCannon, "cannons/bulb_cannon");
		registerRender(cannonCarrotCannon, "cannons/carrot_cannon");
		registerRender(cannonClownCannon, "cannons/clown_cannon");
		registerRender(cannonClownoPulse, "cannons/clowno_pulse");
		registerRender(cannonCoralCannon, "cannons/coral_cannon");
		registerRender(cannonDischargeCannon, "cannons/discharge_cannon");
		registerRender(cannonEnergyCannon, "cannons/energy_cannon");
		registerRender(cannonErebonStickler, "cannons/erebon_stickler");
		registerRender(cannonFloroRPG, "cannons/floro_rpg");
		registerRender(cannonFlowerCannon, "cannons/flower_cannon");
		registerRender(cannonFungalCannon, "cannons/fungal_cannon");
		registerRender(cannonGhastBlaster, "cannons/ghast_blaster");
		registerRender(cannonGhoulCannon, "cannons/ghoul_cannon");
		registerRender(cannonGigaCannon, "cannons/giga_cannon");
		registerRender(cannonGolderBomber, "cannons/golder_bomber");
		registerRender(cannonHiveBlaster, "cannons/hive_blaster");
		registerRender(cannonHiveHowitzer, "cannons/hive_howitzer");
		registerRender(cannonIroCannon, "cannons/iro_cannon");
		registerRender(cannonJackFunger, "cannons/jack_funger");
		registerRender(cannonJackRocker, "cannons/jack_rocker");
		registerRender(cannonLuxonStickler, "cannons/luxon_stickler");
		registerRender(cannonMechaCannon, "cannons/mecha_cannon");
		registerRender(cannonMiniCannon, "cannons/mini_cannon");
		registerRender(cannonMissileMaker, "cannons/missile_maker");
		registerRender(cannonMoonCannon, "cannons/moon_cannon");
		registerRender(cannonPlutonStickler, "cannons/pluton_stickler");
		registerRender(cannonPredatorianBlaster, "cannons/predatorian_blaster");
		registerRender(cannonPulseCannon, "cannons/pulse_cannon");
		registerRender(cannonRPG, "cannons/rpg");
		registerRender(cannonSelyanStickler, "cannons/selyan_stickler");
		registerRender(cannonShadowBlaster, "cannons/shadow_blaster");
		registerRender(cannonShyreBlaster, "cannons/shyre_blaster");
		registerRender(cannonSmileBlaster, "cannons/smile_blaster");
		registerRender(cannonSuperCannon, "cannons/super_cannon");
		registerRender(cannonUltraCannon, "cannons/ultra_cannon");
		registerRender(cannonVoxCannon, "cannons/vox_cannon");
		registerRender(cannonWaterBalloonBomber, "cannons/water_balloon_bomber");
		registerRender(cannonWitherCannon, "cannons/wither_cannon");
		registerRender(throwableGrenade, "thrown/grenade");
		registerRender(throwableHellfire, "thrown/hellfire");
		registerRender(throwableSliceStar, "thrown/slice_star");
		registerRender(throwableChakram, "thrown/chakram");
		registerRender(throwableVulkram, "thrown/vulkram");
		registerRender(throwableGooBall, "thrown/goo_ball");
		registerRender(throwableRunicBomb, "thrown/runic_bomb");
		registerRender(vulcane, "vulcanes/vulcane");
		registerRender(vulcaneBattle, "vulcanes/battle_vulcane");
		registerRender(vulcaneEquality, "vulcanes/equality_vulcane");
		registerRender(vulcaneFire, "vulcanes/fire_vulcane");
		registerRender(vulcaneImpairment, "vulcanes/impairment_vulcane");
		registerRender(vulcanePoison, "vulcanes/poison_vulcane");
		registerRender(vulcanePower, "vulcanes/power_vulcane");
		registerRender(vulcaneWither, "vulcanes/wither_vulcane");
		registerRender(bowAlacrity, "bows/alacrity_bow");
		registerRender(bowAncient, "bows/ancient_bow");
		registerRender(bowAtlantic, "bows/atlantic_bow");
		registerRender(bowBaron, "bows/baron_bow");
		registerRender(bowBoreic, "bows/boreic_bow");
		registerRender(bowDaybreaker, "bows/daybreaker_bow");
		registerRender(bowDeep, "bows/deep_bow");
		registerRender(bowExplosive, "bows/explosive_bow");
		registerRender(bowHaunted, "bows/haunted_bow");
		registerRender(bowIce, "bows/ice_bow");
		registerRender(bowInfernal, "bows/infernal_bow");
		registerRender(bowJustice, "bows/justice_bow");
		registerRender(bowLunar, "bows/lunar_bow");
		registerRender(bowMecha, "bows/mecha_bow");
		registerRender(bowNightmare, "bows/nightmare_bow");
		registerRender(bowPoison, "bows/poison_bow");
		registerRender(bowPredatious, "bows/predatious_bow");
		registerRender(bowPrimordial, "bows/primordial_bow");
		registerRender(bowRosidian, "bows/rosidian_bow");
		registerRender(bowRunic, "bows/runic_bow");
		registerRender(bowScreamer, "bows/screamer_bow");
		registerRender(bowShyregem, "bows/shyregem_bow");
		registerRender(bowSkeletal, "bows/skeletal_bow");
		registerRender(bowSkydriver, "bows/skydriver_bow");
		registerRender(bowSlingshot, "bows/slingshot");
		registerRender(bowSoulfire, "bows/soulfire_bow");
		registerRender(bowSpectral, "bows/spectral_bow");
		registerRender(bowSpeed, "bows/speed_bow");
		registerRender(bowSunshine, "bows/sunshine_bow");
		registerRender(bowToxin, "bows/toxin_bow");
		registerRender(bowVoid, "bows/void_bow");
		registerRender(bowWeaken, "bows/weaken_bow");
		registerRender(bowWither, "bows/wither_bow");
		registerRender(archergunCoral, "archerguns/coral_archergun");
		registerRender(archergunLunar, "archerguns/lunar_archergun");
		registerRender(archergunMecha, "archerguns/mecha_archergun");
		registerRender(archergunPyro, "archerguns/pyro_archergun");
		registerRender(archergunRosidian, "archerguns/rosidian_archergun");
		registerRender(archergunSkeletal, "archerguns/skeletal_archergun");
		registerRender(archergunSpectral, "archerguns/spectral_archergun");
		registerRender(archergunTrolls, "archerguns/trolls_archergun");
		registerRender(archergunViral, "archerguns/viral_archergun");
		registerRender(staffAquatic, "staves/aquatic_staff");
		registerRender(staffAtlantic, "staves/atlantic_staff");
		registerRender(staffBaron, "staves/baron_staff");
		registerRender(staffCandy, "staves/candy_staff");
		registerRender(staffCelestial, "staves/celestial_staff");
		registerRender(staffConcussion, "staves/concussion_staff");
		registerRender(staffCoral, "staves/coral_staff");
		registerRender(staffCrystal, "staves/crystal_staff");
		registerRender(staffCrystik, "staves/crystik_staff");
		registerRender(staffCryston, "staves/cryston_staff");
		registerRender(staffDestruction, "staves/destruction_staff");
		registerRender(staffEmber, "staves/ember_staff");
		registerRender(staffEverfight, "staves/everfight_staff");
		registerRender(staffEvermight, "staves/evermight_staff");
		registerRender(staffFire, "staves/fire_staff");
		registerRender(staffFirefly, "staves/firefly_staff");
		registerRender(staffFirestorm, "staves/firestorm_staff");
		registerRender(staffFungal, "staves/fungal_staff");
		registerRender(staffGhoul, "staves/ghoul_staff");
		registerRender(staffHaunters, "staves/haunters_staff");
		registerRender(staffHive, "staves/hive_staff");
		registerRender(staffJoker, "staves/joker_staff");
		registerRender(staffKaiyu, "staves/kaiyu_staff");
		registerRender(staffLightning, "staves/lightning_staff");
		registerRender(staffLightshine, "staves/lightshine");
		registerRender(staffLunar, "staves/lunar_staff");
		registerRender(staffLyonic, "staves/lyonic_staff");
		registerRender(staffMecha, "staves/mecha_staff");
		registerRender(staffMeteor, "staves/meteor_staff");
		registerRender(staffMoonlight, "staves/moonlight_staff");
		registerRender(staffNature, "staves/nature_staff");
		registerRender(staffNightmare, "staves/nightmare_staff");
		registerRender(staffNoxious, "staves/noxious_staff");
		registerRender(staffPhantom, "staves/phantom_staff");
		registerRender(staffPoison, "staves/poison_staff");
		registerRender(staffPower, "staves/power_staff");
		registerRender(staffPrimordial, "staves/primordial_staff");
		registerRender(staffReef, "staves/reef_staff");
		registerRender(staffRejuvenation, "staves/rejuvenation_staff");
		registerRender(staffRosidian, "staves/rosidian_staff");
		registerRender(staffRunic, "staves/runic_staff");
		registerRender(staffShadowlord, "staves/shadowlord_staff");
		registerRender(staffShow, "staves/show_staff");
		registerRender(staffShyre, "staves/shyre_staff");
		registerRender(staffSky, "staves/sky_staff");
		registerRender(staffStriker, "staves/striker_staff");
		registerRender(staffSun, "staves/sun_staff");
		registerRender(staffSurge, "staves/surge_staff");
		registerRender(staffTangle, "staves/tangle_staff");
		registerRender(staffUltimatum, "staves/ultimatum_staff");
		registerRender(staffUnderworld, "staves/underworld_staff");
		registerRender(staffWater, "staves/water_staff");
		registerRender(staffWeb, "staves/web_staff");
		registerRender(staffWind, "staves/wind_staff");
		registerRender(staffWither, "staves/wither_staff");
		registerRender(staffWizards, "staves/wizards_staff");
		registerRender(blasterApocoShower, "blasters/apoco_shower");
		registerRender(blasterAtomizer, "blasters/atomizer");
		registerRender(blasterBeamer, "blasters/beamer");
		registerRender(blasterBearBlaster, "blasters/bear_blaster");
		registerRender(blasterBeatSoundCannon, "blasters/beat_sound_cannon");
		registerRender(blasterBeeBlaster, "blasters/bee_blaster");
		registerRender(blasterBlastChiller, "blasters/blast_chiller");
		registerRender(blasterBloodDrainer, "blasters/blood_drainer");
		registerRender(blasterBoneBlaster, "blasters/bone_blaster");
		registerRender(blasterBubbleHorn, "blasters/bubble_horn");
		registerRender(blasterCamelCannon, "blasters/camel_cannon");
		registerRender(blasterColourCannon, "blasters/colour_cannon");
		registerRender(blasterConfettiCannon, "blasters/confetti_cannon");
		registerRender(blasterConfettiCluster, "blasters/confetti_cluster");
		registerRender(blasterDarkDestroyer, "blasters/dark_destroyer");
		registerRender(blasterDarklyGuster, "blasters/darkly_guster");
		registerRender(blasterDeathRay, "blasters/death_ray");
		registerRender(blasterDeerDetonator, "blasters/deer_detonator");
		registerRender(blasterDoomBringer, "blasters/doom_bringer");
		registerRender(blasterDragonDestroyer, "blasters/dragon_destroyer");
		registerRender(blasterElectroSoundCannon, "blasters/electro_sound_cannon");
		registerRender(blasterEradicator, "blasters/eradicator");
		registerRender(blasterFishFryer, "blasters/fish_fryer");
		registerRender(blasterFlowercorne, "blasters/flowercorne");
		registerRender(blasterFragment, "blasters/fragment");
		registerRender(blasterFroster, "blasters/froster");
		registerRender(blasterGasBlaster, "blasters/gas_blaster");
		registerRender(blasterGhoulGasser, "blasters/ghoul_gasser");
		registerRender(blasterGoldBringer, "blasters/gold_bringer");
		registerRender(blasterGravityBlaster, "blasters/gravity_blaster");
		registerRender(blasterHellHorn, "blasters/hell_horn");
		registerRender(blasterHoundHoncho, "blasters/hound_honcho");
		registerRender(blasterIllusionRevolver, "blasters/illusion_revolver");
		registerRender(blasterIllusionSMG, "blasters/illusion_smg");
		registerRender(blasterIonBlaster, "blasters/ion_blaster");
		registerRender(blasterIroMiner, "blasters/iro_miner");
		registerRender(blasterLaserBlaster, "blasters/laser_blaster");
		registerRender(blasterLightBlaster, "blasters/light_blaster");
		registerRender(blasterLightSpark, "blasters/light_spark");
		registerRender(blasterLunaBlaster, "blasters/luna_blaster");
		registerRender(blasterMechaBlaster, "blasters/mecha_blaster");
		registerRender(blasterMindBlaster, "blasters/mind_blaster");
		registerRender(blasterMoonDestroyer, "blasters/moon_destroyer");
		registerRender(blasterMoonShiner, "blasters/moon_shiner");
		registerRender(blasterOdious, "blasters/odious");
		registerRender(blasterOrbocron, "blasters/orbocron");
		registerRender(blasterParalyzer, "blasters/paralyzer");
		registerRender(blasterPartyPopper, "blasters/party_popper");
		registerRender(blasterPenguinBlaster, "blasters/penguin_blaster");
		registerRender(blasterPoisonPlunger, "blasters/poison_plunger");
		registerRender(blasterPowerRay, "blasters/power_ray");
		registerRender(blasterProton, "blasters/proton");
		registerRender(blasterReefer, "blasters/reefer");
		registerRender(blasterRevolution, "blasters/revolution");
		registerRender(blasterSeaocron, "blasters/seaocron");
		registerRender(blasterSkulloBlaster, "blasters/skullo_blaster");
		registerRender(blasterSoulDrainer, "blasters/soul_drainer");
		registerRender(blasterSoulSpark, "blasters/soul_spark");
		registerRender(blasterSoulStorm, "blasters/soul_storm");
		registerRender(blasterSpiritShower, "blasters/spirit_shower");
		registerRender(blasterStepSoundCannon, "blasters/step_sound_cannon");
		registerRender(blasterSwarmotron, "blasters/swarmotron");
		registerRender(blasterSynthSoundCannon, "blasters/synth_sound_cannon");
		registerRender(blasterToxicTerrorizer, "blasters/toxic_terrorizer");
		registerRender(blasterVibeSoundCannon, "blasters/vibe_sound_cannon");
		registerRender(blasterVortexBlaster, "blasters/vortex_blaster");
		registerRender(blasterWhimsyWinder, "blasters/whimsy_winder");
		registerRender(blasterWithersWrath, "blasters/withers_wrath");
	}

	private static void registerRender(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation("aoa3:weapons/" + location), "inventory"));
	}

	public static void registerDispensables() {
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableHellfire, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntityHellfire(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableGrenade, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntityGrenade(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableChakram, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntityChakram(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableGooBall, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntityGooBall(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableRunicBomb, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntityRunicBomb(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableVulkram, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntityVulkram(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WeaponRegister.throwableSliceStar, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntitySliceStar(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.hollyArrow, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntityHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.hollyArrowTipped, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				EntityTippedHollyArrow arrow = new EntityTippedHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());

				arrow.setPotionEffect(stack);
				arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				return arrow;
			}
		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemRegister.hollyArrowSpectral, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
				return new EntitySpectralHollyArrow(world, pos.getX(), pos.getY(), pos.getZ());
			}
		});
	}
}
