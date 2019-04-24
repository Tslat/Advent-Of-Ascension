package net.tslat.aoa3.item.weapon.bow;

public class VoidBow extends BaseBow {
	public VoidBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("VoidBow");
		setRegistryName("aoa3:void_bow");
	}
}