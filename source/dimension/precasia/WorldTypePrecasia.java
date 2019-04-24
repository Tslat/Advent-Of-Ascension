package net.tslat.aoa3.dimension.precasia;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.DimensionRegister;

public class WorldTypePrecasia extends WorldType {
	public WorldTypePrecasia() {
		super("precasia");
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(DimensionRegister.biomePrecasia);
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGenPrecasia(world);
	}

	@Override
	public boolean isCustomizable() {
		return false;
	}

	@Override
	public boolean canBeCreated() {
		return false;
	}
}
