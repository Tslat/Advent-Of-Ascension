package net.tslat.aoa3.scheduling.async;

import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AoAHaloUtil;
import net.tslat.aoa3.util.WebUtil;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class UpdateHalosMapTask implements Runnable {
	@Override
	public void run() {
		AoAHaloUtil.updateHalosMap(WebUtil.fillHalosMap(new HashMap<>()));
		schedule(60, TimeUnit.MINUTES);
	}

	public void schedule(Integer time, TimeUnit units) {
		AoAScheduler.scheduleAsyncTask(this, time, units);
	}
}
