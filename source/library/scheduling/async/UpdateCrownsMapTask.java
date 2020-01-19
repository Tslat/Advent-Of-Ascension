package net.tslat.aoa3.library.scheduling.async;

import net.tslat.aoa3.common.handlers.PlayerCrownHandler;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.WebUtil;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UpdateCrownsMapTask implements Runnable {
	@Override
	public void run() {
		PlayerCrownHandler.updateCrownsMap(WebUtil.fillCrownsMap(new HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer>()));
		schedule(30, TimeUnit.MINUTES);
	}

	public void schedule(Integer time, TimeUnit units) {
		ModUtil.scheduleAsyncTask(this, time, units);
	}
}
