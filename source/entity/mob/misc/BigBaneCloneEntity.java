package net.tslat.aoa3.entity.mob.misc;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.BaneEntity;
import net.tslat.aoa3.util.WorldUtil;

public class BigBaneCloneEntity extends AoAMeleeMob {
	public BigBaneCloneEntity(BaneEntity bane) {
		this(AoAEntities.Misc.BIG_BANE_CLONE.get(), bane.world);

		setLocationAndAngles(bane.getPosX(), bane.getPosY(), bane.getPosZ(), rand.nextFloat() * 360, 0);
	}

	public BigBaneCloneEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
			remove();
		}
	}
}
