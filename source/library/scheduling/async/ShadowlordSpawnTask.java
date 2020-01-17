package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.lamps.LampBlock;
import net.tslat.aoa3.entity.boss.shadowlord.EntityShadowlord;
import net.tslat.aoa3.utils.ModUtil;

import java.util.concurrent.TimeUnit;

public class ShadowlordSpawnTask implements Runnable {
    private final World world;
    private final BlockPos altarPosition;
    private boolean spawning = false;
    private boolean spawned = false;

    public ShadowlordSpawnTask(World w, BlockPos altarPosition) {
        this.world = w;
        this.altarPosition = altarPosition;
    }

    @Override
    public void run() {
        if (spawned) {
            for (int x = -2; x <= 2; x += 4) {
                for (int z = -2; z <= 2; z += 4) {
                    BlockPos pos = altarPosition.add(x, 1, z);
                    Block block = world.getBlockState(pos).getBlock();

                    if (block instanceof LampBlock)
                        world.setBlockState(pos, block.getDefaultState().withProperty(LampBlock.FIXED_LAMP, true));
                }
            }
        }
        else if (spawning) {
            spawned = true;
            EntityShadowlord shadowlord = new EntityShadowlord(world);

            shadowlord.setLocationAndAngles(altarPosition.getX(), altarPosition.getY() + 3, altarPosition.getZ(), 0, 0);
            world.spawnEntity(shadowlord);
            schedule(5, TimeUnit.SECONDS);
        }
        else {
            for (int x = -2; x <= 2; x++) {
                for (int y = 0; y <= 1; y++) {
                    for (int z = -2; z <= 2; z++) {
                        BlockPos pos = altarPosition.add(x, y, z);
                        IBlockState state = world.getBlockState(pos);
                        Block block = state.getBlock();

                        if (block instanceof LampBlock && block != ((LampBlock)block).getOffLamp()) {
                            world.setBlockState(pos, ((LampBlock)block).getOffLamp().getDefaultState(), 2);
                            schedule(2, TimeUnit.SECONDS);
                            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);

                            return;
                        }
                        else if (block instanceof BlockTorch) {
                            block.dropBlockAsItem(world, pos, state, 0);
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                            schedule(2, TimeUnit.SECONDS);
                            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);

                            return;
                        }
                    }
                }
            }

            spawning = true;
            schedule(1, TimeUnit.SECONDS);
        }
    }

    public void schedule(Integer time, TimeUnit units) {
        ModUtil.scheduleRequiredAsyncTask(this, time, units);
    }
}
