package net.tslat.aoa3.content.block.generation.ore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.RandomUtil;

public class OreBlock extends Block {
	private final int minXp;
	private final int maxXp;

	public OreBlock(MaterialColor mapColour, int minXp, int maxXp, int harvestLevel) {
		super(new BlockUtil.CompactProperties(Material.STONE, mapColour).stats(5f, 10f).harvestTool(ToolType.PICKAXE, harvestLevel).get());

		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	public OreBlock(MaterialColor mapColour, int harvestLevel) {
		this(mapColour, 0, 0, harvestLevel);
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		if (silktouch > 0 || maxXp == 0)
			return 0;

		return (int)(RandomUtil.randomNumberBetween(minXp, maxXp) * (1 + 0.15f * fortune));
	}
}
