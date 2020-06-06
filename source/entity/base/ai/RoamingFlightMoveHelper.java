package net.tslat.aoa3.entity.base.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class RoamingFlightMoveHelper extends EntityMoveHelper {
	private final EntityLiving taskOwner;
	private int courseChangeCooldown;

	public RoamingFlightMoveHelper(EntityLiving creature) {
		super(creature);
		this.taskOwner = creature;
	}

	@Override
	public void onUpdateMoveHelper() {
		if (this.action == Action.MOVE_TO) {
			double distanceX = this.posX - this.taskOwner.posX;
			double distanceY = this.posY - this.taskOwner.posY;
			double distanceZ = this.posZ - this.taskOwner.posZ;
			double distance = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

			if (this.courseChangeCooldown-- <= 0) {
				this.courseChangeCooldown += this.taskOwner.getRNG().nextInt(5) + 2;
				distance = MathHelper.sqrt(distance);

				if (!this.doesPathCollide(this.posX, this.posY, this.posZ, distance)) {
					this.taskOwner.motionX += distanceX / distance * 0.1D;
					this.taskOwner.motionY += distanceY / distance * 0.1D;
					this.taskOwner.motionZ += distanceZ / distance * 0.1D;
				}
				else {
					this.action = Action.WAIT;
				}
			}
		}
	}

	public boolean doesPathCollide(double posX, double posY, double posZ, double distance) {
		double d0 = (posX - this.taskOwner.posX) / distance;
		double d1 = (posY - this.taskOwner.posY) / distance;
		double d2 = (posZ - this.taskOwner.posZ) / distance;
		AxisAlignedBB collisionBox = this.taskOwner.getEntityBoundingBox();

		for (int i = 1; i < distance; ++i) {
			collisionBox = collisionBox.offset(d0, d1, d2);

			if (!this.taskOwner.world.getCollisionBoxes(this.taskOwner, collisionBox).isEmpty())
				return true;
		}

		return false;
	}
}
