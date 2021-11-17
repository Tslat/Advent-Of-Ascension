package net.tslat.aoa3.event.custom;

import net.minecraftforge.common.MinecraftForge;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.player.PlayerDataManager;

public final class AoAEvents {
	public static void playerLevelChange(PlayerDataManager playerDataManager, AoASkill.Instance skill, int oldLevel, boolean wasNaturallyChanged) {
		MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent(playerDataManager, skill, oldLevel, wasNaturallyChanged));
	}

	public static float playerChangeXp(PlayerDataManager playerDataManager, AoASkill.Instance skill, float xpGained, float xpAfterModifiers, boolean wasNaturallyGained) {
		PlayerChangeXpEvent event = new PlayerChangeXpEvent(playerDataManager, skill, xpGained, xpAfterModifiers, wasNaturallyGained);

		boolean cancelled = MinecraftForge.EVENT_BUS.post(event);

		return cancelled ? 0 : event.getNewXpGain();
	}
}
