package net.tslat.aoa3.content.entity.monster.overworld;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.DamageUtil;
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
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

public class KingChargerEntity extends AoAMeleeMob<KingChargerEntity> {
	private static final AttributeModifier MINION_FOLLOW_RANGE_MOD = new AttributeModifier(AdventOfAscension.id("king_charger_buff"), 40, AttributeModifier.Operation.ADD_VALUE);
	private int nextChargerSpawn = 0;

	public KingChargerEntity(EntityType<? extends KingChargerEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public BrainActivityGroup<KingChargerEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target))
						.whenStopping(entity -> EntityRetrievalUtil.getEntities(entity, 40, ChargerEntity.class)
								.forEach(charger -> BrainUtils.setTargetOfEntity(charger, BrainUtils.getTargetOfEntity(entity)))),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	@Override
	public BrainActivityGroup<KingChargerEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.125f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2));
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
				ChargerEntity charger = EntitySpawningUtil.spawnEntity((ServerLevel)level(), AoAMonsters.CHARGER.get(), RandomUtil.positionWithinRange(target.blockPosition(), 40, 10, 40, 30, 0, 30, true, level(), 2, (state, statePos) -> true), MobSpawnType.MOB_SUMMONED);

				charger.getAttribute(Attributes.FOLLOW_RANGE).addTransientModifier(MINION_FOLLOW_RANGE_MOD);

				if (charger != null)
					BrainUtils.setTargetOfEntity(charger, target);
			}

			this.nextChargerSpawn = this.tickCount + RandomUtil.numberBetween(80, 140);
		}
	}

	public static SpawnPlacements.SpawnPredicate<KingChargerEntity> spawnRules(EntityType<KingChargerEntity> entityType) {
		return EntitySpawnConditions.createDayMonster(entityType).spawnChance(1 / 16f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<KingChargerEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(75)
				.moveSpeed(0.32)
				.meleeStrength(9)
				.knockbackResist(0.2)
				.followRange(40)
				.aggroRange(16);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkRunIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE).transitionLength(0));
	}
}
