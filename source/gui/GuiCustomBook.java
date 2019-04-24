package net.nevermine.gui;

import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.nevermine.item.functional.WornBook;

public class GuiCustomBook extends GuiScreenBook {
	public GuiCustomBook(EntityPlayer p_i1080_1_, ItemStack p_i1080_2_, boolean p_i1080_3_) {
		super(p_i1080_1_, WornBook.getBook(), p_i1080_3_);
	}
}
