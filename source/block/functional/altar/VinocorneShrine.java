package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;

public class VinocorneShrine extends BossAltarBlock {
	public VinocorneShrine() {
		super(MaterialColor.GREEN);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return player.world.getBlockState(pos.up()).getMaterial().isReplaceable();
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		player.world.setBlockState(blockPos.up(), AoABlocks.LIVING_GROWTH.get().getDefaultState());
		player.world.getPendingBlockTicks().scheduleTick(blockPos.up(), AoABlocks.LIVING_GROWTH.get(), 40);

		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.vinocorne.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(world, player, pos, state, te, stack);

		if (world.getBlockState(pos.up()).getBlock() == AoABlocks.LIVING_GROWTH.get())
			world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.PETALS.get();
	}
}