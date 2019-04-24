package net.tslat.aoa3.entity.boss.bane;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

public class EntityBaneClone extends AoAMeleeMob {
	public static final float entityWidth = 0.75f;

	public EntityBaneClone(EntityBane bane) {
		this(bane.world);

		setLocationAndAngles(bane.posX, bane.posY, bane.posZ, rand.nextFloat() * 360, 0);
	}

	public EntityBaneClone(World world) {
		super(world, entityWidth, 2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}
}
