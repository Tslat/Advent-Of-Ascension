package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;

public class YellowRunicLifeformEntity extends RunicLifeformEntity {
	public YellowRunicLifeformEntity(RuneTemplarEntity templar) {
		super(AoAEntities.Mobs.YELLOW_RUNIC_LIFEFORM.get(), templar);
	}

	public YellowRunicLifeformEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}
}
