package net.tslat.aoa3.world.gen.structure.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.tslat.aoa3.object.block.functional.misc.TrophyBlock;
import net.tslat.aoa3.common.registration.worldgen.AoAStructureProcessors;

import javax.annotation.Nullable;

public class TrophyProcessor extends StructureProcessor {
	public static final Codec<TrophyProcessor> CODEC = RecordCodecBuilder.create(builder ->
			builder.group(
					BlockState.CODEC.fieldOf("trophy").forGetter(instance -> instance.trophyBlock),
					Registry.ENTITY_TYPE.fieldOf("entity").forGetter(instance -> instance.entityType)
			).apply(builder, TrophyProcessor::new));

	private final BlockState trophyBlock;
	private final EntityType<?> entityType;

	public TrophyProcessor(BlockState trophyBlock, EntityType<?> entityType) {
		this.trophyBlock = trophyBlock;
		this.entityType = entityType;
	}

	@Override
	protected IStructureProcessorType<TrophyProcessor> getType() {
		return AoAStructureProcessors.TROPHY_PROCESSOR;
	}

	@Nullable
	@Override
	public Template.BlockInfo process(IWorldReader world, BlockPos templatePos, BlockPos structureCenter, Template.BlockInfo blockInfo, Template.BlockInfo relativisedBlockInfo, PlacementSettings placementSettings, @Nullable Template template) {
		if (blockInfo.state.getBlock() == trophyBlock.getBlock())
			return new Template.BlockInfo(relativisedBlockInfo.pos, relativisedBlockInfo.state, TrophyBlock.getTagForEntity(entityType).getCompound("BlockEntityTag"));

		return relativisedBlockInfo;
	}
}
