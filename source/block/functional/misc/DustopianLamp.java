package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;

public class DustopianLamp extends Block {
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

	public DustopianLamp() {
		super(BlockUtil.generateBlockProperties(Material.GLASS, MaterialColor.GRAY, 5f, 10f, SoundType.GLASS).lightValue(14));

		setDefaultState(getDefaultState().with(LIT, false));
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		return state.get(LIT) ? 14 : 0;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getHeldItem(hand).getItem() == AoAItems.DARKLY_POWDER.get() && !state.get(LIT)) {
			if (!world.isRemote()) {
				if (!player.isCreative())
					player.getHeldItem(hand).shrink(1);

				world.setBlockState(pos, getDefaultState().with(LIT, true));
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}
}
