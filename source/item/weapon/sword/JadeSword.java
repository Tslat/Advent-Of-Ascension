package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class JadeSword extends BaseSword implements AdventWeapon {
	public JadeSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("JadeSword");
		setRegistryName("aoa3:jade_sword");
	}
}
