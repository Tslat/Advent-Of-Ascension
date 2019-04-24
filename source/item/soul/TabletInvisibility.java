package net.nevermine.item.soul;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class TabletInvisibility extends BaseTablet {
	public TabletInvisibility(final int price, final int req) {
		super(price, req);
		setCreativeTab(Itemizer.SoulTab);
	}

	@Override
	public void useTablet(final World world, final ItemStack stack, final EntityPlayer var3) {
		var3.addPotionEffect(new PotionEffect(14, 2500, 2));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.TabletInvisibility.desc.1"));
		super.addInformation(stack, player, list, bool);
	}
}
