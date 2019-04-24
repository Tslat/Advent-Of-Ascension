package net.nevermine.block.generation.plant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.nevermine.izer.Itemizer;

public class EyeBlock extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public EyeBlock(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int intSide, final int meta) {
		return (intSide == 1) ? top : ((intSide == 0) ? bottom : side);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:eyeBlock_back");
		side = icon.registerIcon("nevermine:eyeBlock_side");
		bottom = icon.registerIcon("nevermine:eyeBlock_eye");
		blockIcon = icon.registerIcon("nevermine:eyeBlock_side");
	}
}
