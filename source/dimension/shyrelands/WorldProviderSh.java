package net.nevermine.dimension.shyrelands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.nevermine.dimension.DimensionOrganizer;
import net.nevermine.izer.Dimensionizer;

public class WorldProviderSh extends WorldProvider {
	public String getDimensionName() {
		return "DimensionShyrelands";
	}

	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerHell(DimensionOrganizer.Shyrelands, 1.0F);
		dimensionId = Dimensionizer.shyrelandsId;
	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderShyrelands(worldObj, worldObj.getSeed());
	}

	public float getCloudHeight() {
		return 128.0F;
	}

	public boolean canRespawnHere() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2) {
		float f2 = MathHelper.cos(par1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;

		if (f2 < 1.5F) {
			f2 = 1.5F;
		}

		if (f2 > 5.0F) {
			f2 = 5.0F;
		}

		float f3 = 0.7529412F;
		float f4 = 0.84705883F;
		float f5 = 1.0F;
		f3 *= (f2 * 0.94F + 0.06F);
		f4 *= (f2 * 0.94F + 0.06F);
		f5 *= (f2 * 0.91F + 0.09F);
		f3 = 0.414F * f2;
		f4 = 0.421F * f2;
		f5 = 0.13F * f2;
		return Vec3.createVectorHelper(f3, f4, f5);
	}

	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}

	public String getSaveFolder() {
		return "Shyrelands";
	}

	public boolean isSurfaceWorld() {
		return false;
	}

	public float calculateCelestialAngle(long var1, float var3) {
		return 0.0F;
	}
}
