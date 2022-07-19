package net.tslat.aoa3.content.entity.misc;

import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntityRetrievalUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

public class SandGiantPitTrapEntity extends BasicMiscEntity {
	private static final EntityPredicate<LivingEntity> damagePredicate = new EntityPredicate<LivingEntity>().isAlive().isSubtypeOf(LivingEntity.class).isNot(AoAMobs.SAND_GIANT.get()).isDamageable();

	public SandGiantPitTrapEntity(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	public SandGiantPitTrapEntity(Level level, Vec3 pos) {
		super(AoAMiscEntities.SAND_GIANT_PIT_TRAP.get(), level);

		setPos(pos);
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid(FluidType type) {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public void checkDespawn() {
		if (level.getDifficulty() == Difficulty.PEACEFUL)
			discard();
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide()) {
			if (tickCount > 6000) {
				discard();

				return;
			}

			if (tickCount % 5 == 0) {
				for (Entity entity : EntityRetrievalUtil.getEntities(level, getBoundingBox(), damagePredicate)) {
					entity.resetFallDistance();
					((LivingEntity)entity).addEffect(new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 12).level(5).hideEffectIcon().hideParticles().isAmbient().build());
				}
			}
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(AoAAnimations.genericSpawnController(this, 41));
	}
}
