package net.tslat.aoa3.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class WorldGenerator {
	private final boolean doBlockNotify;
	protected ChunkPos chunkPos = null;

	public WorldGenerator() {
		this(false);
	}

	public WorldGenerator(boolean notify) {
		this.doBlockNotify = notify;
	}

	public WorldGenerator setChunkPos(ChunkPos pos) {
		this.chunkPos = pos;

		return this;
	}

	public abstract boolean generate(IWorld world, Random rand, BlockPos position);

	public void setDecorationDefaults() {}

	protected void setBlockAndNotifyAdequately(IWorld world, BlockPos pos, BlockState state) {
		if (chunkPos != null) {
			if (pos.getX() < chunkPos.getXStart() || pos.getX() > chunkPos.getXEnd() || pos.getZ() < chunkPos.getZStart() || pos.getZ() > chunkPos.getZEnd())
				return;
		}

		world.setBlockState(pos, state, 2);
	}

	@Nullable
	protected TileEntity getTileEntity(IWorld world, BlockPos pos) {
		if (chunkPos != null) {
			if (pos.getX() < chunkPos.getXStart() || pos.getX() > chunkPos.getXEnd() || pos.getZ() < chunkPos.getZStart() || pos.getZ() > chunkPos.getZEnd())
				return null;
		}

		return world.getTileEntity(pos);
	}

	@Nullable
	protected void spawnEntity(IWorld world, Entity entity, double x, double y, double z) {
		if (chunkPos != null) {
			if (x < chunkPos.getXStart() || x > chunkPos.getXEnd() || z < chunkPos.getZStart() || z > chunkPos.getZEnd())
				return;
		}

		entity.setPositionAndRotation(x, y, z, 0, 0);
		world.addEntity(entity);
	}
}
