package net.tslat.aoa3.dimension;

import net.minecraft.world.WorldServer;

public interface AoAWorldProvider {
	public AoATeleporter getTeleporter(WorldServer fromWorld);
}
