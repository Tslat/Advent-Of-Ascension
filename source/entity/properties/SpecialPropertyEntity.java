package net.tslat.aoa3.entity.properties;

import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import java.util.TreeSet;

/**
 * Having an entity implement this class will cause the mod to look for special combat properties to render overhead.
 * The renderer wil look through a TreeSet of MobProperties for rendering purposes.
 *
 * NOTE: that implementation of this interface <u>does not</u> handle the effects themselves, only the rendering.
 */
public interface SpecialPropertyEntity {
	/**
	 * The combat icon overhead renderer calls this method looking for a set of MobProperties for rendering.
	 * This properties map may be edited on the fly for dynamic rendering.
	 *
	 * @return A Nonnull TreeSet<Enums.MobProperties> containing all applicable special combat statuses
	 */
	@Nonnull
	public TreeSet<Enums.MobProperties> getMobProperties();
}
