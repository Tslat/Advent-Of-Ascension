package net.tslat.aoa3.object.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.object.item.tablet.TabletItem;
import net.tslat.aoa3.util.PlayerUtil;

public class EnergyTabletEntity extends SoulTabletEntity {
	public EnergyTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public EnergyTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (ServerPlayerEntity pl : getTargetsWithinRadius(ServerPlayerEntity.class, player -> player != null && player.isAlive())) {
			PlayerUtil.addResourceToPlayer(pl, AoAResources.SPIRIT.get(), 10f); // TODO
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.ENERGY_TABLET.get();
	}
}
