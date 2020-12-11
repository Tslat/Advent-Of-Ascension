package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.misc.RuneItem;

public class GreenRuneTemplarEntity extends RuneTemplarEntity {
	public GreenRuneTemplarEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected RunicLifeformEntity getLifeForm() {
		return new GreenRunicLifeformEntity(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return AoAItems.POISON_RUNE.get();
	}
}
