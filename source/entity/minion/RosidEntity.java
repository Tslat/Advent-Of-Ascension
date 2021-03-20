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
		this(AoAEntities.Minions.ROSID.get(), summoner.level);

		if (summoner instanceof PlayerEntity)
			tame((PlayerEntity)summoner);

		setPos(summoner.getX(), summoner.getY(), summoner.getZ());
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.71875f;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}
}
