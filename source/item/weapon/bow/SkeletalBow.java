package net.tslat.aoa3.item.weapon.bow;

public class SkeletalBow extends BaseBow {
	public SkeletalBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("SkeletalBow");
		setRegistryName("aoa3:skeletal_bow");
	}
}