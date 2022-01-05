package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;

import java.util.List;

public class BaronTrait extends Modifier {
	public BaronTrait() {
		super(0xCE0000);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (RandomUtil.oneInNChance(5))
			return super.getEntityDamage(tool, level, context, baseDamage, damage) + 3 * level;

		return super.getEntityDamage(tool, level, context, baseDamage, damage);
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, level * 3, tooltip);
	}
}
