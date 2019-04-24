package net.tslat.aoa3.dimension.iromine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;
import net.tslat.aoa3.common.registration.BlockRegister;

public class CaveGenIromine extends MapGenCaves {
	@Override
	protected boolean canReplaceBlock(IBlockState targetBlock, IBlockState replacementBlock) {
		return targetBlock.getBlock() == BlockRegister.stoneIromine;
	}

	@Override
	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
		net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState top = biome.topBlock;
		IBlockState filler = biome.fillerBlock;

		if (canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()) {
			if (y < 10) {
				data.setBlockState(x, y, z, BLK_LAVA);
			}
			else {
				data.setBlockState(x, y, z, BLK_AIR);

				if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
					data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
			}
		}
	}
}
