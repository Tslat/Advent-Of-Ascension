package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.item.misc.WaterloggedItem;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

public class DecloggingTable extends Block {
	public DecloggingTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.ICE).stats(10f, 15f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() instanceof WaterloggedItem) {
			if (!world.isClientSide()) {
				ItemStack stack = player.getItemInHand(hand);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(((WaterloggedItem)stack.getItem()).getDecloggedItem()));
				stack.shrink(1);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_DECLOGGING_TABLE_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}
