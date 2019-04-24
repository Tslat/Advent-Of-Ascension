package net.nevermine.dimension.celeve;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.nevermine.dimension.DimensionOrganizer;
import net.nevermine.izer.Dimensionizer;

public class WorldProviderCl extends WorldProvider {
	float f3;
	float f4;
	float f5;

	public WorldProviderCl() {
		f3 = 0.214f;
		f4 = 0.421f;
		f5 = 0.23f;
	}

	public String getDimensionName() {
		return "DimensionCeleve";
	}

	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerHell(DimensionOrganizer.Celeve, 0.0f);
		dimensionId = Dimensionizer.celeveId;
	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderCeleve(worldObj, worldObj.getSeed());
	}

	public float getCloudHeight() {
		return 128.0f;
	}

	public boolean canRespawnHere() {
		return false;
	}

	public float calculateCelestialAngle(final long var1, final float var3) {
		return 0.0f;
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
		final float f3 = 0.1f * f2;
		final float f4 = 0.0f * f2;
		final float f5 = 0.0f * f2;
		return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
	}

	public boolean doesXZShowFog(final int par1, final int par2) {
		return true;
	}

	public String getSaveFolder() {
		return "Celeve";
	}

	public boolean isSurfaceWorld() {
		return false;
	}
}
