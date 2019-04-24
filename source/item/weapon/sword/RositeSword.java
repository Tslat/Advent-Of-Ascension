package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class RositeSword extends BaseSword implements AdventWeapon {
	public RositeSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("RositeSword");
		setRegistryName("aoa3:rosite_sword");
	}
}