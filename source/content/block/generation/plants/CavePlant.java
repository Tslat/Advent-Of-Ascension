package net.tslat.aoa3.content.block.generation.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.PlantType;

public class CavePlant extends BushBlock {
	public CavePlant(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return simpleCodec(CavePlant::new);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.isSolidRender(level, pos);
	}

	@Override
	public PlantType getPlantType(BlockGetter level, BlockPos pos) {
		return PlantType.CAVE;
	}
}
