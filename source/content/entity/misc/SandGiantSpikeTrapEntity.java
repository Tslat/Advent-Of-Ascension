package net.tslat.aoa3.content.entity.misc;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.tme.api.object.builder.EntityPredicateBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.function.Predicate;

public class SandGiantSpikeTrapEntity extends BasicMiscEntity {
	private static final Predicate<Entity> VALID_TARGET = EntityPredicateBuilder.builder().isAlive().isNot(AoAMonsters.SAND_GIANT).isDamageable().build();

	public SandGiantSpikeTrapEntity(Level level, Vec3 pos) {
		this(AoAMiscEntities.SAND_GIANT_SPIKE_TRAP.get(), level);

		setPos(pos);
	}

	public SandGiantSpikeTrapEntity(EntityType<?> entityType, Level level) {
		super(entityType, level);

		this.isUnmoveable = true;
		this.lifespan = 6000;
	}

	@Override
	public void checkDespawn() {
		if (level().getDifficulty() == Difficulty.PEACEFUL)
			discard();
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide()) {
			if (this.tickCount > 28 && this.tickCount % 10 == 0) {
				for (Entity entity : EntityRetrievalUtil.getEntities(level(), getBoundingBox(), LivingEntity.class, VALID_TARGET)) {
					entity.hurt(level().damageSources().stalagmite(), 3);
				}
			}
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.getSpawnController(this, state -> this, 41));
	}
}
