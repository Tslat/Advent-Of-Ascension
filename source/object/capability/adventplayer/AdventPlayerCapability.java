package net.tslat.aoa3.object.capability.adventplayer;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class AdventPlayerCapability implements AdventPlayerCapabilityHandles {
	private final ServerPlayerDataManager playerDataManager;

	public AdventPlayerCapability() {
		this.playerDataManager = null;
	}

	public AdventPlayerCapability(ServerPlayerEntity pl) {
		this.playerDataManager = new ServerPlayerDataManager(pl);
	}

	@Override
	public ServerPlayerDataManager getPlayerData() {
		return playerDataManager;
	}

	@Override
	public CompoundNBT serializeNBT() {
		return playerDataManager.savetoNbt(false);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		playerDataManager.loadFromNbt(nbt);
	}
}
