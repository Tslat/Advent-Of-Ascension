package net.tslat.aoa3.capabilities.handlers;

import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStack;

public class AdventMiscStackCapability implements CapabilityBaseMiscStack {
	private float value;
	private Object object;

	public AdventMiscStackCapability() {}

	@Override
	public Object getObject() {
		return object;
	}

	@Override
	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public float getValue() {
		return this.value;
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}
}
