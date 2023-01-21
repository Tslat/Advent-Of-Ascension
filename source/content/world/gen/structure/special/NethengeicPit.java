package net.tslat.aoa3.content.world.gen.structure.special;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.tslat.aoa3.content.world.gen.structure.AoAJigsawAssembler;
import net.tslat.aoa3.content.world.gen.structure.TopAlignedStructure;

import java.util.List;
import java.util.Optional;

import static net.tslat.aoa3.content.world.gen.structure.AoAStructure.Settings.aoaSettings;

public class NethengeicPit extends TopAlignedStructure {
	public static final Codec<NethengeicPit> CODEC = RecordCodecBuilder.<NethengeicPit>mapCodec(codec ->
			codec.group(aoaSettings())
					.apply(codec, NethengeicPit::new)).codec();

	public NethengeicPit(Settings settings) {
		super(settings);
	}

	@Override
	protected AoAJigsawAssembler getJigsawAssembler() {
		return new AoAJigsawAssembler() {
			@Override
			protected Optional<GenerationStub> buildGenerationStub(PoolElementStructurePiece startPiece, BoundingBox startPieceBounds, GenerationContext genContext, int startX, int startY, int startZ, int maxPieces, int maxRadius) {
				WorldgenRandom rand = genContext.random();

				for (int i = 0; i < 5; i++) {
					int x = rand.nextInt(startPieceBounds.maxX() - startPieceBounds.minX());
					int z = rand.nextInt(startPieceBounds.maxZ() - startPieceBounds.minZ());

					if (genContext.chunkGenerator().getBaseColumn(startX + x, startZ + z, genContext.heightAccessor(), genContext.randomState()).getBlock(nextGenHeight).getBlock() != Blocks.LAVA)
						return Optional.empty();
				}

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

					pieceBuilder.offsetPiecesVertically(nextGenHeight - startPieceBounds.maxY());
				}));
			}
		};
	}

	private boolean isInLava(GenerationContext context, GenerationStub genStub) {
		return true;
	}
}
