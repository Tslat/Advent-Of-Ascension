package net.tslat.aoa3.entity.mob.gardencia;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class PodPlantEntity extends AoAMeleeMob {
	private  static final AttributeModifier CANDIED_WATER_BUFF = new AttributeModifier(UUID.fromString("d5356e33-40b6-4515-a37b-4377f911f703"), "AoAGardenciaCandiedWaterBuff", 50, AttributeModifier.Operation.ADDITION);
	private boolean candiedWater = false;

	public PodPlantEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_POD_PLANT_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_POD_PLANT_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_POD_PLANT_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 80));
	}

	@Override
	protected void onInsideBlock(BlockState state) {
        if (state.getBlock() == AoABlocks.CANDIED_WATER.get()) {
            if (!candiedWater) {
                EntityUtil.applyAttributeModifierSafely(this, Attributes.MAX_HEALTH, CANDIED_WATER_BUFF, false);
                setHealth(getHealth() * 1.5f);

                candiedWater = true;
            }
        }
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);

		if (candiedWater)
			compound.putBoolean("AoACandiedWater", true);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		candiedWater = compound.contains("AoACandiedWater");
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isAlive() && getHealth() < getMaxHealth()) {
			if (isInWater()) {
				heal(0.2f);
			}
			else if (level.isRainingAt(blockPosition())) {
				heal(0.1f);
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide && candiedWater && cause.getEntity() instanceof PlayerEntity && ItemUtil.findInventoryItem((PlayerEntity)cause.getEntity(), new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
			ItemUtil.givePlayerItemOrDrop((PlayerEntity)cause.getEntity(), new ItemStack(AoAItems.LBOREAN_REALMSTONE.get()));
	}
}
