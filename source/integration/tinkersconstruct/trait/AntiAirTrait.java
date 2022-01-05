package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.util.EntityUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;

import java.util.List;

public class AntiAirTrait extends Modifier {
	public AntiAirTrait() {
		super(0xCFF99F);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (EntityUtil.isFlyingCreature(context.getLivingTarget()))
			return super.getEntityDamage(tool, level, context, baseDamage, damage) + level * 1.5f;

		return super.getEntityDamage(tool, level, context, baseDamage, damage);
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, level * 1.5f, tooltip);
	}
}
