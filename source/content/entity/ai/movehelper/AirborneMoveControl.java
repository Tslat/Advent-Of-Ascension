package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class AirborneMoveControl extends MoveControl {
	private final int maxTurn;
	private final boolean hoversInPlace;

	public AirborneMoveControl(Mob mob, int maxTurn, boolean hoversInPlace) {
		super(mob);

		this.maxTurn = maxTurn;
		this.hoversInPlace = hoversInPlace;
	}

	@Override
	public void strafe(float pForward, float pStrafe) {
		this.operation = MoveControl.Operation.STRAFE;
		this.strafeForwards = pForward;
		this.strafeRight = pStrafe;
		this.speedModifier = 0.5d;
	}

	@Override
	public void tick() {
		if (this.operation == Operation.STRAFE) {
			this.mob.setNoGravity(true);

			this.operation = Operation.WAIT;
			float moveSpeed = (float)(this.speedModifier * this.mob.getAttributeValue((this.mob.onGround() ? Attributes.MOVEMENT_SPEED : Attributes.FLYING_SPEED)));

			this.mob.setSpeed(moveSpeed);
			this.mob.setZza(this.strafeForwards);
			this.mob.setXxa(this.strafeRight);
		}
		else if (this.operation == Operation.MOVE_TO) {
			this.mob.setNoGravity(true);

			this.operation = Operation.WAIT;
			double distX = this.wantedX - this.mob.getX();
			double distY = this.wantedY - this.mob.getY();
			double distZ = this.wantedZ - this.mob.getZ();
			double distSq = distX * distX + distY * distY + distZ * distZ;

			if (distSq < 0.00000025d) {
				this.mob.setYya(0);
				this.mob.setZza(0);

				return;
			}

			this.mob.setYRot(rotlerp(this.mob.getYRot(), (float)(Mth.atan2(distZ, distX) * Mth.RAD_TO_DEG) - 90, 90));

			float moveSpeed = (float)(this.speedModifier * this.mob.getAttributeValue((this.mob.onGround() ? Attributes.MOVEMENT_SPEED : Attributes.FLYING_SPEED)));

			double lateralDist = Math.sqrt(distX * distX + distZ * distZ);

			this.mob.setSpeed(moveSpeed);

			if (Math.abs(distY) > (double)0.00001f || Math.abs(lateralDist) > (double)0.00001f) {
				float angle = (float)(-(Mth.atan2(distY, lateralDist) * Mth.RAD_TO_DEG));

				this.mob.setXRot(rotlerp(this.mob.getXRot(), angle, (float)this.maxTurn));
				this.mob.setYya(distY > 0 ? moveSpeed : -moveSpeed);
			}
		}
		else {
			if (!this.hoversInPlace)
				this.mob.setNoGravity(false);

			this.mob.setYya(0);
			this.mob.setZza(0);
		}
	}
}
