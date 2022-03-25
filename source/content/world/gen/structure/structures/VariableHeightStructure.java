package net.tslat.aoa3.content.world.gen.structure.structures;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.tslat.aoa3.content.world.gen.feature.features.config.VariablePieceCountConfig;

import java.util.List;
import java.util.Random;

public class VariableHeightStructure extends AoAStructureBase<VariablePieceCountConfig> {
	public VariableHeightStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(VariablePieceCountConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<VariablePieceCountConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<VariablePieceCountConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected void generateStructurePieces(DynamicRegistries registries, int maxDepth, ChunkGenerator chunkGenerator, TemplateManager templateManager, BlockPos chunkCenter, Random rand, boolean bool1, boolean generateOnSurface, VariablePieceCountConfig structureConfig) {
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

				int maxPieces = structureConfig.getPiecesForStartPosition(villageBoundingBox.y0, config.maxDepth());

				if (maxPieces > 0)
					JigsawManager.addPieces(registries, villagePiece, maxPieces, factory, chunkGenerator, templateManager, pieces, rand);

				calculateBoundingBox();
				doPostPlacementOperations(maxDepth, chunkGenerator, chunkCenter, rand);
			}
		};
	}
}
