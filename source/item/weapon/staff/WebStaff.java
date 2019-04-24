package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class WebStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeDistortion, 4);
		runes.put(ItemRegister.runeEnergy, 4);
	}

	public WebStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("WebStaff");
		setRegistryName("aoa3:web_staff");
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		for (Potion pot : caster.getActivePotionMap().keySet()) {
			if (pot.isBadEffect())
				return new Object();
		}

		return null;
	}


	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		caster.getActivePotionEffects().removeIf(effect -> effect.getPotion().isBadEffect());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.WebStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
