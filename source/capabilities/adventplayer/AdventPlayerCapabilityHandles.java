package net.tslat.aoa3.capabilities.adventplayer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.tslat.aoa3.util.player.PlayerDataManager;

public interface AdventPlayerCapabilityHandles extends INBTSerializable<CompoundNBT> {
	PlayerDataManager getPlayerData();
}
