package net.tslat.aoa3.player.halo;

import net.minecraft.network.FriendlyByteBuf;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.HaloChangePacket;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public final class PlayerHaloContainer {
    private final EnumSet<HaloTypes> unlocked = EnumSet.noneOf(HaloTypes.class);
    private final AtomicReference<HaloTypes> current = new AtomicReference<>();

    private PlayerHaloContainer(Set<HaloTypes> unlocked) {
        this.unlocked.addAll(unlocked);

        if (!this.unlocked.isEmpty()) {
            this.current.set(this.unlocked.iterator().next());
        }
        else {
            this.current.set(HaloTypes.DONATOR);
        }
    }

    private PlayerHaloContainer(HaloTypes selected) {
        this.current.set(selected);
    }

    public static PlayerHaloContainer forUnlocked(Set<HaloTypes> unlocked) {
        return new PlayerHaloContainer(unlocked);
    }

    public static PlayerHaloContainer defaulted(HaloTypes selected) {
        return new PlayerHaloContainer(selected);
    }

    public PlayerHaloContainer mergeUpdatedMap(PlayerHaloContainer update) {
        this.unlocked.clear();
        this.unlocked.addAll(update.unlocked);

        return this;
    }

    public PlayerHaloContainer updateSelectedHalo(UUID player, HaloTypes selected, boolean sync) {
        if (this.unlocked.contains(selected))
            this.current.set(selected);

        if (sync)
            AoANetworking.sendToAllPlayers(new HaloChangePacket(player, selected));

        return this;
    }

    public HaloTypes getSelected() {
        return this.current.get();
    }

    public boolean isEmpty() {
        return this.unlocked.isEmpty();
    }

    public boolean hasHalo(HaloTypes halo) {
        return this.unlocked.contains(halo);
    }

    public void toNetwork(FriendlyByteBuf buffer) {
        buffer.writeEnumSet(this.unlocked, HaloTypes.class);
        buffer.writeEnum(this.current.get());
    }

    public static PlayerHaloContainer fromNetwork(FriendlyByteBuf buffer) {
        final PlayerHaloContainer container = new PlayerHaloContainer(buffer.readEnumSet(HaloTypes.class));

        container.current.set(buffer.readEnum(HaloTypes.class));

        return container;
    }
}
