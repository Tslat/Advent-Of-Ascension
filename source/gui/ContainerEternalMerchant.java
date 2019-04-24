package net.nevermine.gui;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.world.World;

public class ContainerEternalMerchant extends ContainerMerchant {
	public ContainerEternalMerchant(final InventoryPlayer par1InventoryPlayer, final IMerchant par2IMerchant, final World par3World) {
		super(par1InventoryPlayer, par2IMerchant, par3World);
	}

	public boolean canInteractWith(final EntityPlayer par1EntityPlayer) {
		return true;
	}
}
