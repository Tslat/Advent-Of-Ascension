package net.tslat.aoa3.event;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.handlers.PlayerCrownHandler;
import net.tslat.aoa3.common.packet.PacketRevenge;
import net.tslat.aoa3.common.packet.PacketSkillData;
import net.tslat.aoa3.common.packet.PacketTributeData;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.PlayerUtil;

import java.util.UUID;

public class GlobalEvents {
	public static int tick;

	@SubscribeEvent
	public void serverTick(final TickEvent.ServerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			tick++;

			if (tick > 24000)
				tick = 0;
		}
	}

	@SubscribeEvent
	public void onLootTableLoad(final LootTableLoadEvent ev) {
		switch (ev.getName().toString()) {
			case "minecraft:chests/stronghold_corridor":
			case "minecraft:chests/simple_dungeon":
			case "minecraft:chests/igloo_chest":
			case "minecraft:chests/abandoned_mineshaft":
			case "minecraft:chests/stronghold_crossing":
			case "minecraft:chests/jungle_temple":
			case "minecraft:chests/desert_pyramid":
			case "minecraft:chests/stronghold_library":
			case "minecraft:chests/village_blacksmith":
			case "minecraft:chests/woodland_mansion":
			case "minecraft:chests/spawn_bonus_chest":
				LootEntry[] entry = new LootEntry[] {new LootEntryTable(new ResourceLocation("aoa3", "inject/carved_rune_blocks"), 1, 0, new LootCondition[0], "aoa3_carved_runic_blocks_entry")};
				LootPool pool = new LootPool(entry, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "aoa_carved_runic_blocks");
				ev.getTable().addPool(pool);
				break;
			default:
				break;
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent e) {
		if (e.player instanceof EntityPlayerMP && !e.player.world.isRemote) {
			UUID uuid = e.player.getGameProfile().getId();
			String msg = null;

			if (AdventOfAscension.instance.isTslat(uuid)) {
				msg = TextFormatting.DARK_RED + "It begins...Is this the end?";

				((WorldServer)e.player.world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, e.player.posX, e.player.posY + 0.2, e.player.posZ, 16, 0.5, 0.5, 0.5, 0.1);
			}
			else if (uuid.equals(UUID.fromString("010318ef-28fc-4c7c-8940-2f0d62eabfa6"))) {
				msg = TextFormatting.LIGHT_PURPLE + "Xolova creeps in to watch you suffer. Feel free to die now.";
			}
			else if (PlayerCrownHandler.isCrazyDonator(uuid)) {
				msg = TextFormatting.LIGHT_PURPLE + "They approach. Tremble before them.";
			}

			if (msg != null)
				FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(new TextComponentString(msg));

			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(e.player);

			for (Enums.Skills sk : Enums.Skills.values()) {
				PacketUtil.network.sendTo(new PacketSkillData(sk.id, cap.getDisplayLevel(sk), cap.getExp(sk), cap.expeditionBoost), (EntityPlayerMP)e.player);
			}

			PacketUtil.network.sendTo(new PacketTributeData(cap.getTribute(Enums.Deities.EREBON), cap.getTribute(Enums.Deities.LUXON), cap.getTribute(Enums.Deities.PLUTON), cap.getTribute(Enums.Deities.SELYAN)), (EntityPlayerMP)e.player);
			PacketUtil.network.sendTo(new PacketRevenge(cap.isRevengeActive()), (EntityPlayerMP)e.player);
			PlayerCrownHandler.syncWithNewClient((EntityPlayerMP)e.player);

			Advancement rootAdv = ModUtil.getAdvancement("overworld/root");
			PlayerAdvancements plAdvancements = ((EntityPlayerMP)e.player).getAdvancements();

			if (!plAdvancements.getProgress(rootAdv).isDone()) {
				plAdvancements.grantCriterion(ModUtil.getAdvancement("overworld/by_the_books"), "legitimate");
				plAdvancements.grantCriterion(rootAdv, "playerjoin");
			}
		}
	}
}
