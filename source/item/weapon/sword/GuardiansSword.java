package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class GuardiansSword extends BaseSword implements AdventWeapon {
	public GuardiansSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("GuardiansSword");
		setRegistryName("aoa3:guardians_sword");
	}
}
