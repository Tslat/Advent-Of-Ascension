package net.nevermine.block.generation.grass;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class GrassLelyetiaDown extends Block implements IGrowable {
	private static Block.SoundType grass;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public GrassLelyetiaDown(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(1.5f);
		setResistance(0.2f);
		setStepSound(GrassLelyetiaDown.grass);
		setTickRandomly(true);
		setHarvestLevel("shovel", 1);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int intSide, final int meta) {
		return (intSide == 1) ? top : ((intSide == 0) ? bottom : side);
	}

	public Item getItemDropped(final int par1, final Random par2, final int par3) {
		return Item.getItemFromBlock(Blockizer.StoneLelyetia);
	}

	public void updateTick(final World world, final int x, final int y, final int z, final Random random) {
		if (!world.isRemote) {
			if (world.getBlockLightValue(x, y - 1, z) < 4 && world.getBlockLightOpacity(x, y - 1, z) > 2) {
				world.setBlock(x, y, z, Blockizer.StoneLelyetia);
			}
			else if (world.getBlockLightValue(x, y - 1, z) >= 9) {
				for (int l = 0; l < 4; ++l) {
					final int i1 = x + random.nextInt(3) - 1;
					final int j1 = y + random.nextInt(5) - 3;
					final int k1 = z + random.nextInt(3) - 1;
					if (world.getBlock(i1, j1, k1) == Blockizer.StoneLelyetia && world.getBlockLightValue(i1, j1 - 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 - 1, k1) <= 2) {
						world.setBlock(i1, j1, k1, this);
					}
				}
			}
		}
	}

	public boolean func_149851_a(final World world, final int x, final int y, final int z, final boolean flag) {
		return true;
	}

	public boolean func_149852_a(final World world, final Random rand, final int x, final int y, final int z) {
		return true;
	}

	public void func_149853_b(final World world, final Random rand, final int x, final int y, final int z) {
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:stoneLelyetia");
		side = icon.registerIcon("nevermine:grassLelyetiaDown_side");
		bottom = icon.registerIcon("nevermine:grassLelyetia_end");
		blockIcon = icon.registerIcon("nevermine:grassLelyetiaDown_side");
	}

	static {
		GrassLelyetiaDown.grass = Block.soundTypeGrass;
	}
}
