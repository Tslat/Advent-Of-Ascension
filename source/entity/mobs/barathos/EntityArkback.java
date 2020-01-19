package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
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

public class EntityArkback extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 3.8f;

	public EntityArkback(World world) {
		super(world, entityWidth, 3f);

		mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
		mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
	}

	@Override
	public float getEyeHeight() {
		return 2.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 14;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Override
	protected double getBaseArmour() {
		return 6d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobArkbackLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobArkbackDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobArkbackHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityArkback;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isGunDamage(source) || EntityUtil.isRangedDamage(source, this, damage);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
