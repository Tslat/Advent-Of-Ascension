package net.tslat.aoa3.content.world.gen.structure;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class GenericAoAStructure<T extends JigsawConfiguration> extends StructureFeature<T> {
	private final GenerationStep.Decoration genStage;
	public GenericAoAStructure(Codec<T> codec) {
		this(codec, new PieceGen<>());
	}
	public GenericAoAStructure(Codec<T> codec, PieceGeneratorSupplier<T> pieceGen) {
		this(codec, pieceGen, GenerationStep.Decoration.SURFACE_STRUCTURES);
	}

	public GenericAoAStructure(Codec<T> codec, PieceGeneratorSupplier<T> pieceGen, GenerationStep.Decoration genStage) {
		super(codec, pieceGen, PostPlacementProcessor.NONE);

		this.genStage = genStage;
	}

	@Override
	public GenerationStep.Decoration step() {
		return this.genStage;
	}

	public static class PieceGen<C extends JigsawConfiguration> implements PieceGeneratorSupplier<C> {
		protected int maxPiecesDepth(Context<C> genContext) {
			return 10;
		}

		protected boolean lockRotation(Context<C> genContext) {
			return false;
		}

		protected boolean genOnWorldSurface(Context<C> genContext) {
			return true;
		}

		protected boolean shouldGenInChunk(Context<C> genContext) {
			return true;
		}

		protected boolean checkAndAdjustGeneration(Context<C> genContext, WorldgenRandom rand, BlockPos.MutableBlockPos currentPos) {
			int y = genContext.chunkGenerator().getFirstOccupiedHeight(currentPos.getX(), currentPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, genContext.heightAccessor());

			if (genOnWorldSurface(genContext))
				currentPos.setY(y);

			return y >= genContext.heightAccessor().getMinBuildHeight();
		}

		protected StructurePoolElement getStartPoolElement(Context<C> genContext, WorldgenRandom rand, BlockPos.MutableBlockPos pos) {
			return genContext.config().startPool().value().getRandomTemplate(rand);
		}

		@Override
		public Optional<PieceGenerator<C>> createGenerator(Context<C> genContext) {
			if (!shouldGenInChunk(genContext))
				return Optional.empty();

			BlockPos.MutableBlockPos chunkCenter = genContext.chunkPos().getMiddleBlockPosition(0).mutable();
			WorldgenRandom rand = new WorldgenRandom(new LegacyRandomSource(0L));

			rand.setLargeFeatureSeed(genContext.seed(), genContext.chunkPos().x, genContext.chunkPos().z);

			if (!checkAndAdjustGeneration(genContext, rand, chunkCenter))
				return Optional.empty();

			StructureFeature.bootstrap();

			ChunkGenerator chunkGenerator = genContext.chunkGenerator();
			StructureManager structureManager = genContext.structureManager();
			Predicate<Holder<Biome>> predicate = genContext.validBiome();


			Rotation rotation = lockRotation(genContext) ? Rotation.NONE : Rotation.getRandom(rand);
			StructurePoolElement startElement = getStartPoolElement(genContext, rand, chunkCenter);

			if (startElement == EmptyPoolElement.INSTANCE)
				return Optional.empty();

			JigsawPlacement.PieceFactory pieceFactory = PoolElementStructurePiece::new;
			PoolElementStructurePiece startPiece = pieceFactory.create(structureManager, startElement, chunkCenter, startElement.getGroundLevelDelta(), rotation, startElement.getBoundingBox(structureManager, chunkCenter, rotation));
			BoundingBox startBounds = startPiece.getBoundingBox();
			int centerX = (startBounds.maxX() + startBounds.minX()) / 2;
			int centerZ = (startBounds.maxZ() + startBounds.minZ()) / 2;

			if (!predicate.test(chunkGenerator.getNoiseBiome(QuartPos.fromBlock(centerX), QuartPos.fromBlock(chunkCenter.getY()), QuartPos.fromBlock(centerZ))))
				return Optional.empty();

			int yAdjust = startBounds.minY() + startPiece.getGroundLevelDelta();

			startPiece.move(0, chunkCenter.getY() - yAdjust, 0);

			return createNewPieceGenerator(startPiece, centerX, centerZ, genContext, rand, pieceFactory, startBounds);
		}

		protected Optional<PieceGenerator<C>> createNewPieceGenerator(PoolElementStructurePiece startPiece, int centerX, int centerZ, Context<C> genContext, WorldgenRandom rand, JigsawPlacement.PieceFactory pieceFactory, BoundingBox startBounds) {
			return Optional.of((pieceBuilder, context2) -> {
				List<PoolElementStructurePiece> pieces = Lists.newArrayList();
				LevelHeightAccessor level = genContext.heightAccessor();

				pieces.add(startPiece);

				if (maxPiecesDepth(genContext) > 0) {
					AABB safeBounds = new AABB(centerX - 80, level.getMinBuildHeight(), centerZ - 80, centerX + 80 + 1, level.getMaxBuildHeight(), centerZ + 80 + 1);
					JigsawAssembler assembler = new JigsawAssembler(genContext.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY), maxPiecesDepth(genContext), pieceFactory, genContext.chunkGenerator(), genContext.structureManager(), pieces, rand);

					assembler.placementQueue.addLast(new JigsawAssembler.Entry(startPiece, new MutableObject<>(Shapes.join(Shapes.create(safeBounds), Shapes.create(AABB.of(startBounds)), BooleanOp.ONLY_FIRST)), 0));

					while(!assembler.placementQueue.isEmpty()) {
						JigsawAssembler.Entry entry = assembler.placementQueue.removeFirst();

						assembler.tryPlacingChildren(entry.piece, entry.free, entry.depth, false, level);
					}

					pieces.forEach(pieceBuilder::addPiece);
				}
			});
		}
	}
}
