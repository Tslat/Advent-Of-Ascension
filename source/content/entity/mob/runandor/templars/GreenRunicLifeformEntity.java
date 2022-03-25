package net.tslat.aoa3.content.entity.mob.runandor.templars;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;

public class GreenRunicLifeformEntity extends RunicLifeformEntity {
	public GreenRunicLifeformEntity(RuneTemplarEntity templar) {
		super(AoAEntities.Mobs.GREEN_RUNIC_LIFEFORM.get(), templar);
	}

	public GreenRunicLifeformEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}
}
