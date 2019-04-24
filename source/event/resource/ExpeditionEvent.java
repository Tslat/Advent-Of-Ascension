package net.nevermine.event.resource;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.skill.expedition.expeditionHelper;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Skills.Expedition;

public class ExpeditionEvent {
	private Random r = new Random();

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		if (!ev.player.worldObj.isRemote && ev.phase == TickEvent.Phase.END && !ev.player.capabilities.isFlying && !ev.player.isRiding() && ((int)ev.player.field_71091_bM != (int)ev.player.posX || (int)ev.player.field_71097_bO != (int)ev.player.posZ)) {


			PlayerContainer cont = PlayerContainer.getProperties(ev.player);
			int level;

			if (ev.player.ticksExisted % 120 == 0 && ev.player.isSprinting()) {
				cont.addExperience(cont.getExpRequired(Expedition) / expeditionHelper.getXpDenominator(cont.getLevel(Expedition)), Expedition);
			}

			if (cont.getExpeditionBoost() == 1) {
				if (ev.player.ticksExisted % 600 == 0 && ev.player.isSprinting()) {
					if (r.nextInt(110) < cont.getLevel(Expedition) && !ev.player.isPotionActive(Potion.moveSpeed)) {
						ev.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 80, 4));
						ev.player.addChatMessage(StringUtil.getColourLocale("message.feedback.expedition.speed", EnumChatFormatting.AQUA));
					}
				}
			}
			else if (cont.getExpeditionBoost() == 2) {
				if (ev.player.ticksExisted % 600 == 0 && ev.player.isSprinting()) {
					if (r.nextInt(110) < cont.getLevel(Expedition)) {
						ev.player.getFoodStats().addStats(5, 0.3f);
						ev.player.addChatMessage(StringUtil.getColourLocale("message.feedback.expedition.food", EnumChatFormatting.AQUA));
					}
				}
			}
			else {
				if (ev.player.ticksExisted % 500 == 0 && ev.player.isInWater()) {
					if (r.nextInt(110) < cont.getLevel(Expedition)) {
						ev.player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 400, 0));
						ev.player.addChatMessage(StringUtil.getColourLocale("message.feedback.expedition.breath", EnumChatFormatting.AQUA));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerHurtEvent(final LivingHurtEvent e) {
		if (!e.entity.worldObj.isRemote && e.entity instanceof EntityPlayer) {
			final EntityPlayer player = (EntityPlayer)e.entity;

			if (e.source == DamageSource.fall) {
				int level = PlayerContainer.getProperties(player).getLevel(Expedition);

				if (level < 10)
					return;

				if (level < 20) {
					e.ammount *= 0.95;
				}
				else if (level < 30) {
					e.ammount *= 0.9;
				}
				else if (level < 40) {
					e.ammount *= 0.85;
				}
				else if (level < 50) {
					e.ammount *= 0.8;
				}
				else if (level < 60) {
					e.ammount *= 0.7;
				}
				else if (level < 70) {
					e.ammount *= 0.6;
				}
				else if (level < 80) {
					e.ammount *= 0.5;
				}
				else if (level < 90) {
					e.ammount *= 0.4;
				}
				else {
					e.ammount *= 0.3;
				}
			}
		}
	}
}
