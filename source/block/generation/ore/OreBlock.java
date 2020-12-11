package net.tslat.aoa3.block.generation.ore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.RandomUtil;

public class OreBlock extends Block {
	private final int minXp;
	private final int maxXp;

	public OreBlock(MaterialColor mapColour, int minXp, int maxXp, int harvestLevel) {
		super(generateBlockProperties(mapColour, harvestLevel));

		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	public OreBlock(MaterialColor mapColour, int harvestLevel) {
		this(mapColour, 0, 0, harvestLevel);
	}

	private static Properties generateBlockProperties(MaterialColor mapColour, int harvestLevel) {
		Properties blockProperties = Properties.create(Material.ROCK, mapColour);

		blockProperties.hardnessAndResistance(5.0f, 10f);
		blockProperties.sound(SoundType.STONE);
		blockProperties.harvestTool(ToolType.PICKAXE);
		blockProperties.harvestLevel(harvestLevel);

		return blockProperties;
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		if (silktouch > 0 || maxXp == 0)
			return 0;

		return (int)(RandomUtil.randomNumberBetween(minXp, maxXp) * (1 + 0.15f * fortune));
	}
}
