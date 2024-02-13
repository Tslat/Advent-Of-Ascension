package net.tslat.aoa3.content.item.weapon.staff;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class WebStaff extends BaseStaff<List<MobEffect>> {
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
	public Optional<List<MobEffect>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<MobEffect> effects = new ObjectArrayList<>(caster.getActiveEffects().size());

		for (MobEffectInstance effect : caster.getActiveEffects()) {
			if (!effect.getEffect().isBeneficial())
				effects.add(effect.getEffect());
		}

		return Optional.ofNullable(effects.isEmpty() ? null : effects);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, List<MobEffect> args) {
		args.forEach(caster::removeEffect);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
