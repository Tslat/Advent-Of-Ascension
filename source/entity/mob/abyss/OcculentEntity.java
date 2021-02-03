package net.tslat.aoa3.entity.mob.abyss;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.ClientOperations;

import javax.annotation.Nullable;

public class OcculentEntity extends AoAMeleeMob {
	private OcculentEntity mirageHost = null;

	public OcculentEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	public OcculentEntity(OcculentEntity mirageHost) {
		this(AoAEntities.Mobs.OCCULENT.get(), mirageHost.world);

		this.mirageHost = mirageHost;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.85f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 98;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
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
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (mirageHost != null) {
			return false;
		}
		else {
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (true)
			return;

		if (world.isRemote()) {
			if (mirageHost != null) {
				if (ticksExisted >= 600 || !mirageHost.isAlive()) {
					remove();
					setPositionAndUpdate(0, 0, 0);
				}
			}
			else if (RandomUtil.oneInNChance(200)) {
				OcculentEntity occulent = new OcculentEntity(this);
				double xPos = getPosX() + (int)(rand.nextFloat() * 10 - 5);
				double zPos = getPosZ() + (int)(rand.nextFloat() * 10 - 5);
				double yPos = world.getHeight(Heightmap.Type.WORLD_SURFACE, (int)xPos, (int)zPos);

				occulent.setPosition(xPos, yPos, zPos);
				ClientOperations.spawnClientOnlyEntity(occulent);
			}
		}
	}
}
