package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;

public class OxygenTabletEntity extends SoulTabletEntity {
	public OxygenTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public OxygenTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (Player pl : getTargetsWithinRadius(Player.class, player -> player != null && player.isAlive() && player.getAirSupply() < 300)) {
			pl.setAirSupply(300);
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.OXYGEN_TABLET.get();
	}
}
