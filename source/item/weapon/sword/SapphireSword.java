package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class SapphireSword extends BaseSword {
	public SapphireSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("SapphireSword");
		setRegistryName("aoa3:sapphire_sword");
	}
}
