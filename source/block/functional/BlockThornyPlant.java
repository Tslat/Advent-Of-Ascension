package net.nevermine.block.functional;

import net.minecraft.item.Item;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Plantizer;

public class BlockThornyPlant extends BlockCrop {
	public BlockThornyPlant() {
		super("thornyPlant");
	}

	public int getRenderType() {
		return 1;
	}

	protected Item func_149866_i() {
		return Plantizer.ThornyPlantSeeds;
	}

	protected Item func_149865_P() {
		return Itemizer.ThornyPetals;
	}
}
