package net.tslat.aoa3.common.packet;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.packets.*;

public class AoAPackets {
	private static final String REV = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(AdventOfAscension.MOD_ID, "aoa_packets"), () -> REV, REV::equals, REV::equals);

	public static void registerPackets() {
		int id = 0;

		INSTANCE.registerMessage(id++, PlayerDataSyncPacket.class, PlayerDataSyncPacket::encode, PlayerDataSyncPacket::decode, PlayerDataSyncPacket::receiveMessage);
		INSTANCE.registerMessage(id++, PlayerDataUpdatePacket.class, PlayerDataUpdatePacket::encode, PlayerDataUpdatePacket::decode, PlayerDataUpdatePacket::receiveMessage);
		INSTANCE.registerMessage(id++, ToastPopupPacket.class, ToastPopupPacket::encode, ToastPopupPacket::decode, ToastPopupPacket::receiveMessage);
		INSTANCE.registerMessage(id++, ScreenOverlayPacket.class, ScreenOverlayPacket::encode, ScreenOverlayPacket::decode, ScreenOverlayPacket::receiveMessage);
		INSTANCE.registerMessage(id++, GunRecoilPacket.class, GunRecoilPacket::encode, GunRecoilPacket::decode, GunRecoilPacket::receiveMessage);
		INSTANCE.registerMessage(id++, XpGainPacket.class, XpGainPacket::encode, XpGainPacket::decode, XpGainPacket::receiveMessage);
		INSTANCE.registerMessage(id++, HaloChangePacket.class, HaloChangePacket::encode, HaloChangePacket::decode, HaloChangePacket::receiveMessage);
		INSTANCE.registerMessage(id++, PlayerHaloDataPacket.class, PlayerHaloDataPacket::encode, PlayerHaloDataPacket::decode, PlayerHaloDataPacket::receiveMessage);
		INSTANCE.registerMessage(id++, GuiDataPacket.class, GuiDataPacket::encode, GuiDataPacket::decode, GuiDataPacket::receiveMessage);
		INSTANCE.registerMessage(id++, WikiSearchPacket.class, WikiSearchPacket::encode, WikiSearchPacket::decode, WikiSearchPacket::receiveMessage);
		INSTANCE.registerMessage(id++, MusicPacket.class, MusicPacket::encode, MusicPacket::decode, MusicPacket::receiveMessage);
		INSTANCE.registerMessage(id++, PatchouliBookSyncPacket.class, PatchouliBookSyncPacket::encode, PatchouliBookSyncPacket::decode, PatchouliBookSyncPacket::receiveMessage);
		INSTANCE.registerMessage(id++, PatchouliBookOpenPacket.class, PatchouliBookOpenPacket::encode, PatchouliBookOpenPacket::decode, PatchouliBookOpenPacket::receiveMessage);
		INSTANCE.registerMessage(id++, PatchouliGiveBookPacket.class, PatchouliGiveBookPacket::encode, PatchouliGiveBookPacket::decode, PatchouliGiveBookPacket::receiveMessage);
		INSTANCE.registerMessage(id++, UpdateClientMovementPacket.class, UpdateClientMovementPacket::encode, UpdateClientMovementPacket::decode, UpdateClientMovementPacket::receiveMessage);
		INSTANCE.registerMessage(id++, PlayerAbilityKeybindPacket.class, PlayerAbilityKeybindPacket::encode, PlayerAbilityKeybindPacket::decode, PlayerAbilityKeybindPacket::receiveMessage);
		INSTANCE.registerMessage(id++, AddSkillCyclePacket.class, AddSkillCyclePacket::encode, AddSkillCyclePacket::decode, AddSkillCyclePacket::receiveMessage);
		INSTANCE.registerMessage(id++, ToggleAoAAbilityPacket.class, ToggleAoAAbilityPacket::encode, ToggleAoAAbilityPacket::decode, ToggleAoAAbilityPacket::receiveMessage);
	}

	public static void messagePlayer(ServerPlayerEntity player, AoAPacket packet) {
		if (player.connection != null)
			INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
	}

	public static void messageAllPlayers(AoAPacket packet) {
		INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
	}

	public static void messageServer(AoAPacket packet) {
		INSTANCE.sendToServer(packet);
	}
}
