package net.tslat.aoa3.event;

import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

public final class EntityEvents {
	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingTickEvent.class, EntityEvents::onEntityUpdate);
		forgeBus.addListener(EventPriority.NORMAL, false, EntityJoinLevelEvent.class, EntityEvents::onEntityJoinWorld);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingSpawnEvent.SpecialSpawn.class, EntityEvents::onEntitySpawn);
		forgeBus.addListener(EventPriority.NORMAL, false, ExplosionEvent.Detonate.class, EntityEvents::onEntityExploded);
		forgeBus.addListener(EventPriority.NORMAL, false, VillagerTradesEvent.class, EntityEvents::onTraderGenTrades);
	}

	private static void onEntityUpdate(LivingEvent.LivingTickEvent ev) {
		if (ev.getEntity().level.isClientSide && AoAConfigs.CLIENT.partyDeaths.get() && ev.getEntity().deathTime >= 19) {
			AABB boundingBox = ev.getEntity().getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				ev.getEntity().level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.03f, 3, 0, 0, 0, 1, -1), boundingBox.minX + RandomUtil.randomValueUpTo(width), boundingBox.minY + RandomUtil.randomValueUpTo(height), boundingBox.minZ + RandomUtil.randomValueUpTo(depth), RandomUtil.randomScaledGaussianValue(0.05d), 0, RandomUtil.randomScaledGaussianValue(0.05d));
			}
		}
	}

	private static void onEntityJoinWorld(EntityJoinLevelEvent ev) {
		if (!ev.getLevel().isClientSide && WorldUtil.isWorld(ev.getLevel(), AoADimensions.NETHER.key)) {
			if (ev.getEntity() instanceof WitherBoss && ((WitherBoss)ev.getEntity()).getInvulnerableTicks() == 220) {
				for (Player pl : ev.getLevel().getEntitiesOfClass(Player.class, ev.getEntity().getBoundingBox().inflate(50))) {
					if (ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
						ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.ABYSS_REALMSTONE.get()));
				}
			}
		}
	}

	private static void onEntitySpawn(final LivingSpawnEvent.SpecialSpawn ev) {
		if (ev.getSpawnReason() == MobSpawnType.SPAWNER)
			ev.getEntity().getPersistentData().putBoolean("spawned_by_spawner", true);
	}

	private static void onEntityExploded(final ExplosionEvent.Detonate ev) {
		if (AoAConfigs.SERVER.saveLootFromExplosions.get())
			ev.getAffectedEntities().removeIf(entity -> entity instanceof ItemEntity && entity.tickCount < 20);
	}

	private static void onTraderGenTrades(final VillagerTradesEvent ev) {
		if (ev.getType() == VillagerProfession.CARTOGRAPHER) {
			ev.getTrades().get(1).add(new VillagerTrades.TreasureMapForEmeralds(4, AoATags.Structures.ON_RUINED_TELEPORTER_FRAME_MAPS, "filled_map." + AdventOfAscension.MOD_ID + ".ruined_teleporter_frame", MapDecoration.Type.TARGET_POINT, 3, 7));
		}
	}
}
