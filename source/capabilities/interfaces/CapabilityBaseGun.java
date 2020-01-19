package net.tslat.aoa3.capabilities.interfaces;

import java.util.UUID;

public interface CapabilityBaseGun {
	int getNextFireTime();

	int setNextFireTime(int firingDelay);

	UUID getLastTarget();

	void setLastTarget(UUID target);

	float getModifier();

	void setModifier(float modifier);
}
