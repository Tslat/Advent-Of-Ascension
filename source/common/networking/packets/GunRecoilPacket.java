package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.tme.api.util.RandomUtil;

public record GunRecoilPacket(float vertical, float lateral) implements AoAPacket {
	public static final Type<GunRecoilPacket> TYPE = new Type<>(AdventOfAscension.id("gun_recoil"));
	public static final StreamCodec<FriendlyByteBuf, GunRecoilPacket> CODEC = StreamCodec.composite(
			ByteBufCodecs.FLOAT, GunRecoilPacket::vertical,
			ByteBufCodecs.FLOAT, GunRecoilPacket::lateral,
            GunRecoilPacket::new);

	public GunRecoilPacket(float vertical) {
		this(vertical, (float)RandomUtil.scaledGaussianValue(0.08f) * vertical);
	}

	@Override
	public Type<? extends GunRecoilPacket> type() {
		return TYPE;
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		ClientOperations.addRecoil(this.vertical, this.lateral);
	}
}
