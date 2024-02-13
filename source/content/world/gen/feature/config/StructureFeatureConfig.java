package net.tslat.aoa3.content.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.tslat.smartbrainlib.util.RandomUtil;

import java.util.Arrays;
import java.util.List;

public record StructureFeatureConfig(List<ResourceLocation> templatePaths, boolean doMirroring, boolean doRotations, boolean spawnEntities, boolean requireGround, Holder<StructureProcessorList> processors) implements FeatureConfiguration {
	public static final Codec<StructureFeatureConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
					ResourceLocation.CODEC.listOf().fieldOf("templates").forGetter(config -> config.templatePaths),
					Codec.BOOL.optionalFieldOf("random_mirroring", true).forGetter(config -> config.doMirroring),
					Codec.BOOL.optionalFieldOf("random_rotation", true).forGetter(config -> config.doRotations),
					Codec.BOOL.optionalFieldOf("spawn_entities", true).forGetter(config -> config.spawnEntities),
					Codec.BOOL.optionalFieldOf("require_ground", true).forGetter(config -> config.requireGround),
					StructureProcessorType.LIST_CODEC.fieldOf("processors").forGetter(config -> config.processors))
			.apply(builder, StructureFeatureConfig::new));

	public static class Builder {
		private final List<ResourceLocation> templatePaths;
		private boolean doMirroring = true;
		private boolean doRotations = true;
		private boolean spawnEntities = true;
		private boolean requireGround = true;
		private final List<StructureProcessor> processors = new ObjectArrayList<>(1);

		public Builder(ResourceLocation... templatePaths) {
			this.templatePaths = Arrays.asList(templatePaths);
		}

		public Builder dontMirror() {
			this.doMirroring = false;

			return this;
		}

		public Builder dontRotate() {
			this.doRotations = false;

			return this;
		}

		public Builder ignoreEntities() {
			this.spawnEntities = false;

			return this;
		}

		public Builder spawnInMidair() {
			this.requireGround = false;

			return this;
		}

		public Builder withProcessors(StructureProcessor... processors) {
			this.processors.addAll(Arrays.asList(processors));

			return this;
		}

		public Builder withProcessors(StructureProcessorList processorList) {
			this.processors.addAll(processorList.list());

			return this;
		}

		public StructureFeatureConfig build() {
			return new StructureFeatureConfig(templatePaths, doMirroring, doRotations, spawnEntities, requireGround, Holder.direct(new StructureProcessorList(this.processors)));
		}
	}

	public StructureTemplate getTemplate(WorldGenLevel level, RandomSource rand) {
		ResourceLocation templatePath = templatePaths.get(rand.nextInt(templatePaths.size()));

		return level.getLevel().getStructureManager().get(templatePath).get();
	}

	public StructurePlaceSettings getPlacementSettings(RandomSource rand) {
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);
		StructurePlaceSettings settings = new StructurePlaceSettings();

		if (doMirroring)
			settings.setMirror(random.getRandomSelection(Mirror.values()));

		if (doRotations)
			settings.setRotation(random.getRandomSelection(Rotation.values()));

		settings.setIgnoreEntities(!spawnEntities);
		settings.setFinalizeEntities(spawnEntities);

		for (StructureProcessor processor : this.processors.value().list()) {
			settings.addProcessor(processor);
		}

		return settings;
	}
}