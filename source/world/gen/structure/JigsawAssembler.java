package net.tslat.aoa3.world.gen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.JigsawBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.tslat.aoa3.advent.Logging;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.*;

public class JigsawAssembler {
	protected final Registry<JigsawPattern> poolRegistry;
	protected final int maxDepth;
	protected final JigsawManager.IPieceFactory factory;
	protected final ChunkGenerator chunkGenerator;
	protected final TemplateManager structureManager;
	protected final List<? super AbstractVillagePiece> pieces;
	protected final Random random;
	protected final Deque<Entry> placeQueue = Queues.newArrayDeque();

	public JigsawAssembler(Registry<JigsawPattern> poolRegistry, int maxPieceDepth, JigsawManager.IPieceFactory factory, ChunkGenerator chunkGen, TemplateManager templateManager, List<? super AbstractVillagePiece> pieces, Random rand) {
		this.poolRegistry = poolRegistry;
		this.maxDepth = maxPieceDepth;
		this.factory = factory;
		this.chunkGenerator = chunkGen;
		this.structureManager = templateManager;
		this.pieces = pieces;
		this.random = rand;
	}

	public static void preGenPieces(DynamicRegistries registry, AbstractVillagePiece startPiece, int maxPieceDepth, JigsawManager.IPieceFactory pieceFactory, ChunkGenerator chunkGen, TemplateManager templateManager, List<? super AbstractVillagePiece> pieceList, Random rand) {
		preGenPieces(startPiece, new JigsawAssembler(registry.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY), maxPieceDepth, pieceFactory, chunkGen, templateManager, pieceList, rand));
	}

	public static void preGenPieces(AbstractVillagePiece startPiece, JigsawAssembler assembler) {
		assembler.placeQueue.addLast(new Entry(startPiece, new MutableObject<VoxelShape>(VoxelShapes.INFINITY), 0, 0));

		while(!assembler.placeQueue.isEmpty()) {
			Entry entry = assembler.placeQueue.removeFirst();

			assembler.tryPlacingChildren(entry.piece, entry.free, entry.boundsTop, entry.depth, false);
		}
	}

	private void tryPlacingChildren(AbstractVillagePiece piece, MutableObject<VoxelShape> freeBounds, int boundsYMax, int currentDepth, boolean doExpansionHack) {
		JigsawPiece jigsawPiece = piece.getElement();
		JigsawPattern.PlacementBehaviour placementBehaviour = jigsawPiece.getProjection();
		MutableBoundingBox pieceBounds = piece.getBoundingBox();
		MutableObject<VoxelShape> boundsShape = new MutableObject<>();
		boolean isRigid = placementBehaviour == JigsawPattern.PlacementBehaviour.RIGID;
		int yMin = pieceBounds.y0;

		for (Template.BlockInfo blockInfo : jigsawPiece.getShuffledJigsawBlocks(this.structureManager, piece.getPosition(), piece.getRotation(), this.random)) {
			handleJunctions(blockInfo, yMin, boundsShape, pieceBounds, boundsYMax, freeBounds, currentDepth, doExpansionHack, isRigid, piece, placementBehaviour);
		}
	}

	@Nullable
	protected Pair<JigsawPattern, JigsawPattern> getPools(Template.BlockInfo jigsawInfo, int yMin) {
		JigsawPattern pool = getPool(new ResourceLocation(jigsawInfo.nbt.getString("pool")));

		if (pool == null)
			return null;

		JigsawPattern fallbackPool = getPool(pool.getFallback());

		return fallbackPool == null ? null : Pair.of(pool, fallbackPool);
	}

	@Nullable
	protected JigsawPattern getPool(ResourceLocation path) {
		Optional<JigsawPattern> pool = this.poolRegistry.getOptional(path);

		if (!pool.isPresent() || (pool.get().size() == 0 && !Objects.equals(path, JigsawPatternRegistry.EMPTY.location()))) {
			Logging.logMessage(Level.WARN, "Invalid pool for structure piece: " + path);

			return null;
		}

		return pool.get();
	}

	protected void handleJunctions(Template.BlockInfo jigsawInfo, int yMin, MutableObject<VoxelShape> boundsShape, MutableBoundingBox pieceBounds, int boundsYMax, MutableObject<VoxelShape> templateFreeBounds, int currentDepth, boolean doExpansionHack, boolean isRigid, AbstractVillagePiece piece, JigsawPattern.PlacementBehaviour placementBehaviour) {
		BlockPos templatePos = jigsawInfo.pos;
		BlockPos pieceAdjustedPos = templatePos.relative(JigsawBlock.getFrontFacing(jigsawInfo.state));
		int pieceHeight = templatePos.getY() - yMin;
		int groundY = -1;
		Pair<JigsawPattern, JigsawPattern> pools = getPools(jigsawInfo, yMin);

		if (pools == null)
			return;

		MutableObject<VoxelShape> freeBounds;
		int boundingBoxTop;

		if (pieceBounds.isInside(pieceAdjustedPos)) {
			freeBounds = boundsShape;
			boundingBoxTop = yMin;

			if (boundsShape.getValue() == null)
				boundsShape.setValue(VoxelShapes.create(AxisAlignedBB.of(pieceBounds)));
		}
		else {
			freeBounds = templateFreeBounds;
			boundingBoxTop = boundsYMax;
		}

		List<JigsawPiece> templateList = Lists.newArrayList();

		if (currentDepth != this.maxDepth)
			templateList.addAll(pools.getFirst().getShuffledTemplates(this.random));

		templateList.addAll(pools.getSecond().getShuffledTemplates(this.random));

		for(JigsawPiece nextTemplate : templateList) {
			if (nextTemplate == EmptyJigsawPiece.INSTANCE)
				break;

			for(Rotation nextRotation : Rotation.getShuffled(this.random)) {
				/*List<Template.BlockInfo> jigsawBlocks = nextTemplate.getShuffledJigsawBlocks(this.structureManager, BlockPos.ZERO, nextRotation, this.random);
				MutableBoundingBox templateBounds = nextTemplate.getBoundingBox(this.structureManager, BlockPos.ZERO, nextRotation);
				int yExpansion = 0;

				if (doExpansionHack && templateBounds.getYSpan() <= 16) {
					yExpansion = jigsawBlocks.stream().mapToInt((jigsawBlock) -> {
						if (!templateBounds.isInside(jigsawBlock.pos.relative(JigsawBlock.getFrontFacing(jigsawBlock.state))))
							return 0;

						Optional<JigsawPattern> jigsawTargetPool = this.pools.getOptional(new ResourceLocation(jigsawBlock.nbt.getString("pool")));
						Optional<JigsawPattern> fallbackTargetPool = jigsawTargetPool.flatMap(pattern -> this.pools.getOptional(pattern.getFallback()));
						int targetMaxSize = jigsawTargetPool.map(pattern -> pattern.getMaxSize(this.structureManager)).orElse(0);
						int fallbackMaxSize = fallbackTargetPool.map(pattern -> pattern.getMaxSize(this.structureManager)).orElse(0);

						return Math.max(targetMaxSize, fallbackMaxSize);
					}).max().orElse(0);
				}*/

				for(Template.BlockInfo templateBlockInfo : nextTemplate.getShuffledJigsawBlocks(this.structureManager, BlockPos.ZERO, nextRotation, this.random)) {
					if (JigsawBlock.canAttach(jigsawInfo, templateBlockInfo)) {
						BlockPos templateBlockInfoPos = templateBlockInfo.pos;
						BlockPos templateRelativeBlockInfoPos = new BlockPos(pieceAdjustedPos.getX() - templateBlockInfoPos.getX(), pieceAdjustedPos.getY() - templateBlockInfoPos.getY(), pieceAdjustedPos.getZ() - templateBlockInfoPos.getZ());
						MutableBoundingBox posBoundingBox = nextTemplate.getBoundingBox(this.structureManager, templateRelativeBlockInfoPos, nextRotation);
						int posMinY = posBoundingBox.y0;
						JigsawPattern.PlacementBehaviour templateProjection = nextTemplate.getProjection();
						boolean rigidPiece = templateProjection == JigsawPattern.PlacementBehaviour.RIGID;
						int jigsawBlockYPos = templateBlockInfoPos.getY();
						int yDelta = pieceHeight - jigsawBlockYPos + JigsawBlock.getFrontFacing(jigsawInfo.state).getStepY();
						int jigsawYOffset;

						if (isRigid && rigidPiece) {
							jigsawYOffset = yMin + yDelta;
						}
						else {
							if (groundY == -1)
								groundY = this.chunkGenerator.getFirstFreeHeight(templatePos.getX(), templatePos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);

							jigsawYOffset = groundY - jigsawBlockYPos;
						}

						int jigsawOffsetRelative = jigsawYOffset - posMinY;
						MutableBoundingBox jigsawOffsetBounds = posBoundingBox.moved(0, jigsawOffsetRelative, 0);
						BlockPos jigsawOffsetPos = templateRelativeBlockInfoPos.offset(0, jigsawOffsetRelative, 0);

						/*if (yExpansion > 0)
							jigsawOffsetBounds.y1 = jigsawOffsetBounds.y0 + Math.max(yExpansion + 1, jigsawOffsetBounds.y1 - jigsawOffsetBounds.y0);*/

						if (!VoxelShapes.joinIsNotEmpty(freeBounds.getValue(), VoxelShapes.create(AxisAlignedBB.of(jigsawOffsetBounds).deflate(0.25)), IBooleanFunction.ONLY_SECOND)) {
							freeBounds.setValue(VoxelShapes.joinUnoptimized(freeBounds.getValue(), VoxelShapes.create(AxisAlignedBB.of(jigsawOffsetBounds)), IBooleanFunction.ONLY_FIRST));
							int pieceGroundDelta = piece.getGroundLevelDelta();
							int pieceGroundDeltaOffset;
							pieceGroundDeltaOffset = rigidPiece ? pieceGroundDelta - yDelta : nextTemplate.getGroundLevelDelta();
							AbstractVillagePiece newPiece = this.factory.create(this.structureManager, nextTemplate, jigsawOffsetPos, pieceGroundDeltaOffset, nextRotation, jigsawOffsetBounds);
							int pieceTop;

							if (isRigid) {
								pieceTop = yMin + pieceHeight;
							}
							else if (rigidPiece) {
								pieceTop = jigsawYOffset + jigsawBlockYPos;
							}
							else {
								if (groundY == -1)
									groundY = this.chunkGenerator.getFirstFreeHeight(templatePos.getX(), templatePos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);

								pieceTop = groundY + yDelta / 2;
							}

							piece.addJunction(new JigsawJunction(pieceAdjustedPos.getX(), pieceTop - pieceHeight + pieceGroundDelta, pieceAdjustedPos.getZ(), yDelta, templateProjection));
							newPiece.addJunction(new JigsawJunction(templatePos.getX(), pieceTop - jigsawBlockYPos + pieceGroundDeltaOffset, templatePos.getZ(), -yDelta, placementBehaviour));
							this.pieces.add(newPiece);

							if (currentDepth + 1 <= this.maxDepth)
								this.placeQueue.addLast(new Entry(newPiece, freeBounds, boundingBoxTop, currentDepth + 1));

							return;
						}
					}
				}
			}
		}
	}

	protected static final class Entry {
		public final AbstractVillagePiece piece;
		public final MutableObject<VoxelShape> free;
		public final int boundsTop;
		public final int depth;

		public Entry(AbstractVillagePiece piece, MutableObject<VoxelShape> free, int boundingBoxTop, int pieceDepth) {
			this.piece = piece;
			this.free = free;
			this.boundsTop = boundingBoxTop;
			this.depth = pieceDepth;
		}
	}
}
