package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntityOxygenTablet extends EntitySoulTablet {
	public EntityOxygenTablet(World world) {
		this(world, null);
	}

	public EntityOxygenTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityPlayer pl : getTargetsWithinRadius(EntityPlayer.class, player -> player != null && player.isEntityAlive() && player.getAir() < 300)) {
			pl.setAir(300);
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.tabletOxygen;
	}
}
