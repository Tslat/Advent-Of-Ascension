package net.tslat.aoa3.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingConversionEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class EntityEvents {
	@SubscribeEvent
	public static void onEntityUpdate(LivingEvent.LivingUpdateEvent ev) {
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

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent ev) {
		if (!ev.getWorld().isClientSide) {
			if (ev.getEntity() instanceof FishingBobberEntity) {
				FishingBobberEntity hook = (FishingBobberEntity)ev.getEntity();
				Entity fisher = hook.getOwner();

				if (fisher instanceof ServerPlayerEntity && PlayerUtil.isWearingFullSet((ServerPlayerEntity)fisher, AdventArmour.Type.HAULING)) {
					ItemStack stack = ((ServerPlayerEntity)fisher).getItemInHand(Hand.MAIN_HAND);

					if (!(stack.getItem() instanceof FishingRodItem))
						stack = ((ServerPlayerEntity)fisher).getItemInHand(Hand.OFF_HAND);

					if (stack.getItem() instanceof FishingRodItem)
						hook.lureSpeed = Math.min(5, 2 + EnchantmentHelper.getFishingSpeedBonus(stack));
				}
			}
			else if (ev.getEntity() instanceof WitherEntity && ((WitherEntity)ev.getEntity()).getInvulnerableTicks() == 220) {
				if (WorldUtil.isWorld(ev.getWorld(), AoADimensions.NETHER.key)) {
					for (PlayerEntity pl : ev.getWorld().getEntitiesOfClass(PlayerEntity.class, ev.getEntity().getBoundingBox().inflate(50))) {
						if (ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.ABYSS_REALMSTONE.get()));
					}
				}
			}

		}
	}

	@SubscribeEvent
	public static void onEntitySpawn(final LivingSpawnEvent.SpecialSpawn ev) {
		if (ev.getSpawnReason() == SpawnReason.SPAWNER)
			ev.getEntity().getPersistentData().putBoolean("spawned_by_spawner", true);
	}

	@SubscribeEvent
	public static void onEntityExploded(ExplosionEvent.Detonate ev) {
		if (AoAConfig.SERVER.saveLootFromExplosions.get())
			ev.getAffectedEntities().removeIf(entity -> entity instanceof ItemEntity && entity.tickCount < 20);

		if (WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key))
			ev.getAffectedBlocks().clear();
	}

	@SubscribeEvent
	public static void livingConvert(LivingConversionEvent.Pre ev) {
		if (ev.getEntityLiving() instanceof AoATrader)
			ev.setCanceled(true);
	}
}
