package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

import java.util.List;

public class HighInCalciumTrait extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(16448150))));
	}

	@Override
	public List<ItemStack> processLoot(IToolStackView tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		if (RandomUtil.oneInNChance(3)) {
			if (RandomUtil.oneInNChance(6)) {
				generatedLoot.add(new ItemStack(Items.BONE));
			}
			else {
				generatedLoot.add(new ItemStack(Items.WHITE_DYE));
			}
		}

		return super.processLoot(tool, level, generatedLoot, context);
	}
}
