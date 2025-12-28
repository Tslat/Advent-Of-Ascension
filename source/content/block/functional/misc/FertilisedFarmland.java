package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.util.TriState;

public class FertilisedFarmland extends FarmBlock {
	public static final BooleanProperty WELL_FERTILISED = BooleanProperty.create("well_fertilised");

	public FertilisedFarmland(BlockBehaviour.Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(WELL_FERTILISED, false));
	}

	@Override
	public boolean isFertile(BlockState state, BlockGetter world, BlockPos pos) {
		return state.getValue(MOISTURE) > 0;
	}

	@Override
	public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
		if (state.is(BlockTags.CROPS))
			return TriState.TRUE;

		return TriState.DEFAULT;
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		entity.causeFallDamage(fallDistance, 1f, entity.damageSources().fall());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(WELL_FERTILISED);
	}
}
