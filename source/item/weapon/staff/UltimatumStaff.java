package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class UltimatumStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();
	private static final int List = 0;
	private Boolean reduce;
	private Random rand = new Random();

	public UltimatumStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(300);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public void fireStaff(World world, ItemStack stack, EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:UltimatumStaff", 1.0F, 1.0F);

		for (EntityMob e : (List<EntityMob>)world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(7.0D, 7.0D, 7.0D))) {
			if (!player.worldObj.isRemote && rand.nextInt(3) == 1 && !EntityUtil.isPowerfulSoul(e)) {
				e.setDead();
			}
		}
		stack.damageItem(1, player);
	}

	@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.UltimatumStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.UltimatumStaff.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.UltimatumStaff.desc.3", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(8), StringUtil.getLocaleString("item.DistortionRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.CompassRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.DistortionRune, 8);
		runes.put((ItemRune)Itemizer.CompassRune, 2);
	}
}
