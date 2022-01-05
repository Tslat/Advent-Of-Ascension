package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CounterweightTrait extends Modifier {
	public CounterweightTrait() {
		super(0xFF8CBF);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (!event.getPlayer().isOnGround() && isEffective)
			event.setNewSpeed(event.getOriginalSpeed() * level * 1.5f);
	}
}
