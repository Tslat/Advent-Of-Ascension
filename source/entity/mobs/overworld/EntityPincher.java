package net.tslat.aoa3.entity.mobs.overworld;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityPincher extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.7f;

	public EntityPincher(World world) {
		super(world, entityWidth, 0.75f);

		mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
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
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 150;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 17;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3286;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobPincherLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobPincherDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobPincherHit;
	}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 55 && super.getCanSpawnHere();
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(75 - lootingMod) == 0)
			dropItem(WeaponRegister.staffAtlantic, 1);

		if (rand.nextInt(75 - lootingMod) == 0)
			dropItem(WeaponRegister.bowAtlantic, 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && (block.getBlock() == Blocks.WATER || WorldUtil.isNaturalOverworldBlock(block));
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getAttackTarget() != null && getAttackTarget().posY > posY)
			motionY = 0.25;

		EntityPlayer nearestPlayer = world.getNearestPlayerNotCreative(this, 15.0);

		if (nearestPlayer == null)
			return;

		AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(nearestPlayer);

		if (!nearestPlayer.isInWater() && cap.getLevel(Enums.Skills.HUNTER) >= getHunterReq())
			nearestPlayer.addVelocity(Math.signum(posX - nearestPlayer.posX) * 0.029, Math.signum(posY - nearestPlayer.posY) * 0.015, Math.signum(posZ - nearestPlayer.posZ) * 0.029);

		if (isInWater()) {
			if (motionX > -1.100000023841858 && motionX < 1.100000023841858)
				motionX *= 1.100000023841858;

			if (motionZ > -1.100000023841858 && motionZ < 1.100000023841858)
				motionZ *= 1.100000023841858;
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 0, true, false));

			if (target instanceof EntityPlayer) {
				target.velocityChanged = true;
				target.addVelocity(motionX * -5, motionY * -2, motionZ * -5);
			}
		}
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isRangedDamage(source, this, 1f);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public int getHunterReq() {
		return 93;
	}

	@Override
	public float getHunterXp() {
		return 6000;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
