package net.nevermine.gui.trader;

import net.minecraft.entity.IMerchant;
import net.nevermine.gui.ContainerEternalMerchant;
import net.nevermine.gui.GuiEternalMerchant;

public class GuiCreepBanker extends GuiEternalMerchant {
	public GuiCreepBanker(final ContainerEternalMerchant container, final IMerchant mer) {
		super(container, mer, "creepbanker", "creepbanker");
	}
}
