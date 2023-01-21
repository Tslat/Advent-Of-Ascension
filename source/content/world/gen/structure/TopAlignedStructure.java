package net.tslat.aoa3.content.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.List;
import java.util.Optional;

import static net.tslat.aoa3.content.world.gen.structure.AoAStructure.Settings.aoaSettings;

public class TopAlignedStructure extends AoAStructure {
	protected int nextGenHeight = 0;

	public static final Codec<TopAlignedStructure> CODEC = RecordCodecBuilder.<TopAlignedStructure>mapCodec(codec ->
			codec.group(aoaSettings())
					.apply(codec, TopAlignedStructure::new)).codec();

	public TopAlignedStructure(Settings settings) {
		super(settings);
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(GenerationContext genContext) {
		ChunkPos chunkpos = genContext.chunkPos();
		this.nextGenHeight = this.settings.startHeight().sample(genContext.random(), new WorldGenerationContext(genContext.chunkGenerator(), genContext.heightAccessor()));
		BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), this.nextGenHeight, chunkpos.getMinBlockZ());

		return assembler.addPieces(genContext, this.settings.startPool(), this.settings.startJigsawName(), this.settings.maxPieces(), blockpos, this.settings.startHeightmap(), 128);
	}

	@Override
	protected AoAJigsawAssembler getJigsawAssembler() {
		return new AoAJigsawAssembler() {
			@Override
			protected Optional<GenerationStub> buildGenerationStub(PoolElementStructurePiece startPiece, BoundingBox startPieceBounds, GenerationContext genContext, int startX, int startY, int startZ, int maxPieces, int maxRadius) {
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

					pieceBuilder.offsetPiecesVertically(TopAlignedStructure.this.nextGenHeight - startPieceBounds.maxY());
				}));
			}
		};
	}
}
