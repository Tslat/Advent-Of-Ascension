package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

import java.util.Random;

public class BulbStockCap extends PlantStackable {
	public BulbStockCap() {
		super("EyeBulbCap", "bulb_stock_cap", Material.GRASS, Material.GROUND);
		setStemBlock((PlantStackable)BlockRegister.getUnmappedBlock("bulb_stock"));
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemRegister.eyeBulb;
	}
}