package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class EverfightStaff extends BaseStaff {
	public EverfightStaff(int durability) {
		super(durability);
		setTranslationKey("EverfightStaff");
		setRegistryName("aoa3:everfight_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.EVER_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.KINETIC_RUNE, 4);
		runes.put(ItemRegister.POWER_RUNE, 4);
	}

	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		float healthPercent = EntityUtil.getCurrentHealthPercent(caster);

		return healthPercent < 1 && healthPercent > 0 ? healthPercent : null;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		caster.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, (int)(1200f * (1 - (float)args)), 0));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EverfightStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
