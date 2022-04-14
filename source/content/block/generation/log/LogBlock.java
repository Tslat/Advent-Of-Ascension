package net.tslat.aoa3.content.block.generation.log;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class LogBlock extends RotatedPillarBlock {
	private final Supplier<BlockState> strippedBlock;

	public LogBlock(MaterialColor mapColour) {
		this(mapColour, mapColour);
	}

	public LogBlock(MaterialColor mapColour, Supplier<Block> strippedBlock) {
		this(mapColour, mapColour, strippedBlock);
	}

	public LogBlock(MaterialColor mapColour, MaterialColor logEndMapColour) {
		this(mapColour, logEndMapColour, null);
	}

	public LogBlock(MaterialColor mapColour, MaterialColor logEndMapColour, Supplier<Block> strippedBlock) {
		super(new BlockUtil.CompactProperties(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? logEndMapColour : mapColour).stats(2f).get());

		this.strippedBlock = strippedBlock == null ? null : () -> strippedBlock.get().defaultBlockState();
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Nullable
	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if (strippedBlock == null)
			return super.getToolModifiedState(state, context, toolAction, simulate);

		return ToolActions.AXE_STRIP.equals(toolAction) ? strippedBlock.get() : null;
	}
}