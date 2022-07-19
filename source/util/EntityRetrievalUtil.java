package net.tslat.aoa3.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public final class EntityRetrievalUtil {
	@Nullable
	public static <T extends Entity> T getNearest(Vec3 origin, List<T> entities) {
		if (entities.isEmpty())
			return null;

		double dist = Double.MAX_VALUE;
		T closest = null;

		for (T entity : entities) {
			double entityDist = entity.distanceToSqr(origin);

			if (entityDist < dist) {
				dist = entityDist;
				closest = entity;
			}
		}

		return closest;
	}

	@Nullable
	public static Entity getNearestEntity(Entity origin, double radius, Predicate<Entity> predicate) {
		return getNearestEntity(origin, radius, radius, radius, predicate);
	}

	@Nullable
	public static Entity getNearestEntity(Entity origin, double radiusX, double radiusY, double radiusZ, Predicate<Entity> predicate) {
		return getNearestEntity(origin.level, new AABB(origin.getX() - radiusX, origin.getY() - radiusY, origin.getZ() - radiusZ, origin.getX() + radiusX, origin.getY() + radiusY, origin.getZ() + radiusZ), origin.position(), predicate);
	}

	@Nullable
	public static Entity getNearestEntity(Level level, AABB area, Vec3 origin, Predicate<Entity> predicate) {
		final MutableDouble dist = new MutableDouble(Double.MAX_VALUE);
		final MutableObject<Entity> closest = new MutableObject<>(null);

		level.getEntities().get(area, entity -> {
			if (predicate.test(entity)) {
				double entityDist = entity.distanceToSqr(origin);

				if (entityDist < dist.getValue()) {
					dist.setValue(entityDist);
					closest.setValue(entity);
				}
			}
		});

		return closest.getValue();
	}

	@Nullable
	public static Player getNearestPlayer(Entity origin, double radius, Predicate<Player> predicate) {
		return getNearestPlayer(origin, radius, radius, radius, predicate);
	}

	@Nullable
	public static Player getNearestPlayer(Entity origin, double radiusX, double radiusY, double radiusZ, Predicate<Player> predicate) {
		return getNearestPlayer(origin.level, new AABB(origin.getX() - radiusX, origin.getY() - radiusY, origin.getZ() - radiusZ, origin.getX() + radiusX, origin.getY() + radiusY, origin.getZ() + radiusZ), origin.position(), predicate);
	}

	@Nullable
	public static Player getNearestPlayer(Level level, AABB area, Vec3 origin, Predicate<Player> predicate) {
		double dist = Double.MAX_VALUE;
		Player closest = null;

		for (Player player : level.players()) {
			if (area.contains(player.position()) && predicate.test(player)) {
				double playerDist = player.distanceToSqr(origin);

				if (playerDist < dist) {
					dist = playerDist;
					closest = player;
				}
			}
		}

		return closest;
	}

	public static List<Player> getPlayers(Level level, AABB area) {
		return getPlayers(level, area, pl -> true);
	}

	public static List<Player> getPlayers(Entity origin, double radius, Predicate<Player> predicate) {
		return getPlayers(origin, radius, radius, radius, predicate);
	}

	public static List<Player> getPlayers(Entity origin, double radiusX, double radiusY, double radiusZ, Predicate<Player> predicate) {
		return getPlayers(origin.level, new AABB(origin.getX() - radiusX, origin.getY() - radiusY, origin.getZ() - radiusZ, origin.getX() + radiusX, origin.getY() + radiusY, origin.getZ() + radiusZ), predicate);
	}

	public static List<Player> getPlayers(Level level, AABB area, Predicate<Player> predicate) {
		List<Player> players = new ObjectArrayList<>();

		for (Player player : level.players()) {
			if (area.contains(player.position()) && predicate.test(player))
				players.add(player);
		}

		return players;
	}

	public static List<Entity> getEntities(Entity origin, double radius, Predicate<Entity> predicate) {
		return getEntities(origin, radius, radius, radius, predicate);
	}

	public static List<Entity> getEntities(Entity origin, double radiusX, double radiusY, double radiusZ, Predicate<Entity> predicate) {
		return getEntities(origin.level, new AABB(origin.getX() - radiusX, origin.getY() - radiusY, origin.getZ() - radiusZ, origin.getX() + radiusX, origin.getY() + radiusY, origin.getZ() + radiusZ), predicate.and(entity -> entity != origin));
	}

	public static List<Entity> getEntities(Level level, AABB area, Predicate<? extends Entity> predicate) {
		Predicate<Entity> typeSafePredicate = (Predicate<Entity>)predicate;
		List<Entity> entities = new ObjectArrayList<>();

		level.getEntities().get(area, entity -> {
			if (typeSafePredicate.test(entity))
				entities.add(entity);
		});

		for (PartEntity<?> part : level.getPartEntities()) {
			if (part.getBoundingBox().intersects(area) && typeSafePredicate.test(part))
				entities.add(part);
		}

		return entities;
	}
}
