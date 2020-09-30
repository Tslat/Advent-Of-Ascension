package net.tslat.aoa3.entity.mobs.overworld.creepday;

import com.google.common.base.Predicate;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityHost extends AoAMeleeMob {
	public static final float entityWidth = 2.1875f;

	public EntityHost(World world) {
		super(world, entityWidth, 2.0625f);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 1.0f));
		tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0f));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
		tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_HOST_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_HOST_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_HOST_LIVING;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityHost;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isAirBorne && motionY < 0)
			motionY *= 0.8;

		if ((getAttackTarget() == null ? rand.nextInt(100) : rand.nextInt(10)) == 0)
			motionY += 0.3;

		if (!world.isRemote && getAttackTarget() != null && rand.nextInt(80) == 0) {
			EntityCreeper creeper = new EntityCreeper(world);

			creeper.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360f, 0.0f);
			world.spawnEntity(creeper);
			world.playSound(null, posX, posY, posZ, SoundsRegister.MOB_HOST_DROP, SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.CREEP_DAY;
	}
}
