package net.tslat.aoa3.item.misc.summon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.entity.boss.nethengeicwither.EntityNethengeicWither;
import net.tslat.aoa3.item.misc.SimpleItem;
import net.tslat.aoa3.utils.StringUtil;

public class NethengeicCallstone extends SimpleItem {
	public NethengeicCallstone() {
		super("NethengeicCallstone", "nethengeic_callstone");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (world.provider.getDimension() == -1) {
				if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
					player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).sendPlayerMessage(StringUtil.getLocale("message.feedback.spawnBoss.difficultyFail"));
					return EnumActionResult.FAIL;
				}

				EntityNethengeicWither nethengeicWither = new EntityNethengeicWither(world);
				BlockPos activatePos = pos.offset(facing);

				nethengeicWither.setLocationAndAngles(activatePos.getX() + 0.5, activatePos.getY(), activatePos.getZ() + 0.5, itemRand.nextFloat() * 360f, 0.0f);
				world.spawnEntity(nethengeicWither);
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.nethengeicWither.spawn", player.getDisplayNameString()), player, 50);

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);
			}
			else {
				player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).sendPlayerMessage(StringUtil.getLocale("message.mob.nethengeicWither.incorrectDimension"));
				return EnumActionResult.FAIL;
			}
		}

		return EnumActionResult.PASS;
	}
}
