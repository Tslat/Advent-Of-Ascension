package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import slimeknights.tconstruct.library.modifiers.IncrementalModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class RefreezingModifier extends IncrementalModifier {
	public RefreezingModifier() {
		super(0xCEFDFF);
	}

	@Override
	public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (!world.isClientSide() && !tool.isBroken()) {
			float repairChance = getAmount(tool) / 9f * 0.025f;

			if (!isSelected)
				repairChance /= 2f;

			if (RandomUtil.percentChance(repairChance) && WorldUtil.getAmbientTemperature(world, holder.blockPosition()) < 0.15f)
				ToolDamageUtil.repair(tool, 1);
		}
	}
}
