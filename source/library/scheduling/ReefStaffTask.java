package net.tslat.aoa3.library.scheduling;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.utils.ModUtil;

import java.util.concurrent.TimeUnit;

public class ReefStaffTask implements Runnable {
    private World world;
    private BlockPos basePos;

    private static final IBlockState coralBlockState = BlockRegister.coralOrange.getDefaultState();

    public ReefStaffTask(World w, BlockPos blockPos) {
        this.world = w;
        this.basePos = blockPos;
    }

    @Override
    public void run() {
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                if (world.getBlockState(basePos.add(x, -2, z)) == coralBlockState)
                    world.setBlockToAir(basePos.add(x, -2, z));

                if (world.getBlockState(basePos.add(x, 2, z)) == coralBlockState)
                    world.setBlockToAir(basePos.add(x, 2, z));
            }

            for (int y = -2; y < 3; y++) {
                if (world.getBlockState(basePos.add(x, y, -2)) == coralBlockState)
                    world.setBlockToAir(basePos.add(x, y, -2));

                if (world.getBlockState(basePos.add(x, y, 2)) == coralBlockState)
                    world.setBlockToAir(basePos.add(x, y, 2));
            }
        }

        for (int z = -2; z < 3; z++) {
            for (int y = -2; y < 3; y++) {
                if (world.getBlockState(basePos.add(-2, y, z)) == coralBlockState)
                    world.setBlockToAir(basePos.add(-2, y, z));

                if (world.getBlockState(basePos.add(2, y, z)) == coralBlockState)
                    world.setBlockToAir(basePos.add(2, y, z));
            }
        }
    }

    public void schedule(Integer time, TimeUnit units) {
        ModUtil.scheduleRequiredTask(this, time, units);
    }
}
