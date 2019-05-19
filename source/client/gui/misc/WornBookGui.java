package net.tslat.aoa3.client.gui.misc;

import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WornBookGui extends GuiScreenBook {
	public WornBookGui(EntityPlayer player, ItemStack book, boolean isUnsigned) {
		super(player, book, isUnsigned);
	}
}
