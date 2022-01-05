package net.tslat.aoa3.object.entity.mob.gardencia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.object.entity.boss.VinocorneEntity;

public class PurpleFlowerEntity extends AoAMeleeMob {
	public PurpleFlowerEntity(VinocorneEntity vinocorne) {
		this(AoAEntities.Mobs.PURPLE_FLOWER.get(), vinocorne.level);

		moveTo(vinocorne.getX(), vinocorne.getY(), vinocorne.getZ(), random.nextFloat() * 360, 0);
	}

	public PurpleFlowerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.5f;
	}

}
