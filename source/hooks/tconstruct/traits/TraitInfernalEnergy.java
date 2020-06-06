package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitInfernalEnergy extends AbstractTrait {
	public TraitInfernalEnergy() {
		super("infernal_energy", 0xFF5D00);
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		if (event.getEntityLiving().isBurning())
			event.setNewSpeed(event.getNewSpeed() * 5);
	}
}
