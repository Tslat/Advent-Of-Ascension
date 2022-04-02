package net.tslat.aoa3.content.capability.adventplayer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class AdventPlayerCapability implements AdventPlayerCapabilityHandles {
	private final ServerPlayerDataManager playerDataManager;

	public AdventPlayerCapability() {
		this.playerDataManager = null;
	}

	public AdventPlayerCapability(ServerPlayer pl) {
		this.playerDataManager = new ServerPlayerDataManager(pl);
	}

	@Override
	public ServerPlayerDataManager getPlayerData() {
		return playerDataManager;
	}

	@Override
	public CompoundTag serializeNBT() {
		return playerDataManager.savetoNbt(false);
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		playerDataManager.loadFromNbt(nbt);
	}
}
