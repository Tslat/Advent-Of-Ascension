package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.nevermine.izer.Itemizer;

public class SpikeyPillar extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public SpikeyPillar(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.5f);
		setResistance(0.2f);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		return (par1 == 1) ? top : ((par1 == 0) ? top : ((par2 == 2 && par1 == 2) ? side : ((par2 == 3 && par1 == 5) ? side : ((par2 == 0 && par1 == 3) ? side : ((par2 == 1 && par1 == 4) ? side : blockIcon)))));
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 13;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:spikeyPillar_top");
		side = icon.registerIcon("nevermine:spikeyPillar_side");
		bottom = icon.registerIcon("nevermine:spikeyPillar_top");
		blockIcon = icon.registerIcon("nevermine:spikeyPillar_side");
	}
}
