package net.nevermine.gui.trader;

import net.minecraft.entity.IMerchant;
import net.nevermine.gui.ContainerEternalMerchant;
import net.nevermine.gui.GuiEternalMerchant;

public class GuiTrollTrader extends GuiEternalMerchant {
	public GuiTrollTrader(final ContainerEternalMerchant container, final IMerchant mer) {
		super(container, mer, "trolltrader", "trolltrader");
	}
}
