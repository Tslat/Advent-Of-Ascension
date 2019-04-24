package net.tslat.aoa3.dimension.creeponia;

import com.google.common.base.MoreObjects;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;
import net.tslat.aoa3.common.registration.BlockRegister;

import java.util.Random;

public class CaveGenCreeponia extends MapGenCaves {
	@Override
	protected void addTunnel(long tunnelSeed, int originChunkX, int originChunkZ, ChunkPrimer primer, double posX, double posY, double posZ, float p_180702_12_, float p_180702_13_, float p_180702_14_, int p_180702_15_, int p_180702_16_, double p_180702_17_) {
		double d0 = originChunkX * 16 + 8;
		double d1 = originChunkZ * 16 + 8;
		float f = 0.0F;
		float f1 = 0.0F;
		Random random = new Random(tunnelSeed);

		if (p_180702_16_ <= 0) {
			int i = this.range * 16 - 16;
			p_180702_16_ = i - random.nextInt(i / 4);
		}

		boolean flag2 = false;

		if (p_180702_15_ == -1) {
			p_180702_15_ = p_180702_16_ / 2;
			flag2 = true;
		}

		int j = random.nextInt(p_180702_16_ / 2) + p_180702_16_ / 4;
		boolean flag = random.nextInt(6) == 0;

		while (p_180702_15_ < p_180702_16_) {
			double d2 = 3.5D + (double)MathHelper.sin((float)p_180702_15_ * (float)Math.PI / (float)p_180702_16_) * p_180702_12_ * 2;
			double d3 = d2 * p_180702_17_;
			float f2 = MathHelper.cos(p_180702_14_);
			float f3 = MathHelper.sin(p_180702_14_);
			posX += (double)(MathHelper.cos(p_180702_13_) * f2);
			posY += (double)f3;
			posZ += (double)(MathHelper.sin(p_180702_13_) * f2);

			if (flag) {
				p_180702_14_ = p_180702_14_ * 0.92F;
			}
			else {
				p_180702_14_ = p_180702_14_ * 0.7F;
			}

			p_180702_14_ = p_180702_14_ + f1 * 0.1F;
			p_180702_13_ += f * 0.1F;
			f1 = f1 * 0.9F;
			f = f * 0.75F;
			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (!flag2 && p_180702_15_ == j && p_180702_12_ > 1.0F && p_180702_16_ > 0) {
				this.addTunnel(random.nextLong(), originChunkX, originChunkZ, primer, posX, posY, posZ, random.nextFloat() * 0.5F + 0.5F, p_180702_13_ - ((float)Math.PI / 2F), p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
				this.addTunnel(random.nextLong(), originChunkX, originChunkZ, primer, posX, posY, posZ, random.nextFloat() * 0.5F + 0.5F, p_180702_13_ + ((float)Math.PI / 2F), p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
				return;
			}

			if (flag2 || random.nextInt(4) != 0) {
				double d4 = posX - d0;
				double d5 = posZ - d1;
				double d6 = (double)(p_180702_16_ - p_180702_15_);
				double d7 = (double)(p_180702_12_ + 2.0F + 16.0F);

				if (d4 * d4 + d5 * d5 - d6 * d6 > d7 * d7) {
					return;
				}

				if (posX >= d0 - 16.0D - d2 * 2.0D && posZ >= d1 - 16.0D - d2 * 2.0D && posX <= d0 + 16.0D + d2 * 2.0D && posZ <= d1 + 16.0D + d2 * 2.0D) {
					int k2 = MathHelper.floor(posX - d2) - originChunkX * 16 - 1;
					int k = MathHelper.floor(posX + d2) - originChunkX * 16 + 1;
					int l2 = MathHelper.floor(posY - d3) - 1;
					int l = MathHelper.floor(posY + d3) + 1;
					int i3 = MathHelper.floor(posZ - d2) - originChunkZ * 16 - 1;
					int i1 = MathHelper.floor(posZ + d2) - originChunkZ * 16 + 1;

					if (k2 < 0) {
						k2 = 0;
					}

					if (k > 16) {
						k = 16;
					}

					if (l2 < 1) {
						l2 = 1;
					}

					if (l > 248) {
						l = 248;
					}

					if (i3 < 0) {
						i3 = 0;
					}

					if (i1 > 16) {
						i1 = 16;
					}

					boolean flag3 = false;

					for (int j1 = k2; !flag3 && j1 < k; ++j1) {
						for (int k1 = i3; !flag3 && k1 < i1; ++k1) {
							for (int l1 = l + 1; !flag3 && l1 >= l2 - 1; --l1) {
								if (l1 >= 0 && l1 < 256) {
									if (isOceanBlock(primer, j1, l1, k1, originChunkX, originChunkZ)) {
										flag3 = true;
									}

									if (l1 != l2 - 1 && j1 != k2 && j1 != k - 1 && k1 != i3 && k1 != i1 - 1) {
										l1 = l2;
									}
								}
							}
						}
					}

					if (!flag3) {
						BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

						for (int j3 = k2; j3 < k; ++j3) {
							double d10 = ((double)(j3 + originChunkX * 16) + 0.5D - posX) / d2;

							for (int i2 = i3; i2 < i1; ++i2) {
								double d8 = ((double)(i2 + originChunkZ * 16) + 0.5D - posZ) / d2;
								boolean flag1 = false;

								if (d10 * d10 + d8 * d8 < 1.0D) {
									for (int j2 = l; j2 > l2; --j2) {
										double d9 = ((double)(j2 - 1) + 0.5D - posY) / d3;

										if (d9 > -0.7D && d10 * d10 + d9 * d9 + d8 * d8 < 1.0D) {
											IBlockState iblockstate1 = primer.getBlockState(j3, j2, i2);
											IBlockState iblockstate2 = MoreObjects.firstNonNull(primer.getBlockState(j3, j2 + 1, i2), BLK_AIR);

											if (isTopBlock(primer, j3, j2, i2, originChunkX, originChunkZ)) {
												flag1 = true;
											}

											digBlock(primer, j3, j2, i2, originChunkX, originChunkZ, flag1, iblockstate1, iblockstate2);
										}
									}
								}
							}
						}

						if (flag2) {
							break;
						}
					}
				}
			}

			++p_180702_15_;
		}
	}

	@Override
	protected boolean canReplaceBlock(IBlockState targetBlock, IBlockState replacementBlock) {
		Block block = targetBlock.getBlock();

		return block == BlockRegister.stonePrimed || block == BlockRegister.stoneUnstable || block == BlockRegister.stoneCreep || block == BlockRegister.lightCreepCrystal;
	}

	private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
		Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState state = data.getBlockState(x, y, z);

		return state.getBlock() == biome.topBlock;
	}

	@Override
	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
		net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState top = biome.topBlock;
		IBlockState filler = biome.fillerBlock;

		if (canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()) {
			if (y <= 5) {
				data.setBlockState(x, y, z, BlockRegister.stonePrimed.getDefaultState());
			}
			else {
				data.setBlockState(x, y, z, BLK_AIR);

				if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
					data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
			}
		}
	}
}
