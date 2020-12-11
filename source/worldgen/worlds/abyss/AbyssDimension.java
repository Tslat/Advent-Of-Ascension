package net.tslat.aoa3.worldgen.worlds.abyss;

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
import net.minecraftforge.client.IRenderHandler;
import net.tslat.aoa3.common.registration.AoABiomes;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.worldgen.chunkgenerator.GenericAoAChunkBuilder;

import javax.annotation.Nullable;

public class AbyssDimension extends Dimension {
	public AbyssDimension(World world, DimensionType dimType) {
		super(world, dimType, 0);
	}

	@Override
	public ChunkGenerator<? extends GenerationSettings> createChunkGenerator() {
		ChunkGeneratorType<GenerationSettings, GenericAoAChunkBuilder> chunkGeneratorType = AoAWorldGen.ChunkGeneratorTypes.GENERIC.get();
		BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeProviderType = BiomeProviderType.FIXED;
		BiomeProvider biomeProvider = biomeProviderType.create(biomeProviderType.createSettings(world.getWorldInfo()).setBiome(AoABiomes.ABYSS.get()));
		BlockState fillerBlock = AoABlocks.ABYSSAL_STONE.get().getDefaultState();
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
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.4f;
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
		float angle = MathHelper.clamp(MathHelper.cos(celestialAngle * (float)Math.PI * 2.0F) * 2.0F + 0.5F, 1.5f, 5f);
		float red = 0.414F * angle;
		float green = 0.021F * angle;
		float blue = 0.03F * angle; // TODO check & convert these to RGB

		return new Vec3d(red, green, blue);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}

	@Nullable
	@Override
	public IRenderHandler getSkyRenderer() {
		return (ticks, partialTicks, world, mc) -> {};
	}

	@Nullable
	@Override
	public IRenderHandler getCloudRenderer() {
		return (ticks, partialTicks, world, mc) -> {};
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
