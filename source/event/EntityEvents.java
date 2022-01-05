package net.tslat.aoa3.event;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

public final class EntityEvents {
	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingUpdateEvent.class, EntityEvents::onEntityUpdate);
		forgeBus.addListener(EventPriority.NORMAL, false, EntityJoinWorldEvent.class, EntityEvents::onEntityJoinWorld);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingSpawnEvent.SpecialSpawn.class, EntityEvents::onEntitySpawn);
		forgeBus.addListener(EventPriority.NORMAL, false, ExplosionEvent.Detonate.class, EntityEvents::onEntityExploded);
	}

	private static void onEntityUpdate(LivingEvent.LivingUpdateEvent ev) {
		if (ev.getEntityLiving().level.isClientSide && AoAConfig.CLIENT.partyDeaths.get() && ev.getEntityLiving().deathTime >= 19) {
			AxisAlignedBB boundingBox = ev.getEntity().getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				ev.getEntityLiving().level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.03f, 3, 0), boundingBox.minX + RandomUtil.randomValueUpTo(width), boundingBox.minY + RandomUtil.randomValueUpTo(height), boundingBox.minZ + RandomUtil.randomValueUpTo(depth), RandomUtil.randomScaledGaussianValue(0.05d), 0, RandomUtil.randomScaledGaussianValue(0.05d));
			}
		}
	}

	private static void onEntityJoinWorld(EntityJoinWorldEvent ev) {
		if (!ev.getWorld().isClientSide && WorldUtil.isWorld(ev.getWorld(), AoADimensions.NETHER.key)) {
			if (ev.getEntity() instanceof WitherEntity && ((WitherEntity)ev.getEntity()).getInvulnerableTicks() == 220) {
				for (PlayerEntity pl : ev.getWorld().getEntitiesOfClass(PlayerEntity.class, ev.getEntity().getBoundingBox().inflate(50))) {
					if (ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
						ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.ABYSS_REALMSTONE.get()));
				}
			}
		}
	}

	private static void onEntitySpawn(final LivingSpawnEvent.SpecialSpawn ev) {
		if (ev.getSpawnReason() == SpawnReason.SPAWNER)
			ev.getEntity().getPersistentData().putBoolean("spawned_by_spawner", true);
	}

	private static void onEntityExploded(ExplosionEvent.Detonate ev) {
		if (AoAConfig.SERVER.saveLootFromExplosions.get())
			ev.getAffectedEntities().removeIf(entity -> entity instanceof ItemEntity && entity.tickCount < 20);
	}
}
