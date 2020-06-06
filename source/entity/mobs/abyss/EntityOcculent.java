package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;

import javax.annotation.Nullable;

public class EntityOcculent extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	private EntityOcculent mirageHost = null;

	public EntityOcculent(World world) {
		super(world, entityWidth, 1.5f);
	}

	public EntityOcculent(World world, EntityOcculent mirageHost) {
		super(world, entityWidth, 1.5f);

		this.mirageHost = mirageHost;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 98;
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
		return SoundsRegister.MOB_OCCULENT_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_OCCULENT_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_OCCULENT_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityOcculent;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof EntityPenumbra)
			return;

		super.setAttackTarget(target);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (mirageHost != null) {
			setDead();

			return false;
		}
		else {
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.isRemote) {
			if (mirageHost != null) {
				if (ticksExisted >= 600 || mirageHost.isDead)
					setDead();
			}
			else if (rand.nextInt(200) == 0) {
				EntityOcculent occulent = new EntityOcculent(world, this);
				double xPos = posX + (int)(rand.nextFloat() * 10 - 5);
				double zPos = posZ + (int)(rand.nextFloat() * 10 - 5);
				double yPos = world.getHeight((int)xPos, (int)zPos);

				occulent.setPosition(xPos, yPos, zPos);
				world.spawnEntity(occulent);
			}
		}
	}
}
