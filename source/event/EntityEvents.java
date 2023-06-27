package net.tslat.aoa3.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

public final class EntityEvents {
	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingTickEvent.class, EntityEvents::onEntityUpdate);
		forgeBus.addListener(EventPriority.NORMAL, false, EntityJoinLevelEvent.class, EntityEvents::onEntityJoinWorld);
		forgeBus.addListener(EventPriority.LOWEST, false, MobSpawnEvent.FinalizeSpawn.class, EntityEvents::onEntitySpawn);
		forgeBus.addListener(EventPriority.NORMAL, false, ExplosionEvent.Detonate.class, EntityEvents::onEntityExploded);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerInteractEvent.EntityInteractSpecific.class, EntityEvents::onEntityInteract);
	}

	private static void onEntityInteract(final PlayerInteractEvent.EntityInteractSpecific ev) {
		if (!ev.getEntity().level().isClientSide) {
			if (ev.getTarget() instanceof Piglin piglin && piglin.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items.GOLDEN_HELMET) {
				ItemStack stack = ev.getEntity().getItemInHand(ev.getHand());

				if ((stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE || stack.getItem() == AoAItems.GOLD_COIN.get()) && piglin.getOffhandItem().isEmpty()) {
					piglin.addTag("BarteringForExplosiveIdol");
					piglin.setItemSlot(EquipmentSlot.OFFHAND, stack.split(1));
					piglin.setGuaranteedDrop(EquipmentSlot.OFFHAND);
					piglin.getBrain().setMemoryWithExpiry(MemoryModuleType.ADMIRING_ITEM, true, 121);
					piglin.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
					piglin.getNavigation().stop();

					AoAScheduler.scheduleSyncronisedTask(() -> {
						if (piglin != null && piglin.isAlive() && piglin.getHealth() >= piglin.getMaxHealth()) {
							ItemStack offHandItem = piglin.getItemInHand(InteractionHand.OFF_HAND);

							if (offHandItem.getItem() == Items.ENCHANTED_GOLDEN_APPLE || offHandItem.getItem() == AoAItems.GOLD_COIN.get()) {
								piglin.setItemInHand(InteractionHand.OFF_HAND, Items.GOLD_INGOT.getDefaultInstance());
								piglin.getBrain().eraseMemory(MemoryModuleType.ADMIRING_ITEM);
							}
						}
					}, 120);
				}
			}
		}
	}

	private static void onEntityUpdate(LivingEvent.LivingTickEvent ev) {
		if (ev.getEntity().level().isClientSide && AoAConfigs.CLIENT.partyDeaths.get() && ev.getEntity().deathTime >= 10) {
			AABB boundingBox = ev.getEntity().getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				ev.getEntity().level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.1f, 3, 0, 0, 0, 1, -1), boundingBox.minX + RandomUtil.randomValueUpTo(width), boundingBox.minY + RandomUtil.randomValueUpTo(height), boundingBox.minZ + RandomUtil.randomValueUpTo(depth), RandomUtil.randomScaledGaussianValue(0.05d), 0, RandomUtil.randomScaledGaussianValue(0.05d));
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

	private static void onEntitySpawn(final MobSpawnEvent.FinalizeSpawn ev) {
		if (ev.getSpawnType() == MobSpawnType.SPAWNER)
			ev.getEntity().getPersistentData().putBoolean("spawned_by_spawner", true);
	}

	private static void onEntityExploded(final ExplosionEvent.Detonate ev) {
		if (AoAConfigs.SERVER.saveLootFromExplosions.get())
			ev.getAffectedEntities().removeIf(entity -> entity instanceof ItemEntity && entity.tickCount < 20);
	}
}
