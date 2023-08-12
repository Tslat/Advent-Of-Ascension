package net.tslat.aoa3.common.packet.packets.leaderboard;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.common.packet.packets.AoAPacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.leaderboard.task.LeaderboardActions;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PageDataRequestPacket implements AoAPacket {
	@Nullable
	private final AoASkill skill;
	private final int page;

	public PageDataRequestPacket(@Nullable AoASkill skill, int page) {
		this.skill = skill;
		this.page = page;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBoolean(this.skill != null);

		if (this.skill != null)
			buffer.writeResourceLocation(RegistryUtil.getId(this.skill));

		buffer.writeVarInt(this.page);
	}

	public PageDataRequestPacket decode(FriendlyByteBuf buf) {
		return new PageDataRequestPacket(buf.readBoolean() ? AoARegistries.AOA_SKILLS.getEntry(buf.readResourceLocation()) : null, buf.readVarInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		LeaderboardActions.getPlayerData(this.skill, this.page, context.get().getSender());
	}
}
