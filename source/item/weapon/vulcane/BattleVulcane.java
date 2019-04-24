package net.tslat.aoa3.item.weapon.vulcane;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class BattleVulcane extends BaseVulcane implements AdventWeapon {
	public BattleVulcane(double dmg, int durability) {
		super(dmg, durability);
		setUnlocalizedName("BattleVulcane");
		setRegistryName("aoa3:battle_vulcane");
	}
}
