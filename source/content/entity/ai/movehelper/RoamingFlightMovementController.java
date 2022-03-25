package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class RoamingFlightMovementController extends MovementController {
	private final MobEntity taskOwner;
	private int courseChangeCooldown;

	public RoamingFlightMovementController(MobEntity creature) {
		super(creature);
		this.taskOwner = creature;
	}

	@Override
	public void tick() {
		if (this.operation == Action.MOVE_TO) {
			double distanceX = this.wantedX - this.taskOwner.getX();
			double distanceY = this.wantedY - this.taskOwner.getY();
			double distanceZ = this.wantedZ - this.taskOwner.getZ();
			double distance = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

			if (this.courseChangeCooldown-- <= 0) {
				this.courseChangeCooldown += this.taskOwner.getRandom().nextInt(5) + 2;
				distance = MathHelper.sqrt(distance);

				if (!this.doesPathCollide(this.wantedX, this.wantedY, this.wantedZ, distance)) {
					taskOwner.setDeltaMovement(taskOwner.getDeltaMovement().add(distanceX / distance * 0.1D, distanceY / distance * 0.1D, distanceZ / distance * 0.1D));
				}
				else {
					this.operation = Action.WAIT;
				}
			}
		}
	}

	public boolean doesPathCollide(double posX, double posY, double posZ, double distance) {
		double d0 = (posX - this.taskOwner.getX()) / distance;
		double d1 = (posY - this.taskOwner.getY()) / distance;
		double d2 = (posZ - this.taskOwner.getZ()) / distance;
		AxisAlignedBB collisionBox = this.taskOwner.getBoundingBox();

		for (int i = 1; i < distance; ++i) {
			collisionBox = collisionBox.move(d0, d1, d2);

			if (!taskOwner.level.noCollision(taskOwner))
				return true;
		}

		return false;
	}
}
