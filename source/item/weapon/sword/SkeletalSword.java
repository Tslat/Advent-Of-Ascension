package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class SkeletalSword extends BaseSword implements AdventWeapon {
	public SkeletalSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("SkeletalSword");
		setRegistryName("aoa3:skeletal_sword");
	}
}
