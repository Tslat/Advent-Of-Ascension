package net.tslat.aoa3.entity.mob.nether;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.KingBambambamEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class LittleBamEntity extends AoAMeleeMob {
	private boolean isMinion = false;

	public LittleBamEntity(KingBambambamEntity kingBambambam) {
		this(AoAEntities.Mobs.LITTLE_BAM.get(), kingBambambam.world);

		setLocationAndAngles(kingBambambam.getPosX(), kingBambambam.getPosY(), kingBambambam.getPosZ(), rand.nextFloat() * 360, 0);

		isMinion = true;
	}

	public LittleBamEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void setAttackTarget(@Nullable LivingEntity target) {
		if (target instanceof KingBambambamEntity)
			return;

		super.setAttackTarget(target);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.78125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_BAT_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_BAT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_BAT_HURT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return isMinion	? null : super.getLootTable();
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		if (compound.contains("IsKingBambambamMinion"))
			isMinion = true;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

		if (isMinion)
			compound.putBoolean("IsKingBambambamMinion", true);
	}

	@Override
	protected void onAttack(Entity target) {
		WorldUtil.createExplosion(this, world, 2f);
	}
}
