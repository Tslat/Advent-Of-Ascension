package net.tslat.aoa3.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.function.Predicate;

public class WorldGenMinable extends WorldGenerator {
	private final BlockState oreBlock;
	private final int numberOfBlocks;
	private final Predicate<BlockState> predicate;

	public WorldGenMinable(BlockState state, int blockCount, Predicate<BlockState> predicate) {
		this.oreBlock = state;
		this.numberOfBlocks = blockCount;
		this.predicate = predicate;
	}

	public boolean generate(IWorld world, Random rand, BlockPos position) {
		float angle = rand.nextFloat() * (float)Math.PI;
		double minX = (float)(position.getX() + 8) + MathHelper.sin(angle) * (float)this.numberOfBlocks / 8.0F;
		double maxX = (float)(position.getX() + 8) - MathHelper.sin(angle) * (float)this.numberOfBlocks / 8.0F;
		double minZ = (float)(position.getZ() + 8) + MathHelper.cos(angle) * (float)this.numberOfBlocks / 8.0F;
		double maxZ = (float)(position.getZ() + 8) - MathHelper.cos(angle) * (float)this.numberOfBlocks / 8.0F;
		double minY = position.getY() + rand.nextInt(3) - 2;
		double maxY = position.getY() + rand.nextInt(3) - 2;

		for (int i = 0; i < this.numberOfBlocks; ++i) {
			float veinProgress = (float)i / (float)this.numberOfBlocks;
			double xLerp = minX + (maxX - minX) * (double)veinProgress;
			double yLerp = minY + (maxY - minY) * (double)veinProgress;
			double zLerp = minZ + (maxZ - minZ) * (double)veinProgress;
			double reachMod = rand.nextDouble() * (double)this.numberOfBlocks / 16.0D;
			double lateralSpan = (double)(MathHelper.sin((float)Math.PI * veinProgress) + 1.0F) * reachMod + 1.0D;
			double verticalSpan = (double)(MathHelper.sin((float)Math.PI * veinProgress) + 1.0F) * reachMod + 1.0D;
			int startX = MathHelper.floor(xLerp - lateralSpan / 2.0D);
			int startY = MathHelper.floor(yLerp - verticalSpan / 2.0D);
			int startZ = MathHelper.floor(zLerp - lateralSpan / 2.0D);
			int endX = MathHelper.floor(xLerp + lateralSpan / 2.0D);
			int endY = MathHelper.floor(yLerp + verticalSpan / 2.0D);
			int endZ = MathHelper.floor(zLerp + lateralSpan / 2.0D);

			for (int x = startX; x <= endX; ++x) {
				double xDist = ((double)x + 0.5D - xLerp) / (lateralSpan / 2.0D);

				if (xDist * xDist < 1.0D) {
					for (int y = startY; y <= endY; ++y) {
						double yDist = ((double)y + 0.5D - yLerp) / (verticalSpan / 2.0D);

						if (xDist * xDist + yDist * yDist < 1.0D) {
							for (int z = startZ; z <= endZ; ++z) {
								double zDist = ((double)z + 0.5D - zLerp) / (lateralSpan / 2.0D);

								if (xDist * xDist + yDist * yDist + zDist * zDist < 1.0D) {
									BlockPos pos = new BlockPos(x, y, z);

									BlockState state = world.getBlockState(pos);

									if (state.getBlock().isReplaceableOreGen(state, world, pos, this.predicate))
										world.setBlockState(pos, this.oreBlock, 2);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
