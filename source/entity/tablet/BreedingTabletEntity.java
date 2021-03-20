package net.tslat.aoa3.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tablet.TabletItem;

public class BreedingTabletEntity extends SoulTabletEntity {
	public BreedingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public BreedingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (AnimalEntity animal : getTargetsWithinRadius(AnimalEntity.class, animal -> animal != null && animal.isAlive() && !animal.isInLove() && animal.getAge() >= 0)) {
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
