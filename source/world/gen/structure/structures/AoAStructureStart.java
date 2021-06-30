package net.tslat.aoa3.world.gen.structure.structures;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class AoAStructureStart<T extends IFeatureConfig> extends StructureStart<T> {
	public AoAStructureStart(AoAStructureBase<T> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
		super(structure, chunkX, chunkZ, boundingBox, references, seed);
	}

	@Override
	public AoAStructureBase<T> getFeature() {
		return (AoAStructureBase<T>)super.getFeature();
	}

	protected int getStructurePieceDepth() {
		return 10;
	}

	protected Random getRandom() {
		return this.random;
	}

	protected boolean shouldAvoidRotating() {
		return false;
	}

	protected boolean shouldGenerateOnWorldSurface() {
		return true;
	}

	@Override
	public void generatePieces(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, T config) {
		BlockPos.Mutable chunkCenter = new BlockPos.Mutable((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);

		if (checkAndAdjustGeneration(chunkGenerator, chunkCenter, biome, config))
			generateStructurePieces(dynamicRegistry, getStructurePieceDepth(), chunkGenerator, templateManager, chunkCenter, getRandom(), false, shouldGenerateOnWorldSurface());
	}

	protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.Mutable chunkCenter, Biome biome, T config) {
		return chunkGenerator.getFirstFreeHeight(chunkCenter.getX(), chunkCenter.getZ(), Heightmap.Type.WORLD_SURFACE_WG) > 0;
	}

	protected void generateStructurePieces(DynamicRegistries registries, int maxDepth, ChunkGenerator chunkGenerator, TemplateManager templateManager, BlockPos chunkCenter, Random rand, boolean bool1, boolean generateOnSurface) {
		Structure.bootstrap();

		VillageConfig config = new VillageConfig(() -> registries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(getFeature().getTemplatePoolPath()), maxDepth);
		JigsawManager.IPieceFactory factory = AbstractVillagePiece::new;
		List<? super AbstractVillagePiece> pieces = getPieces();
		Rotation rotation = shouldAvoidRotating() ? Rotation.NONE : Rotation.getRandom(rand);
		JigsawPattern jigsawpattern = config.startPool().get();
		JigsawPiece jigsawpiece = jigsawpattern.getRandomTemplate(rand);
		AbstractVillagePiece villagePiece = factory.create(templateManager, jigsawpiece, chunkCenter, jigsawpiece.getGroundLevelDelta(), rotation, jigsawpiece.getBoundingBox(templateManager, chunkCenter, rotation));
		MutableBoundingBox villageBoundingBox = villagePiece.getBoundingBox();
		int centerX = (villageBoundingBox.x1 + villageBoundingBox.x0) / 2;
		int centerZ = (villageBoundingBox.z1 + villageBoundingBox.z0) / 2;
		int centerY = chunkCenter.getY();
		int yOffset = villageBoundingBox.y0 + villagePiece.getGroundLevelDelta();

		if (generateOnSurface)
			centerY = chunkCenter.getY() + chunkGenerator.getFirstFreeHeight(centerX, centerZ, Heightmap.Type.WORLD_SURFACE_WG);

		villagePiece.move(0, centerY + 1 - yOffset, 0);
		pieces.add(villagePiece);

		if (config.maxDepth() > 0)
			JigsawManager.addPieces(registries, villagePiece, config.maxDepth(), factory, chunkGenerator, templateManager, pieces, rand);

		calculateBoundingBox();
		doPostPlacementOperations(maxDepth, chunkGenerator, chunkCenter, rand);
	}

	protected void doPostPlacementOperations(int maxDepth, ChunkGenerator chunkGenerator, BlockPos originPos, Random rand) {}
}
