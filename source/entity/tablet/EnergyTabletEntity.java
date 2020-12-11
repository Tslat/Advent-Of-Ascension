package net.tslat.aoa3.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tablet.TabletItem;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerUtil;

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
			PlayerUtil.addResourceToPlayer(pl, Resources.ENERGY, 10f);
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.ENERGY_TABLET.get();
	}
}
