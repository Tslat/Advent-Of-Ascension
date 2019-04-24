package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class EmberstoneSword extends BaseSword implements AdventWeapon {
	public EmberstoneSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("EmberstoneSword");
		setRegistryName("aoa3:emberstone_sword");
	}
}
