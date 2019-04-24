package net.tslat.aoa3.structure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class AoAStructure extends WorldGenerator {
	private final String name;
	public AoAStructure(String name) {
	    this.name = name;

	    StructuresHandler.registerStructure(this);
    }

    private AoAStructure() {
	    this.name = "EMPTY";
    }

    public final String getName() {
	    return name;
    }

    public boolean doPreChecks(World world, Random rand, BlockPos basePos, IBlockState... groundBlocks) {
	    return true;
    }

    @Override
    public final boolean generate(World world, Random rand, BlockPos position) {
        build(world, rand, new BlockPos.MutableBlockPos(position));
        doPostBuildOps(world, rand, position);
        spawnEntities(world, rand, position);

        return true;
    }

    protected abstract void build(World world, Random rand, BlockPos basePos);

    protected void doPostBuildOps(World world, Random rand, BlockPos basePos) {}

    protected void spawnEntities(World world, Random rand, BlockPos basePos) {}

    public final void addBlock(World world, BlockPos pos, int xCoordOffset, int yCoordOffset, int zCoordOffset, IBlockState block) {
        world.setBlockState(pos.add(xCoordOffset, yCoordOffset, zCoordOffset), block);
    }

    static final class EmptyStructure extends AoAStructure {
        EmptyStructure() {
            super();
        }

        @Override
        protected void build(World world, Random rand, BlockPos basePos) {}
    }
}
