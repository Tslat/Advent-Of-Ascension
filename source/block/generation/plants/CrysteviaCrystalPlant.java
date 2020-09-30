package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class CrysteviaCrystalPlant extends PlantStackable {
	private Item druseDrop;

	public CrysteviaCrystalPlant(String name, String registryName) {
		super(name, registryName, Material.GLASS, 0, Material.ROCK);

		setSoundType(SoundType.GLASS);
	}

	public void setDruseDrop(Item druse) {
		this.druseDrop = druse;
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(50) == 0 ? 1 : 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return druseDrop;
	}
}
