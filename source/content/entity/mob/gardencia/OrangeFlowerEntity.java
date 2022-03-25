package net.tslat.aoa3.content.entity.mob.gardencia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.boss.VinocorneEntity;

public class OrangeFlowerEntity extends AoAMeleeMob {
	public OrangeFlowerEntity(VinocorneEntity vinocorne) {
		this(AoAEntities.Mobs.ORANGE_FLOWER.get(), vinocorne.level);

		moveTo(vinocorne.getX(), vinocorne.getY(), vinocorne.getZ(), random.nextFloat() * 360, 0);
	}

	public OrangeFlowerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.5f;
	}

}
