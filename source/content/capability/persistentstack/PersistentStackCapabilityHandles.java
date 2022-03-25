package net.tslat.aoa3.content.capability.persistentstack;

import net.minecraft.nbt.FloatNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface PersistentStackCapabilityHandles extends INBTSerializable<FloatNBT> {
	float getValue();

	void setValue(float value);
}
