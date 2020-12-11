package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CreepirdEntity extends AoAFlyingMeleeMob {
	public CreepirdEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.46875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CREEPIRD_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CREEPIRD_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CREEPIRD_HURT.get();
	}

	@Override
	protected int getMinSpawnHeight() {
		return 50;
	}

	@Override
	protected void onAttack(Entity target) {
		WorldUtil.createExplosion(this, world, 2.5f);
		remove();
	}

	@Override
	protected void onHit(DamageSource source, float amount) {
		if (!world.isRemote && DamageUtil.isPoisonDamage(source, this, amount) && attackingPlayer != null && ItemUtil.findInventoryItem(attackingPlayer, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
			ItemUtil.givePlayerItemOrDrop(attackingPlayer, new ItemStack(AoAItems.MYSTERIUM_REALMSTONE.get()));
	}
}
