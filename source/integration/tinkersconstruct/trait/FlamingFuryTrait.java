package net.tslat.aoa3.integration.tinkersconstruct.trait;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FlamingFuryTrait extends Modifier {
	public FlamingFuryTrait() {
		super(0xFF5D00);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getPlayerAttacker() != null && context.getPlayerAttacker().isOnFire())
			return super.getEntityDamage(tool, level, context, baseDamage, damage) + 3;

		return super.getEntityDamage(tool, level, context, baseDamage, damage);
	}
}
