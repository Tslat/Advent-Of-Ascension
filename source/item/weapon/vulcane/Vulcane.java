package net.tslat.aoa3.item.weapon.vulcane;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class Vulcane extends BaseVulcane {
	public Vulcane(double dmg, int durability) {
		super(dmg, durability);
		setTranslationKey("Vulcane");
		setRegistryName("aoa3:vulcane");
	}
}
