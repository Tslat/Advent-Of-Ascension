package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;
import slimeknights.tconstruct.library.utils.TooltipKey;

import java.util.List;

public class BoneShockTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFE8A0))));
	}

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		LivingEntity target = context.getLivingTarget();

		if (target != null && target.isAlive() && target.getMobType() == MobType.ARTHROPOD)
			return super.getEntityDamage(tool, level, context, baseDamage, damage) + 2 * level;

		return super.getEntityDamage(tool, level, context, baseDamage, damage);
	}

	@Override
	public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
		addDamageTooltip(tool, 2 * level, tooltip);
	}
}
