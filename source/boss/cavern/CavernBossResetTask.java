package net.nevermine.boss.cavern;

import net.minecraft.world.World;
import net.nevermine.container.AncientBossesContainer;
import net.nevermine.event.player.Ticker;

import java.util.concurrent.TimeUnit;

public class CavernBossResetTask implements Runnable {
	private int boss;
	private World world;

	public CavernBossResetTask(int target, World w) {
		boss = target;
		world = w;
	}

	@Override
	public void run() {
		AncientBossesContainer.resetCooldown(boss, world);
	}

	public void schedule(Integer time, TimeUnit units) {
		Ticker.scheduleRequiredTask(this, time, units);
	}
}
