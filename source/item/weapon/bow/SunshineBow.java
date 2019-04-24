package net.tslat.aoa3.item.weapon.bow;

public class SunshineBow extends BaseBow {
	public SunshineBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("SunshineBow");
		setRegistryName("aoa3:sunshine_bow");
	}
}