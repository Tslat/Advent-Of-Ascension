package net.tslat.aoa3.entity.mob.immortallis;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class VisageEntity extends AoAMeleeMob {
	private VisageEntity mirageHost = null;

	public VisageEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	public VisageEntity(VisageEntity mirageHost) {
		this(AoAEntities.Mobs.VISAGE.get(), mirageHost.world);

		this.mirageHost = mirageHost;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.46875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PENUMBRA_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PENUMBRA_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_PENUMBRA_HURT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (mirageHost != null) {
			remove();

			return false;
		}
		else {
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (world.isRemote) {
			if (mirageHost != null) {
				if (ticksExisted >= 600 || !mirageHost.isAlive())
					remove();
			}
			else if (RandomUtil.oneInNChance(200)) {
				VisageEntity visage = new VisageEntity(this);
				BlockPos newPos = RandomUtil.getRandomPositionWithinRange(getPosition(), 5, 0, 5, true, world);

				visage.setPosition(newPos.getX(), newPos.getY(), newPos.getZ());
				world.addEntity(visage);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.getDimension().getType() == AoADimensions.IMMORTALLIS.type()) {
			Entity attacker = cause.getTrueSource();

			if (attacker instanceof PlayerEntity || attacker instanceof TameableEntity) {
				ServerPlayerEntity pl = null;

				if (attacker instanceof TameableEntity) {
					if (((TameableEntity)attacker).getOwner() instanceof ServerPlayerEntity)
						pl = (ServerPlayerEntity)((TameableEntity)attacker).getOwner();
				}
				else {
					pl = (ServerPlayerEntity)attacker;
				}

				if (pl != null) {
					PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

					plData.stats().addTribute(Deities.EREBON, 4);

					if (plData.stats().getTribute(Deities.EREBON) == 200)
						plData.sendThrottledChatMessage("message.feedback.immortallisProgression.evilSpiritsEnd");
				}
			}
		}
	}
}
