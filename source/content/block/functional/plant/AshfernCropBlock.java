package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class AshfernCropBlock extends AoACropBlock {
	public AshfernCropBlock(Properties properties, Supplier<Item> seedItem) {
		super(properties, seedItem);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(Blocks.BASALT);
	}
}
