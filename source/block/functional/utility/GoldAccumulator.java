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

public class GoldAccumulator extends Block {
	public GoldAccumulator() {
		super(BlockUtil.generateBlockProperties(Material.STONE, MaterialColor.GOLD, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.IMPURE_GOLD.get()) {
			if (player instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);

				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);

				plData.stats().addTribute(Deities.PLUTON, Math.min(100 - plData.stats().getTribute(Deities.PLUTON), 20));

				if (plData.stats().getTribute(Deities.PLUTON) >= 100)
					plData.sendThrottledChatMessage("message.feedback.immortallisProgression.goldEnd", player.getDisplayName());
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
