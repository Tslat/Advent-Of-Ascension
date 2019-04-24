package net.tslat.aoa3.item.weapon.bow;

public class DeepBow extends BaseBow {
	public DeepBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("DeepBow");
		setRegistryName("aoa3:deep_bow");
	}
}
