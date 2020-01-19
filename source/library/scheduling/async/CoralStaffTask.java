package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.utils.ModUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CoralStaffTask implements Runnable {
    private World world;
    private ArrayList<BlockPos> coralPositions;

    private static final Block coralBlock = BlockRegister.coralPink;

    public CoralStaffTask(World world, ArrayList<BlockPos> coralPositions) {
        this.world = world;
        this.coralPositions = coralPositions;
    }

    @Override
    public void run() {
        for (BlockPos pos : coralPositions) {
            if (world.getBlockState(pos).getBlock() == coralBlock)
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
        }
    }

    public void schedule(Integer time, TimeUnit units) {
        ModUtil.scheduleRequiredAsyncTask(this, time, units);
    }
}
