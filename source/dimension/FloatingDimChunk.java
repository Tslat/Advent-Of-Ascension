package net.tslat.aoa3.dimension;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class FloatingDimChunk extends Chunk {
	private boolean isEmpty = false;

	public FloatingDimChunk(World world, int x, int z) {
		super(world, x, z);

		this.isEmpty = true;
	}

	public FloatingDimChunk(World worldIn, ChunkPrimer primer, int x, int z) {
		super(worldIn, primer, x, z);
	}

	private int getBlockLightOpacity(int posX, int posY, int posZ) {
		IBlockState blockState = getBlockState(posX, posY, posZ);

		return !isLoaded() ? blockState.getLightOpacity() : blockState.getLightOpacity(getWorld(), new BlockPos(this.x << 4 | posX & 15, posY, this.z << 4 | posZ & 15));
	}

	@Override
	public void generateSkylightMap() {
		if (!isEmpty) {
			int i = this.getTopFilledSegment();
			int minHeight = Integer.MAX_VALUE;

			for (int j = 0; j < 16; ++j) {
				for (int k = 0; k < 16; ++k) {
					for (int l = i + 16; l > 0; --l) {
						if (getBlockLightOpacity(j, l - 1, k) != 0) {
							getHeightMap()[k << 4 | j] = l;

							if (l < minHeight)
								minHeight = l;

							break;
						}
					}

					ObfuscationReflectionHelper.<Chunk, Integer>setPrivateValue(Chunk.class, this, minHeight, "heightMapMinimum", "field_82912_p");

					if (getWorld().provider.hasSkyLight()) {
						int k1 = 15;
						int i1 = i + 16 - 1;

						do {
							int j1 = getBlockLightOpacity(j, i1, k);

							if (j1 == 0 && k1 != 15)
								j1 = 1;

							k1 -= j1;

							if (k1 > 0) {
								ExtendedBlockStorage extendedblockstorage = getBlockStorageArray()[i1 >> 4];

								if (extendedblockstorage != NULL_BLOCK_STORAGE) {
									extendedblockstorage.setSkyLight(j, i1 & 15, k, k1);
									getWorld().notifyLightSet(new BlockPos((this.x << 4) + j, i1, (this.z << 4) + k));
								}
							}

							--i1;

						} while (i1 > 0 && k1 > 0 && i1 >= minHeight);
					}
				}
			}

			markDirty();
		}
	}
}
