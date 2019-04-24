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

public class MineralizationStation extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public MineralizationStation(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(5.5f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		return (par1 == 1) ? top : ((par1 == 0) ? top : ((par2 == 2 && par1 == 2) ? side : ((par2 == 3 && par1 == 5) ? side : ((par2 == 0 && par1 == 3) ? side : ((par2 == 1 && par1 == 4) ? side : blockIcon)))));
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.getHeldItem() != null) {
			if (p.getHeldItem().getItem() == Itemizer.IngotBaronyte) {
				p.inventory.consumeInventoryItem(Itemizer.IngotBaronyte);
				w.playSoundAtEntity(p, "nevermine:Mineralization", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Itemizer.SilverCoin, 3);
				}
				return true;
			}
			if (p.getHeldItem().getItem() == Itemizer.IngotElecanium) {
				p.inventory.consumeInventoryItem(Itemizer.IngotElecanium);
				w.playSoundAtEntity(p, "nevermine:Mineralization", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Itemizer.SilverCoin, 2);
				}
				return true;
			}
			if (p.getHeldItem().getItem() == Itemizer.IngotVarsium) {
				p.inventory.consumeInventoryItem(Itemizer.IngotVarsium);
				w.playSoundAtEntity(p, "nevermine:Mineralization", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Itemizer.SilverCoin, 1);
				}
				return true;
			}
			if (p.getHeldItem().getItem() == Itemizer.IngotBlazium) {
				p.inventory.consumeInventoryItem(Itemizer.IngotBlazium);
				w.playSoundAtEntity(p, "nevermine:Mineralization", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Itemizer.SilverCoin, 20);
				}
				return true;
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:mineralizationStation_top");
		side = icon.registerIcon("nevermine:mineralizationStation_side");
		bottom = icon.registerIcon("nevermine:mineralizationStation_top");
		blockIcon = icon.registerIcon("nevermine:mineralizationStation_side");
	}
}
