package net.tslat.aoa3.content.item.weapon.shotgun;

public class Vivo extends BaseShotgun {
	public Vivo(final float dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, pellets, durability, fireDelayTicks, knockbackFactor, recoil);
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 1.3f;
	}
}
