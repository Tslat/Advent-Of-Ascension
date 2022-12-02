package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class UnderwaterWalkingMovementController extends MoveControl {
	private final PathfinderMob entity;

	public UnderwaterWalkingMovementController(PathfinderMob entity) {
		super(entity);

		this.entity = entity;
	}

	@Override
	public void tick() {
		if (!entity.isEyeInFluid(FluidTags.WATER)) {
			super.tick();

			return;
		}

		if (operation == MoveControl.Operation.MOVE_TO && !entity.getNavigation().isDone()) {
			LivingEntity target = entity.getTarget();
			double distanceX = wantedX - entity.getX();
			double distanceY = wantedY + 1 - entity.getY();
			double distanceZ = wantedZ - entity.getZ();

			if (target != null && target.getY() < entity.getY())
				distanceY = target.getY() - entity.getY();

			distanceY = distanceY / Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
			float rotation = (float)(Mth.atan2(distanceZ, distanceX) * (double)(180f / (float)Math.PI)) - 90f;
			entity.setYRot(rotlerp(entity.getYRot(), rotation, 90f));
			entity.yBodyRot = entity.getYRot();
			float moveSpeed = (float)(speedModifier * entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
			float lerpedSpeed = Mth.lerp(0.125f, entity.getSpeed(), moveSpeed);

			entity.setSpeed(lerpedSpeed);
			entity.setDeltaMovement(entity.getDeltaMovement().add((double)lerpedSpeed * distanceX * 0.01d, (double)lerpedSpeed * distanceY * 0.1d, (double)lerpedSpeed * distanceZ * 0.01d));
		}
		else {
			entity.setDeltaMovement(entity.getDeltaMovement().add(0, -0.008d, 0));
			entity.setSpeed(0);
		}
	}
}
