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

public class GrassChocolate extends Block implements IGrowable {
	private static SoundType grass = Block.soundTypeGrass;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public GrassChocolate(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setCreativeTab(Itemizer.GenerationTab);
		this.setHardness(1.5f);
		this.setResistance(0.2F);
		this.setStepSound(grass);
		setTickRandomly(true);
		setHarvestLevel("shovel", 1);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int intSide, int meta) {
		return intSide == 1 ? top : (intSide == 0 ? bottom : side);
	}

	public Item getItemDropped(int par1, Random par2, int par3) {
		return Item.getItemFromBlock(Blockizer.DirtCandyland);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote) {
			if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
				world.setBlock(x, y, z, Blockizer.DirtCandyland);
			else if (world.getBlockLightValue(x, y + 1, z) >= 9) {
				for (int l = 0; l < 4; ++l) {
					int i1 = x + random.nextInt(3) - 1;
					int j1 = y + random.nextInt(5) - 3;
					int k1 = z + random.nextInt(3) - 1;

					if (world.getBlock(i1, j1, k1) == Blockizer.DirtCandyland && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
						world.setBlock(i1, j1, k1, this);
				}
			}
		}
	}

	public boolean func_149851_a(World world, int x, int y, int z, boolean flag) {
		return true;
	}

	public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
		return true;
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.top = icon.registerIcon("nevermine:grassChocolate_top");
		this.side = icon.registerIcon("nevermine:grassChocolate_side");
		this.bottom = icon.registerIcon("nevermine:dirtCandyland");
		this.blockIcon = icon.registerIcon("nevermine:grassChocolate_side");
	}

}