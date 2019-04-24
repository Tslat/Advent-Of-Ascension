package net.nevermine.event.tasks;

import net.minecraft.entity.player.EntityPlayer;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.player.Ticker;

import java.util.concurrent.TimeUnit;

public class SyncPlayerTributeTask implements Runnable {
	EntityPlayer player;
	PlayerContainer.Deities tributer;
	int value;

	public SyncPlayerTributeTask(EntityPlayer pl, PlayerContainer.Deities deity, int amount) {
		player = pl;
		tributer = deity;
		value = amount;
	}

	@Override
	public void run() {
		PlayerContainer.getProperties(player).adjustTribute(tributer, value);
	}

	public void schedule(Integer time, TimeUnit units) {
		Ticker.scheduleRequiredTask(this, time, units);
	}
}
