package net.tslat.aoa3.item.weapon.maul;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class HorizonMaul extends BaseMaul implements AdventWeapon {
	public HorizonMaul(Float dmg, Double speed, double knockback, int durability) {
		super(dmg, speed, knockback, durability);
		setUnlocalizedName("HorizonMaul");
		setRegistryName("aoa3:horizon_maul");
	}
}
