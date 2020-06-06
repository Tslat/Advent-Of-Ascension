package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.clunkhead.EntityClunkhead;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class ClunkheadAltar extends BossAltarBlock {
	public ClunkheadAltar() {
		super("ClunkheadAltar", "clunkhead_altar");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = player.getHeldItem(hand);

		if (getActivationItem() != null && heldItem.getItem() != getActivationItem())
			return false;

		if (!world.isRemote) {
			if (getActivationItem() == null || (heldItem.getItem() == getActivationItem())) {
				if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
					PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");
					return false;
				}
				else if (checkActivationConditions(player, hand, state, pos)) {
					if (!player.capabilities.isCreativeMode)
						heldItem.damageItem(1, player);

					doActivationEffect(player, hand, state, pos);
				}
			}
		}

		return true;
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityClunkhead clunkhead = new EntityClunkhead(player.world);

		clunkhead.setPositionAndUpdate(blockPos.getX() - 4, blockPos.up().getY(), blockPos.getZ() + 5);
		player.world.spawnEntity(clunkhead);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.clunkhead.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.MEGA_RUNE_STONE;
	}
}
