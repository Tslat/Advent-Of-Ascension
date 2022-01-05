package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class InfernalEnergyTrait extends Modifier {
	public InfernalEnergyTrait() {
		super(0xFF5D00);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (event.getEntityLiving().isOnFire())
			event.setNewSpeed(event.getNewSpeed() * 5);
	}
}
