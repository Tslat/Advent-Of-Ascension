package net.nevermine.dimension.candyland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.nevermine.izer.Dimensionizer;

public class WorldProviderCd extends WorldProvider {
	@Override
	public String getDimensionName() {
		return ("DimensionCandyland");
	}

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerCandyland(worldObj.getSeed(), terrainType);
		this.dimensionId = Dimensionizer.candylandId;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float par1, float par2) {
		float f2 = MathHelper.sin(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;
		if (f2 < 1.5F) {
			f2 = 1.5F;
		}
		if (f2 > 5.0F) {
			f2 = 5.0F;
		}
		float f3;
		float f4;
		float f5;
		f3 = 1.7F * f2;
		f4 = 0.5F * f2;
		f5 = 0.5F * f2;
		return Vec3.createVectorHelper(f3, f4, f5);
	}

	@Override
	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderCandyland(this.worldObj, this.worldObj.getSeed());
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
		return "Candyland";
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long var1, float var3) {
		return 0.0F;
	}
}
