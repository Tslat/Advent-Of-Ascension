package net.tslat.aoa3.content.entity.mob.nowhere;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class GoldusEntity extends AoAMeleeMob<GoldusEntity> {
	public GoldusEntity(EntityType<? extends GoldusEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.5f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.IRON_GOLEM_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.IRON_GOLEM_DEATH;
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			Entity attacker = cause.getEntity();

			if (attacker instanceof Player || attacker instanceof TamableAnimal) {
				Player pl = null;

				if (attacker instanceof TamableAnimal) {
					if (((TamableAnimal)attacker).getOwner() instanceof Player)
						pl = (Player)((TamableAnimal)attacker).getOwner();
				}
				else {
					pl = (Player)attacker;
				}
			}
		}
	}
}
