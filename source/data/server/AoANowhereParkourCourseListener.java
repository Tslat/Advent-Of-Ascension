package net.tslat.aoa3.data.server;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LootUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AoANowhereParkourCourseListener extends SimpleJsonResourceReloadListener {
	private static final Int2ObjectMap<List<NowhereParkourCourse>> REGISTERED_COURSES = new Int2ObjectOpenHashMap<>();
	private static final String folder = "worldgen/nowhere_parkour_courses";

	public AoANowhereParkourCourseListener() {
		super(new GsonBuilder().setPrettyPrinting().create(), folder);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> entryMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		REGISTERED_COURSES.clear();

		for (Map.Entry<ResourceLocation, JsonElement> entry : entryMap.entrySet()) {
			Either<NowhereParkourCourse, DataResult.PartialResult<NowhereParkourCourse>> result = NowhereParkourCourse.CODEC.parse(JsonOps.INSTANCE, entry.getValue()).get();

			if (result.right().isPresent()) {
				Logging.logMessage(Level.ERROR, "Unable to deserialize parkour course data, disabling. (" + entry.getKey() + ") " + result.right().get().message());

				continue;
			}

			NowhereParkourCourse parkourCourse = result.left().get();

			if (parkourCourse.playerStart == null) {
				Logging.logMessage(Level.ERROR, "No player start point provided for parkour course, disabling course. " + entry.getKey());

				continue;
			}

			REGISTERED_COURSES.computeIfAbsent(parkourCourse.getTier(), key -> new ObjectArrayList<>());
			REGISTERED_COURSES.get(parkourCourse.getTier()).add(parkourCourse);
		}
	}

	@Nullable
	public static NowhereParkourCourse getCourseForPosition(ServerLevel level, Vec3 pos) {
		if (!NowhereEvents.isInParkourRegion(new BlockPos(pos)))
			return null;

		int tier = ((int)pos.x - 500) / 500;

		if (!REGISTERED_COURSES.containsKey(tier))
			return null;

		for (NowhereParkourCourse course : REGISTERED_COURSES.get(tier)) {
			if (course.isOnCourse(level, pos))
				return course;
		}

		return null;
	}

	@Nullable
	public static NowhereParkourCourse getNextCourse(NowhereParkourCourse currentCourse) {
		List<NowhereParkourCourse> sameTierCourses = REGISTERED_COURSES.get(currentCourse.getTier());

		for (int i = 0; i < sameTierCourses.size(); i++) {
			if (sameTierCourses.get(i) == currentCourse)
				return i == sameTierCourses.size() - 1 ? null : sameTierCourses.get(i + 1);
		}

		return null;
	}

	@Nullable
	public static NowhereParkourCourse getFirstCourseForTier(int tier) {
		return REGISTERED_COURSES.containsKey(tier) ? REGISTERED_COURSES.get(tier).get(0) : null;
	}

	public static class NowhereParkourCourse {
		public static final Codec<NowhereParkourCourse> CODEC = RecordCodecBuilder.create(builder -> builder.group(
				ResourceLocation.CODEC.fieldOf("structure_id").forGetter(course -> course.structureId),
				BlockPos.CODEC.fieldOf("structure_pos").forGetter(course -> course.structurePos),
				Vec3.CODEC.fieldOf("player_start").forGetter(course -> course.playerStart),
				Codec.INT.fieldOf("tier").forGetter(course -> course.tier),
				ResourceLocation.CODEC.optionalFieldOf("reward_table").forGetter(course -> course.rewardLootTable)
		).apply(builder, NowhereParkourCourse::new));

		public final ResourceLocation structureId;
		private final BlockPos structurePos;
		private final Vec3 playerStart;
		private final int tier;
		private final Optional<ResourceLocation> rewardLootTable;

		private Structure structure = null;
		private StructureStart structureStart = null;
		private AABB structureBounds = null;

		public NowhereParkourCourse(ResourceLocation structureId, BlockPos structurePos, Vec3 playerStart, int tier, Optional<ResourceLocation> rewardTable) {
			this.structureId = structureId;
			this.structurePos = structurePos;
			this.playerStart = playerStart;
			this.tier = tier;
			this.rewardLootTable = rewardTable;
		}

		public int getTier() {
			return this.tier;
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

		public boolean isOnCourse(ServerLevel level, Vec3 position) {
			AABB bounds = getStructureBounds(level);

			return bounds != null && bounds.contains(position);
		}

		public void grantRewards(ServerPlayer player) {
			this.rewardLootTable.ifPresent(tableId -> {
				ServerLevel level = player.getLevel();

				ItemUtil.givePlayerMultipleItems(player, LootUtil.generateLoot(level, tableId, LootUtil.getGiftContext(level, player.position(), player.getLuck(), player)));
			});

			player.getAdvancements().award(AdvancementUtil.getAdvancement(AdventOfAscension.id("nowhere/tier_" + this.tier + "_acrobat")), "complete_course");
		}

		public void teleportPlayerToCourse(ServerPlayer player) {
			player.connection.teleport(playerStart.x, playerStart.y, playerStart.z, player.getYRot(), player.getXRot());
		}
	}
}
