package net.tslat.aoa3.content.entity.mob.nether;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.object.AllDirections;
import net.tslat.aoa3.library.object.explosion.ExplosionInfo;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nullable;
import java.util.List;

public class LittleBamEntity extends AoAMeleeMob<LittleBamEntity> {
	private static final ExplosionInfo LITTLE_BAM_EXPLOSION = new ExplosionInfo().radius(6).baseDamage(20).baseKnockbackStrength(1.25f).explodeInOneTick().penetration(30)
			.onHit((explosion, entity) -> entity.setSecondsOnFire(3))
			.onBlockHit((explosion, state, pos) -> {
				if (explosion.level.random.nextInt(3) == 0 && explosion.level.getBlockState(pos.below()).isSolidRender(explosion.level, pos.below()))
					explosion.level.setBlock(pos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
			})
			.particles((explosion, tick) -> {
				Vec3 pos = explosion.getPosition();
				ServerParticlePacket packet = new ServerParticlePacket()
						.particle(ParticleTypes.EXPLOSION_EMITTER, pos.x, pos.y, pos.z, 0, 0, 0);

				for (AllDirections direction : AllDirections.values()) {
					Vec3 angle = direction.angle();
					packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 5, 0x7C0000), pos.x, pos.y, pos.z, angle.x * 10, angle.y * 10, angle.z * 10);
				}

				AoAPackets.messageNearbyPlayers(packet, (ServerLevel)explosion.level, pos, 64);
			});

	public LittleBamEntity(EntityType<? extends LittleBamEntity> entityType, Level world) {
		super(entityType, world);

		setPathfindingMalus(BlockPathTypes.LAVA, 4);
		setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0);
		setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1 + 7 / 16f;
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
	public List<ExtendedSensor<LittleBamEntity>> getSensors() {
		return ObjectArrayList.of(
				new NearbyPlayersSensor<LittleBamEntity>().setPredicate((player, entity) -> !PiglinAi.isWearingGold(player)),
				new NearbyLivingEntitySensor<LittleBamEntity>().setPredicate((target, entity) -> target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<LittleBamEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>(),
				new SetWalkTargetToAttackTarget<>(),
				new ConditionlessAttack<LittleBamEntity>(getAttackSwingDuration())
						.attack(mob -> {
							new StandardExplosion(LITTLE_BAM_EXPLOSION, (ServerLevel)getLevel(), this, getX(0.5), getY(1), getZ(0.5)).explode();
							discard();
						})
						.requiresTarget()
						.startCondition(entity -> {
							LivingEntity target = BrainUtils.getTargetOfEntity(entity);

							return target != null && entity.getSensing().hasLineOfSight(target) && entity.isWithinMeleeAttackRange(target);
						})
						.whenStarting(entity -> {
							getNavigation().stop();
							setImmobile(true);

							ServerParticlePacket packet = new ServerParticlePacket();
							double targetX = getX(0.5f);
							double targetY = getY(1.25f);
							double targetZ = getZ(0.5f);

							for (int i = 0; i < 10; i++) {
								double x = getRandomX(0.5f);
								double y = getRandomY();
								double z = getRandomZ(0.5f);

								packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.25f, 25f, 0x7C0000), x, y, z, (x - targetX) * 2, (y - targetY) * 2, (z - targetZ) * 2);
							}

							AoAPackets.messageNearbyPlayers(packet, (ServerLevel)getLevel(), position(), 64);
							playSound(AoASounds.ENTITY_LITTLE_BAM_CHARGE.get());
						})
		);
	}

	@Override
	public boolean canSwimInFluidType(FluidType type) {
		return type == ForgeMod.LAVA_TYPE.get() || super.canSwimInFluidType(type);
	}

	@Override
	protected int getAttackSwingDuration() {
		return 61;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_POWERUP));
	}
}