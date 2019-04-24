package net.tslat.aoa3.item.weapon.maul;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class CrystalMaul extends BaseMaul implements AdventWeapon {
	public CrystalMaul(Float dmg, Double speed, double knockback, int durability) {
		super(dmg, speed, knockback, durability);
		setUnlocalizedName("CrystalMaul");
		setRegistryName("aoa3:crystal_maul");
	}
}
