package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.container.MendingTableContainer;
import net.tslat.aoa3.util.BlockUtil;

public class MendingTable extends Block {
	public MendingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_RED).stats(5f, 10f).harvestTool(ToolType.PICKAXE).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player instanceof ServerPlayerEntity)
			MendingTableContainer.openContainer((ServerPlayerEntity)player, pos);

		return ActionResultType.SUCCESS;
	}
}
