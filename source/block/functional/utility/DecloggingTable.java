package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.misc.WaterloggedItem;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

public class DecloggingTable extends Block {
	public DecloggingTable() {
		super(BlockUtil.generateBlockProperties(Material.STONE, MaterialColor.ICE, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getItemInHand(hand).getItem() instanceof WaterloggedItem) {
			if (!world.isClientSide()) {
				ItemStack stack = player.getItemInHand(hand);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(((WaterloggedItem)stack.getItem()).getDecloggedItem()));
				stack.shrink(1);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_DECLOGGING_TABLE_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
