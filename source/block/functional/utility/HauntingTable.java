package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

public class HauntingTable extends Block {
	public HauntingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.TERRACOTTA_PURPLE).stats(10f, 15f).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.GHOULASM.get()) {
			if (!world.isClientSide()) {
				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.PRIMED_GHOULASM.get()));
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_HAUNTING_TABLE_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
