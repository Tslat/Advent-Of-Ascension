package net.tslat.aoa3.entity.boss.bane;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityBaneBig extends AoAMeleeMob {
	public static final float entityWidth = 1.2f;

	public EntityBaneBig(EntityBane bane) {
		this(bane.world);

		setLocationAndAngles(bane.posX, bane.posY, bane.posZ, rand.nextFloat() * 360, 0);
	}

	public EntityBaneBig(World world) {
		super(world, entityWidth, 3.9375f);
	}

	@Override
	public float getEyeHeight() {
		return 2.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 10;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			WorldUtil.createExplosion(this, world, 6f);
			setDead();
		}
	}
}
