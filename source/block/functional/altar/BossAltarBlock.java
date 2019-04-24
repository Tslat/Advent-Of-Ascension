package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.utils.StringUtil;

public abstract class BossAltarBlock extends BasicBlock {
	public BossAltarBlock(String name, String registryName) {
		super(name, registryName, Material.ROCK, -1f, 999999999f);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).sendPlayerMessage(StringUtil.getLocale("message.feedback.spawnBoss.difficultyFail"));
			return false;
		}

		ItemStack heldItem = player.getHeldItem(hand);

		if (!world.isRemote && (getActivationItem() == null || (!heldItem.isEmpty() && heldItem.getItem() == getActivationItem())) && checkActivationConditions(player, hand, state, pos)) {
			if (!player.capabilities.isCreativeMode)
				heldItem.shrink(1);

			doActivationEffect(player, hand, state, pos);
		}

		return true;
	}

	protected abstract Item getActivationItem();

	protected abstract void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos);

	protected boolean checkActivationConditions(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos pos) {
		return true;
	}

	protected void sendSpawnMessage(EntityPlayer player, ITextComponent msg, BlockPos pos) {
		StringUtil.sendMessageWithinRadius(msg, player, 50);
	}
}
