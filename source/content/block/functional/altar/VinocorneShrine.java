package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class VinocorneShrine extends BossAltarBlock {
	public VinocorneShrine() {
		super(MaterialColor.COLOR_GREEN);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.GARDENCIA.key) && player.level.getBlockState(pos.above()).getMaterial().isReplaceable();
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		player.level.setBlockAndUpdate(blockPos.above(), AoABlocks.LIVING_GROWTH.get().defaultBlockState());
		player.level.scheduleTick(blockPos.above(), AoABlocks.LIVING_GROWTH.get(), 40);

		//sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.VINOCORNE.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
		super.playerDestroy(world, player, pos, state, te, stack);

		if (world.getBlockState(pos.above()).getBlock() == AoABlocks.LIVING_GROWTH.get())
			world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.PETALS.get();
	}
}