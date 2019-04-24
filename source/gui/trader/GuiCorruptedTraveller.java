package net.nevermine.gui.trader;

import net.minecraft.entity.IMerchant;
import net.nevermine.gui.ContainerEternalMerchant;
import net.nevermine.gui.GuiEternalMerchant;

public class GuiCorruptedTraveller extends GuiEternalMerchant {
	public GuiCorruptedTraveller(final ContainerEternalMerchant container, final IMerchant mer) {
		super(container, mer, "corruptedtraveller", "corruptedTravellerGui");
	}
}
