package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

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
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
		PlantType type = plantable.getPlantType(world, pos.relative(facing));

		return type == PlantType.CROP;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(WELL_FERTILISED);
	}
}
