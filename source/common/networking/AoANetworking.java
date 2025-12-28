package net.tslat.aoa3.common.networking;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.*;
import net.tslat.aoa3.common.networking.packets.adventplayer.*;
import net.tslat.aoa3.common.networking.packets.patchouli.AccountPatchouliBookPacket;
import net.tslat.aoa3.common.networking.packets.patchouli.GivePatchouliBookPacket;
import net.tslat.aoa3.common.networking.packets.patchouli.PatchouliBookSyncPacket;
import net.tslat.aoa3.common.networking.packets.setup.PlayerHalosLoginSyncPacket;
import net.tslat.aoa3.common.networking.packets.setup.SkillReqLoginSyncPacket;

public class AoANetworking {
	public static void init() {
		AdventOfAscension.getModEventBus().addListener(AoANetworking::registerPackets);
	}

	private static void registerPackets(final RegisterPayloadHandlersEvent ev) {
		final PayloadRegistrar registrar = ev.registrar(AdventOfAscension.MOD_ID);

		registrar.configurationBidirectional(PlayerHalosLoginSyncPacket.TYPE, PlayerHalosLoginSyncPacket.CODEC, PlayerHalosLoginSyncPacket::receiveMessage);
		registrar.configurationBidirectional(SkillReqLoginSyncPacket.TYPE, SkillReqLoginSyncPacket.CODEC, SkillReqLoginSyncPacket::receiveMessage);

		registrar.playToServer(PlayerAbilityKeybindTriggerPacket.TYPE, PlayerAbilityKeybindTriggerPacket.CODEC, PlayerAbilityKeybindTriggerPacket::receiveMessage);
		registrar.playToServer(ToggleAoAAbilityPacket.TYPE, ToggleAoAAbilityPacket.CODEC, ToggleAoAAbilityPacket::receiveMessage);
		registrar.playToServer(HaloSelectPacket.TYPE, HaloSelectPacket.CODEC, HaloSelectPacket::receiveMessage);
		registrar.playToServer(AccountPatchouliBookPacket.TYPE, AccountPatchouliBookPacket.CODEC, AccountPatchouliBookPacket::receiveMessage);
		registrar.playToServer(GivePatchouliBookPacket.TYPE, GivePatchouliBookPacket.CODEC, GivePatchouliBookPacket::receiveMessage);
		registrar.playToServer(AddSkillCyclePacket.TYPE, AddSkillCyclePacket.CODEC, AddSkillCyclePacket::receiveMessage);
		registrar.playToServer(ParticleEffectPacket.TYPE, ParticleEffectPacket.CODEC, ParticleEffectPacket::receiveMessage);
		registrar.playToServer(SyncAoAAbilityDataPacket.TYPE, SyncAoAAbilityDataPacket.CODEC, SyncAoAAbilityDataPacket::receiveMessage);

		registrar.playToClient(PlayerDataSyncPacket.TYPE, PlayerDataSyncPacket.CODEC, PlayerDataSyncPacket::receiveMessage);
		registrar.playToClient(PlayerDataUpdatePacket.TYPE, PlayerDataUpdatePacket.CODEC, PlayerDataUpdatePacket::receiveMessage);
		registrar.playToClient(ToastPopupPacket.TYPE, ToastPopupPacket.CODEC, ToastPopupPacket::receiveMessage);
		registrar.playToClient(ScreenEffectPacket.TYPE, ScreenEffectPacket.CODEC, ScreenEffectPacket::receiveMessage);
		registrar.playToClient(GunRecoilPacket.TYPE, GunRecoilPacket.CODEC, GunRecoilPacket::receiveMessage);
		registrar.playToClient(ScreenShakePacket.TYPE, ScreenShakePacket.CODEC, ScreenShakePacket::receiveMessage);
		registrar.playToClient(XpGainPacket.TYPE, XpGainPacket.CODEC, XpGainPacket::receiveMessage);
		registrar.playToClient(SyncHaloDataPacket.TYPE, SyncHaloDataPacket.CODEC, SyncHaloDataPacket::receiveMessage);
		registrar.playToClient(WikiSearchPacket.TYPE, WikiSearchPacket.CODEC, WikiSearchPacket::receiveMessage);
		registrar.playToClient(PatchouliBookSyncPacket.TYPE, PatchouliBookSyncPacket.CODEC, PatchouliBookSyncPacket::receiveMessage);
		registrar.playToClient(UpdateClientMovementPacket.TYPE, UpdateClientMovementPacket.CODEC, UpdateClientMovementPacket::receiveMessage);
		registrar.playToClient(MultipartTogglePacket.TYPE, MultipartTogglePacket.CODEC, MultipartTogglePacket::receiveMessage);
		registrar.playToClient(HaloChangePacket.TYPE, HaloChangePacket.CODEC, HaloChangePacket::receiveMessage);
		registrar.playToClient(WorldEventSyncPacket.TYPE, WorldEventSyncPacket.CODEC, WorldEventSyncPacket::receiveMessage);
		registrar.playToClient(WorldEventUpdatePacket.TYPE, WorldEventUpdatePacket.CODEC, WorldEventUpdatePacket::receiveMessage);
		registrar.playToClient(EntityRidePlayerPacket.TYPE, EntityRidePlayerPacket.CODEC, EntityRidePlayerPacket::receiveMessage);
	}

	public static void sendToAllPlayersNearby(AoAPacket packet, ServerLevel level, Vec3 origin, double radius) {
		PacketDistributor.sendToPlayersNear(level, null, origin.x, origin.y, origin.z, radius, packet);
	}

	public static void sendToPlayer(ServerPlayer player, AoAPacket packet) {
		if (player.connection != null)
			PacketDistributor.sendToPlayer(player, packet);
	}

	public static void sendToAllPlayers(AoAPacket packet) {
		PacketDistributor.sendToAllPlayers(packet);
	}

	public static void sendToServer(AoAPacket packet) {
		PacketDistributor.sendToServer(packet);
	}

	public static void sendToAllInLevel(ServerLevel level, AoAPacket packet) {
		PacketDistributor.sendToPlayersInDimension(level, packet);
	}

	public static void sendToAllPlayersTrackingBlock(ServerLevel level, BlockPos pos, AoAPacket packet) {
		PacketDistributor.sendToPlayersTrackingChunk(level, new ChunkPos(pos), packet);
	}

	public static void sendToAllPlayersTrackingEntity(AoAPacket packet, Entity entity) {
		if (entity instanceof ServerPlayer pl)
			sendToPlayer(pl, packet);

		PacketDistributor.sendToPlayersTrackingEntity(entity, packet);
	}
}
