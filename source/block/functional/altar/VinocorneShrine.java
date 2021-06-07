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
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class VinocorneShrine extends BossAltarBlock {
	public VinocorneShrine() {
		super(MaterialColor.COLOR_GREEN);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.GARDENCIA.key) && player.level.getBlockState(pos.above()).getMaterial().isReplaceable();
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		player.level.setBlockAndUpdate(blockPos.above(), AoABlocks.LIVING_GROWTH.get().defaultBlockState());
		player.level.getBlockTicks().scheduleTick(blockPos.above(), AoABlocks.LIVING_GROWTH.get(), 40);

		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.VINOCORNE.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.playerDestroy(world, player, pos, state, te, stack);

		if (world.getBlockState(pos.above()).getBlock() == AoABlocks.LIVING_GROWTH.get())
			world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.PETALS.get();
	}
}