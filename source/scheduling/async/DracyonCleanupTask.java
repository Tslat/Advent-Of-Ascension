package net.tslat.aoa3.scheduling.async;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.scheduling.AoAScheduler;

public class DracyonCleanupTask implements Runnable {
    private final Level world;
    private final BlockPos waterPosition;

    public DracyonCleanupTask(Level world, BlockPos waterPos) {
        this.world = world;
        this.waterPosition = waterPos;
    }

    @Override
    public void run() {
        if (world.getBlockState(waterPosition).getBlock() == Blocks.WATER)
            world.setBlockAndUpdate(waterPosition, Blocks.AIR.defaultBlockState());
    }

    public void schedule(int time) {
        AoAScheduler.scheduleSyncronisedTask(this, time);
    }
}
