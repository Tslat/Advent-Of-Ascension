package net.tslat.aoa3.item.weapon.bow;

public class AlacrityBow extends BaseBow {
	public AlacrityBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("AlacrityBow");
		setRegistryName("aoa3:alacrity_bow");
	}
}
