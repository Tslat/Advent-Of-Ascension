package net.tslat.aoa3.common.registration;

import net.minecraft.entity.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.SharedConstants;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.animal.*;
import net.tslat.aoa3.content.entity.animal.fish.BasicFishEntity;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.content.entity.boss.*;
import net.tslat.aoa3.content.entity.misc.*;
import net.tslat.aoa3.content.entity.misc.pixon.*;
import net.tslat.aoa3.content.entity.mob.abyss.*;
import net.tslat.aoa3.content.entity.mob.barathos.*;
import net.tslat.aoa3.content.entity.mob.candyland.*;
import net.tslat.aoa3.content.entity.mob.celeve.*;
import net.tslat.aoa3.content.entity.mob.creeponia.*;
import net.tslat.aoa3.content.entity.mob.crystevia.*;
import net.tslat.aoa3.content.entity.mob.deeplands.*;
import net.tslat.aoa3.content.entity.mob.dustopia.*;
import net.tslat.aoa3.content.entity.mob.gardencia.*;
import net.tslat.aoa3.content.entity.mob.greckon.*;
import net.tslat.aoa3.content.entity.mob.haven.*;
import net.tslat.aoa3.content.entity.mob.iromine.*;
import net.tslat.aoa3.content.entity.mob.lborean.*;
import net.tslat.aoa3.content.entity.mob.lelyetia.*;
import net.tslat.aoa3.content.entity.mob.lunalus.*;
import net.tslat.aoa3.content.entity.mob.misc.*;
import net.tslat.aoa3.content.entity.mob.misc.doppelganger.DoppelgangerEntity;
import net.tslat.aoa3.content.entity.mob.mysterium.*;
import net.tslat.aoa3.content.entity.mob.nether.*;
import net.tslat.aoa3.content.entity.mob.nowhere.*;
import net.tslat.aoa3.content.entity.mob.overworld.*;
import net.tslat.aoa3.content.entity.mob.overworld.bigday.*;
import net.tslat.aoa3.content.entity.mob.precasia.*;
import net.tslat.aoa3.content.entity.mob.runandor.*;
import net.tslat.aoa3.content.entity.mob.runandor.templars.*;
import net.tslat.aoa3.content.entity.mob.shyrelands.*;
import net.tslat.aoa3.content.entity.mob.voxponds.*;
import net.tslat.aoa3.content.entity.npc.ambient.*;
import net.tslat.aoa3.content.entity.npc.banker.*;
import net.tslat.aoa3.content.entity.npc.lottoman.*;
import net.tslat.aoa3.content.entity.npc.trader.*;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.content.entity.projectile.arrow.PopShotEntity;
import net.tslat.aoa3.content.entity.projectile.blaster.*;
import net.tslat.aoa3.content.entity.projectile.cannon.*;
import net.tslat.aoa3.content.entity.projectile.gun.*;
import net.tslat.aoa3.content.entity.projectile.misc.*;
import net.tslat.aoa3.content.entity.projectile.mob.*;
import net.tslat.aoa3.content.entity.projectile.staff.*;
import net.tslat.aoa3.content.entity.projectile.thrown.*;
import net.tslat.aoa3.content.entity.tablet.*;
import net.tslat.aoa3.util.ColourUtil;

import java.util.function.Consumer;

public final class AoAEntities {
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, AdventOfAscension.MOD_ID);

	public static class Mobs {
		public static final DeferredRegister<EntityType<?>> MOBS = DeferredRegister.create(ForgeRegistries.ENTITIES, AdventOfAscension.MOD_ID);

		public static final RegistryObject<EntityType<FlyingEntity>> AIRHEAD = registerMob("airhead", AirheadEntity::new, 1.0f, 1.6875f, 8506334, 6004943);
		public static final RegistryObject<EntityType<MonsterEntity>> ALARMO = registerMob("alarmo", AlarmoEntity::new, 0.625f, 1.375f, 14555160, 6048330);
		public static final RegistryObject<EntityType<WaterMobEntity>> AMPHIBIOR = registerMob("amphibior", AmphibiorEntity::new, 0.625f, 2.1875f, 6026738, 16757504);
		public static final RegistryObject<EntityType<WaterMobEntity>> AMPHIBIYTE = registerMob("amphibiyte", AmphibiyteEntity::new, 0.95f, 1.25f, 5140613, 16744695);
		public static final RegistryObject<EntityType<MonsterEntity>> ANCIENT_GOLEM = registerMob("ancient_golem", AncientGolemEntity::new, 0.85f, 2.25f, 3352336, 11373902);
		public static final RegistryObject<EntityType<FlyingEntity>> ANEMIA = registerMob("anemia", AnemiaEntity::new, 2.25f, 3f, 5179400, 16711680);
		public static final RegistryObject<EntityType<AnglerEntity>> ANGLER = registerMob("angler", AnglerEntity::new, 0.75f, 0.875f, 5667476, 8604807);
		public static final RegistryObject<EntityType<MonsterEntity>> APPARITION = registerMob("apparition", ApparitionEntity::new, 0.5f, 1.5625f, 7040112, 526344);
		public static final RegistryObject<EntityType<MonsterEntity>> ARC_FLOWER = registerMob("arc_flower", ArcFlowerEntity::new, 0.6875f, 0.9375f, 5418751, 15073024);
		public static final RegistryObject<EntityType<MonsterEntity>> ARC_WIZARD = registerMob("arc_wizard", ArcWizardEntity::new, 0.6f, 2.3f, 15269714, 4915148);
		public static final RegistryObject<EntityType<ArcbeastEntity>> ARCBEAST = registerMob("arcbeast", ArcbeastEntity::new, 0.8125f, 1.3125f, 13886315, 14869466);
		public static final RegistryObject<EntityType<MonsterEntity>> ARCHVINE = registerMob("archvine", ArchvineEntity::new, 1.5f, 1.4375f, 15616, 1835776);
		public static final RegistryObject<EntityType<MonsterEntity>> ARCWORM = registerMob("arcworm", ArcwormEntity::new, 0.7f, 1.0f, 16768256, 9109501);
		public static final RegistryObject<EntityType<MonsterEntity>> ARIEL = registerMob("ariel", ArielEntity::new, 0.7f, 2.375f, 6809323, 13959166);
		public static final RegistryObject<EntityType<MonsterEntity>> ARKBACK = registerMob("arkback", ArkbackEntity::new, 3.8f, 3f, 12099007, 8943244);
		public static final RegistryObject<EntityType<MonsterEntity>> ARKZYNE = registerMob("arkzyne", ArkzyneEntity::new, 0.5625f, 2.6875f, 789516, 10690061);
		public static final RegistryObject<EntityType<MonsterEntity>> AROCKNID = registerMob("arocknid", ArocknidEntity::new, 1.7f, 1.3125f, 7235689, 13218830);
		public static final RegistryObject<EntityType<MonsterEntity>> AXIOLIGHT = registerMob("axiolight", AxiolightEntity::new, 0.7f, 2.4375f, 16777215, 5820635);
		public static final RegistryObject<EntityType<MonsterEntity>> BANE = registerMob("bane", BaneEntity::new, 0.75f, 2f, 7868105, 4619942);
		public static final RegistryObject<EntityType<MonsterEntity>> BANSHEE = registerMob("banshee", BansheeEntity::new, 0.875f, 2.375f, 11572669, 8202406);
		public static final RegistryObject<EntityType<MonsterEntity>> BARONESS = registerMob("baroness", BaronessEntity::new, 0.6875f, 2.75f, 5377810, 2362949);
		public static final RegistryObject<EntityType<MonsterEntity>> BASILISK = registerMob("basilisk", BasiliskEntity::new, 0.75f, 1.375f, 1644570, 4736842);
		public static final RegistryObject<EntityType<MonsterEntity>> BAUMBA = registerMob("baumba", BaumbaEntity::new, 0.5f, 2f, 9184292, 9071156);
		public static final RegistryObject<EntityType<MonsterEntity>> BLOODSUCKER = registerMob("bloodsucker", BloodsuckerEntity::new, 1.3f, 1.0f, 3673357, 8397860);
		public static final RegistryObject<EntityType<MonsterEntity>> BLUE_FLOWER = registerMob("blue_flower", BlueFlowerEntity::new, 0.7f, 2.3125f, 5086275, 4223129);
		public static final RegistryObject<EntityType<MonsterEntity>> BLUE_GUARDIAN = registerMob("blue_guardian", BlueGuardianEntity::new, 1.5f, 2.625f, 10266029, 8167615);
		public static final RegistryObject<EntityType<RuneTemplarEntity>> BLUE_RUNE_TEMPLAR = registerMob("blue_rune_templar", BlueRuneTemplarEntity::new, 1.125f, 2f, 16744228, 7194313);
		public static final RegistryObject<EntityType<MonsterEntity>> BLUE_RUNIC_LIFEFORM = registerMob("blue_runic_lifeform", BlueRunicLifeformEntity::new, 0.75f, 0.99f, 496022, 463763);
		public static final RegistryObject<EntityType<MonsterEntity>> BOBO = registerMob("bobo", BoboEntity::new, 0.6f, 2.125f, 1782866, 2249377);
		public static final RegistryObject<EntityType<MonsterEntity>> BOMB_CARRIER = registerMob("bomb_carrier", BombCarrierEntity::new, 0.6f, 1.5f, 11382918, 2037510);
		public static final RegistryObject<EntityType<AoACreeponiaCreeper>> BONE_CREEPER = registerMob("bone_creeper", BoneCreeperEntity::new, 0.5f, 1.625f, 10922149, 2915064);
		public static final RegistryObject<EntityType<BonebackEntity>> BONEBACK = registerMob("boneback", BonebackEntity::new, 0.8125f, 1f, 6182726, 3052700);
		public static final RegistryObject<EntityType<MonsterEntity>> BOUNCER = registerMob("bouncer", BouncerEntity::new, 0.5f, 1.4375f, 5537210, 13685462);
		public static final RegistryObject<EntityType<MonsterEntity>> BROCCOHEAD = registerMob("broccohead", BroccoheadEntity::new, 0.5625f, 2.3625f, 2511901, 5668175);
		public static final RegistryObject<EntityType<BugeyeEntity>> BUGEYE = registerMob("bugeye", BugeyeEntity::new, 1f, 1.25f, 992269, 7243115);
		public static final RegistryObject<EntityType<BushBabyEntity>> BUSH_BABY = registerMob("bush_baby", BushBabyEntity::new, 0.85f, 0.9375f, 7243115, 5721387);
		public static final RegistryObject<EntityType<MonsterEntity>> CANDY_CORNY = registerMob("candy_corny", CandyCornyEntity::new, 0.625f, 2f, 13932049, 15459377);
		public static final RegistryObject<EntityType<MonsterEntity>> CANE_BUG = registerMob("cane_bug", CaneBugEntity::new, 1f, 1.5f, 4352288, 16119280);
		public static final RegistryObject<EntityType<MonsterEntity>> CARROTOP = registerMob("carrotop", CarrotopEntity::new, 0.5625f, 2.375f, 16747528, 4914952);
		public static final RegistryObject<EntityType<MonsterEntity>> CASE_CONSTRUCT = registerMob("case_construct", CaseConstructEntity::new, true, 1.2f, 2.125f, 8026483, 13615203);
		public static final RegistryObject<EntityType<MonsterEntity>> CAVE_CREEP = registerMob("cave_creep", CaveCreepEntity::new, 1f, 1.5f, 8026483, 11579307);
		public static final RegistryObject<EntityType<AoACreeponiaCreeper>> CAVE_CREEPOID = registerMob("cave_creepoid", CaveCreepoidEntity::new, 0.875f, 1.6875f, 3684406, 8026744);
		public static final RegistryObject<EntityType<MonsterEntity>> CENTINEL = registerMob("centinel", CentinelEntity::new, 0.6875f, 1.625f, 3951159, 4606532);
		public static final RegistryObject<EntityType<MonsterEntity>> CHARGER = registerMob("charger", ChargerEntity::new, 0.625f, 1.5f, 14081596, 14081596);
		public static final RegistryObject<EntityType<MonsterEntity>> CHERRY_BLASTER = registerMob("cherry_blaster", CherryBlasterEntity::new, 0.875f, 1.0625f, 14032414, 5400890);
		public static final RegistryObject<EntityType<MonsterEntity>> CHIMERA = registerMob("chimera", ChimeraEntity::new, 1f, 1.375f, 14736043, 4206102);
		public static final RegistryObject<EntityType<MonsterEntity>> CHOCKO = registerMob("chocko", ChockoEntity::new, 0.6f, 2.0f, 8004884, 3346956);
		public static final RegistryObject<EntityType<ChomperEntity>> CHOMPER = registerMob("chomper", ChomperEntity::new, 0.8f, 0.875f, 4479279, 2043409);
		public static final RegistryObject<EntityType<MonsterEntity>> CLUNKHEAD = registerMob("clunkhead", ClunkheadEntity::new, 1f, 2.0625f, 1393533, 6322068);
		public static final RegistryObject<EntityType<FlyingEntity>> CONSTRUCT_OF_FLIGHT = registerMob("construct_of_flight", ConstructOfFlightEntity::new, 0.7f, 0.84375f, 5033917, 1382167);
		public static final RegistryObject<EntityType<MonsterEntity>> CONSTRUCT_OF_MIND = registerMob("construct_of_mind", ConstructOfMindEntity::new, 2f, 2f, 1711130, 4298362);
		public static final RegistryObject<EntityType<MonsterEntity>> CONSTRUCT_OF_RANGE = registerMob("construct_of_range", ConstructOfRangeEntity::new, 1.7f, 1.5f, 6427013, 789261);
		public static final RegistryObject<EntityType<MonsterEntity>> CONSTRUCT_OF_RESISTANCE = registerMob("construct_of_resistance", ConstructOfResistanceEntity::new, 0.625f, 2.375f, 1002578, 3486008);
		public static final RegistryObject<EntityType<MonsterEntity>> CONSTRUCT_OF_SPEED = registerMob("construct_of_speed", ConstructOfSpeedEntity::new, 0.625f, 2.1875f, 11768628, 6907225);
		public static final RegistryObject<EntityType<MonsterEntity>> CONSTRUCT_OF_STRENGTH = registerMob("construct_of_strength", ConstructOfStrengthEntity::new, 1f, 2.375f, 11021588, 3354157);
		public static final RegistryObject<EntityType<FlyingEntity>> CONSTRUCT_OF_TERROR = registerMob("construct_of_terror", ConstructOfTerrorEntity::new, 1.0f, 1.0f, 4299043, 3354157);
		public static final RegistryObject<EntityType<CorallusEntity>> CORALLUS = registerMob("corallus", CorallusEntity::new, 0.75f, 2.875f, 36307, 11862175);
		public static final RegistryObject<EntityType<MonsterEntity>> CORNY = registerMob("corny", CornyEntity::new, 0.625f, 2f, 35104, 13551360);
		public static final RegistryObject<EntityType<CottonCandorEntity>> COTTON_CANDOR = registerMob("cotton_candor", CottonCandorEntity::new, 1.5f, 2.375f, 13990125, 12926954);
		public static final RegistryObject<EntityType<FlyingEntity>> CRAEXXEUS = registerMob("craexxeus", CraexxeusEntity::new, 3.5f, 4.4375f, 14847254, 2876115);
		public static final RegistryObject<EntityType<MonsterEntity>> CREEP = registerMob("creep", CreepEntity::new, 0.7f, 1.75f, 818944, 4608621);
		public static final RegistryObject<EntityType<AoACreeponiaCreeper>> CREEPERLOCK = registerMob("creeperlock", CreeperlockEntity::new, 0.6f, 2.37f, 818944, 5777591);
		public static final RegistryObject<EntityType<FlyingEntity>> CREEPIRD = registerMob("creepird", CreepirdEntity::new, 0.5f, 0.6875f, 818944, 3780390);
		public static final RegistryObject<EntityType<AoACreeponiaCreeper>> CREEPUPLE = registerMob("creepuple", CreepupleEntity::new, 0.6f, 1.5625f, 818944, 3843364);
		public static final RegistryObject<EntityType<MonsterEntity>> CRUSILISK = registerMob("crusilisk", CrusiliskEntity::new, 0.75f, 1.3125f, 660523, 15569979);
		public static final RegistryObject<EntityType<MonsterEntity>> CRYPTID = registerMob("cryptid", CryptidEntity::new, true, 0.875f, 1.0625f, 10038339, 10180711);
		public static final RegistryObject<EntityType<CrystocoreEntity>> CRYSTOCORE = registerMob("crystocore", CrystocoreEntity::new, 2.5f, 4.875f, 1315589, 3812640);
		public static final RegistryObject<EntityType<MonsterEntity>> CYCLOPS = registerMob("cyclops", CyclopsEntity::new, 0.6f, 2.25f, 7500352, 11645566);
		public static final RegistryObject<EntityType<MonsterEntity>> DAYSEE = registerMob("daysee", DayseeEntity::new, 0.5f, 2.0625f, 11913662, 6272881);
		public static final RegistryObject<EntityType<MonsterEntity>> DEAD_TREE = registerMob("dead_tree", DeadTreeEntity::new, 0.875f, 3f, 4470537, 5192459);
		public static final RegistryObject<EntityType<MonsterEntity>> DEINOTHERIUM = registerMob("deinotherium", DeinotheriumEntity::new, 1.7f, 3f, 12040114, 10855832);
		public static final RegistryObject<EntityType<MonsterEntity>> DESERT_CHARGER = registerMob("desert_charger", DesertChargerEntity::new, 0.625f, 1.5f, 8487996, 10194503);
		public static final RegistryObject<EntityType<MonsterEntity>> DESTRUCTOR = registerMob("destructor", DestructorEntity::new, 2.2f, 8.53125f, 2895664, 10696493);
		public static final RegistryObject<EntityType<MonsterEntity>> DEVOURER = registerMob("devourer", DevourerEntity::new, 0.875f, 1.1875f, 1249827, 3750461);
		public static final RegistryObject<EntityType<MonsterEntity>> DIOCUS = registerMob("diocus", DiocusEntity::new, 0.75f, 1f, 5521679, 8994198);
		public static final RegistryObject<EntityType<MonsterEntity>> DISTORTER = registerMob("distorter", DistorterEntity::new, 0.6f, 2.125f, 1579066, 2841448);
		public static final RegistryObject<EntityType<MonsterEntity>> DOUBLER = registerMob("doubler", DoublerEntity::new, 1.3f, 2.25f, 2899233, 2634530);
		public static final RegistryObject<EntityType<FlyingEntity>> DRACYON = registerMob("dracyon", DracyonEntity::new, 1.4f, 1.3125f, 402812, 3553336);
		public static final RegistryObject<EntityType<DoppelgangerEntity>> DOPPELGANGER = registerMob("doppelganger", DoppelgangerEntity::new, 0.6F, 1.8F, 0, ColourUtil.WHITE);
		public static final RegistryObject<EntityType<MonsterEntity>> DUST_STRIDER = registerMob("dust_strider", DustStriderEntity::new, 0.8f, 1.25f, 986902, 8662823);
		public static final RegistryObject<EntityType<MonsterEntity>> DUSTEIVA = registerMob("dusteiva", DusteivaEntity::new, 0.6f, 2.25f, 3150604, 10037031);
		public static final RegistryObject<EntityType<FlyingEntity>> DUSTON = registerMob("duston", DustonEntity::new, 0.6f, 1.5f, 14027274, 857601);
		public static final RegistryObject<EntityType<MonsterEntity>> DWELLER = registerMob("dweller", DwellerEntity::new, 0.625f, 2.4f, 5197117, 3225407);
		public static final RegistryObject<EntityType<MonsterEntity>> DYREHORN = registerMob("dyrehorn", DyrehornEntity::new, 1f, 2f, 12361818, 6317647);
		public static final RegistryObject<EntityType<FlyingEntity>> ECHODAR = registerMob("echodar", EchodarEntity::new, 0.75f, 0.75f, 9388691, 11026825);
		public static final RegistryObject<EntityType<MonsterEntity>> ELITE_SKELE_HOPPER = registerMob("elite_skele_hopper", EliteSkeleHopperEntity::new, 0.8f, 1.6875f, 7375232, 5462374);
		public static final RegistryObject<EntityType<MonsterEntity>> ELITE_SKELE_PIG = registerMob("elite_skele_pig", EliteSkelePigEntity::new, 0.7f, 1.1875f, 6776173, 10196879);
		public static final RegistryObject<EntityType<MonsterEntity>> ELITE_SKELEMAN = registerMob("elite_skeleman", EliteSkelemanEntity::new, 0.8f, 1.75f, 11446967, 5133390);
		public static final RegistryObject<EntityType<MonsterEntity>> ELUSIVE = registerMob("elusive", ElusiveEntity::new, 0.7f, 1.625f, 1121630, 1711428);
		public static final RegistryObject<EntityType<MonsterEntity>> ELUSIVE_CLONE = registerMob("elusive_clone", ElusiveCloneEntity::new, 0.7f, 1.625f);
		public static final RegistryObject<EntityType<MonsterEntity>> EMBRAKE = registerMob("embrake", EmbrakeEntity::new, true, 0.75f, 1f, 986632, 5967104);
		public static final RegistryObject<EntityType<MonsterEntity>> EMPEROR_BEAST = registerMob("emperor_beast", EmperorBeastEntity::new, 1.7f, 6.8f, 3540548, 7480842);
		public static final RegistryObject<EntityType<MonsterEntity>> ENFORCER = registerMob("enforcer", EnforcerEntity::new, 0.75f, 2.25f, 1646128, 3949091);
		public static final RegistryObject<EntityType<MonsterEntity>> EVERBEAST = registerMob("everbeast", EverbeastEntity::new, 1.4f, 1.75f, 8874602, 8987427);
		public static final RegistryObject<EntityType<MonsterEntity>> EXOHEAD = registerMob("exohead", ExoheadEntity::new, 0.6f, 1.8125f, 1841431, 6500657);
		public static final RegistryObject<EntityType<FlyingEntity>> EXPLODOT = registerMob("explodot", ExplodotEntity::new, 0.6f, 1f, 1718972, 6452410);
		public static final RegistryObject<EntityType<MonsterEntity>> FACELESS_FLOATER = registerMob("faceless_floater", FacelessFloaterEntity::new, 0.75f, 2f, 5187949, 5784171);
		public static final RegistryObject<EntityType<MonsterEntity>> FAKE_ZORP = registerMob("fake_zorp", FakeZorpEntity::new, 0.6f, 1.875f);
		public static final RegistryObject<EntityType<MonsterEntity>> FENIX = registerMob("fenix", FenixEntity::new, 0.5f, 1.8125f, 5264212, 7370096);
		public static final RegistryObject<EntityType<MonsterEntity>> FIEND = registerMob("fiend", FiendEntity::new, 0.5625f, 1.5f, 4790796, 4662298);
		public static final RegistryObject<EntityType<WaterMobEntity>> FISCHER = registerMob("fischer", FischerEntity::new, 0.75f, 1.0f, 3156005, 11349293);
		public static final RegistryObject<EntityType<MonsterEntity>> FISHIX = registerMob("fishix", FishixEntity::new, 1.2f, 1.95f, 3819793, 4086071);
		public static final RegistryObject<EntityType<MonsterEntity>> FLAMEWALKER = registerMob("flamewalker", FlamewalkerEntity::new, true, 0.8f, 2.25f, 14354954, 14049890);
		public static final RegistryObject<EntityType<MonsterEntity>> FLASH = registerMob("flash", FlashEntity::new, 0.8f, 2f, 2828311, 6315292);
		public static final RegistryObject<EntityType<MonsterEntity>> FLESH_EATER = registerMob("flesh_eater", FleshEaterEntity::new, 1f, 1.25f, 2827535, 9646899);
		public static final RegistryObject<EntityType<MonsterEntity>> FLOWERFACE = registerMob("flowerface", FlowerfaceEntity::new, 0.5f, 1.5f, 4229424, 11025567);
		public static final RegistryObject<EntityType<FlyingEntity>> FLYE = registerMob("flye", FlyeEntity::new, 1.75f, 1.75f, 15363328, 16577827);
		public static final RegistryObject<EntityType<FlyingEntity>> FUNGAT = registerMob("fungat", FungatEntity::new, 0.75f, 1.125f, 1820193, 7281338);
		public static final RegistryObject<EntityType<MonsterEntity>> FUNGBACK = registerMob("fungback", FungbackEntity::new, 1.0f, 0.875f, 16578797, 4211836);
		public static final RegistryObject<EntityType<MonsterEntity>> FUNGIK = registerMob("fungik", FungikEntity::new, 0.875f, 2.5f, 16578797, 4093269);
		public static final RegistryObject<EntityType<MonsterEntity>> FUNGOCK = registerMob("fungock", FungockEntity::new, 0.875f, 2.125f, 8552047, 7166001);
		public static final RegistryObject<EntityType<MonsterEntity>> FUNGUNG = registerMob("fungung", FungungEntity::new, 0.625f, 2.1875f, 16578797, 2247333);
		public static final RegistryObject<EntityType<MonsterEntity>> FURLION = registerMob("furlion", FurlionEntity::new, 1.0f, 1f, 15724013, 15557475);
		public static final RegistryObject<EntityType<MonsterEntity>> GADGETOID = registerMob("gadgetoid", GadgetoidEntity::new, 1f, 1.8125f, 7037752, 12855593);
		public static final RegistryObject<EntityType<MonsterEntity>> GHASTUS = registerMob("ghastus", GhastusEntity::new, 0.5f, 0.8125f, 4999750, 16380643);
		public static final RegistryObject<EntityType<MonsterEntity>> GHOST = registerMob("ghost", GhostEntity::new, 0.8f, 2f, 15462366, 15134455);
		public static final RegistryObject<EntityType<MonsterEntity>> GIANT_SNAIL = registerMob("giant_snail", GiantSnailEntity::new, 1.2f, 1.5625f, 15589570, 15394116);
		public static final RegistryObject<EntityType<FlyingEntity>> GINGERBIRD = registerMob("gingerbird", GingerbirdEntity::new, true, 0.5f, 0.5625f, 7818256, 2979202);
		public static final RegistryObject<EntityType<MonsterEntity>> GINGERBREAD_MAN = registerMob("gingerbread_man", GingerbreadManEntity::new, true, 0.59375f, 2.125f, 7818256, 7291276);
		public static final RegistryObject<EntityType<MonsterEntity>> GOALBY = registerMob("goalby", GoalbyEntity::new, 1.2f, 2.5f, 8950675, 6661027);
		public static final RegistryObject<EntityType<MonsterEntity>> GOBLIN = registerMob("goblin", GoblinEntity::new, 0.6f, 1.8f, 6710824, 5060198);
		public static final RegistryObject<EntityType<MonsterEntity>> GOLDUM = registerMob("goldum", GoldumEntity::new, 0.6f, 1.5f, 9011712, 1711127);
		public static final RegistryObject<EntityType<MonsterEntity>> GOLDUS = registerMob("goldus", GoldusEntity::new, 0.6f, 1.5f, 11373338, 1711127);
		public static final RegistryObject<EntityType<FlyingEntity>> GRAW = registerMob("graw", GrawEntity::new, 5f, 4f, 16738816, 16764928);
		public static final RegistryObject<EntityType<MonsterEntity>> GREEN_FLOWER = registerMob("green_flower", GreenFlowerEntity::new, 0.7f, 2.3125f, 2742453, 4435278);
		public static final RegistryObject<EntityType<MonsterEntity>> GREEN_GUARDIAN = registerMob("green_guardian", GreenGuardianEntity::new, 1.5f, 2.625f, 4148291, 2937123);
		public static final RegistryObject<EntityType<RuneTemplarEntity>> GREEN_RUNE_TEMPLAR = registerMob("green_rune_templar", GreenRuneTemplarEntity::new, 1.125f, 2f, 6187108, 1645596);
		public static final RegistryObject<EntityType<MonsterEntity>> GREEN_RUNIC_LIFEFORM = registerMob("green_runic_lifeform", GreenRunicLifeformEntity::new, 0.75f, 0.99f, 13485, 41051);
		public static final RegistryObject<EntityType<MonsterEntity>> GRILLFACE = registerMob("grillface", GrillfaceEntity::new, 0.6875f, 2.25f, 2562160, 10820175);
		public static final RegistryObject<EntityType<MonsterEntity>> GROBBLER = registerMob("grobbler", GrobblerEntity::new, 1.5f, 2.375f, 14374157, 2168079);
		public static final RegistryObject<EntityType<FlyingEntity>> GYRO = registerMob("gyro", GyroEntity::new, 1.375f, 1.625f, 16713479, 2895148);
		public static final RegistryObject<EntityType<MonsterEntity>> HAG = registerMob("hag", HagEntity::new, 0.6f, 1.75f, 2835486, 4938051);
		public static final RegistryObject<EntityType<MonsterEntity>> HAPPY = registerMob("happy", HappyEntity::new, 0.5f, 2f, 5376265, 14597565);
		public static final RegistryObject<EntityType<MonsterEntity>> HARKOS = registerMob("harkos", HarkosEntity::new, 0.7f, 2.375f, 5198157, 5312786);
		public static final RegistryObject<EntityType<MonsterEntity>> HIDING_FUNGI = registerMob("hiding_fungi", HidingFungiEntity::new, 1.0f, 1.0f, 7679, 13028863);
		public static final RegistryObject<EntityType<MonsterEntity>> HILL_CHARGER = registerMob("hill_charger", HillChargerEntity::new, 0.625f, 1.5f, 11362333, 8340088);
		public static final RegistryObject<EntityType<HiveKingEntity>> HIVE_KING = registerMob("hive_king", HiveKingEntity::new, 1.2f, 1.5f, 13211587, 15340050);
		public static final RegistryObject<EntityType<MonsterEntity>> HIVE_WORKER = registerMob("hive_worker", HiveWorkerEntity::new, 1.0f, 1.8f, 13211587, 3675965);
		public static final RegistryObject<EntityType<MonsterEntity>> HORNDRON = registerMob("horndron", HorndronEntity::new, 1.7f, 2.2f, 3482377, 11377022);
		public static final RegistryObject<EntityType<MonsterEntity>> HOST = registerMob("host", HostEntity::new, 2.1875f, 2.0625f, 4629316, 11321260);
		public static final RegistryObject<EntityType<MonsterEntity>> HUNTER = registerMob("hunter", HunterEntity::new, 1.3f, 1.3f, 3152742, 4007949);
		public static final RegistryObject<EntityType<IceGiantEntity>> ICE_GIANT = registerMob("ice_giant", IceGiantEntity::new, 1.25f, 3.59375f, 9149372, 7045549);
		public static final RegistryObject<EntityType<MonsterEntity>> INFERNAL = registerMob("infernal", InfernalEntity::new, true, 1.125f, 1.875f, 7151133, 14848287);
		public static final RegistryObject<EntityType<MonsterEntity>> INMATE_X = registerMob("inmate_x", InmateXEntity::new, 0.6f, 2f, 14165970, 15138567);
		public static final RegistryObject<EntityType<MonsterEntity>> INMATE_Y = registerMob("inmate_y", InmateYEntity::new, 0.75f, 1.75f, 792825, 10513678);
		public static final RegistryObject<EntityType<MonsterEntity>> IOSAUR = registerMob("iosaur", IosaurEntity::new, 1f, 1.75f, 1071378, 3919422);
		public static final RegistryObject<EntityType<MonsterEntity>> JAWE = registerMob("jawe", JaweEntity::new, 0.8f, 0.9f, 6825253, 6838617);
		public static final RegistryObject<EntityType<MonsterEntity>> JUMBO = registerMob("jumbo", JumboEntity::new, 0.75f, 2.625f, 9197854, 15723594);
		public static final RegistryObject<EntityType<MonsterEntity>> KAIYU = registerMob("kaiyu", KaiyuEntity::new, 0.75f, 1.125f, 13873991, 15259030);
		public static final RegistryObject<EntityType<MonsterEntity>> KAJAROS = registerMob("kajaros", KajarosEntity::new, 0.7f, 2.375f, 3351588, 9189689);
		public static final RegistryObject<EntityType<MonsterEntity>> KEELER = registerMob("keeler", KeelerEntity::new, 0.6875f, 1.4f, 9271950, 10507941);
		public static final RegistryObject<EntityType<MonsterEntity>> KING_BAMBAMBAM = registerMob("king_bambambam", KingBambambamEntity::new, 1.2f, 2f, 7673107, 11365161);
		public static final RegistryObject<EntityType<MonsterEntity>> KING_CHARGER = registerMob("king_charger", KingChargerEntity::new, 1.25f, 3.25f, 12209209, 11181595);
		public static final RegistryObject<EntityType<AoACreeponiaCreeper>> KING_CREEPER = registerMob("king_creeper", KingCreeperEntity::new, 0.6f, 1.9375f, 2448937, 8348682);
		public static final RegistryObject<EntityType<MonsterEntity>> KING_SHROOMUS = registerMob("king_shroomus", KingShroomusEntity::new, 0.875f, 3.25f, 12368043, 10167758);
		public static final RegistryObject<EntityType<MonsterEntity>> KLOBBER = registerMob("klobber", KlobberEntity::new, 0.8f, 2f, 3158044, 14211163);
		public static final RegistryObject<EntityType<MonsterEntity>> KOKO = registerMob("koko", KokoEntity::new, 0.6f, 2.0f, 16189444, 15569811);
		public static final RegistryObject<EntityType<MonsterEntity>> KRANKY = registerMob("kranky", KrankyEntity::new, 0.6f, 2.3f, 325397, 15569811);
		public static final RegistryObject<EntityType<MonsterEntity>> KROR = registerMob("kror", KrorEntity::new, 1.75f, 3.75f, 8488834, 13620176);
		public static final RegistryObject<EntityType<LeafyGiantEntity>> LEAFY_GIANT = registerMob("leafy_giant", LeafyGiantEntity::new, 1.25f, 3.75f, 149780, 3153154);
		public static final RegistryObject<EntityType<MonsterEntity>> LELYETIAN_CASTER = registerMob("lelyetian_caster", LelyetianCasterEntity::new, 0.6f, 2.375f, 12330277, 14413608);
		public static final RegistryObject<EntityType<MonsterEntity>> LELYETIAN_WARRIOR = registerMob("lelyetian_warrior", LelyetianWarriorEntity::new, 0.6f, 2.375f, 12330277, 13489046);
		public static final RegistryObject<EntityType<MonsterEntity>> LIGHTWALKER = registerMob("lightwalker", LightwalkerEntity::new, 1.0f, 1.5625f, 7765779, 2895137);
		public static final RegistryObject<EntityType<MonsterEntity>> LITTLE_BAM = registerMob("little_bam", LittleBamEntity::new, 0.7f, 1f, 6293512, 8345864);
		public static final RegistryObject<EntityType<MonsterEntity>> LIVING_FUNGI = registerMob("living_fungi", LivingFungiEntity::new, 1.1f, 2.4375f, 1705678, 15000557);
		public static final RegistryObject<EntityType<MonsterEntity>> LOLLYPOPPER = registerMob("lollypopper", LollypopperEntity::new, 1.0625f, 2.4375f, 13387173, 14069706);
		public static final RegistryObject<EntityType<MonsterEntity>> LOST_SOUL = registerMob("lost_soul", LostSoulEntity::new, 0.6f, 2.0f, 1578521, 11774647);
		public static final RegistryObject<EntityType<FlyingEntity>> LUNARCHER = registerMob("lunarcher", LunarcherEntity::new, true, 0.6f, 2.0f, 7932315, 13991919);
		public static final RegistryObject<EntityType<MonsterEntity>> LURKER = registerMob("lurker", LurkerEntity::new, 0.875f, 2.0625f, 525578, 14354440);
		public static final RegistryObject<EntityType<MonsterEntity>> LUXOCRON = registerMob("luxocron", LuxocronEntity::new, 0.6875f, 0.9375f, 14871822, 6932197);
		public static final RegistryObject<EntityType<AoACreeponiaCreeper>> MAGICAL_CREEPER = registerMob("magical_creeper", MagicalCreeperEntity::new, 0.6f, 2.37f, 350480, 2765611);
		public static final RegistryObject<EntityType<MonsterEntity>> MECHACHRON = registerMob("mechachron", MechachronEntity::new, 2f, 1.875f, 2369576, 15920978);
		public static final RegistryObject<EntityType<MonsterEntity>> MECHAMATON = registerMob("mechamaton", MechamatonEntity::new, 1.125f, 2.125f, 9340427, 131585);
		public static final RegistryObject<EntityType<MonsterEntity>> MECHBOT = registerMob("mechbot", MechbotEntity::new, 0.9f, 2.4375f, 13882126, 1644815);
		public static final RegistryObject<EntityType<MonsterEntity>> MECHYON = registerMob("mechyon", MechyonEntity::new, 0.75f, 1.5f, 3355435, 4013324);
		public static final RegistryObject<EntityType<MonsterEntity>> MERKYRE = registerMob("merkyre", MerkyreEntity::new, 0.5625f, 2f, 2500131, 1835779);
		public static final RegistryObject<EntityType<WaterMobEntity>> MERMAGE = registerMob("mermage", MermageEntity::new, 0.6f, 2.125f, 262786, 1756891);
		public static final RegistryObject<EntityType<MonsterEntity>> MIRAGE = registerMob("mirage", MirageEntity::new, 0.8f, 2f, 13552553, 1447439);
		public static final RegistryObject<EntityType<MonsterEntity>> MISKEL = registerMob("miskel", MiskelEntity::new, 0.7f, 2.375f, 10197910, 7021604);
		public static final RegistryObject<EntityType<FlyingEntity>> MODULO = registerMob("modulo", ModuloEntity::new, 1.0f, 1.2f, 5282713, 9060540);
		public static final RegistryObject<EntityType<MonsterEntity>> MOTHER_VOID_WALKER = registerMob("mother_void_walker", MotherVoidWalkerEntity::new, 1.0f, 1.62f, 591882, 3946559);
		public static final RegistryObject<EntityType<MuckopedeEntity>> MUCKOPEDE = registerMob("muckopede", MuckopedeEntity::new, 1.0625f, 0.75f, 9338466, 6709851);
		public static final RegistryObject<EntityType<MuncherEntity>> MUNCHER = registerMob("muncher", MuncherEntity::new, 1.0f, 0.5625f, 660405, 10430127);
		public static final RegistryObject<EntityType<MonsterEntity>> MUSHROOM_SPIDER = registerMob("mushroom_spider", MushroomSpiderEntity::new, 1.4f, 0.8125f, 1739049, 12827332);
		public static final RegistryObject<EntityType<NeptunoEntity>> NEPTUNO = registerMob("neptuno", NeptunoEntity::new, 0.85f, 2.875f, 533611, 1582407);
		public static final RegistryObject<EntityType<MonsterEntity>> NETHENGEIC_BEAST = registerMob("nethengeic_beast", NethengeicBeastEntity::new, true, 1.0f, 1.125f, 3343364, 13834007);
		public static final RegistryObject<EntityType<NethengeicWitherEntity>> NETHENGEIC_WITHER = registerMob("nethengeic_wither", NethengeicWitherEntity::new, true, 1.3f, 3.28125f, 5506309, 11385897);
		public static final RegistryObject<EntityType<NightReaperEntity>> NIGHT_REAPER = registerMob("night_reaper", NightReaperEntity::new, 0.6875f, 1.8125f, 1117453, 11975851);
		public static final RegistryObject<EntityType<NightflyEntity>> NIGHTFLY = registerMob("nightfly", NightflyEntity::new, 0.8125f, 0.8125f, 1315859, 12566398);
		public static final RegistryObject<EntityType<MonsterEntity>> NIGHTMARE_SPIDER = registerMob("nightmare_spider", NightmareSpiderEntity::new, 1.4f, 0.8125f, 1574189, 5180318);
		public static final RegistryObject<EntityType<FlyingEntity>> NIGHTWING = registerMob("nightwing", NightwingEntity::new, 0.75f, 0.9375f, 4012325, 6298909);
		public static final RegistryObject<EntityType<MonsterEntity>> NIPPER = registerMob("nipper", NipperEntity::new, 0.4375f, 0.4f, 12566447, 15395527);
		public static final RegistryObject<EntityType<MonsterEntity>> NOSPIKE = registerMob("nospike", NospikeEntity::new, 1.0f, 1.25f, 11509027, 3683623);
		public static final RegistryObject<EntityType<MonsterEntity>> OCCULENT = registerMob("occulent", OcculentEntity::new, 0.6f, 1.5f, 4802884, 3671558);
		public static final RegistryObject<EntityType<MonsterEntity>> OKAZOR = registerMob("okazor", OkazorEntity::new, 0.7f, 2.375f, 5195591, 4982535);
		public static final RegistryObject<EntityType<OmnilightEntity>> OMNILIGHT = registerMob("omnilight", OmnilightEntity::new, 0.9f, 0.9f, 14540080, 12566365);
		public static final RegistryObject<EntityType<MonsterEntity>> OPTERYX = registerMob("opteryx", OpteryxEntity::new, 0.5f, 1.375f, 11381054, 2779311);
		public static final RegistryObject<EntityType<MonsterEntity>> ORANGE_FLOWER = registerMob("orange_flower", OrangeFlowerEntity::new, 0.7f, 2.3125f, 821547, 12878353);
		public static final RegistryObject<EntityType<MonsterEntity>> PALADIN = registerMob("paladin", PaladinEntity::new, 0.6875f, 2.0f, 15921889, 2019053);
		public static final RegistryObject<EntityType<MonsterEntity>> PARASECT = registerMob("parasect", ParasectEntity::new, 0.6f, 1.375f, 5011275, 2481439);
		public static final RegistryObject<EntityType<MonsterEntity>> PARAVITE = registerMob("paravite", ParaviteEntity::new, 0.625f, 1.75f, 16535301, 16552455);
		public static final RegistryObject<EntityType<WaterMobEntity>> PINCHER = registerMob("pincher", PincherEntity::new, 1f, 0.75f, 467791, 1296854);
		public static final RegistryObject<EntityType<MonsterEntity>> POD_PLANT = registerMob("pod_plant", PodPlantEntity::new, 0.7f, 0.625f, 1307185, 13882270);
		public static final RegistryObject<EntityType<FlyingEntity>> POLYTOM = registerMob("polytom", PolytomEntity::new, 1f, 1.125f, 7491330, 1972754);
		public static final RegistryObject<EntityType<MonsterEntity>> PRIMITIVE_CARROTOP = registerMob("primitive_carrotop", PrimitiveCarrotopEntity::new, 0.5625f, 2.375f, 11447170, 2455829);
		public static final RegistryObject<EntityType<MonsterEntity>> PROSHIELD = registerMob("proshield", ProshieldEntity::new, 0.8f, 2f, 14146468, 10660420);
		public static final RegistryObject<EntityType<MonsterEntity>> PURPLE_FLOWER = registerMob("purple_flower", PurpleFlowerEntity::new, 0.7f, 2.3125f, 2218771, 6757290);
		public static final RegistryObject<EntityType<MonsterEntity>> RAMRADON = registerMob("ramradon", RamradonEntity::new, 0.875f, 1f, 8365446, 8028027);
		public static final RegistryObject<EntityType<MonsterEntity>> RAWBONE = registerMob("rawbone", RawboneEntity::new, 0.625f, 1.0625f, 12561595, 14731739);
		public static final RegistryObject<EntityType<MonsterEntity>> RAXXAN = registerMob("raxxan", RaxxanEntity::new, 0.7f, 2.375f, 5195597, 7023706);
		public static final RegistryObject<EntityType<MonsterEntity>> REAVER = registerMob("reaver", ReaverEntity::new, 0.375f, 2.5f, 1443591, 10827318);
		public static final RegistryObject<EntityType<MonsterEntity>> RED_GUARDIAN = registerMob("red_guardian", RedGuardianEntity::new, 1.5f, 2.625f, 7829107, 9646642);
		public static final RegistryObject<EntityType<RuneTemplarEntity>> RED_RUNE_TEMPLAR = registerMob("red_rune_templar", RedRuneTemplarEntity::new, 1.125f, 2f, 5328462, 12654873);
		public static final RegistryObject<EntityType<MonsterEntity>> RED_RUNIC_LIFEFORM = registerMob("red_runic_lifeform", RedRunicLifeformEntity::new, 0.75f, 0.99f, 730077, 1224933);
		public static final RegistryObject<EntityType<MonsterEntity>> REFLUCT = registerMob("refluct", RefluctEntity::new, 0.6f, 2f, 1177865, 11850163);
		public static final RegistryObject<EntityType<MonsterEntity>> ROCK_CRAWLER = registerMob("rock_crawler", RockCrawlerEntity::new, 0.875f, 1.9375f, 6318176, 14937571);
		public static final RegistryObject<EntityType<MonsterEntity>> ROCK_CRITTER = registerMob("rock_critter", RockCritterEntity::new, 0.875f, 1.21875f, 6318176, 2764074);
		public static final RegistryObject<EntityType<RockRiderEntity>> ROCK_RIDER = registerMob("rock_rider", RockRiderEntity::new, 1.3f, 3.375f, 6699285, 2765578);
		public static final RegistryObject<EntityType<MonsterEntity>> ROCKBITER = registerMob("rockbiter", RockbiterEntity::new, 1f, 1.125f, 7434858, 13559069);
		public static final RegistryObject<EntityType<MonsterEntity>> RUNIC_GOLEM = registerMob("runic_golem", RunicGolemEntity::new, 0.75f, 1.75f, 4655720, 2712772);
		public static final RegistryObject<EntityType<MonsterEntity>> RUNIC_GUARDIAN = registerMob("runic_guardian", RunicGuardianEntity::new, 0.6f, 2f, 336465, 7305092);
		public static final RegistryObject<EntityType<MonsterEntity>> RUNICORN = registerMob("runicorn", RunicornEntity::new, 0.6875f, 2f, 5411475, 4804431);
		public static final RegistryObject<EntityType<MonsterEntity>> RUNICORN_RIDER = registerMob("runicorn_rider", RunicornRiderEntity::new, 0.7f, 2.5625f, 5411475, 539296);
		public static final RegistryObject<EntityType<MonsterEntity>> SABRETOOTH = registerMob("sabretooth", SabretoothEntity::new, 1.125f, 1.59375f, 13403404, 1446670);
		public static final RegistryObject<EntityType<SandGiantEntity>> SAND_GIANT = registerMob("sand_giant", SandGiantEntity::new, 1.5f, 3.46875f, 12695722, 16246218);
		public static final RegistryObject<EntityType<MonsterEntity>> SAND_GOLEM = registerMob("sand_golem", SandGolemEntity::new, 0.75f, 1.8125f, 2827290, 8352867);
		public static final RegistryObject<EntityType<MonsterEntity>> SASQUATCH = registerMob("sasquatch", SasquatchEntity::new, 0.6f, 2.0625f, 13546898, 5656129);
		public static final RegistryObject<EntityType<MonsterEntity>> SCRUBBY = registerMob("scrubby", ScrubbyEntity::new, 0.6875f, 1.125f, 14364720, 12027161);
		public static final RegistryObject<EntityType<MonsterEntity>> SEA_CHARGER = registerMob("sea_charger", SeaChargerEntity::new, 0.625f, 1.5f, 1224378, 3305384);
		public static final RegistryObject<EntityType<MonsterEntity>> SEA_TROLL = registerMob("sea_troll", SeaTrollEntity::new, 0.6f, 1.8125f, 1357024, 14291933);
		public static final RegistryObject<EntityType<SeaViperEntity>> SEA_VIPER = registerMob("sea_viper", SeaViperEntity::new, 0.4375f, 0.59375f, 1357029, 7710903);
		public static final RegistryObject<EntityType<MonsterEntity>> SHADE = registerMob("shade", ShadeEntity::new, 0.6f, 1.75f, 4333857, 13549760);
		public static final RegistryObject<EntityType<MonsterEntity>> SHADOW = registerMob("shadow", ShadowEntity::new, 0.6f, 1.75f, 4333857, 13549760);
		public static final RegistryObject<EntityType<MonsterEntity>> SHADOWLORD = registerMob("shadowlord", ShadowlordEntity::new, 4f, 6.75f, 460551, 10722203);
		public static final RegistryObject<EntityType<MonsterEntity>> SHAVO = registerMob("shavo", ShavoEntity::new, 0.6875f, 1.4375f, 15256780, 2959400);
		public static final RegistryObject<EntityType<MonsterEntity>> SHIFTER = registerMob("shifter", ShifterEntity::new, 0.75f, 1.5625f, 1446161, 656902);
		public static final RegistryObject<EntityType<MonsterEntity>> SHYRE_KNIGHT = registerMob("shyre_knight", ShyreKnightEntity::new, 0.6f, 2f, 967917, 13232654);
		public static final RegistryObject<EntityType<MonsterEntity>> SILENCER = registerMob("silencer", SilencerEntity::new, 0.5625f, 2f, 4667168, 13880772);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELE_ELDER = registerMob("skele_elder", SkeleElderEntity::new, 0.7f, 2.5625f, 14407632, 12368309);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELE_HOPPER = registerMob("skele_hopper", SkeleHopperEntity::new, 0.6f, 1.2f, 14407632, 11709604);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELE_PIG = registerMob("skele_pig", SkelePigEntity::new, 0.7f, 1.125f, 14407632, 12368309);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELEDON = registerMob("skeledon", SkeledonEntity::new, 0.625f, 1.3125f, 14403243, 16249322);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELEKYTE = registerMob("skelekyte", SkelekyteEntity::new, 0.5625f, 2f, 14403243, 13881807);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELEMAN = registerMob("skeleman", SkelemanEntity::new, 0.7f, 1.6875f, 14012143, 4605004);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELETAL_COWMAN = registerMob("skeletal_cowman", SkeletalCowmanEntity::new, 0.6f, 1.95f, 2958092, 14077888);
		public static final RegistryObject<EntityType<MonsterEntity>> SKELETRON = registerMob("skeletron", SkeletronEntity::new, 1.2f, 1.25f, 16052195, 4802370);
		public static final RegistryObject<EntityType<MonsterEntity>> SKULL_CREATURE = registerMob("skull_creature", SkullCreatureEntity::new, 0.6875f, 2.3125f, 2496514, 3351359);
		public static final RegistryObject<EntityType<MonsterEntity>> SLIMER = registerMob("slimer", SlimerEntity::new, 1.7f, 3.125f, 3683898, 978238);
		public static final RegistryObject<EntityType<MonsterEntity>> SMASH = registerMob("smash", SmashEntity::new, 0.8f, 2.6f, 3154949, 3493661);
		public static final RegistryObject<EntityType<MonsterEntity>> SNAPPY = registerMob("snappy", SnappyEntity::new, 0.6f, 2.0f, 1573411, 590407);
		public static final RegistryObject<EntityType<MonsterEntity>> SNOW_CHARGER = registerMob("snow_charger", SnowChargerEntity::new, 0.625f, 1.5f, 5604755, 9951981);
		public static final RegistryObject<EntityType<MonsterEntity>> SOULSCORNE = registerMob("soulscorne", SoulscorneEntity::new, 0.6f, 1.8125f, 14809348, 977903);
		public static final RegistryObject<EntityType<MonsterEntity>> SOULVYRE = registerMob("soulvyre", SoulvyreEntity::new, 0.6f, 2.125f, 14743310, 1369563);
		public static final RegistryObject<EntityType<MonsterEntity>> SPECTRAL_WIZARD = registerMob("spectral_wizard", SpectralWizardEntity::new, 0.5f, 2.1875f, 15267050, 52722);
		public static final RegistryObject<EntityType<MonsterEntity>> SPHINX = registerMob("sphinx", SphinxEntity::new, 1.0f, 1.0f, 15134447, 13422286);
		public static final RegistryObject<EntityType<MonsterEntity>> SPINOLEDON = registerMob("spinoledon", SpinoledonEntity::new, 0.7f, 1.5625f, 7682874, 12016473);
		public static final RegistryObject<EntityType<MonsterEntity>> SPIRIT_GUARDIAN = registerMob("spirit_guardian", SpiritGuardianEntity::new, 0.6f, 1.8125f, 15265007, 13881253);
		public static final RegistryObject<EntityType<MonsterEntity>> SPIRIT_PROTECTOR = registerMob("spirit_protector", SpiritProtectorEntity::new, 0.6f, 1.8125f, 15265007, 14867354);
		public static final RegistryObject<EntityType<MonsterEntity>> SQUASHER = registerMob("squasher", SquasherEntity::new, 0.75f, 1.6f, 5732923, 9476961);
		public static final RegistryObject<EntityType<MonsterEntity>> SQUIGGLER = registerMob("squiggler", SquigglerEntity::new, true, 0.5625f, 1.6875f, 7474700, 13533053);
		public static final RegistryObject<EntityType<MonsterEntity>> STALKER = registerMob("stalker", StalkerEntity::new, 0.6f, 2.5625f, 984582, 1841947);
		public static final RegistryObject<EntityType<MonsterEntity>> STICKY = registerMob("sticky", StickyEntity::new, 0.6f, 2.0f, 16777215, 14084119);
		public static final RegistryObject<EntityType<MonsterEntity>> STIMULO = registerMob("stimulo", StimuloEntity::new, 0.6f, 1.875f, 653303, 14808864);
		public static final RegistryObject<EntityType<MonsterEntity>> STIMULOSUS = registerMob("stimulosus", StimulosusEntity::new, 0.6f, 1.875f, 653303, 13953058);
		public static final RegistryObject<EntityType<MonsterEntity>> STITCHES = registerMob("stitches", StitchesEntity::new, 0.6f, 2.0f, 14498831, 10187624);
		public static final RegistryObject<EntityType<StoneGiantEntity>> STONE_GIANT = registerMob("stone_giant", StoneGiantEntity::new, 1.3125f, 5.0625f, 10181528, 10185496);
		public static final RegistryObject<EntityType<MonsterEntity>> STRONG_SKELE_HOPPER = registerMob("strong_skele_hopper", StrongSkeleHopperEntity::new, 0.8f, 1.2f, 15724008, 13552580);
		public static final RegistryObject<EntityType<MonsterEntity>> STRONG_SKELE_PIG = registerMob("strong_skele_pig", StrongSkelePigEntity::new, 0.7f, 1.125f, 15724008, 9671312);
		public static final RegistryObject<EntityType<MonsterEntity>> STRONG_SKELEMAN = registerMob("strong_skeleman", StrongSkelemanEntity::new, 0.8f, 1.75f, 15724008, 16248798);
		public static final RegistryObject<EntityType<MonsterEntity>> SUGARFACE = registerMob("sugarface", SugarfaceEntity::new, 0.5625f, 2.125f, 11963839, 15787762);
		public static final RegistryObject<EntityType<MonsterEntity>> SUNNY = registerMob("sunny", SunnyEntity::new, 0.75f, 2.375f, 653073, 13750551);
		public static final RegistryObject<EntityType<MonsterEntity>> SWAMP_CHARGER = registerMob("swamp_charger", SwampChargerEntity::new, 0.625f, 1.5f, 1785115, 3626551);
		public static final RegistryObject<EntityType<SyskerEntity>> SYSKER = registerMob("sysker", SyskerEntity::new, 0.6f, 1.5f, 14941189, 653789);
		public static final RegistryObject<EntityType<MonsterEntity>> TERRADON = registerMob("terradon", TerradonEntity::new, 1.25f, 1.875f, 6704409, 12235168);
		public static final RegistryObject<EntityType<MonsterEntity>> TERRESTRIAL = registerMob("terrestrial", TerrestrialEntity::new, 1.2f, 2.357f, 5636940, 15666901);
		public static final RegistryObject<EntityType<FlyingEntity>> THARAFLY = registerMob("tharafly", TharaflyEntity::new, 0.75f, 1.0f, 4470043, 13748410);
		public static final RegistryObject<EntityType<MonsterEntity>> TIPSY = registerMob("tipsy", TipsyEntity::new, 0.5f, 2.0f, 1107429, 13748410);
		public static final RegistryObject<EntityType<MonsterEntity>> TORTIONE = registerMob("tortione", TortioneEntity::new, 1.75f, 1.875f, 4415509, 8691731);
		public static final RegistryObject<EntityType<MonsterEntity>> TOXXULOUS = registerMob("toxxulous", ToxxulousEntity::new, 0.75f, 1.125f, 3165970, 2041878);
		public static final RegistryObject<EntityType<MonsterEntity>> TRACKER = registerMob("tracker", TrackerEntity::new, 0.75f, 1.125f, 15550219, 15706387);
		public static final RegistryObject<EntityType<MonsterEntity>> TREE_SPIRIT = registerMob("tree_spirit", TreeSpiritEntity::new, 1.0f, 3.5f, 4470537, 2505992);
		public static final RegistryObject<EntityType<MonsterEntity>> TRICKSTER = registerMob("trickster", TricksterEntity::new, 0.6f, 1.75f, 2237217, 7501167);
		public static final RegistryObject<EntityType<MonsterEntity>> TRICKSTER_CLONE = registerMob("trickster_clone", TricksterCloneEntity::new, 0.6f, 1.75f);
		public static final RegistryObject<EntityType<MonsterEntity>> TYROSAUR = registerMob("tyrosaur", TyrosaurEntity::new, 0.8f, 1.3125f, 5530376, 13356729);
		public static final RegistryObject<EntityType<MonsterEntity>> UNDEAD_TROLL = registerMob("undead_troll", UndeadTrollEntity::new, 0.6f, 1.8125f, 2229309, 14735336);
		public static final RegistryObject<EntityType<MonsterEntity>> URIOH = registerMob("urioh", UriohEntity::new, 0.5f, 0.9375f, 2959920, 13946587);
		public static final RegistryObject<EntityType<MonsterEntity>> URV = registerMob("urv", UrvEntity::new, 0.75f, 2f, 2960171, 8327184);
		public static final RegistryObject<EntityType<FlyingEntity>> VALKYRIE = registerMob("valkyrie", ValkyrieEntity::new, 0.75f, 1.125f, 3154454, 10502611);
		public static final RegistryObject<EntityType<MonsterEntity>> VINE_WIZARD = registerMob("vine_wizard", VineWizardEntity::new, 0.6f, 2.125f, 801297, 12525086);
		public static final RegistryObject<EntityType<MonsterEntity>> VINOCORNE = registerMob("vinocorne", VinocorneEntity::new, 1.2f, 3f, 4944456, 10838423);
		public static final RegistryObject<EntityType<MonsterEntity>> VISAGE = registerMob("visage", VisageEntity::new, 0.7f, 1.5f, 4143934, 14709955);
		public static final RegistryObject<EntityType<FlyingEntity>> VISUALENT = registerMob("visualent", VisualentEntity::new, 1.4f, 1.5f, 5194571, 8590745);
		public static final RegistryObject<EntityType<VisularEntity>> VISULAR = registerMob("visular", VisularEntity::new, 0.625f, 0.9375f, 15994093, 12655548);
		public static final RegistryObject<EntityType<VisulonEntity>> VISULON = registerMob("visulon", VisulonEntity::new, 0.9375f, 1.0625f, 15863530, 15351009);
		public static final RegistryObject<EntityType<MonsterEntity>> VOID_CHARGER = registerMob("void_charger", VoidChargerEntity::new, 0.625f, 1.5f, 2237992, 5573275);
		public static final RegistryObject<EntityType<MonsterEntity>> VOID_WALKER = registerMob("void_walker", VoidWalkerEntity::new, 1.0f, 1.125f, 789260, 3157296);
		public static final RegistryObject<EntityType<MonsterEntity>> VOLTRON = registerMob("voltron", VoltronEntity::new, 0.7f, 2f, 10725391, 2302750);
		public static final RegistryObject<EntityType<MonsterEntity>> VOXXULON = registerMob("voxxulon", VoxxulonEntity::new, 2f, 2.375f, 2568993, 5954330);
		public static final RegistryObject<EntityType<MonsterEntity>> WEB_REAPER = registerMob("web_reaper", WebReaperEntity::new, 0.75f, 3.5625f, 2494474, 1446932);
		public static final RegistryObject<EntityType<AoACreeponiaCreeper>> WINGED_CREEPER = registerMob("winged_creeper", WingedCreeperEntity::new, 0.6f, 1.625f, 484870, 6585443);
		public static final RegistryObject<EntityType<WoodGiantEntity>> WOOD_GIANT = registerMob("wood_giant", WoodGiantEntity::new, 1.5f, 3.375f, 2957061, 731907);
		public static final RegistryObject<EntityType<MonsterEntity>> XXEUS = registerMob("xxeus", XxeusEntity::new, 1f, 3.125f, 9206543, 1432509);
		public static final RegistryObject<EntityType<MonsterEntity>> YELLOW_FLOWER = registerMob("yellow_flower", YellowFlowerEntity::new, 0.7f, 2.3125f, 1168939, 14283790);
		public static final RegistryObject<EntityType<MonsterEntity>> YELLOW_GUARDIAN = registerMob("yellow_guardian", YellowGuardianEntity::new, 1.5f, 2.625f, 7105891, 14347529);
		public static final RegistryObject<EntityType<RuneTemplarEntity>> YELLOW_RUNE_TEMPLAR = registerMob("yellow_rune_templar", YellowRuneTemplarEntity::new, 1.125f, 2f, 2302754, 986892);
		public static final RegistryObject<EntityType<MonsterEntity>> YELLOW_RUNIC_LIFEFORM = registerMob("yellow_runic_lifeform", YellowRunicLifeformEntity::new, 0.75f, 0.99f, 1083877, 14152204);
		public static final RegistryObject<EntityType<MonsterEntity>> YETI = registerMob("yeti", YetiEntity::new, 1.0f, 3.01375f, 15987944, 13816774);
		public static final RegistryObject<EntityType<MonsterEntity>> ZARG = registerMob("zarg", ZargEntity::new, 0.875f, 2.0625f, 1803734, 13230578);
		public static final RegistryObject<EntityType<MonsterEntity>> ZHINX = registerMob("zhinx", ZhinxEntity::new, 0.6f, 0.6875f, 11056831, 13029730);
		public static final RegistryObject<EntityType<MonsterEntity>> ZORP = registerMob("zorp", ZorpEntity::new, 0.6f, 1.875f, 1093861, 14347506);

		private static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String registryName, EntityType.IFactory<T> factory, float width, float height) {
			return registerMob(registryName, factory, false, width, height, -1, -1);
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String registryName, EntityType.IFactory<T> factory, float width, float height, int primaryEggColour, int secondaryEggColour) {
			return registerMob(registryName, factory, false, width, height, primaryEggColour, secondaryEggColour);
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String registryName, EntityType.IFactory<T> factory, boolean fireImmune, float width, float height) {
			return registerMob(registryName, factory, fireImmune, width, height, -1, -1);
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String registryName, EntityType.IFactory<T> factory, boolean fireImmune, float width, float height, int primaryEggColour, int secondaryEggColour) {
			EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, EntityClassification.MONSTER).sized(width, height);

			if (fireImmune)
				typeBuilder.fireImmune();

			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			RegistryObject<EntityType<T>> registryObject = MOBS.register(registryName, () -> entityType);

			if (primaryEggColour != -1)
				SPAWN_EGGS.register(registryName + "_spawn_egg", () -> new ForgeSpawnEggItem(registryObject, primaryEggColour, secondaryEggColour, new Item.Properties().tab(ItemGroup.TAB_MISC)));

			return registryObject;
		}
	}

	public static class Animals {
		public static final DeferredRegister<EntityType<?>> ANIMALS = DeferredRegister.create(ForgeRegistries.ENTITIES, AdventOfAscension.MOD_ID);

		public static final RegistryObject<EntityType<AnimalEntity>> CORATEE = registerAnimal("coratee", CorateeEntity::new, EntityClassification.WATER_AMBIENT, 1f, 1f,  40623, 7236);
		public static final RegistryObject<EntityType<AnimalEntity>> CREEP_COW = registerAnimal("creep_cow", CreepCowEntity::new, 0.9f, 1.4f, 408837, 15135462);
		public static final RegistryObject<EntityType<AnimalEntity>> ELKANYNE = registerAnimal("elkanyne", ElkanyneEntity::new, 0.85f, 1.1875f, 4471327, 7695709);
		public static final RegistryObject<EntityType<AnimalEntity>> HALYCON = registerAnimal("halycon", HalyconEntity::new, 0.9f, 1.4f, 2683401, 734469);

		public static final RegistryObject<EntityType<ShinySquidEntity>> SHINY_SQUID = registerAnimal("shiny_squid", ShinySquidEntity::new, EntityClassification.WATER_CREATURE, 0.8f, 0.8f, 24217, 978934, builder -> builder.clientTrackingRange(8));
		public static final RegistryObject<EntityType<BasicLavaFishEntity>> CANDLEFISH = registerAnimal("candlefish", BasicLavaFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 739037, 199385, builder -> builder.clientTrackingRange(4).fireImmune());
		public static final RegistryObject<EntityType<BasicLavaFishEntity>> CHARRED_CHAR = registerAnimal("charred_char", BasicLavaFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 1119770, 3384054, builder -> builder.clientTrackingRange(4).fireImmune());
		public static final RegistryObject<EntityType<BasicFishEntity>> CHOCAW = registerAnimal("chocaw", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 1258848, 1258848, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicLavaFishEntity>> CRIMSON_SKIPPER = registerAnimal("crimson_skipper", BasicLavaFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 6174935, 4131984, builder -> builder.clientTrackingRange(4).fireImmune());
		public static final RegistryObject<EntityType<BasicLavaFishEntity>> CRIMSON_STRIPEFISH = registerAnimal("crimson_stripefish", BasicLavaFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 5856681, 13682943, builder -> builder.clientTrackingRange(4).fireImmune());
		public static final RegistryObject<EntityType<BasicFishEntity>> DARK_HATCHETFISH = registerAnimal("dark_hatchetfish", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 5323811, 4892782, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> BLUE_GEMTRAP = registerAnimal("blue_gemtrap", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 6570505, 15658578, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> GREEN_GEMTRAP = registerAnimal("green_gemtrap", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 687370, 1370063, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> PURPLE_GEMTRAP = registerAnimal("purple_gemtrap", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 9513058, 15027428, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> RED_GEMTRAP = registerAnimal("red_gemtrap", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 1256895, 6285047, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> WHITE_GEMTRAP = registerAnimal("white_gemtrap", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 9803157, 16250871, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> YELLOW_GEMTRAP = registerAnimal("yellow_gemtrap", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 610148, 6277111, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> HYDRONE = registerAnimal("hydrone", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 4869452, 1261627, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> IRONBACK = registerAnimal("ironback", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 8816741, 7303023, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> JAMFISH = registerAnimal("jamfish", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 16749559, 9706088, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> PARAPIRANHA = registerAnimal("parapiranha", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 728166, 1514273, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> PEARL_STRIPEFISH = registerAnimal("pearl_stripefish", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 16709631, 9205136, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> RAINBOWFISH = registerAnimal("rainbowfish", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 6284953, 10071794, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> RAZORFISH = registerAnimal("razorfish", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 4480882, 6052956, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> REEFTOOTH = registerAnimal("reeftooth", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 7960386, 6312555, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> ROCKETFISH = registerAnimal("rocketfish", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 994987, 10796527, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> SAILBACK = registerAnimal("sailback", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 14339700, 16250097, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> SAPPHIRE_STRIDER = registerAnimal("sapphire_strider", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 16621128, 16771764, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> SKELECANTH = registerAnimal("skelecanth", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 14016993, 10133150, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> TURQUOISE_STRIPEFISH = registerAnimal("turquoise_stripefish", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 11446887, 16773832, builder -> builder.clientTrackingRange(4));
		public static final RegistryObject<EntityType<BasicFishEntity>> VIOLET_SKIPPER = registerAnimal("violet_skipper", BasicFishEntity::new, EntityClassification.WATER_AMBIENT, 0.7f, 0.4f, 11557499, 14776495, builder -> builder.clientTrackingRange(4));

		public static final RegistryObject<EntityType<AnimalEntity>> ANGELICA = registerAnimal("angelica", AngelicaEntity::new, 0.6f, 2f, 3579029, 13290663);
		public static final RegistryObject<EntityType<AnimalEntity>> DAWNLIGHT = registerAnimal("dawnlight", DawnlightEntity::new, 0.6875f, 1.375f, 10796201, 1948221);
		public static final RegistryObject<EntityType<AnimalEntity>> EEO = registerAnimal("eeo", EeoEntity::new, 0.375f, 1.25f, 15921906, 5155053);
		public static final RegistryObject<EntityType<AnimalEntity>> NIGHT_WATCHER = registerAnimal("night_watcher", NightWatcherEntity::new, 0.6f, 2.7f, 723977, 592898);
		public static final RegistryObject<EntityType<AnimalEntity>> PEPPERMINT_SNAIL = registerAnimal("peppermint_snail", PeppermintSnailEntity::new, 0.5f, 0.8125f, 14162711, 14849941);
		public static final RegistryObject<EntityType<AnimalEntity>> RAINICORN = registerAnimal("rainicorn", RainicornEntity::new, 1.4f, 1.3125f, 15066083, 7836541);
		public static final RegistryObject<EntityType<AnimalEntity>> SPEARMINT_SNAIL = registerAnimal("spearmint_snail", SpearmintSnailEntity::new, 0.5f, 0.8125f, 521247, 14938853);
		public static final RegistryObject<EntityType<AnimalEntity>> TROTTER = registerAnimal("trotter", TrotterEntity::new, 0.75f, 1.1875f, 16213252, 16051204);
		public static final RegistryObject<EntityType<AnimalEntity>> URKA = registerAnimal("urka", UrkaEntity::new, 1.2f, 1.5f, 1644569, 15987444);
		public static final RegistryObject<EntityType<AnimalEntity>> VOLIANT = registerAnimal("voliant", VoliantEntity::new, 3.5f, 4.75f, 811936, 4096951);
		public static final RegistryObject<EntityType<MeganeuropsisEntity>> MEGANEUROPSIS = registerAnimal("meganeuropsis", MeganeuropsisEntity::new, 0.5f, 0.4375f, 16763904, 5986352);
		public static final RegistryObject<EntityType<ShikEntity>> SHIK = registerAnimal("shik", ShikEntity::new, 0.375f, 0.4375f, 7500402, 3947580);

		public static final RegistryObject<EntityType<PixonEntity>> AMBIENT_PIXON = registerAnimal("ambient_pixon", AmbientPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 12235956, 12039354);
		public static final RegistryObject<EntityType<PixonEntity>> BLOOMING_PIXON = registerAnimal("blooming_pixon", BloomingPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 10292975, 13540842);
		public static final RegistryObject<EntityType<PixonEntity>> GLARING_PIXON = registerAnimal("glaring_pixon", GlaringPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 2628099, 2827805);
		public static final RegistryObject<EntityType<PixonEntity>> GLEAMING_PIXON = registerAnimal("gleaming_pixon", GleamingPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 1549800, 2830387);
		public static final RegistryObject<EntityType<PixonEntity>> GLISTENING_PIXON = registerAnimal("glistening_pixon", GlisteningPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 977425, 2370852);
		public static final RegistryObject<EntityType<PixonEntity>> GLOWING_PIXON = registerAnimal("glowing_pixon", GlowingPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 16190476, 2170141);
		public static final RegistryObject<EntityType<PixonEntity>> RADIANT_PIXON = registerAnimal("radiant_pixon", RadiantPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 2689714, 1641556);
		public static final RegistryObject<EntityType<PixonEntity>> SHINING_PIXON = registerAnimal("shining_pixon", ShiningPixonEntity::new, EntityClassification.AMBIENT, 0.9f, 1.3f, 13868556, 9341827);

		private static <T extends Entity> RegistryObject<EntityType<T>> registerAnimal(String registryName, EntityType.IFactory<T> factory, float width, float height) {
			return registerAnimal(registryName, factory, width, height, -1, -1);
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerAnimal(String registryName, EntityType.IFactory<T> factory, float width, float height, int primaryEggColour, int secondaryEggColour) {
			return registerAnimal(registryName, factory, EntityClassification.CREATURE, width, height, primaryEggColour, secondaryEggColour, builder -> {});
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerAnimal(String registryName, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height, int primaryEggColour, int secondaryEggColour) {
			return registerAnimal(registryName, factory, classification, width, height, primaryEggColour, secondaryEggColour, builder -> {});
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerAnimal(String registryName, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height, int primaryEggColour, int secondaryEggColour, Consumer<EntityType.Builder<T>> typeBuilderConsumer) {
			EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, classification).sized(width, height).clientTrackingRange(10);

			typeBuilderConsumer.accept(typeBuilder);

			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			RegistryObject<EntityType<T>> registryObject = ANIMALS.register(registryName, () -> entityType);

			if (primaryEggColour != -1)
				SPAWN_EGGS.register(registryName + "_spawn_egg", () -> new ForgeSpawnEggItem(registryObject, primaryEggColour, secondaryEggColour, new Item.Properties().tab(ItemGroup.TAB_MISC)));

			return registryObject;
		}
	}

	public static class NPCs {
		public static final DeferredRegister<EntityType<?>> NPCS = DeferredRegister.create(ForgeRegistries.ENTITIES, AdventOfAscension.MOD_ID);

		public static final RegistryObject<EntityType<CreatureEntity>> GORB_CITIZEN = registerNPC("gorb_citizen", GorbCitizenEntity::new, 0.5625f, 1.6875f, 3233315, 4744746);
		public static final RegistryObject<EntityType<CreatureEntity>> PRIMORDIAL_GUIDE = registerNPC("primordial_guide", PrimordialGuideEntity::new, 0.5625f, 2.0f, 7037281, 2960171);
		public static final RegistryObject<EntityType<CreatureEntity>> ZAL_CHILD = registerNPC("zal_child", ZalChildEntity::new, 0.16875f, 0.5625f, 15395546, 2631715);
		public static final RegistryObject<EntityType<CreatureEntity>> ZAL_CITIZEN = registerNPC("zal_citizen", ZalCitizenEntity::new, 0.5625f, 1.875f, 15921894, 3487025);

		public static final RegistryObject<EntityType<CreatureEntity>> CREEP_BANKER = registerNPC("creep_banker", CreepBankerEntity::new, 0.5625f, 2.0f, 407559, 5395971);
		public static final RegistryObject<EntityType<CreatureEntity>> LELYETIAN_BANKER = registerNPC("lelyetian_banker", LelyetianBankerEntity::new, 0.5625f, 2.0f, 14705940, 2955779);
		public static final RegistryObject<EntityType<CreatureEntity>> PRIMORDIAL_BANKER = registerNPC("primordial_banker", PrimordialBankerEntity::new, 0.5625f, 2.0f, 14685204, 1315346);
		public static final RegistryObject<EntityType<CreatureEntity>> SHYRE_BANKER = registerNPC("shyre_banker", ShyreBankerEntity::new, 0.5625f, 2.0f, 15369239, 1101037);
		public static final RegistryObject<EntityType<CreatureEntity>> ZAL_BANKER = registerNPC("zal_banker", ZalBankerEntity::new, 0.5625f, 1.875f, 11975695, 4144954);

		public static final RegistryObject<EntityType<AoATrader>> ABYSSAL_LOTTOMAN = registerNPC("abyssal_lottoman", AbyssalLottomanEntity::new, 0.5625f, 2.0f, 15994121, 15917022);
		public static final RegistryObject<EntityType<AoATrader>> BARON_LOTTOMAN = registerNPC("baron_lottoman", BaronLottomanEntity::new, 0.5625f, 2.0f, 15394263, 12029839);
		public static final RegistryObject<EntityType<AoATrader>> BOREIC_LOTTOMAN = registerNPC("boreic_lottoman", BoreicLottomanEntity::new, 0.5625f, 2.0f, 1103858, 14017509);
		public static final RegistryObject<EntityType<AoATrader>> CANDIED_LOTTOMAN = registerNPC("candied_lottoman", CandiedLottomanEntity::new, 0.5625f, 2.0f, 11538661, 15062506);
		public static final RegistryObject<EntityType<AoATrader>> CELEVIAN_LOTTOMAN = registerNPC("celevian_lottoman", CelevianLottomanEntity::new, 0.5625f, 2.0f, 14807784, 10671799);
		public static final RegistryObject<EntityType<AoATrader>> CREEPONIA_LOTTOMAN = registerNPC("creeponia_lottoman", CreeponiaLottomanEntity::new, 0.5625f, 2.0f, 668938, 855821);
		public static final RegistryObject<EntityType<AoATrader>> CRYSTAL_LOTTOMAN = registerNPC("crystal_lottoman", CrystalLottomanEntity::new, 0.5625f, 2.0f, 13097927, 1814501);
		public static final RegistryObject<EntityType<AoATrader>> DUSTOPIAN_LOTTOMAN = registerNPC("dustopian_lottoman", DustopianLottomanEntity::new, 0.5625f, 2.0f, 15065045, 4012857);
		public static final RegistryObject<EntityType<AoATrader>> FLORO_LOTTOMAN = registerNPC("floro_lottoman", FloroLottomanEntity::new, 0.5625f, 2.0f, 16250098, 862212);
		public static final RegistryObject<EntityType<AoATrader>> GOLDEN_LOTTOMAN = registerNPC("golden_lottoman", GoldenLottomanEntity::new, 0.5625f, 2.0f, 16051179, 986638);
		public static final RegistryObject<EntityType<AoATrader>> HAUNTED_LOTTOMAN = registerNPC("haunted_lottoman", HauntedLottomanEntity::new, 0.5625f, 2.0f, 6430178, 16052983);
		public static final RegistryObject<EntityType<AoATrader>> LELYETIAN_LOTTOMAN = registerNPC("lelyetian_lottoman", LelyetianLottomanEntity::new, 0.5625f, 2.0f, 16052459, 10509836);
		public static final RegistryObject<EntityType<AoATrader>> LOTTOMAN = registerNPC("lottoman", LottomanEntity::new, 0.5625f, 2.0f, 15723491, 789515);
		public static final RegistryObject<EntityType<AoATrader>> LUNAR_LOTTOMAN = registerNPC("lunar_lottoman", LunarLottomanEntity::new, 0.5625f, 2.0f, 11034029, 12169914);
		public static final RegistryObject<EntityType<AoATrader>> MYSTIC_LOTTOMAN = registerNPC("mystic_lottoman", MysticLottomanEntity::new, 0.5625f, 2.0f, 15722738, 1238360);
		public static final RegistryObject<EntityType<AoATrader>> PRECASIAN_LOTTOMAN = registerNPC("precasian_lottoman", PrecasianLottomanEntity::new, 0.5625f, 2.0f, 1330186, 15528682);
		public static final RegistryObject<EntityType<AoATrader>> ROCKY_LOTTOMAN = registerNPC("rocky_lottoman", RockyLottomanEntity::new, 0.5625f, 2.0f, 15131631, 2763053);
		public static final RegistryObject<EntityType<AoATrader>> RUNIC_LOTTOMAN = registerNPC("runic_lottoman", RunicLottomanEntity::new, 0.5625f, 2.0f, 1007835, 14804717);
		public static final RegistryObject<EntityType<AoATrader>> SHYRELANDS_LOTTOMAN = registerNPC("shyrelands_lottoman", ShyrelandsLottomanEntity::new, 0.5625f, 2.0f, 14871822, 15395550);
		public static final RegistryObject<EntityType<AoATrader>> TOXIC_LOTTOMAN = registerNPC("toxic_lottoman", ToxicLottomanEntity::new, 0.5625f, 2.0f, 479494, 9081226);
		public static final RegistryObject<EntityType<AoATrader>> TWINKLING_LOTTOMAN = registerNPC("twinkling_lottoman", TwinklingLottomanEntity::new, 0.5625f, 2.0f, 839991, 13360079);
		public static final RegistryObject<EntityType<AoATrader>> WITHERING_LOTTOMAN = registerNPC("withering_lottoman", WitheringLottomanEntity::new, 0.5625f, 2.0f, 15594735, 2698026);

		public static final RegistryObject<EntityType<AoATrader>> ASSASSIN = registerNPC("assassin", AssassinEntity::new, 0.5625f, 2.0f, 5638000, 5393748);
		public static final RegistryObject<EntityType<CreatureEntity>> CORRUPTED_TRAVELLER = registerNPC("corrupted_traveller", CorruptedTravellerEntity::new, 0.5625f, 2.0f, 329538, 340761);
		public static final RegistryObject<EntityType<AoATrader>> CRYSTAL_TRADER = registerNPC("crystal_trader", CrystalTraderEntity::new, 0.5625f, 2.0f, 14150368, 1369392);
		public static final RegistryObject<EntityType<AoATrader>> DUNGEON_KEEPER = registerNPC("dungeon_keeper", DungeonKeeperEntity::new, 0.5625f, 2.0f, 3157507, 2825476);
		public static final RegistryObject<EntityType<AoATrader>> EXPLOSIVES_EXPERT = registerNPC("explosives_expert", ExplosivesExpertEntity::new, 0.5625f, 2.0f, 15864595, 16250871);
		public static final RegistryObject<EntityType<AoATrader>> GORB_ARMS_DEALER = registerNPC("gorb_arms_dealer", GorbArmsDealerEntity::new, 0.5625f, 1.6875f, 4990482, 14195479);
		public static final RegistryObject<EntityType<AoATrader>> GORB_ENGINEER = registerNPC("gorb_engineer", GorbEngineerEntity::new, 0.5625f, 1.6875f, 10032659, 14389134);
		public static final RegistryObject<EntityType<AoATrader>> LELYETIAN_TRADER = registerNPC("lelyetian_trader", LelyetianTraderEntity::new, 0.5625f, 2.0f, 14705940, 2955779);
		public static final RegistryObject<EntityType<AoATrader>> METALLOID = registerNPC("metalloid", MetalloidEntity::new, 0.5625f, 2.0f, 3677189, 7555455);
		public static final RegistryObject<EntityType<AoATrader>> NATURALIST = registerNPC("naturalist", NaturalistEntity::new, 0.5625f, 2.0f, 4927582, 10193420);
		public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_MERCHANT = registerNPC("primordial_merchant", PrimordialMerchantEntity::new, 0.5625f, 2.0f, 909869, 1250835);
		public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_SPELLBINDER = registerNPC("primordial_spellbinder", PrimordialSpellbinderEntity::new, 0.5625f, 2.0f, 1059237, 8097765);
		public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_WIZARD = registerNPC("primordial_wizard", PrimordialWizardEntity::new, 0.5625f, 2.0f, 4000119, 13023446);
		public static final RegistryObject<EntityType<AoATrader>> PROFESSOR = registerNPC("professor", ProfessorEntity::new, 0.5625f, 2.0f, 13493531, 1644822);
		public static final RegistryObject<EntityType<AoATrader>> REALMSHIFTER = registerNPC("realmshifter", RealmshifterEntity::new, 0.5625f, 2.0f, 2953404, 1776158);
		public static final RegistryObject<EntityType<AoATrader>> SHYRE_ARCHER = registerNPC("shyre_archer", ShyreArcherEntity::new, 0.5625f, 2.0f, 14393873, 15397397);
		public static final RegistryObject<EntityType<SkillMasterEntity>> SKILL_MASTER = registerNPC("skill_master", SkillMasterEntity::new, 0.5625f, 2.0f, 13882169, 4470027);
		public static final RegistryObject<EntityType<AoATrader>> STORE_KEEPER = registerNPC("store_keeper", StoreKeeperEntity::new, 0.5625f, 2.0f, 2824965, 10530735);
		public static final RegistryObject<EntityType<AoATrader>> TOKEN_COLLECTOR = registerNPC("token_collector", TokenCollectorEntity::new, 0.5625f, 2.0f, 6840842, 13878796);
		public static final RegistryObject<EntityType<AoATrader>> TOY_MERCHANT = registerNPC("toy_merchant", ToyMerchantEntity::new, 0.5625f, 2.0f, 7821066, 14068552);
		public static final RegistryObject<EntityType<AoATrader>> TROLL_TRADER = registerNPC("troll_trader", TrollTraderEntity::new, 0.5625f, 1.8125f, 779757, 415293);
		public static final RegistryObject<EntityType<AoATrader>> UNDEAD_HERALD = registerNPC("undead_herald", UndeadHeraldEntity::new, 0.5625f, 2.0f, 592394, 1302514);
		public static final RegistryObject<EntityType<AoATrader>> ZAL_GROCER = registerNPC("zal_grocer", ZalGrocerEntity::new, 0.5625f, 1.875f, 2956551, 6315353);
		public static final RegistryObject<EntityType<AoATrader>> ZAL_HERBALIST = registerNPC("zal_herbalist", ZalHerbalistEntity::new, 0.5625f, 1.875f, 997382, 6315353);
		public static final RegistryObject<EntityType<AoATrader>> ZAL_SPELLBINDER = registerNPC("zal_spellbinder", ZalSpellbinderEntity::new, 0.5625f, 1.875f, 3475048, 6315353);
		public static final RegistryObject<EntityType<AoATrader>> ZAL_VENDOR = registerNPC("zal_vendor", ZalVendorEntity::new, 0.5625f, 1.875f, 4144703, 9933723);

		public static final RegistryObject<EntityType<DryadSpriteEntity>> DRYAD_SPRITE = registerNPC("dryad_sprite", DryadSpriteEntity::new, 1f, 1f, 2308682, 3174984);

		private static <T extends Entity> RegistryObject<EntityType<T>> registerNPC(String registryName, EntityType.IFactory<T> factory, float width, float height, int primaryEggColour, int secondaryEggColour) {
			return registerNPC(registryName, factory, false, width, height, primaryEggColour, secondaryEggColour);
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerNPC(String registryName, EntityType.IFactory<T> factory, boolean fireImmune, float width, float height, int primaryEggColour, int secondaryEggColour) {
			EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, EntityClassification.AMBIENT).sized(width, height);

			if (fireImmune)
				typeBuilder.fireImmune();

			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			RegistryObject<EntityType<T>> registryObject = NPCS.register(registryName, () -> entityType);

			if (primaryEggColour != -1)
				SPAWN_EGGS.register(registryName + "_spawn_egg", () -> new ForgeSpawnEggItem(registryObject, primaryEggColour, secondaryEggColour, new Item.Properties().tab(ItemGroup.TAB_MISC)));

			return registryObject;
		}
	}

	public static class Misc {
		public static final DeferredRegister<EntityType<?>> MISC = DeferredRegister.create(ForgeRegistries.ENTITIES, AdventOfAscension.MOD_ID);

		public static final RegistryObject<EntityType<SoulTabletEntity>> AGILITY_TABLET = registerMiscEntity("agility_tablet", AgilityTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> AWARENESS_TABLET = registerMiscEntity("awareness_tablet", AwarenessTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> BREEDING_TABLET = registerMiscEntity("breeding_tablet", BreedingTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> CLEANSING_TABLET = registerMiscEntity("cleansing_tablet", CleansingTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> DISTORTION_TABLET = registerMiscEntity("distortion_tablet", DistortionTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> ENERGY_TABLET = registerMiscEntity("energy_tablet", EnergyTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> GRAVITY_TABLET = registerMiscEntity("gravity_tablet", GravityTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> OXYGEN_TABLET = registerMiscEntity("oxygen_tablet", OxygenTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> PRESSURE_TABLET = registerMiscEntity("pressure_tablet", PressureTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> PROFICIENCY_TABLET = registerMiscEntity("proficiency_tablet", ProficiencyTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> RESISTANCE_TABLET = registerMiscEntity("resistance_tablet", ResistanceTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> SANCTITY_TABLET = registerMiscEntity("sanctity_tablet", SanctityTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> SATIATION_TABLET = registerMiscEntity("satiation_tablet", SatiationTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> SIGHT_TABLET = registerMiscEntity("sight_tablet", SightTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> STRENGTH_TABLET = registerMiscEntity("strength_tablet", StrengthTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> UNTIRING_TABLET = registerMiscEntity("untiring_tablet", UntiringTabletEntity::new, 0.6f, 0.09375f);
		public static final RegistryObject<EntityType<SoulTabletEntity>> VITALITY_TABLET = registerMiscEntity("vitality_tablet", VitalityTabletEntity::new, 0.6f, 0.09375f);

		public static final RegistryObject<EntityType<GyrocopterEntity>> GYROCOPTER = registerMiscEntity("gyrocopter", GyrocopterEntity::new, 1.375f, 1.625f, EntityType.Builder::noSummon);
		public static final RegistryObject<EntityType<ItemEntity>> BOSS_ITEM = registerMiscEntity("boss_item", BossItemEntity::new, 0.25f, 0.25f, EntityType.Builder::noSummon);
		public static final RegistryObject<EntityType<LottoTotemEntity>> LOTTO_TOTEM = registerMiscEntity("lotto_totem", LottoTotemEntity::new, 0.75f, 0.95f, EntityType.Builder::fireImmune);
		public static final RegistryObject<EntityType<TNTEntity>> FAKE_TNT = registerMiscEntity("fake_tnt", FakeTntEntity::new, 1f, 1f, typeBuilder -> typeBuilder.noSave().noSummon());
		public static final RegistryObject<EntityType<BaronBombEntity>> BARON_BOMB = registerMiscEntity("baron_bomb", BaronBombEntity::new, 1f, 0.8125f, EntityType.Builder::noSummon);

		public static final RegistryObject<EntityType<MonsterEntity>> BANE_CLONE = registerMiscEntity("bane_clone", BaneCloneEntity::new, 0.75f, 2f, typeBuilder -> typeBuilder.noSave().noSummon());
		public static final RegistryObject<EntityType<MonsterEntity>> BIG_BANE_CLONE = registerMiscEntity("big_bane_clone", BigBaneCloneEntity::new, 1.2f, 3.9375f, typeBuilder -> typeBuilder.noSave().noSummon());
		public static final RegistryObject<EntityType<ThornyPlantSproutEntity>> THORNY_PLANT_SPROUT = registerMiscEntity("thorny_plant_sprout", ThornyPlantSproutEntity::new, 0.5f, 1.5f, EntityType.Builder::noSummon);

		public static final RegistryObject<EntityType<HaulingFishingBobberEntity>> REINFORCED_BOBBER = registerMiscEntity("reinforced_bobber", EntityType.Builder.<HaulingFishingBobberEntity>createNothing(EntityClassification.MISC).noSave().noSummon().sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(5).setCustomClientFactory(HaulingFishingBobberEntity::handleClientSpawn));
		public static final RegistryObject<EntityType<ThermalFishingBobberEntity>> THERMAL_BOBBER = registerMiscEntity("thermal_bobber", EntityType.Builder.<ThermalFishingBobberEntity>createNothing(EntityClassification.MISC).noSave().noSummon().sized(0.25f, 0.25f).clientTrackingRange(4).fireImmune().updateInterval(5).setCustomClientFactory(ThermalFishingBobberEntity::handleClientSpawn));
		public static final RegistryObject<EntityType<GoldFishingBobberEntity>> GOLD_BOBBER = registerMiscEntity("gold_bobber", EntityType.Builder.<GoldFishingBobberEntity>createNothing(EntityClassification.MISC).noSave().noSummon().sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(5).setCustomClientFactory(GoldFishingBobberEntity::handleClientSpawn));
		public static final RegistryObject<EntityType<FishingCageEntity>> FISHING_CAGE = registerMiscEntity("fishing_cage", FishingCageEntity::new, 0.65f, 0.63f, builder -> builder.noSummon().updateInterval(5));

		private static <T extends Entity> RegistryObject<EntityType<T>> registerMiscEntity(String registryName, EntityType.IFactory<T> factory, float width, float height) {
			return registerMiscEntity(registryName, factory, width, height, typeBuilder -> {});
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerMiscEntity(String registryName, EntityType.IFactory<T> factory, float width, float height, Consumer<EntityType.Builder<T>> typeBuilderConsumer) {
			EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, EntityClassification.MISC).sized(width, height).setTrackingRange(40).setUpdateInterval(1);

			typeBuilderConsumer.accept(typeBuilder);

			return registerMiscEntity(registryName, typeBuilder);
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerMiscEntity(String registryName, EntityType.Builder<T> typeBuilder) {
			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return MISC.register(registryName, () -> entityType);
		}
	}

	public static class Projectiles {
		public static final DeferredRegister<EntityType<?>> PROJECTILES = DeferredRegister.create(ForgeRegistries.ENTITIES, AdventOfAscension.MOD_ID);

		public static final RegistryObject<EntityType<AnemiaBombEntity>> ANEMIA_BOMB = registerProjectile("anemia_bomb", AnemiaBombEntity::new);
		public static final RegistryObject<EntityType<CustomArrowEntity>> ARROW = registerProjectile("arrow", CustomArrowEntity::new);
		public static final RegistryObject<EntityType<AquaballEntity>> AQUABALL = registerProjectile("aquaball", AquaballEntity::new);
		public static final RegistryObject<EntityType<AquaticShotEntity>> AQUATIC_SHOT = registerProjectile("aquatic_shot", AquaticShotEntity::new);
		public static final RegistryObject<EntityType<ArcwormShotEntity>> ARCWORM_SHOT = registerProjectile("arcworm_shot", ArcwormShotEntity::new);
		public static final RegistryObject<EntityType<AtomizerBounceEntity>> ATOMIZER_BOUNCE = registerProjectile("atomizer_bounce", AtomizerBounceEntity::new);
		public static final RegistryObject<EntityType<AtomizerShotEntity>> ATOMIZER_SHOT = registerProjectile("atomizer_shot", AtomizerShotEntity::new);
		public static final RegistryObject<EntityType<BalloonBombEntity>> BALLOON_BOMB = registerProjectile("balloon_bomb", BalloonBombEntity::new);
		public static final RegistryObject<EntityType<BaronessShotEntity>> BARONESS_SHOT = registerProjectile("baroness_shot", BaronessShotEntity::new);
		public static final RegistryObject<EntityType<BaronShotEntity>> BARON_SHOT = registerProjectile("baron_shot", BaronShotEntity::new);
		public static final RegistryObject<EntityType<BeamerShotEntity>> BEAMER_SHOT = registerProjectile("beamer_shot", BeamerShotEntity::new);
		public static final RegistryObject<EntityType<BloodballEntity>> BLOODBALL = registerProjectile("bloodball", BloodballEntity::new);
		public static final RegistryObject<EntityType<BloodDrainerEntity>> BLOOD_DRAINER = registerProjectile("blood_drainer", BloodDrainerEntity::new);
		public static final RegistryObject<EntityType<BlueBulletEntity>> BLUE_BULLET = registerProjectile("blue_bullet", BlueBulletEntity::new);
		public static final RegistryObject<EntityType<BlueGuardianShotEntity>> BLUE_GUARDIAN_SHOT = registerProjectile("blue_guardian_shot", BlueGuardianShotEntity::new);
		public static final RegistryObject<EntityType<BoneBulletEntity>> BONE_BULLET = registerProjectile("bone_bullet", BoneBulletEntity::new);
		public static final RegistryObject<EntityType<BonePelletEntity>> BONE_PELLET = registerProjectile("bone_pellet", BonePelletEntity::new);
		public static final RegistryObject<EntityType<BozoBallEntity>> BOZO_BALL = registerProjectile("bozo_ball", BozoBallEntity::new);
		public static final RegistryObject<EntityType<BubbleShotEntity>> BUBBLE_SHOT = registerProjectile("bubble_shot", BubbleShotEntity::new);
		public static final RegistryObject<EntityType<LimoniteBulletEntity>> BULLET = registerProjectile("bullet", LimoniteBulletEntity::new);
		public static final RegistryObject<EntityType<BulletShotEntity>> BULLET_SHOT = registerProjectile("bullet_shot", BulletShotEntity::new);
		public static final RegistryObject<EntityType<CannonballEntity>> CANNONBALL = registerProjectile("cannonball", CannonballEntity::new);
		public static final RegistryObject<EntityType<CarrotBallEntity>> CARROT_BALL = registerProjectile("carrot_ball", CarrotBallEntity::new);
		public static final RegistryObject<EntityType<CelestialFallEntity>> CELESTIAL_FALL = registerProjectile("celestial_fall", CelestialFallEntity::new);
		public static final RegistryObject<EntityType<ChakramEntity>> CHAKRAM = registerProjectile("chakram", ChakramEntity::new);
		public static final RegistryObject<EntityType<CherryShotEntity>> CHERRY_SHOT = registerProjectile("cherry_shot", CherryShotEntity::new);
		public static final RegistryObject<EntityType<ChilliShotEntity>> CHILLI_SHOT = registerProjectile("chilli_shot", ChilliShotEntity::new);
		public static final RegistryObject<EntityType<ClownBallEntity>> CLOWN_BALL = registerProjectile("clown_ball", ClownBallEntity::new);
		public static final RegistryObject<EntityType<ClownShotEntity>> CLOWN_SHOT = registerProjectile("clown_shot", ClownShotEntity::new);
		public static final RegistryObject<EntityType<ConfettiClusterEntity>> CONFETTI_CLUSTER = registerProjectile("confetti_cluster", ConfettiClusterEntity::new);
		public static final RegistryObject<EntityType<ConfettiShotEntity>> CONFETTI_SHOT = registerProjectile("confetti_shot", ConfettiShotEntity::new);
		public static final RegistryObject<EntityType<ConstructShotEntity>> CONSTRUCT_SHOT = registerProjectile("construct_shot", ConstructShotEntity::new);
		public static final RegistryObject<EntityType<CorallusShotEntity>> CORALLUS_SHOT = registerProjectile("corallus_shot", CorallusShotEntity::new, 1.1f, 1.1f);
		public static final RegistryObject<EntityType<CottonCandorShotEntity>> COTTON_CANDOR_SHOT = registerProjectile("cotton_candor_shot", CottonCandorShotEntity::new);
		public static final RegistryObject<EntityType<CraexxeusNukeEntity>> CRAEXXEUS_NUKE = registerProjectile("craexxeus_nuke", CraexxeusNukeEntity::new);
		public static final RegistryObject<EntityType<CraexxeusShotEntity>> CRAEXXEUS_SHOT = registerProjectile("craexxeus_shot", CraexxeusShotEntity::new);
		public static final RegistryObject<EntityType<CreeperShotEntity>> CREEPER_SHOT = registerProjectile("creeper_shot", CreeperShotEntity::new);
		public static final RegistryObject<EntityType<CreepBombEntity>> CREEP_BOMB = registerProjectile("creep_bomb", CreepBombEntity::new);
		public static final RegistryObject<EntityType<CreepTubeEntity>> CREEP_TUBE = registerProjectile("creep_tube", CreepTubeEntity::new);
		public static final RegistryObject<EntityType<CyanShotEntity>> CYAN_SHOT = registerProjectile("cyan_shot", CyanShotEntity::new);
		public static final RegistryObject<EntityType<DeathRayEntity>> DEATH_RAY = registerProjectile("death_ray", DeathRayEntity::new);
		public static final RegistryObject<EntityType<DestroyerShotEntity>> DESTROYER_SHOT = registerProjectile("destroyer_shot", DestroyerShotEntity::new);
		public static final RegistryObject<EntityType<DestructionShotEntity>> DESTRUCTION_SHOT = registerProjectile("destruction_shot", DestructionShotEntity::new);
		public static final RegistryObject<EntityType<DischargeShotEntity>> DISCHARGE_SHOT = registerProjectile("discharge_shot", DischargeShotEntity::new);
		public static final RegistryObject<EntityType<DischargeSlugEntity>> DISCHARGE_SLUG = registerProjectile("discharge_slug", DischargeSlugEntity::new);
		public static final RegistryObject<EntityType<DoomShotEntity>> DOOM_SHOT = registerProjectile("doom_shot", DoomShotEntity::new);
		public static final RegistryObject<EntityType<EnergyShotEntity>> ENERGY_SHOT = registerProjectile("energy_shot", EnergyShotEntity::new);
		public static final RegistryObject<EntityType<EradicatorShotEntity>> ERADICATOR_SHOT = registerProjectile("eradicator_shot", EradicatorShotEntity::new);
		public static final RegistryObject<EntityType<ErebonSticklerShotEntity>> EREBON_STICKLER_SHOT = registerProjectile("erebon_stickler_shot", ErebonSticklerShotEntity::new);
		public static final RegistryObject<EntityType<ErebonSticklerStuckEntity>> EREBON_STICKLER_STUCK = registerProjectile("erebon_stickler_stuck", ErebonSticklerStuckEntity::new);
		public static final RegistryObject<EntityType<FireflyShotEntity>> FIREFLY_SHOT = registerProjectile("firefly_shot", FireflyShotEntity::new);
		public static final RegistryObject<EntityType<FirestormFallEntity>> FIRESTORM_FALL = registerProjectile("firestorm_fall", FirestormFallEntity::new);
		public static final RegistryObject<EntityType<FireBulletEntity>> FIRE_BULLET = registerProjectile("fire_bullet", FireBulletEntity::new);
		public static final RegistryObject<EntityType<FloroRPGEntity>> FLORO_RPG = registerProjectile("floro_rpg", FloroRPGEntity::new);
		public static final RegistryObject<EntityType<FlowerBallEntity>> FLOWER_BALL = registerProjectile("flower_ball", FlowerBallEntity::new);
		public static final RegistryObject<EntityType<FlowerShotEntity>> FLOWER_SHOT = registerProjectile("flower_shot", FlowerShotEntity::new);
		public static final RegistryObject<EntityType<FragmentShotEntity>> FRAGMENT_SHOT = registerProjectile("fragment_shot", FragmentShotEntity::new);
		public static final RegistryObject<EntityType<FungalBallEntity>> FUNGAL_BALL = registerProjectile("fungal_ball", FungalBallEntity::new);
		public static final RegistryObject<EntityType<FungalRockFragmentEntity>> FUNGAL_ROCK_FRAGMENT = registerProjectile("fungal_rock_fragment", FungalRockFragmentEntity::new);
		public static final RegistryObject<EntityType<GhoulBallEntity>> GHOUL_BALL = registerProjectile("ghoul_ball", GhoulBallEntity::new);
		public static final RegistryObject<EntityType<GhoulShotEntity>> GHOUL_SHOT = registerProjectile("ghoul_shot", GhoulShotEntity::new);
		public static final RegistryObject<EntityType<GigaGreenBallEntity>> GIGA_GREEN_BALL = registerProjectile("giga_green_ball", GigaGreenBallEntity::new);
		public static final RegistryObject<EntityType<GoldenCannonballEntity>> GOLDEN_CANNONBALL = registerProjectile("golden_cannonball", GoldenCannonballEntity::new);
		public static final RegistryObject<EntityType<GoldShotEntity>> GOLD_SHOT = registerProjectile("gold_shot", GoldShotEntity::new);
		public static final RegistryObject<EntityType<GooBallEntity>> GOO_BALL = registerProjectile("goo_ball", GooBallEntity::new);
		public static final RegistryObject<EntityType<GrawShotEntity>> GRAW_SHOT = registerProjectile("graw_shot", GrawShotEntity::new);
		public static final RegistryObject<EntityType<GreenBulletEntity>> GREEN_BULLET = registerProjectile("green_bullet", GreenBulletEntity::new);
		public static final RegistryObject<EntityType<GreenGuardianShotEntity>> GREEN_GUARDIAN_SHOT = registerProjectile("green_guardian_shot", GreenGuardianShotEntity::new);
		public static final RegistryObject<EntityType<GrenadeEntity>> GRENADE = registerProjectile("grenade", GrenadeEntity::new);
		public static final RegistryObject<EntityType<HagShotEntity>> HAG_SHOT = registerProjectile("hag_shot", HagShotEntity::new);
		public static final RegistryObject<EntityType<HardenedParapiranhaEntity>> HARDENED_PARAPIRANHA = registerProjectile("hardened_parapiranha", HardenedParapiranhaEntity::new);
		public static final RegistryObject<EntityType<HaunterShotEntity>> HAUNTER_SHOT = registerProjectile("haunter_shot", HaunterShotEntity::new);
		public static final RegistryObject<EntityType<HeavyBlueCannonballEntity>> HEAVY_BLUE_CANNONBALL = registerProjectile("heavy_blue_cannonball", HeavyBlueCannonballEntity::new);
		public static final RegistryObject<EntityType<HeavyBoneCannonballEntity>> HEAVY_BONE_CANNONBALL = registerProjectile("heavy_bone_cannonball", HeavyBoneCannonballEntity::new);
		public static final RegistryObject<EntityType<HeavyCannonballEntity>> HEAVY_CANNONBALL = registerProjectile("heavy_cannonball", HeavyCannonballEntity::new);
		public static final RegistryObject<EntityType<HeavyGrenadeEntity>> HEAVY_GRENADE = registerProjectile("heavy_grenade", HeavyGrenadeEntity::new);
		public static final RegistryObject<EntityType<HeavyRedBulletEntity>> HEAVY_RED_BULLET = registerProjectile("heavy_red_bullet", HeavyRedBulletEntity::new);
		public static final RegistryObject<EntityType<HeavyRedCannonballEntity>> HEAVY_RED_CANNONBALL = registerProjectile("heavy_red_cannonball", HeavyRedCannonballEntity::new);
		public static final RegistryObject<EntityType<HeavyRunicGuardianShotEntity>> HEAVY_RUNIC_GUARDIAN_SHOT = registerProjectile("heavy_runic_guardian_shot", HeavyRunicGuardianShotEntity::new);
		public static final RegistryObject<EntityType<HeavyShadowballEntity>> HEAVY_SHADOWBALL = registerProjectile("heavy_shadowball", HeavyShadowballEntity::new);
		public static final RegistryObject<EntityType<HeavyShowerShotEntity>> HEAVY_SHOWER_SHOT = registerProjectile("heavy_shower_shot", HeavyShowerShotEntity::new);
		public static final RegistryObject<EntityType<HeavyTriDischargeShotEntity>> HEAVY_TRI_DISCHARGE_SHOT = registerProjectile("heavy_tri_discharge_shot", HeavyTriDischargeShotEntity::new);
		public static final RegistryObject<EntityType<HeavyWitherBallEntity>> HEAVY_WITHER_BALL = registerProjectile("heavy_wither_ball", HeavyWitherBallEntity::new);
		public static final RegistryObject<EntityType<HellfireEntity>> HELLFIRE = registerProjectile("hellfire", HellfireEntity::new);
		public static final RegistryObject<EntityType<HellfireProjectileEntity>> HELLFIRE_TAIL = registerProjectile("hellfire_tail", HellfireProjectileEntity::new);
		public static final RegistryObject<EntityType<HellBubbleShotEntity>> HELL_BUBBLE_SHOT = registerProjectile("hell_bubble", HellBubbleShotEntity::new);
		public static final RegistryObject<EntityType<HiveBallEntity>> HIVE_BALL = registerProjectile("hive_ball", HiveBallEntity::new);
		public static final RegistryObject<EntityType<HotShotEntity>> HOT_SHOT = registerProjectile("hot_shot", HotShotEntity::new);
		public static final RegistryObject<EntityType<IceShotEntity>> ICE_SHOT = registerProjectile("ice_shot", IceShotEntity::new);
		public static final RegistryObject<EntityType<IllusionShotEntity>> ILLUSION_SHOT = registerProjectile("illusion_shot", IllusionShotEntity::new);
		public static final RegistryObject<EntityType<IonShotEntity>> ION_SHOT = registerProjectile("ion_shot", IonShotEntity::new, 0.25f, 0.3f);
		public static final RegistryObject<EntityType<IroMinerShotEntity>> IRO_MINER_SHOT = registerProjectile("iro_miner_shot", IroMinerShotEntity::new);
		public static final RegistryObject<EntityType<LaserShotEntity>> LASER_SHOT = registerProjectile("laser_shot", LaserShotEntity::new);
		public static final RegistryObject<EntityType<LelyetianShotEntity>> LELYETIAN_SHOT = registerProjectile("lelyetian_shot", LelyetianShotEntity::new);
		public static final RegistryObject<EntityType<LightBlasterShotEntity>> LIGHT_BLASTER_SHOT = registerProjectile("light_blaster_shot", LightBlasterShotEntity::new);
		public static final RegistryObject<EntityType<LightIronShotEntity>> LIGHT_IRON_SHOT = registerProjectile("light_iron_shot", LightIronShotEntity::new);
		public static final RegistryObject<EntityType<LightRunicGuardianShotEntity>> LIGHT_RUNIC_GUARDIAN_SHOT = registerProjectile("light_runic_guardian_shot", LightRunicGuardianShotEntity::new);
		public static final RegistryObject<EntityType<LightSparkEntity>> LIGHT_SPARK = registerProjectile("light_spark", LightSparkEntity::new);
		public static final RegistryObject<EntityType<LunarFallEntity>> LUNAR_FALL = registerProjectile("lunar_fall", LunarFallEntity::new);
		public static final RegistryObject<EntityType<LunaShotEntity>> LUNA_SHOT = registerProjectile("luna_shot", LunaShotEntity::new);
		public static final RegistryObject<EntityType<LuxonSticklerShotEntity>> LUXON_STICKLER_SHOT = registerProjectile("luxon_stickler_shot", LuxonSticklerShotEntity::new);
		public static final RegistryObject<EntityType<LuxonSticklerStuckEntity>> LUXON_STICKLER_STUCK = registerProjectile("luxon_stickler_stuck", LuxonSticklerStuckEntity::new);
		public static final RegistryObject<EntityType<LyonicShotEntity>> LYONIC_SHOT = registerProjectile("lyonic_shot", LyonicShotEntity::new);
		public static final RegistryObject<EntityType<MagicBallEntity>> MAGIC_BALL = registerProjectile("magic_ball", MagicBallEntity::new);
		public static final RegistryObject<EntityType<MechFallEntity>> MECH_FALL = registerProjectile("mech_fall", MechFallEntity::new);
		public static final RegistryObject<EntityType<MechShotEntity>> MECH_SHOT = registerProjectile("mech_shot", MechShotEntity::new);
		public static final RegistryObject<EntityType<MetalSlugEntity>> METAL_SLUG = registerProjectile("metal_slug", MetalSlugEntity::new);
		public static final RegistryObject<EntityType<MeteorFallEntity>> METEOR_FALL = registerProjectile("meteor_fall", MeteorFallEntity::new);
		public static final RegistryObject<EntityType<MindBlasterShotEntity>> MIND_BLASTER_SHOT = registerProjectile("mind_blaster_shot", MindBlasterShotEntity::new);
		public static final RegistryObject<EntityType<MiniGreenBallEntity>> MINI_GREEN_BALL = registerProjectile("mini_green_ball", MiniGreenBallEntity::new);
		public static final RegistryObject<EntityType<ModuloShotEntity>> MODULO_SHOT = registerProjectile("modulo_shot", ModuloShotEntity::new);
		public static final RegistryObject<EntityType<MoonlightFallEntity>> MOONLIGHT_FALL = registerProjectile("moonlight_fall", MoonlightFallEntity::new);
		public static final RegistryObject<EntityType<MoonDestroyerShotEntity>> MOON_DESTROYER_SHOT = registerProjectile("moon_destroyer_shot", MoonDestroyerShotEntity::new);
		public static final RegistryObject<EntityType<MoonMakerEntity>> MOON_MAKER = registerProjectile("moon_maker", MoonMakerEntity::new);
		public static final RegistryObject<EntityType<MoonShinerEntity>> MOON_SHINER_SHOT = registerProjectile("moon_shiner_shot", MoonShinerEntity::new);
		public static final RegistryObject<EntityType<MoonShotEntity>> MOON_SHOT = registerProjectile("moon_shot", MoonShotEntity::new);
		public static final RegistryObject<EntityType<MultiplyingGrenadeEntity>> MULTIPLYING_GRENADE = registerProjectile("multiplying_grenade", MultiplyingGrenadeEntity::new);
		public static final RegistryObject<EntityType<NethengeicWitherShotEntity>> NETHENGEIC_WITHER_SHOT = registerProjectile("nethengeic_wither_shot", NethengeicWitherShotEntity::new);
		public static final RegistryObject<EntityType<NightmareFallEntity>> NIGHTMARE_FALL = registerProjectile("nightmare_fall", NightmareFallEntity::new);
		public static final RegistryObject<EntityType<NoxiousShotEntity>> NOXIOUS_SHOT = registerProjectile("noxious_shot", NoxiousShotEntity::new);
		public static final RegistryObject<EntityType<OdiousEntity>> ODIOUS_SHOT = registerProjectile("odious_shot", OdiousEntity::new);
		public static final RegistryObject<EntityType<OmnilightShotEntity>> OMNILIGHT_SHOT = registerProjectile("omnilight_shot", OmnilightShotEntity::new);
		public static final RegistryObject<EntityType<OrangeCannonballEntity>> ORANGE_CANNONBALL = registerProjectile("orange_cannonball", OrangeCannonballEntity::new);
		public static final RegistryObject<EntityType<OrbocronEntity>> ORBOCRON_SHOT = registerProjectile("orbocron_shot", OrbocronEntity::new);
		public static final RegistryObject<EntityType<ParalyzerShotEntity>> PARALYZER_SHOT = registerProjectile("paralyzer_shot", ParalyzerShotEntity::new);
		public static final RegistryObject<EntityType<PartyPopperEntity>> PARTY_POPPER_SHOT = registerProjectile("party_popper_shot", PartyPopperEntity::new);
		public static final RegistryObject<EntityType<PhantomShotEntity>> PHANTOM_SHOT = registerProjectile("phantom_shot", PhantomShotEntity::new);
		public static final RegistryObject<EntityType<PlutonSticklerShotEntity>> PLUTON_STICKLER_SHOT = registerProjectile("pluton_stickler_shot", PlutonSticklerShotEntity::new);
		public static final RegistryObject<EntityType<PlutonSticklerStuckEntity>> PLUTON_STICKLER_STUCK = registerProjectile("pluton_stickler_stuck", PlutonSticklerStuckEntity::new);
		public static final RegistryObject<EntityType<PoisonPlungerEntity>> POISON_PLUNGER_SHOT = registerProjectile("poison_plunger_shot", PoisonPlungerEntity::new);
		public static final RegistryObject<EntityType<PoisonShotEntity>> POISON_SHOT = registerProjectile("poison_shot", PoisonShotEntity::new);
		public static final RegistryObject<EntityType<PolymorphShotEntity>> POLYMORPH_SHOT = registerProjectile("polymorph_shot", PolymorphShotEntity::new);
		public static final RegistryObject<EntityType<PolytomShotEntity>> POLYTOM_SHOT = registerProjectile("polytom_shot", PolytomShotEntity::new);
		public static final RegistryObject<EntityType<PopShotEntity>> POP_SHOT = registerProjectile("pop_shot", PopShotEntity::new);
		public static final RegistryObject<EntityType<PowerRayEntity>> POWER_RAY = registerProjectile("power_ray", PowerRayEntity::new);
		public static final RegistryObject<EntityType<PowerShotEntity>> POWER_SHOT = registerProjectile("power_shot", PowerShotEntity::new);
		public static final RegistryObject<EntityType<PrimordialShotEntity>> PRIMORDIAL_SHOT = registerProjectile("primordial_shot", PrimordialShotEntity::new);
		public static final RegistryObject<EntityType<ProtonShotEntity>> PROTON_SHOT = registerProjectile("proton_shot", ProtonShotEntity::new);
		public static final RegistryObject<EntityType<RainbowShotEntity>> RAINBOW_SHOT = registerProjectile("rainbow_shot", RainbowShotEntity::new);
		public static final RegistryObject<EntityType<RedBulletEntity>> RED_BULLET = registerProjectile("red_bullet", RedBulletEntity::new);
		public static final RegistryObject<EntityType<RedGuardianShotEntity>> RED_GUARDIAN_SHOT = registerProjectile("red_guardian_shot", RedGuardianShotEntity::new);
		public static final RegistryObject<EntityType<ReeferShotEntity>> REEFER_SHOT = registerProjectile("reefer_shot", ReeferShotEntity::new);
		public static final RegistryObject<EntityType<RevolutionShotEntity>> REVOLUTION_SHOT = registerProjectile("revolution_shot", RevolutionShotEntity::new);
		public static final RegistryObject<EntityType<RockFragmentEntity>> ROCK_FRAGMENT = registerProjectile("rock_fragment", RockFragmentEntity::new);
		public static final RegistryObject<EntityType<RosidianShotEntity>> ROSIDIAN_SHOT = registerProjectile("rosidian_shot", RosidianShotEntity::new);
		public static final RegistryObject<EntityType<RPGEntity>> RPG = registerProjectile("rpg", RPGEntity::new);
		public static final RegistryObject<EntityType<RunicBombEntity>> RUNIC_BOMB = registerProjectile("runic_bomb", RunicBombEntity::new);
		public static final RegistryObject<EntityType<RunicGuardianShotEntity>> RUNIC_GUARDIAN_SHOT = registerProjectile("runic_guardian_shot", RunicGuardianShotEntity::new);
		public static final RegistryObject<EntityType<SeaocronEntity>> SEAOCRON_SHOT = registerProjectile("seaocron_shot", SeaocronEntity::new);
		public static final RegistryObject<EntityType<SeedDartEntity>> SEED_DART = registerProjectile("seed_dart", SeedDartEntity::new);
		public static final RegistryObject<EntityType<SelyanSticklerShotEntity>> SELYAN_STICKLER_SHOT = registerProjectile("selyan_stickler_shot", SelyanSticklerShotEntity::new);
		public static final RegistryObject<EntityType<SelyanSticklerStuckEntity>> SELYAN_STICKLER_STUCK = registerProjectile("selyan_stickler_stuck", SelyanSticklerStuckEntity::new);
		public static final RegistryObject<EntityType<ShadowlordShotEntity>> SHADOWLORD_SHOT = registerProjectile("shadowlord_shot", ShadowlordShotEntity::new);
		public static final RegistryObject<EntityType<ShoeShotEntity>> SHOE_SHOT = registerProjectile("shoe_shot", ShoeShotEntity::new);
		public static final RegistryObject<EntityType<ShowerShotEntity>> SHOWER_SHOT = registerProjectile("shower_shot", ShowerShotEntity::new);
		public static final RegistryObject<EntityType<ShroomBulletEntity>> SHROOM_BULLET = registerProjectile("shroom_bullet", ShroomBulletEntity::new);
		public static final RegistryObject<EntityType<ShyreBeamEntity>> SHYRE_BEAM = registerProjectile("shyre_beam", ShyreBeamEntity::new);
		public static final RegistryObject<EntityType<ShyreShotEntity>> SHYRE_SHOT = registerProjectile("shyre_shot", ShyreShotEntity::new);
		public static final RegistryObject<EntityType<SkulloShotEntity>> SKULLO_SHOT = registerProjectile("skullo_shot", SkulloShotEntity::new);
		public static final RegistryObject<EntityType<SkyShotEntity>> SKY_SHOT = registerProjectile("sky_shot", SkyShotEntity::new);
		public static final RegistryObject<EntityType<SliceStarEntity>> SLICE_STAR = registerProjectile("slice_star", SliceStarEntity::new);
		public static final RegistryObject<EntityType<SmileyCannonballEntity>> SMILEY_CANNONBALL = registerProjectile("smiley_cannonball", SmileyCannonballEntity::new);
		public static final RegistryObject<EntityType<SmileBlasterEntity>> SMILE_BLASTER = registerProjectile("smile_blaster", SmileBlasterEntity::new);
		public static final RegistryObject<EntityType<SniperSlugEntity>> SNIPER_SLUG = registerProjectile("sniper_slug", SniperSlugEntity::new);
		public static final RegistryObject<EntityType<SoulDrainerShotEntity>> SOUL_DRAINER_SHOT = registerProjectile("soul_drainer_shot", SoulDrainerShotEntity::new);
		public static final RegistryObject<EntityType<SoulSparkEntity>> SOUL_SPARK = registerProjectile("soul_spark", SoulSparkEntity::new);
		public static final RegistryObject<EntityType<SoulStormEntity>> SOUL_STORM_SHOT = registerProjectile("soul_storm_shot", SoulStormEntity::new);
		public static final RegistryObject<EntityType<SpectralShotEntity>> SPECTRAL_SHOT = registerProjectile("spectral_shot", SpectralShotEntity::new);
		public static final RegistryObject<EntityType<SpiritualShotEntity>> SPIRITUAL_SHOT = registerProjectile("spiritual_shot", SpiritualShotEntity::new);
		public static final RegistryObject<EntityType<StickyCoolBombEntity>> STICKY_COOL_BOMB = registerProjectile("sticky_cool_bomb", StickyCoolBombEntity::new);
		public static final RegistryObject<EntityType<StickyRedBombEntity>> STICKY_RED_BOMB = registerProjectile("sticky_red_bomb", StickyRedBombEntity::new);
		public static final RegistryObject<EntityType<SunsetBulletEntity>> SUNSET_BULLET = registerProjectile("sunset_bullet", SunsetBulletEntity::new);
		public static final RegistryObject<EntityType<SunShotEntity>> SUN_SHOT = registerProjectile("sun_shot", SunShotEntity::new);
		public static final RegistryObject<EntityType<SuperGreenBallEntity>> SUPER_GREEN_BALL = registerProjectile("super_green_ball", SuperGreenBallEntity::new);
		public static final RegistryObject<EntityType<SwarmShotEntity>> SWARM_SHOT = registerProjectile("swarm_shot", SwarmShotEntity::new);
		public static final RegistryObject<EntityType<TangleFallEntity>> TANGLE_FALL = registerProjectile("tangle_fall", TangleFallEntity::new);
		public static final RegistryObject<EntityType<ConstructTerrorShotEntity>> TERROR_CONSTRUCT_SHOT = registerProjectile("terror_construct_shot", ConstructTerrorShotEntity::new);
		public static final RegistryObject<EntityType<TidalWaveEntity>> TIDAL_WAVE = registerProjectile("tidal_wave", TidalWaveEntity::new);
		public static final RegistryObject<EntityType<ToxicBulletEntity>> TOXIC_BULLET = registerProjectile("toxic_bullet", ToxicBulletEntity::new);
		public static final RegistryObject<EntityType<ToxicShotEntity>> TOXIC_SHOT = registerProjectile("toxic_shot", ToxicShotEntity::new);
		public static final RegistryObject<EntityType<TriDischargeShotEntity>> TRI_DISCHARGE_SHOT = registerProjectile("tri_discharge_shot", TriDischargeShotEntity::new);
		public static final RegistryObject<EntityType<UltimatumShotEntity>> ULTIMATUM_SHOT = registerProjectile("ultimatum_shot", UltimatumShotEntity::new);
		public static final RegistryObject<EntityType<UltraGreenBallEntity>> ULTRA_GREEN_BALL = registerProjectile("ultra_green_ball", UltraGreenBallEntity::new);
		public static final RegistryObject<EntityType<ValkyrieShotEntity>> VALKYRIE_SHOT = registerProjectile("valkyrie_shot", ValkyrieShotEntity::new);
		public static final RegistryObject<EntityType<VineWizardShotEntity>> VINE_WIZARD_SHOT = registerProjectile("vine_wizard_shot", VineWizardShotEntity::new);
		public static final RegistryObject<EntityType<VolatileCannonballEntity>> VOLATILE_CANNONBALL = registerProjectile("volatile_cannonball", VolatileCannonballEntity::new);
		public static final RegistryObject<EntityType<VortexBlastEntity>> VORTEX_BLAST = registerProjectile("vortex_blast", VortexBlastEntity::new);
		public static final RegistryObject<EntityType<VoxxulonMeteorEntity>> VOXXULON_METEOR = registerProjectile("voxxulon_meteor", VoxxulonMeteorEntity::new);
		public static final RegistryObject<EntityType<VoxCannonEntity>> VOX_CANNON = registerProjectile("vox_cannon", VoxCannonEntity::new);
		public static final RegistryObject<EntityType<VulkramEntity>> VULKRAM = registerProjectile("vulkram", VulkramEntity::new);
		public static final RegistryObject<EntityType<WartDartEntity>> WART_DART = registerProjectile("wart_dart", WartDartEntity::new);
		public static final RegistryObject<EntityType<WaterBalloonBombEntity>> WATER_BALLOON_BOMB = registerProjectile("water_balloon_bomb", WaterBalloonBombEntity::new);
		public static final RegistryObject<EntityType<WaterShotEntity>> WATER_SHOT = registerProjectile("water_shot", WaterShotEntity::new);
		public static final RegistryObject<EntityType<WeightedShowerShotEntity>> WEIGHTED_SHOWER_SHOT = registerProjectile("weighted_shower_shot", WeightedShowerShotEntity::new);
		public static final RegistryObject<EntityType<WhiteBallEntity>> WHITE_BALL = registerProjectile("white_ball", WhiteBallEntity::new);
		public static final RegistryObject<EntityType<WinderShotEntity>> WINDER_SHOT = registerProjectile("winder_shot", WinderShotEntity::new);
		public static final RegistryObject<EntityType<WitherBallEntity>> WITHER_BALL = registerProjectile("wither_ball", WitherBallEntity::new);
		public static final RegistryObject<EntityType<WitherShotEntity>> WITHER_SHOT = registerProjectile("wither_shot", WitherShotEntity::new);
		public static final RegistryObject<EntityType<WrathShotEntity>> WRATH_SHOT = registerProjectile("wrath_shot", WrathShotEntity::new);
		public static final RegistryObject<EntityType<YellowBulletEntity>> YELLOW_BULLET = registerProjectile("yellow_bullet", YellowBulletEntity::new);
		public static final RegistryObject<EntityType<YellowGuardianShotEntity>> YELLOW_GUARDIAN_SHOT = registerProjectile("yellow_guardian_shot", YellowGuardianShotEntity::new);

		private static <T extends Entity> RegistryObject<EntityType<T>> registerProjectile(String registryName, EntityType.IFactory<T> factory) {
			return registerProjectile(registryName, factory, 0.25f, 0.25f);
		}

		private static <T extends Entity> RegistryObject<EntityType<T>> registerProjectile(String registryName, EntityType.IFactory<T> factory, float width, float height) {
			EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, EntityClassification.MISC).sized(width, height).setTrackingRange(120).setUpdateInterval(20);

			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return PROJECTILES.register(registryName, () -> entityType);
		}
	}
}
