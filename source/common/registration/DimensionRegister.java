package net.tslat.aoa3.common.registration;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.dimension.abyss.WorldProviderAbyss;
import net.tslat.aoa3.dimension.abyss.WorldTypeAbyss;
import net.tslat.aoa3.dimension.abyss.biomes.BiomeAbyss;
import net.tslat.aoa3.dimension.ancientcavern.WorldProviderAncientCavern;
import net.tslat.aoa3.dimension.ancientcavern.WorldTypeAncientCavern;
import net.tslat.aoa3.dimension.ancientcavern.biomes.BiomeAncientCavern;
import net.tslat.aoa3.dimension.barathos.WorldProviderBarathos;
import net.tslat.aoa3.dimension.barathos.WorldTypeBarathos;
import net.tslat.aoa3.dimension.barathos.biomes.BiomeBarathos;
import net.tslat.aoa3.dimension.candyland.WorldProviderCandyland;
import net.tslat.aoa3.dimension.candyland.WorldTypeCandyland;
import net.tslat.aoa3.dimension.candyland.biomes.BiomeCandyland;
import net.tslat.aoa3.dimension.celeve.WorldProviderCeleve;
import net.tslat.aoa3.dimension.celeve.WorldTypeCeleve;
import net.tslat.aoa3.dimension.celeve.biomes.BiomeCeleve;
import net.tslat.aoa3.dimension.creeponia.WorldProviderCreeponia;
import net.tslat.aoa3.dimension.creeponia.WorldTypeCreeponia;
import net.tslat.aoa3.dimension.creeponia.biomes.BiomeCreeponia;
import net.tslat.aoa3.dimension.crystevia.WorldProviderCrystevia;
import net.tslat.aoa3.dimension.crystevia.WorldTypeCrystevia;
import net.tslat.aoa3.dimension.crystevia.biomes.BiomeCrystevia;
import net.tslat.aoa3.dimension.deeplands.WorldProviderDeeplands;
import net.tslat.aoa3.dimension.deeplands.WorldTypeDeeplands;
import net.tslat.aoa3.dimension.deeplands.biomes.BiomeDeeplands;
import net.tslat.aoa3.dimension.dustopia.WorldProviderDustopia;
import net.tslat.aoa3.dimension.dustopia.WorldTypeDustopia;
import net.tslat.aoa3.dimension.dustopia.biomes.BiomeDustopia;
import net.tslat.aoa3.dimension.gardencia.WorldProviderGardencia;
import net.tslat.aoa3.dimension.gardencia.WorldTypeGardencia;
import net.tslat.aoa3.dimension.gardencia.biomes.BiomeGardencia;
import net.tslat.aoa3.dimension.greckon.WorldProviderGreckon;
import net.tslat.aoa3.dimension.greckon.WorldTypeGreckon;
import net.tslat.aoa3.dimension.greckon.biomes.BiomeGreckon;
import net.tslat.aoa3.dimension.haven.WorldProviderHaven;
import net.tslat.aoa3.dimension.haven.WorldTypeHaven;
import net.tslat.aoa3.dimension.haven.biomes.BiomeHaven;
import net.tslat.aoa3.dimension.immortallis.WorldProviderImmortallis;
import net.tslat.aoa3.dimension.immortallis.WorldTypeImmortallis;
import net.tslat.aoa3.dimension.immortallis.biomes.BiomeImmortallis;
import net.tslat.aoa3.dimension.iromine.WorldProviderIromine;
import net.tslat.aoa3.dimension.iromine.WorldTypeIromine;
import net.tslat.aoa3.dimension.iromine.biomes.BiomeIromine;
import net.tslat.aoa3.dimension.lborean.WorldProviderLBorean;
import net.tslat.aoa3.dimension.lborean.WorldTypeLBorean;
import net.tslat.aoa3.dimension.lborean.biomes.BiomeLBorean;
import net.tslat.aoa3.dimension.lelyetia.WorldProviderLelyetia;
import net.tslat.aoa3.dimension.lelyetia.WorldTypeLelyetia;
import net.tslat.aoa3.dimension.lelyetia.biomes.BiomeLelyetia;
import net.tslat.aoa3.dimension.lunalus.WorldProviderLunalus;
import net.tslat.aoa3.dimension.lunalus.WorldTypeLunalus;
import net.tslat.aoa3.dimension.lunalus.biomes.BiomeLunalus;
import net.tslat.aoa3.dimension.mysterium.WorldProviderMysterium;
import net.tslat.aoa3.dimension.mysterium.WorldTypeMysterium;
import net.tslat.aoa3.dimension.mysterium.biomes.BiomeMysterium;
import net.tslat.aoa3.dimension.precasia.WorldProviderPrecasia;
import net.tslat.aoa3.dimension.precasia.WorldTypePrecasia;
import net.tslat.aoa3.dimension.precasia.biomes.BiomePrecasia;
import net.tslat.aoa3.dimension.runandor.WorldProviderRunandor;
import net.tslat.aoa3.dimension.runandor.WorldTypeRunandor;
import net.tslat.aoa3.dimension.runandor.biomes.BiomeRunandor;
import net.tslat.aoa3.dimension.shyrelands.WorldProviderShyrelands;
import net.tslat.aoa3.dimension.shyrelands.WorldTypeShyrelands;
import net.tslat.aoa3.dimension.shyrelands.biomes.BiomeShyrelands;
import net.tslat.aoa3.dimension.voxponds.WorldProviderVoxPonds;
import net.tslat.aoa3.dimension.voxponds.WorldTypeVoxPonds;
import net.tslat.aoa3.dimension.voxponds.biomes.BiomeVoxPonds;
import net.tslat.aoa3.utils.ConfigurationUtil;

@Mod.EventBusSubscriber
public class DimensionRegister {
	public static DimensionType dimensionAbyss = registerDimensionType("abyss", "_abyss", ConfigurationUtil.dimAbyss, WorldProviderAbyss.class, false);
	public static DimensionType dimensionAncientCavern = registerDimensionType("ancient_cavern", "_ancientcavern", ConfigurationUtil.dimAncientCavern, WorldProviderAncientCavern.class, false);
	public static DimensionType dimensionBarathos = registerDimensionType("barathos", "_barathos", ConfigurationUtil.dimBarathos, WorldProviderBarathos.class, false);
	public static DimensionType dimensionCandyland = registerDimensionType("candyland", "_candyland", ConfigurationUtil.dimCandyland, WorldProviderCandyland.class, false);
	public static DimensionType dimensionCeleve = registerDimensionType("celeve", "_celeve", ConfigurationUtil.dimCeleve, WorldProviderCeleve.class, false);
	public static DimensionType dimensionCreeponia = registerDimensionType("creeponia", "_creeponia", ConfigurationUtil.dimCreeponia, WorldProviderCreeponia.class, false);
	public static DimensionType dimensionCrystevia = registerDimensionType("crystevia", "_crystevia", ConfigurationUtil.dimCrystevia, WorldProviderCrystevia.class, false);
	public static DimensionType dimensionDeeplands = registerDimensionType("deeplands", "_deeplands", ConfigurationUtil.dimDeeplands, WorldProviderDeeplands.class, false);
	public static DimensionType dimensionDustopia = registerDimensionType("dustopia", "_dustopia", ConfigurationUtil.dimDustopia, WorldProviderDustopia.class, false);
	public static DimensionType dimensionGardencia = registerDimensionType("gardencia", "_gardencia", ConfigurationUtil.dimGardencia, WorldProviderGardencia.class, false);
	public static DimensionType dimensionGreckon = registerDimensionType("greckon", "_greckon", ConfigurationUtil.dimGreckon, WorldProviderGreckon.class, false);
	public static DimensionType dimensionHaven = registerDimensionType("haven", "_haven", ConfigurationUtil.dimHaven, WorldProviderHaven.class, false);
	public static DimensionType dimensionImmortallis = registerDimensionType("immortallis", "_immortallis", ConfigurationUtil.dimImmortallis, WorldProviderImmortallis.class, false);
	public static DimensionType dimensionIromine = registerDimensionType("iromine", "_iromine", ConfigurationUtil.dimIromine, WorldProviderIromine.class, false);
	public static DimensionType dimensionLBorean = registerDimensionType("lborean", "_lborean", ConfigurationUtil.dimLborean, WorldProviderLBorean.class, false);
	public static DimensionType dimensionLelyetia = registerDimensionType("lelyetia", "_lelyetia", ConfigurationUtil.dimLelyetia, WorldProviderLelyetia.class, false);
	public static DimensionType dimensionLunalus = registerDimensionType("lunalus", "_lunalus", ConfigurationUtil.dimLunalus, WorldProviderLunalus.class, false);
	public static DimensionType dimensionMysterium = registerDimensionType("mysterium", "_mysterium", ConfigurationUtil.dimMysterium, WorldProviderMysterium.class, false);
	public static DimensionType dimensionPrecasia = registerDimensionType("precasia", "_precasia", ConfigurationUtil.dimPrecasia, WorldProviderPrecasia.class, false);
	public static DimensionType dimensionRunandor = registerDimensionType("runandor", "_runandor", ConfigurationUtil.dimRunandor, WorldProviderRunandor.class, false);
	public static DimensionType dimensionShyrelands = registerDimensionType("shyrelands", "_shyrelands", ConfigurationUtil.dimShyrelands, WorldProviderShyrelands.class, false);
	public static DimensionType dimensionVoxPonds = registerDimensionType("vox_ponds", "_voxponds", ConfigurationUtil.dimVoxPonds, WorldProviderVoxPonds.class, false);

	public static WorldTypeAbyss worldTypeAbyss = new WorldTypeAbyss();
	public static WorldTypeAncientCavern worldTypeAncientCavern = new WorldTypeAncientCavern();
	public static WorldTypeBarathos worldTypeBarathos = new WorldTypeBarathos();
	public static WorldTypeCandyland worldTypeCandyland = new WorldTypeCandyland();
	public static WorldTypeCeleve worldTypeCeleve = new WorldTypeCeleve();
	public static WorldTypeCreeponia worldTypeCreeponia = new WorldTypeCreeponia();
	public static WorldTypeCrystevia worldTypeCrystevia = new WorldTypeCrystevia();
	public static WorldTypeDeeplands worldTypeDeeplands = new WorldTypeDeeplands();
	public static WorldTypeDustopia worldTypeDustopia = new WorldTypeDustopia();
	public static WorldTypeGardencia worldTypeGardencia = new WorldTypeGardencia();
	public static WorldTypeGreckon worldTypeGreckon = new WorldTypeGreckon();
	public static WorldTypeHaven worldTypeHaven = new WorldTypeHaven();
	public static WorldTypeImmortallis worldTypeImmortallis = new WorldTypeImmortallis();
	public static WorldTypeIromine worldTypeIromine = new WorldTypeIromine();
	public static WorldTypeLBorean worldTypeLBorean = new WorldTypeLBorean();
	public static WorldTypeLelyetia worldTypeLelyetia = new WorldTypeLelyetia();
	public static WorldTypeLunalus worldTypeLunalus = new WorldTypeLunalus();
	public static WorldTypeMysterium worldTypeMysterium = new WorldTypeMysterium();
	public static WorldTypePrecasia worldTypePrecasia = new WorldTypePrecasia();
	public static WorldTypeRunandor worldTypeRunandor = new WorldTypeRunandor();
	public static WorldTypeShyrelands worldTypeShyrelands = new WorldTypeShyrelands();
	public static WorldTypeVoxPonds worldTypeVoxPonds = new WorldTypeVoxPonds();

	public static BiomeAbyss biomeAbyss = new BiomeAbyss();
	public static BiomeAncientCavern biomeAncientCavern = new BiomeAncientCavern();
	public static BiomeBarathos biomeBarathos = new BiomeBarathos();
	public static BiomeCandyland biomeCandyland = new BiomeCandyland();
	public static BiomeCeleve biomeCeleve = new BiomeCeleve();
	public static BiomeCreeponia biomeCreeponia = new BiomeCreeponia();
	public static BiomeCrystevia biomeCrystevia = new BiomeCrystevia();
	public static BiomeDeeplands biomeDeeplands = new BiomeDeeplands();
	public static BiomeDustopia biomeDustopia = new BiomeDustopia();
	public static BiomeGardencia biomeGardencia = new BiomeGardencia();
	public static BiomeGreckon biomeGreckon = new BiomeGreckon();
	public static BiomeHaven biomeHaven = new BiomeHaven();
	public static BiomeImmortallis biomeImmortallis = new BiomeImmortallis();
	public static BiomeIromine biomeIromine = new BiomeIromine();
	public static BiomeLBorean biomeLBorean = new BiomeLBorean();
	public static BiomeLelyetia biomeLelyetia = new BiomeLelyetia();
	public static BiomeLunalus biomeLunalus = new BiomeLunalus();
	public static BiomeMysterium biomeMysterium = new BiomeMysterium();
	public static BiomePrecasia biomePrecasia = new BiomePrecasia();
	public static BiomeRunandor biomeRunandor = new BiomeRunandor();
	public static BiomeShyrelands biomeShyrelands = new BiomeShyrelands();
	public static BiomeVoxPonds biomeVoxPonds = new BiomeVoxPonds();


	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> ev) {
		ev.getRegistry().registerAll(
				biomeAbyss,
				biomeAncientCavern,
				biomeBarathos,
				biomeCandyland,
				biomeCeleve,
				biomeCreeponia,
				biomeCrystevia,
				biomeDeeplands,
				biomeDustopia,
				biomeGardencia,
				biomeGreckon,
				biomeHaven,
				biomeImmortallis,
				biomeIromine,
				biomeLBorean,
				biomeLelyetia,
				biomeLunalus,
				biomeMysterium,
				biomePrecasia,
				biomeRunandor,
				biomeShyrelands,
				biomeVoxPonds
		);
	}

	public static void preInit() {
		registerDimension(dimensionAbyss.getId(), dimensionAbyss);
		registerDimension(dimensionAncientCavern.getId(), dimensionAncientCavern);
		registerDimension(dimensionBarathos.getId(), dimensionBarathos);
		registerDimension(dimensionCandyland.getId(), dimensionCandyland);
		registerDimension(dimensionCeleve.getId(), dimensionCeleve);
		registerDimension(dimensionCreeponia.getId(), dimensionCreeponia);
		registerDimension(dimensionCrystevia.getId(), dimensionCrystevia);
		registerDimension(dimensionDeeplands.getId(), dimensionDeeplands);
		registerDimension(dimensionDustopia.getId(), dimensionDustopia);
		registerDimension(dimensionGardencia.getId(), dimensionGardencia);
		registerDimension(dimensionGreckon.getId(), dimensionGreckon);
		registerDimension(dimensionHaven.getId(), dimensionHaven);
		registerDimension(dimensionImmortallis.getId(), dimensionImmortallis);
		registerDimension(dimensionIromine.getId(), dimensionIromine);
		registerDimension(dimensionLBorean.getId(), dimensionLBorean);
		registerDimension(dimensionLelyetia.getId(), dimensionLelyetia);
		registerDimension(dimensionLunalus.getId(), dimensionLunalus);
		registerDimension(dimensionMysterium.getId(), dimensionMysterium);
		registerDimension(dimensionPrecasia.getId(), dimensionPrecasia);
		registerDimension(dimensionRunandor.getId(), dimensionRunandor);
		registerDimension(dimensionShyrelands.getId(), dimensionShyrelands);
		registerDimension(dimensionVoxPonds.getId(), dimensionVoxPonds);
	}

	public static void init() {
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

	private static DimensionType registerDimensionType(String name, String suffix, int id, Class<? extends WorldProvider> clazz, boolean keepLoaded) {
		if (DimensionManager.isDimensionRegistered(id)) {
			AdventOfAscension.getLogger().fatal("Dimension \"" + name + "\" with an id of: " + id + " has failed to load due to ID conflict.");

			return null;
		}
		else {
			return DimensionType.register(name, suffix, id, clazz, keepLoaded);
		}
	}

	private static void registerDimension(int id, DimensionType dimType) {
		if (dimType != null)
			DimensionManager.registerDimension(id, dimType);
	}
}
