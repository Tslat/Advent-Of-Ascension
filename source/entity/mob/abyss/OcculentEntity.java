package net.tslat.aoa3.entity.mob.abyss;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.concurrent.CopyOnWriteArrayList;

public class OcculentEntity extends AoAMeleeMob {
	public final CopyOnWriteArrayList<Pair<Integer, Vector3d>> clones = new CopyOnWriteArrayList<Pair<Integer, Vector3d>>();

	public OcculentEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.85f;
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
			double xPos = random.nextFloat() * 10 - 5;
			double zPos = random.nextFloat() * 10 - 5;

			clones.add(Pair.of(GlobalEvents.tick + 600, new Vector3d(xPos, 0, zPos)));
		}
	}
}
