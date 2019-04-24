package net.nevermine.block.generation.plant;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.nevermine.izer.Itemizer;

public class SkullBlock extends BlockDirectional {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	private String name;

	public SkullBlock(Material p_i45394_1_, String strname) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5f);
		setResistance(0.5F);
		setLightLevel(0.0F);
		name = strname;
		GameRegistry.registerBlock(this, strname);

	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int var3 = par2 & 12;
		return var3 == 0 && (par1 == 1 || par1 == 0) ? top : (var3 == 4 && (par1 == 5 || par1 == 4) ? top : (var3 == 8 && (par1 == 2 || par1 == 3) ? top : side));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {

		if (name == "skullBlock") {
			top = icon.registerIcon("nevermine:skullBlockTop");
			bottom = top;
		}
		else {
			top = icon.registerIcon("nevermine:skullBlockDarkTop");
			bottom = top;
		}

		side = icon.registerIcon("nevermine:animated/" + name);
		blockIcon = icon.registerIcon("nevermine:animated/" + name);
	}
}
