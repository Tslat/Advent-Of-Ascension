package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class CrystalliteSword extends BaseSword {
	public CrystalliteSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("CrystalliteSword");
		setRegistryName("aoa3:crystallite_sword");
	}
}
