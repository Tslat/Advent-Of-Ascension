package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.util.DamageUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;

import java.util.List;

public class RunicTrait extends Modifier {
	public RunicTrait() {
		super(0x89FBFF);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		return super.getEntityDamage(tool, level, context, baseDamage, damage) * (1 - level * 0.2f);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getLivingTarget() != null && context.getLivingTarget().isAlive()) {
			float magicDmg = (damageDealt / (1 - level * 0.2f) - damageDealt) / 2f;

			DamageUtil.dealMagicDamage(null, context.getPlayerAttacker(), context.getLivingTarget(), magicDmg, false);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, level * 0.1f, tooltip);
	}
}
