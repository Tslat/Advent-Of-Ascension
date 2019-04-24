package net.nevermine.dimension.precasia;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.nevermine.izer.Dimensionizer;

public class WorldProviderPc extends WorldProvider {

	@Override
	public String getDimensionName() {
		return ("DimensionPrecasia");
	}

	@Override
	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerPrecasia(worldObj.getSeed(), terrainType);
		dimensionId = Dimensionizer.precasiaId;

	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderPrecasia(worldObj, worldObj.getSeed());
	}

	@Override
	public float getCloudHeight() {
		return 128.0F;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public String getSaveFolder() {
		return "Precasia";
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;

	}

}
