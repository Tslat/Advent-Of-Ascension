package net.tslat.aoa3.content.entity.mob.precasia;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.IExtensibleEnum;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.UUID;

public class VeloraptorEntity extends AoAMeleeMob<VeloraptorEntity> {
	private static final EntityDataHolder<String> TYPE = EntityDataHolder.register(VeloraptorEntity.class, EntityDataSerializers.STRING, Type.BROWN.name, entity -> entity.type.name, (entity, value) -> entity.type = Type.fromString(value));
	private static final AttributeModifier LUNGE_DAMAGE_MODIFIER = new AttributeModifier(UUID.fromString("a779f2f0-ea09-4357-96f9-d6bdb4e85d97"), "LungeDamageModifier", 3, AttributeModifier.Operation.ADDITION);

	private static final int ATTACK_BITE = 0;
	private static final int ATTACK_POUNCE = 1;
	private Type type = Type.BROWN;

	public VeloraptorEntity(EntityType<? extends VeloraptorEntity> entityType, Level level) {
		super(entityType, level);

		this.attackReach += 0.5f;

		setParts(new AoAEntityPart<>(this, getBbWidth(), 0.9375f, 0, 1, getBbWidth()).setDamageMultiplier(1.1f));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(TYPE);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_RAPTOR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SPINOLEDON_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_RAPTOR_DEATH.get();
	}

	@Override
	public List<ExtendedSensor<? extends VeloraptorEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new AggroBasedNearbyLivingEntitySensor<VeloraptorEntity>()
						.setPredicate((target, entity) -> (target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null) || target instanceof Animal)
						.setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<VeloraptorEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> entity.distanceToSqr(target) < 8 ? 1f : 1.2f),
				new OneRandomBehaviour<>(
						Pair.of(new AnimatableMeleeAttack<>(7).attackInterval(entity -> 8)
								.whenStarting(entity -> ATTACK_STATE.set(entity, ATTACK_BITE))
								.whenStopping(entity -> BrainUtils.setSpecialCooldown(this, 8)), 5),
						Pair.of(new LungeMeleeAttack(15), 1)
				).startCondition(entity -> !BrainUtils.isOnSpecialCooldown(this))
		);
	}

	@Override
	public BrainActivityGroup<? extends VeloraptorEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> target.isAlive() && (!(target instanceof Player player) || !player.getAbilities().invulnerable) && !isAlliedTo(target)),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.level().isDay() ? entity.getRandom().nextInt(30, 60) : entity.getRandom().nextInt(60, 120))));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putString("Variant", this.type.name);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("Variant"))
			TYPE.set(this, compound.getString("Variant"));
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (this.tickCount % 100 == 0) {
			if (!level().isDay()) {
				EntityUtil.applyAttributeModifierSafely(this, AoAAttributes.AGGRO_RANGE.get(), AoAAttributes.NIGHT_AGGRO_MODIFIER, false);
			}
			else {
				EntityUtil.removeAttributeModifier(this, AoAAttributes.AGGRO_RANGE.get(), AoAAttributes.NIGHT_AGGRO_MODIFIER);
			}
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		TYPE.set(this, (RandomUtil.fiftyFifty() ? Type.BROWN : Type.GREEN).name);

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	public Type getVariant() {
		return this.type;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return size.height * 0.99f;
	}

	@Override
	protected int getAttackSwingDuration() {
		return ATTACK_STATE.is(this, ATTACK_BITE) ? 8 : 40;
	}

	@Override
	protected int getPreAttackTime() {
		return ATTACK_STATE.is(this, ATTACK_BITE) ? 4 : 21;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this));
		controllers.add(new AnimationController<>(this, "attacking", 0, state -> {
			if (this.swinging) {
				if (ATTACK_STATE.is(this, ATTACK_BITE)) {
					state.setControllerSpeed(1);

					return state.setAndContinue(DefaultAnimations.ATTACK_BITE);
				}
				else {
					state.setControllerSpeed(1.5f);

					return state.setAndContinue(AoAAnimations.ATTACK_POUNCE);
				}
			}

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		}));
	}

	public enum Type implements IExtensibleEnum {
		BROWN("brown"),
		GREEN("green");

		public final String name;

		Type(final String variant) {
			this.name = variant;
		}

		public static Type fromString(String name) {
			return switch(name) {
				case "brown" -> BROWN;
				case "green" -> GREEN;
				default -> BROWN;
			};
		}

		// Use this to create additional variants of chargers if you're an addon creator
		public static Type create(String name, String variant) {
			throw new IllegalStateException("Enum not extended");
		}
	}

	public static class LungeMeleeAttack extends AnimatableMeleeAttack<VeloraptorEntity> {
		public LungeMeleeAttack(int delayTicks) {
			super(delayTicks);

			attackInterval(entity -> 30);
		}

		@Override
		protected boolean checkExtraStartConditions(ServerLevel level, VeloraptorEntity entity) {
			this.target = BrainUtils.getTargetOfEntity(entity);
			double targetDist = BrainUtils.getTargetOfEntity(entity).distanceToSqr(entity);

			return entity.isSprinting() && targetDist > 16 && targetDist <= 30 && entity.getSensing().hasLineOfSight(this.target);
		}

		@Override
		protected void start(VeloraptorEntity entity) {
			super.start(entity);

			ATTACK_STATE.set(entity, ATTACK_POUNCE);
			entity.getNavigation().stop();

			AoAScheduler.scheduleSyncronisedTask(() -> entity.setDeltaMovement(entity.getDeltaMovement().add(entity.position().vectorTo(BrainUtils.getTargetOfEntity(entity).position())
					.multiply(0.2f, 1, 0.2f)
					.add(0, 0.1f, 0))), 9);
		}

		@Override
		protected void stop(VeloraptorEntity entity) {
			super.stop(entity);

			BrainUtils.setSpecialCooldown(entity, 40);
		}

		@Override
		protected void doDelayedAction(VeloraptorEntity entity) {
			EntityUtil.applyAttributeModifierSafely(entity, Attributes.ATTACK_DAMAGE, LUNGE_DAMAGE_MODIFIER, false);
			super.doDelayedAction(entity);
			EntityUtil.removeAttributeModifier(entity, Attributes.ATTACK_DAMAGE, LUNGE_DAMAGE_MODIFIER);
		}
	}
}
