package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class LimoniteSword extends BaseSword implements AdventWeapon {
	public LimoniteSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("LimoniteSword");
		setRegistryName("aoa3:limonite_sword");
	}
}
