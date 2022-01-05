package net.tslat.aoa3.object.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
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
		super(new BlockUtil.CompactProperties(Material.GLASS, MaterialColor.COLOR_GRAY).stats(5f, 10f).light(state -> state.getValue(LIT) ? 14 : 0).get());

		registerDefaultState(defaultBlockState().setValue(LIT, false));
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		return state.getValue(LIT) ? 14 : 0;
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.DARKLY_POWDER.get() && !state.getValue(LIT)) {
			if (!world.isClientSide()) {
				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);

				world.setBlockAndUpdate(pos, defaultBlockState().setValue(LIT, true));
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}
}
