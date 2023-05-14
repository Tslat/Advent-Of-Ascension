package net.tslat.aoa3.content.entity.brain.task.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessAttack;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

/**
 * Special attack that performs a ground-slam attack that damages nearby standing targets and throws them back.
 * @param <E> The entity
 */
public class GroundSlamAttack<E extends LivingEntity> extends ConditionlessAttack<E> {
	private SquareRadius radius = new SquareRadius(10, 10);

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

	protected void doSlam(E entity) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		ServerParticlePacket packet = new ServerParticlePacket();
		Level level = entity.getLevel();
		RandomSource rand = entity.getRandom();

		for (int x = entity.getBlockX() - (int)this.radius.xzRadius(); x < entity.getBlockX() + this.radius.xzRadius(); x++) {
			for (int z = entity.getBlockZ() - (int)this.radius.xzRadius(); z < entity.getBlockZ() + this.radius.xzRadius(); z++) {
				BlockState groundState = null;

				for (int y = Math.max(entity.getBlockY() - (int)this.radius.yRadius(), level.getMinBuildHeight()); y < entity.getBlockY() + this.radius.yRadius(); y++) {
					BlockState state;

					if ((state = level.getBlockState(pos.set(x, y, z))).getMaterial().blocksMotion()) {
						groundState = state;
					}
					else if (groundState != null) {
						packet.particle(ParticleBuilder.forPos(new BlockParticleOption(ParticleTypes.BLOCK, groundState), () -> new Vec3(pos.getX() + rand.nextFloat(), pos.getY() + 0.1, pos.getZ() + rand.nextFloat())).spawnNTimes(3));

						groundState = null;
					}
				}
			}
		}

		AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, entity.position(), 50);

		for (LivingEntity target : EntityRetrievalUtil.<LivingEntity>getEntities(entity, radius.xzRadius(), radius.yRadius(), radius.xzRadius(), target -> target.isAlive() && target.isOnGround() && target instanceof LivingEntity && (!(target instanceof Player pl) || !pl.isCreative()))) {
			entity.doHurtTarget(target);
		}
	}
}
