package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.nevermine.izer.Itemizer;

public class LunarPad extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public LunarPad(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		return (par1 == 1) ? top : ((par1 == 0) ? top : ((par2 == 2 && par1 == 2) ? side : ((par2 == 3 && par1 == 5) ? side : ((par2 == 0 && par1 == 3) ? side : ((par2 == 1 && par1 == 4) ? side : blockIcon)))));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:lunarPad_top");
		side = icon.registerIcon("nevermine:lunarPad_side");
		bottom = icon.registerIcon("nevermine:lunarPad_side");
		blockIcon = icon.registerIcon("nevermine:lunarPad_side");
	}
}
