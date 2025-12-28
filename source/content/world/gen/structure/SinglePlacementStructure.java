package net.tslat.aoa3.content.world.gen.structure;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.tslat.aoa3.common.registration.worldgen.AoAStructureTypes;

import java.util.Optional;

public class SinglePlacementStructure extends AoAStructure {
	public static final MapCodec<SinglePlacementStructure> CODEC = RecordCodecBuilder.mapCodec(codec -> codec.group(
			Structure.settingsCodec(codec),
			JigsawSettings.jigsawSettingsCodec(),
			Settings.extraSettingsCodec(),
			BlockPos.CODEC.fieldOf("position").forGetter(SinglePlacementStructure::pos)
	).apply(codec, SinglePlacementStructure::new));

	private final BlockPos pos;

	public SinglePlacementStructure(StructureSettings structureSettings, JigsawSettings jigsawSettings, Settings extraSettings, BlockPos pos) {
		super(structureSettings, jigsawSettings, extraSettings);

		this.pos = pos;
	}

	@Override
	public StructureType<? extends AoAStructure> type() {
		return AoAStructureTypes.SINGLE_PLACEMENT.get();
	}

	public BlockPos pos() {
		return this.pos;
	}

	@Override
	protected AoAJigsawAssembler getJigsawAssembler() {
		return new AoAJigsawAssembler() {
			@Override
			protected boolean ignoreRotations() {
				return true;
			}
		};
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(GenerationContext genContext) {

		return this.assembler.addPieces(genContext, this.jigsawSettings.startPool(), this.jigsawSettings.startJigsawName(), this.jigsawSettings.maxPieces(), pos,
										this.jigsawSettings.startHeightmap(), 128, this.jigsawSettings.padding(), this.jigsawSettings.liquidSettings());
	}

	@Override
	protected BlockPos findStartPos(GenerationContext genContext) {
		BlockPos pos = this.pos;

		if (!(genContext.heightAccessor() instanceof ProtoChunk)) {
			ChunkPos chunkPos = genContext.chunkPos();
			int x = chunkPos.getMiddleBlockX();
			int z = chunkPos.getMiddleBlockZ();
			int y = genContext.chunkGenerator().getFirstOccupiedHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, genContext.heightAccessor(), genContext.randomState());
			pos = new BlockPos(x, y, z);
		}

		return pos;
	}
}
