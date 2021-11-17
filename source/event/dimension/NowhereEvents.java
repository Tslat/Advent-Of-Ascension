package net.tslat.aoa3.event.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tablet.TabletItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class NowhereEvents {
	@SubscribeEvent
	public static void onBreakAttempt(final PlayerEvent.BreakSpeed ev) {
		if (WorldUtil.isWorld(ev.getPlayer().level, AoADimensions.NOWHERE.key))
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void playerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.START && WorldUtil.isWorld(ev.player.level, AoADimensions.NOWHERE.key)) {
			PlayerEntity pl = ev.player;

			pl.abilities.mayBuild = pl.isCreative();

			if (PlayerUtil.shouldPlayerBeAffected(pl)) {
				if (pl.getY() < 0) {
					pl.setPos(0, 202, 0);
					pl.fallDistance = -1;

					if (pl instanceof ServerPlayerEntity)
						PlayerUtil.getAdventPlayer((ServerPlayerEntity)pl).returnItemStorage();
				}

				if (pl.isFallFlying())
					pl.stopFallFlying();
			}
		}
	}

	@SubscribeEvent
	public static void onEmptyBucketUse(final FillBucketEvent ev) {
		if (!WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key))
			return;

		PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntity());

		if (relevantPlayer == null || !relevantPlayer.isCreative()) {
			ev.setCanceled(true);
			ev.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getFrom() == AoADimensions.NOWHERE.key) {
			if (ev.getPlayer() instanceof ServerPlayerEntity) {
				ItemUtil.clearInventoryOfItems(ev.getPlayer(), new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
				PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getPlayer()).returnItemStorage();
			}
		}
	}

	@SubscribeEvent
	public static void onExplosion(final ExplosionEvent.Detonate ev) {
		if (!WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key))
			return;

		ev.getAffectedBlocks().clear();
	}

	@SubscribeEvent
	public static void onFluidSpread(final BlockEvent.FluidPlaceBlockEvent ev) {
		if (!WorldUtil.isWorld((World)ev.getWorld(), AoADimensions.NOWHERE.key))
			return;

		ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void onMobGrief(final EntityMobGriefingEvent ev) {
		if (!WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key))
			return;

		ev.setResult(Event.Result.DENY);
	}

	@SubscribeEvent
	public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getPlayer().isCreative() || !WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key))
			return;

		BlockState block = ev.getWorld().getBlockState(ev.getPos());
		Item heldItem = ev.getPlayer().getItemInHand(ev.getHand()).getItem();

		if (block.getBlock() == Blocks.JUKEBOX) {
			if (heldItem == Items.AIR || heldItem instanceof MusicDiscItem) {
				ev.setUseItem(Event.Result.ALLOW);
				ev.setUseBlock(Event.Result.ALLOW);
				ev.getPlayer().abilities.mayBuild = true;
			}
		}
		else if (block.getBlock() == Blocks.SOUL_CAMPFIRE || block.getBlock() == Blocks.ENDER_CHEST) {
			ev.setUseItem(Event.Result.DENY);
		}
		else if (heldItem instanceof TabletItem || heldItem == AoAItems.LOTTO_TOTEM.get()) {
			ev.setUseItem(Event.Result.ALLOW);
			ev.setUseBlock(Event.Result.DENY);
			ev.getPlayer().abilities.mayBuild = true;
		}
		else {
			ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onLoot(final LivingDropsEvent ev) {
		if (!WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key))
			return;

		if (!(ev.getEntityLiving() instanceof PlayerEntity) && !(ev.getEntityLiving() instanceof TameableEntity)) {
			ev.getDrops().clear();
			ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void blockBreak(final BlockEvent.BreakEvent ev) {
		if (!WorldUtil.isWorld(ev.getPlayer().level, AoADimensions.NOWHERE.key))
			return;

		if (!ev.getPlayer().isCreative())
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void blockPlace(final BlockEvent.EntityPlaceEvent ev) {
		World world = null;

		if (ev.getWorld() instanceof World) {
			world = (World)ev.getWorld();
		}
		else if (ev.getEntity() != null) {
			world = ev.getEntity().level;
		}

		if (world == null || !WorldUtil.isWorld(world, AoADimensions.NOWHERE.key))
			return;

		if (!(ev.getEntity() instanceof PlayerEntity) || !((PlayerEntity)ev.getEntity()).isCreative())
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void chorusFruitTeleport(final EntityTeleportEvent.ChorusFruit ev) {
		if (!WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key))
			return;

		if (ev.getEntity() instanceof PlayerEntity && !((PlayerEntity)ev.getEntity()).isCreative()) {
			ev.setCanceled(true);

			PlayerUtil.notifyPlayer((PlayerEntity)ev.getEntity(), new TranslationTextComponent("message.feedback.nowhere.chorusFruit"));
		}
	}

	@SubscribeEvent
	public static void enderPearlTeleport(final EntityTeleportEvent.EnderPearl ev) {
		if (!WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key))
			return;

		if (ev.getEntity() instanceof PlayerEntity && !((PlayerEntity)ev.getEntity()).isCreative()) {
			ev.setCanceled(true);

			PlayerUtil.notifyPlayer((PlayerEntity)ev.getEntity(), new TranslationTextComponent("message.feedback.nowhere.enderPearl"));
		}
	}

	@SubscribeEvent
	public static void onPlayerTeleport(final EntityTeleportEvent.TeleportCommand ev) {
		if (!WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key))
			return;

		if (ev.getEntity() instanceof PlayerEntity && !((PlayerEntity)ev.getEntity()).isCreative()) {
			ev.setCanceled(true);

			PlayerUtil.notifyPlayer((PlayerEntity)ev.getEntity(), new TranslationTextComponent("message.feedback.nowhere.teleport"));
		}
	}
}
