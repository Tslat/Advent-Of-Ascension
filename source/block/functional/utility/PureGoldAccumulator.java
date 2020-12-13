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
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

public class PureGoldAccumulator extends Block {
	public PureGoldAccumulator() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.GOLD, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getHeldItem(hand).getItem() == AoAItems.PURE_GOLD.get()) {
			if (player instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);

				if (!player.isCreative())
					player.getHeldItem(hand).shrink(1);

				plData.stats().addTribute(Deities.PLUTON, 20);

				if (plData.stats().getTribute(Deities.PLUTON) == 200)
					plData.sendThrottledChatMessage("message.feedback.immortallisProgression.pureGoldEnd", player.getDisplayName().getFormattedText());
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
