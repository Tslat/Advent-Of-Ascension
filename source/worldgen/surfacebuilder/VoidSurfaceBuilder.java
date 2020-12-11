package net.tslat.aoa3.worldgen.surfacebuilder;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class VoidSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	public VoidSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> configFactory) {
		super(configFactory);
	}

	@Override
	public void buildSurface(Random rand, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState fillerBlock, BlockState fillerFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {}
}
