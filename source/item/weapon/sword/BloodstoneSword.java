package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class BloodstoneSword extends BaseSword implements AdventWeapon {
	public BloodstoneSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("BloodstoneSword");
		setRegistryName("aoa3:bloodstone_sword");
	}
}
