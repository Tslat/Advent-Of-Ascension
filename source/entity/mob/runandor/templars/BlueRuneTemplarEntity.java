package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;

public class BlueRuneTemplarEntity extends RuneTemplarEntity {
	public BlueRuneTemplarEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected RunicLifeformEntity getLifeForm() {
		return new BlueRunicLifeformEntity(this);
	}

	@Override
	protected Item getActivationRune() {
		return AoAItems.WATER_RUNE.get();
	}
}
