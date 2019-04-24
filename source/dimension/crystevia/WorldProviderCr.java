package net.nevermine.dimension.crystevia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.nevermine.dimension.DimensionOrganizer;
import net.nevermine.izer.Dimensionizer;

public class WorldProviderCr extends WorldProvider {
	public String getDimensionName() {
		return "DimensionCrystevia";
	}

	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerHell(DimensionOrganizer.Crystevia, 0.0f);
		dimensionId = Dimensionizer.crysteviaId;
		hasNoSky = true;
		isHellWorld = true;
	}

	protected void generateLightBrightnessTable() {
		final float f = 0.1f;
		for (int i = 0; i <= 15; ++i) {
			final float f2 = 1.0f - i / 15.0f;
			lightBrightnessTable[i] = (1.0f - f2) / (f2 * 3.0f + 1.0f) * (1.0f - f) + f;
		}
	}

	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(final float par1, final float par2) {
		float f2 = MathHelper.cos(par1 * 3.1415927f * 2.0f) * 2.0f + 0.5f;
		if (f2 < 1.5f) {
			f2 = 1.5f;
		}
		if (f2 > 5.0f) {
			f2 = 5.0f;
		}
		final float f3 = 0.3f * f2;
		final float f4 = 0.0f * f2;
		final float f5 = 0.3f * f2;
		return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
	}

	public boolean doesXZShowFog(final int par1, final int par2) {
		return true;
	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderCrystevia(worldObj, worldObj.getSeed());
	}

	public float getCloudHeight() {
		return 128.0f;
	}

	public boolean canRespawnHere() {
		return false;
	}

	public String getSaveFolder() {
		return "Crystevia";
	}

	public boolean isSurfaceWorld() {
		return false;
	}

	public float calculateCelestialAngle(final long var1, final float var3) {
		return 0.4f;
	}
}
