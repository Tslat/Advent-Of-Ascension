package net.tslat.aoa3.content.world.gen.structure.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.tslat.aoa3.common.registration.worldgen.AoAStructurePlacements;

import java.util.Optional;

public class SingleStructurePlacement extends StructurePlacement {
	public static final Codec<SingleStructurePlacement> CODEC = RecordCodecBuilder.<SingleStructurePlacement>mapCodec(codec -> codec.group(
			Codec.INT.fieldOf("salt").forGetter(SingleStructurePlacement::salt),
			BlockPos.CODEC.fieldOf("pos").forGetter(SingleStructurePlacement::pos)
	).apply(codec, SingleStructurePlacement::new)).codec();

	private final BlockPos pos;
	private final ChunkPos chunkPos;

	public SingleStructurePlacement(int salt, BlockPos pos) {
		super(Vec3i.ZERO, FrequencyReductionMethod.DEFAULT, 1, salt, Optional.empty());

		this.pos = pos;
		this.chunkPos = new ChunkPos(pos);
	}

	public BlockPos pos() {
		return this.pos;
	}

	@Override
	public int salt() {
		return super.salt();
	}

	@Override
	protected boolean isPlacementChunk(ChunkGeneratorStructureState chunkGenerator, int chunkX, int chunkZ) {
		return chunkPos.x == chunkX && chunkPos.z == chunkZ;
	}

	@Override
	public BlockPos getLocatePos(ChunkPos chunkPos) {
		return pos;
	}

	@Override
	public StructurePlacementType<?> type() {
		return AoAStructurePlacements.SINGLE_STRUCTURE.get();
	}
}
