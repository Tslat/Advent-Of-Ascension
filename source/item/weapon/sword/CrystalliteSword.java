package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class CrystalliteSword extends BaseSword implements AdventWeapon {
	public CrystalliteSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("CrystalliteSword");
		setRegistryName("aoa3:crystallite_sword");
	}
}
