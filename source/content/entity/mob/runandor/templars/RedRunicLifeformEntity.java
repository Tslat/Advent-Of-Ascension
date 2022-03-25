package net.tslat.aoa3.content.entity.mob.runandor.templars;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;

public class RedRunicLifeformEntity extends RunicLifeformEntity {
	public RedRunicLifeformEntity(RuneTemplarEntity templar) {
		super(AoAEntities.Mobs.RED_RUNIC_LIFEFORM.get(), templar);
	}

	public RedRunicLifeformEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}
}
