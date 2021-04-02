package net.tslat.aoa3.common.registration;

import com.google.common.collect.HashBiMap;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public final class AoADimensions {
	private static final HashBiMap<RegistryKey<World>, AoADimension> dimTypeMap = HashBiMap.create(25);

	public static final DimensionContainer ABYSS = new DimensionContainer("abyss", AoADimension.ABYSS);
	public static final DimensionContainer BARATHOS = new DimensionContainer("barathos", AoADimension.BARATHOS);
	public static final DimensionContainer CANDYLAND = new DimensionContainer("candyland", AoADimension.CANDYLAND);
	public static final DimensionContainer CELEVE = new DimensionContainer("celeve", AoADimension.CELEVE);
	public static final DimensionContainer CREEPONIA = new DimensionContainer("creeponia", AoADimension.CREEPONIA);
	public static final DimensionContainer CRYSTEVIA = new DimensionContainer("crystevia", AoADimension.CRYSTEVIA);
	public static final DimensionContainer DEEPLANDS = new DimensionContainer("deeplands", AoADimension.DEEPLANDS);
	public static final DimensionContainer DUSTOPIA = new DimensionContainer("dustopia", AoADimension.DUSTOPIA);
	public static final DimensionContainer THE_END = new DimensionContainer(World.END, AoADimension.THE_END);
	public static final DimensionContainer GARDENCIA = new DimensionContainer("gardencia", AoADimension.GARDENCIA);
	public static final DimensionContainer GRECKON = new DimensionContainer("greckon", AoADimension.GRECKON);
	public static final DimensionContainer HAVEN = new DimensionContainer("haven", AoADimension.HAVEN);
	public static final DimensionContainer IROMINE = new DimensionContainer("iromine", AoADimension.IROMINE);
	public static final DimensionContainer LBOREAN = new DimensionContainer("lborean", AoADimension.LBOREAN);
	public static final DimensionContainer LELYETIA = new DimensionContainer("lelyetia", AoADimension.LELYETIA);
	public static final DimensionContainer LUNALUS = new DimensionContainer("lunalus", AoADimension.LUNALUS);
	public static final DimensionContainer MYSTERIUM = new DimensionContainer("mysterium", AoADimension.MYSTERIUM);
	public static final DimensionContainer NETHER = new DimensionContainer(World.NETHER, AoADimension.NETHER);
	public static final DimensionContainer NOWHERE = new DimensionContainer("nowhere", AoADimension.NOWHERE);
	public static final DimensionContainer OVERWORLD = new DimensionContainer(World.OVERWORLD, AoADimension.OVERWORLD);
	public static final DimensionContainer PRECASIA = new DimensionContainer("precasia", AoADimension.PRECASIA);
	public static final DimensionContainer RUNANDOR = new DimensionContainer("runandor", AoADimension.RUNANDOR);
	public static final DimensionContainer SHYRELANDS = new DimensionContainer("shyrelands", AoADimension.SHYRELANDS);
	public static final DimensionContainer VOX_PONDS = new DimensionContainer("vox_ponds", AoADimension.VOX_PONDS);

	@Nullable
	public static AoADimension getDim(@Nonnull RegistryKey<World> key) {
		return dimTypeMap.get(key);
	}

	@Nullable
	public static RegistryKey<World> getWorldKey(@Nonnull AoADimension dim) {
		return dimTypeMap.inverse().get(dim);
	}

	public static class DimensionContainer {
		public final RegistryKey<World> key;
		public final AoADimension dim;

		private DimensionContainer(RegistryKey<World> key, AoADimension dim) {
			this.key = key;
			this.dim = dim;

			dimTypeMap.put(key, dim);
		}

		private DimensionContainer(String dimId, AoADimension dim) {
			this(RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AdventOfAscension.MOD_ID, dimId)), dim);
		}
	}

	public enum AoADimension {
		ABYSS,
		BARATHOS,
		CANDYLAND,
		CELEVE,
		CREEPONIA,
		CRYSTEVIA,
		DEEPLANDS,
		THE_END,
		DUSTOPIA,
		GARDENCIA,
		GRECKON,
		HAVEN,
		IROMINE,
		LBOREAN,
		LELYETIA,
		LUNALUS,
		MYSTERIUM,
		NETHER,
		NOWHERE,
		OVERWORLD,
		PRECASIA,
		RUNANDOR,
		SHYRELANDS,
		VOX_PONDS;
	}
}
