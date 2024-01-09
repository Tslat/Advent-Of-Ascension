package net.tslat.aoa3.content.entity.brain.task.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.PositionAndMotionUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessAttack;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

/**
 * Special attack that performs a ground-slam attack that damages nearby standing targets and throws them back.
 * @param <E> The entity
 */
public class GroundSlamAttack<E extends LivingEntity> extends ConditionlessAttack<E> {
	protected SquareRadius radius = new SquareRadius(10, 10);
	protected boolean atTarget = false;

	public GroundSlamAttack(int delayTicks) {
		super(delayTicks);

		attack(this::doSlam);
	}

	/**
	 * Set the radius for the ground slam's effect
	 * @param radius The coordinate radius, in blocks
	 * @return this
	 */
	public GroundSlamAttack<E> radius(int radius) {
		return radius(radius, radius);
	}

	/**
	 * Set the radius for the ground slam's effect
	 * @param xz The X/Z coordinate radius, in blocks
	 * @param y The Y coordinate radius, in blocks
	 * @return this
	 */
	public GroundSlamAttack<E> radius(double xz, double y) {
		this.radius = new SquareRadius(xz, y);

		return this;
	}

	public GroundSlamAttack<E> slamAtTarget() {
		requiresTarget();

		this.atTarget = true;

		return this;
	}

	protected void doSlam(E entity) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		TELParticlePacket packet = new TELParticlePacket();
		Level level = entity.level();
		RandomSource rand = entity.getRandom();
		Entity originEntity = this.atTarget ? this.target : entity;

		for (int x = -(int)this.radius.xzRadius(); x < this.radius.xzRadius(); x++) {
			for (int z = -(int)this.radius.xzRadius(); z < this.radius.xzRadius(); z++) {
				Vec3 spawnPos = originEntity.position().add(x, 0, z);

				PositionAndMotionUtil.getNearestOnGroundPosition(level, spawnPos).ifPresent(particlePos -> {
					BlockState groundState;

					if ((groundState = level.getBlockState(pos.set(spawnPos.x, spawnPos.y - 1, spawnPos.z))).blocksMotion())
						packet.particle(ParticleBuilder.forPositions(new BlockParticleOption(ParticleTypes.BLOCK, groundState), new Vec3(spawnPos.x + rand.nextGaussian(), spawnPos.y + 1.1, spawnPos.z + rand.nextGaussian())).spawnNTimes(3));
				});
			}
		}

		packet.sendToAllPlayersTrackingEntity((ServerLevel)level, entity);

		for (LivingEntity target : EntityRetrievalUtil.<LivingEntity>getEntities(entity.level(), new AABB(originEntity.position(), originEntity.position()).inflate(radius.xzRadius(), radius.yRadius(), radius.xzRadius()), target -> target.isAlive() && target.onGround() && target != entity && target instanceof LivingEntity && (!(target instanceof Player pl) || !pl.getAbilities().invulnerable))) {
			entity.doHurtTarget(target);
		}
	}
}
