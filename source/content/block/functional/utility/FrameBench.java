package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.container.FrameBenchContainer;
import net.tslat.aoa3.util.BlockUtil;

public class FrameBench extends Block {
	public FrameBench() {
		super(new BlockUtil.CompactProperties(Material.WOOD, MaterialColor.WOOD).stats(2.5f, 0f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (player instanceof ServerPlayer)
			FrameBenchContainer.openContainer((ServerPlayer)player, pos);

		return InteractionResult.SUCCESS;
	}
}