package net.tslat.aoa3.item.weapon.maul;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class CoralstoneMaul extends BaseMaul implements AdventWeapon {
	public CoralstoneMaul(Float dmg, Double speed, double knockback, int durability) {
		super(dmg, speed, knockback, durability);
		setUnlocalizedName("CoralstoneMaul");
		setRegistryName("aoa3:coralstone_maul");
	}
}
