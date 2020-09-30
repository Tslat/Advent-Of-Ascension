package net.tslat.aoa3.common.registration;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.dimension.abyss.biomes.BiomeAbyss;
import net.tslat.aoa3.dimension.ancientcavern.biomes.BiomeAncientCavern;
import net.tslat.aoa3.dimension.barathos.biomes.BiomeBarathos;
import net.tslat.aoa3.dimension.candyland.biomes.BiomeCandyland;
import net.tslat.aoa3.dimension.celeve.biomes.BiomeCeleve;
import net.tslat.aoa3.dimension.creeponia.biomes.BiomeCreeponia;
import net.tslat.aoa3.dimension.crystevia.biomes.BiomeCrystevia;
import net.tslat.aoa3.dimension.deeplands.biomes.BiomeDeeplands;
import net.tslat.aoa3.dimension.dustopia.biomes.BiomeDustopia;
import net.tslat.aoa3.dimension.gardencia.biomes.BiomeGardencia;
import net.tslat.aoa3.dimension.greckon.biomes.BiomeGreckon;
import net.tslat.aoa3.dimension.haven.biomes.BiomeHaven;
import net.tslat.aoa3.dimension.immortallis.biomes.BiomeImmortallis;
import net.tslat.aoa3.dimension.iromine.biomes.BiomeIromine;
import net.tslat.aoa3.dimension.lborean.biomes.BiomeLBorean;
import net.tslat.aoa3.dimension.lelyetia.biomes.BiomeLelyetia;
import net.tslat.aoa3.dimension.lunalus.biomes.BiomeLunalus;
import net.tslat.aoa3.dimension.mysterium.biomes.BiomeMysterium;
import net.tslat.aoa3.dimension.precasia.biomes.BiomePrecasia;
import net.tslat.aoa3.dimension.runandor.biomes.BiomeRunandor;
import net.tslat.aoa3.dimension.shyrelands.biomes.BiomeShyrelands;
import net.tslat.aoa3.dimension.voxponds.biomes.BiomeVoxPonds;

import javax.annotation.Nonnull;

@SuppressWarnings({"ConstantConditions", "unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class BiomeRegister {
	public static final BiomeAbyss ABYSS = ObjectHolder();
	public static final BiomeAncientCavern ANCIENT_CAVERN = ObjectHolder();
	public static final BiomeBarathos BARATHOS = ObjectHolder();
	public static final BiomeCandyland CANDYLAND = ObjectHolder();
	public static final BiomeCeleve CELEVE = ObjectHolder();
	public static final BiomeCreeponia CREEPONIA = ObjectHolder();
	public static final BiomeCrystevia CRYSTEVIA = ObjectHolder();
	public static final BiomeDeeplands DEEPLANDS = ObjectHolder();
	public static final BiomeDustopia DUSTOPIA = ObjectHolder();
	public static final BiomeGardencia GARDENCIA = ObjectHolder();
	public static final BiomeGreckon GRECKON = ObjectHolder();
	public static final BiomeHaven HAVEN = ObjectHolder();
	public static final BiomeImmortallis IMMORTALLIS = ObjectHolder();
	public static final BiomeIromine IROMINE = ObjectHolder();
	public static final BiomeLBorean LBOREAN = ObjectHolder();
	public static final BiomeLelyetia LELYETIA = ObjectHolder();
	public static final BiomeLunalus LUNALUS = ObjectHolder();
	public static final BiomeMysterium MYSTERIUM = ObjectHolder();
	public static final BiomePrecasia PRECASIA = ObjectHolder();
	public static final BiomeRunandor RUNANDOR = ObjectHolder();
	public static final BiomeShyrelands SHYRELANDS = ObjectHolder();
	public static final BiomeVoxPonds VOX_PONDS = ObjectHolder();

	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> ev) {
		AdventOfAscension.logOptionalMessage("Beginning biome registration");

		ev.getRegistry().registerAll(
				new BiomeAbyss(),
				new BiomeAncientCavern(),
				new BiomeBarathos(),
				new BiomeCandyland(),
				new BiomeCeleve(),
				new BiomeCreeponia(),
				new BiomeCrystevia(),
				new BiomeDeeplands(),
				new BiomeDustopia(),
				new BiomeGardencia(),
				new BiomeGreckon(),
				new BiomeHaven(),
				new BiomeImmortallis(),
				new BiomeIromine(),
				new BiomeLBorean(),
				new BiomeLelyetia(),
				new BiomeLunalus(),
				new BiomeMysterium(),
				new BiomePrecasia(),
				new BiomeRunandor(),
				new BiomeShyrelands(),
				new BiomeVoxPonds()
		);
	}

	public static void init() {
		AdventOfAscension.logOptionalMessage("Initializing biomes");

		ABYSS.biomeInit();
		ANCIENT_CAVERN.biomeInit();
		BARATHOS.biomeInit();
		CANDYLAND.biomeInit();
		CELEVE.biomeInit();
		CREEPONIA.biomeInit();
		CRYSTEVIA.biomeInit();
		DEEPLANDS.biomeInit();
		DUSTOPIA.biomeInit();
		GARDENCIA.biomeInit();
		GRECKON.biomeInit();
		HAVEN.biomeInit();
		IMMORTALLIS.biomeInit();
		IROMINE.biomeInit();
		LBOREAN.biomeInit();
		LELYETIA.biomeInit();
		LUNALUS.biomeInit();
		MYSTERIUM.biomeInit();
		PRECASIA.biomeInit();
		RUNANDOR.biomeInit();
		SHYRELANDS.biomeInit();
		VOX_PONDS.biomeInit();
	}

	@SuppressWarnings("ConstantConditions")
	@Nonnull
	private static <T> T ObjectHolder() {
		return null;
	}
}
