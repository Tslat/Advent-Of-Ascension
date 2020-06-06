package net.tslat.aoa3.hooks.tconstruct.modifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import net.tslat.aoa3.utils.WorldUtil;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class ModRefreezing extends ModifierTrait {
	public ModRefreezing() {
		super("refreezing", 0xCEFDFF, 3, 3);
	}

	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!world.isRemote && shouldRepair(tool)) {
			float repairChance = Traits.getModifierStage(tool, this) / 9f * 0.025f;

			if (!isSelected)
				repairChance /= 2f;

			if (random.nextFloat() < repairChance && WorldUtil.getAmbientTemperature(world, entity.getPosition()) < 0.15f)
				ToolHelper.healTool(tool, 1, (EntityLivingBase)entity);
		}
	}

	private static boolean shouldRepair(ItemStack stack) {
		return !stack.isEmpty() && stack.getItemDamage() > 0;
	}
}
