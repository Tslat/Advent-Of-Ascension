package net.nevermine.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class AquaticFood extends ItemFood {
	float hp;

	public AquaticFood(final int add, final float satur, final float heal) {
		super(add, satur, true);
		hp = heal;
		setCreativeTab(Itemizer.FishTab);
	}

	public ItemStack onEaten(final ItemStack item, final World world, final EntityPlayer player) {
		super.onEaten(item, world, player);

		if (!world.isRemote) {
			player.heal(hp);
		}

		return item;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("items.description.aquaticFood.desc.1"));
		list.add(StringUtil.getLocaleStringWithArguments("items.description.aquaticFood.desc.2", Float.toString(hp / 2.0f)));
	}
}
