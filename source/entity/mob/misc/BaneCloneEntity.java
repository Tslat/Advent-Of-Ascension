package net.tslat.aoa3.entity.mob.misc;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.BaneEntity;

public class BaneCloneEntity extends AoAMeleeMob {
	public BaneCloneEntity(BaneEntity bane) {
		this(AoAEntities.Misc.BANE_CLONE.get(), bane.world);

		setLocationAndAngles(bane.getPosX(), bane.getPosY(), bane.getPosZ(), rand.nextFloat() * 360, 0);
	}

	public BaneCloneEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
