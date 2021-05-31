package net.tslat.aoa3.worldgen.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.*;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class StructureFeatureConfig implements IFeatureConfig {
	public static final Codec<StructureFeatureConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			ResourceLocation.CODEC.listOf().fieldOf("templates").forGetter(config -> config.templatePaths),
			Codec.BOOL.optionalFieldOf("random_mirroring", true).forGetter(config -> config.doMirroring),
			Codec.BOOL.optionalFieldOf("random_rotation", true).forGetter(config -> config.doRotations),
			Codec.BOOL.optionalFieldOf("spawn_entities", true).forGetter(config -> config.spawnEntities),
			Codec.BOOL.optionalFieldOf("require_ground", true).forGetter(config -> config.requireGround),
			IStructureProcessorType.LIST_CODEC.fieldOf("processors").forGetter(config -> config.processors))
			.apply(builder, StructureFeatureConfig::new));

	public final List<ResourceLocation> templatePaths;
	public final boolean doMirroring;
	public final boolean doRotations;
	public final boolean spawnEntities;
	public final boolean requireGround;
	public final Supplier<StructureProcessorList> processors;

	public StructureFeatureConfig(List<ResourceLocation> templatePaths, boolean doMirroring, boolean doRotations, boolean spawnEntities, boolean requireGround, Supplier<StructureProcessorList> processors) {
		this.templatePaths = templatePaths;
		this.doMirroring = doMirroring;
		this.doRotations = doRotations;
		this.spawnEntities = spawnEntities;
		this.requireGround = requireGround;
		this.processors = processors;
	}

	public static class Builder {
		private final List<ResourceLocation> templatePaths;
		private boolean doMirroring = true;
		private boolean doRotations = true;
		private boolean spawnEntities = true;
		private boolean requireGround = true;
		private final ArrayList<StructureProcessor> processors = new ArrayList<StructureProcessor>(1);

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
			return new StructureFeatureConfig(templatePaths, doMirroring, doRotations, spawnEntities, requireGround, () -> new StructureProcessorList(this.processors));
		}
	}

	public Template getTemplate(Random rand) {
		ResourceLocation templatePath = templatePaths.get(rand.nextInt(templatePaths.size()));

		return ServerLifecycleHooks.getCurrentServer().getStructureManager().get(templatePath);
	}

	public PlacementSettings getPlacementSettings(Random rand) {
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);
		PlacementSettings settings = new PlacementSettings();

		if (doMirroring)
			settings.setMirror(random.getRandomSelection(Mirror.values()));

		if (doRotations)
			settings.setRotation(random.getRandomSelection(Rotation.values()));

		settings.setIgnoreEntities(!spawnEntities);

		for (StructureProcessor processor : this.processors.get().list()) {
			settings.addProcessor(processor);
		}

		return settings;
	}
}