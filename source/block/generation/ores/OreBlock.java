package net.tslat.aoa3.block.generation.ores;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class OreBlock extends BlockOre {
	private Item drop;

	public OreBlock(String name, String registryName, int harvestLevel) {
		super();
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(5.0f);
		setHarvestLevel("pickaxe", harvestLevel);
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return drop != null ? drop : Item.getItemFromBlock(this);
	}

	public void setDrop(Item drop) {
		if (this.drop == null)
			this.drop = drop;
	}
}
