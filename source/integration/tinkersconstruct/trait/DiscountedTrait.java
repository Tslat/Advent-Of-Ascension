package net.tslat.aoa3.integration.tinkersconstruct.trait;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class DiscountedTrait extends Modifier {
	public DiscountedTrait() {
		super(0xFFB200);
	}

	@Override
	public float getRepairFactor(IModifierToolStack toolStack, int level, float factor) {
		return super.getRepairFactor(toolStack, level, factor) * 1.2f;
	}
}
