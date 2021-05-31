package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public abstract class BossAltarBlock extends Block {
	public BossAltarBlock(AbstractBlock.Properties properties) {
		super(properties);
	}

	public BossAltarBlock(MaterialColor mapColour) {
		this(new BlockUtil.CompactProperties(Material.STONE, mapColour).stats(35f, 1000f).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (getActivationItem() != null && heldItem.getItem() != getActivationItem())
			return ActionResultType.PASS;

		if (player instanceof ServerPlayerEntity) {
			if (getActivationItem() == null || (heldItem.getItem() == getActivationItem())) {
				if (world.getDifficulty() == Difficulty.PEACEFUL) {
					PlayerUtil.notifyPlayer((ServerPlayerEntity)player, "message.feedback.spawnBoss.difficultyFail");
					return ActionResultType.FAIL;
				}
				else if (checkActivationConditions(player, hand, state, pos)) {
					if (!player.isCreative())
						heldItem.shrink(1);

					doActivationEffect(player, hand, state, pos);
				}
			}
		}

		return ActionResultType.SUCCESS;
	}

	@Nullable
	protected abstract Item getActivationItem();

	protected abstract void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos);

	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return true;
	}

	protected void sendSpawnMessage(PlayerEntity player, TranslationTextComponent msg, BlockPos pos) {
		PlayerUtil.messageAllPlayersInRange(msg, player.level, pos, 50);
	}
}
