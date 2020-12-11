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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

public class StrangeBlock extends Block {
	public StrangeBlock() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.BLUE, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!player.getHeldItem(hand).isEmpty()) {
			ItemStack stack = player.getHeldItem(hand);

			if (stack.getItem() == AoAItems.BLUE_STRANGE_STONE.get()) {
				if (!player.isCreative())
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAWeapons.SHYRE_BLASTER.get()));
				return ActionResultType.SUCCESS;
			}
			else if (stack.getItem() == AoAItems.YELLOW_STRANGE_STONE.get()) {
				if (!player.isCreative())
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAWeapons.AMPLIFIER.get()));
				return ActionResultType.SUCCESS;
			}
			else if (stack.getItem() == AoAItems.WHITE_STRANGE_STONE.get()) {
				if (!player.isCreative())
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAWeapons.SUBLIMUS.get()));
				return ActionResultType.SUCCESS;
			}

			return ActionResultType.PASS;
		}

		return ActionResultType.PASS;
	}
}
