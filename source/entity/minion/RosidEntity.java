package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;

public class RosidEntity extends AoAMinion {
	public RosidEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world, 200);
	}

	public RosidEntity(LivingEntity summoner) {
		this(AoAEntities.Minions.ROSID.get(), summoner.world);

		if (summoner instanceof PlayerEntity)
			setTamedBy((PlayerEntity)summoner);

		setPosition(summoner.getPosX(), summoner.getPosY(), summoner.getPosZ());
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.71875f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20.0d;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}
}
