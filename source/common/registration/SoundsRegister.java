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

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class SoundsRegister {
	public static final SoundEvent MUSIC_NULL = ObjectHolder();

	public static final SoundEvent BARON_BOMB_PRIMING = ObjectHolder();
	public static final SoundEvent BARON_BOMB_SPAWN = ObjectHolder();
	public static final SoundEvent BLOODLUST_COLLECT = ObjectHolder();
	public static final SoundEvent BONE_HORN_CALL = ObjectHolder();
	public static final SoundEvent BUBBLE_SHOT_POP = ObjectHolder();
	public static final SoundEvent CANDY_SNAIL_STEP = ObjectHolder();
	public static final SoundEvent CANDY_THUMP = ObjectHolder();
	public static final SoundEvent CHAINSAW_USE = ObjectHolder();
	public static final SoundEvent CREATION_FORGE_USE = ObjectHolder();
	public static final SoundEvent CRYSTAL_CREATOR_USE = ObjectHolder();
	public static final SoundEvent DECLOGGING_TABLE_USE = ObjectHolder();
	public static final SoundEvent ENTITY_GENERIC_DINO_STEP = ObjectHolder();
	public static final SoundEvent ENTITY_PIXON_HARVEST = ObjectHolder();
	public static final SoundEvent ENTITY_PIXON_LIVING = ObjectHolder();
	public static final SoundEvent BIG_DAY_START = ObjectHolder();
	public static final SoundEvent BLOOD_HUNT_START = ObjectHolder();
	public static final SoundEvent CREEP_DAY_START = ObjectHolder();
	public static final SoundEvent DEATH_DAY_START = ObjectHolder();
	public static final SoundEvent LUNAR_INVASION_START = ObjectHolder();
	public static final SoundEvent SOUL_SCURRY_START = ObjectHolder();
	public static final SoundEvent EXTRACTION_SUCCESS = ObjectHolder();
	public static final SoundEvent FORAGING_LOOT = ObjectHolder();
	public static final SoundEvent GOO_BALL_IMPACT = ObjectHolder();
	public static final SoundEvent GOOFY_TOOL_FAIL = ObjectHolder();
	public static final SoundEvent ABOMINATOR_FIRE = ObjectHolder();
	public static final SoundEvent ARCHERGUN_FIRE = ObjectHolder();
	public static final SoundEvent ARTIFACT_FIRE = ObjectHolder();
	public static final SoundEvent ATOMIZER_FIRE = ObjectHolder();
	public static final SoundEvent BALL_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent BIG_BLAST_FIRE = ObjectHolder();
	public static final SoundEvent BLOWPIPE_FIRE = ObjectHolder();
	public static final SoundEvent BOOM_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent BUBBLE_GUN_FIRE = ObjectHolder();
	public static final SoundEvent CARROT_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent CHAINGUN_FIRE = ObjectHolder();
	public static final SoundEvent CHUGGER_FIRE = ObjectHolder();
	public static final SoundEvent CLOWNER_FIRE = ObjectHolder();
	public static final SoundEvent COLOUR_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent CONFETTI_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent DARK_GUN_FIRE = ObjectHolder();
	public static final SoundEvent DISCHARGE_GUN_FIRE = ObjectHolder();
	public static final SoundEvent DOOM_GUN_FIRE = ObjectHolder();
	public static final SoundEvent DRAIN_GUN_FIRE = ObjectHolder();
	public static final SoundEvent ENERGY_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent FAST_RIFLE_FIRE = ObjectHolder();
	public static final SoundEvent FLINGER_FIRE = ObjectHolder();
	public static final SoundEvent GAS_GUN_FIRE = ObjectHolder();
	public static final SoundEvent GAUGE_RIFLE_FIRE = ObjectHolder();
	public static final SoundEvent GOLEM_GUN_FIRE = ObjectHolder();
	public static final SoundEvent GRAVITY_BLASTER_FIRE = ObjectHolder();
	public static final SoundEvent HEAT_WAVE_FIRE = ObjectHolder();
	public static final SoundEvent HIGH_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent ILLUSION_REVOLVER_FIRE = ObjectHolder();
	public static final SoundEvent ILLUSION_SMG_FIRE = ObjectHolder();
	public static final SoundEvent ION_BLASTER_FIRE = ObjectHolder();
	public static final SoundEvent JACK_ROCKER_FIRE = ObjectHolder();
	public static final SoundEvent LIGHT_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent LOWER_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent MAGIC_GUN_FIRE = ObjectHolder();
	public static final SoundEvent MECHA_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent MIND_BLASTER_FIRE = ObjectHolder();
	public static final SoundEvent MINIGUN_FIRE = ObjectHolder();
	public static final SoundEvent MINI_PISTOL_FIRE = ObjectHolder();
	public static final SoundEvent MISSILE_MAKER_FIRE = ObjectHolder();
	public static final SoundEvent MONSTER_FIRE = ObjectHolder();
	public static final SoundEvent MOON_SHINER_FIRE = ObjectHolder();
	public static final SoundEvent PARALYZER_FIRE = ObjectHolder();
	public static final SoundEvent PARTY_POPPER_FIRE = ObjectHolder();
	public static final SoundEvent RAY_GUN_FIRE = ObjectHolder();
	public static final SoundEvent REEFER_FIRE = ObjectHolder();
	public static final SoundEvent REVOLUTION_FIRE = ObjectHolder();
	public static final SoundEvent REVOLVER_FIRE = ObjectHolder();
	public static final SoundEvent ROULETTE_FIRE = ObjectHolder();
	public static final SoundEvent RPG_FIRE = ObjectHolder();
	public static final SoundEvent SHADOW_BLASTER_FIRE = ObjectHolder();
	public static final SoundEvent SHOTGUN_FIRE = ObjectHolder();
	public static final SoundEvent SLUGGER_FIRE = ObjectHolder();
	public static final SoundEvent SNIPER_FIRE = ObjectHolder();
	public static final SoundEvent SOUL_SPARK_FIRE = ObjectHolder();
	public static final SoundEvent SPACE_GUN_FIRE = ObjectHolder();
	public static final SoundEvent SPACE_REVOLVER_FIRE = ObjectHolder();
	public static final SoundEvent SPIRIT_SHOWER_FIRE = ObjectHolder();
	public static final SoundEvent SPRAYER_FIRE = ObjectHolder();
	public static final SoundEvent SQUAD_GUN_FIRE = ObjectHolder();
	public static final SoundEvent STAMPEDE_FIRE = ObjectHolder();
	public static final SoundEvent SWARMOTRON_FIRE = ObjectHolder();
	public static final SoundEvent UPPER_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent WHIMSY_WINDER_FIRE = ObjectHolder();
	public static final SoundEvent WITHER_CANNON_FIRE = ObjectHolder();
	public static final SoundEvent WITHERS_WRATH_FIRE = ObjectHolder();
	public static final SoundEvent WOOD_RIFLE_FIRE = ObjectHolder();
	public static final SoundEvent HAUNTING_TABLE_USE = ObjectHolder();
	public static final SoundEvent HEART_STONE_USE = ObjectHolder();
	public static final SoundEvent HEART_STONE_SPAWN = ObjectHolder();
	public static final SoundEvent ENTITY_GENERIC_HEAVY_STEP = ObjectHolder();
	public static final SoundEvent HELLFIRE_IMPACT = ObjectHolder();
	public static final SoundEvent INFUSION_SUCCESS = ObjectHolder();
	public static final SoundEvent PLAYER_LEVEL_100 = ObjectHolder();
	public static final SoundEvent PLAYER_LEVEL_UP = ObjectHolder();
	public static final SoundEvent LOTTO_WIN = ObjectHolder();
	public static final SoundEvent LUNAR_ENRICHMENT_TABLE_USE = ObjectHolder();
	public static final SoundEvent RUNES_CRAFT = ObjectHolder();

	public static final SoundEvent ENTITY_CORATEE_DEATH = ObjectHolder();
	public static final SoundEvent ENTITY_CORATEE_HIT = ObjectHolder();
	public static final SoundEvent ENTITY_CORATEE_LIVING = ObjectHolder();
	public static final SoundEvent ENTITY_ELKANYNE_DEATH = ObjectHolder();
	public static final SoundEvent ENTITY_ELKANYNE_HIT = ObjectHolder();
	public static final SoundEvent ENTITY_ELKANYNE_LIVING = ObjectHolder();
	public static final SoundEvent ENTITY_MEGANEUROPSIS_DEATH = ObjectHolder();
	public static final SoundEvent ENTITY_MEGANEUROPSIS_HIT = ObjectHolder();
	public static final SoundEvent ENTITY_MEGANEUROPSIS_LIVING = ObjectHolder();
	public static final SoundEvent ENTITY_SHIK_DEATH = ObjectHolder();
	public static final SoundEvent ENTITY_SHIK_HIT = ObjectHolder();
	public static final SoundEvent ENTITY_TROTTER_DEATH = ObjectHolder();
	public static final SoundEvent ENTITY_TROTTER_HIT = ObjectHolder();
	public static final SoundEvent ENTITY_TROTTER_LIVING = ObjectHolder();

	public static final SoundEvent MOB_AIRHEAD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_AIRHEAD_HIT = ObjectHolder();
	public static final SoundEvent MOB_AIRHEAD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ALARMO_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ALARMO_HIT = ObjectHolder();
	public static final SoundEvent MOB_ALARMO_LIVING = ObjectHolder();
	public static final SoundEvent MOB_AMPHIBIOR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_AMPHIBIOR_HIT = ObjectHolder();
	public static final SoundEvent MOB_AMPHIBIOR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_AMPHIBIYTE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_AMPHIBIYTE_HIT = ObjectHolder();
	public static final SoundEvent MOB_AMPHIBIYTE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ANEMIA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ANEMIA_HIT = ObjectHolder();
	public static final SoundEvent MOB_ANEMIA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ANGELICA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ANGELICA_HIT = ObjectHolder();
	public static final SoundEvent MOB_ANGELICA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ANGLER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ANGLER_HIT = ObjectHolder();
	public static final SoundEvent MOB_ANGLER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_APPARITION_DEATH = ObjectHolder();
	public static final SoundEvent MOB_APPARITION_HIT = ObjectHolder();
	public static final SoundEvent MOB_APPARITION_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ARCBEAST_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ARCBEAST_HIT = ObjectHolder();
	public static final SoundEvent MOB_ARCBEAST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ARCHVINE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ARCHVINE_HIT = ObjectHolder();
	public static final SoundEvent MOB_ARCHVINE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ARCWORM_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ARCWORM_HIT = ObjectHolder();
	public static final SoundEvent MOB_ARCWORM_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ARC_WIZARD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ARC_WIZARD_HIT = ObjectHolder();
	public static final SoundEvent MOB_ARC_WIZARD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ARIEL_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ARIEL_HIT = ObjectHolder();
	public static final SoundEvent MOB_ARIEL_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ARKBACK_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ARKBACK_HIT = ObjectHolder();
	public static final SoundEvent MOB_ARKBACK_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ARKZYNE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ARKZYNE_HIT = ObjectHolder();
	public static final SoundEvent MOB_ARKZYNE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_AUTOMATON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_AUTOMATON_HIT = ObjectHolder();
	public static final SoundEvent MOB_AUTOMATON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_AXIOLIGHT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_AXIOLIGHT_HIT = ObjectHolder();
	public static final SoundEvent MOB_AXIOLIGHT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BANE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BANE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BANSHEE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BANSHEE_HIT = ObjectHolder();
	public static final SoundEvent MOB_BANSHEE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BASILISK_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BASILISK_HIT = ObjectHolder();
	public static final SoundEvent MOB_BASILISK_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BAUMBA_JUMP = ObjectHolder();
	public static final SoundEvent MOB_BLOODMIST_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BLOODMIST_HIT = ObjectHolder();
	public static final SoundEvent MOB_BLOODMIST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BLOODSUCKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BLOODSUCKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_BLOODSUCKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BOMB_CARRIER_HIT = ObjectHolder();
	public static final SoundEvent MOB_BOMB_CARRIER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BONEBACK_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BONEBACK_HIT = ObjectHolder();
	public static final SoundEvent MOB_BONEBACK_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BOUNCER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BOUNCER_HIT = ObjectHolder();
	public static final SoundEvent MOB_BOUNCER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BUGEYE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BUGEYE_HIT = ObjectHolder();
	public static final SoundEvent MOB_BUGEYE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_BUSH_BABY_DEATH = ObjectHolder();
	public static final SoundEvent MOB_BUSH_BABY_HIT = ObjectHolder();
	public static final SoundEvent MOB_BUSH_BABY_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CARROTOP_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CARROTOP_HIT = ObjectHolder();
	public static final SoundEvent MOB_CARROTOP_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CAVE_BUG_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CAVE_BUG_HIT = ObjectHolder();
	public static final SoundEvent MOB_CAVE_BUG_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CAVE_CREEP_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CAVE_CREEP_HIT = ObjectHolder();
	public static final SoundEvent MOB_CAVE_CREEP_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CELEVE_CLOWN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CELEVE_CLOWN_HIT = ObjectHolder();
	public static final SoundEvent MOB_CELEVE_CLOWN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CHARGER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CHARGER_HIT = ObjectHolder();
	public static final SoundEvent MOB_CHARGER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CHIMERA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CHIMERA_HIT = ObjectHolder();
	public static final SoundEvent MOB_CHIMERA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CHOMPER_HIT = ObjectHolder();
	public static final SoundEvent MOB_CHOMPER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CLOWN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CLOWN_HIT = ObjectHolder();
	public static final SoundEvent MOB_CLOWN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CLUNKHEAD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_COMPEER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_COMPEER_HIT = ObjectHolder();
	public static final SoundEvent MOB_COMPEER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CONIFERON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CONIFERON_HIT = ObjectHolder();
	public static final SoundEvent MOB_CONIFERON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CORALLUS_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CORALLUS_HIT = ObjectHolder();
	public static final SoundEvent MOB_CORALLUS_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CORALLUS_TAUNT = ObjectHolder();
	public static final SoundEvent MOB_CORALON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CORALON_HIT = ObjectHolder();
	public static final SoundEvent MOB_CORALON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_COTTON_CANDOR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_COTTON_CANDOR_HIT = ObjectHolder();
	public static final SoundEvent MOB_COTTON_CANDOR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CRAEXXEUS_CHARGE = ObjectHolder();
	public static final SoundEvent MOB_CRAEXXEUS_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CRAEXXEUS_HIT = ObjectHolder();
	public static final SoundEvent MOB_CRAEXXEUS_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CREEPERLOCK_TELEPORT = ObjectHolder();
	public static final SoundEvent MOB_CREEPIRD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CREEPIRD_HIT = ObjectHolder();
	public static final SoundEvent MOB_CREEPIRD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CREEPOID_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CREEPOID_HIT = ObjectHolder();
	public static final SoundEvent MOB_CREEPOID_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CRUSILISK_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CRUSILISK_HIT = ObjectHolder();
	public static final SoundEvent MOB_CRUSILISK_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CRYPTID_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CRYPTID_HIT = ObjectHolder();
	public static final SoundEvent MOB_CRYPTID_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CRYSTAL_CONSTRUCT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CRYSTAL_CONSTRUCT_HIT = ObjectHolder();
	public static final SoundEvent MOB_CRYSTAL_CONSTRUCT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_CYCLOPS_DEATH = ObjectHolder();
	public static final SoundEvent MOB_CYCLOPS_HIT = ObjectHolder();
	public static final SoundEvent MOB_CYCLOPS_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DARK_BEAST_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DARK_BEAST_HIT = ObjectHolder();
	public static final SoundEvent MOB_DARK_BEAST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DAWNLIGHT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DAWNLIGHT_HIT = ObjectHolder();
	public static final SoundEvent MOB_DAWNLIGHT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DEATH_HUNTER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DEATH_HUNTER_HIT = ObjectHolder();
	public static final SoundEvent MOB_DEATH_HUNTER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DEINOTHERIUM_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DEINOTHERIUM_HIT = ObjectHolder();
	public static final SoundEvent MOB_DEINOTHERIUM_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DESTRUCTOR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DESTRUCTOR_HIT = ObjectHolder();
	public static final SoundEvent MOB_DESTRUCTOR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DEVOURER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DEVOURER_HIT = ObjectHolder();
	public static final SoundEvent MOB_DEVOURER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DICER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DICER_HIT = ObjectHolder();
	public static final SoundEvent MOB_DICER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DIOCUS_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DIOCUS_HIT = ObjectHolder();
	public static final SoundEvent MOB_DIOCUS_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DISTORTER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DISTORTER_HIT = ObjectHolder();
	public static final SoundEvent MOB_DISTORTER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DOUBLER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DOUBLER_HIT = ObjectHolder();
	public static final SoundEvent MOB_DOUBLER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DRACYON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DRACYON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DRAGGY_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DRAGGY_HIT = ObjectHolder();
	public static final SoundEvent MOB_DRAGGY_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DUSTEIVA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DUSTEIVA_HIT = ObjectHolder();
	public static final SoundEvent MOB_DUSTEIVA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DUSTON_HIT = ObjectHolder();
	public static final SoundEvent MOB_DUST_STRIDER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DUST_STRIDER_HIT = ObjectHolder();
	public static final SoundEvent MOB_DUST_STRIDER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_DYREHORN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_DYREHORN_HIT = ObjectHolder();
	public static final SoundEvent MOB_DYREHORN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ECHODAR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ECHODAR_HIT = ObjectHolder();
	public static final SoundEvent MOB_ECHODAR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EILOSAPIEN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_EILOSAPIEN_HIT = ObjectHolder();
	public static final SoundEvent MOB_EILOSAPIEN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ELUSIVE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ELUSIVE_HIT = ObjectHolder();
	public static final SoundEvent MOB_ELUSIVE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EMBRAKE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_EMBRAKE_HIT = ObjectHolder();
	public static final SoundEvent MOB_EMBRAKE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EMPEROR_BEAST_DEATH = ObjectHolder();
	public static final SoundEvent MOB_EMPEROR_BEAST_HIT = ObjectHolder();
	public static final SoundEvent MOB_EMPEROR_BEAST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EMPEROR_BEAST_STEP = ObjectHolder();
	public static final SoundEvent MOB_ENFORCER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ENFORCER_HIT = ObjectHolder();
	public static final SoundEvent MOB_ENFORCER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EVERBEAST_HIT = ObjectHolder();
	public static final SoundEvent MOB_EVERBEAST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EXOHEAD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_EXOHEAD_HIT = ObjectHolder();
	public static final SoundEvent MOB_EXOHEAD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EXPLODOT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_EXPLODOT_HIT = ObjectHolder();
	public static final SoundEvent MOB_EXPLODOT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_EYE_CREATURE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_EYE_CREATURE_HIT = ObjectHolder();
	public static final SoundEvent MOB_EYE_CREATURE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FACELESS_RUNNER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FACELESS_RUNNER_HIT = ObjectHolder();
	public static final SoundEvent MOB_FACELESS_RUNNER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FENIX_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FENIX_HIT = ObjectHolder();
	public static final SoundEvent MOB_FENIX_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FIEND_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FIEND_HIT = ObjectHolder();
	public static final SoundEvent MOB_FIEND_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FISHIX_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FISHIX_HIT = ObjectHolder();
	public static final SoundEvent MOB_FISHIX_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FLAMEWALKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FLAMEWALKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_FLAMEWALKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FLESH_EATER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FLESH_EATER_HIT = ObjectHolder();
	public static final SoundEvent MOB_FLESH_EATER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FLYE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FLYE_HIT = ObjectHolder();
	public static final SoundEvent MOB_FLYE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FUNGI_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FUNGI_HIT = ObjectHolder();
	public static final SoundEvent MOB_FUNGI_LIVING = ObjectHolder();
	public static final SoundEvent MOB_FURLION_DEATH = ObjectHolder();
	public static final SoundEvent MOB_FURLION_HIT = ObjectHolder();
	public static final SoundEvent MOB_FURLION_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GADGETOID_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GADGETOID_HIT = ObjectHolder();
	public static final SoundEvent MOB_GADGETOID_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GHOSTINE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GHOSTINE_HIT = ObjectHolder();
	public static final SoundEvent MOB_GHOSTINE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GHOST_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GHOST_HIT = ObjectHolder();
	public static final SoundEvent MOB_GHOST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GIANT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GIANT_HIT = ObjectHolder();
	public static final SoundEvent MOB_GIANT_SNAIL_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GIANT_SNAIL_HIT = ObjectHolder();
	public static final SoundEvent MOB_GIANT_SNAIL_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GIANT_SNAIL_STEP = ObjectHolder();
	public static final SoundEvent MOB_GOALBY_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GOALBY_HIT = ObjectHolder();
	public static final SoundEvent MOB_GOALBY_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GOBLIN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GOBLIN_HIT = ObjectHolder();
	public static final SoundEvent MOB_GOBLIN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GOLDORTH_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GOLDORTH_HIT = ObjectHolder();
	public static final SoundEvent MOB_GOLDORTH_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GOLEM_STEP = ObjectHolder();
	public static final SoundEvent MOB_GRAW_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GRAW_HIT = ObjectHolder();
	public static final SoundEvent MOB_GRAW_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GRILLFACE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GRILLFACE_HIT = ObjectHolder();
	public static final SoundEvent MOB_GRILLFACE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GRILLFACE_SCARE = ObjectHolder();
	public static final SoundEvent MOB_GROBBLER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GROBBLER_HIT = ObjectHolder();
	public static final SoundEvent MOB_GROBBLER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GROCCULATE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GROCCULATE_HIT = ObjectHolder();
	public static final SoundEvent MOB_GROCCULATE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GRUNT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GRUNT_HIT = ObjectHolder();
	public static final SoundEvent MOB_GRUNT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_GUARDIAN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GUARDIAN_HIT = ObjectHolder();
	public static final SoundEvent MOB_GYRO_DEATH = ObjectHolder();
	public static final SoundEvent MOB_GYRO_HIT = ObjectHolder();
	public static final SoundEvent MOB_GYRO_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HAG_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HAG_HIT = ObjectHolder();
	public static final SoundEvent MOB_HAG_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HELLCAT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HELLCAT_HIT = ObjectHolder();
	public static final SoundEvent MOB_HELLCAT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HELLSPOT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HELLSPOT_HIT = ObjectHolder();
	public static final SoundEvent MOB_HELLSPOT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HIVE_KING_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HIVE_KING_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HORNDRON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HORNDRON_HIT = ObjectHolder();
	public static final SoundEvent MOB_HORNDRON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HORON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HORON_HIT = ObjectHolder();
	public static final SoundEvent MOB_HORON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HOST_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HOST_DROP = ObjectHolder();
	public static final SoundEvent MOB_HOST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HUNCH_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HUNCH_HIT = ObjectHolder();
	public static final SoundEvent MOB_HUNCH_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HUNTER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HUNTER_HIT = ObjectHolder();
	public static final SoundEvent MOB_HUNTER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_HYDROLISK_DEATH = ObjectHolder();
	public static final SoundEvent MOB_HYDROLISK_HIT = ObjectHolder();
	public static final SoundEvent MOB_HYDROLISK_LIVING = ObjectHolder();
	public static final SoundEvent MOB_IMMORTAL_DEATH = ObjectHolder();
	public static final SoundEvent MOB_IMMORTAL_LIVING = ObjectHolder();
	public static final SoundEvent MOB_INFERNAL_HIT = ObjectHolder();
	public static final SoundEvent MOB_INFERNAL_LIVING = ObjectHolder();
	public static final SoundEvent MOB_IOSAUR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_IOSAUR_HIT = ObjectHolder();
	public static final SoundEvent MOB_IOSAUR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_IRKLING_DEATH = ObjectHolder();
	public static final SoundEvent MOB_IRKLING_HIT = ObjectHolder();
	public static final SoundEvent MOB_IRKLING_LIVING = ObjectHolder();
	public static final SoundEvent MOB_JAWE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_JAWE_HIT = ObjectHolder();
	public static final SoundEvent MOB_JAWE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_JUMBO_LIVING = ObjectHolder();
	public static final SoundEvent MOB_KAIYU_DEATH = ObjectHolder();
	public static final SoundEvent MOB_KAIYU_HIT = ObjectHolder();
	public static final SoundEvent MOB_KAIYU_LIVING = ObjectHolder();
	public static final SoundEvent MOB_KEELER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_KEELER_HIT = ObjectHolder();
	public static final SoundEvent MOB_KEELER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_KEELER_REVIVE = ObjectHolder();
	public static final SoundEvent MOB_KING_BAMBAMBAM_DEATH = ObjectHolder();
	public static final SoundEvent MOB_KING_BAMBAMBAM_HIT = ObjectHolder();
	public static final SoundEvent MOB_KING_BAMBAMBAM_LIVING = ObjectHolder();
	public static final SoundEvent MOB_KING_SHROOMUS_DEATH = ObjectHolder();
	public static final SoundEvent MOB_KING_SHROOMUS_HEAL = ObjectHolder();
	public static final SoundEvent MOB_KROR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_KROR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_LELYETIAN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_LELYETIAN_HIT = ObjectHolder();
	public static final SoundEvent MOB_LELYETIAN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_LINGER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_LINGER_HIT = ObjectHolder();
	public static final SoundEvent MOB_LINGER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_LITTLE_BAM_SPAWN = ObjectHolder();
	public static final SoundEvent MOB_LIVING_FUNGI_SPAWN = ObjectHolder();
	public static final SoundEvent MOB_LOLLYPOPPER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_LOST_SOUL_DEATH = ObjectHolder();
	public static final SoundEvent MOB_LOST_SOUL_HIT = ObjectHolder();
	public static final SoundEvent MOB_LOST_SOUL_LIVING = ObjectHolder();
	public static final SoundEvent MOB_LUNARCHER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_LUNARCHER_HIT = ObjectHolder();
	public static final SoundEvent MOB_LUNARCHER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_LURKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_LURKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_LURKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_LUXOCRON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_LUXOCRON_HIT = ObjectHolder();
	public static final SoundEvent MOB_LUXOCRON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MAGICKE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MAGICKE_HIT = ObjectHolder();
	public static final SoundEvent MOB_MAGICKE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MECHACHRON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MECHACHRON_HIT = ObjectHolder();
	public static final SoundEvent MOB_MECHACHRON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MECHBOT_JUMP = ObjectHolder();
	public static final SoundEvent MOB_MECHYON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MECHYON_HIT = ObjectHolder();
	public static final SoundEvent MOB_MECHYON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MERKYRE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MERKYRE_HIT = ObjectHolder();
	public static final SoundEvent MOB_MERKYRE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MERMAGE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MERMAGE_HIT = ObjectHolder();
	public static final SoundEvent MOB_MERMAGE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MIRAGE_TELEPORT = ObjectHolder();
	public static final SoundEvent MOB_MODULO_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MODULO_HIT = ObjectHolder();
	public static final SoundEvent MOB_MODULO_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MOTHER_VOID_WALKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MOTHER_VOID_WALKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_MOTHER_VOID_WALKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MUCKOPEDE_HIT = ObjectHolder();
	public static final SoundEvent MOB_MUCKOPEDE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_MUNCHER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_MUNCHER_HIT = ObjectHolder();
	public static final SoundEvent MOB_MUNCHER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NATURA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NATURA_HIT = ObjectHolder();
	public static final SoundEvent MOB_NATURA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NEPTUNO_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NEPTUNO_HIT = ObjectHolder();
	public static final SoundEvent MOB_NEPTUNO_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NETHENGEIC_BEAST_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NETHENGEIC_BEAST_HIT = ObjectHolder();
	public static final SoundEvent MOB_NETHENGEIC_BEAST_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NETHENGEIC_WITHER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NETHENGEIC_WITHER_HIT = ObjectHolder();
	public static final SoundEvent MOB_NETHENGEIC_WITHER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NIGHTFLY_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NIGHTFLY_HIT = ObjectHolder();
	public static final SoundEvent MOB_NIGHTFLY_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NIGHTMARE_SPIDER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NIGHTMARE_SPIDER_HIT = ObjectHolder();
	public static final SoundEvent MOB_NIGHTMARE_SPIDER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NIGHTWING_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NIGHTWING_HIT = ObjectHolder();
	public static final SoundEvent MOB_NIGHTWING_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NIGHT_WATCHER_HIT = ObjectHolder();
	public static final SoundEvent MOB_NIGHT_WATCHER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NIPPER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NIPPER_HIT = ObjectHolder();
	public static final SoundEvent MOB_NIPPER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_NOSPIKE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_NOSPIKE_HIT = ObjectHolder();
	public static final SoundEvent MOB_NOSPIKE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_OCCULENT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_OCCULENT_HIT = ObjectHolder();
	public static final SoundEvent MOB_OCCULENT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_OMNILIGHT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_OMNILIGHT_HIT = ObjectHolder();
	public static final SoundEvent MOB_OMNILIGHT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ORBITER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ORBITER_HIT = ObjectHolder();
	public static final SoundEvent MOB_ORBITER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PARASECT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_PARASECT_HIT = ObjectHolder();
	public static final SoundEvent MOB_PARASECT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PARAVITE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_PARAVITE_HIT = ObjectHolder();
	public static final SoundEvent MOB_PARAVITE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PENGUIN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_PENGUIN_HIT = ObjectHolder();
	public static final SoundEvent MOB_PENGUIN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PENUMBRA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_PENUMBRA_HIT = ObjectHolder();
	public static final SoundEvent MOB_PENUMBRA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PHANTOM_HIT = ObjectHolder();
	public static final SoundEvent MOB_PHANTOM_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PIGOTRON_APPEAR = ObjectHolder();
	public static final SoundEvent MOB_PIGOTRON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_PIGOTRON_HIT = ObjectHolder();
	public static final SoundEvent MOB_PIGOTRON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PINCHER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_PINCHER_HIT = ObjectHolder();
	public static final SoundEvent MOB_PINCHER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_POD_PLANT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_POD_PLANT_HIT = ObjectHolder();
	public static final SoundEvent MOB_POD_PLANT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_POLYTOM_DEATH = ObjectHolder();
	public static final SoundEvent MOB_POLYTOM_HIT = ObjectHolder();
	public static final SoundEvent MOB_POLYTOM_LIVING = ObjectHolder();
	public static final SoundEvent MOB_PRIMORDIAL_DEATH = ObjectHolder();
	public static final SoundEvent MOB_PRIMORDIAL_LIVING = ObjectHolder();
	public static final SoundEvent MOB_QUICKPOCKET_DEATH = ObjectHolder();
	public static final SoundEvent MOB_QUICKPOCKET_HIT = ObjectHolder();
	public static final SoundEvent MOB_QUICKPOCKET_LIVING = ObjectHolder();
	public static final SoundEvent MOB_QUICKPOCKET_STEAL = ObjectHolder();
	public static final SoundEvent MOB_RAINICORN_DEATH = ObjectHolder();
	public static final SoundEvent MOB_RAINICORN_HIT = ObjectHolder();
	public static final SoundEvent MOB_RAINICORN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_RAMMERHEAD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_RAMMERHEAD_HIT = ObjectHolder();
	public static final SoundEvent MOB_RAMMERHEAD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_RAMRADON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_RAMRADON_HIT = ObjectHolder();
	public static final SoundEvent MOB_RAMRADON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_RAWBONE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_RAWBONE_HIT = ObjectHolder();
	public static final SoundEvent MOB_RAWBONE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_REAPER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_REAPER_HIT = ObjectHolder();
	public static final SoundEvent MOB_REAPER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_REFLUCT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_REFLUCT_HIT = ObjectHolder();
	public static final SoundEvent MOB_REFLUCT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ROCKBITER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ROCKBITER_HIT = ObjectHolder();
	public static final SoundEvent MOB_ROCKBITER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ROCK_RIDER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ROCK_RIDER_HIT = ObjectHolder();
	public static final SoundEvent MOB_ROCK_RIDER_SWITCH = ObjectHolder();
	public static final SoundEvent MOB_ROLOSCOPE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ROLOSCOPE_HIT = ObjectHolder();
	public static final SoundEvent MOB_ROLOSCOPE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_RUNIC_GOLEM_CHANGE = ObjectHolder();
	public static final SoundEvent MOB_RUNIC_GOLEM_HIT = ObjectHolder();
	public static final SoundEvent MOB_SABRETOOTH_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SABRETOOTH_HIT = ObjectHolder();
	public static final SoundEvent MOB_SABRETOOTH_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SASQUATCH_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SCRUBBY_HIT = ObjectHolder();
	public static final SoundEvent MOB_SCRUBBY_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SEA_VIPER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SEA_VIPER_HIT = ObjectHolder();
	public static final SoundEvent MOB_SEA_VIPER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SEEKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SEEKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_SEEKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SHADE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SHADE_HIT = ObjectHolder();
	public static final SoundEvent MOB_SHADE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SHADOWLORD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SHADOWLORD_HIT = ObjectHolder();
	public static final SoundEvent MOB_SHADOWLORD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SHADOW_HIT = ObjectHolder();
	public static final SoundEvent MOB_SHADOW_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SHIFTER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SHIFTER_HIT = ObjectHolder();
	public static final SoundEvent MOB_SHIFTER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SKELETAL_COWMAN_HIT = ObjectHolder();
	public static final SoundEvent MOB_SKELETAL_COWMAN_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SKELETRON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SKELETRON_HIT = ObjectHolder();
	public static final SoundEvent MOB_SKELETRON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SKELLOX_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SKELLOX_HIT = ObjectHolder();
	public static final SoundEvent MOB_SKELLOX_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SKIPPER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SKIPPER_HIT = ObjectHolder();
	public static final SoundEvent MOB_SKIPPER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SKULL_CREATURE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SKULL_CREATURE_HIT = ObjectHolder();
	public static final SoundEvent MOB_SKULL_CREATURE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SLIMER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SLIMER_HIT = ObjectHolder();
	public static final SoundEvent MOB_SLIMER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SMASH_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SMASH_HIT = ObjectHolder();
	public static final SoundEvent MOB_SMASH_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SOULSCORNE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SOULSCORNE_HIT = ObjectHolder();
	public static final SoundEvent MOB_SOULSCORNE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SOULVYRE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SOULVYRE_HIT = ObjectHolder();
	public static final SoundEvent MOB_SOULVYRE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SPECTRAL_WIZARD_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SPECTRAL_WIZARD_HIT = ObjectHolder();
	public static final SoundEvent MOB_SPECTRAL_WIZARD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SPHINX_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SPHINX_HIT = ObjectHolder();
	public static final SoundEvent MOB_SPHINX_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SPINOLEDON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SPINOLEDON_HIT = ObjectHolder();
	public static final SoundEvent MOB_SPINOLEDON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SPINUX_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SPINUX_HIT = ObjectHolder();
	public static final SoundEvent MOB_SPINUX_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SPIRIT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SPIRIT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SQUIGGLER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SQUIGGLER_HIT = ObjectHolder();
	public static final SoundEvent MOB_SQUIGGLER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_STALKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_STALKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_STALKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_STIMULOSUS_LIVING = ObjectHolder();
	public static final SoundEvent MOB_STIMULO_DEATH = ObjectHolder();
	public static final SoundEvent MOB_STIMULO_HIT = ObjectHolder();
	public static final SoundEvent MOB_STIMULO_LIVING = ObjectHolder();
	public static final SoundEvent MOB_STINGER_HIT = ObjectHolder();
	public static final SoundEvent MOB_STINGER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SUGARFACE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SUGARFACE_HIT = ObjectHolder();
	public static final SoundEvent MOB_SUGARFACE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SURVEYOR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SURVEYOR_HIT = ObjectHolder();
	public static final SoundEvent MOB_SURVEYOR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_SYSKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_SYSKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_SYSKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TERRADON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_TERRADON_HIT = ObjectHolder();
	public static final SoundEvent MOB_TERRADON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TERRESTRIAL_DEATH = ObjectHolder();
	public static final SoundEvent MOB_TERRESTRIAL_HIT = ObjectHolder();
	public static final SoundEvent MOB_TERRESTRIAL_LIVING = ObjectHolder();
	public static final SoundEvent MOB_THARAFLY_DEATH = ObjectHolder();
	public static final SoundEvent MOB_THARAFLY_HIT = ObjectHolder();
	public static final SoundEvent MOB_THARAFLY_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TORTIONE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_TORTIONE_HIT = ObjectHolder();
	public static final SoundEvent MOB_TORTIONE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TOXXULOUS_DEATH = ObjectHolder();
	public static final SoundEvent MOB_TOXXULOUS_HIT = ObjectHolder();
	public static final SoundEvent MOB_TOXXULOUS_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TRACKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_TRACKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_TRACKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TREE_SPIRIT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_TREE_SPIRIT_HIT = ObjectHolder();
	public static final SoundEvent MOB_TREE_SPIRIT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TRICKSTER_HIDE = ObjectHolder();
	public static final SoundEvent MOB_TRICKSTER_HIT = ObjectHolder();
	public static final SoundEvent MOB_TRICKSTER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TYROSAUR_CHARGE = ObjectHolder();
	public static final SoundEvent MOB_TYROSAUR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_TYROSAUR_HIT = ObjectHolder();
	public static final SoundEvent MOB_TYROSAUR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_TYROSAUR_READY_STOMP = ObjectHolder();
	public static final SoundEvent MOB_TYROSAUR_STEP = ObjectHolder();
	public static final SoundEvent MOB_TYROSAUR_STOMP = ObjectHolder();
	public static final SoundEvent MOB_URKA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_URKA_HIT = ObjectHolder();
	public static final SoundEvent MOB_URKA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_URSA_DEATH = ObjectHolder();
	public static final SoundEvent MOB_URSA_HIT = ObjectHolder();
	public static final SoundEvent MOB_URSA_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VALKYRIE_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VALKYRIE_HIT = ObjectHolder();
	public static final SoundEvent MOB_VALKYRIE_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VERTEBRON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VERTEBRON_HIT = ObjectHolder();
	public static final SoundEvent MOB_VERTEBRON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VISULAR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VISULAR_HIT = ObjectHolder();
	public static final SoundEvent MOB_VISULAR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VISULON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VOID_WALKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VOID_WALKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_VOID_WALKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VOLAR_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VOLAR_HIT = ObjectHolder();
	public static final SoundEvent MOB_VOLAR_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VOLIANT_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VOLIANT_HIT = ObjectHolder();
	public static final SoundEvent MOB_VOLIANT_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VOLTRON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VOLTRON_HIT = ObjectHolder();
	public static final SoundEvent MOB_VOLTRON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_VOXXULON_DEATH = ObjectHolder();
	public static final SoundEvent MOB_VOXXULON_HIT = ObjectHolder();
	public static final SoundEvent MOB_VOXXULON_LIVING = ObjectHolder();
	public static final SoundEvent MOB_WALKER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_WALKER_HIT = ObjectHolder();
	public static final SoundEvent MOB_WALKER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_WEB_REAPER_DEATH = ObjectHolder();
	public static final SoundEvent MOB_WEB_REAPER_HIT = ObjectHolder();
	public static final SoundEvent MOB_WEB_REAPER_LIVING = ObjectHolder();
	public static final SoundEvent MOB_WITHER_WIZARD_HIT = ObjectHolder();
	public static final SoundEvent MOB_WITHER_WIZARD_LIVING = ObjectHolder();
	public static final SoundEvent MOB_XXEUS_DASH = ObjectHolder();
	public static final SoundEvent MOB_XXEUS_DEATH = ObjectHolder();
	public static final SoundEvent MOB_XXEUS_HIT = ObjectHolder();
	public static final SoundEvent MOB_XXEUS_LIVING = ObjectHolder();
	public static final SoundEvent MOB_YETI_DEATH = ObjectHolder();
	public static final SoundEvent MOB_YETI_HIT = ObjectHolder();
	public static final SoundEvent MOB_YETI_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ZARG_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ZARG_HIT = ObjectHolder();
	public static final SoundEvent MOB_ZARG_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ZHINX_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ZHINX_HIT = ObjectHolder();
	public static final SoundEvent MOB_ZHINX_LIVING = ObjectHolder();
	public static final SoundEvent MOB_ZORP_DEATH = ObjectHolder();
	public static final SoundEvent MOB_ZORP_HIT = ObjectHolder();
	public static final SoundEvent MOB_ZORP_LIVING = ObjectHolder();

	public static final SoundEvent BANE_MUSIC = ObjectHolder();
	public static final SoundEvent BARONESS_MUSIC = ObjectHolder();
	public static final SoundEvent CLUNKHEAD_MUSIC = ObjectHolder();
	public static final SoundEvent CONIFERON_MUSIC = ObjectHolder();
	public static final SoundEvent CORALLUS_MUSIC = ObjectHolder();
	public static final SoundEvent COTTON_CANDOR_MUSIC = ObjectHolder();
	public static final SoundEvent CRAEXXEUS_MUSIC = ObjectHolder();
	public static final SoundEvent CREEP_MUSIC = ObjectHolder();
	public static final SoundEvent CRYSTOCORE_MUSIC = ObjectHolder();
	public static final SoundEvent DRACYON_MUSIC = ObjectHolder();
	public static final SoundEvent ELUSIVE_MUSIC = ObjectHolder();
	public static final SoundEvent FOUR_GUARDIANS_MUSIC = ObjectHolder();
	public static final SoundEvent GOLDORTH_MUSIC = ObjectHolder();
	public static final SoundEvent GRAW_MUSIC = ObjectHolder();
	public static final SoundEvent GYRO_MUSIC = ObjectHolder();
	public static final SoundEvent HIVE_KING_MUSIC = ObjectHolder();
	public static final SoundEvent HORON_MUSIC = ObjectHolder();
	public static final SoundEvent HYDROLISK_MUSIC = ObjectHolder();
	public static final SoundEvent KING_BAMBAMBAM_MUSIC = ObjectHolder();
	public static final SoundEvent KING_SHROOMUS_MUSIC = ObjectHolder();
	public static final SoundEvent KROR_MUSIC = ObjectHolder();
	public static final SoundEvent MECHBOT_MUSIC = ObjectHolder();
	public static final SoundEvent NETHENGEIC_WITHER_MUSIC = ObjectHolder();
	public static final SoundEvent PENUMBRA_MUSIC = ObjectHolder();
	public static final SoundEvent PRIMORDIAL_FIVE_MUSIC = ObjectHolder();
	public static final SoundEvent ROCK_RIDER_MUSIC = ObjectHolder();
	public static final SoundEvent SHADOWLORD_MUSIC = ObjectHolder();
	public static final SoundEvent SILVERFOOT_MUSIC = ObjectHolder();
	public static final SoundEvent SKELETRON_MUSIC = ObjectHolder();
	public static final SoundEvent SMASH_MUSIC = ObjectHolder();
	public static final SoundEvent TYROSAUR_MUSIC = ObjectHolder();
	public static final SoundEvent VINOCORNE_MUSIC = ObjectHolder();
	public static final SoundEvent VISUALENT_MUSIC = ObjectHolder();
	public static final SoundEvent VOXXULON_MUSIC = ObjectHolder();
	public static final SoundEvent PETAL_CRAFTING_STATION_SUCCESS = ObjectHolder();
	public static final SoundEvent PLANT_THUMP = ObjectHolder();
	public static final SoundEvent ABYSS_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent ANCIENT_CAVERN_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent BARREN_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent CANDYLAND_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent CELEVE_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent CREEPONIA_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent CRYSTEVIA_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent DARK_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent IMMORTALLIS_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent IROMINE_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent LIGHT_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent NATURAL_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent SHYRELANDS_PORTAL_ACTIVATE = ObjectHolder();
	public static final SoundEvent RUNE_RANDOMIZER_USE = ObjectHolder();
	public static final SoundEvent ARC_WIZARD_SHOOT = ObjectHolder();
	public static final SoundEvent BARONESS_SHOOT = ObjectHolder();
	public static final SoundEvent BAUMBA_SHOOT = ObjectHolder();
	public static final SoundEvent CHERRY_BLASTER_SHOOT = ObjectHolder();
	public static final SoundEvent CLOWN_SHOOT = ObjectHolder();
	public static final SoundEvent CLUNKHEAD_SHOOT = ObjectHolder();
	public static final SoundEvent COTTON_CANDOR_SHOOT = ObjectHolder();
	public static final SoundEvent CRAEXXEUS_SHOOT = ObjectHolder();
	public static final SoundEvent CRAEXXEUS_NUKE_SHOOT = ObjectHolder();
	public static final SoundEvent FUNGIK_SHOOT = ObjectHolder();
	public static final SoundEvent GUARDIAN_SHOOT = ObjectHolder();
	public static final SoundEvent HAG_SHOOT = ObjectHolder();
	public static final SoundEvent KAIYU_SHOOT = ObjectHolder();
	public static final SoundEvent LINGER_SHOOT = ObjectHolder();
	public static final SoundEvent MAGIC_CREEPER_SHOOT = ObjectHolder();
	public static final SoundEvent MAGICKE_SHOOT = ObjectHolder();
	public static final SoundEvent MECHBOT_SHOOT = ObjectHolder();
	public static final SoundEvent MERMAGE_SHOOT = ObjectHolder();
	public static final SoundEvent MIRAGE_SHOOT = ObjectHolder();
	public static final SoundEvent SHYRE_TROLL_SHOOT = ObjectHolder();
	public static final SoundEvent SKELEMAN_SHOOT = ObjectHolder();
	public static final SoundEvent SPIRIT_PROTECTOR_SHOOT = ObjectHolder();
	public static final SoundEvent UNDEAD_TROLL_SHOOT = ObjectHolder();
	public static final SoundEvent VINE_WIZARD_SHOOT = ObjectHolder();
	public static final SoundEvent WEB_REAPER_SHOOT = ObjectHolder();
	public static final SoundEvent WIZARD_BLAST_SHOOT = ObjectHolder();
	public static final SoundEvent SHYRELANDS_WIND = ObjectHolder();
	public static final SoundEvent ATLANTIC_STAFF_CAST = ObjectHolder();
	public static final SoundEvent BASIC_STAFF_CAST = ObjectHolder();
	public static final SoundEvent CANDY_STAFF_CAST = ObjectHolder();
	public static final SoundEvent CELESTIAL_STAFF_CAST = ObjectHolder();
	public static final SoundEvent CONCUSSION_STAFF_CAST = ObjectHolder();
	public static final SoundEvent CORAL_STAFF_CAST = ObjectHolder();
	public static final SoundEvent CRYSTEVIA_STAFF_CAST = ObjectHolder();
	public static final SoundEvent EMBER_STAFF_CAST = ObjectHolder();
	public static final SoundEvent EVER_STAFF_CAST = ObjectHolder();
	public static final SoundEvent FIREFLY_STAFF_CAST = ObjectHolder();
	public static final SoundEvent FUNGAL_STAFF_CAST = ObjectHolder();
	public static final SoundEvent JOKER_STAFF_CAST = ObjectHolder();
	public static final SoundEvent KAIYU_STAFF_CAST = ObjectHolder();
	public static final SoundEvent LIGHTSHINE_STAFF_CAST = ObjectHolder();
	public static final SoundEvent LUNAR_STAFF_CAST = ObjectHolder();
	public static final SoundEvent METEOR_STAFF_CAST = ObjectHolder();
	public static final SoundEvent MOONLIGHT_STAFF_CAST = ObjectHolder();
	public static final SoundEvent NATURE_STAFF_CAST = ObjectHolder();
	public static final SoundEvent NIGHTMARE_STAFF_CAST = ObjectHolder();
	public static final SoundEvent NOXIOUS_STAFF_CAST = ObjectHolder();
	public static final SoundEvent PHANTOM_STAFF_CAST = ObjectHolder();
	public static final SoundEvent REEF_STAFF_CAST = ObjectHolder();
	public static final SoundEvent REJUVENATION_STAFF_CAST = ObjectHolder();
	public static final SoundEvent RUNIC_STAFF_CAST = ObjectHolder();
	public static final SoundEvent SHADOW_STAFF_CAST = ObjectHolder();
	public static final SoundEvent SHOW_STAFF_CAST = ObjectHolder();
	public static final SoundEvent SHYRE_STAFF_CAST = ObjectHolder();
	public static final SoundEvent SKY_STAFF_CAST = ObjectHolder();
	public static final SoundEvent SUN_STAFF_CAST = ObjectHolder();
	public static final SoundEvent SURGE_STAFF_CAST = ObjectHolder();
	public static final SoundEvent TANGLE_STAFF_CAST = ObjectHolder();
	public static final SoundEvent ULTIMATUM_STAFF_CAST = ObjectHolder();
	public static final SoundEvent WEB_STAFF_CAST = ObjectHolder();
	public static final SoundEvent TEA_SINK_FILL = ObjectHolder();
	public static final SoundEvent TEA_SINK_USE = ObjectHolder();
	public static final SoundEvent TRIBUTE_FAIL = ObjectHolder();
	public static final SoundEvent TRIBUTE_SUCCESS = ObjectHolder();
	public static final SoundEvent CREATION_SLAB_USE = ObjectHolder();
	public static final SoundEvent VERY_HEAVY_STEP = ObjectHolder();
	public static final SoundEvent VULCANE_USE = ObjectHolder();

	public static final SoundEvent OUTLAW_DISC = ObjectHolder();

	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> ev) {
		AdventOfAscension.logOptionalMessage("Beginning sounds registration");

		ev.getRegistry().registerAll(
			createSoundEvent("ambient.music.null", "music_null", true),
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
			createSoundEvent("entity.baron_bomb.priming", "baron_bomb_priming"),
			createSoundEvent("entity.baron_bomb.spawn", "baron_bomb_spawn"),
			createSoundEvent("entity.bloodlust.collect", "bloodlust_collect"),
			createSoundEvent("entity.bubble_shot.pop", "bubble_shot_pop"),
			createSoundEvent("entity.coratee.death", "entity_coratee_death"),
			createSoundEvent("entity.coratee.hit", "entity_coratee_hit"),
			createSoundEvent("entity.coratee.living", "entity_coratee_living"),
			createSoundEvent("entity.elkanyne.death", "entity_elkanyne_death"),
			createSoundEvent("entity.elkanyne.hit", "entity_elkanyne_hit"),
			createSoundEvent("entity.elkanyne.living", "entity_elkanyne_living"),
			createSoundEvent("entity.generic.candy_snail_step", "candy_snail_step"),
			createSoundEvent("entity.generic.candy_thump", "candy_thump"),
			createSoundEvent("entity.generic.dino_step", "entity_generic_dino_step"),
			createSoundEvent("entity.generic.heavy_step", "entity_generic_heavy_step"),
			createSoundEvent("entity.generic.plant_thump", "plant_thump"),
			createSoundEvent("entity.generic.very_heavy_step", "very_heavy_step"),
			createSoundEvent("entity.meganeuropsis.death", "entity_meganeuropsis_death"),
			createSoundEvent("entity.meganeuropsis.hit", "entity_meganeuropsis_hit"),
			createSoundEvent("entity.meganeuropsis.living", "entity_meganeuropsis_living"),
			createSoundEvent("entity.shik.death", "entity_shik_death"),
			createSoundEvent("entity.shik.hit", "entity_shik_hit"),
			createSoundEvent("entity.pixon.harvest", "entity_pixon_harvest"),
			createSoundEvent("entity.pixon.living", "entity_pixon_living"),
			createSoundEvent("entity.player.level_100", "player_level_100"),
			createSoundEvent("entity.player.level_up", "player_level_up"),
			createSoundEvent("entity.trotter.death", "entity_trotter_death"),
			createSoundEvent("entity.trotter.hit", "entity_trotter_hit"),
			createSoundEvent("entity.trotter.living", "entity_trotter_living"),
			createSoundEvent("event.big_day.start", "big_day_start"),
			createSoundEvent("event.blood_hunt.start", "blood_hunt_start"),
			createSoundEvent("event.creation_forge.use", "creation_forge_use"),
			createSoundEvent("event.creep_day.start", "creep_day_start"),
			createSoundEvent("event.crystal_creator.use", "crystal_creator_use"),
			createSoundEvent("event.death_day.start", "death_day_start"),
			createSoundEvent("event.declogging_table.use", "declogging_table_use"),
			createSoundEvent("event.extraction.success", "extraction_success"),
			createSoundEvent("event.foraging.loot", "foraging_loot"),
			createSoundEvent("event.haunting_table.use", "haunting_table_use"),
			createSoundEvent("event.heart_stone.spawn", "heart_stone_spawn"),
			createSoundEvent("event.heart_stone.use", "heart_stone_use"),
			createSoundEvent("event.infusion.success", "infusion_success"),
			createSoundEvent("event.lotto.win", "lotto_win"),
			createSoundEvent("event.lunar_enrichment_table.use", "lunar_enrichment_table_use"),
			createSoundEvent("event.lunar_invasion.start", "lunar_invasion_start"),
			createSoundEvent("event.petal_crafting_station.success", "petal_crafting_station_success"),
			createSoundEvent("event.rune_randomizer.use", "rune_randomizer_use"),
			createSoundEvent("event.runes.craft", "runes_craft"),
			createSoundEvent("event.shyrelands.wind", "shyrelands_wind"),
			createSoundEvent("event.soul_scurry.start", "soul_scurry_start"),
			createSoundEvent("event.tea_sink.fill", "tea_sink_fill"),
			createSoundEvent("event.tea_sink.use", "tea_sink_use"),
			createSoundEvent("event.tribute.fail", "tribute_fail"),
			createSoundEvent("event.tribute.success", "tribute_success"),
			createSoundEvent("item.bone_horn.call", "bone_horn_call"),
			createSoundEvent("item.chainsaw.use", "chainsaw_use"),
			createSoundEvent("item.creation_slab.use", "creation_slab_use"),
			createSoundEvent("item.disc.outlaw", "outlaw_disc", true),
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
			createSoundEvent("item.gun.light_cannon.fire", "light_cannon_fire"),
			createSoundEvent("item.gun.lower_cannon.fire", "lower_cannon_fire"),
			createSoundEvent("item.gun.magic_gun.fire", "magic_gun_fire"),
			createSoundEvent("item.gun.mecha_cannon.fire", "mecha_cannon_fire"),
			createSoundEvent("item.gun.mind_blaster.fire", "mind_blaster_fire"),
			createSoundEvent("item.gun.mini_pistol.fire", "mini_pistol_fire"),
			createSoundEvent("item.gun.minigun.fire", "minigun_fire"),
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
			createSoundEvent("item.gun.swarmotron.fire", "swarmotron_fire"),
			createSoundEvent("item.gun.upper_cannon.fire", "upper_cannon_fire"),
			createSoundEvent("item.gun.whimsy_winder.fire", "whimsy_winder_fire"),
			createSoundEvent("item.gun.wither_cannon.fire", "wither_cannon_fire"),
			createSoundEvent("item.gun.withers_wrath.fire", "withers_wrath_fire"),
			createSoundEvent("item.gun.wood_rifle.fire", "wood_rifle_fire"),
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
			createSoundEvent("item.thrown.goo_ball.impact", "goo_ball_impact"),
			createSoundEvent("item.thrown.hellfire.impact", "hellfire_impact"),
			createSoundEvent("item.tool.goofy_tool.fail", "goofy_tool_fail"),
			createSoundEvent("item.vulcane.use", "vulcane_use"),
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
			createSoundEvent("mob.arc_wizard.death", "mob_arc_wizard_death"),
			createSoundEvent("mob.arc_wizard.hit", "mob_arc_wizard_hit"),
			createSoundEvent("mob.arc_wizard.living", "mob_arc_wizard_living"),
			createSoundEvent("mob.arcbeast.death", "mob_arcbeast_death"),
			createSoundEvent("mob.arcbeast.hit", "mob_arcbeast_hit"),
			createSoundEvent("mob.arcbeast.living", "mob_arcbeast_living"),
			createSoundEvent("mob.archvine.death", "mob_archvine_death"),
			createSoundEvent("mob.archvine.hit", "mob_archvine_hit"),
			createSoundEvent("mob.archvine.living", "mob_archvine_living"),
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
			createSoundEvent("mob.death_hunter.death", "mob_death_hunter_death"),
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
			createSoundEvent("mob.dust_strider.death", "mob_dust_strider_death"),
			createSoundEvent("mob.dust_strider.hit", "mob_dust_strider_hit"),
			createSoundEvent("mob.dust_strider.living", "mob_dust_strider_living"),
			createSoundEvent("mob.dusteiva.death", "mob_dusteiva_death"),
			createSoundEvent("mob.dusteiva.hit", "mob_dusteiva_hit"),
			createSoundEvent("mob.dusteiva.living", "mob_dusteiva_living"),
			createSoundEvent("mob.duston.hit", "mob_duston_hit"),
			createSoundEvent("mob.dyrehorn.death", "mob_dyrehorn_death"),
			createSoundEvent("mob.dyrehorn.hit", "mob_dyrehorn_hit"),
			createSoundEvent("mob.dyrehorn.living", "mob_dyrehorn_living"),
			createSoundEvent("mob.echodar.death", "mob_echodar_death"),
			createSoundEvent("mob.echodar.hit", "mob_echodar_hit"),
			createSoundEvent("mob.echodar.living", "mob_echodar_living"),
			createSoundEvent("mob.eilosapien.death", "mob_eilosapien_death"),
			createSoundEvent("mob.eilosapien.hit", "mob_eilosapien_hit"),
			createSoundEvent("mob.eilosapien.living", "mob_eilosapien_living"),
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
			createSoundEvent("mob.ghost.living", "mob_ghost_living"),
			createSoundEvent("mob.ghostine.death", "mob_ghostine_death"),
			createSoundEvent("mob.ghostine.hit", "mob_ghostine_hit"),
			createSoundEvent("mob.ghostine.living", "mob_ghostine_living"),
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
			createSoundEvent("mob.night_watcher.hit", "mob_night_watcher_hit"),
			createSoundEvent("mob.night_watcher.living", "mob_night_watcher_living"),
			createSoundEvent("mob.nightfly.death", "mob_nightfly_death"),
			createSoundEvent("mob.nightfly.hit", "mob_nightfly_hit"),
			createSoundEvent("mob.nightfly.living", "mob_nightfly_living"),
			createSoundEvent("mob.nightmare_spider.death", "mob_nightmare_spider_death"),
			createSoundEvent("mob.nightmare_spider.hit", "mob_nightmare_spider_hit"),
			createSoundEvent("mob.nightmare_spider.living", "mob_nightmare_spider_living"),
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
			createSoundEvent("mob.rock_rider.death", "mob_rock_rider_death"),
			createSoundEvent("mob.rock_rider.hit", "mob_rock_rider_hit"),
			createSoundEvent("mob.rock_rider.switch", "mob_rock_rider_switch"),
			createSoundEvent("mob.rockbiter.death", "mob_rockbiter_death"),
			createSoundEvent("mob.rockbiter.hit", "mob_rockbiter_hit"),
			createSoundEvent("mob.rockbiter.living", "mob_rockbiter_living"),
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
			createSoundEvent("projectile.arc_wizard.shoot", "arc_wizard_shoot"),
			createSoundEvent("projectile.baroness.shoot", "baroness_shoot"),
			createSoundEvent("projectile.baumba.shoot", "baumba_shoot"),
			createSoundEvent("projectile.cherry_blaster.shoot", "cherry_blaster_shoot"),
			createSoundEvent("projectile.clown.shoot", "clown_shoot"),
			createSoundEvent("projectile.clunkhead.shoot", "clunkhead_shoot"),
			createSoundEvent("projectile.cotton_candor.shoot", "cotton_candor_shoot"),
			createSoundEvent("projectile.craexxeus.shoot", "craexxeus_shoot"),
			createSoundEvent("projectile.craexxeus_nuke.shoot", "craexxeus_nuke_shoot"),
			createSoundEvent("projectile.fungik.shoot", "fungik_shoot"),
			createSoundEvent("projectile.guardian.shoot", "guardian_shoot"),
			createSoundEvent("projectile.hag.shoot", "hag_shoot"),
			createSoundEvent("projectile.kaiyu.shoot", "kaiyu_shoot"),
			createSoundEvent("projectile.linger.shoot", "linger_shoot"),
			createSoundEvent("projectile.magic_creeper.shoot", "magic_creeper_shoot"),
			createSoundEvent("projectile.magicke.shoot", "magicke_shoot"),
			createSoundEvent("projectile.mechbot.shoot", "mechbot_shoot"),
			createSoundEvent("projectile.mermage.shoot", "mermage_shoot"),
			createSoundEvent("projectile.mirage.shoot", "mirage_shoot"),
			createSoundEvent("projectile.shyre_troll.shoot", "shyre_troll_shoot"),
			createSoundEvent("projectile.skeleman.shoot", "skeleman_shoot"),
			createSoundEvent("projectile.spirit_protector.shoot", "spirit_protector_shoot"),
			createSoundEvent("projectile.undead_troll.shoot", "undead_troll_shoot"),
			createSoundEvent("projectile.vine_wizard.shoot", "vine_wizard_shoot"),
			createSoundEvent("projectile.web_reaper.shoot", "web_reaper_shoot"),
			createSoundEvent("projectile.wizard_blast.shoot", "wizard_blast_shoot")
		);
	}

	private static SoundEvent createSoundEvent(String key, String name) {
		return createSoundEvent(key, name, false);
	}

	private static SoundEvent createSoundEvent(String key, String name, boolean isMusic) {
		if (AdventOfAscension.instance().holiday == AdventOfAscension.Holiday.CHRISTMAS && key.endsWith(".fire"))
			key = "misc.bells";

		SoundEvent soundEvent = new SoundEvent(new ResourceLocation("aoa3", key)).setRegistryName("aoa3:" + name);

		if (isMusic && ModUtil.isClient())
			registerMusicType(name, soundEvent);

		return soundEvent;
	}

	private static void registerMusicType(String name, SoundEvent soundEvent) {
		EnumHelperClient.addMusicType(name, soundEvent, 0, 0);
	}

	@SuppressWarnings("ConstantConditions")
	@Nonnull
	private static <T> T ObjectHolder() {
		return null;
	}
}
