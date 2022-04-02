package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.util.PlayerUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class SoulHarvestTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x99A8FC))));
	}

	@Override
	public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
		if (context.isEffective() && context.getPlayer() != null) {
			ServerPlayer player = context.getPlayer();

			PlayerUtil.addResourceToPlayer(player, AoAResources.ENERGY.get(), context.getState().getDestroySpeed(player.level, context.getPos()));
		}
	}
}
