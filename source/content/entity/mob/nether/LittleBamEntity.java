/*
package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.boss.KingBambambamEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class LittleBamEntity extends AoAMeleeMob {
	private boolean isMinion = false;

	public LittleBamEntity(KingBambambamEntity kingBambambam) {
		this(AoAMobs.LITTLE_BAM.get(), kingBambambam.level);

		moveTo(kingBambambam.getX(), kingBambambam.getY(), kingBambambam.getZ(), random.nextFloat() * 360, 0);

		isMinion = true;
	}

	public LittleBamEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		if (target instanceof KingBambambamEntity)
			return;

		super.setTarget(target);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.78125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.BAT_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BAT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BAT_HURT;
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return isMinion	? null : super.getDefaultLootTable();
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("IsKingBambambamMinion"))
			isMinion = true;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		if (isMinion)
			compound.putBoolean("IsKingBambambamMinion", true);
	}

	@Override
	protected void onAttack(Entity target) {
		WorldUtil.createExplosion(this, level, 2f);
	}
}
*/
