package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public abstract class BossAltarBlock extends Block {
	public BossAltarBlock(Block.Properties properties) {
		super(properties);
	}

	public BossAltarBlock(MaterialColor mapColour) {
		this(new BlockUtil.CompactProperties(Material.STONE, mapColour).stats(35f, 1000f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (getActivationItem() != null && heldItem.getItem() != getActivationItem())
			return InteractionResult.PASS;

		if (level.getDifficulty() == Difficulty.PEACEFUL) {
			if (!level.isClientSide)
				PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.difficultyFail"));

			return InteractionResult.FAIL;
		}

		if (player instanceof ServerPlayer) {
			if (getActivationItem() == null || heldItem.getItem() == getActivationItem()) {
				if (checkActivationConditions(player, hand, state, pos)) {
					if (!player.getAbilities().instabuild)
						heldItem.shrink(1);

					doActivationEffect(player, hand, state, pos);
				}
			}
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Nullable
	protected abstract Item getActivationItem();

	protected abstract void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos);

	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return true;
	}

	protected void sendSpawnMessage(Player player, MutableComponent msg, BlockPos pos) {
		PlayerUtil.messageAllPlayersInRange(msg, player.level, pos, 50);
	}
}
