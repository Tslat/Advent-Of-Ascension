package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class EmberstoneSword extends BaseSword {
	public EmberstoneSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("EmberstoneSword");
		setRegistryName("aoa3:emberstone_sword");
	}
}
