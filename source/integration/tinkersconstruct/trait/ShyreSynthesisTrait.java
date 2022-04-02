package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

import javax.annotation.Nullable;

public class ShyreSynthesisTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x00CFEA))));
	}

	@Override
	public int onDamageTool(IToolStackView toolStack, int level, int amount, @Nullable LivingEntity holder) {
		if (holder != null && holder.level.isDay() && holder.level.canSeeSky(holder.blockPosition()))
			return 0;

		return super.onDamageTool(toolStack, level, amount, holder);
	}
}
