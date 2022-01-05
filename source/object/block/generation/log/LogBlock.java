package net.tslat.aoa3.object.block.generation.log;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
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
		super(new BlockUtil.CompactProperties(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? logEndMapColour : mapColour).stats(2f).tool(ToolType.AXE).get());

		this.strippedBlock = strippedBlock == null ? null : () -> strippedBlock.get().defaultBlockState();
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 5;
	}

	@Nullable
	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (strippedBlock == null)
			return super.getToolModifiedState(state, world, pos, player, stack, toolType);

		return toolType == ToolType.AXE ? strippedBlock.get() : null;
	}
}