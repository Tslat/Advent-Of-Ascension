package net.tslat.aoa3.event.custom.events;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

/**
 * PlayerChangeXpEvent is fired when a player gains or loses skill xp from any method.<br>
 * XP changes also apply to xp applied from commands or third-party methods.<br>
 * <br>
 * This event is fired via on the {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS} event bus.<br>
 * <br>
 * This event is {@link Cancelable}
 */
@Cancelable
public class PlayerChangeXpEvent extends PlayerEvent {
	private final float rawXpGain;
	private final float xpGain;
	private float newXpGain;
	private final AoASkill.Instance skill;
	private final boolean isNaturalGain;

	public PlayerChangeXpEvent(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, float rawXpGain, float xpAfterModifiers, boolean isNaturalGainMethod) {
		super(playerDataManager.player());

		this.rawXpGain = rawXpGain;
		this.xpGain = xpAfterModifiers;
		this.newXpGain = xpGain;
		this.skill = skill;
		this.isNaturalGain = isNaturalGainMethod;
	}

	/**
	 * Returns the base amount of xp given before any ingame xp modifiers or event listener changes.
	 *
	 * @return the float amount of xp originally given to trigger this event, prior to xp buffs.
	 */
	public float getRawXpGain() {
		return this.rawXpGain;
	}

	/**
	 * Returns the original amount of xp to be gained prior to event handling, but after xp buffs have been applied.
	 *
	 * @return the float amount of xp originally given to trigger this event, with xp buffs applied.
	 */
	public float getOriginalXpGain() {
		return this.xpGain;
	}

	/**
	 * Returns the new amount of xp to be given after this event is completed, with xp modifiers and event handler adjustments applied.
	 *
	 * @return the amount of xp to give to the player after all current modifications taken into account.
	 */
	public float getNewXpGain() {
		return this.newXpGain;
	}

	/**
	 * Returns the {@link AoASkill.Instance} this event is for
	 *
	 * @return the skill the player is changing xp in
	 */
	public AoASkill.Instance getSkill() {
		return skill;
	}

	/**
	 * Set a new amount of xp for the player to change in the relevant skill after all modifications are taken into account.
	 *
	 * @param newXpAmount the amount of xp the player should gain after this event is complete. Negative amounts subtract from the player's current xp
	 */
	public void setXpGain(float newXpAmount) {
		this.newXpGain = newXpAmount;
	}

	/**
	 * Returns true if the source of the xp change is a legitimate source and shouldn't mark the player as a cheater.
	 *
	 * @return a boolean representing the legitimacy of the xp change
	 */
	public boolean isNaturallyGainedXp() {
		return isNaturalGain;
	}
}
