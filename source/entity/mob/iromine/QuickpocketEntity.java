package net.tslat.aoa3.entity.mob.iromine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class QuickpocketEntity extends AoAMeleeMob {
	public QuickpocketEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 75;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_QUICKPOCKET_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_QUICKPOCKET_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_QUICKPOCKET_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof PlayerEntity && getRevengeTarget() == null) {
			PlayerEntity pl = (PlayerEntity)target;
			
			for (ItemStack stack : pl.inventory.mainInventory) {
				if (stack.getItem().isIn(AoATags.Items.CURRENCY)) {
					int dropAmount = rand.nextInt(stack.getCount() + 1);

					entityDropItem(new ItemStack(stack.getItem(), dropAmount), 0);
					playSound(AoASounds.ENTITY_QUICKPOCKET_STEAL.get(), 1.0f, 1.0f);
					stack.shrink(dropAmount);
					pl.container.detectAndSendChanges();

					return;
				}
			}
		}
	}
}
