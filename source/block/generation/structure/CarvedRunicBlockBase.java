package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.block.functional.AdventPortalBlock;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

public class CarvedRunicBlockBase extends BlockDirectional {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	private String name;

	public CarvedRunicBlockBase(final Material p_i45394_1_, final String strname) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setLightLevel(0.0f);
		name = strname;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		Block bl;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				for (int k = z - 1; k <= z + 1; k++) {
					bl = world.getBlock(i, j, k);
					if (bl instanceof AdventPortalBlock) {
						for (int a = j; a > j - 5; a--) {
							if (world.getBlock(i, a, k) == bl)
								continue;

							for (int b = i - 2; b < i + 2; b++) {
								if (world.getBlock(b, a, z) == Blockizer.CarvedRune4) {
									if (!CarvedRunicBlock.checkPortal(world, 2, b, a, z))
										CarvedRunicBlock.breakPortal(world, b, a, z);

									return;
								}
							}

							for (int c = z - 2; c < z + 2; c++) {
								if (world.getBlock(i, a, c) == Blockizer.CarvedRune4) {
									if (!CarvedRunicBlock.checkPortal(world, 4, i, a, c))
										CarvedRunicBlock.breakPortal(world, i, a, c);

									return;
								}
							}

							return;
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		final int var3 = par2 & 0xC;
		return (var3 == 0 && (par1 == 1 || par1 == 0)) ? top : ((var3 == 4 && (par1 == 5 || par1 == 4)) ? top : ((var3 == 8 && (par1 == 2 || par1 == 3)) ? top : side));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:ancientRock");
		bottom = top;
		side = icon.registerIcon("nevermine:animated/" + name);
		blockIcon = icon.registerIcon("nevermine:animated/" + name);
	}
}
