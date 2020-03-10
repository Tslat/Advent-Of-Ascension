package net.tslat.aoa3.item.misc.summon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.entity.boss.gyro.EntityGyrocopter;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ToyGyrocopter extends Item {
	public ToyGyrocopter() {
		setTranslationKey("ToyGyrocopter");
		setRegistryName("aoa3:toy_gyrocopter");
		setCreativeTab(CreativeTabsRegister.miscTab);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.getDifficulty() != EnumDifficulty.PEACEFUL) {
			if (!world.isRemote) {
				world.spawnEntity(new EntityGyrocopter(player));
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.gyro.spawn", player.getDisplayNameString()), player, 50);

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);
			}

			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		else if (!world.isRemote) {
			PlayerUtil.notifyPlayer((EntityPlayerMP)player, "message.feedback.spawnBoss.difficultyFail", TextFormatting.RED);
		}

		return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.ToyGyrocopter.desc.1", TextFormatting.RED));
	}
}
