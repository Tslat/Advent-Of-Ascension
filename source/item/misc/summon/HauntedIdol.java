package net.tslat.aoa3.item.misc.summon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.entity.boss.bane.EntityBane;
import net.tslat.aoa3.item.misc.SimpleItem;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;

public class HauntedIdol extends SimpleItem {
	public HauntedIdol() {
		super("HauntedIdol", "haunted_idol");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (world.provider.getDimension() == ConfigurationUtil.dimGreckon) {
				if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
					player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).sendPlayerMessage(StringUtil.getLocale("message.feedback.spawnBoss.difficultyFail"));
					return EnumActionResult.FAIL;
				}

				EntityBane bane = new EntityBane(world);
				BlockPos activatePos = pos.offset(facing);

				bane.setLocationAndAngles(activatePos.getX() + 0.5, activatePos.getY(), activatePos.getZ() + 0.5, itemRand.nextFloat() * 360f, 0.0f);
				world.spawnEntity(bane);
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.bane.spawn", player.getDisplayNameString()), player, 50);

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);
			}
			else {
				player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).sendPlayerMessage(StringUtil.getLocale("message.mob.bane.incorrectDimension"));
				return EnumActionResult.FAIL;
			}
		}

		return EnumActionResult.PASS;
	}
}
