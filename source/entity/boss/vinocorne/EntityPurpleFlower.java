package net.tslat.aoa3.entity.boss.vinocorne;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

public class EntityPurpleFlower extends AoAMeleeMob {
	public static final float entityWidth = 0.7f;

	public EntityPurpleFlower(EntityVinocorne vinocorne) {
		this(vinocorne.world);

		setLocationAndAngles(vinocorne.posX, vinocorne.posY, vinocorne.posZ, rand.nextFloat() * 360, 0);
	}

	public EntityPurpleFlower(World world) {
		super(world, entityWidth, 2.3125f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}
}
