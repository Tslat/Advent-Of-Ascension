package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

import javax.annotation.Nullable;

public class ShyreSynthesisTrait extends Modifier {
	public ShyreSynthesisTrait() {
		super(0x00CFEA);
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount, @Nullable LivingEntity holder) {
		if (holder != null && holder.level.isDay() && holder.level.canSeeSky(holder.blockPosition()))
			return 0;

		return super.onDamageTool(toolStack, level, amount, holder);
	}
}
