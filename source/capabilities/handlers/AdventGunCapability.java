package net.tslat.aoa3.capabilities.handlers;

import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseGun;
import net.tslat.aoa3.event.GlobalEvents;

import java.util.UUID;

public class AdventGunCapability implements CapabilityBaseGun {
	private int nextFireTime = -1;
	private UUID lastTarget;
	private float modifier;

	public AdventGunCapability() {}

	@Override
	public int getNextFireTime() {
		return nextFireTime;
	}

	@Override
	public int setNextShotDelay(int firingDelay) {
		return nextFireTime = GlobalEvents.tick + firingDelay;
	}

	@Override
	public UUID getLastTarget() {
		return lastTarget;
	}

	@Override
	public void setLastTarget(UUID target) {
		this.lastTarget = target;
	}

	@Override
	public float getModifier() {
		return modifier;
	}

	@Override
	public void setModifier(float modifier) {
		this.modifier = modifier;
	}
}
