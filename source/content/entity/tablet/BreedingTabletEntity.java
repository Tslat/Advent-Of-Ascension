package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;

public class BreedingTabletEntity extends SoulTabletEntity {
	public BreedingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public BreedingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (Animal animal : getTargetsWithinRadius(Animal.class, animal -> animal != null && animal.isAlive() && !animal.isInLove() && animal.getAge() >= 0)) {
			int growingAge = animal.getAge();

			if (growingAge == 0) {
				animal.setInLove(owner);
			}
			else if (growingAge > 10){
				animal.setAge(growingAge - 10);
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.BREEDING_TABLET.get();
	}
}
