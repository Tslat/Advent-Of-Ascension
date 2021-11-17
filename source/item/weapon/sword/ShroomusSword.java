package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShroomusSword extends BaseSword {
	public ShroomusSword() {
		super(ItemUtil.customItemTier(2030, AttackSpeed.NORMAL, 15.0f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.75) {
			Collection<EffectInstance> effects = attacker.getActiveEffects();

			if (!effects.isEmpty()) {
				ArrayList<EffectInstance> removableEffects = new ArrayList<EffectInstance>(effects.size());

				for (EffectInstance effect : effects) {
					if (!effect.getEffect().isBeneficial())
						removableEffects.add(effect);
				}

				for (EffectInstance effect : removableEffects) {
					target.addEffect(effect);
					attacker.removeEffect(effect.getEffect());
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
