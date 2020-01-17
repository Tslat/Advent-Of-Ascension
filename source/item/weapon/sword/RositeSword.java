package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class RositeSword extends BaseSword implements AdventWeapon {
	public RositeSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("RositeSword");
		setRegistryName("aoa3:rosite_sword");
	}
}