package net.tslat.aoa3.content.entity.misc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;

public class GoldFishingBobberEntity extends HaulingFishingBobberEntity {
	public GoldFishingBobberEntity(Player player, Level world, ItemStack rod) {
		super(player, world, rod);
	}

	public GoldFishingBobberEntity(Player player, Level world, ItemStack rod, float luck, float lure) {
		super(player, world, rod, luck, lure);
	}

	public GoldFishingBobberEntity(EntityType<? extends GoldFishingBobberEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public EntityType<?> getType() {
		return AoAMiscEntities.GOLD_BOBBER.get();
	}
}
