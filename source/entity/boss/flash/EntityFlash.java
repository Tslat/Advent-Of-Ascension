package net.tslat.aoa3.entity.boss.flash;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityFlash extends AoAMeleeMob implements BossEntity {
	public static final float entityWidth = 0.8f;

	public EntityFlash(World world) {
		super(world, entityWidth, 2f);

		this.setSlipperyMovement();
		this.setAIMoveSpeed(3.2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobImmortalLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobImmortalDeath;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityFlash;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote && EntityUtil.isMeleeDamage(source)) {
			if (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.immortallis) {
				switch (rand.nextInt(3)) {
					case 0:
						setPositionAndUpdate(235, 22, 10);
						break;
					case 1:
						setPositionAndUpdate(235, 22, -3);
						break;
					case 2:
						setPositionAndUpdate(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)posX + (rand.nextInt(14) - 7);
				int z = (int)posZ + (rand.nextInt(14) - 7);
				setPosition(x, world.getHeight(x, z), z);
			}
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (!world.isRemote) {
			if (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.immortallis) {
				switch (rand.nextInt(3)) {
					case 0:
						setPositionAndUpdate(235, 22, 10);
						break;
					case 1:
						setPositionAndUpdate(235, 22, -3);
						break;
					case 2:
						setPositionAndUpdate(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)posX + (rand.nextInt(14) - 7);
				int z = (int)posZ + (rand.nextInt(14) - 7);
				setPosition(x, world.getHeight(x, z), z);
			}
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return null;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return null;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
