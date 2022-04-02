package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class RoamingSwimmingMovementController extends MoveControl {
	private final PathfinderMob entity;

	public RoamingSwimmingMovementController(PathfinderMob entity) {
		super(entity);

		this.entity = entity;
	}

	@Override
	public void tick() {
		if (entity.isEyeInFluid(FluidTags.WATER))
			entity.setDeltaMovement(entity.getDeltaMovement().add(0, -0.0001d, 0));

		if (operation == MoveControl.Operation.MOVE_TO && !entity.getNavigation().isDone()) {
			float moveSpeed = (float)(speedModifier * entity.getAttributeValue(Attributes.MOVEMENT_SPEED)) * 2f;
			double distanceX = wantedX - entity.getX();
			double distanceY = wantedY - entity.getY();
			double distanceZ = wantedZ - entity.getZ();

			distanceY = distanceY / Math.sqrt((distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ));

			entity.setSpeed(Mth.lerp(0.2f, entity.getSpeed(), moveSpeed));
			entity.setDeltaMovement(entity.getDeltaMovement().add((double)moveSpeed * distanceX * 0.005d, (double)moveSpeed * distanceY * 0.1d, (double)moveSpeed * distanceZ * 0.005d));

			if (distanceX != 0 || distanceZ != 0) {
				float targetAngle = (float)(Mth.atan2(distanceZ, distanceX) * (double)(180f / (float)Math.PI)) - 90;
				entity.setYRot(rotlerp(entity.getYRot(), targetAngle, 90));
				entity.yBodyRot = entity.getYRot();
			}

		}
		else {
			entity.setSpeed(0);
		}
	}
}
