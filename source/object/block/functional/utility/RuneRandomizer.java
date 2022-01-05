package net.tslat.aoa3.object.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LootUtil;

public class RuneRandomizer extends Block {
	public RuneRandomizer() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_CYAN).stats(10f, 15f).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (heldItem.getItem() == AoAItems.UNPOWERED_RUNE.get() || heldItem.getItem() == AoAItems.CHARGED_RUNE.get()) {
			if (player instanceof ServerPlayerEntity) {
				if (!player.isCreative())
					heldItem.shrink(1);

				ItemUtil.givePlayerMultipleItems(player, LootUtil.generateLoot((ServerWorld)world, new ResourceLocation(AdventOfAscension.MOD_ID, "misc/rune_randomizer"), LootUtil.getGiftContext((ServerWorld)world, BlockUtil.posToVec(pos), player)));

				player.level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_RUNE_RANDOMIZER_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
