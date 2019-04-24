package net.nevermine.block.functional;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.nevermine.izer.Itemizer;

public class Crystallanium extends Block {
	private static Block.SoundType glass;

	public Crystallanium(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.FurnitureTab);
		setHardness(1.0f);
		setResistance(0.5f);
		setLightLevel(0.0f);
		slipperiness = 1.25f;
		setSoundType(Crystallanium.glass);
	}

	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		Crystallanium.glass = Block.soundTypeGlass;
	}
}
