package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import slimeknights.tconstruct.library.modifiers.impl.IncrementalModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class RefreezingModifier extends IncrementalModifier {
	public RefreezingModifier() {
	}

	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xCEFDFF))));
	}

	@Override
	public void onInventoryTick(IToolStackView tool, int level, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (!world.isClientSide() && !tool.isBroken()) {
			float repairChance = getAmount(tool) / 9f * 0.025f;

			if (!isSelected)
				repairChance /= 2f;

			if (RandomUtil.percentChance(repairChance) && WorldUtil.getAmbientTemperature(world, holder.blockPosition()) < 0.15f)
				ToolDamageUtil.repair(tool, 1);
		}
	}
}
