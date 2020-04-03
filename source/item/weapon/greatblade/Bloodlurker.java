package net.tslat.aoa3.item.weapon.greatblade;

import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class Bloodlurker extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public Bloodlurker(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("Bloodlurker");
		setRegistryName("aoa3:bloodlurker");
	}
}
