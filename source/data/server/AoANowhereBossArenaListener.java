package net.tslat.aoa3.data.server;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.item.misc.summoning.BossTokenItem;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.ObjectUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AoANowhereBossArenaListener extends SimpleJsonResourceReloadListener {
	private static final List<NowhereBossArena> REGISTERED_ARENAS = new ObjectArrayList<>();
	private static final String folder = "worldgen/nowhere_boss_arenas";

	public AoANowhereBossArenaListener() {
		super(new GsonBuilder().setPrettyPrinting().create(), folder);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> entryMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		REGISTERED_ARENAS.clear();

		for (Map.Entry<ResourceLocation, JsonElement> entry : entryMap.entrySet()) {
			Either<NowhereBossArena, DataResult.PartialResult<NowhereBossArena>> result = NowhereBossArena.CODEC.parse(JsonOps.INSTANCE, entry.getValue()).get();

			if (result.right().isPresent()) {
				Logging.logMessage(Level.ERROR, "Unable to deserialize boss arena, disabling. (" + entry.getKey() + ") " + result.right().get().message());

				continue;
			}

			NowhereBossArena nowhereBossArena = result.left().get();

			if (nowhereBossArena.playerSpawnPoints.isEmpty()) {
				Logging.logMessage(Level.ERROR, "No player spawn points provided for boss arena, disabling arena. " + entry.getKey());

				continue;
			}

			if (nowhereBossArena.bossSpawnPoints.isEmpty()) {
				Logging.logMessage(Level.ERROR, "No boss spawn points provided for boss arena, disabling arena. " + entry.getKey());

				continue;
			}

			REGISTERED_ARENAS.add(nowhereBossArena);
		}
	}

	@Nullable
	public static NowhereBossArena getFreeArena(ServerLevel level) {
		ObjectUtil.fastShuffleList(REGISTERED_ARENAS);

		for (NowhereBossArena arena : REGISTERED_ARENAS) {
			if (arena.checkAndClear(level))
				return arena;
		}

		return null;
	}

	@Nullable
	public static NowhereBossArena getClosestArena(ServerLevel level, Vec3 pos) {
		if (!NowhereEvents.isInBossRegion(BlockPos.containing(pos)))
			return null;

		NowhereBossArena closest = null;
		double dist = Double.MAX_VALUE;

		for (NowhereBossArena arena : REGISTERED_ARENAS) {
			double testDist = arena.getStructureBounds(level).getCenter().distanceToSqr(pos);

			if (testDist < 50 * 50)
				return arena;

			if (testDist < dist) {
				dist = testDist;
				closest = arena;
			}
		}

		return closest;
	}

	public static class NowhereBossArena {
		public static final Codec<NowhereBossArena> CODEC = RecordCodecBuilder.create(builder -> builder.group(
				ResourceLocation.CODEC.fieldOf("structure_id").forGetter(arena -> arena.structureId),
				BlockPos.CODEC.fieldOf("arena_pos").forGetter(arena -> arena.structurePos),
				Vec3.CODEC.listOf().fieldOf("player_spawns").forGetter(arena -> arena.playerSpawnPoints),
				Vec3.CODEC.listOf().fieldOf("boss_spawns").forGetter(arena -> arena.bossSpawnPoints)
		).apply(builder, NowhereBossArena::new));

		public final ResourceLocation structureId;
		private final BlockPos structurePos;
		private final List<Vec3> playerSpawnPoints;
		private final List<Vec3> bossSpawnPoints;

		private Structure structure = null;
		private StructureStart structureStart = null;
		private AABB structureBounds = null;

		public NowhereBossArena(ResourceLocation structureId, BlockPos structurePos, List<Vec3> playerSpawnPoints, List<Vec3> bossSpawnPoints) {
			this.structureId = structureId;
			this.structurePos = structurePos;
			this.playerSpawnPoints = playerSpawnPoints;
			this.bossSpawnPoints = bossSpawnPoints;
		}

		public boolean checkAndClear(ServerLevel level) {
			List<Player> players = getPlayersInside(level);
			List<Entity> entities = getEntitiesInside(level);

			if (!players.isEmpty()) {
				boolean keepArena = false;

				for (Entity entity : entities) {
					if (entity instanceof ItemEntity || entity.getType().is(Tags.EntityTypes.BOSSES)) {
						keepArena = true;

						break;
					}
				}

				if (keepArena)
					return false;

				for (Player player : players) {
					AoAScheduler.scheduleSyncronisedTask(() -> {
						ServerPlayer pl = (ServerPlayer)player;
						PlayerUtil.resetToDefaultStatus(pl);
						pl.connection.teleport(17.5d, 452.5d, 3.5d, 0, pl.getXRot());
						ItemUtil.clearInventoryOfItems(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
						PlayerUtil.getAdventPlayer(pl).returnItemStorage();
					}, 1);
				}
			}

			for (Entity entity : entities) {
				entity.discard();
			}

			return true;
		}

		public void placePlayersAndBoss(ServerLevel level, List<Player> players, Predicate<Player> playerStillValid, ItemStack stack, EntityType<?> entityType, BossTokenItem.SpawningFunction bossFunction) {
			if (players.isEmpty())
				return;

			StructureStart structureStart = getStructureStart(level);

			if (structureStart == null)
				return;

			AoAScheduler.scheduleSyncronisedTask(() -> {
				boolean spawnBoss = false;
				Entity entity = entityType.create(level);
				SoundBuilder soundBuilder = entity instanceof AoABoss boss && boss.getMusic() != null ? new SoundBuilder(boss.getMusic()).isMusic() : null;

				if (entity != null)
					entity.discard();

				for (Player player : players) {
					if (!(player instanceof ServerPlayer serverPlayer))
						continue;

					if (playerStillValid.test(serverPlayer)) {
						Vec3 pos = RandomUtil.getRandomSelection(this.playerSpawnPoints);
						spawnBoss = true;

						serverPlayer.connection.teleport(pos.x, pos.y, pos.z, 0, 0);
						ItemUtil.givePlayerItemOrDrop(serverPlayer, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
						serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.bossWarning", ChatFormatting.GREEN));
					}
					else {
						serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.tooFar", ChatFormatting.RED));
					}

					if (soundBuilder != null)
						soundBuilder.include(player);
				}

				if (spawnBoss) {
					AoAScheduler.scheduleSyncronisedTask(() -> {
						if (soundBuilder != null)
							soundBuilder.execute();
					}, 40);

					AoAScheduler.scheduleSyncronisedTask(() -> {
						if (!getPlayersInside(level).isEmpty())
							bossFunction.spawn(level, getRandomBossSpawn(), stack);
					}, 140);
				}
			}, 100);

			for (Player player : players) {
				player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.teleportWarning", ChatFormatting.GOLD));
			}
		}

		@Nullable
		private Structure getStructure(ServerLevel level) {
			if (this.structure != null)
				return this.structure;

			this.structure = level.registryAccess().registry(Registries.STRUCTURE).get().get(structureId);

			return this.structure;
		}

		@Nullable
		private StructureStart getStructureStart(ServerLevel level) {
			if (this.structureStart != null)
				return this.structureStart;

			Structure structure = getStructure(level);

			if (structure == null)
				return null;

			this.structureStart = level.structureManager().getStructureAt(structurePos, structure);

			return this.structureStart;
		}

		@Nullable
		public AABB getStructureBounds(ServerLevel level) {
			if (this.structureBounds != null)
				return this.structureBounds;

			StructureStart structureStart = getStructureStart(level);

			if (structureStart == null)
				return null;

			this.structureBounds = AABB.of(structureStart.getBoundingBox());

			return this.structureBounds;
		}

		public List<Player> getPlayersInside(ServerLevel level) {
			AABB bounds = getStructureBounds(level);

			if (bounds == null)
				return List.of();

			return EntityRetrievalUtil.getPlayers(level, bounds);
		}

		public List<Entity> getEntitiesInside(ServerLevel level) {
			AABB bounds = getStructureBounds(level);

			if (bounds == null)
				return List.of();

			return EntityRetrievalUtil.getEntities(level, bounds, entity -> !(entity instanceof Player));
		}

		public Vec3 getRandomBossSpawn() {
			return RandomUtil.getRandomSelection(this.bossSpawnPoints);
		}
	}
}
