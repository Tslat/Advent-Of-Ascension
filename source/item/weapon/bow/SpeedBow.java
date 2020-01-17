package net.tslat.aoa3.item.weapon.bow;

public class SpeedBow extends BaseBow {
	public SpeedBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("SpeedBow");
		setRegistryName("aoa3:speed_bow");
	}
}
