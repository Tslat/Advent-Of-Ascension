package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nullable;

/**
 * Fired when an entity is being teleported for any reason other than commands, ender pearls, or chorus fruit. <br>
 * Entities teleporting of their own power and volition are covered under {@link EntityTeleportEvent.EnderEntity}. <br>
 * This event is {@link Cancelable}. <br>
 * If canceled, the entity will not teleport.
 */
@Cancelable
public class MagicTeleportEvent extends EntityTeleportEvent {
	@Nullable
	protected Entity teleportSource;
	@Nullable
	protected Entity indirectTeleportSource;

	public MagicTeleportEvent(Entity entity, @Nullable Entity teleportSource, @Nullable Entity indirectTeleportSource, Vec3 teleportPos) {
		super(entity, teleportPos.x, teleportPos.y, teleportPos.z);
	}

	/**
	 * The source entity of the teleport, if applicable. <br>
	 * In the event of a projectile-based teleport, this will be the projectile entity. <br>
	 * Teleporting without the assistance of another entity (E.G. a staff ability) will leave this value as null.
	 * @return The entity that teleported the target, if applicable
	 */
	@Nullable
	public Entity getTeleporter() {
		return this.teleportSource;
	}

	/**
	 * The indirect source entity of the teleport, if applicable. <br>
	 * In the event of a projectile-based teleport, this will be the thrower of the projectile. <br>
	 * Teleporting without the assistance of another entity (E.G. a staff ability) will leave this value as null.
	 * @return The indirect entity that teleported the target, if applicable
	 */
	@Nullable
	public Entity getIndirectTeleportSource() {
		return this.indirectTeleportSource;
	}
}
