package net.tslat.aoa3.content.entity.misc;

import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.builder.EntityPredicateBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.function.Predicate;

public class SandGiantPitTrapEntity extends BasicMiscEntity {
	private static final Predicate<Entity> TARGET_PREDICATE = EntityPredicateBuilder.builder().isAlive().isNot(AoAMonsters.SAND_GIANT).isDamageable().build();
	private static final EffectBuilder EFFECT = new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 12).level(5).hideEffectIcon().hideParticles().isAmbient();

	public SandGiantPitTrapEntity(Level level, Vec3 pos) {
		this(AoAMiscEntities.SAND_GIANT_PIT_TRAP.get(), level);

		setPos(pos);
	}

	public SandGiantPitTrapEntity(EntityType<?> entityType, Level level) {
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
			if (this.tickCount > 13 && this.tickCount % 5 == 0) {
				for (LivingEntity entity : EntityRetrievalUtil.getEntities(this, 0, LivingEntity.class, TARGET_PREDICATE)) {
					entity.resetFallDistance();
					EntityUtil.applyPotions(entity, this, EFFECT);
				}
			}
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.getSpawnController(this, state -> this, 41));
	}
}
