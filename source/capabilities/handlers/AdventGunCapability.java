package net.tslat.aoa3.capabilities.handlers;

import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseGun;
import net.tslat.aoa3.event.GlobalEvents;

public class AdventGunCapability implements CapabilityBaseGun {
	private int nextFireTime = -1;

	public AdventGunCapability() {}

	@Override
	public int getNextFireTime() {
		return nextFireTime;
	}

	@Override
	public int setNextFireTime(int firingDelay) {
		return nextFireTime = GlobalEvents.tick + firingDelay;
	}
}
