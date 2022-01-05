package net.tslat.aoa3.object.capability.adventplayer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public interface AdventPlayerCapabilityHandles extends INBTSerializable<CompoundNBT> {
	ServerPlayerDataManager getPlayerData();
}
