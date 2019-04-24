package net.tslat.aoa3.item.weapon.bow;

public class ShyregemBow extends BaseBow {
	public ShyregemBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("ShyregemBow");
		setRegistryName("aoa3:shyregem_bow");
	}
}