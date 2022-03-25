package net.tslat.aoa3.content.entity.mob.misc;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.boss.BaneEntity;
import net.tslat.aoa3.util.WorldUtil;

public class BigBaneCloneEntity extends AoAMeleeMob {
	public BigBaneCloneEntity(BaneEntity bane) {
		this(AoAEntities.Misc.BIG_BANE_CLONE.get(), bane.level);

		moveTo(bane.getX(), bane.getY(), bane.getZ(), random.nextFloat() * 360, 0);
	}

	public BigBaneCloneEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.3125f;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			WorldUtil.createExplosion(this, level, 6f);
			remove();
		}
	}
}
