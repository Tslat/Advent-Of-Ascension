package net.tslat.aoa3.object.entity.ai.movehelper;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.MathHelper;

public class UnderwaterWalkingMovementController extends MovementController {
	private final CreatureEntity entity;

	public UnderwaterWalkingMovementController(CreatureEntity entity) {
		super(entity);

		this.entity = entity;
	}

	@Override
	public void tick() {
		if (entity.isEyeInFluid(FluidTags.WATER))
			entity.setDeltaMovement(entity.getDeltaMovement().add(0, -0.008d, 0));

		if (operation == MovementController.Action.MOVE_TO && !entity.getNavigation().isDone()) {
			double distanceX = wantedX - entity.getX();
			double distanceY = wantedY - entity.getY();
			double distanceZ = wantedZ - entity.getZ();
			double distance = MathHelper.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
			distanceY = distanceY / distance;
			float rotation = (float)(MathHelper.atan2(distanceZ, distanceX) * (double)(180f / (float)Math.PI)) - 90f;
			entity.yRot = rotlerp(entity.yRot, rotation, 90f);
			entity.yBodyRot = entity.yRot;
			float moveSpeed = (float)(speedModifier * entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
			float lerpedSpeed = MathHelper.lerp(0.125f, entity.getSpeed(), moveSpeed);

			entity.setSpeed(lerpedSpeed);
			entity.setDeltaMovement(entity.getDeltaMovement().add((double)lerpedSpeed * distanceX * 0.01d, (double)lerpedSpeed * distanceY * 0.1d, (double)lerpedSpeed * distanceZ * 0.01d));
		}
		else {
			entity.setSpeed(0);
		}
	}
}
