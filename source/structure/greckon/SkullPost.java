package net.tslat.aoa3.structure.greckon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class SkullPost extends AoAStructure {
	private static final IBlockState fence = Blocks.DARK_OAK_FENCE.getDefaultState();
	private static final IBlockState skull = BlockRegister.skullBlock.getDefaultState();
	private static final IBlockState darkSkull = BlockRegister.skullBlockDark.getDefaultState();
	private static final IBlockState hauntedOrb = BlockRegister.hauntedOrb.getDefaultState();

	public SkullPost() {
		super("SkullPost");
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, fence);
		addBlock(world, basePos, 0, 1, 0, fence);

		if (rand.nextInt(3) == 0) {
			addBlock(world, basePos, 0, 2, 0, skull);
		}
		else if (rand.nextBoolean()) {
			addBlock(world, basePos, 0, 2, 0, darkSkull);
		}
		else {
			addBlock(world, basePos, 0, 2, 0, hauntedOrb);
		}

	}
}
