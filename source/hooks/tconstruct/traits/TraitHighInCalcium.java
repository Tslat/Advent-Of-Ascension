package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.library.Enums;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitHighInCalcium extends AbstractTrait {
	public TraitHighInCalcium() {
		super("high_in_calcium", Enums.RGBIntegers.BONE);

		addAspects(ModifierAspect.toolOnly);
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
		if (!event.getWorld().isRemote && event.getWorld().rand.nextInt(3) == 0 && ToolHelper.isToolEffective2(tool, event.getState())) {
			if (event.getWorld().rand.nextInt(6) == 0) {
				event.getDrops().add(new ItemStack(Items.BONE));
			}
			else {
				event.getDrops().add(new ItemStack(Items.DYE, 1, 15));
			}
		}
	}
}
