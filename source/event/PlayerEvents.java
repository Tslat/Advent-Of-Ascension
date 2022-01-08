package net.tslat.aoa3.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoATools;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.event.dimension.LelyetiaEvents;
import net.tslat.aoa3.event.dimension.LunalusEvents;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.event.dimension.VoxPondsEvents;
import net.tslat.aoa3.object.item.armour.AdventArmour;
import net.tslat.aoa3.object.item.misc.ReservedItem;
import net.tslat.aoa3.object.item.misc.summoning.BossSpawningItem;
import net.tslat.aoa3.object.item.tool.misc.ExpFlask;
import net.tslat.aoa3.object.item.weapon.sword.BaseSword;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.*;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerEvents {
	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.PlayerTickEvent.class, PlayerEvents::onPlayerTick);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingJumpEvent.class, PlayerEvents::onPlayerJump);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingAttackEvent.class, PlayerEvents::onPlayerHit);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingHurtEvent.class, PlayerEvents::onPlayerHurt);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingFallEvent.class, PlayerEvents::onPlayerFall);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingDeathEvent.class, PlayerEvents::onEntityDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.BreakEvent.class, PlayerEvents::onBlockBreak);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.EntityPlaceEvent.class, PlayerEvents::onBlockPlace);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, PlayerEvents::onPlayerLogin);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTossEvent.class, PlayerEvents::onItemToss);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerXpEvent.PickupXp.class, PlayerEvents::onPlayerPickupXp);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemFishedEvent.class, PlayerEvents::onPlayerFishing);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerChangedDimensionEvent.class, PlayerEvents::onDimensionChange);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTooltipEvent.class, PlayerEvents::onTooltip);
	}

	private static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (WorldUtil.isWorld(ev.player.level, AoADimensions.LELYETIA.key)) {
				LelyetiaEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level, AoADimensions.VOX_PONDS.key)) {
				VoxPondsEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level, AoADimensions.LUNALUS.key)) {
				LunalusEvents.doPlayerTick(ev.player);
			}
		}
		else {
			if (WorldUtil.isWorld(ev.player.level, AoADimensions.NOWHERE.key))
				NowhereEvents.doPlayerTick(ev);
		}
	}

	private static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level, AoADimensions.LUNALUS.key) && ev.getEntity() instanceof PlayerEntity)
			LunalusEvents.doPlayerJump((PlayerEntity)ev.getEntity());
	}

	private static void onPlayerHit(final LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity && ev.getEntityLiving().getHealth() - ev.getAmount() <= 0 && ev.getEntityLiving().level.getLevelData().isHardcore())
			ReservedItem.handlePlayerDeath((ServerPlayerEntity)ev.getEntityLiving());
	}

	private static void onPlayerHurt(final LivingHurtEvent ev) {
		Entity attacker = ev.getSource().getEntity();

		if (DamageUtil.isMeleeDamage(ev.getSource()) && attacker instanceof LivingEntity) {
			ItemStack weapon = ((LivingEntity)attacker).getItemInHand(Hand.MAIN_HAND);

			if (weapon.getItem() instanceof BaseSword)
				ev.setAmount((float)((BaseSword)weapon.getItem()).getDamageForAttack(ev.getEntityLiving(), (LivingEntity)attacker, weapon, ev.getAmount()));
		}

		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntityLiving();

			if (pl.getHealth() > 0 && ev.getSource().isExplosion() && ev.getSource().getDirectEntity() instanceof CreeperEntity) {
				if ((!pl.level.getEntitiesOfClass(TNTEntity.class, ev.getSource().getDirectEntity().getBoundingBox().inflate(3)).isEmpty() || !pl.level.getEntitiesOfClass(TNTEntity.class, pl.getBoundingBox().inflate(3)).isEmpty()) && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.CREEPONIA_REALMSTONE.get()));
			}
		}
	}

	private static void onPlayerFall(final LivingFallEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity)ev.getEntityLiving();

			if (ev.getDistance() > 25 && ev.getDamageMultiplier() > 0 && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.LELYETIA_REALMSTONE.get()));

			if (WorldUtil.isWorld(player.level, AoADimensions.LUNALUS.key))
				LunalusEvents.doPlayerLanding(player, ev);
		}
	}

	private static void onEntityDeath(final LivingDeathEvent ev) {
		if (!ev.getEntity().level.isClientSide) {
			if (ev.getEntity() instanceof ServerPlayerEntity) {
				ReservedItem.handlePlayerDeath((ServerPlayerEntity)ev.getEntity());
			}
			else if (ev.getSource().getEntity() instanceof ServerPlayerEntity) {
				if (WorldUtil.isWorld(ev.getEntity().level, AoADimensions.DEEPLANDS.key)) {
					if (ev.getEntityLiving() instanceof FlyingEntity)
						ev.getEntityLiving().spawnAtLocation(new ItemStack(AoAItems.MUSIC_DISC_CAVERNS.get()), 0.5f);
				}
			}
		}
	}

	private static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		PlayerEntity pl = ev.getPlayer();

		if (pl instanceof ServerPlayerEntity) {
			BlockPos pos = ev.getPos();
			BlockState block = pl.level.getBlockState(pos);

			if (block.is(Tags.Blocks.ORES) && pos.getY() <= 5 && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.DEEPLANDS_REALMSTONE.get()));
		}
	}

	private static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		if (ev.getEntity() instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntity();

			if (PlayerUtil.isWearingFullSet(pl, AdventArmour.Type.HYDRANGIC)) {
				if (ev.getPlacedBlock().getBlock() instanceof IGrowable && BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), ev.getEntity().level, ev.getPos(), pl)) {
					ev.getWorld().levelEvent(2005, ev.getPos(), 0);
					pl.inventory.hurtArmor(DamageSource.GENERIC, 16);
				}
			}
		}
	}

	private static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntityLiving();
			UUID uuid = pl.getGameProfile().getId();
			String msg = null;

			if (uuid.compareTo(UUID.fromString("2459b511-ca45-43d8-808d-f0eb30a63be4")) == 0) {
				msg = TextFormatting.DARK_RED + "It begins...Is this the end?";

				((ServerWorld)pl.level).sendParticles(ParticleTypes.LARGE_SMOKE, pl.getX(), pl.getY() + 0.2d, pl.getZ(), 16, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, 1);
			}
			else if (AoAHaloUtil.isCrazyDonator(uuid)) {
				msg = TextFormatting.LIGHT_PURPLE + "They approach. Tremble before them.";
			}

			if (msg != null)
				pl.getServer().getPlayerList().broadcastMessage(new StringTextComponent(msg), ChatType.GAME_INFO, Util.NIL_UUID);

			AoAHaloUtil.syncWithNewClient(pl);
			ServerPlayerDataManager.syncNewPlayer(pl);
			AoASkillReqReloadListener.syncNewPlayer(pl);

			PlayerAdvancements plAdvancements = pl.getAdvancements();
			Advancement rootAdv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/root"));

			if (rootAdv == null) {
				Logging.logMessage(Level.WARN, "Unable to find inbuilt advancements, another mod is breaking things.");

				if (AoAConfig.COMMON.doVerboseDebugging.get()) {
					Logging.logStatusMessage("Printing out current advancements list...");
					pl.getServer().getAdvancements().getAllAdvancements().forEach(advancement -> Logging.logMessage(Level.INFO, advancement.getId().toString()));
				}
			}
			else if (!plAdvancements.getOrStartProgress(rootAdv).isDone()) {
				plAdvancements.award(AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/by_the_books")), "legitimate");
				plAdvancements.award(rootAdv, "playerjoin");
			}
		}
	}

	private static void onItemToss(final ItemTossEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			ItemEntity entityItem = ev.getEntityItem();
			Item item = entityItem.getItem().getItem();

			if (item == AoAItems.BLANK_REALMSTONE.get()) {
				if (ev.getPlayer().isInLava()) {
					ItemUtil.givePlayerItemOrDrop(ev.getPlayer(), new ItemStack(AoAItems.NETHER_REALMSTONE.get()));
					ev.getEntityItem().remove();
				}
			}
			else if (item instanceof ReservedItem) {
				ReservedItem.handlePlayerToss(ev);
			}
			else if (item instanceof BossSpawningItem) {
				World world = ev.getPlayer().getCommandSenderWorld();

				if (world.getDifficulty() == Difficulty.PEACEFUL) {
					ev.getPlayer().sendMessage(new TranslationTextComponent("message.feedback.spawnBoss.difficultyFail"), Util.NIL_UUID);

					return;
				}

				ev.setCanceled(true);
				world.addFreshEntity(BossSpawningItem.newBossEntityItemFromExisting(entityItem, ev.getPlayer()));

				BossSpawningItem bossItem = (BossSpawningItem)item;

				if (bossItem.getThrowingSound() != null)
					world.playSound(null, entityItem.getX(), entityItem.getX(), entityItem.getZ(), bossItem.getThrowingSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}
		}
	}

	private static void onPlayerPickupXp(final PlayerXpEvent.PickupXp ev) {
		if (!ev.getPlayer().level.isClientSide && ev.getOrb().value > 0) {
			ItemStack stack = ItemUtil.getStackFromInventory(ev.getPlayer(), AoATools.EXP_FLASK.get());

			if (stack != null) {
				ExpFlask.addExp(stack, ev.getOrb().value);
				ev.setCanceled(true);
				ev.getOrb().value = 0;
				ev.getOrb().remove();
			}
		}
	}

	private static void onPlayerFishing(final ItemFishedEvent ev) {
		if (WorldUtil.isWorld(ev.getEntityLiving().level, AoADimensions.LBOREAN.key) && RandomUtil.oneInNChance(10)) {
			FishingBobberEntity hook = ev.getHookEntity();
			LivingEntity fisher = ev.getEntityLiving();

			ItemEntity drop = new ItemEntity(fisher.level, hook.getX(), hook.getY(), hook.getZ(), new ItemStack(AoAItems.CALL_OF_THE_DRAKE.get()));
			double velocityX = fisher.getX() - hook.getX();
			double velocityY = fisher.getY() - hook.getY();
			double velocityZ = fisher.getZ() - hook.getZ();
			double velocity = MathHelper.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);

			drop.setDeltaMovement(velocityX * 0.1D, velocityY * 0.1D + (double)MathHelper.sqrt(velocity) * 0.08D, velocityZ * 0.1D);
			fisher.level.addFreshEntity(drop);
		}
	}

	private static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getFrom() == AoADimensions.NOWHERE.key)
			NowhereEvents.doDimensionChange(ev);
	}

	private static void onTooltip(final ItemTooltipEvent ev) {
		Map<String, List<Pair<ResourceLocation, Integer>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(ev.getItemStack().getItem().getRegistryName());

		if (restrictions != null) {
			List<ITextComponent> lines = ev.getToolTip();

			if (ev.getFlags().isAdvanced()) {
				lines.add(1, LocaleUtil.getLocaleMessage("gui.tooltip.skillReq", TextFormatting.DARK_RED));

				int index = 2;

				for (Map.Entry<String, List<Pair<ResourceLocation, Integer>>> reqEntry : restrictions.entrySet()) {
					lines.add(index++, new StringTextComponent("  ").withStyle(TextFormatting.RED).append(LocaleUtil.getLocaleMessage(Util.makeDescriptionId("ability", AoAAbilities.LEVEL_RESTRICTION.getId()) + ".description." + reqEntry.getKey())).append(":"));

					for (Pair<ResourceLocation, Integer> pair : reqEntry.getValue()) {
						AoASkill skill = AoASkills.getSkill(pair.getFirst());

						lines.add(index++, new StringTextComponent("    ").withStyle(TextFormatting.GOLD).append(pair.getSecond() + " ").append(skill.getName()));
					}
				}

				lines.add(index, new StringTextComponent(""));
			}
			else {
				lines.add(1, LocaleUtil.getLocaleMessage("gui.tooltip.skillReq.hidden", TextFormatting.DARK_RED));
			}
		}
	}
}
