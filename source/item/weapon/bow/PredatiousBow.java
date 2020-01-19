package net.tslat.aoa3.item.weapon.bow;

public class PredatiousBow extends BaseBow {
	public PredatiousBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("PredatiousBow");
		setRegistryName("aoa3:predatious_bow");
	}
}