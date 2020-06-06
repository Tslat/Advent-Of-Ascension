package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.EntitySpawnRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Random;

public class OverworldEvents {
	private static final Random eventRand = new Random();
	private static long lastWorldCheckTime = 0;
	private static final HashSet<Enums.CreatureEvents> events = new HashSet<Enums.CreatureEvents>(Enums.CreatureEvents.values().length);

	public static void doTickCheck(TickEvent.WorldTickEvent ev) {
		if (ev.world.getTotalWorldTime() <= 24000L)
			return;

		int time = (int)(ev.world.getWorldTime() % 24000L);

		switch (time) {
			case 480:
				if (!ConfigurationUtil.MainConfig.overworldEvents.enabled)
					break;

				eventRand.setSeed(ev.world.getSeed() + ev.world.getTotalWorldTime());

				if (ConfigurationUtil.MainConfig.overworldEvents.bigDayChance > 0 && eventRand.nextInt(ConfigurationUtil.MainConfig.overworldEvents.bigDayChance) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.BIG_DAY);

				if (ConfigurationUtil.MainConfig.overworldEvents.creepDayChance > 0 && eventRand.nextInt(ConfigurationUtil.MainConfig.overworldEvents.creepDayChance) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.CREEP_DAY);

				if (ConfigurationUtil.MainConfig.overworldEvents.deathDayChance > 0 && eventRand.nextInt(ConfigurationUtil.MainConfig.overworldEvents.deathDayChance) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.DEATH_DAY);

				break;
			case 12500:
				for (EntityPlayer pl : ev.world.playerEntities) {
					PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
					boolean tributed = false;
					int luxon = plData.stats().getTribute(Enums.Deities.LUXON);
					int selyan = plData.stats().getTribute(Enums.Deities.SELYAN);
					int pluton = plData.stats().getTribute(Enums.Deities.PLUTON);
					int erebon = plData.stats().getTribute(Enums.Deities.EREBON);

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

						if (pluton == 200 && luxon == 200 && erebon == 200 && selyan == 200)
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(WeaponRegister.HOLY_SWORD));
					}

					if (tributed) {
						ev.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.TRIBUTE_SUCCESS, SoundCategory.AMBIENT, 1.0f, 1.0f);
					}
					else {
						if (ConfigurationUtil.MainConfig.showDailyMessages)
							pl.sendMessage(StringUtil.getColourLocale("message.event.tribute.none." + AdventOfAscension.rand.nextInt(10), TextFormatting.DARK_GRAY));

						ev.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.TRIBUTE_FAIL, SoundCategory.AMBIENT, 1.0f, 1.0f);
					}
				}

				break;
			case 12600:
				if (!ConfigurationUtil.MainConfig.overworldEvents.enabled)
					break;

				eventRand.setSeed(ev.world.getSeed() + ev.world.getTotalWorldTime());

				if (ConfigurationUtil.MainConfig.overworldEvents.soulScurryChance > 0 && eventRand.nextInt(ConfigurationUtil.MainConfig.overworldEvents.soulScurryChance) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.SOUL_SCURRY);

				if (ConfigurationUtil.MainConfig.overworldEvents.bloodHuntChance > 0 && eventRand.nextInt(ConfigurationUtil.MainConfig.overworldEvents.bloodHuntChance) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.BLOOD_HUNT);

				if (ConfigurationUtil.MainConfig.overworldEvents.lunarInvasionChance > 0 && eventRand.nextInt(ConfigurationUtil.MainConfig.overworldEvents.lunarInvasionChance) == 0)
					activateEvent(ev.world, Enums.CreatureEvents.LUNAR_INVASION);

				if (ConfigurationUtil.MainConfig.overworldEvents.fullMoonEnabled && ev.world.getCurrentMoonPhaseFactor() == 1)
					activateEvent(ev.world, Enums.CreatureEvents.FULL_MOON);

				break;
			case 12000:
			case 12020:
			case 12040:
			case 13005:
				deactivateEvent(ev.world, Enums.CreatureEvents.BIG_DAY);
				deactivateEvent(ev.world, Enums.CreatureEvents.CREEP_DAY);
				deactivateEvent(ev.world, Enums.CreatureEvents.DEATH_DAY);

				break;
			case 23031:
				for (EntityPlayer pl : ev.world.playerEntities) {
					if (ConfigurationUtil.MainConfig.showDailyMessages)
						pl.sendMessage(StringUtil.getColourLocale("message.event.sunrise", TextFormatting.YELLOW));

					PlayerUtil.getAdventPlayer(pl).stats().resetAllTribute();
				}

				break;
			case 23060:
			case 23080:
			case 23100:
			case 5:
			case 1005:
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
		if (!events.contains(event)) {
			events.add(event);
			EntitySpawnRegister.addEventSpawns(event);

			ITextComponent message = getEventMessage(event, false);
			SoundEvent sound = null;

			switch (event) {
				case BIG_DAY:
					sound = SoundsRegister.BIG_DAY_START;
					break;
				case BLOOD_HUNT:
					sound = SoundsRegister.BLOOD_HUNT_START;
					break;
				case CREEP_DAY:
					sound = SoundsRegister.CREEP_DAY_START;
					break;
				case DEATH_DAY:
					sound = SoundsRegister.DEATH_DAY_START;
					break;
				case FULL_MOON:
					break;
				case LUNAR_INVASION:
					sound = SoundsRegister.LUNAR_INVASION_START;
					break;
				case SOUL_SCURRY:
					sound = SoundsRegister.SOUL_SCURRY_START;
					break;
			}

			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);

				if (sound != null)
					world.playSound(null, pl.posX, pl.posY, pl.posZ, sound, SoundCategory.AMBIENT, 1.0f, 1.0f);
			}
		}
	}

	private static ITextComponent getEventMessage(Enums.CreatureEvents event, boolean isEnding) {
		switch (event) {
			case BIG_DAY:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".bigDay", TextFormatting.GOLD);
			case BLOOD_HUNT:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".bloodHunt", TextFormatting.DARK_RED);
			case CREEP_DAY:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".creepDay", TextFormatting.GREEN);
			case DEATH_DAY:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".deathDay", TextFormatting.RED);
			case FULL_MOON:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".fullMoon", TextFormatting.GRAY);
			case LUNAR_INVASION:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".lunarInvasion", TextFormatting.LIGHT_PURPLE);
			case SOUL_SCURRY:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".soulScurry", TextFormatting.AQUA);
			default:
				return null;
		}
	}

	public static void deactivateEvent(World world, @Nonnull Enums.CreatureEvents event) {
		if (events.contains(event)) {
			events.remove(event);
			EntitySpawnRegister.removeEventSpawns(event);

			ITextComponent message = getEventMessage(event, true);

			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);
			}
		}
	}

	public static boolean isEventActive(@Nonnull Enums.CreatureEvents event) {
		return events.contains(event);
	}

	public static void doWorldStartCheck(World world) {
		if (lastWorldCheckTime >= world.getWorldTime() - 10)
			return;

		events.clear();

		int time = (int)(world.getWorldTime() % 24000L);

		if (time < 23000) {
			if (time >= 12600) {
				long remainder = time - 12600;

				eventRand.setSeed(world.getSeed() + (world.getTotalWorldTime() - remainder));

				if (eventRand.nextInt(15) == 0)
					events.add(Enums.CreatureEvents.SOUL_SCURRY);

				if (eventRand.nextInt(18) == 0)
					events.add(Enums.CreatureEvents.BLOOD_HUNT);

				if (eventRand.nextInt(22) == 0)
					events.add(Enums.CreatureEvents.LUNAR_INVASION);

				if (world.getCurrentMoonPhaseFactor() == 1)
					events.add(Enums.CreatureEvents.FULL_MOON);
			}
			else if (time >= 480) {
				long remainder = time - 480;

				eventRand.setSeed(world.getSeed() + (world.getTotalWorldTime() - remainder));

				if (eventRand.nextInt(26) == 0)
					events.add(Enums.CreatureEvents.BIG_DAY);

				if (eventRand.nextInt(30) == 0)
					events.add(Enums.CreatureEvents.CREEP_DAY);

				if (eventRand.nextInt(25) == 0)
					events.add(Enums.CreatureEvents.DEATH_DAY);
			}
		}

		lastWorldCheckTime = world.getWorldTime();
	}

	public static void alertNewPlayer(World world, EntityPlayer player) {
		for (Enums.CreatureEvents event : events) {
			player.sendMessage(getEventMessage(event, false));
		}
	}
}
