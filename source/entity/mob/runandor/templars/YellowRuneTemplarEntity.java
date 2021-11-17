package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;

public class YellowRuneTemplarEntity extends RuneTemplarEntity {
	public YellowRuneTemplarEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected RunicLifeformEntity getLifeForm() {
		return new YellowRunicLifeformEntity(this);
	}

	@Override
	protected Item getActivationRune() {
		return AoAItems.ENERGY_RUNE.get();
	}
}
