package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.block.functional.light.LampBlock;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.ShadowlordEntity;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

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
                    BlockPos pos = altarPosition.add(x, 1, z);
                    Block block = player.world.getBlockState(pos).getBlock();

                    if (block instanceof LampBlock)
                        player.world.setBlockState(pos, block.getDefaultState().with(LampBlock.LIT, true).with(LampBlock.TOGGLEABLE, false));
                }
            }
        }
        else if (spawning) {
            spawned = true;
            ShadowlordEntity shadowlord = new ShadowlordEntity(AoAEntities.Mobs.SHADOWLORD.get(), player.world);

            shadowlord.setLocationAndAngles(altarPosition.getX(), altarPosition.getY() + 3, altarPosition.getZ(), 0, 0);
            player.world.addEntity(shadowlord);
            PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.shadowlord.spawn", player.getDisplayName().getFormattedText()), player.world, player.getPosition(), 50);
            schedule(5, TimeUnit.SECONDS);
        }
        else {
            for (int x = -2; x <= 2; x++) {
                for (int y = 0; y <= 1; y++) {
                    for (int z = -2; z <= 2; z++) {
                        BlockPos pos = altarPosition.add(x, y, z);
                        BlockState state = player.world.getBlockState(pos);
                        Block block = state.getBlock();

                        if (block instanceof LampBlock && state.get(LampBlock.LIT)) {
                            player.world.setBlockState(pos, state.with(LampBlock.LIT, false), 2);
                            schedule(2, TimeUnit.SECONDS);
                            player.world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);

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
