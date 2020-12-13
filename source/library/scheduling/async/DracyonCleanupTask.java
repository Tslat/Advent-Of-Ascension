package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.library.scheduling.AoAScheduler;

import java.util.concurrent.TimeUnit;

public class DracyonCleanupTask implements Runnable {
    private final World world;
    private final BlockPos waterPosition;

    public DracyonCleanupTask(World world, BlockPos waterPos) {
        this.world = world;
        this.waterPosition = waterPos;
    }

    @Override
    public void run() {
        if (world.getBlockState(waterPosition).getBlock() == Blocks.WATER)
            world.setBlockState(waterPosition, Blocks.AIR.getDefaultState());
    }

    public void schedule(Integer time, TimeUnit units) {
        AoAScheduler.scheduleRequiredAsyncTask(this, time, units);
    }
}
