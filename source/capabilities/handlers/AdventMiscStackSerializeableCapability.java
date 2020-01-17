package net.tslat.aoa3.capabilities.handlers;

import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStackSerializable;

public class AdventMiscStackSerializeableCapability implements CapabilityBaseMiscStackSerializable {
	private float value;

	public AdventMiscStackSerializeableCapability() {}

	@Override
	public float getValue() {
		return this.value;
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}
}
