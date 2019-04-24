package net.tslat.aoa3.common.registration;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class SoundsRegister {
	public static final SoundEvent nullMusic = createSoundEvent("ambient.music.null", "music_null");

	public static final SoundEvent ascensionShrineUse = createSoundEvent("event.ascension_shrine.use", "ascension_shrine_use");
	public static final SoundEvent bloodlustCollect = createSoundEvent("entity.bloodlust.collect", "bloodlust_collect");
	public static final SoundEvent baronBombSpawn = createSoundEvent("entity.baron_bomb.spawn", "baron_bomb_spawn");
	public static final SoundEvent baronBombPriming = createSoundEvent("entity.baron_bomb.priming", "baron_bomb_priming");
	public static final SoundEvent bubbleShotPop = createSoundEvent("entity.bubble_shot.pop", "bubble_shot_pop");
	public static final SoundEvent chainsawUse = createSoundEvent("item.chainsaw.use", "chainsaw_use");
	public static final SoundEvent creationForgeUse = createSoundEvent("event.creation_forge.use", "creation_forge_use");
	public static final SoundEvent crystalCreatorUse = createSoundEvent("event.crystal_creator.use", "crystal_creator_use");
	public static final SoundEvent crystalExtensionShrineUse = createSoundEvent("event.crystal_extension_shrine.use", "crystal_extension_shrine_use");
	public static final SoundEvent decloggingTableUse = createSoundEvent("event.declogging_table.use", "declogging_table_use");
	public static final SoundEvent dinoStep = createSoundEvent("entity.generic.dino_step", "entity_generic_dino_step");
	public static final SoundEvent dodge = createSoundEvent("entity.player.dodge", "player_dodge");
	public static final SoundEvent entityIdolHit = createSoundEvent("entity.idol.hit", "entity_idol_hit");
	public static final SoundEvent entityIdolLiving = createSoundEvent("entity.idol.living", "entity_idol_living");
	public static final SoundEvent entityIdolPrize = createSoundEvent("entity.idol.prize", "entity_idol_prize");
	public static final SoundEvent entityIdolSpawn = createSoundEvent("entity.idol.spawn", "entity_idol_spawn");
	public static final SoundEvent entityPixonHarvest = createSoundEvent("entity.pixon.harvest", "entity_pixon_harvest");
	public static final SoundEvent entityPixonLiving = createSoundEvent("entity.pixon.living", "entity_pixon_living");
	public static final SoundEvent eventBigDayStart = createSoundEvent("event.big_day.start", "big_day_start");
	public static final SoundEvent eventBloodHuntStart = createSoundEvent("event.blood_hunt.start", "blood_hunt_start");
	public static final SoundEvent eventCreepDayStart = createSoundEvent("event.creep_day.start", "creep_day_start");
	public static final SoundEvent eventDeathDayStart = createSoundEvent("event.death_day.start", "death_day_start");
	public static final SoundEvent eventLunarInvasionStart = createSoundEvent("event.lunar_invasion.start", "lunar_invasion_start");
	public static final SoundEvent eventSoulScurryStart = createSoundEvent("event.soul_scurry.start", "soul_scurry_start");
	public static final SoundEvent extractionDeviceSuccess = createSoundEvent("event.extraction.success", "extraction_success");
	public static final SoundEvent filtrationSystemActivate = createSoundEvent("event.filtration_system.activate", "filtration_system_activate");
	public static final SoundEvent filtrationSystemUse = createSoundEvent("event.filtration_system.use", "filtration_system_use");
	public static final SoundEvent foragingLoot = createSoundEvent("event.foraging.loot", "foraging_loot");
	public static final SoundEvent gooBallImpact = createSoundEvent("item.thrown.goo_ball.impact", "goo_ball_impact");
	public static final SoundEvent greatbladeGoofy = createSoundEvent("item.greatblade.goofy_greatblade.fail", "goofy_greatblade_fail");
	public static final SoundEvent gunAbominator = createSoundEvent("item.gun.abominator.fire", "abominator_fire");
	public static final SoundEvent gunAnimalBlaster = createSoundEvent("item.gun.animal_blaster.fire", "animal_blaster_fire");
	public static final SoundEvent gunArchergun = createSoundEvent("item.gun.archergun.fire", "archergun_fire");
	public static final SoundEvent gunArtifact = createSoundEvent("item.gun.artifact.fire", "artifact_fire");
	public static final SoundEvent gunAtomizer = createSoundEvent("item.gun.atomizer.fire", "atomizer_fire");
	public static final SoundEvent gunBallCannon = createSoundEvent("item.gun.ball_cannon.fire", "ball_cannon_fire");
	public static final SoundEvent gunBeatCannon1 = createSoundEvent("item.gun.beat_cannon_1.fire", "beat_cannon_1_fire");
	public static final SoundEvent gunBeatCannon2 = createSoundEvent("item.gun.beat_cannon_2.fire", "beat_cannon_2_fire");
	public static final SoundEvent gunBeatCannon3 = createSoundEvent("item.gun.beat_cannon_3.fire", "beat_cannon_3_fire");
	public static final SoundEvent gunBeatCannon4 = createSoundEvent("item.gun.beat_cannon_4.fire", "beat_cannon_4_fire");
	public static final SoundEvent gunBeatCannon5 = createSoundEvent("item.gun.beat_cannon_5.fire", "beat_cannon_5_fire");
	public static final SoundEvent gunBigBlast = createSoundEvent("item.gun.big_blast.fire", "big_blast_fire");
	public static final SoundEvent gunBlowpipe = createSoundEvent("item.gun.blowpipe.fire", "blowpipe_fire");
	public static final SoundEvent gunBoomCannon = createSoundEvent("item.gun.boom_cannon.fire", "boom_cannon_fire");
	public static final SoundEvent gunBubbleGun = createSoundEvent("item.gun.bubble_gun.fire", "bubble_gun_fire");
	public static final SoundEvent gunCarrotCannon = createSoundEvent("item.gun.carrot_cannon.fire", "carrot_cannon_fire");
	public static final SoundEvent gunChaingun = createSoundEvent("item.gun.chaingun.fire", "chaingun_fire");
	public static final SoundEvent gunChugger = createSoundEvent("item.gun.chugger.fire", "chugger_fire");
	public static final SoundEvent gunClowner = createSoundEvent("item.gun.clowner.fire", "clowner_fire");
	public static final SoundEvent gunColourCannon = createSoundEvent("item.gun.colour_cannon.fire", "colour_cannon_fire");
	public static final SoundEvent gunConfettiCannon = createSoundEvent("item.gun.confetti_cannon.fire", "confetti_cannon_fire");
	public static final SoundEvent gunDarkGun = createSoundEvent("item.gun.dark_gun.fire", "dark_gun_fire");
	public static final SoundEvent gunDischargeGun = createSoundEvent("item.gun.discharge_gun.fire", "discharge_gun_fire");
	public static final SoundEvent gunDoomGun = createSoundEvent("item.gun.doom_gun.fire", "doom_gun_fire");
	public static final SoundEvent gunDrainGun = createSoundEvent("item.gun.drain_gun.fire", "drain_gun_fire");
	public static final SoundEvent gunElectroCannon1 = createSoundEvent("item.gun.electro_cannon_1.fire", "electro_cannon_1_fire");
	public static final SoundEvent gunElectroCannon2 = createSoundEvent("item.gun.electro_cannon_2.fire", "electro_cannon_2_fire");
	public static final SoundEvent gunElectroCannon3 = createSoundEvent("item.gun.electro_cannon_3.fire", "electro_cannon_3_fire");
	public static final SoundEvent gunElectroCannon4 = createSoundEvent("item.gun.electro_cannon_4.fire", "electro_cannon_4_fire");
	public static final SoundEvent gunElectroCannon5 = createSoundEvent("item.gun.electro_cannon_5.fire", "electro_cannon_5_fire");
	public static final SoundEvent gunEnergyCannon = createSoundEvent("item.gun.energy_cannon.fire", "energy_cannon_fire");
	public static final SoundEvent gunFastRifle = createSoundEvent("item.gun.fast_rifle.fire", "fast_rifle_fire");
	public static final SoundEvent gunFlinger = createSoundEvent("item.gun.flinger.fire", "flinger_fire");
	public static final SoundEvent gunGasGun = createSoundEvent("item.gun.gas_gun.fire", "gas_gun_fire");
	public static final SoundEvent gunGaugeRifle = createSoundEvent("item.gun.gauge_rifle.fire", "gauge_rifle_fire");
	public static final SoundEvent gunGolemGun = createSoundEvent("item.gun.golem_gun.fire", "golem_gun_fire");
	public static final SoundEvent gunGravityBlaster = createSoundEvent("item.gun.gravity_blaster.fire", "gravity_blaster_fire");
	public static final SoundEvent gunHeatWave = createSoundEvent("item.gun.heat_wave.fire", "heat_wave_fire");
	public static final SoundEvent gunHighCannon = createSoundEvent("item.gun.high_cannon.fire", "high_cannon_fire");
	public static final SoundEvent gunIllusionRevolver = createSoundEvent("item.gun.illusion_revolver.fire", "illusion_revolver_fire");
	public static final SoundEvent gunIllusionSMG = createSoundEvent("item.gun.illusion_smg.fire", "illusion_smg_fire");
	public static final SoundEvent gunIonBlaster = createSoundEvent("item.gun.ion_blaster.fire", "ion_blaster_fire");
	public static final SoundEvent gunJackRocker = createSoundEvent("item.gun.jack_rocker.fire", "jack_rocker_fire");
	public static final SoundEvent gunKrasaunsDawn = createSoundEvent("item.gun.krasauns_dawn.fire", "krasauns_dawn_fire");
	public static final SoundEvent gunLightCannon = createSoundEvent("item.gun.light_cannon.fire", "light_cannon_fire");
	public static final SoundEvent gunLowerCannon = createSoundEvent("item.gun.lower_cannon.fire", "lower_cannon_fire");
	public static final SoundEvent gunMagicGun = createSoundEvent("item.gun.magic_gun.fire", "magic_gun_fire");
	public static final SoundEvent gunMechCannon = createSoundEvent("item.gun.mech_cannon.fire", "mech_cannon_fire");
	public static final SoundEvent gunMindBlaster = createSoundEvent("item.gun.mind_blaster.fire", "mind_blaster_fire");
	public static final SoundEvent gunMinigun = createSoundEvent("item.gun.minigun.fire", "minigun_fire");
	public static final SoundEvent gunMiniPistol = createSoundEvent("item.gun.mini_pistol.fire", "mini_pistol_fire");
	public static final SoundEvent gunMissileMaker = createSoundEvent("item.gun.missile_maker.fire", "missile_maker_fire");
	public static final SoundEvent gunMonster = createSoundEvent("item.gun.monster.fire", "monster_fire");
	public static final SoundEvent gunMoonShiner = createSoundEvent("item.gun.moon_shiner.fire", "moon_shiner_fire");
	public static final SoundEvent gunParalyzer = createSoundEvent("item.gun.paralyzer.fire", "paralyzer_fire");
	public static final SoundEvent gunPartyPopper = createSoundEvent("item.gun.party_popper.fire", "party_popper_fire");
	public static final SoundEvent gunRayGun = createSoundEvent("item.gun.ray_gun.fire", "ray_gun_fire");
	public static final SoundEvent gunReefer = createSoundEvent("item.gun.reefer.fire", "reefer_fire");
	public static final SoundEvent gunRevolution = createSoundEvent("item.gun.revolution.fire", "revolution_fire");
	public static final SoundEvent gunRevolver = createSoundEvent("item.gun.revolver.fire", "revolver_fire");
	public static final SoundEvent gunRoulette = createSoundEvent("item.gun.roulette.fire", "roulette_fire");
	public static final SoundEvent gunRPG = createSoundEvent("item.gun.rpg.fire", "rpg_fire");
	public static final SoundEvent gunShadowBlaster = createSoundEvent("item.gun.shadow_blaster.fire", "shadow_blaster_fire");
	public static final SoundEvent gunShotgun = createSoundEvent("item.gun.shotgun.fire", "shotgun_fire");
	public static final SoundEvent gunSlugger = createSoundEvent("item.gun.slugger.fire", "slugger_fire");
	public static final SoundEvent gunSniper = createSoundEvent("item.gun.sniper.fire", "sniper_fire");
	public static final SoundEvent gunSoulSpark = createSoundEvent("item.gun.soul_spark.fire", "soul_spark_fire");
	public static final SoundEvent gunSpaceGun = createSoundEvent("item.gun.space_gun.fire", "space_gun_fire");
	public static final SoundEvent gunSpaceRevolver = createSoundEvent("item.gun.space_revolver.fire", "space_revolver_fire");
	public static final SoundEvent gunSpiritShower = createSoundEvent("item.gun.spirit_shower.fire", "spirit_shower_fire");
	public static final SoundEvent gunSprayer = createSoundEvent("item.gun.sprayer.fire", "sprayer_fire");
	public static final SoundEvent gunSquadGun = createSoundEvent("item.gun.squad_gun.fire", "squad_gun_fire");
	public static final SoundEvent gunStampede = createSoundEvent("item.gun.stampede.fire", "stampede_fire");
	public static final SoundEvent gunStepCannon1 = createSoundEvent("item.gun.step_cannon_1.fire", "step_cannon_1_fire");
	public static final SoundEvent gunStepCannon2 = createSoundEvent("item.gun.step_cannon_2.fire", "step_cannon_2_fire");
	public static final SoundEvent gunStepCannon3 = createSoundEvent("item.gun.step_cannon_3.fire", "step_cannon_3_fire");
	public static final SoundEvent gunStepCannon4 = createSoundEvent("item.gun.step_cannon_4.fire", "step_cannon_4_fire");
	public static final SoundEvent gunStepCannon5 = createSoundEvent("item.gun.step_cannon_5.fire", "step_cannon_5_fire");
	public static final SoundEvent gunSwarmotron = createSoundEvent("item.gun.swarmotron.fire", "swarmotron_fire");
	public static final SoundEvent gunSynthCannon1 = createSoundEvent("item.gun.synth_cannon_1.fire", "synth_cannon_1_fire");
	public static final SoundEvent gunSynthCannon2 = createSoundEvent("item.gun.synth_cannon_2.fire", "synth_cannon_2_fire");
	public static final SoundEvent gunSynthCannon3 = createSoundEvent("item.gun.synth_cannon_3.fire", "synth_cannon_3_fire");
	public static final SoundEvent gunSynthCannon4 = createSoundEvent("item.gun.synth_cannon_4.fire", "synth_cannon_4_fire");
	public static final SoundEvent gunSynthCannon5 = createSoundEvent("item.gun.synth_cannon_5.fire", "synth_cannon_5_fire");
	public static final SoundEvent gunUpperCannon = createSoundEvent("item.gun.upper_cannon.fire", "upper_cannon_fire");
	public static final SoundEvent gunVibeCannon1 = createSoundEvent("item.gun.vibe_cannon_1.fire", "vibe_cannon_1_fire");
	public static final SoundEvent gunVibeCannon2 = createSoundEvent("item.gun.vibe_cannon_2.fire", "vibe_cannon_2_fire");
	public static final SoundEvent gunVibeCannon3 = createSoundEvent("item.gun.vibe_cannon_3.fire", "vibe_cannon_3_fire");
	public static final SoundEvent gunVibeCannon4 = createSoundEvent("item.gun.vibe_cannon_4.fire", "vibe_cannon_4_fire");
	public static final SoundEvent gunVibeCannon5 = createSoundEvent("item.gun.vibe_cannon_5.fire", "vibe_cannon_5_fire");
	public static final SoundEvent gunWhimsyWinder = createSoundEvent("item.gun.whimsy_winder.fire", "whimsy_winder_fire");
	public static final SoundEvent gunWitherCannon = createSoundEvent("item.gun.wither_cannon.fire", "wither_cannon_fire");
	public static final SoundEvent gunWithersWrath = createSoundEvent("item.gun.withers_wrath.fire", "withers_wrath_fire");
	public static final SoundEvent gunWoodRifle = createSoundEvent("item.gun.wood_rifle.fire", "wood_rifle_fire");
	public static final SoundEvent hauntingTableUse = createSoundEvent("event.haunting_table.use", "haunting_table_use");
	public static final SoundEvent heartStonePickup = createSoundEvent("event.heart_stone.use", "heart_stone_use");
	public static final SoundEvent heartStoneSpawn = createSoundEvent("event.heart_stone.spawn", "heart_stone_spawn");
	public static final SoundEvent heavyStep = createSoundEvent("entity.generic.heavy_step", "entity_generic_heavy_step");
	public static final SoundEvent hellfireImpact = createSoundEvent("item.thrown.hellfire.impact", "hellfire_impact");
	public static final SoundEvent infusionFail = createSoundEvent("event.infusion.fail", "infusion_fail");
	public static final SoundEvent infusionSuccess = createSoundEvent("event.infusion.success", "infusion_success");
	public static final SoundEvent level100 = createSoundEvent("entity.player.level_100", "player_level_100");
	public static final SoundEvent levelUp = createSoundEvent("entity.player.level_up", "player_level_up");
	public static final SoundEvent lunarCreationTableSuccess = createSoundEvent("event.lunar_creation_table.success", "lunar_creation_table_success");
	public static final SoundEvent lunarEnrichmentTableUse = createSoundEvent("event.lunar_enrichment_table.use", "lunar_enrichment_table_use");
	public static final SoundEvent makeRunes = createSoundEvent("event.runes.craft", "runes_craft");
	public static final SoundEvent mendingSuccess = createSoundEvent("event.mending.success", "mending_success");
	public static final SoundEvent mobAmphibiyteDeath = createSoundEvent("mob.amphibiyte.death", "mob_amphibiyte_death");
	public static final SoundEvent mobAmphibiyteHit = createSoundEvent("mob.amphibiyte.hit", "mob_amphibiyte_hit");
	public static final SoundEvent mobAmphibiyteLiving = createSoundEvent("mob.amphibiyte.living", "mob_amphibiyte_living");
	public static final SoundEvent mobAnemiaDeath = createSoundEvent("mob.anemia.death", "mob_anemia_death");
	public static final SoundEvent mobAnemiaHit = createSoundEvent("mob.anemia.hit", "mob_anemia_hit");
	public static final SoundEvent mobAnemiaLiving = createSoundEvent("mob.anemia.living", "mob_anemia_living");
	public static final SoundEvent mobApparitionDeath = createSoundEvent("mob.apparition.death", "mob_apparition_death");
	public static final SoundEvent mobApparitionHit = createSoundEvent("mob.apparition.hit", "mob_apparition_hit");
	public static final SoundEvent mobApparitionLiving = createSoundEvent("mob.apparition.living", "mob_apparition_living");
	public static final SoundEvent mobArcwormDeath = createSoundEvent("mob.arcworm.death", "mob_arcworm_death");
	public static final SoundEvent mobArcwormHit = createSoundEvent("mob.arcworm.hit", "mob_arcworm_hit");
	public static final SoundEvent mobArcwormLiving = createSoundEvent("mob.arcworm.living", "mob_arcworm_living");
	public static final SoundEvent mobArielLiving = createSoundEvent("mob.ariel.living", "mob_ariel_living");
	public static final SoundEvent mobArielDeath = createSoundEvent("mob.ariel.death", "mob_ariel_death");
	public static final SoundEvent mobArielHit = createSoundEvent("mob.ariel.hit", "mob_ariel_hit");
	public static final SoundEvent mobAutomatonDeath = createSoundEvent("mob.automaton.death", "mob_automaton_death");
	public static final SoundEvent mobAutomatonHit = createSoundEvent("mob.automaton.hit", "mob_automaton_hit");
	public static final SoundEvent mobAutomatonLiving = createSoundEvent("mob.automaton.living", "mob_automaton_living");
	public static final SoundEvent mobArkbackDeath = createSoundEvent("mob.arkback.death", "mob_arkback_death");
	public static final SoundEvent mobArkbackHit = createSoundEvent("mob.arkback.hit", "mob_arkback_hit");
	public static final SoundEvent mobArkbackLiving = createSoundEvent("mob.arkback.living", "mob_arkback_living");
	public static final SoundEvent mobModuloDeath = createSoundEvent("mob.modulo.death", "mob_modulo_death");
	public static final SoundEvent mobModuloHit = createSoundEvent("mob.modulo.hit", "mob_modulo_hit");
	public static final SoundEvent mobModuloLiving = createSoundEvent("mob.modulo.living", "mob_modulo_living");
	public static final SoundEvent mobMirageTeleport = createSoundEvent("mob.mirage.teleport", "mob_mirage_teleport");
	public static final SoundEvent mobEmperorBeastDeath = createSoundEvent("mob.emperor_beast.death", "mob_emperor_beast_death");
	public static final SoundEvent mobEmperorBeastHit = createSoundEvent("mob.emperor_beast.hit", "mob_emperor_beast_hit");
	public static final SoundEvent mobEmperorBeastLiving = createSoundEvent("mob.emperor_beast.living", "mob_emperor_beast_living");
	public static final SoundEvent mobEilosapienDeath = createSoundEvent("mob.eilosapien.death", "mob_eilosapien_death");
	public static final SoundEvent mobEilosapienHit = createSoundEvent("mob.eilosapien.hit", "mob_eilosapien_hit");
	public static final SoundEvent mobEilosapienLiving = createSoundEvent("mob.eilosapien.living", "mob_eilosapien_living");
	public static final SoundEvent mobEchodarDeath = createSoundEvent("mob.echodar.death", "mob_echodar_death");
	public static final SoundEvent mobEchodarHit = createSoundEvent("mob.echodar.hit", "mob_echodar_hit");
	public static final SoundEvent mobEchodarLiving = createSoundEvent("mob.echodar.living", "mob_echodar_living");
	public static final SoundEvent mobPincherDeath = createSoundEvent("mob.pincher.death", "mob_pincher_death");
	public static final SoundEvent mobPincherHit = createSoundEvent("mob.pincher.hit", "mob_pincher_hit");
	public static final SoundEvent mobPincherLiving = createSoundEvent("mob.pincher.living", "mob_pincher_living");
	public static final SoundEvent mobCryptidDeath = createSoundEvent("mob.cryptid.death", "mob_cryptid_death");
	public static final SoundEvent mobCryptidHit = createSoundEvent("mob.cryptid.hit", "mob_cryptid_hit");
	public static final SoundEvent mobCryptidLiving = createSoundEvent("mob.cryptid.living", "mob_cryptid_living");
	public static final SoundEvent mobBaneLiving = createSoundEvent("mob.bane.living", "mob_bane_living");
	public static final SoundEvent mobBaneDeath = createSoundEvent("mob.bane.death", "mob_bane_death");
	public static final SoundEvent mobBloodmistDeath = createSoundEvent("mob.bloodmist.death", "mob_bloodmist_death");
	public static final SoundEvent mobBloodmistHit = createSoundEvent("mob.bloodmist.hit", "mob_bloodmist_hit");
	public static final SoundEvent mobBloodmistLiving = createSoundEvent("mob.bloodmist.living", "mob_bloodmist_living");
	public static final SoundEvent mobBloodsuckerDeath = createSoundEvent("mob.bloodsucker.death", "mob_bloodsucker_death");
	public static final SoundEvent mobBloodsuckerHit = createSoundEvent("mob.bloodsucker.hit", "mob_bloodsucker_hit");
	public static final SoundEvent mobBloodsuckerLiving = createSoundEvent("mob.bloodsucker.living", "mob_bloodsucker_living");
	public static final SoundEvent mobBombCarrierHit = createSoundEvent("mob.bomb_carrier.hit", "mob_bomb_carrier_hit");
	public static final SoundEvent mobBombCarrierLiving = createSoundEvent("mob.bomb_carrier.living", "mob_bomb_carrier_living");
	public static final SoundEvent mobBonebackDeath = createSoundEvent("mob.boneback.death", "mob_boneback_death");
	public static final SoundEvent mobBonebackHit = createSoundEvent("mob.boneback.hit", "mob_boneback_hit");
	public static final SoundEvent mobBonebackLiving = createSoundEvent("mob.boneback.living", "mob_boneback_living");
	public static final SoundEvent mobBugeyeDeath = createSoundEvent("mob.bugeye.death", "mob_bugeye_death");
	public static final SoundEvent mobBugeyeHit = createSoundEvent("mob.bugeye.hit", "mob_bugeye_hit");
	public static final SoundEvent mobBugeyeLiving = createSoundEvent("mob.bugeye.living", "mob_bugeye_living");
	public static final SoundEvent mobBushBabyDeath = createSoundEvent("mob.bush_baby.death", "mob_bush_baby_death");
	public static final SoundEvent mobBushBabyHit = createSoundEvent("mob.bush_baby.hit", "mob_bush_baby_hit");
	public static final SoundEvent mobBushBabyLiving = createSoundEvent("mob.bush_baby.living", "mob_bush_baby_living");
	public static final SoundEvent mobCeleveClownDeath = createSoundEvent("mob.celeve_clown.death", "mob_celeve_clown_death");
	public static final SoundEvent mobCeleveClownHit = createSoundEvent("mob.celeve_clown.hit", "mob_celeve_clown_hit");
	public static final SoundEvent mobCeleveClownLiving = createSoundEvent("mob.celeve_clown.living", "mob_celeve_clown_living");
	public static final SoundEvent mobChargerDeath = createSoundEvent("mob.charger.death", "mob_charger_death");
	public static final SoundEvent mobChargerHit = createSoundEvent("mob.charger.hit", "mob_charger_hit");
	public static final SoundEvent mobChargerLiving = createSoundEvent("mob.charger.living", "mob_charger_living");
	public static final SoundEvent mobChimeraDeath = createSoundEvent("mob.chimera.death", "mob_chimera_death");
	public static final SoundEvent mobChimeraHit = createSoundEvent("mob.chimera.hit", "mob_chimera_hit");
	public static final SoundEvent mobChimeraLiving = createSoundEvent("mob.chimera.living", "mob_chimera_living");
	public static final SoundEvent mobChomperHit = createSoundEvent("mob.chomper.hit", "mob_chomper_hit");
	public static final SoundEvent mobChomperLiving = createSoundEvent("mob.chomper.living", "mob_chomper_living");
	public static final SoundEvent mobClownDeath = createSoundEvent("mob.clown.death", "mob_clown_death");
	public static final SoundEvent mobClownHit = createSoundEvent("mob.clown.hit", "mob_clown_hit");
	public static final SoundEvent mobClownLiving = createSoundEvent("mob.clown.living", "mob_clown_living");
	public static final SoundEvent mobClunkheadDeath = createSoundEvent("mob.clunkhead.death", "mob_clunkhead_death");
	public static final SoundEvent mobCompeerDeath = createSoundEvent("mob.compeer.death", "mob_compeer_death");
	public static final SoundEvent mobCompeerHit = createSoundEvent("mob.compeer.hit", "mob_compeer_hit");
	public static final SoundEvent mobCompeerLiving = createSoundEvent("mob.compeer.living", "mob_compeer_living");
	public static final SoundEvent mobGrillfaceDeath = createSoundEvent("mob.grillface.death", "mob_grillface_death");
	public static final SoundEvent mobGrillfaceHit = createSoundEvent("mob.grillface.hit", "mob_grillface_hit");
	public static final SoundEvent mobGrillfaceLiving = createSoundEvent("mob.grillface.living", "mob_grillface_living");
	public static final SoundEvent mobGrillfaceScare = createSoundEvent("mob.grillface.scare", "mob_grillface_scare");
	public static final SoundEvent mobCarrotopDeath = createSoundEvent("mob.carrotop.death", "mob_carrotop_death");
	public static final SoundEvent mobCarrotopHit = createSoundEvent("mob.carrotop.hit", "mob_carrotop_hit");
	public static final SoundEvent mobCarrotopLiving = createSoundEvent("mob.carrotop.living", "mob_carrotop_living");
	public static final SoundEvent mobArchvineDeath = createSoundEvent("mob.archvine.death", "mob_archvine_death");
	public static final SoundEvent mobArchvineHit = createSoundEvent("mob.archvine.hit", "mob_archvine_hit");
	public static final SoundEvent mobArchvineLiving = createSoundEvent("mob.archvine.living", "mob_archvine_living");
	public static final SoundEvent mobStalkerDeath = createSoundEvent("mob.stalker.death", "mob_stalker_death");
	public static final SoundEvent mobStalkerHit = createSoundEvent("mob.stalker.hit", "mob_stalker_hit");
	public static final SoundEvent mobStalkerLiving = createSoundEvent("mob.stalker.living", "mob_stalker_living");
	public static final SoundEvent mobMerkyreDeath = createSoundEvent("mob.merkyre.death", "mob_merkyre_death");
	public static final SoundEvent mobMerkyreHit = createSoundEvent("mob.merkyre.hit", "mob_merkyre_hit");
	public static final SoundEvent mobMerkyreLiving = createSoundEvent("mob.merkyre.living", "mob_merkyre_living");
	public static final SoundEvent mobLurkerDeath = createSoundEvent("mob.lurker.death", "mob_lurker_death");
	public static final SoundEvent mobLurkerHit = createSoundEvent("mob.lurker.hit", "mob_lurker_hit");
	public static final SoundEvent mobLurkerLiving = createSoundEvent("mob.lurker.living", "mob_lurker_living");
	public static final SoundEvent mobLostSoulDeath = createSoundEvent("mob.lost_soul.death", "mob_lost_soul_death");
	public static final SoundEvent mobLostSoulHit = createSoundEvent("mob.lost_soul.hit", "mob_lost_soul_hit");
	public static final SoundEvent mobLostSoulLiving = createSoundEvent("mob.lost_soul.living", "mob_lost_soul_living");
	public static final SoundEvent mobDustStriderDeath = createSoundEvent("mob.dust_strider.death", "mob_dust_strider_death");
	public static final SoundEvent mobDustStriderHit = createSoundEvent("mob.dust_strider.hit", "mob_dust_strider_hit");
	public static final SoundEvent mobDustStriderLiving = createSoundEvent("mob.dust_strider.living", "mob_dust_strider_living");
	public static final SoundEvent mobDusteivaDeath = createSoundEvent("mob.dusteiva.death", "mob_dusteiva_death");
	public static final SoundEvent mobDusteivaHit = createSoundEvent("mob.dusteiva.hit", "mob_dusteiva_hit");
	public static final SoundEvent mobDusteivaLiving = createSoundEvent("mob.dusteiva.living", "mob_dusteiva_living");
	public static final SoundEvent mobDevourerDeath = createSoundEvent("mob.devourer.death", "mob_devourer_death");
	public static final SoundEvent mobDevourerHit = createSoundEvent("mob.devourer.hit", "mob_devourer_hit");
	public static final SoundEvent mobDevourerLiving = createSoundEvent("mob.devourer.living", "mob_devourer_living");
	public static final SoundEvent mobCrusiliskDeath = createSoundEvent("mob.crusilisk.death", "mob_crusilisk_death");
	public static final SoundEvent mobCrusiliskHit = createSoundEvent("mob.crusilisk.hit", "mob_crusilisk_hit");
	public static final SoundEvent mobCrusiliskLiving = createSoundEvent("mob.crusilisk.living", "mob_crusilisk_living");
	public static final SoundEvent mobCrusiliskScream = createSoundEvent("mob.crusilisk.scream", "mob_crusilisk_scream");
	public static final SoundEvent mobDustonHit = createSoundEvent("mob.duston.hit", "mob_duston_hit");
	public static final SoundEvent mobBasiliskDeath = createSoundEvent("mob.basilisk.death", "mob_basilisk_death");
	public static final SoundEvent mobBasiliskHit = createSoundEvent("mob.basilisk.hit", "mob_basilisk_hit");
	public static final SoundEvent mobBasiliskLiving = createSoundEvent("mob.basilisk.living", "mob_basilisk_living");
	public static final SoundEvent mobArkzyneDeath = createSoundEvent("mob.arkzyne.death", "mob_arkzyne_death");
	public static final SoundEvent mobArkzyneHit = createSoundEvent("mob.arkzyne.hit", "mob_arkzyne_hit");
	public static final SoundEvent mobArkzyneLiving = createSoundEvent("mob.arkzyne.living", "mob_arkzyne_living");
	public static final SoundEvent mobRockbiterDeath = createSoundEvent("mob.rockbiter.death", "mob_rockbiter_death");
	public static final SoundEvent mobRockbiterHit = createSoundEvent("mob.rockbiter.hit", "mob_rockbiter_hit");
	public static final SoundEvent mobRockbiterLiving = createSoundEvent("mob.rockbiter.living", "mob_rockbiter_living");
	public static final SoundEvent mobNipperDeath = createSoundEvent("mob.nipper.death", "mob_nipper_death");
	public static final SoundEvent mobNipperHit = createSoundEvent("mob.nipper.hit", "mob_nipper_hit");
	public static final SoundEvent mobNipperLiving = createSoundEvent("mob.nipper.living", "mob_nipper_living");
	public static final SoundEvent mobDoublerDeath = createSoundEvent("mob.doubler.death", "mob_doubler_death");
	public static final SoundEvent mobDoublerHit = createSoundEvent("mob.doubler.hit", "mob_doubler_hit");
	public static final SoundEvent mobDoublerLiving = createSoundEvent("mob.doubler.living", "mob_doubler_living");
	public static final SoundEvent mobCaveCreepDeath = createSoundEvent("mob.cave_creep.death", "mob_cave_creep_death");
	public static final SoundEvent mobCaveCreepHit = createSoundEvent("mob.cave_creep.hit", "mob_cave_creep_hit");
	public static final SoundEvent mobCaveCreepLiving = createSoundEvent("mob.cave_creep.living", "mob_cave_creep_living");
	public static final SoundEvent mobDestructorDeath = createSoundEvent("mob.destructor.death", "mob_destructor_death");
	public static final SoundEvent mobDestructorHit = createSoundEvent("mob.destructor.hit", "mob_destructor_hit");
	public static final SoundEvent mobDestructorLiving = createSoundEvent("mob.destructor.living", "mob_destructor_living");
	public static final SoundEvent mobCaveBugDeath = createSoundEvent("mob.cave_bug.death", "mob_cave_bug_death");
	public static final SoundEvent mobCaveBugHit = createSoundEvent("mob.cave_bug.hit", "mob_cave_bug_hit");
	public static final SoundEvent mobCaveBugLiving = createSoundEvent("mob.cave_bug.living", "mob_cave_bug_living");
	public static final SoundEvent mobArcbeastDeath = createSoundEvent("mob.arcbeast.death", "mob_arcbeast_death");
	public static final SoundEvent mobArcbeastHit = createSoundEvent("mob.arcbeast.hit", "mob_arcbeast_hit");
	public static final SoundEvent mobArcbeastLiving = createSoundEvent("mob.arcbeast.living", "mob_arcbeast_living");
	public static final SoundEvent mobAxiolightDeath = createSoundEvent("mob.axiolight.death", "mob_axiolight_death");
	public static final SoundEvent mobAxiolightHit = createSoundEvent("mob.axiolight.hit", "mob_axiolight_hit");
	public static final SoundEvent mobAxiolightLiving = createSoundEvent("mob.axiolight.living", "mob_axiolight_living");
	public static final SoundEvent mobLuxocronDeath = createSoundEvent("mob.luxocron.death", "mob_luxocron_death");
	public static final SoundEvent mobLuxocronHit = createSoundEvent("mob.luxocron.hit", "mob_luxocron_hit");
	public static final SoundEvent mobLuxocronLiving = createSoundEvent("mob.luxocron.living", "mob_luxocron_living");
	public static final SoundEvent mobSoulscorneDeath = createSoundEvent("mob.soulscorne.death", "mob_soulscorne_death");
	public static final SoundEvent mobSoulscorneHit = createSoundEvent("mob.soulscorne.hit", "mob_soulscorne_hit");
	public static final SoundEvent mobSoulscorneLiving = createSoundEvent("mob.soulscorne.living", "mob_soulscorne_living");
	public static final SoundEvent mobSoulvyreDeath = createSoundEvent("mob.soulvyre.death", "mob_soulvyre_death");
	public static final SoundEvent mobSoulvyreHit = createSoundEvent("mob.soulvyre.hit", "mob_soulvyre_hit");
	public static final SoundEvent mobSoulvyreLiving = createSoundEvent("mob.soulvyre.living", "mob_soulvyre_living");
	public static final SoundEvent mobStimuloDeath = createSoundEvent("mob.stimulo.death", "mob_stimulo_death");
	public static final SoundEvent mobStimuloHit = createSoundEvent("mob.stimulo.hit", "mob_stimulo_hit");
	public static final SoundEvent mobStimuloLiving = createSoundEvent("mob.stimulo.living", "mob_stimulo_living");
	public static final SoundEvent mobStimulosusLiving = createSoundEvent("mob.stimulosus.living", "mob_stimulosus_living");
	public static final SoundEvent mobSyskerDeath = createSoundEvent("mob.sysker.death", "mob_sysker_death");
	public static final SoundEvent mobSyskerHit = createSoundEvent("mob.sysker.hit", "mob_sysker_hit");
	public static final SoundEvent mobSyskerLiving = createSoundEvent("mob.sysker.living", "mob_sysker_living");
	public static final SoundEvent mobAlarmoDeath = createSoundEvent("mob.alarmo.death", "mob_alarmo_death");
	public static final SoundEvent mobAlarmoHit = createSoundEvent("mob.alarmo.hit", "mob_alarmo_hit");
	public static final SoundEvent mobAlarmoLiving = createSoundEvent("mob.alarmo.living", "mob_alarmo_living");
	public static final SoundEvent mobGadgetoidDeath = createSoundEvent("mob.gadgetoid.death", "mob_gadgetoid_death");
	public static final SoundEvent mobGadgetoidHit = createSoundEvent("mob.gadgetoid.hit", "mob_gadgetoid_hit");
	public static final SoundEvent mobGadgetoidLiving = createSoundEvent("mob.gadgetoid.living", "mob_gadgetoid_living");
	public static final SoundEvent mobGrocculateDeath = createSoundEvent("mob.grocculate.death", "mob_grocculate_death");
	public static final SoundEvent mobGrocculateHit = createSoundEvent("mob.grocculate.hit", "mob_grocculate_hit");
	public static final SoundEvent mobGrocculateLiving = createSoundEvent("mob.grocculate.living", "mob_grocculate_living");
	public static final SoundEvent mobNightwingDeath = createSoundEvent("mob.nightwing.death", "mob_nightwing_death");
	public static final SoundEvent mobNightwingHit = createSoundEvent("mob.nightwing.hit", "mob_nightwing_hit");
	public static final SoundEvent mobNightwingLiving = createSoundEvent("mob.nightwing.living", "mob_nightwing_living");
	public static final SoundEvent mobToxxulousDeath = createSoundEvent("mob.toxxulous.death", "mob_toxxulous_death");
	public static final SoundEvent mobToxxulousHit = createSoundEvent("mob.toxxulous.hit", "mob_toxxulous_hit");
	public static final SoundEvent mobToxxulousLiving = createSoundEvent("mob.toxxulous.living", "mob_toxxulous_living");
	public static final SoundEvent mobConiferonLiving = createSoundEvent("mob.coniferon.living", "mob_coniferon_living");
	public static final SoundEvent mobConiferonDeath = createSoundEvent("mob.coniferon.death", "mob_coniferon_death");
	public static final SoundEvent mobConiferonHit = createSoundEvent("mob.coniferon.hit", "mob_coniferon_hit");
	public static final SoundEvent mobCorallusLiving = createSoundEvent("mob.corallus.living", "mob_corallus_living");
	public static final SoundEvent mobCorallusDeath = createSoundEvent("mob.corallus.death", "mob_corallus_death");
	public static final SoundEvent mobCorallusHit = createSoundEvent("mob.corallus.hit", "mob_corallus_hit");
	public static final SoundEvent mobCorallusTaunt = createSoundEvent("mob.corallus.taunt", "mob_corallus_taunt");
	public static final SoundEvent mobCottonCandorLiving = createSoundEvent("mob.cotton_candor.living", "mob_cotton_candor_living");
	public static final SoundEvent mobCottonCandorDeath = createSoundEvent("mob.cotton_candor.death", "mob_cotton_candor_death");
	public static final SoundEvent mobCottonCandorHit = createSoundEvent("mob.cotton_candor.hit", "mob_cotton_candor_hit");
	public static final SoundEvent mobCraexxeusLiving = createSoundEvent("mob.craexxeus.living", "mob_craexxeus_living");
	public static final SoundEvent mobCraexxeusDeath = createSoundEvent("mob.craexxeus.death", "mob_craexxeus_death");
	public static final SoundEvent mobCraexxeusHit = createSoundEvent("mob.craexxeus.hit", "mob_craexxeus_hit");
	public static final SoundEvent mobCraexxeusCharge = createSoundEvent("mob.craexxeus.charge", "mob_craexxeus_charge");
	public static final SoundEvent mobCreepoidLiving = createSoundEvent("mob.creepoid.living", "mob_creepoid_living");
	public static final SoundEvent mobCreepoidDeath = createSoundEvent("mob.creepoid.death", "mob_creepoid_death");
	public static final SoundEvent mobCreepoidHit = createSoundEvent("mob.creepoid.hit", "mob_creepoid_hit");
	public static final SoundEvent mobCrystalConstructDeath = createSoundEvent("mob.crystal_construct.death", "mob_crystal_construct_death");
	public static final SoundEvent mobCrystalConstructHit = createSoundEvent("mob.crystal_construct.hit", "mob_crystal_construct_hit");
	public static final SoundEvent mobCrystalConstructLiving = createSoundEvent("mob.crystal_construct.living", "mob_crystal_construct_living");
	public static final SoundEvent mobCyclopsDeath = createSoundEvent("mob.cyclops.death", "mob_cyclops_death");
	public static final SoundEvent mobCyclopsHit = createSoundEvent("mob.cyclops.hit", "mob_cyclops_hit");
	public static final SoundEvent mobCyclopsLiving = createSoundEvent("mob.cyclops.living", "mob_cyclops_living");
	public static final SoundEvent mobDarkBeastDeath = createSoundEvent("mob.dark_beast.death", "mob_dark_beast_death");
	public static final SoundEvent mobDarkBeastHit = createSoundEvent("mob.dark_beast.hit", "mob_dark_beast_hit");
	public static final SoundEvent mobDarkBeastLiving = createSoundEvent("mob.dark_beast.living", "mob_dark_beast_living");
	public static final SoundEvent mobDeathHunterDeath = createSoundEvent("mob.death_hunter.death", "mob_vdeath_hunter_death");
	public static final SoundEvent mobDeathHunterHit = createSoundEvent("mob.death_hunter.hit", "mob_death_hunter_hit");
	public static final SoundEvent mobDeathHunterLiving = createSoundEvent("mob.death_hunter.living", "mob_death_hunter_living");
	public static final SoundEvent mobDicerDeath = createSoundEvent("mob.dicer.death", "mob_dicer_death");
	public static final SoundEvent mobDicerHit = createSoundEvent("mob.dicer.hit", "mob_dicer_hit");
	public static final SoundEvent mobDicerLiving = createSoundEvent("mob.dicer.living", "mob_dicer_living");
	public static final SoundEvent mobDistorterDeath = createSoundEvent("mob.distorter.death", "mob_distorter_death");
	public static final SoundEvent mobDistorterHit = createSoundEvent("mob.distorter.hit", "mob_distorter_hit");
	public static final SoundEvent mobDistorterLiving = createSoundEvent("mob.distorter.living", "mob_distorter_living");
	public static final SoundEvent mobDracyonLiving = createSoundEvent("mob.dracyon.living", "mob_dracyon_living");
	public static final SoundEvent mobDracyonDeath = createSoundEvent("mob.dracyon.death", "mob_dracyon_death");
	public static final SoundEvent mobDraggyDeath = createSoundEvent("mob.draggy.death", "mob_draggy_death");
	public static final SoundEvent mobDraggyHit = createSoundEvent("mob.draggy.hit", "mob_draggy_hit");
	public static final SoundEvent mobDraggyLiving = createSoundEvent("mob.draggy.living", "mob_draggy_living");
	public static final SoundEvent mobDyrehornDeath = createSoundEvent("mob.dyrehorn.death", "mob_dyrehorn_death");
	public static final SoundEvent mobDyrehornHit = createSoundEvent("mob.dyrehorn.hit", "mob_dyrehorn_hit");
	public static final SoundEvent mobDyrehornLiving = createSoundEvent("mob.dyrehorn.living", "mob_dyrehorn_living");
	public static final SoundEvent mobElkanyneDeath = createSoundEvent("mob.elkanyne.death", "mob_elkanyne_death");
	public static final SoundEvent mobElkanyneHit = createSoundEvent("mob.elkanyne.hit", "mob_elkanyne_hit");
	public static final SoundEvent mobElkanyneLiving = createSoundEvent("mob.elkanyne.living", "mob_elkanyne_living");
	public static final SoundEvent mobElusiveLiving = createSoundEvent("mob.elusive.living", "mob_elusive_living");
	public static final SoundEvent mobElusiveDeath = createSoundEvent("mob.elusive.death", "mob_elusive_death");
	public static final SoundEvent mobElusiveHit = createSoundEvent("mob.elusive.hit", "mob_elusive_hit");
	public static final SoundEvent mobEmbrakeDeath = createSoundEvent("mob.embrake.death", "mob_embrake_death");
	public static final SoundEvent mobEmbrakeHit = createSoundEvent("mob.embrake.hit", "mob_embrake_hit");
	public static final SoundEvent mobEmbrakeLiving = createSoundEvent("mob.embrake.living", "mob_embrake_living");
	public static final SoundEvent mobEverbeastHit = createSoundEvent("mob.everbeast.hit", "mob_everbeast_hit");
	public static final SoundEvent mobEverbeastLiving = createSoundEvent("mob.everbeast.living", "mob_everbeast_living");
	public static final SoundEvent mobFacelessRunnerDeath = createSoundEvent("mob.faceless_runner.death", "mob_faceless_runner_death");
	public static final SoundEvent mobFacelessRunnerHit = createSoundEvent("mob.faceless_runner.hit", "mob_faceless_runner_hit");
	public static final SoundEvent mobFacelessRunnerLiving = createSoundEvent("mob.faceless_runner.living", "mob_faceless_runner_living");
	public static final SoundEvent mobFadeDeath = createSoundEvent("mob.fade.death", "mob_fade_death");
	public static final SoundEvent mobFadeHit = createSoundEvent("mob.fade.hit", "mob_fade_hit");
	public static final SoundEvent mobFadeLiving = createSoundEvent("mob.fade.living", "mob_fade_living");
	public static final SoundEvent mobToranoDeath = createSoundEvent("mob.torano.death", "mob_torano_death");
	public static final SoundEvent mobToranoHit = createSoundEvent("mob.torano.hit", "mob_torano_hit");
	public static final SoundEvent mobToranoLiving = createSoundEvent("mob.torano.living", "mob_torano_living");
	public static final SoundEvent mobAirheadDeath = createSoundEvent("mob.airhead.death", "mob_airhead_death");
	public static final SoundEvent mobAirheadHit = createSoundEvent("mob.airhead.hit", "mob_airhead_hit");
	public static final SoundEvent mobAirheadLiving = createSoundEvent("mob.airhead.living", "mob_airhead_living");
	public static final SoundEvent mobTharaflyDeath = createSoundEvent("mob.tharafly.death", "mob_tharafly_death");
	public static final SoundEvent mobTharaflyHit = createSoundEvent("mob.tharafly.hit", "mob_tharafly_hit");
	public static final SoundEvent mobTharaflyLiving = createSoundEvent("mob.tharafly.living", "mob_tharafly_living");
	public static final SoundEvent mobSquigglerDeath = createSoundEvent("mob.squiggler.death", "mob_squiggler_death");
	public static final SoundEvent mobSquigglerHit = createSoundEvent("mob.squiggler.hit", "mob_squiggler_hit");
	public static final SoundEvent mobSquigglerLiving = createSoundEvent("mob.squiggler.living", "mob_squiggler_living");
	public static final SoundEvent mobStingerLiving = createSoundEvent("mob.stinger.living", "mob_stinger_living");
	public static final SoundEvent mobStingerHit = createSoundEvent("mob.stinger.hit", "mob_stinger_hit");
	public static final SoundEvent mobRamradonDeath = createSoundEvent("mob.ramradon.death", "mob_ramradon_death");
	public static final SoundEvent mobRamradonHit = createSoundEvent("mob.ramradon.hit", "mob_ramradon_hit");
	public static final SoundEvent mobRamradonLiving = createSoundEvent("mob.ramradon.living", "mob_ramradon_living");
	public static final SoundEvent mobWebReaperDeath = createSoundEvent("mob.web_reaper.death", "mob_web_reaper_death");
	public static final SoundEvent mobWebReaperHit = createSoundEvent("mob.web_reaper.hit", "mob_web_reaper_hit");
	public static final SoundEvent mobWebReaperLiving = createSoundEvent("mob.web_reaper.living", "mob_web_reaper_living");
	public static final SoundEvent mobOcculentDeath = createSoundEvent("mob.occulent.death", "mob_occulent_death");
	public static final SoundEvent mobOcculentHit = createSoundEvent("mob.occulent.hit", "mob_occulent_hit");
	public static final SoundEvent mobMermageDeath = createSoundEvent("mob.mermage.death", "mob_mermage_death");
	public static final SoundEvent mobCreeperlockTeleport = createSoundEvent("mob.creeperlock.teleport", "mob_creeperlock_teleport");
	public static final SoundEvent mobMermageHit = createSoundEvent("mob.mermage.hit", "mob_mermage_hit");
	public static final SoundEvent mobOcculentLiving = createSoundEvent("mob.occulent.living", "mob_occulent_living");
	public static final SoundEvent mobJaweDeath = createSoundEvent("mob.jawe.death", "mob_jawe_death");
	public static final SoundEvent mobJaweHit = createSoundEvent("mob.jawe.hit", "mob_jawe_hit");
	public static final SoundEvent mobJaweLiving = createSoundEvent("mob.jawe.living", "mob_jawe_living");
	public static final SoundEvent mobCreepirdDeath = createSoundEvent("mob.creepird.death", "mob_creepird_death");
	public static final SoundEvent mobCreepirdHit = createSoundEvent("mob.creepird.hit", "mob_creepird_hit");
	public static final SoundEvent mobCreepirdLiving = createSoundEvent("mob.creepird.living", "mob_creepird_living");
	public static final SoundEvent mobPodPlantDeath = createSoundEvent("mob.pod_plant.death", "mob_pod_plant_death");
	public static final SoundEvent mobPodPlantHit = createSoundEvent("mob.pod_plant.hit", "mob_pod_plant_hit");
	public static final SoundEvent mobPodPlantLiving = createSoundEvent("mob.pod_plant.living", "mob_pod_plant_living");
	public static final SoundEvent mobSpectralWizardDeath = createSoundEvent("mob.spectral_wizard.death", "mob_spectral_wizard_death");
	public static final SoundEvent mobSpectralWizardHit = createSoundEvent("mob.spectral_wizard.hit", "mob_spectral_wizard_hit");
	public static final SoundEvent mobSpectralWizardLiving = createSoundEvent("mob.spectral_wizard.living", "mob_spectral_wizard_living");
	public static final SoundEvent mobEyeCreatureDeath = createSoundEvent("mob.eye_creature.death", "mob_eye_creature_death");
	public static final SoundEvent mobEyeCreatureHit = createSoundEvent("mob.eye_creature.hit", "mob_eye_creature_hit");
	public static final SoundEvent mobEyeCreatureLiving = createSoundEvent("mob.eye_creature.living", "mob_eye_creature_living");
	public static final SoundEvent mobBouncerDeath = createSoundEvent("mob.bouncer.death", "mob_bouncer_death");
	public static final SoundEvent mobBouncerHit = createSoundEvent("mob.bouncer.hit", "mob_bouncer_hit");
	public static final SoundEvent mobBouncerLiving = createSoundEvent("mob.bouncer.living", "mob_bouncer_living");
	public static final SoundEvent mobTortioneDeath = createSoundEvent("mob.tortione.death", "mob_tortione_death");
	public static final SoundEvent mobTortioneHit = createSoundEvent("mob.tortione.hit", "mob_tortione_hit");
	public static final SoundEvent mobTortioneLiving = createSoundEvent("mob.tortione.living", "mob_tortione_living");
	public static final SoundEvent mobTerradonDeath = createSoundEvent("mob.terradon.death", "mob_terradon_death");
	public static final SoundEvent mobTerradonHit = createSoundEvent("mob.terradon.hit", "mob_terradon_hit");
	public static final SoundEvent mobTerradonLiving = createSoundEvent("mob.terradon.living", "mob_terradon_living");
	public static final SoundEvent mobSpinoledonDeath = createSoundEvent("mob.spinoledon.death", "mob_spinoledon_death");
	public static final SoundEvent mobSpinoledonHit = createSoundEvent("mob.spinoledon.hit", "mob_spinoledon_hit");
	public static final SoundEvent mobSpinoledonLiving = createSoundEvent("mob.spinoledon.living", "mob_spinoledon_living");
	public static final SoundEvent mobSabretoothDeath = createSoundEvent("mob.sabretooth.death", "mob_sabretooth_death");
	public static final SoundEvent mobSabretoothHit = createSoundEvent("mob.sabretooth.hit", "mob_sabretooth_hit");
	public static final SoundEvent mobSabretoothLiving = createSoundEvent("mob.sabretooth.living", "mob_sabretooth_living");
	public static final SoundEvent mobMegatheriumDeath = createSoundEvent("mob.megatherium.death", "mob_megatherium_death");
	public static final SoundEvent mobMegatheriumHit = createSoundEvent("mob.megatherium.hit", "mob_megatherium_hit");
	public static final SoundEvent mobMegatheriumLiving = createSoundEvent("mob.megatherium.living", "mob_megatherium_living");
	public static final SoundEvent mobKaiyuDeath = createSoundEvent("mob.kaiyu.death", "mob_kaiyu_death");
	public static final SoundEvent mobKaiyuHit = createSoundEvent("mob.kaiyu.hit", "mob_kaiyu_hit");
	public static final SoundEvent mobKaiyuLiving = createSoundEvent("mob.kaiyu.living", "mob_kaiyu_living");
	public static final SoundEvent mobGiantSlugDeath = createSoundEvent("mob.giant_slug.death", "mob_giant_slug_death");
	public static final SoundEvent mobGiantSlugHit = createSoundEvent("mob.giant_slug.hit", "mob_giant_slug_hit");
	public static final SoundEvent mobGiantSlugLiving = createSoundEvent("mob.giant_slug.living", "mob_giant_slug_living");
	public static final SoundEvent mobGiantSlugStep = createSoundEvent("mob.giant_slug.step", "mob_giant_slug_step");
	public static final SoundEvent mobDiocusDeath = createSoundEvent("mob.diocus.death", "mob_diocus_death");
	public static final SoundEvent mobDiocusHit = createSoundEvent("mob.diocus.hit", "mob_diocus_hit");
	public static final SoundEvent mobDiocusLiving = createSoundEvent("mob.diocus.living", "mob_diocus_living");
	public static final SoundEvent mobPhantomHit = createSoundEvent("mob.phantom.hit", "mob_phantom_hit");
	public static final SoundEvent mobPhantomLiving = createSoundEvent("mob.phantom.living", "mob_phantom_living");
	public static final SoundEvent mobNightmareSpiderDeath = createSoundEvent("mob.nightmare_spider.death", "mob_nightmare_spider_death");
	public static final SoundEvent mobNightmareSpiderHit = createSoundEvent("mob.nightmare_spider.hit", "mob_nightmare_spider_hit");
	public static final SoundEvent mobNightmareSpiderLiving = createSoundEvent("mob.nightmare_spider.living", "mob_nightmare_spider_living");
	public static final SoundEvent mobBansheeDeath = createSoundEvent("mob.banshee.death", "mob_banshee_death");
	public static final SoundEvent mobBansheeHit = createSoundEvent("mob.banshee.hit", "mob_banshee_hit");
	public static final SoundEvent mobBansheeLiving = createSoundEvent("mob.banshee.living", "mob_banshee_living");
	public static final SoundEvent mobRunicGolemHit = createSoundEvent("mob.runic_golem.hit", "mob_runic_golem_hit");
	public static final SoundEvent mobSpiritLiving = createSoundEvent("mob.spirit.living", "mob_spirit_living");
	public static final SoundEvent mobSpiritDeath = createSoundEvent("mob.spirit.death", "mob_spirit_death");
	public static final SoundEvent mobRunicGolemChange = createSoundEvent("mob.runic_golem.change", "mob_runic_golem_change");
	public static final SoundEvent mobZargDeath = createSoundEvent("mob.zarg.death", "mob_zarg_death");
	public static final SoundEvent mobZargHit = createSoundEvent("mob.zarg.hit", "mob_zarg_hit");
	public static final SoundEvent mobZargLiving = createSoundEvent("mob.zarg.living", "mob_zarg_living");
	public static final SoundEvent mobRefluctDeath = createSoundEvent("mob.refluct.death", "mob_refluct_death");
	public static final SoundEvent mobRefluctHit = createSoundEvent("mob.refluct.hit", "mob_refluct_hit");
	public static final SoundEvent mobRefluctLiving = createSoundEvent("mob.refluct.living", "mob_refluct_living");
	public static final SoundEvent mobLunarcherDeath = createSoundEvent("mob.lunarcher.death", "mob_lunarcher_death");
	public static final SoundEvent mobLunarcherHit = createSoundEvent("mob.lunarcher.hit", "mob_lunarcher_hit");
	public static final SoundEvent mobLunarcherLiving = createSoundEvent("mob.lunarcher.living", "mob_lunarcher_living");
	public static final SoundEvent mobSeaViperDeath = createSoundEvent("mob.sea_viper.death", "mob_sea_viper_death");
	public static final SoundEvent mobSeaViperHit = createSoundEvent("mob.sea_viper.hit", "mob_sea_viper_hit");
	public static final SoundEvent mobSeaViperLiving = createSoundEvent("mob.sea_viper.living", "mob_sea_viper_living");
	public static final SoundEvent mobNeptunoDeath = createSoundEvent("mob.neptuno.death", "mob_neptuno_death");
	public static final SoundEvent mobNeptunoHit = createSoundEvent("mob.neptuno.hit", "mob_neptuno_hit");
	public static final SoundEvent mobNeptunoLiving = createSoundEvent("mob.neptuno.living", "mob_neptuno_living");
	public static final SoundEvent mobZorpDeath = createSoundEvent("mob.zorp.death", "mob_zorp_death");
	public static final SoundEvent mobZorpHit = createSoundEvent("mob.zorp.hit", "mob_zorp_hit");
	public static final SoundEvent mobZorpLiving = createSoundEvent("mob.zorp.living", "mob_zorp_living");
	public static final SoundEvent mobExplodotDeath = createSoundEvent("mob.explodot.death", "mob_explodot_death");
	public static final SoundEvent mobExplodotHit = createSoundEvent("mob.explodot.hit", "mob_explodot_hit");
	public static final SoundEvent mobExplodotLiving = createSoundEvent("mob.explodot.living", "mob_explodot_living");
	public static final SoundEvent mobTrotterDeath = createSoundEvent("mob.trotter.death", "mob_trotter_death");
	public static final SoundEvent mobTrotterHit = createSoundEvent("mob.trotter.hit", "mob_trotter_hit");
	public static final SoundEvent mobTrotterLiving = createSoundEvent("mob.trotter.living", "mob_trotter_living");
	public static final SoundEvent mobTrackerDeath = createSoundEvent("mob.tracker.death", "mob_tracker_death");
	public static final SoundEvent mobTrackerHit = createSoundEvent("mob.tracker.hit", "mob_tracker_hit");
	public static final SoundEvent mobTrackerLiving = createSoundEvent("mob.tracker.living", "mob_tracker_living");
	public static final SoundEvent mobRawboneDeath = createSoundEvent("mob.rawbone.death", "mob_rawbone_death");
	public static final SoundEvent mobRawboneHit = createSoundEvent("mob.rawbone.hit", "mob_rawbone_hit");
	public static final SoundEvent mobRawboneLiving = createSoundEvent("mob.rawbone.living", "mob_rawbone_living");
	public static final SoundEvent mobParaviteDeath = createSoundEvent("mob.paravite.death", "mob_paravite_death");
	public static final SoundEvent mobParaviteHit = createSoundEvent("mob.paravite.hit", "mob_paravite_hit");
	public static final SoundEvent mobParaviteLiving = createSoundEvent("mob.paravite.living", "mob_paravite_living");
	public static final SoundEvent mobLelyetianDeath = createSoundEvent("mob.lelyetian.death", "mob_lelyetian_death");
	public static final SoundEvent mobLelyetianHit = createSoundEvent("mob.lelyetian.hit", "mob_lelyetian_hit");
	public static final SoundEvent mobLelyetianLiving = createSoundEvent("mob.lelyetian.living", "mob_lelyetian_living");
	public static final SoundEvent mobGrobblerDeath = createSoundEvent("mob.grobbler.death", "mob_grobbler_death");
	public static final SoundEvent mobGrobblerHit = createSoundEvent("mob.grobbler.hit", "mob_grobbler_hit");
	public static final SoundEvent mobGrobblerLiving = createSoundEvent("mob.grobbler.living", "mob_grobbler_living");
	public static final SoundEvent mobFlyeDeath = createSoundEvent("mob.flye.death", "mob_flye_death");
	public static final SoundEvent mobFlyeHit = createSoundEvent("mob.flye.hit", "mob_flye_hit");
	public static final SoundEvent mobFlyeLiving = createSoundEvent("mob.flye.living", "mob_flye_living");
	public static final SoundEvent mobMuncherDeath = createSoundEvent("mob.muncher.death", "mob_muncher_death");
	public static final SoundEvent mobMuncherHit = createSoundEvent("mob.muncher.hit", "mob_muncher_hit");
	public static final SoundEvent mobMuncherLiving = createSoundEvent("mob.muncher.living", "mob_muncher_living");
	public static final SoundEvent mobPoseidoDeath = createSoundEvent("mob.poseido.death", "mob_poseido_death");
	public static final SoundEvent mobPoseidoHit = createSoundEvent("mob.poseido.hit", "mob_poseido_hit");
	public static final SoundEvent mobPoseidoLiving = createSoundEvent("mob.poseido.living", "mob_poseido_living");
	public static final SoundEvent mobCorateeDeath = createSoundEvent("mob.coratee.death", "mob_coratee_death");
	public static final SoundEvent mobCorateeHit = createSoundEvent("mob.coratee.hit", "mob_coratee_hit");
	public static final SoundEvent mobCorateeLiving = createSoundEvent("mob.coratee.living", "mob_coratee_living");
	public static final SoundEvent mobCoralonDeath = createSoundEvent("mob.coralon.death", "mob_coralon_death");
	public static final SoundEvent mobCoralonHit = createSoundEvent("mob.coralon.hit", "mob_coralon_hit");
	public static final SoundEvent mobCoralonLiving = createSoundEvent("mob.coralon.living", "mob_coralon_living");
	public static final SoundEvent mobAnglerDeath = createSoundEvent("mob.angler.death", "mob_angler_death");
	public static final SoundEvent mobAnglerHit = createSoundEvent("mob.angler.hit", "mob_angler_hit");
	public static final SoundEvent mobAnglerLiving = createSoundEvent("mob.angler.living", "mob_angler_living");
	public static final SoundEvent mobAmphibiorDeath = createSoundEvent("mob.amphibior.death", "mob_amphibior_death");
	public static final SoundEvent mobAmphibiorHit = createSoundEvent("mob.amphibior.hit", "mob_amphibior_hit");
	public static final SoundEvent mobAmphibiorLiving = createSoundEvent("mob.amphibior.living", "mob_amphibior_living");
	public static final SoundEvent mobVoltronDeath = createSoundEvent("mob.voltron.death", "mob_voltron_death");
	public static final SoundEvent mobVoltronHit = createSoundEvent("mob.voltron.hit", "mob_voltron_hit");
	public static final SoundEvent mobVoltronLiving = createSoundEvent("mob.voltron.living", "mob_voltron_living");
	public static final SoundEvent mobQuickpocketDeath = createSoundEvent("mob.quickpocket.death", "mob_quickpocket_death");
	public static final SoundEvent mobQuickpocketHit = createSoundEvent("mob.quickpocket.hit", "mob_quickpocket_hit");
	public static final SoundEvent mobQuickpocketLiving = createSoundEvent("mob.quickpocket.living", "mob_quickpocket_living");
	public static final SoundEvent mobPolytomDeath = createSoundEvent("mob.polytom.death", "mob_polytom_death");
	public static final SoundEvent mobPolytomHit = createSoundEvent("mob.polytom.hit", "mob_polytom_hit");
	public static final SoundEvent mobPolytomLiving = createSoundEvent("mob.polytom.living", "mob_polytom_living");
	public static final SoundEvent mobMechachronDeath = createSoundEvent("mob.mechachron.death", "mob_mechachron_death");
	public static final SoundEvent mobMechachronHit = createSoundEvent("mob.mechachron.hit", "mob_mechachron_hit");
	public static final SoundEvent mobMechachronLiving = createSoundEvent("mob.mechachron.living", "mob_mechachron_living");
	public static final SoundEvent mobEnforcerDeath = createSoundEvent("mob.enforcer.death", "mob_enforcer_death");
	public static final SoundEvent mobEnforcerHit = createSoundEvent("mob.enforcer.hit", "mob_enforcer_hit");
	public static final SoundEvent mobEnforcerLiving = createSoundEvent("mob.enforcer.living", "mob_enforcer_living");
	public static final SoundEvent mobVoliantDeath = createSoundEvent("mob.voliant.death", "mob_voliant_death");
	public static final SoundEvent mobVoliantHit = createSoundEvent("mob.voliant.hit", "mob_voliant_hit");
	public static final SoundEvent mobVoliantLiving = createSoundEvent("mob.voliant.living", "mob_voliant_living");
	public static final SoundEvent mobVolarDeath = createSoundEvent("mob.volar.death", "mob_volar_death");
	public static final SoundEvent mobVolarHit = createSoundEvent("mob.volar.hit", "mob_volar_hit");
	public static final SoundEvent mobVolarLiving = createSoundEvent("mob.volar.living", "mob_volar_living");
	public static final SoundEvent mobSurveyorDeath = createSoundEvent("mob.surveyor.death", "mob_surveyor_death");
	public static final SoundEvent mobSurveyorHit = createSoundEvent("mob.surveyor.hit", "mob_surveyor_hit");
	public static final SoundEvent mobSurveyorLiving = createSoundEvent("mob.surveyor.living", "mob_surveyor_living");
	public static final SoundEvent mobSeekerDeath = createSoundEvent("mob.seeker.death", "mob_seeker_death");
	public static final SoundEvent mobSeekerHit = createSoundEvent("mob.seeker.hit", "mob_seeker_hit");
	public static final SoundEvent mobSeekerLiving = createSoundEvent("mob.seeker.living", "mob_seeker_living");
	public static final SoundEvent mobOrbiterDeath = createSoundEvent("mob.orbiter.death", "mob_orbiter_death");
	public static final SoundEvent mobOrbiterHit = createSoundEvent("mob.orbiter.hit", "mob_orbiter_hit");
	public static final SoundEvent mobOrbiterLiving = createSoundEvent("mob.orbiter.living", "mob_orbiter_living");
	public static final SoundEvent mobDawnlightDeath = createSoundEvent("mob.dawnlight.death", "mob_dawnlight_death");
	public static final SoundEvent mobDawnlightHit = createSoundEvent("mob.dawnlight.hit", "mob_dawnlight_hit");
	public static final SoundEvent mobDawnlightLiving = createSoundEvent("mob.dawnlight.living", "mob_dawnlight_living");
	public static final SoundEvent mobAngelicaDeath = createSoundEvent("mob.angelica.death", "mob_angelica_death");
	public static final SoundEvent mobAngelicaHit = createSoundEvent("mob.angelica.hit", "mob_angelica_hit");
	public static final SoundEvent mobAngelicaLiving = createSoundEvent("mob.angelica.living", "mob_angelica_living");
	public static final SoundEvent mobArcWizardDeath = createSoundEvent("mob.arc_wizard.death", "mob_arc_wizard_death");
	public static final SoundEvent mobArcWizardHit = createSoundEvent("mob.arc_wizard.hit", "mob_arc_wizard_hit");
	public static final SoundEvent mobArcWizardLiving = createSoundEvent("mob.arc_wizard.living", "mob_arc_wizard_living");
	public static final SoundEvent mobOmnilightDeath = createSoundEvent("mob.omnilight.death", "mob_omnilight_death");
	public static final SoundEvent mobOmnilightHit = createSoundEvent("mob.omnilight.hit", "mob_omnilight_hit");
	public static final SoundEvent mobOmnilightLiving = createSoundEvent("mob.omnilight.living", "mob_omnilight_living");
	public static final SoundEvent mobSkeletalCowmanHit = createSoundEvent("mob.skeletal_cowman.hit", "mob_skeletal_cowman_hit");
	public static final SoundEvent mobSkeletalCowmanLiving = createSoundEvent("mob.skeletal_cowman.living", "mob_skeletal_cowman_living");
	public static final SoundEvent mobWitherWizardHit = createSoundEvent("mob.wither_wizard.hit", "mob_wither_wizard_hit");
	public static final SoundEvent mobWitherWizardLiving = createSoundEvent("mob.wither_wizard.living", "mob_wither_wizard_living");
	public static final SoundEvent mobNethengeicBeastDeath = createSoundEvent("mob.nethengeic_beast.death", "mob_nethengeic_beast_death");
	public static final SoundEvent mobNethengeicBeastHit = createSoundEvent("mob.nethengeic_beast.hit", "mob_nethengeic_beast_hit");
	public static final SoundEvent mobNethengeicBeastLiving = createSoundEvent("mob.nethengeic_beast.living", "mob_nethengeic_beast_living");
	public static final SoundEvent mobInfernalHit = createSoundEvent("mob.infernal.hit", "mob_infernal_hit");
	public static final SoundEvent mobInfernalLiving = createSoundEvent("mob.infernal.living", "mob_infernal_living");
	public static final SoundEvent mobHellspotDeath = createSoundEvent("mob.hellspot.death", "mob_hellspot_death");
	public static final SoundEvent mobHellspotHit = createSoundEvent("mob.hellspot.hit", "mob_hellspot_hit");
	public static final SoundEvent mobHellspotLiving = createSoundEvent("mob.hellspot.living", "mob_hellspot_living");
	public static final SoundEvent mobHellcatDeath = createSoundEvent("mob.hellcat.death", "mob_hellcat_death");
	public static final SoundEvent mobHellcatHit = createSoundEvent("mob.hellcat.hit", "mob_hellcat_hit");
	public static final SoundEvent mobHellcatLiving = createSoundEvent("mob.hellcat.living", "mob_hellcat_living");
	public static final SoundEvent mobFlamewalkerDeath = createSoundEvent("mob.flamewalker.death", "mob_flamewalker_death");
	public static final SoundEvent mobFlamewalkerHit = createSoundEvent("mob.flamewalker.hit", "mob_flamewalker_hit");
	public static final SoundEvent mobFlamewalkerLiving = createSoundEvent("mob.flamewalker.living", "mob_flamewalker_living");
	public static final SoundEvent mobPigotronDeath = createSoundEvent("mob.pigotron.death", "mob_pigotron_death");
	public static final SoundEvent mobPigotronHit = createSoundEvent("mob.pigotron.hit", "mob_pigotron_hit");
	public static final SoundEvent mobPigotronLiving = createSoundEvent("mob.pigotron.living", "mob_pigotron_living");
	public static final SoundEvent mobPigotronAppear = createSoundEvent("mob.pigotron.appear", "mob_pigotron_appear");
	public static final SoundEvent mobValkyrieDeath = createSoundEvent("mob.valkyrie.death", "mob_valkyrie_death");
	public static final SoundEvent mobValkyrieHit = createSoundEvent("mob.valkyrie.hit", "mob_valkyrie_hit");
	public static final SoundEvent mobValkyrieLiving = createSoundEvent("mob.valkyrie.living", "mob_valkyrie_living");
	public static final SoundEvent mobSugarfaceDeath = createSoundEvent("mob.sugarface.death", "mob_sugarface_death");
	public static final SoundEvent mobSugarfaceHit = createSoundEvent("mob.sugarface.hit", "mob_sugarface_hit");
	public static final SoundEvent mobSugarfaceLiving = createSoundEvent("mob.sugarface.living", "mob_sugarface_living");
	public static final SoundEvent mobSkullCreatureDeath = createSoundEvent("mob.skull_creature.death", "mob_skull_creature_death");
	public static final SoundEvent mobSkullCreatureHit = createSoundEvent("mob.skull_creature.hit", "mob_skull_creature_hit");
	public static final SoundEvent mobSkullCreatureLiving = createSoundEvent("mob.skull_creature.living", "mob_skull_creature_living");
	public static final SoundEvent mobSilencerDeath = createSoundEvent("mob.silencer.death", "mob_silencer_death");
	public static final SoundEvent mobSilencerHit = createSoundEvent("mob.silencer.hit", "mob_silencer_hit");
	public static final SoundEvent mobSilencerLiving = createSoundEvent("mob.silencer.living", "mob_silencer_living");
	public static final SoundEvent mobShifterDeath = createSoundEvent("mob.shifter.death", "mob_shifter_death");
	public static final SoundEvent mobShifterHit = createSoundEvent("mob.shifter.hit", "mob_shifter_hit");
	public static final SoundEvent mobShifterLiving = createSoundEvent("mob.shifter.living", "mob_shifter_living");
	public static final SoundEvent mobHunterDeath = createSoundEvent("mob.hunter.death", "mob_hunter_death");
	public static final SoundEvent mobHunterHit = createSoundEvent("mob.hunter.hit", "mob_hunter_hit");
	public static final SoundEvent mobHunterLiving = createSoundEvent("mob.hunter.living", "mob_hunter_living");
	public static final SoundEvent mobFiendDeath = createSoundEvent("mob.fiend.death", "mob_fiend_death");
	public static final SoundEvent mobFiendHit = createSoundEvent("mob.fiend.hit", "mob_fiend_hit");
	public static final SoundEvent mobFiendLiving = createSoundEvent("mob.fiend.living", "mob_fiend_living");
	public static final SoundEvent mobLollypopperDeath = createSoundEvent("mob.lollypopper.death", "mob_lollypopper_death");
	public static final SoundEvent mobFishixDeath = createSoundEvent("mob.fishix.death", "mob_fishix_death");
	public static final SoundEvent mobFishixHit = createSoundEvent("mob.fishix.hit", "mob_fishix_hit");
	public static final SoundEvent mobFishixLiving = createSoundEvent("mob.fishix.living", "mob_fishix_living");
	public static final SoundEvent mobFleshEaterDeath = createSoundEvent("mob.flesh_eater.death", "mob_flesh_eater_death");
	public static final SoundEvent mobFleshEaterHit = createSoundEvent("mob.flesh_eater.hit", "mob_flesh_eater_hit");
	public static final SoundEvent mobFleshEaterLiving = createSoundEvent("mob.flesh_eater.living", "mob_flesh_eater_living");
	public static final SoundEvent mobFungiDeath = createSoundEvent("mob.fungi.death", "mob_fungi_death");
	public static final SoundEvent mobFungiHit = createSoundEvent("mob.fungi.hit", "mob_fungi_hit");
	public static final SoundEvent mobFungiLiving = createSoundEvent("mob.fungi.living", "mob_fungi_living");
	public static final SoundEvent mobFurlionDeath = createSoundEvent("mob.furlion.death", "mob_furlion_death");
	public static final SoundEvent mobFurlionHit = createSoundEvent("mob.furlion.hit", "mob_furlion_hit");
	public static final SoundEvent mobFurlionLiving = createSoundEvent("mob.furlion.living", "mob_furlion_living");
	public static final SoundEvent mobGhostDeath = createSoundEvent("mob.ghost.death", "mob_ghost_death");
	public static final SoundEvent mobGhostHit = createSoundEvent("mob.ghost.hit", "mob_ghost_hit");
	public static final SoundEvent mobGhostineDeath = createSoundEvent("mob.ghostine.death", "mob_ghostine_death");
	public static final SoundEvent mobGhostineHit = createSoundEvent("mob.ghostine.hit", "mob_ghostine_hit");
	public static final SoundEvent mobGhostineLiving = createSoundEvent("mob.ghostine.living", "mob_ghostine_living");
	public static final SoundEvent mobGhostLiving = createSoundEvent("mob.ghost.living", "mob_ghost_living");
	public static final SoundEvent mobGiantDeath = createSoundEvent("mob.giant.death", "mob_giant_death");
	public static final SoundEvent mobGiantHit = createSoundEvent("mob.giant.hit", "mob_giant_hit");
	public static final SoundEvent mobGoalbyDeath = createSoundEvent("mob.goalby.death", "mob_goalby_death");
	public static final SoundEvent mobGoalbyHit = createSoundEvent("mob.goalby.hit", "mob_goalby_hit");
	public static final SoundEvent mobGoalbyLiving = createSoundEvent("mob.goalby.living", "mob_goalby_living");
	public static final SoundEvent mobGoblinDeath = createSoundEvent("mob.goblin.death", "mob_goblin_death");
	public static final SoundEvent mobGoblinHit = createSoundEvent("mob.goblin.hit", "mob_goblin_hit");
	public static final SoundEvent mobGoblinLiving = createSoundEvent("mob.goblin.living", "mob_goblin_living");
	public static final SoundEvent mobGoldorthLiving = createSoundEvent("mob.goldorth.living", "mob_goldorth_living");
	public static final SoundEvent mobGoldorthDeath = createSoundEvent("mob.goldorth.death", "mob_goldorth_death");
	public static final SoundEvent mobGoldorthHit = createSoundEvent("mob.goldorth.hit", "mob_goldorth_hit");
	public static final SoundEvent mobGolemStep = createSoundEvent("mob.golem.step", "mob_golem_step");
	public static final SoundEvent candySlugStep = createSoundEvent("entity.generic.candy_slug_step", "candy_slug_step");
	public static final SoundEvent mobEmperorBeastStep = createSoundEvent("mob.emperor_beast.step", "mob_emperor_beast_step");
	public static final SoundEvent mobGrawLiving = createSoundEvent("mob.graw.living", "mob_graw_living");
	public static final SoundEvent mobGrawDeath = createSoundEvent("mob.graw.death", "mob_graw_death");
	public static final SoundEvent mobGrawHit = createSoundEvent("mob.graw.hit", "mob_graw_hit");
	public static final SoundEvent mobGruntDeath = createSoundEvent("mob.grunt.death", "mob_grunt_death");
	public static final SoundEvent mobGruntHit = createSoundEvent("mob.grunt.hit", "mob_grunt_hit");
	public static final SoundEvent mobGruntLiving = createSoundEvent("mob.grunt.living", "mob_grunt_living");
	public static final SoundEvent mobGuardianDeath = createSoundEvent("mob.guardian.death", "mob_guardian_death");
	public static final SoundEvent mobGuardianHit = createSoundEvent("mob.guardian.hit", "mob_guardian_hit");
	public static final SoundEvent mobGyroLiving = createSoundEvent("mob.gyro.living", "mob_gyro_living");
	public static final SoundEvent mobGyroDeath = createSoundEvent("mob.gyro.death", "mob_gyro_death");
	public static final SoundEvent mobGyroHit = createSoundEvent("mob.gyro.hit", "mob_gyro_hit");
	public static final SoundEvent mobHagDeath = createSoundEvent("mob.hag.death", "mob_hag_death");
	public static final SoundEvent mobHagHit = createSoundEvent("mob.hag.hit", "mob_hag_hit");
	public static final SoundEvent mobHagLiving = createSoundEvent("mob.hag.living", "mob_hag_living");
	public static final SoundEvent mobHiveKingLiving = createSoundEvent("mob.hive_king.living", "mob_hive_king_living");
	public static final SoundEvent mobHiveKingDeath = createSoundEvent("mob.hive_king.death", "mob_hive_king_death");
	public static final SoundEvent mobKingShroomusDeath = createSoundEvent("mob.king_shroomus.death", "mob_king_shroomus_death");
	public static final SoundEvent mobKingShroomusHeal = createSoundEvent("mob.king_shroomus.heal", "mob_king_shroomus_heal");
	public static final SoundEvent mobHorndronDeath = createSoundEvent("mob.horndron.death", "mob_horndron_death");
	public static final SoundEvent mobHorndronHit = createSoundEvent("mob.horndron.hit", "mob_horndron_hit");
	public static final SoundEvent mobHorndronLiving = createSoundEvent("mob.horndron.living", "mob_horndron_living");
	public static final SoundEvent mobHoronLiving = createSoundEvent("mob.horon.living", "mob_horon_living");
	public static final SoundEvent mobHoronDeath = createSoundEvent("mob.horon.death", "mob_horon_death");
	public static final SoundEvent mobHoronHit = createSoundEvent("mob.horon.hit", "mob_horon_hit");
	public static final SoundEvent mobHostDeath = createSoundEvent("mob.host.death", "mob_host_death");
	public static final SoundEvent mobHostDrop = createSoundEvent("mob.host.living", "mob_host_drop");
	public static final SoundEvent mobHostLiving = createSoundEvent("mob.host.living", "mob_host_living");
	public static final SoundEvent mobHunchDeath = createSoundEvent("mob.hunch.death", "mob_hunch_death");
	public static final SoundEvent mobHunchHit = createSoundEvent("mob.hunch.hit", "mob_hunch_hit");
	public static final SoundEvent mobHunchLiving = createSoundEvent("mob.hunch.living", "mob_hunch_living");
	public static final SoundEvent mobHydroliskLiving = createSoundEvent("mob.hydrolisk.living", "mob_hydrolisk_living");
	public static final SoundEvent mobHydroliskDeath = createSoundEvent("mob.hydrolisk.death", "mob_hydrolisk_death");
	public static final SoundEvent mobHydroliskHit = createSoundEvent("mob.hydrolisk.hit", "mob_hydrolisk_hit");
	public static final SoundEvent mobImmortalLiving = createSoundEvent("mob.immortal.living", "mob_immortal_living");
	public static final SoundEvent mobImmortalDeath = createSoundEvent("mob.immortal.death", "mob_immortal_death");
	public static final SoundEvent mobIosaurDeath = createSoundEvent("mob.iosaur.death", "mob_iosaur_death");
	public static final SoundEvent mobIosaurHit = createSoundEvent("mob.iosaur.hit", "mob_iosaur_hit");
	public static final SoundEvent mobIosaurLiving = createSoundEvent("mob.iosaur.living", "mob_iosaur_living");
	public static final SoundEvent mobIrklingDeath = createSoundEvent("mob.irkling.death", "mob_irkling_death");
	public static final SoundEvent mobIrklingHit = createSoundEvent("mob.irkling.death", "mob_irkling_hit");
	public static final SoundEvent mobIrklingLiving = createSoundEvent("mob.irkling.death", "mob_irkling_living");
	public static final SoundEvent mobKeelerDeath = createSoundEvent("mob.keeler.death", "mob_keeler_death");
	public static final SoundEvent mobKeelerHit = createSoundEvent("mob.keeler.hit", "mob_keeler_hit");
	public static final SoundEvent mobKeelerLiving = createSoundEvent("mob.keeler.living", "mob_keeler_living");
	public static final SoundEvent mobKeelerRevive = createSoundEvent("mob.keeler.revive", "mob_keeler_revive");
	public static final SoundEvent mobKingBamBamBamLiving = createSoundEvent("mob.king_bambambam.living", "mob_king_bambambam_living");
	public static final SoundEvent mobKingBamBamBamHit = createSoundEvent("mob.king_bambambam.hit", "mob_king_bambambam_hit");
	public static final SoundEvent mobKingBamBamBamDeath = createSoundEvent("mob.king_bambambam.death", "mob_king_bambambam_death");
	public static final SoundEvent mobLittleBamSpawn = createSoundEvent("mob.little_bam.spawn", "mob_little_bam_spawn");
	public static final SoundEvent mobKrorLiving = createSoundEvent("mob.kror.living", "mob_kror_living");
	public static final SoundEvent mobKrorDeath = createSoundEvent("mob.kror.death", "mob_kror_death");
	public static final SoundEvent mobLingerDeath = createSoundEvent("mob.linger.death", "mob_linger_death");
	public static final SoundEvent mobLingerHit = createSoundEvent("mob.linger.hit", "mob_linger_hit");
	public static final SoundEvent mobLingerLiving = createSoundEvent("mob.linger.living", "mob_linger_living");
	public static final SoundEvent mobLivingFungiSpawn = createSoundEvent("mob.living_fungi.spawn", "mob_living_fungi_spawn");
	public static final SoundEvent mobMagickeDeath = createSoundEvent("mob.magicke.death", "mob_magicke_death");
	public static final SoundEvent mobMagickeHit = createSoundEvent("mob.magicke.hit", "mob_magicke_hit");
	public static final SoundEvent mobMagickeLiving = createSoundEvent("mob.magicke.living", "mob_magicke_living");
	public static final SoundEvent mobMechyonDeath = createSoundEvent("mob.mechyon.death", "mob_mechyon_death");
	public static final SoundEvent mobMechyonHit = createSoundEvent("mob.mechyon.hit", "mob_mechyon_hit");
	public static final SoundEvent mobMechyonLiving = createSoundEvent("mob.mechyon.living", "mob_mechyon_living");
	public static final SoundEvent mobMotherVoidWalkerDeath = createSoundEvent("mob.mother_void_walker.death", "mob_mother_void_walker_death");
	public static final SoundEvent mobMotherVoidWalkerHit = createSoundEvent("mob.mother_void_walker.hit", "mob_mother_void_walker_hit");
	public static final SoundEvent mobMotherVoidWalkerLiving = createSoundEvent("mob.mother_void_walker.living", "mob_mother_void_walker_living");
	public static final SoundEvent mobMuckopedeHit = createSoundEvent("mob.muckopede.hit", "mob_muckopede_hit");
	public static final SoundEvent mobMuckopedeLiving = createSoundEvent("mob.muckopede.living", "mob_muckopede_living");
	public static final SoundEvent mobNaturaDeath = createSoundEvent("mob.natura.death", "mob_natura_death");
	public static final SoundEvent mobNaturaHit = createSoundEvent("mob.natura.hit", "mob_natura_hit");
	public static final SoundEvent mobNaturaLiving = createSoundEvent("mob.natura.living", "mob_natura_living");
	public static final SoundEvent mobNethengeicWitherLiving = createSoundEvent("mob.nethengeic_wither.living", "mob_nethengeic_wither_living");
	public static final SoundEvent mobNethengeicWitherDeath = createSoundEvent("mob.nethengeic_wither.death", "mob_nethengeic_wither_death");
	public static final SoundEvent mobNethengeicWitherHit = createSoundEvent("mob.nethengeic_wither.hit", "mob_nethengeic_wither_hit");
	public static final SoundEvent mobNightflyDeath = createSoundEvent("mob.nightfly.death", "mob_nightfly_death");
	public static final SoundEvent mobNightflyHit = createSoundEvent("mob.nightfly.hit", "mob_nightfly_hit");
	public static final SoundEvent mobNightflyLiving = createSoundEvent("mob.nightfly.living", "mob_nightfly_living");
	public static final SoundEvent mobNightWatcherHit = createSoundEvent("mob.night_watcher.hit", "night_watcher_hit");
	public static final SoundEvent mobNightWatcherLiving = createSoundEvent("mob.night_watcher.living", "night_watcher_living");
	public static final SoundEvent mobNospikeDeath = createSoundEvent("mob.nospike.death", "mob_nospike_death");
	public static final SoundEvent mobNospikeHit = createSoundEvent("mob.nospike.hit", "mob_nospike_hit");
	public static final SoundEvent mobNospikeLiving = createSoundEvent("mob.nospike.living", "mob_nospike_living");
	public static final SoundEvent mobParasectDeath = createSoundEvent("mob.parasect.death", "mob_parasect_death");
	public static final SoundEvent mobParasectHit = createSoundEvent("mob.parasect.hit", "mob_parasect_hit");
	public static final SoundEvent mobParasectLiving = createSoundEvent("mob.parasect.living", "mob_parasect_living");
	public static final SoundEvent mobPenguinDeath = createSoundEvent("mob.penguin.death", "mob_penguin_death");
	public static final SoundEvent mobPenguinHit = createSoundEvent("mob.penguin.hit", "mob_penguin_hit");
	public static final SoundEvent mobPenguinLiving = createSoundEvent("mob.penguin.living", "mob_penguin_living");
	public static final SoundEvent mobPenumbraLiving = createSoundEvent("mob.penumbra.living", "mob_penumbra_living");
	public static final SoundEvent mobPenumbraHit = createSoundEvent("mob.penumbra.hit", "mob_penumbra_hit");
	public static final SoundEvent mobPenumbraDeath = createSoundEvent("mob.penumbra.death", "mob_penumbra_death");
	public static final SoundEvent mobPrimordialLiving = createSoundEvent("mob.primordial.living", "mob_primordial_living");
	public static final SoundEvent mobPrimordialDeath = createSoundEvent("mob.primordial.death", "mob_primordial_death");
	public static final SoundEvent mobRainicornDeath = createSoundEvent("mob.rainicorn.death", "mob_rainicorn_death");
	public static final SoundEvent mobRainicornHit = createSoundEvent("mob.rainicorn.hit", "mob_rainicorn_hit");
	public static final SoundEvent mobRainicornLiving = createSoundEvent("mob.rainicorn.living", "mob_rainicorn_living");
	public static final SoundEvent mobRammerheadDeath = createSoundEvent("mob.rammerhead.death", "mob_rammerhead_death");
	public static final SoundEvent mobRammerheadHit = createSoundEvent("mob.rammerhead.hit", "mob_rammerhead_hit");
	public static final SoundEvent mobRammerheadLiving = createSoundEvent("mob.rammerhead.living", "mob_rammerhead_living");
	public static final SoundEvent mobReaperDeath = createSoundEvent("mob.reaper.death", "mob_reaper_death");
	public static final SoundEvent mobReaperHit = createSoundEvent("mob.reaper.hit", "mob_reaper_hit");
	public static final SoundEvent mobReaperLiving = createSoundEvent("mob.reaper.living", "mob_reaper_living");
	public static final SoundEvent mobExoheadDeath = createSoundEvent("mob.exohead.death", "mob_exohead_death");
	public static final SoundEvent mobExoheadHit = createSoundEvent("mob.exohead.hit", "mob_exohead_hit");
	public static final SoundEvent mobExoheadLiving = createSoundEvent("mob.exohead.living", "mob_exohead_living");
	public static final SoundEvent mobRockRiderSwitch = createSoundEvent("mob.rock_rider.switch", "mob_rock_rider_switch");
	public static final SoundEvent mobRockRiderDeath = createSoundEvent("mob.rock_rider.death", "mob_rock_rider_death");
	public static final SoundEvent mobRockRiderHit = createSoundEvent("mob.rock_rider.hit", "mob_rock_rider_hit");
	public static final SoundEvent mobRoloscopeDeath = createSoundEvent("mob.roloscope.death", "mob_roloscope_death");
	public static final SoundEvent mobRoloscopeHit = createSoundEvent("mob.roloscope.hit", "mob_roloscope_hit");
	public static final SoundEvent mobRoloscopeLiving = createSoundEvent("mob.roloscope.living", "mob_roloscope_living");
	public static final SoundEvent mobSasquatchLiving = createSoundEvent("mob.sasquatch.living", "mob_sasquatch_living");
	public static final SoundEvent mobScrubbyHit = createSoundEvent("mob.scrubby.hit", "mob_scrubby_hit");
	public static final SoundEvent mobScrubbyLiving = createSoundEvent("mob.scrubby.living", "mob_scrubby_living");
	public static final SoundEvent mobShadeDeath = createSoundEvent("mob.shade.death", "mob_shade_death");
	public static final SoundEvent mobShadeHit = createSoundEvent("mob.shade.hit", "mob_shade_hit");
	public static final SoundEvent mobShadeLiving = createSoundEvent("mob.shade.living", "mob_shade_living");
	public static final SoundEvent mobShadowHit = createSoundEvent("mob.shadow.hit", "mob_shadow_hit");
	public static final SoundEvent mobShadowLiving = createSoundEvent("mob.shadow.living", "mob_shadow_living");
	public static final SoundEvent mobShadowlordLiving = createSoundEvent("mob.shadowlord.living", "mob_shadowlord_living");
	public static final SoundEvent mobShadowlordDeath = createSoundEvent("mob.shadowlord.death", "mob_shadowlord_death");
	public static final SoundEvent mobShadowlordHit = createSoundEvent("mob.shadowlord.hit", "mob_shadowlord_hit");
	public static final SoundEvent mobSkeletronLiving = createSoundEvent("mob.skeletron.living", "mob_skeletron_living");
	public static final SoundEvent mobSkeletronDeath = createSoundEvent("mob.skeletron.death", "mob_skeletron_death");
	public static final SoundEvent mobSkeletronHit = createSoundEvent("mob.skeletron.hit", "mob_skeletron_hit");
	public static final SoundEvent mobSkelloxDeath = createSoundEvent("mob.skellox.death", "mob_skellox_death");
	public static final SoundEvent mobSkelloxHit = createSoundEvent("mob.skellox.hit", "mob_skellox_hit");
	public static final SoundEvent mobSkelloxLiving = createSoundEvent("mob.skellox.living", "mob_skellox_living");
	public static final SoundEvent mobSkipperDeath = createSoundEvent("mob.skipper.death", "mob_skipper_death");
	public static final SoundEvent mobSkipperHit = createSoundEvent("mob.skipper.hit", "mob_skipper_hit");
	public static final SoundEvent mobSkipperLiving = createSoundEvent("mob.skipper.living", "mob_skipper_living");
	public static final SoundEvent mobSlimerDeath = createSoundEvent("mob.slimer.death", "mob_slimer_death");;
	public static final SoundEvent mobSlimerHit = createSoundEvent("mob.slimer.hit", "mob_slimer_hit");
	public static final SoundEvent mobSlimerLiving = createSoundEvent("mob.slimer.living", "mob_slimer_living");
	public static final SoundEvent mobSmashDeath = createSoundEvent("mob.smash.death", "mob_smash_death");
	public static final SoundEvent mobSmashHit = createSoundEvent("mob.smash.hit", "mob_smash_hit");
	public static final SoundEvent mobSmashLiving = createSoundEvent("mob.smash.living", "mob_smash_living");
	public static final SoundEvent mobSphinxDeath = createSoundEvent("mob.sphinx.death", "mob_sphinx_death");
	public static final SoundEvent mobJumboLiving = createSoundEvent("mob.jumbo.living", "mob_jumbo_living");
	public static final SoundEvent mobSphinxHit = createSoundEvent("mob.sphinx.hit", "mob_sphinx_hit");
	public static final SoundEvent mobSphinxLiving = createSoundEvent("mob.sphinx.living", "mob_sphinx_living");
	public static final SoundEvent mobSpinuxDeath = createSoundEvent("mob.spinux.death", "mob_spinux_death");
	public static final SoundEvent mobSpinuxHit = createSoundEvent("mob.spinux.hit", "mob_spinux_hit");
	public static final SoundEvent mobSpinuxLiving = createSoundEvent("mob.spinux.living", "mob_spinux_living");
	public static final SoundEvent mobTerrestrialDeath = createSoundEvent("mob.terrestrial.death", "mob_terrestrial_death");
	public static final SoundEvent mobTerrestrialHit = createSoundEvent("mob.terrestrial.hit", "mob_terrestrial_hit");
	public static final SoundEvent mobTerrestrialLiving = createSoundEvent("mob.terrestrial.living", "mob_terrestrial_living");
	public static final SoundEvent mobTreeSpiritDeath = createSoundEvent("mob.tree_spirit.death", "mob_tree_spirit_death");
	public static final SoundEvent mobTreeSpiritHit = createSoundEvent("mob.tree_spirit.hit", "mob_tree_spirit_hit");
	public static final SoundEvent mobTreeSpiritLiving = createSoundEvent("mob.tree_spirit.living", "mob_tree_spirit_living");
	public static final SoundEvent mobTricksterHide = createSoundEvent("mob.trickster.hide", "mob_trickster_hide");
	public static final SoundEvent mobTricksterHit = createSoundEvent("mob.trickster.hit", "mob_trickster_hit");
	public static final SoundEvent mobTricksterLiving = createSoundEvent("mob.trickster.living", "mob_trickster_living");
	public static final SoundEvent mobTyrosaurLiving = createSoundEvent("mob.tyrosaur.living", "mob_tyrosaur_living");
	public static final SoundEvent mobTyrosaurDeath = createSoundEvent("mob.tyrosaur.death", "mob_tyrosaur_death");
	public static final SoundEvent mobTyrosaurHit = createSoundEvent("mob.tyrosaur.hit", "mob_tyrosaur_hit");
	public static final SoundEvent mobTyrosaurCharge = createSoundEvent("mob.tyrosaur.charge", "mob_tyrosaur_charge");
	public static final SoundEvent mobTyrosaurStep = createSoundEvent("mob.tyrosaur.step", "mob_tyrosaur_step");
	public static final SoundEvent mobTyrosaurStomp = createSoundEvent("mob.tyrosaur.stomp", "mob_tyrosaur_stomp");
	public static final SoundEvent mobTyrosaurReadyStomp = createSoundEvent("mob.tyrosaur.ready_stomp", "mob_tyrosaur_ready_stomp");
	public static final SoundEvent mobUrkaDeath = createSoundEvent("mob.urka.death", "mob_urka_death");
	public static final SoundEvent mobUrkaHit = createSoundEvent("mob.urka.hit", "mob_urka_hit");
	public static final SoundEvent mobUrkaLiving = createSoundEvent("mob.urka.living", "mob_urka_living");
	public static final SoundEvent mobUrsaDeath = createSoundEvent("mob.ursa.death", "mob_ursa_death");
	public static final SoundEvent mobUrsaHit = createSoundEvent("mob.ursa.hit", "mob_ursa_hit");
	public static final SoundEvent mobUrsaLiving = createSoundEvent("mob.ursa.living", "mob_ursa_living");
	public static final SoundEvent mobVertebronDeath = createSoundEvent("mob.vertebron.death", "mob_vertebron_death");
	public static final SoundEvent mobVertebronHit = createSoundEvent("mob.vertebron.hit", "mob_vertebron_hit");
	public static final SoundEvent mobVertebronLiving = createSoundEvent("mob.vertebron.living", "mob_vertebron_living");
	public static final SoundEvent mobVisularLiving = createSoundEvent("mob.visular.living", "mob_visular_living");
	public static final SoundEvent mobVisulonLiving = createSoundEvent("mob.visulon.living", "mob_visulon_living");
	public static final SoundEvent mobVisularDeath = createSoundEvent("mob.visular.death", "mob_visular_death");
	public static final SoundEvent mobVisularHit = createSoundEvent("mob.visular.hit", "mob_visular_hit");
	public static final SoundEvent mobVoidWalkerDeath = createSoundEvent("mob.void_walker.death", "mob_void_walker_death");
	public static final SoundEvent mobVoidWalkerHit = createSoundEvent("mob.void_walker.hit", "mob_void_walker_hit");
	public static final SoundEvent mobVoidWalkerLiving = createSoundEvent("mob.void_walker.living", "mob_void_walker_living");
	public static final SoundEvent mobVoxxulonLiving = createSoundEvent("mob.voxxulon.living", "mob_voxxulon_living");
	public static final SoundEvent mobVoxxulonDeath = createSoundEvent("mob.voxxulon.death", "mob_voxxulon_death");
	public static final SoundEvent mobVoxxulonHit = createSoundEvent("mob.voxxulon.hit", "mob_voxxulon_hit");
	public static final SoundEvent mobWalkerDeath = createSoundEvent("mob.walker.death", "mob_walker_death");
	public static final SoundEvent mobWalkerHit = createSoundEvent("mob.walker.hit", "mob_walker_hit");
	public static final SoundEvent mobWalkerLiving = createSoundEvent("mob.walker.living", "mob_walker_living");
	public static final SoundEvent mobXxeusLiving = createSoundEvent("mob.xxeus.living", "mob_xxeus_living");
	public static final SoundEvent mobXxeusDeath = createSoundEvent("mob.xxeus.death", "mob_xxeus_death");
	public static final SoundEvent mobXxeusHit = createSoundEvent("mob.xxeus.hit", "mob_xxeus_hit");
	public static final SoundEvent mobXxeusDash = createSoundEvent("mob.xxeus.dash", "mob_xxeus_dash");
	public static final SoundEvent mobYetiDeath = createSoundEvent("mob.yeti.death", "mob_yeti_death");
	public static final SoundEvent mobYetiHit = createSoundEvent("mob.yeti.hit", "mob_yeti_hit");
	public static final SoundEvent mobYetiLiving = createSoundEvent("mob.yeti.living", "mob_yeti_living");
	public static final SoundEvent mobZhinxDeath = createSoundEvent("mob.zhinx.death", "mob_zhinx_death");
	public static final SoundEvent mobZhinxHit = createSoundEvent("mob.zhinx.hit", "mob_zhinx_hit");
	public static final SoundEvent mobZhinxLiving = createSoundEvent("mob.zhinx.living", "mob_zhinx_living");
	public static final SoundEvent mobBaumbaJump = createSoundEvent("mob.baumba.jump", "mob_baumba_jump");
	public static final SoundEvent mobMechbotJump = createSoundEvent("mob.mechbot.jump", "mob_mechbot_jump");
	public static final SoundEvent petalCraftingStationSuccess = createSoundEvent("event.petal_crafting_station.success", "petal_crafting_station_success");
	public static final SoundEvent portalAbyss = createSoundEvent("blocks.abyss_portal.activate", "abyss_portal_activate");
	public static final SoundEvent portalAncientCavern = createSoundEvent("blocks.ancient_cavern_portal.activate", "ancient_cavern_portal_activate");
	public static final SoundEvent portalBarren = createSoundEvent("blocks.barren_portal.activate", "barren_portal_activate");
	public static final SoundEvent portalCandyland = createSoundEvent("blocks.candyland_portal.activate", "candyland_portal_activate");
	public static final SoundEvent portalCeleve = createSoundEvent("blocks.celeve_portal.activate", "celeve_portal_activate");
	public static final SoundEvent portalCreeponia = createSoundEvent("blocks.creeponia_portal.activate", "creeponia_portal_activate");
	public static final SoundEvent portalCrystevia = createSoundEvent("blocks.crystevia_portal.activate", "crystevia_portal_activate");
	public static final SoundEvent portalDark = createSoundEvent("blocks.dark_portal.activate", "dark_portal_activate");
	public static final SoundEvent portalImmortallis = createSoundEvent("blocks.immortallis_portal.activate", "immortallis_portal_activate");
	public static final SoundEvent portalIromine = createSoundEvent("blocks.iromine_portal.activate", "iromine_portal_activate");
	public static final SoundEvent portalLight = createSoundEvent("blocks.light_portal.activate", "light_portal_activate");
	public static final SoundEvent portalNatural = createSoundEvent("blocks.natural_portal.activate", "natural_portal_activate");
	public static final SoundEvent portalShyrelands = createSoundEvent("blocks.shyrelands_portal.activate", "shyrelands_portal_activate");
	public static final SoundEvent runeRandomizer = createSoundEvent("event.rune_randomizer.use", "rune_randomizer_use");
	public static final SoundEvent shotClownFire = createSoundEvent("projectile.clown.fire", "clown_fire");
	public static final SoundEvent shotCraexxeusNukeFire = createSoundEvent("projectile.craexxeus_nuke.fire", "craexxeus_nuke_fire");
	public static final SoundEvent shotCraexxeusFire = createSoundEvent("projectile.craexxeus.fire", "craexxeus_fire");
	public static final SoundEvent shotGuardianFire = createSoundEvent("projectile.guardian.fire", "guardian_fire");
	public static final SoundEvent shotSkelemanFire = createSoundEvent("projectile.skeleman.fire", "skeleman_fire");
	public static final SoundEvent shotBaumbaFire = createSoundEvent("projectile.baumba.fire", "baumba_fire");
	public static final SoundEvent shotArcWizardFire = createSoundEvent("projectile.arc_wizard.fire", "arc_wizard_fire");
	public static final SoundEvent shotKaiyuFire = createSoundEvent("projectile.kaiyu.fire", "kaiyu_fire");
	public static final SoundEvent shotHagFire = createSoundEvent("projectile.hag.fire", "hag_fire");
	public static final SoundEvent shotMechbotFire = createSoundEvent("projectile.mechbot.fire", "mechbot_fire");
	public static final SoundEvent shotFungikFire = createSoundEvent("projectile.fungik.fire", "fungik_fire");
	public static final SoundEvent shotMirageFire = createSoundEvent("projectile.mirage.fire", "mirage_fire");
	public static final SoundEvent shotBaronessFire = createSoundEvent("projectile.baroness.fire", "baroness_fire");
	public static final SoundEvent shotShyreTrollFire = createSoundEvent("projectile.shyre_troll.fire", "shyre_troll_fire");
	public static final SoundEvent shotVineWizardFire = createSoundEvent("projectile.vine_wizard.fire", "vine_wizard_fire");
	public static final SoundEvent shotClunkheadFire = createSoundEvent("projectile.clunkhead.fire", "clunkhead_fire");
	public static final SoundEvent shotWebReaperFire = createSoundEvent("projectile.web_reaper.fire", "web_reaper_fire");
	public static final SoundEvent shotMermageFire = createSoundEvent("projectile.mermage.fire", "mermage_fire");
	public static final SoundEvent shotSpiritProtectorFire = createSoundEvent("projectile.spirit_protector.fire", "spirit_protector_fire");
	public static final SoundEvent shotSurgeFire = createSoundEvent("projectile.surge.fire", "surge_fire");
	public static final SoundEvent shotLingerFire = createSoundEvent("projectile.linger.fire", "linger_fire");
	public static final SoundEvent shotMagickeFire = createSoundEvent("projectile.magicke.fire", "magicke_fire");
	public static final SoundEvent shotCottonCandorFire = createSoundEvent("projectile.cotton_candor.fire", "cotton_candor_fire");
	public static final SoundEvent shotWizardBlast = createSoundEvent("projectile.wizard_blast.fire", "wizard_blast_fire");
	public static final SoundEvent shyrelandsDizzy = createSoundEvent("event.shyrelands.dizzy", "shyrelands_dizzy");
	public static final SoundEvent shyrelandsShine = createSoundEvent("event.shyrelands.shine", "shyrelands_shine");
	public static final SoundEvent shyrelandsWeakness = createSoundEvent("event.shyrelands.weakness", "shyrelands_weakness");
	public static final SoundEvent shyrelandsWind = createSoundEvent("event.shyrelands.wind", "shyrelands_wind");
	public static final SoundEvent staffAtlantic = createSoundEvent("item.staff.atlantic.cast", "atlantic_staff_cast");
	public static final SoundEvent staffBasic = createSoundEvent("item.staff.basic.cast", "basic_staff_cast");
	public static final SoundEvent staffCandy = createSoundEvent("item.staff.candy.cast", "candy_staff_cast");
	public static final SoundEvent staffCelestial = createSoundEvent("item.staff.celestial.cast", "celestial_staff_cast");
	public static final SoundEvent staffConcussion = createSoundEvent("item.staff.concussion.cast", "concussion_staff_cast");
	public static final SoundEvent staffCoral = createSoundEvent("item.staff.coral.cast", "coral_staff_cast");
	public static final SoundEvent staffCrystevia = createSoundEvent("item.staff.crystevia.cast", "crystevia_staff_cast");
	public static final SoundEvent staffEmber = createSoundEvent("item.staff.ember.cast", "ember_staff_cast");
	public static final SoundEvent staffEver = createSoundEvent("item.staff.ever.cast", "ever_staff_cast");
	public static final SoundEvent staffFirefly = createSoundEvent("item.staff.firefly.cast", "firefly_staff_cast");
	public static final SoundEvent staffFungal = createSoundEvent("item.staff.fungal.cast", "fungal_staff_cast");
	public static final SoundEvent staffJoker = createSoundEvent("item.staff.joker.cast", "joker_staff_cast");
	public static final SoundEvent staffKaiyu = createSoundEvent("item.staff.kaiyu.cast", "kaiyu_staff_cast");
	public static final SoundEvent staffLightshine = createSoundEvent("item.staff.lightshine.cast", "lightshine_staff_cast");
	public static final SoundEvent staffLunar = createSoundEvent("item.staff.lunar.cast", "lunar_staff_cast");
	public static final SoundEvent staffMeteor = createSoundEvent("item.staff.meteor.cast", "meteor_staff_cast");
	public static final SoundEvent staffMoonlight = createSoundEvent("item.staff.moonlight.cast", "moonlight_staff_cast");
	public static final SoundEvent staffNature = createSoundEvent("item.staff.nature.cast", "nature_staff_cast");
	public static final SoundEvent staffNightmare = createSoundEvent("item.staff.nightmare.cast", "nightmare_staff_cast");
	public static final SoundEvent staffNoxious = createSoundEvent("item.staff.noxious.cast", "noxious_staff_cast");
	public static final SoundEvent staffPhantom = createSoundEvent("item.staff.phantom.cast", "phantom_staff_cast");
	public static final SoundEvent staffReef = createSoundEvent("item.staff.reef.cast", "reef_staff_cast");
	public static final SoundEvent staffRejuvenation = createSoundEvent("item.staff.rejuvenation.cast", "rejuvenation_staff_cast");
	public static final SoundEvent staffRunic = createSoundEvent("item.staff.runic.cast", "runic_staff_cast");
	public static final SoundEvent staffShadow = createSoundEvent("item.staff.shadow.cast", "shadow_staff_cast");
	public static final SoundEvent staffShow = createSoundEvent("item.staff.show.cast", "show_staff_cast");
	public static final SoundEvent staffShyre = createSoundEvent("item.staff.shyre.cast", "shyre_staff_cast");
	public static final SoundEvent staffSky = createSoundEvent("item.staff.sky.cast", "sky_staff_cast");
	public static final SoundEvent staffSun = createSoundEvent("item.staff.sun.cast", "sun_staff_cast");
	public static final SoundEvent staffSurge = createSoundEvent("item.staff.surge.cast", "surge_staff_cast");
	public static final SoundEvent staffTangle = createSoundEvent("item.staff.tangle.cast", "tangle_staff_cast");
	public static final SoundEvent staffUltimatum = createSoundEvent("item.staff.ultimatum.cast", "ultimatum_staff_cast");
	public static final SoundEvent staffWeb = createSoundEvent("item.staff.web.cast", "web_staff_cast");
	public static final SoundEvent teaSinkFill = createSoundEvent("event.tea_sink.fill", "tea_sink_fill");
	public static final SoundEvent teaSinkUse = createSoundEvent("event.tea_sink.use", "tea_sink_use");
	public static final SoundEvent templeTrapLaugh = createSoundEvent("event.temple_trap.laugh", "temple_trap_laugh");
	public static final SoundEvent tributeFail = createSoundEvent("event.tribute.fail", "tribute_fail");
	public static final SoundEvent tributeSuccess = createSoundEvent("event.tribute.success", "tribute_success");
	public static final SoundEvent useCreationSlab = createSoundEvent("item.creation_slab.use", "creation_slab_use");
	public static final SoundEvent veryHeavyStep = createSoundEvent("entity.generic.very_heavy_step", "very_heavy_step");
	public static final SoundEvent candyThump = createSoundEvent("entity.generic.candy_thump", "candy_thump");
	public static final SoundEvent plantThump = createSoundEvent("entity.generic.plant_thump", "plant_thump");
	public static final SoundEvent vulcaneUse = createSoundEvent("item.vulcane.use", "vulcane_use");
	public static final SoundEvent shotCherryBlasterFire = createSoundEvent("projectile.cherry_blaster.fire", "cherry_blaster_fire");
	public static final SoundEvent shotMagicCreeperFire = createSoundEvent("projectile.magic_creeper.fire", "magic_creeper_fire");
	public static final SoundEvent whitewashUse = createSoundEvent("event.whitewash.use", "whitewash_use");
	public static final SoundEvent musicBane = createSoundEvent("music.bane", "bane_music");
	public static final SoundEvent musicXxeus = createSoundEvent("music.xxeus", "xxeus_music");
	public static final SoundEvent musicVoxxulon = createSoundEvent("music.voxxulon", "voxxulon_music");
	public static final SoundEvent musicVisualent = createSoundEvent("music.visualent", "visualent_music");
	public static final SoundEvent musicVinocorne = createSoundEvent("music.vinocorne", "vinocorne_music");
	public static final SoundEvent musicTyrosaur = createSoundEvent("music.tyrosaur", "tyrosaur_music");
	public static final SoundEvent musicSmash = createSoundEvent("music.smash", "smash_music");
	public static final SoundEvent musicSilverfoot = createSoundEvent("music.silverfoot", "silverfoot_music");
	public static final SoundEvent musicShadowlord = createSoundEvent("music.shadowlord", "shadowlord_music");
	public static final SoundEvent musicRockRider = createSoundEvent("music.rock_rider", "rock_rider_music");
	public static final SoundEvent musicPrimordialFive = createSoundEvent("music.primordial_five", "primordial_five_music");
	public static final SoundEvent musicNethengeicWither = createSoundEvent("music.nethengeic_wither", "nethengeic_wither_music");
	public static final SoundEvent musicMechbot = createSoundEvent("music.mechbot", "mechbot_music");
	public static final SoundEvent musicKror = createSoundEvent("music.kror", "kror_music");
	public static final SoundEvent musicKingShroomus = createSoundEvent("music.king_shroomus", "king_shroomus_music");
	public static final SoundEvent musicKingBamBamBam = createSoundEvent("music.king_bambambam", "king_bambambam_music");
	public static final SoundEvent musicHiveKing = createSoundEvent("music.hive_king", "hive_king_music");
	public static final SoundEvent musicGyro = createSoundEvent("music.gyro", "gyro_music");
	public static final SoundEvent musicGraw = createSoundEvent("music.graw", "graw_music");
	public static final SoundEvent musicElusive = createSoundEvent("music.elusive", "elusive_music");
	public static final SoundEvent musicDracyon = createSoundEvent("music.dracyon", "dracyon_music");
	public static final SoundEvent musicCrystocore = createSoundEvent("music.crystocore", "crystocore_music");
	public static final SoundEvent musicCreep = createSoundEvent("music.creep", "creep_music");
	public static final SoundEvent musicCraexxeus = createSoundEvent("music.craexxeus", "craexxeus_music");
	public static final SoundEvent musicCottonCandor = createSoundEvent("music.cotton_candor", "cotton_candor_music");
	public static final SoundEvent musicCorallus = createSoundEvent("music.corallus", "corallus_music");
	public static final SoundEvent musicClunkhead = createSoundEvent("music.clunkhead", "clunkhead_music");
	public static final SoundEvent musicBaroness = createSoundEvent("music.baroness", "baroness_music");

	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> ev) {
		ev.getRegistry().registerAll(
				nullMusic,
				ascensionShrineUse,
				bloodlustCollect,
				baronBombSpawn,
				baronBombPriming,
				bubbleShotPop,
				chainsawUse,
				shotMagicCreeperFire,
				creationForgeUse,
				crystalCreatorUse,
				crystalExtensionShrineUse,
				decloggingTableUse,
				dinoStep,
				dodge,
				entityIdolHit,
				mobJumboLiving,
				entityIdolLiving,
				entityIdolPrize,
				entityIdolSpawn,
				entityPixonHarvest,
				entityPixonLiving,
				eventBigDayStart,
				eventBloodHuntStart,
				eventCreepDayStart,
				eventDeathDayStart,
				eventLunarInvasionStart,
				eventSoulScurryStart,
				extractionDeviceSuccess,
				filtrationSystemActivate,
				filtrationSystemUse,
				foragingLoot,
				gooBallImpact,
				greatbladeGoofy,
				gunAbominator,
				gunAnimalBlaster,
				gunArchergun,
				gunArtifact,
				gunAtomizer,
				gunBallCannon,
				gunBeatCannon1,
				gunBeatCannon2,
				gunBeatCannon3,
				gunBeatCannon4,
				gunBeatCannon5,
				gunBigBlast,
				gunBlowpipe,
				gunBoomCannon,
				gunBubbleGun,
				gunCarrotCannon,
				gunChaingun,
				gunChugger,
				gunClowner,
				gunColourCannon,
				gunConfettiCannon,
				gunDarkGun,
				gunDischargeGun,
				gunDoomGun,
				gunDrainGun,
				gunElectroCannon1,
				gunElectroCannon2,
				gunElectroCannon3,
				gunElectroCannon4,
				gunElectroCannon5,
				gunEnergyCannon,
				gunFastRifle,
				gunFlinger,
				gunGasGun,
				gunGaugeRifle,
				gunGolemGun,
				gunGravityBlaster,
				gunHeatWave,
				gunHighCannon,
				gunIllusionRevolver,
				gunIllusionSMG,
				gunIonBlaster,
				gunJackRocker,
				gunKrasaunsDawn,
				gunLightCannon,
				gunLowerCannon,
				gunMagicGun,
				gunMechCannon,
				gunMindBlaster,
				gunMinigun,
				gunMiniPistol,
				gunMissileMaker,
				gunMonster,
				gunMoonShiner,
				gunParalyzer,
				gunPartyPopper,
				gunRayGun,
				gunReefer,
				gunRevolution,
				gunRevolver,
				gunRoulette,
				gunRPG,
				gunShadowBlaster,
				gunShotgun,
				gunSlugger,
				gunSniper,
				gunSoulSpark,
				gunSpaceGun,
				gunSpaceRevolver,
				gunSpiritShower,
				gunSprayer,
				gunSquadGun,
				gunStampede,
				gunStepCannon1,
				gunStepCannon2,
				gunStepCannon3,
				gunStepCannon4,
				gunStepCannon5,
				gunSwarmotron,
				gunSynthCannon1,
				gunSynthCannon2,
				gunSynthCannon3,
				gunSynthCannon4,
				gunSynthCannon5,
				gunUpperCannon,
				gunVibeCannon1,
				gunVibeCannon2,
				gunVibeCannon3,
				gunVibeCannon4,
				gunVibeCannon5,
				gunWhimsyWinder,
				gunWitherCannon,
				gunWithersWrath,
				gunWoodRifle,
				hauntingTableUse,
				heartStonePickup,
				heartStoneSpawn,
				heavyStep,
				hellfireImpact,
				infusionFail,
				infusionSuccess,
				level100,
				levelUp,
				lunarCreationTableSuccess,
				lunarEnrichmentTableUse,
				makeRunes,
				mendingSuccess,
				mobAmphibiyteDeath,
				mobAmphibiyteHit,
				mobAmphibiyteLiving,
				mobAnemiaDeath,
				mobAnemiaHit,
				mobAnemiaLiving,
				mobApparitionDeath,
				mobApparitionHit,
				mobApparitionLiving,
				mobArcwormDeath,
				mobArcwormHit,
				mobArcwormLiving,
				mobArielLiving,
				mobArielDeath,
				mobArielHit,
				mobAutomatonDeath,
				mobAutomatonHit,
				mobAutomatonLiving,
				mobArkbackDeath,
				mobArkbackHit,
				mobArkbackLiving,
				mobModuloDeath,
				mobModuloHit,
				mobModuloLiving,
				mobEmperorBeastDeath,
				mobEmperorBeastHit,
				mobEmperorBeastLiving,
				mobEilosapienDeath,
				mobEilosapienHit,
				mobEilosapienLiving,
				mobEchodarDeath,
				mobEchodarHit,
				mobEchodarLiving,
				mobPincherDeath,
				mobPincherHit,
				mobPincherLiving,
				mobCryptidDeath,
				mobCryptidHit,
				mobCryptidLiving,
				mobBaneLiving,
				mobBaneDeath,
				mobBloodmistDeath,
				mobBloodmistHit,
				mobBloodmistLiving,
				mobBombCarrierHit,
				mobBombCarrierLiving,
				mobBonebackDeath,
				mobBonebackHit,
				mobBonebackLiving,
				mobBugeyeDeath,
				mobBugeyeHit,
				mobBugeyeLiving,
				mobBushBabyDeath,
				mobBushBabyHit,
				mobBushBabyLiving,
				mobCeleveClownDeath,
				mobCeleveClownHit,
				mobCeleveClownLiving,
				mobChargerDeath,
				mobChargerHit,
				mobChargerLiving,
				mobChimeraDeath,
				mobChimeraHit,
				mobChimeraLiving,
				mobChomperHit,
				mobChomperLiving,
				mobClownDeath,
				mobClownHit,
				mobClownLiving,
				mobClunkheadDeath,
				mobCompeerDeath,
				mobCompeerHit,
				mobCompeerLiving,
				mobGrillfaceDeath,
				mobGrillfaceHit,
				mobGrillfaceLiving,
				mobGrillfaceScare,
				mobCarrotopDeath,
				mobCarrotopHit,
				mobCarrotopLiving,
				mobArchvineDeath,
				mobArchvineHit,
				mobArchvineLiving,
				mobStalkerDeath,
				mobStalkerHit,
				mobStalkerLiving,
				mobMerkyreDeath,
				mobMerkyreHit,
				mobMerkyreLiving,
				mobLurkerDeath,
				mobLurkerHit,
				mobLurkerLiving,
				mobLostSoulDeath,
				mobLostSoulHit,
				mobLostSoulLiving,
				mobDustStriderDeath,
				mobDustStriderHit,
				mobDustStriderLiving,
				mobDusteivaDeath,
				mobDusteivaHit,
				mobDusteivaLiving,
				mobDevourerDeath,
				mobDevourerHit,
				mobDevourerLiving,
				mobCrusiliskDeath,
				mobCrusiliskHit,
				mobCrusiliskLiving,
				mobCrusiliskScream,
				mobDustonHit,
				mobBasiliskDeath,
				mobBasiliskHit,
				mobBasiliskLiving,
				mobArkzyneDeath,
				mobArkzyneHit,
				mobArkzyneLiving,
				mobRockbiterDeath,
				mobRockbiterHit,
				mobRockbiterLiving,
				mobNipperDeath,
				mobNipperHit,
				mobNipperLiving,
				mobDoublerDeath,
				mobDoublerHit,
				mobDoublerLiving,
				mobCaveCreepDeath,
				mobCaveCreepHit,
				mobCaveCreepLiving,
				mobDestructorDeath,
				mobDestructorHit,
				mobDestructorLiving,
				mobCaveBugDeath,
				mobCaveBugHit,
				mobCaveBugLiving,
				mobArcbeastDeath,
				mobArcbeastHit,
				mobArcbeastLiving,
				mobAxiolightDeath,
				mobAxiolightHit,
				mobAxiolightLiving,
				mobLuxocronDeath,
				mobLuxocronHit,
				mobLuxocronLiving,
				mobSoulscorneDeath,
				mobSoulscorneHit,
				mobSoulscorneLiving,
				mobSoulvyreDeath,
				mobSoulvyreHit,
				mobSoulvyreLiving,
				mobStimuloDeath,
				mobStimuloHit,
				mobStimuloLiving,
				mobStimulosusLiving,
				mobSyskerDeath,
				mobSyskerHit,
				mobSyskerLiving,
				mobAlarmoDeath,
				mobAlarmoHit,
				mobAlarmoLiving,
				mobGadgetoidDeath,
				mobGadgetoidHit,
				mobGadgetoidLiving,
				mobGrocculateDeath,
				mobGrocculateHit,
				mobGrocculateLiving,
				mobToxxulousDeath,
				mobToxxulousHit,
				mobToxxulousLiving,
				mobNightwingDeath,
				mobNightwingHit,
				mobNightwingLiving,
				mobConiferonLiving,
				mobConiferonDeath,
				mobConiferonHit,
				mobCorallusLiving,
				mobCottonCandorLiving,
				mobCottonCandorDeath,
				mobCottonCandorHit,
				mobCraexxeusLiving,
				mobCraexxeusDeath,
				mobCraexxeusHit,
				mobCraexxeusCharge,
				mobCreepoidLiving,
				mobCreepoidDeath,
				mobCreepoidHit,
				mobCrystalConstructLiving,
				mobCyclopsDeath,
				mobCyclopsHit,
				mobCyclopsLiving,
				mobDarkBeastDeath,
				mobDarkBeastHit,
				mobDarkBeastLiving,
				mobDeathHunterDeath,
				mobDeathHunterHit,
				mobDeathHunterLiving, 
				mobDicerDeath,
				mobDicerHit,
				mobDicerLiving,
				mobDistorterDeath,
				mobDistorterHit,
				mobDistorterLiving,
				mobDracyonLiving,
				mobDracyonDeath,
				mobDraggyDeath,
				mobDraggyHit,
				mobDraggyLiving,
				mobDyrehornDeath,
				mobDyrehornHit,
				mobDyrehornLiving,
				mobElkanyneDeath,
				mobElkanyneHit,
				mobElkanyneLiving,
				mobElusiveLiving,
				mobElusiveDeath,
				mobElusiveHit,
				mobTreeSpiritLiving,
				mobTreeSpiritDeath,
				mobTreeSpiritHit,
				mobEmbrakeDeath,
				mobEmbrakeHit,
				mobEmbrakeLiving,
				mobEverbeastHit,
				mobEverbeastLiving,
				mobFacelessRunnerDeath,
				mobFacelessRunnerHit,
				mobFacelessRunnerLiving,
				mobFadeDeath,
				mobFadeHit,
				mobFadeLiving,
				mobToranoDeath,
				mobToranoHit,
				mobToranoLiving,
				mobAirheadDeath,
				mobAirheadHit,
				mobAirheadLiving,
				mobTharaflyDeath,
				mobTharaflyHit,
				mobTharaflyLiving,
				mobSquigglerDeath,
				mobSquigglerHit,
				mobStingerHit,
				mobStingerLiving,
				mobSquigglerLiving,
				mobRamradonDeath,
				mobRamradonHit,
				mobRamradonLiving,
				mobWebReaperDeath,
				mobWebReaperHit,
				mobWebReaperLiving,
				mobOcculentDeath,
				mobOcculentHit,
				mobMermageDeath,
				mobCreeperlockTeleport,
				mobMermageHit,
				mobOcculentLiving,
				mobJaweDeath,
				mobJaweHit,
				mobJaweLiving,
				mobCreepirdDeath,
				mobCreepirdHit,
				mobCreepirdLiving,
				mobPodPlantDeath,
				mobPodPlantHit,
				mobPodPlantLiving,
				mobSpectralWizardDeath,
				mobSpectralWizardHit,
				mobSpectralWizardLiving,
				mobEyeCreatureDeath,
				mobEyeCreatureHit,
				mobEyeCreatureLiving,
				mobBouncerDeath,
				mobBouncerHit,
				mobBouncerLiving,
				mobTortioneDeath,
				mobTortioneHit,
				mobTortioneLiving,
				mobTerradonDeath,
				mobTerradonHit,
				mobTerradonLiving,
				mobSpinoledonDeath,
				mobSpinoledonHit,
				mobSpinoledonLiving,
				mobSabretoothDeath,
				mobSabretoothHit,
				mobSabretoothLiving,
				mobMegatheriumDeath,
				mobMegatheriumHit,
				mobMegatheriumLiving,
				mobKaiyuDeath,
				mobKaiyuHit,
				mobKaiyuLiving,
				mobGiantSlugDeath,
				mobGiantSlugHit,
				mobGiantSlugLiving,
				mobGiantSlugStep,
				mobDiocusDeath,
				mobDiocusHit,
				mobDiocusLiving,
				mobNightmareSpiderDeath,
				mobNightmareSpiderHit,
				mobNightmareSpiderLiving,
				mobBansheeDeath,
				mobBansheeHit,
				mobBansheeLiving,
				mobRunicGolemChange,
				mobRunicGolemHit,
				mobSpiritDeath,
				mobSpiritLiving,
				mobZargDeath,
				mobZargHit,
				mobZargLiving,
				mobRefluctDeath,
				mobRefluctHit,
				mobRefluctLiving,
				mobLunarcherDeath,
				mobLunarcherHit,
				mobLunarcherLiving,
				mobSeaViperDeath,
				mobSeaViperHit,
				mobSeaViperLiving,
				mobNeptunoDeath,
				mobNeptunoHit,
				mobNeptunoLiving,
				mobZorpDeath,
				mobZorpHit,
				mobZorpLiving,
				mobExplodotDeath,
				mobExplodotHit,
				mobExplodotLiving,
				mobTrotterDeath,
				mobTrotterHit,
				mobTrotterLiving,
				mobTrackerDeath,
				mobTrackerHit,
				mobTrackerLiving,
				mobRawboneDeath,
				mobRawboneHit,
				mobRawboneLiving,
				mobParaviteDeath,
				mobParaviteHit,
				mobParaviteLiving,
				mobLelyetianDeath,
				mobLelyetianHit,
				mobLelyetianLiving,
				mobGrobblerDeath,
				mobGrobblerHit,
				mobGrobblerLiving,
				mobFlyeDeath,
				mobFlyeHit,
				mobFlyeLiving,
				mobMuncherDeath,
				mobMuncherHit,
				mobMuncherLiving,
				mobPoseidoDeath,
				mobPoseidoHit,
				mobPoseidoLiving,
				mobCorateeDeath,
				mobCorateeHit,
				mobCorateeLiving,
				mobCoralonDeath,
				mobCoralonHit,
				mobCoralonLiving,
				mobAnglerDeath,
				mobAnglerHit,
				mobAnglerLiving,
				mobAmphibiorDeath,
				mobAmphibiorHit,
				mobAmphibiorLiving,
				mobVoltronDeath,
				mobVoltronHit,
				mobVoltronLiving,
				mobQuickpocketDeath,
				mobQuickpocketHit,
				mobQuickpocketLiving,
				mobPolytomDeath,
				mobPolytomHit,
				mobPolytomLiving,
				mobMechachronDeath,
				mobMechachronHit,
				mobMechachronLiving,
				mobEnforcerDeath,
				mobEnforcerHit,
				mobEnforcerLiving,
				mobVoliantDeath,
				mobVoliantHit,
				mobVoliantLiving,
				mobSurveyorDeath,
				mobSurveyorHit,
				mobSurveyorLiving,
				mobVolarDeath,
				mobVolarHit,
				mobVolarLiving,
				mobSeekerDeath,
				mobSeekerHit,
				mobSeekerLiving,
				mobOrbiterDeath,
				mobOrbiterHit,
				mobOrbiterLiving,
				mobDawnlightDeath,
				mobDawnlightHit,
				mobDawnlightLiving,
				mobAngelicaDeath,
				mobAngelicaHit,
				mobAngelicaLiving,
				mobArcWizardLiving,
				mobArcWizardDeath,
				mobArcWizardHit,
				mobOmnilightDeath,
				mobOmnilightHit,
				mobOmnilightLiving,
				mobSkeletalCowmanHit,
				mobSkeletalCowmanLiving,
				mobWitherWizardHit,
				mobWitherWizardLiving,
				mobNethengeicBeastDeath,
				mobNethengeicBeastHit,
				mobNethengeicBeastLiving,
				mobInfernalHit,
				mobInfernalLiving,
				mobHellspotDeath,
				mobHellspotHit,
				mobHellspotLiving,
				mobHellcatDeath,
				mobHellcatHit,
				mobHellcatLiving,
				mobFlamewalkerDeath,
				mobFlamewalkerHit,
				mobFlamewalkerLiving,
				mobPigotronDeath,
				mobPigotronHit,
				mobPigotronLiving,
				mobPigotronAppear,
				mobValkyrieDeath,
				mobValkyrieHit,
				mobValkyrieLiving,
				mobSugarfaceDeath,
				mobSugarfaceHit,
				mobSugarfaceLiving,
				mobSkullCreatureDeath,
				mobSkullCreatureHit,
				mobSkullCreatureLiving,
				mobSilencerDeath,
				mobSilencerHit,
				mobSilencerLiving,
				mobShifterDeath,
				mobShifterHit,
				mobShifterLiving,
				mobHunterDeath,
				mobHunterHit,
				mobHunterLiving,
				mobFleshEaterDeath,
				mobFleshEaterHit,
				mobFleshEaterLiving,
				mobFiendDeath,
				mobFiendHit,
				mobFiendLiving,
				mobLollypopperDeath,
				mobFishixDeath,
				mobFishixHit,
				mobFishixLiving,
				mobFungiDeath,
				mobFungiHit,
				mobFungiLiving,
				mobFurlionDeath,
				mobFurlionHit,
				mobFurlionLiving,
				mobGhostDeath,
				mobGhostHit,
				mobGhostineDeath,
				mobGhostineHit,
				mobGhostineLiving,
				mobGhostLiving,
				mobGiantDeath,
				mobGiantHit,
				mobGoalbyDeath,
				mobGoalbyHit,
				mobGoalbyLiving,
				mobGoblinDeath,
				mobGoblinHit,
				mobGoblinLiving,
				mobGoldorthLiving,
				mobGoldorthDeath,
				mobGoldorthHit,
				mobGolemStep, candySlugStep,
				mobEmperorBeastStep,
				mobGrawLiving,
				mobGrawDeath,
				mobGrawHit,
				mobGruntDeath,
				mobGruntHit,
				mobGruntLiving,
				mobGuardianDeath,
				mobGuardianHit,
				mobGyroLiving,
				mobGyroDeath,
				mobGyroHit,
				mobHagDeath,
				mobHagHit,
				mobHagLiving,
				mobHiveKingLiving,
				mobHiveKingDeath,
				mobHorndronDeath,
				mobHorndronHit,
				mobHorndronLiving,
				mobHoronLiving,
				mobHoronDeath,
				mobHoronHit,
				mobHostDeath,
				mobHostDrop,
				mobHostLiving,
				mobHunchDeath,
				mobHunchHit,
				mobHunchLiving,
				mobHydroliskLiving,
				mobHydroliskDeath,
				mobHydroliskHit,
				mobIosaurDeath,
				mobIosaurHit,
				mobIosaurLiving,
				mobIrklingDeath,
				mobIrklingHit,
				mobIrklingLiving,
				mobKeelerDeath,
				mobKeelerHit,
				mobKeelerLiving,
				mobKeelerRevive,
				mobKingBamBamBamLiving,
				mobKingBamBamBamDeath,
				mobKingBamBamBamHit,
				mobLittleBamSpawn,
				mobKrorLiving,
				mobKingShroomusDeath,
				mobKingShroomusHeal,
				mobKrorDeath,
				mobLingerDeath,
				mobLingerHit,
				mobLingerLiving,
				mobLivingFungiSpawn,
				mobMagickeDeath,
				mobMagickeHit,
				mobMagickeLiving,
				mobMechyonDeath,
				mobMechyonHit,
				mobMechyonLiving,
				mobMotherVoidWalkerDeath,
				mobMotherVoidWalkerHit,
				mobMotherVoidWalkerLiving,
				mobMuckopedeHit,
				mobMuckopedeLiving,
				mobNaturaDeath,
				mobNaturaHit,
				mobNaturaLiving,
				mobNethengeicWitherLiving,
				mobNethengeicWitherDeath,
				mobNethengeicWitherHit,
				mobNightflyDeath,
				mobNightflyHit,
				mobNightflyLiving,
				mobNightWatcherHit,
				mobNightWatcherLiving,
				mobNospikeDeath,
				mobNospikeHit,
				mobNospikeLiving,
				mobParasectDeath,
				mobParasectHit,
				mobParasectLiving,
				mobPenguinDeath,
				mobPenguinHit,
				mobPenguinLiving,
				mobPenumbraLiving,
				mobPenumbraDeath,
				mobPenumbraHit,
				mobPrimordialLiving,
				mobPrimordialDeath,
				mobRainicornDeath,
				mobRainicornHit,
				mobRainicornLiving,
				mobRammerheadDeath,
				mobRammerheadHit,
				mobRammerheadLiving,
				mobReaperDeath,
				mobReaperHit,
				mobReaperLiving,
				mobExoheadDeath,
				mobExoheadHit,
				mobExoheadLiving,
				mobRockRiderSwitch,
				mobRockRiderDeath,
				mobRockRiderHit,
				mobRoloscopeDeath,
				mobRoloscopeHit,
				mobRoloscopeLiving,
				mobSasquatchLiving,
				mobScrubbyHit,
				mobScrubbyLiving,
				mobShadeDeath,
				mobShadeHit,
				mobShadeLiving,
				mobShadowHit,
				mobShadowLiving,
				mobShadowlordLiving,
				mobShadowlordDeath,
				mobShadowlordHit,
				mobSkeletronLiving,
				mobSkeletronDeath,
				mobSkeletronHit,
				mobSkelloxDeath,
				mobSkelloxHit,
				mobSkelloxLiving,
				mobSkipperDeath,
				mobSkipperHit,
				mobSkipperLiving,
				mobSlimerDeath,
				mobSlimerHit,
				mobSlimerLiving,
				mobSmashDeath,
				mobSmashHit,
				mobSmashLiving,
				mobSphinxDeath,
				mobSphinxHit,
				mobSphinxLiving,
				mobSpinuxDeath,
				mobSpinuxHit,
				mobSpinuxLiving,
				mobTerrestrialDeath,
				mobTerrestrialHit,
				mobMirageTeleport,
				mobTerrestrialLiving,
				mobTricksterHide,
				mobTricksterHit,
				mobTricksterLiving,
				mobTyrosaurLiving,
				mobTyrosaurDeath,
				mobTyrosaurHit,
				mobTyrosaurStep,
				mobTyrosaurCharge,
				mobTyrosaurStomp,
				mobTyrosaurReadyStomp,
				mobUrkaDeath,
				mobImmortalLiving,
				mobImmortalDeath,
				mobUrkaHit,
				mobUrkaLiving,
				mobUrsaDeath,
				mobUrsaHit,
				mobUrsaLiving,
				mobVertebronDeath,
				mobVertebronHit,
				mobVertebronLiving,
				mobVisulonLiving,
				mobVisularLiving,
				mobVisularDeath,
				mobVisularHit,
				mobVoidWalkerDeath,
				mobVoidWalkerHit,
				mobVoidWalkerLiving, 
				mobVoxxulonLiving,
				mobVoxxulonDeath,
				mobVoxxulonHit,
				mobWalkerDeath,
				mobWalkerHit,
				mobWalkerLiving,
				mobXxeusLiving,
				mobXxeusDeath,
				mobXxeusHit,
				mobXxeusDash,
				mobYetiDeath,
				mobYetiHit,
				mobYetiLiving,
				mobZhinxDeath,
				mobZhinxHit,
				mobZhinxLiving,
				mobBaumbaJump,
				mobMechbotJump,
				petalCraftingStationSuccess,
				portalAbyss,
				portalAncientCavern,
				portalBarren,
				portalCandyland,
				portalCeleve,
				portalCreeponia,
				portalCrystevia,
				portalDark,
				portalImmortallis,
				portalIromine,
				portalLight,
				portalNatural,
				portalShyrelands,
				runeRandomizer,
				shotClownFire,
				shotCraexxeusFire,
				shotCraexxeusNukeFire,
				shotGuardianFire,
				shotSkelemanFire,
				shotSpiritProtectorFire,
				shotSurgeFire,
				shotArcWizardFire,
				shotMirageFire,
				shotBaumbaFire,
				shotCottonCandorFire,
				shotShyreTrollFire,
				shotVineWizardFire,
				shotHagFire,
				shotMechbotFire,
				shotBaronessFire,
				shotWebReaperFire,
				shotMermageFire,
				shotFungikFire,
				shotLingerFire,
				shotMagickeFire,
				shotWizardBlast,
				shyrelandsDizzy,
				shyrelandsShine,
				shyrelandsWeakness,
				shyrelandsWind,
				staffAtlantic,
				staffBasic,
				staffCandy,
				staffConcussion,
				staffCoral,
				staffCrystevia,
				staffEmber,
				staffEver,
				staffFirefly,
				staffFungal,
				staffJoker,
				staffKaiyu,
				staffLightshine,
				staffLunar,
				staffMeteor,
				staffMoonlight,
				staffNature,
				staffNightmare,
				staffNoxious,
				staffReef,
				staffRejuvenation,
				staffRunic,
				staffShadow,
				staffShow,
				staffShyre,
				staffSky,
				staffSun,
				staffSurge,
				staffTangle,
				staffUltimatum,
				staffWeb,
				teaSinkFill,
				teaSinkUse,
				templeTrapLaugh,
				tributeFail,
				tributeSuccess,
				useCreationSlab,
				veryHeavyStep,
				candyThump,
				shotCherryBlasterFire,
				plantThump,
				vulcaneUse,
				musicBane,
				musicBaroness,
				musicClunkhead,
				musicCorallus,
				musicCottonCandor,
				musicCraexxeus,
				musicCreep,
				musicCrystocore,
				musicDracyon,
				musicElusive,
				musicGraw,
				musicGyro,
				musicHiveKing,
				musicKingBamBamBam,
				musicKingShroomus,
				musicKror,
				musicMechbot,
				musicNethengeicWither,
				musicPrimordialFive,
				musicRockRider,
				musicShadowlord,
				musicSilverfoot,
				musicSmash,
				musicTyrosaur,
				musicVinocorne,
				musicVisualent,
				musicVoxxulon,
				musicXxeus,
				whitewashUse
		);
	}

	private static SoundEvent createSoundEvent(String key, String name) {
		return new SoundEvent(new ResourceLocation("aoa3", key)).setRegistryName("aoa3:" + name);
	}
}
