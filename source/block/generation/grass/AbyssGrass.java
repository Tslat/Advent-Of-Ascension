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

public class AbyssGrass extends Block implements IGrowable {
	private static Block.SoundType grass = Block.soundTypeGrass;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public AbyssGrass(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.2f);
		setResistance(0.2f);
		setStepSound(AbyssGrass.grass);
		setHarvestLevel("shovel", 1);
		setTickRandomly(true);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int intSide, final int meta) {
		return (intSide == 1) ? top : ((intSide == 0) ? bottom : side);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:grassAbyss_top");
		side = icon.registerIcon("nevermine:grassAbyss_side");
		bottom = icon.registerIcon("nevermine:stoneAbyss");
		blockIcon = icon.registerIcon("nevermine:grassAbyss_side");
	}

	public void updateTick(final World world, final int x, final int y, final int z, final Random random) {
		if (!world.isRemote) {
			if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2) {
				world.setBlock(x, y, z, Blockizer.AbyssStone);
			}
			else if (world.getBlockLightValue(x, y + 1, z) >= 9) {
				for (int l = 0; l < 4; ++l) {
					final int i1 = x + random.nextInt(3) - 1;
					final int j1 = y + random.nextInt(5) - 3;
					final int k1 = z + random.nextInt(3) - 1;
					if (world.getBlock(i1, j1, k1) == Blockizer.AbyssStone && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2) {
						world.setBlock(i1, j1, k1, this);
					}
				}
			}
		}
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(Blockizer.AbyssStone);
	}

	public boolean func_149851_a(final World world, final int x, final int y, final int z, final boolean flag) {
		return true;
	}

	public boolean func_149852_a(final World world, final Random rand, final int x, final int y, final int z) {
		return true;
	}

	public void func_149853_b(final World world, final Random rand, final int x, final int y, final int z) {
	}
}
