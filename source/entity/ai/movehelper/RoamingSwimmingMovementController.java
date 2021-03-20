package net.tslat.aoa3.entity.ai.movehelper;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.MathHelper;

public class RoamingSwimmingMovementController extends MovementController {
	private final CreatureEntity entity;

	public RoamingSwimmingMovementController(CreatureEntity entity) {
		super(entity);

		this.entity = entity;
	}

	@Override
	public void tick() {
		if (entity.isEyeInFluid(FluidTags.WATER))
			entity.setDeltaMovement(entity.getDeltaMovement().add(0, -0.0001d, 0));

		if (operation == MovementController.Action.MOVE_TO && !entity.getNavigation().isDone()) {
			float moveSpeed = (float)(speedModifier * entity.getAttributeValue(Attributes.MOVEMENT_SPEED)) * 2f;
			double distanceX = wantedX - entity.getX();
			double distanceY = wantedY - entity.getY();
			double distanceZ = wantedZ - entity.getZ();

			distanceY = distanceY / MathHelper.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);

			entity.setSpeed(MathHelper.lerp(0.2f, entity.getSpeed(), moveSpeed));
			entity.setDeltaMovement(entity.getDeltaMovement().add((double)moveSpeed * distanceX * 0.005d, (double)moveSpeed * distanceY * 0.1d, (double)moveSpeed * distanceZ * 0.005d));

			if (distanceX != 0 || distanceZ != 0) {
				float targetAngle = (float)(MathHelper.atan2(distanceZ, distanceX) * (double)(180f / (float)Math.PI)) - 90;
				entity.yRot = rotlerp(entity.yRot, targetAngle, 90);
				entity.yBodyRot = entity.yRot;
			}

		}
		else {
			entity.setSpeed(0);
		}
	}
}
