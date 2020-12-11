package net.tslat.aoa3.entity.ai.movehelper;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class RoamingFlightMoveHelper extends MovementController {
	private final MobEntity taskOwner;
	private int courseChangeCooldown;

	public RoamingFlightMoveHelper(MobEntity creature) {
		super(creature);
		this.taskOwner = creature;
	}

	@Override
	public void tick() {
		if (this.action == Action.MOVE_TO) {
			double distanceX = this.posX - this.taskOwner.getPosX();
			double distanceY = this.posY - this.taskOwner.getPosY();
			double distanceZ = this.posZ - this.taskOwner.getPosZ();
			double distance = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

			if (this.courseChangeCooldown-- <= 0) {
				this.courseChangeCooldown += this.taskOwner.getRNG().nextInt(5) + 2;
				distance = MathHelper.sqrt(distance);

				if (!this.doesPathCollide(this.posX, this.posY, this.posZ, distance)) {
					taskOwner.setMotion(taskOwner.getMotion().add(distanceX / distance * 0.1D, distanceY / distance * 0.1D, distanceZ / distance * 0.1D));
				}
				else {
					this.action = Action.WAIT;
				}
			}
		}
	}

	public boolean doesPathCollide(double posX, double posY, double posZ, double distance) {
		double d0 = (posX - this.taskOwner.getPosX()) / distance;
		double d1 = (posY - this.taskOwner.getPosY()) / distance;
		double d2 = (posZ - this.taskOwner.getPosZ()) / distance;
		AxisAlignedBB collisionBox = this.taskOwner.getBoundingBox();

		for (int i = 1; i < distance; ++i) {
			collisionBox = collisionBox.offset(d0, d1, d2);

			if (!taskOwner.world.hasNoCollisions(taskOwner))
				return true;
		}

		return false;
	}
}
