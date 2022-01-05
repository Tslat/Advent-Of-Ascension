package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class BlazingTrait extends Modifier {
	public BlazingTrait() {
		super(0x6B1919);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		LivingEntity target = context.getLivingTarget();

		if (target != null && target.isAlive())
			target.setSecondsOnFire(3);

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
