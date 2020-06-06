package net.tslat.aoa3.entity.boss.hydrolisk;

import com.google.common.base.Predicate;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityHydrolon extends AoAMeleeMob {
	public static final float entityWidth = 1.125f;

	public EntityHydrolon(EntityHydrolisk hydrolisk) {
		this(hydrolisk.world);

		setLocationAndAngles(hydrolisk.posX, hydrolisk.posY, hydrolisk.posZ, rand.nextFloat() * 360, 0);
	}

	public EntityHydrolon(World world) {
		super(world, entityWidth, 2.0625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.6875f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 45;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_CORALON_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_CORALON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_CORALON_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityHydrolon;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater())
			heal(0.8f);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.lborean)
			entityDropItem(new ItemStack(ItemRegister.HYDRO_STONE, 1), 0);
	}
}
