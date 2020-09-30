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

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber
public class DimensionRegister {
	public static final DimensionType DIM_ABYSS = registerDimensionType("abyss", "_abyss", ConfigurationUtil.MainConfig.dimensionIds.abyss, WorldProviderAbyss.class, false);
	public static final DimensionType DIM_ANCIENT_CAVERN = registerDimensionType("ancient_cavern", "_ancientcavern", ConfigurationUtil.MainConfig.dimensionIds.ancientCavern, WorldProviderAncientCavern.class, false);
	public static final DimensionType DIM_BARATHOS = registerDimensionType("barathos", "_barathos", ConfigurationUtil.MainConfig.dimensionIds.barathos, WorldProviderBarathos.class, false);
	public static final DimensionType DIM_CANDYLAND = registerDimensionType("candyland", "_candyland", ConfigurationUtil.MainConfig.dimensionIds.candyland, WorldProviderCandyland.class, false);
	public static final DimensionType DIM_CELEVE = registerDimensionType("celeve", "_celeve", ConfigurationUtil.MainConfig.dimensionIds.celeve, WorldProviderCeleve.class, false);
	public static final DimensionType DIM_CREEPONIA = registerDimensionType("creeponia", "_creeponia", ConfigurationUtil.MainConfig.dimensionIds.creeponia, WorldProviderCreeponia.class, false);
	public static final DimensionType DIM_CRYSTEVIA = registerDimensionType("crystevia", "_crystevia", ConfigurationUtil.MainConfig.dimensionIds.crystevia, WorldProviderCrystevia.class, false);
	public static final DimensionType DIM_DEEPLANDS = registerDimensionType("deeplands", "_deeplands", ConfigurationUtil.MainConfig.dimensionIds.deeplands, WorldProviderDeeplands.class, false);
	public static final DimensionType DIM_DUSTOPIA = registerDimensionType("dustopia", "_dustopia", ConfigurationUtil.MainConfig.dimensionIds.dustopia, WorldProviderDustopia.class, false);
	public static final DimensionType DIM_GARDENCIA = registerDimensionType("gardencia", "_gardencia", ConfigurationUtil.MainConfig.dimensionIds.gardencia, WorldProviderGardencia.class, false);
	public static final DimensionType DIM_GRECKON = registerDimensionType("greckon", "_greckon", ConfigurationUtil.MainConfig.dimensionIds.greckon, WorldProviderGreckon.class, false);
	public static final DimensionType DIM_HAVEN = registerDimensionType("haven", "_haven", ConfigurationUtil.MainConfig.dimensionIds.haven, WorldProviderHaven.class, false);
	public static final DimensionType DIM_IMMORTALLIS = registerDimensionType("immortallis", "_immortallis", ConfigurationUtil.MainConfig.dimensionIds.immortallis, WorldProviderImmortallis.class, false);
	public static final DimensionType DIM_IROMINE = registerDimensionType("iromine", "_iromine", ConfigurationUtil.MainConfig.dimensionIds.iromine, WorldProviderIromine.class, false);
	public static final DimensionType DIM_LBOREAN = registerDimensionType("lborean", "_lborean", ConfigurationUtil.MainConfig.dimensionIds.lborean, WorldProviderLBorean.class, false);
	public static final DimensionType DIM_LELYETIA = registerDimensionType("lelyetia", "_lelyetia", ConfigurationUtil.MainConfig.dimensionIds.lelyetia, WorldProviderLelyetia.class, false);
	public static final DimensionType DIM_LUNALUS = registerDimensionType("lunalus", "_lunalus", ConfigurationUtil.MainConfig.dimensionIds.lunalus, WorldProviderLunalus.class, false);
	public static final DimensionType DIM_MYSTERIUM = registerDimensionType("mysterium", "_mysterium", ConfigurationUtil.MainConfig.dimensionIds.mysterium, WorldProviderMysterium.class, false);
	public static final DimensionType DIM_PRECASIA = registerDimensionType("precasia", "_precasia", ConfigurationUtil.MainConfig.dimensionIds.precasia, WorldProviderPrecasia.class, false);
	public static final DimensionType DIM_RUNANDOR = registerDimensionType("runandor", "_runandor", ConfigurationUtil.MainConfig.dimensionIds.runandor, WorldProviderRunandor.class, false);
	public static final DimensionType DIM_SHYRELANDS = registerDimensionType("shyrelands", "_shyrelands", ConfigurationUtil.MainConfig.dimensionIds.shyrelands, WorldProviderShyrelands.class, false);
	public static final DimensionType DIM_VOX_PONDS = registerDimensionType("vox_ponds", "_voxponds", ConfigurationUtil.MainConfig.dimensionIds.voxPonds, WorldProviderVoxPonds.class, false);

	public static final WorldTypeAbyss WORLD_ABYSS = new WorldTypeAbyss();
	public static final WorldTypeAncientCavern WORLD_ANCIENT_CAVERN = new WorldTypeAncientCavern();
	public static final WorldTypeBarathos WORLD_BARATHOS = new WorldTypeBarathos();
	public static final WorldTypeCandyland WORLD_CANDYLAND = new WorldTypeCandyland();
	public static final WorldTypeCeleve WORLD_CELEVE = new WorldTypeCeleve();
	public static final WorldTypeCreeponia WORLD_CREEPONIA = new WorldTypeCreeponia();
	public static final WorldTypeCrystevia WORLD_CRYSTEVIA = new WorldTypeCrystevia();
	public static final WorldTypeDeeplands WORLD_DEEPLANDS = new WorldTypeDeeplands();
	public static final WorldTypeDustopia WORLD_DUSTOPIA = new WorldTypeDustopia();
	public static final WorldTypeGardencia WORLD_GARDENCIA = new WorldTypeGardencia();
	public static final WorldTypeGreckon WORLD_GRECKON = new WorldTypeGreckon();
	public static final WorldTypeHaven WORLD_HAVEN = new WorldTypeHaven();
	public static final WorldTypeImmortallis WORLD_IMMORTALLIS = new WorldTypeImmortallis();
	public static final WorldTypeIromine WORLD_IROMINE = new WorldTypeIromine();
	public static final WorldTypeLBorean WORLD_LBOREAN = new WorldTypeLBorean();
	public static final WorldTypeLelyetia WORLD_LELYETIA = new WorldTypeLelyetia();
	public static final WorldTypeLunalus WORLD_LUNALUS = new WorldTypeLunalus();
	public static final WorldTypeMysterium WORLD_MYSTERIUM = new WorldTypeMysterium();
	public static final WorldTypePrecasia WORLD_PRECASIA = new WorldTypePrecasia();
	public static final WorldTypeRunandor WORLD_RUNANDOR = new WorldTypeRunandor();
	public static final WorldTypeShyrelands WORLD_SHYRELANDS = new WorldTypeShyrelands();
	public static final WorldTypeVoxPonds WORLD_VOX_PONDS = new WorldTypeVoxPonds();

	public static void preInit() {
		AdventOfAscension.logOptionalMessage("Registering dimensions...");

		registerDimension(DIM_ABYSS.getId(), DIM_ABYSS);
		registerDimension(DIM_ANCIENT_CAVERN.getId(), DIM_ANCIENT_CAVERN);
		registerDimension(DIM_BARATHOS.getId(), DIM_BARATHOS);
		registerDimension(DIM_CANDYLAND.getId(), DIM_CANDYLAND);
		registerDimension(DIM_CELEVE.getId(), DIM_CELEVE);
		registerDimension(DIM_CREEPONIA.getId(), DIM_CREEPONIA);
		registerDimension(DIM_CRYSTEVIA.getId(), DIM_CRYSTEVIA);
		registerDimension(DIM_DEEPLANDS.getId(), DIM_DEEPLANDS);
		registerDimension(DIM_DUSTOPIA.getId(), DIM_DUSTOPIA);
		registerDimension(DIM_GARDENCIA.getId(), DIM_GARDENCIA);
		registerDimension(DIM_GRECKON.getId(), DIM_GRECKON);
		registerDimension(DIM_HAVEN.getId(), DIM_HAVEN);
		registerDimension(DIM_IMMORTALLIS.getId(), DIM_IMMORTALLIS);
		registerDimension(DIM_IROMINE.getId(), DIM_IROMINE);
		registerDimension(DIM_LBOREAN.getId(), DIM_LBOREAN);
		registerDimension(DIM_LELYETIA.getId(), DIM_LELYETIA);
		registerDimension(DIM_LUNALUS.getId(), DIM_LUNALUS);
		registerDimension(DIM_MYSTERIUM.getId(), DIM_MYSTERIUM);
		registerDimension(DIM_PRECASIA.getId(), DIM_PRECASIA);
		registerDimension(DIM_RUNANDOR.getId(), DIM_RUNANDOR);
		registerDimension(DIM_SHYRELANDS.getId(), DIM_SHYRELANDS);
		registerDimension(DIM_VOX_PONDS.getId(), DIM_VOX_PONDS);
	}

	@SuppressWarnings("ConstantConditions")
	@Nonnull
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
