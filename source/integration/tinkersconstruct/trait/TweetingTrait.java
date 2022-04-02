package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class TweetingTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFE500))));
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getPlayerAttacker() != null)
			context.getPlayerAttacker().level.playSound(null, context.getPlayerAttacker().blockPosition(), AoASounds.ENTITY_CHARGER_AMBIENT.get(), SoundSource.PLAYERS, 1f, 1f);

		return super.afterEntityHit(tool, level, context, damageDealt);
	}

	@Override
	public void finishBreakingBlocks(IToolStackView tool, int level, ToolHarvestContext context) {
		if (context.getPlayer() != null && RandomUtil.oneInNChance(5))
			context.getPlayer().level.playSound(null, context.getPlayer().blockPosition(), AoASounds.ENTITY_CHARGER_AMBIENT.get(), SoundSource.PLAYERS, 1f, 1f);
	}
}
