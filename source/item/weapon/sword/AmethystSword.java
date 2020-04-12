package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class AmethystSword extends BaseSword {
	public AmethystSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("AmethystSword");
		setRegistryName("aoa3:amethyst_sword");
	}
}
