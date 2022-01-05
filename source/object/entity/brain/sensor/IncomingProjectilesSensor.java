package net.tslat.aoa3.object.entity.brain.sensor;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoABrainMemories;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class IncomingProjectilesSensor extends Sensor<MobEntity> {
	public IncomingProjectilesSensor() {
		super(3);
	}

	@Override
	protected void doTick(ServerWorld level, MobEntity owner) {
		List<ProjectileEntity> projectiles = level.getEntitiesOfClass(ProjectileEntity.class, owner.getBoundingBox().inflate(7, 7, 7), projectile -> {
			if (projectile.isOnGround() || projectile.horizontalCollision || projectile.verticalCollision)
				return false;

			return owner.getBoundingBox().clip(projectile.position(), projectile.position().add(projectile.getDeltaMovement().multiply(3, 3, 3))).isPresent();
		});

		if (!projectiles.isEmpty())
			projectiles.sort(Comparator.comparingDouble(owner::distanceToSqr));

		owner.getBrain().setMemory(AoABrainMemories.INCOMING_PROJECTILES.get(), projectiles.isEmpty() ? Optional.empty() : Optional.of(projectiles));
	}

	@Override
	public Set<MemoryModuleType<?>> requires() {
		return ImmutableSet.of(AoABrainMemories.INCOMING_PROJECTILES.get());
	}
}
