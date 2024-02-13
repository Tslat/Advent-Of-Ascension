package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.base.AoAMonster;

public final class AoAEntityStats {
	public static void init() {
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, EntityAttributeCreationEvent.class, AoAEntityStats::registerStats);
	}

	private static void doOverworldEntityStats(final EntityAttributeCreationEvent ev) {
		AttributeBuilder.createMonster(AoAMobs.ANCIENT_GOLEM.get()).health(50).moveSpeed(0.15).meleeStrength(15).knockbackResist(1).knockback(5).armour(20, 40).followRange(40).aggroRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BOMB_CARRIER.get()).health(23).moveSpeed(0.3).projectileDamage(2).followRange(12).aggroRange(8).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BUSH_BABY.get()).health(10).moveSpeed(0.34).meleeStrength(4).followRange(10).aggroRange(8).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CHARGER.get()).health(16).moveSpeed(0.31).meleeStrength(6).followRange(30).aggroRange(12).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CHOMPER.get()).health(30).moveSpeed(0.3).meleeStrength(7).knockbackResist(0.3).followRange(14).aggroRange(8).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CYCLOPS.get()).health(25).moveSpeed(0.2875).meleeStrength(4).followRange(14).aggroRange(14).build(ev);
		AttributeBuilder.createMonster(AoAMobs.GHOST.get()).health(15).moveSpeed(0.2875).meleeStrength(4).knockbackResist(1).followRange(10).aggroRange(8).build(ev);
		AttributeBuilder.createMonster(AoAMobs.GOBLIN.get()).health(20).moveSpeed(0.29).meleeStrength(4).followRange(12).aggroRange(8).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SASQUATCH.get()).health(25).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.1).followRange(12).aggroRange(12).build(ev);
		AttributeBuilder.createMonster(AoAMobs.TREE_SPIRIT.get()).health(35).moveSpeed(0).projectileDamage(6).knockbackResist(1).aggroRange(16).followRange(32).build(ev);
		AttributeBuilder.createMonster(AoAMobs.VOID_WALKER.get()).health(30).moveSpeed(0.3).meleeStrength(4).followRange(14).aggroRange(10).build(ev);
		AttributeBuilder.createMonster(AoAMobs.YETI.get()).health(25).armour(2, 2).moveSpeed(0.2875).meleeStrength(5).followRange(12).aggroRange(12).build(ev);

		AttributeBuilder.createMonster(AoAMobs.KING_CHARGER.get()).health(75).moveSpeed(0.32).meleeStrength(9).knockbackResist(0.2).followRange(40).aggroRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ICE_GIANT.get()).health(150).moveSpeed(0.31).meleeStrength(10).projectileDamage(1).knockbackResist(1).followRange(40).stepHeight(1.5f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LEAFY_GIANT.get()).health(135).moveSpeed(0.32).meleeStrength(9).knockbackResist(1).followRange(40).stepHeight(1.5f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SAND_GIANT.get()).health(145).moveSpeed(0.31).meleeStrength(10.5).knockbackResist(1).followRange(40).stepHeight(1.5f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.STONE_GIANT.get()).health(150).moveSpeed(0.31).meleeStrength(11.5).projectileDamage(10f).knockbackResist(1).armour(13, 12).followRange(40).stepHeight(1.5f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.WOOD_GIANT.get()).health(140).moveSpeed(0.32).meleeStrength(11).knockbackResist(1).armour(10, 5).followRange(40).stepHeight(1.5f).build(ev);
	}

	private static void doNetherEntityStats(final EntityAttributeCreationEvent ev) {
		AttributeBuilder.createMonster(AoAMobs.EMBRAKE.get()).health(40).moveSpeed(0.25).meleeStrength(7).projectileDamage(2f).knockbackResist(0.6).followRange(16).aggroRange(10).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FLAMEWALKER.get()).health(45).moveSpeed(0.3).projectileDamage(7).followRange(10).aggroRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LITTLE_BAM.get()).health(10).moveSpeed(0.32).followRange(14).aggroRange(8).build(ev);
		AttributeBuilder.createMonster(AoAMobs.NETHENGEIC_BEAST.get()).health(120).moveSpeed(0.25).projectileDamage(10).aggroRange(16).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.INFERNAL.get()).health(100).armour(15, 10).moveSpeed(0.23).meleeStrength(14).aggroRange(16).knockbackResist(1f).followRange(24).build(ev);
	}

	private static void doPrecasiaEntityStats(final EntityAttributeCreationEvent ev) {
		AttributeBuilder.createMonster(AoAMobs.SPINOLEDON.get()).health(51).armour(4, 4).moveSpeed(0.3).meleeStrength(9.5f).knockbackResist(0.7f).aggroRange(16).followRange(32).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.HORNDRON.get()).health(58).moveSpeed(0.25f).followRange(16).knockbackResist(0.75f).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.DEINOTHERIUM.get()).health(95).moveSpeed(0.2f).followRange(16).meleeStrength(8).knockback(1).knockbackResist(0.9f).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MEGANEUROPSIS.get()).health(19).meleeStrength(5).moveSpeed(0.33).flyingSpeed(0.33f).aggroRange(8).armour(1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.OPTERYX.get()).health(34).moveSpeed(0.2875f).flyingSpeed(0.3f).followRange(32).knockbackResist(0.4f).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value()).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DUNKLEOSTEUS.get()).health(70).meleeStrength(7).moveSpeed(1.2f).swimSpeedMod(1.5f).followRange(32).knockbackResist(0.9f).aggroRange(32).followRange(64).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value()).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ATTERCOPUS.get()).health(29).moveSpeed(0.3).meleeStrength(5.5f).aggroRange(16).followRange(32).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SMILODON.get()).health(46).meleeStrength(8).moveSpeed(0.25f).aggroRange(16).followRange(32).knockbackResist(0.3f).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value()).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SKELETAL_ABOMINATION.get()).health(64).meleeStrength(9.5f).moveSpeed(0.35f).aggroRange(32).followRange(48).knockbackResist(0.15f).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value()).build(ev);
		AttributeBuilder.createMonster(AoAMobs.VELORAPTOR.get()).health(37).moveSpeed(0.35).meleeStrength(7.5f).knockbackResist(0.2f).aggroRange(16).followRange(32).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
	}

	private static void doBossEntityStats(final EntityAttributeCreationEvent ev) {
		AttributeBuilder.createMonster(AoAMobs.SMASH.get()).health(275).moveSpeed(0.31).meleeStrength(15).knockbackResist(0.9).followRange(100).aggroRange(64).armour(10, 10).knockback(1f).stepHeight(1.25f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ELITE_SMASH.get()).health(600).moveSpeed(0.315).meleeStrength(30).knockbackResist(1).followRange(100).aggroRange(64).armour(15, 25).knockback(1f).stepHeight(1.25f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.NETHENGEIC_WITHER.get()).health(420).moveSpeed(0.31).flyingSpeed(0.6).projectileDamage(6).knockbackResist(1).followRange(100).aggroRange(64).knockback(1f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ELITE_NETHENGEIC_WITHER.get()).health(950).moveSpeed(0.33).flyingSpeed(1.5f).projectileDamage(30).knockbackResist(1).followRange(100).aggroRange(64).knockback(1f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.KING_BAMBAMBAM.get()).health(740).moveSpeed(0.2875f).projectileDamage(12).knockbackResist(1).followRange(100).aggroRange(64).armour(10, 30).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ELITE_KING_BAMBAMBAM.get()).health(1510).moveSpeed(0.2875f).projectileDamage(25).knockbackResist(1).followRange(100).aggroRange(64).armour(15, 30).build(ev);
		AttributeBuilder.createMonster(AoAMobs.TYROSAUR.get()).health(275).moveSpeed(0.31).meleeStrength(15).knockbackResist(0.9).followRange(100).aggroRange(64).armour(10, 10).knockback(1f).stepHeight(1.25f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ELITE_TYROSAUR.get()).health(600).moveSpeed(0.315).meleeStrength(30).knockbackResist(1).followRange(100).aggroRange(64).armour(15, 25).knockback(1f).stepHeight(1.25f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SKELETRON.get()).health(275).moveSpeed(0.31).meleeStrength(15).knockbackResist(0.9).followRange(100).aggroRange(64).armour(10, 10).knockback(1f).stepHeight(1.25f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ELITE_SKELETRON.get()).health(600).moveSpeed(0.315).meleeStrength(30).knockbackResist(1).followRange(100).aggroRange(64).armour(15, 25).knockback(1f).stepHeight(1.25f).build(ev);
	}

	private static void registerStats(final EntityAttributeCreationEvent ev) {
		doOverworldEntityStats(ev);
		doNetherEntityStats(ev);
		doPrecasiaEntityStats(ev);
		doBossEntityStats(ev);

		AttributeBuilder.create(AoAAnimals.ANGELICA.get()).health(15).flyingSpeed(0.1).knockbackResist(0.1).followRange(24).extraAttributes(Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.CORATEE.get()).health(27).moveSpeed(0.2875).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.CREEP_COW.get()).health(20).moveSpeed(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.DAWNLIGHT.get()).health(23).moveSpeed(0.2875).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.EEO.get()).health(10).moveSpeed(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.HALYCON.get()).health(20).moveSpeed(0.2).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.NIGHT_WATCHER.get()).health(19).moveSpeed(0).knockbackResist(0.3).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.PEPPERMINT_SNAIL.get()).health(25).moveSpeed(0.2875).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.RAINICORN.get()).health(25).moveSpeed(0.3).knockbackResist(0.1).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.SHIK.get()).health(5).moveSpeed(0.2).armour(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.SPEARMINT_SNAIL.get()).health(25).moveSpeed(0.2875).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.TROTTER.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.URKA.get()).health(23).moveSpeed(0.2875).knockbackResist(0.4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.VOLIANT.get()).health(40).flyingSpeed(0.1).knockbackResist(1).followRange(24).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.SHINY_SQUID.get()).health(15).swimSpeedMod(1.1f).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.CANDLEFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.CHARRED_CHAR.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.CHOCAW.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.CRIMSON_SKIPPER.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.CRIMSON_STRIPEFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.DARK_HATCHETFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.BLUE_GEMTRAP.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.GREEN_GEMTRAP.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.PURPLE_GEMTRAP.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.RED_GEMTRAP.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.WHITE_GEMTRAP.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.YELLOW_GEMTRAP.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.HYDRONE.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.IRONBACK.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.JAMFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.PARAPIRANHA.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.PEARL_STRIPEFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.RAINBOWFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.RAZORFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.REEFTOOTH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.ROCKETFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.SAILBACK.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.SAPPHIRE_STRIDER.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.SKELECANTH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.TURQUOISE_STRIPEFISH.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoAAnimals.VIOLET_SKIPPER.get()).health(4).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);

		AttributeBuilder.createMonster(AoAMiscEntities.THORNY_PLANT_SPROUT.get()).health(50).moveSpeed(0).meleeStrength(8).knockbackResist(1).followRange(8).build(ev);












		//AttributeBuilder.create(AoAMiscEntities.BANE_CLONE.get()).health(1).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.get(), NeoForgeMod.NAMETAG_DISTANCE.get(), NeoForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
		//AttributeBuilder.create(AoAMiscEntities.BIG_BANE_CLONE.get()).health(10).moveSpeed(0.2875).meleeStrength(25).knockbackResist(0.8).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.get(), NeoForgeMod.NAMETAG_DISTANCE.get(), NeoForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARCBEAST.get()).health(170).moveSpeed(0.2875).meleeStrength(15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.AIRHEAD.get()).health(2).flyingSpeed(0.1).projectileDamage(14).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ALARMO.get()).health(74).moveSpeed(0.2875).meleeStrength(0).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.AMPHIBIOR.get()).health(122).moveSpeed(0.2875).meleeStrength(13.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.AMPHIBIYTE.get()).health(109).moveSpeed(0.28).meleeStrength(13).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ANGLER.get()).health(112).moveSpeed(0.2875).meleeStrength(14).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ANEMIA.get()).health(92).flyingSpeed(0.1).projectileDamage(10).knockbackResist(0.5).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.APPARITION.get()).health(82).moveSpeed(0.25).meleeStrength(8.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARCHVINE.get()).health(105).moveSpeed(0.275).meleeStrength(13).knockbackResist(0.3).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARCWORM.get()).health(163).moveSpeed(0.2875).meleeStrength(16).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARC_FLOWER.get()).health(1).moveSpeed(0.2875).meleeStrength(14).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARC_WIZARD.get()).health(148).moveSpeed(0.207).projectileDamage(16.5).knockbackResist(0.1).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARIEL.get()).health(115).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARKBACK.get()).health(200).moveSpeed(0.23).meleeStrength(14).knockbackResist(1).armour(6).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ARKZYNE.get()).health(135).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.4).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.AROCKNID.get()).health(75).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.8).armour(2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.AXIOLIGHT.get()).health(167).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.BANE.get()).health(1750).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BANSHEE.get()).health(108).moveSpeed(0.27).meleeStrength(13).knockbackResist(0.2).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.BARONESS.get()).health(2000).moveSpeed(0.207).projectileDamage(8).knockbackResist(1).followRange(50).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BASILISK.get()).health(119).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BAUMBA.get()).health(134).moveSpeed(0).projectileDamage(12).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BLOODSUCKER.get()).health(109).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.3).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.BLUE_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.BLUE_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.BLUE_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.BLUE_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BOBO.get()).health(95).moveSpeed(0.26).meleeStrength(9).knockbackResist(0.05).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BONE_CREEPER.get()).health(45).moveSpeed(0.3).meleeStrength(0).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BOUNCER.get()).health(110).moveSpeed(0.2875).meleeStrength(11).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.BROCCOHEAD.get()).health(103).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.25).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CANDY_CORNY.get()).health(83).moveSpeed(0.2875).meleeStrength(8.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CANE_BUG.get()).health(94).moveSpeed(0.29).meleeStrength(8.5).knockbackResist(0.2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CARROTOP.get()).health(85).moveSpeed(0.2875).meleeStrength(9).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CASE_CONSTRUCT.get()).health(90).moveSpeed(0.25).meleeStrength(6).knockbackResist(0.2).armour(2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CAVE_CREEP.get()).health(65).moveSpeed(0.295).meleeStrength(7.5).knockbackResist(0.6).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CAVE_CREEPOID.get()).health(65).moveSpeed(0.27).meleeStrength(0).knockbackResist(0.4).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CENTINEL.get()).health(90).moveSpeed(0.207).projectileDamage(12).knockbackResist(0.2).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CHERRY_BLASTER.get()).health(87).moveSpeed(0.207).projectileDamage(9).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CHOCKO.get()).health(80).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.CLUNKHEAD.get()).health(2200).moveSpeed(0.207).projectileDamage(13).knockbackResist(1).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CONSTRUCT_OF_FLIGHT.get()).health(55).flyingSpeed(0.1).meleeStrength(7.5).armour(3).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CONSTRUCT_OF_MIND.get()).health(74).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.4).armour(3).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CONSTRUCT_OF_RANGE.get()).health(70).moveSpeed(0.207).projectileDamage(10.5).knockbackResist(0.3).armour(3).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CONSTRUCT_OF_RESISTANCE.get()).health(80).moveSpeed(0.28).meleeStrength(7).knockbackResist(0.15).armour(15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CONSTRUCT_OF_SPEED.get()).health(58).moveSpeed(0.31).meleeStrength(7.5).knockbackResist(0.1).armour(3).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CONSTRUCT_OF_STRENGTH.get()).health(69).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.5).armour(3).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CONSTRUCT_OF_TERROR.get()).health(53).flyingSpeed(0.1).projectileDamage(9.5).armour(3).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.CORALLUS.get()).health(1800).moveSpeed(0.3286).meleeStrength(25).knockbackResist(1).followRange(52).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CORNY.get()).health(95).moveSpeed(0.2875).meleeStrength(11).knockbackResist(0.15).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.COTTON_CANDOR.get()).health(3000).flyingSpeed(0.1).projectileDamage(35).knockbackResist(1).followRange(36).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.CRAEXXEUS.get()).health(3000).flyingSpeed(0.1).projectileDamage(10).knockbackResist(1).followRange(36).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.CREEP.get()).health(3000).moveSpeed(0.23).projectileDamage(6).knockbackResist(1).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CREEPERLOCK.get()).health(50).moveSpeed(0.207).meleeStrength(0).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CREEPIRD.get()).health(40).flyingSpeed(0.1).meleeStrength(4).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CREEPUPLE.get()).health(60).moveSpeed(0.28).meleeStrength(0).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CRUSILISK.get()).health(125).moveSpeed(0.2875).meleeStrength(14).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.CRYPTID.get()).health(55).moveSpeed(0.2875).meleeStrength(7).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.CRYSTOCORE.get()).health(3000).flyingSpeed(0.1).meleeStrength(15).knockbackResist(0.6).followRange(36).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DAYSEE.get()).health(86).moveSpeed(0.2875).meleeStrength(7).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DESTRUCTOR.get()).health(999).moveSpeed(0).projectileDamage(15).knockbackResist(1).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DEVOURER.get()).health(135).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DISTORTER.get()).health(95).moveSpeed(0).meleeStrength(0).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.DOPPELGANGER.get()).health(100).moveSpeed(0.2875).meleeStrength(5).followRange(32).knockbackResist(0.9f).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DOUBLER.get()).health(70).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.DRACYON.get()).health(1700).flyingSpeed(0.1).meleeStrength(25).knockbackResist(1).followRange(36).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DUSTEIVA.get()).health(111).moveSpeed(0.2875).meleeStrength(12).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DUSTON.get()).health(129).flyingSpeed(0.1).meleeStrength(11).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DUST_STRIDER.get()).health(110).moveSpeed(0.2875).meleeStrength(11.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.DWELLER.get()).health(50).moveSpeed(0.3).meleeStrength(6.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ECHODAR.get()).health(50).flyingSpeed(0.1).meleeStrength(6.5).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.ELUSIVE.get()).health(2000).moveSpeed(0.2875).meleeStrength(15).knockbackResist(1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.ELUSIVE_CLONE.get()).health(30).moveSpeed(0.2875).meleeStrength(8).knockbackResist(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.EMPEROR_BEAST.get()).health(150).moveSpeed(0.329).meleeStrength(11).knockbackResist(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ENFORCER.get()).health(89).moveSpeed(0.25).meleeStrength(10).knockbackResist(0.4).armour(4).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.EXOHEAD.get()).health(55).moveSpeed(0.2875).meleeStrength(9).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.EXPLODOT.get()).health(30).flyingSpeed(0.1).meleeStrength(1).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FACELESS_FLOATER.get()).health(131).moveSpeed(0.27).meleeStrength(12.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FAKE_ZORP.get()).health(114).moveSpeed(0.2875).meleeStrength(0).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FIEND.get()).health(90).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FISCHER.get()).health(79).moveSpeed(0.3).meleeStrength(6).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.FLASH.get()).health(1000).moveSpeed(0.329).meleeStrength(9).knockbackResist(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FLESH_EATER.get()).health(95).moveSpeed(0.2875).meleeStrength(9).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FLOWERFACE.get()).health(88).moveSpeed(0.2875).meleeStrength(9).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FLYE.get()).health(50).flyingSpeed(0.1).meleeStrength(6).knockbackResist(0.2).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FUNGAT.get()).health(80).flyingSpeed(0.1).meleeStrength(8).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FUNGBACK.get()).health(90).moveSpeed(0.27).meleeStrength(8.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FUNGIK.get()).health(110).moveSpeed(0.207).projectileDamage(10).knockbackResist(0.7).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FUNGOCK.get()).health(90).moveSpeed(0.2875).meleeStrength(9).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.FUNGUNG.get()).health(100).moveSpeed(0.2875).meleeStrength(11).knockbackResist(0.7).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.GADGETOID.get()).health(110).moveSpeed(0.27).meleeStrength(11).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.GINGERBIRD.get()).health(79).flyingSpeed(0.1).meleeStrength(8).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.GINGERBREAD_MAN.get()).health(95).moveSpeed(0.28).meleeStrength(11.5).knockbackResist(0.15).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.GRAW.get()).health(2500).flyingSpeed(0.1).projectileDamage(4).knockbackResist(1).followRange(36).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.GREEN_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.GREEN_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.GREEN_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.GREEN_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.GRILLFACE.get()).health(131).moveSpeed(0.2875).meleeStrength(16).knockbackResist(0.2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.GROBBLER.get()).health(85).moveSpeed(0.3).meleeStrength(8).knockbackResist(0.9).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.GYRO.get()).health(2500).flyingSpeed(0.1).projectileDamage(5).followRange(36).build(ev);
		AttributeBuilder.createMonster(AoAMobs.HAPPY.get()).health(73).moveSpeed(0.207).projectileDamage(7.5).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.HARKOS.get()).health(1300).moveSpeed(0.329).meleeStrength(13).knockbackResist(0.3).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.HIVE_KING.get()).health(2500).moveSpeed(0.2875).meleeStrength(20).knockbackResist(0.8).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.HIVE_WORKER.get()).health(30).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.HOST.get()).health(180).moveSpeed(0.23).meleeStrength(0).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.HUNTER.get()).health(123).moveSpeed(0.3).meleeStrength(13.5).knockbackResist(0.25).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.INMATE_X.get()).health(138).moveSpeed(0.2875).meleeStrength(13.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.INMATE_Y.get()).health(145).moveSpeed(0.27).meleeStrength(15).knockbackResist(0.2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.JAWE.get()).health(93).moveSpeed(0.28).meleeStrength(11).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.JUMBO.get()).health(100).moveSpeed(0.28).meleeStrength(10).knockbackResist(0.35).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.KAJAROS.get()).health(1750).moveSpeed(0.2875).meleeStrength(23).knockbackResist(0.8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.KEELER.get()).health(70).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.KING_BAMBAMBAM.get()).health(900).moveSpeed(0.207).projectileDamage(20).knockbackResist(1).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.KING_CREEPER.get()).health(85).moveSpeed(0.23).meleeStrength(0).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.KING_SHROOMUS.get()).health(1800).moveSpeed(0.207).projectileDamage(20).knockbackResist(1).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.KLOBBER.get()).health(1000).moveSpeed(0.2875).meleeStrength(20).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.KOKO.get()).health(80).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.KRANKY.get()).health(85).moveSpeed(0.29).meleeStrength(8).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.KROR.get()).health(2200).moveSpeed(0.2875).meleeStrength(60).knockbackResist(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LELYETIAN_CASTER.get()).health(60).moveSpeed(0.23).projectileDamage(7).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LELYETIAN_WARRIOR.get()).health(65).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LIGHTWALKER.get()).health(168).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LOLLYPOPPER.get()).health(80).moveSpeed(0.295).meleeStrength(8).knockbackResist(0.05).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LOST_SOUL.get()).health(125).moveSpeed(0.2875).meleeStrength(13).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LUNARCHER.get()).health(118).flyingSpeed(0.1).projectileDamage(14.5).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LURKER.get()).health(140).moveSpeed(0.2875).meleeStrength(16.5).knockbackResist(0.6).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.LUXOCRON.get()).health(169).moveSpeed(0.2875).meleeStrength(14).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MAGICAL_CREEPER.get()).health(55).moveSpeed(0.25).meleeStrength(0).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MECHACHRON.get()).health(114).moveSpeed(0.295).meleeStrength(10.5).knockbackResist(0.7).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MECHAMATON.get()).health(120).moveSpeed(0.295).meleeStrength(11).knockbackResist(0.8).armour(3.5).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.MECHBOT.get()).health(2500).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.9).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MECHYON.get()).health(85).moveSpeed(0.295).meleeStrength(11).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MERKYRE.get()).health(119).moveSpeed(0.26).meleeStrength(13).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MERMAGE.get()).health(130).moveSpeed(0.207).projectileDamage(14).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.MIRAGE.get()).health(750).moveSpeed(0.23).projectileDamage(8).knockbackResist(1).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.MISKEL.get()).health(1300).moveSpeed(0.207).projectileDamage(14).knockbackResist(0.4).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MODULO.get()).health(111).flyingSpeed(0.1).projectileDamage(13).armour(4).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MUNCHER.get()).health(135).moveSpeed(0).meleeStrength(13.5).knockbackResist(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.MUSHROOM_SPIDER.get()).health(61).moveSpeed(0.28).meleeStrength(7.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.NEPTUNO.get()).health(132).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.4).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.NIGHTMARE_SPIDER.get()).health(63).moveSpeed(0.28).meleeStrength(7.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.NIGHTWING.get()).health(78).flyingSpeed(0.1).meleeStrength(10.5).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.NIPPER.get()).health(45).moveSpeed(0.29).meleeStrength(8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.NOSPIKE.get()).health(65).moveSpeed(0.329).meleeStrength(8.5).knockbackResist(0.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.OCCULENT.get()).health(98).moveSpeed(0.2875).meleeStrength(8).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.OKAZOR.get()).health(1200).moveSpeed(0.2875).meleeStrength(50).knockbackResist(0.8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.OMNILIGHT.get()).health(156).flyingSpeed(0.1).projectileDamage(16).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.ORANGE_FLOWER.get()).health(30).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.PALADIN.get()).health(109).moveSpeed(0.207).meleeStrength(16).knockbackResist(0.2).armour(18).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.PARASECT.get()).health(80).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.PARAVITE.get()).health(68).moveSpeed(0.2875).meleeStrength(9.5).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.POD_PLANT.get()).health(64).moveSpeed(0.27).meleeStrength(6).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.POLYTOM.get()).health(80).flyingSpeed(0.1).projectileDamage(10).armour(5).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.PROSHIELD.get()).health(500).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.PURPLE_FLOWER.get()).health(120).moveSpeed(0.2875).meleeStrength(2).knockbackResist(0.8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.RAMRADON.get()).health(68).moveSpeed(0.2875).meleeStrength(7.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.RAWBONE.get()).health(64).moveSpeed(0.2875).meleeStrength(7).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.RAXXAN.get()).health(1000).moveSpeed(0.329).meleeStrength(15).knockbackResist(1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.RED_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(20).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.RED_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.RED_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.REFLUCT.get()).health(122).moveSpeed(0.2875).meleeStrength(15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ROCKBITER.get()).health(60).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.2).armour(1.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ROCK_CRAWLER.get()).health(70).moveSpeed(0.29).meleeStrength(7).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ROCK_CRITTER.get()).health(75).moveSpeed(0.2875).meleeStrength(7).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.ROCK_RIDER.get()).health(1500).moveSpeed(0.329).meleeStrength(30).knockbackResist(0.9).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.RUNICORN.get()).health(132).moveSpeed(0.3).meleeStrength(14).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.RUNICORN_RIDER.get()).health(132).moveSpeed(0.29).meleeStrength(14).knockbackResist(0.2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.RUNIC_GOLEM.get()).health(95).moveSpeed(0.265).meleeStrength(9).knockbackResist(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.RUNIC_GUARDIAN.get()).health(109).moveSpeed(0.207).projectileDamage(13.5).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SEA_VIPER.get()).health(116).moveSpeed(0.2875).meleeStrength(14.5).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.SHADOWLORD.get()).health(2000).moveSpeed(0.32).meleeStrength(2).knockbackResist(0).armour(0).followRange(52).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SHIFTER.get()).health(130).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SHYRE_KNIGHT.get()).health(140).moveSpeed(0.2875).meleeStrength(18).knockbackResist(0.35).armour(19).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SILENCER.get()).health(124).moveSpeed(0.2875).meleeStrength(12).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.SKELETRON.get()).health(1100).moveSpeed(0.2875).meleeStrength(70).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SKULL_CREATURE.get()).health(118).moveSpeed(0.2875).meleeStrength(12).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SLIMER.get()).health(120).moveSpeed(0.28).meleeStrength(11).knockbackResist(0.7).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SNAPPY.get()).health(85).moveSpeed(0.2875).meleeStrength(9.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SOULSCORNE.get()).health(140).moveSpeed(0.2875).meleeStrength(15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SOULVYRE.get()).health(178).moveSpeed(0.2875).meleeStrength(18.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SPECTRAL_WIZARD.get()).health(120).moveSpeed(0.207).projectileDamage(13).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SPIRIT_GUARDIAN.get()).health(60).moveSpeed(0.2875).meleeStrength(11.5).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SPIRIT_PROTECTOR.get()).health(60).moveSpeed(0.207).projectileDamage(11.5).knockbackResist(0.15).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SQUASHER.get()).health(105).moveSpeed(0.2875).meleeStrength(9.5).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SQUIGGLER.get()).health(76).moveSpeed(0.27).meleeStrength(6).knockbackResist(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.STALKER.get()).health(138).moveSpeed(0.3).meleeStrength(13.5).knockbackResist(0.3).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.STICKY.get()).health(85).moveSpeed(0.2875).meleeStrength(8.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.STIMULO.get()).health(164).moveSpeed(0.27).meleeStrength(15.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.STIMULOSUS.get()).health(180).moveSpeed(0.2875).meleeStrength(17).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.STITCHES.get()).health(85).moveSpeed(0.2875).meleeStrength(10.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SUGARFACE.get()).health(124).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SUNNY.get()).health(102).moveSpeed(0.29).meleeStrength(10.5).knockbackResist(0.15).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.SYSKER.get()).health(163).moveSpeed(0.2875).meleeStrength(16).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.TERRESTRIAL.get()).health(129).moveSpeed(0.2875).meleeStrength(14).knockbackResist(0.2).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.THARAFLY.get()).health(55).flyingSpeed(0.1).meleeStrength(6.5).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.TIPSY.get()).health(85).moveSpeed(0.207).meleeStrength(6).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.TOXXULOUS.get()).health(95).moveSpeed(0.295).meleeStrength(12.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.TRACKER.get()).health(60).moveSpeed(0.3).meleeStrength(6.5).knockbackResist(0.1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.TYROSAUR.get()).health(4000).moveSpeed(0.2875).meleeStrength(15).knockbackResist(0.9).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.UNDEAD_TROLL.get()).health(67).moveSpeed(0.207).projectileDamage(8).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.VALKYRIE.get()).health(115).flyingSpeed(0.1).projectileDamage(13.5).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.VINE_WIZARD.get()).health(90).moveSpeed(0.207).projectileDamage(12).knockbackResist(0.15).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.VINOCORNE.get()).health(2500).moveSpeed(0.2875).meleeStrength(10).knockbackResist(0.8).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.VISAGE.get()).health(60).moveSpeed(0.2875).meleeStrength(11).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.VISUALENT.get()).health(2000).flyingSpeed(0.1).meleeStrength(20).knockbackResist(1).followRange(36).build(ev);
		AttributeBuilder.createMonster(AoAMobs.VISULAR.get()).health(110).flyingSpeed(0.1).meleeStrength(12).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.VISULON.get()).health(150).flyingSpeed(0.1).meleeStrength(15).knockbackResist(0.3).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.VOLTRON.get()).health(94).moveSpeed(0.2875).meleeStrength(12).knockbackResist(0.2).armour(1.5).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.VOXXULON.get()).health(2000).moveSpeed(0).meleeStrength(30).knockbackResist(0.8).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.WEB_REAPER.get()).health(107).moveSpeed(0.207).projectileDamage(13).knockbackResist(0.6).followRange(24).build(ev);
		AttributeBuilder.createMonster(AoAMobs.WINGED_CREEPER.get()).health(55).moveSpeed(0.29).meleeStrength(0).knockbackResist(0.15).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.XXEUS.get()).health(3000).moveSpeed(0.329).meleeStrength(25).knockbackResist(1).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.YELLOW_FLOWER.get()).health(40).moveSpeed(0.2875).meleeStrength(5).knockbackResist(0.8).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.YELLOW_GUARDIAN.get()).health(750).moveSpeed(0.207).projectileDamage(15).followRange(24).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.YELLOW_RUNE_TEMPLAR.get()).health(400).moveSpeed(0).knockbackResist(1).armour(0).followRange(16).build(ev);
		//AttributeBuilder.createMonster(AoAMobs.YELLOW_RUNIC_LIFEFORM.get()).health(80).moveSpeed(0.2875).meleeStrength(6).knockbackResist(0.8).armour(1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ZARG.get()).health(120).moveSpeed(0.2875).meleeStrength(15.5).knockbackResist(0.1).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ZHINX.get()).health(60).moveSpeed(0.28).meleeStrength(6.5).followRange(16).build(ev);
		AttributeBuilder.createMonster(AoAMobs.ZORP.get()).health(114).moveSpeed(0.2875).meleeStrength(15).followRange(16).build(ev);

		//AttributeBuilder.create(AoANpcs.ASSASSIN.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.get(), NeoForgeMod.NAMETAG_DISTANCE.get(), NeoForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.SKILL_MASTER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.CORRUPTED_TRAVELLER.get()).health(50).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.CREEP_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.CRYSTAL_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.DUNGEON_KEEPER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.EXPLOSIVES_EXPERT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.GORB_ARMS_DEALER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.GORB_CITIZEN.get()).health(20).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.GORB_ENGINEER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.LELYETIAN_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.LELYETIAN_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.LOTTOMAN.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.METALLOID.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.NATURALIST.get()).health(25).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.PRIMORDIAL_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.PRIMORDIAL_GUIDE.get()).health(30).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.PRIMORDIAL_MERCHANT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.PRIMORDIAL_SPELLBINDER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.PRIMORDIAL_WIZARD.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.PROFESSOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.SHYRE_ARCHER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.SHYRE_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.STORE_KEEPER.get()).health(20).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.TOKEN_COLLECTOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.TOY_MERCHANT.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.TROLL_TRADER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.UNDEAD_HERALD.get()).health(16).moveSpeed(0.14375f).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.ZAL_BANKER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.ZAL_CHILD.get()).health(15).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.ZAL_CITIZEN.get()).health(20).moveSpeed(0.23).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.ZAL_GROCER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.ZAL_HERBALIST.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.ZAL_SPELLBINDER.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);
		AttributeBuilder.create(AoANpcs.ZAL_VENDOR.get()).health(30).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);

		AttributeBuilder.create(AoANpcs.DRYAD_SPRITE.get()).health(5).moveSpeed(0.329).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.value(), NeoForgeMod.NAMETAG_DISTANCE.value(), NeoForgeMod.ENTITY_GRAVITY.value(), Attributes.ATTACK_KNOCKBACK).build(ev);

		//AttributeBuilder.create(AoAProjectiles.CORALLUS_SHOT.get()).health(20).moveSpeed(0.7).knockbackResist(0).armour(0).followRange(16).extraAttributes(Attributes.ARMOR_TOUGHNESS, NeoForgeMod.SWIM_SPEED.get(), NeoForgeMod.NAMETAG_DISTANCE.get(), NeoForgeMod.ENTITY_GRAVITY.get(), Attributes.ATTACK_KNOCKBACK).build(ev);
	}

	private record AttributeBuilder(EntityType<? extends LivingEntity> entityType, AttributeSupplier.Builder attributeMap) {
		private static AttributeBuilder create(EntityType<? extends LivingEntity> entityType) {
			return new AttributeBuilder(entityType, LivingEntity.createLivingAttributes());
		}

		private static AttributeBuilder createMonster(EntityType<? extends LivingEntity> entityType) {
			return new AttributeBuilder(entityType, AoAMonster.getDefaultAttributes());
		}

		private AttributeBuilder health(double health) {
			attributeMap.add(Attributes.MAX_HEALTH, health);

			return this;
		}

		private AttributeBuilder swimSpeedMod(double speed) {
			attributeMap.add(NeoForgeMod.SWIM_SPEED.value(), speed);

			return this;
		}

		private AttributeBuilder nametagDistance(double distance) {
			attributeMap.add(NeoForgeMod.NAMETAG_DISTANCE.value(), distance);
			;

			return this;
		}

		private AttributeBuilder gravity(double gravity) {
			attributeMap.add(NeoForgeMod.ENTITY_GRAVITY.value(), gravity);

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

		private AttributeBuilder aggroRange(double distance) {
			attributeMap.add(AoAAttributes.AGGRO_RANGE.get(), distance);

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

		private AttributeBuilder stepHeight(double stepHeight) {
			attributeMap.add(NeoForgeMod.STEP_HEIGHT.value(), stepHeight);

			return this;
		}

		private AttributeBuilder extraAttributes(Attribute... attributes) {
			for (Attribute attribute : attributes) {
				if (!this.attributeMap.hasAttribute(attribute))
					this.attributeMap.add(attribute);
			}

			return this;
		}

		private void build(EntityAttributeCreationEvent ev) {
			ev.put(entityType, attributeMap.build());
		}
	}
}
