package net.tslat.aoa3.item.weapon.archergun;

public class MechaArchergun extends BaseArchergun {
	public MechaArchergun(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);
		setTranslationKey("MechaArchergun");
		setRegistryName("aoa3:mecha_archergun");
	}
}
