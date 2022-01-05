package net.tslat.aoa3.object.entity.mob.misc;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.object.entity.boss.BaneEntity;

public class BaneCloneEntity extends AoAMeleeMob {
	public BaneCloneEntity(BaneEntity bane) {
		this(AoAEntities.Misc.BANE_CLONE.get(), bane.level);

		moveTo(bane.getX(), bane.getY(), bane.getZ(), random.nextFloat() * 360, 0);
	}

	public BaneCloneEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.1875f;
	}

}
