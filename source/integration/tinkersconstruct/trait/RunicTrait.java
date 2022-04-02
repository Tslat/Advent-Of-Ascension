package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.DamageUtil;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;
import slimeknights.tconstruct.library.utils.TooltipKey;

import java.util.List;

public class RunicTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x89FBFF))));
	}

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		return super.getEntityDamage(tool, level, context, baseDamage, damage) * (1 - level * 0.2f);
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getLivingTarget() != null && context.getLivingTarget().isAlive()) {
			float magicDmg = (damageDealt / (1 - level * 0.2f) - damageDealt) / 2f;

			DamageUtil.dealMagicDamage(null, context.getPlayerAttacker(), context.getLivingTarget(), magicDmg, false);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}

	@Override
	public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, level * 0.1f, tooltip);
	}
}
