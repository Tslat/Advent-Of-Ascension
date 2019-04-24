package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.effect.EntityLightningBolt;
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

public class PhantomStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();
	private double posX;
	private double posY;
	private double posZ;

	public PhantomStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(600);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.PhantomStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.StrikeRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.PowerRune.name")));
	}

	@Override
	public boolean checkConditions(final World world, final ItemStack stack, final EntityPlayer player) {
		int[] aimedBlock = EntityUtil.getBlockAimingAt(world, player, 70);

		if (aimedBlock == null)
			return false;

		posX = aimedBlock[0];
		posY = aimedBlock[1];
		posZ = aimedBlock[2];
		return true;
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		if (!world.isRemote) {
			world.addWeatherEffect(new EntityLightningBolt(world, posX, posY, posZ));
			world.addWeatherEffect(new EntityLightningBolt(world, posX + 1, posY, posZ));
			world.addWeatherEffect(new EntityLightningBolt(world, posX + 2, posY, posZ));
			world.addWeatherEffect(new EntityLightningBolt(world, posX + 3, posY, posZ));
			world.addWeatherEffect(new EntityLightningBolt(world, posX - 1, posY, posZ));
			world.addWeatherEffect(new EntityLightningBolt(world, posX - 2, posY, posZ));
			world.addWeatherEffect(new EntityLightningBolt(world, posX - 3, posY, posZ));
			world.addWeatherEffect(new EntityLightningBolt(world, posX, posY, posZ + 1));
			world.addWeatherEffect(new EntityLightningBolt(world, posX, posY, posZ + 2));
			world.addWeatherEffect(new EntityLightningBolt(world, posX, posY, posZ + 3));
			world.addWeatherEffect(new EntityLightningBolt(world, posX, posY, posZ - 1));
			world.addWeatherEffect(new EntityLightningBolt(world, posX, posY, posZ - 2));
			world.addWeatherEffect(new EntityLightningBolt(world, posX, posY, posZ - 3));

			player.worldObj.playSoundAtEntity(player, "nevermine:PhantomStaff", 3.0f, 1.0f);
		}

		stack.damageItem(1, player);
	}

	static {
		runes.put((ItemRune)Itemizer.StrikeRune, 2);
		runes.put((ItemRune)Itemizer.PowerRune, 2);
	}
}
