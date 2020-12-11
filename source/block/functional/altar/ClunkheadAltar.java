package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.ClunkheadEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class ClunkheadAltar extends BossAltarBlock {
	public ClunkheadAltar() {
		super(MaterialColor.LIGHT_BLUE);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldItem = player.getHeldItem(hand);

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
						ItemUtil.damageItem(heldItem, player, hand);

					doActivationEffect(player, hand, state, pos);
				}
			}
		}

		return ActionResultType.SUCCESS;
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		ClunkheadEntity clunkhead = new ClunkheadEntity(AoAEntities.Mobs.CLUNKHEAD.get(), player.world);

		clunkhead.setPositionAndUpdate(blockPos.getX() - 4, blockPos.up().getY(), blockPos.getZ() + 5);
		player.world.addEntity(clunkhead);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.clunkhead.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.MEGA_RUNE_STONE.get();
	}
}
