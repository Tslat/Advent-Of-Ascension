package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.library.scheduling.async.ShadowlordSpawnTask;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.concurrent.TimeUnit;

public class ShadowAltar extends BossAltarBlock {
	public ShadowAltar() {
		super(MaterialColor.COLOR_BLACK);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		if (!player.level.isClientSide) {
			new ShadowlordSpawnTask(player, blockPos).schedule(1, TimeUnit.SECONDS);

			if (player.hasEffect(Effects.NIGHT_VISION) && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.DUSTOPIA_REALMSTONE.get()));
		}
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.ABYSS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.BOOK_OF_SHADOWS.get();
	}
}
