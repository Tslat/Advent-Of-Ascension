package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.util.EntityUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;
import slimeknights.tconstruct.tools.item.small.SweepingSwordTool;

import java.util.List;

public class AirBladeModifier extends Modifier {
	public AirBladeModifier() {
		super(0xFFFDD8);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		int toolDamage = super.afterEntityHit(tool, level, context, damageDealt);

		if (damageDealt > 0 && !tool.isBroken() && tool.getItem() instanceof SweepingSwordTool) {
			PlayerEntity player = context.getPlayerAttacker();

			if (player == null || player.fallDistance > 0 || !player.onGround || player.isSprinting() || player.onClimbable() || player.isInWater() || player.getVehicle() != null)
				return toolDamage;

			if (EntityUtil.getAttackCooldown(player) <= 0.9f)
				return toolDamage;

			if (player.walkDist - player.walkDistO >= player.getSpeed())
				return toolDamage;

			float damage = damageDealt * (level / (float)(level + 2));

			for (LivingEntity entity : player.level.getEntitiesOfClass(LivingEntity.class, context.getLivingTarget().getBoundingBox().inflate(1, 0.25, 1))) {
				if (entity != player && entity != context.getLivingTarget() && player.getTeam() != entity.getTeam() && player.distanceToSqr(entity) < 9)
					entity.hurt(DamageSource.playerAttack(player), damage);
			}
		}

		return toolDamage;
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, (level / (float)level + 2f) - 1, tooltip);
	}
}
