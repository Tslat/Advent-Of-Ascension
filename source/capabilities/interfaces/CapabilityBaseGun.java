package net.tslat.aoa3.capabilities.interfaces;

public interface CapabilityBaseGun {
	int getNextFireTime();

	int setNextFireTime(int firingDelay);
}
