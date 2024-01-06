package net.tslat.aoa3.content.world.spawner;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.CustomSpawner;

public interface AoACustomSpawner extends CustomSpawner {
	boolean shouldAddToDimension(ServerLevel level);
	AoACustomSpawner copy();
}
