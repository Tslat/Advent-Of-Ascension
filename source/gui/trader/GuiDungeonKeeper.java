package net.nevermine.gui.trader;

import net.minecraft.entity.IMerchant;
import net.nevermine.gui.ContainerEternalMerchant;
import net.nevermine.gui.GuiEternalMerchant;

public class GuiDungeonKeeper extends GuiEternalMerchant {
	public GuiDungeonKeeper(final ContainerEternalMerchant container, final IMerchant mer) {
		super(container, mer, "dungeonkeeper", "dungeonkeeper");
	}
}
