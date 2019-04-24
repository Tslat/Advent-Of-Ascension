package net.nevermine.item.soul;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class TabletDebuff extends BaseTablet {
	public TabletDebuff(final int price, final int req) {
		super(price, req);
		setCreativeTab(Itemizer.SoulTab);
	}

	@Override
	public void useTablet(final World world, final ItemStack stack, final EntityPlayer var3) {
		var3.removePotionEffect(15);
		var3.removePotionEffect(17);
		var3.removePotionEffect(18);
		var3.removePotionEffect(19);
		var3.removePotionEffect(20);
		var3.removePotionEffect(2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.TabletDebuff.desc.1"));
		super.addInformation(stack, player, list, bool);
	}
}
