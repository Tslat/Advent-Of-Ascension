package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.IncrementalModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;

import java.util.List;

public class SurpriseMeModifier extends IncrementalModifier {
	public SurpriseMeModifier() {
		super(0xF200FF);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		float modifierStage = getScaledLevel(tool, level);
		float adjustment = 1 + 0.02f * modifierStage;
		float baseDmg = damage * adjustment;

		return baseDmg + (float)(RandomUtil.randomGaussianValue() * baseDmg * (2 * adjustment - 2));
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, getScaledLevel(tool, level) * 0.02f * 4, tooltip);
	}
}
