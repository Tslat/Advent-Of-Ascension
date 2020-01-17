package net.tslat.aoa3.common.registration;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.utils.ModUtil;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class SoundsRegister {
	@GameRegistry.ObjectHolder("music_null")
	public static final SoundEvent nullMusic = null;

	@GameRegistry.ObjectHolder("ascension_shrine_use")
	public static final SoundEvent ascensionShrineUse = null;
	@GameRegistry.ObjectHolder("baron_bomb_priming")
	public static final SoundEvent baronBombPriming = null;
	@GameRegistry.ObjectHolder("baron_bomb_spawn")
	public static final SoundEvent baronBombSpawn = null;
	@GameRegistry.ObjectHolder("bloodlust_collect")
	public static final SoundEvent bloodlustCollect = null;
	@GameRegistry.ObjectHolder("bone_horn_call")
	public static final SoundEvent boneHornCall = null;
	@GameRegistry.ObjectHolder("bubble_shot_pop")
	public static final SoundEvent bubbleShotPop = null;
	@GameRegistry.ObjectHolder("candy_snail_step")
	public static final SoundEvent candySnailStep = null;
	@GameRegistry.ObjectHolder("candy_thump")
	public static final SoundEvent candyThump = null;
	@GameRegistry.ObjectHolder("chainsaw_use")
	public static final SoundEvent chainsawUse = null;
	@GameRegistry.ObjectHolder("creation_forge_use")
	public static final SoundEvent creationForgeUse = null;
	@GameRegistry.ObjectHolder("crystal_creator_use")
	public static final SoundEvent crystalCreatorUse = null;
	@GameRegistry.ObjectHolder("crystal_extension_shrine_use")
	public static final SoundEvent crystalExtensionShrineUse = null;
	@GameRegistry.ObjectHolder("declogging_table_use")
	public static final SoundEvent decloggingTableUse = null;
	@GameRegistry.ObjectHolder("entity_generic_dino_step")
	public static final SoundEvent dinoStep = null;
	@GameRegistry.ObjectHolder("player_dodge")
	public static final SoundEvent dodge = null;
	@GameRegistry.ObjectHolder("entity_idol_hit")
	public static final SoundEvent entityIdolHit = null;
	@GameRegistry.ObjectHolder("entity_idol_living")
	public static final SoundEvent entityIdolLiving = null;
	@GameRegistry.ObjectHolder("entity_idol_prize")
	public static final SoundEvent entityIdolPrize = null;
	@GameRegistry.ObjectHolder("entity_idol_spawn")
	public static final SoundEvent entityIdolSpawn = null;
	@GameRegistry.ObjectHolder("entity_pixon_harvest")
	public static final SoundEvent entityPixonHarvest = null;
	@GameRegistry.ObjectHolder("entity_pixon_living")
	public static final SoundEvent entityPixonLiving = null;
	@GameRegistry.ObjectHolder("big_day_start")
	public static final SoundEvent eventBigDayStart = null;
	@GameRegistry.ObjectHolder("blood_hunt_start")
	public static final SoundEvent eventBloodHuntStart = null;
	@GameRegistry.ObjectHolder("creep_day_start")
	public static final SoundEvent eventCreepDayStart = null;
	@GameRegistry.ObjectHolder("death_day_start")
	public static final SoundEvent eventDeathDayStart = null;
	@GameRegistry.ObjectHolder("lunar_invasion_start")
	public static final SoundEvent eventLunarInvasionStart = null;
	@GameRegistry.ObjectHolder("soul_scurry_start")
	public static final SoundEvent eventSoulScurryStart = null;
	@GameRegistry.ObjectHolder("extraction_success")
	public static final SoundEvent extractionDeviceSuccess = null;
	@GameRegistry.ObjectHolder("filtration_system_activate")
	public static final SoundEvent filtrationSystemActivate = null;
	@GameRegistry.ObjectHolder("filtration_system_use")
	public static final SoundEvent filtrationSystemUse = null;
	@GameRegistry.ObjectHolder("foraging_loot")
	public static final SoundEvent foragingLoot = null;
	@GameRegistry.ObjectHolder("goo_ball_impact")
	public static final SoundEvent gooBallImpact = null;
	@GameRegistry.ObjectHolder("goofy_greatblade_fail")
	public static final SoundEvent greatbladeGoofy = null;
	@GameRegistry.ObjectHolder("goofy_tool_fail")
	public static final SoundEvent goofyToolFail = null;
	@GameRegistry.ObjectHolder("abominator_fire")
	public static final SoundEvent gunAbominator = null;
	@GameRegistry.ObjectHolder("archergun_fire")
	public static final SoundEvent gunArchergun = null;
	@GameRegistry.ObjectHolder("artifact_fire")
	public static final SoundEvent gunArtifact = null;
	@GameRegistry.ObjectHolder("atomizer_fire")
	public static final SoundEvent gunAtomizer = null;
	@GameRegistry.ObjectHolder("ball_cannon_fire")
	public static final SoundEvent gunBallCannon = null;
	@GameRegistry.ObjectHolder("big_blast_fire")
	public static final SoundEvent gunBigBlast = null;
	@GameRegistry.ObjectHolder("blowpipe_fire")
	public static final SoundEvent gunBlowpipe = null;
	@GameRegistry.ObjectHolder("boom_cannon_fire")
	public static final SoundEvent gunBoomCannon = null;
	@GameRegistry.ObjectHolder("bubble_gun_fire")
	public static final SoundEvent gunBubbleGun = null;
	@GameRegistry.ObjectHolder("carrot_cannon_fire")
	public static final SoundEvent gunCarrotCannon = null;
	@GameRegistry.ObjectHolder("chaingun_fire")
	public static final SoundEvent gunChaingun = null;
	@GameRegistry.ObjectHolder("chugger_fire")
	public static final SoundEvent gunChugger = null;
	@GameRegistry.ObjectHolder("clowner_fire")
	public static final SoundEvent gunClowner = null;
	@GameRegistry.ObjectHolder("colour_cannon_fire")
	public static final SoundEvent gunColourCannon = null;
	@GameRegistry.ObjectHolder("confetti_cannon_fire")
	public static final SoundEvent gunConfettiCannon = null;
	@GameRegistry.ObjectHolder("dark_gun_fire")
	public static final SoundEvent gunDarkGun = null;
	@GameRegistry.ObjectHolder("discharge_gun_fire")
	public static final SoundEvent gunDischargeGun = null;
	@GameRegistry.ObjectHolder("doom_gun_fire")
	public static final SoundEvent gunDoomGun = null;
	@GameRegistry.ObjectHolder("drain_gun_fire")
	public static final SoundEvent gunDrainGun = null;
	@GameRegistry.ObjectHolder("electro_cannon_1_fire")
	public static final SoundEvent gunElectroCannon1 = null;
	@GameRegistry.ObjectHolder("electro_cannon_2_fire")
	public static final SoundEvent gunElectroCannon2 = null;
	@GameRegistry.ObjectHolder("electro_cannon_3_fire")
	public static final SoundEvent gunElectroCannon3 = null;
	@GameRegistry.ObjectHolder("electro_cannon_4_fire")
	public static final SoundEvent gunElectroCannon4 = null;
	@GameRegistry.ObjectHolder("electro_cannon_5_fire")
	public static final SoundEvent gunElectroCannon5 = null;
	@GameRegistry.ObjectHolder("energy_cannon_fire")
	public static final SoundEvent gunEnergyCannon = null;
	@GameRegistry.ObjectHolder("fast_rifle_fire")
	public static final SoundEvent gunFastRifle = null;
	@GameRegistry.ObjectHolder("flinger_fire")
	public static final SoundEvent gunFlinger = null;
	@GameRegistry.ObjectHolder("gas_gun_fire")
	public static final SoundEvent gunGasGun = null;
	@GameRegistry.ObjectHolder("gauge_rifle_fire")
	public static final SoundEvent gunGaugeRifle = null;
	@GameRegistry.ObjectHolder("golem_gun_fire")
	public static final SoundEvent gunGolemGun = null;
	@GameRegistry.ObjectHolder("gravity_blaster_fire")
	public static final SoundEvent gunGravityBlaster = null;
	@GameRegistry.ObjectHolder("heat_wave_fire")
	public static final SoundEvent gunHeatWave = null;
	@GameRegistry.ObjectHolder("high_cannon_fire")
	public static final SoundEvent gunHighCannon = null;
	@GameRegistry.ObjectHolder("illusion_revolver_fire")
	public static final SoundEvent gunIllusionRevolver = null;
	@GameRegistry.ObjectHolder("illusion_smg_fire")
	public static final SoundEvent gunIllusionSMG = null;
	@GameRegistry.ObjectHolder("ion_blaster_fire")
	public static final SoundEvent gunIonBlaster = null;
	@GameRegistry.ObjectHolder("jack_rocker_fire")
	public static final SoundEvent gunJackRocker = null;
	@GameRegistry.ObjectHolder("krasauns_dawn_fire")
	public static final SoundEvent gunKrasaunsDawn = null;
	@GameRegistry.ObjectHolder("light_cannon_fire")
	public static final SoundEvent gunLightCannon = null;
	@GameRegistry.ObjectHolder("lower_cannon_fire")
	public static final SoundEvent gunLowerCannon = null;
	@GameRegistry.ObjectHolder("magic_gun_fire")
	public static final SoundEvent gunMagicGun = null;
	@GameRegistry.ObjectHolder("mech_cannon_fire")
	public static final SoundEvent gunMechCannon = null;
	@GameRegistry.ObjectHolder("mind_blaster_fire")
	public static final SoundEvent gunMindBlaster = null;
	@GameRegistry.ObjectHolder("minigun_fire")
	public static final SoundEvent gunMinigun = null;
	@GameRegistry.ObjectHolder("mini_pistol_fire")
	public static final SoundEvent gunMiniPistol = null;
	@GameRegistry.ObjectHolder("missile_maker_fire")
	public static final SoundEvent gunMissileMaker = null;
	@GameRegistry.ObjectHolder("monster_fire")
	public static final SoundEvent gunMonster = null;
	@GameRegistry.ObjectHolder("moon_shiner_fire")
	public static final SoundEvent gunMoonShiner = null;
	@GameRegistry.ObjectHolder("paralyzer_fire")
	public static final SoundEvent gunParalyzer = null;
	@GameRegistry.ObjectHolder("party_popper_fire")
	public static final SoundEvent gunPartyPopper = null;
	@GameRegistry.ObjectHolder("ray_gun_fire")
	public static final SoundEvent gunRayGun = null;
	@GameRegistry.ObjectHolder("reefer_fire")
	public static final SoundEvent gunReefer = null;
	@GameRegistry.ObjectHolder("revolution_fire")
	public static final SoundEvent gunRevolution = null;
	@GameRegistry.ObjectHolder("revolver_fire")
	public static final SoundEvent gunRevolver = null;
	@GameRegistry.ObjectHolder("roulette_fire")
	public static final SoundEvent gunRoulette = null;
	@GameRegistry.ObjectHolder("rpg_fire")
	public static final SoundEvent gunRPG = null;
	@GameRegistry.ObjectHolder("shadow_blaster_fire")
	public static final SoundEvent gunShadowBlaster = null;
	@GameRegistry.ObjectHolder("shotgun_fire")
	public static final SoundEvent gunShotgun = null;
	@GameRegistry.ObjectHolder("slugger_fire")
	public static final SoundEvent gunSlugger = null;
	@GameRegistry.ObjectHolder("sniper_fire")
	public static final SoundEvent gunSniper = null;
	@GameRegistry.ObjectHolder("soul_spark_fire")
	public static final SoundEvent gunSoulSpark = null;
	@GameRegistry.ObjectHolder("space_gun_fire")
	public static final SoundEvent gunSpaceGun = null;
	@GameRegistry.ObjectHolder("space_revolver_fire")
	public static final SoundEvent gunSpaceRevolver = null;
	@GameRegistry.ObjectHolder("spirit_shower_fire")
	public static final SoundEvent gunSpiritShower = null;
	@GameRegistry.ObjectHolder("sprayer_fire")
	public static final SoundEvent gunSprayer = null;
	@GameRegistry.ObjectHolder("squad_gun_fire")
	public static final SoundEvent gunSquadGun = null;
	@GameRegistry.ObjectHolder("stampede_fire")
	public static final SoundEvent gunStampede = null;
	@GameRegistry.ObjectHolder("step_cannon_1_fire")
	public static final SoundEvent gunStepCannon1 = null;
	@GameRegistry.ObjectHolder("step_cannon_2_fire")
	public static final SoundEvent gunStepCannon2 = null;
	@GameRegistry.ObjectHolder("step_cannon_3_fire")
	public static final SoundEvent gunStepCannon3 = null;
	@GameRegistry.ObjectHolder("step_cannon_4_fire")
	public static final SoundEvent gunStepCannon4 = null;
	@GameRegistry.ObjectHolder("step_cannon_5_fire")
	public static final SoundEvent gunStepCannon5 = null;
	@GameRegistry.ObjectHolder("swarmotron_fire")
	public static final SoundEvent gunSwarmotron = null;
	@GameRegistry.ObjectHolder("synth_cannon_1_fire")
	public static final SoundEvent gunSynthCannon1 = null;
	@GameRegistry.ObjectHolder("synth_cannon_2_fire")
	public static final SoundEvent gunSynthCannon2 = null;
	@GameRegistry.ObjectHolder("synth_cannon_3_fire")
	public static final SoundEvent gunSynthCannon3 = null;
	@GameRegistry.ObjectHolder("synth_cannon_4_fire")
	public static final SoundEvent gunSynthCannon4 = null;
	@GameRegistry.ObjectHolder("synth_cannon_5_fire")
	public static final SoundEvent gunSynthCannon5 = null;
	@GameRegistry.ObjectHolder("upper_cannon_fire")
	public static final SoundEvent gunUpperCannon = null;
	@GameRegistry.ObjectHolder("vibe_cannon_1_fire")
	public static final SoundEvent gunVibeCannon1 = null;
	@GameRegistry.ObjectHolder("vibe_cannon_2_fire")
	public static final SoundEvent gunVibeCannon2 = null;
	@GameRegistry.ObjectHolder("vibe_cannon_3_fire")
	public static final SoundEvent gunVibeCannon3 = null;
	@GameRegistry.ObjectHolder("vibe_cannon_4_fire")
	public static final SoundEvent gunVibeCannon4 = null;
	@GameRegistry.ObjectHolder("vibe_cannon_5_fire")
	public static final SoundEvent gunVibeCannon5 = null;
	@GameRegistry.ObjectHolder("whimsy_winder_fire")
	public static final SoundEvent gunWhimsyWinder = null;
	@GameRegistry.ObjectHolder("wither_cannon_fire")
	public static final SoundEvent gunWitherCannon = null;
	@GameRegistry.ObjectHolder("withers_wrath_fire")
	public static final SoundEvent gunWithersWrath = null;
	@GameRegistry.ObjectHolder("wood_rifle_fire")
	public static final SoundEvent gunWoodRifle = null;
	@GameRegistry.ObjectHolder("haunting_table_use")
	public static final SoundEvent hauntingTableUse = null;
	@GameRegistry.ObjectHolder("heart_stone_use")
	public static final SoundEvent heartStonePickup = null;
	@GameRegistry.ObjectHolder("heart_stone_spawn")
	public static final SoundEvent heartStoneSpawn = null;
	@GameRegistry.ObjectHolder("entity_generic_heavy_step")
	public static final SoundEvent heavyStep = null;
	@GameRegistry.ObjectHolder("hellfire_impact")
	public static final SoundEvent hellfireImpact = null;
	@GameRegistry.ObjectHolder("infusion_fail")
	public static final SoundEvent infusionFail = null;
	@GameRegistry.ObjectHolder("infusion_success")
	public static final SoundEvent infusionSuccess = null;
	@GameRegistry.ObjectHolder("player_level_100")
	public static final SoundEvent level100 = null;
	@GameRegistry.ObjectHolder("player_level_up")
	public static final SoundEvent levelUp = null;
	@GameRegistry.ObjectHolder("lotto_win")
	public static final SoundEvent lottoWin = null;
	@GameRegistry.ObjectHolder("lunar_creation_table_success")
	public static final SoundEvent lunarCreationTableSuccess = null;
	@GameRegistry.ObjectHolder("lunar_enrichment_table_use")
	public static final SoundEvent lunarEnrichmentTableUse = null;
	@GameRegistry.ObjectHolder("runes_craft")
	public static final SoundEvent makeRunes = null;
	@GameRegistry.ObjectHolder("mending_success")
	public static final SoundEvent mendingSuccess = null;
	@GameRegistry.ObjectHolder("mob_airhead_death")
	public static final SoundEvent mobAirheadDeath = null;
	@GameRegistry.ObjectHolder("mob_airhead_hit")
	public static final SoundEvent mobAirheadHit = null;
	@GameRegistry.ObjectHolder("mob_airhead_living")
	public static final SoundEvent mobAirheadLiving = null;
	@GameRegistry.ObjectHolder("mob_alarmo_death")
	public static final SoundEvent mobAlarmoDeath = null;
	@GameRegistry.ObjectHolder("mob_alarmo_hit")
	public static final SoundEvent mobAlarmoHit = null;
	@GameRegistry.ObjectHolder("mob_alarmo_living")
	public static final SoundEvent mobAlarmoLiving = null;
	@GameRegistry.ObjectHolder("mob_amphibior_death")
	public static final SoundEvent mobAmphibiorDeath = null;
	@GameRegistry.ObjectHolder("mob_amphibior_hit")
	public static final SoundEvent mobAmphibiorHit = null;
	@GameRegistry.ObjectHolder("mob_amphibior_living")
	public static final SoundEvent mobAmphibiorLiving = null;
	@GameRegistry.ObjectHolder("mob_amphibiyte_death")
	public static final SoundEvent mobAmphibiyteDeath = null;
	@GameRegistry.ObjectHolder("mob_amphibiyte_hit")
	public static final SoundEvent mobAmphibiyteHit = null;
	@GameRegistry.ObjectHolder("mob_amphibiyte_living")
	public static final SoundEvent mobAmphibiyteLiving = null;
	@GameRegistry.ObjectHolder("mob_anemia_death")
	public static final SoundEvent mobAnemiaDeath = null;
	@GameRegistry.ObjectHolder("mob_anemia_hit")
	public static final SoundEvent mobAnemiaHit = null;
	@GameRegistry.ObjectHolder("mob_anemia_living")
	public static final SoundEvent mobAnemiaLiving = null;
	@GameRegistry.ObjectHolder("mob_angelica_death")
	public static final SoundEvent mobAngelicaDeath = null;
	@GameRegistry.ObjectHolder("mob_angelica_hit")
	public static final SoundEvent mobAngelicaHit = null;
	@GameRegistry.ObjectHolder("mob_angelica_living")
	public static final SoundEvent mobAngelicaLiving = null;
	@GameRegistry.ObjectHolder("mob_angler_death")
	public static final SoundEvent mobAnglerDeath = null;
	@GameRegistry.ObjectHolder("mob_angler_hit")
	public static final SoundEvent mobAnglerHit = null;
	@GameRegistry.ObjectHolder("mob_angler_living")
	public static final SoundEvent mobAnglerLiving = null;
	@GameRegistry.ObjectHolder("mob_apparition_death")
	public static final SoundEvent mobApparitionDeath = null;
	@GameRegistry.ObjectHolder("mob_apparition_hit")
	public static final SoundEvent mobApparitionHit = null;
	@GameRegistry.ObjectHolder("mob_apparition_living")
	public static final SoundEvent mobApparitionLiving = null;
	@GameRegistry.ObjectHolder("mob_arcbeast_death")
	public static final SoundEvent mobArcbeastDeath = null;
	@GameRegistry.ObjectHolder("mob_arcbeast_hit")
	public static final SoundEvent mobArcbeastHit = null;
	@GameRegistry.ObjectHolder("mob_arcbeast_living")
	public static final SoundEvent mobArcbeastLiving = null;
	@GameRegistry.ObjectHolder("mob_archvine_death")
	public static final SoundEvent mobArchvineDeath = null;
	@GameRegistry.ObjectHolder("mob_archvine_hit")
	public static final SoundEvent mobArchvineHit = null;
	@GameRegistry.ObjectHolder("mob_archvine_living")
	public static final SoundEvent mobArchvineLiving = null;
	@GameRegistry.ObjectHolder("mob_arc_wizard_death")
	public static final SoundEvent mobArcWizardDeath = null;
	@GameRegistry.ObjectHolder("mob_arc_wizard_hit")
	public static final SoundEvent mobArcWizardHit = null;
	@GameRegistry.ObjectHolder("mob_arc_wizard_living")
	public static final SoundEvent mobArcWizardLiving = null;
	@GameRegistry.ObjectHolder("mob_arcworm_death")
	public static final SoundEvent mobArcwormDeath = null;
	@GameRegistry.ObjectHolder("mob_arcworm_hit")
	public static final SoundEvent mobArcwormHit = null;
	@GameRegistry.ObjectHolder("mob_arcworm_living")
	public static final SoundEvent mobArcwormLiving = null;
	@GameRegistry.ObjectHolder("mob_ariel_death")
	public static final SoundEvent mobArielDeath = null;
	@GameRegistry.ObjectHolder("mob_ariel_hit")
	public static final SoundEvent mobArielHit = null;
	@GameRegistry.ObjectHolder("mob_ariel_living")
	public static final SoundEvent mobArielLiving = null;
	@GameRegistry.ObjectHolder("mob_arkback_death")
	public static final SoundEvent mobArkbackDeath = null;
	@GameRegistry.ObjectHolder("mob_arkback_hit")
	public static final SoundEvent mobArkbackHit = null;
	@GameRegistry.ObjectHolder("mob_arkback_living")
	public static final SoundEvent mobArkbackLiving = null;
	@GameRegistry.ObjectHolder("mob_arkzyne_death")
	public static final SoundEvent mobArkzyneDeath = null;
	@GameRegistry.ObjectHolder("mob_arkzyne_hit")
	public static final SoundEvent mobArkzyneHit = null;
	@GameRegistry.ObjectHolder("mob_arkzyne_living")
	public static final SoundEvent mobArkzyneLiving = null;
	@GameRegistry.ObjectHolder("mob_automaton_death")
	public static final SoundEvent mobAutomatonDeath = null;
	@GameRegistry.ObjectHolder("mob_automaton_hit")
	public static final SoundEvent mobAutomatonHit = null;
	@GameRegistry.ObjectHolder("mob_automaton_living")
	public static final SoundEvent mobAutomatonLiving = null;
	@GameRegistry.ObjectHolder("mob_axiolight_death")
	public static final SoundEvent mobAxiolightDeath = null;
	@GameRegistry.ObjectHolder("mob_axiolight_hit")
	public static final SoundEvent mobAxiolightHit = null;
	@GameRegistry.ObjectHolder("mob_axiolight_living")
	public static final SoundEvent mobAxiolightLiving = null;
	@GameRegistry.ObjectHolder("mob_bane_death")
	public static final SoundEvent mobBaneDeath = null;
	@GameRegistry.ObjectHolder("mob_bane_living")
	public static final SoundEvent mobBaneLiving = null;
	@GameRegistry.ObjectHolder("mob_banshee_death")
	public static final SoundEvent mobBansheeDeath = null;
	@GameRegistry.ObjectHolder("mob_banshee_hit")
	public static final SoundEvent mobBansheeHit = null;
	@GameRegistry.ObjectHolder("mob_banshee_living")
	public static final SoundEvent mobBansheeLiving = null;
	@GameRegistry.ObjectHolder("mob_basilisk_death")
	public static final SoundEvent mobBasiliskDeath = null;
	@GameRegistry.ObjectHolder("mob_basilisk_hit")
	public static final SoundEvent mobBasiliskHit = null;
	@GameRegistry.ObjectHolder("mob_basilisk_living")
	public static final SoundEvent mobBasiliskLiving = null;
	@GameRegistry.ObjectHolder("mob_baumba_jump")
	public static final SoundEvent mobBaumbaJump = null;
	@GameRegistry.ObjectHolder("mob_bloodmist_death")
	public static final SoundEvent mobBloodmistDeath = null;
	@GameRegistry.ObjectHolder("mob_bloodmist_hit")
	public static final SoundEvent mobBloodmistHit = null;
	@GameRegistry.ObjectHolder("mob_bloodmist_living")
	public static final SoundEvent mobBloodmistLiving = null;
	@GameRegistry.ObjectHolder("mob_bloodsucker_death")
	public static final SoundEvent mobBloodsuckerDeath = null;
	@GameRegistry.ObjectHolder("mob_bloodsucker_hit")
	public static final SoundEvent mobBloodsuckerHit = null;
	@GameRegistry.ObjectHolder("mob_bloodsucker_living")
	public static final SoundEvent mobBloodsuckerLiving = null;
	@GameRegistry.ObjectHolder("mob_bomb_carrier_hit")
	public static final SoundEvent mobBombCarrierHit = null;
	@GameRegistry.ObjectHolder("mob_bomb_carrier_living")
	public static final SoundEvent mobBombCarrierLiving = null;
	@GameRegistry.ObjectHolder("mob_boneback_death")
	public static final SoundEvent mobBonebackDeath = null;
	@GameRegistry.ObjectHolder("mob_boneback_hit")
	public static final SoundEvent mobBonebackHit = null;
	@GameRegistry.ObjectHolder("mob_boneback_living")
	public static final SoundEvent mobBonebackLiving = null;
	@GameRegistry.ObjectHolder("mob_bouncer_death")
	public static final SoundEvent mobBouncerDeath = null;
	@GameRegistry.ObjectHolder("mob_bouncer_hit")
	public static final SoundEvent mobBouncerHit = null;
	@GameRegistry.ObjectHolder("mob_bouncer_living")
	public static final SoundEvent mobBouncerLiving = null;
	@GameRegistry.ObjectHolder("mob_bugeye_death")
	public static final SoundEvent mobBugeyeDeath = null;
	@GameRegistry.ObjectHolder("mob_bugeye_hit")
	public static final SoundEvent mobBugeyeHit = null;
	@GameRegistry.ObjectHolder("mob_bugeye_living")
	public static final SoundEvent mobBugeyeLiving = null;
	@GameRegistry.ObjectHolder("mob_bush_baby_death")
	public static final SoundEvent mobBushBabyDeath = null;
	@GameRegistry.ObjectHolder("mob_bush_baby_hit")
	public static final SoundEvent mobBushBabyHit = null;
	@GameRegistry.ObjectHolder("mob_bush_baby_living")
	public static final SoundEvent mobBushBabyLiving = null;
	@GameRegistry.ObjectHolder("mob_carrotop_death")
	public static final SoundEvent mobCarrotopDeath = null;
	@GameRegistry.ObjectHolder("mob_carrotop_hit")
	public static final SoundEvent mobCarrotopHit = null;
	@GameRegistry.ObjectHolder("mob_carrotop_living")
	public static final SoundEvent mobCarrotopLiving = null;
	@GameRegistry.ObjectHolder("mob_cave_bug_death")
	public static final SoundEvent mobCaveBugDeath = null;
	@GameRegistry.ObjectHolder("mob_cave_bug_hit")
	public static final SoundEvent mobCaveBugHit = null;
	@GameRegistry.ObjectHolder("mob_cave_bug_living")
	public static final SoundEvent mobCaveBugLiving = null;
	@GameRegistry.ObjectHolder("mob_cave_creep_death")
	public static final SoundEvent mobCaveCreepDeath = null;
	@GameRegistry.ObjectHolder("mob_cave_creep_hit")
	public static final SoundEvent mobCaveCreepHit = null;
	@GameRegistry.ObjectHolder("mob_cave_creep_living")
	public static final SoundEvent mobCaveCreepLiving = null;
	@GameRegistry.ObjectHolder("mob_celeve_clown_death")
	public static final SoundEvent mobCeleveClownDeath = null;
	@GameRegistry.ObjectHolder("mob_celeve_clown_hit")
	public static final SoundEvent mobCeleveClownHit = null;
	@GameRegistry.ObjectHolder("mob_celeve_clown_living")
	public static final SoundEvent mobCeleveClownLiving = null;
	@GameRegistry.ObjectHolder("mob_charger_death")
	public static final SoundEvent mobChargerDeath = null;
	@GameRegistry.ObjectHolder("mob_charger_hit")
	public static final SoundEvent mobChargerHit = null;
	@GameRegistry.ObjectHolder("mob_charger_living")
	public static final SoundEvent mobChargerLiving = null;
	@GameRegistry.ObjectHolder("mob_chimera_death")
	public static final SoundEvent mobChimeraDeath = null;
	@GameRegistry.ObjectHolder("mob_chimera_hit")
	public static final SoundEvent mobChimeraHit = null;
	@GameRegistry.ObjectHolder("mob_chimera_living")
	public static final SoundEvent mobChimeraLiving = null;
	@GameRegistry.ObjectHolder("mob_chomper_hit")
	public static final SoundEvent mobChomperHit = null;
	@GameRegistry.ObjectHolder("mob_chomper_living")
	public static final SoundEvent mobChomperLiving = null;
	@GameRegistry.ObjectHolder("mob_clown_death")
	public static final SoundEvent mobClownDeath = null;
	@GameRegistry.ObjectHolder("mob_clown_hit")
	public static final SoundEvent mobClownHit = null;
	@GameRegistry.ObjectHolder("mob_clown_living")
	public static final SoundEvent mobClownLiving = null;
	@GameRegistry.ObjectHolder("mob_clunkhead_death")
	public static final SoundEvent mobClunkheadDeath = null;
	@GameRegistry.ObjectHolder("mob_compeer_death")
	public static final SoundEvent mobCompeerDeath = null;
	@GameRegistry.ObjectHolder("mob_compeer_hit")
	public static final SoundEvent mobCompeerHit = null;
	@GameRegistry.ObjectHolder("mob_compeer_living")
	public static final SoundEvent mobCompeerLiving = null;
	@GameRegistry.ObjectHolder("mob_coniferon_death")
	public static final SoundEvent mobConiferonDeath = null;
	@GameRegistry.ObjectHolder("mob_coniferon_hit")
	public static final SoundEvent mobConiferonHit = null;
	@GameRegistry.ObjectHolder("mob_coniferon_living")
	public static final SoundEvent mobConiferonLiving = null;
	@GameRegistry.ObjectHolder("mob_corallus_death")
	public static final SoundEvent mobCorallusDeath = null;
	@GameRegistry.ObjectHolder("mob_corallus_hit")
	public static final SoundEvent mobCorallusHit = null;
	@GameRegistry.ObjectHolder("mob_corallus_living")
	public static final SoundEvent mobCorallusLiving = null;
	@GameRegistry.ObjectHolder("mob_corallus_taunt")
	public static final SoundEvent mobCorallusTaunt = null;
	@GameRegistry.ObjectHolder("mob_coralon_death")
	public static final SoundEvent mobCoralonDeath = null;
	@GameRegistry.ObjectHolder("mob_coralon_hit")
	public static final SoundEvent mobCoralonHit = null;
	@GameRegistry.ObjectHolder("mob_coralon_living")
	public static final SoundEvent mobCoralonLiving = null;
	@GameRegistry.ObjectHolder("mob_coratee_death")
	public static final SoundEvent mobCorateeDeath = null;
	@GameRegistry.ObjectHolder("mob_coratee_hit")
	public static final SoundEvent mobCorateeHit = null;
	@GameRegistry.ObjectHolder("mob_coratee_living")
	public static final SoundEvent mobCorateeLiving = null;
	@GameRegistry.ObjectHolder("mob_cotton_candor_death")
	public static final SoundEvent mobCottonCandorDeath = null;
	@GameRegistry.ObjectHolder("mob_cotton_candor_hit")
	public static final SoundEvent mobCottonCandorHit = null;
	@GameRegistry.ObjectHolder("mob_cotton_candor_living")
	public static final SoundEvent mobCottonCandorLiving = null;
	@GameRegistry.ObjectHolder("mob_craexxeus_charge")
	public static final SoundEvent mobCraexxeusCharge = null;
	@GameRegistry.ObjectHolder("mob_craexxeus_death")
	public static final SoundEvent mobCraexxeusDeath = null;
	@GameRegistry.ObjectHolder("mob_craexxeus_hit")
	public static final SoundEvent mobCraexxeusHit = null;
	@GameRegistry.ObjectHolder("mob_craexxeus_living")
	public static final SoundEvent mobCraexxeusLiving = null;
	@GameRegistry.ObjectHolder("mob_creeperlock_teleport")
	public static final SoundEvent mobCreeperlockTeleport = null;
	@GameRegistry.ObjectHolder("mob_creepird_death")
	public static final SoundEvent mobCreepirdDeath = null;
	@GameRegistry.ObjectHolder("mob_creepird_hit")
	public static final SoundEvent mobCreepirdHit = null;
	@GameRegistry.ObjectHolder("mob_creepird_living")
	public static final SoundEvent mobCreepirdLiving = null;
	@GameRegistry.ObjectHolder("mob_creepoid_death")
	public static final SoundEvent mobCreepoidDeath = null;
	@GameRegistry.ObjectHolder("mob_creepoid_hit")
	public static final SoundEvent mobCreepoidHit = null;
	@GameRegistry.ObjectHolder("mob_creepoid_living")
	public static final SoundEvent mobCreepoidLiving = null;
	@GameRegistry.ObjectHolder("mob_crusilisk_death")
	public static final SoundEvent mobCrusiliskDeath = null;
	@GameRegistry.ObjectHolder("mob_crusilisk_hit")
	public static final SoundEvent mobCrusiliskHit = null;
	@GameRegistry.ObjectHolder("mob_crusilisk_living")
	public static final SoundEvent mobCrusiliskLiving = null;
	@GameRegistry.ObjectHolder("mob_crusilisk_scream")
	public static final SoundEvent mobCrusiliskScream = null;
	@GameRegistry.ObjectHolder("mob_cryptid_death")
	public static final SoundEvent mobCryptidDeath = null;
	@GameRegistry.ObjectHolder("mob_cryptid_hit")
	public static final SoundEvent mobCryptidHit = null;
	@GameRegistry.ObjectHolder("mob_cryptid_living")
	public static final SoundEvent mobCryptidLiving = null;
	@GameRegistry.ObjectHolder("mob_crystal_construct_death")
	public static final SoundEvent mobCrystalConstructDeath = null;
	@GameRegistry.ObjectHolder("mob_crystal_construct_hit")
	public static final SoundEvent mobCrystalConstructHit = null;
	@GameRegistry.ObjectHolder("mob_crystal_construct_living")
	public static final SoundEvent mobCrystalConstructLiving = null;
	@GameRegistry.ObjectHolder("mob_cyclops_death")
	public static final SoundEvent mobCyclopsDeath = null;
	@GameRegistry.ObjectHolder("mob_cyclops_hit")
	public static final SoundEvent mobCyclopsHit = null;
	@GameRegistry.ObjectHolder("mob_cyclops_living")
	public static final SoundEvent mobCyclopsLiving = null;
	@GameRegistry.ObjectHolder("mob_dark_beast_death")
	public static final SoundEvent mobDarkBeastDeath = null;
	@GameRegistry.ObjectHolder("mob_dark_beast_hit")
	public static final SoundEvent mobDarkBeastHit = null;
	@GameRegistry.ObjectHolder("mob_dark_beast_living")
	public static final SoundEvent mobDarkBeastLiving = null;
	@GameRegistry.ObjectHolder("mob_dawnlight_death")
	public static final SoundEvent mobDawnlightDeath = null;
	@GameRegistry.ObjectHolder("mob_dawnlight_hit")
	public static final SoundEvent mobDawnlightHit = null;
	@GameRegistry.ObjectHolder("mob_dawnlight_living")
	public static final SoundEvent mobDawnlightLiving = null;
	@GameRegistry.ObjectHolder("mob_vdeath_hunter_death")
	public static final SoundEvent mobDeathHunterDeath = null;
	@GameRegistry.ObjectHolder("mob_death_hunter_hit")
	public static final SoundEvent mobDeathHunterHit = null;
	@GameRegistry.ObjectHolder("mob_death_hunter_living")
	public static final SoundEvent mobDeathHunterLiving = null;
	@GameRegistry.ObjectHolder("mob_deinotherium_death")
	public static final SoundEvent mobDeinotheriumDeath = null;
	@GameRegistry.ObjectHolder("mob_deinotherium_hit")
	public static final SoundEvent mobDeinotheriumHit = null;
	@GameRegistry.ObjectHolder("mob_deinotherium_living")
	public static final SoundEvent mobDeinotheriumLiving = null;
	@GameRegistry.ObjectHolder("mob_destructor_death")
	public static final SoundEvent mobDestructorDeath = null;
	@GameRegistry.ObjectHolder("mob_destructor_hit")
	public static final SoundEvent mobDestructorHit = null;
	@GameRegistry.ObjectHolder("mob_destructor_living")
	public static final SoundEvent mobDestructorLiving = null;
	@GameRegistry.ObjectHolder("mob_devourer_death")
	public static final SoundEvent mobDevourerDeath = null;
	@GameRegistry.ObjectHolder("mob_devourer_hit")
	public static final SoundEvent mobDevourerHit = null;
	@GameRegistry.ObjectHolder("mob_devourer_living")
	public static final SoundEvent mobDevourerLiving = null;
	@GameRegistry.ObjectHolder("mob_dicer_death")
	public static final SoundEvent mobDicerDeath = null;
	@GameRegistry.ObjectHolder("mob_dicer_hit")
	public static final SoundEvent mobDicerHit = null;
	@GameRegistry.ObjectHolder("mob_dicer_living")
	public static final SoundEvent mobDicerLiving = null;
	@GameRegistry.ObjectHolder("mob_diocus_death")
	public static final SoundEvent mobDiocusDeath = null;
	@GameRegistry.ObjectHolder("mob_diocus_hit")
	public static final SoundEvent mobDiocusHit = null;
	@GameRegistry.ObjectHolder("mob_diocus_living")
	public static final SoundEvent mobDiocusLiving = null;
	@GameRegistry.ObjectHolder("mob_distorter_death")
	public static final SoundEvent mobDistorterDeath = null;
	@GameRegistry.ObjectHolder("mob_distorter_hit")
	public static final SoundEvent mobDistorterHit = null;
	@GameRegistry.ObjectHolder("mob_distorter_living")
	public static final SoundEvent mobDistorterLiving = null;
	@GameRegistry.ObjectHolder("mob_doubler_death")
	public static final SoundEvent mobDoublerDeath = null;
	@GameRegistry.ObjectHolder("mob_doubler_hit")
	public static final SoundEvent mobDoublerHit = null;
	@GameRegistry.ObjectHolder("mob_doubler_living")
	public static final SoundEvent mobDoublerLiving = null;
	@GameRegistry.ObjectHolder("mob_dracyon_death")
	public static final SoundEvent mobDracyonDeath = null;
	@GameRegistry.ObjectHolder("mob_dracyon_living")
	public static final SoundEvent mobDracyonLiving = null;
	@GameRegistry.ObjectHolder("mob_draggy_death")
	public static final SoundEvent mobDraggyDeath = null;
	@GameRegistry.ObjectHolder("mob_draggy_hit")
	public static final SoundEvent mobDraggyHit = null;
	@GameRegistry.ObjectHolder("mob_draggy_living")
	public static final SoundEvent mobDraggyLiving = null;
	@GameRegistry.ObjectHolder("mob_dusteiva_death")
	public static final SoundEvent mobDusteivaDeath = null;
	@GameRegistry.ObjectHolder("mob_dusteiva_hit")
	public static final SoundEvent mobDusteivaHit = null;
	@GameRegistry.ObjectHolder("mob_dusteiva_living")
	public static final SoundEvent mobDusteivaLiving = null;
	@GameRegistry.ObjectHolder("mob_duston_hit")
	public static final SoundEvent mobDustonHit = null;
	@GameRegistry.ObjectHolder("mob_dust_strider_death")
	public static final SoundEvent mobDustStriderDeath = null;
	@GameRegistry.ObjectHolder("mob_dust_strider_hit")
	public static final SoundEvent mobDustStriderHit = null;
	@GameRegistry.ObjectHolder("mob_dust_strider_living")
	public static final SoundEvent mobDustStriderLiving = null;
	@GameRegistry.ObjectHolder("mob_dyrehorn_death")
	public static final SoundEvent mobDyrehornDeath = null;
	@GameRegistry.ObjectHolder("mob_dyrehorn_hit")
	public static final SoundEvent mobDyrehornHit = null;
	@GameRegistry.ObjectHolder("mob_dyrehorn_living")
	public static final SoundEvent mobDyrehornLiving = null;
	@GameRegistry.ObjectHolder("mob_echodar_death")
	public static final SoundEvent mobEchodarDeath = null;
	@GameRegistry.ObjectHolder("mob_echodar_hit")
	public static final SoundEvent mobEchodarHit = null;
	@GameRegistry.ObjectHolder("mob_echodar_living")
	public static final SoundEvent mobEchodarLiving = null;
	@GameRegistry.ObjectHolder("mob_eilosapien_death")
	public static final SoundEvent mobEilosapienDeath = null;
	@GameRegistry.ObjectHolder("mob_eilosapien_hit")
	public static final SoundEvent mobEilosapienHit = null;
	@GameRegistry.ObjectHolder("mob_eilosapien_living")
	public static final SoundEvent mobEilosapienLiving = null;
	@GameRegistry.ObjectHolder("mob_elkanyne_death")
	public static final SoundEvent mobElkanyneDeath = null;
	@GameRegistry.ObjectHolder("mob_elkanyne_hit")
	public static final SoundEvent mobElkanyneHit = null;
	@GameRegistry.ObjectHolder("mob_elkanyne_living")
	public static final SoundEvent mobElkanyneLiving = null;
	@GameRegistry.ObjectHolder("mob_elusive_death")
	public static final SoundEvent mobElusiveDeath = null;
	@GameRegistry.ObjectHolder("mob_elusive_hit")
	public static final SoundEvent mobElusiveHit = null;
	@GameRegistry.ObjectHolder("mob_elusive_living")
	public static final SoundEvent mobElusiveLiving = null;
	@GameRegistry.ObjectHolder("mob_embrake_death")
	public static final SoundEvent mobEmbrakeDeath = null;
	@GameRegistry.ObjectHolder("mob_embrake_hit")
	public static final SoundEvent mobEmbrakeHit = null;
	@GameRegistry.ObjectHolder("mob_embrake_living")
	public static final SoundEvent mobEmbrakeLiving = null;
	@GameRegistry.ObjectHolder("mob_emperor_beast_death")
	public static final SoundEvent mobEmperorBeastDeath = null;
	@GameRegistry.ObjectHolder("mob_emperor_beast_hit")
	public static final SoundEvent mobEmperorBeastHit = null;
	@GameRegistry.ObjectHolder("mob_emperor_beast_living")
	public static final SoundEvent mobEmperorBeastLiving = null;
	@GameRegistry.ObjectHolder("mob_emperor_beast_step")
	public static final SoundEvent mobEmperorBeastStep = null;
	@GameRegistry.ObjectHolder("mob_enforcer_death")
	public static final SoundEvent mobEnforcerDeath = null;
	@GameRegistry.ObjectHolder("mob_enforcer_hit")
	public static final SoundEvent mobEnforcerHit = null;
	@GameRegistry.ObjectHolder("mob_enforcer_living")
	public static final SoundEvent mobEnforcerLiving = null;
	@GameRegistry.ObjectHolder("mob_everbeast_hit")
	public static final SoundEvent mobEverbeastHit = null;
	@GameRegistry.ObjectHolder("mob_everbeast_living")
	public static final SoundEvent mobEverbeastLiving = null;
	@GameRegistry.ObjectHolder("mob_exohead_death")
	public static final SoundEvent mobExoheadDeath = null;
	@GameRegistry.ObjectHolder("mob_exohead_hit")
	public static final SoundEvent mobExoheadHit = null;
	@GameRegistry.ObjectHolder("mob_exohead_living")
	public static final SoundEvent mobExoheadLiving = null;
	@GameRegistry.ObjectHolder("mob_explodot_death")
	public static final SoundEvent mobExplodotDeath = null;
	@GameRegistry.ObjectHolder("mob_explodot_hit")
	public static final SoundEvent mobExplodotHit = null;
	@GameRegistry.ObjectHolder("mob_explodot_living")
	public static final SoundEvent mobExplodotLiving = null;
	@GameRegistry.ObjectHolder("mob_eye_creature_death")
	public static final SoundEvent mobEyeCreatureDeath = null;
	@GameRegistry.ObjectHolder("mob_eye_creature_hit")
	public static final SoundEvent mobEyeCreatureHit = null;
	@GameRegistry.ObjectHolder("mob_eye_creature_living")
	public static final SoundEvent mobEyeCreatureLiving = null;
	@GameRegistry.ObjectHolder("mob_faceless_runner_death")
	public static final SoundEvent mobFacelessRunnerDeath = null;
	@GameRegistry.ObjectHolder("mob_faceless_runner_hit")
	public static final SoundEvent mobFacelessRunnerHit = null;
	@GameRegistry.ObjectHolder("mob_faceless_runner_living")
	public static final SoundEvent mobFacelessRunnerLiving = null;
	@GameRegistry.ObjectHolder("mob_fenix_death")
	public static final SoundEvent mobFenixDeath = null;
	@GameRegistry.ObjectHolder("mob_fenix_hit")
	public static final SoundEvent mobFenixHit = null;
	@GameRegistry.ObjectHolder("mob_fenix_living")
	public static final SoundEvent mobFenixLiving = null;
	@GameRegistry.ObjectHolder("mob_fiend_death")
	public static final SoundEvent mobFiendDeath = null;
	@GameRegistry.ObjectHolder("mob_fiend_hit")
	public static final SoundEvent mobFiendHit = null;
	@GameRegistry.ObjectHolder("mob_fiend_living")
	public static final SoundEvent mobFiendLiving = null;
	@GameRegistry.ObjectHolder("mob_fishix_death")
	public static final SoundEvent mobFishixDeath = null;
	@GameRegistry.ObjectHolder("mob_fishix_hit")
	public static final SoundEvent mobFishixHit = null;
	@GameRegistry.ObjectHolder("mob_fishix_living")
	public static final SoundEvent mobFishixLiving = null;
	@GameRegistry.ObjectHolder("mob_flamewalker_death")
	public static final SoundEvent mobFlamewalkerDeath = null;
	@GameRegistry.ObjectHolder("mob_flamewalker_hit")
	public static final SoundEvent mobFlamewalkerHit = null;
	@GameRegistry.ObjectHolder("mob_flamewalker_living")
	public static final SoundEvent mobFlamewalkerLiving = null;
	@GameRegistry.ObjectHolder("mob_flesh_eater_death")
	public static final SoundEvent mobFleshEaterDeath = null;
	@GameRegistry.ObjectHolder("mob_flesh_eater_hit")
	public static final SoundEvent mobFleshEaterHit = null;
	@GameRegistry.ObjectHolder("mob_flesh_eater_living")
	public static final SoundEvent mobFleshEaterLiving = null;
	@GameRegistry.ObjectHolder("mob_flye_death")
	public static final SoundEvent mobFlyeDeath = null;
	@GameRegistry.ObjectHolder("mob_flye_hit")
	public static final SoundEvent mobFlyeHit = null;
	@GameRegistry.ObjectHolder("mob_flye_living")
	public static final SoundEvent mobFlyeLiving = null;
	@GameRegistry.ObjectHolder("mob_fungi_death")
	public static final SoundEvent mobFungiDeath = null;
	@GameRegistry.ObjectHolder("mob_fungi_hit")
	public static final SoundEvent mobFungiHit = null;
	@GameRegistry.ObjectHolder("mob_fungi_living")
	public static final SoundEvent mobFungiLiving = null;
	@GameRegistry.ObjectHolder("mob_furlion_death")
	public static final SoundEvent mobFurlionDeath = null;
	@GameRegistry.ObjectHolder("mob_furlion_hit")
	public static final SoundEvent mobFurlionHit = null;
	@GameRegistry.ObjectHolder("mob_furlion_living")
	public static final SoundEvent mobFurlionLiving = null;
	@GameRegistry.ObjectHolder("mob_gadgetoid_death")
	public static final SoundEvent mobGadgetoidDeath = null;
	@GameRegistry.ObjectHolder("mob_gadgetoid_hit")
	public static final SoundEvent mobGadgetoidHit = null;
	@GameRegistry.ObjectHolder("mob_gadgetoid_living")
	public static final SoundEvent mobGadgetoidLiving = null;
	@GameRegistry.ObjectHolder("mob_ghost_death")
	public static final SoundEvent mobGhostDeath = null;
	@GameRegistry.ObjectHolder("mob_ghost_hit")
	public static final SoundEvent mobGhostHit = null;
	@GameRegistry.ObjectHolder("mob_ghostine_death")
	public static final SoundEvent mobGhostineDeath = null;
	@GameRegistry.ObjectHolder("mob_ghostine_hit")
	public static final SoundEvent mobGhostineHit = null;
	@GameRegistry.ObjectHolder("mob_ghostine_living")
	public static final SoundEvent mobGhostineLiving = null;
	@GameRegistry.ObjectHolder("mob_ghost_living")
	public static final SoundEvent mobGhostLiving = null;
	@GameRegistry.ObjectHolder("mob_giant_death")
	public static final SoundEvent mobGiantDeath = null;
	@GameRegistry.ObjectHolder("mob_giant_hit")
	public static final SoundEvent mobGiantHit = null;
	@GameRegistry.ObjectHolder("mob_giant_snail_death")
	public static final SoundEvent mobGiantSnailDeath = null;
	@GameRegistry.ObjectHolder("mob_giant_snail_hit")
	public static final SoundEvent mobGiantSnailHit = null;
	@GameRegistry.ObjectHolder("mob_giant_snail_living")
	public static final SoundEvent mobGiantSnailLiving = null;
	@GameRegistry.ObjectHolder("mob_giant_snail_step")
	public static final SoundEvent mobGiantSnailStep = null;
	@GameRegistry.ObjectHolder("mob_goalby_death")
	public static final SoundEvent mobGoalbyDeath = null;
	@GameRegistry.ObjectHolder("mob_goalby_hit")
	public static final SoundEvent mobGoalbyHit = null;
	@GameRegistry.ObjectHolder("mob_goalby_living")
	public static final SoundEvent mobGoalbyLiving = null;
	@GameRegistry.ObjectHolder("mob_goblin_death")
	public static final SoundEvent mobGoblinDeath = null;
	@GameRegistry.ObjectHolder("mob_goblin_hit")
	public static final SoundEvent mobGoblinHit = null;
	@GameRegistry.ObjectHolder("mob_goblin_living")
	public static final SoundEvent mobGoblinLiving = null;
	@GameRegistry.ObjectHolder("mob_goldorth_death")
	public static final SoundEvent mobGoldorthDeath = null;
	@GameRegistry.ObjectHolder("mob_goldorth_hit")
	public static final SoundEvent mobGoldorthHit = null;
	@GameRegistry.ObjectHolder("mob_goldorth_living")
	public static final SoundEvent mobGoldorthLiving = null;
	@GameRegistry.ObjectHolder("mob_golem_step")
	public static final SoundEvent mobGolemStep = null;
	@GameRegistry.ObjectHolder("mob_graw_death")
	public static final SoundEvent mobGrawDeath = null;
	@GameRegistry.ObjectHolder("mob_graw_hit")
	public static final SoundEvent mobGrawHit = null;
	@GameRegistry.ObjectHolder("mob_graw_living")
	public static final SoundEvent mobGrawLiving = null;
	@GameRegistry.ObjectHolder("mob_grillface_death")
	public static final SoundEvent mobGrillfaceDeath = null;
	@GameRegistry.ObjectHolder("mob_grillface_hit")
	public static final SoundEvent mobGrillfaceHit = null;
	@GameRegistry.ObjectHolder("mob_grillface_living")
	public static final SoundEvent mobGrillfaceLiving = null;
	@GameRegistry.ObjectHolder("mob_grillface_scare")
	public static final SoundEvent mobGrillfaceScare = null;
	@GameRegistry.ObjectHolder("mob_grobbler_death")
	public static final SoundEvent mobGrobblerDeath = null;
	@GameRegistry.ObjectHolder("mob_grobbler_hit")
	public static final SoundEvent mobGrobblerHit = null;
	@GameRegistry.ObjectHolder("mob_grobbler_living")
	public static final SoundEvent mobGrobblerLiving = null;
	@GameRegistry.ObjectHolder("mob_grocculate_death")
	public static final SoundEvent mobGrocculateDeath = null;
	@GameRegistry.ObjectHolder("mob_grocculate_hit")
	public static final SoundEvent mobGrocculateHit = null;
	@GameRegistry.ObjectHolder("mob_grocculate_living")
	public static final SoundEvent mobGrocculateLiving = null;
	@GameRegistry.ObjectHolder("mob_grunt_death")
	public static final SoundEvent mobGruntDeath = null;
	@GameRegistry.ObjectHolder("mob_grunt_hit")
	public static final SoundEvent mobGruntHit = null;
	@GameRegistry.ObjectHolder("mob_grunt_living")
	public static final SoundEvent mobGruntLiving = null;
	@GameRegistry.ObjectHolder("mob_guardian_death")
	public static final SoundEvent mobGuardianDeath = null;
	@GameRegistry.ObjectHolder("mob_guardian_hit")
	public static final SoundEvent mobGuardianHit = null;
	@GameRegistry.ObjectHolder("mob_gyro_death")
	public static final SoundEvent mobGyroDeath = null;
	@GameRegistry.ObjectHolder("mob_gyro_hit")
	public static final SoundEvent mobGyroHit = null;
	@GameRegistry.ObjectHolder("mob_gyro_living")
	public static final SoundEvent mobGyroLiving = null;
	@GameRegistry.ObjectHolder("mob_hag_death")
	public static final SoundEvent mobHagDeath = null;
	@GameRegistry.ObjectHolder("mob_hag_hit")
	public static final SoundEvent mobHagHit = null;
	@GameRegistry.ObjectHolder("mob_hag_living")
	public static final SoundEvent mobHagLiving = null;
	@GameRegistry.ObjectHolder("mob_hellcat_death")
	public static final SoundEvent mobHellcatDeath = null;
	@GameRegistry.ObjectHolder("mob_hellcat_hit")
	public static final SoundEvent mobHellcatHit = null;
	@GameRegistry.ObjectHolder("mob_hellcat_living")
	public static final SoundEvent mobHellcatLiving = null;
	@GameRegistry.ObjectHolder("mob_hellspot_death")
	public static final SoundEvent mobHellspotDeath = null;
	@GameRegistry.ObjectHolder("mob_hellspot_hit")
	public static final SoundEvent mobHellspotHit = null;
	@GameRegistry.ObjectHolder("mob_hellspot_living")
	public static final SoundEvent mobHellspotLiving = null;
	@GameRegistry.ObjectHolder("mob_hive_king_death")
	public static final SoundEvent mobHiveKingDeath = null;
	@GameRegistry.ObjectHolder("mob_hive_king_living")
	public static final SoundEvent mobHiveKingLiving = null;
	@GameRegistry.ObjectHolder("mob_horndron_death")
	public static final SoundEvent mobHorndronDeath = null;
	@GameRegistry.ObjectHolder("mob_horndron_hit")
	public static final SoundEvent mobHorndronHit = null;
	@GameRegistry.ObjectHolder("mob_horndron_living")
	public static final SoundEvent mobHorndronLiving = null;
	@GameRegistry.ObjectHolder("mob_horon_death")
	public static final SoundEvent mobHoronDeath = null;
	@GameRegistry.ObjectHolder("mob_horon_hit")
	public static final SoundEvent mobHoronHit = null;
	@GameRegistry.ObjectHolder("mob_horon_living")
	public static final SoundEvent mobHoronLiving = null;
	@GameRegistry.ObjectHolder("mob_host_death")
	public static final SoundEvent mobHostDeath = null;
	@GameRegistry.ObjectHolder("mob_host_drop")
	public static final SoundEvent mobHostDrop = null;
	@GameRegistry.ObjectHolder("mob_host_living")
	public static final SoundEvent mobHostLiving = null;
	@GameRegistry.ObjectHolder("mob_hunch_death")
	public static final SoundEvent mobHunchDeath = null;
	@GameRegistry.ObjectHolder("mob_hunch_hit")
	public static final SoundEvent mobHunchHit = null;
	@GameRegistry.ObjectHolder("mob_hunch_living")
	public static final SoundEvent mobHunchLiving = null;
	@GameRegistry.ObjectHolder("mob_hunter_death")
	public static final SoundEvent mobHunterDeath = null;
	@GameRegistry.ObjectHolder("mob_hunter_hit")
	public static final SoundEvent mobHunterHit = null;
	@GameRegistry.ObjectHolder("mob_hunter_living")
	public static final SoundEvent mobHunterLiving = null;
	@GameRegistry.ObjectHolder("mob_hydrolisk_death")
	public static final SoundEvent mobHydroliskDeath = null;
	@GameRegistry.ObjectHolder("mob_hydrolisk_hit")
	public static final SoundEvent mobHydroliskHit = null;
	@GameRegistry.ObjectHolder("mob_hydrolisk_living")
	public static final SoundEvent mobHydroliskLiving = null;
	@GameRegistry.ObjectHolder("mob_immortal_death")
	public static final SoundEvent mobImmortalDeath = null;
	@GameRegistry.ObjectHolder("mob_immortal_living")
	public static final SoundEvent mobImmortalLiving = null;
	@GameRegistry.ObjectHolder("mob_infernal_hit")
	public static final SoundEvent mobInfernalHit = null;
	@GameRegistry.ObjectHolder("mob_infernal_living")
	public static final SoundEvent mobInfernalLiving = null;
	@GameRegistry.ObjectHolder("mob_iosaur_death")
	public static final SoundEvent mobIosaurDeath = null;
	@GameRegistry.ObjectHolder("mob_iosaur_hit")
	public static final SoundEvent mobIosaurHit = null;
	@GameRegistry.ObjectHolder("mob_iosaur_living")
	public static final SoundEvent mobIosaurLiving = null;
	@GameRegistry.ObjectHolder("mob_irkling_death")
	public static final SoundEvent mobIrklingDeath = null;
	@GameRegistry.ObjectHolder("mob_irkling_hit")
	public static final SoundEvent mobIrklingHit = null;
	@GameRegistry.ObjectHolder("mob_irkling_living")
	public static final SoundEvent mobIrklingLiving = null;
	@GameRegistry.ObjectHolder("mob_jawe_death")
	public static final SoundEvent mobJaweDeath = null;
	@GameRegistry.ObjectHolder("mob_jawe_hit")
	public static final SoundEvent mobJaweHit = null;
	@GameRegistry.ObjectHolder("mob_jawe_living")
	public static final SoundEvent mobJaweLiving = null;
	@GameRegistry.ObjectHolder("mob_jumbo_living")
	public static final SoundEvent mobJumboLiving = null;
	@GameRegistry.ObjectHolder("mob_kaiyu_death")
	public static final SoundEvent mobKaiyuDeath = null;
	@GameRegistry.ObjectHolder("mob_kaiyu_hit")
	public static final SoundEvent mobKaiyuHit = null;
	@GameRegistry.ObjectHolder("mob_kaiyu_living")
	public static final SoundEvent mobKaiyuLiving = null;
	@GameRegistry.ObjectHolder("mob_keeler_death")
	public static final SoundEvent mobKeelerDeath = null;
	@GameRegistry.ObjectHolder("mob_keeler_hit")
	public static final SoundEvent mobKeelerHit = null;
	@GameRegistry.ObjectHolder("mob_keeler_living")
	public static final SoundEvent mobKeelerLiving = null;
	@GameRegistry.ObjectHolder("mob_keeler_revive")
	public static final SoundEvent mobKeelerRevive = null;
	@GameRegistry.ObjectHolder("mob_king_bambambam_death")
	public static final SoundEvent mobKingBamBamBamDeath = null;
	@GameRegistry.ObjectHolder("mob_king_bambambam_hit")
	public static final SoundEvent mobKingBamBamBamHit = null;
	@GameRegistry.ObjectHolder("mob_king_bambambam_living")
	public static final SoundEvent mobKingBamBamBamLiving = null;
	@GameRegistry.ObjectHolder("mob_king_shroomus_death")
	public static final SoundEvent mobKingShroomusDeath = null;
	@GameRegistry.ObjectHolder("mob_king_shroomus_heal")
	public static final SoundEvent mobKingShroomusHeal = null;
	@GameRegistry.ObjectHolder("mob_kror_death")
	public static final SoundEvent mobKrorDeath = null;
	@GameRegistry.ObjectHolder("mob_kror_living")
	public static final SoundEvent mobKrorLiving = null;
	@GameRegistry.ObjectHolder("mob_lelyetian_death")
	public static final SoundEvent mobLelyetianDeath = null;
	@GameRegistry.ObjectHolder("mob_lelyetian_hit")
	public static final SoundEvent mobLelyetianHit = null;
	@GameRegistry.ObjectHolder("mob_lelyetian_living")
	public static final SoundEvent mobLelyetianLiving = null;
	@GameRegistry.ObjectHolder("mob_linger_death")
	public static final SoundEvent mobLingerDeath = null;
	@GameRegistry.ObjectHolder("mob_linger_hit")
	public static final SoundEvent mobLingerHit = null;
	@GameRegistry.ObjectHolder("mob_linger_living")
	public static final SoundEvent mobLingerLiving = null;
	@GameRegistry.ObjectHolder("mob_little_bam_spawn")
	public static final SoundEvent mobLittleBamSpawn = null;
	@GameRegistry.ObjectHolder("mob_living_fungi_spawn")
	public static final SoundEvent mobLivingFungiSpawn = null;
	@GameRegistry.ObjectHolder("mob_lollypopper_death")
	public static final SoundEvent mobLollypopperDeath = null;
	@GameRegistry.ObjectHolder("mob_lost_soul_death")
	public static final SoundEvent mobLostSoulDeath = null;
	@GameRegistry.ObjectHolder("mob_lost_soul_hit")
	public static final SoundEvent mobLostSoulHit = null;
	@GameRegistry.ObjectHolder("mob_lost_soul_living")
	public static final SoundEvent mobLostSoulLiving = null;
	@GameRegistry.ObjectHolder("mob_lunarcher_death")
	public static final SoundEvent mobLunarcherDeath = null;
	@GameRegistry.ObjectHolder("mob_lunarcher_hit")
	public static final SoundEvent mobLunarcherHit = null;
	@GameRegistry.ObjectHolder("mob_lunarcher_living")
	public static final SoundEvent mobLunarcherLiving = null;
	@GameRegistry.ObjectHolder("mob_lurker_death")
	public static final SoundEvent mobLurkerDeath = null;
	@GameRegistry.ObjectHolder("mob_lurker_hit")
	public static final SoundEvent mobLurkerHit = null;
	@GameRegistry.ObjectHolder("mob_lurker_living")
	public static final SoundEvent mobLurkerLiving = null;
	@GameRegistry.ObjectHolder("mob_luxocron_death")
	public static final SoundEvent mobLuxocronDeath = null;
	@GameRegistry.ObjectHolder("mob_luxocron_hit")
	public static final SoundEvent mobLuxocronHit = null;
	@GameRegistry.ObjectHolder("mob_luxocron_living")
	public static final SoundEvent mobLuxocronLiving = null;
	@GameRegistry.ObjectHolder("mob_magicke_death")
	public static final SoundEvent mobMagickeDeath = null;
	@GameRegistry.ObjectHolder("mob_magicke_hit")
	public static final SoundEvent mobMagickeHit = null;
	@GameRegistry.ObjectHolder("mob_magicke_living")
	public static final SoundEvent mobMagickeLiving = null;
	@GameRegistry.ObjectHolder("mob_mechachron_death")
	public static final SoundEvent mobMechachronDeath = null;
	@GameRegistry.ObjectHolder("mob_mechachron_hit")
	public static final SoundEvent mobMechachronHit = null;
	@GameRegistry.ObjectHolder("mob_mechachron_living")
	public static final SoundEvent mobMechachronLiving = null;
	@GameRegistry.ObjectHolder("mob_mechbot_jump")
	public static final SoundEvent mobMechbotJump = null;
	@GameRegistry.ObjectHolder("mob_mechyon_death")
	public static final SoundEvent mobMechyonDeath = null;
	@GameRegistry.ObjectHolder("mob_mechyon_hit")
	public static final SoundEvent mobMechyonHit = null;
	@GameRegistry.ObjectHolder("mob_mechyon_living")
	public static final SoundEvent mobMechyonLiving = null;
	@GameRegistry.ObjectHolder("mob_merkyre_death")
	public static final SoundEvent mobMerkyreDeath = null;
	@GameRegistry.ObjectHolder("mob_merkyre_hit")
	public static final SoundEvent mobMerkyreHit = null;
	@GameRegistry.ObjectHolder("mob_merkyre_living")
	public static final SoundEvent mobMerkyreLiving = null;
	@GameRegistry.ObjectHolder("mob_mermage_death")
	public static final SoundEvent mobMermageDeath = null;
	@GameRegistry.ObjectHolder("mob_mermage_hit")
	public static final SoundEvent mobMermageHit = null;
	@GameRegistry.ObjectHolder("mob_mermage_living")
	public static final SoundEvent mobMermageLiving = null;
	@GameRegistry.ObjectHolder("mob_mirage_teleport")
	public static final SoundEvent mobMirageTeleport = null;
	@GameRegistry.ObjectHolder("mob_modulo_death")
	public static final SoundEvent mobModuloDeath = null;
	@GameRegistry.ObjectHolder("mob_modulo_hit")
	public static final SoundEvent mobModuloHit = null;
	@GameRegistry.ObjectHolder("mob_modulo_living")
	public static final SoundEvent mobModuloLiving = null;
	@GameRegistry.ObjectHolder("mob_mother_void_walker_death")
	public static final SoundEvent mobMotherVoidWalkerDeath = null;
	@GameRegistry.ObjectHolder("mob_mother_void_walker_hit")
	public static final SoundEvent mobMotherVoidWalkerHit = null;
	@GameRegistry.ObjectHolder("mob_mother_void_walker_living")
	public static final SoundEvent mobMotherVoidWalkerLiving = null;
	@GameRegistry.ObjectHolder("mob_muckopede_hit")
	public static final SoundEvent mobMuckopedeHit = null;
	@GameRegistry.ObjectHolder("mob_muckopede_living")
	public static final SoundEvent mobMuckopedeLiving = null;
	@GameRegistry.ObjectHolder("mob_muncher_death")
	public static final SoundEvent mobMuncherDeath = null;
	@GameRegistry.ObjectHolder("mob_muncher_hit")
	public static final SoundEvent mobMuncherHit = null;
	@GameRegistry.ObjectHolder("mob_muncher_living")
	public static final SoundEvent mobMuncherLiving = null;
	@GameRegistry.ObjectHolder("mob_natura_death")
	public static final SoundEvent mobNaturaDeath = null;
	@GameRegistry.ObjectHolder("mob_natura_hit")
	public static final SoundEvent mobNaturaHit = null;
	@GameRegistry.ObjectHolder("mob_natura_living")
	public static final SoundEvent mobNaturaLiving = null;
	@GameRegistry.ObjectHolder("mob_neptuno_death")
	public static final SoundEvent mobNeptunoDeath = null;
	@GameRegistry.ObjectHolder("mob_neptuno_hit")
	public static final SoundEvent mobNeptunoHit = null;
	@GameRegistry.ObjectHolder("mob_neptuno_living")
	public static final SoundEvent mobNeptunoLiving = null;
	@GameRegistry.ObjectHolder("mob_nethengeic_beast_death")
	public static final SoundEvent mobNethengeicBeastDeath = null;
	@GameRegistry.ObjectHolder("mob_nethengeic_beast_hit")
	public static final SoundEvent mobNethengeicBeastHit = null;
	@GameRegistry.ObjectHolder("mob_nethengeic_beast_living")
	public static final SoundEvent mobNethengeicBeastLiving = null;
	@GameRegistry.ObjectHolder("mob_nethengeic_wither_death")
	public static final SoundEvent mobNethengeicWitherDeath = null;
	@GameRegistry.ObjectHolder("mob_nethengeic_wither_hit")
	public static final SoundEvent mobNethengeicWitherHit = null;
	@GameRegistry.ObjectHolder("mob_nethengeic_wither_living")
	public static final SoundEvent mobNethengeicWitherLiving = null;
	@GameRegistry.ObjectHolder("mob_nightfly_death")
	public static final SoundEvent mobNightflyDeath = null;
	@GameRegistry.ObjectHolder("mob_nightfly_hit")
	public static final SoundEvent mobNightflyHit = null;
	@GameRegistry.ObjectHolder("mob_nightfly_living")
	public static final SoundEvent mobNightflyLiving = null;
	@GameRegistry.ObjectHolder("mob_nightmare_spider_death")
	public static final SoundEvent mobNightmareSpiderDeath = null;
	@GameRegistry.ObjectHolder("mob_nightmare_spider_hit")
	public static final SoundEvent mobNightmareSpiderHit = null;
	@GameRegistry.ObjectHolder("mob_nightmare_spider_living")
	public static final SoundEvent mobNightmareSpiderLiving = null;
	@GameRegistry.ObjectHolder("night_watcher_hit")
	public static final SoundEvent mobNightWatcherHit = null;
	@GameRegistry.ObjectHolder("night_watcher_living")
	public static final SoundEvent mobNightWatcherLiving = null;
	@GameRegistry.ObjectHolder("mob_nightwing_death")
	public static final SoundEvent mobNightwingDeath = null;
	@GameRegistry.ObjectHolder("mob_nightwing_hit")
	public static final SoundEvent mobNightwingHit = null;
	@GameRegistry.ObjectHolder("mob_nightwing_living")
	public static final SoundEvent mobNightwingLiving = null;
	@GameRegistry.ObjectHolder("mob_nipper_death")
	public static final SoundEvent mobNipperDeath = null;
	@GameRegistry.ObjectHolder("mob_nipper_hit")
	public static final SoundEvent mobNipperHit = null;
	@GameRegistry.ObjectHolder("mob_nipper_living")
	public static final SoundEvent mobNipperLiving = null;
	@GameRegistry.ObjectHolder("mob_nospike_death")
	public static final SoundEvent mobNospikeDeath = null;
	@GameRegistry.ObjectHolder("mob_nospike_hit")
	public static final SoundEvent mobNospikeHit = null;
	@GameRegistry.ObjectHolder("mob_nospike_living")
	public static final SoundEvent mobNospikeLiving = null;
	@GameRegistry.ObjectHolder("mob_occulent_death")
	public static final SoundEvent mobOcculentDeath = null;
	@GameRegistry.ObjectHolder("mob_occulent_hit")
	public static final SoundEvent mobOcculentHit = null;
	@GameRegistry.ObjectHolder("mob_occulent_living")
	public static final SoundEvent mobOcculentLiving = null;
	@GameRegistry.ObjectHolder("mob_omnilight_death")
	public static final SoundEvent mobOmnilightDeath = null;
	@GameRegistry.ObjectHolder("mob_omnilight_hit")
	public static final SoundEvent mobOmnilightHit = null;
	@GameRegistry.ObjectHolder("mob_omnilight_living")
	public static final SoundEvent mobOmnilightLiving = null;
	@GameRegistry.ObjectHolder("mob_orbiter_death")
	public static final SoundEvent mobOrbiterDeath = null;
	@GameRegistry.ObjectHolder("mob_orbiter_hit")
	public static final SoundEvent mobOrbiterHit = null;
	@GameRegistry.ObjectHolder("mob_orbiter_living")
	public static final SoundEvent mobOrbiterLiving = null;
	@GameRegistry.ObjectHolder("mob_parasect_death")
	public static final SoundEvent mobParasectDeath = null;
	@GameRegistry.ObjectHolder("mob_parasect_hit")
	public static final SoundEvent mobParasectHit = null;
	@GameRegistry.ObjectHolder("mob_parasect_living")
	public static final SoundEvent mobParasectLiving = null;
	@GameRegistry.ObjectHolder("mob_paravite_death")
	public static final SoundEvent mobParaviteDeath = null;
	@GameRegistry.ObjectHolder("mob_paravite_hit")
	public static final SoundEvent mobParaviteHit = null;
	@GameRegistry.ObjectHolder("mob_paravite_living")
	public static final SoundEvent mobParaviteLiving = null;
	@GameRegistry.ObjectHolder("mob_penguin_death")
	public static final SoundEvent mobPenguinDeath = null;
	@GameRegistry.ObjectHolder("mob_penguin_hit")
	public static final SoundEvent mobPenguinHit = null;
	@GameRegistry.ObjectHolder("mob_penguin_living")
	public static final SoundEvent mobPenguinLiving = null;
	@GameRegistry.ObjectHolder("mob_penumbra_death")
	public static final SoundEvent mobPenumbraDeath = null;
	@GameRegistry.ObjectHolder("mob_penumbra_hit")
	public static final SoundEvent mobPenumbraHit = null;
	@GameRegistry.ObjectHolder("mob_penumbra_living")
	public static final SoundEvent mobPenumbraLiving = null;
	@GameRegistry.ObjectHolder("mob_phantom_hit")
	public static final SoundEvent mobPhantomHit = null;
	@GameRegistry.ObjectHolder("mob_phantom_living")
	public static final SoundEvent mobPhantomLiving = null;
	@GameRegistry.ObjectHolder("mob_pigotron_appear")
	public static final SoundEvent mobPigotronAppear = null;
	@GameRegistry.ObjectHolder("mob_pigotron_death")
	public static final SoundEvent mobPigotronDeath = null;
	@GameRegistry.ObjectHolder("mob_pigotron_hit")
	public static final SoundEvent mobPigotronHit = null;
	@GameRegistry.ObjectHolder("mob_pigotron_living")
	public static final SoundEvent mobPigotronLiving = null;
	@GameRegistry.ObjectHolder("mob_pincher_death")
	public static final SoundEvent mobPincherDeath = null;
	@GameRegistry.ObjectHolder("mob_pincher_hit")
	public static final SoundEvent mobPincherHit = null;
	@GameRegistry.ObjectHolder("mob_pincher_living")
	public static final SoundEvent mobPincherLiving = null;
	@GameRegistry.ObjectHolder("mob_pod_plant_death")
	public static final SoundEvent mobPodPlantDeath = null;
	@GameRegistry.ObjectHolder("mob_pod_plant_hit")
	public static final SoundEvent mobPodPlantHit = null;
	@GameRegistry.ObjectHolder("mob_pod_plant_living")
	public static final SoundEvent mobPodPlantLiving = null;
	@GameRegistry.ObjectHolder("mob_polytom_death")
	public static final SoundEvent mobPolytomDeath = null;
	@GameRegistry.ObjectHolder("mob_polytom_hit")
	public static final SoundEvent mobPolytomHit = null;
	@GameRegistry.ObjectHolder("mob_polytom_living")
	public static final SoundEvent mobPolytomLiving = null;
	@GameRegistry.ObjectHolder("mob_primordial_death")
	public static final SoundEvent mobPrimordialDeath = null;
	@GameRegistry.ObjectHolder("mob_primordial_living")
	public static final SoundEvent mobPrimordialLiving = null;
	@GameRegistry.ObjectHolder("mob_quickpocket_death")
	public static final SoundEvent mobQuickpocketDeath = null;
	@GameRegistry.ObjectHolder("mob_quickpocket_hit")
	public static final SoundEvent mobQuickpocketHit = null;
	@GameRegistry.ObjectHolder("mob_quickpocket_living")
	public static final SoundEvent mobQuickpocketLiving = null;
	@GameRegistry.ObjectHolder("mob_quickpocket_steal")
	public static final SoundEvent mobQuickpocketSteal = null;
	@GameRegistry.ObjectHolder("mob_rainicorn_death")
	public static final SoundEvent mobRainicornDeath = null;
	@GameRegistry.ObjectHolder("mob_rainicorn_hit")
	public static final SoundEvent mobRainicornHit = null;
	@GameRegistry.ObjectHolder("mob_rainicorn_living")
	public static final SoundEvent mobRainicornLiving = null;
	@GameRegistry.ObjectHolder("mob_rammerhead_death")
	public static final SoundEvent mobRammerheadDeath = null;
	@GameRegistry.ObjectHolder("mob_rammerhead_hit")
	public static final SoundEvent mobRammerheadHit = null;
	@GameRegistry.ObjectHolder("mob_rammerhead_living")
	public static final SoundEvent mobRammerheadLiving = null;
	@GameRegistry.ObjectHolder("mob_ramradon_death")
	public static final SoundEvent mobRamradonDeath = null;
	@GameRegistry.ObjectHolder("mob_ramradon_hit")
	public static final SoundEvent mobRamradonHit = null;
	@GameRegistry.ObjectHolder("mob_ramradon_living")
	public static final SoundEvent mobRamradonLiving = null;
	@GameRegistry.ObjectHolder("mob_rawbone_death")
	public static final SoundEvent mobRawboneDeath = null;
	@GameRegistry.ObjectHolder("mob_rawbone_hit")
	public static final SoundEvent mobRawboneHit = null;
	@GameRegistry.ObjectHolder("mob_rawbone_living")
	public static final SoundEvent mobRawboneLiving = null;
	@GameRegistry.ObjectHolder("mob_reaper_death")
	public static final SoundEvent mobReaperDeath = null;
	@GameRegistry.ObjectHolder("mob_reaper_hit")
	public static final SoundEvent mobReaperHit = null;
	@GameRegistry.ObjectHolder("mob_reaper_living")
	public static final SoundEvent mobReaperLiving = null;
	@GameRegistry.ObjectHolder("mob_refluct_death")
	public static final SoundEvent mobRefluctDeath = null;
	@GameRegistry.ObjectHolder("mob_refluct_hit")
	public static final SoundEvent mobRefluctHit = null;
	@GameRegistry.ObjectHolder("mob_refluct_living")
	public static final SoundEvent mobRefluctLiving = null;
	@GameRegistry.ObjectHolder("mob_rockbiter_death")
	public static final SoundEvent mobRockbiterDeath = null;
	@GameRegistry.ObjectHolder("mob_rockbiter_hit")
	public static final SoundEvent mobRockbiterHit = null;
	@GameRegistry.ObjectHolder("mob_rockbiter_living")
	public static final SoundEvent mobRockbiterLiving = null;
	@GameRegistry.ObjectHolder("mob_rock_rider_death")
	public static final SoundEvent mobRockRiderDeath = null;
	@GameRegistry.ObjectHolder("mob_rock_rider_hit")
	public static final SoundEvent mobRockRiderHit = null;
	@GameRegistry.ObjectHolder("mob_rock_rider_switch")
	public static final SoundEvent mobRockRiderSwitch = null;
	@GameRegistry.ObjectHolder("mob_roloscope_death")
	public static final SoundEvent mobRoloscopeDeath = null;
	@GameRegistry.ObjectHolder("mob_roloscope_hit")
	public static final SoundEvent mobRoloscopeHit = null;
	@GameRegistry.ObjectHolder("mob_roloscope_living")
	public static final SoundEvent mobRoloscopeLiving = null;
	@GameRegistry.ObjectHolder("mob_runic_golem_change")
	public static final SoundEvent mobRunicGolemChange = null;
	@GameRegistry.ObjectHolder("mob_runic_golem_hit")
	public static final SoundEvent mobRunicGolemHit = null;
	@GameRegistry.ObjectHolder("mob_sabretooth_death")
	public static final SoundEvent mobSabretoothDeath = null;
	@GameRegistry.ObjectHolder("mob_sabretooth_hit")
	public static final SoundEvent mobSabretoothHit = null;
	@GameRegistry.ObjectHolder("mob_sabretooth_living")
	public static final SoundEvent mobSabretoothLiving = null;
	@GameRegistry.ObjectHolder("mob_sasquatch_living")
	public static final SoundEvent mobSasquatchLiving = null;
	@GameRegistry.ObjectHolder("mob_scrubby_hit")
	public static final SoundEvent mobScrubbyHit = null;
	@GameRegistry.ObjectHolder("mob_scrubby_living")
	public static final SoundEvent mobScrubbyLiving = null;
	@GameRegistry.ObjectHolder("mob_sea_viper_death")
	public static final SoundEvent mobSeaViperDeath = null;
	@GameRegistry.ObjectHolder("mob_sea_viper_hit")
	public static final SoundEvent mobSeaViperHit = null;
	@GameRegistry.ObjectHolder("mob_sea_viper_living")
	public static final SoundEvent mobSeaViperLiving = null;
	@GameRegistry.ObjectHolder("mob_seeker_death")
	public static final SoundEvent mobSeekerDeath = null;
	@GameRegistry.ObjectHolder("mob_seeker_hit")
	public static final SoundEvent mobSeekerHit = null;
	@GameRegistry.ObjectHolder("mob_seeker_living")
	public static final SoundEvent mobSeekerLiving = null;
	@GameRegistry.ObjectHolder("mob_shade_death")
	public static final SoundEvent mobShadeDeath = null;
	@GameRegistry.ObjectHolder("mob_shade_hit")
	public static final SoundEvent mobShadeHit = null;
	@GameRegistry.ObjectHolder("mob_shade_living")
	public static final SoundEvent mobShadeLiving = null;
	@GameRegistry.ObjectHolder("mob_shadow_hit")
	public static final SoundEvent mobShadowHit = null;
	@GameRegistry.ObjectHolder("mob_shadow_living")
	public static final SoundEvent mobShadowLiving = null;
	@GameRegistry.ObjectHolder("mob_shadowlord_death")
	public static final SoundEvent mobShadowlordDeath = null;
	@GameRegistry.ObjectHolder("mob_shadowlord_hit")
	public static final SoundEvent mobShadowlordHit = null;
	@GameRegistry.ObjectHolder("mob_shadowlord_living")
	public static final SoundEvent mobShadowlordLiving = null;
	@GameRegistry.ObjectHolder("mob_shifter_death")
	public static final SoundEvent mobShifterDeath = null;
	@GameRegistry.ObjectHolder("mob_shifter_hit")
	public static final SoundEvent mobShifterHit = null;
	@GameRegistry.ObjectHolder("mob_shifter_living")
	public static final SoundEvent mobShifterLiving = null;
	@GameRegistry.ObjectHolder("mob_silencer_death")
	public static final SoundEvent mobSilencerDeath = null;
	@GameRegistry.ObjectHolder("mob_silencer_hit")
	public static final SoundEvent mobSilencerHit = null;
	@GameRegistry.ObjectHolder("mob_silencer_living")
	public static final SoundEvent mobSilencerLiving = null;
	@GameRegistry.ObjectHolder("mob_skeletal_cowman_hit")
	public static final SoundEvent mobSkeletalCowmanHit = null;
	@GameRegistry.ObjectHolder("mob_skeletal_cowman_living")
	public static final SoundEvent mobSkeletalCowmanLiving = null;
	@GameRegistry.ObjectHolder("mob_skeletron_death")
	public static final SoundEvent mobSkeletronDeath = null;
	@GameRegistry.ObjectHolder("mob_skeletron_hit")
	public static final SoundEvent mobSkeletronHit = null;
	@GameRegistry.ObjectHolder("mob_skeletron_living")
	public static final SoundEvent mobSkeletronLiving = null;
	@GameRegistry.ObjectHolder("mob_skellox_death")
	public static final SoundEvent mobSkelloxDeath = null;
	@GameRegistry.ObjectHolder("mob_skellox_hit")
	public static final SoundEvent mobSkelloxHit = null;
	@GameRegistry.ObjectHolder("mob_skellox_living")
	public static final SoundEvent mobSkelloxLiving = null;
	@GameRegistry.ObjectHolder("mob_skipper_death")
	public static final SoundEvent mobSkipperDeath = null;
	@GameRegistry.ObjectHolder("mob_skipper_hit")
	public static final SoundEvent mobSkipperHit = null;
	@GameRegistry.ObjectHolder("mob_skipper_living")
	public static final SoundEvent mobSkipperLiving = null;
	@GameRegistry.ObjectHolder("mob_skull_creature_death")
	public static final SoundEvent mobSkullCreatureDeath = null;
	@GameRegistry.ObjectHolder("mob_skull_creature_hit")
	public static final SoundEvent mobSkullCreatureHit = null;
	@GameRegistry.ObjectHolder("mob_skull_creature_living")
	public static final SoundEvent mobSkullCreatureLiving = null;
	@GameRegistry.ObjectHolder("mob_slimer_death")
	public static final SoundEvent mobSlimerDeath = null;
	@GameRegistry.ObjectHolder("mob_slimer_hit")
	public static final SoundEvent mobSlimerHit = null;
	@GameRegistry.ObjectHolder("mob_slimer_living")
	public static final SoundEvent mobSlimerLiving = null;
	@GameRegistry.ObjectHolder("mob_smash_death")
	public static final SoundEvent mobSmashDeath = null;
	@GameRegistry.ObjectHolder("mob_smash_hit")
	public static final SoundEvent mobSmashHit = null;
	@GameRegistry.ObjectHolder("mob_smash_living")
	public static final SoundEvent mobSmashLiving = null;
	@GameRegistry.ObjectHolder("mob_soulscorne_death")
	public static final SoundEvent mobSoulscorneDeath = null;
	@GameRegistry.ObjectHolder("mob_soulscorne_hit")
	public static final SoundEvent mobSoulscorneHit = null;
	@GameRegistry.ObjectHolder("mob_soulscorne_living")
	public static final SoundEvent mobSoulscorneLiving = null;
	@GameRegistry.ObjectHolder("mob_soulvyre_death")
	public static final SoundEvent mobSoulvyreDeath = null;
	@GameRegistry.ObjectHolder("mob_soulvyre_hit")
	public static final SoundEvent mobSoulvyreHit = null;
	@GameRegistry.ObjectHolder("mob_soulvyre_living")
	public static final SoundEvent mobSoulvyreLiving = null;
	@GameRegistry.ObjectHolder("mob_spectral_wizard_death")
	public static final SoundEvent mobSpectralWizardDeath = null;
	@GameRegistry.ObjectHolder("mob_spectral_wizard_hit")
	public static final SoundEvent mobSpectralWizardHit = null;
	@GameRegistry.ObjectHolder("mob_spectral_wizard_living")
	public static final SoundEvent mobSpectralWizardLiving = null;
	@GameRegistry.ObjectHolder("mob_sphinx_death")
	public static final SoundEvent mobSphinxDeath = null;
	@GameRegistry.ObjectHolder("mob_sphinx_hit")
	public static final SoundEvent mobSphinxHit = null;
	@GameRegistry.ObjectHolder("mob_sphinx_living")
	public static final SoundEvent mobSphinxLiving = null;
	@GameRegistry.ObjectHolder("mob_spinoledon_death")
	public static final SoundEvent mobSpinoledonDeath = null;
	@GameRegistry.ObjectHolder("mob_spinoledon_hit")
	public static final SoundEvent mobSpinoledonHit = null;
	@GameRegistry.ObjectHolder("mob_spinoledon_living")
	public static final SoundEvent mobSpinoledonLiving = null;
	@GameRegistry.ObjectHolder("mob_spinux_death")
	public static final SoundEvent mobSpinuxDeath = null;
	@GameRegistry.ObjectHolder("mob_spinux_hit")
	public static final SoundEvent mobSpinuxHit = null;
	@GameRegistry.ObjectHolder("mob_spinux_living")
	public static final SoundEvent mobSpinuxLiving = null;
	@GameRegistry.ObjectHolder("mob_spirit_death")
	public static final SoundEvent mobSpiritDeath = null;
	@GameRegistry.ObjectHolder("mob_spirit_living")
	public static final SoundEvent mobSpiritLiving = null;
	@GameRegistry.ObjectHolder("mob_squiggler_death")
	public static final SoundEvent mobSquigglerDeath = null;
	@GameRegistry.ObjectHolder("mob_squiggler_hit")
	public static final SoundEvent mobSquigglerHit = null;
	@GameRegistry.ObjectHolder("mob_squiggler_living")
	public static final SoundEvent mobSquigglerLiving = null;
	@GameRegistry.ObjectHolder("mob_stalker_death")
	public static final SoundEvent mobStalkerDeath = null;
	@GameRegistry.ObjectHolder("mob_stalker_hit")
	public static final SoundEvent mobStalkerHit = null;
	@GameRegistry.ObjectHolder("mob_stalker_living")
	public static final SoundEvent mobStalkerLiving = null;
	@GameRegistry.ObjectHolder("mob_stimulo_death")
	public static final SoundEvent mobStimuloDeath = null;
	@GameRegistry.ObjectHolder("mob_stimulo_hit")
	public static final SoundEvent mobStimuloHit = null;
	@GameRegistry.ObjectHolder("mob_stimulo_living")
	public static final SoundEvent mobStimuloLiving = null;
	@GameRegistry.ObjectHolder("mob_stimulosus_living")
	public static final SoundEvent mobStimulosusLiving = null;
	@GameRegistry.ObjectHolder("mob_stinger_hit")
	public static final SoundEvent mobStingerHit = null;
	@GameRegistry.ObjectHolder("mob_stinger_living")
	public static final SoundEvent mobStingerLiving = null;
	@GameRegistry.ObjectHolder("mob_sugarface_death")
	public static final SoundEvent mobSugarfaceDeath = null;
	@GameRegistry.ObjectHolder("mob_sugarface_hit")
	public static final SoundEvent mobSugarfaceHit = null;
	@GameRegistry.ObjectHolder("mob_sugarface_living")
	public static final SoundEvent mobSugarfaceLiving = null;
	@GameRegistry.ObjectHolder("mob_surveyor_death")
	public static final SoundEvent mobSurveyorDeath = null;
	@GameRegistry.ObjectHolder("mob_surveyor_hit")
	public static final SoundEvent mobSurveyorHit = null;
	@GameRegistry.ObjectHolder("mob_surveyor_living")
	public static final SoundEvent mobSurveyorLiving = null;
	@GameRegistry.ObjectHolder("mob_sysker_death")
	public static final SoundEvent mobSyskerDeath = null;
	@GameRegistry.ObjectHolder("mob_sysker_hit")
	public static final SoundEvent mobSyskerHit = null;
	@GameRegistry.ObjectHolder("mob_sysker_living")
	public static final SoundEvent mobSyskerLiving = null;
	@GameRegistry.ObjectHolder("mob_terradon_death")
	public static final SoundEvent mobTerradonDeath = null;
	@GameRegistry.ObjectHolder("mob_terradon_hit")
	public static final SoundEvent mobTerradonHit = null;
	@GameRegistry.ObjectHolder("mob_terradon_living")
	public static final SoundEvent mobTerradonLiving = null;
	@GameRegistry.ObjectHolder("mob_terrestrial_death")
	public static final SoundEvent mobTerrestrialDeath = null;
	@GameRegistry.ObjectHolder("mob_terrestrial_hit")
	public static final SoundEvent mobTerrestrialHit = null;
	@GameRegistry.ObjectHolder("mob_terrestrial_living")
	public static final SoundEvent mobTerrestrialLiving = null;
	@GameRegistry.ObjectHolder("mob_tharafly_death")
	public static final SoundEvent mobTharaflyDeath = null;
	@GameRegistry.ObjectHolder("mob_tharafly_hit")
	public static final SoundEvent mobTharaflyHit = null;
	@GameRegistry.ObjectHolder("mob_tharafly_living")
	public static final SoundEvent mobTharaflyLiving = null;
	@GameRegistry.ObjectHolder("mob_tortione_death")
	public static final SoundEvent mobTortioneDeath = null;
	@GameRegistry.ObjectHolder("mob_tortione_hit")
	public static final SoundEvent mobTortioneHit = null;
	@GameRegistry.ObjectHolder("mob_tortione_living")
	public static final SoundEvent mobTortioneLiving = null;
	@GameRegistry.ObjectHolder("mob_toxxulous_death")
	public static final SoundEvent mobToxxulousDeath = null;
	@GameRegistry.ObjectHolder("mob_toxxulous_hit")
	public static final SoundEvent mobToxxulousHit = null;
	@GameRegistry.ObjectHolder("mob_toxxulous_living")
	public static final SoundEvent mobToxxulousLiving = null;
	@GameRegistry.ObjectHolder("mob_tracker_death")
	public static final SoundEvent mobTrackerDeath = null;
	@GameRegistry.ObjectHolder("mob_tracker_hit")
	public static final SoundEvent mobTrackerHit = null;
	@GameRegistry.ObjectHolder("mob_tracker_living")
	public static final SoundEvent mobTrackerLiving = null;
	@GameRegistry.ObjectHolder("mob_tree_spirit_death")
	public static final SoundEvent mobTreeSpiritDeath = null;
	@GameRegistry.ObjectHolder("mob_tree_spirit_hit")
	public static final SoundEvent mobTreeSpiritHit = null;
	@GameRegistry.ObjectHolder("mob_tree_spirit_living")
	public static final SoundEvent mobTreeSpiritLiving = null;
	@GameRegistry.ObjectHolder("mob_trickster_hide")
	public static final SoundEvent mobTricksterHide = null;
	@GameRegistry.ObjectHolder("mob_trickster_hit")
	public static final SoundEvent mobTricksterHit = null;
	@GameRegistry.ObjectHolder("mob_trickster_living")
	public static final SoundEvent mobTricksterLiving = null;
	@GameRegistry.ObjectHolder("mob_trotter_death")
	public static final SoundEvent mobTrotterDeath = null;
	@GameRegistry.ObjectHolder("mob_trotter_hit")
	public static final SoundEvent mobTrotterHit = null;
	@GameRegistry.ObjectHolder("mob_trotter_living")
	public static final SoundEvent mobTrotterLiving = null;
	@GameRegistry.ObjectHolder("mob_tyrosaur_charge")
	public static final SoundEvent mobTyrosaurCharge = null;
	@GameRegistry.ObjectHolder("mob_tyrosaur_death")
	public static final SoundEvent mobTyrosaurDeath = null;
	@GameRegistry.ObjectHolder("mob_tyrosaur_hit")
	public static final SoundEvent mobTyrosaurHit = null;
	@GameRegistry.ObjectHolder("mob_tyrosaur_living")
	public static final SoundEvent mobTyrosaurLiving = null;
	@GameRegistry.ObjectHolder("mob_tyrosaur_ready_stomp")
	public static final SoundEvent mobTyrosaurReadyStomp = null;
	@GameRegistry.ObjectHolder("mob_tyrosaur_step")
	public static final SoundEvent mobTyrosaurStep = null;
	@GameRegistry.ObjectHolder("mob_tyrosaur_stomp")
	public static final SoundEvent mobTyrosaurStomp = null;
	@GameRegistry.ObjectHolder("mob_urka_death")
	public static final SoundEvent mobUrkaDeath = null;
	@GameRegistry.ObjectHolder("mob_urka_hit")
	public static final SoundEvent mobUrkaHit = null;
	@GameRegistry.ObjectHolder("mob_urka_living")
	public static final SoundEvent mobUrkaLiving = null;
	@GameRegistry.ObjectHolder("mob_ursa_death")
	public static final SoundEvent mobUrsaDeath = null;
	@GameRegistry.ObjectHolder("mob_ursa_hit")
	public static final SoundEvent mobUrsaHit = null;
	@GameRegistry.ObjectHolder("mob_ursa_living")
	public static final SoundEvent mobUrsaLiving = null;
	@GameRegistry.ObjectHolder("mob_valkyrie_death")
	public static final SoundEvent mobValkyrieDeath = null;
	@GameRegistry.ObjectHolder("mob_valkyrie_hit")
	public static final SoundEvent mobValkyrieHit = null;
	@GameRegistry.ObjectHolder("mob_valkyrie_living")
	public static final SoundEvent mobValkyrieLiving = null;
	@GameRegistry.ObjectHolder("mob_vertebron_death")
	public static final SoundEvent mobVertebronDeath = null;
	@GameRegistry.ObjectHolder("mob_vertebron_hit")
	public static final SoundEvent mobVertebronHit = null;
	@GameRegistry.ObjectHolder("mob_vertebron_living")
	public static final SoundEvent mobVertebronLiving = null;
	@GameRegistry.ObjectHolder("mob_visular_death")
	public static final SoundEvent mobVisularDeath = null;
	@GameRegistry.ObjectHolder("mob_visular_hit")
	public static final SoundEvent mobVisularHit = null;
	@GameRegistry.ObjectHolder("mob_visular_living")
	public static final SoundEvent mobVisularLiving = null;
	@GameRegistry.ObjectHolder("mob_visulon_living")
	public static final SoundEvent mobVisulonLiving = null;
	@GameRegistry.ObjectHolder("mob_void_walker_death")
	public static final SoundEvent mobVoidWalkerDeath = null;
	@GameRegistry.ObjectHolder("mob_void_walker_hit")
	public static final SoundEvent mobVoidWalkerHit = null;
	@GameRegistry.ObjectHolder("mob_void_walker_living")
	public static final SoundEvent mobVoidWalkerLiving = null;
	@GameRegistry.ObjectHolder("mob_volar_death")
	public static final SoundEvent mobVolarDeath = null;
	@GameRegistry.ObjectHolder("mob_volar_hit")
	public static final SoundEvent mobVolarHit = null;
	@GameRegistry.ObjectHolder("mob_volar_living")
	public static final SoundEvent mobVolarLiving = null;
	@GameRegistry.ObjectHolder("mob_voliant_death")
	public static final SoundEvent mobVoliantDeath = null;
	@GameRegistry.ObjectHolder("mob_voliant_hit")
	public static final SoundEvent mobVoliantHit = null;
	@GameRegistry.ObjectHolder("mob_voliant_living")
	public static final SoundEvent mobVoliantLiving = null;
	@GameRegistry.ObjectHolder("mob_voltron_death")
	public static final SoundEvent mobVoltronDeath = null;
	@GameRegistry.ObjectHolder("mob_voltron_hit")
	public static final SoundEvent mobVoltronHit = null;
	@GameRegistry.ObjectHolder("mob_voltron_living")
	public static final SoundEvent mobVoltronLiving = null;
	@GameRegistry.ObjectHolder("mob_voxxulon_death")
	public static final SoundEvent mobVoxxulonDeath = null;
	@GameRegistry.ObjectHolder("mob_voxxulon_hit")
	public static final SoundEvent mobVoxxulonHit = null;
	@GameRegistry.ObjectHolder("mob_voxxulon_living")
	public static final SoundEvent mobVoxxulonLiving = null;
	@GameRegistry.ObjectHolder("mob_walker_death")
	public static final SoundEvent mobWalkerDeath = null;
	@GameRegistry.ObjectHolder("mob_walker_hit")
	public static final SoundEvent mobWalkerHit = null;
	@GameRegistry.ObjectHolder("mob_walker_living")
	public static final SoundEvent mobWalkerLiving = null;
	@GameRegistry.ObjectHolder("mob_web_reaper_death")
	public static final SoundEvent mobWebReaperDeath = null;
	@GameRegistry.ObjectHolder("mob_web_reaper_hit")
	public static final SoundEvent mobWebReaperHit = null;
	@GameRegistry.ObjectHolder("mob_web_reaper_living")
	public static final SoundEvent mobWebReaperLiving = null;
	@GameRegistry.ObjectHolder("mob_wither_wizard_hit")
	public static final SoundEvent mobWitherWizardHit = null;
	@GameRegistry.ObjectHolder("mob_wither_wizard_living")
	public static final SoundEvent mobWitherWizardLiving = null;
	@GameRegistry.ObjectHolder("mob_xxeus_dash")
	public static final SoundEvent mobXxeusDash = null;
	@GameRegistry.ObjectHolder("mob_xxeus_death")
	public static final SoundEvent mobXxeusDeath = null;
	@GameRegistry.ObjectHolder("mob_xxeus_hit")
	public static final SoundEvent mobXxeusHit = null;
	@GameRegistry.ObjectHolder("mob_xxeus_living")
	public static final SoundEvent mobXxeusLiving = null;
	@GameRegistry.ObjectHolder("mob_yeti_death")
	public static final SoundEvent mobYetiDeath = null;
	@GameRegistry.ObjectHolder("mob_yeti_hit")
	public static final SoundEvent mobYetiHit = null;
	@GameRegistry.ObjectHolder("mob_yeti_living")
	public static final SoundEvent mobYetiLiving = null;
	@GameRegistry.ObjectHolder("mob_zarg_death")
	public static final SoundEvent mobZargDeath = null;
	@GameRegistry.ObjectHolder("mob_zarg_hit")
	public static final SoundEvent mobZargHit = null;
	@GameRegistry.ObjectHolder("mob_zarg_living")
	public static final SoundEvent mobZargLiving = null;
	@GameRegistry.ObjectHolder("mob_zhinx_death")
	public static final SoundEvent mobZhinxDeath = null;
	@GameRegistry.ObjectHolder("mob_zhinx_hit")
	public static final SoundEvent mobZhinxHit = null;
	@GameRegistry.ObjectHolder("mob_zhinx_living")
	public static final SoundEvent mobZhinxLiving = null;
	@GameRegistry.ObjectHolder("mob_zorp_death")
	public static final SoundEvent mobZorpDeath = null;
	@GameRegistry.ObjectHolder("mob_zorp_hit")
	public static final SoundEvent mobZorpHit = null;
	@GameRegistry.ObjectHolder("mob_zorp_living")
	public static final SoundEvent mobZorpLiving = null;
	@GameRegistry.ObjectHolder("bane_music")
	public static final SoundEvent musicBane = null;
	@GameRegistry.ObjectHolder("baroness_music")
	public static final SoundEvent musicBaroness = null;
	@GameRegistry.ObjectHolder("clunkhead_music")
	public static final SoundEvent musicClunkhead = null;
	@GameRegistry.ObjectHolder("coniferon_music")
	public static final SoundEvent musicConiferon = null;
	@GameRegistry.ObjectHolder("corallus_music")
	public static final SoundEvent musicCorallus = null;
	@GameRegistry.ObjectHolder("cotton_candor_music")
	public static final SoundEvent musicCottonCandor = null;
	@GameRegistry.ObjectHolder("craexxeus_music")
	public static final SoundEvent musicCraexxeus = null;
	@GameRegistry.ObjectHolder("creep_music")
	public static final SoundEvent musicCreep = null;
	@GameRegistry.ObjectHolder("crystocore_music")
	public static final SoundEvent musicCrystocore = null;
	@GameRegistry.ObjectHolder("dracyon_music")
	public static final SoundEvent musicDracyon = null;
	@GameRegistry.ObjectHolder("elusive_music")
	public static final SoundEvent musicElusive = null;
	@GameRegistry.ObjectHolder("four_guardians_music")
	public static final SoundEvent musicFourGuardians = null;
	@GameRegistry.ObjectHolder("goldorth_music")
	public static final SoundEvent musicGoldorth = null;
	@GameRegistry.ObjectHolder("graw_music")
	public static final SoundEvent musicGraw = null;
	@GameRegistry.ObjectHolder("gyro_music")
	public static final SoundEvent musicGyro = null;
	@GameRegistry.ObjectHolder("hive_king_music")
	public static final SoundEvent musicHiveKing = null;
	@GameRegistry.ObjectHolder("horon_music")
	public static final SoundEvent musicHoron = null;
	@GameRegistry.ObjectHolder("hydrolisk_music")
	public static final SoundEvent musicHydrolisk = null;
	@GameRegistry.ObjectHolder("king_bambambam_music")
	public static final SoundEvent musicKingBamBamBam = null;
	@GameRegistry.ObjectHolder("king_shroomus_music")
	public static final SoundEvent musicKingShroomus = null;
	@GameRegistry.ObjectHolder("kror_music")
	public static final SoundEvent musicKror = null;
	@GameRegistry.ObjectHolder("mechbot_music")
	public static final SoundEvent musicMechbot = null;
	@GameRegistry.ObjectHolder("nethengeic_wither_music")
	public static final SoundEvent musicNethengeicWither = null;
	@GameRegistry.ObjectHolder("penumbra_music")
	public static final SoundEvent musicPenumbra = null;
	@GameRegistry.ObjectHolder("primordial_five_music")
	public static final SoundEvent musicPrimordialFive = null;
	@GameRegistry.ObjectHolder("rock_rider_music")
	public static final SoundEvent musicRockRider = null;
	@GameRegistry.ObjectHolder("shadowlord_music")
	public static final SoundEvent musicShadowlord = null;
	@GameRegistry.ObjectHolder("silverfoot_music")
	public static final SoundEvent musicSilverfoot = null;
	@GameRegistry.ObjectHolder("skeletron_music")
	public static final SoundEvent musicSkeletron = null;
	@GameRegistry.ObjectHolder("smash_music")
	public static final SoundEvent musicSmash = null;
	@GameRegistry.ObjectHolder("tyrosaur_music")
	public static final SoundEvent musicTyrosaur = null;
	@GameRegistry.ObjectHolder("vinocorne_music")
	public static final SoundEvent musicVinocorne = null;
	@GameRegistry.ObjectHolder("visualent_music")
	public static final SoundEvent musicVisualent = null;
	@GameRegistry.ObjectHolder("voxxulon_music")
	public static final SoundEvent musicVoxxulon = null;
	@GameRegistry.ObjectHolder("petal_crafting_station_success")
	public static final SoundEvent petalCraftingStationSuccess = null;
	@GameRegistry.ObjectHolder("plant_thump")
	public static final SoundEvent plantThump = null;
	@GameRegistry.ObjectHolder("abyss_portal_activate")
	public static final SoundEvent portalAbyss = null;
	@GameRegistry.ObjectHolder("ancient_cavern_portal_activate")
	public static final SoundEvent portalAncientCavern = null;
	@GameRegistry.ObjectHolder("barren_portal_activate")
	public static final SoundEvent portalBarren = null;
	@GameRegistry.ObjectHolder("candyland_portal_activate")
	public static final SoundEvent portalCandyland = null;
	@GameRegistry.ObjectHolder("celeve_portal_activate")
	public static final SoundEvent portalCeleve = null;
	@GameRegistry.ObjectHolder("creeponia_portal_activate")
	public static final SoundEvent portalCreeponia = null;
	@GameRegistry.ObjectHolder("crystevia_portal_activate")
	public static final SoundEvent portalCrystevia = null;
	@GameRegistry.ObjectHolder("dark_portal_activate")
	public static final SoundEvent portalDark = null;
	@GameRegistry.ObjectHolder("immortallis_portal_activate")
	public static final SoundEvent portalImmortallis = null;
	@GameRegistry.ObjectHolder("iromine_portal_activate")
	public static final SoundEvent portalIromine = null;
	@GameRegistry.ObjectHolder("light_portal_activate")
	public static final SoundEvent portalLight = null;
	@GameRegistry.ObjectHolder("natural_portal_activate")
	public static final SoundEvent portalNatural = null;
	@GameRegistry.ObjectHolder("shyrelands_portal_activate")
	public static final SoundEvent portalShyrelands = null;
	@GameRegistry.ObjectHolder("rune_randomizer_use")
	public static final SoundEvent runeRandomizer = null;
	@GameRegistry.ObjectHolder("arc_wizard_fire")
	public static final SoundEvent shotArcWizardFire = null;
	@GameRegistry.ObjectHolder("baroness_fire")
	public static final SoundEvent shotBaronessFire = null;
	@GameRegistry.ObjectHolder("baumba_fire")
	public static final SoundEvent shotBaumbaFire = null;
	@GameRegistry.ObjectHolder("cherry_blaster_fire")
	public static final SoundEvent shotCherryBlasterFire = null;
	@GameRegistry.ObjectHolder("clown_fire")
	public static final SoundEvent shotClownFire = null;
	@GameRegistry.ObjectHolder("clunkhead_fire")
	public static final SoundEvent shotClunkheadFire = null;
	@GameRegistry.ObjectHolder("cotton_candor_fire")
	public static final SoundEvent shotCottonCandorFire = null;
	@GameRegistry.ObjectHolder("craexxeus_fire")
	public static final SoundEvent shotCraexxeusFire = null;
	@GameRegistry.ObjectHolder("craexxeus_nuke_fire")
	public static final SoundEvent shotCraexxeusNukeFire = null;
	@GameRegistry.ObjectHolder("fungik_fire")
	public static final SoundEvent shotFungikFire = null;
	@GameRegistry.ObjectHolder("guardian_fire")
	public static final SoundEvent shotGuardianFire = null;
	@GameRegistry.ObjectHolder("hag_fire")
	public static final SoundEvent shotHagFire = null;
	@GameRegistry.ObjectHolder("kaiyu_fire")
	public static final SoundEvent shotKaiyuFire = null;
	@GameRegistry.ObjectHolder("linger_fire")
	public static final SoundEvent shotLingerFire = null;
	@GameRegistry.ObjectHolder("magic_creeper_fire")
	public static final SoundEvent shotMagicCreeperFire = null;
	@GameRegistry.ObjectHolder("magicke_fire")
	public static final SoundEvent shotMagickeFire = null;
	@GameRegistry.ObjectHolder("mechbot_fire")
	public static final SoundEvent shotMechbotFire = null;
	@GameRegistry.ObjectHolder("mermage_fire")
	public static final SoundEvent shotMermageFire = null;
	@GameRegistry.ObjectHolder("mirage_fire")
	public static final SoundEvent shotMirageFire = null;
	@GameRegistry.ObjectHolder("shyre_troll_fire")
	public static final SoundEvent shotShyreTrollFire = null;
	@GameRegistry.ObjectHolder("skeleman_fire")
	public static final SoundEvent shotSkelemanFire = null;
	@GameRegistry.ObjectHolder("spirit_protector_fire")
	public static final SoundEvent shotSpiritProtectorFire = null;
	@GameRegistry.ObjectHolder("surge_fire")
	public static final SoundEvent shotSurgeFire = null;
	@GameRegistry.ObjectHolder("vine_wizard_fire")
	public static final SoundEvent shotVineWizardFire = null;
	@GameRegistry.ObjectHolder("web_reaper_fire")
	public static final SoundEvent shotWebReaperFire = null;
	@GameRegistry.ObjectHolder("wizard_blast_fire")
	public static final SoundEvent shotWizardBlast = null;
	@GameRegistry.ObjectHolder("shyrelands_dizzy")
	public static final SoundEvent shyrelandsDizzy = null;
	@GameRegistry.ObjectHolder("shyrelands_shine")
	public static final SoundEvent shyrelandsShine = null;
	@GameRegistry.ObjectHolder("shyrelands_weakness")
	public static final SoundEvent shyrelandsWeakness = null;
	@GameRegistry.ObjectHolder("shyrelands_wind")
	public static final SoundEvent shyrelandsWind = null;
	@GameRegistry.ObjectHolder("atlantic_staff_cast")
	public static final SoundEvent staffAtlantic = null;
	@GameRegistry.ObjectHolder("basic_staff_cast")
	public static final SoundEvent staffBasic = null;
	@GameRegistry.ObjectHolder("candy_staff_cast")
	public static final SoundEvent staffCandy = null;
	@GameRegistry.ObjectHolder("celestial_staff_cast")
	public static final SoundEvent staffCelestial = null;
	@GameRegistry.ObjectHolder("concussion_staff_cast")
	public static final SoundEvent staffConcussion = null;
	@GameRegistry.ObjectHolder("coral_staff_cast")
	public static final SoundEvent staffCoral = null;
	@GameRegistry.ObjectHolder("crystevia_staff_cast")
	public static final SoundEvent staffCrystevia = null;
	@GameRegistry.ObjectHolder("ember_staff_cast")
	public static final SoundEvent staffEmber = null;
	@GameRegistry.ObjectHolder("ever_staff_cast")
	public static final SoundEvent staffEver = null;
	@GameRegistry.ObjectHolder("firefly_staff_cast")
	public static final SoundEvent staffFirefly = null;
	@GameRegistry.ObjectHolder("fungal_staff_cast")
	public static final SoundEvent staffFungal = null;
	@GameRegistry.ObjectHolder("joker_staff_cast")
	public static final SoundEvent staffJoker = null;
	@GameRegistry.ObjectHolder("kaiyu_staff_cast")
	public static final SoundEvent staffKaiyu = null;
	@GameRegistry.ObjectHolder("lightshine_staff_cast")
	public static final SoundEvent staffLightshine = null;
	@GameRegistry.ObjectHolder("lunar_staff_cast")
	public static final SoundEvent staffLunar = null;
	@GameRegistry.ObjectHolder("meteor_staff_cast")
	public static final SoundEvent staffMeteor = null;
	@GameRegistry.ObjectHolder("moonlight_staff_cast")
	public static final SoundEvent staffMoonlight = null;
	@GameRegistry.ObjectHolder("nature_staff_cast")
	public static final SoundEvent staffNature = null;
	@GameRegistry.ObjectHolder("nightmare_staff_cast")
	public static final SoundEvent staffNightmare = null;
	@GameRegistry.ObjectHolder("noxious_staff_cast")
	public static final SoundEvent staffNoxious = null;
	@GameRegistry.ObjectHolder("phantom_staff_cast")
	public static final SoundEvent staffPhantom = null;
	@GameRegistry.ObjectHolder("reef_staff_cast")
	public static final SoundEvent staffReef = null;
	@GameRegistry.ObjectHolder("rejuvenation_staff_cast")
	public static final SoundEvent staffRejuvenation = null;
	@GameRegistry.ObjectHolder("runic_staff_cast")
	public static final SoundEvent staffRunic = null;
	@GameRegistry.ObjectHolder("shadow_staff_cast")
	public static final SoundEvent staffShadow = null;
	@GameRegistry.ObjectHolder("show_staff_cast")
	public static final SoundEvent staffShow = null;
	@GameRegistry.ObjectHolder("shyre_staff_cast")
	public static final SoundEvent staffShyre = null;
	@GameRegistry.ObjectHolder("sky_staff_cast")
	public static final SoundEvent staffSky = null;
	@GameRegistry.ObjectHolder("sun_staff_cast")
	public static final SoundEvent staffSun = null;
	@GameRegistry.ObjectHolder("surge_staff_cast")
	public static final SoundEvent staffSurge = null;
	@GameRegistry.ObjectHolder("tangle_staff_cast")
	public static final SoundEvent staffTangle = null;
	@GameRegistry.ObjectHolder("ultimatum_staff_cast")
	public static final SoundEvent staffUltimatum = null;
	@GameRegistry.ObjectHolder("web_staff_cast")
	public static final SoundEvent staffWeb = null;
	@GameRegistry.ObjectHolder("tea_sink_fill")
	public static final SoundEvent teaSinkFill = null;
	@GameRegistry.ObjectHolder("tea_sink_use")
	public static final SoundEvent teaSinkUse = null;
	@GameRegistry.ObjectHolder("temple_trap_laugh")
	public static final SoundEvent templeTrapLaugh = null;
	@GameRegistry.ObjectHolder("tribute_fail")
	public static final SoundEvent tributeFail = null;
	@GameRegistry.ObjectHolder("tribute_success")
	public static final SoundEvent tributeSuccess = null;
	@GameRegistry.ObjectHolder("creation_slab_use")
	public static final SoundEvent useCreationSlab = null;
	@GameRegistry.ObjectHolder("very_heavy_step")
	public static final SoundEvent veryHeavyStep = null;
	@GameRegistry.ObjectHolder("vulcane_use")
	public static final SoundEvent vulcaneUse = null;
	@GameRegistry.ObjectHolder("whitewash_use")
	public static final SoundEvent whitewashUse = null;

	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> ev) {
		AdventOfAscension.logOptionalMessage("Beginning sounds registration");

		ev.getRegistry().registerAll(
			createSoundEvent("ambient.music.null", "music_null", true),
			createSoundEvent("event.ascension_shrine.use", "ascension_shrine_use"),
			createSoundEvent("entity.baron_bomb.priming", "baron_bomb_priming"),
			createSoundEvent("entity.baron_bomb.spawn", "baron_bomb_spawn"),
			createSoundEvent("entity.bloodlust.collect", "bloodlust_collect"),
			createSoundEvent("item.bone_horn.call", "bone_horn_call"),
			createSoundEvent("entity.bubble_shot.pop", "bubble_shot_pop"),
			createSoundEvent("entity.generic.candy_snail_step", "candy_snail_step"),
			createSoundEvent("entity.generic.candy_thump", "candy_thump"),
			createSoundEvent("item.chainsaw.use", "chainsaw_use"),
			createSoundEvent("event.creation_forge.use", "creation_forge_use"),
			createSoundEvent("event.crystal_creator.use", "crystal_creator_use"),
			createSoundEvent("event.crystal_extension_shrine.use", "crystal_extension_shrine_use"),
			createSoundEvent("event.declogging_table.use", "declogging_table_use"),
			createSoundEvent("entity.generic.dino_step", "entity_generic_dino_step"),
			createSoundEvent("entity.player.dodge", "player_dodge"),
			createSoundEvent("entity.idol.hit", "entity_idol_hit"),
			createSoundEvent("entity.idol.living", "entity_idol_living"),
			createSoundEvent("entity.idol.prize", "entity_idol_prize"),
			createSoundEvent("entity.idol.spawn", "entity_idol_spawn"),
			createSoundEvent("entity.pixon.harvest", "entity_pixon_harvest"),
			createSoundEvent("entity.pixon.living", "entity_pixon_living"),
			createSoundEvent("event.big_day.start", "big_day_start"),
			createSoundEvent("event.blood_hunt.start", "blood_hunt_start"),
			createSoundEvent("event.creep_day.start", "creep_day_start"),
			createSoundEvent("event.death_day.start", "death_day_start"),
			createSoundEvent("event.lunar_invasion.start", "lunar_invasion_start"),
			createSoundEvent("event.soul_scurry.start", "soul_scurry_start"),
			createSoundEvent("event.extraction.success", "extraction_success"),
			createSoundEvent("event.filtration_system.activate", "filtration_system_activate"),
			createSoundEvent("event.filtration_system.use", "filtration_system_use"),
			createSoundEvent("event.foraging.loot", "foraging_loot"),
			createSoundEvent("item.thrown.goo_ball.impact", "goo_ball_impact"),
			createSoundEvent("item.greatblade.goofy_greatblade.fail", "goofy_greatblade_fail"),
			createSoundEvent("item.tool.goofy_tool.fail", "goofy_tool_fail"),
			createSoundEvent("item.gun.abominator.fire", "abominator_fire"),
			createSoundEvent("item.gun.archergun.fire", "archergun_fire"),
			createSoundEvent("item.gun.artifact.fire", "artifact_fire"),
			createSoundEvent("item.gun.atomizer.fire", "atomizer_fire"),
			createSoundEvent("item.gun.ball_cannon.fire", "ball_cannon_fire"),
			createSoundEvent("item.gun.big_blast.fire", "big_blast_fire"),
			createSoundEvent("item.gun.blowpipe.fire", "blowpipe_fire"),
			createSoundEvent("item.gun.boom_cannon.fire", "boom_cannon_fire"),
			createSoundEvent("item.gun.bubble_gun.fire", "bubble_gun_fire"),
			createSoundEvent("item.gun.carrot_cannon.fire", "carrot_cannon_fire"),
			createSoundEvent("item.gun.chaingun.fire", "chaingun_fire"),
			createSoundEvent("item.gun.chugger.fire", "chugger_fire"),
			createSoundEvent("item.gun.clowner.fire", "clowner_fire"),
			createSoundEvent("item.gun.colour_cannon.fire", "colour_cannon_fire"),
			createSoundEvent("item.gun.confetti_cannon.fire", "confetti_cannon_fire"),
			createSoundEvent("item.gun.dark_gun.fire", "dark_gun_fire"),
			createSoundEvent("item.gun.discharge_gun.fire", "discharge_gun_fire"),
			createSoundEvent("item.gun.doom_gun.fire", "doom_gun_fire"),
			createSoundEvent("item.gun.drain_gun.fire", "drain_gun_fire"),
			createSoundEvent("item.gun.electro_cannon_1.fire", "electro_cannon_1_fire"),
			createSoundEvent("item.gun.electro_cannon_2.fire", "electro_cannon_2_fire"),
			createSoundEvent("item.gun.electro_cannon_3.fire", "electro_cannon_3_fire"),
			createSoundEvent("item.gun.electro_cannon_4.fire", "electro_cannon_4_fire"),
			createSoundEvent("item.gun.electro_cannon_5.fire", "electro_cannon_5_fire"),
			createSoundEvent("item.gun.energy_cannon.fire", "energy_cannon_fire"),
			createSoundEvent("item.gun.fast_rifle.fire", "fast_rifle_fire"),
			createSoundEvent("item.gun.flinger.fire", "flinger_fire"),
			createSoundEvent("item.gun.gas_gun.fire", "gas_gun_fire"),
			createSoundEvent("item.gun.gauge_rifle.fire", "gauge_rifle_fire"),
			createSoundEvent("item.gun.golem_gun.fire", "golem_gun_fire"),
			createSoundEvent("item.gun.gravity_blaster.fire", "gravity_blaster_fire"),
			createSoundEvent("item.gun.heat_wave.fire", "heat_wave_fire"),
			createSoundEvent("item.gun.high_cannon.fire", "high_cannon_fire"),
			createSoundEvent("item.gun.illusion_revolver.fire", "illusion_revolver_fire"),
			createSoundEvent("item.gun.illusion_smg.fire", "illusion_smg_fire"),
			createSoundEvent("item.gun.ion_blaster.fire", "ion_blaster_fire"),
			createSoundEvent("item.gun.jack_rocker.fire", "jack_rocker_fire"),
			createSoundEvent("item.gun.krasauns_dawn.fire", "krasauns_dawn_fire"),
			createSoundEvent("item.gun.light_cannon.fire", "light_cannon_fire"),
			createSoundEvent("item.gun.lower_cannon.fire", "lower_cannon_fire"),
			createSoundEvent("item.gun.magic_gun.fire", "magic_gun_fire"),
			createSoundEvent("item.gun.mech_cannon.fire", "mech_cannon_fire"),
			createSoundEvent("item.gun.mind_blaster.fire", "mind_blaster_fire"),
			createSoundEvent("item.gun.minigun.fire", "minigun_fire"),
			createSoundEvent("item.gun.mini_pistol.fire", "mini_pistol_fire"),
			createSoundEvent("item.gun.missile_maker.fire", "missile_maker_fire"),
			createSoundEvent("item.gun.monster.fire", "monster_fire"),
			createSoundEvent("item.gun.moon_shiner.fire", "moon_shiner_fire"),
			createSoundEvent("item.gun.paralyzer.fire", "paralyzer_fire"),
			createSoundEvent("item.gun.party_popper.fire", "party_popper_fire"),
			createSoundEvent("item.gun.ray_gun.fire", "ray_gun_fire"),
			createSoundEvent("item.gun.reefer.fire", "reefer_fire"),
			createSoundEvent("item.gun.revolution.fire", "revolution_fire"),
			createSoundEvent("item.gun.revolver.fire", "revolver_fire"),
			createSoundEvent("item.gun.roulette.fire", "roulette_fire"),
			createSoundEvent("item.gun.rpg.fire", "rpg_fire"),
			createSoundEvent("item.gun.shadow_blaster.fire", "shadow_blaster_fire"),
			createSoundEvent("item.gun.shotgun.fire", "shotgun_fire"),
			createSoundEvent("item.gun.slugger.fire", "slugger_fire"),
			createSoundEvent("item.gun.sniper.fire", "sniper_fire"),
			createSoundEvent("item.gun.soul_spark.fire", "soul_spark_fire"),
			createSoundEvent("item.gun.space_gun.fire", "space_gun_fire"),
			createSoundEvent("item.gun.space_revolver.fire", "space_revolver_fire"),
			createSoundEvent("item.gun.spirit_shower.fire", "spirit_shower_fire"),
			createSoundEvent("item.gun.sprayer.fire", "sprayer_fire"),
			createSoundEvent("item.gun.squad_gun.fire", "squad_gun_fire"),
			createSoundEvent("item.gun.stampede.fire", "stampede_fire"),
			createSoundEvent("item.gun.step_cannon_1.fire", "step_cannon_1_fire"),
			createSoundEvent("item.gun.step_cannon_2.fire", "step_cannon_2_fire"),
			createSoundEvent("item.gun.step_cannon_3.fire", "step_cannon_3_fire"),
			createSoundEvent("item.gun.step_cannon_4.fire", "step_cannon_4_fire"),
			createSoundEvent("item.gun.step_cannon_5.fire", "step_cannon_5_fire"),
			createSoundEvent("item.gun.swarmotron.fire", "swarmotron_fire"),
			createSoundEvent("item.gun.synth_cannon_1.fire", "synth_cannon_1_fire"),
			createSoundEvent("item.gun.synth_cannon_2.fire", "synth_cannon_2_fire"),
			createSoundEvent("item.gun.synth_cannon_3.fire", "synth_cannon_3_fire"),
			createSoundEvent("item.gun.synth_cannon_4.fire", "synth_cannon_4_fire"),
			createSoundEvent("item.gun.synth_cannon_5.fire", "synth_cannon_5_fire"),
			createSoundEvent("item.gun.upper_cannon.fire", "upper_cannon_fire"),
			createSoundEvent("item.gun.vibe_cannon_1.fire", "vibe_cannon_1_fire"),
			createSoundEvent("item.gun.vibe_cannon_2.fire", "vibe_cannon_2_fire"),
			createSoundEvent("item.gun.vibe_cannon_3.fire", "vibe_cannon_3_fire"),
			createSoundEvent("item.gun.vibe_cannon_4.fire", "vibe_cannon_4_fire"),
			createSoundEvent("item.gun.vibe_cannon_5.fire", "vibe_cannon_5_fire"),
			createSoundEvent("item.gun.whimsy_winder.fire", "whimsy_winder_fire"),
			createSoundEvent("item.gun.wither_cannon.fire", "wither_cannon_fire"),
			createSoundEvent("item.gun.withers_wrath.fire", "withers_wrath_fire"),
			createSoundEvent("item.gun.wood_rifle.fire", "wood_rifle_fire"),
			createSoundEvent("event.haunting_table.use", "haunting_table_use"),
			createSoundEvent("event.heart_stone.use", "heart_stone_use"),
			createSoundEvent("event.heart_stone.spawn", "heart_stone_spawn"),
			createSoundEvent("entity.generic.heavy_step", "entity_generic_heavy_step"),
			createSoundEvent("item.thrown.hellfire.impact", "hellfire_impact"),
			createSoundEvent("event.infusion.fail", "infusion_fail"),
			createSoundEvent("event.infusion.success", "infusion_success"),
			createSoundEvent("entity.player.level_100", "player_level_100"),
			createSoundEvent("entity.player.level_up", "player_level_up"),
			createSoundEvent("event.lotto.win", "lotto_win"),
			createSoundEvent("event.lunar_creation_table.success", "lunar_creation_table_success"),
			createSoundEvent("event.lunar_enrichment_table.use", "lunar_enrichment_table_use"),
			createSoundEvent("event.runes.craft", "runes_craft"),
			createSoundEvent("event.mending.success", "mending_success"),
			createSoundEvent("mob.airhead.death", "mob_airhead_death"),
			createSoundEvent("mob.airhead.hit", "mob_airhead_hit"),
			createSoundEvent("mob.airhead.living", "mob_airhead_living"),
			createSoundEvent("mob.alarmo.death", "mob_alarmo_death"),
			createSoundEvent("mob.alarmo.hit", "mob_alarmo_hit"),
			createSoundEvent("mob.alarmo.living", "mob_alarmo_living"),
			createSoundEvent("mob.amphibior.death", "mob_amphibior_death"),
			createSoundEvent("mob.amphibior.hit", "mob_amphibior_hit"),
			createSoundEvent("mob.amphibior.living", "mob_amphibior_living"),
			createSoundEvent("mob.amphibiyte.death", "mob_amphibiyte_death"),
			createSoundEvent("mob.amphibiyte.hit", "mob_amphibiyte_hit"),
			createSoundEvent("mob.amphibiyte.living", "mob_amphibiyte_living"),
			createSoundEvent("mob.anemia.death", "mob_anemia_death"),
			createSoundEvent("mob.anemia.hit", "mob_anemia_hit"),
			createSoundEvent("mob.anemia.living", "mob_anemia_living"),
			createSoundEvent("mob.angelica.death", "mob_angelica_death"),
			createSoundEvent("mob.angelica.hit", "mob_angelica_hit"),
			createSoundEvent("mob.angelica.living", "mob_angelica_living"),
			createSoundEvent("mob.angler.death", "mob_angler_death"),
			createSoundEvent("mob.angler.hit", "mob_angler_hit"),
			createSoundEvent("mob.angler.living", "mob_angler_living"),
			createSoundEvent("mob.apparition.death", "mob_apparition_death"),
			createSoundEvent("mob.apparition.hit", "mob_apparition_hit"),
			createSoundEvent("mob.apparition.living", "mob_apparition_living"),
			createSoundEvent("mob.arcbeast.death", "mob_arcbeast_death"),
			createSoundEvent("mob.arcbeast.hit", "mob_arcbeast_hit"),
			createSoundEvent("mob.arcbeast.living", "mob_arcbeast_living"),
			createSoundEvent("mob.archvine.death", "mob_archvine_death"),
			createSoundEvent("mob.archvine.hit", "mob_archvine_hit"),
			createSoundEvent("mob.archvine.living", "mob_archvine_living"),
			createSoundEvent("mob.arc_wizard.death", "mob_arc_wizard_death"),
			createSoundEvent("mob.arc_wizard.hit", "mob_arc_wizard_hit"),
			createSoundEvent("mob.arc_wizard.living", "mob_arc_wizard_living"),
			createSoundEvent("mob.arcworm.death", "mob_arcworm_death"),
			createSoundEvent("mob.arcworm.hit", "mob_arcworm_hit"),
			createSoundEvent("mob.arcworm.living", "mob_arcworm_living"),
			createSoundEvent("mob.ariel.death", "mob_ariel_death"),
			createSoundEvent("mob.ariel.hit", "mob_ariel_hit"),
			createSoundEvent("mob.ariel.living", "mob_ariel_living"),
			createSoundEvent("mob.arkback.death", "mob_arkback_death"),
			createSoundEvent("mob.arkback.hit", "mob_arkback_hit"),
			createSoundEvent("mob.arkback.living", "mob_arkback_living"),
			createSoundEvent("mob.arkzyne.death", "mob_arkzyne_death"),
			createSoundEvent("mob.arkzyne.hit", "mob_arkzyne_hit"),
			createSoundEvent("mob.arkzyne.living", "mob_arkzyne_living"),
			createSoundEvent("mob.automaton.death", "mob_automaton_death"),
			createSoundEvent("mob.automaton.hit", "mob_automaton_hit"),
			createSoundEvent("mob.automaton.living", "mob_automaton_living"),
			createSoundEvent("mob.axiolight.death", "mob_axiolight_death"),
			createSoundEvent("mob.axiolight.hit", "mob_axiolight_hit"),
			createSoundEvent("mob.axiolight.living", "mob_axiolight_living"),
			createSoundEvent("mob.bane.death", "mob_bane_death"),
			createSoundEvent("mob.bane.living", "mob_bane_living"),
			createSoundEvent("mob.banshee.death", "mob_banshee_death"),
			createSoundEvent("mob.banshee.hit", "mob_banshee_hit"),
			createSoundEvent("mob.banshee.living", "mob_banshee_living"),
			createSoundEvent("mob.basilisk.death", "mob_basilisk_death"),
			createSoundEvent("mob.basilisk.hit", "mob_basilisk_hit"),
			createSoundEvent("mob.basilisk.living", "mob_basilisk_living"),
			createSoundEvent("mob.baumba.jump", "mob_baumba_jump"),
			createSoundEvent("mob.bloodmist.death", "mob_bloodmist_death"),
			createSoundEvent("mob.bloodmist.hit", "mob_bloodmist_hit"),
			createSoundEvent("mob.bloodmist.living", "mob_bloodmist_living"),
			createSoundEvent("mob.bloodsucker.death", "mob_bloodsucker_death"),
			createSoundEvent("mob.bloodsucker.hit", "mob_bloodsucker_hit"),
			createSoundEvent("mob.bloodsucker.living", "mob_bloodsucker_living"),
			createSoundEvent("mob.bomb_carrier.hit", "mob_bomb_carrier_hit"),
			createSoundEvent("mob.bomb_carrier.living", "mob_bomb_carrier_living"),
			createSoundEvent("mob.boneback.death", "mob_boneback_death"),
			createSoundEvent("mob.boneback.hit", "mob_boneback_hit"),
			createSoundEvent("mob.boneback.living", "mob_boneback_living"),
			createSoundEvent("mob.bouncer.death", "mob_bouncer_death"),
			createSoundEvent("mob.bouncer.hit", "mob_bouncer_hit"),
			createSoundEvent("mob.bouncer.living", "mob_bouncer_living"),
			createSoundEvent("mob.bugeye.death", "mob_bugeye_death"),
			createSoundEvent("mob.bugeye.hit", "mob_bugeye_hit"),
			createSoundEvent("mob.bugeye.living", "mob_bugeye_living"),
			createSoundEvent("mob.bush_baby.death", "mob_bush_baby_death"),
			createSoundEvent("mob.bush_baby.hit", "mob_bush_baby_hit"),
			createSoundEvent("mob.bush_baby.living", "mob_bush_baby_living"),
			createSoundEvent("mob.carrotop.death", "mob_carrotop_death"),
			createSoundEvent("mob.carrotop.hit", "mob_carrotop_hit"),
			createSoundEvent("mob.carrotop.living", "mob_carrotop_living"),
			createSoundEvent("mob.cave_bug.death", "mob_cave_bug_death"),
			createSoundEvent("mob.cave_bug.hit", "mob_cave_bug_hit"),
			createSoundEvent("mob.cave_bug.living", "mob_cave_bug_living"),
			createSoundEvent("mob.cave_creep.death", "mob_cave_creep_death"),
			createSoundEvent("mob.cave_creep.hit", "mob_cave_creep_hit"),
			createSoundEvent("mob.cave_creep.living", "mob_cave_creep_living"),
			createSoundEvent("mob.celeve_clown.death", "mob_celeve_clown_death"),
			createSoundEvent("mob.celeve_clown.hit", "mob_celeve_clown_hit"),
			createSoundEvent("mob.celeve_clown.living", "mob_celeve_clown_living"),
			createSoundEvent("mob.charger.death", "mob_charger_death"),
			createSoundEvent("mob.charger.hit", "mob_charger_hit"),
			createSoundEvent("mob.charger.living", "mob_charger_living"),
			createSoundEvent("mob.chimera.death", "mob_chimera_death"),
			createSoundEvent("mob.chimera.hit", "mob_chimera_hit"),
			createSoundEvent("mob.chimera.living", "mob_chimera_living"),
			createSoundEvent("mob.chomper.hit", "mob_chomper_hit"),
			createSoundEvent("mob.chomper.living", "mob_chomper_living"),
			createSoundEvent("mob.clown.death", "mob_clown_death"),
			createSoundEvent("mob.clown.hit", "mob_clown_hit"),
			createSoundEvent("mob.clown.living", "mob_clown_living"),
			createSoundEvent("mob.clunkhead.death", "mob_clunkhead_death"),
			createSoundEvent("mob.compeer.death", "mob_compeer_death"),
			createSoundEvent("mob.compeer.hit", "mob_compeer_hit"),
			createSoundEvent("mob.compeer.living", "mob_compeer_living"),
			createSoundEvent("mob.coniferon.death", "mob_coniferon_death"),
			createSoundEvent("mob.coniferon.hit", "mob_coniferon_hit"),
			createSoundEvent("mob.coniferon.living", "mob_coniferon_living"),
			createSoundEvent("mob.corallus.death", "mob_corallus_death"),
			createSoundEvent("mob.corallus.hit", "mob_corallus_hit"),
			createSoundEvent("mob.corallus.living", "mob_corallus_living"),
			createSoundEvent("mob.corallus.taunt", "mob_corallus_taunt"),
			createSoundEvent("mob.coralon.death", "mob_coralon_death"),
			createSoundEvent("mob.coralon.hit", "mob_coralon_hit"),
			createSoundEvent("mob.coralon.living", "mob_coralon_living"),
			createSoundEvent("mob.coratee.death", "mob_coratee_death"),
			createSoundEvent("mob.coratee.hit", "mob_coratee_hit"),
			createSoundEvent("mob.coratee.living", "mob_coratee_living"),
			createSoundEvent("mob.cotton_candor.death", "mob_cotton_candor_death"),
			createSoundEvent("mob.cotton_candor.hit", "mob_cotton_candor_hit"),
			createSoundEvent("mob.cotton_candor.living", "mob_cotton_candor_living"),
			createSoundEvent("mob.craexxeus.charge", "mob_craexxeus_charge"),
			createSoundEvent("mob.craexxeus.death", "mob_craexxeus_death"),
			createSoundEvent("mob.craexxeus.hit", "mob_craexxeus_hit"),
			createSoundEvent("mob.craexxeus.living", "mob_craexxeus_living"),
			createSoundEvent("mob.creeperlock.teleport", "mob_creeperlock_teleport"),
			createSoundEvent("mob.creepird.death", "mob_creepird_death"),
			createSoundEvent("mob.creepird.hit", "mob_creepird_hit"),
			createSoundEvent("mob.creepird.living", "mob_creepird_living"),
			createSoundEvent("mob.creepoid.death", "mob_creepoid_death"),
			createSoundEvent("mob.creepoid.hit", "mob_creepoid_hit"),
			createSoundEvent("mob.creepoid.living", "mob_creepoid_living"),
			createSoundEvent("mob.crusilisk.death", "mob_crusilisk_death"),
			createSoundEvent("mob.crusilisk.hit", "mob_crusilisk_hit"),
			createSoundEvent("mob.crusilisk.living", "mob_crusilisk_living"),
			createSoundEvent("mob.crusilisk.scream", "mob_crusilisk_scream"),
			createSoundEvent("mob.cryptid.death", "mob_cryptid_death"),
			createSoundEvent("mob.cryptid.hit", "mob_cryptid_hit"),
			createSoundEvent("mob.cryptid.living", "mob_cryptid_living"),
			createSoundEvent("mob.crystal_construct.death", "mob_crystal_construct_death"),
			createSoundEvent("mob.crystal_construct.hit", "mob_crystal_construct_hit"),
			createSoundEvent("mob.crystal_construct.living", "mob_crystal_construct_living"),
			createSoundEvent("mob.cyclops.death", "mob_cyclops_death"),
			createSoundEvent("mob.cyclops.hit", "mob_cyclops_hit"),
			createSoundEvent("mob.cyclops.living", "mob_cyclops_living"),
			createSoundEvent("mob.dark_beast.death", "mob_dark_beast_death"),
			createSoundEvent("mob.dark_beast.hit", "mob_dark_beast_hit"),
			createSoundEvent("mob.dark_beast.living", "mob_dark_beast_living"),
			createSoundEvent("mob.dawnlight.death", "mob_dawnlight_death"),
			createSoundEvent("mob.dawnlight.hit", "mob_dawnlight_hit"),
			createSoundEvent("mob.dawnlight.living", "mob_dawnlight_living"),
			createSoundEvent("mob.death_hunter.death", "mob_vdeath_hunter_death"),
			createSoundEvent("mob.death_hunter.hit", "mob_death_hunter_hit"),
			createSoundEvent("mob.death_hunter.living", "mob_death_hunter_living"),
			createSoundEvent("mob.deinotherium.death", "mob_deinotherium_death"),
			createSoundEvent("mob.deinotherium.hit", "mob_deinotherium_hit"),
			createSoundEvent("mob.deinotherium.living", "mob_deinotherium_living"),
			createSoundEvent("mob.destructor.death", "mob_destructor_death"),
			createSoundEvent("mob.destructor.hit", "mob_destructor_hit"),
			createSoundEvent("mob.destructor.living", "mob_destructor_living"),
			createSoundEvent("mob.devourer.death", "mob_devourer_death"),
			createSoundEvent("mob.devourer.hit", "mob_devourer_hit"),
			createSoundEvent("mob.devourer.living", "mob_devourer_living"),
			createSoundEvent("mob.dicer.death", "mob_dicer_death"),
			createSoundEvent("mob.dicer.hit", "mob_dicer_hit"),
			createSoundEvent("mob.dicer.living", "mob_dicer_living"),
			createSoundEvent("mob.diocus.death", "mob_diocus_death"),
			createSoundEvent("mob.diocus.hit", "mob_diocus_hit"),
			createSoundEvent("mob.diocus.living", "mob_diocus_living"),
			createSoundEvent("mob.distorter.death", "mob_distorter_death"),
			createSoundEvent("mob.distorter.hit", "mob_distorter_hit"),
			createSoundEvent("mob.distorter.living", "mob_distorter_living"),
			createSoundEvent("mob.doubler.death", "mob_doubler_death"),
			createSoundEvent("mob.doubler.hit", "mob_doubler_hit"),
			createSoundEvent("mob.doubler.living", "mob_doubler_living"),
			createSoundEvent("mob.dracyon.death", "mob_dracyon_death"),
			createSoundEvent("mob.dracyon.living", "mob_dracyon_living"),
			createSoundEvent("mob.draggy.death", "mob_draggy_death"),
			createSoundEvent("mob.draggy.hit", "mob_draggy_hit"),
			createSoundEvent("mob.draggy.living", "mob_draggy_living"),
			createSoundEvent("mob.dusteiva.death", "mob_dusteiva_death"),
			createSoundEvent("mob.dusteiva.hit", "mob_dusteiva_hit"),
			createSoundEvent("mob.dusteiva.living", "mob_dusteiva_living"),
			createSoundEvent("mob.duston.hit", "mob_duston_hit"),
			createSoundEvent("mob.dust_strider.death", "mob_dust_strider_death"),
			createSoundEvent("mob.dust_strider.hit", "mob_dust_strider_hit"),
			createSoundEvent("mob.dust_strider.living", "mob_dust_strider_living"),
			createSoundEvent("mob.dyrehorn.death", "mob_dyrehorn_death"),
			createSoundEvent("mob.dyrehorn.hit", "mob_dyrehorn_hit"),
			createSoundEvent("mob.dyrehorn.living", "mob_dyrehorn_living"),
			createSoundEvent("mob.echodar.death", "mob_echodar_death"),
			createSoundEvent("mob.echodar.hit", "mob_echodar_hit"),
			createSoundEvent("mob.echodar.living", "mob_echodar_living"),
			createSoundEvent("mob.eilosapien.death", "mob_eilosapien_death"),
			createSoundEvent("mob.eilosapien.hit", "mob_eilosapien_hit"),
			createSoundEvent("mob.eilosapien.living", "mob_eilosapien_living"),
			createSoundEvent("mob.elkanyne.death", "mob_elkanyne_death"),
			createSoundEvent("mob.elkanyne.hit", "mob_elkanyne_hit"),
			createSoundEvent("mob.elkanyne.living", "mob_elkanyne_living"),
			createSoundEvent("mob.elusive.death", "mob_elusive_death"),
			createSoundEvent("mob.elusive.hit", "mob_elusive_hit"),
			createSoundEvent("mob.elusive.living", "mob_elusive_living"),
			createSoundEvent("mob.embrake.death", "mob_embrake_death"),
			createSoundEvent("mob.embrake.hit", "mob_embrake_hit"),
			createSoundEvent("mob.embrake.living", "mob_embrake_living"),
			createSoundEvent("mob.emperor_beast.death", "mob_emperor_beast_death"),
			createSoundEvent("mob.emperor_beast.hit", "mob_emperor_beast_hit"),
			createSoundEvent("mob.emperor_beast.living", "mob_emperor_beast_living"),
			createSoundEvent("mob.emperor_beast.step", "mob_emperor_beast_step"),
			createSoundEvent("mob.enforcer.death", "mob_enforcer_death"),
			createSoundEvent("mob.enforcer.hit", "mob_enforcer_hit"),
			createSoundEvent("mob.enforcer.living", "mob_enforcer_living"),
			createSoundEvent("mob.everbeast.hit", "mob_everbeast_hit"),
			createSoundEvent("mob.everbeast.living", "mob_everbeast_living"),
			createSoundEvent("mob.exohead.death", "mob_exohead_death"),
			createSoundEvent("mob.exohead.hit", "mob_exohead_hit"),
			createSoundEvent("mob.exohead.living", "mob_exohead_living"),
			createSoundEvent("mob.explodot.death", "mob_explodot_death"),
			createSoundEvent("mob.explodot.hit", "mob_explodot_hit"),
			createSoundEvent("mob.explodot.living", "mob_explodot_living"),
			createSoundEvent("mob.eye_creature.death", "mob_eye_creature_death"),
			createSoundEvent("mob.eye_creature.hit", "mob_eye_creature_hit"),
			createSoundEvent("mob.eye_creature.living", "mob_eye_creature_living"),
			createSoundEvent("mob.faceless_runner.death", "mob_faceless_runner_death"),
			createSoundEvent("mob.faceless_runner.hit", "mob_faceless_runner_hit"),
			createSoundEvent("mob.faceless_runner.living", "mob_faceless_runner_living"),
			createSoundEvent("mob.fenix.death", "mob_fenix_death"),
			createSoundEvent("mob.fenix.hit", "mob_fenix_hit"),
			createSoundEvent("mob.fenix.living", "mob_fenix_living"),
			createSoundEvent("mob.fiend.death", "mob_fiend_death"),
			createSoundEvent("mob.fiend.hit", "mob_fiend_hit"),
			createSoundEvent("mob.fiend.living", "mob_fiend_living"),
			createSoundEvent("mob.fishix.death", "mob_fishix_death"),
			createSoundEvent("mob.fishix.hit", "mob_fishix_hit"),
			createSoundEvent("mob.fishix.living", "mob_fishix_living"),
			createSoundEvent("mob.flamewalker.death", "mob_flamewalker_death"),
			createSoundEvent("mob.flamewalker.hit", "mob_flamewalker_hit"),
			createSoundEvent("mob.flamewalker.living", "mob_flamewalker_living"),
			createSoundEvent("mob.flesh_eater.death", "mob_flesh_eater_death"),
			createSoundEvent("mob.flesh_eater.hit", "mob_flesh_eater_hit"),
			createSoundEvent("mob.flesh_eater.living", "mob_flesh_eater_living"),
			createSoundEvent("mob.flye.death", "mob_flye_death"),
			createSoundEvent("mob.flye.hit", "mob_flye_hit"),
			createSoundEvent("mob.flye.living", "mob_flye_living"),
			createSoundEvent("mob.fungi.death", "mob_fungi_death"),
			createSoundEvent("mob.fungi.hit", "mob_fungi_hit"),
			createSoundEvent("mob.fungi.living", "mob_fungi_living"),
			createSoundEvent("mob.furlion.death", "mob_furlion_death"),
			createSoundEvent("mob.furlion.hit", "mob_furlion_hit"),
			createSoundEvent("mob.furlion.living", "mob_furlion_living"),
			createSoundEvent("mob.gadgetoid.death", "mob_gadgetoid_death"),
			createSoundEvent("mob.gadgetoid.hit", "mob_gadgetoid_hit"),
			createSoundEvent("mob.gadgetoid.living", "mob_gadgetoid_living"),
			createSoundEvent("mob.ghost.death", "mob_ghost_death"),
			createSoundEvent("mob.ghost.hit", "mob_ghost_hit"),
			createSoundEvent("mob.ghostine.death", "mob_ghostine_death"),
			createSoundEvent("mob.ghostine.hit", "mob_ghostine_hit"),
			createSoundEvent("mob.ghostine.living", "mob_ghostine_living"),
			createSoundEvent("mob.ghost.living", "mob_ghost_living"),
			createSoundEvent("mob.giant.death", "mob_giant_death"),
			createSoundEvent("mob.giant.hit", "mob_giant_hit"),
			createSoundEvent("mob.giant_snail.death", "mob_giant_snail_death"),
			createSoundEvent("mob.giant_snail.hit", "mob_giant_snail_hit"),
			createSoundEvent("mob.giant_snail.living", "mob_giant_snail_living"),
			createSoundEvent("mob.giant_snail.step", "mob_giant_snail_step"),
			createSoundEvent("mob.goalby.death", "mob_goalby_death"),
			createSoundEvent("mob.goalby.hit", "mob_goalby_hit"),
			createSoundEvent("mob.goalby.living", "mob_goalby_living"),
			createSoundEvent("mob.goblin.death", "mob_goblin_death"),
			createSoundEvent("mob.goblin.hit", "mob_goblin_hit"),
			createSoundEvent("mob.goblin.living", "mob_goblin_living"),
			createSoundEvent("mob.goldorth.death", "mob_goldorth_death"),
			createSoundEvent("mob.goldorth.hit", "mob_goldorth_hit"),
			createSoundEvent("mob.goldorth.living", "mob_goldorth_living"),
			createSoundEvent("mob.golem.step", "mob_golem_step"),
			createSoundEvent("mob.graw.death", "mob_graw_death"),
			createSoundEvent("mob.graw.hit", "mob_graw_hit"),
			createSoundEvent("mob.graw.living", "mob_graw_living"),
			createSoundEvent("mob.grillface.death", "mob_grillface_death"),
			createSoundEvent("mob.grillface.hit", "mob_grillface_hit"),
			createSoundEvent("mob.grillface.living", "mob_grillface_living"),
			createSoundEvent("mob.grillface.scare", "mob_grillface_scare"),
			createSoundEvent("mob.grobbler.death", "mob_grobbler_death"),
			createSoundEvent("mob.grobbler.hit", "mob_grobbler_hit"),
			createSoundEvent("mob.grobbler.living", "mob_grobbler_living"),
			createSoundEvent("mob.grocculate.death", "mob_grocculate_death"),
			createSoundEvent("mob.grocculate.hit", "mob_grocculate_hit"),
			createSoundEvent("mob.grocculate.living", "mob_grocculate_living"),
			createSoundEvent("mob.grunt.death", "mob_grunt_death"),
			createSoundEvent("mob.grunt.hit", "mob_grunt_hit"),
			createSoundEvent("mob.grunt.living", "mob_grunt_living"),
			createSoundEvent("mob.guardian.death", "mob_guardian_death"),
			createSoundEvent("mob.guardian.hit", "mob_guardian_hit"),
			createSoundEvent("mob.gyro.death", "mob_gyro_death"),
			createSoundEvent("mob.gyro.hit", "mob_gyro_hit"),
			createSoundEvent("mob.gyro.living", "mob_gyro_living"),
			createSoundEvent("mob.hag.death", "mob_hag_death"),
			createSoundEvent("mob.hag.hit", "mob_hag_hit"),
			createSoundEvent("mob.hag.living", "mob_hag_living"),
			createSoundEvent("mob.hellcat.death", "mob_hellcat_death"),
			createSoundEvent("mob.hellcat.hit", "mob_hellcat_hit"),
			createSoundEvent("mob.hellcat.living", "mob_hellcat_living"),
			createSoundEvent("mob.hellspot.death", "mob_hellspot_death"),
			createSoundEvent("mob.hellspot.hit", "mob_hellspot_hit"),
			createSoundEvent("mob.hellspot.living", "mob_hellspot_living"),
			createSoundEvent("mob.hive_king.death", "mob_hive_king_death"),
			createSoundEvent("mob.hive_king.living", "mob_hive_king_living"),
			createSoundEvent("mob.horndron.death", "mob_horndron_death"),
			createSoundEvent("mob.horndron.hit", "mob_horndron_hit"),
			createSoundEvent("mob.horndron.living", "mob_horndron_living"),
			createSoundEvent("mob.horon.death", "mob_horon_death"),
			createSoundEvent("mob.horon.hit", "mob_horon_hit"),
			createSoundEvent("mob.horon.living", "mob_horon_living"),
			createSoundEvent("mob.host.death", "mob_host_death"),
			createSoundEvent("mob.host.living", "mob_host_drop"),
			createSoundEvent("mob.host.living", "mob_host_living"),
			createSoundEvent("mob.hunch.death", "mob_hunch_death"),
			createSoundEvent("mob.hunch.hit", "mob_hunch_hit"),
			createSoundEvent("mob.hunch.living", "mob_hunch_living"),
			createSoundEvent("mob.hunter.death", "mob_hunter_death"),
			createSoundEvent("mob.hunter.hit", "mob_hunter_hit"),
			createSoundEvent("mob.hunter.living", "mob_hunter_living"),
			createSoundEvent("mob.hydrolisk.death", "mob_hydrolisk_death"),
			createSoundEvent("mob.hydrolisk.hit", "mob_hydrolisk_hit"),
			createSoundEvent("mob.hydrolisk.living", "mob_hydrolisk_living"),
			createSoundEvent("mob.immortal.death", "mob_immortal_death"),
			createSoundEvent("mob.immortal.living", "mob_immortal_living"),
			createSoundEvent("mob.infernal.hit", "mob_infernal_hit"),
			createSoundEvent("mob.infernal.living", "mob_infernal_living"),
			createSoundEvent("mob.iosaur.death", "mob_iosaur_death"),
			createSoundEvent("mob.iosaur.hit", "mob_iosaur_hit"),
			createSoundEvent("mob.iosaur.living", "mob_iosaur_living"),
			createSoundEvent("mob.irkling.death", "mob_irkling_death"),
			createSoundEvent("mob.irkling.hit", "mob_irkling_hit"),
			createSoundEvent("mob.irkling.living", "mob_irkling_living"),
			createSoundEvent("mob.jawe.death", "mob_jawe_death"),
			createSoundEvent("mob.jawe.hit", "mob_jawe_hit"),
			createSoundEvent("mob.jawe.living", "mob_jawe_living"),
			createSoundEvent("mob.jumbo.living", "mob_jumbo_living"),
			createSoundEvent("mob.kaiyu.death", "mob_kaiyu_death"),
			createSoundEvent("mob.kaiyu.hit", "mob_kaiyu_hit"),
			createSoundEvent("mob.kaiyu.living", "mob_kaiyu_living"),
			createSoundEvent("mob.keeler.death", "mob_keeler_death"),
			createSoundEvent("mob.keeler.hit", "mob_keeler_hit"),
			createSoundEvent("mob.keeler.living", "mob_keeler_living"),
			createSoundEvent("mob.keeler.revive", "mob_keeler_revive"),
			createSoundEvent("mob.king_bambambam.death", "mob_king_bambambam_death"),
			createSoundEvent("mob.king_bambambam.hit", "mob_king_bambambam_hit"),
			createSoundEvent("mob.king_bambambam.living", "mob_king_bambambam_living"),
			createSoundEvent("mob.king_shroomus.death", "mob_king_shroomus_death"),
			createSoundEvent("mob.king_shroomus.heal", "mob_king_shroomus_heal"),
			createSoundEvent("mob.kror.death", "mob_kror_death"),
			createSoundEvent("mob.kror.living", "mob_kror_living"),
			createSoundEvent("mob.lelyetian.death", "mob_lelyetian_death"),
			createSoundEvent("mob.lelyetian.hit", "mob_lelyetian_hit"),
			createSoundEvent("mob.lelyetian.living", "mob_lelyetian_living"),
			createSoundEvent("mob.linger.death", "mob_linger_death"),
			createSoundEvent("mob.linger.hit", "mob_linger_hit"),
			createSoundEvent("mob.linger.living", "mob_linger_living"),
			createSoundEvent("mob.little_bam.spawn", "mob_little_bam_spawn"),
			createSoundEvent("mob.living_fungi.spawn", "mob_living_fungi_spawn"),
			createSoundEvent("mob.lollypopper.death", "mob_lollypopper_death"),
			createSoundEvent("mob.lost_soul.death", "mob_lost_soul_death"),
			createSoundEvent("mob.lost_soul.hit", "mob_lost_soul_hit"),
			createSoundEvent("mob.lost_soul.living", "mob_lost_soul_living"),
			createSoundEvent("mob.lunarcher.death", "mob_lunarcher_death"),
			createSoundEvent("mob.lunarcher.hit", "mob_lunarcher_hit"),
			createSoundEvent("mob.lunarcher.living", "mob_lunarcher_living"),
			createSoundEvent("mob.lurker.death", "mob_lurker_death"),
			createSoundEvent("mob.lurker.hit", "mob_lurker_hit"),
			createSoundEvent("mob.lurker.living", "mob_lurker_living"),
			createSoundEvent("mob.luxocron.death", "mob_luxocron_death"),
			createSoundEvent("mob.luxocron.hit", "mob_luxocron_hit"),
			createSoundEvent("mob.luxocron.living", "mob_luxocron_living"),
			createSoundEvent("mob.magicke.death", "mob_magicke_death"),
			createSoundEvent("mob.magicke.hit", "mob_magicke_hit"),
			createSoundEvent("mob.magicke.living", "mob_magicke_living"),
			createSoundEvent("mob.mechachron.death", "mob_mechachron_death"),
			createSoundEvent("mob.mechachron.hit", "mob_mechachron_hit"),
			createSoundEvent("mob.mechachron.living", "mob_mechachron_living"),
			createSoundEvent("mob.mechbot.jump", "mob_mechbot_jump"),
			createSoundEvent("mob.mechyon.death", "mob_mechyon_death"),
			createSoundEvent("mob.mechyon.hit", "mob_mechyon_hit"),
			createSoundEvent("mob.mechyon.living", "mob_mechyon_living"),
			createSoundEvent("mob.merkyre.death", "mob_merkyre_death"),
			createSoundEvent("mob.merkyre.hit", "mob_merkyre_hit"),
			createSoundEvent("mob.merkyre.living", "mob_merkyre_living"),
			createSoundEvent("mob.mermage.death", "mob_mermage_death"),
			createSoundEvent("mob.mermage.hit", "mob_mermage_hit"),
			createSoundEvent("mob.mermage.living", "mob_mermage_living"),
			createSoundEvent("mob.mirage.teleport", "mob_mirage_teleport"),
			createSoundEvent("mob.modulo.death", "mob_modulo_death"),
			createSoundEvent("mob.modulo.hit", "mob_modulo_hit"),
			createSoundEvent("mob.modulo.living", "mob_modulo_living"),
			createSoundEvent("mob.mother_void_walker.death", "mob_mother_void_walker_death"),
			createSoundEvent("mob.mother_void_walker.hit", "mob_mother_void_walker_hit"),
			createSoundEvent("mob.mother_void_walker.living", "mob_mother_void_walker_living"),
			createSoundEvent("mob.muckopede.hit", "mob_muckopede_hit"),
			createSoundEvent("mob.muckopede.living", "mob_muckopede_living"),
			createSoundEvent("mob.muncher.death", "mob_muncher_death"),
			createSoundEvent("mob.muncher.hit", "mob_muncher_hit"),
			createSoundEvent("mob.muncher.living", "mob_muncher_living"),
			createSoundEvent("mob.natura.death", "mob_natura_death"),
			createSoundEvent("mob.natura.hit", "mob_natura_hit"),
			createSoundEvent("mob.natura.living", "mob_natura_living"),
			createSoundEvent("mob.neptuno.death", "mob_neptuno_death"),
			createSoundEvent("mob.neptuno.hit", "mob_neptuno_hit"),
			createSoundEvent("mob.neptuno.living", "mob_neptuno_living"),
			createSoundEvent("mob.nethengeic_beast.death", "mob_nethengeic_beast_death"),
			createSoundEvent("mob.nethengeic_beast.hit", "mob_nethengeic_beast_hit"),
			createSoundEvent("mob.nethengeic_beast.living", "mob_nethengeic_beast_living"),
			createSoundEvent("mob.nethengeic_wither.death", "mob_nethengeic_wither_death"),
			createSoundEvent("mob.nethengeic_wither.hit", "mob_nethengeic_wither_hit"),
			createSoundEvent("mob.nethengeic_wither.living", "mob_nethengeic_wither_living"),
			createSoundEvent("mob.nightfly.death", "mob_nightfly_death"),
			createSoundEvent("mob.nightfly.hit", "mob_nightfly_hit"),
			createSoundEvent("mob.nightfly.living", "mob_nightfly_living"),
			createSoundEvent("mob.nightmare_spider.death", "mob_nightmare_spider_death"),
			createSoundEvent("mob.nightmare_spider.hit", "mob_nightmare_spider_hit"),
			createSoundEvent("mob.nightmare_spider.living", "mob_nightmare_spider_living"),
			createSoundEvent("mob.night_watcher.hit", "night_watcher_hit"),
			createSoundEvent("mob.night_watcher.living", "night_watcher_living"),
			createSoundEvent("mob.nightwing.death", "mob_nightwing_death"),
			createSoundEvent("mob.nightwing.hit", "mob_nightwing_hit"),
			createSoundEvent("mob.nightwing.living", "mob_nightwing_living"),
			createSoundEvent("mob.nipper.death", "mob_nipper_death"),
			createSoundEvent("mob.nipper.hit", "mob_nipper_hit"),
			createSoundEvent("mob.nipper.living", "mob_nipper_living"),
			createSoundEvent("mob.nospike.death", "mob_nospike_death"),
			createSoundEvent("mob.nospike.hit", "mob_nospike_hit"),
			createSoundEvent("mob.nospike.living", "mob_nospike_living"),
			createSoundEvent("mob.occulent.death", "mob_occulent_death"),
			createSoundEvent("mob.occulent.hit", "mob_occulent_hit"),
			createSoundEvent("mob.occulent.living", "mob_occulent_living"),
			createSoundEvent("mob.omnilight.death", "mob_omnilight_death"),
			createSoundEvent("mob.omnilight.hit", "mob_omnilight_hit"),
			createSoundEvent("mob.omnilight.living", "mob_omnilight_living"),
			createSoundEvent("mob.orbiter.death", "mob_orbiter_death"),
			createSoundEvent("mob.orbiter.hit", "mob_orbiter_hit"),
			createSoundEvent("mob.orbiter.living", "mob_orbiter_living"),
			createSoundEvent("mob.parasect.death", "mob_parasect_death"),
			createSoundEvent("mob.parasect.hit", "mob_parasect_hit"),
			createSoundEvent("mob.parasect.living", "mob_parasect_living"),
			createSoundEvent("mob.paravite.death", "mob_paravite_death"),
			createSoundEvent("mob.paravite.hit", "mob_paravite_hit"),
			createSoundEvent("mob.paravite.living", "mob_paravite_living"),
			createSoundEvent("mob.penguin.death", "mob_penguin_death"),
			createSoundEvent("mob.penguin.hit", "mob_penguin_hit"),
			createSoundEvent("mob.penguin.living", "mob_penguin_living"),
			createSoundEvent("mob.penumbra.death", "mob_penumbra_death"),
			createSoundEvent("mob.penumbra.hit", "mob_penumbra_hit"),
			createSoundEvent("mob.penumbra.living", "mob_penumbra_living"),
			createSoundEvent("mob.phantom.hit", "mob_phantom_hit"),
			createSoundEvent("mob.phantom.living", "mob_phantom_living"),
			createSoundEvent("mob.pigotron.appear", "mob_pigotron_appear"),
			createSoundEvent("mob.pigotron.death", "mob_pigotron_death"),
			createSoundEvent("mob.pigotron.hit", "mob_pigotron_hit"),
			createSoundEvent("mob.pigotron.living", "mob_pigotron_living"),
			createSoundEvent("mob.pincher.death", "mob_pincher_death"),
			createSoundEvent("mob.pincher.hit", "mob_pincher_hit"),
			createSoundEvent("mob.pincher.living", "mob_pincher_living"),
			createSoundEvent("mob.pod_plant.death", "mob_pod_plant_death"),
			createSoundEvent("mob.pod_plant.hit", "mob_pod_plant_hit"),
			createSoundEvent("mob.pod_plant.living", "mob_pod_plant_living"),
			createSoundEvent("mob.polytom.death", "mob_polytom_death"),
			createSoundEvent("mob.polytom.hit", "mob_polytom_hit"),
			createSoundEvent("mob.polytom.living", "mob_polytom_living"),
			createSoundEvent("mob.primordial.death", "mob_primordial_death"),
			createSoundEvent("mob.primordial.living", "mob_primordial_living"),
			createSoundEvent("mob.quickpocket.death", "mob_quickpocket_death"),
			createSoundEvent("mob.quickpocket.hit", "mob_quickpocket_hit"),
			createSoundEvent("mob.quickpocket.living", "mob_quickpocket_living"),
			createSoundEvent("mob.quickpocket.steal", "mob_quickpocket_steal"),
			createSoundEvent("mob.rainicorn.death", "mob_rainicorn_death"),
			createSoundEvent("mob.rainicorn.hit", "mob_rainicorn_hit"),
			createSoundEvent("mob.rainicorn.living", "mob_rainicorn_living"),
			createSoundEvent("mob.rammerhead.death", "mob_rammerhead_death"),
			createSoundEvent("mob.rammerhead.hit", "mob_rammerhead_hit"),
			createSoundEvent("mob.rammerhead.living", "mob_rammerhead_living"),
			createSoundEvent("mob.ramradon.death", "mob_ramradon_death"),
			createSoundEvent("mob.ramradon.hit", "mob_ramradon_hit"),
			createSoundEvent("mob.ramradon.living", "mob_ramradon_living"),
			createSoundEvent("mob.rawbone.death", "mob_rawbone_death"),
			createSoundEvent("mob.rawbone.hit", "mob_rawbone_hit"),
			createSoundEvent("mob.rawbone.living", "mob_rawbone_living"),
			createSoundEvent("mob.reaper.death", "mob_reaper_death"),
			createSoundEvent("mob.reaper.hit", "mob_reaper_hit"),
			createSoundEvent("mob.reaper.living", "mob_reaper_living"),
			createSoundEvent("mob.refluct.death", "mob_refluct_death"),
			createSoundEvent("mob.refluct.hit", "mob_refluct_hit"),
			createSoundEvent("mob.refluct.living", "mob_refluct_living"),
			createSoundEvent("mob.rockbiter.death", "mob_rockbiter_death"),
			createSoundEvent("mob.rockbiter.hit", "mob_rockbiter_hit"),
			createSoundEvent("mob.rockbiter.living", "mob_rockbiter_living"),
			createSoundEvent("mob.rock_rider.death", "mob_rock_rider_death"),
			createSoundEvent("mob.rock_rider.hit", "mob_rock_rider_hit"),
			createSoundEvent("mob.rock_rider.switch", "mob_rock_rider_switch"),
			createSoundEvent("mob.roloscope.death", "mob_roloscope_death"),
			createSoundEvent("mob.roloscope.hit", "mob_roloscope_hit"),
			createSoundEvent("mob.roloscope.living", "mob_roloscope_living"),
			createSoundEvent("mob.runic_golem.change", "mob_runic_golem_change"),
			createSoundEvent("mob.runic_golem.hit", "mob_runic_golem_hit"),
			createSoundEvent("mob.sabretooth.death", "mob_sabretooth_death"),
			createSoundEvent("mob.sabretooth.hit", "mob_sabretooth_hit"),
			createSoundEvent("mob.sabretooth.living", "mob_sabretooth_living"),
			createSoundEvent("mob.sasquatch.living", "mob_sasquatch_living"),
			createSoundEvent("mob.scrubby.hit", "mob_scrubby_hit"),
			createSoundEvent("mob.scrubby.living", "mob_scrubby_living"),
			createSoundEvent("mob.sea_viper.death", "mob_sea_viper_death"),
			createSoundEvent("mob.sea_viper.hit", "mob_sea_viper_hit"),
			createSoundEvent("mob.sea_viper.living", "mob_sea_viper_living"),
			createSoundEvent("mob.seeker.death", "mob_seeker_death"),
			createSoundEvent("mob.seeker.hit", "mob_seeker_hit"),
			createSoundEvent("mob.seeker.living", "mob_seeker_living"),
			createSoundEvent("mob.shade.death", "mob_shade_death"),
			createSoundEvent("mob.shade.hit", "mob_shade_hit"),
			createSoundEvent("mob.shade.living", "mob_shade_living"),
			createSoundEvent("mob.shadow.hit", "mob_shadow_hit"),
			createSoundEvent("mob.shadow.living", "mob_shadow_living"),
			createSoundEvent("mob.shadowlord.death", "mob_shadowlord_death"),
			createSoundEvent("mob.shadowlord.hit", "mob_shadowlord_hit"),
			createSoundEvent("mob.shadowlord.living", "mob_shadowlord_living"),
			createSoundEvent("mob.shifter.death", "mob_shifter_death"),
			createSoundEvent("mob.shifter.hit", "mob_shifter_hit"),
			createSoundEvent("mob.shifter.living", "mob_shifter_living"),
			createSoundEvent("mob.silencer.death", "mob_silencer_death"),
			createSoundEvent("mob.silencer.hit", "mob_silencer_hit"),
			createSoundEvent("mob.silencer.living", "mob_silencer_living"),
			createSoundEvent("mob.skeletal_cowman.hit", "mob_skeletal_cowman_hit"),
			createSoundEvent("mob.skeletal_cowman.living", "mob_skeletal_cowman_living"),
			createSoundEvent("mob.skeletron.death", "mob_skeletron_death"),
			createSoundEvent("mob.skeletron.hit", "mob_skeletron_hit"),
			createSoundEvent("mob.skeletron.living", "mob_skeletron_living"),
			createSoundEvent("mob.skellox.death", "mob_skellox_death"),
			createSoundEvent("mob.skellox.hit", "mob_skellox_hit"),
			createSoundEvent("mob.skellox.living", "mob_skellox_living"),
			createSoundEvent("mob.skipper.death", "mob_skipper_death"),
			createSoundEvent("mob.skipper.hit", "mob_skipper_hit"),
			createSoundEvent("mob.skipper.living", "mob_skipper_living"),
			createSoundEvent("mob.skull_creature.death", "mob_skull_creature_death"),
			createSoundEvent("mob.skull_creature.hit", "mob_skull_creature_hit"),
			createSoundEvent("mob.skull_creature.living", "mob_skull_creature_living"),
			createSoundEvent("mob.slimer.death", "mob_slimer_death"),
			createSoundEvent("mob.slimer.hit", "mob_slimer_hit"),
			createSoundEvent("mob.slimer.living", "mob_slimer_living"),
			createSoundEvent("mob.smash.death", "mob_smash_death"),
			createSoundEvent("mob.smash.hit", "mob_smash_hit"),
			createSoundEvent("mob.smash.living", "mob_smash_living"),
			createSoundEvent("mob.soulscorne.death", "mob_soulscorne_death"),
			createSoundEvent("mob.soulscorne.hit", "mob_soulscorne_hit"),
			createSoundEvent("mob.soulscorne.living", "mob_soulscorne_living"),
			createSoundEvent("mob.soulvyre.death", "mob_soulvyre_death"),
			createSoundEvent("mob.soulvyre.hit", "mob_soulvyre_hit"),
			createSoundEvent("mob.soulvyre.living", "mob_soulvyre_living"),
			createSoundEvent("mob.spectral_wizard.death", "mob_spectral_wizard_death"),
			createSoundEvent("mob.spectral_wizard.hit", "mob_spectral_wizard_hit"),
			createSoundEvent("mob.spectral_wizard.living", "mob_spectral_wizard_living"),
			createSoundEvent("mob.sphinx.death", "mob_sphinx_death"),
			createSoundEvent("mob.sphinx.hit", "mob_sphinx_hit"),
			createSoundEvent("mob.sphinx.living", "mob_sphinx_living"),
			createSoundEvent("mob.spinoledon.death", "mob_spinoledon_death"),
			createSoundEvent("mob.spinoledon.hit", "mob_spinoledon_hit"),
			createSoundEvent("mob.spinoledon.living", "mob_spinoledon_living"),
			createSoundEvent("mob.spinux.death", "mob_spinux_death"),
			createSoundEvent("mob.spinux.hit", "mob_spinux_hit"),
			createSoundEvent("mob.spinux.living", "mob_spinux_living"),
			createSoundEvent("mob.spirit.death", "mob_spirit_death"),
			createSoundEvent("mob.spirit.living", "mob_spirit_living"),
			createSoundEvent("mob.squiggler.death", "mob_squiggler_death"),
			createSoundEvent("mob.squiggler.hit", "mob_squiggler_hit"),
			createSoundEvent("mob.squiggler.living", "mob_squiggler_living"),
			createSoundEvent("mob.stalker.death", "mob_stalker_death"),
			createSoundEvent("mob.stalker.hit", "mob_stalker_hit"),
			createSoundEvent("mob.stalker.living", "mob_stalker_living"),
			createSoundEvent("mob.stimulo.death", "mob_stimulo_death"),
			createSoundEvent("mob.stimulo.hit", "mob_stimulo_hit"),
			createSoundEvent("mob.stimulo.living", "mob_stimulo_living"),
			createSoundEvent("mob.stimulosus.living", "mob_stimulosus_living"),
			createSoundEvent("mob.stinger.hit", "mob_stinger_hit"),
			createSoundEvent("mob.stinger.living", "mob_stinger_living"),
			createSoundEvent("mob.sugarface.death", "mob_sugarface_death"),
			createSoundEvent("mob.sugarface.hit", "mob_sugarface_hit"),
			createSoundEvent("mob.sugarface.living", "mob_sugarface_living"),
			createSoundEvent("mob.surveyor.death", "mob_surveyor_death"),
			createSoundEvent("mob.surveyor.hit", "mob_surveyor_hit"),
			createSoundEvent("mob.surveyor.living", "mob_surveyor_living"),
			createSoundEvent("mob.sysker.death", "mob_sysker_death"),
			createSoundEvent("mob.sysker.hit", "mob_sysker_hit"),
			createSoundEvent("mob.sysker.living", "mob_sysker_living"),
			createSoundEvent("mob.terradon.death", "mob_terradon_death"),
			createSoundEvent("mob.terradon.hit", "mob_terradon_hit"),
			createSoundEvent("mob.terradon.living", "mob_terradon_living"),
			createSoundEvent("mob.terrestrial.death", "mob_terrestrial_death"),
			createSoundEvent("mob.terrestrial.hit", "mob_terrestrial_hit"),
			createSoundEvent("mob.terrestrial.living", "mob_terrestrial_living"),
			createSoundEvent("mob.tharafly.death", "mob_tharafly_death"),
			createSoundEvent("mob.tharafly.hit", "mob_tharafly_hit"),
			createSoundEvent("mob.tharafly.living", "mob_tharafly_living"),
			createSoundEvent("mob.tortione.death", "mob_tortione_death"),
			createSoundEvent("mob.tortione.hit", "mob_tortione_hit"),
			createSoundEvent("mob.tortione.living", "mob_tortione_living"),
			createSoundEvent("mob.toxxulous.death", "mob_toxxulous_death"),
			createSoundEvent("mob.toxxulous.hit", "mob_toxxulous_hit"),
			createSoundEvent("mob.toxxulous.living", "mob_toxxulous_living"),
			createSoundEvent("mob.tracker.death", "mob_tracker_death"),
			createSoundEvent("mob.tracker.hit", "mob_tracker_hit"),
			createSoundEvent("mob.tracker.living", "mob_tracker_living"),
			createSoundEvent("mob.tree_spirit.death", "mob_tree_spirit_death"),
			createSoundEvent("mob.tree_spirit.hit", "mob_tree_spirit_hit"),
			createSoundEvent("mob.tree_spirit.living", "mob_tree_spirit_living"),
			createSoundEvent("mob.trickster.hide", "mob_trickster_hide"),
			createSoundEvent("mob.trickster.hit", "mob_trickster_hit"),
			createSoundEvent("mob.trickster.living", "mob_trickster_living"),
			createSoundEvent("mob.trotter.death", "mob_trotter_death"),
			createSoundEvent("mob.trotter.hit", "mob_trotter_hit"),
			createSoundEvent("mob.trotter.living", "mob_trotter_living"),
			createSoundEvent("mob.tyrosaur.charge", "mob_tyrosaur_charge"),
			createSoundEvent("mob.tyrosaur.death", "mob_tyrosaur_death"),
			createSoundEvent("mob.tyrosaur.hit", "mob_tyrosaur_hit"),
			createSoundEvent("mob.tyrosaur.living", "mob_tyrosaur_living"),
			createSoundEvent("mob.tyrosaur.ready_stomp", "mob_tyrosaur_ready_stomp"),
			createSoundEvent("mob.tyrosaur.step", "mob_tyrosaur_step"),
			createSoundEvent("mob.tyrosaur.stomp", "mob_tyrosaur_stomp"),
			createSoundEvent("mob.urka.death", "mob_urka_death"),
			createSoundEvent("mob.urka.hit", "mob_urka_hit"),
			createSoundEvent("mob.urka.living", "mob_urka_living"),
			createSoundEvent("mob.ursa.death", "mob_ursa_death"),
			createSoundEvent("mob.ursa.hit", "mob_ursa_hit"),
			createSoundEvent("mob.ursa.living", "mob_ursa_living"),
			createSoundEvent("mob.valkyrie.death", "mob_valkyrie_death"),
			createSoundEvent("mob.valkyrie.hit", "mob_valkyrie_hit"),
			createSoundEvent("mob.valkyrie.living", "mob_valkyrie_living"),
			createSoundEvent("mob.vertebron.death", "mob_vertebron_death"),
			createSoundEvent("mob.vertebron.hit", "mob_vertebron_hit"),
			createSoundEvent("mob.vertebron.living", "mob_vertebron_living"),
			createSoundEvent("mob.visular.death", "mob_visular_death"),
			createSoundEvent("mob.visular.hit", "mob_visular_hit"),
			createSoundEvent("mob.visular.living", "mob_visular_living"),
			createSoundEvent("mob.visulon.living", "mob_visulon_living"),
			createSoundEvent("mob.void_walker.death", "mob_void_walker_death"),
			createSoundEvent("mob.void_walker.hit", "mob_void_walker_hit"),
			createSoundEvent("mob.void_walker.living", "mob_void_walker_living"),
			createSoundEvent("mob.volar.death", "mob_volar_death"),
			createSoundEvent("mob.volar.hit", "mob_volar_hit"),
			createSoundEvent("mob.volar.living", "mob_volar_living"),
			createSoundEvent("mob.voliant.death", "mob_voliant_death"),
			createSoundEvent("mob.voliant.hit", "mob_voliant_hit"),
			createSoundEvent("mob.voliant.living", "mob_voliant_living"),
			createSoundEvent("mob.voltron.death", "mob_voltron_death"),
			createSoundEvent("mob.voltron.hit", "mob_voltron_hit"),
			createSoundEvent("mob.voltron.living", "mob_voltron_living"),
			createSoundEvent("mob.voxxulon.death", "mob_voxxulon_death"),
			createSoundEvent("mob.voxxulon.hit", "mob_voxxulon_hit"),
			createSoundEvent("mob.voxxulon.living", "mob_voxxulon_living"),
			createSoundEvent("mob.walker.death", "mob_walker_death"),
			createSoundEvent("mob.walker.hit", "mob_walker_hit"),
			createSoundEvent("mob.walker.living", "mob_walker_living"),
			createSoundEvent("mob.web_reaper.death", "mob_web_reaper_death"),
			createSoundEvent("mob.web_reaper.hit", "mob_web_reaper_hit"),
			createSoundEvent("mob.web_reaper.living", "mob_web_reaper_living"),
			createSoundEvent("mob.wither_wizard.hit", "mob_wither_wizard_hit"),
			createSoundEvent("mob.wither_wizard.living", "mob_wither_wizard_living"),
			createSoundEvent("mob.xxeus.dash", "mob_xxeus_dash"),
			createSoundEvent("mob.xxeus.death", "mob_xxeus_death"),
			createSoundEvent("mob.xxeus.hit", "mob_xxeus_hit"),
			createSoundEvent("mob.xxeus.living", "mob_xxeus_living"),
			createSoundEvent("mob.yeti.death", "mob_yeti_death"),
			createSoundEvent("mob.yeti.hit", "mob_yeti_hit"),
			createSoundEvent("mob.yeti.living", "mob_yeti_living"),
			createSoundEvent("mob.zarg.death", "mob_zarg_death"),
			createSoundEvent("mob.zarg.hit", "mob_zarg_hit"),
			createSoundEvent("mob.zarg.living", "mob_zarg_living"),
			createSoundEvent("mob.zhinx.death", "mob_zhinx_death"),
			createSoundEvent("mob.zhinx.hit", "mob_zhinx_hit"),
			createSoundEvent("mob.zhinx.living", "mob_zhinx_living"),
			createSoundEvent("mob.zorp.death", "mob_zorp_death"),
			createSoundEvent("mob.zorp.hit", "mob_zorp_hit"),
			createSoundEvent("mob.zorp.living", "mob_zorp_living"),
			createSoundEvent("music.bane", "bane_music", true),
			createSoundEvent("music.baroness", "baroness_music", true),
			createSoundEvent("music.clunkhead", "clunkhead_music", true),
			createSoundEvent("music.coniferon", "coniferon_music", true),
			createSoundEvent("music.corallus", "corallus_music", true),
			createSoundEvent("music.cotton_candor", "cotton_candor_music", true),
			createSoundEvent("music.craexxeus", "craexxeus_music", true),
			createSoundEvent("music.creep", "creep_music", true),
			createSoundEvent("music.crystocore", "crystocore_music", true),
			createSoundEvent("music.dracyon", "dracyon_music", true),
			createSoundEvent("music.elusive", "elusive_music", true),
			createSoundEvent("music.four_guardians", "four_guardians_music", true),
			createSoundEvent("music.goldorth", "goldorth_music", true),
			createSoundEvent("music.graw", "graw_music", true),
			createSoundEvent("music.gyro", "gyro_music", true),
			createSoundEvent("music.hive_king", "hive_king_music", true),
			createSoundEvent("music.horon", "horon_music", true),
			createSoundEvent("music.hydrolisk", "hydrolisk_music", true),
			createSoundEvent("music.king_bambambam", "king_bambambam_music", true),
			createSoundEvent("music.king_shroomus", "king_shroomus_music", true),
			createSoundEvent("music.kror", "kror_music", true),
			createSoundEvent("music.mechbot", "mechbot_music", true),
			createSoundEvent("music.nethengeic_wither", "nethengeic_wither_music", true),
			createSoundEvent("music.penumbra", "penumbra_music", true),
			createSoundEvent("music.primordial_five", "primordial_five_music", true),
			createSoundEvent("music.rock_rider", "rock_rider_music", true),
			createSoundEvent("music.shadowlord", "shadowlord_music", true),
			createSoundEvent("music.silverfoot", "silverfoot_music", true),
			createSoundEvent("music.skeletron", "skeletron_music", true),
			createSoundEvent("music.smash", "smash_music", true),
			createSoundEvent("music.tyrosaur", "tyrosaur_music", true),
			createSoundEvent("music.vinocorne", "vinocorne_music", true),
			createSoundEvent("music.visualent", "visualent_music", true),
			createSoundEvent("music.voxxulon", "voxxulon_music", true),
			createSoundEvent("event.petal_crafting_station.success", "petal_crafting_station_success"),
			createSoundEvent("entity.generic.plant_thump", "plant_thump"),
			createSoundEvent("blocks.abyss_portal.activate", "abyss_portal_activate"),
			createSoundEvent("blocks.ancient_cavern_portal.activate", "ancient_cavern_portal_activate"),
			createSoundEvent("blocks.barren_portal.activate", "barren_portal_activate"),
			createSoundEvent("blocks.candyland_portal.activate", "candyland_portal_activate"),
			createSoundEvent("blocks.celeve_portal.activate", "celeve_portal_activate"),
			createSoundEvent("blocks.creeponia_portal.activate", "creeponia_portal_activate"),
			createSoundEvent("blocks.crystevia_portal.activate", "crystevia_portal_activate"),
			createSoundEvent("blocks.dark_portal.activate", "dark_portal_activate"),
			createSoundEvent("blocks.immortallis_portal.activate", "immortallis_portal_activate"),
			createSoundEvent("blocks.iromine_portal.activate", "iromine_portal_activate"),
			createSoundEvent("blocks.light_portal.activate", "light_portal_activate"),
			createSoundEvent("blocks.natural_portal.activate", "natural_portal_activate"),
			createSoundEvent("blocks.shyrelands_portal.activate", "shyrelands_portal_activate"),
			createSoundEvent("event.rune_randomizer.use", "rune_randomizer_use"),
			createSoundEvent("projectile.arc_wizard.fire", "arc_wizard_fire"),
			createSoundEvent("projectile.baroness.fire", "baroness_fire"),
			createSoundEvent("projectile.baumba.fire", "baumba_fire"),
			createSoundEvent("projectile.cherry_blaster.fire", "cherry_blaster_fire"),
			createSoundEvent("projectile.clown.fire", "clown_fire"),
			createSoundEvent("projectile.clunkhead.fire", "clunkhead_fire"),
			createSoundEvent("projectile.cotton_candor.fire", "cotton_candor_fire"),
			createSoundEvent("projectile.craexxeus.fire", "craexxeus_fire"),
			createSoundEvent("projectile.craexxeus_nuke.fire", "craexxeus_nuke_fire"),
			createSoundEvent("projectile.fungik.fire", "fungik_fire"),
			createSoundEvent("projectile.guardian.fire", "guardian_fire"),
			createSoundEvent("projectile.hag.fire", "hag_fire"),
			createSoundEvent("projectile.kaiyu.fire", "kaiyu_fire"),
			createSoundEvent("projectile.linger.fire", "linger_fire"),
			createSoundEvent("projectile.magic_creeper.fire", "magic_creeper_fire"),
			createSoundEvent("projectile.magicke.fire", "magicke_fire"),
			createSoundEvent("projectile.mechbot.fire", "mechbot_fire"),
			createSoundEvent("projectile.mermage.fire", "mermage_fire"),
			createSoundEvent("projectile.mirage.fire", "mirage_fire"),
			createSoundEvent("projectile.shyre_troll.fire", "shyre_troll_fire"),
			createSoundEvent("projectile.skeleman.fire", "skeleman_fire"),
			createSoundEvent("projectile.spirit_protector.fire", "spirit_protector_fire"),
			createSoundEvent("projectile.surge.fire", "surge_fire"),
			createSoundEvent("projectile.vine_wizard.fire", "vine_wizard_fire"),
			createSoundEvent("projectile.web_reaper.fire", "web_reaper_fire"),
			createSoundEvent("projectile.wizard_blast.fire", "wizard_blast_fire"),
			createSoundEvent("event.shyrelands.dizzy", "shyrelands_dizzy"),
			createSoundEvent("event.shyrelands.shine", "shyrelands_shine"),
			createSoundEvent("event.shyrelands.weakness", "shyrelands_weakness"),
			createSoundEvent("event.shyrelands.wind", "shyrelands_wind"),
			createSoundEvent("item.staff.atlantic.cast", "atlantic_staff_cast"),
			createSoundEvent("item.staff.basic.cast", "basic_staff_cast"),
			createSoundEvent("item.staff.candy.cast", "candy_staff_cast"),
			createSoundEvent("item.staff.celestial.cast", "celestial_staff_cast"),
			createSoundEvent("item.staff.concussion.cast", "concussion_staff_cast"),
			createSoundEvent("item.staff.coral.cast", "coral_staff_cast"),
			createSoundEvent("item.staff.crystevia.cast", "crystevia_staff_cast"),
			createSoundEvent("item.staff.ember.cast", "ember_staff_cast"),
			createSoundEvent("item.staff.ever.cast", "ever_staff_cast"),
			createSoundEvent("item.staff.firefly.cast", "firefly_staff_cast"),
			createSoundEvent("item.staff.fungal.cast", "fungal_staff_cast"),
			createSoundEvent("item.staff.joker.cast", "joker_staff_cast"),
			createSoundEvent("item.staff.kaiyu.cast", "kaiyu_staff_cast"),
			createSoundEvent("item.staff.lightshine.cast", "lightshine_staff_cast"),
			createSoundEvent("item.staff.lunar.cast", "lunar_staff_cast"),
			createSoundEvent("item.staff.meteor.cast", "meteor_staff_cast"),
			createSoundEvent("item.staff.moonlight.cast", "moonlight_staff_cast"),
			createSoundEvent("item.staff.nature.cast", "nature_staff_cast"),
			createSoundEvent("item.staff.nightmare.cast", "nightmare_staff_cast"),
			createSoundEvent("item.staff.noxious.cast", "noxious_staff_cast"),
			createSoundEvent("item.staff.phantom.cast", "phantom_staff_cast"),
			createSoundEvent("item.staff.reef.cast", "reef_staff_cast"),
			createSoundEvent("item.staff.rejuvenation.cast", "rejuvenation_staff_cast"),
			createSoundEvent("item.staff.runic.cast", "runic_staff_cast"),
			createSoundEvent("item.staff.shadow.cast", "shadow_staff_cast"),
			createSoundEvent("item.staff.show.cast", "show_staff_cast"),
			createSoundEvent("item.staff.shyre.cast", "shyre_staff_cast"),
			createSoundEvent("item.staff.sky.cast", "sky_staff_cast"),
			createSoundEvent("item.staff.sun.cast", "sun_staff_cast"),
			createSoundEvent("item.staff.surge.cast", "surge_staff_cast"),
			createSoundEvent("item.staff.tangle.cast", "tangle_staff_cast"),
			createSoundEvent("item.staff.ultimatum.cast", "ultimatum_staff_cast"),
			createSoundEvent("item.staff.web.cast", "web_staff_cast"),
			createSoundEvent("event.tea_sink.fill", "tea_sink_fill"),
			createSoundEvent("event.tea_sink.use", "tea_sink_use"),
			createSoundEvent("event.temple_trap.laugh", "temple_trap_laugh"),
			createSoundEvent("event.tribute.fail", "tribute_fail"),
			createSoundEvent("event.tribute.success", "tribute_success"),
			createSoundEvent("item.creation_slab.use", "creation_slab_use"),
			createSoundEvent("entity.generic.very_heavy_step", "very_heavy_step"),
			createSoundEvent("item.vulcane.use", "vulcane_use"),
			createSoundEvent("event.whitewash.use", "whitewash_use")
		);
	}

	private static SoundEvent createSoundEvent(String key, String name) {
		return createSoundEvent(key, name, false);
	}

	private static SoundEvent createSoundEvent(String key, String name, boolean isMusic) {
		SoundEvent soundEvent = new SoundEvent(new ResourceLocation("aoa3", key)).setRegistryName("aoa3:" + name);

		if (isMusic && ModUtil.isClient())
			registerMusicType(name, soundEvent);

		return soundEvent;
	}

	private static void registerMusicType(String name, SoundEvent soundEvent) {
		EnumHelperClient.addMusicType(name, soundEvent, 0, 0);
	}
}
