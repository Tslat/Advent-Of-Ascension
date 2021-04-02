package net.tslat.aoa3.integration.crafttweaker.util;

import com.blamejared.crafttweaker_annotations.annotations.TypedExpansion;
import net.minecraft.entity.player.PlayerEntity;
import org.openzen.zencode.java.ZenCodeType;

@TypedExpansion(PlayerEntity.class)
public class CTPlayer {
	@ZenCodeType.Method
	public static CTPlayerData getPlayerData(PlayerEntity pl) {
		return CTPlayerData.getForPlayer(pl);
	}
}
