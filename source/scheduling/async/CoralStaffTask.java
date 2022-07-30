package net.tslat.aoa3.scheduling.async;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.scheduling.AoAScheduler;

import java.util.ArrayList;

public class CoralStaffTask implements Runnable {
    private final Level world;
    private final ArrayList<BlockPos> coralPositions;

    private static final Block coralBlock = Blocks.BRAIN_CORAL_BLOCK;
    private static final Block deadCoralBlock = Blocks.DEAD_BRAIN_CORAL_BLOCK;

    public CoralStaffTask(Level world, ArrayList<BlockPos> coralPositions) {
        this.world = world;
        this.coralPositions = coralPositions;
    }

    @Override
    public void run() {
        for (BlockPos pos : coralPositions) {
            Block block = world.getBlockState(pos).getBlock();

            if (block == coralBlock || block == deadCoralBlock)
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
    }

    public void schedule(int time) {
        AoAScheduler.scheduleSyncronisedTask(this, time);
    }
}
