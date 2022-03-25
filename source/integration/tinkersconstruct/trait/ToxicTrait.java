package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;
import net.tslat.aoa3.library.builder.EffectBuilder;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class ToxicTrait extends Modifier {
	public ToxicTrait() {
		super(0x6BFFBE);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (damageDealt > 0 && context.isCritical()) {
			LivingEntity target = context.getLivingTarget();

			if (target == null)
				return super.afterEntityHit(tool, level, context, damageDealt);

			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(EntityType.AREA_EFFECT_CLOUD, target.level);

			cloud.setDuration(3);
			cloud.setRadius(2);
			cloud.setRadiusPerTick(-0.015f);
			cloud.setFixedColor(3368448);
			cloud.addEffect(new EffectBuilder(Effects.POISON, 60).level(2).build());
			cloud.setPos(target.getX(), target.getY(), target.getZ());
			target.level.addFreshEntity(cloud);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
