package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;

import java.util.List;

public class BoneShockTrait extends Modifier {
	public BoneShockTrait() {
		super(0xFFE8A0);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		LivingEntity target = context.getLivingTarget();

		if (target != null && target.isAlive() && target.getMobType() == CreatureAttribute.ARTHROPOD)
			return super.getEntityDamage(tool, level, context, baseDamage, damage) + 2 * level;

		return super.getEntityDamage(tool, level, context, baseDamage, damage);
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, 2 * level, tooltip);
	}
}
