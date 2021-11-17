package net.tslat.aoa3.scheduling.async;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.block.functional.light.LampBlock;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.ShadowlordEntity;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.concurrent.TimeUnit;

public class ShadowlordSpawnTask implements Runnable {
    private final PlayerEntity player;
    private final BlockPos altarPosition;
    private boolean spawning = false;
    private boolean spawned = false;

    public ShadowlordSpawnTask(PlayerEntity player, BlockPos altarPosition) {
        this.player = player;
        this.altarPosition = altarPosition;
    }

    @Override
    public void run() {
        if (spawned) {
            for (int x = -2; x <= 2; x += 4) {
                for (int z = -2; z <= 2; z += 4) {
                    BlockPos pos = altarPosition.offset(x, 1, z);
                    Block block = player.level.getBlockState(pos).getBlock();

                    if (block instanceof LampBlock)
                        player.level.setBlockAndUpdate(pos, block.defaultBlockState().setValue(LampBlock.LIT, true).setValue(LampBlock.TOGGLEABLE, false));
                }
            }
        }
        else if (spawning) {
            spawned = true;
            ShadowlordEntity shadowlord = new ShadowlordEntity(AoAEntities.Mobs.SHADOWLORD.get(), player.level);

            shadowlord.moveTo(altarPosition.getX(), altarPosition.getY() + 3, altarPosition.getZ(), 0, 0);
            player.level.addFreshEntity(shadowlord);
            PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.SHADOWLORD.get().getDescriptionId() + ".spawn", player.getDisplayName()), player.level, player.blockPosition(), 50);
            schedule(5, TimeUnit.SECONDS);
        }
        else {
            for (int x = -2; x <= 2; x++) {
                for (int y = 0; y <= 1; y++) {
                    for (int z = -2; z <= 2; z++) {
                        BlockPos pos = altarPosition.offset(x, y, z);
                        BlockState state = player.level.getBlockState(pos);
                        Block block = state.getBlock();

                        if (block instanceof LampBlock && state.getValue(LampBlock.LIT)) {
                            player.level.setBlock(pos, state.setValue(LampBlock.LIT, false), 2);
                            schedule(2, TimeUnit.SECONDS);
                            player.level.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);

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
        AoAScheduler.scheduleAsyncTask(this, time, units);
    }
}
