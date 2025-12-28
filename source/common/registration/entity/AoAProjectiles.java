package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.projectile.arrow.PopShotEntity;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.blaster.*;
import net.tslat.aoa3.content.entity.projectile.cannon.*;
import net.tslat.aoa3.content.entity.projectile.gun.*;
import net.tslat.aoa3.content.entity.projectile.misc.*;
import net.tslat.aoa3.content.entity.projectile.mob.*;
import net.tslat.aoa3.content.entity.projectile.staff.*;
import net.tslat.aoa3.content.entity.projectile.thrown.*;

import java.util.function.Consumer;

public final class AoAProjectiles {
	public static void init() {}

	public static final DeferredHolder<EntityType<?>, EntityType<AnemiaBombEntity>> ANEMIA_BOMB = registerProjectile("anemia_bomb", AnemiaBombEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CannonGrenade>> CANNON_GRENADE = registerProjectile("cannon_grenade", CannonGrenade::new);
	public static final DeferredHolder<EntityType<?>, EntityType<AquaballEntity>> AQUABALL = registerProjectile("aquaball", AquaballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> AQUATIC_SHOT = registerProjectile("aquatic_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ArcwormShotEntity>> ARCWORM_SHOT = registerProjectile("arcworm_shot", ArcwormShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<AtomizerShotEntity>> ATOMIZER_SHOT = registerProjectile("atomizer_shot", AtomizerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BalloonBombEntity>> BALLOON_BOMB = registerProjectile("balloon_bomb", BalloonBombEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> BARON_SHOT = registerProjectile("baron_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BaronessShotEntity>> BARONESS_SHOT = registerProjectile("baroness_shot", BaronessShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BeamerShotEntity>> BEAMER_SHOT = registerProjectile("beamer_shot", BeamerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> BLOOD_DRAINER = registerProjectile("blood_drainer", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BloodballEntity>> BLOODBALL = registerProjectile("bloodball", BloodballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> BLUE_BULLET = registerProjectile("blue_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BlueGuardianShotEntity>> BLUE_GUARDIAN_SHOT = registerProjectile("blue_guardian_shot", BlueGuardianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BombCarrierDynamiteEntity>> BOMB_CARRIER_DYNAMITE = registerProjectile("bomb_carrier_dynamite", BombCarrierDynamiteEntity::new, 0.375f, 0.1875f);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> BONE_BULLET = registerProjectile("bone_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> BONE_PELLET = registerProjectile("bone_pellet", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BozoBallEntity>> BOZO_BALL = registerProjectile("bozo_ball", BozoBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BubbleShotEntity>> BUBBLE_SHOT = registerProjectile("bubble_shot", BubbleShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<BulletShotEntity>> BULLET_SHOT = registerProjectile("bullet_shot", BulletShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> CANNONBALL = registerProjectile("cannonball", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CarrotBallEntity>> CARROT_BALL = registerProjectile("carrot_ball", CarrotBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> CELESTIAL_FALL = registerProjectile("celestial_fall", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ChakramEntity>> CHAKRAM = registerProjectile("chakram", ChakramEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CherryShotEntity>> CHERRY_SHOT = registerProjectile("cherry_shot", CherryShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ChilliShotEntity>> CHILLI_SHOT = registerProjectile("chilli_shot", ChilliShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ClownBallEntity>> CLOWN_BALL = registerProjectile("clown_ball", ClownBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> LIMONITE_BULLET = registerProjectile("limonite_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ClownShotEntity>> CLOWN_SHOT = registerProjectile("clown_shot", ClownShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> CONFETTI_CLUSTER = registerProjectile("confetti_cluster", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> CONFETTI_SHOT = registerProjectile("confetti_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ConstructShotEntity>> CONSTRUCT_SHOT = registerProjectile("construct_shot", ConstructShotEntity::new);
	//public static final DeferredHolder<EntityType<?>, EntityType<CorallusShotEntity>> CORALLUS_SHOT = registerProjectile("corallus_shot", CorallusShotEntity::new, 1.1f, 1.1f);
	public static final DeferredHolder<EntityType<?>, EntityType<CottonCandorShotEntity>> COTTON_CANDOR_SHOT = registerProjectile("cotton_candor_shot", CottonCandorShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CraexxeusNukeEntity>> CRAEXXEUS_NUKE = registerProjectile("craexxeus_nuke", CraexxeusNukeEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CraexxeusShotEntity>> CRAEXXEUS_SHOT = registerProjectile("craexxeus_shot", CraexxeusShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CreepBombEntity>> CREEP_BOMB = registerProjectile("creep_bomb", CreepBombEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CreepTubeEntity>> CREEP_TUBE = registerProjectile("creep_tube", CreepTubeEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<CyanShotEntity>> CYAN_SHOT = registerProjectile("cyan_shot", CyanShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> DEATH_RAY = registerProjectile("death_ray", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<DestroyerShotEntity>> DESTROYER_SHOT = registerProjectile("destroyer_shot", DestroyerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<DestructionShotEntity>> DESTRUCTION_SHOT = registerProjectile("destruction_shot", DestructionShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<DischargeShotEntity>> DISCHARGE_SHOT = registerProjectile("discharge_shot", DischargeShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<DischargeSlugEntity>> DISCHARGE_SLUG = registerProjectile("discharge_slug", DischargeSlugEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> DISCHARGE_PELLET = registerProjectile("discharge_pellet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<DoomShotEntity>> DOOM_SHOT = registerProjectile("doom_shot", DoomShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> ENERGY_SHOT = registerProjectile("energy_shot", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<EradicatorShotEntity>> ERADICATOR_SHOT = registerProjectile("eradicator_shot", EradicatorShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ErebonSticklerShotEntity>> EREBON_STICKLER_SHOT = registerProjectile("erebon_stickler_shot", ErebonSticklerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ErebonSticklerStuckEntity>> EREBON_STICKLER_STUCK = registerProjectile("erebon_stickler_stuck", ErebonSticklerStuckEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<FireflyShotEntity>> FIREFLY_SHOT = registerProjectile("firefly_shot", FireflyShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> FIRESTORM_FALL = registerProjectile("firestorm_fall", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> FIRE_BULLET = registerProjectile("fire_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<FloroRPGEntity>> FLORO_RPG = registerProjectile("floro_rpg", FloroRPGEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<FlowerBallEntity>> FLOWER_BALL = registerProjectile("flower_ball", FlowerBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<FlowerShotEntity>> FLOWER_SHOT = registerProjectile("flower_shot", FlowerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> FRAGMENT_SHOT = registerProjectile("fragment_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<FungalBallEntity>> FUNGAL_BALL = registerProjectile("fungal_ball", FungalBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<FungalRockFragmentEntity>> FUNGAL_ROCK_FRAGMENT = registerProjectile("fungal_rock_fragment", FungalRockFragmentEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<GhoulBallEntity>> GHOUL_BALL = registerProjectile("ghoul_ball", GhoulBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> GHOUL_SHOT = registerProjectile("ghoul_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> GIGA_GREEN_BALL = registerProjectile("giga_green_ball", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<GoldenCannonballEntity>> GOLDEN_CANNONBALL = registerProjectile("golden_cannonball", GoldenCannonballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<GoldShotEntity>> GOLD_SHOT = registerProjectile("gold_shot", GoldShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<GooBallEntity>> GOO_BALL = registerProjectile("goo_ball", GooBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<GrawShotEntity>> GRAW_SHOT = registerProjectile("graw_shot", GrawShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> GREEN_BULLET = registerProjectile("green_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<GreenGuardianShotEntity>> GREEN_GUARDIAN_SHOT = registerProjectile("green_guardian_shot", GreenGuardianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<GrenadeEntity>> GRENADE = registerProjectile("grenade", GrenadeEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HagShotEntity>> HAG_SHOT = registerProjectile("hag_shot", HagShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HardenedParapiranhaEntity>> HARDENED_PARAPIRANHA = registerProjectile("hardened_parapiranha", HardenedParapiranhaEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HaunterShotEntity>> HAUNTER_SHOT = registerProjectile("haunter_shot", HaunterShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyBlueCannonballEntity>> HEAVY_BLUE_CANNONBALL = registerProjectile("heavy_blue_cannonball", HeavyBlueCannonballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyBoneCannonballEntity>> HEAVY_BONE_CANNONBALL = registerProjectile("heavy_bone_cannonball", HeavyBoneCannonballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyCannonballEntity>> HEAVY_CANNONBALL = registerProjectile("heavy_cannonball", HeavyCannonballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyRedCannonballEntity>> HEAVY_RED_CANNONBALL = registerProjectile("heavy_red_cannonball", HeavyRedCannonballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyRunicGuardianShotEntity>> HEAVY_RUNIC_GUARDIAN_SHOT = registerProjectile("heavy_runic_guardian_shot", HeavyRunicGuardianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyShadowballEntity>> HEAVY_SHADOWBALL = registerProjectile("heavy_shadowball", HeavyShadowballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyShowerShotEntity>> HEAVY_SHOWER_SHOT = registerProjectile("heavy_shower_shot", HeavyShowerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyWitherBallEntity>> HEAVY_WITHER_BALL = registerProjectile("heavy_wither_ball", HeavyWitherBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HellfireEntity>> HELLFIRE = registerProjectile("hellfire", HellfireEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HellfireProjectileEntity>> HELLFIRE_TAIL = registerProjectile("hellfire_tail", HellfireProjectileEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HellBubbleShotEntity>> HELL_BUBBLE_SHOT = registerProjectile("hell_bubble", HellBubbleShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<HiveBallEntity>> HIVE_BALL = registerProjectile("hive_ball", HiveBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> HOT_SHOT = registerProjectile("hot_shot", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> ILLUSION_SHOT = registerProjectile("illusion_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> ION_SHOT = registerProjectile("ion_shot", NonPhysicalWeaponProjectile::new, 0.25f, 0.3f);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> IRO_MINER_SHOT = registerProjectile("iro_miner_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<LelyetianShotEntity>> LELYETIAN_SHOT = registerProjectile("lelyetian_shot", LelyetianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> LIGHT_BLASTER_SHOT = registerProjectile("light_blaster_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> LIGHT_IRON_SHOT = registerProjectile("light_iron_shot", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<LightRunicGuardianShotEntity>> LIGHT_RUNIC_GUARDIAN_SHOT = registerProjectile("light_runic_guardian_shot", LightRunicGuardianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> LUNAR_FALL = registerProjectile("lunar_fall", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> LUNA_SHOT = registerProjectile("luna_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<LuxonSticklerShotEntity>> LUXON_STICKLER_SHOT = registerProjectile("luxon_stickler_shot", LuxonSticklerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<LuxonSticklerStuckEntity>> LUXON_STICKLER_STUCK = registerProjectile("luxon_stickler_stuck", LuxonSticklerStuckEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> LYONIC_SHOT = registerProjectile("lyonic_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<MagicBallEntity>> MAGIC_BALL = registerProjectile("magic_ball", MagicBallEntity::new);
	//public static final DeferredHolder<EntityType<?>, EntityType<MechFallEntity>> MECH_FALL = registerProjectile("mech_fall", MechFallEntity::new);
	//public static final DeferredHolder<EntityType<?>, EntityType<MechShotEntity>> MECH_SHOT = registerProjectile("mech_shot", MechShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> METAL_SLUG = registerProjectile("metal_slug", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> METEOR_FALL = registerProjectile("meteor_fall", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> MIND_BLASTER_SHOT = registerProjectile("mind_blaster_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> MINI_GREEN_BALL = registerProjectile("mini_green_ball", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ModuloShotEntity>> MODULO_SHOT = registerProjectile("modulo_shot", ModuloShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> MOONLIGHT_FALL = registerProjectile("moonlight_fall", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<MoonDestroyerShotEntity>> MOON_DESTROYER_SHOT = registerProjectile("moon_destroyer_shot", MoonDestroyerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> MOON_MAKER = registerProjectile("moon_maker", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> MOON_SHINER_SHOT = registerProjectile("moon_shiner_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> MOON_SHOT = registerProjectile("moon_shot", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<MissileMakerEntity>> MISSILE_MAKER = registerProjectile("missile_maker", MissileMakerEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> NIGHTMARE_FALL = registerProjectile("nightmare_fall", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NoxiousShotEntity>> NOXIOUS_SHOT = registerProjectile("noxious_shot", NoxiousShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> ODIOUS_SHOT = registerProjectile("odious_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<OmnilightShotEntity>> OMNILIGHT_SHOT = registerProjectile("omnilight_shot", OmnilightShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<OrangeCannonballEntity>> ORANGE_CANNONBALL = registerProjectile("orange_cannonball", OrangeCannonballEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> ORBOCRON_SHOT = registerProjectile("orbocron_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> PARALYZER_SHOT = registerProjectile("paralyzer_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> PARTY_POPPER_SHOT = registerProjectile("party_popper_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> PHANTOM_SHOT = registerProjectile("phantom_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PlutonSticklerShotEntity>> PLUTON_STICKLER_SHOT = registerProjectile("pluton_stickler_shot", PlutonSticklerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PlutonSticklerStuckEntity>> PLUTON_STICKLER_STUCK = registerProjectile("pluton_stickler_stuck", PlutonSticklerStuckEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> POISON_SHOT = registerProjectile("poison_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PolytomShotEntity>> POLYTOM_SHOT = registerProjectile("polytom_shot", PolytomShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PopShotEntity>> POP_SHOT = registerProjectile("pop_shot", PopShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> POWER_RAY = registerProjectile("power_ray", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> POWER_SHOT = registerProjectile("power_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> PRIMORDIAL_SHOT = registerProjectile("primordial_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> PROTON_SHOT = registerProjectile("proton_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<RainbowShotEntity>> RAINBOW_SHOT = registerProjectile("rainbow_shot", RainbowShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> RED_BULLET = registerProjectile("red_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<RedGuardianShotEntity>> RED_GUARDIAN_SHOT = registerProjectile("red_guardian_shot", RedGuardianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ReeferShotEntity>> REEFER_SHOT = registerProjectile("reefer_shot", ReeferShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> REVOLUTION_SHOT = registerProjectile("revolution_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<RockFragmentEntity>> ROCK_FRAGMENT = registerProjectile("rock_fragment", RockFragmentEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<RosidianShotEntity>> ROSIDIAN_SHOT = registerProjectile("rosidian_shot", RosidianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<RPGEntity>> RPG = registerProjectile("rpg", RPGEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<RunicBombEntity>> RUNIC_BOMB = registerProjectile("runic_bomb", RunicBombEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<RunicGuardianShotEntity>> RUNIC_GUARDIAN_SHOT = registerProjectile("runic_guardian_shot", RunicGuardianShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> SEAOCRON_SHOT = registerProjectile("seaocron_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> SEED_DART = registerProjectile("seed_dart", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SelyanSticklerShotEntity>> SELYAN_STICKLER_SHOT = registerProjectile("selyan_stickler_shot", SelyanSticklerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SelyanSticklerStuckEntity>> SELYAN_STICKLER_STUCK = registerProjectile("selyan_stickler_stuck", SelyanSticklerStuckEntity::new);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShadowlordShotEntity>> SHADOWLORD_SHOT = registerProjectile("shadowlord_shot", ShadowlordShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ShoeShotEntity>> SHOE_SHOT = registerProjectile("shoe_shot", ShoeShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ShowerShotEntity>> SHOWER_SHOT = registerProjectile("shower_shot", ShowerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> SHROOM_BULLET = registerProjectile("shroom_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ShyreBeamEntity>> SHYRE_BEAM = registerProjectile("shyre_beam", ShyreBeamEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ShyreShotEntity>> SHYRE_SHOT = registerProjectile("shyre_shot", ShyreShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> SKULLO_SHOT = registerProjectile("skullo_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SkyShotEntity>> SKY_SHOT = registerProjectile("sky_shot", SkyShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SliceStarEntity>> SLICE_STAR = registerProjectile("slice_star", SliceStarEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> SMILEY_CANNONBALL = registerProjectile("smiley_cannonball", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SmileBlasterEntity>> SMILE_BLASTER = registerProjectile("smile_blaster", SmileBlasterEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> SNIPER_SLUG = registerProjectile("sniper_slug", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> SOUL_DRAINER_SHOT = registerProjectile("soul_drainer_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SoulStormEntity>> SOUL_STORM_SHOT = registerProjectile("soul_storm_shot", SoulStormEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SpectralShotEntity>> SPECTRAL_SHOT = registerProjectile("spectral_shot", SpectralShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SpiritualShotEntity>> SPIRITUAL_SHOT = registerProjectile("spiritual_shot", SpiritualShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<StickyCoolBombEntity>> STICKY_COOL_BOMB = registerProjectile("sticky_cool_bomb", StickyCoolBombEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<StickyRedBombEntity>> STICKY_RED_BOMB = registerProjectile("sticky_red_bomb", StickyRedBombEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<SunShotEntity>> SUN_SHOT = registerProjectile("sun_shot", SunShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> SUPER_GREEN_BALL = registerProjectile("super_green_ball", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> SWARM_SHOT = registerProjectile("swarm_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> TANGLE_FALL = registerProjectile("tangle_fall", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ConstructTerrorShotEntity>> TERROR_CONSTRUCT_SHOT = registerProjectile("terror_construct_shot", ConstructTerrorShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<TidalWaveEntity>> TIDAL_WAVE = registerProjectile("tidal_wave", TidalWaveEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> TOXIC_BULLET = registerProjectile("toxic_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> TOXIC_SHOT = registerProjectile("toxic_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<TriDischargeShotEntity>> TRI_DISCHARGE_SHOT = registerProjectile("tri_discharge_shot", TriDischargeShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> ULTIMATUM_SHOT = registerProjectile("ultimatum_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> ULTRA_GREEN_BALL = registerProjectile("ultra_green_ball", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<ValkyrieShotEntity>> VALKYRIE_SHOT = registerProjectile("valkyrie_shot", ValkyrieShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<VineWizardShotEntity>> VINE_WIZARD_SHOT = registerProjectile("vine_wizard_shot", VineWizardShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<VolatileCannonballEntity>> VOLATILE_CANNONBALL = registerProjectile("volatile_cannonball", VolatileCannonballEntity::new);
	//public static final DeferredHolder<EntityType<?>, EntityType<VoxxulonMeteorEntity>> VOXXULON_METEOR = registerProjectile("voxxulon_meteor", VoxxulonMeteorEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> VOX_CANNONBALL = registerProjectile("vox_cannonball", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<VulkramEntity>> VULKRAM = registerProjectile("vulkram", VulkramEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> WART_DART = registerProjectile("wart_dart", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<WaterBalloonBombEntity>> WATER_BALLOON_BOMB = registerProjectile("water_balloon_bomb", WaterBalloonBombEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> WATER_SHOT = registerProjectile("water_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<WeightedShowerShotEntity>> WEIGHTED_SHOWER_SHOT = registerProjectile("weighted_shower_shot", WeightedShowerShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<WhiteBallEntity>> WHITE_BALL = registerProjectile("white_ball", WhiteBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> WINDER_SHOT = registerProjectile("winder_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<WitherBallEntity>> WITHER_BALL = registerProjectile("wither_ball", WitherBallEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<NonPhysicalWeaponProjectile>> WITHER_SHOT = registerProjectile("wither_shot", NonPhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<WrathShotEntity>> WRATH_SHOT = registerProjectile("wrath_shot", WrathShotEntity::new);
	public static final DeferredHolder<EntityType<?>, EntityType<PhysicalWeaponProjectile>> YELLOW_BULLET = registerProjectile("yellow_bullet", PhysicalWeaponProjectile::new);
	public static final DeferredHolder<EntityType<?>, EntityType<YellowGuardianShotEntity>> YELLOW_GUARDIAN_SHOT = registerProjectile("yellow_guardian_shot", YellowGuardianShotEntity::new);

	public static final DeferredHolder<EntityType<?>, EntityType<StoneGiantRockEntity>> STONE_GIANT_ROCK = registerProjectile("stone_giant_rock", StoneGiantRockEntity::new, 0.5f, 0.5f);
	public static final DeferredHolder<EntityType<?>, EntityType<TreeSpiritSpriteEntity>> TREE_SPIRIT_SPRITE = registerProjectile("tree_spirit_sprite", TreeSpiritSpriteEntity::new, 0.375f, 0.375f, 1);
	public static final DeferredHolder<EntityType<?>, EntityType<FireballEntity>> FIREBALL = registerProjectile("fireball", FireballEntity::new, 0.25f, 0.25f);
	public static final DeferredHolder<EntityType<?>, EntityType<StickyFireballEntity>> STICKY_FIREBALL = registerProjectile("sticky_fireball", StickyFireballEntity::new, 0.25f, 0.25f);

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerProjectile(String registryName, EntityType.EntityFactory<T> factory) {
		return registerProjectile(registryName, factory, 0.25f, 0.25f);
	}

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerProjectile(String registryName, EntityType.EntityFactory<T> factory, float width, float height) {
		return registerProjectile(registryName, factory, width, height, 3);
	}

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerProjectile(String registryName, EntityType.EntityFactory<T> factory, float width, float height, int updateInterval) {
		return registerProjectile(registryName, factory, width, height, updateInterval, EntityType.Builder::noSave);
	}

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerProjectile(String registryName, EntityType.EntityFactory<T> factory, float width, float height, int updateInterval, Consumer<EntityType.Builder<T>> builderMod) {
		EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).clientTrackingRange(8).setTrackingRange(120).setUpdateInterval(updateInterval);

		builderMod.accept(typeBuilder);

		return AoARegistries.ENTITIES.register(registryName, () -> {
			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return entityType;
		});
	}
}
