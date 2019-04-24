package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EnigmaTable extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public EnigmaTable(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(5.5f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int p_149691_1_, final int p_149691_2_) {
		return (p_149691_1_ == 0) ? bottom : ((p_149691_1_ == 1) ? top : blockIcon);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.inventory.consumeInventoryItem(Itemizer.UnchargedStone) && !w.isRemote) {
			p.dropItem(Itemizer.VileStone, 1);
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:enigmaTable_top");
		side = icon.registerIcon("nevermine:enigmaTable_side");
		bottom = icon.registerIcon("nevermine:enigmaTable_bottom");
		blockIcon = icon.registerIcon("nevermine:enigmaTable_side");
	}
}
