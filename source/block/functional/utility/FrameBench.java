package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.container.FrameBenchContainer;
import net.tslat.aoa3.util.BlockUtil;

public class FrameBench extends Block {
	public FrameBench() {
		super(BlockUtil.generateBlockProperties(Material.WOOD, MaterialColor.WOOD, 2.5f, 0, SoundType.WOOD));
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (player instanceof ServerPlayerEntity)
			FrameBenchContainer.openContainer((ServerPlayerEntity)player, pos);

		return ActionResultType.SUCCESS;
	}
}