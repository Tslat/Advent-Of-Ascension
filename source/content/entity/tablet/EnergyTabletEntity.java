package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;
import net.tslat.aoa3.util.PlayerUtil;

public class EnergyTabletEntity extends SoulTabletEntity {
	public EnergyTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public EnergyTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (ServerPlayer pl : getTargetsWithinRadius(ServerPlayer.class, player -> player != null && player.isAlive())) {
			PlayerUtil.addResourceToPlayer(pl, AoAResources.SPIRIT.get(), 10f); // TODO
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.ENERGY_TABLET.get();
	}
}
