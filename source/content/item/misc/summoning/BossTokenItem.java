package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;


public interface BossTokenItem {
	Entity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack);

	@Nullable
	EntityType<? extends Entity> getEntityType(ItemStack stack);

	@FunctionalInterface
	interface SpawningFunction {
		void spawn(ServerLevel level, Vec3 position, ItemStack stack);
	}
}
