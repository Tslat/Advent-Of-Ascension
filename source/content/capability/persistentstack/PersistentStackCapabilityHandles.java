package net.tslat.aoa3.content.capability.persistentstack;

import net.minecraft.nbt.FloatTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface PersistentStackCapabilityHandles extends INBTSerializable<FloatTag> {
	float getValue();

	void setValue(float value);
}
