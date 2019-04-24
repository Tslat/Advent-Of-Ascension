package net.nevermine.block.generation;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class AbyssStone extends Block {
	public AbyssStone(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.5f);
		setResistance(0.2f);
	}
}
