package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Random;

public class OverworldEvents {
	private static final Random eventRand = new Random();
	private static long lastWorldCheckTime = 0;
	private static final HashMap<Enums.CreatureEvents, Boolean> eventMap = new HashMap<Enums.CreatureEvents, Boolean>(Enums.CreatureEvents.values().length);

	static {
		for (Enums.CreatureEvents event : Enums.CreatureEvents.values()) {
			eventMap.put(event, false);
		}
	}

	public static void doTickCheck(TickEvent.WorldTickEvent ev) {
		int time = (int)(ev.world.getWorldTime() % 24000L);

		switch (time) {
			case 480:
				eventRand.setSeed(ev.world.getSeed() + ev.world.getTotalWorldTime());

				if (eventRand.nextInt(26) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.BIG_DAY);

				if (eventRand.nextInt(30) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.CREEP_DAY);

				if (eventRand.nextInt(25) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.DEATH_DAY);
				break;
			case 12500:
				for (EntityPlayer pl : ev.world.playerEntities) {
					AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(pl);
					boolean tributed = false;
					int luxon = cap.getTribute(Enums.Deities.LUXON);
					int selyan = cap.getTribute(Enums.Deities.SELYAN);
					int pluton = cap.getTribute(Enums.Deities.PLUTON);
					int erebon = cap.getTribute(Enums.Deities.EREBON);

					if (ConfigurationUtil.MainConfig.showDailyMessages)
						pl.sendMessage(StringUtil.getColourLocale("message.event.nightfall", TextFormatting.DARK_AQUA));

					if (pluton == 200) {
						pl.sendMessage(StringUtil.getColourLocale("message.event.tribute.pluton", TextFormatting.GOLD));
						tributed = true;
					}

					if (luxon == 200) {
						pl.sendMessage(StringUtil.getColourLocale("message.event.tribute.luxon", TextFormatting.AQUA));
						tributed = true;
					}

					if (selyan == 200) {
						pl.sendMessage(StringUtil.getColourLocale("message.event.tribute.selyan", TextFormatting.GREEN));
						tributed = true;
					}

					if (erebon == 200) {
						pl.sendMessage(StringUtil.getColourLocale("message.event.tribute.erebon", TextFormatting.DARK_RED));
						tributed = true;
					}

					if (pluton >= 100 && luxon >= 100 && erebon >= 100 && selyan >= 100) {
						pl.sendMessage(StringUtil.getColourLocale("message.event.tribute.krasaun", TextFormatting.LIGHT_PURPLE));
						pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 6000, 0, true, false));

						tributed = true;
					}

					if (tributed) {
						ev.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.tributeSuccess, SoundCategory.AMBIENT, 1.0f, 1.0f);
					}
					else {
						if (ConfigurationUtil.MainConfig.showDailyMessages)
							pl.sendMessage(StringUtil.getColourLocale("message.event.tribute.none." + AdventOfAscension.rand.nextInt(10), TextFormatting.DARK_GRAY));

						ev.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.tributeFail, SoundCategory.AMBIENT, 1.0f, 1.0f);
					}
				}
				break;
			case 12600:
				eventRand.setSeed(ev.world.getSeed() + ev.world.getTotalWorldTime());

				if (eventRand.nextInt(15) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.SOUL_SCURRY);

				if (eventRand.nextInt(18) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.BLOOD_HUNT);

				if (eventRand.nextInt(22) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.LUNAR_INVASION);

				if (ev.world.getCurrentMoonPhaseFactor() == 1)
					activateEvent(ev.world, Enums.CreatureEvents.FULL_MOON);
				break;
			case 12000:
			case 12020:
			case 12040:
				deactivateEvent(ev.world, Enums.CreatureEvents.BIG_DAY);
				deactivateEvent(ev.world, Enums.CreatureEvents.CREEP_DAY);
				deactivateEvent(ev.world, Enums.CreatureEvents.DEATH_DAY);
				break;
			case 23031:
				for (EntityPlayer pl : ev.world.playerEntities) {
					if (ConfigurationUtil.MainConfig.showDailyMessages)
						pl.sendMessage(StringUtil.getColourLocale("message.event.sunrise", TextFormatting.YELLOW));

					PlayerUtil.getAdventPlayer(pl).resetAllTribute();
				}
				break;
			case 23060:
			case 23080:
			case 23100:
				deactivateEvent(ev.world, Enums.CreatureEvents.SOUL_SCURRY);
				deactivateEvent(ev.world, Enums.CreatureEvents.BLOOD_HUNT);
				deactivateEvent(ev.world, Enums.CreatureEvents.LUNAR_INVASION);
				deactivateEvent(ev.world, Enums.CreatureEvents.FULL_MOON);
				break;
			default:
				break;
		}
	}

	public static void activateEvent(World world, @Nonnull Enums.CreatureEvents event) {
		if (!eventMap.get(event)) {
			eventMap.put(event, true);

			ITextComponent message = null;
			SoundEvent sound = null;

			switch (event) {
				case BIG_DAY:
					message = StringUtil.getColourLocale("message.event.start.bigDay", TextFormatting.GOLD);
					sound = SoundsRegister.eventBigDayStart;
					break;
				case BLOOD_HUNT:
					message = StringUtil.getColourLocale("message.event.start.bloodHunt", TextFormatting.DARK_RED);
					sound = SoundsRegister.eventBloodHuntStart;
					break;
				case CREEP_DAY:
					message = StringUtil.getColourLocale("message.event.start.creepDay", TextFormatting.GREEN);
					sound = SoundsRegister.eventCreepDayStart;
					break;
				case DEATH_DAY:
					message = StringUtil.getColourLocale("message.event.start.deathDay", TextFormatting.RED);
					sound = SoundsRegister.eventDeathDayStart;
					break;
				case FULL_MOON:
					message = StringUtil.getColourLocale("message.event.start.fullMoon", TextFormatting.GRAY);
					break;
				case LUNAR_INVASION:
					message = StringUtil.getColourLocale("message.event.start.lunarInvasion", TextFormatting.LIGHT_PURPLE);
					sound = SoundsRegister.eventLunarInvasionStart;
					break;
				case SOUL_SCURRY:
					message = StringUtil.getColourLocale("message.event.start.soulScurry", TextFormatting.AQUA);
					sound = SoundsRegister.eventSoulScurryStart;
					break;
			}

			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);

				if (sound != null)
					world.playSound(null, pl.posX, pl.posY, pl.posZ, sound, SoundCategory.AMBIENT, 1.0f, 1.0f);
			}
		}
	}

	public static void deactivateEvent(World world, @Nonnull Enums.CreatureEvents event) {
		if (eventMap.get(event)) {
			eventMap.put(event, false);

			ITextComponent message = null;
			SoundEvent sound = null;

			switch (event) {
				case BIG_DAY:
					message = StringUtil.getColourLocale("message.event.end.bigDay", TextFormatting.GOLD);
					break;
				case BLOOD_HUNT:
					message = StringUtil.getColourLocale("message.event.end.bloodHunt", TextFormatting.DARK_RED);
					break;
				case CREEP_DAY:
					message = StringUtil.getColourLocale("message.event.end.creepDay", TextFormatting.GREEN);
					break;
				case DEATH_DAY:
					message = StringUtil.getColourLocale("message.event.end.deathDay", TextFormatting.RED);
					break;
				case FULL_MOON:
					message = StringUtil.getColourLocale("message.event.end.fullMoon", TextFormatting.GRAY);
					break;
				case LUNAR_INVASION:
					message = StringUtil.getColourLocale("message.event.end.lunarInvasion", TextFormatting.LIGHT_PURPLE);
					break;
				case SOUL_SCURRY:
					message = StringUtil.getColourLocale("message.event.end.soulScurry", TextFormatting.AQUA);
					break;
			}

			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);
			}
		}
	}

	public static boolean isEventActive(@Nonnull Enums.CreatureEvents event) {
		return eventMap.get(event);
	}

	public static void doWorldStartCheck(World world) {
		if (lastWorldCheckTime >= world.getWorldTime() - 10)
			return;

		for (Enums.CreatureEvents ev : Enums.CreatureEvents.values()) {
			eventMap.put(ev, false);
		}

		int time = (int)(world.getWorldTime() % 24000L);

		if (time < 23000) {
			if (time >= 12600) {
				long remainder = time - 12600;

				eventRand.setSeed(world.getSeed() + (world.getTotalWorldTime() - remainder));

				if (eventRand.nextInt(15) == 0)
					eventMap.put(Enums.CreatureEvents.SOUL_SCURRY, true);

				if (eventRand.nextInt(18) == 0)
					eventMap.put(Enums.CreatureEvents.BLOOD_HUNT, true);

				if (eventRand.nextInt(22) == 0)
					eventMap.put(Enums.CreatureEvents.LUNAR_INVASION, true);

				if (world.getCurrentMoonPhaseFactor() == 1)
					eventMap.put(Enums.CreatureEvents.FULL_MOON, true);
			}
			else if (time >= 480) {
				long remainder = time - 480;

				eventRand.setSeed(world.getSeed() + (world.getTotalWorldTime() - remainder));

				if (eventRand.nextInt(26) == 0)
					eventMap.put(Enums.CreatureEvents.BIG_DAY, true);

				if (eventRand.nextInt(30) == 0)
					eventMap.put(Enums.CreatureEvents.CREEP_DAY, true);

				if (eventRand.nextInt(25) == 0)
					eventMap.put(Enums.CreatureEvents.DEATH_DAY, true);
			}
		}

		lastWorldCheckTime = world.getWorldTime();
	}
}
