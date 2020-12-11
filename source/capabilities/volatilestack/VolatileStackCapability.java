package net.tslat.aoa3.capabilities.volatilestack;

public class VolatileStackCapability implements VolatileStackCapabilityHandles {
	private Object object;
	private float value;

	@Override
	public float getValue() {
		return value;
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public Object getObject() {
		return object;
	}

	@Override
	public void setObject(Object obj) {
		this.object = obj;
	}
}
