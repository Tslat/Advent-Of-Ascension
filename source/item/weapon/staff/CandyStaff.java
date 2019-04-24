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

public class CandyStaff extends BaseStaff {
	private final float dmg = 5.0f;
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public CandyStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(300);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:CandyStaff", 1.0f, 1.0f);

		for (final EntityLiving e : (List<EntityLiving>)world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(10.0, 10.0, 10.0))) {
			e.addVelocity(Math.signum(player.posX - e.posX) * 2.229, 0.0, Math.signum(player.posZ - e.posZ) * 2.229);
			e.attackEntityFrom(DamageSource.magic, dmg);
		}
		stack.damageItem(1, player);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.magic", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.CandyStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(10), StringUtil.getLocaleString("item.CompassRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.CompassRune, 10);
	}
}
