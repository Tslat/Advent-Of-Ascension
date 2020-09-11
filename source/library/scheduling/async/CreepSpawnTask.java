package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.boss.creep.EntityCreep;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.concurrent.TimeUnit;

public class CreepSpawnTask implements Runnable {
    private final World world;
    private final double centerX;
    private final double centerY;
    private final double centerZ;
    private int currentCorner;
    private int count = 0;

    public CreepSpawnTask(World w, double centerX, double centerY, double centerZ, int startingPosition) {
        this.world = w;
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        this.currentCorner = startingPosition;
    }

    @Override
    public void run() {
        double x = centerX;
        double z = centerZ;

        switch (currentCorner) {
            case 0:
                x += 9;
                z += 9;
                break;
            case 1:
                x -= 9;
                z += 9;
                break;
            case 2:
                x -= 9;
                z -= 9;
                break;
            case 3:
                x += 9;
                z -= 9;
                break;
        }

        WorldUtil.createExplosion(null, world, x, centerY, z, 1.5f, WorldUtil.checkGameRule(world, "doStrongerMobGriefing"));

        count++;

        if (count >= 4) {
            EntityCreep creep = new EntityCreep(world);

            creep.setPosition(x, centerY, z);
            world.spawnEntity(creep);

            return;
        }

        currentCorner += 1;

        if (currentCorner > 3)
            currentCorner = 0;

        schedule(2, TimeUnit.SECONDS);
    }

    public void schedule(Integer time, TimeUnit units) {
        ModUtil.scheduleAsyncTask(this, time, units);
    }
}
