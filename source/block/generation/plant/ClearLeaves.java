package net.nevermine.block.generation.plant;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;
import java.util.Random;

public class ClearLeaves extends BlockLeavesBase implements IShearable {
	private static SoundType leaves = Block.soundTypeGrass;
	private String name;

	public ClearLeaves() {
		super(Material.leaves, false);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.2F);
		setResistance(0.0F);
		setSoundType(leaves);
	}

	public Block setTextureName(String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int quantityDropped(Random p_149745_1_) {
		return 0;
	}

	public int getRenderBlockPass() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		Block i1 = blockAccess.getBlock(x, y, z);
		return i1 == this ? false : super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}

	public Block setName(String name) {
		this.name = name;
		setBlockName(name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	public Block setSoundType(SoundType name) {
		return setStepSound(name);
	}

	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 0x3));
		return ret;
	}
}
