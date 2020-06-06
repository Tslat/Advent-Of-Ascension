package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntityBreedingTablet extends EntitySoulTablet {
	public EntityBreedingTablet(World world) {
		this(world, null);
	}

	public EntityBreedingTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityAnimal animal : getTargetsWithinRadius(EntityAnimal.class, animal -> animal != null && animal.isEntityAlive() && !animal.isInLove() && animal.getGrowingAge() >= 0)) {
			int growingAge = animal.getGrowingAge();

			if (growingAge == 0) {
				animal.setInLove(owner);
			}
			else if (growingAge > 10){
				animal.setGrowingAge(growingAge - 10);
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.BREEDING_TABLET;
	}
}
