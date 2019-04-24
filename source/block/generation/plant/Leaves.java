package net.nevermine.block.generation.plant;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;
import java.util.Random;

public class Leaves extends BlockLeavesBase implements IShearable {
	private static Block.SoundType leaves = Block.soundTypeGrass;
	private String name;

	public Leaves() {
		super(Material.leaves, false);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.2f);
		setResistance(0.0f);
		setSoundType(Leaves.leaves);
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	public boolean isShearable(final ItemStack item, final IBlockAccess world, final int x, final int y, final int z) {
		return true;
	}

	public ArrayList<ItemStack> onSheared(final ItemStack item, final IBlockAccess world, final int x, final int y, final int z, final int fortune) {
		final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 0x3));
		return ret;
	}
}
