package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;

public class BoltRifle extends BaseSniper implements AdventWeapon {
	public BoltRifle(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("BoltRifle");
		setRegistryName("aoa3:bolt_rifle");
	}
}
