package net.nevermine.item.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class SkillCrystal extends Item {
	private int denominator;

	public SkillCrystal(final int deno) {
		denominator = deno;
		setCreativeTab(Itemizer.MiscTab);
	}

	public boolean onItemUse(final ItemStack stack, final EntityPlayer player, final World world, final int par4, final int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
		if (!world.isRemote) {
			PlayerContainer cont = PlayerContainer.getProperties(player);
			PlayerContainer.Skills sk = cont.getLowestSkillWithLimit(15);

			if (sk != null) {
				--stack.stackSize;
				cont.addExperience(cont.getExpRequired(sk) / denominator, sk);
			}
			else {
				player.addChatMessage(StringUtil.getLocale("message.feedback.item.skillCrystal.fail"));
			}
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("items.description.skillCrystal.desc.1", EnumChatFormatting.GOLD));
		list.add(StringUtil.getColourLocaleString("items.description.skillCrystal.desc.2", EnumChatFormatting.GOLD));
	}
}
