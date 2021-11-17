package net.tslat.aoa3.capabilities.adventplayer;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.player.PlayerDataManager;

public class AdventPlayerCapability implements AdventPlayerCapabilityHandles {
	private final PlayerDataManager playerDataManager;

	public AdventPlayerCapability() {
		this.playerDataManager = null;
	}

	public AdventPlayerCapability(ServerPlayerEntity pl) {
		this.playerDataManager = new PlayerDataManager(pl);
	}

	@Override
	public PlayerDataManager getPlayerData() {
		return playerDataManager;
	}

	@Override
	public CompoundNBT serializeNBT() {
		return playerDataManager.saveToNBT(false);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		playerDataManager.loadFromNBT(nbt);
	}
}
