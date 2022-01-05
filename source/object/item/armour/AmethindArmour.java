package net.tslat.aoa3.object.item.armour;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundEvents;
import net.tslat.aoa3.util.ItemUtil;

public class AmethindArmour extends AdventArmour {
	public AmethindArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:amethind", 25, new int[] {3, 6, 7, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 2), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.AMETHIND;
	}
}
