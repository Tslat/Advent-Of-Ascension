package net.tslat.aoa3.common.networking.packets.leaderboard;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.leaderboard.task.LeaderboardActions;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.RegistryUtil;

import java.util.Optional;

public record LeaderboardPageDataRequestPacket(Optional<AoASkill> skill, int page) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("leaderboard_page_data_request");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeOptional(this.skill, (buf, skill) -> buf.writeResourceLocation(RegistryUtil.getId(skill)));
		buffer.writeVarInt(this.page);
	}

	public LeaderboardPageDataRequestPacket decode(FriendlyByteBuf buffer) {
		return new LeaderboardPageDataRequestPacket(buffer.readOptional(buf -> AoARegistries.AOA_SKILLS.getEntry(buf.readResourceLocation())), buffer.readVarInt());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		LeaderboardActions.getPlayerData(this.skill, this.page, (ServerPlayer)context.player().get());
	}
}
