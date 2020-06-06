package net.tslat.aoa3.dimension.deeplands;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.BiomeRegister;

public class WorldTypeDeeplands extends WorldType {
	public WorldTypeDeeplands() {
		super("deeplands");
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(BiomeRegister.DEEPLANDS);
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGenDeeplands(world);
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
