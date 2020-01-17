package net.tslat.aoa3.block.functional.lamps;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.ItemRegister;

import java.util.Random;

public class LifeLampBlock extends LampBlock {
	public LifeLampBlock(String name, String registryName, Material material, float luminosity, float hardness, float resistance) {
		super(name, registryName, material, luminosity, hardness, resistance);
		setTickRandomly(true);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemRegister.ingotEmberstone;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1 + random.nextInt(4);
	}
}
