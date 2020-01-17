package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class BloodstoneSword extends BaseSword implements AdventWeapon {
	public BloodstoneSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("BloodstoneSword");
		setRegistryName("aoa3:bloodstone_sword");
	}
}
