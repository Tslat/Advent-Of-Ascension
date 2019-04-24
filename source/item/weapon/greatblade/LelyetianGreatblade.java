package net.tslat.aoa3.item.weapon.greatblade;

import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class LelyetianGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public LelyetianGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("LelyetianGreatblade");
		setRegistryName("aoa3:lelyetian_greatblade");
	}
}
