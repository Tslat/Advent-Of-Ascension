package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class LunarCreationTable extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public LunarCreationTable(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int p_149691_1_, final int p_149691_2_) {
		return (p_149691_1_ == 0) ? bottom : ((p_149691_1_ == 1) ? top : blockIcon);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking()) {
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbDusk && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.IonRevolver, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
				}
			}
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbDusk && w.getBlock(x - 2, y, z + 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.IonBlaster, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x - 2, y, z + 2, Blocks.air);
				}
			}
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbDarklight && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.LunarStaff, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
				}
			}
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbMoonlight && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar && w.getBlock(x + 2, y, z - 2) == Blockizer.OrbMoonlight) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.MoonlightStaff, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
					w.setBlock(x + 2, y, z - 2, Blocks.air);
				}
			}
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbLunar && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar && w.getBlock(x - 2, y, z + 2) == Blockizer.OrbLunar && w.getBlock(x + 2, y, z - 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.MeteorStaff, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
					w.setBlock(x - 2, y, z + 2, Blocks.air);
					w.setBlock(x + 2, y, z - 2, Blocks.air);
				}
			}
			if (w.getBlock(x + 2, y, z - 2) == Blockizer.OrbMoonlight && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.EnergyCannon, 1);
					w.setBlock(x + 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
				}
			}
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbSunfire && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.SunStaff, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
				}
			}
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbLunar && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.CelestialStaff, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
				}
			}
			if (w.getBlock(x - 2, y, z - 2) == Blockizer.OrbMoonlight && w.getBlock(x + 2, y, z + 2) == Blockizer.OrbLunar) {
				p.worldObj.playSoundAtEntity(p, "nevermine:LunarCreation", 1.0f, 1.0f);
				if (!w.isRemote) {
					p.dropItem(Weaponizer.Atomizer, 1);
					w.setBlock(x - 2, y, z - 2, Blocks.air);
					w.setBlock(x + 2, y, z + 2, Blocks.air);
				}
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:lunarCreationTable_top");
		side = icon.registerIcon("nevermine:lunarCreationTable_side");
		bottom = icon.registerIcon("nevermine:lunarCreationTable_bottom");
		blockIcon = icon.registerIcon("nevermine:lunarCreationTable_side");
	}
}
