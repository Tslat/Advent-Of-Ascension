package net.tslat.aoa3.item.weapon.bow;

public class AtlanticBow extends BaseBow {
	public AtlanticBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("AtlanticBow");
		setRegistryName("aoa3:atlantic_bow");
	}
}
