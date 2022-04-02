package net.tslat.aoa3.content.capability.adventplayer;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public interface AdventPlayerCapabilityHandles extends INBTSerializable<CompoundTag> {
	ServerPlayerDataManager getPlayerData();
}
