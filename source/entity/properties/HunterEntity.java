package net.tslat.aoa3.entity.properties;


/**
 *  Implement this interface on your entity, and the mod will protect it from any internal damage and effects applicable to hunter mobs.<br>
 *      Does not protect from any third-party mods, but it is expected those mods implement their own protections with this interface in mind.<br>
 *      Additionally, the mod will attempt to render the hunter icon overhead for it as appropriate.
 */
public interface HunterEntity extends SpecialPropertyEntity {
	/**
	 * The Hunter level required to affect the mob while in survival.
	 *
	 * @return An int value representing the level required to affect the entity.
	 */
	public int getHunterReq();

	/**
	 * The xp a player should gain from killing this entity.
	 *
	 * @return A float value representing the amount of xp the player should gain upon a successful kill.
	 */
	public float getHunterXp();
}
