package net.tslat.aoa3.common.networking;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
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

	private static void registerPackets(final RegisterPayloadHandlerEvent ev) {
		final IPayloadRegistrar registrar = ev.registrar(AdventOfAscension.MOD_ID);

		registrar.configuration(PlayerHalosLoginSyncPacket.ID, PlayerHalosLoginSyncPacket::decode, PlayerHalosLoginSyncPacket::receiveMessage);
		registrar.configuration(SkillReqLoginSyncPacket.ID, SkillReqLoginSyncPacket::decode, SkillReqLoginSyncPacket::receiveMessage);

		registrar.play(PlayerDataSyncPacket.ID, PlayerDataSyncPacket::decode, PlayerDataSyncPacket::receiveMessage);
		registrar.play(PlayerDataUpdatePacket.ID, PlayerDataUpdatePacket::decode, PlayerDataUpdatePacket::receiveMessage);
		registrar.play(ToastPopupPacket.ID, ToastPopupPacket::decode, ToastPopupPacket::receiveMessage);
		registrar.play(ScreenEffectPacket.ID, ScreenEffectPacket::decode, ScreenEffectPacket::receiveMessage);
		registrar.play(GunRecoilPacket.ID, GunRecoilPacket::decode, GunRecoilPacket::receiveMessage);
		registrar.play(XpGainPacket.ID, XpGainPacket::decode, XpGainPacket::receiveMessage);
		registrar.play(HaloSelectPacket.ID, HaloSelectPacket::decode, HaloSelectPacket::receiveMessage);
		registrar.play(SyncHaloDataPacket.ID, SyncHaloDataPacket::decode, SyncHaloDataPacket::receiveMessage);
		registrar.play(GuiDataPacket.ID, GuiDataPacket::decode, GuiDataPacket::receiveMessage);
		registrar.play(WikiSearchPacket.ID, WikiSearchPacket::decode, WikiSearchPacket::receiveMessage);
		registrar.play(ChangeMusicPacket.ID, ChangeMusicPacket::decode, ChangeMusicPacket::receiveMessage);
		registrar.play(PatchouliBookSyncPacket.ID, PatchouliBookSyncPacket::decode, PatchouliBookSyncPacket::receiveMessage);
		registrar.play(AccountPatchouliBookPacket.ID, AccountPatchouliBookPacket::decode, AccountPatchouliBookPacket::receiveMessage);
		registrar.play(GivePatchouliBookPacket.ID, GivePatchouliBookPacket::decode, GivePatchouliBookPacket::receiveMessage);
		registrar.play(UpdateClientMovementPacket.ID, UpdateClientMovementPacket::decode, UpdateClientMovementPacket::receiveMessage);
		registrar.play(PlayerAbilityKeybindTriggerPacket.ID, PlayerAbilityKeybindTriggerPacket::decode, PlayerAbilityKeybindTriggerPacket::receiveMessage);
		registrar.play(AddSkillCyclePacket.ID, AddSkillCyclePacket::decode, AddSkillCyclePacket::receiveMessage);
		registrar.play(ToggleAoAAbilityPacket.ID, ToggleAoAAbilityPacket::decode, ToggleAoAAbilityPacket::receiveMessage);
		registrar.play(SyncAoAAbilityDataPacket.ID, SyncAoAAbilityDataPacket::decode, SyncAoAAbilityDataPacket::receiveMessage);
		registrar.play(AoASoundBuilderPacket.ID, AoASoundBuilderPacket::decode, AoASoundBuilderPacket::receiveMessage);
		registrar.play(ParticleEffectPacket.ID, ParticleEffectPacket::decode, ParticleEffectPacket::receiveMessage);
		registrar.play(MultipartTogglePacket.ID, MultipartTogglePacket::decode, MultipartTogglePacket::receiveMessage);
	}

	public static void sendToAllNearbyPlayers(AoAPacket packet, ServerLevel level, Vec3 origin, double radius) {
		PacketDistributor.NEAR.with(new PacketDistributor.TargetPoint(origin.x, origin.y, origin.z, radius, level.dimension())).send(packet);
	}

	public static void sendToPlayer(ServerPlayer player, AoAPacket packet) {
		if (player.connection != null)
			PacketDistributor.PLAYER.with(player).send(packet);
	}

	public static void sendToAllPlayers(AoAPacket packet) {
		PacketDistributor.ALL.noArg().send(packet);
	}

	public static void sendToServer(AoAPacket packet) {
		PacketDistributor.SERVER.noArg().send(packet);
	}

	public static void sendToAllPlayersTrackingBlock(ServerLevel level, BlockPos pos, AoAPacket packet) {
		PacketDistributor.TRACKING_CHUNK.with(level.getChunkAt(pos)).send(packet);
	}

	public static void sendToAllPlayersTrackingEntity(AoAPacket packet, Entity entity) {
		if (entity instanceof ServerPlayer pl)
			sendToPlayer(pl, packet);

		PacketDistributor.TRACKING_ENTITY.with(entity).send(packet);
	}
}
