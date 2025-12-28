package net.tslat.aoa3.scheduling.async;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.tme.api.scheduling.TickScheduler;

import java.util.List;

public class CoralStaffTask implements TickScheduler.Task {
    private final Level world;
    private final List<BlockPos> coralPositions;

    private static final Block coralBlock = Blocks.BRAIN_CORAL_BLOCK;
    private static final Block deadCoralBlock = Blocks.DEAD_BRAIN_CORAL_BLOCK;

    public CoralStaffTask(Level world, List<BlockPos> coralPositions) {
        this.world = world;
        this.coralPositions = coralPositions;
    }

    @Override
    public void run(int tick) {
        for (BlockPos pos : coralPositions) {
            Block block = world.getBlockState(pos).getBlock();

            if (block == coralBlock || block == deadCoralBlock)
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
    }

    public void schedule(int time) {
        AoAScheduler.schedule(time, this);
    }
}
