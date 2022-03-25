package net.tslat.aoa3.content.entity.mob.runandor.templars;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;

public class BlueRunicLifeformEntity extends RunicLifeformEntity {
	public BlueRunicLifeformEntity(RuneTemplarEntity templar) {
		super(AoAEntities.Mobs.BLUE_RUNIC_LIFEFORM.get(), templar);
	}

	public BlueRunicLifeformEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}
}
