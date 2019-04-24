package net.nevermine.dimension.labricon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.nevermine.dimension.DimensionOrganizer;
import net.nevermine.izer.Dimensionizer;

public class WorldProviderLc extends WorldProvider {
	public String getDimensionName() {
		return "DimensionLabricon";
	}

	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerHell(DimensionOrganizer.Labricon, 1.0f);
		dimensionId = Dimensionizer.labriconId;
		hasNoSky = true;
	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderLabricon(worldObj, worldObj.getSeed());
	}

	public float getCloudHeight() {
		return 128.0f;
	}

	public boolean canRespawnHere() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(final float par1, final float par2) {
		float f2 = MathHelper.cos(par1 * 3.1415927f * 2.0f) * 2.0f + 0.5f;
		if (f2 < 0.0f) {
			f2 = 0.0f;
		}
		if (f2 > 0.23f) {
			f2 = 0.23f;
		}
		float f3 = 0.7529412f;
		float f4 = 0.84705883f;
		float f5 = 1.0f;
		f3 *= f2 * 0.94f + 0.06f;
		f4 *= f2 * 0.94f + 0.06f;
		f5 *= f2 * 0.91f + 0.09f;
		f3 = 0.514f * f2;
		f4 = 0.621f * f2;
		f5 = 0.03f * f2;
		return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
	}

	public boolean doesXZShowFog(final int par1, final int par2) {
		return false;
	}

	public String getSaveFolder() {
		return "Labricon";
	}

	public boolean isSurfaceWorld() {
		return false;
	}

	public float calculateCelestialAngle(final long var1, final float var3) {
		return 0.45f;
	}
}
