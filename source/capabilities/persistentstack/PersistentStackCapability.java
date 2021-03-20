package net.tslat.aoa3.capabilities.persistentstack;

import net.minecraft.nbt.FloatNBT;

public class PersistentStackCapability implements PersistentStackCapabilityHandles {
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
	public FloatNBT serializeNBT() {
		return FloatNBT.valueOf(value);
	}

	@Override
	public void deserializeNBT(FloatNBT nbt) {
		setValue(nbt.getAsFloat());
	}
}
