package net.nevermine.event.resource;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.assist.WorldUtil;
import net.nevermine.boss.cavern.CavernBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import java.util.List;
import java.util.Random;

import static cpw.mods.fml.common.gameevent.TickEvent.Phase.END;
import static net.nevermine.container.PlayerContainer.Deities.*;

public class TributeTicker {
	private Random rand = new Random();

	@SubscribeEvent
	public void onTickEvent(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		PlayerContainer cont = PlayerContainer.getProperties(ev.player);

		cont.resetAllTribute();

		PlayerContainer.Deities deity = WorldUtil.getWorldDeity(ev.toDim);

		if (deity != null) {
			cont.adjustTribute(deity, 200);
		}
	}

	@SubscribeEvent
	public void tickWorld(final TickEvent.WorldTickEvent evt) {
		if (evt.world.isRemote || evt.phase != END || evt.world.provider.dimensionId != 0)
			return;

		long time = evt.world.getWorldTime() % 24000L;
		if (time == 12500L) {
			for (EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				PlayerContainer cont = PlayerContainer.getProperties(p);
				boolean tributed = false;
				int luxon = cont.getTribute(Luxon);
				int selyan = cont.getTribute(Selyan);
				int pluton = cont.getTribute(Pluton);
				int erebon = cont.getTribute(Erebon);

				p.addChatMessage(StringUtil.getColourLocale("message.event.nightfall", EnumChatFormatting.LIGHT_PURPLE));

				if (pluton == 200) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.tribute.pluton", EnumChatFormatting.GOLD));
					tributed = true;
				}

				if (luxon == 200) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.tribute.luxon", EnumChatFormatting.AQUA));
					tributed = true;
				}

				if (selyan == 200) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.tribute.selyan", EnumChatFormatting.GREEN));
					tributed = true;
				}

				if (erebon == 200) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.tribute.erebon", EnumChatFormatting.DARK_RED));
					tributed = true;
				}

				if (pluton >= 100 && luxon >= 100 && erebon >= 100 && selyan >= 100) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.tribute.krasaun", EnumChatFormatting.LIGHT_PURPLE));

					if (!p.inventory.addItemStackToInventory(new ItemStack(Itemizer.GoldCoin, 3)))
						p.dropItem(Itemizer.GoldCoin, 3);

					p.addPotionEffect(new PotionEffect(21, 6000, 1));

					tributed = true;
				}

				if (tributed) {
					evt.world.playSoundAtEntity(p, "nevermine:TributeAcquired", 3.85f, 1.0f);
				}
				else {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.tribute.none." + Integer.toString(rand.nextInt(10)), EnumChatFormatting.GRAY));
					evt.world.playSoundAtEntity(p, "nevermine:TributeNone", 4.85f, 1.0f);
				}
			}
		}
		else if (time == 23031L) {
			for (EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.sunrise", EnumChatFormatting.LIGHT_PURPLE));
				PlayerContainer.getProperties(p).resetAllTribute();
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event) {
		PlayerContainer.getProperties(event.player).resetAllTribute();
	}

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		if (ev.player.worldObj.isRemote)
			return;

		PlayerContainer cont = PlayerContainer.getProperties(ev.player);

		if (ev.player.isPlayerFullyAsleep())
			cont.resetAllTribute();

		if (ev.player.dimension != 0 || !ev.player.worldObj.isDaytime()) {
			if (cont.getTribute(Luxon) == 200)
				if (!ev.player.isPotionActive(Potion.regeneration))
					ev.player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 220, 1));

			if (cont.getTribute(Erebon) == 200)
				ev.player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, -1, 0));

			if (cont.getTribute(Pluton) == 200)
				ev.player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, -1, 2));

			if (cont.getTribute(Selyan) == 200)
				ev.player.addPotionEffect(new PotionEffect(Potion.resistance.id, -1, 0));
		}
	}

	@SubscribeEvent
	public void onPlaceEvent(final BlockEvent.PlaceEvent e) {
		if (e.player.capabilities.isCreativeMode)
			return;

		if (e.player.dimension == ConfigurationHelper.ancientcavern) {
			if (e.placedBlock != Blocks.torch) {
				e.setCanceled(true);
				e.player.addChatMessage(StringUtil.getColourLocale("message.feedback.blockPlace.ancientCavern", EnumChatFormatting.DARK_RED));
			}
		}
		else if (e.player.dimension == ConfigurationHelper.immortallis) {
			e.setCanceled(true);
			e.player.setFire(10);
			e.player.addChatMessage(StringUtil.getColourLocale("message.feedback.blockPlace.immortallis", EnumChatFormatting.DARK_RED));
		}
	}

	@SubscribeEvent
	public void onBucketEvent(final PlayerInteractEvent e) {
		if (e.entityPlayer.capabilities.isCreativeMode)
			return;

		if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
			if (e.entityPlayer.getHeldItem() != null && (e.entityPlayer.getHeldItem().getItem() == Items.water_bucket || e.entityPlayer.getHeldItem().getItem() == Items.lava_bucket)) {
				if (e.entity.dimension == ConfigurationHelper.immortallis) {
					e.setCanceled(true);
					e.entityPlayer.addChatMessage(StringUtil.getColourLocale("message.feedback.blockPlace.immortallis", EnumChatFormatting.DARK_RED));
				}
				else if (e.entity.dimension == ConfigurationHelper.ancientcavern) {
					e.setCanceled(true);
					e.entityPlayer.addChatMessage(StringUtil.getColourLocale("message.feedback.blockPlace.ancientCavern", EnumChatFormatting.DARK_RED));
				}
			}
		}
	}

	@SubscribeEvent
	public void onBreakBlock(final BlockEvent.BreakEvent e) {
		if (!(e.getPlayer() instanceof FakePlayer) && e.world.isDaytime() && e.getPlayer().dimension == 0) {
			if (e.block instanceof BlockCrops && e.world.getBlockMetadata(e.x, e.y, e.z) == 7) {
				PlayerContainer.getProperties(e.getPlayer()).adjustTribute(Selyan, 2);
			}
		}
	}

	@SubscribeEvent
	public void onKill(final LivingDeathEvent e) {
		if (e.entity instanceof EntityPlayer && e.source.getSourceOfDamage() instanceof CavernBoss) {
			((EntityLivingBase)e.source.getEntity()).heal(4000.0f);
		}

		if (e.entity.dimension == 0 && e.entity.worldObj.isDaytime() && e.source.getSourceOfDamage() instanceof EntityPlayer) {
			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)e.source.getSourceOfDamage());

			cont.adjustTribute(Erebon, 8);
			cont.adjustTribute(Luxon, -8);
		}
	}
}
