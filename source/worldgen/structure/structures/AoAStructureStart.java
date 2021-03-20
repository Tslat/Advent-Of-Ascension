package net.tslat.aoa3.worldgen.structure.structures;

import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public abstract class AoAStructureStart<T extends IFeatureConfig> extends StructureStart<T> {
	public AoAStructureStart(AoAStructureBase<T> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
		super(structure, chunkX, chunkZ, boundingBox, references, seed);
	}

	@Override
	public abstract void generatePieces(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, T config);

	@Override
	public AoAStructureBase<T> getFeature() {
		return (AoAStructureBase<T>)super.getFeature();
	}
}
