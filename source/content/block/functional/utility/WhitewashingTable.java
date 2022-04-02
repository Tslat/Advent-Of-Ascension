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
import net.tslat.aoa3.common.container.WhitewashingTableContainer;
import net.tslat.aoa3.util.BlockUtil;

public class WhitewashingTable extends Block {
	public WhitewashingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.TERRACOTTA_WHITE).stats(5f, 10f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player instanceof ServerPlayer)
			WhitewashingTableContainer.openContainer((ServerPlayer)player, pos);

		return InteractionResult.SUCCESS;
	}
}
