package net.tslat.aoa3.entity.projectiles.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.utils.PredicateUtil;

import java.util.List;

public class EntityHellBubbleShot extends BaseEnergyShot {
	public EntityHellBubbleShot(World world) {
		super(world);
	}

	public EntityHellBubbleShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter, weapon, maxAge);
	}

	public EntityHellBubbleShot(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		motionX *= 0.3;
		motionY *= 0.3;
		motionZ *= 0.3;

		if (getAge() >= 100)
			setDead();

		if (isDead) {
			world.playSound(null, posX, posY, posZ, SoundsRegister.BUBBLE_SHOT_POP, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
		else if (!world.isRemote && weapon != null) {
			List<EntityLivingBase> collidingEntities = world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox(), PredicateUtil.IS_HOSTILE_MOB);

			if (!collidingEntities.isEmpty()) {
				weapon.doEntityImpact(this, collidingEntities.get(0), thrower);
				setDead();
			}
		}
	}
}
