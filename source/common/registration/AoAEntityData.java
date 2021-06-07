package net.tslat.aoa3.common.registration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public final class AoAEntityData {
	public static void init() {
		SpawnConditions.registerSpawnHeightLimitations();
		SpawnConditions.registerDaylightMobs();
	}

	public static class SpawnConditions {
		public static final HashMap<EntityType<?>, Integer> SPAWN_HEIGHTS = new HashMap<EntityType<?>, Integer>();
		public static final HashSet<EntityType<?>> DAYLIGHT_MOBS = new HashSet<EntityType<?>>();

		private static void registerSpawnHeightLimitations() {
			SPAWN_HEIGHTS.put(AoAEntities.Animals.CORATEE.get(), 125);
			SPAWN_HEIGHTS.put(AoAEntities.Animals.GLOWING_PIXON.get(), 125);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.ANGLER.get(), 125);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.ARCWORM.get(), 35);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.CAVE_CREEPOID.get(), 30);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.CREEPERLOCK.get(), 50);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.CREEPERLOCK.get(), 50);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.CRYPTID.get(), 27);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.ECHODAR.get(), 20);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.EVERBEAST.get(), 25);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.FISCHER.get(), 30);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.GHOST.get(), 50);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.KEELER.get(), 50);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.MAGICAL_CREEPER.get(), 50);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.MAGICAL_CREEPER.get(), 50);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.MOTHER_VOID_WALKER.get(), 35);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.MUNCHER.get(), 125);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.NEPTUNO.get(), 125);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.PARASECT.get(), 50);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.PINCHER.get(), 55);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.SEA_VIPER.get(), 125);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.SHADE.get(), 30);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.SHADOW.get(), 45);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.SQUIGGLER.get(), 27);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.TRICKSTER.get(), 20);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.VOID_CHARGER.get(), 20);
			SPAWN_HEIGHTS.put(AoAEntities.Mobs.VOID_WALKER.get(), 50);
		}

		private static void registerDaylightMobs() {
			DAYLIGHT_MOBS.addAll(Arrays.asList(
					AoAEntities.Mobs.AIRHEAD.get(),
					AoAEntities.Mobs.ANCIENT_GOLEM.get(),
					AoAEntities.Mobs.ANGLER.get(),
					AoAEntities.Mobs.ARCBEAST.get(),
					AoAEntities.Mobs.ARCWORM.get(),
					AoAEntities.Mobs.ARCHVINE.get(),
					AoAEntities.Mobs.ARC_FLOWER.get(),
					AoAEntities.Mobs.ARC_WIZARD.get(),
					AoAEntities.Mobs.ARIEL.get(),
					AoAEntities.Mobs.ARKBACK.get(),
					AoAEntities.Mobs.AXIOLIGHT.get(),
					AoAEntities.Mobs.BOBO.get(),
					AoAEntities.Mobs.BOMB_CARRIER.get(),
					AoAEntities.Mobs.BONEBACK.get(),
					AoAEntities.Mobs.BONE_CREEPER.get(),
					AoAEntities.Mobs.BOUNCER.get(),
					AoAEntities.Mobs.BROCCOHEAD.get(),
					AoAEntities.Mobs.BUSH_BABY.get(),
					AoAEntities.Mobs.CANDY_CORNY.get(),
					AoAEntities.Mobs.CARROTOP.get(),
					AoAEntities.Mobs.CAVE_CREEPOID.get(),
					AoAEntities.Mobs.CHARGER.get(),
					AoAEntities.Mobs.CHERRY_BLASTER.get(),
					AoAEntities.Mobs.CHIMERA.get(),
					AoAEntities.Mobs.CHOCKO.get(),
					AoAEntities.Mobs.CHOMPER.get(),
					AoAEntities.Mobs.CORNY.get(),
					AoAEntities.Mobs.CREEPERLOCK.get(),
					AoAEntities.Mobs.CREEPIRD.get(),
					AoAEntities.Mobs.CREEPUPLE.get(),
					AoAEntities.Mobs.CRYPTID.get(),
					AoAEntities.Mobs.CYCLOPS.get(),
					AoAEntities.Mobs.DEAD_TREE.get(),
					AoAEntities.Mobs.DEINOTHERIUM.get(),
					AoAEntities.Mobs.DESERT_CHARGER.get(),
					AoAEntities.Mobs.DYREHORN.get(),
					AoAEntities.Mobs.ECHODAR.get(),
					AoAEntities.Mobs.EMPEROR_BEAST.get(),
					AoAEntities.Mobs.FISHIX.get(),
					AoAEntities.Mobs.FLOWERFACE.get(),
					AoAEntities.Mobs.FLYE.get(),
					AoAEntities.Mobs.FURLION.get(),
					AoAEntities.Mobs.GIANT_SNAIL.get(),
					AoAEntities.Mobs.GOALBY.get(),
					AoAEntities.Mobs.GOBLIN.get(),
					AoAEntities.Mobs.GROBBLER.get(),
					AoAEntities.Mobs.HAG.get(),
					AoAEntities.Mobs.HIDING_FUNGI.get(),
					AoAEntities.Mobs.HILL_CHARGER.get(),
					AoAEntities.Mobs.HORNDRON.get(),
					AoAEntities.Mobs.HOST.get(),
					AoAEntities.Mobs.ICE_GIANT.get(),
					AoAEntities.Mobs.JUMBO.get(),
					AoAEntities.Mobs.KEELER.get(),
					AoAEntities.Mobs.KING_CHARGER.get(),
					AoAEntities.Mobs.KING_CREEPER.get(),
					AoAEntities.Mobs.KOKO.get(),
					AoAEntities.Mobs.KRANKY.get(),
					AoAEntities.Mobs.LEAFY_GIANT.get(),
					AoAEntities.Mobs.LELYETIAN_CASTER.get(),
					AoAEntities.Mobs.LELYETIAN_WARRIOR.get(),
					AoAEntities.Mobs.LIGHTWALKER.get(),
					AoAEntities.Mobs.LIVING_FUNGI.get(),
					AoAEntities.Mobs.LOLLYPOPPER.get(),
					AoAEntities.Mobs.LUXOCRON.get(),
					AoAEntities.Mobs.MAGICAL_CREEPER.get(),
					AoAEntities.Mobs.MECHACHRON.get(),
					AoAEntities.Mobs.MECHAMATON.get(),
					AoAEntities.Mobs.MUCKOPEDE.get(),
					AoAEntities.Mobs.MUNCHER.get(),
					AoAEntities.Mobs.NEPTUNO.get(),
					AoAEntities.Mobs.NOSPIKE.get(),
					AoAEntities.Mobs.OMNILIGHT.get(),
					AoAEntities.Mobs.PALADIN.get(),
					AoAEntities.Mobs.PARASECT.get(),
					AoAEntities.Mobs.POLYTOM.get(),
					AoAEntities.Mobs.RAMRADON.get(),
					AoAEntities.Mobs.RUNICORN.get(),
					AoAEntities.Mobs.RUNICORN_RIDER.get(),
					AoAEntities.Mobs.SABRETOOTH.get(),
					AoAEntities.Mobs.SAND_GIANT.get(),
					AoAEntities.Mobs.SAND_GOLEM.get(),
					AoAEntities.Mobs.SASQUATCH.get(),
					AoAEntities.Mobs.SEA_CHARGER.get(),
					AoAEntities.Mobs.SEA_TROLL.get(),
					AoAEntities.Mobs.SEA_VIPER.get(),
					AoAEntities.Mobs.SHYRE_KNIGHT.get(),
					AoAEntities.Mobs.SNAPPY.get(),
					AoAEntities.Mobs.SNOW_CHARGER.get(),
					AoAEntities.Mobs.SOULVYRE.get(),
					AoAEntities.Mobs.SOULSCORNE.get(),
					AoAEntities.Mobs.SPHINX.get(),
					AoAEntities.Mobs.SPIRIT_GUARDIAN.get(),
					AoAEntities.Mobs.SPIRIT_PROTECTOR.get(),
					AoAEntities.Mobs.SQUASHER.get(),
					AoAEntities.Mobs.SQUIGGLER.get(),
					AoAEntities.Mobs.STICKY.get(),
					AoAEntities.Mobs.STIMULO.get(),
					AoAEntities.Mobs.STIMULOSUS.get(),
					AoAEntities.Mobs.STITCHES.get(),
					AoAEntities.Mobs.STONE_GIANT.get(),
					AoAEntities.Mobs.SUNNY.get(),
					AoAEntities.Mobs.SWAMP_CHARGER.get(),
					AoAEntities.Mobs.SYSKER.get(),
					AoAEntities.Mobs.TERRADON.get(),
					AoAEntities.Mobs.THARAFLY.get(),
					AoAEntities.Mobs.TIPSY.get(),
					AoAEntities.Mobs.TORTIONE.get(),
					AoAEntities.Mobs.TRACKER.get(),
					AoAEntities.Mobs.TREE_SPIRIT.get(),
					AoAEntities.Mobs.VOLTRON.get(),
					AoAEntities.Mobs.WINGED_CREEPER.get(),
					AoAEntities.Mobs.WOOD_GIANT.get(),
					AoAEntities.Mobs.YETI.get()
			));
		}
	}

	@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class Stats {
		@SubscribeEvent
		public static void registerStats(EntityAttributeCreationEvent ev) {
			AttributeBuilder.create(AoAEntities.Animals.AMBIENT_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.ANGELICA.get()).health(15).flyingSpeed(0.1).knockbackResist(0.1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.BLOOMING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.CORATEE.get()).health(27).moveSpeed(0.2875).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.CREEP_COW.get()).health(20).moveSpeed(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.DAWNLIGHT.get()).health(23).moveSpeed(0.2875).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.EEO.get()).health(10).moveSpeed(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.ELKANYNE.get()).health(20).moveSpeed(0.2875).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.GLARING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.GLEAMING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.GLISTENING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.GLOWING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.HALYCON.get()).health(20).moveSpeed(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.MEGANEUROPSIS.get()).health(9).moveSpeed(0.5).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.NIGHT_WATCHER.get()).health(19).moveSpeed(0).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.PEPPERMINT_SNAIL.get()).health(25).moveSpeed(0.2875).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.RADIANT_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.RAINICORN.get()).health(25).moveSpeed(0.3).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.SHIK.get()).health(5).moveSpeed(0.2).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.SHINING_PIXON.get()).health(15).moveSpeed(0.4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.SPEARMINT_SNAIL.get()).health(25).moveSpeed(0.2875).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.TROTTER.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.URKA.get()).health(23).moveSpeed(0.2875).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Animals.VOLIANT.get()).health(40).flyingSpeed(0.1).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoAEntities.Minions.ALLURICORN.get()).health(200).moveSpeed(0.3).meleeStrength(13).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.BLISSARD.get()).health(100).moveSpeed(0.3).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.COMPEER.get()).health(20).moveSpeed(0.3).meleeStrength(5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.CONSTRUCT_OF_SERVILITY.get()).health(260).moveSpeed(0.41618).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.CORBY.get()).health(200).moveSpeed(0.3).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.CRAGGY.get()).health(180).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.DRAGGY.get()).health(100).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.ENDER_CARRIER.get()).health(100).moveSpeed(0.3346).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.FRIENDLY_CREEPER.get()).health(20).moveSpeed(0.25).meleeStrength(0).followRange(8).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.GNAWER.get()).health(430).moveSpeed(0.3).meleeStrength(9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.GOOFER.get()).health(270).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.HEALING_GOLEM.get()).health(200).moveSpeed(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.HELLQUIN.get()).health(120).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.HIVE_SOLDIER.get()).health(25).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.HORNTAIL.get()).health(270).moveSpeed(0.3).meleeStrength(18).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.MECHA_CYCLOPS.get()).health(100).moveSpeed(0.3).meleeStrength(30).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.MECHA_SKELLOX.get()).health(160).moveSpeed(0.72).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.ORBLING.get()).health(10).moveSpeed(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.PENGUIN.get()).health(100).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.PLATEOSAUR.get()).health(320).moveSpeed(0.3).meleeStrength(5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.RAMMERHORN.get()).health(210).moveSpeed(0.3).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.ROSID.get()).health(20).moveSpeed(0.3).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.SHADDY.get()).health(200).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.SHADOW_STALKER.get()).health(25).moveSpeed(0.3).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.SPIKEBACK.get()).health(60).moveSpeed(0.3).meleeStrength(23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.SPRAGGY.get()).health(130).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Minions.WAGGY.get()).health(110).moveSpeed(0.3).meleeStrength(10).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoAEntities.Misc.BANE_CLONE.get()).health(1).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Misc.BIG_BANE_CLONE.get()).health(10).moveSpeed(0.2875).meleeStrength(25).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Misc.BLOODLUST.get()).health(1).moveSpeed(0.2).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoAEntities.Mobs.AIRHEAD.get()).health(2).flyingSpeed(0.1).projectileDamage(14).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ALARMO.get()).health(74).moveSpeed(0.2875).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.AMPHIBIOR.get()).health(122).moveSpeed(0.2875).meleeStrength(13.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.AMPHIBIYTE.get()).health(109).moveSpeed(0.28).meleeStrength(13).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ANCIENT_GOLEM.get()).health(50).moveSpeed(0.23).meleeStrength(5).knockbackResist(0.6).armour(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ANEMIA.get()).health(92).flyingSpeed(0.1).projectileDamage(10).knockbackResist(0.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ANGLER.get()).health(112).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.APPARITION.get()).health(82).moveSpeed(0.25).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARCBEAST.get()).health(170).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARCHVINE.get()).health(105).moveSpeed(0.275).meleeStrength(13).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARCWORM.get()).health(163).moveSpeed(0.2875).meleeStrength(16).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARC_FLOWER.get()).health(1).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARC_WIZARD.get()).health(148).moveSpeed(0.207).projectileDamage(16.5).knockbackResist(0.1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARIEL.get()).health(115).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARKBACK.get()).health(200).moveSpeed(0.23).meleeStrength(14).knockbackResist(1).armour(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ARKZYNE.get()).health(135).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.AROCKNID.get()).health(75).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.8).armour(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.AXIOLIGHT.get()).health(167).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BANE.get()).health(1750).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BANSHEE.get()).health(108).moveSpeed(0.27).meleeStrength(13).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BARONESS.get()).health(2000).moveSpeed(0.207).projectileDamage(8).knockbackResist(1).followRange(50).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BASILISK.get()).health(119).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BAUMBA.get()).health(134).moveSpeed(0).projectileDamage(12).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BLOODSUCKER.get()).health(109).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BLUE_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BLUE_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BLUE_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BLUE_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BOBO.get()).health(95).moveSpeed(0.26).meleeStrength(9).knockbackResist(0.05).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BOMB_CARRIER.get()).health(20).moveSpeed(0.2875).meleeStrength(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BONEBACK.get()).health(40).moveSpeed(0.25).meleeStrength(4).knockbackResist(0.1).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BONE_CREEPER.get()).health(45).moveSpeed(0.3).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BOUNCER.get()).health(110).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BROCCOHEAD.get()).health(103).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.25).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BUGEYE.get()).health(15).moveSpeed(0.29).meleeStrength(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.BUSH_BABY.get()).health(10).moveSpeed(0.329).meleeStrength(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CANDY_CORNY.get()).health(83).moveSpeed(0.2875).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CANE_BUG.get()).health(94).moveSpeed(0.29).meleeStrength(8.5).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CARROTOP.get()).health(85).moveSpeed(0.2875).meleeStrength(9).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CASE_CONSTRUCT.get()).health(90).moveSpeed(0.25).meleeStrength(6).knockbackResist(0.2).armour(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CAVE_CREEP.get()).health(65).moveSpeed(0.295).meleeStrength(7.5).knockbackResist(0.6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CAVE_CREEPOID.get()).health(65).moveSpeed(0.27).meleeStrength(0).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CENTINEL.get()).health(90).moveSpeed(0.207).projectileDamage(12).knockbackResist(0.2).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CHERRY_BLASTER.get()).health(87).moveSpeed(0.207).projectileDamage(9).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CHIMERA.get()).health(25).moveSpeed(0.2875).meleeStrength(3.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CHOCKO.get()).health(80).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CHOMPER.get()).health(30).moveSpeed(0.23).meleeStrength(2).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CLUNKHEAD.get()).health(2200).moveSpeed(0.207).projectileDamage(13).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CONSTRUCT_OF_FLIGHT.get()).health(55).flyingSpeed(0.1).meleeStrength(7.5).armour(3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CONSTRUCT_OF_MIND.get()).health(74).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.4).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CONSTRUCT_OF_RANGE.get()).health(70).moveSpeed(0.207).projectileDamage(10.5).knockbackResist(0.3).armour(3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CONSTRUCT_OF_RESISTANCE.get()).health(80).moveSpeed(0.28).meleeStrength(7).knockbackResist(0.15).armour(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CONSTRUCT_OF_SPEED.get()).health(58).moveSpeed(0.31).meleeStrength(7.5).knockbackResist(0.1).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CONSTRUCT_OF_STRENGTH.get()).health(69).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.5).armour(3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CONSTRUCT_OF_TERROR.get()).health(53).flyingSpeed(0.1).projectileDamage(9.5).armour(3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CORALLUS.get()).health(1800).moveSpeed(0.3286).meleeStrength(25).knockbackResist(1).followRange(52).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CORNY.get()).health(95).moveSpeed(0.2875).meleeStrength(11).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.COTTON_CANDOR.get()).health(3000).flyingSpeed(0.1).projectileDamage(35).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CRAEXXEUS.get()).health(3000).flyingSpeed(0.1).projectileDamage(10).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CREEP.get()).health(3000).moveSpeed(0.23).projectileDamage(6).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CREEPERLOCK.get()).health(50).moveSpeed(0.207).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CREEPIRD.get()).health(40).flyingSpeed(0.1).meleeStrength(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CREEPUPLE.get()).health(60).moveSpeed(0.28).meleeStrength(0).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CRUSILISK.get()).health(125).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CRYPTID.get()).health(55).moveSpeed(0.2875).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CRYSTOCORE.get()).health(3000).flyingSpeed(0.1).meleeStrength(15).knockbackResist(0.6).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.CYCLOPS.get()).health(25).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DAYSEE.get()).health(86).moveSpeed(0.2875).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DEAD_TREE.get()).health(0.5).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DEINOTHERIUM.get()).health(120).moveSpeed(0.29).meleeStrength(10).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DESERT_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DESTRUCTOR.get()).health(999).moveSpeed(0).projectileDamage(15).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DEVOURER.get()).health(135).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DIOCUS.get()).health(64).moveSpeed(0.28).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DISTORTER.get()).health(95).moveSpeed(0).meleeStrength(0).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DOUBLER.get()).health(70).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DRACYON.get()).health(1700).flyingSpeed(0.1).meleeStrength(25).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DUSTEIVA.get()).health(111).moveSpeed(0.2875).meleeStrength(12).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DUSTON.get()).health(129).flyingSpeed(0.1).meleeStrength(11).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DUST_STRIDER.get()).health(110).moveSpeed(0.2875).meleeStrength(11.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DWELLER.get()).health(50).moveSpeed(0.3).meleeStrength(6.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.DYREHORN.get()).health(60).moveSpeed(0.329).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ECHODAR.get()).health(50).flyingSpeed(0.1).meleeStrength(6.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ELITE_SKELEMAN.get()).health(120).moveSpeed(0.207).projectileDamage(7).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get()).health(64).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ELITE_SKELE_PIG.get()).health(100).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ELUSIVE.get()).health(2000).moveSpeed(0.2875).meleeStrength(15).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ELUSIVE_CLONE.get()).health(30).moveSpeed(0.2875).meleeStrength(8).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.EMBRAKE.get()).health(70).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.EMPEROR_BEAST.get()).health(150).moveSpeed(0.329).meleeStrength(11).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ENFORCER.get()).health(89).moveSpeed(0.25).meleeStrength(10).knockbackResist(0.4).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.EVERBEAST.get()).health(40).moveSpeed(0.25).meleeStrength(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.EXOHEAD.get()).health(55).moveSpeed(0.2875).meleeStrength(9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.EXPLODOT.get()).health(30).flyingSpeed(0.1).meleeStrength(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FACELESS_FLOATER.get()).health(131).moveSpeed(0.27).meleeStrength(12.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FAKE_ZORP.get()).health(114).moveSpeed(0.2875).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FENIX.get()).health(1).moveSpeed(0.27).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FIEND.get()).health(90).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FISCHER.get()).health(79).moveSpeed(0.3).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FISHIX.get()).health(30).moveSpeed(0.2875).meleeStrength(4).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FLAMEWALKER.get()).health(65).moveSpeed(0.2875).meleeStrength(6.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FLASH.get()).health(1000).moveSpeed(0.329).meleeStrength(9).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FLESH_EATER.get()).health(95).moveSpeed(0.2875).meleeStrength(9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FLOWERFACE.get()).health(88).moveSpeed(0.2875).meleeStrength(9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FLYE.get()).health(50).flyingSpeed(0.1).meleeStrength(6).knockbackResist(0.2).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FUNGAT.get()).health(80).flyingSpeed(0.1).meleeStrength(8).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FUNGBACK.get()).health(90).moveSpeed(0.27).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FUNGIK.get()).health(110).moveSpeed(0.207).projectileDamage(10).knockbackResist(0.7).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FUNGOCK.get()).health(90).moveSpeed(0.2875).meleeStrength(9).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FUNGUNG.get()).health(100).moveSpeed(0.2875).meleeStrength(11).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.FURLION.get()).health(20).moveSpeed(0.329).meleeStrength(2.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GADGETOID.get()).health(110).moveSpeed(0.27).meleeStrength(11).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GHASTUS.get()).health(15).moveSpeed(0.2875).meleeStrength(10.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GHOST.get()).health(15).moveSpeed(0.2875).meleeStrength(3).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GIANT_SNAIL.get()).health(80).moveSpeed(0.25).meleeStrength(7.5).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GINGERBIRD.get()).health(79).flyingSpeed(0.1).meleeStrength(8).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GINGERBREAD_MAN.get()).health(95).moveSpeed(0.28).meleeStrength(11.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GOALBY.get()).health(30).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GOBLIN.get()).health(20).moveSpeed(0.207).projectileDamage(5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GOLDUM.get()).health(1).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GOLDUS.get()).health(1).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GRAW.get()).health(2500).flyingSpeed(0.1).projectileDamage(4).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GREEN_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GREEN_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GREEN_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GREEN_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GRILLFACE.get()).health(131).moveSpeed(0.2875).meleeStrength(16).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GROBBLER.get()).health(85).moveSpeed(0.3).meleeStrength(8).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.GYRO.get()).health(2500).flyingSpeed(0.1).projectileDamage(5).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HAG.get()).health(15).moveSpeed(0.23).projectileDamage(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HAPPY.get()).health(73).moveSpeed(0.207).projectileDamage(7.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HARKOS.get()).health(1300).moveSpeed(0.329).meleeStrength(13).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HIDING_FUNGI.get()).health(1).moveSpeed(0).meleeStrength(0).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HILL_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HIVE_KING.get()).health(2500).moveSpeed(0.2875).meleeStrength(20).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HIVE_WORKER.get()).health(30).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HORNDRON.get()).health(40).moveSpeed(0.25).meleeStrength(5).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HOST.get()).health(180).moveSpeed(0.23).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.HUNTER.get()).health(123).moveSpeed(0.3).meleeStrength(13.5).knockbackResist(0.25).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ICE_GIANT.get()).health(60).moveSpeed(0.3).meleeStrength(10).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.INFERNAL.get()).health(95).moveSpeed(0.25).meleeStrength(8.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.INMATE_X.get()).health(138).moveSpeed(0.2875).meleeStrength(13.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.INMATE_Y.get()).health(145).moveSpeed(0.27).meleeStrength(15).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.IOSAUR.get()).health(78).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.JAWE.get()).health(93).moveSpeed(0.28).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.JUMBO.get()).health(100).moveSpeed(0.28).meleeStrength(10).knockbackResist(0.35).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KAIYU.get()).health(70).moveSpeed(0.23).projectileDamage(9).knockbackResist(0.1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KAJAROS.get()).health(1750).moveSpeed(0.2875).meleeStrength(23).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KEELER.get()).health(70).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KING_BAMBAMBAM.get()).health(900).moveSpeed(0.207).projectileDamage(20).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KING_CHARGER.get()).health(75).moveSpeed(0.329).meleeStrength(9).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KING_CREEPER.get()).health(85).moveSpeed(0.23).meleeStrength(0).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KING_SHROOMUS.get()).health(1800).moveSpeed(0.207).projectileDamage(20).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KLOBBER.get()).health(1000).moveSpeed(0.2875).meleeStrength(20).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KOKO.get()).health(80).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KRANKY.get()).health(85).moveSpeed(0.29).meleeStrength(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.KROR.get()).health(2200).moveSpeed(0.2875).meleeStrength(60).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LEAFY_GIANT.get()).health(55).moveSpeed(0.32).meleeStrength(9).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LELYETIAN_CASTER.get()).health(60).moveSpeed(0.23).projectileDamage(7).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LELYETIAN_WARRIOR.get()).health(65).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LIGHTWALKER.get()).health(168).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LITTLE_BAM.get()).health(30).moveSpeed(0.2875).meleeStrength(4).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LIVING_FUNGI.get()).health(30).moveSpeed(0.2875).meleeStrength(2.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LOLLYPOPPER.get()).health(80).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.05).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LOST_SOUL.get()).health(125).moveSpeed(0.2875).meleeStrength(13).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LUNARCHER.get()).health(118).flyingSpeed(0.1).projectileDamage(14.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LURKER.get()).health(140).moveSpeed(0.2875).meleeStrength(16.5).knockbackResist(0.6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.LUXOCRON.get()).health(169).moveSpeed(0.2875).meleeStrength(14).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MAGICAL_CREEPER.get()).health(55).moveSpeed(0.25).meleeStrength(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MECHACHRON.get()).health(114).moveSpeed(0.295).meleeStrength(10.5).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MECHAMATON.get()).health(120).moveSpeed(0.295).meleeStrength(11).knockbackResist(0.8).armour(3.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MECHBOT.get()).health(2500).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MECHYON.get()).health(85).moveSpeed(0.295).meleeStrength(11).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MERKYRE.get()).health(119).moveSpeed(0.26).meleeStrength(13).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MERMAGE.get()).health(130).moveSpeed(0.207).projectileDamage(14).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MIRAGE.get()).health(750).moveSpeed(0.23).projectileDamage(8).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MISKEL.get()).health(1300).moveSpeed(0.207).projectileDamage(14).knockbackResist(0.4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MODULO.get()).health(111).flyingSpeed(0.1).projectileDamage(13).armour(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MOTHER_VOID_WALKER.get()).health(40).moveSpeed(0.2875).meleeStrength(6.5).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MUCKOPEDE.get()).health(30).moveSpeed(0.3).meleeStrength(4).knockbackResist(0.699999988079071).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MUNCHER.get()).health(135).moveSpeed(0).meleeStrength(13.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.MUSHROOM_SPIDER.get()).health(61).moveSpeed(0.28).meleeStrength(7.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NEPTUNO.get()).health(132).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NETHENGEIC_BEAST.get()).health(65).moveSpeed(0.29).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NETHENGEIC_WITHER.get()).health(1100).moveSpeed(0.32).meleeStrength(2).knockbackResist(0).armour(0).followRange(52).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NIGHTFLY.get()).health(15).flyingSpeed(0.3544).meleeStrength(3.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NIGHTMARE_SPIDER.get()).health(63).moveSpeed(0.28).meleeStrength(7.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NIGHTWING.get()).health(78).flyingSpeed(0.1).meleeStrength(10.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NIGHT_REAPER.get()).health(35).moveSpeed(0.23).meleeStrength(5).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NIPPER.get()).health(45).moveSpeed(0.29).meleeStrength(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.NOSPIKE.get()).health(65).moveSpeed(0.329).meleeStrength(8.5).knockbackResist(0.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.OCCULENT.get()).health(98).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.OKAZOR.get()).health(1200).moveSpeed(0.2875).meleeStrength(50).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.OMNILIGHT.get()).health(156).flyingSpeed(0.1).projectileDamage(16).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.OPTERYX.get()).health(75).moveSpeed(0.329).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ORANGE_FLOWER.get()).health(30).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.PALADIN.get()).health(109).moveSpeed(0.207).meleeStrength(16).knockbackResist(0.2).armour(18).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.PARASECT.get()).health(80).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.PARAVITE.get()).health(68).moveSpeed(0.2875).meleeStrength(9.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.PINCHER.get()).health(25).moveSpeed(0.29).meleeStrength(4).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.POD_PLANT.get()).health(64).moveSpeed(0.27).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.POLYTOM.get()).health(80).flyingSpeed(0.1).projectileDamage(10).armour(5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.PRIMITIVE_CARROTOP.get()).health(70).moveSpeed(0.3).meleeStrength(9.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.PROSHIELD.get()).health(500).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.PURPLE_FLOWER.get()).health(120).moveSpeed(0.2875).meleeStrength(2).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RAMRADON.get()).health(68).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RAWBONE.get()).health(64).moveSpeed(0.2875).meleeStrength(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RAXXAN.get()).health(1000).moveSpeed(0.329).meleeStrength(15).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.REAVER.get()).health(40).moveSpeed(0.2875).meleeStrength(12).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RED_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RED_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RED_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.REFLUCT.get()).health(122).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ROCKBITER.get()).health(60).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.2).armour(1.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ROCK_CRAWLER.get()).health(70).moveSpeed(0.29).meleeStrength(7).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ROCK_CRITTER.get()).health(75).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ROCK_RIDER.get()).health(1500).moveSpeed(0.329).meleeStrength(30).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RUNICORN.get()).health(132).moveSpeed(0.3).meleeStrength(14).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RUNICORN_RIDER.get()).health(132).moveSpeed(0.29).meleeStrength(14).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RUNIC_GOLEM.get()).health(95).moveSpeed(0.265).meleeStrength(9).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.RUNIC_GUARDIAN.get()).health(109).moveSpeed(0.207).projectileDamage(13.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SABRETOOTH.get()).health(90).moveSpeed(0.29).meleeStrength(10.5).knockbackResist(0.65).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SAND_GIANT.get()).health(80).moveSpeed(0.2875).meleeStrength(10.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SAND_GOLEM.get()).health(45).moveSpeed(0.23).meleeStrength(4.5).knockbackResist(0.8).armour(8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SASQUATCH.get()).health(25).moveSpeed(0.2875).meleeStrength(4).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SCRUBBY.get()).health(35).moveSpeed(0.4).meleeStrength(4.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SEA_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SEA_TROLL.get()).health(20).moveSpeed(0.207).projectileDamage(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SEA_VIPER.get()).health(116).moveSpeed(0.2875).meleeStrength(14.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SHADE.get()).health(15).moveSpeed(0.23).meleeStrength(4).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SHADOW.get()).health(0.5).moveSpeed(0.1095).meleeStrength(2.5).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SHADOWLORD.get()).health(2000).moveSpeed(0.32).meleeStrength(2).knockbackResist(0).armour(0).followRange(52).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SHAVO.get()).health(125).moveSpeed(0.25).meleeStrength(8.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SHIFTER.get()).health(130).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SHYRE_KNIGHT.get()).health(140).moveSpeed(0.2875).meleeStrength(18).knockbackResist(0.35).armour(19).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SILENCER.get()).health(124).moveSpeed(0.2875).meleeStrength(12).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELEDON.get()).health(120).moveSpeed(0.2875).meleeStrength(11.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELEKYTE.get()).health(115).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELEMAN.get()).health(30).moveSpeed(0.207).projectileDamage(2).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELETAL_COWMAN.get()).health(58).moveSpeed(0.207).projectileDamage(5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELETRON.get()).health(1100).moveSpeed(0.2875).meleeStrength(70).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELE_ELDER.get()).health(60).moveSpeed(0.25).meleeStrength(3).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELE_HOPPER.get()).health(16).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKELE_PIG.get()).health(20).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SKULL_CREATURE.get()).health(118).moveSpeed(0.2875).meleeStrength(12).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SLIMER.get()).health(120).moveSpeed(0.28).meleeStrength(11).knockbackResist(0.7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SMASH.get()).health(500).moveSpeed(0.329).meleeStrength(15).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SNAPPY.get()).health(85).moveSpeed(0.2875).meleeStrength(9.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SNOW_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SOULSCORNE.get()).health(140).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SOULVYRE.get()).health(178).moveSpeed(0.2875).meleeStrength(18.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SPECTRAL_WIZARD.get()).health(120).moveSpeed(0.207).projectileDamage(13).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SPHINX.get()).health(20).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SPINOLEDON.get()).health(72).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SPIRIT_GUARDIAN.get()).health(60).moveSpeed(0.2875).meleeStrength(11.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SPIRIT_PROTECTOR.get()).health(60).moveSpeed(0.207).projectileDamage(11.5).knockbackResist(0.15).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SQUASHER.get()).health(105).moveSpeed(0.2875).meleeStrength(9.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SQUIGGLER.get()).health(76).moveSpeed(0.27).meleeStrength(6).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STALKER.get()).health(138).moveSpeed(0.3).meleeStrength(13.5).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STICKY.get()).health(85).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STIMULO.get()).health(164).moveSpeed(0.27).meleeStrength(15.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STIMULOSUS.get()).health(180).moveSpeed(0.2875).meleeStrength(17).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STITCHES.get()).health(85).moveSpeed(0.2875).meleeStrength(10.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STONE_GIANT.get()).health(95).moveSpeed(0.28).meleeStrength(11.5).knockbackResist(1).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STRONG_SKELEMAN.get()).health(60).moveSpeed(0.207).projectileDamage(4).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get()).health(32).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.STRONG_SKELE_PIG.get()).health(40).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SUGARFACE.get()).health(124).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SUNNY.get()).health(102).moveSpeed(0.29).meleeStrength(10.5).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SWAMP_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.SYSKER.get()).health(163).moveSpeed(0.2875).meleeStrength(16).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TERRADON.get()).health(105).moveSpeed(0.329).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TERRESTRIAL.get()).health(129).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.THARAFLY.get()).health(55).flyingSpeed(0.1).meleeStrength(6.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TIPSY.get()).health(85).moveSpeed(0.207).meleeStrength(6).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TORTIONE.get()).health(100).moveSpeed(0.25).meleeStrength(7.5).knockbackResist(0.9).armour(7).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TOXXULOUS.get()).health(95).moveSpeed(0.295).meleeStrength(12.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TRACKER.get()).health(60).moveSpeed(0.3).meleeStrength(6.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TREE_SPIRIT.get()).health(35).moveSpeed(0.329).meleeStrength(6).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TRICKSTER.get()).health(35).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TRICKSTER_CLONE.get()).health(35).moveSpeed(0.2875).meleeStrength(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.TYROSAUR.get()).health(4000).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.9).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.UNDEAD_TROLL.get()).health(67).moveSpeed(0.207).projectileDamage(8).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.URIOH.get()).health(35).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.URV.get()).health(75).moveSpeed(0.25).meleeStrength(14).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VALKYRIE.get()).health(115).flyingSpeed(0.1).projectileDamage(13.5).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VINE_WIZARD.get()).health(90).moveSpeed(0.207).projectileDamage(12).knockbackResist(0.15).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VINOCORNE.get()).health(2500).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VISAGE.get()).health(60).moveSpeed(0.2875).meleeStrength(11).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VISUALENT.get()).health(2000).flyingSpeed(0.1).meleeStrength(20).knockbackResist(1).followRange(36).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VISULAR.get()).health(110).flyingSpeed(0.1).meleeStrength(12).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VISULON.get()).health(150).flyingSpeed(0.1).meleeStrength(15).knockbackResist(0.3).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VOID_CHARGER.get()).health(20).moveSpeed(0.329).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VOID_WALKER.get()).health(30).moveSpeed(0.2875).meleeStrength(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VOLTRON.get()).health(94).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.2).armour(1.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.VOXXULON.get()).health(2000).moveSpeed(0).meleeStrength(30).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.WEB_REAPER.get()).health(107).moveSpeed(0.207).projectileDamage(13).knockbackResist(0.6).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.WINGED_CREEPER.get()).health(55).moveSpeed(0.29).meleeStrength(0).knockbackResist(0.15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.WOOD_GIANT.get()).health(90).moveSpeed(0.2875).meleeStrength(11).knockbackResist(1).armour(2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.XXEUS.get()).health(3000).moveSpeed(0.329).meleeStrength(25).knockbackResist(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.YELLOW_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.YELLOW_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(15).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.YELLOW_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.YELLOW_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.YETI.get()).health(30).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ZARG.get()).health(120).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ZHINX.get()).health(60).moveSpeed(0.28).meleeStrength(6.5).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.Mobs.ZORP.get()).health(114).moveSpeed(0.2875).meleeStrength(15).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoAEntities.NPCs.ABYSSAL_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ANIMA_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ASSASSIN.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.AUGURY_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.BARON_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.BOREIC_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.BUTCHERY_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CANDIED_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CELEVIAN_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CORRUPTED_TRAVELLER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CREATION_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CREEPONIA_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CREEP_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CRYSTAL_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.CRYSTAL_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.DUNGEON_KEEPER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.DUSTOPIAN_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.EXPEDITION_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.EXPLOSIVES_EXPERT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.EXTRACTION_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.FLORO_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.FORAGING_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.GOLDEN_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.GORB_ARMS_DEALER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.GORB_CITIZEN.get()).health(20).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.GORB_ENGINEER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.HAULING_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.HAUNTED_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.HUNTER_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.INFUSION_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.INNERVATION_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.LELYETIAN_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.LELYETIAN_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.LELYETIAN_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.LOGGING_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.LUNAR_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.METALLOID.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.MYSTIC_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.NATURALIST.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.PRECASIAN_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.PRIMORDIAL_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.PRIMORDIAL_GUIDE.get()).health(30).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.PRIMORDIAL_MERCHANT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.PRIMORDIAL_SPELLBINDER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.PRIMORDIAL_WIZARD.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.PROFESSOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.REALMSHIFTER.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ROCKY_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.RUNATION_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.RUNIC_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.SHYRELANDS_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.SHYRE_ARCHER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.SHYRE_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.STORE_KEEPER.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.TOKEN_COLLECTOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.TOXIC_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.TOY_MERCHANT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.TROLL_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.TWINKLING_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.UNDEAD_HERALD.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.WITHERING_LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ZAL_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ZAL_CHILD.get()).health(15).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ZAL_CITIZEN.get()).health(20).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ZAL_GROCER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ZAL_HERBALIST.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ZAL_SPELLBINDER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
			AttributeBuilder.create(AoAEntities.NPCs.ZAL_VENDOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);

			AttributeBuilder.create(AoAEntities.Projectiles.CORALLUS_SHOT.get()).health(20).moveSpeed(0.7).knockbackResist(0).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, ForgeMod.SWIM_SPEED.get(), ForgeMod.NAMETAG_DISTANCE.get(), ForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
		}

		private static class AttributeBuilder {
			private final EntityType<? extends LivingEntity> entityType;
			private final AttributeModifierMap.MutableAttribute attributeMap;

			private AttributeBuilder(EntityType<? extends LivingEntity> entityType, AttributeModifierMap.MutableAttribute attributeMap) {
				this.entityType = entityType;
				this.attributeMap = attributeMap;
			}

			private static AttributeBuilder create(EntityType<? extends LivingEntity> entityType) {
				return new AttributeBuilder(entityType, LivingEntity.createLivingAttributes());
			}

			private static AttributeBuilder createMob(EntityType<? extends LivingEntity> entityType) {
				return new AttributeBuilder(entityType, MobEntity.createMobAttributes());
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
