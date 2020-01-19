package net.tslat.aoa3.capabilities.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBasePlayer;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;

public class AdventPlayerCapability implements CapabilityBasePlayer {
	private PlayerDataManager playerDataManager = null;

	public void init(EntityPlayer player) {
		if (playerDataManager == null)
			playerDataManager = new PlayerDataManager(player);
	}

	@Override
	@Nullable
	public PlayerDataManager getPlayerData() {
		return playerDataManager;
	}
}
