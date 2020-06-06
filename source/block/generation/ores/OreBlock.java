package net.tslat.aoa3.block.generation.ores;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class OreBlock extends BlockOre {
	private Item drop;
	private final int xpMin;
	private final int xpMax;

	public OreBlock(String name, String registryName, int harvestLevel) {
		this(name, registryName, harvestLevel, 0, 0);
	}

	public OreBlock(String name, String registryName, int harvestLevel, int xpDropMin, int xpDropMax) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(5.0f);
		setHarvestLevel("pickaxe", harvestLevel);
		setCreativeTab(CreativeTabsRegister.GENERATION_BLOCKS);

		this.xpMin = xpDropMin;
		this.xpMax = Math.max(xpMin, xpDropMax);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return drop != null ? drop : Item.getItemFromBlock(this);
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		if (xpMax <= 0)
			return 0;

		Random rand = world instanceof World ? ((World)world).rand : AdventOfAscension.rand;

		return rand.nextInt(xpMax - xpMin + 1) + xpMin;
	}

	public void setDrop(Item drop) {
		if (this.drop == null)
			this.drop = drop;
	}
}
