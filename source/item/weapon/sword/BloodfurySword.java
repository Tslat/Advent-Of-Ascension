package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class BloodfurySword extends BaseSword implements AdventWeapon {
	public BloodfurySword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("Bloodfury");
		setRegistryName("aoa3:bloodfury");
	}
}
