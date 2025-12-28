package net.tslat.aoa3.content.world.nowhere;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AoAResourceCaching;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.item.misc.summoning.BossTokenItem;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.ObjectUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class NowhereBossArena {
	public static final Codec<NowhereBossArena> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			ResourceLocation.CODEC.fieldOf("structure_id").forGetter(arena -> arena.structureId),
			BlockPos.CODEC.fieldOf("arena_pos").forGetter(arena -> arena.structurePos),
			Vec3.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("player_spawns").forGetter(arena -> arena.playerSpawnPoints),
			Vec3.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("boss_spawns").forGetter(arena -> arena.bossSpawnPoints)
	).apply(builder, NowhereBossArena::new));

	private static NowhereBossArena[] ARENAS = new NowhereBossArena[0];

	static {
		AoAResourceCaching.onDataReload(registryAccess -> ARENAS = registryAccess.registryOrThrow(AoARegistries.NOWHERE_BOSS_ARENAS_REGISTRY_KEY).stream().toArray(NowhereBossArena[]::new));
		AoAResourceCaching.onClientLogout(() -> ARENAS = null);
	}

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

	@Nullable
	public static NowhereBossArena getFreeArena(ServerLevel level) {
		ObjectUtil.shuffleArray(ARENAS, level.getRandom());

		for (NowhereBossArena arena : ARENAS) {
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

		for (NowhereBossArena arena : ARENAS) {
			AABB bounds = arena.getStructureBounds(level);

			if (bounds == null)
				continue;

			double testDist = bounds.getCenter().distanceToSqr(pos);

			if (testDist < 50 * 50)
				return arena;

			if (testDist < dist) {
				dist = testDist;
				closest = arena;
			}
		}

		return closest;
	}

	public boolean checkAndClear(ServerLevel level) {
		AABB bounds = getStructureBounds(level);

		if (bounds == null)
			return false;

		if (!level.isLoaded(BlockPos.containing(bounds.getCenter())))
			return true;

		List<Player> players = getPlayersInside(level, bounds);
		List<Entity> entities = getEntitiesInside(level, bounds);

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
				AoAScheduler.schedule(1, tick -> {
					ServerPlayer pl = (ServerPlayer)player;
					PlayerUtil.resetToDefaultStatus(pl);
					pl.teleportTo(pl.serverLevel(), 17.5d, 452.5d, 3.5d, Set.of(), 0, pl.getXRot());
					InventoryUtil.clearItems(pl, AoAItems.RETURN_CRYSTAL);
					PlayerUtil.getAdventPlayer(pl).storage.returnStoredItems();
				});
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

		AoAScheduler.schedule(100, tick -> {
			boolean spawnBoss = false;
			Entity entity = entityType.create(level);
			SoundBuilder soundBuilder = entity instanceof AoABoss boss && boss.getMusic() != null ? SoundBuilder.asMusic(boss.getMusic(), level) : null;

			if (entity != null)
				entity.discard();

			for (Player player : players) {
				if (!(player instanceof ServerPlayer serverPlayer))
					continue;

				if (playerStillValid.test(serverPlayer)) {
					Vec3 pos = RandomUtil.selection(this.playerSpawnPoints);
					spawnBoss = true;

					serverPlayer.connection.teleport(pos.x, pos.y, pos.z, 0, 0);
					InventoryUtil.giveItemTo(serverPlayer, AoAItems.RETURN_CRYSTAL);
					serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.bossWarning"), ChatFormatting.GREEN));
				}
				else {
					serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.tooFar"), ChatFormatting.RED));
				}

				if (soundBuilder != null)
					soundBuilder.onlyFor(player);
			}

			if (spawnBoss) {
				AABB bounds = getStructureBounds(level);

				AoAScheduler.schedule(1, tick2 -> {
					getEntitiesInside(level, bounds).forEach(entity2 -> {
						if (!(entity2 instanceof Player) && !(entity2 instanceof ItemEntity))
							entity2.discard();
					});
				});

				AoAScheduler.schedule(40, tick2 -> {
					if (soundBuilder != null)
						soundBuilder.play();
				});

				AoAScheduler.schedule(140, tick2 -> {
					int playerCount = getPlayersInside(level, bounds).size();

					if (playerCount > 0)
						bossFunction.spawn(level, getRandomBossSpawn(), stack, playerCount);
				});
			}
		});

		for (Player player : players) {
			player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.teleportWarning"), ChatFormatting.GOLD));
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

		this.structureStart = level.structureManager().getStructureAt(this.structurePos, structure);

		return this.structureStart;
	}

	@Nullable
	public AABB getStructureBounds(ServerLevel level) {
		if (this.structureBounds != null)
			return this.structureBounds;

		StructureStart structureStart = getStructureStart(level);

		if (structureStart == null)
			return null;

		this.structureBounds = AABB.of(structureStart.getBoundingBox()).inflate(50);

		return this.structureBounds;
	}

	public List<Player> getPlayersInside(ServerLevel level) {
		AABB bounds = getStructureBounds(level);

		if (bounds == null)
			return List.of();

		return getPlayersInside(level, bounds);
	}

	public List<Player> getPlayersInside(ServerLevel level, AABB bounds) {
		return EntityRetrievalUtil.getPlayers(level, bounds);
	}

	public List<Entity> getEntitiesInside(ServerLevel level) {
		AABB bounds = getStructureBounds(level);

		if (bounds == null)
			return List.of();

		return getEntitiesInside(level, bounds);
	}

	public List<Entity> getEntitiesInside(ServerLevel level, AABB bounds) {
		return EntityRetrievalUtil.getEntities(level, bounds, entity -> !(entity instanceof Player));
	}

	public Vec3 getRandomBossSpawn() {
		return RandomUtil.selection(this.bossSpawnPoints);
	}
}