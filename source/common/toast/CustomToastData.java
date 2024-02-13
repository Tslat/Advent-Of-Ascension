package net.tslat.aoa3.common.toast;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ToastPopupPacket;

public interface CustomToastData<S, V> {
    Type<? extends CustomToastData<S, V>> type();
    void write(FriendlyByteBuf buffer);
    void handleOnClient();
    default void sendToPlayer(ServerPlayer pl) {
        AoANetworking.sendToPlayer(pl, new ToastPopupPacket(this));
    }

    @FunctionalInterface
     interface Type<D extends CustomToastData<?, ?>> {
        D create(FriendlyByteBuf buffer);
    }
}