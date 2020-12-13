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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.EnumSet;

public class FriendlyCreeperEntity extends AoAMinion {
	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer>createKey(FriendlyCreeperEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean>createKey(FriendlyCreeperEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.<Boolean>createKey(FriendlyCreeperEntity.class, DataSerializers.BOOLEAN);

	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 20;
	private float explosionRadius = 2.2f;

	public FriendlyCreeperEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world);
	}

	@Override
	protected void registerData() {
		super.registerData();

		dataManager.register(STATE, -1);
		dataManager.register(POWERED, false);
		dataManager.register(IGNITED, false);
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
	protected void registerAttributes() {
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25d);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0d);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8d);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	public int getMaxFallHeight() {
		return getAttackTarget() == null ? 3 : 3 + (int)getHealth() - 1;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		boolean result = super.onLivingFall(distance, damageMultiplier);

		timeSinceIgnited = (int)((float)timeSinceIgnited + distance * 1.5f);

		if (timeSinceIgnited > fuseTime - 5)
			timeSinceIgnited = fuseTime	- 5;

		return result;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

		if (dataManager.get(POWERED))
			compound.putBoolean("powered", true);

		compound.putShort("Fuse", (short)fuseTime);
		compound.putByte("ExplosionRadius", (byte)explosionRadius);
		compound.putBoolean("ignited", hasIgnited());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		dataManager.set(POWERED, compound.getBoolean("powered"));

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
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0f, 0.5f);

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
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.isExplosion() && source.getTrueSource() != null && source.getTrueSource().getUniqueID().equals(getOwnerId()))
			return false;

		return super.attackEntityFrom(source, amount);
	}

	public boolean getPowered() {
		return dataManager.get(POWERED);
	}

	@OnlyIn(Dist.CLIENT)
	public float getCreeperFlashIntensity(float partialTicks) {
		return MathHelper.lerp(partialTicks, (float)lastActiveTime, (float)timeSinceIgnited) / (float)(fuseTime - 2);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	public int getCreeperState() {
		return dataManager.get(STATE);
	}

	public void setCreeperState(int state) {
		dataManager.set(STATE, state);
	}

	@Override
	public void onStruckByLightning(LightningBoltEntity lightningBolt) {
		super.onStruckByLightning(lightningBolt);

		this.dataManager.set(POWERED, true);
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() instanceof FlintAndSteelItem) {
			world.playSound(player, getPosX(), getPosY(), getPosZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0f, rand.nextFloat() * 0.4F + 0.8F);
			player.swingArm(hand);

			if (!world.isRemote) {
				ignite();
				ItemUtil.damageItem(heldStack, player, hand);
			}
		}

		return super.processInteract(player, hand);
	}

	public void explode() {
		if (!world.isRemote) {
			LivingEntity owner = getOwner();

			world.createExplosion(owner == null ? this : owner, getPosX(), getPosY(), getPosZ(), explosionRadius, WorldUtil.checkGameRule(world, AoAGameRules.STRONGER_MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
			spawnLingeringCloud();
			remove();
		}
	}

	private void spawnLingeringCloud() {
		Collection<EffectInstance> collection = this.getActivePotionEffects();
		if (!collection.isEmpty()) {
			AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ());
			areaeffectcloudentity.setRadius(2.5F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());

			for(EffectInstance effectinstance : collection) {
				areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
			}

			this.world.addEntity(areaeffectcloudentity);
		}
	}

	public boolean hasIgnited() {
		return dataManager.get(IGNITED);
	}

	public void ignite() {
		dataManager.set(IGNITED, true);
	}

	private class EntityAIFriendlyCreeperSwell extends Goal {
		private final FriendlyCreeperEntity swellingCreeper;
		private LivingEntity target;

		public EntityAIFriendlyCreeperSwell(FriendlyCreeperEntity creeper) {
			this.swellingCreeper = creeper;

			setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			LivingEntity livingentity = swellingCreeper.getAttackTarget();

			return swellingCreeper.getCreeperState() > 0 || livingentity != null && swellingCreeper.getDistanceSq(livingentity) < 9.0D;
		}

		@Override
		public void startExecuting() {
			swellingCreeper.getNavigator().clearPath();

			target = this.swellingCreeper.getAttackTarget();
		}

		@Override
		public void resetTask() {
			target = null;
		}

		@Override
		public void tick() {
			if (target == null || swellingCreeper.getDistanceSq(target) > 49.0D || !swellingCreeper.getEntitySenses().canSee(target)) {
				swellingCreeper.setCreeperState(-1);
			}
			else {
				swellingCreeper.setCreeperState(1);
			}
		}
	}
}
