package net.tslat.aoa3.content.capability.persistentstack;

import net.minecraft.nbt.FloatTag;

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
	public FloatTag serializeNBT() {
		return FloatTag.valueOf(value);
	}

	@Override
	public void deserializeNBT(FloatTag nbt) {
		setValue(nbt.getAsFloat());
	}
}
