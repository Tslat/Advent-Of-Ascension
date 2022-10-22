package net.tslat.aoa3.content.entity.mob.abyss;

import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.concurrent.CopyOnWriteArrayList;

public class OcculentEntity extends AoAMeleeMob<OcculentEntity> {
	public final CopyOnWriteArrayList<Pair<Integer, Vec3>> clones = new CopyOnWriteArrayList<Pair<Integer, Vec3>>();

	public OcculentEntity(EntityType<? extends OcculentEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.85f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_OCCULENT_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_OCCULENT_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_OCCULENT_HURT.get();
	}

	@Override
	public void tick() {
		super.tick();

		if (level.isClientSide() && RandomUtil.oneInNChance(200)) {
			double xPos = rand().nextFloat() * 10 - 5;
			double zPos = rand().nextFloat() * 10 - 5;

			clones.add(Pair.of(GlobalEvents.tick + 600, new Vec3(xPos, 0, zPos)));
		}
	}
}
