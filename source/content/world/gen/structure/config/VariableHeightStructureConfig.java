package net.tslat.aoa3.content.world.gen.structure.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;

public class VariableHeightStructureConfig extends JigsawConfiguration {
	public static final Codec<VariableHeightStructureConfig> CODEC = RecordCodecBuilder.create(codec -> codec.group(
					StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
					Codec.intRange(1, 50).fieldOf("size").forGetter(JigsawConfiguration::maxDepth),
					HeightProvider.CODEC.fieldOf("height").forGetter(VariableHeightStructureConfig::getHeight),
					Codec.BOOL.fieldOf("place_on_ground").forGetter(VariableHeightStructureConfig::dropToNearestFloor),
					Codec.BOOL.fieldOf("ignore_obstructions").forGetter(VariableHeightStructureConfig::ignoreObstructions))
			.apply(codec, VariableHeightStructureConfig::new));

	private final HeightProvider height;
	private final boolean dropToNearestFloor;
	private final boolean ignoreObstructions;

	public VariableHeightStructureConfig(Holder<StructureTemplatePool> startPool, int maxPieces, HeightProvider height, boolean dropToNearestFloor, boolean ignoreObstructions) {
		super(startPool, maxPieces);

		this.height = height;
		this.dropToNearestFloor = dropToNearestFloor;
		this.ignoreObstructions = ignoreObstructions;
	}

	public HeightProvider getHeight() {
		return this.height;
	}

	public boolean dropToNearestFloor() {
		return this.dropToNearestFloor;
	}

	public boolean ignoreObstructions() {
		return this.ignoreObstructions;
	}

	public static class Builder {
		private final HeightProvider height;

		private boolean dropToNearestFloor = false;
		private boolean ignoreObstructions = false;
		private int maxPieces = 10;

		public Builder(HeightProvider height) {
			this.height = height;
		}

		public Builder dropToNearestFloor() {
			this.dropToNearestFloor = true;

			return this;
		}

		public Builder spawnInsideBlocksIfRequired() {
			this.ignoreObstructions = true;

			return this;
		}

		public Builder maxPieces(int maxPieces) {
			this.maxPieces = maxPieces;

			return this;
		}

		public VariableHeightStructureConfig get() {
			return new VariableHeightStructureConfig(Holder.direct(new StructureTemplatePool(new ResourceLocation(""), new ResourceLocation(""), List.of(), StructureTemplatePool.Projection.RIGID)), this.maxPieces, this.height, this.dropToNearestFloor, this.ignoreObstructions);
		}
	}
}
