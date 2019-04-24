package net.nevermine.item.weapon.archergun;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.weapon.gun.BaseGun;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.auxillary.EntityElementalArrow;

import java.util.List;
import java.util.Random;

public class LunarArchergun extends BaseGun {
	private Random rand;
	private int canShootTick;

	public LunarArchergun(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item) {
		super(consumeChance, effect, uses, fireRate, item);
		rand = new Random();
		canShootTick = 0;
		setCreativeTab(Weaponizer.ArchergunTab);
	}

	@Override
	public void fireGun(final World world, final ItemStack stack, final EntityPlayer player, final float multi, boolean consume) {
		final EntityElementalArrow var5 = new EntityElementalArrow(world, player, (2.53f + rand.nextInt(5) / 2) * multi, 0, true);
		var5.setDamage((2.53f + rand.nextInt(5) / 2) * multi);
		world.spawnEntityInWorld(var5);
	}

	@Override
	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotLunar == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.rangedBase", EnumChatFormatting.DARK_RED, "10-22"));
		list.add(StringUtil.getLocaleString("items.description.speed.fast"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo", EnumChatFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.ElementalArrow.name")));
	}
}
