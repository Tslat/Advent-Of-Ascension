package net.tslat.aoa3.item.weapon.greatblade;

import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class CoralGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public CoralGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("CoralGreatblade");
		setRegistryName("aoa3:coral_greatblade");
	}
}
