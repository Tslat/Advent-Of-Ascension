package net.tslat.aoa3.object.entity.mob.runandor.templars;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;

public class GreenRuneTemplarEntity extends RuneTemplarEntity {
	public GreenRuneTemplarEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected RunicLifeformEntity getLifeForm() {
		return new GreenRunicLifeformEntity(this);
	}

	@Override
	protected Item getActivationRune() {
		return AoAItems.POISON_RUNE.get();
	}
}
