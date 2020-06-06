package net.tslat.aoa3.hooks.crafttweaker.util;

import crafttweaker.api.player.IPlayer;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenGetter;

@ZenExpansion("crafttweaker.player.IPlayer")
public class CTPlayer {
	@ZenGetter("aoa3")
	public static CTPlayerData getPlayerData(IPlayer pl) {
		return CTPlayerData.getForPlayer(pl);
	}
}
