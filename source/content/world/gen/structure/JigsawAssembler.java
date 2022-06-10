/*
package net.tslat.aoa3.content.world.gen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.advent.Logging;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class JigsawAssembler {
	protected final Registry<StructureTemplatePool> poolRegistry;
	protected final int maxDepth;
	protected final JigsawPlacement.PieceFactory pieceFactory;
	protected final ChunkGenerator chunkGenerator;
	protected final StructureManager structureManager;
	protected final List<? super PoolElementStructurePiece> pieces;
	protected final Random random;
	protected final Deque<Entry> placementQueue = Queues.newArrayDeque();

	public JigsawAssembler(Registry<StructureTemplatePool> poolRegistry, int maxDepth, JigsawPlacement.PieceFactory pieceFactory, ChunkGenerator chunkGenerator, StructureManager structureManager, List<? super PoolElementStructurePiece> pieceList, Random rand) {
		this.poolRegistry = poolRegistry;
		this.maxDepth = maxDepth;
		this.pieceFactory = pieceFactory;
		this.chunkGenerator = chunkGenerator;
		this.structureManager = structureManager;
		this.pieces = pieceList;
		this.random = rand;
	}

	public void tryPlacingChildren(PoolElementStructurePiece elementPiece, MutableObject<VoxelShape> freeBounds, int pieceDepth, boolean doExpansionHack, LevelHeightAccessor level) {
		StructurePoolElement poolElement = elementPiece.getElement();
		StructureTemplatePool.Projection projection = poolElement.getProjection();
		boolean isRigid = projection == StructureTemplatePool.Projection.RIGID;
		MutableObject<VoxelShape> shapeHolder = new MutableObject<>();
		BoundingBox elementPieceBounds = elementPiece.getBoundingBox();
		int yMin = elementPieceBounds.minY();

		for(StructureTemplate.StructureBlockInfo jigsawBlock : poolElement.getShuffledJigsawBlocks(this.structureManager, elementPiece.getPosition(), elementPiece.getRotation(), this.random)) {
			handleJunctions(jigsawBlock, yMin, elementPieceBounds, shapeHolder, freeBounds, pieceDepth, doExpansionHack, isRigid, level, projection, elementPiece);
		}
	}

	@Nullable
	protected Pair<StructureTemplatePool, StructureTemplatePool> getPools(StructureTemplate.StructureBlockInfo jigsawInfo, int yMin) {
		StructureTemplatePool pool = getPool(new ResourceLocation(jigsawInfo.nbt.getString("pool")));

		if (pool == null)
			return null;

		StructureTemplatePool fallbackPool = getPool(pool.getFallback());

		return fallbackPool == null ? null : Pair.of(pool, fallbackPool);
	}

	@Nullable
	protected StructureTemplatePool getPool(ResourceLocation poolPath) {
		Optional<StructureTemplatePool> pool = this.poolRegistry.getOptional(poolPath);

		if (pool.isEmpty() || (pool.get().size() == 0 && !Objects.equals(poolPath, Pools.EMPTY.location()))) {
			Logging.logMessage(Level.WARN, "Invalid pool for structure piece: " + poolPath);

			return null;
		}

		return pool.get();
	}

	protected void handleJunctions(StructureTemplate.StructureBlockInfo jigsawBlock, int yMin, BoundingBox elementPieceBounds, MutableObject<VoxelShape> shapeHolder, MutableObject<VoxelShape> freeBounds, int pieceDepth, boolean doExpansionHack, boolean isRigid, LevelHeightAccessor level, StructureTemplatePool.Projection projection, PoolElementStructurePiece elementPiece) {
		BlockPos jigsawPos = jigsawBlock.pos;
		BlockPos jigsawFacingPos = jigsawPos.relative(JigsawBlock.getFrontFacing(jigsawBlock.state));
		int jigsawYDelta = jigsawPos.getY() - yMin;
		int baseHeight = -1;
		Pair<StructureTemplatePool, StructureTemplatePool> pools = getPools(jigsawBlock, yMin);

		if (pools == null)
			return;

		MutableObject<VoxelShape> pieceFreeBounds;
		List<StructurePoolElement> poolElements = Lists.newArrayList();

		if (elementPieceBounds.isInside(jigsawFacingPos)) {
			pieceFreeBounds = shapeHolder;

			if (shapeHolder.getValue() == null)
				shapeHolder.setValue(Shapes.create(AABB.of(elementPieceBounds)));
		}
		else {
			pieceFreeBounds = freeBounds;
		}

		if (pieceDepth != this.maxDepth)
			poolElements.addAll(pools.getFirst().getShuffledTemplates(this.random));

		poolElements.addAll(pools.getSecond().getShuffledTemplates(this.random));

		for(StructurePoolElement nextPoolElement : poolElements) {
			if (nextPoolElement == EmptyPoolElement.INSTANCE)
				break;

			for(Rotation rotation : Rotation.getShuffled(this.random)) {
				List<StructureTemplate.StructureBlockInfo> nextPoolElementJigsawBlocks = nextPoolElement.getShuffledJigsawBlocks(this.structureManager, BlockPos.ZERO, rotation, this.random);
				BoundingBox subElementBounds = nextPoolElement.getBoundingBox(this.structureManager, BlockPos.ZERO, rotation);
				int subPoolElementSize;

				if (doExpansionHack && subElementBounds.getYSpan() <= 16) {
					subPoolElementSize = nextPoolElementJigsawBlocks.stream().mapToInt(jigsaw -> {
						if (!subElementBounds.isInside(jigsaw.pos.relative(JigsawBlock.getFrontFacing(jigsaw.state))))
							return 0;

						ResourceLocation poolId = new ResourceLocation(jigsaw.nbt.getString("pool"));
						Optional<StructureTemplatePool> mainSubPool = this.poolRegistry.getOptional(poolId);
						Optional<StructureTemplatePool> fallbackSubPool = mainSubPool.flatMap(pool -> this.poolRegistry.getOptional(pool.getFallback()));
						int mainPoolSize = mainSubPool.map(pool -> pool.getMaxSize(this.structureManager)).orElse(0);
						int fallbackPoolSize = fallbackSubPool.map(pool -> pool.getMaxSize(this.structureManager)).orElse(0);

						return Math.max(mainPoolSize, fallbackPoolSize);
					}).max().orElse(0);
				}
				else {
					subPoolElementSize = 0;
				}

				for(StructureTemplate.StructureBlockInfo nextPoolElementJigsawBlock : nextPoolElementJigsawBlocks) {
					if (!JigsawBlock.canAttach(jigsawBlock, nextPoolElementJigsawBlock))
						continue;

					BlockPos nextPoolElementJigsawBlockPos = nextPoolElementJigsawBlock.pos;
					BlockPos alignedJigsawPos = jigsawFacingPos.subtract(nextPoolElementJigsawBlockPos);
					BoundingBox nextPoolElementBounds = nextPoolElement.getBoundingBox(this.structureManager, alignedJigsawPos, rotation);
					int nextPoolElementMinY = nextPoolElementBounds.minY();
					StructureTemplatePool.Projection nextPoolElementProjection = nextPoolElement.getProjection();
					boolean nextPoolElementRigid = nextPoolElementProjection == StructureTemplatePool.Projection.RIGID;
					int subJigsawPosY = nextPoolElementJigsawBlockPos.getY();
					int subPieceJigsawDelta = jigsawYDelta - subJigsawPosY + JigsawBlock.getFrontFacing(jigsawBlock.state).getStepY();
					int yAdjust;

					if (isRigid && nextPoolElementRigid) {
						yAdjust = yMin + subPieceJigsawDelta;
					}
					else {
						if (baseHeight == -1)
							baseHeight = this.chunkGenerator.getFirstFreeHeight(jigsawPos.getX(), jigsawPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, level);

						yAdjust = baseHeight - subJigsawPosY;
					}

					int finalYAdjust = yAdjust - nextPoolElementMinY;
					nextPoolElementBounds = nextPoolElementBounds.moved(0, finalYAdjust, 0);
					alignedJigsawPos = alignedJigsawPos.offset(0, finalYAdjust, 0);

					if (subPoolElementSize > 0)
						nextPoolElementBounds.encapsulate(new BlockPos(nextPoolElementBounds.minX(), nextPoolElementBounds.minY() + Math.max(subPoolElementSize + 1, nextPoolElementBounds.maxY() - nextPoolElementBounds.minY()), nextPoolElementBounds.minZ()));

					if (Shapes.joinIsNotEmpty(pieceFreeBounds.getValue(), Shapes.create(AABB.of(nextPoolElementBounds).deflate(0.25D)), BooleanOp.ONLY_SECOND))
						continue;

					pieceFreeBounds.setValue(Shapes.joinUnoptimized(pieceFreeBounds.getValue(), Shapes.create(AABB.of(nextPoolElementBounds)), BooleanOp.ONLY_FIRST));

					int groundLevelDelta = nextPoolElementRigid ? elementPiece.getGroundLevelDelta() - subPieceJigsawDelta : nextPoolElement.getGroundLevelDelta();
					PoolElementStructurePiece subPoolElementPiece = this.pieceFactory.create(this.structureManager, nextPoolElement, alignedJigsawPos, groundLevelDelta, rotation, nextPoolElementBounds);

					if (isRigid) {
						yAdjust = yMin + jigsawYDelta;
					}
					else if (nextPoolElementRigid) {
						yAdjust += subJigsawPosY;
					}
					else {
						if (baseHeight == -1)
							baseHeight = this.chunkGenerator.getFirstFreeHeight(jigsawPos.getX(), jigsawPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, level);

						yAdjust = baseHeight + subPieceJigsawDelta / 2;
					}

					elementPiece.addJunction(new JigsawJunction(jigsawFacingPos.getX(), yAdjust - jigsawYDelta + elementPiece.getGroundLevelDelta(), jigsawFacingPos.getZ(), subPieceJigsawDelta, nextPoolElementProjection));
					subPoolElementPiece.addJunction(new JigsawJunction(jigsawPos.getX(), yAdjust - subJigsawPosY + groundLevelDelta, jigsawPos.getZ(), -subPieceJigsawDelta, projection));
					this.pieces.add(subPoolElementPiece);

					if (pieceDepth + 1 <= this.maxDepth)
						this.placementQueue.addLast(new Entry(subPoolElementPiece, pieceFreeBounds, pieceDepth + 1));

					return;
				}
			}
		}
	}

	public static <C extends FeatureConfiguration> Optional<PieceGenerator<C>> createPieceGenerator(PieceGeneratorSupplier.Context<C> context, StructureTemplatePool templatePool, JigsawPlacement.PieceFactory pieceFactory, BlockPos rootPos, boolean doExpansionHack, boolean genOnSurface, int maxPieces) {
		StructureFeature.bootstrap();

		WorldgenRandom rand = new WorldgenRandom(new LegacyRandomSource(0L));
		RegistryAccess registries = context.registryAccess();
		ChunkGenerator chunkGenerator = context.chunkGenerator();
		StructureManager structureManager = context.structureManager();
		LevelHeightAccessor level = context.heightAccessor();
		Predicate<Holder<Biome>> predicate = context.validBiome();
		Registry<StructureTemplatePool> poolRegistry = registries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);

		rand.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);

		Rotation rotation = Rotation.getRandom(rand);
		StructurePoolElement startElement = templatePool.getRandomTemplate(rand);

		if (startElement == EmptyPoolElement.INSTANCE)
			return Optional.empty();

		PoolElementStructurePiece startPiece = pieceFactory.create(structureManager, startElement, rootPos, startElement.getGroundLevelDelta(), rotation, startElement.getBoundingBox(structureManager, rootPos, rotation));
		BoundingBox startBounds = startPiece.getBoundingBox();
		int centerX = (startBounds.maxX() + startBounds.minX()) / 2;
		int centerZ = (startBounds.maxZ() + startBounds.minZ()) / 2;
		int y;

		if (genOnSurface) {
			y = rootPos.getY() + chunkGenerator.getFirstFreeHeight(centerX, centerZ, Heightmap.Types.WORLD_SURFACE_WG, level);
		}
		else {
			y = rootPos.getY();
		}

		if (!predicate.test(chunkGenerator.getNoiseBiome(QuartPos.fromBlock(centerX), QuartPos.fromBlock(y), QuartPos.fromBlock(centerZ))))
			return Optional.empty();

		int yAdjust = startBounds.minY() + startPiece.getGroundLevelDelta();

		startPiece.move(0, y - yAdjust, 0);

		return Optional.of((pieceBuilder, context2) -> {
			List<PoolElementStructurePiece> pieces = Lists.newArrayList();

			pieces.add(startPiece);

			if (maxPieces > 0) {
				AABB safeBounds = new AABB(centerX - 80, level.getMinBuildHeight(), centerZ - 80, centerX + 80 + 1, level.getMaxBuildHeight(), centerZ + 80 + 1);
				JigsawAssembler assembler = new JigsawAssembler(poolRegistry, maxPieces, pieceFactory, chunkGenerator, structureManager, pieces, rand);

				assembler.placementQueue.addLast(new Entry(startPiece, new MutableObject<>(Shapes.join(Shapes.create(safeBounds), Shapes.create(AABB.of(startBounds)), BooleanOp.ONLY_FIRST)), 0));

				while(!assembler.placementQueue.isEmpty()) {
					Entry entry = assembler.placementQueue.removeFirst();

					assembler.tryPlacingChildren(entry.piece, entry.free, entry.depth, doExpansionHack, level);
				}

				pieces.forEach(pieceBuilder::addPiece);
			}
		});
	}

	public static void addPieces(RegistryAccess registries, PoolElementStructurePiece startPiece, int maxPieces, JigsawPlacement.PieceFactory pieceFactory, ChunkGenerator chunkGenerator, StructureManager structureManager, List<? super PoolElementStructurePiece> pieceList, Random rand, LevelHeightAccessor level) {
		Registry<StructureTemplatePool> registry = registries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
		JigsawAssembler assembler = new JigsawAssembler(registry, maxPieces, pieceFactory, chunkGenerator, structureManager, pieceList, rand);

		assembler.placementQueue.addLast(new Entry(startPiece, new MutableObject<>(Shapes.INFINITY), 0));

		while (!assembler.placementQueue.isEmpty()) {
			Entry nextPiece = assembler.placementQueue.removeFirst();

			assembler.tryPlacingChildren(nextPiece.piece, nextPiece.free, nextPiece.depth, false, level);
		}
	}

	protected static final class Entry {
		public final PoolElementStructurePiece piece;
		public final MutableObject<VoxelShape> free;
		public final int depth;

		public Entry(PoolElementStructurePiece piece, MutableObject<VoxelShape> free, int pieceDepth) {
			this.piece = piece;
			this.free = free;
			this.depth = pieceDepth;
		}
	}
}*/
