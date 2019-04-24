package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class SapphireSword extends BaseSword implements AdventWeapon {
	public SapphireSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("SapphireSword");
		setRegistryName("aoa3:sapphire_sword");
	}
}
