package net.tslat.aoa3.library.scheduling.async;

import net.tslat.aoa3.library.misc.AoAHalos;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.util.WebUtil;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UpdateHalosMapTask implements Runnable {
	@Override
	public void run() {
		AoAHalos.updateHalosMap(WebUtil.fillHalosMap(new HashMap<UUID, AoAHalos.PlayerHaloContainer>()));
		schedule(60, TimeUnit.MINUTES);
	}

	public void schedule(Integer time, TimeUnit units) {
		AoAScheduler.scheduleAsyncTask(this, time, units);
	}
}
