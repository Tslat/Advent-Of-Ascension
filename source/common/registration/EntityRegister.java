package net.tslat.aoa3.common.registration;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.tslat.aoa3.entity.animals.*;
import net.tslat.aoa3.entity.boss.bane.EntityBane;
import net.tslat.aoa3.entity.boss.bane.EntityBaneBig;
import net.tslat.aoa3.entity.boss.bane.EntityBaneClone;
import net.tslat.aoa3.entity.boss.baroness.EntityBaronBomb;
import net.tslat.aoa3.entity.boss.baroness.EntityBaroness;
import net.tslat.aoa3.entity.boss.clunkhead.EntityClunkhead;
import net.tslat.aoa3.entity.boss.coniferon.EntityConiferon;
import net.tslat.aoa3.entity.boss.corallus.EntityCorallus;
import net.tslat.aoa3.entity.boss.cottoncandor.EntityCottonCandor;
import net.tslat.aoa3.entity.boss.craexxeus.EntityCraexxeus;
import net.tslat.aoa3.entity.boss.craexxeus.EntityXxeus;
import net.tslat.aoa3.entity.boss.creep.EntityCreep;
import net.tslat.aoa3.entity.boss.crystocore.EntityCrystocore;
import net.tslat.aoa3.entity.boss.dracyon.EntityDracyon;
import net.tslat.aoa3.entity.boss.elusive.EntityElusive;
import net.tslat.aoa3.entity.boss.elusive.EntityElusiveClone;
import net.tslat.aoa3.entity.boss.flash.EntityFlash;
import net.tslat.aoa3.entity.boss.fourguardians.EntityBlueGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityGreenGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityRedGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityYellowGuardian;
import net.tslat.aoa3.entity.boss.goldorth.EntityGoldorth;
import net.tslat.aoa3.entity.boss.graw.EntityGraw;
import net.tslat.aoa3.entity.boss.gyro.EntityGyro;
import net.tslat.aoa3.entity.boss.gyro.EntityGyrocopter;
import net.tslat.aoa3.entity.boss.hiveking.EntityHiveKing;
import net.tslat.aoa3.entity.boss.hiveking.EntityHiveWorker;
import net.tslat.aoa3.entity.boss.horon.EntityHoron;
import net.tslat.aoa3.entity.boss.hydrolisk.EntityHydrolisk;
import net.tslat.aoa3.entity.boss.hydrolisk.EntityHydrolon;
import net.tslat.aoa3.entity.boss.kingbambambam.EntityKingBamBamBam;
import net.tslat.aoa3.entity.boss.kingbambambam.EntityLittleBam;
import net.tslat.aoa3.entity.boss.kingshroomus.EntityKingShroomus;
import net.tslat.aoa3.entity.boss.klobber.EntityKlobber;
import net.tslat.aoa3.entity.boss.kror.EntityKror;
import net.tslat.aoa3.entity.boss.mechbot.EntityMechbot;
import net.tslat.aoa3.entity.boss.mirage.EntityMirage;
import net.tslat.aoa3.entity.boss.nethengeicwither.EntityNethengeicWither;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;
import net.tslat.aoa3.entity.boss.primordialfive.*;
import net.tslat.aoa3.entity.boss.proshield.EntityProshield;
import net.tslat.aoa3.entity.boss.rockrider.EntityRockRider;
import net.tslat.aoa3.entity.boss.shadowlord.EntityShadowlord;
import net.tslat.aoa3.entity.boss.silverfoot.EntitySilverfoot;
import net.tslat.aoa3.entity.boss.skeletalarmy.*;
import net.tslat.aoa3.entity.boss.smash.EntitySmash;
import net.tslat.aoa3.entity.boss.tyrosaur.EntityTyrosaur;
import net.tslat.aoa3.entity.boss.vinocorne.*;
import net.tslat.aoa3.entity.boss.visualent.EntityVisualent;
import net.tslat.aoa3.entity.boss.voxxulon.EntityVoxxulon;
import net.tslat.aoa3.entity.minions.*;
import net.tslat.aoa3.entity.misc.*;
import net.tslat.aoa3.entity.misc.pixon.*;
import net.tslat.aoa3.entity.misc.tablet.*;
import net.tslat.aoa3.entity.mobs.abyss.*;
import net.tslat.aoa3.entity.mobs.barathos.*;
import net.tslat.aoa3.entity.mobs.candyland.*;
import net.tslat.aoa3.entity.mobs.celeve.*;
import net.tslat.aoa3.entity.mobs.creeponia.*;
import net.tslat.aoa3.entity.mobs.crystevia.*;
import net.tslat.aoa3.entity.mobs.deeplands.*;
import net.tslat.aoa3.entity.mobs.dustopia.*;
import net.tslat.aoa3.entity.mobs.gardencia.*;
import net.tslat.aoa3.entity.mobs.greckon.*;
import net.tslat.aoa3.entity.mobs.haven.*;
import net.tslat.aoa3.entity.mobs.immortallis.*;
import net.tslat.aoa3.entity.mobs.iromine.*;
import net.tslat.aoa3.entity.mobs.lborean.*;
import net.tslat.aoa3.entity.mobs.lelyetia.*;
import net.tslat.aoa3.entity.mobs.lunalus.*;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSkeleton;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSpider;
import net.tslat.aoa3.entity.mobs.mysterium.*;
import net.tslat.aoa3.entity.mobs.nether.*;
import net.tslat.aoa3.entity.mobs.overworld.*;
import net.tslat.aoa3.entity.mobs.overworld.bigday.*;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityAnemia;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityBloodmist;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityLinger;
import net.tslat.aoa3.entity.mobs.overworld.creepday.EntityHost;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityDeathHunter;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityHeadlessDestroyer;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityReaperTwins;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityTriclops;
import net.tslat.aoa3.entity.mobs.overworld.fullmoon.*;
import net.tslat.aoa3.entity.mobs.overworld.lunarinvasion.*;
import net.tslat.aoa3.entity.mobs.overworld.soulscurry.*;
import net.tslat.aoa3.entity.mobs.precasia.*;
import net.tslat.aoa3.entity.mobs.runandor.*;
import net.tslat.aoa3.entity.mobs.runandor.templars.*;
import net.tslat.aoa3.entity.mobs.shyrelands.*;
import net.tslat.aoa3.entity.mobs.voxponds.*;
import net.tslat.aoa3.entity.npcs.ambient.EntityGorbCitizen;
import net.tslat.aoa3.entity.npcs.ambient.EntityPrimordialGuide;
import net.tslat.aoa3.entity.npcs.ambient.EntityZalChild;
import net.tslat.aoa3.entity.npcs.ambient.EntityZalCitizen;
import net.tslat.aoa3.entity.npcs.banker.*;
import net.tslat.aoa3.entity.npcs.lottoman.*;
import net.tslat.aoa3.entity.npcs.skillmaster.*;
import net.tslat.aoa3.entity.npcs.trader.*;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntityPopShot;
import net.tslat.aoa3.entity.projectiles.arrow.EntitySpectralHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntityTippedHollyArrow;
import net.tslat.aoa3.entity.projectiles.blaster.*;
import net.tslat.aoa3.entity.projectiles.cannon.*;
import net.tslat.aoa3.entity.projectiles.gun.*;
import net.tslat.aoa3.entity.projectiles.misc.*;
import net.tslat.aoa3.entity.projectiles.mob.*;
import net.tslat.aoa3.entity.projectiles.staff.*;
import net.tslat.aoa3.entity.projectiles.thrown.*;

@Mod.EventBusSubscriber
public class EntityRegister {
	static int entityId = 0;

	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityEntry> ev) {
		ev.getRegistry().registerAll(buildMobEntries());
		ev.getRegistry().registerAll(buildMinionEntries());
		ev.getRegistry().registerAll(buildEntityEntries());
		ev.getRegistry().registerAll(buildMiscEntityEntries());
		ev.getRegistry().registerAll(buildProjectileEntries());
	}

	private static EntityEntry[] buildMobEntries() {
		return new EntityEntry[]{
				newEntry("airhead", EntityAirhead.class, 8506334, 6004943),
				newEntry("alarmo", EntityAlarmo.class, 14555160, 6048330),
				newEntry("amphibior", EntityAmphibior.class, 6026738, 16757504),
				newEntry("amphibiyte", EntityAmphibiyte.class, 5140613, 16744695),
				newEntry("ancient_golem", EntityAncientGolem.class, 3352336, 11373902),
				newEntry("anemia", EntityAnemia.class, 5179400, 16711680),
				newEntry("angelica", EntityAngelica.class, 3579029, 13290663),
				newEntry("angler", EntityAngler.class, 5667476, 8604807),
				newEntry("apparition", EntityApparition.class, 7040112, 526344),
				newEntry("arc_flower", EntityArcFlower.class, 5418751, 15073024),
				newEntry("arc_wizard", EntityArcWizard.class, 15269714, 4915148),
				newEntry("arcbeast", EntityArcbeast.class, 13886315, 14869466),
				newEntry("archvine", EntityArchvine.class, 15616, 1835776),
				newEntry("arcworm", EntityArcworm.class, 16768256, 9109501),
				newEntry("ariel", EntityAriel.class, 6809323, 13959166),
				newEntry("arkback", EntityArkback.class, 12099007, 8943244),
				newEntry("arkzyne", EntityArkzyne.class, 789516, 10690061),
				newEntry("arocknid", EntityArocknid.class, 7235689, 13218830),
				newEntry("axiolight", EntityAxiolight.class, 16777215, 5820635),
				newEntry("bane", EntityBane.class, 7868105, 4619942),
				newEntry("banshee", EntityBanshee.class, 11572669, 8202406),
				newEntry("baroness", EntityBaroness.class, 5377810, 2362949),
				newEntry("basilisk", EntityBasilisk.class, 1644570, 4736842),
				newEntry("baumba", EntityBaumba.class, 9184292, 9071156),
				newEntry("black_ursa", EntityBlackUrsa.class, 4341560, 8026227),
				newEntry("bloodmist", EntityBloodmist.class, 11740453, 14577519),
				newEntry("bloodsucker", EntityBloodsucker.class, 3673357, 8397860),
				newEntry("blue_automaton", EntityBlueAutomaton.class, 6652835, 10457021),
				newEntry("blue_flower", EntityBlueFlower.class, 5086275, 4223129),
				newEntry("blue_guardian", EntityBlueGuardian.class, 10266029, 8167615),
				newEntry("blue_rune_templar", EntityRuneTemplarBlue.class, 16744228, 7194313),
				newEntry("blue_runic_lifeform", EntityRunicLifeformBlue.class, 496022, 463763),
				newEntry("bobo", EntityBobo.class, 1782866, 2249377),
				newEntry("bomb_carrier", EntityBombCarrier.class, 11382918, 2037510),
				newEntry("bone_creature", EntityBoneCreature.class, 15132390, 13617352),
				newEntry("bone_creeper", EntityBoneCreeper.class, 10922149, 2915064),
				newEntry("boneback", EntityBoneback.class, 6182726, 3052700),
				newEntry("bouncer", EntityBouncer.class, 5537210, 13685462),
				newEntry("broccohead", EntityBroccohead.class, 2511901, 5668175),
				newEntry("brute", EntityBrute.class, 6904102, 8420984),
				newEntry("bugeye", EntityBugeye.class, 992269, 7243115),
				newEntry("bush_baby", EntityBushBaby.class, 7243115, 5721387),
				newEntry("candy_corny", EntityCandyCorny.class, 13932049, 15459377),
				newEntry("cane_bug", EntityCaneBug.class, 4352288, 16119280),
				newEntry("carrotop", EntityCarrotop.class, 16747528, 4914952),
				newEntry("case_construct", EntityCaseConstruct.class, 8026483, 13615203),
				newEntry("cave_creep", EntityCaveCreep.class, 8026483, 11579307),
				newEntry("cave_creepoid", EntityCaveCreepoid.class, 3684406, 8026744),
				newEntry("centinel", EntityCentinel.class, 3951159, 4606532),
				newEntry("charger", EntityCharger.class, 14081596, 14081596),
				newEntry("cherry_barrager", EntityCherryBarrager.class, 14032414, 7374678),
				newEntry("cherry_blaster", EntityCherryBlaster.class, 14032414, 5400890),
				newEntry("chimera", EntityChimera.class, 14736043, 4206102),
				newEntry("chocko", EntityChocko.class, 8004884, 3346956),
				newEntry("chomper", EntityChomper.class, 4479279, 2043409),
				//newEntry("cindaxas", EntityCindaxas.class),
				newEntry("clown", EntityClown.class, 5376265, 14597565),
				newEntry("clunkhead", EntityClunkhead.class, 1393533, 6322068),
				newEntry("coniferon", EntityConiferon.class, 2694151, 1452309),
				newEntry("construct_flight", EntityConstructFlight.class, 5033917, 1382167),
				newEntry("construct_mind", EntityConstructMind.class, 1711130, 4298362),
				newEntry("construct_range", EntityConstructRange.class, 6427013, 789261),
				newEntry("construct_resistance", EntityConstructResistance.class, 1002578, 3486008),
				newEntry("construct_speed", EntityConstructSpeed.class, 11768628, 6907225),
				newEntry("construct_strength", EntityConstructStrength.class, 11021588, 3354157),
				newEntry("construct_terror", EntityConstructTerror.class, 4299043, 3354157),
				newEntry("corallus", EntityCorallus.class, 36307, 11862175),
				newEntry("coralon", EntityCoralon.class, 32221, 16120064),
				newEntry("corny", EntityCorny.class, 35104, 13551360),
				newEntry("cotton_candor", EntityCottonCandor.class, 13990125, 12926954),
				newEntry("craexxeus", EntityCraexxeus.class, 14847254, 2876115),
				newEntry("creep", EntityCreep.class, 818944, 4608621),
				newEntry("creeperlock", EntityCreeperlock.class, 818944, 5777591),
				newEntry("creepird", EntityCreepird.class, 818944, 3780390),
				newEntry("creepuple", EntityCreepuple.class, 818944, 3843364),
				newEntry("crusilisk", EntityCrusilisk.class, 660523, 15569979),
				newEntry("cryptid", EntityCryptid.class, 10038339, 10180711),
				newEntry("crystocore", EntityCrystocore.class, 1315589, 3812640),
				newEntry("cyclops", EntityCyclops.class, 7500352, 11645566),
				newEntry("dark_beast", EntityDarkBeast.class, 2699834, 2632478),
				newEntry("dawnlight", EntityDawnlight.class, 10796201, 1948221),
				newEntry("daysee", EntityDaysee.class, 11913662, 6272881),
				newEntry("dead_tree", EntityDeadTree.class, 4470537, 2505992),
				newEntry("death_hunter", EntityDeathHunter.class, 6291456, 14680832),
				newEntry("deinotherium", EntityDeinotherium.class, 12040114, 10855832),
				newEntry("demon_reaper", EntityDemonReaper.class, 1968647, 13848626),
				newEntry("desert_charger", EntityDesertCharger.class, 8487996, 10194503),
				newEntry("destructor", EntityDestructor.class, 2895664, 10696493),
				newEntry("devourer", EntityDevourer.class, 1249827, 3750461),
				newEntry("dicer", EntityDicer.class, 2500408, 16529978),
				newEntry("diocus", EntityDiocus.class, 5521679, 8994198),
				newEntry("distorter", EntityDistorter.class, 1579066, 2841448),
				newEntry("doubler", EntityDoubler.class, 2899233, 2634530),
				newEntry("dracyon", EntityDracyon.class, 402812, 3553336),
				newEntry("dust_strider", EntityDustStrider.class, 986902, 8662823),
				newEntry("dusteiva", EntityDusteiva.class, 3150604, 10037031),
				newEntry("duston", EntityDuston.class, 14027274, 857601),
				newEntry("dweller", EntityDweller.class, 5197117, 3225407),
				newEntry("dyrehorn", EntityDyrehorn.class, 12361818, 6317647),
				newEntry("echodar", EntityEchodar.class, 9388691, 11026825),
				newEntry("eeo", EntityEeo.class, 15921906, 5155053),
				newEntry("eilosapien", EntityEilosapien.class, 1810557, 13706193),
				newEntry("elite_skele_hopper", EntityEliteSkeleHopper.class, 7375232, 5462374),
				newEntry("elite_skele_pig", EntityEliteSkelePig.class, 6776173, 10196879),
				newEntry("elite_skeleman", EntityEliteSkeleman.class, 11446967, 5133390),
				newEntry("elusive", EntityElusive.class, 1121630, 1711428),
				newEgglessEntry("elusive_clone", EntityElusiveClone.class),
				newEntry("embrake", EntityEmbrake.class, 986632, 5967104),
				newEntry("emperor_beast", EntityEmperorBeast.class, 3540548, 7480842),
				newEntry("enforcer", EntityEnforcer.class, 1646128, 3949091),
				newEntry("everbeast", EntityEverbeast.class, 8874602, 8987427),
				newEntry("exohead", EntityExohead.class, 1841431, 6500657),
				newEntry("exoid", EntityExoid.class, 1184278, 10370108),
				newEntry("explodot", EntityExplodot.class, 1718972, 6452410),
				newEntry("eye_creature", EntityEyeCreature.class, 4486586, 4172457),
				newEntry("faceless_floater", EntityFacelessFloater.class, 5187949, 5784171),
				newEntry("faceless_runner", EntityFacelessRunner.class, 5322600, 8210052),
				newEntry("fake_pigman", EntityFakePigman.class, 10174865, 2911049),
				newEgglessEntry("fake_zorp", EntityFakeZorp.class),
				newEntry("fenix", EntityFenix.class, 5264212, 7370096),
				newEntry("fiend", EntityFiend.class, 4790796, 4662298),
				newEntry("fischer", EntityFischer.class, 3156005, 11349293),
				newEntry("fishix", EntityFishix.class, 3819793, 4086071),
				newEntry("flamewalker", EntityFlamewalker.class, 14354954, 14049890),
				newEntry("flash", EntityFlash.class, 2828311, 6315292),
				newEntry("flesh_eater", EntityFleshEater.class, 2827535, 9646899),
				newEntry("flowerface", EntityFlowerface.class, 4229424, 11025567),
				newEntry("flye", EntityFlye.class, 15363328, 16577827),
				newEntry("fungat", EntityFungat.class, 1820193, 7281338),
				newEntry("fungback", EntityFungback.class, 16578797, 4211836),
				newEntry("fungik", EntityFungik.class, 16578797, 4093269),
				newEntry("fungock", EntityFungock.class, 8552047, 7166001),
				newEntry("fungung", EntityFungung.class, 16578797, 2247333),
				newEntry("furlion", EntityFurlion.class, 15724013, 15557475),
				newEntry("gadgetoid", EntityGadgetoid.class, 7037752, 12855593),
				newEntry("ghastus", EntityGhastus.class, 4999750, 16380643),
				newEntry("ghost", EntityGhost.class, 15462366, 15134455),
				newEntry("ghostine_ancient", EntityGhostineAncient.class, 11719412, 10340338),
				newEntry("ghostly_bugeye", EntityGhostlyBugeye.class, 12572655, 9213871),
				newEntry("ghostly_charger", EntityGhostlyCharger.class, 12572655, 8556461),
				newEntry("ghostly_cyclops", EntityGhostlyCyclops.class, 12572655, 8227493),
				newEntry("ghostly_goblin", EntityGhostlyGoblin.class, 12572655, 9149111),
				newEntry("ghostly_night_reaper", EntityGhostlyNightReaper.class, 12572655, 7312324),
				newEntry("ghostly_sasquatch", EntityGhostlySasquatch.class, 12572655, 8757185),
				newEntry("giant_snail", EntityGiantSnail.class, 15589570, 15394116),
				newEntry("gingerbird", EntityGingerbird.class, 7818256, 2979202),
				newEntry("gingerbread_man", EntityGingerbreadMan.class, 7818256, 7291276),
				newEntry("goalby", EntityGoalby.class, 8950675, 6661027),
				newEntry("goblin", EntityGoblin.class, 6710824, 5060198),
				newEntry("goldorth", EntityGoldorth.class, 5657109, 8659233),
				newEntry("goldum", EntityGoldum.class, 9011712, 1711127),
				newEntry("goldus", EntityGoldus.class, 11373338, 1711127),
				newEntry("graw", EntityGraw.class, 16738816, 16764928),
				newEntry("green_automaton", EntityGreenAutomaton.class, 5005352, 6532937),
				newEntry("green_flower", EntityGreenFlower.class, 2742453, 4435278),
				newEntry("green_guardian", EntityGreenGuardian.class, 4148291, 2937123),
				newEntry("green_rune_templar", EntityRuneTemplarGreen.class, 6187108, 1645596),
				newEntry("green_runic_lifeform", EntityRunicLifeformGreen.class, 13485, 41051),
				newEntry("grillface", EntityGrillface.class, 2562160, 10820175),
				newEntry("grobbler", EntityGrobbler.class, 14374157, 2168079),
				newEntry("grocculate", EntityGrocculate.class, 1124377, 1845268),
				newEntry("grunt", EntityGrunt.class, 2638869, 4009744),
				newEntry("gyro", EntityGyro.class, 16713479, 2895148),
				newEntry("hag", EntityHag.class, 2835486, 4938051),
				newEntry("harkos", EntityHarkos.class, 5198157, 5312786),
				newEntry("headless_destroyer", EntityHeadlessDestroyer.class, 7341574, 8681335),
				newEntry("headless_hunter", EntityHeadlessHunter.class, 7168353, 1644568),
				newEntry("hellcat", EntityHellcat.class, 11690766, 2824195),
				newEntry("hellspot", EntityHellspot.class, 13832204, 3676183),
				newEntry("hiding_fungi", EntityHidingFungi.class, 7679, 13028863),
				newEntry("hill_charger", EntityHillCharger.class, 11362333, 8340088),
				newEntry("hive_king", EntityHiveKing.class, 13211587, 15340050),
				newEntry("hive_worker", EntityHiveWorker.class, 13211587, 3675965),
				newEntry("horndron", EntityHorndron.class, 3482377, 11377022),
				newEntry("horon", EntityHoron.class, 9865773, 12039325),
				newEntry("host", EntityHost.class, 4629316, 11321260),
				newEntry("hunch", EntityHunch.class, 7370943, 1710883),
				newEntry("hunter", EntityHunter.class, 3152742, 4007949),
				newEntry("hydrolisk", EntityHydrolisk.class, 2956372, 5315355),
				newEntry("hydrolon", EntityHydrolon.class, 16711896, 53503),
				newEntry("ice_giant", EntityIceGiant.class, 9149372, 7045549),
				newEntry("infernal", EntityInfernal.class, 7151133, 14848287),
				newEntry("inmate_x", EntityInmateX.class, 14165970, 15138567),
				newEntry("inmate_y", EntityInmateY.class, 792825, 10513678),
				newEntry("iosaur", EntityIosaur.class, 1071378, 3919422),
				newEntry("irkling", EntityIrkling.class, 5935451, 12175801),
				newEntry("jawe", EntityJawe.class, 6825253, 6838617),
				newEntry("jumbo", EntityJumbo.class, 9197854, 15723594),
				newEntry("kaiyu", EntityKaiyu.class, 13873991, 15259030),
				newEntry("kajaros", EntityKajaros.class, 3351588, 9189689),
				newEntry("keeler", EntityKeeler.class, 9271950, 10507941),
				newEntry("king_bambambam", EntityKingBamBamBam.class, 7673107, 11365161),
				newEntry("king_charger", EntityKingCharger.class, 12209209, 11181595),
				newEntry("king_creeper", EntityKingCreeper.class, 2448937, 8348682),
				newEntry("king_shroomus", EntityKingShroomus.class, 12368043, 10167758),
				newEntry("klobber", EntityKlobber.class, 3158044, 14211163),
				newEntry("koko", EntityKoko.class, 16189444, 15569811),
				newEntry("kranky", EntityKranky.class, 325397, 15569811),
				newEntry("kror", EntityKror.class, 8488834, 13620176),
				newEntry("leafy_giant", EntityLeafyGiant.class, 149780, 3153154),
				newEntry("lelyetian_caster", EntityLelyetianCaster.class, 12330277, 14413608),
				newEntry("lelyetian_warrior", EntityLelyetianWarrior.class, 12330277, 13489046),
				newEntry("lightwalker", EntityLightwalker.class, 7765779, 2895137),
				newEntry("linger", EntityLinger.class, 12325394, 4791324),
				newEntry("little_bam", EntityLittleBam.class, 6293512, 8345864),
				newEntry("living_fungi", EntityLivingFungi.class, 1705678, 15000557),
				newEntry("lollypopper", EntityLollypopper.class, 13387173, 14069706),
				newEntry("lost_soul", EntityLostSoul.class, 1578521, 11774647),
				newEntry("lunarcher", EntityLunarcher.class, 7932315, 13991919),
				newEntry("lurker", EntityLurker.class, 525578, 14354440),
				newEntry("luxocron", EntityLuxocron.class, 14871822, 6932197),
				newEntry("magical_creeper", EntityMagicalCreeper.class, 350480, 2765611),
				newEntry("magicke", EntityMagicke.class, 1765349, 12112335),
				newEntry("mechachron", EntityMechachron.class, 2369576, 15920978),
				newEntry("mechamaton", EntityMechamaton.class, 9340427, 131585),
				newEntry("mechbot", EntityMechbot.class, 13882126, 1644815),
				newEntry("mechyon", EntityMechyon.class, 3355435, 4013324),
				newEntry("merkyre", EntityMerkyre.class, 2500131, 1835779),
				newEntry("mermage", EntityMermage.class, 262786, 1756891),
				newEntry("mirage", EntityMirage.class, 13552553, 1447439),
				newEntry("miskel", EntityMiskel.class, 10197910, 7021604),
				newEntry("modulo", EntityModulo.class, 5282713, 9060540),
				newEntry("mother_void_walker", EntityMotherVoidWalker.class, 591882, 3946559),
				newEntry("muckopede", EntityMuckopede.class, 9338466, 6709851),
				newEntry("muncher", EntityMuncher.class, 660405, 10430127),
				newEntry("mushroom_spider", EntityMushroomSpider.class, 1739049, 12827332),
				newEntry("natura", EntityNatura.class, 288273, 946815),
				newEntry("neptuno", EntityNeptuno.class, 533611, 1582407),
				newEntry("nethengeic_beast", EntityNethengeicBeast.class, 3343364, 13834007),
				newEntry("nethengeic_wither", EntityNethengeicWither.class, 5506309, 11385897),
				newEntry("night_reaper", EntityNightReaper.class, 1117453, 11975851),
				newEntry("night_watcher", EntityNightWatcher.class, 723977, 592898),
				newEntry("nightfly", EntityNightfly.class, 1315859, 12566398),
				newEntry("nightmare_spider", EntityNightmareSpider.class, 1574189, 5180318),
				newEntry("nightwing", EntityNightwing.class, 4012325, 6298909),
				newEntry("nipper", EntityNipper.class, 12566447, 15395527),
				newEntry("nospike", EntityNospike.class, 11509027, 3683623),
				newEntry("occulent", EntityOcculent.class, 4802884, 3671558),
				newEntry("okazor", EntityOkazor.class, 5195591, 4982535),
				newEntry("omnilight", EntityOmnilight.class, 14540080, 12566365),
				newEntry("opteryx", EntityOpteryx.class, 11381054, 2779311),
				newEntry("orange_flower", EntityOrangeFlower.class, 821547, 12878353),
				newEntry("orbiter", EntityOrbiter.class, 973549, 16776960),
				newEntry("paladin", EntityPaladin.class, 15921889, 2019053),
				newEntry("parasect", EntityParasect.class, 5011275, 2481439),
				newEntry("paravite", EntityParavite.class, 16535301, 16552455),
				newEntry("penumbra", EntityPenumbra.class, 5317906, 11473937),
				newEntry("phantom", EntityPhantom.class, 4786795, 6048870),
				newEntry("pigotron", EntityPigotron.class, 12850616, 1195290),
				newEntry("pincher", EntityPincher.class, 467791, 1296854),
				newEntry("pod_plant", EntityPodPlant.class, 1307185, 13882270),
				newEntry("polar_ursa", EntityPolarUrsa.class, 14346986, 10998483),
				newEntry("polytom", EntityPolytom.class, 7491330, 1972754),
				newEntry("primitive_carrotop", EntityPrimitiveCarrotop.class, 11447170, 2455829),
				newEntry("proshield", EntityProshield.class, 14146468, 10660420),
				newEntry("purple_automaton", EntityPurpleAutomaton.class, 4985215, 11630818),
				newEntry("purple_flower", EntityPurpleFlower.class, 2218771, 6757290),
				newEntry("quickpocket", EntityQuickpocket.class, 1580567, 4130567),
				newEntry("rainicorn", EntityRainicorn.class, 15066083, 7836541),
				newEntry("rammerhead", EntityRammerhead.class, 4473924, 15921135),
				newEntry("ramradon", EntityRamradon.class, 8365446, 8028027),
				newEntry("rawbone", EntityRawbone.class, 12561595, 14731739),
				newEntry("raxxan", EntityRaxxan.class, 5195597, 7023706),
				newEntry("reaper_twins", EntityReaperTwins.class, 8024365, 1447440),
				newEntry("reaver", EntityReaver.class, 1443591, 10827318),
				newEntry("red_automaton", EntityRedAutomaton.class, 11702871, 14864044),
				newEntry("red_guardian", EntityRedGuardian.class, 7829107, 9646642),
				newEntry("red_rune_templar", EntityRuneTemplarRed.class, 5328462, 12654873),
				newEntry("red_runic_lifeform", EntityRunicLifeformRed.class, 730077, 1224933),
				newEntry("refluct", EntityRefluct.class, 1177865, 11850163),
				newEntry("rock_crawler", EntityRockCrawler.class, 6318176, 14937571),
				newEntry("rock_critter", EntityRockCritter.class, 6318176, 2764074),
				newEntry("rock_rider", EntityRockRider.class, 6699285, 2765578),
				newEntry("rockbiter", EntityRockbiter.class, 7434858, 13559069),
				newEntry("roloscope", EntityRoloscope.class, 4478340, 12041417),
				newEntry("runic_golem", EntityRunicGolem.class, 4655720, 2712772),
				newEntry("runic_guardian", EntityRunicGuardian.class, 336465, 7305092),
				newEntry("runicorn", EntityRunicorn.class, 5411475, 4804431),
				newEntry("runicorn_rider", EntityRunicornRider.class, 5411475, 539296),
				newEntry("sabretooth", EntitySabretooth.class, 13403404, 1446670),
				newEntry("sand_giant", EntitySandGiant.class, 12695722, 16246218),
				newEntry("sand_golem", EntitySandGolem.class, 2827290, 8352867),
				newEntry("sasquatch", EntitySasquatch.class, 13546898, 5656129),
				newEntry("scrubby", EntityScrubby.class, 14364720, 12027161),
				newEntry("sea_charger", EntitySeaCharger.class, 1224378, 3305384),
				newEntry("sea_skeleton", EntitySeaSkeleton.class, 1612512, 552828),
				newEntry("sea_spider", EntitySeaSpider.class, 1115030, 4272621),
				newEntry("sea_troll", EntitySeaTroll.class, 1357024, 14291933),
				newEntry("sea_viper", EntitySeaViper.class, 1357029, 7710903),
				newEntry("seeker", EntitySeeker.class, 14738782, 15461809),
				newEntry("shade", EntityShade.class, 4333857, 13549760),
				newEntry("shadow", EntityShadow.class, 4333857, 13549760),
				newEntry("shadowlord", EntityShadowlord.class, 460551, 10722203),
				newEntry("shavo", EntityShavo.class, 15256780, 2959400),
				newEntry("shifter", EntityShifter.class, 1446161, 656902),
				newEntry("shyre_knight", EntityShyreKnight.class, 967917, 13232654),
				newEntry("shyre_troll", EntityShyreTroll.class, 15263760, 408936),
				newEntry("silencer", EntitySilencer.class, 4667168, 13880772),
				newEntry("silverfoot", EntitySilverfoot.class, 2301720, 12235169),
				newEntry("skele_elder", EntitySkeleElder.class, 14407632, 12368309),
				newEntry("skele_hopper", EntitySkeleHopper.class, 14407632, 11709604),
				newEntry("skele_pig", EntitySkelePig.class, 14407632, 12368309),
				newEntry("skeledon", EntitySkeledon.class, 14403243, 16249322),
				newEntry("skelekyte", EntitySkelekyte.class, 14403243, 13881807),
				newEntry("skeleman", EntitySkeleman.class, 14012143, 4605004),
				newEntry("skeletal_cowman", EntitySkeletalCowman.class, 2958092, 14077888),
				newEntry("skeletron", EntitySkeletron.class, 16052195, 4802370),
				newEntry("skellox", EntitySkellox.class, 2958092, 11183773),
				newEntry("skipper", EntitySkipper.class, 9742445, 13168000),
				newEntry("skolle", EntitySkolle.class, 3680010, 13873788),
				newEntry("skull_creature", EntitySkullCreature.class, 2496514, 3351359),
				newEntry("slimer", EntitySlimer.class, 3683898, 978238),
				newEntry("smash", EntitySmash.class, 3154949, 3493661),
				newEntry("snappy", EntitySnappy.class, 1573411, 590407),
				newEntry("snow_charger", EntitySnowCharger.class, 5604755, 9951981),
				newEntry("soulscorne", EntitySoulscorne.class, 14809348, 977903),
				newEntry("soulvyre", EntitySoulvyre.class, 14743310, 1369563),
				newEntry("spectral_wizard", EntitySpectralWizard.class, 15267050, 52722),
				newEntry("sphinx", EntitySphinx.class, 15134447, 13422286),
				newEntry("spinoledon", EntitySpinoledon.class, 7682874, 12016473),
				newEntry("spinux", EntitySpinux.class, 3239057, 6592954),
				newEntry("spirit_guardian", EntitySpiritGuardian.class, 15265007, 13881253),
				newEntry("spirit_protector", EntitySpiritProtector.class, 15265007, 14867354),
				newEntry("squasher", EntitySquasher.class, 5732923, 9476961),
				newEntry("squiggler", EntitySquiggler.class, 7474700, 13533053),
				newEntry("stalker", EntityStalker.class, 984582, 1841947),
				newEntry("stalker_prime", EntityStalkerPrime.class, 131329, 2499877),
				newEntry("sticky", EntitySticky.class, 16777215, 14084119),
				newEntry("stimulo", EntityStimulo.class, 653303, 14808864),
				newEntry("stimulosus", EntityStimulosus.class, 653303, 13953058),
				newEntry("stinger", EntityStinger.class, 2825223, 3526170),
				newEntry("stitches", EntityStitches.class, 14498831, 10187624),
				newEntry("stone_giant", EntityStoneGiant.class, 10181528, 10185496),
				newEntry("strong_skele_hopper", EntityStrongSkeleHopper.class, 15724008, 13552580),
				newEntry("strong_skele_pig", EntityStrongSkelePig.class, 15724008, 9671312),
				newEntry("strong_skeleman", EntityStrongSkeleman.class, 15724008, 16248798),
				newEntry("sugarface", EntitySugarface.class, 11963839, 15787762),
				newEntry("sunny", EntitySunny.class, 653073, 13750551),
				newEntry("surveyor", EntitySurveyor.class, 1366801, 1448214),
				newEntry("swamp_charger", EntitySwampCharger.class, 1785115, 3626551),
				newEntry("sysker", EntitySysker.class, 14941189, 653789),
				newEntry("terradon", EntityTerradon.class, 6704409, 12235168),
				newEntry("terrestrial", EntityTerrestrial.class, 5636940, 15666901),
				newEntry("tharafly", EntityTharafly.class, 4470043, 13748410),
				newEntry("tipsy", EntityTipsy.class, 1107429, 13748410),
				newEntry("tortione", EntityTortione.class, 4415509, 8691731),
				newEntry("toxxulous", EntityToxxulous.class, 3165970, 2041878),
				newEntry("tracker", EntityTracker.class, 15550219, 15706387),
				newEgglessEntry("tree_spirit", EntityTreeSpirit.class),
				newEntry("trickster", EntityTrickster.class, 2237217, 7501167),
				newEgglessEntry("trickster_clone", EntityTricksterClone.class),
				newEntry("triclops", EntityTriclops.class, 5311500, 3353644),
				newEntry("tyrosaur", EntityTyrosaur.class, 5530376, 13356729),
				newEntry("undead_troll", EntityUndeadTroll.class, 2229309, 14735336),
				newEntry("urioh", EntityUrioh.class, 2959920, 13946587),
				newEntry("urka", EntityUrka.class, 1644569, 15987444),
				newEntry("urv", EntityUrv.class, 2960171, 8327184),
				newEntry("valkyrie", EntityValkyrie.class, 3154454, 10502611),
				newEntry("vertebron", EntityVertebron.class, 4485706, 979500),
				newEntry("vine_wizard", EntityVineWizard.class, 801297, 12525086),
				newEntry("vinocorne", EntityVinocorne.class, 4944456, 10838423),
				newEntry("visage", EntityVisage.class, 4143934, 14709955),
				newEntry("visualent", EntityVisualent.class, 5194571, 8590745),
				newEntry("visular", EntityVisular.class, 15994093, 12655548),
				newEntry("visulon", EntityVisulon.class, 15863530, 15351009),
				newEntry("void_charger", EntityVoidCharger.class, 2237992, 5573275),
				newEntry("void_walker", EntityVoidWalker.class, 789260, 3157296),
				newEntry("volar", EntityVolar.class, 1477341, 4096951),
				newEntry("voliant", EntityVoliant.class, 811936, 4096951),
				newEntry("voltron", EntityVoltron.class, 10725391, 2302750),
				newEntry("voxxulon", EntityVoxxulon.class, 2568993, 5954330),
				newEntry("walker", EntityWalker.class, 14839305, 6251357),
				newEntry("warclops", EntityWarclops.class, 1977608, 3290412),
				newEntry("web_reaper", EntityWebReaper.class, 2494474, 1446932),
				newEntry("wickett", EntityWickett.class, 2170398, 3815480),
				newEntry("winged_creeper", EntityWingedCreeper.class, 484870, 6585443),
				newEntry("wither_wizard", EntityWitherWizard.class, 659209, 4145726),
				newEntry("wood_giant", EntityWoodGiant.class, 2957061, 731907),
				newEntry("xxeus", EntityXxeus.class, 9206543, 1432509),
				newEntry("yellow_automaton", EntityYellowAutomaton.class, 8353035, 14734174),
				newEntry("yellow_flower", EntityYellowFlower.class, 1168939, 14283790),
				newEntry("yellow_guardian", EntityYellowGuardian.class, 7105891, 14347529),
				newEntry("yellow_rune_templar", EntityRuneTemplarYellow.class, 2302754, 986892),
				newEntry("yellow_runic_lifeform", EntityRunicLifeformYellow.class, 1083877, 14152204),
				newEntry("yeti", EntityYeti.class, 15987944, 13816774),
				newEntry("zarg", EntityZarg.class, 1803734, 13230578),
				newEntry("zhinx", EntityZhinx.class, 11056831, 13029730),
				newEntry("zorp", EntityZorp.class, 1093861, 14347506)
		};
	}

	private static EntityEntry[] buildMinionEntries() {
		return new EntityEntry[]{
				newEntry("alluricorn", EntityAlluricorn.class, 14737517, 8684555),
				newEntry("blissard", EntityBlissard.class, 6662606, 15462900),
				newEntry("compeer", EntityCompeer.class, 1250582, 13424605),
				newEntry("construct_of_servility", EntityConstructOfServility.class, 278281, 2371621),
				newEntry("corby", EntityCorby.class, 630253, 14486998),
				newEntry("craggy", EntityCraggy.class, 15013009, 15563787),
				newEntry("draggy", EntityDraggy.class, 11605487, 14663917),
				newEntry("ender_carrier", EntityEnderCarrier.class, 1447445, 8393957),
				newEntry("friendly_creeper", EntityFriendlyCreeper.class, 894731, 0),
				newEntry("gnawer", EntityGnawer.class, 3160086, 3882796),
				newEntry("goofer", EntityGoofer.class, 15996691, 15053232),
				newEntry("healing_golem", EntityHealingGolem.class, 5328976, 15665931),
				newEntry("hellquin", EntityHellquin.class, 1315603, 15337737),
				newEntry("hive_soldier", EntityHiveSoldier.class, 12350388, 15259109),
				newEntry("horntail", EntityHorntail.class, 15063708, 15720333),
				newEntry("mecha_cyclops", EntityMechaCyclops.class, 11710995, 2500128),
				newEntry("mecha_skellox", EntityMechaSkellox.class, 2500128, 11710995),
				newEntry("orbling", EntityOrbling.class, 16189423, 15582443),
				newEntry("penguin", EntityPenguin.class, 857102, 16248822),
				newEntry("plateosaur", EntityPlateosaur.class, 5077436, 6649230),
				newEntry("rammerhorn", EntityRammerhorn.class, 12281066, 4143128),
				newEntry("rosid", EntityRosid.class, 14161122, 519461),
				newEntry("shaddy", EntityShaddy.class, 5987931, 14610398),
				newEntry("shadow_stalker", EntityShadowStalker.class, 2171681, 14807777),
				newEntry("spikeback", EntitySpikeback.class, 2958082, 13881282),
				newEntry("spraggy", EntitySpraggy.class, 5817443, 12509378),
				newEntry("waggy", EntityWaggy.class, 6396660, 340855)
		};
	}

	private static EntityEntry[] buildEntityEntries() {
		return new EntityEntry[]{
			newEntry("abyssal_lottoman", EntityAbyssalLottoman.class, 15994121, 15917022),
			newEntry("ambient_pixon", EntityAmbientPixon.class, 12235956, 12039354),
			newEntry("anima_master", EntityAnimaMaster.class, 639471, 8445943),
			newEntry("assassin", EntityAssassin.class, 5638000, 5393748),
			newEntry("augury_master", EntityAuguryMaster.class, 13882169, 4470027),
			newEntry("baron_lottoman", EntityBaronLottoman.class, 15394263, 12029839),
			newEntry("blooming_pixon", EntityBloomingPixon.class, 10292975, 13540842),
			newEntry("boreic_lottoman", EntityBoreicLottoman.class, 1103858, 14017509),
			newEntry("butchery_master", EntityButcheryMaster.class, 15667987, 7014151),
			newEntry("candied_lottoman", EntityCandiedLottoman.class, 11538661, 15062506),
			newEntry("celevian_lottoman", EntityCelevianLottoman.class, 14807784, 10671799),
			newEntry("coratee", EntityCoratee.class, 40623, 7236),
			newEntry("corrupted_traveller", EntityCorruptedTraveller.class, 329538, 340761),
			newEntry("creation_master", EntityCreationMaster.class, 2360660, 5724933),
			newEntry("creep_banker", EntityCreepBanker.class, 407559, 5395971),
			newEntry("creep_cow", EntityCreepCow.class, 408837, 15135462),
			newEntry("creeponia_lottoman", EntityCreeponiaLottoman.class, 668938, 855821),
			newEntry("crystal_lottoman", EntityCrystalLottoman.class, 13097927, 1814501),
			newEntry("crystal_trader", EntityCrystalTrader.class, 14150368, 1369392),
			newEntry("dungeon_keeper", EntityDungeonKeeper.class, 3157507, 2825476),
			newEntry("dustopian_lottoman", EntityDustopianLottoman.class, 15065045, 4012857),
			newEntry("elkanyne", EntityElkanyne.class, 4471327, 7695709),
			newEntry("expedition_master", EntityExpeditionMaster.class, 1164512, 15134962),
			newEntry("explosives_expert", EntityExplosivesExpert.class, 15864595, 16250871),
			newEntry("extraction_master", EntityExtractionMaster.class, 16031758, 2499876),
			newEntry("floro_lottoman", EntityFloroLottoman.class, 16250098, 862212),
			newEntry("foraging_master", EntityForagingMaster.class, 16053260, 1842202),
			newEntry("glaring_pixon", EntityGlaringPixon.class, 2628099, 2827805),
			newEntry("gleaming_pixon", EntityGleamingPixon.class, 1549800, 2830387),
			newEntry("glistening_pixon", EntityGlisteningPixon.class, 977425, 2370852),
			newEntry("glowing_pixon", EntityGlowingPixon.class, 16190476, 2170141),
			newEntry("golden_lottoman", EntityGoldenLottoman.class, 16051179, 986638),
			newEntry("gorb_arms_dealer", EntityGorbArmsDealer.class, 4990482, 14195479),
			newEntry("gorb_citizen", EntityGorbCitizen.class, 3233315, 4744746),
			newEntry("gorb_engineer", EntityGorbEngineer.class, 10032659, 14389134),
			newEntry("halycon", EntityHalycon.class, 2683401, 734469),
			newEntry("hauling_master", EntityHaulingMaster.class, 979188, 8346895),
			newEntry("haunted_lottoman", EntityHauntedLottoman.class, 6430178, 16052983),
			newEntry("hunter_master", EntityHunterMaster.class, 15864334, 848943),
			newEntry("infusion_master", EntityInfusionMaster.class, 782319, 9442287),
			newEntry("innervation_master", EntityInnervationMaster.class, 15537429, 5523514),
			newEntry("lelyetian_banker", EntityLelyetianBanker.class, 14705940, 2955779),
			newEntry("lelyetian_lottoman", EntityLelyetianLottoman.class, 16052459, 10509836),
			newEntry("lelyetian_trader", EntityLelyetianTrader.class, 14705940, 2955779),
			newEntry("logging_master", EntityLoggingMaster.class, 2879253, 7364669),
			newEntry("lottoman", EntityLottoman.class, 15723491, 789515),
			newEntry("lunar_lottoman", EntityLunarLottoman.class, 11034029, 12169914),
			newEntry("metalloid", EntityMetalloid.class, 3677189, 7555455),
			newEntry("mystic_lottoman", EntityMysticLottoman.class, 15722738, 1238360),
			newEntry("naturalist", EntityNaturalist.class, 4927582, 10193420),
			newEntry("peppermint_snail", EntityPeppermintSnail.class, 14162711, 14849941),
			newEntry("portal_master", EntityPortalMaster.class, 8325930, 460294),
			newEntry("precasian_lottoman", EntityPrecasianLottoman.class, 1330186, 15528682),
			newEntry("primordial_banker", EntityPrimordialBanker.class, 14685204, 1315346),
			newEntry("primordial_guide", EntityPrimordialGuide.class, 7037281, 2960171),
			newEntry("primordial_merchant", EntityPrimordialMerchant.class, 909869, 1250835),
			newEntry("primordial_spellbinder", EntityPrimordialSpellbinder.class, 1059237, 8097765),
			newEntry("primordial_wizard", EntityPrimordialWizard.class, 4000119, 13023446),
			newEntry("professor", EntityProfessor.class, 13493531, 1644822),
			newEntry("radiant_pixon", EntityRadiantPixon.class, 2689714, 1641556),
			newEntry("realmshifter", EntityRealmshifter.class, 2953404, 1776158),
			newEntry("rocky_lottoman", EntityRockyLottoman.class, 15131631, 2763053),
			newEntry("runation_master", EntityRunationMaster.class, 1108561, 5195834),
			newEntry("runic_lottoman", EntityRunicLottoman.class, 1007835, 14804717),
			newEntry("shining_pixon", EntityShiningPixon.class, 13868556, 9341827),
			newEntry("shyre_archer", EntityShyreArcher.class, 14393873, 15397397),
			newEntry("shyre_banker", EntityShyreBanker.class, 15369239, 1101037),
			newEntry("shyrelands_lottoman", EntityShyrelandsLottoman.class, 14871822, 15395550),
			newEntry("spearmint_snail", EntitySpearmintSnail.class, 521247, 14938853),
			newEntry("store_keeper", EntityStoreKeeper.class, 2824965, 10530735),
			newEntry("token_collector", EntityTokenCollector.class, 6840842, 13878796),
			newEntry("toxic_lottoman", EntityToxicLottoman.class, 479494, 9081226),
			newEntry("toy_merchant", EntityToyMerchant.class, 7821066, 14068552),
			newEntry("troll_trader", EntityTrollTrader.class, 779757, 415293),
			newEntry("trotter", EntityTrotter.class, 16213252, 16051204),
			newEntry("twinkling_lottoman", EntityTwinklingLottoman.class, 839991, 13360079),
			newEntry("undead_herald", EntityUndeadHerald.class, 592394, 1302514),
			newEntry("withering_lottoman", EntityWitheringLottoman.class, 15594735, 2698026),
			newEntry("zal_banker", EntityZalBanker.class, 11975695, 4144954),
			newEntry("zal_child", EntityZalChild.class, 15395546, 2631715),
			newEntry("zal_citizen", EntityZalCitizen.class, 15921894, 3487025),
			newEntry("zal_grocer", EntityZalGrocer.class, 2956551, 6315353),
			newEntry("zal_herbalist", EntityZalHerbalist.class, 997382, 6315353),
			newEntry("zal_spellbinder", EntityZalSpellbinder.class, 3475048, 6315353),
			newEntry("zal_vendor", EntityZalVendor.class, 4144703, 9933723)
		};
	}

	private static EntityEntry[] buildMiscEntityEntries() {
		return new EntityEntry[]{
				newMiscEntityEntry("agility_tablet", EntityAgilityTablet.class),
				newMiscEntityEntry("anima_stone", EntityAnimaStone.class),
				newMiscEntityEntry("awareness_tablet", EntityAwarenessTablet.class),
				newMiscEntityEntry("bane_big", EntityBaneBig.class),
				newMiscEntityEntry("bane_clone", EntityBaneClone.class),
				newMiscEntityEntry("baron_bomb", EntityBaronBomb.class),
				newMiscEntityEntry("bloodlust", EntityBloodlust.class),
				newMiscEntityEntry("boss_item", EntityBossItem.class),
				newMiscEntityEntry("breeding_tablet", EntityBreedingTablet.class),
				newMiscEntityEntry("cleansing_tablet", EntityCleansingTablet.class),
				newMiscEntityEntry("distortion_tablet", EntityDistortionTablet.class),
				newMiscEntityEntry("energy_tablet", EntityEnergyTablet.class),
				newMiscEntityEntry("fake_tnt", EntityFakeTnt.class),
				newMiscEntityEntry("gravity_tablet", EntityGravityTablet.class),
				newMiscEntityEntry("gyrocopter", EntityGyrocopter.class),
				newMiscEntityEntry("heart_stone", EntityHeartStone.class),
				newMiscEntityEntry("lotto_totem", EntityLottoTotem.class),
				newMiscEntityEntry("occult_block", EntityOccultBlock.class),
				newMiscEntityEntry("oxygen_tablet", EntityOxygenTablet.class),
				newMiscEntityEntry("pressure_tablet", EntityPressureTablet.class),
				newMiscEntityEntry("proficiency_tablet", EntityProficiencyTablet.class),
				newMiscEntityEntry("resistance_tablet", EntityResistanceTablet.class),
				newMiscEntityEntry("sanctity_tablet", EntitySanctityTablet.class),
				newMiscEntityEntry("satiation_tablet", EntitySatiationTablet.class),
				newMiscEntityEntry("sight_tablet", EntitySightTablet.class),
				newMiscEntityEntry("strength_tablet", EntityStrengthTablet.class),
				newMiscEntityEntry("untiring_tablet", EntityUntiringTablet.class),
				newMiscEntityEntry("vitality_tablet", EntityVitalityTablet.class)
		};
	}

	private static EntityEntry[] buildProjectileEntries() {
		return new EntityEntry[]{
				newProjectileEntry("anemia_bomb", EntityAnemiaBomb.class),
				newProjectileEntry("arcworm_shot", EntityArcwormShot.class),
				newProjectileEntry("aquaball", EntityAquaball.class),
				newProjectileEntry("aquatic_shot", EntityAquaticShot.class),
				newProjectileEntry("atomizer_bounce", EntityAtomizerBounce.class),
				newProjectileEntry("atomizer_shot", EntityAtomizerShot.class),
				newProjectileEntry("baron_shot", EntityBaronShot.class),
				newProjectileEntry("baroness_shot", EntityBaronessShot.class),
				newProjectileEntry("beamer_shot", EntityBeamerShot.class),
				newProjectileEntry("blood_drainer", EntityBloodDrainer.class),
				newProjectileEntry("bloodball", EntityBloodball.class),
				newProjectileEntry("blue_guardian_shot", EntityBlueGuardianShot.class),
				newProjectileEntry("bone_pellet", EntityBonePellet.class),
				newProjectileEntry("bozo_ball", EntityBozoBall.class),
				newProjectileEntry("bubble_shot", EntityBubbleShot.class),
				newProjectileEntry("bullet_bone", EntityBoneBullet.class),
				newProjectileEntry("bullet_shot", EntityBulletShot.class),
				newProjectileEntry("cannonball", EntityCannonball.class),
				newProjectileEntry("cannonball_balloon_bomb", EntityBalloonBomb.class),
				newProjectileEntry("cannonball_blue_heavy", EntityHeavyBlueCannonball.class),
				newProjectileEntry("cannonball_carrot", EntityCarrotBall.class),
				newProjectileEntry("cannonball_clown_ball", EntityClownBall.class),
				newProjectileEntry("cannonball_energy_shot", EntityEnergyShot.class),
				newProjectileEntry("cannonball_erebon_stickler", EntityErebonSticklerShot.class),
				newProjectileEntry("cannonball_erebon_stickler_stuck", EntityErebonSticklerStuck.class),
				newProjectileEntry("cannonball_flower_ball", EntityFlowerBall.class),
				newProjectileEntry("cannonball_fungal_ball", EntityFungalBall.class),
				newProjectileEntry("cannonball_ghoul_ball", EntityGhoulBall.class),
				newProjectileEntry("cannonball_golden", EntityGoldenCannonball.class),
				newProjectileEntry("cannonball_green_ball_mini", EntityMiniGreenBall.class),
				newProjectileEntry("cannonball_green_ball_super", EntitySuperGreenBall.class),
				newProjectileEntry("cannonball_green_ball_ultra", EntityUltraGreenBall.class),
				newProjectileEntry("cannonball_green_ball_giga", EntityGigaGreenBall.class),
				newProjectileEntry("cannonball_heavy", EntityHeavyCannonball.class),
				newProjectileEntry("cannonball_heavy_bone", EntityHeavyBoneCannonball.class),
				newProjectileEntry("cannonball_heavy_red", EntityHeavyCannonballRed.class),
				newProjectileEntry("cannonball_heavy_shadowball", EntityHeavyShadowball.class),
				newProjectileEntry("cannonball_heavy_wither", EntityHeavyWitherBall.class),
				newProjectileEntry("cannonball_hive_ball", EntityHiveBall.class),
				newProjectileEntry("cannonball_luxon_stickler", EntityLuxonSticklerShot.class),
				newProjectileEntry("cannonball_luxon_stickler_stuck", EntityLuxonSticklerStuck.class),
				newProjectileEntry("cannonball_moon_shot", EntityMoonShot.class),
				newProjectileEntry("cannonball_orange", EntityOrangeCannonball.class),
				newProjectileEntry("cannonball_pluton_stickler", EntityPlutonSticklerShot.class),
				newProjectileEntry("cannonball_pluton_stickler_stuck", EntityPlutonSticklerStuck.class),
				newProjectileEntry("cannonball_selyan_stickler", EntitySelyanSticklerShot.class),
				newProjectileEntry("cannonball_selyan_stickler_stuck", EntitySelyanSticklerStuck.class),
				newProjectileEntry("cannonball_smile_blaster", EntitySmileBlaster.class),
				newProjectileEntry("cannonball_smiley", EntitySmileyCannonball.class),
				newProjectileEntry("cannonball_sticky_bomb_cool", EntityStickyCoolBomb.class),
				newProjectileEntry("cannonball_sticky_bomb_red", EntityStickyRedBomb.class),
				newProjectileEntry("cannonball_vox_cannon", EntityVoxCannon.class),
				newProjectileEntry("cannonball_water_balloon_bomb", EntityWaterBalloonBomb.class),
				newProjectileEntry("celestial_fall", EntityCelestialFall.class),
				newProjectileEntry("chakram", EntityChakram.class),
				newProjectileEntry("cherry_shot", EntityCherryShot.class),
				newProjectileEntry("chilli_shot", EntityChilliShot.class),
				newProjectileEntry("clown_shot", EntityClownShot.class),
				newProjectileEntry("confetti_cluster", EntityConfettiCluster.class),
				newProjectileEntry("confetti_shot", EntityConfettiShot.class),
				newProjectileEntry("construct_shot", EntityConstructShot.class),
				newProjectileEntry("corallus_shot", EntityCorallusShot.class),
				newProjectileEntry("cotton_candor_shot", EntityCottonCandorShot.class),
				newProjectileEntry("craexxeus_nuke", EntityCraexxeusNuke.class),
				newProjectileEntry("craexxeus_shot", EntityCraexxeusShot.class),
				newProjectileEntry("creep_bomb", EntityCreepBomb.class),
				newProjectileEntry("creep_tube", EntityCreepTube.class),
				newProjectileEntry("creeper_shot", EntityCreeperShot.class),
				newProjectileEntry("cyan_shot", EntityCyanShot.class),
				newProjectileEntry("death_ray", EntityDeathRay.class),
				newProjectileEntry("destroyer_shot", EntityDestroyerShot.class),
				newProjectileEntry("destruction_shot", EntityDestructionShot.class),
				newProjectileEntry("discharge_shot", EntityDischargeShot.class),
				newProjectileEntry("discharge_slug", EntityDischargeSlug.class),
				newProjectileEntry("doom_shot", EntityDoomShot.class),
				newProjectileEntry("eradicator_shot", EntityEradicatorShot.class),
				newProjectileEntry("firefly_shot", EntityFireflyShot.class),
				newProjectileEntry("firestorm_fall", EntityFirestormFall.class),
				newProjectileEntry("flower_shot", EntityFlowerShot.class),
				newProjectileEntry("fragment_shot", EntityFragmentShot.class),
				newProjectileEntry("ghoul_shot", EntityGhoulShot.class),
				newProjectileEntry("gold_shot", EntityGoldShot.class),
				newProjectileEntry("goo_ball", EntityGooBall.class),
				newProjectileEntry("graw_shot", EntityGrawShot.class),
				newProjectileEntry("green_guardian_shot", EntityGreenGuardianShot.class),
				newProjectileEntry("grenade", EntityGrenade.class),
				newProjectileEntry("grenade_floro_rpg", EntityFloroRPG.class),
				newProjectileEntry("grenade_multiplying", EntityMultiplyingGrenade.class),
				newProjectileEntry("grenade_rpg", EntityRPG.class),
				newProjectileEntry("hag_shot", EntityHagShot.class),
				newProjectileEntry("haunter_shot", EntityHaunterShot.class),
				newProjectileEntry("heavy_grenade", EntityHeavyGrenade.class),
				newProjectileEntry("heavy_runic_guardian_shot", EntityRunicGuardianShotHeavy.class),
				newProjectileEntry("heavy_shower_shot", EntityHeavyShowerShot.class),
				newProjectileEntry("heavy_tri_discharge_shot", EntityHeavyTriDischargeShot.class),
				newProjectileEntry("hell_bubble", EntityHellBubbleShot.class),
				newProjectileEntry("hellfire", EntityHellfire.class),
				newProjectileEntry("hellfire_tail", EntityHellfireProjectile.class),
				newProjectileEntry("holly_arrow", EntityHollyArrow.class),
				newProjectileEntry("holly_arrow_shot", EntityHollyArrowShot.class),
				newProjectileEntry("hot_shot", EntityHotShot.class),
				newProjectileEntry("ice_shot", EntityIceShot.class),
				newProjectileEntry("illusion_shot", EntityIllusionShot.class),
				newProjectileEntry("ion_shot", EntityIonShot.class),
				newProjectileEntry("iro_miner_shot", EntityIroMinerShot.class),
				newProjectileEntry("laser_shot", EntityLaserShot.class),
				newProjectileEntry("lelyetian_shot", EntityLelyetianShot.class),
				newProjectileEntry("light_blaster_shot", EntityLightBlasterShot.class),
				newProjectileEntry("light_iron_shot", EntityLightIronShot.class),
				newProjectileEntry("light_runic_guardian_shot", EntityRunicGuardianShotLight.class),
				newProjectileEntry("light_spark", EntityLightSpark.class),
				newProjectileEntry("limonite_bullet", EntityLimoniteBullet.class),
				newProjectileEntry("limonite_bullet_blue", EntityBlueBullet.class),
				newProjectileEntry("limonite_bullet_fire", EntityFireBullet.class),
				newProjectileEntry("limonite_bullet_green", EntityGreenBullet.class),
				newProjectileEntry("limonite_bullet_red", EntityRedBullet.class),
				newProjectileEntry("limonite_bullet_red_heavy", EntityHeavyRedBullet.class),
				newProjectileEntry("limonite_bullet_shroom", EntityShroomBullet.class),
				newProjectileEntry("limonite_bullet_sunset", EntitySunsetBullet.class),
				newProjectileEntry("limonite_bullet_toxic", EntityToxicBullet.class),
				newProjectileEntry("limonite_bullet_yellow", EntityYellowBullet.class),
				newProjectileEntry("luna_shot", EntityLunaShot.class),
				newProjectileEntry("lunar_fall", EntityLunarFall.class),
				newProjectileEntry("lunar_holly_arrow_shot", EntityLunarHollyArrowShot.class),
				newProjectileEntry("lyonic_shot", EntityLyonicShot.class),
				newProjectileEntry("magic_ball", EntityMagicBall.class),
				newProjectileEntry("magicke_shot", EntityMagickeShot.class),
				newProjectileEntry("mech_fall", EntityMechFall.class),
				newProjectileEntry("mech_shot", EntityMechShot.class),
				newProjectileEntry("metal_slug", EntityMetalSlug.class),
				newProjectileEntry("meteor_fall", EntityMeteorFall.class),
				newProjectileEntry("mind_blaster_shot", EntityMindBlasterShot.class),
				newProjectileEntry("modulo_shot", EntityModuloShot.class),
				newProjectileEntry("moon_destroyer_shot", EntityMoonDestroyerShot.class),
				newProjectileEntry("moon_maker", EntityMoonMaker.class),
				newProjectileEntry("moon_shiner_shot", EntityMoonShiner.class),
				newProjectileEntry("moonlight_fall", EntityMoonlightFall.class),
				newProjectileEntry("nethengeic_wither_shot", EntityNethengeicWitherShot.class),
				newProjectileEntry("nightmare_fall", EntityNightmareFall.class),
				newProjectileEntry("noxious_shot", EntityNoxiousShot.class),
				newProjectileEntry("odious_shot", EntityOdious.class),
				newProjectileEntry("omnilight_shot", EntityOmnilightShot.class),
				newProjectileEntry("orbocron_shot", EntityOrbocron.class),
				newProjectileEntry("paralyzer_shot", EntityParalyzerShot.class),
				newProjectileEntry("party_popper_shot", EntityPartyPopper.class),
				newProjectileEntry("penumbra_shot", EntityPenumbraShot.class),
				newProjectileEntry("phantom_shot", EntityPhantomShot.class),
				newProjectileEntry("poison_plunger_shot", EntityPoisonPlunger.class),
				newProjectileEntry("poison_shot", EntityPoisonShot.class),
				newProjectileEntry("polymorph_shot", EntityPolymorphShot.class),
				newProjectileEntry("polytom_shot", EntityPolytomShot.class),
				newProjectileEntry("pop_shot", EntityPopShot.class),
				newProjectileEntry("power_ray", EntityPowerRay.class),
				newProjectileEntry("power_shot", EntityPowerShot.class),
				newProjectileEntry("primordial_shot", EntityPrimordialShot.class),
				newProjectileEntry("proton_shot", EntityProton.class),
				newProjectileEntry("rainbow_shot", EntityRainbowShot.class),
				newProjectileEntry("red_guardian_shot", EntityRedGuardianShot.class),
				newProjectileEntry("reefer_shot", EntityReeferShot.class),
				newProjectileEntry("revolution_shot", EntityRevolutionShot.class),
				newProjectileEntry("rock_fragment", EntityRockFragment.class),
				newProjectileEntry("rock_fragment_fungal", EntityRockFragmentFungal.class),
				newProjectileEntry("rosidian_shot", EntityRosidianShot.class),
				newProjectileEntry("runic_bomb", EntityRunicBomb.class),
				newProjectileEntry("runic_guardian_shot", EntityRunicGuardianShot.class),
				newProjectileEntry("seaocron_shot", EntitySeaocron.class),
				newProjectileEntry("seed_dart", EntitySeedDart.class),
				newProjectileEntry("shadowlord_shot", EntityShadowlordShot.class),
				newProjectileEntry("shoe_shot", EntityShoeShot.class),
				newProjectileEntry("shower_shot", EntityShowerShot.class),
				newProjectileEntry("shyre_beam", EntityShyreBeam.class),
				newProjectileEntry("shyre_shot", EntityShyreShot.class),
				newProjectileEntry("skullo_shot", EntitySkulloShot.class),
				newProjectileEntry("sky_shot", EntitySkyShot.class),
				newProjectileEntry("slice_star", EntitySliceStar.class),
				newProjectileEntry("sniper_slug", EntitySniperSlug.class),
				newProjectileEntry("soul_drainer_shot", EntitySoulDrainer.class),
				newProjectileEntry("soul_spark", EntitySoulSpark.class),
				newProjectileEntry("soul_storm_shot", EntitySoulStorm.class),
				newProjectileEntry("spectral_holly_arrow", EntitySpectralHollyArrow.class),
				newProjectileEntry("spectral_shot", EntitySpectralShot.class),
				newProjectileEntry("spiritual_shot", EntitySpiritualShot.class),
				newProjectileEntry("sun_shot", EntitySunShot.class),
				newProjectileEntry("swarm_shot", EntitySwarmShot.class),
				newProjectileEntry("tangle_fall", EntityTangleFall.class),
				newProjectileEntry("terror_construct_shot", EntityConstructTerrorShot.class),
				newProjectileEntry("tidal_wave", EntityTidalWave.class),
				newProjectileEntry("tipped_holly_arrow", EntityTippedHollyArrow.class),
				newProjectileEntry("toxic_shot", EntityToxicShot.class),
				newProjectileEntry("tri_discharge_shot", EntityTriDischargeShot.class),
				newProjectileEntry("ultimatum_shot", EntityUltimatumShot.class),
				newProjectileEntry("valkyrie_shot", EntityValkyrieShot.class),
				newProjectileEntry("vine_wizard_shot", EntityVineWizardShot.class),
				newProjectileEntry("volar_shot", EntityVolarShot.class),
				newProjectileEntry("vortex_blast", EntityVortexBlaster.class),
				newProjectileEntry("voxxulon_meteor", EntityVoxxulonMeteor.class),
				newProjectileEntry("vulkram", EntityVulkram.class),
				newProjectileEntry("wart_dart", EntityWartDart.class),
				newProjectileEntry("water_shot", EntityWaterShot.class),
				newProjectileEntry("weighted_shower_shot", EntityWeightedShowerShot.class),
				newProjectileEntry("white_ball", EntityWhiteBall.class),
				newProjectileEntry("winder_shot", EntityWinderShot.class),
				newProjectileEntry("wither_ball", EntityWitherBall.class),
				newProjectileEntry("wither_shot", EntityWitherShot.class),
				newProjectileEntry("wrath_shot", EntityWrathShot.class),
				newProjectileEntry("yellow_guardian_shot", EntityYellowGuardianShot.class)
		};
	}
	
	private static <E extends Entity> EntityEntry newEntry(final String name, final Class<E> entityClass) {
		return newEntry(name, entityClass, 16777215, 0);
	}

	private static <E extends Entity> EntityEntry newEntry(final String name, final Class<E> entityClass, int primaryEggColour, int secondaryEggColour) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation resource = new ResourceLocation("aoa3", name);

		builder.entity(entityClass);
		builder.tracker(120, 1, true);
		builder.egg(primaryEggColour, secondaryEggColour);

		EntityEntryBuilder.BuiltEntityEntry entry = (EntityEntryBuilder.BuiltEntityEntry)builder.id(resource, entityId++).name(resource.getNamespace() + "." + resource.getPath()).build();
		entry.addedToRegistry();

		return entry;
	}

	private static <E extends Entity> EntityEntry newEgglessEntry(final String name, final Class<E> entityClass) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation resource = new ResourceLocation("aoa3", name);

		builder.entity(entityClass);
		builder.tracker(120, 1, true);

		EntityEntryBuilder.BuiltEntityEntry entry = (EntityEntryBuilder.BuiltEntityEntry)builder.id(resource, entityId++).name(resource.getNamespace() + "." + resource.getPath()).build();
		entry.addedToRegistry();

		return entry;
	}

	private static <E extends Entity> EntityEntry newMiscEntityEntry(final String name, final Class<E> entityClass) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation resource = new ResourceLocation("aoa3", name);

		builder.entity(entityClass);
		builder.tracker(40, 1, true);

		return builder.id(resource, entityId++).name(resource.getNamespace() + "." + resource.getPath()).build();
	}

	private static EntityEntry newProjectileEntry(final String name, final Class clazz) {
		final EntityEntryBuilder<? extends Entity> builder = EntityEntryBuilder.create();
		final ResourceLocation resource = new ResourceLocation("aoa3", name);

		return builder.id(resource, entityId++).name(resource.getNamespace() + "." + resource.getPath()).entity(clazz).tracker(120, 20, true).build();
	}
}
