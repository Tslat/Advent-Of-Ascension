package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.ItemRegister;

import java.util.Random;

public class TangleThorns extends GenericPlantBlock {
	public TangleThorns() {
		super("TangleThorns", "tangle_thorns", 0.0f, Material.GROUND, Material.GRASS);
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemRegister.HEART_FRUIT_SEEDS;
	}
}