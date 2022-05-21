package net.tslat.aoa3.content.block.generation.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.RandomUtil;

public class OreBlock extends Block {
	private final int minXp;
	private final int maxXp;

	public OreBlock(MaterialColor mapColour, int minXp, int maxXp, SoundType sound) {
		super(new BlockUtil.CompactProperties(Material.STONE, mapColour).stats(5f, 10f).needsTool().sound(sound).get());

		this.minXp = minXp;
		this.maxXp = maxXp;
	}

	public OreBlock(MaterialColor mapColour, int minXp, int maxXp) {
		this(mapColour, minXp, maxXp, SoundType.STONE);
	}

	public OreBlock(MaterialColor mapColour) {
		this(mapColour, 0, 0, SoundType.STONE);
	}

	public OreBlock(MaterialColor mapColour, SoundType sound) {
		this(mapColour, 0, 0, sound);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader world, BlockPos pos, int fortune, int silktouch) {
		if (silktouch > 0 || maxXp == 0)
			return 0;

		return (int)(RandomUtil.randomNumberBetween(minXp, maxXp) * (1 + 0.15f * fortune));
	}
}
