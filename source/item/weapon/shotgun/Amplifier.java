package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;

public class Amplifier extends BaseShotgun implements AdventWeapon {
	public Amplifier(final double dmg, final int pellets, final SoundEvent sound, final int durability, final int fireDelayTicks, final float recoil) {
		super(dmg, pellets, sound, durability, fireDelayTicks, recoil);
		setUnlocalizedName("Amplifier");
		setRegistryName("aoa3:amplifier");
	}
}
