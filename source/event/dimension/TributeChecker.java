package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class TributeChecker {
	@SubscribeEvent
	public static void onOverworldtick(TickEvent.WorldTickEvent ev) {
		if (ev.phase == TickEvent.Phase.START && !ev.world.isClientSide() && WorldUtil.isWorld(ev.world, AoADimensions.OVERWORLD.key)) {
			if (ev.world.getGameTime() <= 24000L || !(ev.world instanceof ServerWorld))
				return;

			ServerWorld world = (ServerWorld)ev.world;

			int time = (int)(world.getDayTime() % 24000L);

			switch (time) {
				case 12500:
					for (ServerPlayerEntity pl : world.players()) {
						PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
						boolean tributed = false;
						int luxon = plData.stats().getTribute(Deities.LUXON);
						int selyan = plData.stats().getTribute(Deities.SELYAN);
						int pluton = plData.stats().getTribute(Deities.PLUTON);
						int erebon = plData.stats().getTribute(Deities.EREBON);

						if (pluton == 200) {
							pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.pluton", TextFormatting.GOLD), Util.NIL_UUID);
							tributed = true;
						}

						if (luxon == 200) {
							pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.luxon", TextFormatting.AQUA), Util.NIL_UUID);
							tributed = true;
						}

						if (selyan == 200) {
							pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.selyan", TextFormatting.GREEN), Util.NIL_UUID);
							tributed = true;
						}

						if (erebon == 200) {
							pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.erebon", TextFormatting.DARK_RED), Util.NIL_UUID);
							tributed = true;
						}

						if (pluton >= 100 && luxon >= 100 && erebon >= 100 && selyan >= 100) {
							pl.sendMessage(LocaleUtil.getLocaleMessage("message.event.tribute.krasaun", TextFormatting.LIGHT_PURPLE), Util.NIL_UUID);
							pl.addEffect(new EffectInstance(Effects.NIGHT_VISION, 6000, 0, true, false));

							tributed = true;

							if (pluton == 200 && luxon == 200 && erebon == 200 && selyan == 200 && AoAConfig.COMMON.resourcesEnabled.get())
								ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAWeapons.HOLY_SWORD.get()));
						}

						if (tributed)
							world.playSound(null, pl.getX(), pl.getY(), pl.getZ(), AoASounds.TRIBUTE_SUCCESS.get(), SoundCategory.AMBIENT, 1.0f, 1.0f);
					}

					break;
				case 23031:
					for (ServerPlayerEntity pl : world.players()) {
						PlayerUtil.getAdventPlayer(pl).stats().resetAllTribute();
					}

					break;
				default:
					break;
			}
		}
	}
}
