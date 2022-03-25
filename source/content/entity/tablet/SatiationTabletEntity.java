package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;

public class SatiationTabletEntity extends SoulTabletEntity {
	public SatiationTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public SatiationTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (PlayerEntity pl : getTargetsWithinRadius(PlayerEntity.class, player -> player != null && player.isAlive() && (player.getFoodData().needsFood() || player.getFoodData().getSaturationLevel() <= 0))) {
			pl.getFoodData().eat(1, 2f);
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.SATIATION_TABLET.get();
	}
}
