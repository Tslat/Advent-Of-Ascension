package net.tslat.aoa3.item.armour;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.tslat.aoa3.library.Enums;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURVOID;

public class VoidArmour extends AdventArmour {
	public VoidArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURVOID, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.VOID;
	}
}
