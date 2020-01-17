package net.tslat.aoa3.item.weapon.greatblade;

import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class RoyalGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public RoyalGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("RoyalGreatblade");
		setRegistryName("aoa3:royal_greatblade");
	}
}
