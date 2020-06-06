package net.tslat.aoa3.library.scheduling.async;

import net.tslat.aoa3.common.handlers.PlayerHaloHandler;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.WebUtil;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UpdateHalosMapTask implements Runnable {
	@Override
	public void run() {
		PlayerHaloHandler.updateHalosMap(WebUtil.fillHalosMap(new HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer>()));
		schedule(60, TimeUnit.MINUTES);
	}

	public void schedule(Integer time, TimeUnit units) {
		ModUtil.scheduleAsyncTask(this, time, units);
	}
}
