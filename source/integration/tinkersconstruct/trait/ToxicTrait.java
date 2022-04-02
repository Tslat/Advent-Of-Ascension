package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class ToxicTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x6BFFBE))));
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (damageDealt > 0 && context.isCritical()) {
			LivingEntity target = context.getLivingTarget();

			if (target == null)
				return super.afterEntityHit(tool, level, context, damageDealt);

			AreaEffectCloud cloud = new AreaEffectCloud(EntityType.AREA_EFFECT_CLOUD, target.level);

			cloud.setDuration(3);
			cloud.setRadius(2);
			cloud.setRadiusPerTick(-0.015f);
			cloud.setFixedColor(3368448);
			cloud.addEffect(new EffectBuilder(MobEffects.POISON, 60).level(2).build());
			cloud.setPos(target.getX(), target.getY(), target.getZ());
			target.level.addFreshEntity(cloud);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
