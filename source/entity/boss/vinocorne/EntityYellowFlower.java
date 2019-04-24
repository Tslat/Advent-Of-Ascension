package net.tslat.aoa3.entity.boss.vinocorne;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

public class EntityYellowFlower extends AoAMeleeMob {
	public static final float entityWidth = 0.7f;

	public EntityYellowFlower(EntityVinocorne vinocorne) {
		this(vinocorne.world);

		setLocationAndAngles(vinocorne.posX, vinocorne.posY, vinocorne.posZ, rand.nextFloat() * 360, 0);
	}

	public EntityYellowFlower(World world) {
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
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (rand.nextInt(100) == 0)
			addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 35, 1, true, true));
	}
}
