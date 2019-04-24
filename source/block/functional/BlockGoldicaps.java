package net.nevermine.block.functional;

import net.minecraft.item.Item;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Plantizer;

public class BlockGoldicaps extends BlockCrop {
	public BlockGoldicaps() {
		super("goldicaps");
	}

	public int getRenderType() {
		return 1;
	}

	protected Item func_149866_i() {
		return Plantizer.GoldicapSeeds;
	}

	protected Item func_149865_P() {
		return Itemizer.GoldicapPedals;
	}
}
