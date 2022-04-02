package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.AABB;

public class RoamingFlightMovementController extends MoveControl {
	private final Mob taskOwner;
	private int courseChangeCooldown;

	public RoamingFlightMovementController(Mob creature) {
		super(creature);
		this.taskOwner = creature;
	}

	@Override
	public void tick() {
		if (this.operation == Operation.MOVE_TO) {
			double distanceX = this.wantedX - this.taskOwner.getX();
			double distanceY = this.wantedY - this.taskOwner.getY();
			double distanceZ = this.wantedZ - this.taskOwner.getZ();
			double distance = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

			if (this.courseChangeCooldown-- <= 0) {
				this.courseChangeCooldown += this.taskOwner.getRandom().nextInt(5) + 2;
				distance = Math.sqrt(distance);

				if (!this.doesPathCollide(this.wantedX, this.wantedY, this.wantedZ, distance)) {
					taskOwner.setDeltaMovement(taskOwner.getDeltaMovement().add(distanceX / distance * 0.1D, distanceY / distance * 0.1D, distanceZ / distance * 0.1D));
				}
				else {
					this.operation = Operation.WAIT;
				}
			}
		}
	}

	public boolean doesPathCollide(double posX, double posY, double posZ, double distance) {
		double d0 = (posX - this.taskOwner.getX()) / distance;
		double d1 = (posY - this.taskOwner.getY()) / distance;
		double d2 = (posZ - this.taskOwner.getZ()) / distance;
		AABB collisionBox = this.taskOwner.getBoundingBox();

		for (int i = 1; i < distance; ++i) {
			collisionBox = collisionBox.move(d0, d1, d2);

			if (!taskOwner.level.noCollision(taskOwner))
				return true;
		}

		return false;
	}
}
