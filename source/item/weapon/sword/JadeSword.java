package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class JadeSword extends BaseSword implements AdventWeapon {
	public JadeSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("JadeSword");
		setRegistryName("aoa3:jade_sword");
	}
}
