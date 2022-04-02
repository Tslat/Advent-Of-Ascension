package net.tslat.aoa3.content.block.generation.log;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
	public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolAction action) {
		if (strippedBlock == null)
			return super.getToolModifiedState(state, world, pos, player, stack, action);

		return ToolActions.AXE_STRIP.equals(action) ? strippedBlock.get() : null;
	}
}