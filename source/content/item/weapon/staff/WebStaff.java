package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebStaff extends BaseStaff<ArrayList<Effect>> {
	public WebStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_WEB_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 4);
		runes.put(AoAItems.ENERGY_RUNE.get(), 4);
	}

	@Override
	public ArrayList<Effect> checkPreconditions(LivingEntity caster, ItemStack staff) {
		ArrayList<Effect> effects = new ArrayList<Effect>(caster.getActiveEffects().size());

		for (EffectInstance effect : caster.getActiveEffects()) {
			if (!effect.getEffect().isBeneficial())
				effects.add(effect.getEffect());
		}

		return effects.size() > 0 ? effects : null;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, ArrayList<Effect> args) {
		args.forEach(caster::removeEffect);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
