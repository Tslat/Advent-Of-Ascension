package net.tslat.aoa3.dimension.iromine;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.dimension.AoATeleporter;
import net.tslat.aoa3.dimension.AoAWorldProvider;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class WorldProviderIromine extends WorldProvider implements AoAWorldProvider {
	@Override
	public DimensionType getDimensionType() {
		return DimensionRegister.DIM_IROMINE;
	}

	@Override
	protected void init() {
		this.hasSkyLight = true;
		this.biomeProvider = DimensionRegister.WORLD_IROMINE.getBiomeProvider(world);
	}

	@Override
	public AoATeleporter getTeleporter(WorldServer fromWorld) {
		return new IromineTeleporter(fromWorld);
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return DimensionRegister.WORLD_IROMINE.getChunkGenerator(world, null);
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;
	}

	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Nullable
	@Override
	public String getSaveFolder() {
		return "AoA_Iromine";
	}

	@Override
	public boolean canDoLightning(Chunk chunk) {
		return false;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@Nullable
	@Override
	public MusicTicker.MusicType getMusicType() {
		return Enums.NULL_MUSIC;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public float getCloudHeight() {
		return -1000f;
	}

	@Nullable
	@Override
	public IRenderHandler getCloudRenderer() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float x, float z) {
		return new Vec3d(0, 0, 0);
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 1f;
	}
}
