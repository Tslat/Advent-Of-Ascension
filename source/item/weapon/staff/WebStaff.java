package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebStaff extends BaseStaff {
	public WebStaff(int durability) {
		super(durability);
		setTranslationKey("WebStaff");
		setRegistryName("aoa3:web_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.WEB_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.DISTORTION_RUNE, 4);
		runes.put(ItemRegister.ENERGY_RUNE, 4);
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		ArrayList<Potion> effects = new ArrayList<Potion>(caster.getActivePotionEffects().size());

		for (PotionEffect effect : caster.getActivePotionEffects()) {
			if (effect.getPotion().isBadEffect())
				effects.add(effect.getPotion());
		}

		return effects.size() > 0 ? effects : null;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		((ArrayList<Potion>)args).forEach(caster::removePotionEffect);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.WebStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
