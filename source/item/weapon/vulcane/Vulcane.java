package net.tslat.aoa3.item.weapon.vulcane;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class Vulcane extends BaseVulcane implements AdventWeapon {
	public Vulcane(double dmg, int durability) {
		super(dmg, durability);
		setUnlocalizedName("Vulcane");
		setRegistryName("aoa3:vulcane");
	}
}
