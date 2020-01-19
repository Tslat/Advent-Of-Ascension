package net.tslat.aoa3.item.weapon.greatblade;

import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class LyonicGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public LyonicGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("LyonicGreatblade");
		setRegistryName("aoa3:lyonic_greatblade");
	}
}
