package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public interface BossTokenItem<T extends Entity> {
	T spawnBoss(ServerLevel level, Vec3 position);

	@Nullable
	EntityType<T> getEntityType(ItemStack stack);
}
