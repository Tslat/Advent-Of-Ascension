package net.tslat.aoa3.content.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Map;
import java.util.Optional;

import static net.tslat.aoa3.content.world.gen.structure.AoAStructure.Settings.aoaSettings;

public class AoAStructure extends Structure {
	public static final Codec<AoAStructure> DEFAULT_CODEC = RecordCodecBuilder.<AoAStructure>mapCodec(codec -> codec.group(aoaSettings()).apply(codec, AoAStructure::new)).codec();

	protected final Settings settings;

	public AoAStructure(Settings settings) {
		super(settings.toVanillaSettings());

		this.settings = settings;
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(GenerationContext genContext) {
		return Optional.empty();
	}

	@Override
	public StructureType<?> type() {
		return null;
	}

	public record Settings(HolderSet<Biome> biomes, Map<MobCategory, StructureSpawnOverride> spawnOverrides, GenerationStep.Decoration step, TerrainAdjustment terrainAdaptation, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int maxPieces, HeightProvider startHeight, Optional<Heightmap.Types> startHeightmap) {
		private static final MapCodec<Settings> CODEC = RecordCodecBuilder.mapCodec(codec -> codec.group(
				RegistryCodecs.homogeneousList(Registry.BIOME_REGISTRY).fieldOf("biomes").forGetter(Settings::biomes),
				Codec.simpleMap(MobCategory.CODEC, StructureSpawnOverride.CODEC, StringRepresentable.keys(MobCategory.values())).fieldOf("spawn_overrides").forGetter(Settings::spawnOverrides),
				GenerationStep.Decoration.CODEC.optionalFieldOf("step", GenerationStep.Decoration.SURFACE_STRUCTURES).forGetter(Settings::step),
				TerrainAdjustment.CODEC.optionalFieldOf("terrain_adaptation", TerrainAdjustment.NONE).forGetter(Settings::terrainAdaptation),
				StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(Settings::startPool),
				ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(Settings::startJigsawName),
				Codec.intRange(0, 15).optionalFieldOf("size", 15).forGetter(Settings::maxPieces),
				HeightProvider.CODEC.optionalFieldOf("start_height", ConstantHeight.of(VerticalAnchor.absolute(0))).forGetter(Settings::startHeight),
				Heightmap.Types.CODEC.optionalFieldOf("heightmap_for_start").forGetter(Settings::startHeightmap)
		).apply(codec, Settings::new));

		private StructureSettings toVanillaSettings() {
			return new StructureSettings(biomes, spawnOverrides, step, terrainAdaptation);
		}

		public static <T extends AoAStructure> RecordCodecBuilder<T, Settings> aoaSettings() {
			return Settings.CODEC.forGetter(instance -> instance.settings);
		}
	}
}
