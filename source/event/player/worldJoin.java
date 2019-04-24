package net.nevermine.event.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.nevermine.assist.AddPackets;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.WorldUtil;
import net.nevermine.container.AncientBossesContainer;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.tasks.SyncPlayerTributeTask;
import net.nevermine.resource.tribute.TributeMessage;
import net.nevermine.skill.SkillMessage;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class worldJoin {
	@SubscribeEvent
	public void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent e) {
		if (e.player instanceof EntityPlayerMP && !e.player.worldObj.isRemote) {
			if (e.player.getUniqueID().equals(UUID.fromString("010318ef-28fc-4c7c-8940-2f0d62eabfa6")) || e.player.getUniqueID().equals(UUID.fromString("2459b511-ca45-43d8-808d-f0eb30a63be4"))) {
				for (final EntityPlayer pl : (List<EntityPlayer>)e.player.worldObj.playerEntities) {
					pl.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "The divines approach..."));
				}
			}

			PlayerContainer cont = PlayerContainer.getProperties(e.player);

			for (PlayerContainer.Skills sk : PlayerContainer.Skills.values()) {
				AddPackets.network.sendTo(new SkillMessage(PlayerContainer.skillIndex(sk), cont.getLevel(sk), cont.progressPercentage(sk), sk == PlayerContainer.Skills.Expedition ? cont.getExpeditionBoost() : 0), (EntityPlayerMP)e.player);
			}

			AddPackets.network.sendTo(new TributeMessage(cont.getTribute(PlayerContainer.Deities.Selyan), cont.getTribute(PlayerContainer.Deities.Luxon), cont.getTribute(PlayerContainer.Deities.Erebon), cont.getTribute(PlayerContainer.Deities.Pluton)), (EntityPlayerMP)e.player);

			PlayerContainer.Deities deity = WorldUtil.getWorldDeity(e.player.worldObj.provider.dimensionId);

			if (deity != null) {
				new SyncPlayerTributeTask(e.player, deity, 200).schedule(1, TimeUnit.SECONDS);
			}
		}
	}

	@SubscribeEvent
	public void onJoinWorld(final EntityJoinWorldEvent e) {
		if (e.entity instanceof EntityPlayer) {

			if (e.entity.posY <= 2) {
				((EntityPlayer)e.entity).setPositionAndUpdate(e.entity.posX, (double)e.entity.worldObj.getHeightValue((int)e.entity.posX, (int)e.entity.posZ), e.entity.posZ);
			}
		}
	}

	@SubscribeEvent
	public void onWorldUnload(final WorldEvent.Unload e) {
		if (e.world.provider.dimensionId == ConfigurationHelper.ancientcavern) {
			AncientBossesContainer.unloadAllBosses();
		}
	}
}
