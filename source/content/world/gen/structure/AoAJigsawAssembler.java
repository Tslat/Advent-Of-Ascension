package net.tslat.aoa3.content.world.gen.structure;

import com.google.common.collect.Queues;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.advent.Logging;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.logging.log4j.Level;

import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class AoAJigsawAssembler {
	protected boolean ignoreRotations() {
		return false;
	}

	protected BlockPos getStartPos(PoolElementStructurePiece startPiece, int x, int y, int z) {
		return new BlockPos(x, y, z);
	}

	public Optional<Structure.GenerationStub> addPieces(Structure.GenerationContext genContext, Holder<StructureTemplatePool> templatePoolHolder, Optional<ResourceLocation> startJigsawName, int maxPieces, BlockPos startPos, Optional<Heightmap.Types> heightmap, int maxRadius) {
		StructureTemplateManager templateManager = genContext.structureTemplateManager();
		WorldgenRandom rand = genContext.random();
		Rotation rotation = ignoreRotations() ? Rotation.NONE : Rotation.getRandom(rand);
		StructurePoolElement poolElement = templatePoolHolder.value().getRandomTemplate(rand);

		if (poolElement == EmptyPoolElement.INSTANCE)
			return Optional.empty();

		Vec3i startOffset = Vec3i.ZERO;

		if (startJigsawName.isPresent()) {
			Optional<BlockPos> startJigsawPos = getRandomNamedJigsaw(poolElement, startJigsawName.get(), startPos, rotation, templateManager, rand);

			if (startJigsawPos.isEmpty()) {
				Logging.logMessage(Level.ERROR, "No starting jigsaw " + startJigsawName.get() + " found in start pool "  + templatePoolHolder.unwrapKey().get().location());

				return Optional.empty();
			}

			startOffset = startJigsawPos.get().subtract(startPos);
		}

		BlockPos finalStartPos = startPos.subtract(startOffset);
		PoolElementStructurePiece startPiece = new PoolElementStructurePiece(templateManager, poolElement, finalStartPos, poolElement.getGroundLevelDelta(), rotation, poolElement.getBoundingBox(templateManager, finalStartPos, rotation));
		BoundingBox startPieceBounds = startPiece.getBoundingBox();
		int structurePosX = (startPieceBounds.minX() + startPieceBounds.maxX()) / 2;
		int structurePosZ = (startPieceBounds.minZ() + startPieceBounds.maxZ()) / 2;
		int startY = finalStartPos.getY();

		if (heightmap.isPresent())
			startY = startPos.getY() + genContext.chunkGenerator().getFirstFreeHeight(structurePosX, structurePosZ, heightmap.get(), genContext.heightAccessor(), genContext.randomState());

		startPiece.move(0, startY - (startPieceBounds.minY() + startPiece.getGroundLevelDelta()), 0);

		return buildGenerationStub(startPiece, startPieceBounds, genContext, structurePosX, startY + startOffset.getY(), structurePosZ, maxPieces, maxRadius);
	}

	protected Optional<Structure.GenerationStub> buildGenerationStub(PoolElementStructurePiece startPiece, BoundingBox startPieceBounds, Structure.GenerationContext genContext, int startX, int startY, int startZ, int maxPieces, int maxRadius) {
		return Optional.of(new Structure.GenerationStub(getStartPos(startPiece, startX, startY, startZ), pieceBuilder -> {
			List<PoolElementStructurePiece> pieces = new ObjectArrayList<>();

			pieces.add(startPiece);

			if (maxPieces > 0) {
				addPieces(
						genContext.randomState(),
						maxPieces,
						genContext.chunkGenerator(),
						genContext.structureTemplateManager(),
						genContext.heightAccessor(),
						genContext.random(),
						genContext.registryAccess().registryOrThrow(Registries.TEMPLATE_POOL),
						startPiece,
						pieces,
						Shapes.join(
								Shapes.create(new AABB(startX - maxRadius, -4000, startZ - maxRadius, startX + maxRadius + 1, 4000, startZ + maxRadius + 1)),
								Shapes.create(AABB.of(startPieceBounds)), BooleanOp.ONLY_FIRST));
				pieces.forEach(pieceBuilder::addPiece);
			}
		}));
	}

	protected Optional<BlockPos> getRandomNamedJigsaw(StructurePoolElement poolElement, ResourceLocation jigsawName, BlockPos startPos, Rotation rotation, StructureTemplateManager templateManager, WorldgenRandom rand) {
		for(StructureTemplate.StructureBlockInfo jigsawBlockInfo : poolElement.getShuffledJigsawBlocks(templateManager, startPos, rotation, rand)) {
			if (jigsawName.equals(ResourceLocation.tryParse(jigsawBlockInfo.nbt.getString("name"))))
				return Optional.of(jigsawBlockInfo.pos);
		}

		return Optional.empty();
	}

	protected void addPieces(RandomState genState, int maxPieces, ChunkGenerator chunkGen, StructureTemplateManager templateManager, LevelHeightAccessor heightAccessor, RandomSource rand, Registry<StructureTemplatePool> templatePool, PoolElementStructurePiece parentPiece, List<PoolElementStructurePiece> childPieces, VoxelShape bounds) {
		PiecePlacer piecePlacer = new PiecePlacer(templatePool, maxPieces, chunkGen, templateManager, childPieces, rand);

		piecePlacer.placing.addLast(new PieceState(parentPiece, new MutableObject<>(bounds), 0));

		while (!piecePlacer.placing.isEmpty()) {
			PieceState pieceState = piecePlacer.placing.removeFirst();

			piecePlacer.tryPlacingChildren(pieceState.piece, pieceState.bounds, pieceState.depth, heightAccessor, genState);
		}

	}

	public boolean generateJigsaw(ServerLevel level, Holder<StructureTemplatePool> templatePool, ResourceLocation startJigsawName, int maxPieces, BlockPos startPos, boolean keepJigsaws) {
		ChunkGenerator chunkGen = level.getChunkSource().getGenerator();
		StructureTemplateManager templateManager = level.getStructureManager();
		StructureManager structureManager = level.structureManager();
		RandomSource rand = level.getRandom();
		Structure.GenerationContext genContext = new Structure.GenerationContext(level.registryAccess(), chunkGen, chunkGen.getBiomeSource(), level.getChunkSource().randomState(), templateManager, level.getSeed(), new ChunkPos(startPos), level, (p_227255_) -> true);
		Optional<Structure.GenerationStub> pieceGen = addPieces(genContext, templatePool, Optional.of(startJigsawName), maxPieces, startPos, Optional.empty(), 128);

		if (pieceGen.isEmpty())
			return false;

		StructurePiecesBuilder pieceBuilder = pieceGen.get().getPiecesBuilder();

		for(StructurePiece structurepiece : pieceBuilder.build().pieces()) {
			if (structurepiece instanceof PoolElementStructurePiece piece)
				piece.place(level, structureManager, chunkGen, rand, BoundingBox.infinite(), startPos, keepJigsaws);
		}

		return true;
	}

	protected static final class PieceState {
		private final PoolElementStructurePiece piece;
		private final MutableObject<VoxelShape> bounds;
		private final int depth;

		PieceState(PoolElementStructurePiece piece, MutableObject<VoxelShape> bounds, int pieceDepth) {
			this.piece = piece;
			this.bounds = bounds;
			this.depth = pieceDepth;
		}
	}

	protected static final class PiecePlacer {
		private final Registry<StructureTemplatePool> pools;
		private final int maxPieces;
		private final ChunkGenerator chunkGenerator;
		private final StructureTemplateManager structureTemplateManager;
		private final List<? super PoolElementStructurePiece> pieces;
		private final RandomSource random;
		final Deque<PieceState> placing = Queues.newArrayDeque();

		private PiecePlacer(Registry<StructureTemplatePool> templatePoolRegistry, int maxPieces, ChunkGenerator chunkGen, StructureTemplateManager templateManager, List<? super PoolElementStructurePiece> pieces, RandomSource rand) {
			this.pools = templatePoolRegistry;
			this.maxPieces = maxPieces;
			this.chunkGenerator = chunkGen;
			this.structureTemplateManager = templateManager;
			this.pieces = pieces;
			this.random = rand;
		}

		void tryPlacingChildren(PoolElementStructurePiece parentPiece, MutableObject<VoxelShape> bounds, int pieceDepth, LevelHeightAccessor heightAccessor, RandomState genState) {
			StructurePoolElement poolElement = parentPiece.getElement();
			StructureTemplatePool.Projection projection = poolElement.getProjection();
			MutableObject<VoxelShape> shape = new MutableObject<>();
			BoundingBox parentBounds = parentPiece.getBoundingBox();
			int minY = parentBounds.minY();

			for(StructureTemplate.StructureBlockInfo jigsawBlockInfo : poolElement.getShuffledJigsawBlocks(this.structureTemplateManager, parentPiece.getPosition(), parentPiece.getRotation(), this.random)) {
				BlockPos jigsawPos = jigsawBlockInfo.pos;
				BlockPos jigsawFacingPos = jigsawPos.relative(JigsawBlock.getFrontFacing(jigsawBlockInfo.state));
				ResourceLocation poolPath = new ResourceLocation(jigsawBlockInfo.nbt.getString("pool"));
				Optional<StructureTemplatePool> pool = this.pools.getOptional(poolPath);

				if (pool.isEmpty() || (pool.get().size() == 0 && !poolPath.equals(Pools.EMPTY.location()))) {
					Logging.logMessage(Level.WARN, "Empty or non-existent pool: " + poolPath);

					return;
				}

				Holder<StructureTemplatePool> fallback = pool.get().getFallback();
				StructureTemplatePool fallbackPool = fallback.get();

				if (fallbackPool.size() == 0 && !fallback.is(Pools.EMPTY)) {
					Logging.logMessage(Level.WARN, "Empty or non-existent fallback pool: " + fallback.unwrapKey().get().location());

					return;
				}

				MutableObject<VoxelShape> placementBounds;

				if (parentBounds.isInside(jigsawFacingPos)) {
					placementBounds = shape;

					if (shape.getValue() == null)
						shape.setValue(Shapes.create(AABB.of(parentBounds)));
				}
				else {
					placementBounds = bounds;
				}

				List<StructurePoolElement> piecesToGen = new ObjectArrayList<>();

				if (pieceDepth != this.maxPieces)
					piecesToGen.addAll(pool.get().getShuffledTemplates(this.random));

				piecesToGen.addAll(fallbackPool.getShuffledTemplates(this.random));

				placeChildren(jigsawBlockInfo, heightAccessor, genState, parentPiece, jigsawFacingPos, placementBounds, jigsawPos.getY() - minY, projection == StructureTemplatePool.Projection.RIGID, minY, pieceDepth, projection, jigsawPos, piecesToGen);
			}
		}

		private void placeChildren(final StructureTemplate.StructureBlockInfo jigsawBlockInfo, final LevelHeightAccessor heightAccessor, final RandomState genState, final PoolElementStructurePiece parentPiece,
								   final BlockPos jigsawFacingPos, final MutableObject<VoxelShape> placementBounds, final int jigsawPosDelta, final boolean isRigid, final int minY, final int pieceDepth,
								   final StructureTemplatePool.Projection projection, final BlockPos jigsawPos, final List<StructurePoolElement> piecesToGen) {
			int terrainMatchDelta = -1;

			for(StructurePoolElement childPoolElement : piecesToGen) {
				if (childPoolElement == EmptyPoolElement.INSTANCE)
					return;

				for(Rotation pieceRotation : Rotation.getShuffled(this.random)) {
					List<StructureTemplate.StructureBlockInfo> childPieceJigsawBlocks = childPoolElement.getShuffledJigsawBlocks(this.structureTemplateManager, BlockPos.ZERO, pieceRotation, this.random);

					for(StructureTemplate.StructureBlockInfo childPieceJigsawBlock : childPieceJigsawBlocks) {
						if (JigsawBlock.canAttach(jigsawBlockInfo, childPieceJigsawBlock)) {
							BlockPos childPieceJigsawPos = childPieceJigsawBlock.pos;
							BlockPos jigsawToPieceDelta = jigsawFacingPos.subtract(childPieceJigsawPos);
							BoundingBox childBounds = childPoolElement.getBoundingBox(this.structureTemplateManager, jigsawToPieceDelta, pieceRotation);
							StructureTemplatePool.Projection childProjection = childPoolElement.getProjection();
							boolean childIsRigid = childProjection == StructureTemplatePool.Projection.RIGID;
							int childJigsawYPos = childPieceJigsawPos.getY();
							int deltaY = jigsawPosDelta - childJigsawYPos + JigsawBlock.getFrontFacing(jigsawBlockInfo.state).getStepY();
							int placementYOffset = minY + deltaY;

							if (!isRigid || !childIsRigid) {
								if (terrainMatchDelta == -1)
									terrainMatchDelta = this.chunkGenerator.getFirstFreeHeight(jigsawPos.getX(), jigsawPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, genState);

								placementYOffset = terrainMatchDelta - childJigsawYPos;
							}

							int childBoundsOffset = placementYOffset - childBounds.minY();
							childBounds = childBounds.moved(0, childBoundsOffset, 0);
							BlockPos childPos = jigsawToPieceDelta.offset(0, childBoundsOffset, 0);

							if (!Shapes.joinIsNotEmpty(placementBounds.getValue(), Shapes.create(AABB.of(childBounds).deflate(0.25D)), BooleanOp.ONLY_SECOND)) {
								placementBounds.setValue(Shapes.joinUnoptimized(placementBounds.getValue(), Shapes.create(AABB.of(childBounds)), BooleanOp.ONLY_FIRST));
								int parentGroundDelta = parentPiece.getGroundLevelDelta();
								int groundLevelDelta = childPoolElement.getGroundLevelDelta();

								if (childIsRigid)
									groundLevelDelta = parentGroundDelta - deltaY;

								PoolElementStructurePiece childPiece = new PoolElementStructurePiece(this.structureTemplateManager, childPoolElement, childPos, groundLevelDelta, pieceRotation, childBounds);
								int childBaseY;

								if (isRigid) {
									childBaseY = minY + jigsawPosDelta;
								}
								else if (childIsRigid) {
									childBaseY = placementYOffset + childJigsawYPos;
								}
								else {
									if (terrainMatchDelta == -1)
										terrainMatchDelta = this.chunkGenerator.getFirstFreeHeight(jigsawPos.getX(), jigsawPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, genState);

									childBaseY = terrainMatchDelta + deltaY / 2;
								}

								parentPiece.addJunction(new JigsawJunction(jigsawFacingPos.getX(), childBaseY - jigsawPosDelta + parentGroundDelta, jigsawFacingPos.getZ(), deltaY, childProjection));
								childPiece.addJunction(new JigsawJunction(jigsawPos.getX(), childBaseY - childJigsawYPos + groundLevelDelta, jigsawPos.getZ(), -deltaY, projection));
								this.pieces.add(childPiece);

								if (pieceDepth + 1 <= this.maxPieces)
									this.placing.addLast(new PieceState(childPiece, placementBounds, pieceDepth + 1));

								return;
							}
						}
					}
				}
			}
		}
	}
}
