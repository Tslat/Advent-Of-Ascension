package net.tslat.aoa3.object.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class EvermightStaff extends BaseStaff<Float> {
	public EvermightStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_EMBER_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 2);
		runes.put(AoAItems.POWER_RUNE.get(), 4);
	}

	public Float checkPreconditions(LivingEntity caster, ItemStack staff) {
		float healthPercent = EntityUtil.getCurrentHealthPercent(caster);

		return healthPercent < 1 && healthPercent > 0 ? healthPercent : null;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Float args) {
		EntityUtil.applyPotions(caster, new PotionUtil.EffectBuilder(Effects.DAMAGE_BOOST, (int)(1200f * (1 - args))).level(args < 0.1 ? 3 : args < 0.5 ? 2 : 1));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
