package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.common.ToolActions;
import net.tslat.aoa3.util.EntityUtil;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;
import slimeknights.tconstruct.library.utils.TooltipKey;

import java.util.List;

public class AirBladeModifier extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFDD8))));
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		int toolDamage = super.afterEntityHit(tool, level, context, damageDealt);

		if (damageDealt > 0 && !tool.isBroken() && tool.getItem().canPerformAction(tool.getItem().getDefaultInstance(), ToolActions.SWORD_SWEEP)) {
			Player player = context.getPlayerAttacker();

			if (player == null || player.fallDistance > 0 || !player.isOnGround() || player.isSprinting() || player.onClimbable() || player.isInWater() || player.getVehicle() != null)
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
	public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, (level / (float)level + 2f) - 1, tooltip);
	}
}
