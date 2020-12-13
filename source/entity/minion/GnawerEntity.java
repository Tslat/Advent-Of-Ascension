package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.food.HealingFood;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class GnawerEntity extends AoAMinion {
	public GnawerEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.28125f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 430.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9.0d;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		Item item = stack.getItem();

		if (item != Items.AIR && getOwner() != null && getOwnerId().equals(player.getUniqueID()) && item.isIn(ItemTags.FISHES)) {
			if (item instanceof HealingFood) {
				if (!player.isCreative() && player.getHealth() < player.getMaxHealth()) {
					stack.shrink(1);
					EntityUtil.healEntity(player, ((HealingFood)item).getHealAmount());
					setHealth(getHealth() - ((HealingFood)item).getHealAmount());

					return true;
				}
			}
			else if (item.isFood()) {
				if (!player.isCreative() && player.getHealth() < player.getMaxHealth()) {
					stack.shrink(1);
					EntityUtil.healEntity(player, item.getFood().getHealing());
					setHealth(getHealth() - item.getFood().getHealing());

					return true;
				}
			}
		}

		return super.processInteract(player, hand);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHOMPER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHOMPER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return getHurtSound(DamageSource.FALL);
	}
}
