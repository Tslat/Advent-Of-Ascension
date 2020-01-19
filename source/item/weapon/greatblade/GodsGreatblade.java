package net.tslat.aoa3.item.weapon.greatblade;

import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class GodsGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public GodsGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("GodsGreatblade");
		setRegistryName("aoa3:gods_greatblade");
	}
}
