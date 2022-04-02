package net.tslat.aoa3.scheduling.async;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.scheduling.AoAScheduler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CoralStaffTask implements Runnable {
    private final Level world;
    private final ArrayList<BlockPos> coralPositions;

    private static final Block coralBlock = AoABlocks.PINK_CORAL.get();

    public CoralStaffTask(Level world, ArrayList<BlockPos> coralPositions) {
        this.world = world;
        this.coralPositions = coralPositions;
    }

    @Override
    public void run() {
        for (BlockPos pos : coralPositions) {
            if (world.getBlockState(pos).getBlock() == coralBlock)
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
    }

    public void schedule(Integer time, TimeUnit units) {
        AoAScheduler.scheduleRequiredAsyncTask(this, time, units);
    }
}
