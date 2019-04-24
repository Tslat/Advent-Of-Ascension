package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class AmethystSword extends BaseSword implements AdventWeapon {
	public AmethystSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("AmethystSword");
		setRegistryName("aoa3:amethyst_sword");
	}
}
