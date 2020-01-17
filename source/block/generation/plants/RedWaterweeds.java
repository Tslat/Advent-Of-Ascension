package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.ItemRegister;

import java.util.Random;

public class RedWaterweeds extends PlantStackable {
	public RedWaterweeds() {
		super("WaterweedsRed", "red_waterweeds", Material.GRASS, Material.GROUND);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemRegister.seedsFloracle;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return random.nextInt(Math.max(1, 5 - fortune)) == 0 ? 1 : 0;
	}
}
