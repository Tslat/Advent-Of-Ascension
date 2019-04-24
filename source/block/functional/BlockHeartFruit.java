package net.nevermine.block.functional;

import net.minecraft.item.Item;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Plantizer;

public class BlockHeartFruit extends BlockCrop {
	public BlockHeartFruit() {
		super("heartFruit");
	}

	public int getRenderType() {
		return 1;
	}

	protected Item func_149866_i() {
		return Plantizer.HeartFruitSeeds;
	}

	protected Item func_149865_P() {
		return Itemizer.HeartFruit;
	}
}
