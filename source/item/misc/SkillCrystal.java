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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

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
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			Enums.Skills skill = PlayerUtil.getLowestSkillWithLimit(plData, lowerLimit);

			if (skill != null) {
				plData.stats().addXp(skill, PlayerUtil.getXpRequiredForNextLevel(plData.stats().getLevel(skill)) / denominator, false);

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);
			}
			else {
				plData.sendThrottledChatMessage("message.feedback.item.skillCrystal.levelFail",  Integer.toString(lowerLimit));
			}
		}

		return EnumActionResult.PASS;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.skillCrystal.desc.1", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillCrystal.desc.2", TextFormatting.GOLD, Integer.toString(lowerLimit)));
	}
}
