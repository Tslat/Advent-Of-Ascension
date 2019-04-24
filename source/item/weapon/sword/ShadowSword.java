package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class ShadowSword extends BaseSword implements AdventWeapon {
	public ShadowSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("ShadowSword");
		setRegistryName("aoa3:shadow_sword");
	}
}
