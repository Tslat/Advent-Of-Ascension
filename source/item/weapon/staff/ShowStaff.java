package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;

public class ShowStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();
	private final float dmg = 5.0f;

	public ShowStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(300);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:ShowStaff", 1.0f, 1.0f);

		final int size = world.getEntitiesWithinAABB(EntityLiving.class, player.boundingBox.expand(10.0, 10.0, 10.0)).size();
		for (final EntityLiving e : (List<EntityLiving>)world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(10.0, 10.0, 10.0))) {
			e.attackEntityFrom(DamageSource.causeIndirectMagicDamage(e, player), dmg * size);
		}
		stack.damageItem(1, player);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.magicBase", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.ShowStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.ShowStaff.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(3), StringUtil.getLocaleString("item.CompassRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(3), StringUtil.getLocaleString("item.EnergyRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.CompassRune, 3);
		runes.put((ItemRune)Itemizer.EnergyRune, 3);
	}
}
