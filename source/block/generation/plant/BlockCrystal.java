package net.nevermine.block.generation.plant;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class BlockCrystal extends Block {
	private String name;

	public BlockCrystal(final Material Rck) {
		super(Rck);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.4f);
		setResistance(0.5f);
		setStepSound(BlockCrystal.soundTypeGlass);
	}

	public int getRenderType() {
		return 1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void onEntityCollidedWithBlock(final World p_149670_1_, final int p_149670_2_, final int p_149670_3_, final int p_149670_4_, final Entity p_149670_5_) {
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
		return null;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
