package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class AlluricornEntity extends AoAMinion {
	public AlluricornEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.65625f;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (stack != ItemStack.EMPTY && getOwner() != null && getOwnerUUID().equals(player.getUUID())) {
			if (stack.getItem() == AoAItems.KINETIC_RUNE.get() && !player.hasEffect(Effects.MOVEMENT_SPEED)) {
				if (!player.isCreative())
					stack.shrink(1);

				EntityUtil.applyPotions(player, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SPEED, 150).level(4));

				return ActionResultType.SUCCESS;
			}
		}

		return super.mobInteract(player, hand);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_RAINICORN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_RAINICORN_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_RAINICORN_DEATH.get();
	}
}
