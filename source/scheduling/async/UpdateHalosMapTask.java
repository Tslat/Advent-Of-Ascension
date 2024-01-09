package net.tslat.aoa3.scheduling.async;

import net.tslat.aoa3.player.halo.PlayerHaloManager;
import net.tslat.aoa3.scheduling.AoAScheduler;

import java.util.concurrent.TimeUnit;

public class UpdateHalosMapTask implements Runnable {
	@Override
	public void run() {
		PlayerHaloManager.updateHalosFromWeb();
		schedule(60, TimeUnit.MINUTES);
	}

	public void schedule(Integer time, TimeUnit units) {
		AoAScheduler.scheduleAsyncTask(this, time, units);
	}
}
