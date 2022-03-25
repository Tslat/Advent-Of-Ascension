package net.tslat.aoa3.content.world.gen.carver.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.carver.ICarverConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigurableCarverConfig implements ICarverConfig {
	public static final Codec<ConfigurableCarverConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			Codec.floatRange(0, 1).optionalFieldOf("carve_chance", 0.14285715f).forGetter(config -> config.carveChance),
			Registry.BLOCK.listOf().fieldOf("carvable_blocks").forGetter(config -> config.carvableBlocks),
			Registry.FLUID.listOf().fieldOf("carvable_fluids").forGetter(config -> config.carvableFluids),
			Codec.intRange(0, 256).optionalFieldOf("deep_floor_height", 11).forGetter(config -> config.deepFloorHeight),
			Codec.intRange(0, 256).optionalFieldOf("min_height", 256).forGetter(config -> config.minHeight),
			Codec.intRange(0, 256).optionalFieldOf("max_height", 256).forGetter(config -> config.maxHeight),
			Codec.FLOAT.optionalFieldOf("radius_modifier", 1f).forGetter(config -> config.radiusModifier),
			BlockState.CODEC.optionalFieldOf("deep_filler_block", Blocks.LAVA.defaultBlockState()).forGetter(config -> config.deepFillerBlock),
			BlockState.CODEC.optionalFieldOf("filler_block", Blocks.CAVE_AIR.defaultBlockState()).forGetter(config -> config.fillerBlock),
			BlockState.CODEC.optionalFieldOf("carved_floor_block", Blocks.CAVE_AIR.defaultBlockState()).forGetter(config -> config.floorBlock)
	).apply(builder, ConfigurableCarverConfig::new));

	public final float carveChance;
	public final List<Block> carvableBlocks;
	public final List<Fluid> carvableFluids;
	public final int deepFloorHeight;
	public final int minHeight;
	public final int maxHeight;
	public final float radiusModifier;
	public final BlockState deepFillerBlock;
	public final BlockState fillerBlock;
	public final BlockState floorBlock;

	public ConfigurableCarverConfig(float carveChance, List<Block> carvableBlocks, List<Fluid> carvableFluids, int deepFloorHeight, int minHeight, int maxHeight, float radiusModifier, BlockState deepFillerBlock, BlockState fillerBlock, BlockState floorBlock) {
		this.carveChance = carveChance;
		this.carvableBlocks = carvableBlocks;
		this.carvableFluids = carvableFluids;
		this.deepFloorHeight = deepFloorHeight;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.radiusModifier = radiusModifier;
		this.deepFillerBlock = deepFillerBlock;
		this.fillerBlock = fillerBlock;
		this.floorBlock = floorBlock;
	}

	public static class Builder {
		private float carveChance = 0.14285715f;
		private int deepFloorHeight = 11;
		private int minHeight = 3;
		private int maxHeight = 256;
		private float radiusModifier = 1;

		private final List<Block> carvableBlocks = new ArrayList<Block>();
		private final List<Fluid> carvableFluids = new ArrayList<Fluid>();

		private BlockState deepFloorBlock = Blocks.LAVA.defaultBlockState();
		private BlockState fillerBlock = Blocks.CAVE_AIR.defaultBlockState();
		private BlockState floorBlock = Blocks.CAVE_AIR.defaultBlockState();

		public Builder carveChance(float chancePerChunk) {
			this.carveChance = chancePerChunk;

			return this;
		}

		public Builder deepFloorHeight(int yPos) {
			this.deepFloorHeight = yPos;

			return this;
		}

		public Builder minCarveHeight(int minHeight) {
			this.minHeight = minHeight;

			return this;
		}

		public Builder maxCarveHeight(int maxHeight) {
			this.maxHeight = maxHeight;

			return this;
		}

		public Builder radiusMod(float radiusModifier) {
			this.radiusModifier = radiusModifier;

			return this;
		}

		public Builder deepFloorBlock(BlockState block) {
			this.deepFloorBlock = block;

			return this;
		}

		public Builder fillerBlock(BlockState block) {
			this.fillerBlock = block;

			return this;
		}

		public Builder floorBlock(BlockState block) {
			this.floorBlock = block;

			return this;
		}

		public Builder carveBlocks(Block... blocks) {
			this.carvableBlocks.addAll(Arrays.asList(blocks));

			return this;
		}

		public Builder carveFluids(Fluid... fluids) {
			this.carvableFluids.addAll(Arrays.asList(fluids));

			return this;
		}

		public ConfigurableCarverConfig build() {
			return new ConfigurableCarverConfig(carveChance, carvableBlocks, carvableFluids, deepFloorHeight, minHeight, maxHeight, radiusModifier, deepFloorBlock, fillerBlock, floorBlock);
		}
	}
}
