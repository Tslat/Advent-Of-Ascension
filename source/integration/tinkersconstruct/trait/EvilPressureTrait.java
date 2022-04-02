package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

import java.util.ArrayList;

public class EvilPressureTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xC68A2F))));
	}
	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		Player player = context.getPlayerAttacker();
		LivingEntity target = context.getLivingTarget();

		if (player != null && !player.level.isClientSide() && RandomUtil.oneInNChance(Math.max(1, (int)Math.ceil(target.getMaxHealth() / damageDealt)))) {
			ArrayList<MobEffectInstance> negativeEffects = new ArrayList<MobEffectInstance>();

			target.getActiveEffects().forEach(effect -> {
				if (effect.getEffect().getCategory() == MobEffectCategory.HARMFUL)
					negativeEffects.add(effect);
			});

			if (negativeEffects.isEmpty())
				return super.afterEntityHit(tool, level, context, damageDealt);

			MobEffectInstance effect = RandomUtil.getRandomSelection(negativeEffects);

			effect.update(new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier() + 2, effect.isAmbient(), effect.isVisible()));
			target.addEffect(effect);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
