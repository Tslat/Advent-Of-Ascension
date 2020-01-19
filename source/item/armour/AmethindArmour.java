package net.tslat.aoa3.item.armour;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.tslat.aoa3.library.Enums;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_AMETHIND;

public class AmethindArmour extends AdventArmour {
	public AmethindArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_AMETHIND, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.AMETHIND;
	}
}
