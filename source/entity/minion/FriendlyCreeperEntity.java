package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.EnumSet;

public class FriendlyCreeperEntity extends AoAMinion {
	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer>defineId(FriendlyCreeperEntity.class, DataSerializers.INT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean>defineId(FriendlyCreeperEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.<Boolean>defineId(FriendlyCreeperEntity.class, DataSerializers.BOOLEAN);

	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 20;
	private float explosionRadius = 2.2f;

	public FriendlyCreeperEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(STATE, -1);
		entityData.define(POWERED, false);
		entityData.define(IGNITED, false);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new EntityAIFriendlyCreeperSwell(this));
		goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8d));
		goalSelector.addGoal(6, new LookAtGoal(this, MonsterEntity.class, 8));
		goalSelector.addGoal(6, new LookRandomlyGoal(this));

		targetSelector.addGoal(1, new NearestAttackableTargetGoal<MonsterEntity>(this, MonsterEntity.class, true));
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	public int getMaxFallDistance() {
		return getTarget() == null ? 3 : 3 + (int)getHealth() - 1;
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		boolean result = super.causeFallDamage(distance, damageMultiplier);

		timeSinceIgnited = (int)((float)timeSinceIgnited + distance * 1.5f);

		if (timeSinceIgnited > fuseTime - 5)
			timeSinceIgnited = fuseTime	- 5;

		return result;
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);

		if (entityData.get(POWERED))
			compound.putBoolean("powered", true);

		compound.putShort("Fuse", (short)fuseTime);
		compound.putByte("ExplosionRadius", (byte)explosionRadius);
		compound.putBoolean("ignited", hasIgnited());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		entityData.set(POWERED, compound.getBoolean("powered"));

		if (compound.contains("Fuse", 99))
			fuseTime = compound.getShort("Fuse");

		if (compound.contains("ExplosionRadius", 99))
			explosionRadius = compound.getByte("ExplosionRadius");

		if (compound.getBoolean("ignited"))
			ignite();
	}

	@Override
	public void tick() {
		if (isAlive()) {
			lastActiveTime = timeSinceIgnited;

			if (hasIgnited())
				setCreeperState(1);

			int state = getCreeperState();

			if (state > 0 && timeSinceIgnited == 0)
				playSound(SoundEvents.CREEPER_PRIMED, 1.0f, 0.5f);

			timeSinceIgnited += state;

			if (timeSinceIgnited < 0)
				timeSinceIgnited = 0;

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;
				explode();
			}
		}

		super.tick();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.CREEPER_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.CREEPER_DEATH;
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		return true;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.isExplosion() && source.getEntity() != null && source.getEntity().getUUID().equals(getOwnerUUID()))
			return false;

		return super.hurt(source, amount);
	}

	public boolean getPowered() {
		return entityData.get(POWERED);
	}

	@OnlyIn(Dist.CLIENT)
	public float getCreeperFlashIntensity(float partialTicks) {
		return MathHelper.lerp(partialTicks, (float)lastActiveTime, (float)timeSinceIgnited) / (float)(fuseTime - 2);
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	public int getCreeperState() {
		return entityData.get(STATE);
	}

	public void setCreeperState(int state) {
		entityData.set(STATE, state);
	}

	@Override
	public void thunderHit(ServerWorld world, LightningBoltEntity lightningBolt) {
		super.thunderHit(world, lightningBolt);

		this.entityData.set(POWERED, true);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() instanceof FlintAndSteelItem) {
			level.playSound(player, getX(), getY(), getZ(), SoundEvents.FLINTANDSTEEL_USE, getSoundSource(), 1.0f, random.nextFloat() * 0.4F + 0.8F);
			player.swing(hand);

			if (!level.isClientSide) {
				ignite();
				ItemUtil.damageItem(heldStack, player, hand);
			}
		}

		return super.mobInteract(player, hand);
	}

	public void explode() {
		if (!level.isClientSide) {
			LivingEntity owner = getOwner();

			level.explode(owner == null ? this : owner, getX(), getY(), getZ(), explosionRadius, WorldUtil.checkGameRule(level, AoAGameRules.STRONGER_MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
			spawnLingeringCloud();
			remove();
		}
	}

	private void spawnLingeringCloud() {
		Collection<EffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty()) {
			AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
			areaeffectcloudentity.setRadius(2.5F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());

			for(EffectInstance effectinstance : collection) {
				areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
			}

			this.level.addFreshEntity(areaeffectcloudentity);
		}
	}

	public boolean hasIgnited() {
		return entityData.get(IGNITED);
	}

	public void ignite() {
		entityData.set(IGNITED, true);
	}

	private class EntityAIFriendlyCreeperSwell extends Goal {
		private final FriendlyCreeperEntity swellingCreeper;
		private LivingEntity target;

		public EntityAIFriendlyCreeperSwell(FriendlyCreeperEntity creeper) {
			this.swellingCreeper = creeper;

			setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse() {
			LivingEntity livingentity = swellingCreeper.getTarget();

			return swellingCreeper.getCreeperState() > 0 || livingentity != null && swellingCreeper.distanceToSqr(livingentity) < 9.0D;
		}

		@Override
		public void start() {
			swellingCreeper.getNavigation().stop();

			target = this.swellingCreeper.getTarget();
		}

		@Override
		public void stop() {
			target = null;
		}

		@Override
		public void tick() {
			if (target == null || swellingCreeper.distanceToSqr(target) > 49.0D || !swellingCreeper.getSensing().canSee(target)) {
				swellingCreeper.setCreeperState(-1);
			}
			else {
				swellingCreeper.setCreeperState(1);
			}
		}
	}
}
