package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;
import net.tslat.aoa3.library.builder.EffectBuilder;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class LacedTrait extends Modifier {
	public LacedTrait() {
		super(0x6BFFBE);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (damageDealt > 0) {
			LivingEntity target = context.getLivingTarget();

			if (target != null)
				target.addEffect(new EffectBuilder(Effects.POISON, 60).build());
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
