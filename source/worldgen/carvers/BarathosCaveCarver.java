package net.tslat.aoa3.worldgen.carvers;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.function.Function;

public class BarathosCaveCarver extends CaveWorldCarver {
	public BarathosCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int maxHeight) {
		super(config, maxHeight);
	}

	@Override
	protected boolean canCarveBlock(BlockState state, BlockState aboveState) {
		Block block = state.getBlock();

		return (block == AoABlocks.BARON_GROUND.get() || block == AoABlocks.BARON_STONE.get() || block == AoABlocks.HELLSTONE.get()) && !aboveState.getFluidState().isTagged(FluidTags.WATER);
	}
}
