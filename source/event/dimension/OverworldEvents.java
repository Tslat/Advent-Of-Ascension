package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAEntitySpawns;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Random;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class OverworldEvents {
	private static final Random eventRand = new Random();
	private static long lastWorldCheckTime = 0;
	private static final HashSet<Event> events = new HashSet<Event>(Event.values().length);

	@SubscribeEvent
	public static void onOverworldtick(TickEvent.WorldTickEvent ev) {
		if (ev.phase == TickEvent.Phase.START && !ev.world.isRemote() && ev.world.getDimension().getType() == DimensionType.OVERWORLD)
			doTickCheck(ev);
	}

	public static void doTickCheck(TickEvent.WorldTickEvent ev) {
		if (ev.world.getGameTime() <= 24000L || ev.phase != TickEvent.Phase.START || !(ev.world instanceof ServerWorld))
			return;

		ServerWorld world = (ServerWorld)ev.world;
		
		int time = (int)(world.getDayTime() % 24000L);

		switch (time) {
			case 480:
				if (!AoAConfig.SERVER.overworldEventsEnabled.get())
					break;

				eventRand.setSeed(world.getSeed() + world.getGameTime());

				if (RandomUtil.percentChance(AoAConfig.SERVER.bigDayEventChance.get()))
					activateEvent(world, Event.BIG_DAY);

				if (RandomUtil.percentChance(AoAConfig.SERVER.creepDayEventChance.get()))
					activateEvent(world, Event.CREEP_DAY);

				if (RandomUtil.percentChance(AoAConfig.SERVER.deathDayEventChance.get()))
					activateEvent(world, Event.DEATH_DAY);

				break;
			case 12500:
				for (ServerPlayerEntity pl : world.getPlayers()) {
					PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
					boolean tributed = false;
					int luxon = plData.stats().getTribute(Deities.LUXON);
					int selyan = plData.stats().getTribute(Deities.SELYAN);
					int pluton = plData.stats().getTribute(Deities.PLUTON);
					int erebon = plData.stats().getTribute(Deities.EREBON);

					if (pluton == 200) {
						pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.pluton", TextFormatting.GOLD));
						tributed = true;
					}

					if (luxon == 200) {
						pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.luxon", TextFormatting.AQUA));
						tributed = true;
					}

					if (selyan == 200) {
						pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.selyan", TextFormatting.GREEN));
						tributed = true;
					}

					if (erebon == 200) {
						pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.erebon", TextFormatting.DARK_RED));
						tributed = true;
					}

					if (pluton >= 100 && luxon >= 100 && erebon >= 100 && selyan >= 100) {
						pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.krasaun", TextFormatting.LIGHT_PURPLE));
						pl.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 6000, 0, true, false));

						tributed = true;

						if (pluton == 200 && luxon == 200 && erebon == 200 && selyan == 200 && AoAConfig.COMMON.resourcesEnabled.get())
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAWeapons.HOLY_SWORD.get()));
					}

					if (tributed)
						world.playSound(null, pl.getPosX(), pl.getPosY(), pl.getPosZ(), AoASounds.TRIBUTE_SUCCESS.get(), SoundCategory.AMBIENT, 1.0f, 1.0f);
				}

				break;
			case 12600:
				if (!AoAConfig.SERVER.overworldEventsEnabled.get())
					break;

				eventRand.setSeed(world.getSeed() + world.getGameTime());

				if (RandomUtil.percentChance(AoAConfig.SERVER.soulScurryEventChance.get()))
					activateEvent(world, Event.SOUL_SCURRY);

				if (RandomUtil.percentChance(AoAConfig.SERVER.bloodHuntEventChance.get()))
					activateEvent(world, Event.BLOOD_HUNT);

				if (RandomUtil.percentChance(AoAConfig.SERVER.lunarInvasionEventChance.get()))
					activateEvent(world, Event.LUNAR_INVASION);

				if (AoAConfig.SERVER.fullMoonEventEnabled.get() && world.getCurrentMoonPhaseFactor() == 1)
					activateEvent(world, Event.FULL_MOON);

				break;
			case 12000:
			case 12020:
			case 12040:
			case 13005:
				deactivateEvent(world, Event.BIG_DAY);
				deactivateEvent(world, Event.CREEP_DAY);
				deactivateEvent(world, Event.DEATH_DAY);

				break;
			case 23031:
				for (ServerPlayerEntity pl : world.getPlayers()) {
					PlayerUtil.getAdventPlayer(pl).stats().resetAllTribute();
				}

				break;
			case 23060:
			case 23080:
			case 23100:
			case 5:
			case 1005:
				deactivateEvent(world, Event.SOUL_SCURRY);
				deactivateEvent(world, Event.BLOOD_HUNT);
				deactivateEvent(world, Event.LUNAR_INVASION);
				deactivateEvent(world, Event.FULL_MOON);

				break;
			default:
				break;
		}
	}

	public static void activateEvent(ServerWorld world, @Nonnull Event event) {
		if (!events.contains(event)) {
			events.add(event);
			AoAEntitySpawns.addEventSpawns(event);

			ITextComponent message = getEventMessage(event, false);
			SoundEvent sound = null;

			switch (event) {
				case BIG_DAY:
					sound = AoASounds.BIG_DAY_START.get();
					break;
				case BLOOD_HUNT:
					sound = AoASounds.BLOOD_HUNT_START.get();
					break;
				case CREEP_DAY:
					sound = AoASounds.CREEP_DAY_START.get();
					break;
				case DEATH_DAY:
					sound = AoASounds.DEATH_DAY_START.get();
					break;
				case FULL_MOON:
					break;
				case LUNAR_INVASION:
					sound = AoASounds.LUNAR_INVASION_START.get();
					break;
				case SOUL_SCURRY:
					sound = AoASounds.SOUL_SCURRY_START.get();
					break;
			}

			for (ServerPlayerEntity pl : world.getPlayers()) {
				pl.sendMessage(message);

				if (sound != null)
					world.playSound(null, pl.getPosX(), pl.getPosY(), pl.getPosZ(), sound, SoundCategory.AMBIENT, 1.0f, 1.0f);
			}
		}
	}

	private static ITextComponent getEventMessage(Event event, boolean isEnding) {
		switch (event) {
			case BIG_DAY:
				return LocaleUtil.getLocaleMessage("message.event." + (isEnding ? "end" : "start") + ".bigDay", TextFormatting.GOLD);
			case BLOOD_HUNT:
				return LocaleUtil.getLocaleMessage("message.event." + (isEnding ? "end" : "start") + ".bloodHunt", TextFormatting.DARK_RED);
			case CREEP_DAY:
				return LocaleUtil.getLocaleMessage("message.event." + (isEnding ? "end" : "start") + ".creepDay", TextFormatting.GREEN);
			case DEATH_DAY:
				return LocaleUtil.getLocaleMessage("message.event." + (isEnding ? "end" : "start") + ".deathDay", TextFormatting.RED);
			case FULL_MOON:
				return LocaleUtil.getLocaleMessage("message.event." + (isEnding ? "end" : "start") + ".fullMoon", TextFormatting.GRAY);
			case LUNAR_INVASION:
				return LocaleUtil.getLocaleMessage("message.event." + (isEnding ? "end" : "start") + ".lunarInvasion", TextFormatting.LIGHT_PURPLE);
			case SOUL_SCURRY:
				return LocaleUtil.getLocaleMessage("message.event." + (isEnding ? "end" : "start") + ".soulScurry", TextFormatting.AQUA);
			default:
				return null;
		}
	}

	public static void deactivateEvent(ServerWorld world, @Nonnull Event event) {
		if (events.contains(event)) {
			events.remove(event);
			AoAEntitySpawns.removeEventSpawns(event);

			ITextComponent message = getEventMessage(event, true);

			for (ServerPlayerEntity pl : world.getPlayers()) {
				pl.sendMessage(message);
			}
		}
	}

	public static boolean isEventActive(@Nonnull Event event) {
		return events.contains(event);
	}

	public static void doWorldStartCheck(World world) {
		if (lastWorldCheckTime >= world.getDayTime() - 10)
			return;

		events.clear();

		int time = (int)(world.getDayTime() % 24000L);

		if (time < 23000) {
			if (time >= 12600) {
				long remainder = time - 12600;

				eventRand.setSeed(world.getSeed() + (world.getGameTime() - remainder));

				if (RandomUtil.oneInNChance(15))
					events.add(Event.SOUL_SCURRY);

				if (RandomUtil.oneInNChance(18))
					events.add(Event.BLOOD_HUNT);

				if (RandomUtil.oneInNChance(22))
					events.add(Event.LUNAR_INVASION);

				if (world.getCurrentMoonPhaseFactor() == 1)
					events.add(Event.FULL_MOON);
			}
			else if (time >= 480) {
				long remainder = time - 480;

				eventRand.setSeed(world.getSeed() + (world.getGameTime() - remainder));

				if (RandomUtil.oneInNChance(26))
					events.add(Event.BIG_DAY);

				if (RandomUtil.oneInNChance(30))
					events.add(Event.CREEP_DAY);

				if (RandomUtil.oneInNChance(25))
					events.add(Event.DEATH_DAY);
			}
		}

		lastWorldCheckTime = world.getDayTime();
	}

	public static void alertNewPlayer(World world, ServerPlayerEntity player) {
		for (Event event : events) {
			player.sendMessage(getEventMessage(event, false));
		}
	}

	public enum Event {
		BIG_DAY,
		BLOOD_HUNT,
		CREEP_DAY,
		DEATH_DAY,
		LUNAR_INVASION,
		SOUL_SCURRY,
		FULL_MOON
	}
}
