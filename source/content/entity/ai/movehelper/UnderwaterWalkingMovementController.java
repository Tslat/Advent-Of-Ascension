package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
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
			if (wantedY <= entity.getY())
				entity.setDeltaMovement(entity.getDeltaMovement().add(0, -0.008d, 0));

			double distanceX = wantedX - entity.getX();
			double distanceY = wantedY - entity.getY();
			double distanceZ = wantedZ - entity.getZ();
			double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
			distanceY = distanceY / distance;
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
