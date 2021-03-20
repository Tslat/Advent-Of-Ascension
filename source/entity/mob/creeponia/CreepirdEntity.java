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
	protected void onAttack(Entity target) {
		WorldUtil.createExplosion(this, level, 2.5f);
		remove();
	}

	@Override
	protected void onHit(DamageSource source, float amount) {
		if (!level.isClientSide && DamageUtil.isPoisonDamage(source, this, amount) && lastHurtByPlayer != null && ItemUtil.findInventoryItem(lastHurtByPlayer, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
			ItemUtil.givePlayerItemOrDrop(lastHurtByPlayer, new ItemStack(AoAItems.MYSTERIUM_REALMSTONE.get()));
	}
}
