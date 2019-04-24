package net.nevermine.block.generation.wood;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.nevermine.izer.Itemizer;

public class WoodLucalus extends BlockLog {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon side;

	public WoodLucalus(final Material p_i45394_1_) {
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(2.0f);
		setResistance(0.5f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		final int var3 = par2 & 0xC;
		return (var3 == 0 && (par1 == 1 || par1 == 0)) ? top : ((var3 == 4 && (par1 == 5 || par1 == 4)) ? top : ((var3 == 8 && (par1 == 2 || par1 == 3)) ? top : side));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:woodLucalusTop");
		bottom = top;
		side = icon.registerIcon("nevermine:woodLucalus");
		blockIcon = icon.registerIcon("nevermine:woodLucalus");
	}
}
