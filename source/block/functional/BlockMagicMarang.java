package net.nevermine.block.functional;

import net.minecraft.item.Item;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Plantizer;

public class BlockMagicMarang extends BlockCrop {
	public BlockMagicMarang() {
		super("magicMarang");
	}

	public int getRenderType() {
		return 1;
	}

	protected Item func_149866_i() {
		return Plantizer.MagicMarangSeeds;
	}

	protected Item func_149865_P() {
		return Itemizer.MagicMarang;
	}
}
