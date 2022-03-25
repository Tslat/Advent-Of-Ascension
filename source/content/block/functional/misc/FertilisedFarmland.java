package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class FertilisedFarmland extends FarmlandBlock {
	public static final BooleanProperty WELL_FERTILISED = BooleanProperty.create("well_fertilised");

	public FertilisedFarmland() {
		super(AbstractBlock.Properties.of(Material.DIRT).randomTicks().strength(0.6f).sound(SoundType.GRAVEL).isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true));

		registerDefaultState(defaultBlockState().setValue(WELL_FERTILISED, false));
	}

	@Override
	public boolean isFertile(BlockState state, IBlockReader world, BlockPos pos) {
		return state.getValue(MOISTURE) > 0;
	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
		PlantType type = plantable.getPlantType(world, pos.relative(facing));

		return type == PlantType.CROP;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(WELL_FERTILISED);
	}
}
