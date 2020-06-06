package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class EntityEnergyTablet extends EntitySoulTablet {
	public EntityEnergyTablet(World world) {
		this(world, null);
	}

	public EntityEnergyTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityPlayer pl : getTargetsWithinRadius(EntityPlayer.class, player -> player != null && player.isEntityAlive())) {
			PlayerUtil.addResourceToPlayer(pl, Enums.Resources.ENERGY, 10f);
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.ENERGY_TABLET;
	}
}
