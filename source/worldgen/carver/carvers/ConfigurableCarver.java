package net.tslat.aoa3.worldgen.carver.carvers;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.tslat.aoa3.worldgen.carver.config.ConfigurableCarverConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class ConfigurableCarver extends WorldCarver<ConfigurableCarverConfig> {
	public ConfigurableCarver(Codec<ConfigurableCarverConfig> codec) {
		super(codec, 256);

		this.replaceableBlocks = ImmutableSet.of();
		this.liquids = ImmutableSet.of();
	}

	protected boolean carveSlice(ConfigurableCarverConfig config, IChunk chunk, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, double horizontalRadius, double verticalRadius, BitSet carvingMask) {
		Random random = new Random(seed + (long)chunkX + (long)chunkZ);
		double chunkCenterX = chunkX * 16 + 8;
		double chunkCenterZ = chunkZ * 16 + 8;

		if (!(randOffsetXCoord < chunkCenterX - 16 - horizontalRadius * 2) && !(randOffsetZCoord < chunkCenterZ - 16 - horizontalRadius * 2) && !(randOffsetXCoord > chunkCenterX + 16 + horizontalRadius * 2) && !(randOffsetZCoord > chunkCenterZ + 16 + horizontalRadius * 2)) {
			int minXPos = Math.max(MathHelper.floor(randOffsetXCoord - horizontalRadius) - chunkX * 16 - 1, 0);
			int maxXPos = Math.min(MathHelper.floor(randOffsetXCoord + horizontalRadius) - chunkX * 16 + 1, 16);
			int minYPos = Math.max(MathHelper.floor(startY - verticalRadius) - 1, config.minHeight);
			int maxYPos = Math.min(MathHelper.floor(startY + verticalRadius) + 1, config.maxHeight - (int)verticalRadius);
			int minZPos = Math.max(MathHelper.floor(randOffsetZCoord - horizontalRadius) - chunkZ * 16 - 1, 0);
			int maxZPos = Math.min(MathHelper.floor(randOffsetZCoord + horizontalRadius) - chunkZ * 16 + 1, 16);

			if (blockedByFluid(config, chunk, chunkX, chunkZ, minXPos, maxXPos, minYPos, maxYPos, minZPos, maxZPos))
				return false;

			boolean carved = false;
			BlockPos.Mutable mutablePos1 = new BlockPos.Mutable();
			BlockPos.Mutable mutablePos2 = new BlockPos.Mutable();
			BlockPos.Mutable mutablePos3 = new BlockPos.Mutable();

			for(int x = minXPos; x < maxXPos; ++x) {
				int posX = x + chunkX * 16;
				double xRadius = ((double)posX + 0.5D - randOffsetXCoord) / horizontalRadius;

				for(int z = minZPos; z < maxZPos; ++z) {
					int posZ = z + chunkZ * 16;
					double zRadius = ((double)posZ + 0.5D - randOffsetZCoord) / horizontalRadius;

					if (!(xRadius * xRadius + zRadius * zRadius >= 1)) {
						MutableBoolean isSurface = new MutableBoolean(false);

						for(int y = maxYPos; y > minYPos; --y) {
							double yRadius = ((double)y - 0.5D - startY) / verticalRadius;

							if (!skip(xRadius, yRadius, zRadius, y))
								carved |= carveBlock(config, chunk, biomePos, carvingMask, random, mutablePos1, mutablePos2, mutablePos3, seaLevel, chunkX, chunkZ, posX, posZ, x, y, z, isSurface);
						}
					}
				}
			}

			return carved;
		}

		return false;
	}

	protected boolean carveBlock(ConfigurableCarverConfig config, IChunk chunk, Function<BlockPos, Biome> biomeFunction, BitSet carvingMask, Random rand, BlockPos.Mutable basePos, BlockPos.Mutable upPos, BlockPos.Mutable mutablePos, int p_230358_8_, int p_230358_9_, int p_230358_10_, int posX, int posZ, int bitPosX, int posY, int bitPosZ, MutableBoolean isSurface) {
		int maskBit = bitPosX | bitPosZ << 4 | posY << 8;

		if (carvingMask.get(maskBit)) {
			return false;
		}
		else {
			carvingMask.set(maskBit);
			basePos.set(posX, posY, posZ);

			BlockState state = chunk.getBlockState(basePos);
			BlockState upState = chunk.getBlockState(upPos.setWithOffset(basePos, Direction.UP));
			ISurfaceBuilderConfig biomeConfig = biomeFunction.apply(basePos).getGenerationSettings().getSurfaceBuilderConfig();
			Block surfaceBlock = biomeConfig.getTopMaterial().getBlock();

			if (state.getBlock() == surfaceBlock)
				isSurface.setTrue();

			if (!canCarveBlock(config, state, upState)) {
				return false;
			}
			else {
				if (posY < config.deepFloorHeight) {
					chunk.setBlockState(basePos, config.deepFillerBlock, false);
				}
				else {
					chunk.setBlockState(basePos, config.fillerBlock, false);

					if (isSurface.isTrue()) {
						mutablePos.setWithOffset(basePos, Direction.DOWN);

						if (chunk.getBlockState(mutablePos).getBlock() == biomeConfig.getUnderMaterial().getBlock())
							chunk.setBlockState(mutablePos, biomeConfig.getTopMaterial(), false);
					}
				}

				BlockPos floorPos = basePos.below();

				if (config.floorBlock != Blocks.CAVE_AIR.defaultBlockState() && chunk.getBlockState(floorPos).getMaterial() != Material.AIR)
					chunk.setBlockState(floorPos, config.floorBlock, false);

				return true;
			}
		}
	}

	protected boolean canCarveBlock(ConfigurableCarverConfig config, BlockState state, BlockState aboveState) {
		return config.carvableBlocks.contains(state.getBlock()) && aboveState.getFluidState().getType() == Fluids.EMPTY;
	}

	protected boolean blockedByFluid(ConfigurableCarverConfig config, IChunk chunk, int chunkX, int chunkZ, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
		BlockPos.Mutable checkPos = new BlockPos.Mutable();

		for(int posX = minX; posX < maxX; ++posX) {
			for(int posZ = minZ; posZ < maxZ; ++posZ) {
				for(int posY = minY - 1; posY <= maxY + 1; ++posY) {
					FluidState fluid = chunk.getFluidState(checkPos.set(posX + chunkX * 16, posY, posZ + chunkZ * 16));

					if (fluid.getType() != Fluids.EMPTY && !config.carvableFluids.contains(fluid))
						return true;

					if (posY != maxY + 1 && !isAtSideOfTunnel(minX, maxX, minZ, maxZ, posX, posZ))
						posY = maxY;
				}
			}
		}

		return false;
	}

	private boolean isAtSideOfTunnel(int minX, int maxX, int minZ, int maxZ, int posX, int posZ) {
		return posX == minX || posX == maxX - 1 || posZ == minZ || posZ == maxZ - 1;
	}

	@Override
	public boolean carve(IChunk chunk, Function<BlockPos, Biome> biomePos, Random rand, int seaLevel, int chunkXOffset, int chunkZOffset, int chunkX, int chunkZ, BitSet carvingMask, ConfigurableCarverConfig config) {
		int i = (getRange() * 2 - 1) * 16;
		int j = rand.nextInt(rand.nextInt(rand.nextInt(15) + 1) + 1);

		for(int k = 0; k < j; ++k) {
			double randOffsetXCoord = chunkXOffset * 16 + rand.nextInt(16);
			double startY = getStartYPos(rand);
			double randOffsetZCoord = chunkZOffset * 16 + rand.nextInt(16);
			int l = 1;

			if (rand.nextInt(4) == 0) {
				carveShallowSlice(config, chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, 1.0F + rand.nextFloat() * 6.0F * config.radiusModifier, 0.5D * config.radiusModifier, carvingMask);

				l += rand.nextInt(4);
			}

			for(int k1 = 0; k1 < l; ++k1) {
				float pitch = rand.nextFloat() * ((float)Math.PI * 2F);
				float f3 = (rand.nextFloat() - 0.5F) / 4.0F;
				float caveRadius = getCaveRadiusModifier(rand, config);
				int i1 = i - rand.nextInt(i / 4);

				genTunnel(config, chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, caveRadius, pitch, f3, 0, i1, 1, carvingMask);
			}
		}

		return true;
	}

	protected float getCaveRadiusModifier(Random rand, ConfigurableCarverConfig config) {
		float f = rand.nextFloat() * 2.0F + rand.nextFloat();

		if (rand.nextInt(10) == 0)
			f *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;

		return f * config.radiusModifier;
	}

	protected int getStartYPos(Random rand) {
		return rand.nextInt(rand.nextInt(120) + 8);
	}

	protected void carveShallowSlice(ConfigurableCarverConfig config, IChunk chunk, Function<BlockPos, Biome> biomeFunction, long p_227205_3_, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float radiusModifier, double verticalRadiusRatio, BitSet carvingMask) {
		double horizontalRadius = 1.5D + (double)(MathHelper.sin(((float)Math.PI / 2F)) * radiusModifier);
		double verticalRadius = horizontalRadius * verticalRadiusRatio;

		carveSlice(config, chunk, biomeFunction, p_227205_3_, seaLevel, chunkX, chunkZ, randOffsetXCoord + 1.0D, startY, randOffsetZCoord, horizontalRadius, verticalRadius, carvingMask);
	}

	protected void genTunnel(ConfigurableCarverConfig config, IChunk chunk, Function<BlockPos, Biome> biomeFunction, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float caveRadius, float pitch, float p_227206_16_, int p_227206_17_, int p_227206_18_, double p_227206_19_, BitSet carvingMask) {
		Random rand = new Random(seed);
		int i = rand.nextInt(p_227206_18_ / 2) + p_227206_18_ / 4;
		boolean flag = rand.nextInt(6) == 0;
		float f = 0;
		float f1 = 0;

		for(int j = p_227206_17_; j < p_227206_18_; ++j) {
			double d0 = 1.5D + (double)(MathHelper.sin((float)Math.PI * (float)j / (float)p_227206_18_) * caveRadius);
			double d1 = d0 * p_227206_19_;
			float f2 = MathHelper.cos(p_227206_16_);
			randOffsetXCoord += MathHelper.cos(pitch) * f2;
			startY += MathHelper.sin(p_227206_16_);
			randOffsetZCoord += MathHelper.sin(pitch) * f2;
			p_227206_16_ = p_227206_16_ * (flag ? 0.92F : 0.7F);
			p_227206_16_ = p_227206_16_ + f1 * 0.1F;
			pitch += f * 0.1F;
			f1 = f1 * 0.9F;
			f = f * 0.75F;
			f1 = f1 + (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 2.0F;
			f = f + (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 4.0F;

			if (j == i && caveRadius > 1.0F) {
				genTunnel(config, chunk, biomeFunction, rand.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, rand.nextFloat() * 0.5F + 0.5F, pitch - ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, carvingMask);
				genTunnel(config, chunk, biomeFunction, rand.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, rand.nextFloat() * 0.5F + 0.5F, pitch + ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, carvingMask);

				return;
			}

			if (rand.nextInt(4) != 0) {
				if (!canReach(chunkX, chunkZ, randOffsetXCoord, randOffsetZCoord, j, p_227206_18_, caveRadius))
					return;

				carveSlice(config, chunk, biomeFunction, seed, seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, d0, d1, carvingMask);
			}
		}
	}

	@Override
	public boolean isStartChunk(Random rand, int chunkX, int chunkZ, ConfigurableCarverConfig config) {
		return rand.nextFloat() <= config.carveChance;
	}

	@Override
	protected boolean skip(double xRadius, double yRadius, double zRadius, int posY) {
		return yRadius <= -0.7D || xRadius * xRadius + yRadius * yRadius + zRadius * zRadius >= 1.0D;
	}
}
