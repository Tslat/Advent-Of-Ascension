package net.tslat.aoa3.worldgen.surfacebuilder.config;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Tuple;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;

public class TieredSurfaceBuilderConfig implements ISurfaceBuilderConfig {
	private final BlockState surfaceBlock;
	private final BlockState subsurfaceBlock;
	private final Tuple<BlockState, Integer>[] blockLayers;

	public TieredSurfaceBuilderConfig(BlockState surfaceBlock, BlockState subsurfaceBlock, Tuple<BlockState, Integer>... blockLayers) {
		this.surfaceBlock = surfaceBlock;
		this.subsurfaceBlock = subsurfaceBlock;
		this.blockLayers = blockLayers;
	}

	@Override
	public BlockState getTop() {
		return surfaceBlock;
	}

	@Override
	public BlockState getUnder() {
		return subsurfaceBlock;
	}

	public BlockState getBlockForLayer(int yPos) {
		for (Tuple<BlockState, Integer> layer : blockLayers) {
			if (yPos < layer.getB())
				return layer.getA();
		}

		return blockLayers[0].getA();
	}

	public static TieredSurfaceBuilderConfig deserialize(Dynamic<?> data) {
		BlockState surfaceBlock = data.get("surface_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState subsurfaceBlock = data.get("subsurface_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());

		return new TieredSurfaceBuilderConfig(surfaceBlock, subsurfaceBlock, new Tuple<BlockState, Integer>(subsurfaceBlock, 256));
	}
}
