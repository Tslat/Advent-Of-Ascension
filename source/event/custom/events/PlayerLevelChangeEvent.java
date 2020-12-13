package net.tslat.aoa3.event.custom.events;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;

/**
 * PlayerLevelChangeEvent is fired when a player levels up from any method.<br>
 * Level changes also applies to commands and other third party methods.<br>
 * This event is only fired on the logical server side.<br>
 * <br>
 * This event is fired via on the {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS} event bus.<br>
 * <br>
 * {@link #getOldLevel()} will return the player's original level prior to levelling up.<br>
 * {@link #getNewLevel()} will return the player's new level after levelling up.<br>
 * {@link #getSkill()} will return the relevant {@link Skills} skill that the level up is for.<br>
 * {@link #wasNaturalLevelChange()} will return true if the level change is from naturally levelling up, or false if changed via a command or third-party.<br>
 * <br>
 * This event is not {@link net.minecraftforge.eventbus.api.Cancelable}
 */

public class PlayerLevelChangeEvent extends PlayerEvent {
	private final int oldLevel;
	private final int newLevel;
	private final Skills skill;
	private final boolean wasNaturallyChanged;

	public PlayerLevelChangeEvent(PlayerDataManager playerDataManager, int oldLevel, int newLevel, Skills skill, boolean wasNaturallyChanged) {
		super(playerDataManager.player());

		this.oldLevel = oldLevel;
		this.newLevel = newLevel;
		this.skill = skill;
		this.wasNaturallyChanged = wasNaturallyChanged;
	}

	public int getOldLevel() {
		return oldLevel;
	}

	public int getNewLevel() {
		return newLevel;
	}

	public Skills getSkill() {
		return skill;
	}

	public boolean wasNaturalLevelChange() {
		return wasNaturallyChanged;
	}
}
