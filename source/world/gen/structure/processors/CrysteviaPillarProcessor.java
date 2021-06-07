package net.tslat.aoa3.world.gen.structure.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.tslat.aoa3.common.registration.worldgen.AoAStructureProcessors;

import javax.annotation.Nullable;

public class CrysteviaPillarProcessor extends StructureProcessor {
	public static final Codec<CrysteviaPillarProcessor> CODEC = RecordCodecBuilder.create(builder ->
			builder.group(
					BlockState.CODEC.fieldOf("pillar_block").forGetter(instance -> instance.pillarBlock)
			).apply(builder, CrysteviaPillarProcessor::new));

	private final BlockState pillarBlock;

	public CrysteviaPillarProcessor(BlockState pillarBlock) {
		this.pillarBlock = pillarBlock;
	}

	@Override
	protected IStructureProcessorType<CrysteviaPillarProcessor> getType() {
		return AoAStructureProcessors.CRYSTEVIA_PILLAR;
	}

	@Nullable
	@Override
	public Template.BlockInfo process(IWorldReader world, BlockPos pos1, BlockPos pos2, Template.BlockInfo blockInfo, Template.BlockInfo relativisedBlockInfo, PlacementSettings placementSettings, @Nullable Template template) {
		if (blockInfo.state.is(Blocks.BEDROCK)) {
			BlockPos.Mutable placementPos = new BlockPos.Mutable().set(relativisedBlockInfo.pos);
			BlockState testBlock = world.getBlockState(placementPos);
			world.getChunk(placementPos).setBlockState(placementPos, pillarBlock, false);

			while (testBlock.getBlock().isAir(testBlock, world, placementPos) && placementPos.getY() >= 0) {
				world.getChunk(placementPos).setBlockState(placementPos, pillarBlock, false);
				placementPos.move(Direction.DOWN);
				testBlock = world.getBlockState(placementPos);
			}

			return new Template.BlockInfo(relativisedBlockInfo.pos, pillarBlock, null);
		}

		return relativisedBlockInfo;
	}
}
