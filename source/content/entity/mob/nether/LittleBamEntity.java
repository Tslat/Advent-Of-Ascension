package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.boss.KingBambambamEntity;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class LittleBamEntity extends AoAMeleeMob {
	private boolean isMinion = false;

	public LittleBamEntity(KingBambambamEntity kingBambambam) {
		this(AoAEntities.Mobs.LITTLE_BAM.get(), kingBambambam.level);

		moveTo(kingBambambam.getX(), kingBambambam.getY(), kingBambambam.getZ(), random.nextFloat() * 360, 0);

		isMinion = true;
	}

	public LittleBamEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		if (target instanceof KingBambambamEntity)
			return;

		super.setTarget(target);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("IsKingBambambamMinion"))
			isMinion = true;
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);

		if (isMinion)
			compound.putBoolean("IsKingBambambamMinion", true);
	}

	@Override
	protected void onAttack(Entity target) {
		WorldUtil.createExplosion(this, level, 2f);
	}
}
