/*
package net.tslat.aoa3.content.world.gen.structure.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.LevelReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.StructurePlaceSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.tslat.aoa3.content.block.functional.misc.TrophyBlock;
import net.tslat.aoa3.common.registration.worldgen.AoAStructureProcessors;


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
	public Template.BlockInfo process(LevelReader world, BlockPos templatePos, BlockPos structureCenter, Template.BlockInfo blockInfo, Template.BlockInfo relativisedBlockInfo, StructurePlaceSettings placementSettings, @Nullable Template template) {
		if (blockInfo.state.getBlock() == trophyBlock.getBlock())
			return new Template.BlockInfo(relativisedBlockInfo.pos, relativisedBlockInfo.state, TrophyBlock.getTagForEntity(entityType).getCompound("BlockEntityTag"));

		return relativisedBlockInfo;
	}
}
*/
