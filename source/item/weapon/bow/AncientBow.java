package net.tslat.aoa3.item.weapon.bow;

public class AncientBow extends BaseBow {
	public AncientBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("AncientBow");
		setRegistryName("aoa3:ancient_bow");
	}
}