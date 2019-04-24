package net.tslat.aoa3.item.armour;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.tslat.aoa3.library.Enums;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURLYNDAMYTE;

public class LyndamyteArmour extends AdventArmour {
	public LyndamyteArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURLYNDAMYTE, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.LYNDAMYTE;
	}
}
