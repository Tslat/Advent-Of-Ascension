package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.misc.RuneItem;

public class BlueRuneTemplarEntity extends RuneTemplarEntity {
	public BlueRuneTemplarEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected RunicLifeformEntity getLifeForm() {
		return new BlueRunicLifeformEntity(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return AoAItems.WATER_RUNE.get();
	}
}
