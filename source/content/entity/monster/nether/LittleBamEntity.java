package net.tslat.aoa3.content.entity.monster.nether;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class LittleBamEntity extends AoAMeleeMob<LittleBamEntity> {
	public LittleBamEntity(EntityType<? extends LittleBamEntity> entityType, Level world) {
		super(entityType, world);

		setPathfindingMalus(PathType.LAVA, 4);
		setPathfindingMalus(PathType.DANGER_FIRE, 0);
		setPathfindingMalus(PathType.DAMAGE_FIRE, 0);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.PIGLIN_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PIGLIN_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.PIGLIN_HURT;
	}

	@Override
	public float getVoicePitch() {
		return 2f;
	}

	@Override
	public List<ExtendedSensor<? extends LittleBamEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<LittleBamEntity>().setPredicate((player, entity) -> !PiglinAi.isWearingGold(player)),
				new NearbyLivingEntitySensor<LittleBamEntity>().setPredicate((target, entity) -> target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<LittleBamEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new SetWalkTargetToAttackTarget<>(),
				new ConditionlessAttack<LittleBamEntity>(getAttackSwingDuration())
						.attack(mob -> {
							AoAExplosionBuilder.at(this, AoAExplosions.LITTLE_BAM_OVERLOAD, StandardExplosion::new).explode();
							discard();
						})
						.requiresTarget()
						.startCondition(entity -> {
							LivingEntity target = BrainUtils.getTargetOfEntity(entity);

							return target != null && entity.getSensing().hasLineOfSight(target) && entity.isWithinMeleeAttackRange(target);
						})
						.whenStarting(entity -> {
							setImmobile(true);

							TMEParticlePacket packet = new TMEParticlePacket();
							double targetX = getX(0.5f);
							double targetY = getY(1.25f);
							double targetZ = getZ(0.5f);

							for (int i = 0; i < 10; i++) {
								double x = getRandomX(0.5f);
								double y = getRandomY();
								double z = getRandomZ(0.5f);

								float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

								packet.particle(ParticleBuilder.forPosition(AoAParticleTypes.GENERIC_DUST.get(), x, y, z)
										.scaleMod(0.25f)
										.lifespan(Mth.ceil(25 * (this.random.nextFloat() * 0.8f + 0.2f)))
										.colourTint(colourMod * 124 / 255f, 0, 0, 1f)
										.velocity((x - targetX) * 2, (y - targetY) * 2, (z - targetZ) * 2));
							}

							packet.sendToAllPlayersNearby((ServerLevel)level(), position(), 64);
							SoundBuilder.following(AoASounds.ENTITY_LITTLE_BAM_CHARGE, this).play();
						})
		);
	}

	@Override
	public boolean canSwimInFluidType(FluidType type) {
		return type == NeoForgeMod.LAVA_TYPE.value() || super.canSwimInFluidType(type);
	}

	@Override
	protected int getAttackSwingDuration() {
		return 61;
	}

	public static SpawnPlacements.SpawnPredicate<LittleBamEntity> spawnRules(EntityType<LittleBamEntity> entityType) {
		return EntitySpawnConditions.create(entityType).noPeacefulSpawn().spawnChance(1 / 2f).noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock();
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<LittleBamEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(10)
				.moveSpeed(0.32)
				.followRange(14)
				.aggroRange(8);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_POWERUP).transitionLength(0));
	}
}