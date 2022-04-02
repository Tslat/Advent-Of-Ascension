package net.tslat.aoa3.content.item.armour;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.tslat.aoa3.util.ItemUtil;

public class AmethindArmour extends AdventArmour {
	public AmethindArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:amethind", 25, new int[] {3, 6, 7, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 2), slot);
	}

	@Override
	public Type setType() {
		return Type.AMETHIND;
	}
}
