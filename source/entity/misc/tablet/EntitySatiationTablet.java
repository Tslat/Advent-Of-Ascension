package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntitySatiationTablet extends EntitySoulTablet {
	public EntitySatiationTablet(World world) {
		this(world, null);
	}

	public EntitySatiationTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityPlayer pl : getTargetsWithinRadius(EntityPlayer.class, player -> player != null && player.isEntityAlive() && (player.getFoodStats().needFood() || player.getFoodStats().getSaturationLevel() <= 0))) {
			pl.getFoodStats().addStats(1, 2f);
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.tabletSatiation;
	}
}
