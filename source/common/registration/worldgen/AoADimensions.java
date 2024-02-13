package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoADimensions {
	public static final ResourceKey<Level> ABYSS = create("abyss");
	public static final ResourceKey<Level> BARATHOS = create("barathos");
	public static final ResourceKey<Level> CANDYLAND = create("candyland");
	public static final ResourceKey<Level> CELEVE = create("celeve");
	public static final ResourceKey<Level> CREEPONIA = create("creeponia");
	public static final ResourceKey<Level> CRYSTEVIA = create("crystevia");
	public static final ResourceKey<Level> DEEPLANDS = create("deeplands");
	public static final ResourceKey<Level> DUSTOPIA = create("dustopia");
	public static final ResourceKey<Level> GARDENCIA = create("gardencia");
	public static final ResourceKey<Level> GRECKON = create("greckon");
	public static final ResourceKey<Level> HAVEN = create("haven");
	public static final ResourceKey<Level> IROMINE = create("iromine");
	public static final ResourceKey<Level> LBOREAN = create("lborean");
	public static final ResourceKey<Level> LELYETIA = create("lelyetia");
	public static final ResourceKey<Level> LUNALUS = create("lunalus");
	public static final ResourceKey<Level> MYSTERIUM = create("mysterium");
	public static final ResourceKey<Level> NOWHERE = create("nowhere");
	public static final ResourceKey<Level> PRECASIA = create("precasia");
	public static final ResourceKey<Level> RUNANDOR = create("runandor");
	public static final ResourceKey<Level> SHYRELANDS = create("shyrelands");
	public static final ResourceKey<Level> VOX_PONDS = create("vox_ponds");

	public static final ResourceKey<Level> OVERWORLD = Level.OVERWORLD;
	public static final ResourceKey<Level> NETHER = Level.NETHER;
	public static final ResourceKey<Level> THE_END = Level.END;

	private static ResourceKey<Level> create(String id) {
		return ResourceKey.create(Registries.DIMENSION, AdventOfAscension.id(id));
	}
}
