package net.tslat.aoa3.common.packet.networkcodec;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

public interface NetworkCodec<T> {
    void encode(FriendlyByteBuf buf, T data);
    T decode(FriendlyByteBuf buf);

    NetworkCodec<Vec3> VEC3 = new NetworkCodec<Vec3>() {
        @Override
        public void encode(FriendlyByteBuf buf, Vec3 data) {
            buf.writeDouble(data.x);
            buf.writeDouble(data.y);
            buf.writeDouble(data.z);
        }

        @Override
        public Vec3 decode(FriendlyByteBuf buf) {
            return new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
        }
    };
}
