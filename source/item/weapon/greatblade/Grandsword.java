package net.tslat.aoa3.item.weapon.greatblade;

import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class Grandsword extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public Grandsword(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("Grandsword");
		setRegistryName("aoa3:grandsword");
	}
}
