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

@SuppressWarnings({"ConstantConditions", "unused"})
@Mod.EventBusSubscriber
public class BiomeRegister {
	@GameRegistry.ObjectHolder("aoa3:abyss")
	public static final BiomeAbyss biomeAbyss = null;
	@GameRegistry.ObjectHolder("aoa3:ancient_cavern")
	public static final BiomeAncientCavern biomeAncientCavern = null;
	@GameRegistry.ObjectHolder("aoa3:barathos")
	public static final BiomeBarathos biomeBarathos = null;
	@GameRegistry.ObjectHolder("aoa3:candyland")
	public static final BiomeCandyland biomeCandyland = null;
	@GameRegistry.ObjectHolder("aoa3:celeve")
	public static final BiomeCeleve biomeCeleve = null;
	@GameRegistry.ObjectHolder("aoa3:creeponia")
	public static final BiomeCreeponia biomeCreeponia = null;
	@GameRegistry.ObjectHolder("aoa3:crystevia")
	public static final BiomeCrystevia biomeCrystevia = null;
	@GameRegistry.ObjectHolder("aoa3:deeplands")
	public static final BiomeDeeplands biomeDeeplands = null;
	@GameRegistry.ObjectHolder("aoa3:dustopia")
	public static final BiomeDustopia biomeDustopia = null;
	@GameRegistry.ObjectHolder("aoa3:gardencia")
	public static final BiomeGardencia biomeGardencia = null;
	@GameRegistry.ObjectHolder("aoa3:greckon")
	public static final BiomeGreckon biomeGreckon = null;
	@GameRegistry.ObjectHolder("aoa3:haven")
	public static final BiomeHaven biomeHaven = null;
	@GameRegistry.ObjectHolder("aoa3:immortallis")
	public static final BiomeImmortallis biomeImmortallis = null;
	@GameRegistry.ObjectHolder("aoa3:iromine")
	public static final BiomeIromine biomeIromine = null;
	@GameRegistry.ObjectHolder("aoa3:lborean")
	public static final BiomeLBorean biomeLBorean = null;
	@GameRegistry.ObjectHolder("aoa3:lelyetia")
	public static final BiomeLelyetia biomeLelyetia = null;
	@GameRegistry.ObjectHolder("aoa3:lunalus")
	public static final BiomeLunalus biomeLunalus = null;
	@GameRegistry.ObjectHolder("aoa3:mysterium")
	public static final BiomeMysterium biomeMysterium = null;
	@GameRegistry.ObjectHolder("aoa3:precasia")
	public static final BiomePrecasia biomePrecasia = null;
	@GameRegistry.ObjectHolder("aoa3:runandor")
	public static final BiomeRunandor biomeRunandor = null;
	@GameRegistry.ObjectHolder("aoa3:shyrelands")
	public static final BiomeShyrelands biomeShyrelands = null;
	@GameRegistry.ObjectHolder("aoa3:vox_ponds")
	public static final BiomeVoxPonds biomeVoxPonds = null;

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

		biomeAbyss.biomeInit();
		biomeAncientCavern.biomeInit();
		biomeBarathos.biomeInit();
		biomeCandyland.biomeInit();
		biomeCeleve.biomeInit();
		biomeCreeponia.biomeInit();
		biomeCrystevia.biomeInit();
		biomeDeeplands.biomeInit();
		biomeDustopia.biomeInit();
		biomeGardencia.biomeInit();
		biomeGreckon.biomeInit();
		biomeHaven.biomeInit();
		biomeImmortallis.biomeInit();
		biomeIromine.biomeInit();
		biomeLBorean.biomeInit();
		biomeLelyetia.biomeInit();
		biomeLunalus.biomeInit();
		biomeMysterium.biomeInit();
		biomePrecasia.biomeInit();
		biomeRunandor.biomeInit();
		biomeShyrelands.biomeInit();
		biomeVoxPonds.biomeInit();
	}
}
