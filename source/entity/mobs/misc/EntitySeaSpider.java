package net.tslat.aoa3.entity.mobs.misc;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntitySeaSpider extends AoAMeleeMob {
	public static final float entityWidth = 1.4f;
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntitySeaSpider.class, DataSerializers.BYTE);

	public EntitySeaSpider(World world) {
		super(world, entityWidth, 0.8125f);
	}

	public EntitySeaSpider(World world, double posX, double posY, double posZ) {
		this(world);
		setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360, 1.0f);
	}

	@Override
	public float getEyeHeight() {
		return 0.625f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4f));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0f));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.4;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SPIDER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SPIDER_HURT;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SPIDER_STEP;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected PathNavigate createNavigator(World world) {
		return new PathNavigateClimber(this, world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(CLIMBING, (byte)0);
	}

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

	@Override
	public void setInWeb() {}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
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
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 100, true, true));

			return true;
		}

		return false;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
