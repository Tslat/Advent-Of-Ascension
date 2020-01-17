package net.tslat.aoa3.item.weapon.bow;

public class MechaBow extends BaseBow {
	public MechaBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("MechaBow");
		setRegistryName("aoa3:mecha_bow");
	}
}
