package net.tslat.aoa3.item.weapon.sword;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class LightsWay extends BaseSword implements AdventWeapon {
	public LightsWay(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("LightsWay");
		setRegistryName("aoa3:lights_way");
	}
}
