package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class ShadowSword extends BaseSword implements AdventWeapon {
	public ShadowSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("ShadowSword");
		setRegistryName("aoa3:shadow_sword");
	}
}
