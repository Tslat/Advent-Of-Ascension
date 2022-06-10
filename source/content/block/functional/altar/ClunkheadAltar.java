package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

public class ClunkheadAltar extends BossAltarBlock {
	public ClunkheadAltar() {
		super(MaterialColor.COLOR_LIGHT_BLUE);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (getActivationItem() != null && heldItem.getItem() != getActivationItem())
			return InteractionResult.PASS;

		if (player instanceof ServerPlayer) {
			if (getActivationItem() == null || (heldItem.getItem() == getActivationItem())) {
				if (world.getDifficulty() == Difficulty.PEACEFUL) {
					PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.difficultyFail"));
					return InteractionResult.FAIL;
				}
				else if (checkActivationConditions(player, hand, state, pos)) {
					if (!player.isCreative())
						ItemUtil.damageItem(heldItem, player, hand);

					doActivationEffect(player, hand, state, pos);
				}
			}
		}

		return InteractionResult.SUCCESS;
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*ClunkheadEntity clunkhead = new ClunkheadEntity(AoAMobs.CLUNKHEAD.get(), player.level);

		clunkhead.teleportTo(blockPos.getX() - 1, blockPos.above().getY() + 1, blockPos.getZ() - 1);
		player.level.addFreshEntity(clunkhead);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.CLUNKHEAD.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.RUNANDOR.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.MEGA_RUNE_STONE.get();
	}
}
