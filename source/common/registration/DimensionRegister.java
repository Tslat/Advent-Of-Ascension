package net.tslat.aoa3.common.registration;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.dimension.abyss.WorldProviderAbyss;
import net.tslat.aoa3.dimension.abyss.WorldTypeAbyss;
import net.tslat.aoa3.dimension.ancientcavern.WorldProviderAncientCavern;
import net.tslat.aoa3.dimension.ancientcavern.WorldTypeAncientCavern;
import net.tslat.aoa3.dimension.barathos.WorldProviderBarathos;
import net.tslat.aoa3.dimension.barathos.WorldTypeBarathos;
import net.tslat.aoa3.dimension.candyland.WorldProviderCandyland;
import net.tslat.aoa3.dimension.candyland.WorldTypeCandyland;
import net.tslat.aoa3.dimension.celeve.WorldProviderCeleve;
import net.tslat.aoa3.dimension.celeve.WorldTypeCeleve;
import net.tslat.aoa3.dimension.creeponia.WorldProviderCreeponia;
import net.tslat.aoa3.dimension.creeponia.WorldTypeCreeponia;
import net.tslat.aoa3.dimension.crystevia.WorldProviderCrystevia;
import net.tslat.aoa3.dimension.crystevia.WorldTypeCrystevia;
import net.tslat.aoa3.dimension.deeplands.WorldProviderDeeplands;
import net.tslat.aoa3.dimension.deeplands.WorldTypeDeeplands;
import net.tslat.aoa3.dimension.dustopia.WorldProviderDustopia;
import net.tslat.aoa3.dimension.dustopia.WorldTypeDustopia;
import net.tslat.aoa3.dimension.gardencia.WorldProviderGardencia;
import net.tslat.aoa3.dimension.gardencia.WorldTypeGardencia;
import net.tslat.aoa3.dimension.greckon.WorldProviderGreckon;
import net.tslat.aoa3.dimension.greckon.WorldTypeGreckon;
import net.tslat.aoa3.dimension.haven.WorldProviderHaven;
import net.tslat.aoa3.dimension.haven.WorldTypeHaven;
import net.tslat.aoa3.dimension.immortallis.WorldProviderImmortallis;
import net.tslat.aoa3.dimension.immortallis.WorldTypeImmortallis;
import net.tslat.aoa3.dimension.iromine.WorldProviderIromine;
import net.tslat.aoa3.dimension.iromine.WorldTypeIromine;
import net.tslat.aoa3.dimension.lborean.WorldProviderLBorean;
import net.tslat.aoa3.dimension.lborean.WorldTypeLBorean;
import net.tslat.aoa3.dimension.lelyetia.WorldProviderLelyetia;
import net.tslat.aoa3.dimension.lelyetia.WorldTypeLelyetia;
import net.tslat.aoa3.dimension.lunalus.WorldProviderLunalus;
import net.tslat.aoa3.dimension.lunalus.WorldTypeLunalus;
import net.tslat.aoa3.dimension.mysterium.WorldProviderMysterium;
import net.tslat.aoa3.dimension.mysterium.WorldTypeMysterium;
import net.tslat.aoa3.dimension.precasia.WorldProviderPrecasia;
import net.tslat.aoa3.dimension.precasia.WorldTypePrecasia;
import net.tslat.aoa3.dimension.runandor.WorldProviderRunandor;
import net.tslat.aoa3.dimension.runandor.WorldTypeRunandor;
import net.tslat.aoa3.dimension.shyrelands.WorldProviderShyrelands;
import net.tslat.aoa3.dimension.shyrelands.WorldTypeShyrelands;
import net.tslat.aoa3.dimension.voxponds.WorldProviderVoxPonds;
import net.tslat.aoa3.dimension.voxponds.WorldTypeVoxPonds;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber
public class DimensionRegister {
	public static DimensionType dimensionAbyss = registerDimensionType("abyss", "_abyss", ConfigurationUtil.MainConfig.dimensionIds.abyss, WorldProviderAbyss.class, false);
	public static DimensionType dimensionAncientCavern = registerDimensionType("ancient_cavern", "_ancientcavern", ConfigurationUtil.MainConfig.dimensionIds.ancientCavern, WorldProviderAncientCavern.class, false);
	public static DimensionType dimensionBarathos = registerDimensionType("barathos", "_barathos", ConfigurationUtil.MainConfig.dimensionIds.barathos, WorldProviderBarathos.class, false);
	public static DimensionType dimensionCandyland = registerDimensionType("candyland", "_candyland", ConfigurationUtil.MainConfig.dimensionIds.candyland, WorldProviderCandyland.class, false);
	public static DimensionType dimensionCeleve = registerDimensionType("celeve", "_celeve", ConfigurationUtil.MainConfig.dimensionIds.celeve, WorldProviderCeleve.class, false);
	public static DimensionType dimensionCreeponia = registerDimensionType("creeponia", "_creeponia", ConfigurationUtil.MainConfig.dimensionIds.creeponia, WorldProviderCreeponia.class, false);
	public static DimensionType dimensionCrystevia = registerDimensionType("crystevia", "_crystevia", ConfigurationUtil.MainConfig.dimensionIds.crystevia, WorldProviderCrystevia.class, false);
	public static DimensionType dimensionDeeplands = registerDimensionType("deeplands", "_deeplands", ConfigurationUtil.MainConfig.dimensionIds.deeplands, WorldProviderDeeplands.class, false);
	public static DimensionType dimensionDustopia = registerDimensionType("dustopia", "_dustopia", ConfigurationUtil.MainConfig.dimensionIds.dustopia, WorldProviderDustopia.class, false);
	public static DimensionType dimensionGardencia = registerDimensionType("gardencia", "_gardencia", ConfigurationUtil.MainConfig.dimensionIds.gardencia, WorldProviderGardencia.class, false);
	public static DimensionType dimensionGreckon = registerDimensionType("greckon", "_greckon", ConfigurationUtil.MainConfig.dimensionIds.greckon, WorldProviderGreckon.class, false);
	public static DimensionType dimensionHaven = registerDimensionType("haven", "_haven", ConfigurationUtil.MainConfig.dimensionIds.haven, WorldProviderHaven.class, false);
	public static DimensionType dimensionImmortallis = registerDimensionType("immortallis", "_immortallis", ConfigurationUtil.MainConfig.dimensionIds.immortallis, WorldProviderImmortallis.class, false);
	public static DimensionType dimensionIromine = registerDimensionType("iromine", "_iromine", ConfigurationUtil.MainConfig.dimensionIds.iromine, WorldProviderIromine.class, false);
	public static DimensionType dimensionLBorean = registerDimensionType("lborean", "_lborean", ConfigurationUtil.MainConfig.dimensionIds.lborean, WorldProviderLBorean.class, false);
	public static DimensionType dimensionLelyetia = registerDimensionType("lelyetia", "_lelyetia", ConfigurationUtil.MainConfig.dimensionIds.lelyetia, WorldProviderLelyetia.class, false);
	public static DimensionType dimensionLunalus = registerDimensionType("lunalus", "_lunalus", ConfigurationUtil.MainConfig.dimensionIds.lunalus, WorldProviderLunalus.class, false);
	public static DimensionType dimensionMysterium = registerDimensionType("mysterium", "_mysterium", ConfigurationUtil.MainConfig.dimensionIds.mysterium, WorldProviderMysterium.class, false);
	public static DimensionType dimensionPrecasia = registerDimensionType("precasia", "_precasia", ConfigurationUtil.MainConfig.dimensionIds.precasia, WorldProviderPrecasia.class, false);
	public static DimensionType dimensionRunandor = registerDimensionType("runandor", "_runandor", ConfigurationUtil.MainConfig.dimensionIds.runandor, WorldProviderRunandor.class, false);
	public static DimensionType dimensionShyrelands = registerDimensionType("shyrelands", "_shyrelands", ConfigurationUtil.MainConfig.dimensionIds.shyrelands, WorldProviderShyrelands.class, false);
	public static DimensionType dimensionVoxPonds = registerDimensionType("vox_ponds", "_voxponds", ConfigurationUtil.MainConfig.dimensionIds.voxPonds, WorldProviderVoxPonds.class, false);

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

	public static void preInit() {
		AdventOfAscension.logOptionalMessage("Registering dimensions...");

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

	private static DimensionType registerDimensionType(String name, String suffix, int id, Class<? extends WorldProvider> clazz, boolean keepLoaded) {
		if (DimensionManager.isDimensionRegistered(id)) {
			AdventOfAscension.logMessage(Level.FATAL, "Dimension \"" + name + "\" with an id of: " + id + " has failed to load due to ID conflict.");

			return null;
		}
		else {
			return DimensionType.register(name, suffix, id, clazz, keepLoaded);
		}
	}

	private static void registerDimension(int id, DimensionType dimType) {
		if (dimType != null) {
			DimensionManager.registerDimension(id, dimType);
			AdventOfAscension.logOptionalMessage("Registered dimension " + dimType.getName() + ", with ID: " + id);
		}
	}
}
