package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class LimoniteSword extends BaseSword implements AdventWeapon {
	public LimoniteSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("LimoniteSword");
		setRegistryName("aoa3:limonite_sword");
	}
}
