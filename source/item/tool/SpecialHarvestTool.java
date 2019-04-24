package net.tslat.aoa3.item.tool;

import net.minecraftforge.event.world.BlockEvent;

public interface SpecialHarvestTool {
	void doHarvestEffect(BlockEvent.HarvestDropsEvent e);
}
