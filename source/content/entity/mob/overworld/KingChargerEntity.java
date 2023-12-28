package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.UUID;

public class KingChargerEntity extends AoAMeleeMob<KingChargerEntity> {
	private static final AttributeModifier MINION_FOLLOW_RANGE_MOD = new AttributeModifier(UUID.fromString("52da0dd9-a425-48cb-a2c9-6f0d8d5a25d1"), "KingChargerFollowRangeMod", 40, AttributeModifier.Operation.ADDITION);
	private int nextChargerSpawn = 0;

	public KingChargerEntity(EntityType<? extends KingChargerEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public BrainActivityGroup<KingChargerEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> target.isAlive() && (!(target instanceof Player player) || !player.getAbilities().invulnerable) && !isAlliedTo(target))
						.whenStopping(entity -> EntityRetrievalUtil.<ChargerEntity>getEntities(entity, 40, ally -> ally instanceof ChargerEntity)
								.forEach(charger -> BrainUtils.setTargetOfEntity(charger, BrainUtils.getTargetOfEntity(entity)))),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	@Override
	public BrainActivityGroup<KingChargerEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.125f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.84375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_KING_CHARGER_HURT.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (this.nextChargerSpawn < this.tickCount) {
			LivingEntity target = getTarget();

			if (target != null) {
				ChargerEntity charger = EntitySpawningUtil.spawnEntity((ServerLevel)level(), AoAMobs.CHARGER.get(), RandomUtil.getRandomPositionWithinRange(target.blockPosition(), 40, 10, 40, 30, 0, 30, true, level(), 2, (state, statePos) -> true), MobSpawnType.MOB_SUMMONED);

				charger.getAttribute(Attributes.FOLLOW_RANGE).addTransientModifier(MINION_FOLLOW_RANGE_MOD);

				if (charger != null)
					BrainUtils.setTargetOfEntity(charger, target);
			}

			this.nextChargerSpawn = this.tickCount + RandomUtil.randomNumberBetween(80, 140);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkRunIdleController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE));
	}
}
