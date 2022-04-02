package net.tslat.aoa3.common.registration;

import com.google.common.collect.HashBiMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class AoADimensions {
	private static final HashBiMap<ResourceKey<Level>, AoADimension> dimTypeMap = HashBiMap.create(25);

	public static final DimensionContainer ABYSS = new DimensionContainer("abyss", AoADimension.ABYSS);
	public static final DimensionContainer BARATHOS = new DimensionContainer("barathos", AoADimension.BARATHOS);
	public static final DimensionContainer CANDYLAND = new DimensionContainer("candyland", AoADimension.CANDYLAND);
	public static final DimensionContainer CELEVE = new DimensionContainer("celeve", AoADimension.CELEVE);
	public static final DimensionContainer CREEPONIA = new DimensionContainer("creeponia", AoADimension.CREEPONIA);
	public static final DimensionContainer CRYSTEVIA = new DimensionContainer("crystevia", AoADimension.CRYSTEVIA);
	public static final DimensionContainer DEEPLANDS = new DimensionContainer("deeplands", AoADimension.DEEPLANDS);
	public static final DimensionContainer DUSTOPIA = new DimensionContainer("dustopia", AoADimension.DUSTOPIA);
	public static final DimensionContainer THE_END = new DimensionContainer(Level.END, AoADimension.THE_END);
	public static final DimensionContainer GARDENCIA = new DimensionContainer("gardencia", AoADimension.GARDENCIA);
	public static final DimensionContainer GRECKON = new DimensionContainer("greckon", AoADimension.GRECKON);
	public static final DimensionContainer HAVEN = new DimensionContainer("haven", AoADimension.HAVEN);
	public static final DimensionContainer IROMINE = new DimensionContainer("iromine", AoADimension.IROMINE);
	public static final DimensionContainer LBOREAN = new DimensionContainer("lborean", AoADimension.LBOREAN);
	public static final DimensionContainer LELYETIA = new DimensionContainer("lelyetia", AoADimension.LELYETIA);
	public static final DimensionContainer LUNALUS = new DimensionContainer("lunalus", AoADimension.LUNALUS);
	public static final DimensionContainer MYSTERIUM = new DimensionContainer("mysterium", AoADimension.MYSTERIUM);
	public static final DimensionContainer NETHER = new DimensionContainer(Level.NETHER, AoADimension.NETHER);
	public static final DimensionContainer NOWHERE = new DimensionContainer("nowhere", AoADimension.NOWHERE);
	public static final DimensionContainer OVERWORLD = new DimensionContainer(Level.OVERWORLD, AoADimension.OVERWORLD);
	public static final DimensionContainer PRECASIA = new DimensionContainer("precasia", AoADimension.PRECASIA);
	public static final DimensionContainer RUNANDOR = new DimensionContainer("runandor", AoADimension.RUNANDOR);
	public static final DimensionContainer SHYRELANDS = new DimensionContainer("shyrelands", AoADimension.SHYRELANDS);
	public static final DimensionContainer VOX_PONDS = new DimensionContainer("vox_ponds", AoADimension.VOX_PONDS);

	@Nullable
	public static AoADimension getDim(@Nonnull ResourceKey<Level> key) {
		return dimTypeMap.get(key);
	}

	@Nullable
	public static ResourceKey<Level> getWorldKey(@Nonnull AoADimension dim) {
		return dimTypeMap.inverse().get(dim);
	}

	public static class DimensionContainer {
		public final ResourceKey<Level> key;
		public final AoADimension dim;
		private ServerLevel world = null;

		private DimensionContainer(ResourceKey<Level> key, AoADimension dim) {
			this.key = key;
			this.dim = dim;

			dimTypeMap.put(key, dim);
		}

		private DimensionContainer(String dimId, AoADimension dim) {
			this(ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AdventOfAscension.MOD_ID, dimId)), dim);
		}

		@Nullable
		public ServerLevel getWorld() {
			if (this.world != null)
				return this.world;

			if (ServerLifecycleHooks.getCurrentServer() == null)
				return null;

			this.world = ServerLifecycleHooks.getCurrentServer().getLevel(key);

			return this.world;
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
