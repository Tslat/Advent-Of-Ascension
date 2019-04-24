package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SkillCrystal extends SimpleItem {
	private final float denominator;
	private final int lowerLimit = 15;

	public SkillCrystal(String name, String registryName, float denominator) {
		super(name, registryName);
		setCreativeTab(CreativeTabsRegister.miscTab);
		this.denominator = denominator;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

			Enums.Skills sk = cap.getLowestSkillWithLimit(lowerLimit);

			if (sk != null) {
				cap.addXp(sk, cap.getXpReqForLevel(cap.getLevel(sk)) / denominator, false);

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);
			}
			else {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.item.skillCrystal.levelFail", Integer.toString(lowerLimit)));
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.skillCrystal.desc.1", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillCrystal.desc.2", TextFormatting.GOLD, Integer.toString(lowerLimit)));
	}
}
