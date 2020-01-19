package net.tslat.aoa3.event.custom;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;

/**
 * PlayerGainXpEvent is fired when a player gains xp from any method.<br>
 * XP Gain also applies to xp applied from commands or third-party methods.<br>
 * <br>
 * This event is fired via on the {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS} event bus.<br>
 * <br>
 * {@link #getSkill()} will return the relevant {@link Enums.Skills} skill the xp is to be gained for.<br>
 * {@link #getXpGained()} will return the amount of xp to be gained.<br>
 * {@link #isNaturallyGainedXp()} will return true if the xp is gained legitimately, or false for commands or other non-natural methods.<br>
 * <br>
 * This event is {@link Cancelable}
 */
@Cancelable
public class PlayerGainXpEvent extends PlayerEvent {
	private float xpGained;
	private final Enums.Skills skill;
	private final boolean isNaturalGain;

	public PlayerGainXpEvent(PlayerDataManager playerDataManager, float xp, Enums.Skills skill, boolean isNaturalGainMethod) {
		super(playerDataManager.player());

		this.xpGained = xp;
		this.skill = skill;
		this.isNaturalGain = isNaturalGainMethod;
	}

	public float getXpGained() {
		return xpGained;
	}

	public Enums.Skills getSkill() {
		return skill;
	}

	public void setXpGained(float newXpAmount) {
		this.xpGained = newXpAmount;
	}

	public boolean isNaturallyGainedXp() {
		return isNaturalGain;
	}
}
