package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.aoa3.util.WorldUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class CreepifiedModifier extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xA4EA00))));
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (damageDealt > 0) {
			LivingEntity target = context.getLivingTarget();
			LivingEntity attacker = context.getAttacker();
			float strength = level;
			double offset = target.getBbWidth() / 1.99d;
			double offsetX = Mth.clamp(attacker.getX() - target.getX(), -offset, offset);
			double offsetY = Mth.clamp(attacker.getY() + attacker.getEyeHeight() - target.getY(), -0.1, target.getBbHeight() + 0.1);
			double offsetZ = Mth.clamp(attacker.getZ() - target.getZ(), -offset, offset);

			BlockPos explosionPos = new BlockPos(target.getX() - offsetX, target.getY() + offsetY, target.getZ() - offsetZ);

			if (context.isCritical())
				strength *= 1.1f;

			WorldUtil.createExplosion(attacker, attacker.level, explosionPos, strength);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
