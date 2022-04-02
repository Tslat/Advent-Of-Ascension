package net.tslat.aoa3.content.entity.brain.sensor;

import com.google.common.collect.ImmutableSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.projectile.Projectile;
import net.tslat.aoa3.common.registration.AoABrainMemories;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class IncomingProjectilesSensor extends Sensor<Mob> {
	public IncomingProjectilesSensor() {
		super(3);
	}

	@Override
	protected void doTick(ServerLevel level, Mob owner) {
		List<Projectile> projectiles = level.getEntitiesOfClass(Projectile.class, owner.getBoundingBox().inflate(7, 7, 7), projectile -> {
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
