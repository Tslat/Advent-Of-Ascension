package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FungalStaff extends BaseStaff {
	private static final HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();
	public static final HashSet<Item> blacklistFoods = new HashSet<Item>();
	public static final ArrayList<Item> validFoods = new ArrayList<Item>();
	public static final ArrayList<Integer> validFoodsMeta = new ArrayList<Integer>();

	static {
		runes.put(ItemRegister.runeDistortion, 5);
		runes.put(ItemRegister.runeLife, 2);

		blacklistFoods.add(ItemRegister.candlefish);
		blacklistFoods.add(ItemRegister.candyCane);
		blacklistFoods.add(ItemRegister.candyCorn);
		blacklistFoods.add(ItemRegister.crimsonSkipper);
		blacklistFoods.add(ItemRegister.crimsonStripefish);
		blacklistFoods.add(ItemRegister.darkHatchetfish);
		blacklistFoods.add(ItemRegister.fingerfish);
		blacklistFoods.add(ItemRegister.gingerbreadCookie);
		blacklistFoods.add(ItemRegister.gingerbreadWing);
		blacklistFoods.add(ItemRegister.goldenGullfish);
		blacklistFoods.add(ItemRegister.ironback);
		blacklistFoods.add(ItemRegister.limefish);
		blacklistFoods.add(ItemRegister.pearlStripefish);
		blacklistFoods.add(ItemRegister.peppermintCandy);
		blacklistFoods.add(ItemRegister.rainbowfish);
		blacklistFoods.add(ItemRegister.razorfish);
		blacklistFoods.add(ItemRegister.returnCrystal);
		blacklistFoods.add(ItemRegister.rocketfish);
		blacklistFoods.add(ItemRegister.sailback);
		blacklistFoods.add(ItemRegister.sapphireStrider);
		blacklistFoods.add(ItemRegister.sourCandy);
		blacklistFoods.add(ItemRegister.sourGummy);
		blacklistFoods.add(ItemRegister.sourPop);
		blacklistFoods.add(ItemRegister.spearmintCandy);
		blacklistFoods.add(ItemRegister.turquoiseStripefish);
		blacklistFoods.add(ItemRegister.violetSkipper);
		blacklistFoods.add(Items.GOLDEN_APPLE);
		blacklistFoods.add(Items.GOLDEN_CARROT);
	}

	public FungalStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("FungalStaff");
		setRegistryName("aoa3:fungal_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		if (!(caster instanceof EntityPlayer))
			return;

		int count = 1 + itemRand.nextInt(5);

		for (int i = 0; i < count; i++) {
			int pick = itemRand.nextInt(validFoods.size());
			ItemStack stack = new ItemStack(validFoods.get(pick), 1, validFoodsMeta.get(pick));
			ItemUtil.givePlayerItemOrDrop(((EntityPlayer)caster), stack);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.FungalStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
