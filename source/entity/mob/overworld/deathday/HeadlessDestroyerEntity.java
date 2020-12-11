package net.tslat.aoa3.entity.mob.overworld.deathday;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeadlessDestroyerEntity extends AoAMeleeMob {
	public HeadlessDestroyerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.7f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 55;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25d;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SLIMER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SLIMER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public void onKillEntity(LivingEntity victim) {
		if (victim instanceof PlayerEntity && RandomUtil.oneInNChance(10)) {
			ItemStack headStack = new ItemStack(Items.PLAYER_HEAD);
			CompoundNBT tag = headStack.getOrCreateTag();

			tag.putString("SkullOwner", ((PlayerEntity)victim).getGameProfile().getName());
			entityDropItem(headStack);
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected OverworldEvents.Event getEventRequirement() {
		return OverworldEvents.Event.DEATH_DAY;
	}
}
