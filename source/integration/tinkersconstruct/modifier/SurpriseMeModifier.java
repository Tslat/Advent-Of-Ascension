package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.impl.IncrementalModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;
import slimeknights.tconstruct.library.utils.TooltipKey;

import java.util.List;

public class SurpriseMeModifier extends IncrementalModifier {
	public SurpriseMeModifier() {
		super();
	}

	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xF200FF))));
	}

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		float modifierStage = getScaledLevel(tool, level);
		float adjustment = 1 + 0.02f * modifierStage;
		float baseDmg = damage * adjustment;

		return baseDmg + (float)(RandomUtil.randomGaussianValue() * baseDmg * (2 * adjustment - 2));
	}

	@Override
	public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, getScaledLevel(tool, level) * 0.02f * 4, tooltip);
	}
}
