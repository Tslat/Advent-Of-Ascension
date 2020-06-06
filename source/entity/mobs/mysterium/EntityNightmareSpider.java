package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityNightmareSpider extends AoAMeleeMob {
	public static final float entityWidth = 1.4f;
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityNightmareSpider.class, DataSerializers.BYTE);

	public EntityNightmareSpider(World world) {
		super(world, entityWidth, 0.8125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.59275f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(CLIMBING, (byte)0);
	}

	@Override
	protected PathNavigate createNavigator(World world) {
		return new PathNavigateClimber(this, world);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 63d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.28d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_NIGHTMARE_SPIDER_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_NIGHTMARE_SPIDER_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_NIGHTMARE_SPIDER_HIT;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SPIDER_STEP;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityNightmareSpider;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void setInWeb() {}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote)
			setBesideClimbableBlock(this.collidedHorizontally);
	}

	@Override
	public boolean isOnLadder() {
		return isBesideClimbableBlock();
	}

	public boolean isBesideClimbableBlock() {
		return (this.dataManager.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte climbingBit = this.dataManager.get(CLIMBING);

		if (climbing) {
			climbingBit = (byte)(climbingBit | 1);
		}
		else {
			climbingBit = (byte)(climbingBit & -2);
		}

		this.dataManager.set(CLIMBING, climbingBit);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 10, 0, false, true));
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 0, false, true));
		}
	}
}
