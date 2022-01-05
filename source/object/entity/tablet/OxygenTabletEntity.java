package net.tslat.aoa3.object.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.item.tablet.TabletItem;

public class OxygenTabletEntity extends SoulTabletEntity {
	public OxygenTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public OxygenTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (PlayerEntity pl : getTargetsWithinRadius(PlayerEntity.class, player -> player != null && player.isAlive() && player.getAirSupply() < 300)) {
			pl.setAirSupply(300);
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.OXYGEN_TABLET.get();
	}
}
