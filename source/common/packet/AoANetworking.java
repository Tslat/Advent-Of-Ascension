package net.tslat.aoa3.common.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.packets.*;
import net.tslat.aoa3.common.packet.packets.patchouli.PatchouliBookOpenPacket;
import net.tslat.aoa3.common.packet.packets.patchouli.PatchouliBookSyncPacket;
import net.tslat.aoa3.common.packet.packets.patchouli.PatchouliGiveBookPacket;

public class AoANetworking {
	private static final String REV = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(AdventOfAscension.MOD_ID, "aoa_packets"), () -> REV, REV::equals, REV::equals);

	public static void init() {
		int id = 0;

		INSTANCE.registerMessage(id++, PlayerDataSyncPacket.class, PlayerDataSyncPacket::encode, PlayerDataSyncPacket::decode, PlayerDataSyncPacket::receiveMessage);
		INSTANCE.registerMessage(id++, PlayerDataUpdatePacket.class, PlayerDataUpdatePacket::encode, PlayerDataUpdatePacket::decode, PlayerDataUpdatePacket::receiveMessage);
		INSTANCE.registerMessage(id++, ToastPopupPacket.class, ToastPopupPacket::encode, ToastPopupPacket::decode, ToastPopupPacket::receiveMessage);
		INSTANCE.registerMessage(id++, ScreenEffectPacket.class, ScreenEffectPacket::encode, ScreenEffectPacket::decode, ScreenEffectPacket::receiveMessage);
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
		INSTANCE.registerMessage(id++, ServerParticlePacket.class, ServerParticlePacket::encode, ServerParticlePacket::decode, ServerParticlePacket::receiveMessage);
		INSTANCE.registerMessage(id++, SkillRequirementDataPacket.class, SkillRequirementDataPacket::encode, SkillRequirementDataPacket::decode, SkillRequirementDataPacket::receiveMessage);
		INSTANCE.registerMessage(id++, SyncAoAAbilityDataPacket.class, SyncAoAAbilityDataPacket::encode, SyncAoAAbilityDataPacket::decode, SyncAoAAbilityDataPacket::receiveMessage);
		INSTANCE.registerMessage(id++, AoASoundBuilderPacket.class, AoASoundBuilderPacket::encode, AoASoundBuilderPacket::decode, AoASoundBuilderPacket::receiveMessage);
		INSTANCE.registerMessage(id++, ParticleEffectPacket.class, ParticleEffectPacket::encode, ParticleEffectPacket::decode, ParticleEffectPacket::receiveMessage);
		INSTANCE.registerMessage(id++, MultipartTogglePacket.class, MultipartTogglePacket::encode, MultipartTogglePacket::decode, MultipartTogglePacket::receiveMessage);
	}

	public static void sendToAllNearbyPlayers(AoAPacket packet, ServerLevel level, Vec3 origin, double radius) {
		INSTANCE.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(origin.x, origin.y, origin.z, radius, level.dimension())), packet);
	}

	public static void sendToPlayer(ServerPlayer player, AoAPacket packet) {
		if (player.connection != null)
			INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
	}

	public static void sendToAllPlayers(AoAPacket packet) {
		INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
	}

	public static void sendToServer(AoAPacket packet) {
		INSTANCE.sendToServer(packet);
	}

	public static void sendToAllPlayersTrackingBlock(ServerLevel level, BlockPos pos, AoAPacket packet) {
		INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(pos)), packet);
	}

	public static void sendToAllPlayersTrackingEntity(AoAPacket packet, Entity entity) {
		if (entity instanceof ServerPlayer pl)
			sendToPlayer(pl, packet);

		INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), packet);
	}
}
