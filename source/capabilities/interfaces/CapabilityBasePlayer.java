package net.tslat.aoa3.capabilities.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.utils.player.PlayerDataManager;

public interface CapabilityBasePlayer {
	public void init(EntityPlayer pl);

	public PlayerDataManager getPlayerData();
}
