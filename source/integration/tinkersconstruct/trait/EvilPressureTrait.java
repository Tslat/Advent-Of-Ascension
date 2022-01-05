package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

import java.util.ArrayList;

public class EvilPressureTrait extends Modifier {
	public EvilPressureTrait() {
		super(0xC68A2F);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		PlayerEntity player = context.getPlayerAttacker();
		LivingEntity target = context.getLivingTarget();

		if (player != null && !player.level.isClientSide() && RandomUtil.oneInNChance(Math.max(1, (int)Math.ceil(target.getMaxHealth() / damageDealt)))) {
			ArrayList<EffectInstance> negativeEffects = new ArrayList<EffectInstance>();

			target.getActiveEffects().forEach(effect -> {
				if (effect.getEffect().getCategory() == EffectType.HARMFUL)
					negativeEffects.add(effect);
			});

			if (negativeEffects.isEmpty())
				return super.afterEntityHit(tool, level, context, damageDealt);

			EffectInstance effect = RandomUtil.getRandomSelection(negativeEffects);

			effect.update(new EffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier() + 2, effect.isAmbient(), effect.isVisible()));
			target.addEffect(effect);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
