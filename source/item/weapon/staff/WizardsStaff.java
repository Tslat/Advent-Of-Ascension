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
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class WizardsStaff extends BaseStaff {
	public WizardsStaff(int durability) {
		super(durability);
		setTranslationKey("WizardsStaff");
		setRegistryName("aoa3:wizards_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.BASIC_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.LIFE_RUNE, 3);
		runes.put(ItemRegister.POWER_RUNE, 3);
		runes.put(ItemRegister.DISTORTION_RUNE, 3);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		caster.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 0, false, true));
		caster.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 200, 0, false, true));
		caster.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 200, 0, false, true));
		caster.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 200, 0, false, true));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.WizardsStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
