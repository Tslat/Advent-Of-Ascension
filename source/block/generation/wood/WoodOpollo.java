package net.nevermine.block.generation.wood;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.nevermine.izer.Itemizer;

public class WoodOpollo extends BlockLog {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon side;

	public WoodOpollo(Material p_i45394_1_) {
		super();
		this.setCreativeTab(Itemizer.GenerationTab);
		this.setHardness(2f);
		this.setResistance(0.5F);

	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int var3 = par2 & 12;
		return var3 == 0 && (par1 == 1 || par1 == 0) ? this.top : (var3 == 4 && (par1 == 5 || par1 == 4) ? this.top : (var3 == 8 && (par1 == 2 || par1 == 3) ? top : this.side));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.top = icon.registerIcon("nevermine:woodOpolloTop");
		this.bottom = this.top;
		this.side = icon.registerIcon("nevermine:woodOpollo");
		this.blockIcon = icon.registerIcon("nevermine:woodOpollo");
	}

}
