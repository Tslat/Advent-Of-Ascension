package net.tslat.aoa3.common.registration;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.entity.AoANpcs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public final class AoAEntityData {
	public static void preInit() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(EventPriority.NORMAL, false, EntityAttributeCreationEvent.class, Stats::registerStats);
	}

	public static void init() {
		SpawnConditions.registerSpawnHeightLimitations();
		SpawnConditions.registerDaylightMobs();
	}

	public static class SpawnConditions {
		public static final HashMap<EntityType<?>, Integer> SPAWN_HEIGHTS = new HashMap<EntityType<?>, Integer>();
		public static final HashSet<EntityType<?>> DAYLIGHT_MOBS = new HashSet<EntityType<?>>();

		private static void registerSpawnHeightLimitations() {
			SPAWN_HEIGHTS.put(AoAAnimals.CORATEE.get(), 125);
			SPAWN_HEIGHTS.put(AoAAnimals.GLOWING_PIXON.get(), 125);
			SPAWN_HEIGHTS.put(AoAMobs.ANGLER.get(), 125);
			SPAWN_HEIGHTS.put(AoAMobs.ARCWORM.get(), 35);
			SPAWN_HEIGHTS.put(AoAMobs.CAVE_CREEPOID.get(), 30);
			SPAWN_HEIGHTS.put(AoAMobs.CREEPERLOCK.get(), 50);
			SPAWN_HEIGHTS.put(AoAMobs.CREEPERLOCK.get(), 50);
			SPAWN_HEIGHTS.put(AoAMobs.CRYPTID.get(), 27);
			SPAWN_HEIGHTS.put(AoAMobs.ECHODAR.get(), 20);
			SPAWN_HEIGHTS.put(AoAMobs.EVERBEAST.get(), 25);
			SPAWN_HEIGHTS.put(AoAMobs.FISCHER.get(), 30);
			SPAWN_HEIGHTS.put(AoAMobs.GHOST.get(), 50);
			SPAWN_HEIGHTS.put(AoAMobs.KEELER.get(), 50);
			SPAWN_HEIGHTS.put(AoAMobs.MAGICAL_CREEPER.get(), 50);
			SPAWN_HEIGHTS.put(AoAMobs.MAGICAL_CREEPER.get(), 50);
			SPAWN_HEIGHTS.put(AoAMobs.MOTHER_VOID_WALKER.get(), 35);
			SPAWN_HEIGHTS.put(AoAMobs.MUNCHER.get(), 125);
			SPAWN_HEIGHTS.put(AoAMobs.NEPTUNO.get(), 125);
			SPAWN_HEIGHTS.put(AoAMobs.PARASECT.get(), 50);
			SPAWN_HEIGHTS.put(AoAMobs.PINCHER.get(), 55);
			SPAWN_HEIGHTS.put(AoAMobs.SEA_VIPER.get(), 125);
			SPAWN_HEIGHTS.put(AoAMobs.SHADE.get(), 30);
			SPAWN_HEIGHTS.put(AoAMobs.SHADOW.get(), 45);
			SPAWN_HEIGHTS.put(AoAMobs.SQUIGGLER.get(), 27);
			SPAWN_HEIGHTS.put(AoAMobs.TRICKSTER.get(), 20);
			SPAWN_HEIGHTS.put(AoAMobs.VOID_CHARGER.get(), 20);
			SPAWN_HEIGHTS.put(AoAMobs.VOID_WALKER.get(), 50);
		}

		private static void registerDaylightMobs() {
			DAYLIGHT_MOBS.addAll(Arrays.asList(
					AoAMobs.AIRHEAD.get(),
					AoAMobs.ANCIENT_GOLEM.get(),
					AoAMobs.ANGLER.get(),
					AoAMobs.ARCBEAST.get(),
					AoAMobs.ARCWORM.get(),
					AoAMobs.ARCHVINE.get(),
					AoAMobs.ARC_FLOWER.get(),
					AoAMobs.ARC_WIZARD.get(),
					AoAMobs.ARIEL.get(),
					AoAMobs.ARKBACK.get(),
					AoAMobs.AXIOLIGHT.get(),
					AoAMobs.BOBO.get(),
					AoAMobs.BOMB_CARRIER.get(),
					AoAMobs.BONEBACK.get(),
					AoAMobs.BONE_CREEPER.get(),
					AoAMobs.BOUNCER.get(),
					AoAMobs.BROCCOHEAD.get(),
					AoAMobs.BUSH_BABY.get(),
					AoAMobs.CANDY_CORNY.get(),
					AoAMobs.CARROTOP.get(),
					AoAMobs.CAVE_CREEPOID.get(),
					AoAMobs.CHARGER.get(),
					AoAMobs.CHERRY_BLASTER.get(),
					AoAMobs.CHIMERA.get(),
					AoAMobs.CHOCKO.get(),
					AoAMobs.CHOMPER.get(),
					AoAMobs.CORNY.get(),
					AoAMobs.CREEPERLOCK.get(),
					AoAMobs.CREEPIRD.get(),
					AoAMobs.CREEPUPLE.get(),
					AoAMobs.CRYPTID.get(),
					AoAMobs.CYCLOPS.get(),
					AoAMobs.DEAD_TREE.get(),
					AoAMobs.DEINOTHERIUM.get(),
					AoAMobs.DESERT_CHARGER.get(),
					AoAMobs.DYREHORN.get(),
					AoAMobs.ECHODAR.get(),
					AoAMobs.EMPEROR_BEAST.get(),
					AoAMobs.FISHIX.get(),
					AoAMobs.FLOWERFACE.get(),
					AoAMobs.FLYE.get(),
					AoAMobs.FURLION.get(),
					AoAMobs.GIANT_SNAIL.get(),
					AoAMobs.GOALBY.get(),
					AoAMobs.GOBLIN.get(),
					AoAMobs.GROBBLER.get(),
					AoAMobs.HAG.get(),
					AoAMobs.HAPPY.get(),
					AoAMobs.HIDING_FUNGI.get(),
					AoAMobs.HILL_CHARGER.get(),
					AoAMobs.HORNDRON.get(),
					AoAMobs.HOST.get(),
					AoAMobs.ICE_GIANT.get(),
					AoAMobs.JUMBO.get(),
					AoAMobs.KEELER.get(),
					AoAMobs.KING_CHARGER.get(),
					AoAMobs.KING_CREEPER.get(),
					AoAMobs.KOKO.get(),
					AoAMobs.KRANKY.get(),
					AoAMobs.LEAFY_GIANT.get(),
					AoAMobs.LELYETIAN_CASTER.get(),
					AoAMobs.LELYETIAN_WARRIOR.get(),
					AoAMobs.LIGHTWALKER.get(),
					AoAMobs.LIVING_FUNGI.get(),
					AoAMobs.LOLLYPOPPER.get(),
					AoAMobs.LUXOCRON.get(),
					AoAMobs.MAGICAL_CREEPER.get(),
					AoAMobs.MECHACHRON.get(),
					AoAMobs.MECHAMATON.get(),
					AoAMobs.MUCKOPEDE.get(),
					AoAMobs.MUNCHER.get(),
					AoAMobs.NEPTUNO.get(),
					AoAMobs.NOSPIKE.get(),
					AoAMobs.OMNILIGHT.get(),
					AoAMobs.PALADIN.get(),
					AoAMobs.PARASECT.get(),
					AoAMobs.POLYTOM.get(),
					AoAMobs.RAMRADON.get(),
					AoAMobs.RUNICORN.get(),
					AoAMobs.RUNICORN_RIDER.get(),
					AoAMobs.SABRETOOTH.get(),
					AoAMobs.SAND_GIANT.get(),
					AoAMobs.SAND_GOLEM.get(),
					AoAMobs.SASQUATCH.get(),
					AoAMobs.SEA_CHARGER.get(),
					AoAMobs.SEA_TROLL.get(),
					AoAMobs.SEA_VIPER.get(),
					AoAMobs.SHYRE_KNIGHT.get(),
					AoAMobs.SNAPPY.get(),
					AoAMobs.SNOW_CHARGER.get(),
					AoAMobs.SOULVYRE.get(),
					AoAMobs.SOULSCORNE.get(),
					AoAMobs.SPHINX.get(),
					AoAMobs.SPIRIT_GUARDIAN.get(),
					AoAMobs.SPIRIT_PROTECTOR.get(),
					AoAMobs.SQUASHER.get(),
					AoAMobs.SQUIGGLER.get(),
					AoAMobs.STICKY.get(),
					AoAMobs.STIMULO.get(),
					AoAMobs.STIMULOSUS.get(),
					AoAMobs.STITCHES.get(),
					AoAMobs.STONE_GIANT.get(),
					AoAMobs.SUNNY.get(),
					AoAMobs.SWAMP_CHARGER.get(),
					AoAMobs.SYSKER.get(),
					AoAMobs.TERRADON.get(),
					AoAMobs.THARAFLY.get(),
					AoAMobs.TIPSY.get(),
					AoAMobs.TORTIONE.get(),
					AoAMobs.TRACKER.get(),
					AoAMobs.TREE_SPIRIT.get(),
					AoAMobs.VOLTRON.get(),
					AoAMobs.WINGED_CREEPER.get(),
					AoAMobs.WOOD_GIANT.get(),
					AoAMobs.YETI.get()
			));
		}
	}

	private static class Stats {
		private static void registerStats(final EntityAttributeCreationEvent ev) {
			AttributeBuilder.create(AoAAnimals.AMBIENT_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.ANGELICA.get()).health(15).flyingSpeed(0.1).knockbackResist(0.1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.BLOOMING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.CORATEE.get()).health(27).moveSpeed(0.2875).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.CREEP_COW.get()).health(20).moveSpeed(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.DAWNLIGHT.get()).health(23).moveSpeed(0.2875).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.EEO.get()).health(10).moveSpeed(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.ELKANYNE.get()).health(20).moveSpeed(0.2875).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.GLARING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.GLEAMING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.GLISTENING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.GLOWING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.HALYCON.get()).health(20).moveSpeed(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.MEGANEUROPSIS.get()).health(9).moveSpeed(0.5).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.NIGHT_WATCHER.get()).health(19).moveSpeed(0).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.PEPPERMINT_SNAIL.get()).health(25).moveSpeed(0.2875).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.RADIANT_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.RAINICORN.get()).health(25).moveSpeed(0.3).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.SHIK.get()).health(5).moveSpeed(0.2).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.SHINING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.SPEARMINT_SNAIL.get()).health(25).moveSpeed(0.2875).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.TROTTER.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.URKA.get()).health(23).moveSpeed(0.2875).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.VOLIANT.get()).health(40).flyingSpeed(0.1).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.SHINY_SQUID.get()).health(15).swimSpeed(1.1f).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.CANDLEFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.CHARRED_CHAR.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.CHOCAW.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.CRIMSON_SKIPPER.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.CRIMSON_STRIPEFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.DARK_HATCHETFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.BLUE_GEMTRAP.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.GREEN_GEMTRAP.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.PURPLE_GEMTRAP.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.RED_GEMTRAP.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.WHITE_GEMTRAP.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.YELLOW_GEMTRAP.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.HYDRONE.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.IRONBACK.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.JAMFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.PARAPIRANHA.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.PEARL_STRIPEFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.RAINBOWFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.RAZORFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.REEFTOOTH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.ROCKETFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.SAILBACK.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.SAPPHIRE_STRIDER.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.SKELECANTH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.TURQUOISE_STRIPEFISH.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAAnimals.VIOLET_SKIPPER.get()).health(4).swimSpeed(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			//AttributeBuilder.create(AoAMiscEntities.BANE_CLONE.get()).health(1).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMiscEntities.BIG_BANE_CLONE.get()).health(10).moveSpeed(0.2875).meleeStrength(25).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMiscEntities.THORNY_PLANT_SPROUT.get()).health(50).moveSpeed(0).meleeStrength(8).knockbackResist(1).followRange(8).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoAMobs.AIRHEAD.get()).health(2).flyingSpeed(0.1).projectileDamage(14).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ALARMO.get()).health(74).moveSpeed(0.2875).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.AMPHIBIOR.get()).health(122).moveSpeed(0.2875).meleeStrength(13.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.AMPHIBIYTE.get()).health(109).moveSpeed(0.28).meleeStrength(13).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ANCIENT_GOLEM.get()).health(50).moveSpeed(0.23).meleeStrength(5).knockbackResist(0.6).armour(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ANEMIA.get()).health(92).flyingSpeed(0.1).projectileDamage(10).knockbackResist(0.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ANGLER.get()).health(112).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.APPARITION.get()).health(82).moveSpeed(0.25).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARCBEAST.get()).health(170).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARCHVINE.get()).health(105).moveSpeed(0.275).meleeStrength(13).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARCWORM.get()).health(163).moveSpeed(0.2875).meleeStrength(16).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARC_FLOWER.get()).health(1).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARC_WIZARD.get()).health(148).moveSpeed(0.207).projectileDamage(16.5).knockbackResist(0.1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARIEL.get()).health(115).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARKBACK.get()).health(200).moveSpeed(0.23).meleeStrength(14).knockbackResist(1).armour(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ARKZYNE.get()).health(135).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.AROCKNID.get()).health(75).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.8).armour(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.AXIOLIGHT.get()).health(167).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.BANE.get()).health(1750).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BANSHEE.get()).health(108).moveSpeed(0.27).meleeStrength(13).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.BARONESS.get()).health(2000).moveSpeed(0.207).projectileDamage(8).knockbackResist(1).followRange(50).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BASILISK.get()).health(119).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BAUMBA.get()).health(134).moveSpeed(0).projectileDamage(12).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BLOODSUCKER.get()).health(109).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.BLUE_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.BLUE_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.BLUE_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.BLUE_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BOBO.get()).health(95).moveSpeed(0.26).meleeStrength(9).knockbackResist(0.05).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BOMB_CARRIER.get()).health(20).moveSpeed(0.2875).meleeStrength(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BONEBACK.get()).health(40).moveSpeed(0.25).meleeStrength(4).knockbackResist(0.1).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BONE_CREEPER.get()).health(45).moveSpeed(0.3).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BOUNCER.get()).health(110).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BROCCOHEAD.get()).health(103).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.25).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BUGEYE.get()).health(15).moveSpeed(0.29).meleeStrength(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.BUSH_BABY.get()).health(10).moveSpeed(0.329).meleeStrength(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CANDY_CORNY.get()).health(83).moveSpeed(0.2875).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CANE_BUG.get()).health(94).moveSpeed(0.29).meleeStrength(8.5).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CARROTOP.get()).health(85).moveSpeed(0.2875).meleeStrength(9).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CASE_CONSTRUCT.get()).health(90).moveSpeed(0.25).meleeStrength(6).knockbackResist(0.2).armour(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CAVE_CREEP.get()).health(65).moveSpeed(0.295).meleeStrength(7.5).knockbackResist(0.6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CAVE_CREEPOID.get()).health(65).moveSpeed(0.27).meleeStrength(0).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CENTINEL.get()).health(90).moveSpeed(0.207).projectileDamage(12).knockbackResist(0.2).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CHERRY_BLASTER.get()).health(87).moveSpeed(0.207).projectileDamage(9).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CHIMERA.get()).health(25).moveSpeed(0.2875).meleeStrength(3.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CHOCKO.get()).health(80).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CHOMPER.get()).health(30).moveSpeed(0.23).meleeStrength(2).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.CLUNKHEAD.get()).health(2200).moveSpeed(0.207).projectileDamage(13).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CONSTRUCT_OF_FLIGHT.get()).health(55).flyingSpeed(0.1).meleeStrength(7.5).armour(3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CONSTRUCT_OF_MIND.get()).health(74).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.4).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CONSTRUCT_OF_RANGE.get()).health(70).moveSpeed(0.207).projectileDamage(10.5).knockbackResist(0.3).armour(3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CONSTRUCT_OF_RESISTANCE.get()).health(80).moveSpeed(0.28).meleeStrength(7).knockbackResist(0.15).armour(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CONSTRUCT_OF_SPEED.get()).health(58).moveSpeed(0.31).meleeStrength(7.5).knockbackResist(0.1).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CONSTRUCT_OF_STRENGTH.get()).health(69).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.5).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CONSTRUCT_OF_TERROR.get()).health(53).flyingSpeed(0.1).projectileDamage(9.5).armour(3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.CORALLUS.get()).health(1800).moveSpeed(0.3286).meleeStrength(25).knockbackResist(1).followRange(52).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CORNY.get()).health(95).moveSpeed(0.2875).meleeStrength(11).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.COTTON_CANDOR.get()).health(3000).flyingSpeed(0.1).projectileDamage(35).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.CRAEXXEUS.get()).health(3000).flyingSpeed(0.1).projectileDamage(10).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.CREEP.get()).health(3000).moveSpeed(0.23).projectileDamage(6).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CREEPERLOCK.get()).health(50).moveSpeed(0.207).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CREEPIRD.get()).health(40).flyingSpeed(0.1).meleeStrength(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CREEPUPLE.get()).health(60).moveSpeed(0.28).meleeStrength(0).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CRUSILISK.get()).health(125).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CRYPTID.get()).health(55).moveSpeed(0.2875).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.CRYSTOCORE.get()).health(3000).flyingSpeed(0.1).meleeStrength(15).knockbackResist(0.6).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.CYCLOPS.get()).health(25).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DAYSEE.get()).health(86).moveSpeed(0.2875).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DEAD_TREE.get()).health(0.5).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DEINOTHERIUM.get()).health(120).moveSpeed(0.29).meleeStrength(10).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DESERT_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DESTRUCTOR.get()).health(999).moveSpeed(0).projectileDamage(15).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DEVOURER.get()).health(135).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DIOCUS.get()).health(64).moveSpeed(0.28).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DISTORTER.get()).health(95).moveSpeed(0).meleeStrength(0).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DOPPELGANGER.get()).health(100).moveSpeed(0.2875).meleeStrength(5).followRange(32).knockbackResist(0.9f).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK, Attributes.KNOCKBACK_RESISTANCE).build(ev);
			AttributeBuilder.create(AoAMobs.DOUBLER.get()).health(70).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.DRACYON.get()).health(1700).flyingSpeed(0.1).meleeStrength(25).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DUSTEIVA.get()).health(111).moveSpeed(0.2875).meleeStrength(12).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DUSTON.get()).health(129).flyingSpeed(0.1).meleeStrength(11).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DUST_STRIDER.get()).health(110).moveSpeed(0.2875).meleeStrength(11.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DWELLER.get()).health(50).moveSpeed(0.3).meleeStrength(6.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.DYREHORN.get()).health(60).moveSpeed(0.329).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ECHODAR.get()).health(50).flyingSpeed(0.1).meleeStrength(6.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ELITE_SKELEMAN.get()).health(120).moveSpeed(0.207).projectileDamage(7).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ELITE_SKELE_HOPPER.get()).health(64).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ELITE_SKELE_PIG.get()).health(100).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.ELUSIVE.get()).health(2000).moveSpeed(0.2875).meleeStrength(15).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.ELUSIVE_CLONE.get()).health(30).moveSpeed(0.2875).meleeStrength(8).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.EMBRAKE.get()).health(70).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.EMPEROR_BEAST.get()).health(150).moveSpeed(0.329).meleeStrength(11).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ENFORCER.get()).health(89).moveSpeed(0.25).meleeStrength(10).knockbackResist(0.4).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.EVERBEAST.get()).health(40).moveSpeed(0.25).meleeStrength(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.EXOHEAD.get()).health(55).moveSpeed(0.2875).meleeStrength(9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.EXPLODOT.get()).health(30).flyingSpeed(0.1).meleeStrength(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FACELESS_FLOATER.get()).health(131).moveSpeed(0.27).meleeStrength(12.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FAKE_ZORP.get()).health(114).moveSpeed(0.2875).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FENIX.get()).health(1).moveSpeed(0.27).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FIEND.get()).health(90).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FISCHER.get()).health(79).moveSpeed(0.3).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FISHIX.get()).health(30).moveSpeed(0.2875).meleeStrength(4).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FLAMEWALKER.get()).health(65).moveSpeed(0.2875).meleeStrength(6.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.FLASH.get()).health(1000).moveSpeed(0.329).meleeStrength(9).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FLESH_EATER.get()).health(95).moveSpeed(0.2875).meleeStrength(9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FLOWERFACE.get()).health(88).moveSpeed(0.2875).meleeStrength(9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FLYE.get()).health(50).flyingSpeed(0.1).meleeStrength(6).knockbackResist(0.2).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FUNGAT.get()).health(80).flyingSpeed(0.1).meleeStrength(8).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FUNGBACK.get()).health(90).moveSpeed(0.27).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FUNGIK.get()).health(110).moveSpeed(0.207).projectileDamage(10).knockbackResist(0.7).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FUNGOCK.get()).health(90).moveSpeed(0.2875).meleeStrength(9).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FUNGUNG.get()).health(100).moveSpeed(0.2875).meleeStrength(11).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.FURLION.get()).health(20).moveSpeed(0.329).meleeStrength(2.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GADGETOID.get()).health(110).moveSpeed(0.27).meleeStrength(11).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GHASTUS.get()).health(15).moveSpeed(0.2875).meleeStrength(10.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GHOST.get()).health(15).moveSpeed(0.2875).meleeStrength(3).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GIANT_SNAIL.get()).health(80).moveSpeed(0.25).meleeStrength(7.5).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GINGERBIRD.get()).health(79).flyingSpeed(0.1).meleeStrength(8).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GINGERBREAD_MAN.get()).health(95).moveSpeed(0.28).meleeStrength(11.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GOALBY.get()).health(30).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GOBLIN.get()).health(20).moveSpeed(0.207).projectileDamage(5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GOLDUM.get()).health(1).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GOLDUS.get()).health(1).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.GRAW.get()).health(2500).flyingSpeed(0.1).projectileDamage(4).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.GREEN_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.GREEN_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.GREEN_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.GREEN_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GRILLFACE.get()).health(131).moveSpeed(0.2875).meleeStrength(16).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.GROBBLER.get()).health(85).moveSpeed(0.3).meleeStrength(8).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.GYRO.get()).health(2500).flyingSpeed(0.1).projectileDamage(5).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.HAG.get()).health(15).moveSpeed(0.23).projectileDamage(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.HAPPY.get()).health(73).moveSpeed(0.207).projectileDamage(7.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.HARKOS.get()).health(1300).moveSpeed(0.329).meleeStrength(13).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.HIDING_FUNGI.get()).health(1).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.HILL_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.HIVE_KING.get()).health(2500).moveSpeed(0.2875).meleeStrength(20).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.HIVE_WORKER.get()).health(30).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.HORNDRON.get()).health(40).moveSpeed(0.25).meleeStrength(5).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.HOST.get()).health(180).moveSpeed(0.23).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.HUNTER.get()).health(123).moveSpeed(0.3).meleeStrength(13.5).knockbackResist(0.25).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ICE_GIANT.get()).health(150).moveSpeed(0.3).meleeStrength(10).knockbackResist(1).followRange(40).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.INFERNAL.get()).health(95).moveSpeed(0.25).meleeStrength(8.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.INMATE_X.get()).health(138).moveSpeed(0.2875).meleeStrength(13.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.INMATE_Y.get()).health(145).moveSpeed(0.27).meleeStrength(15).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.IOSAUR.get()).health(78).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.JAWE.get()).health(93).moveSpeed(0.28).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.JUMBO.get()).health(100).moveSpeed(0.28).meleeStrength(10).knockbackResist(0.35).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.KAIYU.get()).health(70).moveSpeed(0.23).projectileDamage(9).knockbackResist(0.1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.KAJAROS.get()).health(1750).moveSpeed(0.2875).meleeStrength(23).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.KEELER.get()).health(70).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.KING_BAMBAMBAM.get()).health(900).moveSpeed(0.207).projectileDamage(20).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.KING_CHARGER.get()).health(75).moveSpeed(0.329).meleeStrength(9).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.KING_CREEPER.get()).health(85).moveSpeed(0.23).meleeStrength(0).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.KING_SHROOMUS.get()).health(1800).moveSpeed(0.207).projectileDamage(20).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.KLOBBER.get()).health(1000).moveSpeed(0.2875).meleeStrength(20).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.KOKO.get()).health(80).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.KRANKY.get()).health(85).moveSpeed(0.29).meleeStrength(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.KROR.get()).health(2200).moveSpeed(0.2875).meleeStrength(60).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LEAFY_GIANT.get()).health(135).moveSpeed(0.32).meleeStrength(9).knockbackResist(1).followRange(40).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LELYETIAN_CASTER.get()).health(60).moveSpeed(0.23).projectileDamage(7).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LELYETIAN_WARRIOR.get()).health(65).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LIGHTWALKER.get()).health(168).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.LITTLE_BAM.get()).health(30).moveSpeed(0.2875).meleeStrength(4).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LIVING_FUNGI.get()).health(30).moveSpeed(0.2875).meleeStrength(2.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LOLLYPOPPER.get()).health(80).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.05).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LOST_SOUL.get()).health(125).moveSpeed(0.2875).meleeStrength(13).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LUNARCHER.get()).health(118).flyingSpeed(0.1).projectileDamage(14.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LURKER.get()).health(140).moveSpeed(0.2875).meleeStrength(16.5).knockbackResist(0.6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.LUXOCRON.get()).health(169).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MAGICAL_CREEPER.get()).health(55).moveSpeed(0.25).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MECHACHRON.get()).health(114).moveSpeed(0.295).meleeStrength(10.5).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MECHAMATON.get()).health(120).moveSpeed(0.295).meleeStrength(11).knockbackResist(0.8).armour(3.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.MECHBOT.get()).health(2500).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MECHYON.get()).health(85).moveSpeed(0.295).meleeStrength(11).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MERKYRE.get()).health(119).moveSpeed(0.26).meleeStrength(13).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MERMAGE.get()).health(130).moveSpeed(0.207).projectileDamage(14).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.MIRAGE.get()).health(750).moveSpeed(0.23).projectileDamage(8).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.MISKEL.get()).health(1300).moveSpeed(0.207).projectileDamage(14).knockbackResist(0.4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MODULO.get()).health(111).flyingSpeed(0.1).projectileDamage(13).armour(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MOTHER_VOID_WALKER.get()).health(40).moveSpeed(0.2875).meleeStrength(6.5).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MUCKOPEDE.get()).health(30).moveSpeed(0.3).meleeStrength(4).knockbackResist(0.699999988079071).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MUNCHER.get()).health(135).moveSpeed(0).meleeStrength(13.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.MUSHROOM_SPIDER.get()).health(61).moveSpeed(0.28).meleeStrength(7.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NEPTUNO.get()).health(132).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NETHENGEIC_BEAST.get()).health(65).moveSpeed(0.29).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.NETHENGEIC_WITHER.get()).health(1100).moveSpeed(0.32).meleeStrength(2).knockbackResist(0).armour(0).followRange(52).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NIGHTFLY.get()).health(15).flyingSpeed(0.3544).meleeStrength(3.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NIGHTMARE_SPIDER.get()).health(63).moveSpeed(0.28).meleeStrength(7.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NIGHTWING.get()).health(78).flyingSpeed(0.1).meleeStrength(10.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NIGHT_REAPER.get()).health(35).moveSpeed(0.23).meleeStrength(5).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NIPPER.get()).health(45).moveSpeed(0.29).meleeStrength(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.NOSPIKE.get()).health(65).moveSpeed(0.329).meleeStrength(8.5).knockbackResist(0.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.OCCULENT.get()).health(98).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.OKAZOR.get()).health(1200).moveSpeed(0.2875).meleeStrength(50).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.OMNILIGHT.get()).health(156).flyingSpeed(0.1).projectileDamage(16).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.OPTERYX.get()).health(75).moveSpeed(0.329).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.ORANGE_FLOWER.get()).health(30).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.PALADIN.get()).health(109).moveSpeed(0.207).meleeStrength(16).knockbackResist(0.2).armour(18).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.PARASECT.get()).health(80).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.PARAVITE.get()).health(68).moveSpeed(0.2875).meleeStrength(9.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.PINCHER.get()).health(25).moveSpeed(0.29).meleeStrength(4).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.POD_PLANT.get()).health(64).moveSpeed(0.27).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.POLYTOM.get()).health(80).flyingSpeed(0.1).projectileDamage(10).armour(5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.PRIMITIVE_CARROTOP.get()).health(70).moveSpeed(0.3).meleeStrength(9.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.PROSHIELD.get()).health(500).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.PURPLE_FLOWER.get()).health(120).moveSpeed(0.2875).meleeStrength(2).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.RAMRADON.get()).health(68).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.RAWBONE.get()).health(64).moveSpeed(0.2875).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.RAXXAN.get()).health(1000).moveSpeed(0.329).meleeStrength(15).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.REAVER.get()).health(40).moveSpeed(0.2875).meleeStrength(12).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.RED_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.RED_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.RED_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.REFLUCT.get()).health(122).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ROCKBITER.get()).health(60).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.2).armour(1.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ROCK_CRAWLER.get()).health(70).moveSpeed(0.29).meleeStrength(7).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ROCK_CRITTER.get()).health(75).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.ROCK_RIDER.get()).health(1500).moveSpeed(0.329).meleeStrength(30).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.RUNICORN.get()).health(132).moveSpeed(0.3).meleeStrength(14).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.RUNICORN_RIDER.get()).health(132).moveSpeed(0.29).meleeStrength(14).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.RUNIC_GOLEM.get()).health(95).moveSpeed(0.265).meleeStrength(9).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.RUNIC_GUARDIAN.get()).health(109).moveSpeed(0.207).projectileDamage(13.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SABRETOOTH.get()).health(90).moveSpeed(0.29).meleeStrength(10.5).knockbackResist(0.65).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SAND_GIANT.get()).health(80).moveSpeed(0.2875).meleeStrength(10.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SAND_GOLEM.get()).health(45).moveSpeed(0.23).meleeStrength(4.5).knockbackResist(0.8).armour(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SASQUATCH.get()).health(25).moveSpeed(0.2875).meleeStrength(4).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SCRUBBY.get()).health(35).moveSpeed(0.4).meleeStrength(4.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SEA_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SEA_TROLL.get()).health(20).moveSpeed(0.207).projectileDamage(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SEA_VIPER.get()).health(116).moveSpeed(0.2875).meleeStrength(14.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SHADE.get()).health(15).moveSpeed(0.23).meleeStrength(4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SHADOW.get()).health(0.5).moveSpeed(0.1095).meleeStrength(2.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.SHADOWLORD.get()).health(2000).moveSpeed(0.32).meleeStrength(2).knockbackResist(0).armour(0).followRange(52).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SHAVO.get()).health(125).moveSpeed(0.25).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SHIFTER.get()).health(130).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SHYRE_KNIGHT.get()).health(140).moveSpeed(0.2875).meleeStrength(18).knockbackResist(0.35).armour(19).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SILENCER.get()).health(124).moveSpeed(0.2875).meleeStrength(12).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKELEDON.get()).health(120).moveSpeed(0.2875).meleeStrength(11.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKELEKYTE.get()).health(115).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKELEMAN.get()).health(30).moveSpeed(0.207).projectileDamage(2).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKELETAL_COWMAN.get()).health(58).moveSpeed(0.207).projectileDamage(5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.SKELETRON.get()).health(1100).moveSpeed(0.2875).meleeStrength(70).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKELE_ELDER.get()).health(60).moveSpeed(0.25).meleeStrength(3).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKELE_HOPPER.get()).health(16).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKELE_PIG.get()).health(20).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SKULL_CREATURE.get()).health(118).moveSpeed(0.2875).meleeStrength(12).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SLIMER.get()).health(120).moveSpeed(0.28).meleeStrength(11).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.SMASH.get()).health(500).moveSpeed(0.329).meleeStrength(15).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SNAPPY.get()).health(85).moveSpeed(0.2875).meleeStrength(9.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SNOW_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SOULSCORNE.get()).health(140).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SOULVYRE.get()).health(178).moveSpeed(0.2875).meleeStrength(18.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SPECTRAL_WIZARD.get()).health(120).moveSpeed(0.207).projectileDamage(13).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SPHINX.get()).health(20).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SPINOLEDON.get()).health(72).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SPIRIT_GUARDIAN.get()).health(60).moveSpeed(0.2875).meleeStrength(11.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SPIRIT_PROTECTOR.get()).health(60).moveSpeed(0.207).projectileDamage(11.5).knockbackResist(0.15).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SQUASHER.get()).health(105).moveSpeed(0.2875).meleeStrength(9.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SQUIGGLER.get()).health(76).moveSpeed(0.27).meleeStrength(6).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STALKER.get()).health(138).moveSpeed(0.3).meleeStrength(13.5).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STICKY.get()).health(85).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STIMULO.get()).health(164).moveSpeed(0.27).meleeStrength(15.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STIMULOSUS.get()).health(180).moveSpeed(0.2875).meleeStrength(17).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STITCHES.get()).health(85).moveSpeed(0.2875).meleeStrength(10.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STONE_GIANT.get()).health(95).moveSpeed(0.28).meleeStrength(11.5).knockbackResist(1).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STRONG_SKELEMAN.get()).health(60).moveSpeed(0.207).projectileDamage(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STRONG_SKELE_HOPPER.get()).health(32).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.STRONG_SKELE_PIG.get()).health(40).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SUGARFACE.get()).health(124).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SUNNY.get()).health(102).moveSpeed(0.29).meleeStrength(10.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SWAMP_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.SYSKER.get()).health(163).moveSpeed(0.2875).meleeStrength(16).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TERRADON.get()).health(105).moveSpeed(0.329).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TERRESTRIAL.get()).health(129).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.THARAFLY.get()).health(55).flyingSpeed(0.1).meleeStrength(6.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TIPSY.get()).health(85).moveSpeed(0.207).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TORTIONE.get()).health(100).moveSpeed(0.25).meleeStrength(7.5).knockbackResist(0.9).armour(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TOXXULOUS.get()).health(95).moveSpeed(0.295).meleeStrength(12.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TRACKER.get()).health(60).moveSpeed(0.3).meleeStrength(6.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TREE_SPIRIT.get()).health(35).moveSpeed(0.329).meleeStrength(6).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TRICKSTER.get()).health(35).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.TRICKSTER_CLONE.get()).health(35).moveSpeed(0.2875).meleeStrength(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.TYROSAUR.get()).health(4000).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.UNDEAD_TROLL.get()).health(67).moveSpeed(0.207).projectileDamage(8).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.URIOH.get()).health(35).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.URV.get()).health(75).moveSpeed(0.25).meleeStrength(14).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.VALKYRIE.get()).health(115).flyingSpeed(0.1).projectileDamage(13.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.VINE_WIZARD.get()).health(90).moveSpeed(0.207).projectileDamage(12).knockbackResist(0.15).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.VINOCORNE.get()).health(2500).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.VISAGE.get()).health(60).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.VISUALENT.get()).health(2000).flyingSpeed(0.1).meleeStrength(20).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.VISULAR.get()).health(110).flyingSpeed(0.1).meleeStrength(12).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.VISULON.get()).health(150).flyingSpeed(0.1).meleeStrength(15).knockbackResist(0.3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.VOID_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.VOID_WALKER.get()).health(30).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.VOLTRON.get()).health(94).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.2).armour(1.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.VOXXULON.get()).health(2000).moveSpeed(0).meleeStrength(30).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.WEB_REAPER.get()).health(107).moveSpeed(0.207).projectileDamage(13).knockbackResist(0.6).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.WINGED_CREEPER.get()).health(55).moveSpeed(0.29).meleeStrength(0).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.WOOD_GIANT.get()).health(90).moveSpeed(0.2875).meleeStrength(11).knockbackResist(1).armour(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.XXEUS.get()).health(3000).moveSpeed(0.329).meleeStrength(25).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.YELLOW_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.YELLOW_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(15).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.YELLOW_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			//AttributeBuilder.create(AoAMobs.YELLOW_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.YETI.get()).health(30).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ZARG.get()).health(120).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ZHINX.get()).health(60).moveSpeed(0.28).meleeStrength(6.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAMobs.ZORP.get()).health(114).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoANpcs.ASSASSIN.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.SKILL_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.CORRUPTED_TRAVELLER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.CREEP_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.CRYSTAL_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.DUNGEON_KEEPER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.EXPLOSIVES_EXPERT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.GORB_ARMS_DEALER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.GORB_CITIZEN.get()).health(20).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.GORB_ENGINEER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.LELYETIAN_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.LELYETIAN_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.METALLOID.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.NATURALIST.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.PRIMORDIAL_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.PRIMORDIAL_GUIDE.get()).health(30).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.PRIMORDIAL_MERCHANT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.PRIMORDIAL_SPELLBINDER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.PRIMORDIAL_WIZARD.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.PROFESSOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.REALMSHIFTER.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.SHYRE_ARCHER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.SHYRE_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.STORE_KEEPER.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.TOKEN_COLLECTOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.TOY_MERCHANT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.TROLL_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.UNDEAD_HERALD.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.ZAL_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.ZAL_CHILD.get()).health(15).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.ZAL_CITIZEN.get()).health(20).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.ZAL_GROCER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.ZAL_HERBALIST.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.ZAL_SPELLBINDER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoANpcs.ZAL_VENDOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoANpcs.DRYAD_SPRITE.get()).health(5).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			//AttributeBuilder.create(AoAProjectiles.CORALLUS_SHOT.get()).health(20).moveSpeed(0.7).knockbackResist(0).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
		}

		private static class AttributeBuilder {
			private final EntityType<? extends LivingEntity> entityType;
			private final AttributeSupplier.Builder attributeMap;

			private AttributeBuilder(EntityType<? extends LivingEntity> entityType, AttributeSupplier.Builder attributeMap) {
				this.entityType = entityType;
				this.attributeMap = attributeMap;
			}

			private static AttributeBuilder create(EntityType<? extends LivingEntity> entityType) {
				return new AttributeBuilder(entityType, LivingEntity.createLivingAttributes());
			}

			private static AttributeBuilder createMob(EntityType<? extends LivingEntity> entityType) {
				return new AttributeBuilder(entityType, Mob.createMobAttributes());
			}

			private AttributeBuilder health(double health) {
				attributeMap.add(Attributes.MAX_HEALTH, health);

				return this;
			}

			private AttributeBuilder swimSpeed(double speed) {
				attributeMap.add(ForgeMod.SWIM_SPEED.get(), speed);

				return this;
			}

			private AttributeBuilder nametagDistance(double distance) {
				attributeMap.add(ForgeMod.NAMETAG_DISTANCE.get(), distance);;

				return this;
			}

			private AttributeBuilder gravity(double gravity) {
				attributeMap.add(ForgeMod.ENTITY_GRAVITY.get(), gravity);

				return this;
			}

			private AttributeBuilder meleeStrength(double strength) {
				attributeMap.add(Attributes.ATTACK_DAMAGE, strength);

				return this;
			}

			private AttributeBuilder meleeSpeed(double speed) {
				attributeMap.add(Attributes.ATTACK_SPEED, speed);

				return this;
			}

			private AttributeBuilder projectileDamage(double strength) {
				attributeMap.add(AoAAttributes.RANGED_ATTACK_DAMAGE.get(), strength);

				return this;
			}

			private AttributeBuilder moveSpeed(double speed) {
				attributeMap.add(Attributes.MOVEMENT_SPEED, speed);

				return this;
			}

			private AttributeBuilder flyingSpeed(double flyingSpeed) {
				attributeMap.add(Attributes.FLYING_SPEED, flyingSpeed);

				return this;
			}

			private AttributeBuilder armour(double armour) {
				attributeMap.add(Attributes.ARMOR, armour);

				return this;
			}

			private AttributeBuilder armour(double armour, double toughness) {
				attributeMap.add(Attributes.ARMOR, armour);
				attributeMap.add(Attributes.ARMOR_TOUGHNESS, toughness);

				return this;
			}

			private AttributeBuilder followRange(double distance) {
				attributeMap.add(Attributes.FOLLOW_RANGE, distance);

				return this;
			}

			private AttributeBuilder knockback(double knockbackStrength) {
				attributeMap.add(Attributes.ATTACK_KNOCKBACK, knockbackStrength);

				return this;
			}

			private AttributeBuilder knockbackResist(double resistance) {
				attributeMap.add(Attributes.KNOCKBACK_RESISTANCE, resistance);

				return this;
			}

			private AttributeBuilder extraAttributes(Attribute... attributes) {
				for (Attribute attribute : attributes) {
					attributeMap.add(attribute);
				}

				return this;
			}

			private void build(EntityAttributeCreationEvent ev) {
				ev.put(entityType, attributeMap.build());
			}
		}
	}
}
