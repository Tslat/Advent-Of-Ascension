package net.tslat.aoa3.object.entity.mob.lelyetia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nullable;

public class LelyetianWarriorEntity extends AoAMeleeMob {
	public LelyetianWarriorEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.75f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_LELYETIAN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_LELYETIAN_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_LELYETIAN_HURT.get();
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide() && cause.getMsgId().equals("fireworks") && cause.getEntity() instanceof PlayerEntity && ItemUtil.findInventoryItem((PlayerEntity)cause.getEntity(), new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
			ItemUtil.givePlayerItemOrDrop((PlayerEntity)cause.getEntity(), new ItemStack(AoAItems.CELEVE_REALMSTONE.get()));
	}
}
