package net.tslat.aoa3.common.registration.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoADamageTypes {
	public static final ResourceKey<DamageType> HAULING = create("hauling");
	public static final ResourceKey<DamageType> RECOIL = create("recoil");
	public static final ResourceKey<DamageType> BURN = create("burn");
	public static final ResourceKey<DamageType> FREEZE = create("freeze");
	public static final ResourceKey<DamageType> SUFFOCATION = create("suffocation");
	public static final ResourceKey<DamageType> BLEED = create("bleed");
	public static final ResourceKey<DamageType> ACID = create("acid");

	public static final ResourceKey<DamageType> RANGED_ATTACK = create("ranged_attack");

	public static final ResourceKey<DamageType> MOB_MELEE_ATTACK = create("mob_melee_attack");
	public static final ResourceKey<DamageType> MOB_FIRE_MELEE_ATTACK = create("mob_fire_melee_attack");
	public static final ResourceKey<DamageType> MOB_FIRE_RANGED_ATTACK = create("mob_fire_ranged_attack");

	public static final ResourceKey<DamageType> MOB_FIRE_RECOIL = create("mob_fire_recoil");
	public static final ResourceKey<DamageType> MOB_FLAMETHROWER = create("mob_flamethrower");
	public static final ResourceKey<DamageType> MOB_ICEBEAM = create("mob_icebeam");

	public static final ResourceKey<DamageType> VULCANE = create("vulcane");
	public static final ResourceKey<DamageType> GUN = create("gun");
	public static final ResourceKey<DamageType> HEAVY_GUN = create("heavy_gun");
	public static final ResourceKey<DamageType> ENERGY_PROJECTILE = create("energy_projectile");
	public static final ResourceKey<DamageType> MAGIC_PROJECTILE = create("magic_projectile");
	public static final ResourceKey<DamageType> MAGIC_ATTACK = create("magic_effect");
	public static final ResourceKey<DamageType> ENERGY_ATTACK = create("energy_effect");

	private static ResourceKey<DamageType> create(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, AdventOfAscension.id(id));
	}
}
