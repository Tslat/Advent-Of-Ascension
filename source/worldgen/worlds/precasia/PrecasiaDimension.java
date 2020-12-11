package net.tslat.aoa3.worldgen.worlds.precasia;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.tslat.aoa3.common.registration.AoABiomes;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.worldgen.chunkgenerator.GenericAoAChunkBuilder;

import javax.annotation.Nullable;

public class PrecasiaDimension extends Dimension {
	public PrecasiaDimension(World world, DimensionType dimType) {
		super(world, dimType, 0);
	}

	@Override
	public ChunkGenerator<? extends GenerationSettings> createChunkGenerator() {
		ChunkGeneratorType<GenerationSettings, GenericAoAChunkBuilder> chunkGeneratorType = AoAWorldGen.ChunkGeneratorTypes.GENERIC.get();
		BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeProviderType = BiomeProviderType.FIXED;
		BiomeProvider biomeProvider = biomeProviderType.create(biomeProviderType.createSettings(world.getWorldInfo()).setBiome(AoABiomes.PRECASIA.get()));
		BlockState fillerBlock = AoABlocks.PRECASIAN_LOWER_ROCK.get().getDefaultState();
		BlockState liquidBlock = Blocks.AIR.getDefaultState();
		GenerationSettings settings = chunkGeneratorType.createSettings();

		settings.setDefaultBlock(fillerBlock);
		settings.setDefaultFluid(liquidBlock);

		return chunkGeneratorType.create(world, biomeProvider, settings);
	}

	@Nullable
	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
		return null;
	}

	@Nullable
	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		return null;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		float solarAngle = MathHelper.cos(celestialAngle * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
		solarAngle = MathHelper.clamp(solarAngle, 0.0F, 1.0F);
		float red = 0.7529412F;
		float green = 0.84705883F;
		float blue = 1.0F;
		red = red * (solarAngle * 0.94F + 0.06F);
		green = green * (solarAngle * 0.94F + 0.06F);
		blue = blue * (solarAngle * 0.91F + 0.09F);

		return new Vec3d(red, green, blue);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		double dayPercent = MathHelper.frac((double)worldTime / 24000.0D - 0.25D);
		double solarAngle = 0.5D - Math.cos(dayPercent * Math.PI) / 2.0D;

		return (float)(dayPercent * 2.0D + solarAngle) / 3.0F;
	}

	@Override
	public float getCloudHeight() {
		return 200f;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;
	}

	@Override
	public boolean canDoLightning(Chunk chunk) {
		return false;
	}
}
