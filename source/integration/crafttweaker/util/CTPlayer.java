package net.tslat.aoa3.integration.crafttweaker.util;

import com.blamejared.crafttweaker_annotations.annotations.TypedExpansion;
import net.minecraft.world.entity.player.Player;
import org.openzen.zencode.java.ZenCodeType;

@TypedExpansion(Player.class)
public class CTPlayer {
	@ZenCodeType.Method
	public static CTPlayerData getPlayerData(Player pl) {
		return CTPlayerData.getForPlayer(pl);
	}
}
