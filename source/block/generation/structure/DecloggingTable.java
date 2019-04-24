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
import net.nevermine.izer.equipment.Weaponizer;

public class DecloggingTable extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public DecloggingTable(final Material p_i45394_1_) {
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
		if (!p.isSneaking() && p.getHeldItem() != null) {
			if (p.getHeldItem().getItem() == Itemizer.CoralCloggerWaterlogged) {
				p.inventory.consumeInventoryItem(Itemizer.CoralCloggerWaterlogged);
				w.playSoundAtEntity(p, "nevermine:Declogging", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.CoralClogger, 1);
				}
				return true;
			}
			if (p.getHeldItem().getItem() == Itemizer.CoralCannonWaterlogged) {
				p.inventory.consumeInventoryItem(Itemizer.CoralCannonWaterlogged);
				w.playSoundAtEntity(p, "nevermine:Declogging", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.CoralCannon, 1);
				}
				return true;
			}
			if (p.getHeldItem().getItem() == Itemizer.ReeferWaterlogged) {
				p.inventory.consumeInventoryItem(Itemizer.ReeferWaterlogged);
				w.playSoundAtEntity(p, "nevermine:Declogging", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.Reefer, 1);
				}
				return true;
			}
			if (p.getHeldItem().getItem() == Itemizer.AquaCannonWaterlogged) {
				p.inventory.consumeInventoryItem(Itemizer.AquaCannonWaterlogged);
				w.playSoundAtEntity(p, "nevermine:Declogging", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.AquaCannon, 1);
				}
				return true;
			}
			if (p.getHeldItem().getItem() == Itemizer.CoralArchergunWaterlogged) {
				p.inventory.consumeInventoryItem(Itemizer.CoralArchergunWaterlogged);
				w.playSoundAtEntity(p, "nevermine:Declogging", 1.85f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.CoralArchergun, 1);
				}
				return true;
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:decloggingTable_top");
		side = icon.registerIcon("nevermine:decloggingTable_side");
		bottom = icon.registerIcon("nevermine:decloggingTable_bottom");
		blockIcon = icon.registerIcon("nevermine:decloggingTable_side");
	}
}
