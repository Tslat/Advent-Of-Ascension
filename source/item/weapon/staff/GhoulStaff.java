package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.common.nevermine;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.staff.EntityStaffGhoul;

import java.util.HashMap;
import java.util.List;

public class GhoulStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();
	private final float dmg = 13.0f;

	public GhoulStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(700);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:BasicStaff", 1.0f, 1.0f);

		if (!player.worldObj.isRemote) {
			float damage = 0.0f;

			switch (nevermine.rand.nextInt(7)) {
				case 0:
					player.worldObj.spawnEntityInWorld(new EntityStaffGhoul(player.worldObj, player, 13.0f, 0, 0.0f));
					break;
				case 1:
					player.worldObj.spawnEntityInWorld(new EntityStaffGhoul(player.worldObj, player, 16.0f, 0, 0.0f));
					damage = 0.5f;
					break;
				case 2:
					player.worldObj.spawnEntityInWorld(new EntityStaffGhoul(player.worldObj, player, 19.0f, 0, 0.0f));
					damage = 1.0f;
					break;
				case 3:
					player.worldObj.spawnEntityInWorld(new EntityStaffGhoul(player.worldObj, player, 23.0f, 0, 0.0f));
					damage = 1.5f;
					break;
				case 4:
					player.worldObj.spawnEntityInWorld(new EntityStaffGhoul(player.worldObj, player, 28.0f, 0, 0.0f));
					damage = 2.0f;
					break;
				case 5:
					player.worldObj.spawnEntityInWorld(new EntityStaffGhoul(player.worldObj, player, 34.0f, 0, 0.0f));
					damage = 2.5f;
					break;
				case 6:
					player.worldObj.spawnEntityInWorld(new EntityStaffGhoul(player.worldObj, player, 40.0f, 0, 0.0f));
					damage = 3.0f;
					break;
				default:
					break;
			}

			if (!player.capabilities.isCreativeMode) {
				if (!player.capabilities.isCreativeMode) {
					if (player.getHealth() > damage) {
						player.setHealth(player.getHealth() - damage);
					}
					else {
						player.attackEntityFrom(DamageSource.magic, damage);
					}
				}

				stack.damageItem(1, player);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.rangedBase", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.GhoulStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.GhoulStaff.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(5), StringUtil.getLocaleString("item.WindRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.PowerRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.WindRune, 5);
		runes.put((ItemRune)Itemizer.PowerRune, 1);
	}
}
