package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

import java.util.List;

public class HighInCalciumTrait extends Modifier {
	public HighInCalciumTrait() {
		super(16448150);
	}

	@Override
	public List<ItemStack> processLoot(IModifierToolStack tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		if (RandomUtil.oneInNChance(3)) {
			if (RandomUtil.oneInNChance(6)) {
				generatedLoot.add(new ItemStack(Items.BONE));
			}
			else {
				generatedLoot.add(new ItemStack(Items.BONE_MEAL));
			}
		}

		return super.processLoot(tool, level, generatedLoot, context);
	}
}
