package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityDistorter extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6f;

	private int effectTick = 60;

	public EntityDistorter(World world) {
		super(world, entityWidth, 2.125f);

		mobProperties.add(Enums.MobProperties.MAGIC_IMMUNE);
		mobProperties.add(Enums.MobProperties.BLASTER_IMMUNE);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 15.0f));
		tasks.addTask(2, new EntityAILookIdle(this));
	}

	@Override
	public float getEyeHeight() {
		return 1.675f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 95;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_DISTORTER_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_DISTORTER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_DISTORTER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityDistorter;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isBlasterDamage(source) || EntityUtil.isMagicDamage(source, this, damage);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		effectTick--;

		if (ticksExisted % 5 == 0) {
			Potion currentEffect = effectTick <= 30 ? MobEffects.SLOWNESS : MobEffects.SPEED;

			for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(2), pl -> pl != null && !pl.isSpectator() && !pl.isCreative() && canEntityBeSeen(pl))) {
				pl.addPotionEffect(new PotionEffect(currentEffect, 5, 5, true, false));
			}
		}

		if (effectTick <= 0)
			effectTick = 60;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
