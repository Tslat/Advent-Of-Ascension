package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntityProficiencyTablet extends EntitySoulTablet {
	public EntityProficiencyTablet(World world) {
		this(world, null);
	}

	public EntityProficiencyTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.tabletProficiency;
	}
}
